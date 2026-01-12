package net.ent.entstupidstuff.client.entity.mob;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.monster.Blaze;
import net.minecraft.world.level.Level;

public class HoveringInfernoEntity extends Blaze{

    public HoveringInfernoEntity(EntityType<? extends Blaze> entityType, Level world) {
        super(entityType, world);
    }

    private static final EntityDataAccessor<Boolean> ATTACKING =
	SynchedEntityData.defineId(HoveringInfernoEntity.class, EntityDataSerializers.BOOLEAN);

    public final  AnimationState attackAnimationState = new AnimationState();
	public int attackAnimationTimeout = 0;

	public final  AnimationState idleAnimationState = new AnimationState();
	private int idleAnimationTimeout = 0;

    @Override
    public void tick() {
        super.tick();
        if(this.level().isClientSide()) {
            setupAnimationStates();
        }
    }

    


    //// Animation ////

    private void setupAnimationStates() {
        if (this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = this.random.nextInt(40) + 80;
			System.out.println("Idle");
            //this.idleAnimationState.start(this.age);
        } else {
            --this.idleAnimationTimeout;
        }

        if (this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = this.random.nextInt(40) + 80;
            this.idleAnimationState.start(this.tickCount);
          }
        else
            --this.idleAnimationTimeout;


        if(this.isAggressive() && attackAnimationTimeout <= 0) {
            attackAnimationTimeout = 25;
			System.out.println("Attacking");
            attackAnimationState.start(this.tickCount);
        } else {
            --this.attackAnimationTimeout;
        }

        //HoveringInfernoEntity.idleAnimationState.start(this.age);
        //this.idleAnimationState.setRunning(true, this.age);

        //System.out.println("nope");
        
        /*if(!this.isAttacking()) {
			System.out.println("stop");
            attackAnimationState.stop();
        }*/
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
        
		builder.define(ATTACKING, false);
    }

    @Override
    protected void updateWalkAnimation(float posDelta) {
        float f = this.getPose() == Pose.STANDING ? Math.min(posDelta * 6.0f, 1.0f) : 0.0f;
        this.walkAnimation.update(f, 0.2f, 1);
    }

}
