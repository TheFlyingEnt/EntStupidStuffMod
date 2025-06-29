package net.ent.entstupidstuff.entity.ai;

//import net.ent.entstupidstuff.entity.SunkenSkeletonEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.BowAttackGoal;
import net.minecraft.entity.mob.SkeletonEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.item.BowItem;
import net.minecraft.item.Items;

//ENT EDIT: I changed the SunkenSkeletonEntity to SkeletonEntity 8/1 12:48am - UnTested
public class UnderwaterBowAttackGoal<T extends /*SunkenSkeletonEntity*/ SkeletonEntity> extends /*Goal*/BowAttackGoal<T> {

    private final T skeleton;
    private final double speed;
    private int attackInterval;
    private final float maxRange;
    private int attackCooldown;
    private int seeTime;
    private boolean movingToLeft;
    private boolean strafingClockwise;
    private int combatTicks = -1;

    public UnderwaterBowAttackGoal(T skeleton, double speed, int attackInterval, float maxRange) {
        super(skeleton, speed, attackInterval, maxRange);
        this.skeleton = skeleton;
        this.speed = speed;
        this.attackInterval = attackInterval;
        this.maxRange = maxRange * maxRange;
    }

   /*@Override
    public boolean canStart() {
        return this.skeleton.getTarget() != null && this.isHoldingBow();
    }

    @Override
    protected boolean isHoldingBow() {
        return this.skeleton.isHolding(Items.BOW);
    }

    @Override
    public boolean shouldContinue() {
        return (this.canStart() || !this.skeleton.getNavigation().isIdle()) && this.isHoldingBow();
    }

    @Override
    public void start() {
        super.start();
        this.skeleton.setAttacking(true);
    }

    @Override
    public void stop() {
        super.stop();
        this.skeleton.setAttacking(false);
        this.seeTime = 0;
        this.attackCooldown = -1;
        this.skeleton.clearActiveItem();
    }

    @Override
    public boolean shouldRunEveryTick() {
        return true;
    } */ 

    public void setAttackInterval(int attackInterval) {
		this.attackInterval = attackInterval;
	}

	@Override
	public boolean canStart() {
		return this.skeleton.getTarget() == null ? false : this.isHoldingBow();
	}

	protected boolean isHoldingBow() {
		return this.skeleton.isHolding(Items.BOW);
	}

	@Override
	public boolean shouldContinue() {
		return (this.canStart() || !this.skeleton.getNavigation().isIdle()) && this.isHoldingBow();
	}

	@Override
	public void start() {
		super.start();
		this.skeleton.setAttacking(true);
	}

	@Override
	public void stop() {
		super.stop();
		this.skeleton.setAttacking(false);
		this.seeTime = 0;
		this.attackCooldown = -1;
		this.skeleton.clearActiveItem();
	}

	@Override
	public boolean shouldRunEveryTick() {
		return true;
	}

    @Override
    public void tick() {
        LivingEntity target = this.skeleton.getTarget();
        if (target != null) {
            double distance = this.skeleton.squaredDistanceTo(target.getX(), target.getY(), target.getZ());
            boolean canSee = this.skeleton.getVisibilityCache().canSee(target);
            boolean wasSeeing = this.seeTime > 0;

            //Testing
            this.skeleton.getLookControl().lookAt(target, 30.0F, 30.0F);
            this.skeleton.lookAtEntity(target, 30.0F, 30.0F);

            if (canSee != wasSeeing) {
                this.seeTime = 0;
            }

            if (canSee) {
                this.seeTime++;
            } else {
                this.seeTime--;
            }

            if (/*distance <= this.maxRange*/ !(distance > (double)this.maxRange) && this.seeTime >= 20) {
                this.skeleton.getNavigation().stop();
                this.combatTicks++;
            } else {
                this.skeleton.getNavigation().startMovingTo(target, this.speed);
                this.combatTicks = -1;
            }

            if (this.combatTicks >= 20) {
                if (this.skeleton.getRandom().nextFloat() < 0.3D) {
                    this.movingToLeft = !this.movingToLeft;
                }

                if (this.skeleton.getRandom().nextFloat() < 0.3D) {
                    this.strafingClockwise = !this.strafingClockwise;
                }

                this.combatTicks = 0;
            }

            if (this.combatTicks > -1) {
                if (distance > this.maxRange * 0.75F) {
                    this.strafingClockwise = false;
                } else if (distance < this.maxRange * 0.25F) {
                    this.strafingClockwise = true;
                }

                this.skeleton.getMoveControl().strafeTo(this.movingToLeft ? -0.5F : 0.5F, this.strafingClockwise ? 0.5F : -0.5F);
                this.skeleton.lookAtEntity(target, 30.0F, 30.0F);
            } else {
                this.skeleton.getLookControl().lookAt(target, 30.0F, 30.0F);
            }

            if (this.skeleton.isUsingItem()) {
                if (!canSee && this.seeTime < -60) {
                    this.skeleton.clearActiveItem();
                } else if (canSee) {
                    int i = this.skeleton.getItemUseTime();
                    if (i >= 20) {
                        this.skeleton.clearActiveItem();
                        this.skeleton.shootAt(target, BowItem.getPullProgress(i));
                        this.attackCooldown = this.attackInterval;
                        System.out.println(attackCooldown);
                    }
                }
             } else if (--this.attackCooldown <= 0 && this.seeTime >= -60) {
                this.skeleton.setCurrentHand(ProjectileUtil.getHandPossiblyHolding(this.skeleton, Items.BOW));
            }
        }
    }
}
