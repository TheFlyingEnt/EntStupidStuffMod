package net.ent.entstupidstuff.client.render.entity.model.skeleton;

import net.ent.entstupidstuff.client.entity.generic.GenericSkeletonCrossbowPose;
import net.ent.entstupidstuff.client.render.entity.state.CrossbowSkeletonEntityRenderState;
import net.minecraft.client.model.AnimationUtils;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.SkeletonModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.world.entity.HumanoidArm;

public class CoralSkeletonModel<S extends CrossbowSkeletonEntityRenderState> extends SkeletonModel<S> {

    public CoralSkeletonModel(ModelPart modelPart) {
        super(modelPart);
    }

    public static LayerDefinition createBodyLayer() {
		MeshDefinition meshDefinition = HumanoidModel.createMesh(CubeDeformation.NONE, 0.0F);
		PartDefinition partDefinition = meshDefinition.getRoot();
		createCoralSkeletonMesh(partDefinition);
		return LayerDefinition.create(meshDefinition, 64, 32);
	}

    @SuppressWarnings("unused")
    protected static void createCoralSkeletonMesh(PartDefinition partDefinition) {
		PartDefinition right_arm = partDefinition.addOrReplaceChild(
			"right_arm", CubeListBuilder.create().texOffs(40, 16).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 12.0F, 2.0F), PartPose.offset(-5.0F, 2.0F, 0.0F)
		);
		PartDefinition left_arm = partDefinition.addOrReplaceChild(
			"left_arm", CubeListBuilder.create().texOffs(40, 16).mirror().addBox(-1.0F, -2.0F, -1.0F, 2.0F, 12.0F, 2.0F), PartPose.offset(5.0F, 2.0F, 0.0F)
		);
		PartDefinition right_leg = partDefinition.addOrReplaceChild(
			"right_leg", CubeListBuilder.create().texOffs(0, 16).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 12.0F, 2.0F), PartPose.offset(-2.0F, 12.0F, 0.0F)
		);
		PartDefinition left_leg = partDefinition.addOrReplaceChild(
			"left_leg", CubeListBuilder.create().texOffs(0, 16).mirror().addBox(-1.0F, 0.0F, -1.0F, 2.0F, 12.0F, 2.0F), PartPose.offset(2.0F, 12.0F, 0.0F)
		);

        right_leg.addOrReplaceChild("right_leg_coral", CubeListBuilder.create().texOffs(24, 4).addBox(-2.0F, 4.0F, -2.0F, 2.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        partDefinition.getChild("head").addOrReplaceChild("head_coral", CubeListBuilder.create().texOffs(25, 0).addBox(2.0F, -3.0F, 2.0F, 3.0F, 0.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(22, 0).addBox(-1.0F, -3.0F, 2.0F, 3.0F, 0.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(32, 1).addBox(-1.0F, -15.0F, -4.01F, 9.0F, 9.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        partDefinition.getChild("body").addOrReplaceChild("body_coral", CubeListBuilder.create().texOffs(13, 30).addBox(-1.0F, -2.0F, 0.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(12, 30).mirror().addBox(5.0F, 6.0F, 0.0F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-4.0F, -6.0F, -2.0F));

	}

    @Override
    public void setupAnim(S state) {
        super.setupAnim(state);
        GenericSkeletonCrossbowPose skeletonArmPose = state.armPose;

        if (skeletonArmPose == GenericSkeletonCrossbowPose.CROSSBOW_HOLD) {
			AnimationUtils.animateCrossbowHold(this.rightArm, this.leftArm, this.head, state.mainArm == HumanoidArm.RIGHT);
		} else if (skeletonArmPose == GenericSkeletonCrossbowPose.CROSSBOW_CHARGE) {
			AnimationUtils.animateCrossbowCharge(
				this.rightArm, this.leftArm, state.maxCrossbowChageDuration, state.ticksUsingItem, state.mainArm == HumanoidArm.RIGHT
			);
        }
    }
    
}
