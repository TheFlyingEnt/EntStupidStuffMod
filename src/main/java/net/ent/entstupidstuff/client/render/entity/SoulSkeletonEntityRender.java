package net.ent.entstupidstuff.client.render.entity;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.client.render.entity.model.SoulSkeletonGlowRender;
import net.ent.entstupidstuff.entity.mob.SoulSkeletonEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.SkeletonEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.util.Identifier;

//this.addFeature(new SoulSkeletonGlowRender<>(this));

//@Environment(EnvType.CLIENT)
public class SoulSkeletonEntityRender extends SkeletonEntityRenderer<SoulSkeletonEntity>  {

	private static final Identifier TEXTURE = Identifier.of(EntStupidStuff.MOD_ID, "textures/entity/soul_skeleton.png");

	public SoulSkeletonEntityRender(EntityRendererFactory.Context context) {
		super(context, EntityModelLayers.SKELETON, EntityModelLayers.SKELETON_INNER_ARMOR, EntityModelLayers.SKELETON_OUTER_ARMOR);
		//this.addFeature(new SkeletonOverlayFeatureRenderer<>(this, context.getModelLoader(), EntityModelLayers.STRAY_OUTER, field_49165));
		this.addFeature(new SoulSkeletonGlowRender<>(this));
	}

	public SoulSkeletonEntityRender(EntityRendererFactory.Context ctx, EntityModelLayer layer, EntityModelLayer legArmorLayer, EntityModelLayer bodyArmorLayer) {
		super(ctx, layer, legArmorLayer, bodyArmorLayer);
		this.addFeature(new SoulSkeletonGlowRender<>(this));

	}

	public Identifier getTexture(SoulSkeletonEntity soulEnt) {
		return TEXTURE;
	}

    


}

