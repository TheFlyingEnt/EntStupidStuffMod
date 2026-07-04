package net.ent.entstupidstuff.client.render.entity.renderer;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.client.ModEntityModelLayers;
import net.ent.entstupidstuff.client.entity.generic.GenericSkeletonCrossbow;
import net.ent.entstupidstuff.client.render.entity.state.CrossbowSkeletonEntityRenderState;
import net.ent.entstupidstuff.item.base.CannonItem;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.AbstractSkeletonRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;

public class AncientSkeletonRenderer extends AbstractSkeletonRenderer<GenericSkeletonCrossbow, CrossbowSkeletonEntityRenderState> {

    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/skeleton/ancient_skeleton.png");

    public AncientSkeletonRenderer(Context context) {
        super(context, ModEntityModelLayers.ANCIENT_SKELETON, ModelLayers.SKELETON_ARMOR);
    }

    @Override
    public ResourceLocation getTextureLocation(CrossbowSkeletonEntityRenderState livingEntityRenderState) {
        return TEXTURE;
    }

    @Override
    public CrossbowSkeletonEntityRenderState createRenderState() {
       return new CrossbowSkeletonEntityRenderState();
    }

    @Override
    protected HumanoidModel.ArmPose getArmPose(GenericSkeletonCrossbow entity, HumanoidArm arm) {
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

            if (entity.isChargingCrossbow() && mainHandItem.getItem() instanceof CannonItem) {
                return HumanoidModel.ArmPose.CROSSBOW_CHARGE;
            }
            
            if (mainHandItem.getItem() instanceof CannonItem && CannonItem.isCharged(mainHandItem)) {
                return HumanoidModel.ArmPose.CROSSBOW_HOLD;
            }

        }
        
        return HumanoidModel.ArmPose.EMPTY;
    }

    @Override
    public void extractRenderState(GenericSkeletonCrossbow entity, CrossbowSkeletonEntityRenderState entityState, float tickDelta) {
        super.extractRenderState(entity, entityState, tickDelta);

        CrossbowSkeletonEntityRenderState.extractArmedEntityRenderState(entity, entityState, this.itemModelResolver);
        entityState.armPose = entity.getArmPose();
		entityState.maxCrossbowChageDuration = CrossbowItem.getChargeDuration(entity.getUseItem(), entity);
    }

    
}
