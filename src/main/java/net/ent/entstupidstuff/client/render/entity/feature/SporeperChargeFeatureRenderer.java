package net.ent.entstupidstuff.client.render.entity.feature;

import com.mojang.blaze3d.vertex.PoseStack;
import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.client.render.entity.model.SporeperModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EnergySwirlLayer;
import net.minecraft.client.renderer.entity.state.CreeperRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

public class SporeperChargeFeatureRenderer extends EnergySwirlLayer<CreeperRenderState, SporeperModel> {
	private static final ResourceLocation SKIN = ResourceLocation.withDefaultNamespace("textures/entity/creeper/creeper_armor.png");
	private static final RenderType EYE = RenderType.eyes(ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/sporeper/sporeper_e.png"));
	private final SporeperModel model;

	public SporeperChargeFeatureRenderer(RenderLayerParent<CreeperRenderState, SporeperModel> context, EntityModelSet loader) {
		super(context);
		this.model = new SporeperModel(loader.bakeLayer(ModelLayers.CREEPER_ARMOR));
	}

	protected boolean shouldRender(CreeperRenderState creeperEntityRenderState) {
		return creeperEntityRenderState.isPowered;
	}

	@Override
	protected float xOffset(float partialAge) {
		return partialAge * 0.01F;
	}

	@Override
	protected ResourceLocation getTextureLocation() {
		return SKIN;
	}

	protected SporeperModel model() {
		return this.model;
	}

	@Override
	public void submit(PoseStack matrices, SubmitNodeCollector queue, int light, CreeperRenderState state, float limbAngle, float limbDistance) {
		super.submit(matrices, queue, light, state, limbAngle, limbDistance);

		if (state.swelling != 0) {
			queue.order(1)
			.submitModel(this.getParentModel(), state, matrices, this.getEyesTexture(), light, OverlayTexture.NO_OVERLAY, -1, null, state.outlineColor, null);
		}

		
	}


    public RenderType getEyesTexture() {
        return EYE;
    }

	@Override
	protected boolean isPowered(CreeperRenderState entityRenderState) {
		if (entityRenderState.isPowered)
			return true;
		else
			return false;
	}
}
