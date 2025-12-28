package net.ent.entstupidstuff.client.render.entity;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.client.entity.mob.FungalSkeletonEntity;
import net.ent.entstupidstuff.client.render.entity.feature.FungalSkeletonGlowRender;
import net.minecraft.client.render.entity.AbstractSkeletonEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.EquipmentModelData;
import net.minecraft.client.render.entity.state.SkeletonEntityRenderState;
import net.minecraft.util.Identifier;

public class FungalSkeletonEntityRender extends AbstractSkeletonEntityRenderer<FungalSkeletonEntity, SkeletonEntityRenderState>  {
    private static final Identifier TEXTURE = Identifier.of(EntStupidStuff.MOD_ID, "textures/entity/fungal_skeleton.png");
	private static final Identifier OVERLAY_TEXTURE = Identifier.ofVanilla("textures/entity/fungal_skeleton_overlay.png");

	public FungalSkeletonEntityRender(EntityRendererFactory.Context context) {
		super(context, EntityModelLayers.SKELETON, EntityModelLayers.SKELETON_EQUIPMENT);
		
		this.addFeature(new FungalSkeletonGlowRender(this));
		//this.addFeature(new SkeletonOverlayFeatureRenderer<>(this, context.getEntityModels(), ModEntityModelLayers.FUNGAL_SKELTON_OUTER, OVERLAY_TEXTURE));
	}

	public FungalSkeletonEntityRender(EntityRendererFactory.Context ctx, EntityModelLayer layer, EquipmentModelData<EntityModelLayer> armor) {
		super(ctx, layer, armor);
		this.addFeature(new FungalSkeletonGlowRender(this));
        //this.addFeature(new SkeletonOverlayFeatureRenderer<>(this, ctx.getEntityModels(), ModEntityModelLayers.FUNGAL_SKELTON_OUTER, OVERLAY_TEXTURE));

	}

	public Identifier getTexture(SkeletonEntityRenderState soulEnt) {
		return TEXTURE;
	}

	@Override
	public SkeletonEntityRenderState createRenderState() {
		return new SkeletonEntityRenderState();
	}
    
}
