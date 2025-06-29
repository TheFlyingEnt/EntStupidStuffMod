package net.ent.entstupidstuff.client.render.entity;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.client.render.ModModelLayers;
import net.ent.entstupidstuff.client.render.entity.model.SunkenSkeletonModel;
import net.ent.entstupidstuff.entity.generic.GenericSkeletonBow;
import net.ent.entstupidstuff.entity.mob.SkeletonCrossbowEntity;
import net.ent.entstupidstuff.entity.mob.SunkenSkeletonEntity;
import net.minecraft.client.render.entity.BipedEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.feature.ArmorFeatureRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.util.Identifier;

public class SunkenSkeletonEntityRenderer extends /*SkeletonEntityRenderer<SunkenSkeletonEntity>*/ BipedEntityRenderer<GenericSkeletonBow, SunkenSkeletonModel<GenericSkeletonBow>>{


    public SunkenSkeletonEntityRenderer(EntityRendererFactory.Context context) {
		//super(context, ModModelLayers.SUNKEN_SKELTON, EntityModelLayers.SKELETON_INNER_ARMOR, EntityModelLayers.SKELETON_OUTER_ARMOR);
        this(context, ModModelLayers.SUNKEN_SKELTON, EntityModelLayers.SKELETON_INNER_ARMOR, EntityModelLayers.SKELETON_OUTER_ARMOR);
	}

	public SunkenSkeletonEntityRenderer(EntityRendererFactory.Context ctx, EntityModelLayer layer, EntityModelLayer legArmorLayer, EntityModelLayer bodyArmorLayer) {
		//super(ctx, layer, legArmorLayer, bodyArmorLayer);
        this(ctx, legArmorLayer, bodyArmorLayer, new SunkenSkeletonModel<>(ctx.getPart(layer)));

	}

    public SunkenSkeletonEntityRenderer(
		EntityRendererFactory.Context context, EntityModelLayer entityModelLayer, EntityModelLayer entityModelLayer2, SunkenSkeletonModel<GenericSkeletonBow> skeletonEntityModel
	) {
		super(context, skeletonEntityModel, 0.5F);
		this.addFeature(
			new ArmorFeatureRenderer<>(
				this, new SunkenSkeletonModel<>(context.getPart(entityModelLayer)), new SunkenSkeletonModel<>(context.getPart(entityModelLayer2)), context.getModelManager()
			)
		);
	}

    @Override
	public Identifier getTexture(GenericSkeletonBow soulEnt) {

        if (soulEnt instanceof SunkenSkeletonEntity) {
			SunkenSkeletonEntity.Variant var = ((SunkenSkeletonEntity) soulEnt).getVariant();

        	if (var == SunkenSkeletonEntity.Variant.Variant1) 
            	return Identifier.of(EntStupidStuff.MOD_ID, "textures/entity/sunken_skeleton/sunken_skeleton_1.png");
        	else if (var == SunkenSkeletonEntity.Variant.Variant2)
            	return Identifier.of(EntStupidStuff.MOD_ID, "textures/entity/sunken_skeleton/sunken_skeleton_2.png");
        	else {
            	return Identifier.of(EntStupidStuff.MOD_ID, "textures/entity/sunken_skeleton/sunken_skeleton_3.png"); 
        	} 
		}
		else if (soulEnt instanceof SkeletonCrossbowEntity ) {
			SkeletonCrossbowEntity.Variant var = ((SkeletonCrossbowEntity) soulEnt).getVariant();

        	if (var == SkeletonCrossbowEntity.Variant.Variant1) 
            	return Identifier.of(EntStupidStuff.MOD_ID, "textures/entity/sunken_skeleton/sunken_skeleton_1.png");
        	else if (var == SkeletonCrossbowEntity.Variant.Variant2)
            	return Identifier.of(EntStupidStuff.MOD_ID, "textures/entity/sunken_skeleton/sunken_skeleton_2.png");
        	else {
            	return Identifier.of(EntStupidStuff.MOD_ID, "textures/entity/sunken_skeleton/sunken_skeleton_3.png"); 
        	} 
		}
		else {
			return Identifier.of(EntStupidStuff.MOD_ID, "textures/entity/sunken_skeleton/sunken_skeleton_3.png");
		}
        

	}

}
