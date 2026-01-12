package net.ent.entstupidstuff.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.client.render.entity.model.CustomBoatModel;
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

public class CustomBoatEntityRenderer extends AbstractBoatRenderer {
	private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/ccustomboat_3.png");
	private final Model.Simple waterMaskModel;
	private final CustomBoatModel model;

	public CustomBoatEntityRenderer(EntityRendererProvider.Context ctx, boolean layer) {
		super(ctx);
		this.shadowRadius = 0.8F;
		
		ModelLayerLocation modelLayer = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "customboat"), "main");
		ModelPart modelPart = ctx.bakeLayer(modelLayer);
		this.model = new CustomBoatModel(modelPart);
		this.waterMaskModel = new Model.Simple(ctx.bakeLayer(modelLayer), id -> RenderType.waterMask());

	}

	@Override
	protected RenderType renderType() {
		return this.model.renderType(TEXTURE);
	}

	@Override
	protected void submitTypeAdditions(BoatRenderState state, PoseStack matrices, SubmitNodeCollector orderedRenderCommandQueue, int light) {
		if (!state.isUnderWater) {
			orderedRenderCommandQueue.submitModel(
				this.waterMaskModel, Unit.INSTANCE, matrices, this.waterMaskModel.renderType(TEXTURE), light, OverlayTexture.NO_OVERLAY, state.outlineColor, null
			);
		}
	}

	@Override
	protected EntityModel<BoatRenderState> model() {
		return this.model;
	}
}
