package net.ent.entstupidstuff.client.render.entity.model.zombie;

import net.ent.entstupidstuff.client.render.entity.state.FrostbittenEntityRenderState;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.ZombieModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartNames;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;

public class FrostbittenZombieModel extends ZombieModel<FrostbittenEntityRenderState> {
    public FrostbittenZombieModel(ModelPart root) {
        super(root);
    }

	public static LayerDefinition getTexturedModelData(CubeDeformation dilation) {
		MeshDefinition modelData = HumanoidModel.createMesh(dilation, 0.0F);
		PartDefinition modelPartData = modelData.getRoot();
		modelPartData.addOrReplaceChild(
			PartNames.LEFT_ARM,
			CubeListBuilder.create().texOffs(32, 48).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, dilation),
			PartPose.offset(5.0F, 2.0F, 0.0F)
		);
		modelPartData.addOrReplaceChild(
			PartNames.LEFT_LEG,
			CubeListBuilder.create().texOffs(16, 48).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, dilation),
			PartPose.offset(1.9F, 12.0F, 0.0F)
		);
		return LayerDefinition.create(modelData, 64, 64);
	}

	@Override
	public void setupAnim(FrostbittenEntityRenderState zombieEntityRenderState) {
		super.setupAnim(zombieEntityRenderState);
		if (zombieEntityRenderState.leftArmPose == HumanoidModel.ArmPose.THROW_SPEAR) {
			this.leftArm.xRot = this.leftArm.xRot * 0.5F - (float) Math.PI;
			this.leftArm.yRot = 0.0F;
		}

		if (zombieEntityRenderState.rightArmPose == HumanoidModel.ArmPose.THROW_SPEAR) {
			this.rightArm.xRot = this.rightArm.xRot * 0.5F - (float) Math.PI;
			this.rightArm.yRot = 0.0F;
		}

		float f = zombieEntityRenderState.swimAmount;
		if (f > 0.0F) {
			this.rightArm.xRot = Mth.rotLerpRad(f, this.rightArm.xRot, (float) (-Math.PI * 4.0 / 5.0))
				+ f * 0.35F * Mth.sin(0.1F * zombieEntityRenderState.ageInTicks);
			this.leftArm.xRot = Mth.rotLerpRad(f, this.leftArm.xRot, (float) (-Math.PI * 4.0 / 5.0))
				- f * 0.35F * Mth.sin(0.1F * zombieEntityRenderState.ageInTicks);
			this.rightArm.zRot = Mth.rotLerpRad(f, this.rightArm.zRot, -0.15F);
			this.leftArm.zRot = Mth.rotLerpRad(f, this.leftArm.zRot, 0.15F);
			this.leftLeg.xRot = this.leftLeg.xRot - f * 0.55F * Mth.sin(0.1F * zombieEntityRenderState.ageInTicks);
			this.rightLeg.xRot = this.rightLeg.xRot + f * 0.55F * Mth.sin(0.1F * zombieEntityRenderState.ageInTicks);
			this.head.xRot = 0.0F;
		}
	}
}
