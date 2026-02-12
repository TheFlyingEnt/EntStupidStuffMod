package net.ent.entstupidstuff.item.base.combat;

import net.minecraft.world.item.Item;

public class WeaponDaggerNew extends Item{

    public WeaponDaggerNew(Properties properties) {
        super(properties);
    }

    /*@Override
    public void postHurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (!(attacker instanceof Player player)) return;
        if (!(player.level() instanceof ServerLevel serverLevel)) return;

        ItemStack offhand = player.getOffhandItem();

        // Must be dual-wielding daggers
        if (!(offhand.getItem() instanceof WeaponDaggerNew)) return;

        // Only trigger from MAIN hand hit
        if (player.getMainHandItem() != stack) return;

        // Respect cooldown (avoid spam)
        //if (player.getAttackStrengthScale(0.5f) < 1.0f) return;

        // Apply extra offhand damage (not a second attack)
        float baseDamage = (float) player.getAttributeValue(Attributes.ATTACK_DAMAGE);
        float offhandDamage = baseDamage * 0.6f; // tweak as needed

        /*target.hurt(
                player.damageSources().playerAttack(player),
                offhandDamage
        );

        // Force offhand swing animation
        serverLevel.getChunkSource().sendToTrackingPlayersAndSelf(
                player,
                new ClientboundAnimatePacket(player, ClientboundAnimatePacket.SWING_OFF_HAND)
        );*

        ServerDelayedTasks.scheduleNextTick(() -> {
            if (!target.isAlive()) return;
            if (player.isRemoved()) return;

            //float baseDamage = (float) player.getAttributeValue(Attributes.ATTACK_DAMAGE);
            //float offhandDamage = baseDamage * 0.6f;
            
            target.hurt(
                    player.damageSources().cactus(),
                    offhandDamage
            );

            // Offhand swing animation
            serverLevel.getChunkSource().sendToTrackingPlayersAndSelf(
                    player,
                    new ClientboundAnimatePacket(player, ClientboundAnimatePacket.SWING_OFF_HAND)
            );
        });

        System.out.println("DD 3 RUN");
    }*/


    
}
