package net.ent.entstupidstuff.client.render.entity.state;

import net.ent.entstupidstuff.entity.generic.GenericSkeletonCrossbow;
import net.minecraft.client.item.ItemModelManager;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.state.ArmedEntityRenderState;
import net.minecraft.client.render.entity.state.BipedEntityRenderState;
import net.minecraft.client.render.item.ItemRenderState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemDisplayContext;
import net.minecraft.util.Arm;

public class CrossbowSkeletonEntityRenderState extends BipedEntityRenderState {
	public Arm mainArm = Arm.RIGHT;
	public BipedEntityModel.ArmPose rightArmPose = BipedEntityModel.ArmPose.EMPTY;
	public final ItemRenderState rightHandItemState = new ItemRenderState();
	public BipedEntityModel.ArmPose leftArmPose = BipedEntityModel.ArmPose.EMPTY;
	public final ItemRenderState leftHandItemState = new ItemRenderState();

    public boolean hasVehicle;
	public boolean attacking;
	public Arm illagerMainArm = Arm.RIGHT;
	public GenericSkeletonCrossbow.State illagerState = GenericSkeletonCrossbow.State.NEUTRAL;
	public int crossbowPullTime;
	public int itemUseTime;
	public float handSwingProgress;

	public boolean shaking;
	public boolean holdingBow;

    public ItemRenderState getMainHandItemState() {
		return this.mainArm == Arm.RIGHT ? this.rightHandItemState : this.leftHandItemState;
	}

    public static void updateRenderState(LivingEntity entity, ArmedEntityRenderState state, ItemModelManager itemModelManager) {
		state.mainArm = entity.getMainArm();
		itemModelManager.updateForLivingEntity(state.rightHandItemState, entity.getStackInArm(Arm.RIGHT), ItemDisplayContext.THIRD_PERSON_RIGHT_HAND, entity);
		itemModelManager.updateForLivingEntity(state.leftHandItemState, entity.getStackInArm(Arm.LEFT), ItemDisplayContext.THIRD_PERSON_LEFT_HAND, entity);
	}
    
}
