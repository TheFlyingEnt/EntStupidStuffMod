package net.ent.entstupidstuff.client.entity.mob;

import java.util.List;

import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.BreakDoorGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.MoveTowardsTargetGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.Evoker;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.raid.Raid;
import net.minecraft.world.entity.raid.Raider;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class RedStoneGolemEntity extends Raider {
    
    private static final EntityDataAccessor<Integer> ATTACK_TYPE = 
        SynchedEntityData.defineId(RedStoneGolemEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> ATTACK_TICK = 
        SynchedEntityData.defineId(RedStoneGolemEntity.class, EntityDataSerializers.INT);
    
    // Attack types
    public static final int NO_ATTACK = 0;
    public static final int SWEEP_ATTACK = 1;
    public static final int NORMAL_ATTACK = 2;
    
    // Attack timings (in ticks)
    private static final int SWEEP_ATTACK_DURATION = 15; // 0.75 seconds
    private static final int NORMAL_ATTACK_DURATION = 30; // 1.5 seconds
    
    private int attackCooldown = 0;
    
    // Animation states - these are used by the renderer
    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState walkAnimationState = new AnimationState();
    public final AnimationState sweepAttackAnimationState = new AnimationState();
    public final AnimationState normalAttackAnimationState = new AnimationState();

    private final RedStoneGolemPartEntity[] parts;
    public final RedStoneGolemPartEntity backPart;

    public RedStoneGolemEntity(EntityType<? extends Raider> entityType, Level world) {
        super(entityType, world);
        this.backPart = new RedStoneGolemPartEntity(this, 16F, 16F);
        this.parts = new RedStoneGolemPartEntity[]{this.backPart};
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(ATTACK_TYPE, NO_ATTACK);
        builder.define(ATTACK_TICK, 0);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Raider.createMobAttributes()
            .add(Attributes.MAX_HEALTH, 100.0)
            .add(Attributes.MOVEMENT_SPEED, 0.25)
            .add(Attributes.ATTACK_DAMAGE, 15.0)
            .add(Attributes.KNOCKBACK_RESISTANCE, 0.9)
            .add(Attributes.ARMOR, 8.0)
            .add(Attributes.FOLLOW_RANGE, 32.0);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new RedStoneGolemAttackGoal(this, 1.0, false));
        this.goalSelector.addGoal(2, new BreakDoorGoal(this, (difficulty) -> difficulty.getId() >= 1));
        this.goalSelector.addGoal(3, new MoveTowardsTargetGoal(this, 0.9, 32.0F));
        this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 0.6));
        this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));

        this.targetSelector.addGoal(1, new HurtByTargetGoal(this, Raider.class));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractVillager.class, false));
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, IronGolem.class, true));
    }

    @Override
    public void tick() {
        super.tick();
        
        if (this.attackCooldown > 0) {
            this.attackCooldown--;
        }
        
        int attackTick = this.getAttackTick();
        if (attackTick > 0) {
            this.setAttackTick(attackTick - 1);
            
            // Execute attack at specific tick
            if (this.getAttackType() == SWEEP_ATTACK && attackTick == 7) {
                this.performSweepAttack();
            } else if (this.getAttackType() == NORMAL_ATTACK && attackTick == 16) {
                this.performNormalAttack();
            }
            
            // Reset attack type when animation finishes
            if (attackTick == 1) {
                this.setAttackType(NO_ATTACK);
            }
        }
        
        // Handle idle animation - start it when not moving and not attacking
        this.idleAnimationState.startIfStopped(this.tickCount);

        // Update part positions
        this.updatePartPositions();
    }

    /**
     * Updates the positions of all part entities (like the back hitbox)
     */
    private void updatePartPositions() {
        if (this.parts != null) {
            // Position the back part behind the golem
            // Calculate position based on the golem's rotation
            float yaw = this.yBodyRot * ((float) Math.PI / 180F);
            
            // Offset backwards from center (negative because we want behind)
            double offsetX = -Math.sin(yaw) * -5.0; // 1.0 blocks behind
            double offsetZ = Math.cos(yaw) * -1.0;
            
            // Set back part position (behind and slightly lower)
            this.backPart.setPos(
                this.getX() + offsetX,
                this.getY() + 0.5, // Slightly lower than main body
                this.getZ() + offsetZ
            );

        }

    }



    public void startSweepAttack() {
        if (this.attackCooldown == 0 && this.getAttackType() == NO_ATTACK) {
            this.setAttackType(SWEEP_ATTACK);
            this.setAttackTick(SWEEP_ATTACK_DURATION);
            this.attackCooldown = 40; // 2 second cooldown
            this.playSound(SoundEvents.IRON_GOLEM_ATTACK, 1.0F, 1.0F);
        }
    }

    public void startNormalAttack() {
        if (this.attackCooldown == 0 && this.getAttackType() == NO_ATTACK) {
            this.setAttackType(NORMAL_ATTACK);
            this.setAttackTick(NORMAL_ATTACK_DURATION);
            this.attackCooldown = 60; // 3 second cooldown
            this.playSound(SoundEvents.IRON_GOLEM_ATTACK, 1.0F, 0.8F);
        }
    }

    private void performSweepAttack() {
        float range = 4.0F;
        float damage = 12.0F;
        float knockback = 2.5F;
        
        AABB attackBox = this.getBoundingBox().inflate(range, 2.0, range);
        List<LivingEntity> targets = this.level().getEntitiesOfClass(
            LivingEntity.class, 
            attackBox, 
            entity -> entity != this && this.canAttack(entity)
        );
        
        for (LivingEntity target : targets) {
            if (this.distanceTo(target) <= range) {
                target.hurt(this.damageSources().mobAttack(this), damage);
                
                // Apply knockback
                Vec3 direction = target.position().subtract(this.position()).normalize();
                target.push(direction.x * knockback, 0.4, direction.z * knockback);
                target.hurtMarked = true;
            }
        }
        
        this.playSound(SoundEvents.PLAYER_ATTACK_SWEEP, 1.0F, 1.0F);
    }

    private void performNormalAttack() {
        float range = 3.5F;
        float damage = 15.0F;
        
        AABB attackBox = this.getBoundingBox().inflate(range, 2.0, range);
        List<LivingEntity> targets = this.level().getEntitiesOfClass(
            LivingEntity.class, 
            attackBox, 
            entity -> entity != this && this.canAttack(entity)
        );
        
        for (LivingEntity target : targets) {
            if (this.distanceTo(target) <= range) {
                target.hurt(this.damageSources().mobAttack(this), damage);
            }
        }
        
        this.playSound(SoundEvents.IRON_GOLEM_DAMAGE, 1.0F, 1.0F);
    }

    @Override
    public boolean hurtServer(ServerLevel world, DamageSource source, float amount) {
		if (source.is(DamageTypeTags.IS_PROJECTILE)) {
			return false;
		}
		else {
			return super.hurtServer(world, source, amount);
		}
    }


    @Override
    public boolean canBeLeashed() {
        return false;
    }

    @Override
    protected boolean canRide(Entity entity) {
        return false;
    }

    // Allow Evokers to ride this entity
    @Override
    public boolean canAddPassenger(Entity passenger) {
        return passenger instanceof Evoker && super.canAddPassenger(passenger);
    }

    // Data accessors
    public int getAttackType() {
        return this.entityData.get(ATTACK_TYPE);
    }

    public void setAttackType(int type) {
        this.entityData.set(ATTACK_TYPE, type);
    }
    
    @Override
    public void onSyncedDataUpdated(EntityDataAccessor<?> entityDataAccessor) {
        if (ATTACK_TYPE.equals(entityDataAccessor)) {
            int attackType = this.getAttackType();
            // Stop all attack animations
            this.sweepAttackAnimationState.stop();
            this.normalAttackAnimationState.stop();
            
            // Start the appropriate animation
            if (attackType == SWEEP_ATTACK) {
                this.sweepAttackAnimationState.startIfStopped(this.tickCount);
            } else if (attackType == NORMAL_ATTACK) {
                this.normalAttackAnimationState.startIfStopped(this.tickCount);
            }
        }
        
        super.onSyncedDataUpdated(entityDataAccessor);
    }

    public int getAttackTick() {
        return this.entityData.get(ATTACK_TICK);
    }

    public void setAttackTick(int tick) {
        this.entityData.set(ATTACK_TICK, tick);
    }

    public boolean isAttacking() {
        return this.getAttackType() != NO_ATTACK;
    }

    @Override
    public void addAdditionalSaveData(ValueOutput view) {
        super.addAdditionalSaveData(view);
        view.putInt("AttackType", this.getAttackType());
        view.putInt("AttackTick", this.getAttackTick());
        view.putInt("AttackCooldown", this.attackCooldown);
    }

    @Override
    protected void readAdditionalSaveData(ValueInput view) {
        super.readAdditionalSaveData(view);
        this.setAttackType(view.getIntOr("AttackType", 1));
        this.setAttackTick(view.getIntOr("AttackTick", 1));
        this.attackCooldown = view.getIntOr("AttackCooldown", 1);
    }

    @Override
    public void applyRaidBuffs(ServerLevel serverLevel, int wave, boolean bl) {
        // Apply raid buffs based on wave
        Raid raid = this.getCurrentRaid();
        if (raid != null) {
            // Give additional health and damage on higher waves
            this.setHealth(this.getMaxHealth());
        }
    }

    @Override
    public SoundEvent getCelebrateSound() {
        return SoundEvents.PILLAGER_CELEBRATE;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.IRON_GOLEM_STEP;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.IRON_GOLEM_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.IRON_GOLEM_DEATH;
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
        this.playSound(SoundEvents.IRON_GOLEM_STEP, 1.0F, 1.0F);
    }

    // Custom attack goal
    static class RedStoneGolemAttackGoal extends MeleeAttackGoal {
        private final RedStoneGolemEntity golem;
        private int ticksSinceLastAttack = 0;

        public RedStoneGolemAttackGoal(RedStoneGolemEntity golem, double speed, boolean pauseWhenMobIdle) {
            super(golem, speed, pauseWhenMobIdle);
            this.golem = golem;
        }

        @Override
        public void tick() {
            super.tick();
            this.ticksSinceLastAttack++;
        }

        @Override
        protected void checkAndPerformAttack(LivingEntity target) {
            if (this.canPerformAttack(target)) {
                this.resetAttackCooldown();
                
                // Randomly choose attack type
                if (this.golem.getRandom().nextFloat() < 0.4F) {
                    this.golem.startSweepAttack();
                } else {
                    this.golem.startNormalAttack();
                }
                
                this.ticksSinceLastAttack = 0;
            }
        }

        @Override
        protected boolean canPerformAttack(LivingEntity target) {
            double attackReach = this.golem.getBbWidth() * 2.0F * this.golem.getBbWidth() * 2.0F + target.getBbWidth();
            return this.isTimeToAttack() 
                && this.golem.distanceToSqr(target) <= attackReach
                && !this.golem.isAttacking();
        }

        @Override
        public boolean canUse() {
            return super.canUse() && !this.golem.isAttacking();
        }

        @Override
        public boolean canContinueToUse() {
            return super.canContinueToUse() && !this.golem.isAttacking();
        }
    }
}
