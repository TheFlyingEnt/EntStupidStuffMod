package net.ent.entstupidstuff.entity.ai;

import java.util.EnumSet;

import net.ent.entstupidstuff.entity.mob.SunkenSkeletonEntity;
import net.minecraft.entity.ai.goal.Goal;

public class SunkenSkeletonSwimGoal extends Goal {
    private final SunkenSkeletonEntity skeleton;

    public SunkenSkeletonSwimGoal(SunkenSkeletonEntity skeleton) {
        this.skeleton = skeleton;
        this.setControls(EnumSet.of(Goal.Control.MOVE, Goal.Control.JUMP));
    }

    @Override
    public boolean canStart() {
        return this.skeleton.isTouchingWater();
    }

    @Override
    public void start() {
        this.skeleton.getNavigation().startMovingTo(this.skeleton.getX(), this.skeleton.getY(), this.skeleton.getZ(), 1.0D);
    }

    @Override
    public void stop() {
        this.skeleton.setSwimming(false);
    }
}
