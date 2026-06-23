package net.ent.entstupidstuff.api.ship;

import com.mojang.blaze3d.vertex.PoseStack;

import net.ent.entstupidstuff.client.ModEntityModelLayers;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;



public class AnchorEntityRenderer extends EntityRenderer<AnchorEntity, AnchorRenderState> {
 
    private static final ResourceLocation TEXTURE =
            ResourceLocation.fromNamespaceAndPath("entstupidstuff", "textures/entity/bigboat_alt.png");
 
    private final AnchorModel model;
 
    public AnchorEntityRenderer(EntityRendererProvider.Context ctx) {
        super(ctx);
        this.model = new AnchorModel(ctx.bakeLayer(ModEntityModelLayers.ANCHOR));
    }
 
    @Override
    public AnchorRenderState createRenderState() {
        return new AnchorRenderState();
    }
 
    @Override
    public void extractRenderState(AnchorEntity e, AnchorRenderState st, float partialTick) {
        super.extractRenderState(e, st, partialTick);
    }
 
    @Override
    public void submit(AnchorRenderState st, PoseStack pose, SubmitNodeCollector collector, CameraRenderState cam) {
        pose.pushPose();
        pose.scale(0.9f, 0.9f, 0.9f);
        this.model.setupAnim(st);
        collector.submitModel(
            this.model, st, pose,
            RenderType.entityCutoutNoCull(TEXTURE),
            st.lightCoords, OverlayTexture.NO_OVERLAY, st.outlineColor, null);
        pose.popPose();
    }
}


