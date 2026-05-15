package net.ent.entstupidstuff.client.render.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.api.ship.ShipEntityTest;
import net.ent.entstupidstuff.client.ModEntityModelLayers;
import net.ent.entstupidstuff.client.render.entity.model.CustomBoatModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.BoatRenderState;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

public class ShipTestRenderer extends EntityRenderer<ShipEntityTest, BoatRenderState> {

    private static final ResourceLocation TEXTURE =
            ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID,
                    "textures/entity/ccustomboat_3.png");

    private final CustomBoatModel model;

    public ShipTestRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.model = new CustomBoatModel(
                context.bakeLayer(ModEntityModelLayers.SHIPENTITYTEST));
        this.shadowRadius = 1.6f;
    }

    @Override
    public BoatRenderState createRenderState() {
        return new BoatRenderState();
    }

    @Override
    public void extractRenderState(ShipEntityTest entity, BoatRenderState state,
                                   float partialTick) {
        super.extractRenderState(entity, state, partialTick);
        // If BoatRenderState has extra fields (e.g. sail deploy, wheel angle,
        // wave bob), copy them from the entity here. Examples:
        //   state.sailDeploy = entity.getSailDeploy();
        //   state.wheelAngle = entity.getWheelAngle(partialTick);
        // The base EntityRenderState already gets x/y/z, yRot, xRot,
        // ageInTicks, partialTick, isInvisible, etc.
    }

    @Override
    public void submit(BoatRenderState state, PoseStack poseStack, SubmitNodeCollector buffers, CameraRenderState cameraState) {
        poseStack.pushPose();

        // Lift the model so the deck sits at the entity's origin instead of
        // its hull bottom. Tune this to match where your model's pivot is.
        poseStack.translate(0.0, 0.375, 0.0);
        poseStack.mulPose(Axis.YP.rotationDegrees(90.0F));
        poseStack.scale(3f, 3f, 3f);

        // Rotate around Y so the model faces the ship's heading.
        // The 180° offset is the standard vanilla convention because
        // entity models are typically authored facing -Z.
        poseStack.mulPose(Axis.XP.rotationDegrees(180.0F - state.yRot));

        this.model.setupAnim(state);

        buffers.submitModel(
			this.model, state, poseStack, this.renderType(), state.lightCoords, OverlayTexture.NO_OVERLAY, state.outlineColor, null
		);

        poseStack.popPose();
        super.submit(state, poseStack, buffers, cameraState);


        
    }

    protected RenderType renderType() {
		return this.model.renderType(TEXTURE);
	}
}
