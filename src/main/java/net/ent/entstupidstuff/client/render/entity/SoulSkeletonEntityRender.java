package net.ent.entstupidstuff.client.render.entity;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.client.entity.mob.SoulSkeletonEntity;
import net.ent.entstupidstuff.client.render.entity.feature.SoulSkeletonGlowRender;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.AbstractSkeletonRenderer;
import net.minecraft.client.renderer.entity.ArmorModelSet;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.SkeletonRenderState;
import net.minecraft.resources.ResourceLocation;

@Environment(EnvType.CLIENT)
public class SoulSkeletonEntityRender extends AbstractSkeletonRenderer<SoulSkeletonEntity, SkeletonRenderState>  {
	private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/soul_skeleton.png");

	public SoulSkeletonEntityRender(EntityRendererProvider.Context context) {
		super(context, ModelLayers.SKELETON, ModelLayers.SKELETON_ARMOR);
		this.addLayer(new SoulSkeletonGlowRender(this));
	}

	public SoulSkeletonEntityRender(EntityRendererProvider.Context ctx, ModelLayerLocation layer, ArmorModelSet<ModelLayerLocation> armor) {
		super(ctx, layer, armor);
		this.addLayer(new SoulSkeletonGlowRender(this));

	}

	public ResourceLocation getTextureLocation(SkeletonRenderState soulEnt) {
		return TEXTURE;
	}

	@Override
	public SkeletonRenderState createRenderState() {
		return new SkeletonRenderState();
	}

}

