package net.ent.entstupidstuff.mixin;

import net.ent.entstupidstuff.item.base.CannonItem;
import net.ent.entstupidstuff.item.base.DoubleBarrelCrossbowItem;
import net.ent.entstupidstuff.item.base.FlintlockPistolItem;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.entity.player.AvatarRenderer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Avatar;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUseAnimation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AvatarRenderer.class)
public abstract class AvatarRendererMixin {
    @Inject(method = "getArmPose(Lnet/minecraft/world/entity/Avatar;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/InteractionHand;)Lnet/minecraft/client/model/HumanoidModel$ArmPose;", 
    at = @At("HEAD"), 
    cancellable = true)
    
    private static void getDoubleBarrelCrossbowPose(Avatar avatar, ItemStack itemStack, InteractionHand interactionHand, CallbackInfoReturnable<HumanoidModel.ArmPose> cir) {
        if (itemStack.getItem() instanceof DoubleBarrelCrossbowItem) {
            if (!avatar.swinging && DoubleBarrelCrossbowItem.isCharged(itemStack)) {
                cir.setReturnValue(HumanoidModel.ArmPose.CROSSBOW_HOLD);
            } else if (avatar.getUsedItemHand() == interactionHand && avatar.getUseItemRemainingTicks() > 0) {
                ItemUseAnimation itemUseAnimation = itemStack.getUseAnimation();
                if (itemUseAnimation == ItemUseAnimation.CROSSBOW) {
                    cir.setReturnValue(HumanoidModel.ArmPose.CROSSBOW_CHARGE);
                }
            }
        }

        if (itemStack.getItem() instanceof CannonItem) {
            if (!avatar.swinging && CannonItem.isCharged(itemStack)) {
                cir.setReturnValue(HumanoidModel.ArmPose.CROSSBOW_HOLD);
            } else if (avatar.getUsedItemHand() == interactionHand && avatar.getUseItemRemainingTicks() > 0) {
                ItemUseAnimation itemUseAnimation = itemStack.getUseAnimation();
                if (itemUseAnimation == ItemUseAnimation.CROSSBOW) {
                    cir.setReturnValue(HumanoidModel.ArmPose.CROSSBOW_CHARGE);
                }
            }
        }

        if (itemStack.getItem() instanceof FlintlockPistolItem) {
            if (!avatar.swinging && FlintlockPistolItem.isCharged(itemStack)) {
                cir.setReturnValue(HumanoidModel.ArmPose.CROSSBOW_HOLD);
            } else if (avatar.getUsedItemHand() == interactionHand && avatar.getUseItemRemainingTicks() > 0) {
                ItemUseAnimation itemUseAnimation = itemStack.getUseAnimation();
                if (itemUseAnimation == ItemUseAnimation.CROSSBOW) {
                    cir.setReturnValue(HumanoidModel.ArmPose.CROSSBOW_CHARGE);
                }
            }
        }

    }
}
