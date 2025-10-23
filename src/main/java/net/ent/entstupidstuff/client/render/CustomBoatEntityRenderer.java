package net.ent.entstupidstuff.client.render;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.client.render.entity.model.CustomBoatModel;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.entity.AbstractBoatEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.state.BoatEntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.Unit;

public class CustomBoatEntityRenderer extends AbstractBoatEntityRenderer {
	private static final Identifier TEXTURE = Identifier.of(EntStupidStuff.MOD_ID, "textures/entity/ccustomboat_3.png");
	private final Model.SinglePartModel waterMaskModel;
	private final CustomBoatModel model;

	public CustomBoatEntityRenderer(EntityRendererFactory.Context ctx, boolean layer) {
		super(ctx);
		this.shadowRadius = 0.8F;
		
		EntityModelLayer modelLayer = new EntityModelLayer(Identifier.of(EntStupidStuff.MOD_ID, "customboat"), "main");
		ModelPart modelPart = ctx.getPart(modelLayer);
		this.model = new CustomBoatModel(modelPart);
		this.waterMaskModel = new Model.SinglePartModel(ctx.getPart(modelLayer), id -> RenderLayer.getWaterMask());

	}

	@Override
	protected RenderLayer getRenderLayer() {
		return this.model.getLayer(TEXTURE);
	}

	@Override
	protected void renderWaterMask(BoatEntityRenderState state, MatrixStack matrices, OrderedRenderCommandQueue orderedRenderCommandQueue, int light) {
		if (!state.submergedInWater) {
			orderedRenderCommandQueue.submitModel(
				this.waterMaskModel, Unit.INSTANCE, matrices, this.waterMaskModel.getLayer(TEXTURE), light, OverlayTexture.DEFAULT_UV, state.outlineColor, null
			);
		}
	}

	@Override
	protected EntityModel<BoatEntityRenderState> getModel() {
		return this.model;
	}
}
