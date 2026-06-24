package net.ent.entstupidstuff.api.ship;

import com.mojang.blaze3d.vertex.PoseStack;
import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.client.ModEntityModelLayers;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.AbstractBoatRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.BoatRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Unit;
import net.minecraft.world.entity.vehicle.AbstractBoat;

public class CustomBoatEntityRenderer extends AbstractBoatRenderer {
	private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/bigboat_alt.png");
	private final Model.Simple waterMaskModel;
	private final CustomBoatModel model;

	public CustomBoatEntityRenderer(EntityRendererProvider.Context ctx, boolean layer) {
		super(ctx);
		this.shadowRadius = 0.8F;

		ModelLayerLocation modelLayer = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "customboat"), "main");
		ModelPart modelPart = ctx.bakeLayer(modelLayer);
		this.model = new CustomBoatModel(modelPart);
		this.waterMaskModel = new Model.Simple(ctx.bakeLayer(ModEntityModelLayers.WATER_PATCH), id -> RenderType.waterMask());
	}

	@Override
	protected RenderType renderType() {
		return this.model.renderType(TEXTURE);
	}

	private static final float MODEL_SCALE = 1.15f;

	@Override
	protected void submitTypeAdditions(BoatRenderState state, PoseStack matrices, SubmitNodeCollector orderedRenderCommandQueue, int light) {
		matrices.scale(MODEL_SCALE, MODEL_SCALE, MODEL_SCALE);

		if (!state.isUnderWater) {
			orderedRenderCommandQueue.submitModel(
				this.waterMaskModel, Unit.INSTANCE, matrices, this.waterMaskModel.renderType(TEXTURE), light, OverlayTexture.NO_OVERLAY, state.outlineColor, null
			);
		}
	}

	@Override
	public void extractRenderState(AbstractBoat boat, BoatRenderState state, float partialTick) {
		super.extractRenderState(boat, state, partialTick);
		if (boat instanceof CustomBoatEntity ship) {
			this.model.sailLevel    = ship.getSailLevel();
			this.model.forwardSpeed = ship.getForwardSpeed();
			this.model.sinkProgress = ship.getSinkProgress();
			this.model.waveTime     = ship.tickCount + partialTick;

			// FIX: Feed the actual rudder angle (-1..+1) instead of getDeckDYaw().
			// getDeckDYaw() was the per-tick yaw change — a tiny, jerky value that
			// made the rudder twitch instead of smoothly following the helm.
			// The rudder angle builds up gradually while holding A/D and springs
			// back when released, giving a smooth animated swing.
			this.model.rudderTurn = ship.getRudderAngle();
		}
	}

	@Override
	protected EntityModel<BoatRenderState> model() {
		return this.model;
	}
}