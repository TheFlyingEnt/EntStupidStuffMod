package net.ent.entstupidstuff.mixin;

import net.ent.entstupidstuff.item.ItemFactory;
import net.ent.entstupidstuff.item.base.FinalCannon;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.UseAction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntityRenderer.class)
public abstract class PlayerEntityRendererMixin {

    @SuppressWarnings("unused")
    @Inject(method = "getArmPose", at = @At("RETURN"), cancellable = true)
    private static void modifyArmPose(AbstractClientPlayerEntity player, Hand hand, CallbackInfoReturnable<BipedEntityModel.ArmPose> cir) {
        ItemStack itemStack = player.getStackInHand(hand);
        BipedEntityModel.ArmPose armPose = cir.getReturnValue();

        if (!itemStack.isEmpty()) {
            if (player.getActiveHand() == hand && player.getItemUseTimeLeft() > 0) {
                UseAction useAction = itemStack.getUseAction();
                
            }
            else if (!player.handSwinging && itemStack.isOf(ItemFactory.CANNON_ITEM) && FinalCannon.isCharged(itemStack)) {
                armPose = BipedEntityModel.ArmPose.CROSSBOW_HOLD;
            }
            else if (!player.handSwinging && itemStack.isOf(ItemFactory.callItem("diamond_long_sword"))) {
                //armPose = //BipedEntityModel.ArmPose.CROSSBOW_HOLD;
            }
        }

        cir.setReturnValue(armPose);
    }
}
