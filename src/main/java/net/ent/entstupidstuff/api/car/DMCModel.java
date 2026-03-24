package net.ent.entstupidstuff.api.car;

import net.ent.entstupidstuff.EntStupidStuff;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class DMCModel extends EntityModel<CarRenderState> {

    private static final float MAX_WHEEL_STEER_RAD = 0.4f;
    private static final float STEERING_WHEEL_MULTIPLIER = (float)(Math.PI * 3.5f);
    private static final float SHIFTER_MAX_TILT = 0.35f;
    private static final float BODY_ROLL_MAX = 0.15f;
    private static final float BODY_ROLL_LERP = 0.15f;
    private static final float STEER_WHEEL_LERP = 0.2f;
    private float currentSteerWheelRot = 0f;
    private float currentShifterRot = 0f;

    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
        ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "car"), "main"
    );

	private final ModelPart body;
	private final ModelPart steering_wheel;
	private final ModelPart shifter;
	private final ModelPart Front_Left_Wheel;
	private final ModelPart Front_Right_Wheel;
	private final ModelPart Back_Left_Wheel;
	private final ModelPart Back_Right_Wheel;

	public DMCModel(ModelPart root) {
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

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 196).addBox(16.6916F, -5.0035F, 17.0609F, 2.0F, 1.0F, 39.0F, new CubeDeformation(0.0F))
		.texOffs(0, 196).mirror().addBox(-19.3084F, -5.0035F, 17.0609F, 2.0F, 1.0F, 39.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(52, 243).addBox(16.6916F, -13.0035F, 36.0609F, 2.0F, 2.0F, 22.0F, new CubeDeformation(0.0F))
		.texOffs(0, 172).addBox(-17.3084F, -13.0035F, 36.0609F, 34.0F, 2.0F, 22.0F, new CubeDeformation(0.0F))
		.texOffs(52, 243).mirror().addBox(-19.3084F, -13.0035F, 36.0609F, 2.0F, 2.0F, 22.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(210, 185).addBox(-22.3084F, -0.0035F, 85.0609F, 44.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(112, 172).addBox(16.6916F, -12.0035F, 54.0609F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(148, 270).addBox(17.6916F, -11.0035F, 54.0609F, 0.0F, 7.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(148, 270).mirror().addBox(-18.3084F, -11.0035F, 54.0609F, 0.0F, 7.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(212, 245).addBox(16.6916F, -5.9035F, 56.0609F, 2.0F, 1.0F, 19.0F, new CubeDeformation(0.0F))
		.texOffs(212, 245).mirror().addBox(-19.3084F, -5.9035F, 56.0609F, 2.0F, 1.0F, 19.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(112, 172).mirror().addBox(-19.3084F, -12.0035F, 54.0609F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(0, 103).addBox(-18.3084F, -5.0035F, 60.0609F, 35.0F, 1.0F, 24.0F, new CubeDeformation(0.0F))
		.texOffs(104, 128).addBox(17.3416F, 2.9965F, 56.8109F, 5.0F, 8.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(240, 265).addBox(17.3416F, -2.0035F, 61.8109F, 5.0F, 2.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(104, 128).addBox(17.3416F, 2.9965F, 74.8109F, 5.0F, 8.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(104, 128).addBox(17.3416F, 2.9965F, -6.1891F, 5.0F, 8.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(240, 265).addBox(17.3416F, -2.0035F, -1.1891F, 5.0F, 2.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(104, 128).addBox(17.3416F, 2.9965F, 11.8109F, 5.0F, 8.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(104, 128).mirror().addBox(-22.9584F, 2.9965F, -6.1891F, 5.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(240, 265).mirror().addBox(-22.9584F, -2.0035F, -1.1891F, 5.0F, 2.0F, 10.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(104, 128).mirror().addBox(-22.9584F, 2.9965F, 11.8109F, 5.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(104, 128).mirror().addBox(-22.9584F, 2.9965F, 56.8109F, 5.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(240, 265).mirror().addBox(-22.9584F, -2.0035F, 61.8109F, 5.0F, 2.0F, 10.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(104, 128).mirror().addBox(-22.9584F, 2.9965F, 74.8109F, 5.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(126, 264).addBox(16.6916F, -13.0035F, 58.0609F, 2.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(126, 264).mirror().addBox(-19.3084F, -13.0035F, 58.0609F, 2.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(228, 229).addBox(-17.3084F, -13.0035F, 58.0609F, 34.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(228, 242).addBox(-17.3084F, -11.0035F, 67.0609F, 34.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(228, 242).addBox(-17.3084F, -12.0035F, 64.0609F, 34.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 138).addBox(-19.8084F, -4.0035F, 7.6609F, 39.0F, 2.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(228, 242).addBox(-17.3084F, -10.0035F, 70.0609F, 34.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(210, 194).addBox(-18.3084F, -8.0035F, 81.0609F, 36.0F, 2.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(214, 101).addBox(-18.2084F, -8.0035F, 75.0609F, 36.0F, 3.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(214, 121).addBox(-20.3084F, -7.0035F, 84.0609F, 40.0F, 8.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(82, 201).addBox(-4.3084F, 3.7465F, 15.0609F, 7.0F, 6.0F, 30.0F, new CubeDeformation(0.0F))
		.texOffs(210, 188).addBox(-20.3084F, -2.0035F, -12.9391F, 39.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-20.3084F, 8.9965F, 12.0609F, 38.0F, 1.0F, 45.0F, new CubeDeformation(0.0F))
		.texOffs(214, 110).addBox(-20.3084F, 0.9965F, 12.0609F, 38.0F, 8.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(270, 265).addBox(19.3916F, 1.9965F, -14.9391F, 3.0F, 4.0F, 9.0F, new CubeDeformation(0.0F))
		.texOffs(166, 0).addBox(19.1916F, 1.9965F, 13.0609F, 3.0F, 2.0F, 44.0F, new CubeDeformation(0.0F))
		.texOffs(28, 267).addBox(18.7916F, -0.0035F, 74.0609F, 3.0F, 2.0F, 11.0F, new CubeDeformation(0.0F))
		.texOffs(28, 267).mirror().addBox(-22.4084F, -0.0035F, 74.0609F, 3.0F, 2.0F, 11.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(1, 267).addBox(-18.4084F, 5.9965F, 76.0609F, 2.0F, 2.0F, 11.0F, new CubeDeformation(0.0F))
		.texOffs(1, 267).addBox(15.5916F, 5.9965F, 76.0609F, 2.0F, 2.0F, 11.0F, new CubeDeformation(0.0F))
		.texOffs(166, 0).mirror().addBox(-22.8084F, 1.9965F, 13.0609F, 3.0F, 2.0F, 44.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(270, 265).mirror().addBox(-23.0084F, 1.9965F, -14.9391F, 3.0F, 4.0F, 9.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(210, 168).addBox(-20.8084F, 1.9965F, -17.0391F, 41.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(186, 91).addBox(-20.8084F, 8.9965F, -14.2391F, 41.0F, 2.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(210, 150).addBox(-20.3084F, 5.9965F, -13.9391F, 40.0F, 3.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(100, 248).addBox(3.6916F, 5.7465F, 32.0609F, 10.0F, 4.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(98, 46).addBox(16.6916F, -4.0035F, 17.0609F, 1.0F, 14.0F, 43.0F, new CubeDeformation(0.0F))
		.texOffs(118, 103).addBox(14.6916F, -0.0035F, 15.0609F, 3.0F, 2.0F, 45.0F, new CubeDeformation(0.0F))
		.texOffs(156, 229).addBox(-15.3084F, -0.0035F, 53.0609F, 30.0F, 9.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(98, 46).mirror().addBox(-18.3084F, -4.0035F, 17.0609F, 1.0F, 14.0F, 43.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(118, 103).mirror().addBox(-18.3084F, -0.0035F, 15.0609F, 3.0F, 2.0F, 45.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(100, 248).addBox(-15.3084F, 5.7465F, 32.0609F, 10.0F, 4.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(228, 235).addBox(-17.3084F, 5.9965F, -12.9391F, 33.0F, 3.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(260, 34).addBox(-7.3084F, 6.4965F, 64.5609F, 14.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(76, 237).addBox(-16.3084F, 6.9965F, 65.0609F, 34.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(260, 34).addBox(-7.3084F, 6.4965F, 1.5609F, 14.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(76, 237).addBox(-16.3084F, 6.9965F, 2.0609F, 34.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(0, 150).addBox(-19.7834F, -4.0535F, -13.3391F, 39.0F, 1.0F, 21.0F, new CubeDeformation(0.0F)), PartPose.offset(0.3084F, 7.0035F, -31.0609F));

		PartDefinition cube_r1 = body.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(274, 80).addBox(-19.0F, -7.0F, -1.0F, 2.0F, 6.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(156, 214).addBox(-19.0F, -9.0F, -1.0F, 39.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(274, 80).addBox(18.0F, -7.0F, -1.0F, 2.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.8084F, 4.6465F, -15.9391F, -0.4014F, 0.0F, 0.0F));

		PartDefinition cube_r2 = body.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 236).addBox(-17.0F, -3.0F, -1.0F, 35.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.8084F, 4.7465F, -15.9891F, -0.4014F, 0.0F, 0.0F));

		PartDefinition cube_r3 = body.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(250, 201).addBox(-8.0F, -8.0F, -1.0F, 17.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.8084F, 5.2965F, -15.4391F, -0.4014F, 0.0F, 0.0F));

		PartDefinition cube_r4 = body.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(234, 219).addBox(-17.0F, -3.0F, -1.0F, 35.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.8084F, 0.1465F, -13.1391F, -0.4014F, 0.0F, 0.0F));

		PartDefinition cube_r5 = body.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(100, 244).addBox(-16.0F, -1.0F, -2.0F, 33.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.3084F, 7.9965F, -10.9391F, -0.4014F, 0.0F, 0.0F));

		PartDefinition cube_r6 = body.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(100, 264).addBox(1.0F, -10.0F, 5.0F, 10.0F, 17.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(100, 264).addBox(20.0F, -10.0F, 5.0F, 10.0F, 17.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-16.3084F, -1.2535F, 40.0609F, -0.4014F, 0.0F, 0.0F));

		PartDefinition cube_r7 = body.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(210, 176).addBox(-19.0F, 0.0F, -2.0F, 40.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.3084F, 3.9965F, -15.2391F, 0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r8 = body.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(274, 64).mirror().addBox(6.0F, 5.0F, -58.0F, 3.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(13.7916F, -3.0035F, 30.3109F, 0.0F, 0.7854F, 0.0F));

		PartDefinition cube_r9 = body.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(274, 64).addBox(-9.0F, 5.0F, -58.0F, 3.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-14.4084F, -3.0035F, 30.3109F, 0.0F, -0.7854F, 0.0F));

		PartDefinition left_door_r1 = body.addOrReplaceChild("left_door_r1", CubeListBuilder.create().texOffs(186, 46).mirror().addBox(-3.0F, 3.0F, -22.0F, 1.0F, 2.0F, 43.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-19.3084F, 4.9965F, 36.0609F, 0.0F, 0.0F, -0.4014F));

		PartDefinition right_door_r1 = body.addOrReplaceChild("right_door_r1", CubeListBuilder.create().texOffs(216, 265).addBox(-2.0F, -5.0F, 38.0F, 1.0F, 7.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-18.3084F, 4.9965F, 36.0609F, 0.0F, 0.0F, -0.384F));

		PartDefinition right_door_r2 = body.addOrReplaceChild("right_door_r2", CubeListBuilder.create().texOffs(186, 46).addBox(2.0F, 3.0F, -22.0F, 1.0F, 2.0F, 43.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(18.6916F, 4.9965F, 36.0609F, 0.0F, 0.0F, 0.4014F));

		PartDefinition right_side_r1 = body.addOrReplaceChild("right_side_r1", CubeListBuilder.create().texOffs(260, 16).mirror().addBox(-2.0F, -5.0F, 38.0F, 3.0F, 7.0F, 11.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-18.3084F, 4.9965F, 36.0609F, 0.0F, 0.0F, -0.4014F));

		PartDefinition right_side_r2 = body.addOrReplaceChild("right_side_r2", CubeListBuilder.create().texOffs(254, 245).mirror().addBox(-17.0F, 4.0F, 39.0F, 2.0F, 6.0F, 14.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(166, 265).mirror().addBox(-17.0F, 2.0F, 55.0F, 2.0F, 9.0F, 10.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(112, 182).mirror().addBox(-17.0F, 4.0F, 53.0F, 2.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.0084F, -3.0035F, 20.0609F, 0.0F, 0.0F, 0.384F));

		PartDefinition right_side_r3 = body.addOrReplaceChild("right_side_r3", CubeListBuilder.create().texOffs(0, 46).mirror().addBox(-2.0F, -4.0F, -24.0F, 3.0F, 9.0F, 46.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-19.0584F, 5.3465F, 36.0609F, 0.0F, 0.0F, -0.4014F));

		PartDefinition right_side_r4 = body.addOrReplaceChild("right_side_r4", CubeListBuilder.create().texOffs(120, 150).mirror().addBox(-15.0F, 5.0F, -3.0F, 2.0F, 8.0F, 43.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(260, 0).mirror().addBox(-15.0F, 6.0F, -25.4F, 3.0F, 3.0F, 13.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(126, 270).mirror().addBox(-15.0F, 6.0F, -33.4F, 3.0F, 7.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(98, 138).mirror().addBox(-15.0F, 11.0F, -9.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(56, 267).mirror().addBox(-15.0F, 6.0F, -13.0F, 2.0F, 5.0F, 10.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-3.7084F, -4.0035F, 20.0609F, 0.0F, 0.0F, 0.384F));

		PartDefinition right_side_r5 = body.addOrReplaceChild("right_side_r5", CubeListBuilder.create().texOffs(260, 16).addBox(-1.0F, -5.0F, 38.0F, 3.0F, 7.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(17.6916F, 4.9965F, 36.0609F, 0.0F, 0.0F, 0.4014F));

		PartDefinition right_side_r6 = body.addOrReplaceChild("right_side_r6", CubeListBuilder.create().texOffs(0, 46).addBox(-1.0F, -4.0F, -24.0F, 3.0F, 9.0F, 46.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(18.4416F, 5.3465F, 36.0609F, 0.0F, 0.0F, 0.4014F));

		PartDefinition cube_r10 = body.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(112, 191).mirror().addBox(-1.0F, -4.0F, -1.0F, 2.0F, 9.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-19.0084F, -4.0035F, 86.0609F, 0.0F, 0.0F, 0.384F));

		PartDefinition right_side_r7 = body.addOrReplaceChild("right_side_r7", CubeListBuilder.create().texOffs(166, 265).addBox(15.0F, 2.0F, 55.0F, 2.0F, 9.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(112, 182).addBox(15.0F, 4.0F, 53.0F, 2.0F, 7.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(254, 245).addBox(15.0F, 4.0F, 39.0F, 2.0F, 6.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.3916F, -3.0035F, 20.0609F, 0.0F, 0.0F, -0.384F));

		PartDefinition right_door_r3 = body.addOrReplaceChild("right_door_r3", CubeListBuilder.create().texOffs(98, 146).addBox(12.0F, 9.0F, -24.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.6916F, -4.0035F, 20.6609F, 0.0F, 0.0F, -0.384F));

		PartDefinition right_side_r8 = body.addOrReplaceChild("right_side_r8", CubeListBuilder.create().texOffs(80, 267).addBox(12.0F, 9.0F, -26.0F, 3.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(260, 0).addBox(12.0F, 6.0F, -26.0F, 3.0F, 3.0F, 13.0F, new CubeDeformation(0.0F))
		.texOffs(126, 270).addBox(12.0F, 6.0F, -34.0F, 3.0F, 7.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(98, 138).addBox(13.0F, 11.0F, -9.6F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(56, 267).addBox(13.0F, 6.0F, -13.6F, 2.0F, 5.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(120, 150).addBox(13.0F, 5.0F, -3.6F, 2.0F, 8.0F, 43.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0916F, -4.0035F, 20.6609F, 0.0F, 0.0F, -0.384F));

		PartDefinition cube_r11 = body.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(240, 214).addBox(2.0F, -4.0F, -12.0F, 34.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(190, 265).addBox(15.0F, -3.0F, -36.0F, 7.0F, 10.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(156, 219).addBox(0.0F, -4.0F, -34.0F, 36.0F, 7.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(0, 241).mirror().addBox(0.0F, -4.0F, -34.0F, 2.0F, 2.0F, 24.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(0, 241).addBox(36.0F, -4.0F, -34.0F, 2.0F, 2.0F, 24.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-19.3084F, -13.0035F, 47.0609F, 0.384F, 0.0F, 0.0F));

		PartDefinition cube_r12 = body.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(274, 58).addBox(-3.0F, -7.0F, -11.0F, 7.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.3084F, 6.7465F, 43.0609F, 0.384F, 0.0F, 0.0F));

		PartDefinition cube_r13 = body.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(156, 201).addBox(-37.0F, 1.0F, 3.0F, 38.0F, 4.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(17.6916F, -11.0035F, 70.0609F, -0.4014F, 0.0F, 0.0F));

		PartDefinition cube_r14 = body.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(168, 244).mirror().addBox(-1.0F, 1.0F, -8.0F, 2.0F, 1.0F, 20.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(168, 244).addBox(35.0F, 1.0F, -8.0F, 2.0F, 1.0F, 20.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-18.3084F, -10.7535F, 70.8109F, -0.4014F, 0.0F, 0.0F));

		PartDefinition cube_r15 = body.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(274, 42).mirror().addBox(-9.0F, -23.0F, 22.0F, 5.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(274, 42).addBox(31.3F, -23.0F, 22.0F, 5.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-13.9584F, -1.3035F, 40.0109F, -0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r16 = body.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(274, 42).mirror().addBox(-9.0F, -23.0F, -29.0F, 5.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(274, 42).addBox(31.3F, -23.0F, -29.0F, 5.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-13.9584F, -1.3035F, 93.6109F, 0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r17 = body.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(274, 42).mirror().addBox(-9.0F, -23.0F, 22.0F, 5.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(274, 42).addBox(31.3F, -23.0F, 22.0F, 5.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-13.9584F, -1.3035F, -22.9891F, -0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r18 = body.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(274, 42).mirror().addBox(-9.0F, -23.0F, -29.0F, 5.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(274, 42).addBox(31.3F, -23.0F, -29.0F, 5.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-13.9584F, -1.3035F, 30.6109F, 0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r19 = body.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(214, 130).addBox(-18.0F, 4.0F, -12.0F, 35.0F, 17.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.3084F, -4.0035F, 73.0609F, -0.4014F, 0.0F, 0.0F));

		PartDefinition cube_r20 = body.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(92, 275).mirror().addBox(-1.0F, -1.0F, 0.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(92, 275).addBox(35.0F, -1.0F, 0.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-18.3084F, -6.0035F, 54.0609F, -0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r21 = body.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(112, 172).mirror().addBox(-1.0F, -4.0F, -1.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(112, 172).addBox(35.0F, -4.0F, -1.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-18.3084F, -8.7535F, 59.0609F, 0.384F, 0.0F, 0.0F));

		PartDefinition cube_r22 = body.addOrReplaceChild("cube_r22", CubeListBuilder.create().texOffs(0, 128).addBox(-22.0F, -1.0F, -7.0F, 44.0F, 2.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.3084F, 5.0965F, 83.7609F, 0.384F, 0.0F, 0.0F));

		PartDefinition cube_r23 = body.addOrReplaceChild("cube_r23", CubeListBuilder.create().texOffs(210, 161).addBox(-21.0F, -1.0F, -1.0F, 44.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.3084F, 1.5965F, 85.7609F, -0.4014F, 0.0F, 0.0F));

		PartDefinition cube_r24 = body.addOrReplaceChild("cube_r24", CubeListBuilder.create().texOffs(82, 196).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(112, 191).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 9.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(18.3916F, -4.0035F, 86.0609F, 0.0F, 0.0F, -0.384F));

		PartDefinition steering_wheel = body.addOrReplaceChild("steering_wheel", CubeListBuilder.create().texOffs(114, 138).addBox(3.0F, -2.6071F, -0.5F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 101).addBox(-3.0F, 2.3929F, -0.5F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(80, 275).addBox(-3.0F, -2.8571F, 0.0F, 6.0F, 6.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(14, 101).addBox(-2.0F, -0.8571F, -0.5F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(114, 138).addBox(-4.0F, -2.6071F, -0.5F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 101).addBox(-3.0F, -3.6071F, -0.5F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(24, 101).addBox(-1.0F, 0.1429F, -0.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(108, 146).addBox(-1.0F, -0.8571F, -2.5F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(9.6916F, -0.3963F, 20.5609F, 0.0F, 0.3054F, -1.5708F));

		PartDefinition shifter = body.addOrReplaceChild("shifter", CubeListBuilder.create().texOffs(88, 196).addBox(-0.75F, -2.5F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(30, 101).addBox(-0.75F, -3.5F, -0.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.5584F, 4.2465F, 24.5609F));

		PartDefinition Front_Left_Wheel = body.addOrReplaceChild("Front_Left_Wheel", CubeListBuilder.create().texOffs(144, 248).addBox(0.0F, -5.7F, -5.61F, 0.0F, 11.0F, 11.0F, new CubeDeformation(0.0F))
		.texOffs(274, 72).addBox(-0.75F, -1.95F, -1.86F, 2.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(20.6916F, 7.6965F, 3.6709F));

		PartDefinition rim_r1 = Front_Left_Wheel.addOrReplaceChild("rim_r1", CubeListBuilder.create().texOffs(158, 270).mirror().addBox(1.0F, -0.2322F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(158, 270).addBox(1.0F, -4.2322F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 0.05F, 0.14F, -0.7854F, 0.0F, 0.0F));

		PartDefinition rim_r2 = Front_Left_Wheel.addOrReplaceChild("rim_r2", CubeListBuilder.create().texOffs(158, 270).mirror().addBox(1.0F, -2.0F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(158, 270).addBox(1.0F, -6.0F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 1.3F, 1.39F, 0.7854F, 0.0F, 0.0F));

		PartDefinition right_wheel_r1 = Front_Left_Wheel.addOrReplaceChild("right_wheel_r1", CubeListBuilder.create().texOffs(274, 51).addBox(-2.0F, -1.4697F, -7.5347F, 7.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 0.0F, 0.7854F, 0.0F, 0.0F));

		PartDefinition right_wheel_r2 = Front_Left_Wheel.addOrReplaceChild("right_wheel_r2", CubeListBuilder.create().texOffs(274, 51).addBox(-2.0F, -1.4114F, -7.5036F, 7.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 0.0F, -2.7576F, 0.0F, 0.0F));

		PartDefinition right_wheel_r3 = Front_Left_Wheel.addOrReplaceChild("right_wheel_r3", CubeListBuilder.create().texOffs(274, 51).addBox(-2.0F, -1.5F, -7.51F, 7.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 0.0F, 3.1416F, 0.0F, 0.0F));

		PartDefinition right_wheel_r4 = Front_Left_Wheel.addOrReplaceChild("right_wheel_r4", CubeListBuilder.create().texOffs(274, 51).addBox(-2.0F, -1.4697F, -7.5347F, 7.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 0.0F, -2.3562F, 0.0F, 0.0F));

		PartDefinition right_wheel_r5 = Front_Left_Wheel.addOrReplaceChild("right_wheel_r5", CubeListBuilder.create().texOffs(274, 51).addBox(-2.0F, -1.5226F, -7.5449F, 7.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 0.0F, -1.9635F, 0.0F, 0.0F));

		PartDefinition right_wheel_r6 = Front_Left_Wheel.addOrReplaceChild("right_wheel_r6", CubeListBuilder.create().texOffs(274, 51).addBox(-2.0F, -1.5F, -7.51F, 7.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 0.0F, -1.5708F, 0.0F, 0.0F));

		PartDefinition right_wheel_r7 = Front_Left_Wheel.addOrReplaceChild("right_wheel_r7", CubeListBuilder.create().texOffs(274, 51).addBox(-2.0F, -1.4114F, -7.5036F, 7.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 0.0F, -1.1868F, 0.0F, 0.0F));

		PartDefinition right_wheel_r8 = Front_Left_Wheel.addOrReplaceChild("right_wheel_r8", CubeListBuilder.create().texOffs(274, 51).addBox(-2.0F, -1.4697F, -7.5347F, 7.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 0.0F, -0.7854F, 0.0F, 0.0F));

		PartDefinition right_wheel_r9 = Front_Left_Wheel.addOrReplaceChild("right_wheel_r9", CubeListBuilder.create().texOffs(274, 51).addBox(-2.0F, -1.5226F, -7.5449F, 7.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 0.0F, -0.3927F, 0.0F, 0.0F));

		PartDefinition right_wheel_r10 = Front_Left_Wheel.addOrReplaceChild("right_wheel_r10", CubeListBuilder.create().texOffs(274, 51).addBox(-3.5F, -1.472F, -7.5999F, 7.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.5F, 0.0F, 0.0F, -0.0087F, 0.0F, 0.0F));

		PartDefinition right_wheel_r11 = Front_Left_Wheel.addOrReplaceChild("right_wheel_r11", CubeListBuilder.create().texOffs(274, 51).addBox(-2.0F, -1.5F, -7.51F, 7.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 0.0F, 1.5708F, 0.0F, 0.0F));

		PartDefinition right_wheel_r12 = Front_Left_Wheel.addOrReplaceChild("right_wheel_r12", CubeListBuilder.create().texOffs(274, 51).addBox(-2.0F, -1.4114F, -7.5036F, 7.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 0.0F, 1.9548F, 0.0F, 0.0F));

		PartDefinition right_wheel_r13 = Front_Left_Wheel.addOrReplaceChild("right_wheel_r13", CubeListBuilder.create().texOffs(274, 51).addBox(-2.0F, -1.4697F, -7.5347F, 7.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 0.0F, 2.3562F, 0.0F, 0.0F));

		PartDefinition right_wheel_r14 = Front_Left_Wheel.addOrReplaceChild("right_wheel_r14", CubeListBuilder.create().texOffs(274, 51).addBox(-2.0F, -1.5226F, -7.5449F, 7.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 0.0F, 2.7489F, 0.0F, 0.0F));

		PartDefinition right_wheel_r15 = Front_Left_Wheel.addOrReplaceChild("right_wheel_r15", CubeListBuilder.create().texOffs(274, 51).addBox(-2.0F, -1.5226F, -7.5449F, 7.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 0.0F, 1.1781F, 0.0F, 0.0F));

		PartDefinition right_wheel_r16 = Front_Left_Wheel.addOrReplaceChild("right_wheel_r16", CubeListBuilder.create().texOffs(274, 51).addBox(-2.0F, -1.4114F, -7.5036F, 7.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 0.0F, 0.384F, 0.0F, 0.0F));

		PartDefinition Front_Right_Wheel = body.addOrReplaceChild("Front_Right_Wheel", CubeListBuilder.create().texOffs(144, 248).mirror().addBox(0.0F, -5.7F, -5.61F, 0.0F, 11.0F, 11.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(274, 72).mirror().addBox(-1.25F, -1.95F, -1.86F, 2.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-21.3084F, 7.6965F, 3.6709F));

		PartDefinition rim_r3 = Front_Right_Wheel.addOrReplaceChild("rim_r3", CubeListBuilder.create().texOffs(158, 270).addBox(-3.0F, -0.2322F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(158, 270).mirror().addBox(-3.0F, -4.2322F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.0F, 0.05F, 0.14F, -0.7854F, 0.0F, 0.0F));

		PartDefinition rim_r4 = Front_Right_Wheel.addOrReplaceChild("rim_r4", CubeListBuilder.create().texOffs(158, 270).addBox(-3.0F, -2.0F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(158, 270).mirror().addBox(-3.0F, -6.0F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.0F, 1.3F, 1.39F, 0.7854F, 0.0F, 0.0F));

		PartDefinition left_wheel_r1 = Front_Right_Wheel.addOrReplaceChild("left_wheel_r1", CubeListBuilder.create().texOffs(274, 51).mirror().addBox(-5.0F, -1.4697F, -7.5347F, 7.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.0F, 0.0F, 0.0F, 0.7854F, 0.0F, 0.0F));

		PartDefinition left_wheel_r2 = Front_Right_Wheel.addOrReplaceChild("left_wheel_r2", CubeListBuilder.create().texOffs(274, 51).mirror().addBox(-5.0F, -1.4114F, -7.5036F, 7.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.0F, 0.0F, 0.0F, -2.7576F, 0.0F, 0.0F));

		PartDefinition left_wheel_r3 = Front_Right_Wheel.addOrReplaceChild("left_wheel_r3", CubeListBuilder.create().texOffs(274, 51).mirror().addBox(-5.0F, -1.5F, -7.51F, 7.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.0F, 0.0F, 0.0F, 3.1416F, 0.0F, 0.0F));

		PartDefinition left_wheel_r4 = Front_Right_Wheel.addOrReplaceChild("left_wheel_r4", CubeListBuilder.create().texOffs(274, 51).mirror().addBox(-5.0F, -1.4697F, -7.5347F, 7.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.0F, 0.0F, 0.0F, -2.3562F, 0.0F, 0.0F));

		PartDefinition left_wheel_r5 = Front_Right_Wheel.addOrReplaceChild("left_wheel_r5", CubeListBuilder.create().texOffs(274, 51).mirror().addBox(-5.0F, -1.5226F, -7.5449F, 7.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.0F, 0.0F, 0.0F, -1.9635F, 0.0F, 0.0F));

		PartDefinition left_wheel_r6 = Front_Right_Wheel.addOrReplaceChild("left_wheel_r6", CubeListBuilder.create().texOffs(274, 51).mirror().addBox(-5.0F, -1.5F, -7.51F, 7.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.0F, 0.0F, 0.0F, -1.5708F, 0.0F, 0.0F));

		PartDefinition left_wheel_r7 = Front_Right_Wheel.addOrReplaceChild("left_wheel_r7", CubeListBuilder.create().texOffs(274, 51).mirror().addBox(-5.0F, -1.4114F, -7.5036F, 7.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.0F, 0.0F, 0.0F, -1.1868F, 0.0F, 0.0F));

		PartDefinition left_wheel_r8 = Front_Right_Wheel.addOrReplaceChild("left_wheel_r8", CubeListBuilder.create().texOffs(274, 51).mirror().addBox(-5.0F, -1.4697F, -7.5347F, 7.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.0F, 0.0F, 0.0F, -0.7854F, 0.0F, 0.0F));

		PartDefinition left_wheel_r9 = Front_Right_Wheel.addOrReplaceChild("left_wheel_r9", CubeListBuilder.create().texOffs(274, 51).mirror().addBox(-5.0F, -1.5226F, -7.5449F, 7.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.0F, 0.0F, 0.0F, -0.3927F, 0.0F, 0.0F));

		PartDefinition left_wheel_r10 = Front_Right_Wheel.addOrReplaceChild("left_wheel_r10", CubeListBuilder.create().texOffs(274, 51).mirror().addBox(-3.5F, -1.472F, -7.5999F, 7.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(1.5F, 0.0F, 0.0F, -0.0087F, 0.0F, 0.0F));

		PartDefinition left_wheel_r11 = Front_Right_Wheel.addOrReplaceChild("left_wheel_r11", CubeListBuilder.create().texOffs(274, 51).mirror().addBox(-5.0F, -1.5F, -7.51F, 7.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.0F, 0.0F, 0.0F, 1.5708F, 0.0F, 0.0F));

		PartDefinition left_wheel_r12 = Front_Right_Wheel.addOrReplaceChild("left_wheel_r12", CubeListBuilder.create().texOffs(274, 51).mirror().addBox(-5.0F, -1.4114F, -7.5036F, 7.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.0F, 0.0F, 0.0F, 1.9548F, 0.0F, 0.0F));

		PartDefinition left_wheel_r13 = Front_Right_Wheel.addOrReplaceChild("left_wheel_r13", CubeListBuilder.create().texOffs(274, 51).mirror().addBox(-5.0F, -1.4697F, -7.5347F, 7.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.0F, 0.0F, 0.0F, 2.3562F, 0.0F, 0.0F));

		PartDefinition left_wheel_r14 = Front_Right_Wheel.addOrReplaceChild("left_wheel_r14", CubeListBuilder.create().texOffs(274, 51).mirror().addBox(-5.0F, -1.5226F, -7.5449F, 7.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.0F, 0.0F, 0.0F, 2.7489F, 0.0F, 0.0F));

		PartDefinition left_wheel_r15 = Front_Right_Wheel.addOrReplaceChild("left_wheel_r15", CubeListBuilder.create().texOffs(274, 51).mirror().addBox(-5.0F, -1.5226F, -7.5449F, 7.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.0F, 0.0F, 0.0F, 1.1781F, 0.0F, 0.0F));

		PartDefinition left_wheel_r16 = Front_Right_Wheel.addOrReplaceChild("left_wheel_r16", CubeListBuilder.create().texOffs(274, 51).mirror().addBox(-5.0F, -1.4114F, -7.5036F, 7.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.0F, 0.0F, 0.0F, 0.384F, 0.0F, 0.0F));

		PartDefinition Back_Left_Wheel = body.addOrReplaceChild("Back_Left_Wheel", CubeListBuilder.create().texOffs(144, 248).addBox(0.0F, -5.7F, -5.61F, 0.0F, 11.0F, 11.0F, new CubeDeformation(0.0F))
		.texOffs(274, 72).addBox(-0.75F, -1.95F, -1.86F, 2.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(20.6916F, 7.6965F, 66.6709F));

		PartDefinition rim_r5 = Back_Left_Wheel.addOrReplaceChild("rim_r5", CubeListBuilder.create().texOffs(158, 270).mirror().addBox(1.0F, -0.2322F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(158, 270).addBox(1.0F, -4.2322F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 0.05F, 0.14F, -0.7854F, 0.0F, 0.0F));

		PartDefinition rim_r6 = Back_Left_Wheel.addOrReplaceChild("rim_r6", CubeListBuilder.create().texOffs(158, 270).mirror().addBox(1.0F, -2.0F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(158, 270).addBox(1.0F, -6.0F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 1.3F, 1.39F, 0.7854F, 0.0F, 0.0F));

		PartDefinition right_wheel_r17 = Back_Left_Wheel.addOrReplaceChild("right_wheel_r17", CubeListBuilder.create().texOffs(274, 51).addBox(-2.0F, -1.4697F, -7.5347F, 7.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 0.0F, 0.7854F, 0.0F, 0.0F));

		PartDefinition right_wheel_r18 = Back_Left_Wheel.addOrReplaceChild("right_wheel_r18", CubeListBuilder.create().texOffs(274, 51).addBox(-2.0F, -1.4114F, -7.5036F, 7.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 0.0F, -2.7576F, 0.0F, 0.0F));

		PartDefinition right_wheel_r19 = Back_Left_Wheel.addOrReplaceChild("right_wheel_r19", CubeListBuilder.create().texOffs(274, 51).addBox(-2.0F, -1.5F, -7.51F, 7.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 0.0F, 3.1416F, 0.0F, 0.0F));

		PartDefinition right_wheel_r20 = Back_Left_Wheel.addOrReplaceChild("right_wheel_r20", CubeListBuilder.create().texOffs(274, 51).addBox(-2.0F, -1.4697F, -7.5347F, 7.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 0.0F, -2.3562F, 0.0F, 0.0F));

		PartDefinition right_wheel_r21 = Back_Left_Wheel.addOrReplaceChild("right_wheel_r21", CubeListBuilder.create().texOffs(274, 51).addBox(-2.0F, -1.5226F, -7.5449F, 7.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 0.0F, -1.9635F, 0.0F, 0.0F));

		PartDefinition right_wheel_r22 = Back_Left_Wheel.addOrReplaceChild("right_wheel_r22", CubeListBuilder.create().texOffs(274, 51).addBox(-2.0F, -1.5F, -7.51F, 7.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 0.0F, -1.5708F, 0.0F, 0.0F));

		PartDefinition right_wheel_r23 = Back_Left_Wheel.addOrReplaceChild("right_wheel_r23", CubeListBuilder.create().texOffs(274, 51).addBox(-2.0F, -1.4114F, -7.5036F, 7.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 0.0F, -1.1868F, 0.0F, 0.0F));

		PartDefinition right_wheel_r24 = Back_Left_Wheel.addOrReplaceChild("right_wheel_r24", CubeListBuilder.create().texOffs(274, 51).addBox(-2.0F, -1.4697F, -7.5347F, 7.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 0.0F, -0.7854F, 0.0F, 0.0F));

		PartDefinition right_wheel_r25 = Back_Left_Wheel.addOrReplaceChild("right_wheel_r25", CubeListBuilder.create().texOffs(274, 51).addBox(-2.0F, -1.5226F, -7.5449F, 7.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 0.0F, -0.3927F, 0.0F, 0.0F));

		PartDefinition right_wheel_r26 = Back_Left_Wheel.addOrReplaceChild("right_wheel_r26", CubeListBuilder.create().texOffs(274, 51).addBox(-3.5F, -1.472F, -7.5999F, 7.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.5F, 0.0F, 0.0F, -0.0087F, 0.0F, 0.0F));

		PartDefinition right_wheel_r27 = Back_Left_Wheel.addOrReplaceChild("right_wheel_r27", CubeListBuilder.create().texOffs(274, 51).addBox(-2.0F, -1.5F, -7.51F, 7.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 0.0F, 1.5708F, 0.0F, 0.0F));

		PartDefinition right_wheel_r28 = Back_Left_Wheel.addOrReplaceChild("right_wheel_r28", CubeListBuilder.create().texOffs(274, 51).addBox(-2.0F, -1.4114F, -7.5036F, 7.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 0.0F, 1.9548F, 0.0F, 0.0F));

		PartDefinition right_wheel_r29 = Back_Left_Wheel.addOrReplaceChild("right_wheel_r29", CubeListBuilder.create().texOffs(274, 51).addBox(-2.0F, -1.4697F, -7.5347F, 7.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 0.0F, 2.3562F, 0.0F, 0.0F));

		PartDefinition right_wheel_r30 = Back_Left_Wheel.addOrReplaceChild("right_wheel_r30", CubeListBuilder.create().texOffs(274, 51).addBox(-2.0F, -1.5226F, -7.5449F, 7.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 0.0F, 2.7489F, 0.0F, 0.0F));

		PartDefinition right_wheel_r31 = Back_Left_Wheel.addOrReplaceChild("right_wheel_r31", CubeListBuilder.create().texOffs(274, 51).addBox(-2.0F, -1.5226F, -7.5449F, 7.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 0.0F, 1.1781F, 0.0F, 0.0F));

		PartDefinition right_wheel_r32 = Back_Left_Wheel.addOrReplaceChild("right_wheel_r32", CubeListBuilder.create().texOffs(274, 51).addBox(-2.0F, -1.4114F, -7.5036F, 7.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 0.0F, 0.384F, 0.0F, 0.0F));

		PartDefinition Back_Right_Wheel = body.addOrReplaceChild("Back_Right_Wheel", CubeListBuilder.create().texOffs(144, 248).mirror().addBox(0.0F, -5.7F, -5.61F, 0.0F, 11.0F, 11.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(274, 72).mirror().addBox(-1.25F, -1.95F, -1.86F, 2.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-21.3084F, 7.6965F, 66.6709F));

		PartDefinition rim_r7 = Back_Right_Wheel.addOrReplaceChild("rim_r7", CubeListBuilder.create().texOffs(158, 270).addBox(-3.0F, -0.2322F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(158, 270).mirror().addBox(-3.0F, -4.2322F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.0F, 0.05F, 0.14F, -0.7854F, 0.0F, 0.0F));

		PartDefinition rim_r8 = Back_Right_Wheel.addOrReplaceChild("rim_r8", CubeListBuilder.create().texOffs(158, 270).addBox(-3.0F, -2.0F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(158, 270).mirror().addBox(-3.0F, -6.0F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.0F, 1.3F, 1.39F, 0.7854F, 0.0F, 0.0F));

		PartDefinition left_wheel_r17 = Back_Right_Wheel.addOrReplaceChild("left_wheel_r17", CubeListBuilder.create().texOffs(274, 51).mirror().addBox(-5.0F, -1.4697F, -7.5347F, 7.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.0F, 0.0F, 0.0F, 0.7854F, 0.0F, 0.0F));

		PartDefinition left_wheel_r18 = Back_Right_Wheel.addOrReplaceChild("left_wheel_r18", CubeListBuilder.create().texOffs(274, 51).mirror().addBox(-5.0F, -1.4114F, -7.5036F, 7.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.0F, 0.0F, 0.0F, -2.7576F, 0.0F, 0.0F));

		PartDefinition left_wheel_r19 = Back_Right_Wheel.addOrReplaceChild("left_wheel_r19", CubeListBuilder.create().texOffs(274, 51).mirror().addBox(-5.0F, -1.5F, -7.51F, 7.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.0F, 0.0F, 0.0F, 3.1416F, 0.0F, 0.0F));

		PartDefinition left_wheel_r20 = Back_Right_Wheel.addOrReplaceChild("left_wheel_r20", CubeListBuilder.create().texOffs(274, 51).mirror().addBox(-5.0F, -1.4697F, -7.5347F, 7.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.0F, 0.0F, 0.0F, -2.3562F, 0.0F, 0.0F));

		PartDefinition left_wheel_r21 = Back_Right_Wheel.addOrReplaceChild("left_wheel_r21", CubeListBuilder.create().texOffs(274, 51).mirror().addBox(-5.0F, -1.5226F, -7.5449F, 7.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.0F, 0.0F, 0.0F, -1.9635F, 0.0F, 0.0F));

		PartDefinition left_wheel_r22 = Back_Right_Wheel.addOrReplaceChild("left_wheel_r22", CubeListBuilder.create().texOffs(274, 51).mirror().addBox(-5.0F, -1.5F, -7.51F, 7.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.0F, 0.0F, 0.0F, -1.5708F, 0.0F, 0.0F));

		PartDefinition left_wheel_r23 = Back_Right_Wheel.addOrReplaceChild("left_wheel_r23", CubeListBuilder.create().texOffs(274, 51).mirror().addBox(-5.0F, -1.4114F, -7.5036F, 7.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.0F, 0.0F, 0.0F, -1.1868F, 0.0F, 0.0F));

		PartDefinition left_wheel_r24 = Back_Right_Wheel.addOrReplaceChild("left_wheel_r24", CubeListBuilder.create().texOffs(274, 51).mirror().addBox(-5.0F, -1.4697F, -7.5347F, 7.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.0F, 0.0F, 0.0F, -0.7854F, 0.0F, 0.0F));

		PartDefinition left_wheel_r25 = Back_Right_Wheel.addOrReplaceChild("left_wheel_r25", CubeListBuilder.create().texOffs(274, 51).mirror().addBox(-5.0F, -1.5226F, -7.5449F, 7.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.0F, 0.0F, 0.0F, -0.3927F, 0.0F, 0.0F));

		PartDefinition left_wheel_r26 = Back_Right_Wheel.addOrReplaceChild("left_wheel_r26", CubeListBuilder.create().texOffs(274, 51).mirror().addBox(-3.5F, -1.472F, -7.5999F, 7.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(1.5F, 0.0F, 0.0F, -0.0087F, 0.0F, 0.0F));

		PartDefinition left_wheel_r27 = Back_Right_Wheel.addOrReplaceChild("left_wheel_r27", CubeListBuilder.create().texOffs(274, 51).mirror().addBox(-5.0F, -1.5F, -7.51F, 7.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.0F, 0.0F, 0.0F, 1.5708F, 0.0F, 0.0F));

		PartDefinition left_wheel_r28 = Back_Right_Wheel.addOrReplaceChild("left_wheel_r28", CubeListBuilder.create().texOffs(274, 51).mirror().addBox(-5.0F, -1.4114F, -7.5036F, 7.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.0F, 0.0F, 0.0F, 1.9548F, 0.0F, 0.0F));

		PartDefinition left_wheel_r29 = Back_Right_Wheel.addOrReplaceChild("left_wheel_r29", CubeListBuilder.create().texOffs(274, 51).mirror().addBox(-5.0F, -1.4697F, -7.5347F, 7.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.0F, 0.0F, 0.0F, 2.3562F, 0.0F, 0.0F));

		PartDefinition left_wheel_r30 = Back_Right_Wheel.addOrReplaceChild("left_wheel_r30", CubeListBuilder.create().texOffs(274, 51).mirror().addBox(-5.0F, -1.5226F, -7.5449F, 7.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.0F, 0.0F, 0.0F, 2.7489F, 0.0F, 0.0F));

		PartDefinition left_wheel_r31 = Back_Right_Wheel.addOrReplaceChild("left_wheel_r31", CubeListBuilder.create().texOffs(274, 51).mirror().addBox(-5.0F, -1.5226F, -7.5449F, 7.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.0F, 0.0F, 0.0F, 1.1781F, 0.0F, 0.0F));

		PartDefinition left_wheel_r32 = Back_Right_Wheel.addOrReplaceChild("left_wheel_r32", CubeListBuilder.create().texOffs(274, 51).mirror().addBox(-5.0F, -1.4114F, -7.5036F, 7.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.0F, 0.0F, 0.0F, 0.384F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 512, 512);
	}

	@Override
    public void setupAnim(CarRenderState state) {

        //1. WHEEL SPIN

        if (state.forwardSpeed > 0.01) {
            float spinRad = (float) Math.toRadians(state.wheelSpin);
            this.Front_Left_Wheel.xRot  = spinRad;
            this.Front_Right_Wheel.xRot = spinRad;
            this.Back_Left_Wheel.xRot   = spinRad;
            this.Back_Right_Wheel.xRot  = spinRad;
        } else {
             float spinRad = -(float) Math.toRadians(state.wheelSpin);
            this.Front_Left_Wheel.xRot  = spinRad;
            this.Front_Right_Wheel.xRot = spinRad;
            this.Back_Left_Wheel.xRot   = spinRad;
            this.Back_Right_Wheel.xRot  = spinRad;
        }

        // 2. FRONT WHEEL STEERING

        //float wheelSteer = -state.steerInput * MAX_WHEEL_STEER_RAD;
        float wheelSteer = state.steerInput * MAX_WHEEL_STEER_RAD;
        this.Front_Left_Wheel.yRot  = wheelSteer;
        this.Front_Right_Wheel.yRot = wheelSteer;

        // 3. STEERING WHEEL

        float targetSteerWheelRot = state.steerInput * STEERING_WHEEL_MULTIPLIER;
        currentSteerWheelRot = Mth.lerp(STEER_WHEEL_LERP, currentSteerWheelRot, targetSteerWheelRot);
        //this.steering_wheel.yRot = currentSteerWheelRot;
        this.steering_wheel.zRot = currentSteerWheelRot;

        // 4. GEAR SHIFTER

        float targetShifterRot;
        if (state.forwardSpeed > 0.01f) {
            // Forward — lean toward dashboard
            targetShifterRot = -SHIFTER_MAX_TILT * Mth.clamp(state.forwardSpeed / 0.3f, 0f, 1f);
        } else if (state.forwardSpeed < -0.01f) {
            // Reversing — pull back
            targetShifterRot =  SHIFTER_MAX_TILT;
        } else {
            // At rest — return to neutral
            targetShifterRot = 0f;
        }
        currentShifterRot = Mth.lerp(0.1f, currentShifterRot, targetShifterRot);
        this.shifter.xRot = currentShifterRot;

        // 5. BODY ROLL

        float targetRoll = Mth.clamp(state.lateralVelocity * -0.8f, -BODY_ROLL_MAX, BODY_ROLL_MAX);
        this.body.yRot = Mth.lerp(BODY_ROLL_LERP, this.body.yRot, targetRoll);
 
        if (state.isDrifting) {
            this.body.yRot += Mth.sin(state.ageInTicks * 0.3f) * 0.025f;
        }


    }
}
