package net.ent.entstupidstuff.client.render.entity;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.client.render.ModEntityModelLayers;
import net.ent.entstupidstuff.client.render.entity.model.RedPandaModel;
import net.ent.entstupidstuff.entity.passive.RedPandaEntity;
import net.minecraft.client.render.entity.AgeableMobEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.util.Identifier;

@SuppressWarnings("deprecation")
public class RedPandaRenderer extends AgeableMobEntityRenderer<RedPandaEntity, LivingEntityRenderState, RedPandaModel> {
    private static final Identifier TEXTURE = Identifier.of(EntStupidStuff.MOD_ID, "textures/entity/red_panda.png");

	public RedPandaRenderer(EntityRendererFactory.Context context) {
		super(context, new RedPandaModel(context.getPart(ModEntityModelLayers.RED_PANDA)), new RedPandaModel(context.getPart(ModEntityModelLayers.RED_PANDA)), 0.7F);
		//this.addFeature(new FoxHeldItemFeatureRenderer(this));
	}

	public Identifier getTexture(LivingEntityRenderState redPandaEntity) {
		return TEXTURE;
	}

	@Override
	public LivingEntityRenderState createRenderState() {
		return new LivingEntityRenderState();
	}
}
