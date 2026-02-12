package net.ent.entstupidstuff.client.entity.mob;

import org.jetbrains.annotations.Nullable;

import net.ent.entstupidstuff.client.entity.ai.SporeperIgniteGoal;
import net.ent.entstupidstuff.effects.ModEffects;
import net.ent.entstupidstuff.particle.ParticleTypesFactory;
import net.ent.entstupidstuff.sound.SoundFactory;
import net.minecraft.core.particles.ExplosionParticleInfo;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.Mth;
import net.minecraft.util.random.WeightedList;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.entity.animal.Ocelot;
import net.minecraft.world.entity.animal.goat.Goat;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;

public class SporeperEntity extends Monster {
	private static final EntityDataAccessor<Integer> FUSE_SPEED = SynchedEntityData.defineId(SporeperEntity.class, EntityDataSerializers.INT);
	private static final EntityDataAccessor<Boolean> CHARGED = SynchedEntityData.defineId(SporeperEntity.class, EntityDataSerializers.BOOLEAN);
	private static final EntityDataAccessor<Boolean> IGNITED = SynchedEntityData.defineId(SporeperEntity.class, EntityDataSerializers.BOOLEAN);

	private int lastFuseTime;
	private int currentFuseTime;
	private int fuseTime = 30;
	private int explosionRadius = 3;
	private boolean headsDropped;

