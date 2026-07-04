package net.ent.entstupidstuff.client.render.entity.model.skeleton;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartNames;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.entity.state.SkeletonRenderState;

public class PhantomSkeletonCaptainModel <S extends SkeletonRenderState> extends HumanoidModel<S> {
	public ModelPart rightarm;
    public ModelPart leftarm;


	public PhantomSkeletonCaptainModel(ModelPart modelPart) {
		super(modelPart);
		this.head.setPos(this.head.x, this.head.y, this.head.z);
        this.body.setPos(this.body.x, this.body.y, this.body.z);
        this.leftLeg.setPos(this.leftLeg.x, this.leftLeg.y, this.leftLeg.z);

        this.rightarm = modelPart.getChild(PartNames.RIGHT_ARM);
        this.leftarm = modelPart.getChild(PartNames.LEFT_ARM);
	}

    @SuppressWarnings("unused")
    public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition hat = head.addOrReplaceChild("hat", CubeListBuilder.create().texOffs(32, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.5F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition head_extra = head.addOrReplaceChild("head_extra", CubeListBuilder.create().texOffs(82, 26).addBox(-5.0F, 0.5833F, -4.5F, 10.0F, 1.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(64, 0).addBox(-7.0F, -1.4167F, -4.5F, 14.0F, 2.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(92, 22).addBox(-5.0F, -4.4167F, -4.5F, 10.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(92, 12).addBox(-4.0F, -3.4167F, -3.5F, 8.0F, 2.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(92, 22).addBox(-5.0F, -4.4167F, 4.5F, 10.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(90, 41).addBox(-5.0F, 3.5833F, -3.5F, 10.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -6.5833F, -0.6F));

		PartDefinition feather = head_extra.addOrReplaceChild("feather", CubeListBuilder.create().texOffs(88, 50).addBox(0.0F, -7.5F, -0.5F, 0.0F, 8.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, -0.9167F, 0.0F, -0.4185F, 0.1274F, 0.2783F));

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(16, 17).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition body_layer = body.addOrReplaceChild("body_layer", CubeListBuilder.create().texOffs(15, 32).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.25F))
		.texOffs(1, 48).addBox(-4.0F, 12.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition body_extra = body.addOrReplaceChild("body_extra", CubeListBuilder.create().texOffs(64, 37).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(64, 12).addBox(-4.5F, -2.0F, -2.5F, 9.0F, 19.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition right_arm = partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(40, 16).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, 2.0F, 0.0F));

		PartDefinition right_arm_layer = right_arm.addOrReplaceChild("right_arm_layer", CubeListBuilder.create().texOffs(40, 30).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.25F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition right_arm_extra = right_arm.addOrReplaceChild("right_arm_extra", CubeListBuilder.create().texOffs(112, 22).mirror().addBox(-3.5F, -1.5F, -1.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(-0.2F)).mirror(false)
		.texOffs(112, 37).addBox(-3.5F, 5.5F, -1.0F, 4.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, -1.0F, -1.0F));

		PartDefinition cube_r1 = right_arm_extra.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(112, 22).mirror().addBox(-3.0F, -2.0F, -1.0F, 4.0F, 2.0F, 4.0F, new CubeDeformation(-0.15F)).mirror(false), PartPose.offsetAndRotation(-2.0F, -0.25F, 0.0F, 0.0F, 0.0F, -0.3927F));

		PartDefinition left_arm = partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(56, 16).mirror().addBox(-1.0F, -2.0F, -1.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(5.0F, 2.0F, 0.0F));

		PartDefinition left_arm_layer = left_arm.addOrReplaceChild("left_arm_layer", CubeListBuilder.create().texOffs(56, 30).mirror().addBox(-1.0F, -1.0F, -1.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.25F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition left_arm_extra = left_arm.addOrReplaceChild("left_arm_extra", CubeListBuilder.create().texOffs(112, 22).addBox(-0.5F, -1.5F, -1.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(-0.2F))
		.texOffs(112, 37).mirror().addBox(-0.5F, 5.5F, -1.0F, 4.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-1.0F, -1.0F, -1.0F));

		PartDefinition cube_r2 = left_arm_extra.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(112, 22).addBox(-1.0F, -2.0F, -1.0F, 4.0F, 2.0F, 4.0F, new CubeDeformation(-0.15F)), PartPose.offsetAndRotation(2.0F, -0.25F, 0.0F, 0.0F, 0.0F, 0.3927F));

		PartDefinition right_leg = partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 16).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, 12.0F, 0.0F));

		PartDefinition right_leg_pant = right_leg.addOrReplaceChild("right_leg_pant", CubeListBuilder.create().texOffs(32, 48).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition right_leg_layer = right_leg.addOrReplaceChild("right_leg_layer", CubeListBuilder.create().texOffs(0, 30).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.25F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition right_leg_extra = right_leg.addOrReplaceChild("right_leg_extra", CubeListBuilder.create().texOffs(112, 0).addBox(-2.0F, 0.0F, -2.1F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition left_leg = partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(8, 16).mirror().addBox(-1.0F, 0.0F, -1.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(2.0F, 12.0F, 0.0F));

		PartDefinition left_leg_pant = left_leg.addOrReplaceChild("left_leg_pant", CubeListBuilder.create().texOffs(48, 48).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition left_leg_layer = left_leg.addOrReplaceChild("left_leg_layer", CubeListBuilder.create().texOffs(48, 31).mirror().addBox(-1.0F, 0.0F, -1.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.25F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition left_leg_extra = left_leg.addOrReplaceChild("left_leg_extra", CubeListBuilder.create().texOffs(112, 0).mirror().addBox(-5.0F, 10.0F, -2.1F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(87, 12).addBox(-4.5F, 17.0F, -1.6F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, -10.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 64);
	}

}
