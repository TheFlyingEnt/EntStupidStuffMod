package net.ent.entstupidstuff.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.ent.entstupidstuff.api.util.HorizontalBannerRenderState;
import net.ent.entstupidstuff.block.base.HorizontalBannerBlock;
import net.minecraft.client.renderer.entity.IllagerRenderer;
import net.minecraft.client.renderer.entity.state.IllagerRenderState;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.monster.AbstractIllager;
import net.minecraft.world.item.BannerItem;
import net.minecraft.world.item.ItemStack;

@Mixin(IllagerRenderer.class)
public abstract class IllagerRendererMixin<T extends AbstractIllager, S extends IllagerRenderState> {

    //@Inject(method = "extractRenderState(Lnet/minecraft/world/entity/Avatar;Lnet/minecraft/client/renderer/entity/state/AvatarRenderState;F)V",
    //        at = @At("TAIL"))
    @Inject(method = "extractRenderState", at = @At("TAIL"))
    private void onExtractRenderState(T player, S state, float partialTick, CallbackInfo ci) {
        HorizontalBannerRenderState bannerState = (HorizontalBannerRenderState) state;

        ItemStack main = player.getItemInHand(InteractionHand.MAIN_HAND);
        ItemStack off  = player.getItemInHand(InteractionHand.OFF_HAND);

        bannerState.entstupidstuff$setHoldingBannerMainHand(
            main.getItem() instanceof BannerItem bi && bi.getBlock() instanceof HorizontalBannerBlock
        );
        bannerState.entstupidstuff$setHoldingBannerOffHand(
            off.getItem() instanceof BannerItem bi && bi.getBlock() instanceof HorizontalBannerBlock
        ); 
    }
    
}
