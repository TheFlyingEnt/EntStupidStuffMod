package net.ent.entstupidstuff.client.render.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.client.entity.projectile.UnderwaterArrowEntity;
import net.minecraft.client.model.ArrowModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.TippableArrowRenderState;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.resources.ResourceLocation;
public class PrismerineArrowRenderer extends ArrowRenderer<UnderwaterArrowEntity, TippableArrowRenderState> {

    public static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/projectiles/prismerine_arrow.png");
    public final ArrowModel model;

    public PrismerineArrowRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.model = new ArrowModel(context.bakeLayer(ModelLayers.ARROW));
    }

    @Override
    public ResourceLocation getTextureLocation(TippableArrowRenderState state) {
        return TEXTURE;
    }

    @Override
    public void submit(TippableArrowRenderState state, PoseStack matrices, SubmitNodeCollector commandQueue, CameraRenderState cameraState) {
        matrices.pushPose();
        super.submit(state, matrices, commandQueue, cameraState);
        matrices.popPose();
    }

    private void renderGlowLayer(TippableArrowRenderState state, PoseStack matrices, SubmitNodeCollector commandQueue) {
        commandQueue.submitCustomGeometry(matrices, RenderType.entityTranslucentEmissive(TEXTURE), (entry, vertexConsumer) -> {
            int light = 0xF000F0;
            int overlay = 0;
            int outlineColor = 0;

            commandQueue.submitModelPart(
                    this.model.root(),
                    matrices,
                    RenderType.entityTranslucentEmissive(TEXTURE),
                    light,
                    overlay,
                    null,
                    false,
                    false,
                    -1,
                    null,
                    outlineColor
            );
        });
    }

    @Override
    public TippableArrowRenderState createRenderState() {
        return new TippableArrowRenderState();
    }

    @Override
    public void extractRenderState(UnderwaterArrowEntity arrow, TippableArrowRenderState state, float tickDelta) {
        super.extractRenderState(arrow, state, tickDelta);
    }
}
