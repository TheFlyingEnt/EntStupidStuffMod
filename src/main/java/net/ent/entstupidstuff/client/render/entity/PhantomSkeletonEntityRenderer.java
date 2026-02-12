package net.ent.entstupidstuff.client.render.entity;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.client.ModEntityModelLayers;
import net.ent.entstupidstuff.client.entity.mob.PhantomSkeletonEntity;
import net.ent.entstupidstuff.client.entity.mob.skeleton.CoralSkeletonEntity;
import net.ent.entstupidstuff.client.render.entity.state.PhantomSkeletonRenderState;
import net.ent.entstupidstuff.client.render.entity.state.skeleton.CoralSkeletonRenderState;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.AbstractSkeletonRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;

public class PhantomSkeletonEntityRenderer extends AbstractSkeletonRenderer<PhantomSkeletonEntity, PhantomSkeletonRenderState>{

    private static final ResourceLocation TEXTURE3 = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/skeleton/phantom_skeleton_1.png");
	private static final ResourceLocation TEXTURE2 = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/skeleton/phantom_skeleton_1.png");
	private static final ResourceLocation TEXTURE1 = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "textures/entity/skeleton/phantom_skeleton_1.png");
    
    public PhantomSkeletonEntityRenderer(EntityRendererProvider.Context context) {
		super(context, ModEntityModelLayers.PHANTOM_SKELETON, ModelLayers.SKELETON_ARMOR);
	}

    @Override
    protected RenderType getRenderType(PhantomSkeletonRenderState entity, boolean showBody, boolean translucent, boolean showOutline) {
        return RenderType.entityTranslucent(this.getTextureLocation(entity));
    }

	@Override
	public ResourceLocation getTextureLocation(PhantomSkeletonRenderState state) {
		return switch (state.variant) {
			case MELEE -> TEXTURE1;
			case CROSSBOW -> TEXTURE2;
			default -> TEXTURE3;
		};
	}

    @Override
    protected HumanoidModel.ArmPose getArmPose(PhantomSkeletonEntity entity, HumanoidArm arm) {
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
	public PhantomSkeletonRenderState createRenderState() {
		return new PhantomSkeletonRenderState();
	}

    @Override
    public void extractRenderState(PhantomSkeletonEntity entity, PhantomSkeletonRenderState entityState, float tickDelta) {
        super.extractRenderState(entity, entityState, tickDelta);
        entityState.variant = entity.getVariant();

        PhantomSkeletonRenderState.extractArmedEntityRenderState(entity, entityState, this.itemModelResolver);
        entityState.armPose = entity.getArmPose();
		entityState.maxCrossbowChageDuration = CrossbowItem.getChargeDuration(entity.getUseItem(), entity);
    }
}

