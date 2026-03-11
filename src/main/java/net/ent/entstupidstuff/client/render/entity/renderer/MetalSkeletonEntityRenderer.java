package net.ent.entstupidstuff.client.render.entity.renderer;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.client.ModEntityModelLayers;
import net.ent.entstupidstuff.client.entity.mob.MetalSkeletonEntity;
import net.ent.entstupidstuff.client.render.entity.state.MetalSkeletonRenderState;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.AbstractSkeletonRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;

public class MetalSkeletonEntityRenderer extends AbstractSkeletonRenderer<MetalSkeletonEntity, MetalSkeletonRenderState> {

	private static final ResourceLocation TEXTURE3 = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/skeleton/metal_skeleton_3.png");
	private static final ResourceLocation TEXTURE2 = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/skeleton/metal_skeleton_2.png");
	private static final ResourceLocation TEXTURE1 = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/skeleton/metal_skeleton_1.png");

	public MetalSkeletonEntityRenderer(Context context) {
		super(context, ModEntityModelLayers.METAL_SKELETON, ModelLayers.SKELETON_ARMOR);
	}
    
	@Override
	public ResourceLocation getTextureLocation(MetalSkeletonRenderState state) {
		return switch (state.variant) {
			case BLUE -> TEXTURE2;
			case RED -> TEXTURE3;
			default -> TEXTURE1;
		};
	}

    @Override
    protected HumanoidModel.ArmPose getArmPose(MetalSkeletonEntity entity, HumanoidArm arm) {
        ItemStack mainHandItem = entity.getMainHandItem();
        
        if (entity.getMainArm() == arm) {
            if (entity.isChargingCrossbow() && mainHandItem.getItem() instanceof CrossbowItem) {
                return HumanoidModel.ArmPose.CROSSBOW_CHARGE;
            }
            
            if (mainHandItem.getItem() instanceof CrossbowItem && CrossbowItem.isCharged(mainHandItem)) {
                return HumanoidModel.ArmPose.CROSSBOW_HOLD;
            }
            
            if (entity.isAggressive() && mainHandItem.getItem() instanceof BowItem) {
                return HumanoidModel.ArmPose.BOW_AND_ARROW;
            }
        }
        
        return HumanoidModel.ArmPose.EMPTY;
    }

	@Override
	public void extractRenderState(MetalSkeletonEntity entity, MetalSkeletonRenderState state, float tickDelta) {
		super.extractRenderState(entity, state, tickDelta);
		state.variant = entity.getVariant();

        MetalSkeletonRenderState.extractArmedEntityRenderState(entity, state, this.itemModelResolver);
        state.armPose = entity.getArmPose();
		state.maxCrossbowChageDuration = CrossbowItem.getChargeDuration(entity.getUseItem(), entity);
	}

	@Override
	public MetalSkeletonRenderState createRenderState() {
		return new MetalSkeletonRenderState();
	}
}
