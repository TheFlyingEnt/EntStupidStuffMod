package net.ent.entstupidstuff.entity.mob;

import java.util.EnumSet;
import java.util.function.Predicate;

import net.ent.entstupidstuff.entity.ai.RedStoneGolemAttackGoal;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.provider.EnchantmentProvider;
import net.minecraft.enchantment.provider.EnchantmentProviders;
import net.minecraft.entity.AnimationState;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.NavigationConditions;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.LookAtEntityGoal;
import net.minecraft.entity.ai.goal.RevengeGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WanderAroundGoal;
import net.minecraft.entity.ai.pathing.MobNavigation;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.IllagerEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.passive.MerchantEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.raid.RaiderEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKey;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.village.raid.Raid;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;

public class RedStoneGolemEntity extends IllagerEntity{

	static final Predicate<Difficulty> DIFFICULTY_ALLOWS_DOOR_BREAKING_PREDICATE = difficulty -> difficulty == Difficulty.NORMAL || difficulty == Difficulty.HARD;



	//Animation

	private static final TrackedData<Boolean> ATTACKING =
	DataTracker.registerData(RedStoneGolemEntity.class, TrackedDataHandlerRegistry.BOOLEAN);

	//private static final TrackedData<Boolean> IDLE =
	//DataTracker.registerData(RSGEntity.class, TrackedDataHandlerRegistry.BOOLEAN);

	public final  AnimationState attackAnimationState = new AnimationState();
	public int attackAnimationTimeout = 0;

	public final  AnimationState idleAnimationState = new AnimationState();
	private int idleAnimationTimeout = 0;

	
	//Default Code
	public RedStoneGolemEntity(EntityType<? extends IllagerEntity> entityType, World world) {
        super(entityType, world);
    }

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void initGoals() {
		super.initGoals();
		this.goalSelector.add(0, new SwimGoal(this));
		this.goalSelector.add(1, new RedStoneGolemEntity.BreakDoorGoal(this));
		this.goalSelector.add(2, new IllagerEntity.LongDoorInteractGoal(this));
		this.goalSelector.add(3, new RaiderEntity.PatrolApproachGoal(this, 10.0F));
		//this.goalSelector.add(4, new RSGAttackGoal(this, 1.0, false, 40)); //- Fix then Add
		//this.goalSelector.add(4, new RSGAttackGoal(this, 1.0, false, 40)); //- Fix then Add

		this.goalSelector.add(4, new RedStoneGolemAttackGoal(this, 1D, false));

		this.targetSelector.add(1, new RevengeGoal(this, RaiderEntity.class).setGroupRevenge());
		this.targetSelector.add(2, new ActiveTargetGoal(this, PlayerEntity.class, true));
		this.targetSelector.add(3, new ActiveTargetGoal(this, MerchantEntity.class, true));
		this.targetSelector.add(3, new ActiveTargetGoal(this, IronGolemEntity.class, true));
		this.targetSelector.add(4, new RedStoneGolemEntity.TargetGoal(this));
		this.goalSelector.add(8, new WanderAroundGoal(this, 0.6));
		this.goalSelector.add(9, new LookAtEntityGoal(this, PlayerEntity.class, 3.0F, 1.0F));
		this.goalSelector.add(10, new LookAtEntityGoal(this, MobEntity.class, 8.0F));

	}

	@Override
	public void mobTick() {
		if (!this.isAiDisabled() && NavigationConditions.hasMobNavigation(this)) {
			boolean bl = ((ServerWorld)this.getWorld()).hasRaidAt(this.getBlockPos());
			((MobNavigation)this.getNavigation()).setCanPathThroughDoors(bl);
		}
		super.mobTick();
	}

	///////////////

