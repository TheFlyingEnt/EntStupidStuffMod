package net.ent.entstupidstuff.client.entity.ai;

import net.ent.entstupidstuff.client.entity.mob.SunkenSkeletonEntity;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;

public class SunkenSkeletonMoveControl extends MoveControl {
    private final SunkenSkeletonEntity skeleton;

    public SunkenSkeletonMoveControl(SunkenSkeletonEntity skeleton) {
        super(skeleton);
        this.skeleton = skeleton;
    }

    @Override
    public void tick() {
        LivingEntity target = this.skeleton.getTarget();
        if (/*this.skeleton.isTargetingUnderwater() &&*/ this.skeleton.isInWater()) {
            if (target != null && target.getY() > this.skeleton.getY() /*|| this.skeleton.targetingUnderwater*/) {
                this.skeleton.setDeltaMovement(this.skeleton.getDeltaMovement().add(0.0, 0.002, 0.0));
            }

            if (this.operation != MoveControl.Operation.MOVE_TO || this.skeleton.getNavigation().isDone()) {
                this.skeleton.setSpeed(0.0F);
                return;
            }

            double d = this.wantedX - this.skeleton.getX();
            double e = this.wantedY - this.skeleton.getY();
            double f = this.wantedZ - this.skeleton.getZ();
            double g = Math.sqrt(d * d + e * e + f * f);
            e /= g;
            float h = (float)(Mth.atan2(f, d) * 180.0F / (float)Math.PI) - 90.0F;
            this.skeleton.setYRot(this.rotlerp(this.skeleton.getYRot(), h, 90.0F));
            this.skeleton.yBodyRot = this.skeleton.getYRot();
            float i = (float)(this.speedModifier * this.skeleton.getAttributeValue(Attributes.MOVEMENT_SPEED));
            float j = Mth.lerp(0.125F, this.skeleton.getSpeed(), i);
            this.skeleton.setSpeed(j);
            this.skeleton.setDeltaMovement(this.skeleton.getDeltaMovement().add((double)j * d * 0.005, (double)j * e * 0.1, (double)j * f * 0.005));
        } else {
            if (!this.skeleton.onGround()) {
                this.skeleton.setDeltaMovement(this.skeleton.getDeltaMovement().add(0.0, -0.008, 0.0));
            }

            super.tick();
        }
    }
}
