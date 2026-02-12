package net.ent.entstupidstuff.client.entity.ai;

import net.ent.entstupidstuff.client.entity.passive.ButterflyEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.phys.Vec3;
import java.util.EnumSet;
import java.util.Random;

@Deprecated
public class ButterflyFlyingAroundGoal extends Goal {
    private final ButterflyEntity butterfly;
    private final double speed;
    private final int interval;
    private int cooldown;
    private Vec3 target;
    private boolean hovering;

    @Deprecated
    public ButterflyFlyingAroundGoal(ButterflyEntity butterfly, double speed, int interval) {
        this.butterfly = butterfly;
        this.speed = speed;
        this.interval = interval;
        this.setFlags(EnumSet.of(Flag.MOVE));
    }

    @Override
    public boolean canUse() {
        return true;
    }

    @Override
    public void tick() {
        if (--cooldown <= 0 || target == null || butterfly.position().distanceTo(target) < 2.0) {
            cooldown = interval;

            Vec3 pos = butterfly.position();
            Random random = new Random();

            double dx = (random.nextDouble() - 0.5) * 12;
            double dy = (random.nextDouble() - 0.5) * 6;
            double dz = (random.nextDouble() - 0.5) * 12;

            target = pos.add(dx, dy, dz);

            butterfly.getMoveControl().setWantedPosition(target.x, target.y, target.z, speed);
        }
    }
}
