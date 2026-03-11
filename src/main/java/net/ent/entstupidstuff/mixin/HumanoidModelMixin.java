package net.ent.entstupidstuff.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.ent.entstupidstuff.api.util.HorizontalBannerRenderState;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.HumanoidArm;

@Mixin(HumanoidModel.class)
public class HumanoidModelMixin<T extends HumanoidRenderState> {

    @Inject(method = "setupAnim", at = @At("TAIL"))
    private void onSetupAnim(T state, CallbackInfo ci) {
        if (!(state instanceof HorizontalBannerRenderState bannerState)) return;

        boolean mainArm = state.mainArm == HumanoidArm.RIGHT;

        if (bannerState.entstupidstuff$isHoldingBannerMainHand()) {
            ModelPart arm = mainArm ? ((HumanoidModel<?>) (Object) this).rightArm
                                    : ((HumanoidModel<?>) (Object) this).leftArm;
            //arm.xRot = -(float)(Math.PI / 2) + 0.1F; // straight forward/up — adjust to taste
            //arm.zRot = 0.0F;
            arm.xRot = -(float)(Math.PI / 2) - 0.1F;
            arm.zRot = 0.0F;
            arm.xRot += 1.0F * (Mth.sin(state.ageInTicks * 0.067F) * 0.05F);

        }
        if (bannerState.entstupidstuff$isHoldingBannerOffHand()) {
            ModelPart arm = mainArm ? ((HumanoidModel<?>) (Object) this).leftArm
                                    : ((HumanoidModel<?>) (Object) this).rightArm;
            //arm.xRot = -(float)(Math.PI / 2) + 0.1F;
            //arm.zRot = 0.0F;
            arm.xRot = -(float)(Math.PI / 2) - 0.1F;
            arm.zRot = 0.0F;
            arm.xRot += -1.0F * (Mth.sin(state.ageInTicks * 0.067F) * 0.05F);
        }
    }
}
