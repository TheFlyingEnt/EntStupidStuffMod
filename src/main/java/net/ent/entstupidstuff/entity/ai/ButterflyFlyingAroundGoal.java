package net.ent.entstupidstuff.entity.ai;

import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.mob.FlyingEntity;
import net.minecraft.entity.ai.control.MoveControl;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.MathHelper;
import net.ent.entstupidstuff.entity.passive.ButterflyEntity;
import net.minecraft.entity.LivingEntity;

import java.util.EnumSet;
import java.util.Random;

public class ButterflyFlyingAroundGoal extends Goal {
    private final ButterflyEntity butterfly;
    private final double speed;
    private final int interval;
    private int cooldown;
    private Vec3d target;
    private boolean hovering;

    public ButterflyFlyingAroundGoal(ButterflyEntity butterfly, double speed, int interval) {
        this.butterfly = butterfly;
        this.speed = speed;
        this.interval = interval;
        this.setControls(EnumSet.of(Control.MOVE));
    }

    @Override
    public boolean canStart() {
        return true;
    }

    @Override
    public void tick() {
        if (--cooldown <= 0 || target == null || butterfly.getPos().distanceTo(target) < 2.0) {
            cooldown = interval;

            Vec3d pos = butterfly.getPos();
            Random random = new Random();

            double dx = (random.nextDouble() - 0.5) * 12;
            double dy = (random.nextDouble() - 0.5) * 6;
            double dz = (random.nextDouble() - 0.5) * 12;

            target = pos.add(dx, dy, dz);

            butterfly.getMoveControl().moveTo(target.x, target.y, target.z, speed);
        }
    }
}
