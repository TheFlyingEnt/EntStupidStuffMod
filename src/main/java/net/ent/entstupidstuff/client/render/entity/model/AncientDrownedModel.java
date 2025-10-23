package net.ent.entstupidstuff.client.render.entity.model;

import net.minecraft.client.model.Dilation;
import net.minecraft.client.model.ModelData;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.ModelPartBuilder;
import net.minecraft.client.model.ModelPartData;
import net.minecraft.client.model.ModelTransform;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.ZombieEntityModel;
import net.minecraft.client.render.entity.state.ZombieEntityRenderState;
import net.minecraft.util.math.MathHelper;

public class AncientDrownedModel extends ZombieEntityModel<ZombieEntityRenderState>{

    public AncientDrownedModel(ModelPart modelPart) {
        super(modelPart);
    }

    public static TexturedModelData getTexturedModelData(Dilation dilation) {
      ModelData modelData = BipedEntityModel.getModelData(dilation, 0.0F);
      ModelPartData modelPartData = modelData.getRoot();
      modelPartData.addChild("left_arm", ModelPartBuilder.create().uv(32, 48).cuboid(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, dilation), ModelTransform.origin(5.0F, 2.0F, 0.0F));
      ModelPartData left_leg = modelPartData.addChild("left_leg", ModelPartBuilder.create().uv(16, 48).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, dilation), ModelTransform.origin(1.9F, 12.0F, 0.0F));

      /*ModelPartData cube_r1 = left_leg.addChild("chain", ModelPartBuilder.create().uv(0, 40).cuboid(-0.6478F, -2.0F, -0.0308F, 9.0F, 3.0F, 0.0F, dilation)
		.uv(0, 32).cuboid(8.3522F, -3.0F, -2.0308F, 4.0F, 4.0F, 4.0F, dilation)
		.uv(-3, 40).cuboid(-0.6478F, 0.0F, -1.0308F, 9.0F, 0.0F, 3.0F, dilation), ModelTransform.of(-1.9F, 10.0F, 2.0F, -0.2992F, -0.833F, 0.395F));*/

      ModelPartData chain_wrap = left_leg.addChild("chain", ModelPartBuilder.create().uv(0, 36).cuboid(0.05F, -3.0F, -1.75F, 4.0F, 3.0F, 4.0F, new Dilation(0.75F)), ModelTransform.origin(-2.15F, 10.0F, -0.5F));

      ModelPartData chain_ball = chain_wrap.addChild("chain_ball", ModelPartBuilder.create(), ModelTransform.origin(0.5F, -2.5F, -2.25F));

      ModelPartData chain_r1 = chain_ball.addChild("chain_r1", ModelPartBuilder.create().uv(3, 43).cuboid(5.3522F, 0.0F, -1.0308F, 3.0F, 0.0F, 3.0F, dilation)
		.uv(0, 32).cuboid(8.3522F, -3.0F, -2.0308F, 4.0F, 4.0F, 4.0F, dilation)
		.uv(6, 43).cuboid(5.3522F, -2.0F, -0.0308F, 3.0F, 3.0F, 0.0F, dilation), ModelTransform.of(0.0F, 0.0F, 0.0F, -0.2992F, -0.833F, 0.395F));

      return TexturedModelData.of(modelData, 64, 64);
   }

   @Override
	public void setAngles(ZombieEntityRenderState zombieEntityRenderState) {
		super.setAngles(zombieEntityRenderState);
		if (zombieEntityRenderState.leftArmPose == BipedEntityModel.ArmPose.THROW_SPEAR) {
			this.leftArm.pitch = this.leftArm.pitch * 0.5F - (float) Math.PI;
			this.leftArm.yaw = 0.0F;
		}

		if (zombieEntityRenderState.rightArmPose == BipedEntityModel.ArmPose.THROW_SPEAR) {
			this.rightArm.pitch = this.rightArm.pitch * 0.5F - (float) Math.PI;
			this.rightArm.yaw = 0.0F;
		}

		float f = zombieEntityRenderState.leaningPitch;
		if (f > 0.0F) {
			this.rightArm.pitch = MathHelper.lerpAngleRadians(f, this.rightArm.pitch, (float) (-Math.PI * 4.0 / 5.0))
				+ f * 0.35F * MathHelper.sin(0.1F * zombieEntityRenderState.age);
			this.leftArm.pitch = MathHelper.lerpAngleRadians(f, this.leftArm.pitch, (float) (-Math.PI * 4.0 / 5.0))
				- f * 0.35F * MathHelper.sin(0.1F * zombieEntityRenderState.age);
			this.rightArm.roll = MathHelper.lerpAngleRadians(f, this.rightArm.roll, -0.15F);
			this.leftArm.roll = MathHelper.lerpAngleRadians(f, this.leftArm.roll, 0.15F);
			this.leftLeg.pitch = this.leftLeg.pitch - f * 0.55F * MathHelper.sin(0.1F * zombieEntityRenderState.age);
			this.rightLeg.pitch = this.rightLeg.pitch + f * 0.55F * MathHelper.sin(0.1F * zombieEntityRenderState.age);
			this.head.pitch = 0.0F;
		}
	}
    
}
