package net.ent.entstupidstuff.client.render.entity;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.client.render.ModModelLayers;
import net.ent.entstupidstuff.entity.mob.MetalSkeletonEntity;
import net.ent.entstupidstuff.entity.mob.MetalSkeletonEntity.MetalSkeletonVariant;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.SkeletonEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.util.Identifier;

public class SkeletonGoldRenderer extends SkeletonEntityRenderer<MetalSkeletonEntity>{

    private static final Identifier TEXTURE3 = Identifier.of(EntStupidStuff.MOD_ID, "textures/entity/metal_skeleton/metal_skeleton_03.png");
	private static final Identifier TEXTURE2 = Identifier.of(EntStupidStuff.MOD_ID, "textures/entity/metal_skeleton/metal_skeleton_02.png");
	private static final Identifier TEXTURE1 = Identifier.of(EntStupidStuff.MOD_ID, "textures/entity/metal_skeleton/metal_skeleton_01.png");
    
    public SkeletonGoldRenderer(EntityRendererFactory.Context context) {
		super(context, ModModelLayers.SKELETON_PIRATE_CAPTAIN, EntityModelLayers.SKELETON_INNER_ARMOR, EntityModelLayers.SKELETON_OUTER_ARMOR);
		//this.addFeature(new SkeletonOverlayFeatureRenderer<>(this, context.getModelLoader(), ModModelLayers.SKELETON_PIRATE_CAPTAIN, OUTERLAYER));
	}

	public SkeletonGoldRenderer(EntityRendererFactory.Context ctx, EntityModelLayer layer, EntityModelLayer legArmorLayer, EntityModelLayer bodyArmorLayer) {
		super(ctx, layer, legArmorLayer, bodyArmorLayer);
		//this.addFeature(new SkeletonOverlayFeatureRenderer<>(this, ctx.getModelLoader(), ModModelLayers.SKELETON_PIRATE_CAPTAIN, OUTERLAYER));
	}

	@Override
    public Identifier getTexture(MetalSkeletonEntity ent) {

		if (ent.getVariant() == MetalSkeletonVariant.BLUE) {
			return TEXTURE2;
		} else if (ent.getVariant() == MetalSkeletonVariant.RED) {
			return TEXTURE3;
		} else {
			return TEXTURE1;
		}
 
		
        //return Identifier.of(EntStupidStuff.MOD_ID, "textures/entity/skeleton_pirate_captain.png");
		//return Identifier.of(EntStupidStuff.MOD_ID, "textures/entity/skeleton_pirate_captain_overlay.png");
	}
}
