package net.ent.entstupidstuff.item;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class ReachHelper {
    public static Entity raycastEntity(World world, PlayerEntity player, double reach) {
        Vec3d eyePos = player.getCameraPosVec(1.0f);
        Vec3d lookVec = player.getRotationVec(1.0f);
        Vec3d targetPos = eyePos.add(lookVec.multiply(reach));
        Box box = player.getBoundingBox().stretch(lookVec.multiply(reach)).expand(1.0);

        List<Entity> hits = world.getOtherEntities(player, box);
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
