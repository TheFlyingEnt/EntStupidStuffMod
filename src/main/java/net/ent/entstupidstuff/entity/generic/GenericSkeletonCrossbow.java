package net.ent.entstupidstuff.entity.generic;

import org.jetbrains.annotations.Nullable;

import net.ent.entstupidstuff.entity.ai.TrackTargetGoal;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.provider.EnchantmentProviders;
import net.minecraft.entity.CrossbowUser;
import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.ai.goal.AvoidSunlightGoal;
import net.minecraft.entity.ai.goal.CrossbowAttackGoal;
import net.minecraft.entity.ai.goal.EscapeSunlightGoal;
import net.minecraft.entity.ai.goal.FleeEntityGoal;
import net.minecraft.entity.ai.goal.LookAroundGoal;
import net.minecraft.entity.ai.goal.LookAtEntityGoal;
import net.minecraft.entity.ai.goal.RevengeGoal;
import net.minecraft.entity.ai.goal.WanderAroundFarGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.passive.TurtleEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.inventory.StackReference;
import net.minecraft.item.BannerItem;
import net.minecraft.item.BowItem;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.RangedWeaponItem;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;

/*
*   This class is used as a refenece to Sea Skeleton
*   Any Mob refering to this will have the ability to Shoot Arrows Underwater
*   with a Crossbow with the Ability to Swim!!
*/

public class GenericSkeletonCrossbow extends GenericSkeletonBow implements CrossbowUser{

    private static final TrackedData<Boolean> CHARGING = DataTracker.registerData(GenericSkeletonCrossbow.class, TrackedDataHandlerRegistry.BOOLEAN);
	private final SimpleInventory inventory = new SimpleInventory(5);

