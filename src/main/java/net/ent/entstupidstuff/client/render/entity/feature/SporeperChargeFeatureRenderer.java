package net.ent.entstupidstuff.client.render.entity.feature;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.client.render.entity.model.SporeperModel;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.entity.feature.EnergySwirlOverlayFeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.LoadedEntityModels;
import net.minecraft.client.render.entity.state.CreeperEntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class SporeperChargeFeatureRenderer extends EnergySwirlOverlayFeatureRenderer<CreeperEntityRenderState, SporeperModel> {
	private static final Identifier SKIN = Identifier.ofVanilla("textures/entity/creeper/creeper_armor.png");
	private static final RenderLayer EYE = RenderLayer.getEyes(Identifier.of(EntStupidStuff.MOD_ID, "textures/entity/sporeper/sporeper_e.png"));
	private final SporeperModel model;

	public SporeperChargeFeatureRenderer(FeatureRendererContext<CreeperEntityRenderState, SporeperModel> context, LoadedEntityModels loader) {
		super(context);
		this.model = new SporeperModel(loader.getModelPart(EntityModelLayers.CREEPER_ARMOR));
	}

	protected boolean shouldRender(CreeperEntityRenderState creeperEntityRenderState) {
		return creeperEntityRenderState.charged;
	}

	@Override
	protected float getEnergySwirlX(float partialAge) {
		return partialAge * 0.01F;
	}

	@Override
	protected Identifier getEnergySwirlTexture() {
		return SKIN;
	}

	protected SporeperModel getEnergySwirlModel() {
		return this.model;
	}

	@Override
	public void render(MatrixStack matrices, OrderedRenderCommandQueue queue, int light, CreeperEntityRenderState state, float limbAngle, float limbDistance) {
		super.render(matrices, queue, light, state, limbAngle, limbDistance);

		if (state.fuseTime != 0) {
			queue.getBatchingQueue(1)
			.submitModel(this.getContextModel(), state, matrices, this.getEyesTexture(), light, OverlayTexture.DEFAULT_UV, -1, null, state.outlineColor, null);
		}

		
	}


    public RenderLayer getEyesTexture() {
        return EYE;
    }
}