	public static DefaultAttributeContainer.Builder createVindicatorAttributes() {
		return HostileEntity.createHostileAttributes()
			.add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.25F) //was 0.35
			.add(EntityAttributes.GENERIC_FOLLOW_RANGE, 12.0)
			.add(EntityAttributes.GENERIC_MAX_HEALTH, 24.0)
			.add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 5.0);
	}

    @Override
	public void addBonusForWave(ServerWorld world, int wave, boolean unused) {
		ItemStack itemStack = new ItemStack(Items.IRON_AXE);
		Raid raid = this.getRaid();
		boolean bl = this.random.nextFloat() <= raid.getEnchantmentChance();
		if (bl) {
			RegistryKey<EnchantmentProvider> registryKey = wave > raid.getMaxWaves(Difficulty.NORMAL)
				? EnchantmentProviders.VINDICATOR_POST_WAVE_5_RAID
				: EnchantmentProviders.VINDICATOR_RAID;
			EnchantmentHelper.applyEnchantmentProvider(itemStack, world.getRegistryManager(), registryKey, world.getLocalDifficulty(this.getBlockPos()), this.random);
		}

		this.equipStack(EquipmentSlot.MAINHAND, itemStack);
	}

    @Override
	public SoundEvent getCelebratingSound() {
		return SoundEvents.ENTITY_VINDICATOR_CELEBRATE;
	}

    static class BreakDoorGoal extends net.minecraft.entity.ai.goal.BreakDoorGoal {
		public BreakDoorGoal(MobEntity mobEntity) {
			super(mobEntity, 6, RedStoneGolemEntity.DIFFICULTY_ALLOWS_DOOR_BREAKING_PREDICATE);
			this.setControls(EnumSet.of(Goal.Control.MOVE));
		}

		@Override
		public boolean shouldContinue() {
			RedStoneGolemEntity rsgEntity = (RedStoneGolemEntity)this.mob;
			return rsgEntity.hasActiveRaid() && super.shouldContinue();
		}

		@Override
		public boolean canStart() {
			RedStoneGolemEntity rsgEntity = (RedStoneGolemEntity)this.mob;
			return rsgEntity.hasActiveRaid() && rsgEntity.random.nextInt(toGoalTicks(10)) == 0 && super.canStart();
		}

		@Override
		public void start() {
			super.start();
			this.mob.setDespawnCounter(0);
		}
	}

	static class TargetGoal extends ActiveTargetGoal<LivingEntity> {
		public TargetGoal(RedStoneGolemEntity vindicator) {
			super(vindicator, LivingEntity.class, 0, true, true, LivingEntity::isMobOrPlayer);
		}

		@Override
		public void start() {
			super.start();
			this.mob.setDespawnCounter(0);
		}
	}

	///////////////////////

	//Animation
	private void setupAnimationStates() {
        if (this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = this.random.nextInt(40) + 80;
			System.out.println("Idle");
            this.idleAnimationState.start(this.age);
        } else {
            --this.idleAnimationTimeout;
        }

        if(this.isAttacking() && attackAnimationTimeout <= 0) {
            attackAnimationTimeout = 20;//40;
			System.out.println("Attacking");
            attackAnimationState.start(this.age);
        } else {
            --this.attackAnimationTimeout;
        }

        if(!this.isAttacking()) {
			System.out.println("stop");
            attackAnimationState.stop();
        }
    }

	@Override
    protected void updateLimbs(float posDelta) {
        float f = this.getPose() == EntityPose.STANDING ? Math.min(posDelta * 6.0f, 1.0f) : 0.0f;
        this.limbAnimator.updateLimbs(f, 0.2f);
    }

    @Override
    public void tick() {
        super.tick();
        if(this.getWorld().isClient()) {
            setupAnimationStates();
        }
    }

	public void setAttacking(boolean attacking) {
        this.dataTracker.set(ATTACKING, attacking);
    }

    @Override
    public boolean isAttacking() {
        return this.dataTracker.get(ATTACKING);
    }

    @Override
    public void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
		//this.dataTracker.set(ATTACKING, false, false);

		builder.add(ATTACKING, false);

		// DataTracker.startTracking(TrackedData<T>, T)
		//this.attackAnimationState.start()
        //this.dataTracker.startTracking(ATTACKING, false);
		//dataTracker.set(ATTACKING, false);
		//this.dataTracker.set(ATTACKING, false);
    }



}
