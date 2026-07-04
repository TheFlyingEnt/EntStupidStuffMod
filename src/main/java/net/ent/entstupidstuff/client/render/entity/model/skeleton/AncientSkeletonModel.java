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

public class AncientSkeletonModel <S extends CrossbowSkeletonEntityRenderState> extends SkeletonModel<S> {

    public AncientSkeletonModel(ModelPart modelPart) {
        super(modelPart);
    }

    public static LayerDefinition createBodyLayer() {
		MeshDefinition meshDefinition = HumanoidModel.createMesh(CubeDeformation.NONE, 0.0F);
		PartDefinition partDefinition = meshDefinition.getRoot();
		createCoralSkeletonMesh(partDefinition);
		return LayerDefinition.create(meshDefinition, 64, 64);
	}

    @SuppressWarnings("unused")
    protected static void createCoralSkeletonMesh(PartDefinition partdefinition) {
		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition hat = head.addOrReplaceChild("hat", CubeListBuilder.create().texOffs(32, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.5F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(16, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition body_back = body.addOrReplaceChild("body_back", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition buckle_r1 = body_back.addOrReplaceChild("buckle_r1", CubeListBuilder.create().texOffs(5, 37).addBox(-2.0F, -2.0F, -0.1F, 4.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.9562F, 3.9668F, -2.5F, 0.0F, 0.0F, -0.3927F));

		PartDefinition band_r1 = body_back.addOrReplaceChild("band_r1", CubeListBuilder.create().texOffs(31, 32).addBox(15.0F, -1.0F, -2.0F, 0.0F, 2.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(21, 37).addBox(0.0F, -1.0F, 3.0F, 15.0F, 2.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(16, 32).addBox(0.0F, -1.0F, -2.0F, 0.0F, 2.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(12, 41).addBox(9.0F, -2.0F, -2.1F, 2.0F, 4.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(21, 37).addBox(0.0F, -1.0F, -2.0F, 15.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, 0.0F, -0.5F, 0.0F, 0.0F, 0.9163F));

		PartDefinition gold_pouch = body_back.addOrReplaceChild("gold_pouch", CubeListBuilder.create().texOffs(10, 39).addBox(-3.0046F, -3.7717F, -3.0F, 6.0F, 8.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(27, 46).addBox(2.4954F, -5.7717F, -3.5F, 1.0F, 2.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(27, 46).mirror().addBox(-3.5046F, -5.7717F, -3.5F, 1.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(0, 34).addBox(-2.5046F, -5.7717F, 2.5F, 5.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 41).addBox(-2.5046F, -5.7717F, -3.5F, 5.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0046F, 6.2717F, 5.55F, 0.0F, 0.0F, 0.0873F));

		PartDefinition gold_pouch_r1 = gold_pouch.addOrReplaceChild("gold_pouch_r1", CubeListBuilder.create().texOffs(23, 40).addBox(-2.5F, 0.0F, -2.5F, 5.0F, 0.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0229F, -5.1413F, 0.0F, -0.0873F, 0.0F, 0.1484F));

		PartDefinition right_arm = partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(40, 16).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, 2.0F, 0.0F));

		PartDefinition left_arm = partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(48, 16).mirror().addBox(-1.0F, -2.0F, -1.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(5.0F, 2.0F, 0.0F));

		PartDefinition right_leg = partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 16).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, 12.0F, 0.0F));

		PartDefinition left_leg = partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(8, 16).mirror().addBox(-1.0F, 0.0F, -1.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(2.0F, 12.0F, 0.0F));

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
