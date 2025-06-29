package net.ent.entstupidstuff.client.render.entity;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.client.render.ModModelLayers;
import net.ent.entstupidstuff.entity.mob.SkeletonPirateCaptainEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.SkeletonEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.util.Identifier;

public class SkeletonPirateCaptainEntityRenderer extends SkeletonEntityRenderer<SkeletonPirateCaptainEntity>{

	//Textures:
	private static final Identifier TEXTURE = Identifier.of(EntStupidStuff.MOD_ID, "textures/entity/toadd/skeleton_pirate_captain.png");
	//private static final Identifier OUTERLAYER = Identifier.of(EntStupidStuff.MOD_ID, "textures/entity/toadd/skeleton_pirate_captain_overlay.png");



    public SkeletonPirateCaptainEntityRenderer(EntityRendererFactory.Context context) {
		super(context, ModModelLayers.SKELETON_PIRATE_CAPTAIN, EntityModelLayers.SKELETON_INNER_ARMOR, EntityModelLayers.SKELETON_OUTER_ARMOR);
		//this.addFeature(new SkeletonOverlayFeatureRenderer<>(this, context.getModelLoader(), ModModelLayers.SKELETON_PIRATE_CAPTAIN, OUTERLAYER));
	}

	public SkeletonPirateCaptainEntityRenderer(EntityRendererFactory.Context ctx, EntityModelLayer layer, EntityModelLayer legArmorLayer, EntityModelLayer bodyArmorLayer) {
		super(ctx, layer, legArmorLayer, bodyArmorLayer);
		//this.addFeature(new SkeletonOverlayFeatureRenderer<>(this, ctx.getModelLoader(), ModModelLayers.SKELETON_PIRATE_CAPTAIN, OUTERLAYER));
	}

	@Override
    public Identifier getTexture(SkeletonPirateCaptainEntity ent) {

		return TEXTURE;
        //return Identifier.of(EntStupidStuff.MOD_ID, "textures/entity/skeleton_pirate_captain.png");
		//return Identifier.of(EntStupidStuff.MOD_ID, "textures/entity/skeleton_pirate_captain_overlay.png");
	}

}
