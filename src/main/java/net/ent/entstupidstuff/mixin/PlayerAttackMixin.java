package net.ent.entstupidstuff.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.ent.entstupidstuff.item.base.combat.WeaponDaggerNew;
import net.minecraft.network.protocol.game.ClientboundAnimatePacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

@Mixin(Player.class)
public abstract class PlayerAttackMixin {

    @Shadow
    protected abstract void resetAttackStrengthTicker();

    @Inject(
        method = "attack",
        at = @At("TAIL")
    )
    private void dualDaggerSecondHit(Entity target, CallbackInfo ci) {
        Player player = (Player) (Object) this;

        if (!(player.level() instanceof ServerLevel serverLevel)) return;
        if (!(target instanceof LivingEntity living)) return;

        ItemStack main = player.getMainHandItem();
        ItemStack off = player.getOffhandItem();

        if (!(main.getItem() instanceof WeaponDaggerNew)) return;
        if (!(off.getItem() instanceof WeaponDaggerNew)) return;

        // Prevent recursion
        if (player.getAttackStrengthScale(0.5f) < 1.0f) return;

        // Apply offhand hit
        resetAttackStrengthTicker();
        player.doHurtTarget(serverLevel, living);

        // Offhand swing animation
        serverLevel.getChunkSource().sendToTrackingPlayersAndSelf(
                player,
                new ClientboundAnimatePacket(player, ClientboundAnimatePacket.SWING_OFF_HAND)
        );

        System.out.println("DD RNA 2");
    }
}
