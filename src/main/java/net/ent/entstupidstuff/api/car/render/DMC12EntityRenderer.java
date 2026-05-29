package net.ent.entstupidstuff.api.car.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.api.car.BaseCarEntity;
import net.ent.entstupidstuff.api.car.models.DMC13Model;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

public class DMC12EntityRenderer extends BaseCarEntityRenderer<BaseCarEntity, BaseCarRenderState> {
 
    private static final ResourceLocation TEXTURE =
        ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/dmc_13.png");
    private static final ResourceLocation GLOW =
        ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/dmc_13_glow.png");
    private static final ResourceLocation GLOW_BACKUP =
        ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/dmc_13_glow_backup.png");
 
    public DMC12EntityRenderer(EntityRendererProvider.Context context) {
        super(context, new DMC13Model(context.bakeLayer(DMC13Model.LAYER_LOCATION)));
    }
 
    @Override protected ResourceLocation texture(BaseCarRenderState state)            { return TEXTURE; }
    @Override protected ResourceLocation glowTexture(BaseCarRenderState state)        { return GLOW; }
    @Override protected ResourceLocation glowBackupTexture(BaseCarRenderState state)  { return GLOW_BACKUP; }

    private static final ResourceLocation GEAR_0 = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/dmc_13/dmc_13_gear_0.png");
    private static final ResourceLocation GEAR_1 = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/dmc_13/dmc_13_gear_1.png");
    private static final ResourceLocation GEAR_2 = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/dmc_13/dmc_13_gear_2.png");
    private static final ResourceLocation GEAR_3 = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/dmc_13/dmc_13_gear_3.png");
    //private static final ResourceLocation GEAR_4 = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/dmc_13/dmc_13_gear_4.png");

    @Override
    public void extractRenderState(BaseCarEntity entity, BaseCarRenderState state, float partialTick) {
        super.extractRenderState(entity, state, partialTick);
        state.revLightState = entity.getRevLightState();
    }

    @Override
    public void submit(BaseCarRenderState state, PoseStack poseStack, SubmitNodeCollector collector, CameraRenderState cameraState) {
        super.submit(state, poseStack, collector, cameraState);

        poseStack.pushPose();

        poseStack.mulPose(Axis.YP.rotationDegrees(180f - state.yRot));
        poseStack.mulPose(Axis.ZP.rotationDegrees(180f));
 
        poseStack.scale(1.0f, 1.0f, 1.0f);
        poseStack.translate(0, -1.35F, 0);

        ResourceLocation revTex = switch (state.revLightState) {
            case 1 -> GEAR_0;  // red only
            case 2 -> GEAR_1;  // red + orange
            case 3 -> GEAR_2;  // red + orange + yellow
            case 4 -> GEAR_3;  // all lit
            default -> GEAR_0;
        };

        int alphaByte = Math.round(1 * 255.0f);
        int color = (alphaByte << 24) | 0x00FFFFFF;

        if (revTex != null) {
            collector.order(2)
                .submitModel(
				this.model(), state, poseStack, RenderType.eyes(revTex), state.lightCoords, OverlayTexture.NO_OVERLAY, color, null, state.outlineColor, null
			);
        }

        poseStack.popPose();

    }
}