    public GenericSkeletonCrossbow(EntityType<? extends GenericSkeletonCrossbow> entityType, World world) {
        super(entityType, world);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	protected void initGoals() {
		this.goalSelector.add(2, new AvoidSunlightGoal(this));
		this.goalSelector.add(3, new EscapeSunlightGoal(this, 1.0));
		this.goalSelector.add(3, new FleeEntityGoal(this, WolfEntity.class, 6.0F, 1.0, 1.2));
		this.goalSelector.add(4, new CrossbowAttackGoal<>(this, 1.0, 8.0F));

		this.goalSelector.add(5, new WanderAroundFarGoal(this, 1.0));
		this.goalSelector.add(6, new LookAtEntityGoal(this, MobEntity.class, 8.0F));
		this.goalSelector.add(6, new LookAroundGoal(this));
		this.goalSelector.add(6, new GenericSkeletonBow.GenericSkeletonBowSwimGoal(this));
		this.goalSelector.add(6, new TrackTargetGoal(this)); 

		this.targetSelector.add(1, new RevengeGoal(this));
		this.targetSelector.add(2, new ActiveTargetGoal(this, PlayerEntity.class, true));
		this.targetSelector.add(3, new ActiveTargetGoal(this, IronGolemEntity.class, true));
		this.targetSelector.add(3, new ActiveTargetGoal(this, TurtleEntity.class, 10, true, false, TurtleEntity.BABY_TURTLE_ON_LAND_FILTER));
	}

    public static enum State {
		CROSSED,
		ATTACKING,
		CROSSBOW_HOLD,
		CROSSBOW_CHARGE,
		NEUTRAL;
	}

    @Override
    public void postShoot() {
        this.despawnCounter = 0;
    }

    public static DefaultAttributeContainer.Builder createGenericSkeletonCrossbow() {
		return HostileEntity.createHostileAttributes()
			.add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.35F)
			.add(EntityAttributes.GENERIC_MAX_HEALTH, 24.0)
			.add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 5.0)
			.add(EntityAttributes.GENERIC_FOLLOW_RANGE, 32.0);
	}

    @Override
	protected void initDataTracker(DataTracker.Builder builder) {
		super.initDataTracker(builder);
		builder.add(CHARGING, false);
	}

	@Override
	public boolean canUseRangedWeapon(RangedWeaponItem weapon) {
		return weapon == Items.CROSSBOW;
	}

	public boolean isCharging() {
		return this.dataTracker.get(CHARGING);
	}

	@Override
	public void setCharging(boolean charging) {
		this.dataTracker.set(CHARGING, charging);
	}

    @Override
	public void writeCustomDataToNbt(NbtCompound nbt) {
		super.writeCustomDataToNbt(nbt);
	}

	public GenericSkeletonCrossbow.State getState() {
		if (this.isCharging()) {
			return GenericSkeletonCrossbow.State.CROSSBOW_CHARGE;
		} else if (this.isHolding(Items.CROSSBOW)) {
			return GenericSkeletonCrossbow.State.CROSSBOW_HOLD;
		} else {
			return this.isAttacking() ? GenericSkeletonCrossbow.State.ATTACKING : GenericSkeletonCrossbow.State.NEUTRAL;
		}
	}

    @Override
	public void readCustomDataFromNbt(NbtCompound nbt) {
		super.readCustomDataFromNbt(nbt);
		this.setCanPickUpLoot(true);
	}

	@Override
	public float getPathfindingFavor(BlockPos pos, WorldView world) {
		return 0.0F;
	}

	@Override
	public int getLimitPerChunk() {
		return 1;
	}

	@Nullable
	@Override
	public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData) {
		Random random = world.getRandom();
		this.initEquipment(random, difficulty);
		this.updateEnchantments(world, random, difficulty);
		return super.initialize(world, difficulty, spawnReason, entityData);
	}

	@Override
	protected void initEquipment(Random random, LocalDifficulty localDifficulty) {
		this.equipStack(EquipmentSlot.MAINHAND, new ItemStack(Items.CROSSBOW));
	}

	@Override
	protected void enchantMainHandItem(ServerWorldAccess world, Random random, LocalDifficulty localDifficulty) {
		super.enchantMainHandItem(world, random, localDifficulty);
		if (random.nextInt(300) == 0) {
			ItemStack itemStack = this.getMainHandStack();
			if (itemStack.isOf(Items.CROSSBOW)) {
				EnchantmentHelper.applyEnchantmentProvider(itemStack, world.getRegistryManager(), EnchantmentProviders.PILLAGER_SPAWN_CROSSBOW, localDifficulty, random);
			}
		}
	}

    //Sound Events
    @Override
	protected SoundEvent getAmbientSound() { //TODO: Replace with Sunken Skeleton Sound
		return SoundEvents.ENTITY_SKELETON_AMBIENT;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundEvents.ENTITY_SKELETON_DEATH;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundEvents.ENTITY_SKELETON_HURT;
	}

    @Override
	protected void loot(ItemEntity item) {
		ItemStack itemStack = item.getStack();
		if (itemStack.getItem() instanceof BannerItem) {
			super.loot(item);
		} 
	}

    @Override
	public StackReference getStackReference(int mappedIndex) {
		int i = mappedIndex - 300;
		return i >= 0 && i < this.inventory.size() ? StackReference.of(this.inventory, i) : super.getStackReference(mappedIndex);
	}

	@Override
	public void shootAt(LivingEntity target, float pullProgress) {
		ItemStack mainHandStack = this.getMainHandStack();

		if (mainHandStack.getItem() instanceof CrossbowItem) {
			ItemStack itemStack = this.getStackInHand(ProjectileUtil.getHandPossiblyHolding(this, Items.CROSSBOW));
			ItemStack itemStack2 = this.getProjectileType(itemStack);
			PersistentProjectileEntity persistentProjectileEntity = this.createArrowProjectile(itemStack2, pullProgress, itemStack);
			double d = target.getX() - this.getX();
			double e = target.getBodyY(0.3333333333333333) - persistentProjectileEntity.getY();
			double f = target.getZ() - this.getZ();
			double g = Math.sqrt(d * d + f * f);
			persistentProjectileEntity.setVelocity(d, e + g * 0.2F, f, 1.6F, (float)(14 - this.getWorld().getDifficulty().getId() * 4));
			this.playSound(SoundEvents.ENTITY_SKELETON_SHOOT, 1.0F, 1.0F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
			this.getWorld().spawnEntity(persistentProjectileEntity);
		}

		else if (mainHandStack.getItem() instanceof BowItem) {

			ItemStack itemStack = this.getStackInHand(ProjectileUtil.getHandPossiblyHolding(this, Items.BOW));
			ItemStack itemStack2 = this.getProjectileType(itemStack);
			PersistentProjectileEntity persistentProjectileEntity = this.createArrowProjectile(itemStack2, pullProgress, itemStack);
			double d = target.getX() - this.getX();
			double e = target.getBodyY(0.3333333333333333) - persistentProjectileEntity.getY();
			double f = target.getZ() - this.getZ();
			double g = Math.sqrt(d * d + f * f);
			persistentProjectileEntity.setVelocity(d, e + g * 0.2F, f, 1.6F, (float)(14 - this.getWorld().getDifficulty().getId() * 4));
			this.playSound(SoundEvents.ENTITY_SKELETON_SHOOT, 1.0F, 1.0F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
			this.getWorld().spawnEntity(persistentProjectileEntity);
		}


	}




}
