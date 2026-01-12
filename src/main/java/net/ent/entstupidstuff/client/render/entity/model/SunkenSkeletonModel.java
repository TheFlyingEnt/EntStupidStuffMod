package net.ent.entstupidstuff.client.render.entity.model;

import com.mojang.blaze3d.vertex.PoseStack;
import net.ent.entstupidstuff.client.entity.generic.GenericSkeletonCrossbow;
import net.ent.entstupidstuff.client.render.entity.state.CrossbowSkeletonEntityRenderState;
import net.minecraft.client.model.AnimationUtils;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.SkeletonModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartNames;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.HumanoidArm;

//ENT UPDATE: Changed SunkenSkeletonEntity to SkeletonEntity 8/1 1:00a - Unteated
public class SunkenSkeletonModel <S extends CrossbowSkeletonEntityRenderState> extends HumanoidModel<S> {

    public ModelPart rightarm;
    public ModelPart leftarm;


    public SunkenSkeletonModel(ModelPart modelPart) {
        super(modelPart);
        this.head.setPos(this.head.x, this.head.y, this.head.z);
        this.body.setPos(this.body.x, this.body.y, this.body.z);
        this.leftLeg.setPos(this.leftLeg.x, this.leftLeg.y, this.leftLeg.z);

        this.rightarm = modelPart.getChild(PartNames.RIGHT_ARM);
        this.leftarm = modelPart.getChild(PartNames.LEFT_ARM);

    }

    public static LayerDefinition getTexturedModelData() {
        MeshDefinition modelData = SkeletonModel.createMesh(CubeDeformation.NONE, 0.0F);


        CubeListBuilder head = CubeListBuilder.create() //Works
        .texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(32, 0).addBox(-1.0F, -16.0F, -4.002F, 10.0F, 10.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(20, 0).addBox(1.0F, -3.0F, 2.0F, 4.0F, 0.0F, 4.0F, new CubeDeformation(0.0F));

        CubeListBuilder body = CubeListBuilder.create()
        .texOffs(16, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F)
        .texOffs(12, 30).addBox(2.0F, 0.0F, -2.0F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F))
        .texOffs(12, 30).addBox(-6.0F, -8.0F, -2.0F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F));

        CubeListBuilder right_leg = CubeListBuilder.create()
        .texOffs(20, 4).addBox(-2.0F, 4.0F, -2.0F, 4.0F, 0.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(40, 16).mirror().addBox(-1.0F, 0.0F, -1.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false);

        modelData.getRoot().addOrReplaceChild(
			PartNames.RIGHT_ARM, CubeListBuilder.create().texOffs(40, 16).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 12.0F, 2.0F), PartPose.offset(-5.0F, 2.0F, 0.0F)
		);

        modelData.getRoot().addOrReplaceChild(
			PartNames.LEFT_ARM,
			CubeListBuilder.create().texOffs(40, 16).mirror().addBox(-1.0F, -2.0F, -1.0F, 2.0F, 12.0F, 2.0F),
			PartPose.offset(5.0F, 2.0F, 0.0F)
		);

        modelData.getRoot().addOrReplaceChild(
			PartNames.LEFT_LEG,
			CubeListBuilder.create().texOffs(0, 16).mirror().addBox(-1.0F, 0.0F, -1.0F, 2.0F, 12.0F, 2.0F),
			PartPose.offset(2.0F, 12.0F, 0.0F)
		);

        

        modelData.getRoot().addOrReplaceChild(PartNames.HEAD, head, PartPose.offset(0.0F, 0, 0.0F));
        modelData.getRoot().addOrReplaceChild(PartNames.BODY, body, PartPose.offset(0.0F, 0F, 0.0F));
        modelData.getRoot().addOrReplaceChild(PartNames.RIGHT_LEG, right_leg, PartPose.offset(-2.0F, 12.0F, 0.0F));

