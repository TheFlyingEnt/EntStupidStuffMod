package net.ent.entstupidstuff.client.render.entity;


import com.mojang.blaze3d.vertex.PoseStack;
import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.client.ModEntityModelLayers;
import net.ent.entstupidstuff.client.entity.mob.SporeperEntity;
import net.ent.entstupidstuff.client.render.entity.feature.SporeperChargeFeatureRenderer;
import net.ent.entstupidstuff.client.render.entity.model.SporeperModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.CreeperRenderState;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class SporeperRenderer extends MobRenderer<SporeperEntity, CreeperRenderState, SporeperModel>{
	private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/sporeper/sporeper.png");
    //private static final Identifier TEXTURE_C = Identifier.of(EntStupidStuff.MOD_ID, "textures/entity/sporeper/sporeper_charged.png");

	public SporeperRenderer(EntityRendererProvider.Context context) {
		super(context, new SporeperModel(context.bakeLayer(ModEntityModelLayers.SPOREPER)), 0.5F);
		//this.addFeature(new SporeperGlowRender(this));
		this.addLayer(new SporeperChargeFeatureRenderer(this, context.getModelSet()));
	}

	protected void scale(CreeperRenderState creeperEntityRenderState, PoseStack matrixStack) {
		float f = creeperEntityRenderState.swelling;
		float g = 1.0F + Mth.sin(f * 100.0F) * f * 0.01F;
		f = Mth.clamp(f, 0.0F, 1.0F);
		f *= f;
		f *= f;
		float h = (1.0F + f * 0.4F) * g;
		float i = (1.0F + f * 0.1F) / g;
		matrixStack.scale(h, i, h);
	}

	protected float getAnimationCounter(CreeperRenderState creeperEntityRenderState) {
		float f = creeperEntityRenderState.swelling;
		return (int)(f * 10.0F) % 2 == 0 ? 0.0F : Mth.clamp(f, 0.5F, 1.0F);
	}

	public ResourceLocation getTextureLocation(CreeperRenderState creeperEntityRenderState) {
        if (creeperEntityRenderState.isPowered) 
            return TEXTURE;
		return TEXTURE;
	}

	public CreeperRenderState createRenderState() {
		return new CreeperRenderState();
	}

	public void extractRenderState(SporeperEntity creeperEntity, CreeperRenderState creeperEntityRenderState, float f) {
		super.extractRenderState(creeperEntity, creeperEntityRenderState, f);
		creeperEntityRenderState.swelling = creeperEntity.getLerpedFuseTime(f);
		creeperEntityRenderState.isPowered = creeperEntity.isCharged();
	}
}
