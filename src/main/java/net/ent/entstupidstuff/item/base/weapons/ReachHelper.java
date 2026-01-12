package net.ent.entstupidstuff.item.base.weapons;

//package net.ent.entstupidstuff.util;

import java.util.List;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class ReachHelper {
    public static Entity pickAttackTarget(Level world, Player player, double reach) {
        Vec3 eye = player.getEyePosition(1f);
        Vec3 look = player.getViewVector(1f);
        Vec3 end  = eye.add(look.scale(reach));
        AABB box    = player.getBoundingBox().expandTowards(look.scale(reach)).inflate(1.0);
        List<Entity> list = world.getEntities(player, box, e -> e.isAttackable() && e.isAlive());
        Entity best = null;
        double bestDot = 0.95; // favor things near crosshair
        for (Entity e : list) {
            Vec3 dir = e.getBoundingBox().getCenter().subtract(eye).normalize();
            double dot = dir.dot(look.normalize());
            if (dot > bestDot) {
                bestDot = dot;
                best = e;
            }
        }
        return best;
    }
}
