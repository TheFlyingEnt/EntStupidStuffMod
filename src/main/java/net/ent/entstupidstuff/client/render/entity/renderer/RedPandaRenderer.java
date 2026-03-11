package net.ent.entstupidstuff.client.render.entity.renderer;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.client.ModEntityModelLayers;
import net.ent.entstupidstuff.client.entity.passive.RedPandaEntity;
import net.ent.entstupidstuff.client.render.entity.model.RedPandaModel;
import net.minecraft.client.renderer.entity.AgeableMobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.ResourceLocation;

@SuppressWarnings("deprecation")
public class RedPandaRenderer extends AgeableMobRenderer<RedPandaEntity, LivingEntityRenderState, RedPandaModel> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/red_panda.png");

	public RedPandaRenderer(EntityRendererProvider.Context context) {
		super(context, new RedPandaModel(context.bakeLayer(ModEntityModelLayers.RED_PANDA)), new RedPandaModel(context.bakeLayer(ModEntityModelLayers.RED_PANDA)), 0.7F);
		//this.addFeature(new FoxHeldItemFeatureRenderer(this));
	}

	public ResourceLocation getTextureLocation(LivingEntityRenderState redPandaEntity) {
		return TEXTURE;
	}

	@Override
	public LivingEntityRenderState createRenderState() {
		return new LivingEntityRenderState();
	}
}
