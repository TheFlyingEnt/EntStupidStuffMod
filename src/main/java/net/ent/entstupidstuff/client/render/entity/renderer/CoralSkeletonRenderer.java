package net.ent.entstupidstuff.client.render.entity.renderer;


import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.client.ModEntityModelLayers;
import net.ent.entstupidstuff.client.entity.mob.skeleton.CoralSkeletonEntity;
import net.ent.entstupidstuff.client.render.entity.state.skeleton.CoralSkeletonRenderState;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.AbstractSkeletonRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;

public class CoralSkeletonRenderer extends AbstractSkeletonRenderer<CoralSkeletonEntity, CoralSkeletonRenderState> {

    private static final ResourceLocation BRAIN_TEXTURE = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/skeleton/coral_skeleton_brain.png");
    private static final ResourceLocation FIRE_TEXTURE = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/skeleton/coral_skeleton_fire.png");
    private static final ResourceLocation HORN_TEXTURE = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/skeleton/coral_skeleton_horn.png");
    private static final ResourceLocation BUBBLE_TEXTURE = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/skeleton/coral_skeleton_bubble.png");
    private static final ResourceLocation TUBE_TEXTURE = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/skeleton/coral_skeleton_tube.png");
    private static final ResourceLocation UNUSED_TEXTURE = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/skeleton/coral_skeleton_unused.png");

    public CoralSkeletonRenderer(Context context) {
		super(context, ModEntityModelLayers.CORAL_SKELETON, ModelLayers.SKELETON_ARMOR);
	}

    @Override
    public ResourceLocation getTextureLocation(CoralSkeletonRenderState state) {
        return switch (state.variant) {
			case BRAIN -> BRAIN_TEXTURE;
			case FIRE -> FIRE_TEXTURE;
			case HORN -> HORN_TEXTURE;
			case BUBBLE -> BUBBLE_TEXTURE;
			case TUBE -> TUBE_TEXTURE;
			case UNUSED -> UNUSED_TEXTURE;
			default -> BRAIN_TEXTURE;
		};
    }

    @Override
    public CoralSkeletonRenderState createRenderState() {
        return new CoralSkeletonRenderState();
    }

    @Override
    protected HumanoidModel.ArmPose getArmPose(CoralSkeletonEntity entity, HumanoidArm arm) {
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
    public void extractRenderState(CoralSkeletonEntity entity, CoralSkeletonRenderState entityState, float tickDelta) {
        super.extractRenderState(entity, entityState, tickDelta);
        entityState.variant = entity.getVariant();

        CoralSkeletonRenderState.extractArmedEntityRenderState(entity, entityState, this.itemModelResolver);
        entityState.armPose = entity.getArmPose();
		entityState.maxCrossbowChageDuration = CrossbowItem.getChargeDuration(entity.getUseItem(), entity);
    }

}
