package net.ent.entstupidstuff.client.entity.ai;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.monster.Skeleton;

public class TrackTargetGoal extends Goal {
    private final Skeleton skeleton;

    public TrackTargetGoal(Skeleton skeleton) {
        this.skeleton = skeleton;
    }

    @Override
    public boolean canUse() {
        return this.skeleton.getTarget() != null;
    }

    @Override
    public void tick() {
        LivingEntity target = this.skeleton.getTarget();
        if (target != null) {
            this.skeleton.lookAt(target, 30.0F, 30.0F);
        }
    }
}
