package net.ent.entstupidstuff.client.render.entity.model;

import net.ent.entstupidstuff.client.render.entity.state.CrossbowSkeletonEntityRenderState;
import net.ent.entstupidstuff.entity.generic.GenericSkeletonCrossbow;
import net.minecraft.client.model.Dilation;
import net.minecraft.client.model.ModelData;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.ModelPartBuilder;
import net.minecraft.client.model.ModelTransform;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.entity.model.ArmPosing;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.EntityModelPartNames;
import net.minecraft.client.render.entity.model.SkeletonEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Arm;
import net.minecraft.util.math.MathHelper;

//ENT UPDATE: Changed SunkenSkeletonEntity to SkeletonEntity 8/1 1:00a - Unteated
public class SunkenSkeletonModel <S extends CrossbowSkeletonEntityRenderState> extends BipedEntityModel<S> {

    public ModelPart rightarm;
    public ModelPart leftarm;


    public SunkenSkeletonModel(ModelPart modelPart) {
        super(modelPart);
        this.head.setOrigin(this.head.originX, this.head.originY, this.head.originZ);
        this.body.setOrigin(this.body.originX, this.body.originY, this.body.originZ);
        this.leftLeg.setOrigin(this.leftLeg.originX, this.leftLeg.originY, this.leftLeg.originZ);

        this.rightarm = modelPart.getChild(EntityModelPartNames.RIGHT_ARM);
        this.leftarm = modelPart.getChild(EntityModelPartNames.LEFT_ARM);

    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = SkeletonEntityModel.getModelData(Dilation.NONE, 0.0F);


        ModelPartBuilder head = ModelPartBuilder.create() //Works
        .uv(0, 0).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.0F))
		.uv(32, 0).cuboid(-1.0F, -16.0F, -4.002F, 10.0F, 10.0F, 0.0F, new Dilation(0.0F))
		.uv(20, 0).cuboid(1.0F, -3.0F, 2.0F, 4.0F, 0.0F, 4.0F, new Dilation(0.0F));

        ModelPartBuilder body = ModelPartBuilder.create()
        .uv(16, 16).cuboid(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F)
        .uv(12, 30).cuboid(2.0F, 0.0F, -2.0F, 2.0F, 2.0F, 0.0F, new Dilation(0.0F))
        .uv(12, 30).cuboid(-6.0F, -8.0F, -2.0F, 2.0F, 2.0F, 0.0F, new Dilation(0.0F));

        ModelPartBuilder right_leg = ModelPartBuilder.create()
        .uv(20, 4).cuboid(-2.0F, 4.0F, -2.0F, 4.0F, 0.0F, 4.0F, new Dilation(0.0F))
		.uv(40, 16).mirrored().cuboid(-1.0F, 0.0F, -1.0F, 2.0F, 12.0F, 2.0F, new Dilation(0.0F)).mirrored(false);

        modelData.getRoot().addChild(
			EntityModelPartNames.RIGHT_ARM, ModelPartBuilder.create().uv(40, 16).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 12.0F, 2.0F), ModelTransform.origin(-5.0F, 2.0F, 0.0F)
		);

        modelData.getRoot().addChild(
			EntityModelPartNames.LEFT_ARM,
			ModelPartBuilder.create().uv(40, 16).mirrored().cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 12.0F, 2.0F),
			ModelTransform.origin(5.0F, 2.0F, 0.0F)
		);

        modelData.getRoot().addChild(
			EntityModelPartNames.LEFT_LEG,
			ModelPartBuilder.create().uv(0, 16).mirrored().cuboid(-1.0F, 0.0F, -1.0F, 2.0F, 12.0F, 2.0F),
			ModelTransform.origin(2.0F, 12.0F, 0.0F)
		);

        

        modelData.getRoot().addChild(EntityModelPartNames.HEAD, head, ModelTransform.origin(0.0F, 0, 0.0F));
        modelData.getRoot().addChild(EntityModelPartNames.BODY, body, ModelTransform.origin(0.0F, 12, 0.0F));
        modelData.getRoot().addChild(EntityModelPartNames.RIGHT_LEG, right_leg, ModelTransform.origin(-2.0F, 12.0F, 0.0F));

        return TexturedModelData.of(modelData, 64, 32);

    } // GenericSkeletonCrossbow

    @Override
    public void setAngles(S crossbowSkeletonEntityRenderState) {
		super.setAngles(crossbowSkeletonEntityRenderState);
		this.head.yaw = crossbowSkeletonEntityRenderState.relativeHeadYaw * (float) (Math.PI / 180.0);
		this.head.pitch = crossbowSkeletonEntityRenderState.pitch * (float) (Math.PI / 180.0);
		if (crossbowSkeletonEntityRenderState.hasVehicle) {
			this.rightArm.pitch = (float) (-Math.PI / 5);
			this.rightArm.yaw = 0.0F;
			this.rightArm.roll = 0.0F;
			this.leftArm.pitch = (float) (-Math.PI / 5);
			this.leftArm.yaw = 0.0F;
			this.leftArm.roll = 0.0F;
			this.rightLeg.pitch = -1.4137167F;
			this.rightLeg.yaw = (float) (Math.PI / 10);
			this.rightLeg.roll = 0.07853982F;
			this.leftLeg.pitch = -1.4137167F;
			this.leftLeg.yaw = (float) (-Math.PI / 10);
			this.leftLeg.roll = -0.07853982F;
		} else {
			float f = crossbowSkeletonEntityRenderState.limbSwingAmplitude;
			float g = crossbowSkeletonEntityRenderState.limbSwingAnimationProgress;
			this.rightArm.pitch = MathHelper.cos(g * 0.6662F + (float) Math.PI) * 2.0F * f * 0.5F;
			this.rightArm.yaw = 0.0F;
			this.rightArm.roll = 0.0F;
			this.leftArm.pitch = MathHelper.cos(g * 0.6662F) * 2.0F * f * 0.5F;
			this.leftArm.yaw = 0.0F;
			this.leftArm.roll = 0.0F;
			this.rightLeg.pitch = MathHelper.cos(g * 0.6662F) * 1.4F * f * 0.5F;
			this.rightLeg.yaw = 0.0F;
			this.rightLeg.roll = 0.0F;
			this.leftLeg.pitch = MathHelper.cos(g * 0.6662F + (float) Math.PI) * 1.4F * f * 0.5F;
			this.leftLeg.yaw = 0.0F;
			this.leftLeg.roll = 0.0F;
		}

		//next
		GenericSkeletonCrossbow.State state = crossbowSkeletonEntityRenderState.illagerState;
		if (state == GenericSkeletonCrossbow.State.ATTACKING) {
			if (crossbowSkeletonEntityRenderState.getMainHandItemState().isEmpty()) {
				ArmPosing.zombieArms(this.leftArm, this.rightArm, true, crossbowSkeletonEntityRenderState.handSwingProgress, crossbowSkeletonEntityRenderState.age);
			} else {
				ArmPosing.meleeAttack(
					this.rightArm, this.leftArm, crossbowSkeletonEntityRenderState.illagerMainArm, crossbowSkeletonEntityRenderState.handSwingProgress, crossbowSkeletonEntityRenderState.age
				);
			}
		} else if (state == GenericSkeletonCrossbow.State.CROSSBOW_HOLD) {
			ArmPosing.hold(this.rightArm, this.leftArm, this.head, true);
		} else if (state == GenericSkeletonCrossbow.State.CROSSBOW_CHARGE) {
			ArmPosing.charge(this.rightArm, this.leftArm, crossbowSkeletonEntityRenderState.crossbowPullTime, crossbowSkeletonEntityRenderState.itemUseTime, true);
		}
	}

	public void setArmAngle(CrossbowSkeletonEntityRenderState illagerEntityRenderState, Arm arm, MatrixStack matrixStack) {
		this.root.applyTransform(matrixStack);
		this.getAttackingArm(arm).applyTransform(matrixStack);
	}

	private ModelPart getAttackingArm(Arm arm) {
		return arm == Arm.LEFT ? this.leftArm : this.rightArm;
	}



}
