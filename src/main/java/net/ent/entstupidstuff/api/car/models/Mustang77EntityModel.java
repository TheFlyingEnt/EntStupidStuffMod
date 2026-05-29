package net.ent.entstupidstuff.api.car.models;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.api.car.render.BaseCarRenderState;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.ResourceLocation;

public class Mustang77EntityModel extends BaseCarEntityModel<BaseCarRenderState> {

    private final ModelPart body;
	private final ModelPart steering_wheel;
	private final ModelPart shifter;
	private final ModelPart Front_Left_Wheel;
	private final ModelPart Front_Right_Wheel;
	private final ModelPart Back_Left_Wheel;
	private final ModelPart Back_Right_Wheel;
	private final ModelPart leftDoor;
	private final ModelPart rightDoor;
	private final ModelPart hood;

	public Mustang77EntityModel(ModelPart root) {
        super(root);
		this.body = root.getChild("body");
		this.steering_wheel = this.body.getChild("steering_wheel");
		this.shifter = this.body.getChild("shifter");
		this.Front_Left_Wheel = this.body.getChild("Front_Left_Wheel");
		this.Front_Right_Wheel = this.body.getChild("Front_Right_Wheel");
		this.Back_Left_Wheel = this.body.getChild("Back_Left_Wheel");
		this.Back_Right_Wheel = this.body.getChild("Back_Right_Wheel");
		this.leftDoor = this.body.getChild("left_door");
		this.rightDoor = this.body.getChild("right_door");
		this.hood = this.body.getChild("hood");
	}

    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
        ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "mustang77"), "main"
    );

    @SuppressWarnings("unused")
    public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(316, 289).addBox(9.2934F, 7.8446F, -17.8096F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(284, 104).addBox(9.2934F, 7.8446F, 12.1904F, 2.0F, 1.0F, 13.0F, new CubeDeformation(0.0F))
		.texOffs(304, 143).addBox(2.5934F, -2.0554F, -48.8096F, 5.0F, 5.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(80, 334).addBox(-23.4066F, -2.0554F, -48.8096F, 5.0F, 5.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(160, 252).addBox(-25.4066F, -4.0554F, -46.8096F, 35.0F, 7.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(228, 48).addBox(-26.4066F, -5.0554F, -14.8096F, 36.0F, 6.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(142, 187).addBox(-2.4066F, -5.0554F, -12.8096F, 11.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(302, 88).addBox(-2.4066F, -4.0554F, -15.8096F, 11.0F, 5.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(110, 339).addBox(8.5934F, -2.0554F, -15.8096F, 1.0F, 3.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(54, 255).addBox(-10.4066F, 2.9446F, -13.8096F, 8.0F, 5.0F, 17.0F, new CubeDeformation(0.0F))
		.texOffs(54, 255).addBox(-10.4066F, 2.9446F, 3.1904F, 8.0F, 5.0F, 17.0F, new CubeDeformation(0.0F))
		.texOffs(276, 221).addBox(-7.4066F, 1.9446F, -10.8096F, 3.0F, 6.0F, 13.0F, new CubeDeformation(0.0F))
		.texOffs(0, 48).addBox(-27.4066F, -14.0554F, -5.8096F, 39.0F, 2.0F, 25.0F, new CubeDeformation(0.0F))
		.texOffs(118, 83).addBox(-27.4066F, -4.0554F, -45.8096F, 7.0F, 2.0F, 33.0F, new CubeDeformation(0.0F))
		.texOffs(8, 121).addBox(4.5934F, -4.0554F, -45.8096F, 7.0F, 2.0F, 33.0F, new CubeDeformation(0.0F))
		.texOffs(142, 261).addBox(-26.4066F, -14.0554F, 12.1904F, 0.0F, 11.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(156, 312).addBox(10.5934F, -14.0554F, 12.1904F, 0.0F, 11.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(128, 48).addBox(-27.4066F, -7.0501F, 38.5553F, 39.0F, 15.0F, 11.0F, new CubeDeformation(0.0F))
		.texOffs(260, 181).addBox(-1.4066F, 2.9499F, 45.5553F, 14.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(276, 240).addBox(-28.4066F, 2.9499F, 45.5553F, 14.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(280, 150).addBox(-14.4066F, 3.9499F, 45.5553F, 13.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(322, 21).addBox(3.5934F, 1.6999F, 46.5553F, 2.0F, 5.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(0, 326).addBox(-21.4066F, 1.6999F, 46.5553F, 2.0F, 5.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(94, 277).addBox(-26.4066F, 7.4499F, 49.5553F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(292, 248).addBox(-26.4066F, 7.4499F, 47.5553F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(202, 339).addBox(-23.4066F, 7.4499F, 49.5553F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(84, 177).addBox(-27.4066F, -4.5501F, 45.2553F, 39.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(172, 183).addBox(-27.4066F, 1.9499F, 45.2553F, 39.0F, 2.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-23.4066F, 6.1946F, -18.8096F, 33.0F, 2.0F, 46.0F, new CubeDeformation(0.0F))
		.texOffs(96, 139).addBox(-26.4066F, -5.0554F, 16.1904F, 36.0F, 12.0F, 14.0F, new CubeDeformation(0.0F))
		.texOffs(44, 319).addBox(9.5934F, -5.0554F, 12.1904F, 2.0F, 6.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(44, 319).mirror().addBox(-27.4066F, -5.0554F, 12.1904F, 2.0F, 6.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(114, 299).addBox(9.5934F, -4.0554F, -33.2596F, 4.0F, 2.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(114, 299).addBox(9.5934F, -4.0554F, 30.7404F, 4.0F, 2.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(114, 299).mirror().addBox(-29.4066F, -4.0554F, -33.2596F, 4.0F, 2.0F, 10.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(114, 299).mirror().addBox(-29.4066F, -4.0554F, 30.7404F, 4.0F, 2.0F, 10.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(318, 10).addBox(12.5934F, 2.9446F, -47.8096F, 1.0F, 2.0F, 9.0F, new CubeDeformation(0.0F))
		.texOffs(232, 319).addBox(-29.3066F, 2.9446F, -47.8096F, 1.0F, 2.0F, 9.0F, new CubeDeformation(0.0F))
		.texOffs(238, 294).addBox(-27.1066F, 7.8446F, 12.1904F, 2.0F, 1.0F, 13.0F, new CubeDeformation(0.0F))
		.texOffs(330, 260).addBox(-27.1066F, 7.8446F, -17.8096F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(260, 72).addBox(-2.4066F, 3.9446F, -6.8096F, 11.0F, 4.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(260, 72).addBox(-22.4066F, 3.9446F, -6.8096F, 11.0F, 4.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(84, 165).addBox(-25.4066F, 6.9446F, -48.8096F, 35.0F, 2.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(228, 56).addBox(-25.4066F, 3.9446F, -29.8096F, 35.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(7.9066F, 9.0554F, -2.1904F));

		PartDefinition cube_r1 = body.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(66, 277).addBox(0.212F, -8.9058F, 4.9491F, 11.0F, 17.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(66, 277).addBox(20.212F, -8.9058F, 4.9491F, 11.0F, 17.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-22.6186F, -3.1496F, 1.2413F, -0.3927F, 0.0F, 0.0F));

		PartDefinition glass_r1 = body.addOrReplaceChild("glass_r1", CubeListBuilder.create().texOffs(158, 34).addBox(-3.988F, -9.9058F, -8.0509F, 37.0F, 0.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-22.4186F, -2.5496F, -2.7587F, 0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r2 = body.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(278, 255).addBox(32.012F, -9.9058F, -8.0509F, 2.0F, 2.0F, 14.0F, new CubeDeformation(0.0F))
		.texOffs(232, 278).addBox(-4.988F, -9.9058F, -8.0509F, 2.0F, 2.0F, 14.0F, new CubeDeformation(0.0F))
		.texOffs(206, 116).addBox(-3.988F, -9.9058F, 4.9491F, 36.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-22.4186F, -2.8496F, -3.0587F, 0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r3 = body.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(304, 48).addBox(-30.012F, 12.0942F, -45.0509F, 2.0F, 8.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.6054F, -3.1496F, -2.7587F, 0.0F, 0.0F, 0.3927F));

		PartDefinition left_door_r1 = body.addOrReplaceChild("left_door_r1", CubeListBuilder.create().texOffs(210, 294).addBox(-15.9776F, 5.0942F, -34.0784F, 2.0F, 5.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(154, 284).addBox(-15.9776F, 5.0942F, -22.0784F, 2.0F, 3.0F, 13.0F, new CubeDeformation(0.0F))
		.texOffs(312, 307).addBox(-15.9776F, 5.0942F, -9.0784F, 2.0F, 5.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-10.679F, -3.6496F, -12.7311F, 0.0F, 0.0F, 0.3927F));

		PartDefinition right_door_r1 = body.addOrReplaceChild("right_door_r1", CubeListBuilder.create().texOffs(54, 340).addBox(14.9776F, 2.0942F, -5.9728F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(310, 248).addBox(14.9776F, -3.9058F, 86.0272F, 3.0F, 9.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(160, 260).addBox(14.9776F, -3.9058F, 55.0272F, 3.0F, 9.0F, 15.0F, new CubeDeformation(0.0F))
		.texOffs(114, 311).addBox(14.9776F, -3.9058F, 23.0272F, 3.0F, 9.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(184, 284).addBox(14.9776F, -3.9058F, -3.9728F, 3.0F, 9.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.6342F, -3.7496F, -42.8367F, 0.0F, 0.0F, 0.3927F));

		PartDefinition left_door_r2 = body.addOrReplaceChild("left_door_r2", CubeListBuilder.create().texOffs(44, 340).addBox(-17.9776F, 2.0942F, -5.9728F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 286).addBox(-17.9776F, -3.9058F, -3.9728F, 3.0F, 9.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(212, 311).addBox(-17.9776F, -3.9058F, 23.0272F, 3.0F, 9.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-11.179F, -3.7496F, -42.8367F, 0.0F, 0.0F, -0.3927F));

		PartDefinition cube_r4 = body.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(170, 303).addBox(28.012F, 12.0942F, -45.0509F, 2.0F, 8.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-22.4186F, -3.1496F, -2.7587F, 0.0F, 0.0F, -0.3927F));

		PartDefinition cube_r5 = body.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(102, 321).addBox(-2.0F, -1.0F, -1.5F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(11.9548F, 3.9446F, -47.346F, 0.0F, -0.7854F, 0.0F));

		PartDefinition cube_r6 = body.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(316, 240).addBox(-7.0F, -1.0F, -6.0F, 7.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-14.8403F, 3.9446F, -53.1838F, 0.0F, 1.7453F, 0.0F));

		PartDefinition cube_r7 = body.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(246, 338).addBox(-0.0351F, -1.0F, -2.9875F, 4.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-22.7604F, 3.9446F, -52.1487F, 0.0F, -1.5708F, 0.0F));

		PartDefinition cube_r8 = body.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(296, 22).addBox(0.0F, -1.0F, -6.0F, 7.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.8729F, 3.9446F, -53.1838F, 0.0F, -1.7453F, 0.0F));

		PartDefinition cube_r9 = body.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(260, 88).addBox(-0.0351F, -1.0F, -11.9875F, 7.0F, 2.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-12.8604F, 3.9446F, -53.1487F, 0.0F, -1.5708F, 0.0F));

		PartDefinition cube_r10 = body.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(134, 326).addBox(-0.005F, -1.001F, -3.9799F, 6.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-26.4604F, 3.9446F, -50.6487F, 0.0F, -1.1781F, 0.0F));

		PartDefinition cube_r11 = body.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(252, 326).addBox(0.0F, -1.0F, -4.0F, 5.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-29.3066F, 3.9446F, -47.8096F, 0.0F, -0.7854F, 0.0F));

		PartDefinition cube_r12 = body.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(96, 113).addBox(-3.9649F, -1.0F, -2.9875F, 4.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.0472F, 3.9446F, -52.1487F, 0.0F, 1.5708F, 0.0F));

		PartDefinition cube_r13 = body.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(326, 54).addBox(-5.995F, -1.001F, -3.9799F, 6.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(10.7472F, 3.9446F, -50.6487F, 0.0F, 1.1781F, 0.0F));

		PartDefinition cube_r14 = body.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(192, 303).addBox(-5.0F, -1.0F, -4.0F, 5.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(13.5934F, 3.9446F, -47.8096F, 0.0F, 0.7854F, 0.0F));

		PartDefinition cube_r15 = body.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(304, 132).mirror().addBox(-2.0F, -0.0101F, -8.9695F, 4.0F, 2.0F, 9.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(304, 132).addBox(37.0F, -0.0101F, -8.9695F, 4.0F, 2.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-27.4066F, 1.6301F, 25.0691F, 1.5708F, 0.0F, 0.0F));

		PartDefinition cube_r16 = body.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(314, 97).mirror().addBox(-2.0F, 0.0F, -8.0F, 4.0F, 2.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(314, 97).addBox(37.0F, 0.0F, -8.0F, 4.0F, 2.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-27.4066F, -4.0554F, 30.7404F, 0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r17 = body.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(314, 107).mirror().addBox(-2.0F, 0.0F, 0.0F, 4.0F, 2.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(314, 107).addBox(37.0F, 0.0F, 0.0F, 4.0F, 2.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-27.4066F, -4.0554F, 40.7404F, -0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r18 = body.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(0, 305).mirror().addBox(-2.0F, -0.0101F, -0.0305F, 4.0F, 2.0F, 9.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(0, 305).addBox(37.0F, -0.0101F, -0.0305F, 4.0F, 2.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-27.4066F, 1.6301F, 46.4117F, -1.5708F, 0.0F, 0.0F));

		PartDefinition cube_r19 = body.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(304, 132).mirror().addBox(-2.0F, -0.0101F, -8.9695F, 4.0F, 2.0F, 9.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(304, 132).addBox(37.0F, -0.0101F, -8.9695F, 4.0F, 2.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-27.4066F, 1.6301F, -38.9309F, 1.5708F, 0.0F, 0.0F));

		PartDefinition cube_r20 = body.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(314, 97).mirror().addBox(-2.0F, 0.0F, -8.0F, 4.0F, 2.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(314, 97).addBox(37.0F, 0.0F, -8.0F, 4.0F, 2.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-27.4066F, -4.0554F, -33.2596F, 0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r21 = body.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(314, 107).mirror().addBox(-2.0F, 0.0F, 0.0F, 4.0F, 2.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(314, 107).addBox(37.0F, 0.0F, 0.0F, 4.0F, 2.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-27.4066F, -4.0554F, -23.2596F, -0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r22 = body.addOrReplaceChild("cube_r22", CubeListBuilder.create().texOffs(0, 305).mirror().addBox(-2.0F, -0.0101F, -0.0305F, 4.0F, 2.0F, 9.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(0, 305).addBox(37.0F, -0.0101F, -0.0305F, 4.0F, 2.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-27.4066F, 1.6301F, -17.5883F, -1.5708F, 0.0F, 0.0F));

		PartDefinition cube_r23 = body.addOrReplaceChild("cube_r23", CubeListBuilder.create().texOffs(304, 118).mirror().addBox(-34.011F, 4.0942F, 13.9491F, 2.0F, 4.0F, 10.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(26, 286).mirror().addBox(-34.011F, -1.9058F, 20.9491F, 2.0F, 7.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(304, 118).addBox(2.987F, 4.0942F, 13.9491F, 2.0F, 4.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(26, 286).addBox(2.987F, -1.9058F, 20.9491F, 2.0F, 7.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(306, 45).addBox(-15.012F, -5.9058F, -10.0509F, 4.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(260, 104).addBox(-15.012F, -2.9058F, -13.0509F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(278, 158).addBox(-15.012F, -5.9058F, -13.0509F, 18.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(258, 141).addBox(-33.012F, -5.9058F, -13.0509F, 18.0F, 4.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(194, 339).addBox(1.988F, -5.9058F, -11.0509F, 1.0F, 4.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(252, 319).addBox(-11.012F, -5.9058F, -11.0509F, 2.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.6054F, -3.1496F, -2.7587F, 0.3927F, 0.0F, 0.0F));

		PartDefinition cube_r24 = body.addOrReplaceChild("cube_r24", CubeListBuilder.create().texOffs(196, 158).addBox(-19.25F, -5.0F, -1.0F, 39.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.1566F, -5.0501F, 49.3553F, -0.5236F, 0.0F, 0.0F));

		PartDefinition cube_r25 = body.addOrReplaceChild("cube_r25", CubeListBuilder.create().texOffs(206, 109).addBox(-3.988F, -13.9058F, 49.9491F, 36.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-22.4186F, -3.1496F, -3.0087F, -0.3927F, 0.0F, 0.0F));

		PartDefinition cube_r26 = body.addOrReplaceChild("cube_r26", CubeListBuilder.create().texOffs(242, 30).addBox(-17.988F, 5.3442F, 7.9491F, 36.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.4186F, -13.9496F, 20.2413F, 0.0436F, 0.0F, 0.0F));

		PartDefinition cube_r27 = body.addOrReplaceChild("cube_r27", CubeListBuilder.create().texOffs(228, 68).addBox(-17.988F, 3.3442F, 4.9491F, 36.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.4186F, -15.9496F, 15.2413F, 0.0436F, 0.0F, 0.0F));

		PartDefinition cube_r28 = body.addOrReplaceChild("cube_r28", CubeListBuilder.create().texOffs(228, 68).addBox(-17.988F, 3.3442F, 4.9491F, 36.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.4186F, -13.9496F, 19.2413F, 0.0436F, 0.0F, 0.0F));

		PartDefinition cube_r29 = body.addOrReplaceChild("cube_r29", CubeListBuilder.create().texOffs(206, 74).mirror().addBox(-20.013F, -0.0932F, -0.0711F, 3.0F, 11.0F, 24.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(206, 74).addBox(15.989F, -0.0932F, -0.0711F, 3.0F, 11.0F, 24.0F, new CubeDeformation(0.0F))
		.texOffs(96, 118).addBox(-17.012F, 1.4911F, -0.2433F, 33.0F, 0.0F, 21.0F, new CubeDeformation(0.0F))
		.texOffs(174, 165).addBox(-17.012F, -0.0932F, 13.9289F, 33.0F, 11.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.3946F, -13.9496F, 19.2413F, -0.3491F, 0.0F, 0.0F));

		PartDefinition cube_r30 = body.addOrReplaceChild("cube_r30", CubeListBuilder.create().texOffs(16, 326).addBox(22.012F, -9.9058F, -45.0509F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(134, 218).addBox(5.012F, -9.9058F, -45.0509F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-22.4186F, -3.1496F, -5.7587F, 0.3927F, 0.0F, 0.0F));

		PartDefinition cube_r31 = body.addOrReplaceChild("cube_r31", CubeListBuilder.create().texOffs(228, 62).addBox(-2.988F, -9.9058F, -45.0509F, 35.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-22.4186F, -3.1496F, -4.7587F, 0.3927F, 0.0F, 0.0F));

		PartDefinition cube_r32 = body.addOrReplaceChild("cube_r32", CubeListBuilder.create().texOffs(277, 104).addBox(30.012F, 3.0942F, -10.0509F, 1.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(310, 264).addBox(20.012F, 3.0942F, -10.0509F, 1.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-22.4186F, -3.8496F, -2.2087F, -0.3927F, 0.0F, 0.0F));

		PartDefinition glass_r2 = body.addOrReplaceChild("glass_r2", CubeListBuilder.create().texOffs(308, 230).addBox(12.012F, 9.0942F, -5.0509F, 8.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-22.4186F, -3.1496F, -2.7587F, -0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r33 = body.addOrReplaceChild("cube_r33", CubeListBuilder.create().texOffs(0, 190).addBox(-3.988F, 7.0942F, -11.0509F, 37.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-22.4186F, -3.1496F, -2.7587F, -0.3927F, 0.0F, 0.0F));

		PartDefinition cube_r34 = body.addOrReplaceChild("cube_r34", CubeListBuilder.create().texOffs(254, 211).addBox(-7.988F, -3.0058F, -0.0009F, 19.0F, 3.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-9.4186F, -3.0496F, -50.8087F, -1.2217F, 0.0F, 0.0F));

		PartDefinition left_door_r3 = body.addOrReplaceChild("left_door_r3", CubeListBuilder.create().texOffs(104, 261).mirror().addBox(-15.9776F, 5.0942F, -0.0784F, 2.0F, 5.0F, 17.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(264, 278).mirror().addBox(-15.9776F, 5.0942F, 16.9216F, 2.0F, 3.0F, 13.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(318, 143).mirror().addBox(-15.9776F, 5.0942F, 29.9216F, 2.0F, 5.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-10.679F, -3.6496F, 12.2689F, 0.0F, 0.0F, 0.3927F));

		PartDefinition left_door_r4 = body.addOrReplaceChild("left_door_r4", CubeListBuilder.create().texOffs(310, 248).mirror().addBox(-17.9776F, -3.9058F, 61.0272F, 3.0F, 9.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(160, 260).mirror().addBox(-17.9776F, -3.9058F, 30.0272F, 3.0F, 9.0F, 15.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-11.179F, -3.7496F, -17.8367F, 0.0F, 0.0F, -0.3927F));

		PartDefinition right_door_r2 = body.addOrReplaceChild("right_door_r2", CubeListBuilder.create().texOffs(318, 143).addBox(13.9776F, 5.0942F, 29.9216F, 2.0F, 5.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(264, 278).addBox(13.9776F, 5.0942F, 16.9216F, 2.0F, 3.0F, 13.0F, new CubeDeformation(0.0F))
		.texOffs(104, 261).addBox(13.9776F, 5.0942F, -0.0784F, 2.0F, 5.0F, 17.0F, new CubeDeformation(0.0F))
		.texOffs(312, 293).addBox(13.9776F, 5.0942F, -34.0784F, 2.0F, 5.0F, 9.0F, new CubeDeformation(0.0F))
		.texOffs(56, 200).addBox(13.9776F, 5.0942F, -59.0784F, 2.0F, 5.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(94, 283).addBox(13.9776F, 5.0942F, -47.0784F, 2.0F, 3.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.1342F, -3.6496F, 12.2689F, 0.0F, 0.0F, -0.3927F));

		PartDefinition Engine = body.addOrReplaceChild("Engine", CubeListBuilder.create().texOffs(308, 221).addBox(-4.5F, -15.0F, -29.0F, 7.0F, 2.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(318, 156).addBox(1.5F, -16.0F, -29.0F, 8.0F, 3.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(78, 321).addBox(-8.5F, -17.0F, -44.0F, 8.0F, 3.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(210, 284).addBox(-11.5F, -18.0F, -42.0F, 3.0F, 3.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(302, 97).addBox(-0.5F, -16.0F, -44.0F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(312, 321).addBox(1.5F, -17.0F, -44.0F, 8.0F, 3.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(232, 255).addBox(-8.5F, -13.0F, -38.0F, 8.0F, 8.0F, 15.0F, new CubeDeformation(0.0F))
		.texOffs(254, 190).addBox(-5.5F, -14.0F, -35.0F, 14.0F, 9.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(258, 118).addBox(1.5F, -13.0F, -38.0F, 8.0F, 8.0F, 15.0F, new CubeDeformation(0.0F))
		.texOffs(238, 308).addBox(-3.5F, -18.0F, -40.0F, 11.0F, 9.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(270, 326).addBox(2.5F, -17.0F, -38.0F, 1.0F, 1.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(254, 221).addBox(3.5F, -17.0F, -32.0F, 8.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(142, 279).addBox(-10.5F, -17.0F, -28.0F, 8.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(306, 326).addBox(-3.5F, -16.0F, -28.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(156, 261).addBox(-10.5F, -16.0F, -28.0F, 1.0F, 7.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(330, 141).addBox(-2.5F, -16.0F, -26.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(142, 281).addBox(-9.5F, -17.0F, -26.0F, 8.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(156, 269).addBox(-9.5F, -16.0F, -26.0F, 1.0F, 7.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(326, 45).addBox(10.5F, -17.0F, -40.0F, 1.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(258, 150).addBox(3.5F, -18.0F, -42.0F, 8.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(334, 236).addBox(10.5F, -18.0F, -41.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 337).addBox(-7.5F, -17.0F, -40.0F, 2.0F, 2.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(228, 72).addBox(-8.5F, -16.0F, -38.0F, 11.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(276, 248).addBox(-4.6F, -17.0F, -32.9F, 6.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(326, 289).addBox(-0.6F, -15.0F, -32.9F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(338, 17).addBox(4.5F, -17.0F, -40.0F, 2.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(-7.9066F, 14.9446F, 2.1904F));

		PartDefinition engine_r1 = Engine.addOrReplaceChild("engine_r1", CubeListBuilder.create().texOffs(14, 338).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.5F, -17.0F, -35.0F, -0.7854F, 0.0F, 0.0F));

		PartDefinition engine_r2 = Engine.addOrReplaceChild("engine_r2", CubeListBuilder.create().texOffs(338, 10).addBox(0.0F, -1.0F, 0.0F, 2.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.5F, -16.0F, -35.0F, 0.0F, 0.6109F, 0.0F));

		PartDefinition steering_wheel = body.addOrReplaceChild("steering_wheel", CubeListBuilder.create().texOffs(192, 309).addBox(-2.1364F, 2.6818F, -0.4376F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(310, 269).addBox(-3.1364F, 1.6818F, -0.4376F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(78, 319).addBox(1.8636F, 1.6818F, -0.4376F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(326, 60).addBox(2.8636F, -2.3182F, -0.4376F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(170, 300).addBox(-3.1364F, -3.3182F, -0.4376F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(94, 328).addBox(-4.1364F, -2.3182F, -0.4376F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(264, 314).addBox(-1.1364F, 0.6818F, 0.2624F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(0.8636F, 0.6818F, 0.5624F, 0.0F, 2.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(232, 317).addBox(0.8636F, -1.3182F, 0.2624F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(302, 326).addBox(-3.1364F, -1.3182F, 0.2624F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(2.8636F, -0.3182F, 0.5624F, 0.0F, 0.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(0.8636F, -0.3182F, 0.5624F, 1.0F, 0.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(1.8636F, -0.3182F, 0.5624F, 0.0F, 0.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-2.1364F, -0.3182F, 0.5624F, 0.0F, 0.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-3.1364F, -0.3182F, 0.5624F, 1.0F, 0.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-1.1364F, -0.3182F, 0.5624F, 0.0F, 0.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(222, 327).addBox(-1.1364F, -1.3182F, -0.4376F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(170, 320).addBox(-1.1364F, -1.0682F, -8.4376F, 2.0F, 2.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.7297F, -1.6611F, -8.9893F, 0.3927F, 0.0F, 0.0F));

		PartDefinition Front_Left_Wheel = body.addOrReplaceChild("Front_Left_Wheel", CubeListBuilder.create().texOffs(268, 294).addBox(0.0F, -5.7F, -5.61F, 0.0F, 11.0F, 11.0F, new CubeDeformation(0.0F))
		.texOffs(294, 271).addBox(-2.75F, -5.7F, -5.61F, 0.0F, 11.0F, 11.0F, new CubeDeformation(0.0F))
		.texOffs(84, 156).addBox(-0.75F, -1.95F, -1.86F, 2.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(94, 339).addBox(-4.0F, -3.7F, -1.61F, 1.0F, 7.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(76, 340).addBox(-4.0F, -1.7F, -3.61F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(212, 339).addBox(-4.0F, -2.7F, -2.61F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(340, 236).addBox(-4.0F, -1.7F, 2.39F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(64, 340).addBox(-4.0F, -2.7F, 1.39F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(11.5934F, 5.6446F, -28.1996F));

		PartDefinition cube_r35 = Front_Left_Wheel.addOrReplaceChild("cube_r35", CubeListBuilder.create().texOffs(340, 268).addBox(-0.5F, 0.0F, -1.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.5F, 1.3F, 3.39F, -0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r36 = Front_Left_Wheel.addOrReplaceChild("cube_r36", CubeListBuilder.create().texOffs(326, 32).addBox(-0.5F, -1.05F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.5F, 3.35F, 1.39F, 0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r37 = Front_Left_Wheel.addOrReplaceChild("cube_r37", CubeListBuilder.create().texOffs(268, 340).addBox(-0.5F, -2.0F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.5F, -1.7F, -3.61F, -0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r38 = Front_Left_Wheel.addOrReplaceChild("cube_r38", CubeListBuilder.create().texOffs(338, 153).addBox(-0.5F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.5F, 1.3F, -3.61F, 0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r39 = Front_Left_Wheel.addOrReplaceChild("cube_r39", CubeListBuilder.create().texOffs(322, 32).addBox(-0.5F, -1.05F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.5F, 3.35F, -1.61F, -0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r40 = Front_Left_Wheel.addOrReplaceChild("cube_r40", CubeListBuilder.create().texOffs(86, 319).addBox(-0.5F, 0.05F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.5F, -3.75F, -1.61F, 0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r41 = Front_Left_Wheel.addOrReplaceChild("cube_r41", CubeListBuilder.create().texOffs(82, 319).addBox(-0.5F, 0.05F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.5F, -3.75F, 1.39F, -0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r42 = Front_Left_Wheel.addOrReplaceChild("cube_r42", CubeListBuilder.create().texOffs(336, 325).addBox(-0.5F, -2.0F, -1.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.5F, -1.7F, 3.39F, 0.7854F, 0.0F, 0.0F));

		PartDefinition rim_r1 = Front_Left_Wheel.addOrReplaceChild("rim_r1", CubeListBuilder.create().texOffs(126, 333).addBox(-1.0F, -5.1161F, -0.8839F, 2.0F, 10.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.1142F, 0.14F, -0.7854F, 0.0F, 0.0F));

		PartDefinition rim_r2 = Front_Left_Wheel.addOrReplaceChild("rim_r2", CubeListBuilder.create().texOffs(196, 139).addBox(-1.0F, -5.1161F, -1.1161F, 2.0F, 10.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.1142F, 0.14F, 0.7854F, 0.0F, 0.0F));

		PartDefinition right_wheel_r1 = Front_Left_Wheel.addOrReplaceChild("right_wheel_r1", CubeListBuilder.create().texOffs(330, 129).addBox(-0.625F, -1.4379F, -7.5029F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.375F, 0.0F, -0.045F, 0.7854F, 0.0F, 0.0F));

		PartDefinition right_wheel_r2 = Front_Left_Wheel.addOrReplaceChild("right_wheel_r2", CubeListBuilder.create().texOffs(318, 328).addBox(-0.625F, -1.4282F, -7.5453F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.375F, 0.0F, -0.045F, -2.7576F, 0.0F, 0.0F));

		PartDefinition right_wheel_r3 = Front_Left_Wheel.addOrReplaceChild("right_wheel_r3", CubeListBuilder.create().texOffs(302, 328).addBox(-0.625F, -1.5F, -7.555F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.375F, 0.0F, -0.045F, 3.1416F, 0.0F, 0.0F));

		PartDefinition right_wheel_r4 = Front_Left_Wheel.addOrReplaceChild("right_wheel_r4", CubeListBuilder.create().texOffs(328, 181).addBox(-0.625F, -1.5015F, -7.5665F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.375F, 0.0F, -0.045F, -2.3562F, 0.0F, 0.0F));

		PartDefinition right_wheel_r5 = Front_Left_Wheel.addOrReplaceChild("right_wheel_r5", CubeListBuilder.create().texOffs(328, 175).addBox(-0.625F, -1.5642F, -7.5621F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.375F, 0.0F, -0.045F, -1.9635F, 0.0F, 0.0F));

		PartDefinition right_wheel_r6 = Front_Left_Wheel.addOrReplaceChild("right_wheel_r6", CubeListBuilder.create().texOffs(328, 169).addBox(-0.625F, -1.545F, -7.51F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.375F, 0.0F, -0.045F, -1.5708F, 0.0F, 0.0F));

		PartDefinition right_wheel_r7 = Front_Left_Wheel.addOrReplaceChild("right_wheel_r7", CubeListBuilder.create().texOffs(328, 163).addBox(-0.625F, -1.4531F, -7.4867F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.375F, 0.0F, -0.045F, -1.1868F, 0.0F, 0.0F));

		PartDefinition right_wheel_r8 = Front_Left_Wheel.addOrReplaceChild("right_wheel_r8", CubeListBuilder.create().texOffs(328, 123).addBox(-0.625F, -1.5015F, -7.5029F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.375F, 0.0F, -0.045F, -0.7854F, 0.0F, 0.0F));

		PartDefinition right_wheel_r9 = Front_Left_Wheel.addOrReplaceChild("right_wheel_r9", CubeListBuilder.create().texOffs(328, 117).addBox(-0.625F, -1.5398F, -7.5033F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.375F, 0.0F, -0.045F, -0.3927F, 0.0F, 0.0F));

		PartDefinition right_wheel_r10 = Front_Left_Wheel.addOrReplaceChild("right_wheel_r10", CubeListBuilder.create().texOffs(78, 328).addBox(-0.625F, -1.4724F, -7.5549F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.375F, 0.0F, -0.045F, -0.0087F, 0.0F, 0.0F));

		PartDefinition right_wheel_r11 = Front_Left_Wheel.addOrReplaceChild("right_wheel_r11", CubeListBuilder.create().texOffs(206, 327).addBox(-0.625F, -1.455F, -7.51F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.375F, 0.0F, -0.045F, 1.5708F, 0.0F, 0.0F));

		PartDefinition right_wheel_r12 = Front_Left_Wheel.addOrReplaceChild("right_wheel_r12", CubeListBuilder.create().texOffs(190, 327).addBox(-0.625F, -1.3697F, -7.5204F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.375F, 0.0F, -0.045F, 1.9548F, 0.0F, 0.0F));

		PartDefinition right_wheel_r13 = Front_Left_Wheel.addOrReplaceChild("right_wheel_r13", CubeListBuilder.create().texOffs(118, 327).addBox(-0.625F, -1.4379F, -7.5665F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.375F, 0.0F, -0.045F, 2.3562F, 0.0F, 0.0F));

		PartDefinition right_wheel_r14 = Front_Left_Wheel.addOrReplaceChild("right_wheel_r14", CubeListBuilder.create().texOffs(102, 327).addBox(-0.625F, -1.5054F, -7.5864F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.375F, 0.0F, -0.045F, 2.7489F, 0.0F, 0.0F));

		PartDefinition right_wheel_r15 = Front_Left_Wheel.addOrReplaceChild("right_wheel_r15", CubeListBuilder.create().texOffs(286, 326).addBox(-0.625F, -1.4811F, -7.5277F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.375F, 0.0F, -0.045F, 1.1781F, 0.0F, 0.0F));

		PartDefinition right_wheel_r16 = Front_Left_Wheel.addOrReplaceChild("right_wheel_r16", CubeListBuilder.create().texOffs(278, 271).addBox(-0.625F, -1.3945F, -7.4619F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.375F, 0.0F, -0.045F, 0.384F, 0.0F, 0.0F));

		PartDefinition Front_Right_Wheel = body.addOrReplaceChild("Front_Right_Wheel", CubeListBuilder.create().texOffs(268, 294).mirror().addBox(0.0F, -5.7F, -5.61F, 0.0F, 11.0F, 11.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(294, 271).mirror().addBox(2.75F, -5.7F, -5.61F, 0.0F, 11.0F, 11.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(84, 156).mirror().addBox(-1.25F, -1.95F, -1.86F, 2.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(94, 339).mirror().addBox(3.0F, -3.7F, -1.61F, 1.0F, 7.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(76, 340).mirror().addBox(3.0F, -1.7F, -3.61F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(212, 339).mirror().addBox(3.0F, -2.7F, -2.61F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(340, 236).mirror().addBox(3.0F, -1.7F, 2.39F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(64, 340).mirror().addBox(3.0F, -2.7F, 1.39F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-27.4066F, 5.6446F, -28.1996F));

		PartDefinition cube_r43 = Front_Right_Wheel.addOrReplaceChild("cube_r43", CubeListBuilder.create().texOffs(340, 268).mirror().addBox(-0.5F, 0.0F, -1.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.5F, 1.3F, 3.39F, -0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r44 = Front_Right_Wheel.addOrReplaceChild("cube_r44", CubeListBuilder.create().texOffs(326, 32).mirror().addBox(-0.5F, -1.05F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.5F, 3.35F, 1.39F, 0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r45 = Front_Right_Wheel.addOrReplaceChild("cube_r45", CubeListBuilder.create().texOffs(268, 340).mirror().addBox(-0.5F, -2.0F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.5F, -1.7F, -3.61F, -0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r46 = Front_Right_Wheel.addOrReplaceChild("cube_r46", CubeListBuilder.create().texOffs(338, 153).mirror().addBox(-0.5F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.5F, 1.3F, -3.61F, 0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r47 = Front_Right_Wheel.addOrReplaceChild("cube_r47", CubeListBuilder.create().texOffs(322, 32).mirror().addBox(-0.5F, -1.05F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.5F, 3.35F, -1.61F, -0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r48 = Front_Right_Wheel.addOrReplaceChild("cube_r48", CubeListBuilder.create().texOffs(86, 319).mirror().addBox(-0.5F, 0.05F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.5F, -3.75F, -1.61F, 0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r49 = Front_Right_Wheel.addOrReplaceChild("cube_r49", CubeListBuilder.create().texOffs(82, 319).mirror().addBox(-0.5F, 0.05F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.5F, -3.75F, 1.39F, -0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r50 = Front_Right_Wheel.addOrReplaceChild("cube_r50", CubeListBuilder.create().texOffs(336, 325).mirror().addBox(-0.5F, -2.0F, -1.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.5F, -1.7F, 3.39F, 0.7854F, 0.0F, 0.0F));

		PartDefinition rim_r3 = Front_Right_Wheel.addOrReplaceChild("rim_r3", CubeListBuilder.create().texOffs(126, 333).mirror().addBox(-1.0F, -5.1161F, -0.8839F, 2.0F, 10.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, -0.1142F, 0.14F, -0.7854F, 0.0F, 0.0F));

		PartDefinition rim_r4 = Front_Right_Wheel.addOrReplaceChild("rim_r4", CubeListBuilder.create().texOffs(196, 139).mirror().addBox(-1.0F, -5.1161F, -1.1161F, 2.0F, 10.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, -0.1142F, 0.14F, 0.7854F, 0.0F, 0.0F));

		PartDefinition left_wheel_r1 = Front_Right_Wheel.addOrReplaceChild("left_wheel_r1", CubeListBuilder.create().texOffs(330, 129).mirror().addBox(-4.375F, -1.4379F, -7.5029F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.375F, 0.0F, -0.045F, 0.7854F, 0.0F, 0.0F));

		PartDefinition left_wheel_r2 = Front_Right_Wheel.addOrReplaceChild("left_wheel_r2", CubeListBuilder.create().texOffs(318, 328).mirror().addBox(-4.375F, -1.4282F, -7.5453F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.375F, 0.0F, -0.045F, -2.7576F, 0.0F, 0.0F));

		PartDefinition left_wheel_r3 = Front_Right_Wheel.addOrReplaceChild("left_wheel_r3", CubeListBuilder.create().texOffs(302, 328).mirror().addBox(-4.375F, -1.5F, -7.555F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.375F, 0.0F, -0.045F, 3.1416F, 0.0F, 0.0F));

		PartDefinition left_wheel_r4 = Front_Right_Wheel.addOrReplaceChild("left_wheel_r4", CubeListBuilder.create().texOffs(328, 181).mirror().addBox(-4.375F, -1.5015F, -7.5665F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.375F, 0.0F, -0.045F, -2.3562F, 0.0F, 0.0F));

		PartDefinition left_wheel_r5 = Front_Right_Wheel.addOrReplaceChild("left_wheel_r5", CubeListBuilder.create().texOffs(328, 175).mirror().addBox(-4.375F, -1.5642F, -7.5621F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.375F, 0.0F, -0.045F, -1.9635F, 0.0F, 0.0F));

		PartDefinition left_wheel_r6 = Front_Right_Wheel.addOrReplaceChild("left_wheel_r6", CubeListBuilder.create().texOffs(328, 169).mirror().addBox(-4.375F, -1.545F, -7.51F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.375F, 0.0F, -0.045F, -1.5708F, 0.0F, 0.0F));

		PartDefinition left_wheel_r7 = Front_Right_Wheel.addOrReplaceChild("left_wheel_r7", CubeListBuilder.create().texOffs(328, 163).mirror().addBox(-4.375F, -1.4531F, -7.4867F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.375F, 0.0F, -0.045F, -1.1868F, 0.0F, 0.0F));

		PartDefinition left_wheel_r8 = Front_Right_Wheel.addOrReplaceChild("left_wheel_r8", CubeListBuilder.create().texOffs(328, 123).mirror().addBox(-4.375F, -1.5015F, -7.5029F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.375F, 0.0F, -0.045F, -0.7854F, 0.0F, 0.0F));

		PartDefinition left_wheel_r9 = Front_Right_Wheel.addOrReplaceChild("left_wheel_r9", CubeListBuilder.create().texOffs(328, 117).mirror().addBox(-4.375F, -1.5398F, -7.5033F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.375F, 0.0F, -0.045F, -0.3927F, 0.0F, 0.0F));

		PartDefinition left_wheel_r10 = Front_Right_Wheel.addOrReplaceChild("left_wheel_r10", CubeListBuilder.create().texOffs(78, 328).mirror().addBox(-4.375F, -1.4724F, -7.5549F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.375F, 0.0F, -0.045F, -0.0087F, 0.0F, 0.0F));

		PartDefinition left_wheel_r11 = Front_Right_Wheel.addOrReplaceChild("left_wheel_r11", CubeListBuilder.create().texOffs(206, 327).mirror().addBox(-4.375F, -1.455F, -7.51F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.375F, 0.0F, -0.045F, 1.5708F, 0.0F, 0.0F));

		PartDefinition left_wheel_r12 = Front_Right_Wheel.addOrReplaceChild("left_wheel_r12", CubeListBuilder.create().texOffs(190, 327).mirror().addBox(-4.375F, -1.3697F, -7.5204F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.375F, 0.0F, -0.045F, 1.9548F, 0.0F, 0.0F));

		PartDefinition left_wheel_r13 = Front_Right_Wheel.addOrReplaceChild("left_wheel_r13", CubeListBuilder.create().texOffs(118, 327).mirror().addBox(-4.375F, -1.4379F, -7.5665F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.375F, 0.0F, -0.045F, 2.3562F, 0.0F, 0.0F));

		PartDefinition left_wheel_r14 = Front_Right_Wheel.addOrReplaceChild("left_wheel_r14", CubeListBuilder.create().texOffs(102, 327).mirror().addBox(-4.375F, -1.5054F, -7.5864F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.375F, 0.0F, -0.045F, 2.7489F, 0.0F, 0.0F));

		PartDefinition left_wheel_r15 = Front_Right_Wheel.addOrReplaceChild("left_wheel_r15", CubeListBuilder.create().texOffs(286, 326).mirror().addBox(-4.375F, -1.4811F, -7.5277F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.375F, 0.0F, -0.045F, 1.1781F, 0.0F, 0.0F));

		PartDefinition left_wheel_r16 = Front_Right_Wheel.addOrReplaceChild("left_wheel_r16", CubeListBuilder.create().texOffs(278, 271).mirror().addBox(-4.375F, -1.3945F, -7.4619F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.375F, 0.0F, -0.045F, 0.384F, 0.0F, 0.0F));

		PartDefinition Back_Left_Wheel = body.addOrReplaceChild("Back_Left_Wheel", CubeListBuilder.create().texOffs(268, 294).addBox(0.0F, -5.7F, -5.61F, 0.0F, 11.0F, 11.0F, new CubeDeformation(0.0F))
		.texOffs(294, 271).addBox(-2.75F, -5.7F, -5.61F, 0.0F, 11.0F, 11.0F, new CubeDeformation(0.0F))
		.texOffs(84, 156).addBox(-0.75F, -1.95F, -1.86F, 2.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(94, 339).addBox(-4.0F, -3.7F, -1.61F, 1.0F, 7.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(76, 340).addBox(-4.0F, -1.7F, -3.61F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(212, 339).addBox(-4.0F, -2.7F, -2.61F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(340, 236).addBox(-4.0F, -1.7F, 2.39F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(64, 340).addBox(-4.0F, -2.7F, 1.39F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(11.5934F, 5.6446F, 35.8004F));

		PartDefinition cube_r51 = Back_Left_Wheel.addOrReplaceChild("cube_r51", CubeListBuilder.create().texOffs(340, 268).addBox(-0.5F, 0.0F, -1.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.5F, 1.3F, 3.39F, -0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r52 = Back_Left_Wheel.addOrReplaceChild("cube_r52", CubeListBuilder.create().texOffs(326, 32).addBox(-0.5F, -1.05F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.5F, 3.35F, 1.39F, 0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r53 = Back_Left_Wheel.addOrReplaceChild("cube_r53", CubeListBuilder.create().texOffs(268, 340).addBox(-0.5F, -2.0F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.5F, -1.7F, -3.61F, -0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r54 = Back_Left_Wheel.addOrReplaceChild("cube_r54", CubeListBuilder.create().texOffs(338, 153).addBox(-0.5F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.5F, 1.3F, -3.61F, 0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r55 = Back_Left_Wheel.addOrReplaceChild("cube_r55", CubeListBuilder.create().texOffs(322, 32).addBox(-0.5F, -1.05F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.5F, 3.35F, -1.61F, -0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r56 = Back_Left_Wheel.addOrReplaceChild("cube_r56", CubeListBuilder.create().texOffs(86, 319).addBox(-0.5F, 0.05F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.5F, -3.75F, -1.61F, 0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r57 = Back_Left_Wheel.addOrReplaceChild("cube_r57", CubeListBuilder.create().texOffs(82, 319).addBox(-0.5F, 0.05F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.5F, -3.75F, 1.39F, -0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r58 = Back_Left_Wheel.addOrReplaceChild("cube_r58", CubeListBuilder.create().texOffs(336, 325).addBox(-0.5F, -2.0F, -1.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.5F, -1.7F, 3.39F, 0.7854F, 0.0F, 0.0F));

		PartDefinition rim_r5 = Back_Left_Wheel.addOrReplaceChild("rim_r5", CubeListBuilder.create().texOffs(126, 333).addBox(-1.0F, -5.1161F, -0.8839F, 2.0F, 10.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.1142F, 0.14F, -0.7854F, 0.0F, 0.0F));

		PartDefinition rim_r6 = Back_Left_Wheel.addOrReplaceChild("rim_r6", CubeListBuilder.create().texOffs(196, 139).addBox(-1.0F, -5.1161F, -1.1161F, 2.0F, 10.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.1142F, 0.14F, 0.7854F, 0.0F, 0.0F));

		PartDefinition right_wheel_r17 = Back_Left_Wheel.addOrReplaceChild("right_wheel_r17", CubeListBuilder.create().texOffs(330, 129).addBox(-0.625F, -1.4379F, -7.5029F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.375F, 0.0F, -0.045F, 0.7854F, 0.0F, 0.0F));

		PartDefinition right_wheel_r18 = Back_Left_Wheel.addOrReplaceChild("right_wheel_r18", CubeListBuilder.create().texOffs(318, 328).addBox(-0.625F, -1.4282F, -7.5453F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.375F, 0.0F, -0.045F, -2.7576F, 0.0F, 0.0F));

		PartDefinition right_wheel_r19 = Back_Left_Wheel.addOrReplaceChild("right_wheel_r19", CubeListBuilder.create().texOffs(302, 328).addBox(-0.625F, -1.5F, -7.555F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.375F, 0.0F, -0.045F, 3.1416F, 0.0F, 0.0F));

		PartDefinition right_wheel_r20 = Back_Left_Wheel.addOrReplaceChild("right_wheel_r20", CubeListBuilder.create().texOffs(328, 181).addBox(-0.625F, -1.5015F, -7.5665F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.375F, 0.0F, -0.045F, -2.3562F, 0.0F, 0.0F));

		PartDefinition right_wheel_r21 = Back_Left_Wheel.addOrReplaceChild("right_wheel_r21", CubeListBuilder.create().texOffs(328, 175).addBox(-0.625F, -1.5642F, -7.5621F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.375F, 0.0F, -0.045F, -1.9635F, 0.0F, 0.0F));

		PartDefinition right_wheel_r22 = Back_Left_Wheel.addOrReplaceChild("right_wheel_r22", CubeListBuilder.create().texOffs(328, 169).addBox(-0.625F, -1.545F, -7.51F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.375F, 0.0F, -0.045F, -1.5708F, 0.0F, 0.0F));

		PartDefinition right_wheel_r23 = Back_Left_Wheel.addOrReplaceChild("right_wheel_r23", CubeListBuilder.create().texOffs(328, 163).addBox(-0.625F, -1.4531F, -7.4867F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.375F, 0.0F, -0.045F, -1.1868F, 0.0F, 0.0F));

		PartDefinition right_wheel_r24 = Back_Left_Wheel.addOrReplaceChild("right_wheel_r24", CubeListBuilder.create().texOffs(328, 123).addBox(-0.625F, -1.5015F, -7.5029F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.375F, 0.0F, -0.045F, -0.7854F, 0.0F, 0.0F));

		PartDefinition right_wheel_r25 = Back_Left_Wheel.addOrReplaceChild("right_wheel_r25", CubeListBuilder.create().texOffs(328, 117).addBox(-0.625F, -1.5398F, -7.5033F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.375F, 0.0F, -0.045F, -0.3927F, 0.0F, 0.0F));

		PartDefinition right_wheel_r26 = Back_Left_Wheel.addOrReplaceChild("right_wheel_r26", CubeListBuilder.create().texOffs(78, 328).addBox(-0.625F, -1.4724F, -7.5549F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.375F, 0.0F, -0.045F, -0.0087F, 0.0F, 0.0F));

		PartDefinition right_wheel_r27 = Back_Left_Wheel.addOrReplaceChild("right_wheel_r27", CubeListBuilder.create().texOffs(206, 327).addBox(-0.625F, -1.455F, -7.51F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.375F, 0.0F, -0.045F, 1.5708F, 0.0F, 0.0F));

		PartDefinition right_wheel_r28 = Back_Left_Wheel.addOrReplaceChild("right_wheel_r28", CubeListBuilder.create().texOffs(190, 327).addBox(-0.625F, -1.3697F, -7.5204F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.375F, 0.0F, -0.045F, 1.9548F, 0.0F, 0.0F));

		PartDefinition right_wheel_r29 = Back_Left_Wheel.addOrReplaceChild("right_wheel_r29", CubeListBuilder.create().texOffs(118, 327).addBox(-0.625F, -1.4379F, -7.5665F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.375F, 0.0F, -0.045F, 2.3562F, 0.0F, 0.0F));

		PartDefinition right_wheel_r30 = Back_Left_Wheel.addOrReplaceChild("right_wheel_r30", CubeListBuilder.create().texOffs(102, 327).addBox(-0.625F, -1.5054F, -7.5864F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.375F, 0.0F, -0.045F, 2.7489F, 0.0F, 0.0F));

		PartDefinition right_wheel_r31 = Back_Left_Wheel.addOrReplaceChild("right_wheel_r31", CubeListBuilder.create().texOffs(286, 326).addBox(-0.625F, -1.4811F, -7.5277F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.375F, 0.0F, -0.045F, 1.1781F, 0.0F, 0.0F));

		PartDefinition right_wheel_r32 = Back_Left_Wheel.addOrReplaceChild("right_wheel_r32", CubeListBuilder.create().texOffs(278, 271).addBox(-0.625F, -1.3945F, -7.4619F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.375F, 0.0F, -0.045F, 0.384F, 0.0F, 0.0F));

		PartDefinition Back_Right_Wheel = body.addOrReplaceChild("Back_Right_Wheel", CubeListBuilder.create().texOffs(268, 294).mirror().addBox(0.0F, -5.7F, -5.61F, 0.0F, 11.0F, 11.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(294, 271).mirror().addBox(2.75F, -5.7F, -5.61F, 0.0F, 11.0F, 11.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(84, 156).mirror().addBox(-1.25F, -1.95F, -1.86F, 2.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(94, 339).mirror().addBox(3.0F, -3.7F, -1.61F, 1.0F, 7.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(76, 340).mirror().addBox(3.0F, -1.7F, -3.61F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(212, 339).mirror().addBox(3.0F, -2.7F, -2.61F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(340, 236).mirror().addBox(3.0F, -1.7F, 2.39F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(64, 340).mirror().addBox(3.0F, -2.7F, 1.39F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-27.4066F, 5.6446F, 35.8004F));

		PartDefinition cube_r59 = Back_Right_Wheel.addOrReplaceChild("cube_r59", CubeListBuilder.create().texOffs(340, 268).mirror().addBox(-0.5F, 0.0F, -1.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.5F, 1.3F, 3.39F, -0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r60 = Back_Right_Wheel.addOrReplaceChild("cube_r60", CubeListBuilder.create().texOffs(326, 32).mirror().addBox(-0.5F, -1.05F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.5F, 3.35F, 1.39F, 0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r61 = Back_Right_Wheel.addOrReplaceChild("cube_r61", CubeListBuilder.create().texOffs(268, 340).mirror().addBox(-0.5F, -2.0F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.5F, -1.7F, -3.61F, -0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r62 = Back_Right_Wheel.addOrReplaceChild("cube_r62", CubeListBuilder.create().texOffs(338, 153).mirror().addBox(-0.5F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.5F, 1.3F, -3.61F, 0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r63 = Back_Right_Wheel.addOrReplaceChild("cube_r63", CubeListBuilder.create().texOffs(322, 32).mirror().addBox(-0.5F, -1.05F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.5F, 3.35F, -1.61F, -0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r64 = Back_Right_Wheel.addOrReplaceChild("cube_r64", CubeListBuilder.create().texOffs(86, 319).mirror().addBox(-0.5F, 0.05F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.5F, -3.75F, -1.61F, 0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r65 = Back_Right_Wheel.addOrReplaceChild("cube_r65", CubeListBuilder.create().texOffs(82, 319).mirror().addBox(-0.5F, 0.05F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.5F, -3.75F, 1.39F, -0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r66 = Back_Right_Wheel.addOrReplaceChild("cube_r66", CubeListBuilder.create().texOffs(336, 325).mirror().addBox(-0.5F, -2.0F, -1.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.5F, -1.7F, 3.39F, 0.7854F, 0.0F, 0.0F));

		PartDefinition rim_r7 = Back_Right_Wheel.addOrReplaceChild("rim_r7", CubeListBuilder.create().texOffs(126, 333).mirror().addBox(-1.0F, -5.1161F, -0.8839F, 2.0F, 10.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, -0.1142F, 0.14F, -0.7854F, 0.0F, 0.0F));

		PartDefinition rim_r8 = Back_Right_Wheel.addOrReplaceChild("rim_r8", CubeListBuilder.create().texOffs(196, 139).mirror().addBox(-1.0F, -5.1161F, -1.1161F, 2.0F, 10.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, -0.1142F, 0.14F, 0.7854F, 0.0F, 0.0F));

		PartDefinition left_wheel_r17 = Back_Right_Wheel.addOrReplaceChild("left_wheel_r17", CubeListBuilder.create().texOffs(330, 129).mirror().addBox(-4.375F, -1.4379F, -7.5029F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.375F, 0.0F, -0.045F, 0.7854F, 0.0F, 0.0F));

		PartDefinition left_wheel_r18 = Back_Right_Wheel.addOrReplaceChild("left_wheel_r18", CubeListBuilder.create().texOffs(318, 328).mirror().addBox(-4.375F, -1.4282F, -7.5453F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.375F, 0.0F, -0.045F, -2.7576F, 0.0F, 0.0F));

		PartDefinition left_wheel_r19 = Back_Right_Wheel.addOrReplaceChild("left_wheel_r19", CubeListBuilder.create().texOffs(302, 328).mirror().addBox(-4.375F, -1.5F, -7.555F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.375F, 0.0F, -0.045F, 3.1416F, 0.0F, 0.0F));

		PartDefinition left_wheel_r20 = Back_Right_Wheel.addOrReplaceChild("left_wheel_r20", CubeListBuilder.create().texOffs(328, 181).mirror().addBox(-4.375F, -1.5015F, -7.5665F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.375F, 0.0F, -0.045F, -2.3562F, 0.0F, 0.0F));

		PartDefinition left_wheel_r21 = Back_Right_Wheel.addOrReplaceChild("left_wheel_r21", CubeListBuilder.create().texOffs(328, 175).mirror().addBox(-4.375F, -1.5642F, -7.5621F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.375F, 0.0F, -0.045F, -1.9635F, 0.0F, 0.0F));

		PartDefinition left_wheel_r22 = Back_Right_Wheel.addOrReplaceChild("left_wheel_r22", CubeListBuilder.create().texOffs(328, 169).mirror().addBox(-4.375F, -1.545F, -7.51F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.375F, 0.0F, -0.045F, -1.5708F, 0.0F, 0.0F));

		PartDefinition left_wheel_r23 = Back_Right_Wheel.addOrReplaceChild("left_wheel_r23", CubeListBuilder.create().texOffs(328, 163).mirror().addBox(-4.375F, -1.4531F, -7.4867F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.375F, 0.0F, -0.045F, -1.1868F, 0.0F, 0.0F));

		PartDefinition left_wheel_r24 = Back_Right_Wheel.addOrReplaceChild("left_wheel_r24", CubeListBuilder.create().texOffs(328, 123).mirror().addBox(-4.375F, -1.5015F, -7.5029F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.375F, 0.0F, -0.045F, -0.7854F, 0.0F, 0.0F));

		PartDefinition left_wheel_r25 = Back_Right_Wheel.addOrReplaceChild("left_wheel_r25", CubeListBuilder.create().texOffs(328, 117).mirror().addBox(-4.375F, -1.5398F, -7.5033F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.375F, 0.0F, -0.045F, -0.3927F, 0.0F, 0.0F));

		PartDefinition left_wheel_r26 = Back_Right_Wheel.addOrReplaceChild("left_wheel_r26", CubeListBuilder.create().texOffs(78, 328).mirror().addBox(-4.375F, -1.4724F, -7.5549F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.375F, 0.0F, -0.045F, -0.0087F, 0.0F, 0.0F));

		PartDefinition left_wheel_r27 = Back_Right_Wheel.addOrReplaceChild("left_wheel_r27", CubeListBuilder.create().texOffs(206, 327).mirror().addBox(-4.375F, -1.455F, -7.51F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.375F, 0.0F, -0.045F, 1.5708F, 0.0F, 0.0F));

		PartDefinition left_wheel_r28 = Back_Right_Wheel.addOrReplaceChild("left_wheel_r28", CubeListBuilder.create().texOffs(190, 327).mirror().addBox(-4.375F, -1.3697F, -7.5204F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.375F, 0.0F, -0.045F, 1.9548F, 0.0F, 0.0F));

		PartDefinition left_wheel_r29 = Back_Right_Wheel.addOrReplaceChild("left_wheel_r29", CubeListBuilder.create().texOffs(118, 327).mirror().addBox(-4.375F, -1.4379F, -7.5665F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.375F, 0.0F, -0.045F, 2.3562F, 0.0F, 0.0F));

		PartDefinition left_wheel_r30 = Back_Right_Wheel.addOrReplaceChild("left_wheel_r30", CubeListBuilder.create().texOffs(102, 327).mirror().addBox(-4.375F, -1.5054F, -7.5864F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.375F, 0.0F, -0.045F, 2.7489F, 0.0F, 0.0F));

		PartDefinition left_wheel_r31 = Back_Right_Wheel.addOrReplaceChild("left_wheel_r31", CubeListBuilder.create().texOffs(286, 326).mirror().addBox(-4.375F, -1.4811F, -7.5277F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.375F, 0.0F, -0.045F, 1.1781F, 0.0F, 0.0F));

		PartDefinition left_wheel_r32 = Back_Right_Wheel.addOrReplaceChild("left_wheel_r32", CubeListBuilder.create().texOffs(278, 271).mirror().addBox(-4.375F, -1.3945F, -7.4619F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.375F, 0.0F, -0.045F, 0.384F, 0.0F, 0.0F));

		PartDefinition shifter = body.addOrReplaceChild("shifter", CubeListBuilder.create().texOffs(98, 328).addBox(-0.75F, -0.75F, -0.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(196, 151).addBox(-0.75F, -1.75F, -0.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.6566F, 0.6946F, -3.3096F));

		PartDefinition right_door = body.addOrReplaceChild("right_door", CubeListBuilder.create().texOffs(218, 224).addBox(0.4422F, 8.3198F, -2.0F, 2.0F, 1.0F, 27.0F, new CubeDeformation(0.0F))
		.texOffs(84, 184).mirror().addBox(0.1422F, -4.5802F, -2.0F, 2.0F, 7.0F, 27.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(56, 218).addBox(1.1422F, -13.5802F, -1.0F, 0.0F, 11.0F, 26.0F, new CubeDeformation(0.0F)), PartPose.offset(-27.5488F, -0.4752F, -12.8096F));

		PartDefinition left_door_r5 = right_door.addOrReplaceChild("left_door_r5", CubeListBuilder.create().texOffs(0, 234).mirror().addBox(-1.4386F, -1.9287F, -12.0F, 2.0F, 5.0F, 25.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.75F, -2.25F, 12.0F, 0.0F, 0.0F, 0.3927F));

		PartDefinition left_door_r6 = right_door.addOrReplaceChild("left_door_r6", CubeListBuilder.create().texOffs(142, 190).mirror().addBox(-3.1548F, 1.1252F, -12.0F, 3.0F, 9.0F, 25.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.75F, -2.25F, 12.0F, 0.0F, 0.0F, -0.3927F));

		PartDefinition left_door = body.addOrReplaceChild("left_door", CubeListBuilder.create().texOffs(160, 224).addBox(-2.4422F, 8.3198F, -2.0F, 2.0F, 1.0F, 27.0F, new CubeDeformation(0.0F))
		.texOffs(108, 224).addBox(-1.1422F, -13.5802F, -1.0F, 0.0F, 11.0F, 26.0F, new CubeDeformation(0.0F))
		.texOffs(84, 184).addBox(-2.1422F, -4.5802F, -2.0F, 2.0F, 7.0F, 27.0F, new CubeDeformation(0.0F)), PartPose.offset(11.7356F, -0.4752F, -12.8096F));

		PartDefinition right_door_r3 = left_door.addOrReplaceChild("right_door_r3", CubeListBuilder.create().texOffs(0, 234).addBox(-0.5614F, -1.9287F, -12.0F, 2.0F, 5.0F, 25.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.75F, -2.25F, 12.0F, 0.0F, 0.0F, -0.3927F));

		PartDefinition right_door_r4 = left_door.addOrReplaceChild("right_door_r4", CubeListBuilder.create().texOffs(142, 190).addBox(0.1548F, 1.1252F, -12.0F, 3.0F, 9.0F, 25.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.75F, -2.25F, 12.0F, 0.0F, 0.0F, 0.3927F));

		PartDefinition hood = body.addOrReplaceChild("hood", CubeListBuilder.create().texOffs(0, 156).addBox(9.012F, -1.9058F, -32.0509F, 10.0F, 2.0F, 32.0F, new CubeDeformation(0.0F))
		.texOffs(0, 75).addBox(-9.988F, -1.9058F, -36.0509F, 19.0F, 2.0F, 36.0F, new CubeDeformation(0.0F))
		.texOffs(158, 0).addBox(-19.988F, -1.9058F, -32.0509F, 10.0F, 2.0F, 32.0F, new CubeDeformation(0.0F))
		.texOffs(142, 184).addBox(-6.988F, -3.9058F, -16.3509F, 13.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-7.4186F, -3.1496F, -14.7587F));

		PartDefinition cube_r67 = hood.addOrReplaceChild("cube_r67", CubeListBuilder.create().texOffs(260, 34).addBox(8.012F, 6.0942F, -27.0509F, 13.0F, 4.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-15.0F, 0.85F, 12.0F, -0.3927F, 0.0F, 0.0F));

		PartDefinition cube_r68 = hood.addOrReplaceChild("cube_r68", CubeListBuilder.create().texOffs(108, 218).addBox(-19.988F, -14.0463F, -31.8869F, 10.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(316, 284).addBox(9.012F, -14.0463F, -31.8869F, 10.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.3927F, 0.0F, 0.0F));

		PartDefinition cube_r69 = hood.addOrReplaceChild("cube_r69", CubeListBuilder.create().texOffs(139, 100).addBox(0.5F, -1.0F, -7.5F, 1.0F, 1.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-16.488F, 8.0942F, -20.5509F, 1.7453F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 512, 512);
	}

    @Override protected ModelPart body()             { return this.body; }
	@Override protected ModelPart frontLeftWheel()   { return this.Front_Left_Wheel; }
	@Override protected ModelPart frontRightWheel()  { return this.Front_Right_Wheel; }
	@Override protected ModelPart backLeftWheel()    { return this.Back_Left_Wheel; }
	@Override protected ModelPart backRightWheel()   { return this.Back_Right_Wheel; }
	@Override protected ModelPart steeringWheel()    { return this.steering_wheel; }
	@Override protected ModelPart shifter()          { return this.shifter; }
    @Override protected ModelPart bodykits()         {return null; }
    @Override protected ModelPart leftDoor()         {return leftDoor; }
    @Override protected ModelPart rightDoor()         {return rightDoor; }
    @Override protected ModelPart hood()         {return hood; }

    
}
