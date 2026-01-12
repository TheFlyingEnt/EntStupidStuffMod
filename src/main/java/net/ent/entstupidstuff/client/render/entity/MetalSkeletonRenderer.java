package net.ent.entstupidstuff.client.render.entity;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.client.ModEntityModelLayers;
import net.ent.entstupidstuff.client.entity.mob.MetalSkeletonEntity;
import net.ent.entstupidstuff.client.render.entity.state.MetalSkeletonRenderState;
import net.minecraft.client.model.SkeletonModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.AbstractSkeletonRenderer;
import net.minecraft.client.renderer.entity.ArmorModelSet;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;

public class MetalSkeletonRenderer extends AbstractSkeletonRenderer<MetalSkeletonEntity, MetalSkeletonRenderState> {

	public MetalSkeletonRenderer(Context context, ModelLayerLocation layer, ArmorModelSet<ModelLayerLocation> equipmentModelData) {
		super(context, ModEntityModelLayers.METAL_SKELETON, equipmentModelData);
	}

	public MetalSkeletonRenderer(Context context, ArmorModelSet<ModelLayerLocation> equipmentModelData, SkeletonModel<MetalSkeletonRenderState> skeletonEntityModel) {
		super(context, equipmentModelData, skeletonEntityModel);
	}

	private static final ResourceLocation TEXTURE3 = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/metal_skeleton/metal_skeleton_03.png");
	private static final ResourceLocation TEXTURE2 = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/metal_skeleton/metal_skeleton_02.png");
	private static final ResourceLocation TEXTURE1 = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/metal_skeleton/metal_skeleton_01.png");
    

	@Override
	public ResourceLocation getTextureLocation(MetalSkeletonRenderState state) {
		return switch (state.variant) {
			case BLUE -> TEXTURE2;
			case RED -> TEXTURE3;
			default -> TEXTURE1;
		};
	}

	@Override
	public void extractRenderState(MetalSkeletonEntity entity, MetalSkeletonRenderState state, float tickDelta) {
		super.extractRenderState(entity, state, tickDelta);
		state.variant = entity.getVariant();
	}

	@Override
	public MetalSkeletonRenderState createRenderState() {
		return new MetalSkeletonRenderState();
	}
}