        return LayerDefinition.create(modelData, 64, 32);

    } // GenericSkeletonCrossbow

    @Override
    public void setupAnim(S crossbowSkeletonEntityRenderState) {
		super.setupAnim(crossbowSkeletonEntityRenderState);
		this.head.yRot = crossbowSkeletonEntityRenderState.yRot * (float) (Math.PI / 180.0);
		this.head.xRot = crossbowSkeletonEntityRenderState.xRot * (float) (Math.PI / 180.0);
		if (crossbowSkeletonEntityRenderState.isPassenger) {
			this.rightArm.xRot = (float) (-Math.PI / 5);
			this.rightArm.yRot = 0.0F;
			this.rightArm.zRot = 0.0F;
			this.leftArm.xRot = (float) (-Math.PI / 5);
			this.leftArm.yRot = 0.0F;
			this.leftArm.zRot = 0.0F;
			this.rightLeg.xRot = -1.4137167F;
			this.rightLeg.yRot = (float) (Math.PI / 10);
			this.rightLeg.zRot = 0.07853982F;
			this.leftLeg.xRot = -1.4137167F;
			this.leftLeg.yRot = (float) (-Math.PI / 10);
			this.leftLeg.zRot = -0.07853982F;
		} else {
			float f = crossbowSkeletonEntityRenderState.walkAnimationSpeed;
			float g = crossbowSkeletonEntityRenderState.walkAnimationPos;
			this.rightArm.xRot = Mth.cos(g * 0.6662F + (float) Math.PI) * 2.0F * f * 0.5F;
			this.rightArm.yRot = 0.0F;
			this.rightArm.zRot = 0.0F;
			this.leftArm.xRot = Mth.cos(g * 0.6662F) * 2.0F * f * 0.5F;
			this.leftArm.yRot = 0.0F;
			this.leftArm.zRot = 0.0F;
			this.rightLeg.xRot = Mth.cos(g * 0.6662F) * 1.4F * f * 0.5F;
			this.rightLeg.yRot = 0.0F;
			this.rightLeg.zRot = 0.0F;
			this.leftLeg.xRot = Mth.cos(g * 0.6662F + (float) Math.PI) * 1.4F * f * 0.5F;
			this.leftLeg.yRot = 0.0F;
			this.leftLeg.zRot = 0.0F;
		}

		//next
		GenericSkeletonCrossbow.State state = crossbowSkeletonEntityRenderState.illagerState;
		if (state == GenericSkeletonCrossbow.State.ATTACKING) {
			if (crossbowSkeletonEntityRenderState.getMainHandItem().isEmpty()) {
				AnimationUtils.animateZombieArms(this.leftArm, this.rightArm, true, crossbowSkeletonEntityRenderState.attackTime, crossbowSkeletonEntityRenderState.ageInTicks);
			} else {
				AnimationUtils.swingWeaponDown(
					this.rightArm, this.leftArm, crossbowSkeletonEntityRenderState.illagerMainArm, crossbowSkeletonEntityRenderState.attackTime, crossbowSkeletonEntityRenderState.ageInTicks
				);
			}
		} else if (state == GenericSkeletonCrossbow.State.CROSSBOW_HOLD) {
			AnimationUtils.animateCrossbowHold(this.rightArm, this.leftArm, this.head, true);
		} else if (state == GenericSkeletonCrossbow.State.CROSSBOW_CHARGE) {
			AnimationUtils.animateCrossbowCharge(this.rightArm, this.leftArm, crossbowSkeletonEntityRenderState.crossbowPullTime, crossbowSkeletonEntityRenderState.ticksUsingItem, true);
		}
	}

	public void setArmAngle(CrossbowSkeletonEntityRenderState illagerEntityRenderState, HumanoidArm arm, PoseStack matrixStack) {
		this.root.translateAndRotate(matrixStack);
		this.getAttackingArm(arm).translateAndRotate(matrixStack);
	}

	private ModelPart getAttackingArm(HumanoidArm arm) {
		return arm == HumanoidArm.LEFT ? this.leftArm : this.rightArm;
	}



}
