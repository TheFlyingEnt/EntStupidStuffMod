package net.ent.entstupidstuff.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.mojang.blaze3d.vertex.PoseStack;

import net.ent.entstupidstuff.client.render.entity.model.skull.LayeredSkullModel;
import net.minecraft.client.model.SkullModelBase;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.SkullBlockRenderer;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;

@Mixin(SkullBlockRenderer.class)
public class SkullBlockRendererLayerMixin {
    
    @Inject(method = "submitSkull", at = @At("TAIL"))
    private static void renderOuterLayer(
        Direction direction,
        float yRot,
        float animationPos,
        PoseStack poseStack,
        SubmitNodeCollector submitNodeCollector,
        int packedLight,
        SkullModelBase skullModelBase,
        RenderType renderType,
        int j,
        ModelFeatureRenderer.CrumblingOverlay crumblingOverlay,
        CallbackInfo ci
    ) {
        if (skullModelBase instanceof LayeredSkullModel layeredModel) {
            ResourceLocation outerTexture = layeredModel.getOuterTexture();            
            if (outerTexture != null) {

                poseStack.pushPose();

                if (direction == null) {
                    poseStack.translate(0.5F, 0.0F, 0.5F);
                } else {
                    float h = 0.25F;
                    poseStack.translate(0.5F - direction.getStepX() * 0.25F, 0.25F, 0.5F - direction.getStepZ() * 0.25F);
                }

                RenderType outerRenderType = RenderType.entityCutoutNoCullZOffset(outerTexture);
                SkullModelBase.State state = new SkullModelBase.State();
                state.animationPos = animationPos;
                state.yRot = yRot;

                poseStack.scale(-1.125F, -1.125F, 1.125F);
                poseStack.translate(0, 0.03125F, 0);
                
                // Render the outer layer model
                submitNodeCollector.submitModel(
                    layeredModel.getOuterLayerModel(),
                    state,
                    poseStack,
                    outerRenderType,
                    packedLight,
                    OverlayTexture.NO_OVERLAY,
                    j,
                    crumblingOverlay
                );

                poseStack.popPose();
            }
        }
    }
}
