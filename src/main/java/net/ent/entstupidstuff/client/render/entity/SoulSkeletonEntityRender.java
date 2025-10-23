package net.ent.entstupidstuff.client.render.entity;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.client.render.entity.model.SoulSkeletonGlowRender;
import net.ent.entstupidstuff.entity.mob.SoulSkeletonEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.AbstractSkeletonEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.EquipmentModelData;
import net.minecraft.client.render.entity.state.SkeletonEntityRenderState;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class SoulSkeletonEntityRender extends AbstractSkeletonEntityRenderer<SoulSkeletonEntity, SkeletonEntityRenderState>  {
	private static final Identifier TEXTURE = Identifier.of(EntStupidStuff.MOD_ID, "textures/entity/soul_skeleton.png");

	public SoulSkeletonEntityRender(EntityRendererFactory.Context context) {
		super(context, EntityModelLayers.SKELETON, EntityModelLayers.SKELETON_EQUIPMENT);
		this.addFeature(new SoulSkeletonGlowRender(this));
	}

	public SoulSkeletonEntityRender(EntityRendererFactory.Context ctx, EntityModelLayer layer, EquipmentModelData<EntityModelLayer> armor) {
		super(ctx, layer, armor);
		this.addFeature(new SoulSkeletonGlowRender(this));

	}

	public Identifier getTexture(SkeletonEntityRenderState soulEnt) {
		return TEXTURE;
	}

	@Override
	public SkeletonEntityRenderState createRenderState() {
		return new SkeletonEntityRenderState();
	}

}

