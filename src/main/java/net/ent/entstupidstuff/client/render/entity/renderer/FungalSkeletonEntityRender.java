package net.ent.entstupidstuff.client.render.entity.renderer;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.client.entity.mob.FungalSkeletonEntity;
import net.ent.entstupidstuff.client.render.entity.feature.FungalSkeletonGlowRender;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.AbstractSkeletonRenderer;
import net.minecraft.client.renderer.entity.ArmorModelSet;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.SkeletonRenderState;
import net.minecraft.resources.ResourceLocation;

public class FungalSkeletonEntityRender extends AbstractSkeletonRenderer<FungalSkeletonEntity, SkeletonRenderState>  {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/skeleton/sporebone.png");
	//private static final ResourceLocation OVERLAY_TEXTURE = ResourceLocation.withDefaultNamespace("textures/entity/skeleton/sporebone_overlay.png");

	public FungalSkeletonEntityRender(EntityRendererProvider.Context context) {
		super(context, ModelLayers.SKELETON, ModelLayers.SKELETON_ARMOR);
		
		this.addLayer(new FungalSkeletonGlowRender(this));
		//this.addFeature(new SkeletonOverlayFeatureRenderer<>(this, context.getEntityModels(), ModEntityModelLayers.SPOREBONE_OUTER, OVERLAY_TEXTURE));
	}

	public FungalSkeletonEntityRender(EntityRendererProvider.Context ctx, ModelLayerLocation layer, ArmorModelSet<ModelLayerLocation> armor) {
		super(ctx, layer, armor);
		this.addLayer(new FungalSkeletonGlowRender(this));
        //this.addFeature(new SkeletonOverlayFeatureRenderer<>(this, ctx.getEntityModels(), ModEntityModelLayers.SPOREBONE_OUTER, OVERLAY_TEXTURE));

	}

	public ResourceLocation getTextureLocation(SkeletonRenderState soulEnt) {
		return TEXTURE;
	}

	@Override
	public SkeletonRenderState createRenderState() {
		return new SkeletonRenderState();
	}
    
}
