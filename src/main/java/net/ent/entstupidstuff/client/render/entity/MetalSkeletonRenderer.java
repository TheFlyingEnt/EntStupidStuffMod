package net.ent.entstupidstuff.client.render.entity;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.client.render.ModEntityModelLayers;
import net.ent.entstupidstuff.client.render.entity.state.MetalSkeletonRenderState;
import net.ent.entstupidstuff.entity.mob.MetalSkeletonEntity;
import net.minecraft.client.render.entity.AbstractSkeletonEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory.Context;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.EquipmentModelData;
import net.minecraft.client.render.entity.model.SkeletonEntityModel;
import net.minecraft.util.Identifier;

public class MetalSkeletonRenderer extends AbstractSkeletonEntityRenderer<MetalSkeletonEntity, MetalSkeletonRenderState> {

	public MetalSkeletonRenderer(Context context, EntityModelLayer layer, EquipmentModelData<EntityModelLayer> equipmentModelData) {
		super(context, ModEntityModelLayers.METAL_SKELETON, equipmentModelData);
	}

	public MetalSkeletonRenderer(Context context, EquipmentModelData<EntityModelLayer> equipmentModelData, SkeletonEntityModel<MetalSkeletonRenderState> skeletonEntityModel) {
		super(context, equipmentModelData, skeletonEntityModel);
	}

	private static final Identifier TEXTURE3 = Identifier.of(EntStupidStuff.MOD_ID, "textures/entity/metal_skeleton/metal_skeleton_03.png");
	private static final Identifier TEXTURE2 = Identifier.of(EntStupidStuff.MOD_ID, "textures/entity/metal_skeleton/metal_skeleton_02.png");
	private static final Identifier TEXTURE1 = Identifier.of(EntStupidStuff.MOD_ID, "textures/entity/metal_skeleton/metal_skeleton_01.png");
    

	@Override
	public Identifier getTexture(MetalSkeletonRenderState state) {
		return switch (state.variant) {
			case BLUE -> TEXTURE2;
			case RED -> TEXTURE3;
			default -> TEXTURE1;
		};
	}

	@Override
	public void updateRenderState(MetalSkeletonEntity entity, MetalSkeletonRenderState state, float tickDelta) {
		super.updateRenderState(entity, state, tickDelta);
		state.variant = entity.getVariant();
	}

	@Override
	public MetalSkeletonRenderState createRenderState() {
		return new MetalSkeletonRenderState();
	}
}
