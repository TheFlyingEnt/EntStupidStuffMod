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

public class CyberCarModel extends BaseCarEntityModel<BaseCarRenderState>  {

    //private static final float MAX_WHEEL_STEER_RAD = 0.4f;

    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
        ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "cybercar"), "main"
    );

	private final ModelPart body;
	private final ModelPart steering_wheel;
	private final ModelPart shifter;
	private final ModelPart Front_Left_Wheel;
	private final ModelPart Front_Right_Wheel;
	private final ModelPart Back_Left_Wheel;
	private final ModelPart Back_Right_Wheel;

	public CyberCarModel(ModelPart root) {
        super(root);
		this.body = root.getChild("body");
		this.steering_wheel = this.body.getChild("steering_wheel");
		this.shifter = this.body.getChild("shifter");
		this.Front_Left_Wheel = this.body.getChild("Front_Left_Wheel");
		this.Front_Right_Wheel = this.body.getChild("Front_Right_Wheel");
		this.Back_Left_Wheel = this.body.getChild("Back_Left_Wheel");
		this.Back_Right_Wheel = this.body.getChild("Back_Right_Wheel");
	}


    @SuppressWarnings("unused")
    public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(260, 34).addBox(-9.652F, 4.8195F, -28.6915F, 14.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(176, 9).addBox(-25.652F, 5.3195F, -28.1915F, 45.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(260, 34).addBox(-9.652F, 3.8195F, 39.6085F, 14.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(176, 9).addBox(-25.652F, 4.5695F, 40.3585F, 45.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(224, 114).addBox(-20.652F, -3.9305F, -14.8915F, 35.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 163).addBox(-22.652F, -4.4805F, -29.2415F, 39.0F, 1.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(272, 26).addBox(13.348F, -4.6805F, -29.8915F, 5.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(182, 281).addBox(13.348F, 3.7195F, -37.2415F, 5.0F, 5.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(142, 231).addBox(16.498F, -0.8805F, -18.8915F, 1.0F, 8.0F, 23.0F, new CubeDeformation(0.0F))
		.texOffs(84, 200).addBox(16.498F, -0.8805F, 3.1085F, 1.0F, 7.0F, 28.0F, new CubeDeformation(0.0F))
		.texOffs(56, 235).addBox(16.348F, 6.3195F, -17.8915F, 2.0F, 3.0F, 21.0F, new CubeDeformation(0.0F))
		.texOffs(0, 239).addBox(16.348F, 7.3195F, -17.8915F, 1.0F, 2.0F, 21.0F, new CubeDeformation(0.0F))
		.texOffs(200, 200).addBox(16.348F, 5.3195F, 3.1085F, 2.0F, 3.0F, 27.0F, new CubeDeformation(0.0F))
		.texOffs(230, 77).addBox(14.348F, -14.1305F, 4.4085F, 2.0F, 2.0F, 25.0F, new CubeDeformation(0.0F))
		.texOffs(176, 34).addBox(-20.652F, -14.1305F, 4.4085F, 35.0F, 2.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(136, 283).addBox(12.348F, -13.6805F, 11.1085F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(136, 283).addBox(-20.652F, -13.6805F, 11.1085F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(230, 77).addBox(-22.652F, -14.1305F, 4.4085F, 2.0F, 2.0F, 25.0F, new CubeDeformation(0.0F))
		.texOffs(70, 280).addBox(11.348F, -16.6805F, 8.1085F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(28, 284).addBox(-17.652F, -16.6805F, 8.1085F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(28, 284).addBox(9.348F, -16.6805F, 8.1085F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(70, 280).addBox(-19.652F, -16.6805F, 8.1085F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(176, 43).addBox(-22.652F, -14.1305F, 29.4085F, 39.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(132, 107).addBox(-22.652F, -14.1305F, 31.4085F, 39.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-23.652F, 7.4195F, -17.8915F, 41.0F, 1.0F, 47.0F, new CubeDeformation(0.0F))
		.texOffs(51, 17).addBox(-20.652F, -4.5805F, -18.8915F, 35.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(0, 109).addBox(14.348F, -3.6805F, -19.8915F, 2.0F, 2.0F, 52.0F, new CubeDeformation(0.0F))
		.texOffs(18, 279).addBox(0.348F, 1.3195F, 54.1085F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(18, 279).addBox(-7.652F, 1.3195F, 54.1085F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(134, 262).addBox(-7.652F, 5.0195F, 43.6085F, 2.0F, 3.0F, 11.0F, new CubeDeformation(0.0F))
		.texOffs(134, 262).addBox(0.348F, 5.0195F, 43.6085F, 2.0F, 3.0F, 11.0F, new CubeDeformation(0.0F))
		.texOffs(0, 199).addBox(-23.652F, 0.3195F, 23.1085F, 41.0F, 8.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(138, 70).addBox(-25.652F, -4.6805F, 39.1085F, 45.0F, 2.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(228, 258).addBox(15.348F, 4.3195F, 20.1085F, 3.0F, 2.0F, 9.0F, new CubeDeformation(0.0F))
		.texOffs(238, 47).addBox(15.348F, -7.6805F, 29.1085F, 3.0F, 5.0F, 19.0F, new CubeDeformation(0.0F))
		.texOffs(252, 270).addBox(-24.652F, 4.3195F, 20.1085F, 2.0F, 2.0F, 9.0F, new CubeDeformation(0.0F))
		.texOffs(242, 168).addBox(-24.652F, -7.6805F, 29.1085F, 2.0F, 5.0F, 19.0F, new CubeDeformation(0.0F))
		.texOffs(182, 281).mirror().addBox(-24.652F, 3.7195F, -37.2415F, 5.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(272, 26).mirror().addBox(-24.652F, -4.6805F, -29.8915F, 5.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(56, 235).mirror().addBox(-24.652F, 6.3195F, -17.8915F, 2.0F, 3.0F, 21.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(142, 231).mirror().addBox(-23.802F, -0.8805F, -18.8915F, 1.0F, 8.0F, 23.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(84, 200).mirror().addBox(-23.802F, -0.8805F, 3.1085F, 1.0F, 7.0F, 28.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(200, 200).mirror().addBox(-24.652F, 5.3195F, 3.1085F, 2.0F, 3.0F, 27.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(228, 258).mirror().addBox(-24.652F, 4.3195F, 20.1085F, 3.0F, 2.0F, 9.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(0, 109).mirror().addBox(-22.652F, -3.6805F, -19.8915F, 2.0F, 2.0F, 52.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(108, 130).addBox(-23.152F, -12.1805F, 31.1085F, 40.0F, 2.0F, 13.0F, new CubeDeformation(0.0F))
		.texOffs(0, 48).addBox(-23.102F, -11.3305F, 33.1085F, 40.0F, 1.0F, 29.0F, new CubeDeformation(0.0F))
		.texOffs(0, 78).addBox(-22.652F, -10.3805F, 33.1085F, 38.0F, 2.0F, 28.0F, new CubeDeformation(0.0F))
		.texOffs(0, 79).addBox(-22.652F, -8.3805F, 33.1085F, 38.0F, 1.0F, 28.0F, new CubeDeformation(0.0F))
		.texOffs(108, 109).addBox(-24.652F, -7.6805F, 47.2085F, 43.0F, 6.0F, 15.0F, new CubeDeformation(0.0F))
		.texOffs(224, 107).addBox(-17.652F, 12.4695F, -40.6915F, 29.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(26, 275).addBox(11.348F, 10.3195F, -39.8915F, 3.0F, 3.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(44, 251).addBox(14.348F, 9.3195F, -37.8915F, 2.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(36, 284).addBox(16.348F, 8.7195F, -37.2415F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(182, 281).mirror().addBox(-24.652F, 3.7195F, -37.2415F, 5.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(36, 284).mirror().addBox(-24.652F, 8.7195F, -37.2415F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(26, 275).addBox(-20.652F, 10.3195F, -39.8915F, 3.0F, 3.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(44, 251).addBox(-22.652F, 9.3195F, -37.8915F, 2.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(200, 145).addBox(-20.652F, -2.8805F, -16.8915F, 36.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(260, 42).addBox(1.348F, -3.8805F, -16.8915F, 10.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(214, 130).addBox(-21.652F, 5.3195F, -17.8915F, 37.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(102, 235).addBox(13.348F, 2.3195F, -18.8915F, 3.0F, 7.0F, 17.0F, new CubeDeformation(0.0F))
		.texOffs(0, 279).addBox(13.348F, 1.3195F, -18.8915F, 3.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(276, 157).addBox(-22.652F, 1.3195F, -4.8915F, 4.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(276, 157).addBox(-22.652F, 1.3195F, -1.8915F, 4.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(276, 157).addBox(-22.652F, 1.3195F, 1.1085F, 4.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(276, 157).addBox(-22.652F, 1.3195F, 4.1085F, 4.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(276, 157).mirror().addBox(13.348F, 1.3195F, -4.8915F, 4.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(124, 276).addBox(-22.652F, 1.3195F, -18.8915F, 4.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(252, 230).addBox(-22.652F, 2.3195F, -18.8915F, 4.0F, 7.0F, 17.0F, new CubeDeformation(0.0F))
		.texOffs(252, 230).addBox(-22.652F, 2.3195F, -1.8915F, 4.0F, 7.0F, 17.0F, new CubeDeformation(0.0F))
		.texOffs(252, 230).mirror().addBox(12.348F, 2.3195F, -1.8915F, 4.0F, 7.0F, 17.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(276, 157).mirror().addBox(12.348F, 1.3195F, -1.8915F, 4.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(276, 157).mirror().addBox(12.348F, 1.3195F, 1.1085F, 4.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(276, 157).mirror().addBox(12.348F, 1.3195F, 4.1085F, 4.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(140, 50).addBox(-20.652F, -3.6805F, 22.1085F, 35.0F, 12.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(258, 192).addBox(1.348F, 0.3195F, 7.1085F, 3.0F, 8.0F, 15.0F, new CubeDeformation(0.0F))
		.texOffs(258, 192).addBox(-10.652F, 0.3195F, 7.1085F, 3.0F, 8.0F, 15.0F, new CubeDeformation(0.0F))
		.texOffs(190, 258).addBox(-23.652F, 0.3195F, 7.1085F, 4.0F, 8.0F, 15.0F, new CubeDeformation(0.0F))
		.texOffs(258, 192).addBox(13.348F, 0.3195F, 7.1085F, 3.0F, 8.0F, 15.0F, new CubeDeformation(0.0F))
		.texOffs(252, 254).addBox(-20.652F, 4.3195F, 8.1085F, 11.0F, 4.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(252, 254).addBox(-8.652F, 4.3195F, 8.1085F, 11.0F, 4.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(252, 254).addBox(3.348F, 4.3195F, 8.1085F, 11.0F, 4.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(3.152F, 8.6805F, -4.1085F));

		PartDefinition inside_r1 = body.addOrReplaceChild("inside_r1", CubeListBuilder.create().texOffs(56, 211).addBox(-11.4412F, -9.8439F, 5.3589F, 11.0F, 18.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(56, 211).addBox(-23.4412F, -9.8439F, 5.3589F, 11.0F, 18.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(56, 211).addBox(-35.4412F, -9.8439F, 5.3589F, 11.0F, 18.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(14.7892F, -2.8365F, 15.7495F, -0.3927F, 0.0F, 0.0F));

		PartDefinition inside_r2 = body.addOrReplaceChild("inside_r2", CubeListBuilder.create().texOffs(200, 151).addBox(-4.4088F, 2.9061F, -9.6411F, 37.0F, 9.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-17.2432F, -1.5865F, -5.2505F, -0.3927F, 0.0F, 0.0F));

		PartDefinition cube_r1 = body.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(258, 215).addBox(2.75F, -3.9299F, -2.2161F, 5.0F, 1.0F, 13.0F, new CubeDeformation(0.0F))
		.texOffs(278, 282).addBox(2.75F, -3.9299F, -5.2161F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(160, 262).addBox(-31.25F, -2.9299F, 0.7839F, 3.0F, 1.0F, 9.0F, new CubeDeformation(0.0F))
		.texOffs(114, 283).addBox(-31.25F, -2.9299F, -2.2161F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(160, 262).addBox(4.75F, -2.9299F, 0.7839F, 3.0F, 1.0F, 9.0F, new CubeDeformation(0.0F))
		.texOffs(114, 283).addBox(4.75F, -2.9299F, -2.2161F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(266, 282).addBox(2.75F, -2.9299F, -5.2161F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(196, 281).addBox(-31.25F, -1.9299F, 0.7839F, 5.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(196, 281).addBox(2.75F, -1.9299F, 0.7839F, 5.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(274, 270).addBox(2.75F, -1.9299F, -2.2161F, 5.0F, 9.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(44, 239).addBox(2.75F, -1.9299F, -5.2161F, 3.0F, 9.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(258, 215).addBox(-31.25F, -3.9299F, -2.2161F, 5.0F, 1.0F, 13.0F, new CubeDeformation(0.0F))
		.texOffs(278, 282).addBox(-29.25F, -3.9299F, -5.2161F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(266, 282).addBox(-29.25F, -2.9299F, -5.2161F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(274, 270).addBox(-31.25F, -1.9299F, -2.2161F, 5.0F, 9.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(44, 239).addBox(-29.25F, -1.9299F, -5.2161F, 3.0F, 9.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(8.598F, 3.2494F, -37.6754F, 0.3927F, 0.0F, 0.0F));

		PartDefinition cube_r2 = body.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(224, 117).addBox(-2.75F, -17.9799F, -40.0F, 29.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(242, 161).addBox(-2.75F, -16.9799F, -40.0F, 29.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(108, 145).addBox(-2.75F, -18.9799F, -40.0F, 29.0F, 1.0F, 17.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-14.902F, 4.2494F, -0.6915F, 0.3927F, 0.0F, 0.0F));

		PartDefinition cube_r3 = body.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(104, 282).addBox(-36.65F, -1.5845F, 0.3517F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(104, 282).addBox(0.35F, -1.5845F, 0.3517F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(13.998F, 9.904F, -39.2432F, -0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r4 = body.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(282, 59).addBox(-37.65F, -0.5845F, 0.3517F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(282, 59).addBox(-5.65F, -0.5845F, 0.3517F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(16.998F, 9.904F, -41.2432F, -0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r5 = body.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(70, 280).mirror().addBox(-37.0F, -0.102F, 1.829F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(70, 280).addBox(4.0F, -0.102F, 1.829F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(12.348F, 6.1215F, -38.0205F, -0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r6 = body.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(282, 64).addBox(35.0F, 3.898F, -4.171F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-18.652F, 6.1215F, -38.0205F, 0.3927F, 0.0F, 0.0F));

		PartDefinition cube_r7 = body.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(282, 64).addBox(35.0F, 3.898F, -4.171F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(282, 64).addBox(-2.0F, 3.898F, -4.171F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-20.652F, 6.4215F, -39.7205F, 0.3927F, 0.0F, 0.0F));

		PartDefinition cube_r8 = body.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(278, 126).addBox(35.0F, 3.898F, -4.171F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(278, 126).addBox(3.0F, 3.898F, -4.171F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-23.652F, 6.4215F, -41.7205F, 0.3927F, 0.0F, 0.0F));

		PartDefinition cube_r9 = body.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(224, 122).addBox(7.0F, 3.898F, -4.171F, 29.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-24.652F, 6.5715F, -42.5205F, 0.3927F, 0.0F, 0.0F));

		PartDefinition cube_r10 = body.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(70, 280).addBox(-36.65F, -0.102F, 1.829F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(70, 280).addBox(0.35F, -0.102F, 1.829F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(13.998F, 6.4215F, -39.7205F, -0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r11 = body.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(126, 283).addBox(-37.65F, 0.898F, 1.829F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(126, 283).addBox(-5.65F, 0.898F, 1.829F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(16.998F, 6.4215F, -41.7205F, -0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r12 = body.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(224, 122).addBox(7.0F, 0.4155F, 0.3517F, 29.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-24.652F, 10.054F, -42.0432F, -0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r13 = body.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(238, 71).addBox(7.0F, 0.898F, 1.829F, 29.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-24.652F, 6.5715F, -42.5205F, -0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r14 = body.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(98, 163).mirror().addBox(-21.75F, 0.3022F, -27.55F, 3.0F, 4.0F, 33.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.952F, -3.2827F, 56.6585F, 0.0F, 0.0F, 0.3927F));

		PartDefinition cube_r15 = body.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(98, 163).addBox(18.75F, 0.3022F, -27.55F, 3.0F, 4.0F, 33.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.352F, -3.2827F, 56.6585F, 0.0F, 0.0F, -0.3927F));

		PartDefinition roof_r1 = body.addOrReplaceChild("roof_r1", CubeListBuilder.create().texOffs(228, 47).addBox(-38.75F, -10.176F, 11.4477F, 2.0F, 11.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(228, 47).addBox(-1.75F, -10.176F, 11.4477F, 2.0F, 11.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(16.098F, -8.5045F, -7.3392F, -0.3927F, 0.0F, 0.0F));

		PartDefinition roof_r2 = body.addOrReplaceChild("roof_r2", CubeListBuilder.create().texOffs(0, 211).addBox(-76.75F, -28.2F, -14.0F, 2.0F, 2.0F, 26.0F, new CubeDeformation(0.0F))
		.texOffs(0, 211).addBox(-39.75F, -28.2F, -14.0F, 2.0F, 2.0F, 26.0F, new CubeDeformation(0.0F))
		.texOffs(214, 141).addBox(-74.75F, -28.2F, 10.0F, 35.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(214, 136).addBox(-74.75F, -28.2F, -14.0F, 35.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(54.098F, 16.5195F, 4.1085F, 0.3927F, 0.0F, 0.0F));

		PartDefinition roof_r3 = body.addOrReplaceChild("roof_r3", CubeListBuilder.create().texOffs(0, 208).addBox(0.25F, 10.8F, 9.2F, 39.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-22.902F, -13.5805F, 16.1585F, 0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r16 = body.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(43, 258).mirror().addBox(-39.24F, 8.8F, -5.8F, 2.0F, 6.0F, 16.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(43, 258).addBox(-2.26F, 8.8F, -5.8F, 2.0F, 6.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(16.598F, -13.4805F, 15.9085F, 0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r17 = body.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(26, 262).mirror().addBox(-0.1722F, -0.9638F, 13.75F, 2.0F, 6.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(104, 259).mirror().addBox(-0.1722F, -0.9638F, 0.75F, 2.0F, 3.0F, 13.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(26, 262).addBox(40.8278F, -0.9638F, 13.75F, 2.0F, 6.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(104, 259).addBox(40.8278F, -0.9638F, 0.75F, 2.0F, 3.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-24.4799F, 7.2833F, 15.3585F, 0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r18 = body.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(0, 262).mirror().addBox(-42.75F, -11.9F, -0.9F, 3.0F, 7.0F, 10.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(0, 262).addBox(-2.75F, -11.9F, -0.9F, 3.0F, 7.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(18.098F, 9.2195F, 20.0085F, -0.3927F, 0.0F, 0.0F));

		PartDefinition cube_r19 = body.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(142, 200).mirror().addBox(0.4278F, -1.0638F, -14.5F, 1.0F, 3.0F, 28.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-23.4799F, -2.8667F, 17.6085F, 0.0F, 0.0F, 0.3927F));

		PartDefinition cube_r20 = body.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(200, 230).mirror().addBox(0.4278F, -1.0638F, -18.5F, 1.0F, 3.0F, 25.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-23.4799F, -2.8667F, -3.3915F, 0.0F, 0.0F, 0.3927F));

		PartDefinition cube_r21 = body.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(228, 279).addBox(-2.75F, -2.4042F, -1.3668F, 5.0F, 8.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(58, 280).addBox(36.25F, -2.4042F, -1.3668F, 4.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-21.902F, 2.4738F, -17.2747F, 0.3927F, 0.0F, 0.0F));

		PartDefinition cube_r22 = body.addOrReplaceChild("cube_r22", CubeListBuilder.create().texOffs(104, 275).mirror().addBox(-2.75F, -1.0537F, -1.4476F, 5.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(104, 275).addBox(35.25F, -1.0537F, -1.4476F, 5.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-21.902F, -1.7768F, -20.8939F, -0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r23 = body.addOrReplaceChild("cube_r23", CubeListBuilder.create().texOffs(280, 145).mirror().addBox(-2.75F, -3.35F, 4.0F, 5.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(280, 145).addBox(35.25F, -3.35F, 4.0F, 5.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-21.902F, -3.1305F, -28.8915F, -0.3927F, 0.0F, 0.0F));

		PartDefinition cube_r24 = body.addOrReplaceChild("cube_r24", CubeListBuilder.create().texOffs(104, 275).mirror().addBox(-2.75F, -0.5813F, -12.7638F, 5.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(104, 275).addBox(35.25F, -0.5813F, -12.7638F, 5.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-21.902F, -7.0992F, -22.5277F, 0.3927F, 0.0F, 0.0F));

		PartDefinition cube_r25 = body.addOrReplaceChild("cube_r25", CubeListBuilder.create().texOffs(44, 280).mirror().addBox(-2.75F, 1.4833F, -2.0499F, 5.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(44, 280).addBox(35.25F, 1.4833F, -2.0499F, 5.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-21.902F, -3.3137F, -32.0917F, -0.3927F, 0.0F, 0.0F));

		PartDefinition cube_r26 = body.addOrReplaceChild("cube_r26", CubeListBuilder.create().texOffs(228, 60).addBox(-42.25F, 0.998F, 0.422F, 2.0F, 7.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(158, 281).addBox(-2.25F, 0.998F, 0.422F, 3.0F, 7.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(17.598F, -1.6785F, 47.6864F, 0.3927F, 0.0F, 0.0F));

		PartDefinition cube_r27 = body.addOrReplaceChild("cube_r27", CubeListBuilder.create().texOffs(104, 282).addBox(-42.25F, -3.9126F, 0.4614F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(282, 53).addBox(-2.25F, -3.9126F, 0.4614F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(17.598F, -2.7678F, 43.6471F, -0.3927F, 0.0F, 0.0F));

		PartDefinition cube_r28 = body.addOrReplaceChild("cube_r28", CubeListBuilder.create().texOffs(180, 272).addBox(-42.25F, -20.75F, -4.7F, 2.0F, 4.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(254, 281).addBox(-2.25F, -20.75F, -4.7F, 3.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(17.598F, 9.0695F, 63.8085F, 0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r29 = body.addOrReplaceChild("cube_r29", CubeListBuilder.create().texOffs(104, 282).addBox(-42.25F, -4.0626F, -3.0614F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(282, 53).addBox(-2.25F, -4.0626F, -3.0614F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(17.598F, -2.6178F, 40.1698F, 0.3927F, 0.0F, 0.0F));

		PartDefinition cube_r30 = body.addOrReplaceChild("cube_r30", CubeListBuilder.create().texOffs(84, 184).addBox(-42.25F, -22.9F, -0.9F, 2.0F, 10.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(272, 0).addBox(-2.25F, -22.9F, -0.9F, 3.0F, 10.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(17.598F, 9.2195F, 20.0085F, -0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r31 = body.addOrReplaceChild("cube_r31", CubeListBuilder.create().texOffs(176, 23).addBox(-5.75F, -2.3626F, -0.4386F, 45.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-19.902F, -2.3178F, 43.5471F, -0.3927F, 0.0F, 0.0F));

		PartDefinition cube_r32 = body.addOrReplaceChild("cube_r32", CubeListBuilder.create().texOffs(176, 15).addBox(-5.75F, 1.548F, 0.522F, 45.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-19.902F, -1.1285F, 46.7364F, 0.3927F, 0.0F, 0.0F));

		PartDefinition cube_r33 = body.addOrReplaceChild("cube_r33", CubeListBuilder.create().texOffs(176, 28).addBox(-5.75F, -21.2F, -4.6F, 45.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-19.902F, 9.6195F, 63.6585F, 0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r34 = body.addOrReplaceChild("cube_r34", CubeListBuilder.create().texOffs(176, 23).addBox(-5.75F, -2.1126F, -3.4614F, 45.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-19.902F, -2.9178F, 40.3698F, 0.3927F, 0.0F, 0.0F));

		PartDefinition cube_r35 = body.addOrReplaceChild("cube_r35", CubeListBuilder.create().texOffs(176, 0).addBox(-5.75F, -20.95F, 2.7F, 45.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-19.902F, 9.3695F, 19.6085F, -0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r36 = body.addOrReplaceChild("cube_r36", CubeListBuilder.create().texOffs(0, 184).addBox(-0.75F, -25.2F, -0.3F, 41.0F, 14.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-22.902F, 8.5195F, 15.4085F, -0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r37 = body.addOrReplaceChild("cube_r37", CubeListBuilder.create().texOffs(0, 174).addBox(-5.75F, -11.95F, 7.7F, 45.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-19.902F, 9.5195F, 19.7085F, -0.3927F, 0.0F, 0.0F));

		PartDefinition cube_r38 = body.addOrReplaceChild("cube_r38", CubeListBuilder.create().texOffs(190, 241).addBox(3.25F, 1.2712F, -1.0173F, 2.0F, 4.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(190, 241).addBox(-4.75F, 1.2712F, -1.0173F, 2.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.902F, 2.3983F, 54.7758F, -0.3927F, 0.0F, 0.0F));

		PartDefinition cube_r39 = body.addOrReplaceChild("cube_r39", CubeListBuilder.create().texOffs(132, 78).addBox(-21.25F, -4.6978F, -7.55F, 43.0F, 11.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.402F, -3.5327F, 57.1585F, 0.3927F, 0.0F, 0.0F));

		PartDefinition cube_r40 = body.addOrReplaceChild("cube_r40", CubeListBuilder.create().texOffs(132, 94).addBox(-21.25F, -2.6978F, -0.55F, 43.0F, 7.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.402F, -3.6327F, 56.4585F, -0.7854F, 0.0F, 0.0F));

		PartDefinition roof_r4 = body.addOrReplaceChild("roof_r4", CubeListBuilder.create().texOffs(224, 126).addBox(-12.25F, -1.1343F, -0.6848F, 25.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.402F, -15.5461F, 8.7932F, 0.3927F, 0.0F, 0.0F));

		PartDefinition cube_r41 = body.addOrReplaceChild("cube_r41", CubeListBuilder.create().texOffs(142, 200).addBox(-1.4278F, -1.0638F, -14.5F, 1.0F, 3.0F, 28.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(17.1758F, -2.8667F, 17.6085F, 0.0F, 0.0F, -0.3927F));

		PartDefinition cube_r42 = body.addOrReplaceChild("cube_r42", CubeListBuilder.create().texOffs(200, 230).addBox(-1.4278F, -1.0638F, -18.5F, 1.0F, 3.0F, 25.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(17.1758F, -2.8667F, -3.3915F, 0.0F, 0.0F, -0.3927F));

		PartDefinition cube_r43 = body.addOrReplaceChild("cube_r43", CubeListBuilder.create().texOffs(0, 0).addBox(-3.25F, 0.1516F, 0.4203F, 0.0F, 3.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-3.25F, 0.1516F, 0.4203F, 7.0F, 0.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.402F, -3.832F, -15.3119F, 0.3927F, 0.0F, 0.0F));

		PartDefinition cube_r44 = body.addOrReplaceChild("cube_r44", CubeListBuilder.create().texOffs(242, 192).addBox(-3.25F, 0.1516F, -0.5797F, 7.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.402F, -4.032F, -13.3119F, 0.3927F, 0.0F, 0.0F));

		PartDefinition shifter = body.addOrReplaceChild("shifter", CubeListBuilder.create(), PartPose.offset(-2.3331F, 4.2653F, -7.9974F));

		PartDefinition steering_wheel = body.addOrReplaceChild("steering_wheel", CubeListBuilder.create().texOffs(94, 174).addBox(-4.0167F, -2.4313F, 0.3991F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(200, 161).addBox(-3.0167F, 1.5687F, 0.3991F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(94, 179).addBox(-3.0167F, 0.5687F, 0.3991F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(138, 77).addBox(-3.0167F, -1.4313F, 0.3991F, 6.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(56, 232).addBox(-1.0167F, -1.4313F, 0.3991F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(150, 77).addBox(-1.0167F, 0.5687F, 0.3991F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(94, 179).addBox(1.9833F, 0.5687F, 0.3991F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(282, 47).addBox(-1.0167F, -1.4313F, -3.6009F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(94, 174).addBox(2.9833F, -2.4313F, 0.3991F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.3646F, -2.2491F, -10.2906F, 0.3927F, 0.0F, 0.0F));

		PartDefinition bone = body.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(252, 254).addBox(0.3912F, 7.1561F, -7.6411F, 11.0F, 4.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(252, 254).addBox(18.3912F, 7.1561F, -7.6411F, 11.0F, 4.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(228, 269).addBox(11.3912F, 6.1561F, -1.6411F, 7.0F, 5.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(272, 15).addBox(12.3912F, 5.1561F, -1.6411F, 5.0F, 6.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(228, 269).addBox(11.3912F, 6.1561F, -17.6411F, 7.0F, 5.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(272, 15).addBox(12.8912F, 5.1561F, -17.6411F, 4.0F, 6.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(231, 272).addBox(11.3912F, 6.1561F, -19.6411F, 7.0F, 5.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(160, 272).addBox(11.8912F, 6.1561F, -5.6411F, 6.0F, 5.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(94, 174).addBox(15.3912F, 3.1561F, -3.6411F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(230, 104).addBox(15.3912F, 2.1561F, -3.6411F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(190, 231).addBox(16.8912F, 5.1561F, -9.6411F, 1.0F, 6.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(190, 248).addBox(12.8912F, 5.1561F, -6.6411F, 4.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(190, 248).addBox(12.8912F, 5.1561F, -12.6411F, 4.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(190, 248).addBox(12.8912F, 5.1561F, -18.6411F, 4.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(190, 231).mirror().addBox(11.8912F, 5.1561F, -9.6411F, 1.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(191, 234).mirror().addBox(11.8912F, 7.1561F, -12.6411F, 1.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(191, 234).addBox(16.8912F, 7.1561F, -12.6411F, 1.0F, 4.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(191, 232).mirror().addBox(11.8912F, 5.1561F, -12.6411F, 1.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(191, 232).addBox(16.8912F, 5.1561F, -12.6411F, 1.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(191, 232).mirror().addBox(11.8912F, 5.1561F, -15.6411F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(191, 232).addBox(16.8912F, 5.1561F, -15.6411F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(191, 232).addBox(16.8912F, 5.1561F, -18.6411F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(191, 232).mirror().addBox(11.8912F, 5.1561F, -18.6411F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(18, 284).addBox(12.8912F, 7.1561F, -9.6411F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(18, 284).addBox(12.8912F, 7.1561F, -10.6411F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(18, 284).addBox(12.8912F, 7.1561F, -11.6411F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(18, 284).addBox(12.8912F, 7.1561F, -12.6411F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-18.0432F, -2.8365F, 3.7495F));

		PartDefinition inside_r3 = bone.addOrReplaceChild("inside_r3", CubeListBuilder.create().texOffs(212, 281).addBox(-1.5872F, -2.299F, -2.8169F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(14.4784F, 8.4551F, -5.8241F, 0.3927F, 0.0F, 0.0F));

		PartDefinition inside_r4 = bone.addOrReplaceChild("inside_r4", CubeListBuilder.create().texOffs(56, 211).addBox(0.2912F, -9.8439F, 5.3589F, 11.0F, 18.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(56, 211).addBox(-17.7088F, -9.8439F, 5.3589F, 11.0F, 18.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(18.1F, 0.0F, 0.0F, -0.3927F, 0.0F, 0.0F));

		PartDefinition Front_Left_Wheel = body.addOrReplaceChild("Front_Left_Wheel", CubeListBuilder.create().texOffs(78, 259).addBox(1.3194F, -6.5958F, -6.6067F, 0.0F, 13.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offset(17.0285F, 6.7654F, -26.7849F));

		PartDefinition right_wheel_r1 = Front_Left_Wheel.addOrReplaceChild("right_wheel_r1", CubeListBuilder.create().texOffs(276, 151).addBox(-4.375F, -1.4379F, -7.5029F, 7.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.6944F, 0.0042F, -0.0416F, 0.7854F, 0.0F, 0.0F));

		PartDefinition right_wheel_r2 = Front_Left_Wheel.addOrReplaceChild("right_wheel_r2", CubeListBuilder.create().texOffs(276, 151).addBox(-4.375F, -1.4282F, -7.5453F, 7.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.6944F, 0.0042F, -0.0416F, -2.7576F, 0.0F, 0.0F));

		PartDefinition right_wheel_r3 = Front_Left_Wheel.addOrReplaceChild("right_wheel_r3", CubeListBuilder.create().texOffs(276, 151).addBox(-4.375F, -1.5F, -7.555F, 7.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.6944F, 0.0042F, -0.0416F, 3.1416F, 0.0F, 0.0F));

		PartDefinition right_wheel_r4 = Front_Left_Wheel.addOrReplaceChild("right_wheel_r4", CubeListBuilder.create().texOffs(276, 151).addBox(-4.375F, -1.5015F, -7.5665F, 7.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.6944F, 0.0042F, -0.0416F, -2.3562F, 0.0F, 0.0F));

		PartDefinition right_wheel_r5 = Front_Left_Wheel.addOrReplaceChild("right_wheel_r5", CubeListBuilder.create().texOffs(276, 151).addBox(-4.375F, -1.5642F, -7.5621F, 7.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.6944F, 0.0042F, -0.0416F, -1.9635F, 0.0F, 0.0F));

		PartDefinition right_wheel_r6 = Front_Left_Wheel.addOrReplaceChild("right_wheel_r6", CubeListBuilder.create().texOffs(276, 151).addBox(-4.375F, -1.545F, -7.51F, 7.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.6944F, 0.0042F, -0.0416F, -1.5708F, 0.0F, 0.0F));

		PartDefinition right_wheel_r7 = Front_Left_Wheel.addOrReplaceChild("right_wheel_r7", CubeListBuilder.create().texOffs(276, 151).addBox(-4.375F, -1.4531F, -7.4867F, 7.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.6944F, 0.0042F, -0.0416F, -1.1868F, 0.0F, 0.0F));

		PartDefinition right_wheel_r8 = Front_Left_Wheel.addOrReplaceChild("right_wheel_r8", CubeListBuilder.create().texOffs(276, 151).addBox(-4.375F, -1.5015F, -7.5029F, 7.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.6944F, 0.0042F, -0.0416F, -0.7854F, 0.0F, 0.0F));

		PartDefinition right_wheel_r9 = Front_Left_Wheel.addOrReplaceChild("right_wheel_r9", CubeListBuilder.create().texOffs(276, 151).addBox(-4.375F, -1.5398F, -7.5033F, 7.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.6944F, 0.0042F, -0.0416F, -0.3927F, 0.0F, 0.0F));

		PartDefinition right_wheel_r10 = Front_Left_Wheel.addOrReplaceChild("right_wheel_r10", CubeListBuilder.create().texOffs(276, 151).addBox(-4.375F, -1.4724F, -7.5549F, 7.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.6944F, 0.0042F, -0.0416F, -0.0087F, 0.0F, 0.0F));

		PartDefinition right_wheel_r11 = Front_Left_Wheel.addOrReplaceChild("right_wheel_r11", CubeListBuilder.create().texOffs(276, 151).addBox(-4.375F, -1.455F, -7.51F, 7.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.6944F, 0.0042F, -0.0416F, 1.5708F, 0.0F, 0.0F));

		PartDefinition right_wheel_r12 = Front_Left_Wheel.addOrReplaceChild("right_wheel_r12", CubeListBuilder.create().texOffs(276, 151).addBox(-4.375F, -1.3697F, -7.5204F, 7.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.6944F, 0.0042F, -0.0416F, 1.9548F, 0.0F, 0.0F));

		PartDefinition right_wheel_r13 = Front_Left_Wheel.addOrReplaceChild("right_wheel_r13", CubeListBuilder.create().texOffs(276, 151).addBox(-4.375F, -1.4379F, -7.5665F, 7.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.6944F, 0.0042F, -0.0416F, 2.3562F, 0.0F, 0.0F));

		PartDefinition right_wheel_r14 = Front_Left_Wheel.addOrReplaceChild("right_wheel_r14", CubeListBuilder.create().texOffs(276, 151).addBox(-4.375F, -1.5054F, -7.5864F, 7.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.6944F, 0.0042F, -0.0416F, 2.7489F, 0.0F, 0.0F));

		PartDefinition right_wheel_r15 = Front_Left_Wheel.addOrReplaceChild("right_wheel_r15", CubeListBuilder.create().texOffs(276, 151).addBox(-4.375F, -1.4811F, -7.5277F, 7.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.6944F, 0.0042F, -0.0416F, 1.1781F, 0.0F, 0.0F));

		PartDefinition right_wheel_r16 = Front_Left_Wheel.addOrReplaceChild("right_wheel_r16", CubeListBuilder.create().texOffs(276, 151).addBox(-4.375F, -1.3945F, -7.4619F, 7.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.6944F, 0.0042F, -0.0416F, 0.384F, 0.0F, 0.0F));

		PartDefinition Front_Right_Wheel = body.addOrReplaceChild("Front_Right_Wheel", CubeListBuilder.create().texOffs(78, 259).mirror().addBox(-2.2639F, -6.5958F, -6.6067F, 0.0F, 13.0F, 13.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-22.3881F, 6.7654F, -26.7849F));

		PartDefinition left_wheel_r1 = Front_Right_Wheel.addOrReplaceChild("left_wheel_r1", CubeListBuilder.create().texOffs(276, 151).mirror().addBox(-2.25F, -1.4379F, -7.5029F, 7.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-1.0139F, 0.0042F, -0.0416F, 0.7854F, 0.0F, 0.0F));

		PartDefinition left_wheel_r2 = Front_Right_Wheel.addOrReplaceChild("left_wheel_r2", CubeListBuilder.create().texOffs(276, 151).mirror().addBox(-2.25F, -1.4282F, -7.5453F, 7.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-1.0139F, 0.0042F, -0.0416F, -2.7576F, 0.0F, 0.0F));

		PartDefinition left_wheel_r3 = Front_Right_Wheel.addOrReplaceChild("left_wheel_r3", CubeListBuilder.create().texOffs(276, 151).mirror().addBox(-2.25F, -1.5F, -7.555F, 7.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-1.0139F, 0.0042F, -0.0416F, 3.1416F, 0.0F, 0.0F));

		PartDefinition left_wheel_r4 = Front_Right_Wheel.addOrReplaceChild("left_wheel_r4", CubeListBuilder.create().texOffs(276, 151).mirror().addBox(-2.25F, -1.5015F, -7.5665F, 7.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-1.0139F, 0.0042F, -0.0416F, -2.3562F, 0.0F, 0.0F));

		PartDefinition left_wheel_r5 = Front_Right_Wheel.addOrReplaceChild("left_wheel_r5", CubeListBuilder.create().texOffs(276, 151).mirror().addBox(-2.25F, -1.5642F, -7.5621F, 7.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-1.0139F, 0.0042F, -0.0416F, -1.9635F, 0.0F, 0.0F));

		PartDefinition left_wheel_r6 = Front_Right_Wheel.addOrReplaceChild("left_wheel_r6", CubeListBuilder.create().texOffs(276, 151).mirror().addBox(-2.25F, -1.545F, -7.51F, 7.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-1.0139F, 0.0042F, -0.0416F, -1.5708F, 0.0F, 0.0F));

		PartDefinition left_wheel_r7 = Front_Right_Wheel.addOrReplaceChild("left_wheel_r7", CubeListBuilder.create().texOffs(276, 151).mirror().addBox(-2.25F, -1.4531F, -7.4867F, 7.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-1.0139F, 0.0042F, -0.0416F, -1.1868F, 0.0F, 0.0F));

		PartDefinition left_wheel_r8 = Front_Right_Wheel.addOrReplaceChild("left_wheel_r8", CubeListBuilder.create().texOffs(276, 151).mirror().addBox(-2.25F, -1.5015F, -7.5029F, 7.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-1.0139F, 0.0042F, -0.0416F, -0.7854F, 0.0F, 0.0F));

		PartDefinition left_wheel_r9 = Front_Right_Wheel.addOrReplaceChild("left_wheel_r9", CubeListBuilder.create().texOffs(276, 151).mirror().addBox(-2.25F, -1.5398F, -7.5033F, 7.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-1.0139F, 0.0042F, -0.0416F, -0.3927F, 0.0F, 0.0F));

		PartDefinition left_wheel_r10 = Front_Right_Wheel.addOrReplaceChild("left_wheel_r10", CubeListBuilder.create().texOffs(276, 151).mirror().addBox(-2.25F, -1.4724F, -7.5549F, 7.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-1.0139F, 0.0042F, -0.0416F, -0.0087F, 0.0F, 0.0F));

		PartDefinition left_wheel_r11 = Front_Right_Wheel.addOrReplaceChild("left_wheel_r11", CubeListBuilder.create().texOffs(276, 151).mirror().addBox(-2.25F, -1.455F, -7.51F, 7.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-1.0139F, 0.0042F, -0.0416F, 1.5708F, 0.0F, 0.0F));

		PartDefinition left_wheel_r12 = Front_Right_Wheel.addOrReplaceChild("left_wheel_r12", CubeListBuilder.create().texOffs(276, 151).mirror().addBox(-2.25F, -1.3697F, -7.5204F, 7.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-1.0139F, 0.0042F, -0.0416F, 1.9548F, 0.0F, 0.0F));

		PartDefinition left_wheel_r13 = Front_Right_Wheel.addOrReplaceChild("left_wheel_r13", CubeListBuilder.create().texOffs(276, 151).mirror().addBox(-2.25F, -1.4379F, -7.5665F, 7.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-1.0139F, 0.0042F, -0.0416F, 2.3562F, 0.0F, 0.0F));

		PartDefinition left_wheel_r14 = Front_Right_Wheel.addOrReplaceChild("left_wheel_r14", CubeListBuilder.create().texOffs(276, 151).mirror().addBox(-2.25F, -1.5054F, -7.5864F, 7.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-1.0139F, 0.0042F, -0.0416F, 2.7489F, 0.0F, 0.0F));

		PartDefinition left_wheel_r15 = Front_Right_Wheel.addOrReplaceChild("left_wheel_r15", CubeListBuilder.create().texOffs(276, 151).mirror().addBox(-2.25F, -1.4811F, -7.5277F, 7.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-1.0139F, 0.0042F, -0.0416F, 1.1781F, 0.0F, 0.0F));

		PartDefinition left_wheel_r16 = Front_Right_Wheel.addOrReplaceChild("left_wheel_r16", CubeListBuilder.create().texOffs(276, 151).mirror().addBox(-2.25F, -1.3945F, -7.4619F, 7.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-1.0139F, 0.0042F, -0.0416F, 0.384F, 0.0F, 0.0F));

		PartDefinition Back_Left_Wheel = body.addOrReplaceChild("Back_Left_Wheel", CubeListBuilder.create().texOffs(78, 259).addBox(1.3194F, -6.5819F, -6.5789F, 0.0F, 13.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offset(17.0285F, 6.0015F, 41.6874F));

		PartDefinition right_wheel_r17 = Back_Left_Wheel.addOrReplaceChild("right_wheel_r17", CubeListBuilder.create().texOffs(276, 151).addBox(-4.375F, -1.4379F, -7.5029F, 7.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.6944F, 0.0181F, -0.0138F, 0.7854F, 0.0F, 0.0F));

		PartDefinition right_wheel_r18 = Back_Left_Wheel.addOrReplaceChild("right_wheel_r18", CubeListBuilder.create().texOffs(276, 151).addBox(-4.375F, -1.4282F, -7.5453F, 7.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.6944F, 0.0181F, -0.0138F, -2.7576F, 0.0F, 0.0F));

		PartDefinition right_wheel_r19 = Back_Left_Wheel.addOrReplaceChild("right_wheel_r19", CubeListBuilder.create().texOffs(276, 151).addBox(-4.375F, -1.5F, -7.555F, 7.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.6944F, 0.0181F, -0.0138F, 3.1416F, 0.0F, 0.0F));

		PartDefinition right_wheel_r20 = Back_Left_Wheel.addOrReplaceChild("right_wheel_r20", CubeListBuilder.create().texOffs(276, 151).addBox(-4.375F, -1.5015F, -7.5665F, 7.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.6944F, 0.0181F, -0.0138F, -2.3562F, 0.0F, 0.0F));

		PartDefinition right_wheel_r21 = Back_Left_Wheel.addOrReplaceChild("right_wheel_r21", CubeListBuilder.create().texOffs(276, 151).addBox(-4.375F, -1.5642F, -7.5621F, 7.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.6944F, 0.0181F, -0.0138F, -1.9635F, 0.0F, 0.0F));

		PartDefinition right_wheel_r22 = Back_Left_Wheel.addOrReplaceChild("right_wheel_r22", CubeListBuilder.create().texOffs(276, 151).addBox(-4.375F, -1.545F, -7.51F, 7.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.6944F, 0.0181F, -0.0138F, -1.5708F, 0.0F, 0.0F));

		PartDefinition right_wheel_r23 = Back_Left_Wheel.addOrReplaceChild("right_wheel_r23", CubeListBuilder.create().texOffs(276, 151).addBox(-4.375F, -1.4531F, -7.4867F, 7.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.6944F, 0.0181F, -0.0138F, -1.1868F, 0.0F, 0.0F));

		PartDefinition right_wheel_r24 = Back_Left_Wheel.addOrReplaceChild("right_wheel_r24", CubeListBuilder.create().texOffs(276, 151).addBox(-4.375F, -1.5015F, -7.5029F, 7.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.6944F, 0.0181F, -0.0138F, -0.7854F, 0.0F, 0.0F));

		PartDefinition right_wheel_r25 = Back_Left_Wheel.addOrReplaceChild("right_wheel_r25", CubeListBuilder.create().texOffs(276, 151).addBox(-4.375F, -1.5398F, -7.5033F, 7.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.6944F, 0.0181F, -0.0138F, -0.3927F, 0.0F, 0.0F));

		PartDefinition right_wheel_r26 = Back_Left_Wheel.addOrReplaceChild("right_wheel_r26", CubeListBuilder.create().texOffs(276, 151).addBox(-4.375F, -1.4724F, -7.5549F, 7.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.6944F, 0.0181F, -0.0138F, -0.0087F, 0.0F, 0.0F));

		PartDefinition right_wheel_r27 = Back_Left_Wheel.addOrReplaceChild("right_wheel_r27", CubeListBuilder.create().texOffs(276, 151).addBox(-4.375F, -1.455F, -7.51F, 7.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.6944F, 0.0181F, -0.0138F, 1.5708F, 0.0F, 0.0F));

		PartDefinition right_wheel_r28 = Back_Left_Wheel.addOrReplaceChild("right_wheel_r28", CubeListBuilder.create().texOffs(276, 151).addBox(-4.375F, -1.3697F, -7.5204F, 7.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.6944F, 0.0181F, -0.0138F, 1.9548F, 0.0F, 0.0F));

		PartDefinition right_wheel_r29 = Back_Left_Wheel.addOrReplaceChild("right_wheel_r29", CubeListBuilder.create().texOffs(276, 151).addBox(-4.375F, -1.4379F, -7.5665F, 7.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.6944F, 0.0181F, -0.0138F, 2.3562F, 0.0F, 0.0F));

		PartDefinition right_wheel_r30 = Back_Left_Wheel.addOrReplaceChild("right_wheel_r30", CubeListBuilder.create().texOffs(276, 151).addBox(-4.375F, -1.5054F, -7.5864F, 7.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.6944F, 0.0181F, -0.0138F, 2.7489F, 0.0F, 0.0F));

		PartDefinition right_wheel_r31 = Back_Left_Wheel.addOrReplaceChild("right_wheel_r31", CubeListBuilder.create().texOffs(276, 151).addBox(-4.375F, -1.4811F, -7.5277F, 7.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.6944F, 0.0181F, -0.0138F, 1.1781F, 0.0F, 0.0F));

		PartDefinition right_wheel_r32 = Back_Left_Wheel.addOrReplaceChild("right_wheel_r32", CubeListBuilder.create().texOffs(276, 151).addBox(-4.375F, -1.3945F, -7.4619F, 7.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.6944F, 0.0181F, -0.0138F, 0.384F, 0.0F, 0.0F));

		PartDefinition Back_Right_Wheel = body.addOrReplaceChild("Back_Right_Wheel", CubeListBuilder.create().texOffs(78, 259).mirror().addBox(-2.2639F, -6.5819F, -6.5789F, 0.0F, 13.0F, 13.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-22.3881F, 6.0015F, 41.6874F));

		PartDefinition left_wheel_r17 = Back_Right_Wheel.addOrReplaceChild("left_wheel_r17", CubeListBuilder.create().texOffs(276, 151).mirror().addBox(-2.25F, -1.4379F, -7.5029F, 7.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-1.0139F, 0.0181F, -0.0138F, 0.7854F, 0.0F, 0.0F));

		PartDefinition left_wheel_r18 = Back_Right_Wheel.addOrReplaceChild("left_wheel_r18", CubeListBuilder.create().texOffs(276, 151).mirror().addBox(-2.25F, -1.4282F, -7.5453F, 7.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-1.0139F, 0.0181F, -0.0138F, -2.7576F, 0.0F, 0.0F));

		PartDefinition left_wheel_r19 = Back_Right_Wheel.addOrReplaceChild("left_wheel_r19", CubeListBuilder.create().texOffs(276, 151).mirror().addBox(-2.25F, -1.5F, -7.555F, 7.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-1.0139F, 0.0181F, -0.0138F, 3.1416F, 0.0F, 0.0F));

		PartDefinition left_wheel_r20 = Back_Right_Wheel.addOrReplaceChild("left_wheel_r20", CubeListBuilder.create().texOffs(276, 151).mirror().addBox(-2.25F, -1.5015F, -7.5665F, 7.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-1.0139F, 0.0181F, -0.0138F, -2.3562F, 0.0F, 0.0F));

		PartDefinition left_wheel_r21 = Back_Right_Wheel.addOrReplaceChild("left_wheel_r21", CubeListBuilder.create().texOffs(276, 151).mirror().addBox(-2.25F, -1.5642F, -7.5621F, 7.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-1.0139F, 0.0181F, -0.0138F, -1.9635F, 0.0F, 0.0F));

		PartDefinition left_wheel_r22 = Back_Right_Wheel.addOrReplaceChild("left_wheel_r22", CubeListBuilder.create().texOffs(276, 151).mirror().addBox(-2.25F, -1.545F, -7.51F, 7.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-1.0139F, 0.0181F, -0.0138F, -1.5708F, 0.0F, 0.0F));

		PartDefinition left_wheel_r23 = Back_Right_Wheel.addOrReplaceChild("left_wheel_r23", CubeListBuilder.create().texOffs(276, 151).mirror().addBox(-2.25F, -1.4531F, -7.4867F, 7.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-1.0139F, 0.0181F, -0.0138F, -1.1868F, 0.0F, 0.0F));

		PartDefinition left_wheel_r24 = Back_Right_Wheel.addOrReplaceChild("left_wheel_r24", CubeListBuilder.create().texOffs(276, 151).mirror().addBox(-2.25F, -1.5015F, -7.5029F, 7.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-1.0139F, 0.0181F, -0.0138F, -0.7854F, 0.0F, 0.0F));

		PartDefinition left_wheel_r25 = Back_Right_Wheel.addOrReplaceChild("left_wheel_r25", CubeListBuilder.create().texOffs(276, 151).mirror().addBox(-2.25F, -1.5398F, -7.5033F, 7.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-1.0139F, 0.0181F, -0.0138F, -0.3927F, 0.0F, 0.0F));

		PartDefinition left_wheel_r26 = Back_Right_Wheel.addOrReplaceChild("left_wheel_r26", CubeListBuilder.create().texOffs(276, 151).mirror().addBox(-2.25F, -1.4724F, -7.5549F, 7.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-1.0139F, 0.0181F, -0.0138F, -0.0087F, 0.0F, 0.0F));

		PartDefinition left_wheel_r27 = Back_Right_Wheel.addOrReplaceChild("left_wheel_r27", CubeListBuilder.create().texOffs(276, 151).mirror().addBox(-2.25F, -1.455F, -7.51F, 7.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-1.0139F, 0.0181F, -0.0138F, 1.5708F, 0.0F, 0.0F));

		PartDefinition left_wheel_r28 = Back_Right_Wheel.addOrReplaceChild("left_wheel_r28", CubeListBuilder.create().texOffs(276, 151).mirror().addBox(-2.25F, -1.3697F, -7.5204F, 7.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-1.0139F, 0.0181F, -0.0138F, 1.9548F, 0.0F, 0.0F));

		PartDefinition left_wheel_r29 = Back_Right_Wheel.addOrReplaceChild("left_wheel_r29", CubeListBuilder.create().texOffs(276, 151).mirror().addBox(-2.25F, -1.4379F, -7.5665F, 7.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-1.0139F, 0.0181F, -0.0138F, 2.3562F, 0.0F, 0.0F));

		PartDefinition left_wheel_r30 = Back_Right_Wheel.addOrReplaceChild("left_wheel_r30", CubeListBuilder.create().texOffs(276, 151).mirror().addBox(-2.25F, -1.5054F, -7.5864F, 7.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-1.0139F, 0.0181F, -0.0138F, 2.7489F, 0.0F, 0.0F));

		PartDefinition left_wheel_r31 = Back_Right_Wheel.addOrReplaceChild("left_wheel_r31", CubeListBuilder.create().texOffs(276, 151).mirror().addBox(-2.25F, -1.4811F, -7.5277F, 7.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-1.0139F, 0.0181F, -0.0138F, 1.1781F, 0.0F, 0.0F));

		PartDefinition left_wheel_r32 = Back_Right_Wheel.addOrReplaceChild("left_wheel_r32", CubeListBuilder.create().texOffs(276, 151).mirror().addBox(-2.25F, -1.3945F, -7.4619F, 7.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-1.0139F, 0.0181F, -0.0138F, 0.384F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 512, 512);
	}
    
	// ═══════════════════════════════════════════════════════════
	//  MODEL PART ACCESSORS  (required by BaseCarModel)
	// ═══════════════════════════════════════════════════════════
 
	@Override protected ModelPart body()             { return this.body; }
	@Override protected ModelPart frontLeftWheel()   { return this.Front_Left_Wheel; }
	@Override protected ModelPart frontRightWheel()  { return this.Front_Right_Wheel; }
	@Override protected ModelPart backLeftWheel()    { return this.Back_Left_Wheel; }
	@Override protected ModelPart backRightWheel()   { return this.Back_Right_Wheel; }
	@Override protected ModelPart steeringWheel()    { return this.steering_wheel; }
	@Override protected ModelPart shifter()          { return this.shifter; }
    @Override protected ModelPart bodykits()         {return null; }
    @Override protected ModelPart leftDoor()         {return null; }
    @Override protected ModelPart rightDoor()         {return null; }
    @Override protected ModelPart hood()         {return null; }

}