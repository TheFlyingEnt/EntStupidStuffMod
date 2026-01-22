package net.ent.entstupidstuff.event.callback;

import net.ent.entstupidstuff.item.base.WeaponDaggerNew;
import net.ent.entstupidstuff.item.itemType.DaggerItem;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.minecraft.network.protocol.game.ClientboundAnimatePacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public class DualDaggerHandler {

    public static void register() {
        AttackEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> {

            if (!(world instanceof ServerLevel serverLevel)) return InteractionResult.PASS;
            if (hand != InteractionHand.MAIN_HAND) return InteractionResult.PASS;
            if (!(entity instanceof LivingEntity target)) return InteractionResult.PASS;

            ItemStack main = player.getMainHandItem();
            ItemStack off = player.getOffhandItem();

            if (!(main.getItem() instanceof WeaponDaggerNew)) return InteractionResult.PASS;
            if (!(off.getItem() instanceof WeaponDaggerNew)) return InteractionResult.PASS;

            // Schedule second hit AFTER vanilla hit
            serverLevel.getServer().execute(() -> {

                // Ensure target is still alive
                if (!target.isAlive()) return;

                // Second hit (offhand)
                player.doHurtTarget(serverLevel, target);

                // Force offhand swing animation sync
                serverLevel.getChunkSource().sendToTrackingPlayers(
                        player,
                        new ClientboundAnimatePacket(player, ClientboundAnimatePacket.SWING_OFF_HAND)
                );
            });

            return InteractionResult.PASS;










            //Old

            
        });
    }
}
