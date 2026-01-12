package net.ent.entstupidstuff.client.entity.ai;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.Items;

public class CustomBowAttackGoal<T extends Skeleton> extends Goal {
    private final T skeleton;
    private final double speed;
    private final int attackInterval;
    private final float maxRange;
    private int attackCooldown;
    private int seeTime;
    private boolean movingToLeft;
    @SuppressWarnings("unused")
    private boolean movingToRight;
    private boolean strafingClockwise;
    private int strafingTime = -1;

    public CustomBowAttackGoal(T skeleton, double speed, int attackInterval, float maxRange) {
        this.skeleton = skeleton;
        this.speed = speed;
        this.attackInterval = attackInterval;
        this.maxRange = maxRange;
    }

    @Override
    public boolean canUse() {
        return this.skeleton.getTarget() != null && this.isHoldingBow();
    }

    protected boolean isHoldingBow() {
        return this.skeleton.isHolding(Items.BOW);
    }

    @Override
    public void start() {
        super.start();
        this.skeleton.setAggressive(true);
    }

    @Override
    public void stop() {
        super.stop();
        this.skeleton.setAggressive(false);
        this.skeleton.stopUsingItem();
        this.seeTime = 0;
        this.attackCooldown = -1;
        this.skeleton.setTarget(null);
    }

    @Override
    public void tick() {
        LivingEntity target = this.skeleton.getTarget();
        if (target != null) {
            double distance = this.skeleton.distanceToSqr(target.getX(), target.getY(), target.getZ());
            boolean canSee = this.skeleton.getSensing().hasLineOfSight(target);
            boolean wasSeeing = this.seeTime > 0;

            if (canSee != wasSeeing) {
                this.seeTime = 0;
            }

            if (canSee) {
                this.seeTime++;
            } else {
                this.seeTime--;
            }

            if (distance <= this.maxRange && this.seeTime >= 20) {
                this.skeleton.getNavigation().stop();
                this.strafingTime++;
            } else {
                this.skeleton.getNavigation().moveTo(target, this.speed);
                this.strafingTime = -1;
            }

            if (this.strafingTime >= 20) {
                if (this.skeleton.getRandom().nextFloat() < 0.3D) {
                    this.movingToLeft = !this.movingToLeft;
                }

                if (this.skeleton.getRandom().nextFloat() < 0.3D) {
                    this.strafingClockwise = !this.strafingClockwise;
                }

                this.strafingTime = 0;
            }

            if (this.strafingTime > -1) {
                if (distance > this.maxRange * 0.75F) {
                    this.strafingClockwise = false;
                } else if (distance < this.maxRange * 0.25F) {
                    this.strafingClockwise = true;
                }

                this.skeleton.getMoveControl().strafe(this.movingToLeft ? -0.5F : 0.5F, this.strafingClockwise ? 0.5F : -0.5F);
                this.skeleton.lookAt(target, 30.0F, 30.0F);
            } else {
                this.skeleton.getLookControl().setLookAt(target, 30.0F, 30.0F);
            }

            if (this.skeleton.isUsingItem()) {
                if (!canSee && this.seeTime < -60) {
                    this.skeleton.stopUsingItem();
                } else if (canSee) {
                    int useTicks = this.skeleton.getTicksUsingItem();
                    if (useTicks >= 20) {
                        this.skeleton.stopUsingItem();
                        this.skeleton.performRangedAttack(target, BowItem.getPowerForTime(useTicks));
                        this.attackCooldown = this.attackInterval;
                    }
                }
            } else if (--this.attackCooldown <= 0 && this.seeTime >= -60) {
                this.skeleton.startUsingItem(InteractionHand.MAIN_HAND);
            }
        }
    }


}