package net.ent.entstupidstuff.client.render.entity.model.skeleton;

import net.ent.entstupidstuff.client.entity.generic.GenericSkeletonCrossbowPose;
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
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.HumanoidArm;

public class MetalSkeletonModel<S extends CrossbowSkeletonEntityRenderState> extends SkeletonModel<S>{

    public ModelPart rightarm;
    public ModelPart leftarm;

    public MetalSkeletonModel(ModelPart modelPart) {
        super(modelPart);

        this.head.setPos(this.head.x, this.head.y, this.head.z);
        this.body.setPos(this.body.x, this.body.y, this.body.z);
        this.leftLeg.setPos(this.leftLeg.x, this.leftLeg.y, this.leftLeg.z);

        this.rightarm = modelPart.getChild(PartNames.RIGHT_ARM);
        this.leftarm = modelPart.getChild(PartNames.LEFT_ARM);
    }

    public static LayerDefinition createBodyLayer() {
		MeshDefinition meshDefinition = HumanoidModel.createMesh(CubeDeformation.NONE, 0.0F);
		PartDefinition partDefinition = meshDefinition.getRoot();
		createMetalSkeletonMesh(partDefinition);
		return LayerDefinition.create(meshDefinition, 64, 64);
	}

    protected static void createMetalSkeletonMesh(PartDefinition partDefinition) {
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

        right_leg.addOrReplaceChild("right_leg_pant", CubeListBuilder.create().texOffs(48, 48).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)), PartPose.offset(0.0F, 0.0F, 0.0F));
		right_leg.addOrReplaceChild("right_leg_layer", CubeListBuilder.create().texOffs(0, 30).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.25F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        left_leg.addOrReplaceChild("left_leg_pant", CubeListBuilder.create().texOffs(32, 48).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)), PartPose.offset(0.0F, 0.0F, 0.0F));
		left_leg.addOrReplaceChild("left_leg_layer", CubeListBuilder.create().texOffs(48, 30).mirror().addBox(-1.0F, 0.0F, -1.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.25F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));
	
        PartDefinition body = partDefinition.getChild("body");
        
        body.addOrReplaceChild("body_layer", CubeListBuilder.create().texOffs(15, 32).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.25F))
            .texOffs(1, 48).addBox(-4.0F, 12.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        left_arm.addOrReplaceChild("left_arm_layer", CubeListBuilder.create().texOffs(56, 30).mirror().addBox(-1.0F, -1.0F, -1.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.25F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));
        right_arm.addOrReplaceChild("right_arm_layer", CubeListBuilder.create().texOffs(40, 30).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.25F)), PartPose.offset(0.0F, 0.0F, 0.0F));

    }

    @Deprecated
    public static LayerDefinition getTexturedModelData() {

        MeshDefinition modelData = SkeletonModel.createMesh(CubeDeformation.NONE, 0.0F);

		modelData.getRoot();

        modelData.getRoot().addOrReplaceChild(
			PartNames.HEAD, CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
            /*.uv(32, 0).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.0F))*/, PartPose.offset(0.0F, 0.0F, 0.0F)
        );
		
        modelData.getRoot().addOrReplaceChild(
            PartNames.BODY, CubeListBuilder.create().texOffs(16, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
		    .texOffs(15, 32).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.25F))
            .texOffs(1, 48).addBox(-4.0F, 12.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)), PartPose.offset(0.0F, 0.0F, 0.0F)
        );

        modelData.getRoot().addOrReplaceChild(
            PartNames.RIGHT_ARM, CubeListBuilder.create().texOffs(56, 16).mirror().addBox(-1.0F, -2.0F, -1.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
		    .texOffs(56, 30).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.25F)), PartPose.offset(5.0F, 2.0F, 0.0F)
            );

		modelData.getRoot().addOrReplaceChild(
            PartNames.LEFT_ARM, CubeListBuilder.create().texOffs(40, 16).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F))
		    .texOffs(40, 30).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.25F)), PartPose.offset(-5.0F, 2.0F, 0.0F)
        );

		modelData.getRoot().addOrReplaceChild(
            PartNames.RIGHT_LEG, CubeListBuilder.create().texOffs(48, 48).addBox(-6.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
		    .texOffs(0, 30).addBox(-5.0F, 0.0F, -1.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.25F))
		    .texOffs(0, 16).addBox(-5.0F, 0.0F, -1.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.25F)), PartPose.offset(2.0F, 12.0F, 0.0F)
        );

		modelData.getRoot().addOrReplaceChild(
            PartNames.LEFT_LEG, CubeListBuilder.create().texOffs(48, 16).mirror().addBox(-1.0F, 0.0F, -1.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
		    .texOffs(32, 48).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F))
		    .texOffs(48, 30).mirror().addBox(-1.0F, 0.0F, -1.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.25F)).mirror(false), PartPose.offset(2.0F, 12.0F, 0.0F)
        );
		
        return LayerDefinition.create(modelData, 64, 64);

    }  
    
    @Override
    public void setupAnim(S state) {
		super.setupAnim(state);
		this.head.yRot = state.yRot * (float) (Math.PI / 180.0);
		this.head.xRot = state.xRot * (float) (Math.PI / 180.0);
		if (state.isPassenger) {
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
			float f = state.walkAnimationSpeed;
			float g = state.walkAnimationPos;
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
