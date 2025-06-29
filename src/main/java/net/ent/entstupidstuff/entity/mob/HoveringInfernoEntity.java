package net.ent.entstupidstuff.entity.mob;

import net.minecraft.entity.AnimationState;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.BlazeEntity;
import net.minecraft.world.World;

public class HoveringInfernoEntity extends BlazeEntity{

    public HoveringInfernoEntity(EntityType<? extends BlazeEntity> entityType, World world) {
        super(entityType, world);
    }

    private static final TrackedData<Boolean> ATTACKING =
	DataTracker.registerData(HoveringInfernoEntity.class, TrackedDataHandlerRegistry.BOOLEAN);

    public final  AnimationState attackAnimationState = new AnimationState();
	public int attackAnimationTimeout = 0;

	public final  AnimationState idleAnimationState = new AnimationState();
	private int idleAnimationTimeout = 0;

    @Override
    public void tick() {
        super.tick();
        if(this.getWorld().isClient()) {
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
            this.idleAnimationState.start(this.age);
          }
        else
            --this.idleAnimationTimeout;


        if(this.isAttacking() && attackAnimationTimeout <= 0) {
            attackAnimationTimeout = 25;
			System.out.println("Attacking");
            attackAnimationState.start(this.age);
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
        
		builder.add(ATTACKING, false);
    }

    @Override
    protected void updateLimbs(float posDelta) {
        float f = this.getPose() == EntityPose.STANDING ? Math.min(posDelta * 6.0f, 1.0f) : 0.0f;
        this.limbAnimator.updateLimbs(f, 0.2f);
    }

}
