package net.ent.entstupidstuff.client.render.entity.model.zombie;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.ZombieModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.entity.state.ZombieRenderState;
import net.minecraft.util.Mth;

public class AncientDrownedModel extends ZombieModel<ZombieRenderState>{

    public AncientDrownedModel(ModelPart modelPart) {
        super(modelPart);
    }

    public static LayerDefinition getTexturedModelData(CubeDeformation dilation) {
      MeshDefinition modelData = HumanoidModel.createMesh(dilation, 0.0F);
      PartDefinition modelPartData = modelData.getRoot();
      modelPartData.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(32, 48).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, dilation), PartPose.offset(5.0F, 2.0F, 0.0F));
      PartDefinition left_leg = modelPartData.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(16, 48).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, dilation), PartPose.offset(1.9F, 12.0F, 0.0F));

      /*ModelPartData cube_r1 = left_leg.addChild("chain", ModelPartBuilder.create().uv(0, 40).cuboid(-0.6478F, -2.0F, -0.0308F, 9.0F, 3.0F, 0.0F, dilation)
		.uv(0, 32).cuboid(8.3522F, -3.0F, -2.0308F, 4.0F, 4.0F, 4.0F, dilation)
		.uv(-3, 40).cuboid(-0.6478F, 0.0F, -1.0308F, 9.0F, 0.0F, 3.0F, dilation), ModelTransform.of(-1.9F, 10.0F, 2.0F, -0.2992F, -0.833F, 0.395F));*/

      PartDefinition chain_wrap = left_leg.addOrReplaceChild("chain", CubeListBuilder.create().texOffs(0, 36).addBox(0.05F, -3.0F, -1.75F, 4.0F, 3.0F, 4.0F, new CubeDeformation(0.75F)), PartPose.offset(-2.15F, 10.0F, -0.5F));

      PartDefinition chain_ball = chain_wrap.addOrReplaceChild("chain_ball", CubeListBuilder.create(), PartPose.offset(0.5F, -2.5F, -2.25F));

      PartDefinition chain_r1 = chain_ball.addOrReplaceChild("chain_r1", CubeListBuilder.create().texOffs(3, 43).addBox(5.3522F, 0.0F, -1.0308F, 3.0F, 0.0F, 3.0F, dilation)
		.texOffs(0, 32).addBox(8.3522F, -3.0F, -2.0308F, 4.0F, 4.0F, 4.0F, dilation)
		.texOffs(6, 43).addBox(5.3522F, -2.0F, -0.0308F, 3.0F, 3.0F, 0.0F, dilation), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.2992F, -0.833F, 0.395F));

      return LayerDefinition.create(modelData, 64, 64);
   }

   @Override
	public void setupAnim(ZombieRenderState zombieEntityRenderState) {
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
