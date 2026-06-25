package net.ent.entstupidstuff.api.ship;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.client.ModEntityModelLayers;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

public class ShipCannonBallEntityRenderer extends EntityRenderer<ShipCannonballEntity, EntityRenderState> {
	private final ShipCannonBallModel model;

    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID,"textures/entity/projectiles/cannon_ball.png");


	public ShipCannonBallEntityRenderer(EntityRendererProvider.Context context) {
		super(context);
		this.model = new ShipCannonBallModel(context.bakeLayer(ModEntityModelLayers.CANNON_BALL));
	}

    @Override
    public void extractRenderState(ShipCannonballEntity entity, EntityRenderState state, float partialTick) {
        super.extractRenderState(entity, state, partialTick);
    }
 
    @Override
    public void submit(EntityRenderState state, PoseStack pose, SubmitNodeCollector collector, CameraRenderState cam) {
        pose.pushPose();
 
        // Scale down: the 6×6×6 pixel model at 0.5 scale = ~3 pixel ball = ~0.19 blocks
        //pose.scale(0.5f, 0.5f, 0.5f);
        pose.translate(0.0D, -3.0D, 0.0D);
        pose.scale(2.0F, 2.0F, 2.0F);
 
        // Spin during flight — simple tumble around Y and X
        //float spin = state.ageInTicks * 15.0f;
        //pose.mulPose(Axis.YP.rotationDegrees(spin));
        //pose.mulPose(Axis.XP.rotationDegrees(spin * 0.7f));
 
        collector.submitModel(
            this.model, //Model<? super S> model,
            state,      // S object,
            pose,       // PoseStack poseStack,
            RenderType.entityCutoutNoCull(TEXTURE), //RenderType renderType,
            state.lightCoords, //Int
            OverlayTexture.NO_OVERLAY, //int
            state.outlineColor,  //int
            null); //nullable

        /*collector.submitModel(
			this.model,
			state,
			pose,
			RenderType.entityCutout(TEXTURE),
			state.lightCoords,
			OverlayTexture.NO_OVERLAY,
			state.outlineColor,
			null
		);*/
 
        pose.popPose();
    }

    
	protected ResourceLocation getTexture(EntityRenderState state) {
        return TEXTURE;
    }


    @Override
    public EntityRenderState createRenderState() {
        return new EntityRenderState();
    }
}

