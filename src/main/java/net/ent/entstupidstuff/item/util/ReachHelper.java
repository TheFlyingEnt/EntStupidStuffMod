package net.ent.entstupidstuff.item.util;

import java.util.List;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class ReachHelper {
    public static Entity raycastEntity(Level world, Player player, double reach) {
        Vec3 eyePos = player.getEyePosition(1.0f);
        Vec3 lookVec = player.getViewVector(1.0f);
        Vec3 targetPos = eyePos.add(lookVec.scale(reach));
        AABB box = player.getBoundingBox().expandTowards(lookVec.scale(reach)).inflate(1.0);

        List<Entity> hits = world.getEntities(player, box);
        Entity closest = null;
        double closestDist = reach;

        for (Entity e : hits) {
            if (e.isAttackable()) {
                double dist = eyePos.distanceTo(e.getBoundingBox().getCenter());
                if (dist < closestDist) {
                    closestDist = dist;
                    closest = e;
                }
            }
        }
        return closest;
    }
}
