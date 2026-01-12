package net.ent.entstupidstuff.api.weaponTrait;

import java.util.List;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

public class CircleSlashTrait {

    private static final int COOLDOWN_TICKS = 100; // Customize the cooldown duration (in ticks, 1 second = 20 ticks)
    private static final int DURABILITY_DAMAGE = 5; // Customize the durability damage

    public static void performCircleSlash(Player player, double radius) {
        Level world = player.level();
        AABB area = new AABB(
            player.getX() - radius, player.getY() - radius, player.getZ() - radius,
            player.getX() + radius, player.getY() + radius, player.getZ() + radius
        );

        List<LivingEntity> entities = world.getEntitiesOfClass(LivingEntity.class, area, entity -> entity != player);

        for (LivingEntity entity : entities) {
            // Deal damage to each entity
            entity.hurtServer(null, player.damageSources().playerAttack(player), 10.0F); // Adjust the damage value as needed
        }

        // Apply cooldown to the player
        player.getCooldowns().addCooldown(player.getMainHandItem(), COOLDOWN_TICKS);
    
        // Damage the item
        // Damage the item
        ItemStack mainHandStack = player.getMainHandItem();
        mainHandStack.hurtAndBreak(DURABILITY_DAMAGE, player, EquipmentSlot.MAINHAND); 

        //Creating particle
        /*Vec3d playerPos = player.getPos();
        for (int i = 0; i < 360; i += 10) {
            System.out.println("mathing");
            double angle = Math.toRadians(i);
            double offsetX = playerPos.x + radius * Math.cos(angle);
            double offsetY = playerPos.y + 7; // Adjust Y position as needed
            double offsetZ = playerPos.z + radius * Math.sin(angle);

            // Spawn particles
            world.addParticle(ParticleTypes.FLAME, offsetX, offsetY, offsetZ, 0, 0, 0);
        }*/
        
    }

    public static boolean canPerformCircleSlash(Player player) {
        return !player.getCooldowns().isOnCooldown(player.getMainHandItem());
    }

}
