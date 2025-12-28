package net.ent.entstupidstuff.client.render.entity;


import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.client.ModEntityModelLayers;
import net.ent.entstupidstuff.client.entity.mob.SporeperEntity;
import net.ent.entstupidstuff.client.render.entity.feature.SporeperChargeFeatureRenderer;
import net.ent.entstupidstuff.client.render.entity.feature.SporeperGlowRender;
import net.ent.entstupidstuff.client.render.entity.model.SporeperModel;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.state.CreeperEntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

public class SporeperRenderer extends MobEntityRenderer<SporeperEntity, CreeperEntityRenderState, SporeperModel>{
	private static final Identifier TEXTURE = Identifier.of(EntStupidStuff.MOD_ID, "textures/entity/sporeper/sporeper.png");
    //private static final Identifier TEXTURE_C = Identifier.of(EntStupidStuff.MOD_ID, "textures/entity/sporeper/sporeper_charged.png");

	public SporeperRenderer(EntityRendererFactory.Context context) {
		super(context, new SporeperModel(context.getPart(ModEntityModelLayers.SPOREPER)), 0.5F);
		//this.addFeature(new SporeperGlowRender(this));
		this.addFeature(new SporeperChargeFeatureRenderer(this, context.getEntityModels()));
	}

	protected void scale(CreeperEntityRenderState creeperEntityRenderState, MatrixStack matrixStack) {
		float f = creeperEntityRenderState.fuseTime;
		float g = 1.0F + MathHelper.sin(f * 100.0F) * f * 0.01F;
		f = MathHelper.clamp(f, 0.0F, 1.0F);
		f *= f;
		f *= f;
		float h = (1.0F + f * 0.4F) * g;
		float i = (1.0F + f * 0.1F) / g;
		matrixStack.scale(h, i, h);
	}

	protected float getAnimationCounter(CreeperEntityRenderState creeperEntityRenderState) {
		float f = creeperEntityRenderState.fuseTime;
		return (int)(f * 10.0F) % 2 == 0 ? 0.0F : MathHelper.clamp(f, 0.5F, 1.0F);
	}

	public Identifier getTexture(CreeperEntityRenderState creeperEntityRenderState) {
        if (creeperEntityRenderState.charged) 
            return TEXTURE;
		return TEXTURE;
	}

	public CreeperEntityRenderState createRenderState() {
		return new CreeperEntityRenderState();
	}

	public void updateRenderState(SporeperEntity creeperEntity, CreeperEntityRenderState creeperEntityRenderState, float f) {
		super.updateRenderState(creeperEntity, creeperEntityRenderState, f);
		creeperEntityRenderState.fuseTime = creeperEntity.getLerpedFuseTime(f);
		creeperEntityRenderState.charged = creeperEntity.isCharged();
	}
}
