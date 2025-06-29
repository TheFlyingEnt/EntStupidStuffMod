package net.ent.entstupidstuff.entity.ai;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.mob.SkeletonEntity;

public class TrackTargetGoal extends Goal {
    private final SkeletonEntity skeleton;

    public TrackTargetGoal(SkeletonEntity skeleton) {
        this.skeleton = skeleton;
    }

    @Override
    public boolean canStart() {
        return this.skeleton.getTarget() != null;
    }

    @Override
    public void tick() {
        LivingEntity target = this.skeleton.getTarget();
        if (target != null) {
            this.skeleton.lookAtEntity(target, 30.0F, 30.0F);
        }
    }
}
