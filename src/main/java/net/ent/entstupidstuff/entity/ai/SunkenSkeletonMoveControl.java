package net.ent.entstupidstuff.entity.ai;

import net.ent.entstupidstuff.entity.mob.SunkenSkeletonEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.control.MoveControl;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.util.math.MathHelper;

public class SunkenSkeletonMoveControl extends MoveControl {
    private final SunkenSkeletonEntity skeleton;

    public SunkenSkeletonMoveControl(SunkenSkeletonEntity skeleton) {
        super(skeleton);
        this.skeleton = skeleton;
    }

    @Override
    public void tick() {
        LivingEntity target = this.skeleton.getTarget();
        if (/*this.skeleton.isTargetingUnderwater() &&*/ this.skeleton.isTouchingWater()) {
            if (target != null && target.getY() > this.skeleton.getY() /*|| this.skeleton.targetingUnderwater*/) {
                this.skeleton.setVelocity(this.skeleton.getVelocity().add(0.0, 0.002, 0.0));
            }

            if (this.state != MoveControl.State.MOVE_TO || this.skeleton.getNavigation().isIdle()) {
                this.skeleton.setMovementSpeed(0.0F);
                return;
            }

            double d = this.targetX - this.skeleton.getX();
            double e = this.targetY - this.skeleton.getY();
            double f = this.targetZ - this.skeleton.getZ();
            double g = Math.sqrt(d * d + e * e + f * f);
            e /= g;
            float h = (float)(MathHelper.atan2(f, d) * 180.0F / (float)Math.PI) - 90.0F;
            this.skeleton.setYaw(this.wrapDegrees(this.skeleton.getYaw(), h, 90.0F));
            this.skeleton.bodyYaw = this.skeleton.getYaw();
            float i = (float)(this.speed * this.skeleton.getAttributeValue(EntityAttributes.GENERIC_MOVEMENT_SPEED));
            float j = MathHelper.lerp(0.125F, this.skeleton.getMovementSpeed(), i);
            this.skeleton.setMovementSpeed(j);
            this.skeleton.setVelocity(this.skeleton.getVelocity().add((double)j * d * 0.005, (double)j * e * 0.1, (double)j * f * 0.005));
        } else {
            if (!this.skeleton.isOnGround()) {
                this.skeleton.setVelocity(this.skeleton.getVelocity().add(0.0, -0.008, 0.0));
            }

            super.tick();
        }
    }
}