	public SporeperEntity(EntityType<? extends SporeperEntity> entityType, Level world) {
		super(entityType, world);

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
	protected void registerGoals() {
		this.goalSelector.addGoal(1, new FloatGoal(this));
		this.goalSelector.addGoal(2, new SporeperIgniteGoal(this));
		this.goalSelector.addGoal(3, new AvoidEntityGoal(this, Ocelot.class, 6.0F, 1.0, 1.2));
		this.goalSelector.addGoal(3, new AvoidEntityGoal(this, Cat.class, 6.0F, 1.0, 1.2));
		this.goalSelector.addGoal(4, new MeleeAttackGoal(this, 1.0, false));
		this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 0.8));
		this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));
		this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
		this.targetSelector.addGoal(1, new NearestAttackableTargetGoal(this, Player.class, true));
		this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
	}

	public static AttributeSupplier.Builder createCreeperAttributes() {
		return Monster.createMonsterAttributes().add(Attributes.MOVEMENT_SPEED, 0.25);
	}

	@Override
	public int getMaxFallDistance() {
		return this.getTarget() == null ? this.getComfortableFallDistance(0.0F) : this.getComfortableFallDistance(this.getHealth() - 1.0F);
	}

	@Override
	public boolean causeFallDamage(double fallDistance, float damagePerDistance, DamageSource damageSource) {
		boolean bl = super.causeFallDamage(fallDistance, damagePerDistance, damageSource);
		this.currentFuseTime += (int)(fallDistance * 1.5);
		if (this.currentFuseTime > this.fuseTime - 5) {
			this.currentFuseTime = this.fuseTime - 5;
		}

		return bl;
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.Builder builder) {
		super.defineSynchedData(builder);
		builder.define(FUSE_SPEED, -1);
		builder.define(CHARGED, false);
		builder.define(IGNITED, false);
	}

	@Override
	protected void addAdditionalSaveData(ValueOutput view) {
		super.addAdditionalSaveData(view);
		view.putBoolean("powered", this.isCharged());
		view.putShort("Fuse", (short)this.fuseTime);
		view.putByte("ExplosionRadius", (byte)this.explosionRadius);
		view.putBoolean("ignited", this.isIgnited());
	}

	@Override
	protected void readAdditionalSaveData(ValueInput view) {
		super.readAdditionalSaveData(view);
		this.entityData.set(CHARGED, view.getBooleanOr("powered", false));
		this.fuseTime = view.getShortOr("Fuse", (short)30);
		this.explosionRadius = view.getByteOr("ExplosionRadius", (byte)3);
		if (view.getBooleanOr("ignited", false)) {
			this.ignite();
		}
	}

	@Override
	public void tick() {
		if (this.isAlive()) {
			this.lastFuseTime = this.currentFuseTime;
			if (this.isIgnited()) {
				this.setFuseSpeed(1);
			}

			int i = this.getFuseSpeed();
			if (i > 0 && this.currentFuseTime == 0) {
				this.playSound(SoundEvents.CREEPER_PRIMED, 1.0F, 0.5F);
				this.gameEvent(GameEvent.PRIME_FUSE);
			}

			this.currentFuseTime += i;
			if (this.currentFuseTime < 0) {
				this.currentFuseTime = 0;
			}

			if (this.currentFuseTime >= this.fuseTime) {
				this.currentFuseTime = this.fuseTime;
				this.explode();
			}
		}

		super.tick();
	}

	@Override
	public void setTarget(@Nullable LivingEntity target) {
		if (!(target instanceof Goat)) {
			super.setTarget(target);
		}
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundEvents.CREEPER_HURT;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundEvents.CREEPER_DEATH;
	}

	@Override
	public boolean killedEntity(ServerLevel world, LivingEntity other, DamageSource damageSource) {
		if (this.shouldDropLoot(world) && this.isCharged() && !this.headsDropped) {
			other.dropFromLootTable(world, damageSource, false, BuiltInLootTables.CHARGED_CREEPER, stack -> {
				other.spawnAtLocation(world, stack);
				this.headsDropped = true;
			});
		}

		return super.killedEntity(world, other, damageSource);
	}

	@Override
	public boolean doHurtTarget(ServerLevel world, Entity target) {
		return true;
	}

	public boolean isCharged() {
		return this.entityData.get(CHARGED);
	}

	public float getLerpedFuseTime(float tickProgress) {
		return Mth.lerp(tickProgress, (float)this.lastFuseTime, (float)this.currentFuseTime) / (this.fuseTime - 2);
	}

	public int getFuseSpeed() {
		return this.entityData.get(FUSE_SPEED);
	}

	public void setFuseSpeed(int fuseSpeed) {
		this.entityData.set(FUSE_SPEED, fuseSpeed);
	}

	@Override
	public void thunderHit(ServerLevel world, LightningBolt lightning) {
		super.thunderHit(world, lightning);
		this.entityData.set(CHARGED, true);
	}

	@Override
	protected InteractionResult mobInteract(Player player, InteractionHand hand) {
		ItemStack itemStack = player.getItemInHand(hand);
		if (itemStack.is(ItemTags.CREEPER_IGNITERS)) {
			SoundEvent soundEvent = itemStack.is(Items.FIRE_CHARGE) ? SoundEvents.FIRECHARGE_USE : SoundEvents.FLINTANDSTEEL_USE;
			this.level()
				.playSound(player, this.getX(), this.getY(), this.getZ(), soundEvent, this.getSoundSource(), 1.0F, this.random.nextFloat() * 0.4F + 0.8F);
			if (!this.level().isClientSide()) {
				this.ignite();
				if (!itemStack.isDamageableItem()) {
					itemStack.shrink(1);
				} else {
					itemStack.hurtAndBreak(1, player, hand.asEquipmentSlot());
				}
			}

			return InteractionResult.SUCCESS;
		} else {
			return super.mobInteract(player, hand);
		}
	}

    private static final WeightedList<ExplosionParticleInfo> EXPLOSION_BLOCK_PARTICLES = WeightedList.<ExplosionParticleInfo>builder()
		.add(new ExplosionParticleInfo(ParticleTypes.POOF, 0.5F, 1.0F))
		.add(new ExplosionParticleInfo(ParticleTypes.SMOKE, 1.0F, 1.0F))
		.add(new ExplosionParticleInfo(ParticleTypesFactory.FALLING_MUSHROOM_SPORE, 1.0F, 1.0F))
		.build();

	private void explode() {
		if (this.level() instanceof ServerLevel serverWorld) {
			float f = this.isCharged() ? 2.0F : 1.0F;
			this.dead = true;
            serverWorld.explode(
			this.asLivingEntity(),
			Explosion.getDefaultDamageSource(serverWorld, this.asLivingEntity()),
			null,
			this.getX(),
			this.getY(),
			this.getZ(),
			this.explosionRadius * f,
			false,
			Level.ExplosionInteraction.MOB,
			ParticleTypes.EXPLOSION,
			ParticleTypes.EXPLOSION_EMITTER,
			EXPLOSION_BLOCK_PARTICLES,
			SoundFactory.ENTITY_SPOREPER_EXPLODE
		);


			this.spawnEffectsCloud();
			this.triggerOnDeathMobEffects(serverWorld, Entity.RemovalReason.KILLED);
			this.discard();
		}
	}

	private void spawnEffectsCloud() {

        AreaEffectCloud cloud = new AreaEffectCloud(
            this.level(),
            this.getX(),
            this.getY(),
            this.getZ()
        );

        cloud.setRadius(3.0F);
        cloud.setRadiusOnUse(-0.5F);
        cloud.setWaitTime(10);
        cloud.setDuration(200); // 10 seconds
        cloud.setRadiusPerTick(-cloud.getRadius() / cloud.getDuration());

        cloud.addEffect(new MobEffectInstance(
            ModEffects.HALLUC,
            200,   // effect duration
            0      // amplifier
        ));

	}

	public boolean isIgnited() {
		return this.entityData.get(IGNITED);
	}

	public void ignite() {
		this.entityData.set(IGNITED, true);
	}
}

