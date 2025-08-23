package net.ent.entstupidstuff.item.base.weapons;

//package net.ent.entstupidstuff.util;

import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class ReachHelper {
    public static Entity pickAttackTarget(World world, PlayerEntity player, double reach) {
        Vec3d eye = player.getCameraPosVec(1f);
        Vec3d look = player.getRotationVec(1f);
        Vec3d end  = eye.add(look.multiply(reach));
        Box box    = player.getBoundingBox().stretch(look.multiply(reach)).expand(1.0);
        List<Entity> list = world.getOtherEntities(player, box, e -> e.isAttackable() && e.isAlive());
        Entity best = null;
        double bestDot = 0.95; // favor things near crosshair
        for (Entity e : list) {
            Vec3d dir = e.getBoundingBox().getCenter().subtract(eye).normalize();
            double dot = dir.dotProduct(look.normalize());
            if (dot > bestDot) {
                bestDot = dot;
                best = e;
            }
        }
        return best;
    }
}
