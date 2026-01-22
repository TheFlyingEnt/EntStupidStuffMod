package net.ent.entstupidstuff.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.ent.entstupidstuff.item.base.WeaponDaggerNew;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

@Mixin(Player.class)
public abstract class PlayerDualDaggerMixin {

    //@Shadow public abstract void swing(InteractionHand hand, boolean fromServer);

    @Inject(
        method = "attack",
        at = @At("HEAD")
    )
    private void dualDaggerAttack(Entity target, CallbackInfo ci) {
        Player player = (Player)(Object)this;

        if (player.level().isClientSide()) return;

        ItemStack main = player.getMainHandItem();
        ItemStack off = player.getOffhandItem();

        if (!(main.getItem() instanceof WeaponDaggerNew)) return;
        if (!(off.getItem() instanceof WeaponDaggerNew)) return;
        if (!(target instanceof LivingEntity livingTarget)) return;

        // Main hand swing happens naturally
        // Force OFFHAND swing here (allowed!)
        player.swing(InteractionHand.OFF_HAND, true);

        float baseDamage = (float) player.getAttributeValue(Attributes.ATTACK_DAMAGE);
        float offhandDamage = baseDamage * 0.6f;

        livingTarget.hurt(
            player.damageSources().playerAttack(player),
            offhandDamage
        );

        System.out.println("DD 4");
    }
}
