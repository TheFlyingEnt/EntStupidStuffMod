package net.ent.entstupidstuff.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.ent.entstupidstuff.client.render.entity.state.HeldHorizontalBannerRenderState;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.IllagerModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.entity.state.IllagerRenderState;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.HumanoidArm;

@Mixin(IllagerModel.class)
public class IllagerModelMixin<S extends IllagerRenderState>{

    @Shadow private ModelPart rightArm;
    @Shadow private ModelPart leftArm;

    @Inject(method = "setupAnim", at = @At("TAIL"))
    private void onSetupAnim(S state, CallbackInfo ci) {
        if (!(state instanceof HeldHorizontalBannerRenderState bannerState)) return;
        boolean mainArm = state.mainArm == HumanoidArm.RIGHT;
        if (bannerState.entstupidstuff$isHoldingBannerMainHand()) {
            ModelPart arm = mainArm ? this.rightArm : this.leftArm;
            arm.xRot = -(float)(Math.PI / 2) - 0.1F;
            arm.zRot = 0.0F;
            arm.xRot += 1.0F * (Mth.sin(state.ageInTicks * 0.067F) * 0.05F);
        }
        if (bannerState.entstupidstuff$isHoldingBannerOffHand()) {
            ModelPart arm = mainArm ? this.leftArm : this.rightArm;
            arm.xRot = -(float)(Math.PI / 2) - 0.1F;
            arm.zRot = 0.0F;
            arm.xRot += -1.0F * (Mth.sin(state.ageInTicks * 0.067F) * 0.05F);
        }
    }
    
}
