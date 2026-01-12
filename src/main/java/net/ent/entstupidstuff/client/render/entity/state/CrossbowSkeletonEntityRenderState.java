package net.ent.entstupidstuff.client.render.entity.state;

import net.ent.entstupidstuff.client.entity.generic.GenericSkeletonCrossbow;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.entity.state.ArmedEntityRenderState;
import net.minecraft.client.renderer.entity.state.SkeletonRenderState;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemDisplayContext;

public class CrossbowSkeletonEntityRenderState extends SkeletonRenderState {
	public HumanoidArm mainArm = HumanoidArm.RIGHT;
	public HumanoidModel.ArmPose rightArmPose = HumanoidModel.ArmPose.EMPTY;
	public final ItemStackRenderState rightHandItem = new ItemStackRenderState();
	public HumanoidModel.ArmPose leftArmPose = HumanoidModel.ArmPose.EMPTY;
	public final ItemStackRenderState leftHandItem = new ItemStackRenderState();

    public boolean isPassenger;
	public boolean isAggressive;
	public HumanoidArm illagerMainArm = HumanoidArm.RIGHT;
	public GenericSkeletonCrossbow.State illagerState = GenericSkeletonCrossbow.State.NEUTRAL;
	public int crossbowPullTime;
	public int ticksUsingItem;
	public float attackTime;

	public boolean isShaking;
	public boolean isHoldingBow;

    public ItemStackRenderState getMainHandItem() {
		return this.mainArm == HumanoidArm.RIGHT ? this.rightHandItem : this.leftHandItem;
	}

    public static void extractArmedEntityRenderState(LivingEntity entity, ArmedEntityRenderState state, ItemModelResolver itemModelManager) {
		state.mainArm = entity.getMainArm();
		itemModelManager.updateForLiving(state.rightHandItem, entity.getItemHeldByArm(HumanoidArm.RIGHT), ItemDisplayContext.THIRD_PERSON_RIGHT_HAND, entity);
		itemModelManager.updateForLiving(state.leftHandItem, entity.getItemHeldByArm(HumanoidArm.LEFT), ItemDisplayContext.THIRD_PERSON_LEFT_HAND, entity);
	}
    
}
