package net.ent.entstupidstuff.client.entity.mob;

import java.util.EnumSet;
import java.util.function.Predicate;

import net.ent.entstupidstuff.client.entity.ai.RedStoneGolemAttackGoal;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.AbstractIllager;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.raid.Raid;
import net.minecraft.world.entity.raid.Raider;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.providers.EnchantmentProvider;
import net.minecraft.world.item.enchantment.providers.VanillaEnchantmentProviders;
import net.minecraft.world.level.Level;

public class RedStoneGolemEntity extends AbstractIllager{

	static final Predicate<Difficulty> DIFFICULTY_ALLOWS_DOOR_BREAKING_PREDICATE = difficulty -> difficulty == Difficulty.NORMAL || difficulty == Difficulty.HARD;



	//Animation

	private static final EntityDataAccessor<Boolean> ATTACKING =
	SynchedEntityData.defineId(RedStoneGolemEntity.class, EntityDataSerializers.BOOLEAN);

	//private static final TrackedData<Boolean> IDLE =
	//DataTracker.registerData(RSGEntity.class, TrackedDataHandlerRegistry.BOOLEAN);

	public final  AnimationState attackAnimationState = new AnimationState();
	public int attackAnimationTimeout = 0;

	public final  AnimationState idleAnimationState = new AnimationState();
	private int idleAnimationTimeout = 0;

	
	//Default Code
	public RedStoneGolemEntity(EntityType<? extends AbstractIllager> entityType, Level world) {
        super(entityType, world);
    }

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(0, new FloatGoal(this));
		this.goalSelector.addGoal(1, new RedStoneGolemEntity.BreakDoorGoal(this));
		this.goalSelector.addGoal(2, new AbstractIllager.RaiderOpenDoorGoal(this));
		this.goalSelector.addGoal(3, new Raider.HoldGroundAttackGoal(this, 10.0F));
		//this.goalSelector.add(4, new RSGAttackGoal(this, 1.0, false, 40)); //- Fix then Add
		//this.goalSelector.add(4, new RSGAttackGoal(this, 1.0, false, 40)); //- Fix then Add

		this.goalSelector.addGoal(4, new RedStoneGolemAttackGoal(this, 1D, false));

		this.targetSelector.addGoal(1, new HurtByTargetGoal(this, Raider.class).setAlertOthers());
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal(this, Player.class, true));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal(this, AbstractVillager.class, true));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal(this, IronGolem.class, true));
		this.targetSelector.addGoal(4, new RedStoneGolemEntity.TargetGoal(this));
		this.goalSelector.addGoal(8, new RandomStrollGoal(this, 0.6));
		this.goalSelector.addGoal(9, new LookAtPlayerGoal(this, Player.class, 3.0F, 1.0F));
		this.goalSelector.addGoal(10, new LookAtPlayerGoal(this, Mob.class, 8.0F));

	}

	///////////////

	public static AttributeSupplier.Builder createVindicatorAttributes() {
		return Monster.createMonsterAttributes()
			.add(Attributes.MOVEMENT_SPEED, 0.25F) //was 0.35
			.add(Attributes.FOLLOW_RANGE, 12.0)
			.add(Attributes.MAX_HEALTH, 24.0)
			.add(Attributes.ATTACK_DAMAGE, 5.0);
	}

    @Override
	public void applyRaidBuffs(ServerLevel world, int wave, boolean unused) {
		ItemStack itemStack = new ItemStack(Items.IRON_AXE);
		Raid raid = this.getCurrentRaid();
		boolean bl = this.random.nextFloat() <= raid.getEnchantOdds();
		if (bl) {
			ResourceKey<EnchantmentProvider> registryKey = wave > raid.getNumGroups(Difficulty.NORMAL)
				? VanillaEnchantmentProviders.RAID_VINDICATOR_POST_WAVE_5
				: VanillaEnchantmentProviders.RAID_VINDICATOR;
			EnchantmentHelper.enchantItemFromProvider(itemStack, world.registryAccess(), registryKey, world.getCurrentDifficultyAt(this.blockPosition()), this.random);
		}

		this.setItemSlot(EquipmentSlot.MAINHAND, itemStack);
	}

    @Override
	public SoundEvent getCelebrateSound() {
		return SoundEvents.VINDICATOR_CELEBRATE;
	}

    static class BreakDoorGoal extends net.minecraft.world.entity.ai.goal.BreakDoorGoal {
		public BreakDoorGoal(Mob mobEntity) {
			super(mobEntity, 6, RedStoneGolemEntity.DIFFICULTY_ALLOWS_DOOR_BREAKING_PREDICATE);
			this.setFlags(EnumSet.of(Goal.Flag.MOVE));
		}

		@Override
		public boolean canContinueToUse() {
			RedStoneGolemEntity rsgEntity = (RedStoneGolemEntity)this.mob;
			return rsgEntity.hasActiveRaid() && super.canContinueToUse();
		}

		@Override
		public boolean canUse() {
			RedStoneGolemEntity rsgEntity = (RedStoneGolemEntity)this.mob;
			return rsgEntity.hasActiveRaid() && rsgEntity.random.nextInt(reducedTickDelay(10)) == 0 && super.canUse();
		}

		@Override
		public void start() {
			super.start();
			this.mob.setNoActionTime(0);
		}
	}

	static class TargetGoal extends NearestAttackableTargetGoal<LivingEntity> {
		public TargetGoal(RedStoneGolemEntity vindicator) {
			super(vindicator, LivingEntity.class, 0, true, true, (target, world) -> target.attackable());
		}

		@Override
		public void start() {
			super.start();
			this.mob.setNoActionTime(0);
		}
	}

	///////////////////////

	//Animation
	private void setupAnimationStates() {
        if (this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = this.random.nextInt(40) + 80;
			System.out.println("Idle");
            this.idleAnimationState.start(this.tickCount);
        } else {
            --this.idleAnimationTimeout;
        }

        if(this.isAggressive() && attackAnimationTimeout <= 0) {
            attackAnimationTimeout = 20;//40;
			System.out.println("Attacking");
            attackAnimationState.start(this.tickCount);
        } else {
            --this.attackAnimationTimeout;
        }

        if(!this.isAggressive()) {
			System.out.println("stop");
            attackAnimationState.stop();
        }
    }

	@Override
    protected void updateWalkAnimation(float posDelta) {
        float f = this.getPose() == Pose.STANDING ? Math.min(posDelta * 6.0f, 1.0f) : 0.0f;
        this.walkAnimation.update(f, 0.2f, 1);
    }

    @Override
    public void tick() {
        super.tick();
        if(this.level().isClientSide()) {
            setupAnimationStates();
        }
    }

	public void setAggressive(boolean attacking) {
        this.entityData.set(ATTACKING, attacking);
    }

    @Override
    public boolean isAggressive() {
        return this.entityData.get(ATTACKING);
    }

    @Override
    public void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
		//this.dataTracker.set(ATTACKING, false, false);

		builder.define(ATTACKING, false);

		// DataTracker.startTracking(TrackedData<T>, T)
		//this.attackAnimationState.start()
        //this.dataTracker.startTracking(ATTACKING, false);
		//dataTracker.set(ATTACKING, false);
		//this.dataTracker.set(ATTACKING, false);
    }



}
