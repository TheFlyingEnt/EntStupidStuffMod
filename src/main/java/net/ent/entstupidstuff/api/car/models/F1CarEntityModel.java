package net.ent.entstupidstuff.api.car.models;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.api.car.render.F1CarRenderState;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.ResourceLocation;

public class F1CarEntityModel extends BaseCarEntityModel<F1CarRenderState> {

    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
        ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "f1car"), "main"
    );

	private final ModelPart body;
	private final ModelPart steering_wheel;
	private final ModelPart shifter;
	private final ModelPart Front_Left_Wheel;
	private final ModelPart Front_Right_Wheel;
	private final ModelPart Back_Left_Wheel;
	private final ModelPart Back_Right_Wheel;

	public F1CarEntityModel(ModelPart root) {
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

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.2992F, 10.2349F, 6.854F));

		PartDefinition updated_body = body.addOrReplaceChild("updated_body", CubeListBuilder.create().texOffs(226, 270).addBox(14.0F, -10.0F, -47.0F, 1.0F, 5.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(44, 234).addBox(14.0F, -8.0F, -52.0F, 5.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(192, 63).addBox(-16.673F, -4.0F, -7.9838F, 6.0F, 1.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(40, 211).addBox(-16.673F, -6.0F, -7.9838F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(78, 115).addBox(-6.0F, -5.0F, -15.9838F, 13.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(170, 220).addBox(-4.0F, -14.0F, -16.9838F, 8.0F, 10.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(78, 107).addBox(-7.0F, -5.0F, -7.9838F, 15.0F, 1.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(186, 284).addBox(9.5F, -14.01F, 1.0F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(206, 113).addBox(0.5F, -11.0F, 35.5F, 12.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(70, 42).addBox(-9.5F, -9.3F, 37.5F, 19.0F, 1.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(0, 83).addBox(-1.0F, -7.0F, -58.0F, 2.0F, 1.0F, 21.0F, new CubeDeformation(0.0F))
		.texOffs(230, 100).addBox(-4.0F, -11.0F, -37.0F, 8.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(182, 21).addBox(-6.0F, -10.0F, -44.0F, 12.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(98, 129).addBox(14.0F, -5.0F, -54.05F, 1.0F, 1.0F, 14.0F, new CubeDeformation(0.0F))
		.texOffs(52, 135).addBox(15.0F, -5.0F, -54.05F, 2.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(28, 172).addBox(17.0F, -6.0F, -54.05F, 1.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(164, 248).addBox(19.0F, -5.0F, -54.05F, 1.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(208, 25).addBox(18.0F, -6.0F, -54.05F, 2.0F, 1.0F, 9.0F, new CubeDeformation(0.0F))
		.texOffs(108, 212).addBox(-3.0F, -5.0F, -56.25F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(128, 129).addBox(-15.0F, -5.0F, -54.05F, 1.0F, 1.0F, 14.0F, new CubeDeformation(0.0F))
		.texOffs(238, 270).addBox(-15.0F, -10.0F, -47.0F, 1.0F, 5.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(238, 46).addBox(-19.0F, -8.0F, -52.0F, 5.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(222, 147).addBox(-17.0F, -5.0F, -54.05F, 2.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(182, 248).addBox(-18.0F, -6.0F, -54.05F, 1.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(208, 92).addBox(-20.0F, -6.0F, -54.05F, 2.0F, 1.0F, 9.0F, new CubeDeformation(0.0F))
		.texOffs(200, 248).addBox(-20.0F, -5.0F, -54.05F, 1.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(256, 180).addBox(-3.4258F, -11.0F, -38.507F, 7.0F, 5.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(254, 46).addBox(-16.5F, -14.0F, -5.0F, 2.0F, 1.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(52, 181).addBox(-14.5F, -14.0F, -5.0F, 4.0F, 1.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(242, 198).addBox(-16.5F, -13.0F, -5.0F, 2.0F, 3.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(284, 247).addBox(-11.5F, -14.01F, 1.0F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(216, 35).addBox(10.5F, -14.0F, -5.0F, 4.0F, 1.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(208, 162).addBox(-12.5F, -11.0F, 35.5F, 12.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(158, 255).addBox(-1.0F, -7.0F, -57.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(234, 280).addBox(-20.4F, -9.5F, -21.0F, 1.0F, 6.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(126, 215).addBox(-11.487F, -4.0F, -16.9776F, 6.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(96, 145).addBox(-20.4F, -5.0F, -21.0F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(266, 151).addBox(-20.4F, -4.0F, -21.0F, 5.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(256, 109).addBox(14.5F, -14.0F, -5.0F, 2.0F, 1.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(226, 144).addBox(5.487F, -4.0F, -16.9776F, 6.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(274, 199).addBox(15.4F, -4.0F, -21.0F, 5.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(242, 280).addBox(19.4F, -9.5F, -21.0F, 1.0F, 6.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(84, 224).addBox(15.673F, -6.0F, -7.9838F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(198, 191).addBox(10.673F, -4.0F, -7.9838F, 6.0F, 1.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(100, 246).addBox(-3.0F, -16.1F, -22.3F, 6.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(264, 0).addBox(-3.5742F, -11.0F, -38.507F, 7.0F, 5.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(120, 115).addBox(-2.0F, -20.0F, 1.0F, 4.0F, 1.0F, 13.0F, new CubeDeformation(0.0F))
		.texOffs(146, 144).addBox(-3.0F, -22.0F, 1.0F, 1.0F, 2.0F, 13.0F, new CubeDeformation(0.0F))
		.texOffs(52, 190).addBox(-3.0F, -20.0F, 7.0F, 6.0F, 2.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(150, 0).addBox(2.0F, -22.0F, 1.0F, 1.0F, 2.0F, 13.0F, new CubeDeformation(0.0F))
		.texOffs(38, 121).addBox(-2.0F, -23.0F, 1.0F, 4.0F, 1.0F, 13.0F, new CubeDeformation(0.0F))
		.texOffs(286, 86).addBox(-2.0F, -22.0F, 13.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(284, 255).addBox(-2.0F, -22.0F, 4.0F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(258, 68).addBox(-0.5F, -24.0F, 2.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(276, 237).addBox(-1.5F, -25.0F, 1.0F, 3.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(286, 203).addBox(-14.5649F, -16.5F, -15.9442F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(286, 273).addBox(11.5649F, -16.5F, -15.9442F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(222, 199).addBox(-6.55F, -14.99F, -10.0F, 2.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(88, 223).addBox(4.55F, -14.99F, -10.0F, 2.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(172, 205).addBox(1.0F, -18.0F, -1.0F, 7.0F, 13.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(206, 6).addBox(-8.0F, -18.0F, -1.0F, 7.0F, 13.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(258, 54).addBox(-8.0F, -18.0F, -2.0F, 5.0F, 13.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(252, 259).addBox(3.0F, -18.0F, -2.0F, 5.0F, 13.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(102, 177).addBox(-1.0F, -18.0F, -1.0F, 2.0F, 13.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(46, 83).addBox(-1.0F, -11.0F, 35.0F, 2.0F, 3.0F, 19.0F, new CubeDeformation(0.0F))
		.texOffs(238, 22).addBox(-3.0F, -19.0F, -1.0F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(120, 0).addBox(-14.0F, -22.0F, 40.0F, 1.0F, 5.0F, 14.0F, new CubeDeformation(0.0F))
		.texOffs(120, 19).addBox(13.0F, -22.0F, 40.0F, 1.0F, 5.0F, 14.0F, new CubeDeformation(0.0F))
		.texOffs(70, 38).addBox(-13.0F, -22.0F, 51.0F, 26.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(174, 191).addBox(8.7068F, -12.2955F, 42.0F, 1.0F, 3.0F, 11.0F, new CubeDeformation(0.0F))
		.texOffs(70, 50).addBox(-9.0F, -10.5455F, 51.0F, 18.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(192, 36).addBox(-9.7068F, -12.2955F, 42.0F, 1.0F, 3.0F, 11.0F, new CubeDeformation(0.0F))
		.texOffs(0, 52).addBox(-13.0F, -20.0F, 40.0F, 26.0F, 1.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(178, 181).addBox(-3.0F, -15.5F, 26.0F, 6.0F, 1.0F, 9.0F, new CubeDeformation(0.0F))
		.texOffs(0, 105).addBox(-1.0F, -13.5F, 28.0F, 2.0F, 2.0F, 17.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.2992F, 13.7651F, -6.854F));

		PartDefinition cube_r1 = updated_body.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(178, 103).addBox(0.0F, 0.0F, -2.0F, 0.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -19.95F, 33.25F, 0.2793F, 0.0F, 0.0F));

		PartDefinition cube_r2 = updated_body.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(18, 211).addBox(-0.0555F, -0.1061F, 2.0F, 2.0F, 1.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.3F, -14.05F, 24.0F, 0.0F, 0.0F, -0.7854F));

		PartDefinition cube_r3 = updated_body.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(210, 117).addBox(-10.0555F, 0.8939F, 9.0F, 11.0F, 7.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(122, 93).addBox(-10.0555F, 0.8939F, 5.0F, 7.0F, 7.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.3939F, -14.15F, 24.0F, 0.0F, 0.0F, -1.5708F));

		PartDefinition cube_r4 = updated_body.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(202, 287).addBox(0.0F, -0.5F, 6.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(144, 247).addBox(0.0F, -0.5F, 0.0F, 4.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, -4.0945F, 38.0F, 0.0F, 0.576F, -1.5708F));

		PartDefinition cube_r5 = updated_body.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(288, 114).addBox(0.0F, 3.0F, 4.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(288, 17).addBox(0.0F, 3.0F, 3.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(62, 287).addBox(0.0F, 3.0F, 1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(56, 287).addBox(0.0F, 0.0F, 6.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(74, 270).addBox(0.0F, 0.0F, 0.0F, 1.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -4.0945F, 38.0F, -0.4305F, 0.3953F, -2.4437F));

		PartDefinition cube_r6 = updated_body.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(288, 112).addBox(-1.0F, 3.0F, 4.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(288, 14).addBox(-1.0F, 3.0F, 3.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(42, 287).addBox(-1.0F, 3.0F, 1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(36, 287).addBox(-1.0F, 0.0F, 6.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(270, 54).addBox(-1.0F, 0.0F, 0.0F, 1.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -4.0945F, 38.0F, -0.4305F, -0.3953F, 2.4437F));

		PartDefinition cube_r7 = updated_body.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(122, 83).addBox(0.0555F, -0.1061F, 2.0F, 10.0F, 1.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.3F, -14.15F, 24.0F, 0.0F, 0.0F, 1.5708F));

		PartDefinition cube_r8 = updated_body.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(122, 42).addBox(-10.0555F, -0.1061F, 2.0F, 10.0F, 1.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.3F, -14.15F, 24.0F, 0.0F, 0.0F, -1.5708F));

		PartDefinition cube_r9 = updated_body.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(44, 210).addBox(-1.9445F, -0.1061F, 2.0F, 2.0F, 1.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.3F, -14.05F, 24.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition cube_r10 = updated_body.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(66, 52).addBox(-13.0F, -0.5F, 0.0F, 26.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -19.3F, 45.7F, 0.3927F, 0.0F, 0.0F));

		PartDefinition cube_r11 = updated_body.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(174, 186).addBox(-0.0083F, -6.9909F, 14.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(98, 144).addBox(-0.0083F, -6.9909F, 2.0F, 1.0F, 7.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-9.5F, -11.65F, 39.0F, 0.0F, 0.0F, -0.6981F));

		PartDefinition cube_r12 = updated_body.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(160, 47).addBox(-0.9917F, -6.9909F, 14.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(26, 135).addBox(-0.9917F, -6.9909F, 2.0F, 1.0F, 7.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(9.5F, -11.65F, 39.0F, 0.0F, 0.0F, 0.6981F));

		PartDefinition cube_r13 = updated_body.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(52, 274).addBox(0.0F, 2.2F, 0.0F, 1.0F, 10.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(46, 154).addBox(-5.0F, 2.2F, 0.0F, 1.0F, 10.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, -22.0F, 41.0F, -0.3927F, 0.0F, 0.0F));

		PartDefinition cube_r14 = updated_body.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(202, 278).addBox(0.0F, -0.0233F, -1.9866F, 0.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -19.95F, 31.25F, -0.0087F, 0.0F, 0.0F));

		PartDefinition cube_r15 = updated_body.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(190, 278).addBox(1.99F, 1.0F, 0.0F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(278, 121).addBox(-2.99F, 1.0F, 0.0F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -22.9F, 14.4F, -0.4276F, 0.0F, 0.0F));

		PartDefinition cube_r16 = updated_body.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(0, 172).addBox(0.0F, 0.0F, 0.0F, 2.0F, 2.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -23.0F, 14.0F, -0.306F, 0.2311F, 0.7603F));

		PartDefinition cube_r17 = updated_body.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(46, 167).addBox(-2.0F, 0.0F, 0.0F, 2.0F, 2.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -23.0F, 14.0F, -0.306F, -0.2311F, -0.7603F));

		PartDefinition cube_r18 = updated_body.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(190, 82).addBox(-3.0F, -0.0028F, -0.0669F, 6.0F, 3.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -18.0F, 27.3F, -0.384F, 0.0F, 0.0F));

		PartDefinition cube_r19 = updated_body.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(38, 105).addBox(-3.0F, 2.0F, 0.0F, 6.0F, 2.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -23.0F, 14.0F, -0.2182F, 0.0F, 0.0F));

		PartDefinition cube_r20 = updated_body.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(252, 163).addBox(-1.0F, -0.0208F, -0.0214F, 1.0F, 4.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -21.7F, 20.9F, -0.5717F, -0.1176F, 0.0754F));

		PartDefinition cube_r21 = updated_body.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(114, 252).addBox(0.0F, -0.0208F, -0.0214F, 1.0F, 4.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -21.7F, 20.9F, -0.5717F, 0.1176F, -0.0754F));

		PartDefinition cube_r22 = updated_body.addOrReplaceChild("cube_r22", CubeListBuilder.create().texOffs(28, 181).addBox(0.0F, 0.0F, 6.0F, 0.0F, 4.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(224, 4).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 4.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -23.0F, 14.0F, -0.1833F, 0.0F, 0.0F));

		PartDefinition cube_r23 = updated_body.addOrReplaceChild("cube_r23", CubeListBuilder.create().texOffs(30, 287).addBox(0.0F, 0.0F, 0.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -19.0F, 2.5F, 0.0F, 0.0F, -0.3927F));

		PartDefinition cube_r24 = updated_body.addOrReplaceChild("cube_r24", CubeListBuilder.create().texOffs(230, 180).addBox(0.0F, -3.0F, 0.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4F, -19.25F, 1.5F, 0.0F, 0.0F, -0.3927F));

		PartDefinition cube_r25 = updated_body.addOrReplaceChild("cube_r25", CubeListBuilder.create().texOffs(84, 228).addBox(-1.0F, -3.0F, 0.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.4F, -19.25F, 1.5F, 0.0F, 0.0F, 0.3927F));

		PartDefinition cube_r26 = updated_body.addOrReplaceChild("cube_r26", CubeListBuilder.create().texOffs(24, 287).addBox(-1.0F, 0.0F, 0.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -19.0F, 2.5F, 0.0F, 0.0F, 0.3927F));

		PartDefinition cube_r27 = updated_body.addOrReplaceChild("cube_r27", CubeListBuilder.create().texOffs(164, 68).addBox(-1.0F, -1.0F, -2.0F, 1.0F, 1.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, -19.0F, 3.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition cube_r28 = updated_body.addOrReplaceChild("cube_r28", CubeListBuilder.create().texOffs(164, 54).addBox(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, -20.0F, 1.0F, 0.0F, 0.0F, -0.7854F));

		PartDefinition cube_r29 = updated_body.addOrReplaceChild("cube_r29", CubeListBuilder.create().texOffs(164, 40).addBox(-1.0F, 0.0F, 0.0F, 1.0F, 1.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, -20.0F, 1.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition cube_r30 = updated_body.addOrReplaceChild("cube_r30", CubeListBuilder.create().texOffs(96, 163).addBox(0.0F, -1.0F, -2.0F, 1.0F, 1.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, -19.0F, 3.0F, 0.0F, 0.0F, -0.7854F));

		PartDefinition cube_r31 = updated_body.addOrReplaceChild("cube_r31", CubeListBuilder.create().texOffs(146, 159).addBox(-1.0F, -1.0F, 0.0F, 1.0F, 1.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, -22.0F, 1.0F, 0.0F, 0.0F, -0.7854F));

		PartDefinition cube_r32 = updated_body.addOrReplaceChild("cube_r32", CubeListBuilder.create().texOffs(158, 125).addBox(0.0F, 0.0F, -2.0F, 1.0F, 1.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, -23.0F, 3.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition cube_r33 = updated_body.addOrReplaceChild("cube_r33", CubeListBuilder.create().texOffs(154, 111).addBox(-1.0F, 0.0F, -2.0F, 1.0F, 1.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, -23.0F, 3.0F, 0.0F, 0.0F, -0.7854F));

		PartDefinition cube_r34 = updated_body.addOrReplaceChild("cube_r34", CubeListBuilder.create().texOffs(274, 29).addBox(-0.0155F, 0.0F, 5.9263F, 5.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(146, 200).addBox(-0.0155F, 0.0F, -1.0737F, 6.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.5F, -18.0F, 18.9F, -0.2537F, -0.1556F, 1.0322F));

		PartDefinition cube_r35 = updated_body.addOrReplaceChild("cube_r35", CubeListBuilder.create().texOffs(270, 63).addBox(-4.9845F, 0.0F, 5.9263F, 5.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(200, 139).addBox(-5.9845F, 0.0F, -1.0737F, 6.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.5F, -18.0F, 18.9F, -0.2537F, 0.1556F, -1.0322F));

		PartDefinition cube_r36 = updated_body.addOrReplaceChild("cube_r36", CubeListBuilder.create().texOffs(44, 200).addBox(0.0155F, -1.0F, -0.0737F, 3.0F, 1.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.5F, -17.0F, 18.9F, 0.0F, 0.2967F, 0.0F));

		PartDefinition cube_r37 = updated_body.addOrReplaceChild("cube_r37", CubeListBuilder.create().texOffs(108, 227).addBox(0.0F, 0.0F, -1.0F, 5.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(286, 280).addBox(2.0F, 0.0F, 2.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(8.0F, -18.0F, -1.0F, 0.0F, 0.0F, 1.021F));

		PartDefinition cube_r38 = updated_body.addOrReplaceChild("cube_r38", CubeListBuilder.create().texOffs(286, 278).addBox(-5.0F, 0.0F, 2.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(158, 139).addBox(-5.0F, 0.0F, -1.0F, 5.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.0F, -18.0F, -1.0F, 0.0F, 0.0F, -1.021F));

		PartDefinition cube_r39 = updated_body.addOrReplaceChild("cube_r39", CubeListBuilder.create().texOffs(70, 19).addBox(0.0F, -1.0F, 0.0F, 7.0F, 1.0F, 18.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.0F, -17.0F, 1.0F, 0.0F, 0.1396F, 0.0F));

		PartDefinition cube_r40 = updated_body.addOrReplaceChild("cube_r40", CubeListBuilder.create().texOffs(50, 60).addBox(0.0F, 0.0F, 0.0F, 7.0F, 5.0F, 18.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.0F, -18.0F, 1.0F, 0.0733F, 0.1189F, 0.5541F));

		PartDefinition cube_r41 = updated_body.addOrReplaceChild("cube_r41", CubeListBuilder.create().texOffs(198, 199).addBox(-3.0155F, -1.0F, -0.0737F, 3.0F, 1.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.5F, -17.0F, 18.9F, 0.0F, -0.2967F, 0.0F));

		PartDefinition cube_r42 = updated_body.addOrReplaceChild("cube_r42", CubeListBuilder.create().texOffs(0, 60).addBox(-7.0F, 0.0F, 0.0F, 7.0F, 5.0F, 18.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(8.0F, -18.0F, 1.0F, 0.0733F, -0.1189F, -0.5541F));

		PartDefinition cube_r43 = updated_body.addOrReplaceChild("cube_r43", CubeListBuilder.create().texOffs(70, 0).addBox(-7.0F, -1.0F, 0.0F, 7.0F, 1.0F, 18.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(8.0F, -17.0F, 1.0F, 0.0F, -0.1396F, 0.0F));

		PartDefinition cube_r44 = updated_body.addOrReplaceChild("cube_r44", CubeListBuilder.create().texOffs(278, 80).addBox(-1.0F, -0.99F, -5.0402F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.5402F, -14.0F, -12.8F, 0.0F, 1.5708F, 0.0F));

		PartDefinition cube_r45 = updated_body.addOrReplaceChild("cube_r45", CubeListBuilder.create().texOffs(272, 212).addBox(0.0F, 0.0F, -2.5F, 3.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, -16.1F, -19.8F, 0.0F, 0.0F, 0.7854F));

		PartDefinition cube_r46 = updated_body.addOrReplaceChild("cube_r46", CubeListBuilder.create().texOffs(74, 264).addBox(0.0F, -0.0373F, -2.0612F, 4.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, -15.6F, -15.3F, -0.1618F, 0.1597F, 0.7724F));

		PartDefinition cube_r47 = updated_body.addOrReplaceChild("cube_r47", CubeListBuilder.create().texOffs(74, 177).addBox(-1.9802F, 0.0F, -1.9976F, 2.0F, 1.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(72, 124).addBox(-1.9802F, 1.0F, -1.9976F, 1.0F, 9.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.75F, -15.0F, -17.2F, 0.0F, 0.3316F, 0.0F));

		PartDefinition cube_r48 = updated_body.addOrReplaceChild("cube_r48", CubeListBuilder.create().texOffs(276, 242).addBox(-3.0017F, 0.0F, -4.0075F, 3.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(200, 265).addBox(-2.0017F, 1.0F, -4.0075F, 1.0F, 9.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(8.85F, -15.0F, -3.95F, 0.0F, 0.2182F, 0.0F));

		PartDefinition cube_r49 = updated_body.addOrReplaceChild("cube_r49", CubeListBuilder.create().texOffs(278, 234).addBox(-4.0F, 0.0F, -5.0F, 4.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(14, 255).addBox(-2.0F, 1.0F, -5.0F, 1.0F, 9.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(9.5F, -15.0F, 1.0F, 0.0F, 0.1309F, 0.0F));

		PartDefinition cube_r50 = updated_body.addOrReplaceChild("cube_r50", CubeListBuilder.create().texOffs(210, 272).addBox(-1.0F, -0.99F, -5.0F, 3.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.3F, -14.0F, -10.0F, 0.0F, 0.7854F, 0.0F));

		PartDefinition cube_r51 = updated_body.addOrReplaceChild("cube_r51", CubeListBuilder.create().texOffs(272, 206).addBox(-2.0F, -0.99F, -5.0F, 3.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.3F, -14.0F, -10.0F, 0.0F, -0.7854F, 0.0F));

		PartDefinition cube_r52 = updated_body.addOrReplaceChild("cube_r52", CubeListBuilder.create().texOffs(276, 160).addBox(-2.0F, -0.1F, -1.9F, 4.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -19.7F, -12.0F, -0.1745F, 0.0F, 0.0F));

		PartDefinition cube_r53 = updated_body.addOrReplaceChild("cube_r53", CubeListBuilder.create().texOffs(264, 76).addBox(-0.5F, -0.5F, -0.5F, 8.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.0F, -15.5F, -13.5F, 0.0F, 0.3927F, 0.0F));

		PartDefinition cube_r54 = updated_body.addOrReplaceChild("cube_r54", CubeListBuilder.create().texOffs(270, 287).addBox(-1.9457F, -0.1F, -0.2704F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.5F, -15.9F, -13.5F, -0.2849F, 0.274F, -0.8249F));

		PartDefinition cube_r55 = updated_body.addOrReplaceChild("cube_r55", CubeListBuilder.create().texOffs(134, 283).addBox(-0.0543F, -0.1F, -0.2704F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.5F, -15.9F, -13.5F, -0.2849F, -0.274F, 0.8249F));

		PartDefinition cube_r56 = updated_body.addOrReplaceChild("cube_r56", CubeListBuilder.create().texOffs(264, 12).addBox(-7.5F, -0.5F, -0.5F, 8.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.0F, -15.5F, -13.5F, 0.0F, -0.3927F, 0.0F));

		PartDefinition cube_r57 = updated_body.addOrReplaceChild("cube_r57", CubeListBuilder.create().texOffs(150, 26).addBox(0.0F, -1.0F, 0.0F, 1.0F, 1.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, -22.0F, 1.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition cube_r58 = updated_body.addOrReplaceChild("cube_r58", CubeListBuilder.create().texOffs(134, 67).addBox(-3.0F, 0.0F, -3.0F, 3.0F, 3.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, -20.0F, 10.0F, 0.0F, 0.0F, -0.7854F));

		PartDefinition cube_r59 = updated_body.addOrReplaceChild("cube_r59", CubeListBuilder.create().texOffs(134, 52).addBox(0.0F, 0.0F, -3.0F, 3.0F, 3.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, -20.0F, 10.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition cube_r60 = updated_body.addOrReplaceChild("cube_r60", CubeListBuilder.create().texOffs(0, 282).addBox(-1.0F, -0.0013F, 0.0002F, 2.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -20.15F, -15.95F, -0.9425F, 0.0F, 0.0F));

		PartDefinition cube_r61 = updated_body.addOrReplaceChild("cube_r61", CubeListBuilder.create().texOffs(108, 282).addBox(-1.0F, -0.9706F, 0.0188F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -20.4F, -14.95F, -1.8326F, 0.0F, 0.0F));

		PartDefinition cube_r62 = updated_body.addOrReplaceChild("cube_r62", CubeListBuilder.create().texOffs(98, 278).addBox(-1.0F, -1.0F, 1.0F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(278, 218).addBox(-1.0F, 1.0F, 0.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -20.6F, -14.0F, -1.3439F, 0.0F, 0.0F));

		PartDefinition cube_r63 = updated_body.addOrReplaceChild("cube_r63", CubeListBuilder.create().texOffs(208, 257).addBox(-1.9973F, 1.0068F, 0.2013F, 2.0F, 1.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(190, 257).addBox(-16.0027F, 1.0068F, 0.2013F, 2.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(8.0F, -19.8F, -5.9F, -0.1658F, 0.0F, 0.0F));

		PartDefinition cube_r64 = updated_body.addOrReplaceChild("cube_r64", CubeListBuilder.create().texOffs(282, 73).addBox(-1.9591F, 0.4291F, -0.2315F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.1F, -19.8F, -9.4F, -0.1708F, 0.2409F, -0.0411F));

		PartDefinition cube_r65 = updated_body.addOrReplaceChild("cube_r65", CubeListBuilder.create().texOffs(218, 285).addBox(-1.9591F, 0.4291F, -3.2315F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.2F, -19.8F, -9.4F, -0.2182F, 0.7037F, -0.1425F));

		PartDefinition cube_r66 = updated_body.addOrReplaceChild("cube_r66", CubeListBuilder.create().texOffs(134, 285).addBox(-1.9101F, -0.2311F, -3.093F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.6F, -19.8F, -13.4F, -0.2093F, 0.6526F, -0.1282F));

		PartDefinition cube_r67 = updated_body.addOrReplaceChild("cube_r67", CubeListBuilder.create().texOffs(280, 268).addBox(-2.0027F, 0.033F, -4.133F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.2F, -19.8F, -11.8F, -0.2981F, 0.9741F, -0.2489F));

		PartDefinition cube_r68 = updated_body.addOrReplaceChild("cube_r68", CubeListBuilder.create().texOffs(108, 285).addBox(-0.0899F, -0.2311F, -3.093F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.6F, -19.8F, -13.4F, -0.2093F, -0.6526F, 0.1282F));

		PartDefinition cube_r69 = updated_body.addOrReplaceChild("cube_r69", CubeListBuilder.create().texOffs(280, 263).addBox(0.0027F, 0.033F, -4.133F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.2F, -19.8F, -11.8F, -0.2981F, -0.9741F, 0.2489F));

		PartDefinition cube_r70 = updated_body.addOrReplaceChild("cube_r70", CubeListBuilder.create().texOffs(284, 251).addBox(-0.0409F, 0.4291F, -3.2315F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.2F, -19.8F, -9.4F, -0.2182F, -0.7037F, 0.1425F));

		PartDefinition cube_r71 = updated_body.addOrReplaceChild("cube_r71", CubeListBuilder.create().texOffs(58, 282).addBox(-0.0409F, 0.4291F, -0.2315F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.1F, -19.8F, -9.4F, -0.1708F, -0.2409F, 0.0411F));

		PartDefinition cube_r72 = updated_body.addOrReplaceChild("cube_r72", CubeListBuilder.create().texOffs(260, 14).addBox(0.0F, 0.0248F, -6.9792F, 2.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, -15.7F, -27.3F, 0.1492F, -0.1476F, 0.8354F));

		PartDefinition cube_r73 = updated_body.addOrReplaceChild("cube_r73", CubeListBuilder.create().texOffs(268, 107).addBox(0.4F, 0.0248F, -0.9793F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, -15.7F, -27.3F, 0.1492F, -0.1476F, 0.7744F));

		PartDefinition cube_r74 = updated_body.addOrReplaceChild("cube_r74", CubeListBuilder.create().texOffs(288, 110).addBox(1.5F, 0.0188F, -2.4921F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(288, 108).addBox(1.6F, 0.0188F, -1.4921F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(288, 106).addBox(1.8F, 0.0188F, -0.4921F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(236, 71).addBox(2.0F, 0.0187F, 0.5079F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(274, 193).addBox(0.0F, 0.0187F, -2.4921F, 2.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, -15.9F, -24.8F, 0.0618F, -0.0617F, 0.7835F));

		PartDefinition cube_r75 = updated_body.addOrReplaceChild("cube_r75", CubeListBuilder.create().texOffs(268, 29).addBox(-2.4F, 0.0248F, -0.9793F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, -15.7F, -27.3F, 0.1492F, 0.1476F, -0.7744F));

		PartDefinition cube_r76 = updated_body.addOrReplaceChild("cube_r76", CubeListBuilder.create().texOffs(258, 241).addBox(-2.0F, 0.0248F, -6.9792F, 2.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, -15.7F, -27.3F, 0.1492F, 0.1476F, -0.8354F));

		PartDefinition cube_r77 = updated_body.addOrReplaceChild("cube_r77", CubeListBuilder.create().texOffs(150, 15).addBox(-3.0F, 0.0248F, -9.9792F, 6.0F, 1.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(178, 171).addBox(-3.0F, 0.0248F, -18.9792F, 6.0F, 1.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -15.7F, -27.3F, 0.2094F, 0.0F, 0.0F));

		PartDefinition cube_r78 = updated_body.addOrReplaceChild("cube_r78", CubeListBuilder.create().texOffs(268, 163).addBox(-1.9798F, 0.0F, 0.0008F, 2.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(218, 62).addBox(-1.9798F, -4.0F, 6.0008F, 2.0F, 5.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.1F, -7.0F, -51.5F, 0.0F, 0.0349F, 0.0F));

		PartDefinition cube_r79 = updated_body.addOrReplaceChild("cube_r79", CubeListBuilder.create().texOffs(216, 81).addBox(0.0F, -0.0164F, 0.0494F, 4.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, -9.5F, -52.6F, 0.017F, -0.3137F, 1.5157F));

		PartDefinition cube_r80 = updated_body.addOrReplaceChild("cube_r80", CubeListBuilder.create().texOffs(166, 282).addBox(-1.7704F, 0.0F, -2.9956F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.1F, -10.0F, -45.5F, 0.3927F, 0.0349F, 0.0F));

		PartDefinition cube_r81 = updated_body.addOrReplaceChild("cube_r81", CubeListBuilder.create().texOffs(190, 209).addBox(0.0F, -0.0355F, -3.6313F, 2.0F, 1.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, -12.45F, -42.3F, 0.0812F, -0.1933F, 1.1702F));

		PartDefinition cube_r82 = updated_body.addOrReplaceChild("cube_r82", CubeListBuilder.create().texOffs(268, 137).addBox(-0.0084F, -0.0294F, -6.0329F, 2.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, -14.199F, -34.0825F, 0.1036F, -0.1831F, 1.1163F));

		PartDefinition cube_r83 = updated_body.addOrReplaceChild("cube_r83", CubeListBuilder.create().texOffs(250, 100).addBox(-1.9759F, 0.0F, -4.007F, 2.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.55F, -12.0F, -36.5F, 0.1745F, 0.1222F, 0.0F));

		PartDefinition cube_r84 = updated_body.addOrReplaceChild("cube_r84", CubeListBuilder.create().texOffs(114, 263).addBox(-1.9758F, -5.0F, -2.007F, 2.0F, 6.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(14, 269).addBox(-1.9758F, -5.0F, 2.993F, 2.0F, 6.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(154, 93).addBox(-1.9758F, -7.0F, 6.993F, 2.0F, 8.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.55F, -7.0F, -36.5F, 0.0F, 0.1222F, 0.0F));

		PartDefinition cube_r85 = updated_body.addOrReplaceChild("cube_r85", CubeListBuilder.create().texOffs(26, 265).addBox(-0.8547F, -1.0F, -6.1384F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.55F, -13.0F, -27.3F, 0.1396F, 0.1222F, 0.0F));

		PartDefinition cube_r86 = updated_body.addOrReplaceChild("cube_r86", CubeListBuilder.create().texOffs(204, 147).addBox(-0.281F, -8.0F, -6.356F, 2.0F, 8.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.5258F, -4.3F, -17.0886F, -0.299F, 0.1722F, -0.0155F));

		PartDefinition cube_r87 = updated_body.addOrReplaceChild("cube_r87", CubeListBuilder.create().texOffs(268, 100).addBox(-1.9916F, -0.0294F, -6.0329F, 2.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, -14.199F, -34.0825F, 0.1036F, 0.1831F, -1.1163F));

		PartDefinition cube_r88 = updated_body.addOrReplaceChild("cube_r88", CubeListBuilder.create().texOffs(208, 176).addBox(-2.0F, -0.0355F, -3.6313F, 2.0F, 1.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, -12.45F, -42.3F, 0.0812F, 0.1933F, -1.1702F));

		PartDefinition cube_r89 = updated_body.addOrReplaceChild("cube_r89", CubeListBuilder.create().texOffs(210, 130).addBox(-3.0F, 0.0187F, 0.5079F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(102, 288).addBox(-2.5F, 0.0188F, -2.4921F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(98, 288).addBox(-2.6F, 0.0188F, -1.4921F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(288, 20).addBox(-2.8F, 0.0188F, -0.4921F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(274, 178).addBox(-2.0F, 0.0187F, -2.4921F, 2.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, -15.9F, -24.8F, 0.0618F, 0.0617F, -0.7835F));

		PartDefinition cube_r90 = updated_body.addOrReplaceChild("cube_r90", CubeListBuilder.create().texOffs(246, 187).addBox(-3.0F, 0.0F, -5.0F, 6.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -16.1F, -22.3F, 0.0873F, 0.0F, 0.0F));

		PartDefinition cube_r91 = updated_body.addOrReplaceChild("cube_r91", CubeListBuilder.create().texOffs(272, 170).addBox(-1.0F, 0.0F, -2.0F, 2.0F, 3.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(142, 272).addBox(-15.0F, 0.0F, -2.0F, 2.0F, 3.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.0F, -16.1F, -5.0F, 0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r92 = updated_body.addOrReplaceChild("cube_r92", CubeListBuilder.create().texOffs(22, 228).addBox(-4.0F, -0.0373F, -2.0612F, 4.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, -15.6F, -15.3F, -0.1618F, -0.1597F, -0.7724F));

		PartDefinition cube_r93 = updated_body.addOrReplaceChild("cube_r93", CubeListBuilder.create().texOffs(122, 246).addBox(-3.0F, 0.0F, 0.0F, 6.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -16.1F, -17.3F, -0.2269F, 0.0F, 0.0F));

		PartDefinition cube_r94 = updated_body.addOrReplaceChild("cube_r94", CubeListBuilder.create().texOffs(88, 272).addBox(-3.0F, 0.0F, -2.5F, 3.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, -16.1F, -19.8F, 0.0F, 0.0F, -0.7854F));

		PartDefinition cube_r95 = updated_body.addOrReplaceChild("cube_r95", CubeListBuilder.create().texOffs(174, 257).addBox(-2.0F, 0.0F, 0.0F, 2.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(17.5F, -9.0F, 2.0F, -0.0742F, -0.1772F, 0.3993F));

		PartDefinition cube_r96 = updated_body.addOrReplaceChild("cube_r96", CubeListBuilder.create().texOffs(158, 257).addBox(-2.0F, 0.0F, 0.0F, 2.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(17.5F, -13.0F, 2.0F, 0.0F, -0.192F, 0.0F));

		PartDefinition cube_r97 = updated_body.addOrReplaceChild("cube_r97", CubeListBuilder.create().texOffs(236, 253).addBox(-1.9969F, 0.0F, -0.011F, 2.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(16.35F, -13.0F, 7.9F, 0.0F, -0.2793F, 0.0F));

		PartDefinition cube_r98 = updated_body.addOrReplaceChild("cube_r98", CubeListBuilder.create().texOffs(166, 274).addBox(-1.9818F, 0.0F, -4.0F, 2.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(13.25F, -13.0F, 17.4F, 0.0F, -0.3665F, 0.0F));

		PartDefinition cube_r99 = updated_body.addOrReplaceChild("cube_r99", CubeListBuilder.create().texOffs(128, 266).addBox(-1.9818F, 0.0F, -5.0F, 2.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(10.6F, -13.0F, 21.6F, 0.0F, -0.5585F, 0.0F));

		PartDefinition cube_r100 = updated_body.addOrReplaceChild("cube_r100", CubeListBuilder.create().texOffs(192, 50).addBox(-2.0F, 0.0F, -8.0F, 2.0F, 4.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.8F, -13.0F, 28.0F, 0.0F, -0.6458F, 0.0F));

		PartDefinition cube_r101 = updated_body.addOrReplaceChild("cube_r101", CubeListBuilder.create().texOffs(100, 59).addBox(-2.0F, 0.0F, -10.0F, 2.0F, 9.0F, 15.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.8F, -13.0F, 28.0F, 0.0F, -0.3578F, 0.0F));

		PartDefinition cube_r102 = updated_body.addOrReplaceChild("cube_r102", CubeListBuilder.create().texOffs(102, 198).addBox(-2.0F, 0.0F, -8.0F, 2.0F, 6.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.8F, -9.0F, 28.0F, -0.2808F, -0.5896F, 0.4785F));

		PartDefinition cube_r103 = updated_body.addOrReplaceChild("cube_r103", CubeListBuilder.create().texOffs(0, 262).addBox(-1.9818F, 0.0F, -5.0F, 2.0F, 6.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(10.6F, -9.0F, 21.6F, -0.2347F, -0.5116F, 0.4544F));

		PartDefinition cube_r104 = updated_body.addOrReplaceChild("cube_r104", CubeListBuilder.create().texOffs(58, 272).addBox(-1.9818F, 0.0F, -4.0F, 2.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(13.25F, -9.0F, 17.4F, -0.1459F, -0.3375F, 0.4176F));

		PartDefinition cube_r105 = updated_body.addOrReplaceChild("cube_r105", CubeListBuilder.create().texOffs(44, 245).addBox(-1.9969F, 0.0F, -0.011F, 2.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(16.35F, -9.0F, 7.9F, -0.1093F, -0.2575F, 0.4069F));

		PartDefinition cube_r106 = updated_body.addOrReplaceChild("cube_r106", CubeListBuilder.create().texOffs(264, 68).addBox(-2.0F, 0.0F, 0.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(15.9F, -5.3F, 1.7F, -0.1124F, -0.156F, 0.6284F));

		PartDefinition cube_r107 = updated_body.addOrReplaceChild("cube_r107", CubeListBuilder.create().texOffs(282, 149).addBox(-11.0F, -1.0F, 5.0F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(186, 133).addBox(-11.0F, -1.0F, 0.0F, 10.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(178, 36).addBox(-25.6F, -1.0F, 5.0F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(122, 109).addBox(-32.6F, -1.0F, 0.0F, 10.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(16.8F, -3.0F, 23.75F, 0.3927F, 0.0F, 0.0F));

		PartDefinition cube_r108 = updated_body.addOrReplaceChild("cube_r108", CubeListBuilder.create().texOffs(270, 87).addBox(-3.0F, -0.0353F, -4.9913F, 3.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(8.8F, -6.9494F, 30.7639F, 0.5458F, -0.7419F, -0.3894F));

		PartDefinition cube_r109 = updated_body.addOrReplaceChild("cube_r109", CubeListBuilder.create().texOffs(0, 26).addBox(-10.0F, 1.0F, -25.0F, 10.0F, 1.0F, 25.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(15.8F, -5.0F, 24.0F, 0.0F, -0.0349F, 0.0F));

		PartDefinition cube_r110 = updated_body.addOrReplaceChild("cube_r110", CubeListBuilder.create().texOffs(42, 257).addBox(-1.0F, -2.5F, 4.3301F, 1.0F, 3.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(20.4F, -9.5F, -18.0F, -0.5236F, -0.3491F, 0.0F));

		PartDefinition cube_r111 = updated_body.addOrReplaceChild("cube_r111", CubeListBuilder.create().texOffs(218, 283).addBox(-1.0F, 6.0F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(256, 208).addBox(-1.0F, 3.9939F, 4.0605F, 1.0F, 3.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(178, 6).addBox(-1.0F, 0.5F, 0.0F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(156, 274).addBox(-1.0F, 0.5F, 1.0F, 1.0F, 6.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(174, 154).addBox(-5.0F, 6.0F, 1.0F, 5.0F, 1.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(284, 226).addBox(-8.0F, 6.0F, 4.0F, 3.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(286, 90).addBox(-4.0F, 6.0F, 0.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(20.4F, -10.0F, -18.0F, 0.0F, -0.3491F, 0.0F));

		PartDefinition cube_r112 = updated_body.addOrReplaceChild("cube_r112", CubeListBuilder.create().texOffs(108, 265).addBox(-1.0F, -4.5F, -2.0F, 1.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(20.4F, -5.0F, -21.0F, 0.0F, 0.3927F, 0.0F));

		PartDefinition cube_r113 = updated_body.addOrReplaceChild("cube_r113", CubeListBuilder.create().texOffs(160, 40).addBox(-0.5F, -5.0F, -0.5F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(12.4109F, -4.0F, -15.0548F, -0.7854F, -0.3491F, 0.0F));

		PartDefinition cube_r114 = updated_body.addOrReplaceChild("cube_r114", CubeListBuilder.create().texOffs(68, 203).addBox(-1.0001F, 0.0F, -9.0099F, 1.0F, 4.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(12.7F, -8.0F, -11.35F, 0.8467F, 0.5453F, 1.1408F));

		PartDefinition cube_r115 = updated_body.addOrReplaceChild("cube_r115", CubeListBuilder.create().texOffs(212, 209).addBox(-1.0001F, 0.0F, -9.0099F, 1.0F, 2.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(13.3F, -9.0F, -11.95F, 0.7622F, 0.6713F, 0.9933F));

		PartDefinition cube_r116 = updated_body.addOrReplaceChild("cube_r116", CubeListBuilder.create().texOffs(174, 139).addBox(-2.0F, 0.0F, -11.0F, 2.0F, 4.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(17.5F, -13.0F, 2.0F, 0.0F, 0.1396F, 0.0F));

		PartDefinition cube_r117 = updated_body.addOrReplaceChild("cube_r117", CubeListBuilder.create().texOffs(118, 285).addBox(-2.0001F, 1.0F, 1.9901F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(264, 7).addBox(-6.0001F, 4.0F, -0.0099F, 6.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(24, 282).addBox(-2.0001F, 0.0F, -0.0099F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(13.3F, -14.0F, -11.95F, 0.0F, 0.7243F, 0.0F));

		PartDefinition cube_r118 = updated_body.addOrReplaceChild("cube_r118", CubeListBuilder.create().texOffs(260, 282).addBox(-1.0001F, 0.0F, -0.0099F, 1.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(13.3F, -9.0F, -11.95F, 0.5171F, 0.5324F, 0.8421F));

		PartDefinition cube_r119 = updated_body.addOrReplaceChild("cube_r119", CubeListBuilder.create().texOffs(182, 103).addBox(-2.0F, 0.0F, -10.0F, 2.0F, 5.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(18, 208).addBox(-2.0F, 0.0F, -11.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(17.5F, -9.0F, 2.0F, 0.0526F, 0.1294F, 0.6666F));

		PartDefinition cube_r120 = updated_body.addOrReplaceChild("cube_r120", CubeListBuilder.create().texOffs(122, 188).addBox(-1.0F, 0.0F, -11.0F, 1.0F, 3.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(14.5F, -6.0F, 2.0F, -0.068F, 0.122F, -0.2311F));

		PartDefinition cube_r121 = updated_body.addOrReplaceChild("cube_r121", CubeListBuilder.create().texOffs(186, 118).addBox(-1.0001F, 0.0F, -10.0099F, 1.0F, 4.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(12.2F, -7.6F, -9.25F, -0.3104F, 0.6978F, -0.3695F));

		PartDefinition cube_r122 = updated_body.addOrReplaceChild("cube_r122", CubeListBuilder.create().texOffs(178, 93).addBox(-6.0001F, 0.0F, -9.0099F, 6.0F, 1.0F, 9.0F, new CubeDeformation(0.0F))
		.texOffs(206, 103).addBox(-2.0001F, -4.0F, -9.0099F, 2.0F, 1.0F, 9.0F, new CubeDeformation(0.0F))
		.texOffs(254, 230).addBox(-2.0001F, -3.0F, -9.0099F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(13.3F, -10.0F, -11.95F, 0.0F, 0.9687F, 0.0F));

		PartDefinition cube_r123 = updated_body.addOrReplaceChild("cube_r123", CubeListBuilder.create().texOffs(100, 252).addBox(-2.0067F, -7.0F, -0.9873F, 2.0F, 8.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.65F, -7.0F, -19.65F, 0.0F, 0.2094F, 0.0F));

		PartDefinition cube_r124 = updated_body.addOrReplaceChild("cube_r124", CubeListBuilder.create().texOffs(268, 22).addBox(-2.0F, 0.0F, 0.0F, 2.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(226, 263).addBox(-5.0F, 0.0F, 0.0F, 3.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(16.5F, -14.0F, 2.0F, 0.0F, -0.192F, 0.0F));

		PartDefinition cube_r125 = updated_body.addOrReplaceChild("cube_r125", CubeListBuilder.create().texOffs(264, 267).addBox(-1.9969F, 0.0F, -0.011F, 2.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(260, 230).addBox(-4.9969F, 0.0F, -0.011F, 3.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(15.35F, -14.0F, 7.9F, 0.0F, -0.2793F, 0.0F));

		PartDefinition cube_r126 = updated_body.addOrReplaceChild("cube_r126", CubeListBuilder.create().texOffs(280, 258).addBox(-1.9818F, 0.0F, -4.0F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(222, 280).addBox(-3.9818F, 0.0F, -4.0F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(12.25F, -14.0F, 17.4F, 0.0F, -0.3665F, 0.0F));

		PartDefinition cube_r127 = updated_body.addOrReplaceChild("cube_r127", CubeListBuilder.create().texOffs(114, 274).addBox(-1.9818F, 0.0F, -5.0F, 2.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(274, 107).addBox(-3.9818F, 0.0F, -5.0F, 2.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(9.6F, -14.0F, 21.6F, 0.0F, -0.5585F, 0.0F));

		PartDefinition cube_r128 = updated_body.addOrReplaceChild("cube_r128", CubeListBuilder.create().texOffs(208, 166).addBox(-2.0F, 0.0F, -8.0F, 2.0F, 1.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.8F, -14.0F, 28.0F, 0.0F, -0.6458F, 0.0F));

		PartDefinition cube_r129 = updated_body.addOrReplaceChild("cube_r129", CubeListBuilder.create().texOffs(142, 255).addBox(0.0F, 3.4939F, 4.0605F, 1.0F, 3.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(286, 40).addBox(0.0F, 5.5F, 0.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(284, 190).addBox(5.0F, 5.5F, 4.0F, 3.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(160, 82).addBox(0.0F, 5.5F, 1.0F, 5.0F, 1.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(186, 282).addBox(0.0F, 5.5F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(130, 52).addBox(0.0F, 0.0F, 0.0F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(104, 272).addBox(0.0F, 0.0F, 1.0F, 1.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-20.4F, -9.5F, -18.0F, 0.0F, 0.3491F, 0.0F));

		PartDefinition cube_r130 = updated_body.addOrReplaceChild("cube_r130", CubeListBuilder.create().texOffs(26, 255).addBox(0.0F, -2.5F, 4.3301F, 1.0F, 3.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-20.4F, -9.5F, -18.0F, -0.5236F, 0.3491F, 0.0F));

		PartDefinition cube_r131 = updated_body.addOrReplaceChild("cube_r131", CubeListBuilder.create().texOffs(18, 201).addBox(0.0F, -4.5F, -2.0F, 1.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-20.4F, -5.0F, -21.0F, 0.0F, -0.3927F, 0.0F));

		PartDefinition cube_r132 = updated_body.addOrReplaceChild("cube_r132", CubeListBuilder.create().texOffs(22, 145).addBox(-0.5F, -5.0F, -0.5F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-12.4109F, -4.0F, -15.0548F, -0.7854F, 0.3491F, 0.0F));

		PartDefinition cube_r133 = updated_body.addOrReplaceChild("cube_r133", CubeListBuilder.create().texOffs(66, 216).addBox(-4.0F, -0.0164F, 0.0494F, 4.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, -9.5F, -52.6F, 0.017F, 0.3137F, -1.5157F));

		PartDefinition cube_r134 = updated_body.addOrReplaceChild("cube_r134", CubeListBuilder.create().texOffs(192, 71).addBox(-3.0F, -0.0164F, 0.0494F, 6.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -9.5F, -52.6F, 0.3142F, 0.0F, 0.0F));

		PartDefinition cube_r135 = updated_body.addOrReplaceChild("cube_r135", CubeListBuilder.create().texOffs(286, 174).addBox(-2.0F, 0.8507F, -1.999F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(174, 267).addBox(-2.0F, -0.1493F, -5.999F, 2.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, -9.4F, -52.5F, 0.4582F, 0.2995F, 0.1445F));

		PartDefinition cube_r136 = updated_body.addOrReplaceChild("cube_r136", CubeListBuilder.create().texOffs(280, 282).addBox(-1.0062F, -1.0F, 0.0318F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.6F, -7.0F, -55.5F, -0.0503F, 0.1209F, -0.3957F));

		PartDefinition cube_r137 = updated_body.addOrReplaceChild("cube_r137", CubeListBuilder.create().texOffs(270, 93).addBox(-2.0F, -1.0F, 1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -7.0F, -58.0F, -0.4233F, 0.3897F, -0.8702F));

		PartDefinition cube_r138 = updated_body.addOrReplaceChild("cube_r138", CubeListBuilder.create().texOffs(230, 32).addBox(0.0F, 0.8507F, -1.999F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(158, 267).addBox(0.0F, -0.1493F, -5.999F, 2.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, -9.4F, -52.5F, 0.4582F, -0.2995F, -0.1445F));

		PartDefinition cube_r139 = updated_body.addOrReplaceChild("cube_r139", CubeListBuilder.create().texOffs(42, 267).addBox(-1.0F, -0.01F, 0.0F, 2.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -7.0F, -58.0F, 0.4363F, 0.0F, 0.0F));

		PartDefinition cube_r140 = updated_body.addOrReplaceChild("cube_r140", CubeListBuilder.create().texOffs(210, 265).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -6.0F, -58.0F, -0.1222F, 0.0F, 0.0F));

		PartDefinition cube_r141 = updated_body.addOrReplaceChild("cube_r141", CubeListBuilder.create().texOffs(246, 193).addBox(-11.0F, 0.0F, 1.0F, 11.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(12.5F, -10.5F, 35.5F, 0.1068F, -0.3786F, -0.2823F));

		PartDefinition cube_r142 = updated_body.addOrReplaceChild("cube_r142", CubeListBuilder.create().texOffs(242, 153).addBox(-11.0F, 0.0F, 1.0F, 11.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(12.5F, -10.5F, 35.5F, -0.0789F, -0.3851F, 0.2074F));

		PartDefinition cube_r143 = updated_body.addOrReplaceChild("cube_r143", CubeListBuilder.create().texOffs(242, 151).addBox(0.0F, 0.0F, 1.0F, 11.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-12.5F, -10.5F, 35.5F, 0.1068F, 0.3786F, 0.2823F));

		PartDefinition cube_r144 = updated_body.addOrReplaceChild("cube_r144", CubeListBuilder.create().texOffs(242, 78).addBox(0.0F, 0.0F, 1.0F, 11.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-12.5F, -10.5F, 35.5F, -0.0789F, 0.3851F, -0.2074F));

		PartDefinition cube_r145 = updated_body.addOrReplaceChild("cube_r145", CubeListBuilder.create().texOffs(212, 21).addBox(0.0F, 0.0F, 0.0F, 11.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-12.5F, -11.0F, 35.5F, 0.0F, 0.7854F, 0.0F));

		PartDefinition cube_r146 = updated_body.addOrReplaceChild("cube_r146", CubeListBuilder.create().texOffs(278, 14).addBox(-1.9343F, 0.01F, -0.0489F, 2.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.15F, -14.0F, -16.85F, 0.0F, 0.5498F, 0.0F));

		PartDefinition cube_r147 = updated_body.addOrReplaceChild("cube_r147", CubeListBuilder.create().texOffs(276, 274).addBox(-0.0657F, 0.01F, -0.0489F, 2.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.15F, -14.0F, -16.85F, 0.0F, -0.5498F, 0.0F));

		PartDefinition cube_r148 = updated_body.addOrReplaceChild("cube_r148", CubeListBuilder.create().texOffs(80, 246).addBox(-0.9802F, 0.03F, 4.0024F, 3.0F, 1.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(214, 50).addBox(-1.9802F, 0.02F, 3.0024F, 2.0F, 5.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.75F, -14.0F, -17.2F, 0.0F, 0.3316F, 0.0F));

		PartDefinition cube_r149 = updated_body.addOrReplaceChild("cube_r149", CubeListBuilder.create().texOffs(60, 246).addBox(-2.0198F, 0.03F, 4.0024F, 3.0F, 1.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(108, 215).addBox(-0.0198F, 0.02F, 3.0024F, 2.0F, 5.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.75F, -14.0F, -17.2F, 0.0F, -0.3316F, 0.0F));

		PartDefinition cube_r150 = updated_body.addOrReplaceChild("cube_r150", CubeListBuilder.create().texOffs(130, 252).addBox(1.0F, 1.0F, -5.0F, 1.0F, 9.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(274, 203).addBox(0.0F, 0.0F, -5.0F, 4.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-9.5F, -15.0F, 1.0F, 0.0F, -0.1309F, 0.0F));

		PartDefinition cube_r151 = updated_body.addOrReplaceChild("cube_r151", CubeListBuilder.create().texOffs(190, 265).addBox(1.0017F, 1.0F, -4.0075F, 1.0F, 9.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(276, 155).addBox(0.0017F, 0.0F, -4.0075F, 3.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.85F, -15.0F, -3.95F, 0.0F, -0.2182F, 0.0F));

		PartDefinition cube_r152 = updated_body.addOrReplaceChild("cube_r152", CubeListBuilder.create().texOffs(0, 124).addBox(0.9802F, 1.0F, -1.9976F, 1.0F, 9.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(150, 173).addBox(-0.0198F, 0.0F, -1.9976F, 2.0F, 1.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.75F, -15.0F, -17.2F, 0.0F, -0.3316F, 0.0F));

		PartDefinition cube_r153 = updated_body.addOrReplaceChild("cube_r153", CubeListBuilder.create().texOffs(38, 274).addBox(0.0F, -0.01F, -5.0F, 2.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-11.5F, -14.0F, 1.0F, 0.0F, -0.1309F, 0.0F));

		PartDefinition cube_r154 = updated_body.addOrReplaceChild("cube_r154", CubeListBuilder.create().texOffs(280, 144).addBox(0.0F, -0.01F, 0.0F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-11.5F, -14.0F, 4.0F, 0.0F, 0.1047F, 0.0F));

		PartDefinition cube_r155 = updated_body.addOrReplaceChild("cube_r155", CubeListBuilder.create().texOffs(254, 222).addBox(-0.0031F, -0.01F, -0.011F, 2.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-11.1F, -14.0F, 7.95F, 0.0F, 0.2094F, 0.0F));

		PartDefinition cube_r156 = updated_body.addOrReplaceChild("cube_r156", CubeListBuilder.create().texOffs(258, 155).addBox(-0.0031F, -0.01F, -0.011F, 2.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-9.65F, -14.0F, 14.8F, 0.0F, 0.2793F, 0.0F));

		PartDefinition cube_r157 = updated_body.addOrReplaceChild("cube_r157", CubeListBuilder.create().texOffs(228, 109).addBox(0.0001F, -3.0F, -9.0099F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(178, 26).addBox(0.0001F, 0.0F, -9.0099F, 6.0F, 1.0F, 9.0F, new CubeDeformation(0.0F))
		.texOffs(74, 167).addBox(0.0001F, -4.0F, -9.0099F, 2.0F, 1.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-13.3F, -10.0F, -11.95F, 0.0F, -0.9687F, 0.0F));

		PartDefinition cube_r158 = updated_body.addOrReplaceChild("cube_r158", CubeListBuilder.create().texOffs(88, 278).mirror().addBox(-1.0001F, 0.0F, -0.0099F, 1.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(12.7F, -8.0F, -11.35F, 0.6088F, 0.4202F, 1.0414F));

		PartDefinition cube_r159 = updated_body.addOrReplaceChild("cube_r159", CubeListBuilder.create().texOffs(88, 278).addBox(0.0001F, 0.0F, -0.0099F, 1.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-12.7F, -8.0F, -11.35F, 0.6088F, -0.4202F, -1.0414F));

		PartDefinition cube_r160 = updated_body.addOrReplaceChild("cube_r160", CubeListBuilder.create().texOffs(0, 186).addBox(0.0001F, 0.0F, -10.0099F, 1.0F, 4.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-12.2F, -7.6F, -9.25F, -0.3104F, -0.6978F, 0.3695F));

		PartDefinition cube_r161 = updated_body.addOrReplaceChild("cube_r161", CubeListBuilder.create().texOffs(122, 202).addBox(0.0001F, 0.0F, -9.0099F, 1.0F, 4.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-12.7F, -8.0F, -11.35F, 0.8467F, -0.5453F, -1.1408F));

		PartDefinition cube_r162 = updated_body.addOrReplaceChild("cube_r162", CubeListBuilder.create().texOffs(88, 212).addBox(0.0001F, 0.0F, -9.0099F, 1.0F, 2.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-13.3F, -9.0F, -11.95F, 0.7622F, -0.6713F, -0.9933F));

		PartDefinition cube_r163 = updated_body.addOrReplaceChild("cube_r163", CubeListBuilder.create().texOffs(176, 282).addBox(0.0001F, 0.0F, -0.0099F, 1.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-13.3F, -9.0F, -11.95F, 0.5171F, -0.5324F, -0.8421F));

		PartDefinition cube_r164 = updated_body.addOrReplaceChild("cube_r164", CubeListBuilder.create().texOffs(260, 132).addBox(0.0001F, 0.0F, -0.0099F, 6.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(14, 249).addBox(0.0001F, -3.0F, 1.9901F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(282, 0).addBox(0.0001F, -4.0F, -0.0099F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-13.3F, -10.0F, -11.95F, 0.0F, -0.7243F, 0.0F));

		PartDefinition cube_r165 = updated_body.addOrReplaceChild("cube_r165", CubeListBuilder.create().texOffs(24, 197).addBox(0.0F, 0.0F, -8.0F, 2.0F, 6.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.8F, -9.0F, 28.0F, -0.2808F, 0.5896F, -0.4785F));

		PartDefinition cube_r166 = updated_body.addOrReplaceChild("cube_r166", CubeListBuilder.create().texOffs(88, 83).addBox(0.0F, 0.0F, -10.0F, 2.0F, 9.0F, 15.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.8F, -13.0F, 28.0F, 0.0F, 0.3578F, 0.0F));

		PartDefinition cube_r167 = updated_body.addOrReplaceChild("cube_r167", CubeListBuilder.create().texOffs(80, 190).addBox(0.0F, 0.0F, -8.0F, 2.0F, 4.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.8F, -13.0F, 28.0F, 0.0F, 0.6458F, 0.0F));

		PartDefinition cube_r168 = updated_body.addOrReplaceChild("cube_r168", CubeListBuilder.create().texOffs(260, 195).addBox(-0.0182F, 0.0F, -5.0F, 2.0F, 6.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-10.6F, -9.0F, 21.6F, -0.2347F, 0.5116F, -0.4544F));

		PartDefinition cube_r169 = updated_body.addOrReplaceChild("cube_r169", CubeListBuilder.create().texOffs(88, 203).addBox(-0.0182F, 0.0F, -5.0F, 2.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-10.6F, -13.0F, 21.6F, 0.0F, 0.5585F, 0.0F));

		PartDefinition cube_r170 = updated_body.addOrReplaceChild("cube_r170", CubeListBuilder.create().texOffs(272, 43).addBox(-0.0182F, 0.0F, -4.0F, 2.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-13.25F, -9.0F, 17.4F, -0.1459F, 0.3375F, -0.4176F));

		PartDefinition cube_r171 = updated_body.addOrReplaceChild("cube_r171", CubeListBuilder.create().texOffs(274, 34).addBox(-0.0182F, 0.0F, -4.0F, 2.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-13.25F, -13.0F, 17.4F, 0.0F, 0.3665F, 0.0F));

		PartDefinition cube_r172 = updated_body.addOrReplaceChild("cube_r172", CubeListBuilder.create().texOffs(242, 241).addBox(-0.0031F, 0.0F, -0.011F, 2.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-16.35F, -9.0F, 7.9F, -0.1093F, 0.2575F, -0.4069F));

		PartDefinition cube_r173 = updated_body.addOrReplaceChild("cube_r173", CubeListBuilder.create().texOffs(252, 22).addBox(-0.0031F, 0.0F, -0.011F, 2.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-16.35F, -13.0F, 7.9F, 0.0F, 0.2793F, 0.0F));

		PartDefinition cube_r174 = updated_body.addOrReplaceChild("cube_r174", CubeListBuilder.create().texOffs(150, 186).addBox(0.0F, 0.0F, -11.0F, 1.0F, 3.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-14.5F, -6.0F, 2.0F, -0.068F, -0.122F, 0.2311F));

		PartDefinition cube_r175 = updated_body.addOrReplaceChild("cube_r175", CubeListBuilder.create().texOffs(72, 121).addBox(0.0F, 0.0F, -11.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(182, 6).addBox(0.0F, 0.0F, -10.0F, 2.0F, 5.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-17.5F, -9.0F, 2.0F, 0.0526F, -0.1294F, -0.6666F));

		PartDefinition cube_r176 = updated_body.addOrReplaceChild("cube_r176", CubeListBuilder.create().texOffs(124, 173).addBox(0.0F, 0.0F, -11.0F, 2.0F, 4.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-17.5F, -13.0F, 2.0F, 0.0F, -0.1396F, 0.0F));

		PartDefinition cube_r177 = updated_body.addOrReplaceChild("cube_r177", CubeListBuilder.create().texOffs(58, 264).addBox(0.0F, 0.0F, 0.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-15.9F, -5.3F, 1.7F, -0.1124F, 0.156F, -0.6284F));

		PartDefinition cube_r178 = updated_body.addOrReplaceChild("cube_r178", CubeListBuilder.create().texOffs(76, 254).addBox(0.0F, 0.0F, 0.0F, 2.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-17.5F, -9.0F, 2.0F, -0.0742F, 0.1772F, -0.3993F));

		PartDefinition cube_r179 = updated_body.addOrReplaceChild("cube_r179", CubeListBuilder.create().texOffs(60, 254).addBox(0.0F, 0.0F, 0.0F, 2.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-17.5F, -13.0F, 2.0F, 0.0F, 0.192F, 0.0F));

		PartDefinition cube_r180 = updated_body.addOrReplaceChild("cube_r180", CubeListBuilder.create().texOffs(284, 222).addBox(0.0F, -1.0F, 0.0F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-16.5F, -9.0F, 2.0F, 0.4006F, 0.1933F, 0.0812F));

		PartDefinition cube_r181 = updated_body.addOrReplaceChild("cube_r181", CubeListBuilder.create().texOffs(142, 208).addBox(0.0F, 0.0F, -8.0F, 2.0F, 1.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.8F, -14.0F, 28.0F, 0.0F, 0.6458F, 0.0F));

		PartDefinition cube_r182 = updated_body.addOrReplaceChild("cube_r182", CubeListBuilder.create().texOffs(140, 280).addBox(1.9818F, 0.0F, -4.0F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(280, 132).addBox(-0.0182F, 0.0F, -4.0F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-12.25F, -14.0F, 17.4F, 0.0F, 0.3665F, 0.0F));

		PartDefinition cube_r183 = updated_body.addOrReplaceChild("cube_r183", CubeListBuilder.create().texOffs(250, 273).addBox(1.9818F, 0.0F, -5.0F, 2.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(224, 15).addBox(-0.0182F, 0.0F, -5.0F, 2.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-9.6F, -14.0F, 21.6F, 0.0F, 0.5585F, 0.0F));

		PartDefinition cube_r184 = updated_body.addOrReplaceChild("cube_r184", CubeListBuilder.create().texOffs(260, 125).addBox(2.0F, 0.0F, 0.0F, 3.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(92, 265).addBox(0.0F, 0.0F, 0.0F, 2.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-16.5F, -14.0F, 2.0F, 0.0F, 0.192F, 0.0F));

		PartDefinition cube_r185 = updated_body.addOrReplaceChild("cube_r185", CubeListBuilder.create().texOffs(260, 80).addBox(1.9969F, 0.0F, -0.011F, 3.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(142, 265).addBox(-0.0031F, 0.0F, -0.011F, 2.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-15.35F, -14.0F, 7.9F, 0.0F, 0.2793F, 0.0F));

		PartDefinition cube_r186 = updated_body.addOrReplaceChild("cube_r186", CubeListBuilder.create().texOffs(0, 249).addBox(0.0067F, -7.0F, -0.9873F, 2.0F, 8.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.65F, -7.0F, -19.65F, 0.0F, -0.2094F, 0.0F));

		PartDefinition cube_r187 = updated_body.addOrReplaceChild("cube_r187", CubeListBuilder.create().texOffs(98, 282).addBox(-0.2296F, 0.0F, -2.9956F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.1F, -10.0F, -45.5F, 0.3927F, -0.0349F, 0.0F));

		PartDefinition cube_r188 = updated_body.addOrReplaceChild("cube_r188", CubeListBuilder.create().texOffs(0, 216).addBox(-0.0202F, -4.0F, 6.0008F, 2.0F, 5.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(264, 144).addBox(-0.0202F, 0.0F, 0.0008F, 2.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.1F, -7.0F, -51.5F, 0.0F, -0.0349F, 0.0F));

		PartDefinition cube_r189 = updated_body.addOrReplaceChild("cube_r189", CubeListBuilder.create().texOffs(270, 282).addBox(0.0062F, -1.0F, 0.0318F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.6F, -7.0F, -55.5F, -0.0503F, -0.1209F, 0.3957F));

		PartDefinition cube_r190 = updated_body.addOrReplaceChild("cube_r190", CubeListBuilder.create().texOffs(114, 280).addBox(0.0062F, 0.0F, 0.0318F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.6F, -7.0F, -55.5F, 0.0F, -0.1309F, 0.0F));

		PartDefinition cube_r191 = updated_body.addOrReplaceChild("cube_r191", CubeListBuilder.create().texOffs(44, 197).addBox(0.0F, -1.0F, 1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -7.0F, -58.0F, -0.4233F, -0.3897F, 0.8702F));

		PartDefinition cube_r192 = updated_body.addOrReplaceChild("cube_r192", CubeListBuilder.create().texOffs(284, 218).addBox(0.0F, 0.0F, 0.0F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -7.0F, -58.0F, 0.0F, -0.5672F, 0.0F));

		PartDefinition cube_r193 = updated_body.addOrReplaceChild("cube_r193", CubeListBuilder.create().texOffs(264, 259).addBox(-1.1453F, -1.0F, -6.1384F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.55F, -13.0F, -27.3F, 0.1396F, -0.1222F, 0.0F));

		PartDefinition cube_r194 = updated_body.addOrReplaceChild("cube_r194", CubeListBuilder.create().texOffs(26, 124).addBox(-0.0242F, -5.0F, 2.993F, 2.0F, 6.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(22, 154).addBox(-0.0242F, -7.0F, 6.993F, 2.0F, 8.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(260, 32).addBox(-0.0242F, -5.0F, -2.007F, 2.0F, 6.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.55F, -7.0F, -36.5F, 0.0F, -0.1222F, 0.0F));

		PartDefinition cube_r195 = updated_body.addOrReplaceChild("cube_r195", CubeListBuilder.create().texOffs(0, 201).addBox(-1.719F, -8.0F, -6.356F, 2.0F, 8.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.5258F, -4.3F, -17.0886F, -0.299F, -0.1722F, 0.0155F));

		PartDefinition cube_r196 = updated_body.addOrReplaceChild("cube_r196", CubeListBuilder.create().texOffs(218, 248).addBox(-0.0241F, 0.0F, -4.007F, 2.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.55F, -12.0F, -36.5F, 0.1745F, -0.1222F, 0.0F));

		PartDefinition cube_r197 = updated_body.addOrReplaceChild("cube_r197", CubeListBuilder.create().texOffs(280, 68).addBox(-2.0062F, 0.0F, 0.0318F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.6F, -7.0F, -55.5F, 0.0F, 0.1309F, 0.0F));

		PartDefinition cube_r198 = updated_body.addOrReplaceChild("cube_r198", CubeListBuilder.create().texOffs(284, 186).addBox(-2.0F, 0.0F, 0.0F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -7.0F, -58.0F, 0.0F, 0.5672F, 0.0F));

		PartDefinition cube_r199 = updated_body.addOrReplaceChild("cube_r199", CubeListBuilder.create().texOffs(286, 63).addBox(0.0F, 5.7101F, 0.6985F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(26, 273).addBox(0.0F, 1.7101F, -0.3015F, 1.0F, 4.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(190, 205).addBox(29.0F, 5.7101F, 0.6985F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(272, 220).addBox(29.0F, 1.7101F, -0.3015F, 1.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-15.0F, -10.0F, -52.0F, 0.3491F, 0.0F, 0.0F));

		PartDefinition cube_r200 = updated_body.addOrReplaceChild("cube_r200", CubeListBuilder.create().texOffs(48, 286).addBox(-1.0F, -0.5F, -4.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(260, 43).addBox(-4.0F, -1.5F, -2.0F, 4.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-18.0F, -4.5F, -45.05F, 0.0F, -1.3003F, 0.0F));

		PartDefinition cube_r201 = updated_body.addOrReplaceChild("cube_r201", CubeListBuilder.create().texOffs(238, 0).addBox(0.0F, 0.0F, -1.0F, 1.0F, 2.25F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(204, 162).addBox(29.0F, 0.0F, -1.0F, 1.0F, 2.25F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-15.0F, -10.0F, -42.0F, 0.1222F, 0.0F, 0.0F));

		PartDefinition cube_r202 = updated_body.addOrReplaceChild("cube_r202", CubeListBuilder.create().texOffs(82, 279).addBox(0.0F, -0.2141F, -1.98F, 1.0F, 3.25F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(236, 248).addBox(29.0F, -0.2141F, -1.98F, 1.0F, 3.25F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-15.0F, -7.8F, -41.75F, 0.3316F, 0.0F, 0.0F));

		PartDefinition cube_r203 = updated_body.addOrReplaceChild("cube_r203", CubeListBuilder.create().texOffs(250, 286).addBox(0.0F, 0.01F, -2.002F, 1.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(228, 285).addBox(29.0F, 0.01F, -2.002F, 1.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-15.0F, -8.3F, -51.7F, 0.5236F, 0.0F, 0.0F));

		PartDefinition cube_r204 = updated_body.addOrReplaceChild("cube_r204", CubeListBuilder.create().texOffs(196, 284).addBox(0.0F, 0.01F, -2.002F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(256, 286).addBox(0.0F, 0.01F, -2.502F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(260, 279).addBox(29.0F, 0.01F, -2.502F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(160, 284).addBox(29.0F, 0.01F, -2.002F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-15.0F, -7.3F, -53.45F, 1.309F, 0.0F, 0.0F));

		PartDefinition cube_r205 = updated_body.addOrReplaceChild("cube_r205", CubeListBuilder.create().texOffs(252, 253).addBox(-3.9988F, 0.6F, 0.0166F, 4.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.05F, -6.5F, -52.3F, 0.0F, -0.2182F, 0.0349F));

		PartDefinition cube_r206 = updated_body.addOrReplaceChild("cube_r206", CubeListBuilder.create().texOffs(210, 125).addBox(-0.0249F, 0.5F, 0.0254F, 8.0F, 0.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(38, 280).addBox(1.9751F, -1.6F, 1.0254F, 1.0F, 3.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(256, 220).addBox(-0.0249F, 0.4F, 0.0254F, 7.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(8.0F, -5.4F, -55.75F, 0.0F, -0.2793F, 0.0F));

		PartDefinition cube_r207 = updated_body.addOrReplaceChild("cube_r207", CubeListBuilder.create().texOffs(234, 222).addBox(0.0F, 0.5F, 0.0F, 5.0F, 0.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(212, 4).addBox(0.0F, 0.4F, 0.0F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, -5.4F, -56.25F, 0.0F, -0.1047F, 0.0F));

		PartDefinition cube_r208 = updated_body.addOrReplaceChild("cube_r208", CubeListBuilder.create().texOffs(252, 174).addBox(-0.0012F, 0.6F, 0.0166F, 4.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.05F, -6.5F, -52.3F, 0.0F, 0.2182F, -0.0349F));

		PartDefinition cube_r209 = updated_body.addOrReplaceChild("cube_r209", CubeListBuilder.create().texOffs(178, 0).addBox(-2.0249F, 0.6F, 0.0254F, 11.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.0F, -6.5F, -51.75F, 0.01F, -0.2791F, -0.0014F));

		PartDefinition cube_r210 = updated_body.addOrReplaceChild("cube_r210", CubeListBuilder.create().texOffs(174, 165).addBox(-8.9751F, 0.6F, 0.0254F, 11.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.0F, -6.5F, -51.75F, 0.01F, 0.2791F, 0.0014F));

		PartDefinition cube_r211 = updated_body.addOrReplaceChild("cube_r211", CubeListBuilder.create().texOffs(286, 38).addBox(-0.0012F, 0.0F, 0.0166F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.05F, -6.0F, -52.3F, 0.0F, 0.2182F, 0.0F));

		PartDefinition cube_r212 = updated_body.addOrReplaceChild("cube_r212", CubeListBuilder.create().texOffs(286, 276).addBox(-0.0181F, 0.0F, -0.0642F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.3F, -7.0F, -48.75F, 0.0F, 0.3665F, 0.0F));

		PartDefinition cube_r213 = updated_body.addOrReplaceChild("cube_r213", CubeListBuilder.create().texOffs(216, 89).addBox(-5.9801F, 0.0F, -0.9578F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.9F, -7.0F, -47.95F, 0.0F, 0.5061F, 0.0F));

		PartDefinition cube_r214 = updated_body.addOrReplaceChild("cube_r214", CubeListBuilder.create().texOffs(286, 36).addBox(0.0F, 0.0F, -1.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-14.0F, -7.0F, -44.05F, 0.0F, 0.2443F, 0.0F));

		PartDefinition cube_r215 = updated_body.addOrReplaceChild("cube_r215", CubeListBuilder.create().texOffs(256, 218).addBox(-7.9751F, 0.0F, 0.0254F, 10.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.0F, -6.0F, -51.75F, 0.0F, 0.2793F, 0.0F));

		PartDefinition cube_r216 = updated_body.addOrReplaceChild("cube_r216", CubeListBuilder.create().texOffs(0, 167).addBox(-5.0F, 0.5F, 0.0F, 5.0F, 0.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(260, 206).addBox(-5.0F, 0.4F, 0.0F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, -5.4F, -56.25F, 0.0F, 0.1047F, 0.0F));

		PartDefinition cube_r217 = updated_body.addOrReplaceChild("cube_r217", CubeListBuilder.create().texOffs(208, 186).addBox(-7.9751F, 0.5F, 0.0254F, 8.0F, 0.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(250, 279).addBox(-2.9751F, -1.6F, 1.0254F, 1.0F, 3.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(266, 78).addBox(-6.9751F, 0.4F, 0.0254F, 7.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.0F, -5.4F, -55.75F, 0.0F, 0.2793F, 0.0F));

		PartDefinition cube_r218 = updated_body.addOrReplaceChild("cube_r218", CubeListBuilder.create().texOffs(286, 34).addBox(0.0F, -1.0F, -7.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, -4.0F, -48.25F, 0.0F, 0.0F, -0.3927F));

		PartDefinition cube_r219 = updated_body.addOrReplaceChild("cube_r219", CubeListBuilder.create().texOffs(282, 153).addBox(-3.9988F, 0.0F, 0.0166F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.05F, -6.0F, -52.3F, 0.0F, -0.2182F, 0.0F));

		PartDefinition cube_r220 = updated_body.addOrReplaceChild("cube_r220", CubeListBuilder.create().texOffs(256, 98).addBox(-2.0249F, 0.0F, 0.0254F, 10.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.0F, -6.0F, -51.75F, 0.0F, -0.2793F, 0.0F));

		PartDefinition cube_r221 = updated_body.addOrReplaceChild("cube_r221", CubeListBuilder.create().texOffs(216, 137).addBox(-4.0F, -1.0F, -7.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, -4.0F, -48.25F, 0.0F, 0.0F, 0.3927F));

		PartDefinition cube_r222 = updated_body.addOrReplaceChild("cube_r222", CubeListBuilder.create().texOffs(98, 124).addBox(-3.0F, 0.0F, -2.5F, 6.0F, 0.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -4.4F, -53.75F, 0.1571F, 0.0F, 0.0F));

		PartDefinition cube_r223 = updated_body.addOrReplaceChild("cube_r223", CubeListBuilder.create().texOffs(276, 247).addBox(-2.9819F, 0.0F, -0.0642F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.3F, -7.0F, -48.75F, 0.0F, -0.3665F, 0.0F));

		PartDefinition cube_r224 = updated_body.addOrReplaceChild("cube_r224", CubeListBuilder.create().texOffs(108, 50).addBox(-0.02F, 0.0F, -0.9578F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.9F, -7.0F, -47.95F, 0.0F, -0.5061F, 0.0F));

		PartDefinition cube_r225 = updated_body.addOrReplaceChild("cube_r225", CubeListBuilder.create().texOffs(78, 105).addBox(-4.0F, 0.0F, -1.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(14.0F, -7.0F, -44.05F, 0.0F, -0.2443F, 0.0F));

		PartDefinition cube_r226 = updated_body.addOrReplaceChild("cube_r226", CubeListBuilder.create().texOffs(68, 200).addBox(0.0F, -1.5F, -2.0F, 4.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(144, 285).addBox(0.0F, -0.5F, -4.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(18.0F, -4.5F, -45.05F, 0.0F, 1.3003F, 0.0F));

		PartDefinition cube_r227 = updated_body.addOrReplaceChild("cube_r227", CubeListBuilder.create().texOffs(256, 96).addBox(0.0F, 0.0F, 0.0F, 10.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, -11.0F, -37.0F, 0.0F, -0.1222F, 0.0F));

		PartDefinition cube_r228 = updated_body.addOrReplaceChild("cube_r228", CubeListBuilder.create().texOffs(230, 98).addBox(-2.0F, 0.0F, 0.0F, 12.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.5F, -7.8F, -37.0F, 0.0389F, -0.1158F, -0.3251F));

		PartDefinition cube_r229 = updated_body.addOrReplaceChild("cube_r229", CubeListBuilder.create().texOffs(282, 78).addBox(0.0165F, 0.0F, -1.005F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(9.45F, -9.0F, -31.0F, 0.0F, 0.4712F, 0.0F));

		PartDefinition cube_r230 = updated_body.addOrReplaceChild("cube_r230", CubeListBuilder.create().texOffs(192, 79).addBox(-13.1692F, 0.0F, -0.7664F, 13.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(14.0F, -11.0F, -35.0F, -0.1846F, 0.5187F, -0.2898F));

		PartDefinition cube_r231 = updated_body.addOrReplaceChild("cube_r231", CubeListBuilder.create().texOffs(236, 163).addBox(-1.0F, 0.0F, -1.0F, 7.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.2F, -9.0F, -27.2F, 0.0F, 0.733F, 0.0F));

		PartDefinition cube_r232 = updated_body.addOrReplaceChild("cube_r232", CubeListBuilder.create().texOffs(128, 40).addBox(0.1692F, 0.0F, -0.7664F, 13.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-14.0F, -11.0F, -35.0F, -0.1846F, -0.5187F, 0.2898F));

		PartDefinition cube_r233 = updated_body.addOrReplaceChild("cube_r233", CubeListBuilder.create().texOffs(230, 96).addBox(-10.0F, 0.0F, 0.0F, 12.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.5F, -7.8F, -37.0F, 0.0389F, 0.1158F, 0.3251F));

		PartDefinition cube_r234 = updated_body.addOrReplaceChild("cube_r234", CubeListBuilder.create().texOffs(128, 38).addBox(-10.0F, 0.0F, 0.0F, 10.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, -11.0F, -37.0F, 0.0F, 0.1222F, 0.0F));

		PartDefinition cube_r235 = updated_body.addOrReplaceChild("cube_r235", CubeListBuilder.create().texOffs(282, 12).addBox(-5.0165F, 0.0F, -1.005F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-9.45F, -9.0F, -31.0F, 0.0F, -0.4712F, 0.0F));

		PartDefinition cube_r236 = updated_body.addOrReplaceChild("cube_r236", CubeListBuilder.create().texOffs(172, 231).addBox(-6.0F, 0.0F, -1.0F, 7.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.2F, -9.0F, -27.2F, 0.0F, -0.733F, 0.0F));

		PartDefinition cube_r237 = updated_body.addOrReplaceChild("cube_r237", CubeListBuilder.create().texOffs(70, 279).addBox(0.0F, 0.0F, -4.0F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(248, 137).addBox(2.0F, 0.01F, -6.0F, 4.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(258, 249).addBox(3.0F, 0.02F, -7.0F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-16.5F, -14.0F, -5.0F, 0.0F, -0.1309F, 0.0F));

		PartDefinition cube_r238 = updated_body.addOrReplaceChild("cube_r238", CubeListBuilder.create().texOffs(22, 248).addBox(-6.0F, 0.01F, -6.0F, 4.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(234, 187).addBox(-6.0F, 0.02F, -7.0F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(278, 229).addBox(-2.0F, 0.0F, -4.0F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(16.5F, -14.0F, -5.0F, 0.0F, 0.1309F, 0.0F));

		PartDefinition cube_r239 = updated_body.addOrReplaceChild("cube_r239", CubeListBuilder.create().texOffs(12, 279).addBox(0.0F, 0.0F, -4.0F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-16.5F, -10.0F, -5.0F, 0.0F, -0.1309F, 0.0F));

		PartDefinition cube_r240 = updated_body.addOrReplaceChild("cube_r240", CubeListBuilder.create().texOffs(18, 221).addBox(0.0F, 0.0F, -4.0F, 7.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-16.5F, -13.0F, -5.0F, 0.0F, -0.1309F, 0.0F));

		PartDefinition cube_r241 = updated_body.addOrReplaceChild("cube_r241", CubeListBuilder.create().texOffs(212, 220).addBox(-7.0F, 0.0F, -4.0F, 7.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(16.5F, -13.0F, -5.0F, 0.0F, 0.1309F, 0.0F));

		PartDefinition cube_r242 = updated_body.addOrReplaceChild("cube_r242", CubeListBuilder.create().texOffs(210, 278).addBox(-2.0F, 0.0F, -4.0F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(16.5F, -10.0F, -5.0F, 0.0F, 0.1309F, 0.0F));

		PartDefinition cube_r243 = updated_body.addOrReplaceChild("cube_r243", CubeListBuilder.create().texOffs(252, 88).addBox(-1.9969F, -0.01F, -0.011F, 2.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(11.1F, -14.0F, 7.95F, 0.0F, -0.2094F, 0.0F));

		PartDefinition cube_r244 = updated_body.addOrReplaceChild("cube_r244", CubeListBuilder.create().texOffs(258, 117).addBox(-1.9969F, -0.01F, -0.011F, 2.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(9.65F, -14.0F, 14.8F, 0.0F, -0.2793F, 0.0F));

		PartDefinition cube_r245 = updated_body.addOrReplaceChild("cube_r245", CubeListBuilder.create().texOffs(212, 0).addBox(-11.0F, 0.0F, 0.0F, 11.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(12.5F, -11.0F, 35.5F, 0.0F, -0.7854F, 0.0F));

		PartDefinition cube_r246 = updated_body.addOrReplaceChild("cube_r246", CubeListBuilder.create().texOffs(0, 273).addBox(0.0017F, -0.01F, -4.0075F, 2.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-10.85F, -14.0F, -3.95F, 0.0F, -0.2182F, 0.0F));

		PartDefinition cube_r247 = updated_body.addOrReplaceChild("cube_r247", CubeListBuilder.create().texOffs(272, 249).addBox(-2.0017F, -0.01F, -4.0075F, 2.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(10.85F, -14.0F, -3.95F, 0.0F, 0.2182F, 0.0F));

		PartDefinition cube_r248 = updated_body.addOrReplaceChild("cube_r248", CubeListBuilder.create().texOffs(222, 156).addBox(-2.0F, -0.01F, -5.0F, 2.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(11.5F, -14.0F, 1.0F, 0.0F, 0.1309F, 0.0F));

		PartDefinition cube_r249 = updated_body.addOrReplaceChild("cube_r249", CubeListBuilder.create().texOffs(278, 127).addBox(-2.0F, -0.01F, 0.0F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(11.5F, -14.0F, 4.0F, 0.0F, -0.1047F, 0.0F));

		PartDefinition cube_r250 = updated_body.addOrReplaceChild("cube_r250", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, 1.0F, -25.0F, 10.0F, 1.0F, 25.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-15.8F, -5.0F, 24.0F, 0.0F, 0.0349F, 0.0F));

		PartDefinition cube_r251 = updated_body.addOrReplaceChild("cube_r251", CubeListBuilder.create().texOffs(268, 187).addBox(0.0F, -0.0353F, -4.9913F, 3.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.8F, -6.9494F, 30.7639F, 0.5458F, 0.7419F, 0.3894F));

		PartDefinition steering_wheel = body.addOrReplaceChild("steering_wheel", CubeListBuilder.create().texOffs(200, 147).addBox(3.0F, -2.6071F, -0.5F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(246, 195).addBox(-3.0F, 2.3929F, -0.5F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(278, 93).addBox(-3.0F, -2.8571F, 0.0F, 6.0F, 6.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(286, 88).addBox(-2.0F, -0.8571F, -0.5F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(142, 202).addBox(-4.0F, -2.6071F, -0.5F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(274, 184).addBox(-3.0F, -3.6071F, -0.5F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(276, 287).addBox(-1.0F, 0.1429F, -0.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(286, 170).addBox(-1.0F, -0.8571F, -2.5F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.2992F, 3.3722F, -17.354F, 0.2182F, 0.0F, 0.0F));

		PartDefinition shifter = body.addOrReplaceChild("shifter", CubeListBuilder.create().texOffs(232, 57).addBox(-0.75F, -2.5F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(282, 287).addBox(-0.75F, -3.5F, -0.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.5492F, 8.0151F, -13.354F));

		PartDefinition Front_Left_Wheel = body.addOrReplaceChild("Front_Left_Wheel", CubeListBuilder.create().texOffs(124, 144).addBox(0.0F, -5.7F, -5.61F, 0.0F, 11.0F, 11.0F, new CubeDeformation(0.0F))
		.texOffs(124, 144).addBox(-4.75F, -5.7F, -5.61F, 0.0F, 11.0F, 11.0F, new CubeDeformation(0.0F))
		.texOffs(178, 274).addBox(-0.75F, -1.95F, -1.86F, 2.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(92, 254).addBox(-6.0F, -3.7F, -1.61F, 1.0F, 7.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(40, 215).addBox(-6.0F, -1.7F, -3.61F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(146, 188).addBox(-6.0F, -2.7F, -2.61F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(62, 220).addBox(-6.0F, -1.7F, 2.39F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(146, 194).addBox(-6.0F, -2.7F, 1.39F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(18.7008F, 4.4651F, -41.244F));

		PartDefinition cube_r252 = Front_Left_Wheel.addOrReplaceChild("cube_r252", CubeListBuilder.create().texOffs(94, 286).addBox(-0.5F, 0.0F, -1.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.5F, 1.3F, 3.39F, -0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r253 = Front_Left_Wheel.addOrReplaceChild("cube_r253", CubeListBuilder.create().texOffs(222, 278).addBox(-0.5F, -1.05F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.5F, 3.35F, 1.39F, 0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r254 = Front_Left_Wheel.addOrReplaceChild("cube_r254", CubeListBuilder.create().texOffs(152, 280).addBox(-0.5F, -2.0F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.5F, -1.7F, -3.61F, -0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r255 = Front_Left_Wheel.addOrReplaceChild("cube_r255", CubeListBuilder.create().texOffs(48, 283).addBox(-0.5F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.5F, 1.3F, -3.61F, 0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r256 = Front_Left_Wheel.addOrReplaceChild("cube_r256", CubeListBuilder.create().texOffs(270, 193).addBox(-0.5F, -1.05F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.5F, 3.35F, -1.61F, -0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r257 = Front_Left_Wheel.addOrReplaceChild("cube_r257", CubeListBuilder.create().texOffs(88, 270).addBox(-0.5F, 0.05F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.5F, -3.75F, -1.61F, 0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r258 = Front_Left_Wheel.addOrReplaceChild("cube_r258", CubeListBuilder.create().texOffs(230, 184).addBox(-0.5F, 0.05F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.5F, -3.75F, 1.39F, -0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r259 = Front_Left_Wheel.addOrReplaceChild("cube_r259", CubeListBuilder.create().texOffs(48, 280).addBox(-0.5F, -2.0F, -1.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.5F, -1.7F, 3.39F, 0.7854F, 0.0F, 0.0F));

		PartDefinition rim_r1 = Front_Left_Wheel.addOrReplaceChild("rim_r1", CubeListBuilder.create().texOffs(284, 5).addBox(1.0F, -0.2322F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(210, 283).addBox(1.0F, -4.2322F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 0.05F, 0.14F, -0.7854F, 0.0F, 0.0F));

		PartDefinition rim_r2 = Front_Left_Wheel.addOrReplaceChild("rim_r2", CubeListBuilder.create().texOffs(126, 283).addBox(1.0F, -2.0F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(244, 263).addBox(1.0F, -6.0F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 1.3F, 1.39F, 0.7854F, 0.0F, 0.0F));

		PartDefinition right_wheel_r1 = Front_Left_Wheel.addOrReplaceChild("right_wheel_r1", CubeListBuilder.create().texOffs(233, 51).addBox(-2.0F, -1.4697F, -7.5347F, 7.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 0.0F, 0.7854F, 0.0F, 0.0F));

		PartDefinition right_wheel_r2 = Front_Left_Wheel.addOrReplaceChild("right_wheel_r2", CubeListBuilder.create().texOffs(63, 232).addBox(-2.0F, -1.4114F, -7.5036F, 7.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 0.0F, -2.7576F, 0.0F, 0.0F));

		PartDefinition right_wheel_r3 = Front_Left_Wheel.addOrReplaceChild("right_wheel_r3", CubeListBuilder.create().texOffs(231, 174).addBox(-2.0F, -1.5F, -7.51F, 7.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 0.0F, 3.1416F, 0.0F, 0.0F));

		PartDefinition right_wheel_r4 = Front_Left_Wheel.addOrReplaceChild("right_wheel_r4", CubeListBuilder.create().texOffs(231, 167).addBox(-2.0F, -1.4697F, -7.5347F, 7.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 0.0F, -2.3562F, 0.0F, 0.0F));

		PartDefinition right_wheel_r5 = Front_Left_Wheel.addOrReplaceChild("right_wheel_r5", CubeListBuilder.create().texOffs(231, 90).addBox(-2.0F, -1.5226F, -7.5449F, 7.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 0.0F, -1.9635F, 0.0F, 0.0F));

		PartDefinition right_wheel_r6 = Front_Left_Wheel.addOrReplaceChild("right_wheel_r6", CubeListBuilder.create().texOffs(231, 26).addBox(-2.0F, -1.5F, -7.51F, 7.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 0.0F, -1.5708F, 0.0F, 0.0F));

		PartDefinition right_wheel_r7 = Front_Left_Wheel.addOrReplaceChild("right_wheel_r7", CubeListBuilder.create().texOffs(229, 103).addBox(-2.0F, -1.4114F, -7.5036F, 7.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 0.0F, -1.1868F, 0.0F, 0.0F));

		PartDefinition right_wheel_r8 = Front_Left_Wheel.addOrReplaceChild("right_wheel_r8", CubeListBuilder.create().texOffs(1, 229).addBox(-2.0F, -1.4697F, -7.5347F, 7.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 0.0F, -0.7854F, 0.0F, 0.0F));

		PartDefinition right_wheel_r9 = Front_Left_Wheel.addOrReplaceChild("right_wheel_r9", CubeListBuilder.create().texOffs(211, 228).addBox(-2.0F, -1.5226F, -7.5449F, 7.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 0.0F, -0.3927F, 0.0F, 0.0F));

		PartDefinition right_wheel_r10 = Front_Left_Wheel.addOrReplaceChild("right_wheel_r10", CubeListBuilder.create().texOffs(41, 228).addBox(-3.5F, -1.472F, -7.5999F, 7.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.5F, 0.0F, 0.0F, -0.0087F, 0.0F, 0.0F));

		PartDefinition right_wheel_r11 = Front_Left_Wheel.addOrReplaceChild("right_wheel_r11", CubeListBuilder.create().texOffs(189, 227).addBox(-2.0F, -1.5F, -7.51F, 7.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 0.0F, 1.5708F, 0.0F, 0.0F));

		PartDefinition right_wheel_r12 = Front_Left_Wheel.addOrReplaceChild("right_wheel_r12", CubeListBuilder.create().texOffs(227, 138).addBox(-2.0F, -1.4114F, -7.5036F, 7.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 0.0F, 1.9548F, 0.0F, 0.0F));

		PartDefinition right_wheel_r13 = Front_Left_Wheel.addOrReplaceChild("right_wheel_r13", CubeListBuilder.create().texOffs(149, 226).addBox(-2.0F, -1.4697F, -7.5347F, 7.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 0.0F, 2.3562F, 0.0F, 0.0F));

		PartDefinition right_wheel_r14 = Front_Left_Wheel.addOrReplaceChild("right_wheel_r14", CubeListBuilder.create().texOffs(127, 226).addBox(-2.0F, -1.5226F, -7.5449F, 7.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 0.0F, 2.7489F, 0.0F, 0.0F));

		PartDefinition right_wheel_r15 = Front_Left_Wheel.addOrReplaceChild("right_wheel_r15", CubeListBuilder.create().texOffs(225, 192).addBox(-2.0F, -1.5226F, -7.5449F, 7.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 0.0F, 1.1781F, 0.0F, 0.0F));

		PartDefinition right_wheel_r16 = Front_Left_Wheel.addOrReplaceChild("right_wheel_r16", CubeListBuilder.create().texOffs(63, 225).addBox(-2.0F, -1.4114F, -7.5036F, 7.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 0.0F, 0.384F, 0.0F, 0.0F));

		PartDefinition Front_Right_Wheel = body.addOrReplaceChild("Front_Right_Wheel", CubeListBuilder.create().texOffs(0, 145).addBox(0.0F, -5.7F, -5.61F, 0.0F, 11.0F, 11.0F, new CubeDeformation(0.0F))
		.texOffs(0, 145).addBox(4.75F, -5.7F, -5.61F, 0.0F, 11.0F, 11.0F, new CubeDeformation(0.0F))
		.texOffs(264, 274).addBox(-1.25F, -1.95F, -1.86F, 2.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(182, 118).addBox(5.0F, -2.7F, -2.61F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(154, 125).addBox(5.0F, -1.7F, -3.61F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(164, 208).addBox(5.0F, -3.7F, -1.61F, 1.0F, 7.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(24, 192).addBox(5.0F, -1.7F, 2.39F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(24, 186).addBox(5.0F, -2.7F, 1.39F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-19.2992F, 4.4651F, -41.244F));

		PartDefinition cube_r260 = Front_Right_Wheel.addOrReplaceChild("cube_r260", CubeListBuilder.create().texOffs(174, 171).addBox(-0.5F, -1.05F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.5F, 3.35F, 1.39F, 0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r261 = Front_Right_Wheel.addOrReplaceChild("cube_r261", CubeListBuilder.create().texOffs(178, 13).addBox(-0.5F, -1.05F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.5F, 3.35F, -1.61F, -0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r262 = Front_Right_Wheel.addOrReplaceChild("cube_r262", CubeListBuilder.create().texOffs(70, 275).addBox(-0.5F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.5F, 1.3F, -3.61F, 0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r263 = Front_Right_Wheel.addOrReplaceChild("cube_r263", CubeListBuilder.create().texOffs(70, 272).addBox(-0.5F, 0.0F, -1.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.5F, 1.3F, 3.39F, -0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r264 = Front_Right_Wheel.addOrReplaceChild("cube_r264", CubeListBuilder.create().texOffs(156, 40).addBox(-0.5F, 0.05F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.5F, -3.75F, 1.39F, -0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r265 = Front_Right_Wheel.addOrReplaceChild("cube_r265", CubeListBuilder.create().texOffs(268, 170).addBox(-0.5F, -2.0F, -1.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.5F, -1.7F, 3.39F, 0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r266 = Front_Right_Wheel.addOrReplaceChild("cube_r266", CubeListBuilder.create().texOffs(22, 152).addBox(-0.5F, 0.05F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.5F, -3.75F, -1.61F, 0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r267 = Front_Right_Wheel.addOrReplaceChild("cube_r267", CubeListBuilder.create().texOffs(254, 54).addBox(-0.5F, -2.0F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.5F, -1.7F, -3.61F, -0.7854F, 0.0F, 0.0F));

		PartDefinition rim_r3 = Front_Right_Wheel.addOrReplaceChild("rim_r3", CubeListBuilder.create().texOffs(284, 42).addBox(-3.0F, -0.2322F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(284, 22).addBox(-3.0F, -4.2322F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 0.05F, 0.14F, -0.7854F, 0.0F, 0.0F));

		PartDefinition rim_r4 = Front_Right_Wheel.addOrReplaceChild("rim_r4", CubeListBuilder.create().texOffs(16, 284).addBox(-3.0F, -2.0F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(8, 284).addBox(-3.0F, -6.0F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 1.3F, 1.39F, 0.7854F, 0.0F, 0.0F));

		PartDefinition left_wheel_r1 = Front_Right_Wheel.addOrReplaceChild("left_wheel_r1", CubeListBuilder.create().texOffs(237, 58).addBox(-5.0F, -1.4697F, -7.5347F, 7.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 0.0F, 0.0F, 0.7854F, 0.0F, 0.0F));

		PartDefinition left_wheel_r2 = Front_Right_Wheel.addOrReplaceChild("left_wheel_r2", CubeListBuilder.create().texOffs(1, 236).addBox(-5.0F, -1.4114F, -7.5036F, 7.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 0.0F, 0.0F, -2.7576F, 0.0F, 0.0F));

		PartDefinition left_wheel_r3 = Front_Right_Wheel.addOrReplaceChild("left_wheel_r3", CubeListBuilder.create().texOffs(217, 235).addBox(-5.0F, -1.5F, -7.51F, 7.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 0.0F, 0.0F, 3.1416F, 0.0F, 0.0F));

		PartDefinition left_wheel_r4 = Front_Right_Wheel.addOrReplaceChild("left_wheel_r4", CubeListBuilder.create().texOffs(235, 216).addBox(-5.0F, -1.4697F, -7.5347F, 7.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 0.0F, 0.0F, -2.3562F, 0.0F, 0.0F));

		PartDefinition left_wheel_r5 = Front_Right_Wheel.addOrReplaceChild("left_wheel_r5", CubeListBuilder.create().texOffs(195, 235).addBox(-5.0F, -1.5226F, -7.5449F, 7.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 0.0F, 0.0F, -1.9635F, 0.0F, 0.0F));

		PartDefinition left_wheel_r6 = Front_Right_Wheel.addOrReplaceChild("left_wheel_r6", CubeListBuilder.create().texOffs(235, 181).addBox(-5.0F, -1.5F, -7.51F, 7.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 0.0F, 0.0F, -1.5708F, 0.0F, 0.0F));

		PartDefinition left_wheel_r7 = Front_Right_Wheel.addOrReplaceChild("left_wheel_r7", CubeListBuilder.create().texOffs(235, 117).addBox(-5.0F, -1.4114F, -7.5036F, 7.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 0.0F, 0.0F, -1.1868F, 0.0F, 0.0F));

		PartDefinition left_wheel_r8 = Front_Right_Wheel.addOrReplaceChild("left_wheel_r8", CubeListBuilder.create().texOffs(235, 110).addBox(-5.0F, -1.4697F, -7.5347F, 7.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 0.0F, 0.0F, -0.7854F, 0.0F, 0.0F));

		PartDefinition left_wheel_r9 = Front_Right_Wheel.addOrReplaceChild("left_wheel_r9", CubeListBuilder.create().texOffs(23, 235).addBox(-5.0F, -1.5226F, -7.5449F, 7.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 0.0F, 0.0F, -0.3927F, 0.0F, 0.0F));

		PartDefinition left_wheel_r10 = Front_Right_Wheel.addOrReplaceChild("left_wheel_r10", CubeListBuilder.create().texOffs(173, 234).addBox(-3.5F, -1.472F, -7.5999F, 7.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.5F, 0.0F, 0.0F, -0.0087F, 0.0F, 0.0F));

		PartDefinition left_wheel_r11 = Front_Right_Wheel.addOrReplaceChild("left_wheel_r11", CubeListBuilder.create().texOffs(233, 228).addBox(-5.0F, -1.5F, -7.51F, 7.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 0.0F, 0.0F, 1.5708F, 0.0F, 0.0F));

		PartDefinition left_wheel_r12 = Front_Right_Wheel.addOrReplaceChild("left_wheel_r12", CubeListBuilder.create().texOffs(233, 209).addBox(-5.0F, -1.4114F, -7.5036F, 7.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 0.0F, 0.0F, 1.9548F, 0.0F, 0.0F));

		PartDefinition left_wheel_r13 = Front_Right_Wheel.addOrReplaceChild("left_wheel_r13", CubeListBuilder.create().texOffs(151, 233).addBox(-5.0F, -1.4697F, -7.5347F, 7.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 0.0F, 0.0F, 2.3562F, 0.0F, 0.0F));

		PartDefinition left_wheel_r14 = Front_Right_Wheel.addOrReplaceChild("left_wheel_r14", CubeListBuilder.create().texOffs(129, 233).addBox(-5.0F, -1.5226F, -7.5449F, 7.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 0.0F, 0.0F, 2.7489F, 0.0F, 0.0F));

		PartDefinition left_wheel_r15 = Front_Right_Wheel.addOrReplaceChild("left_wheel_r15", CubeListBuilder.create().texOffs(107, 233).addBox(-5.0F, -1.5226F, -7.5449F, 7.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 0.0F, 0.0F, 1.1781F, 0.0F, 0.0F));

		PartDefinition left_wheel_r16 = Front_Right_Wheel.addOrReplaceChild("left_wheel_r16", CubeListBuilder.create().texOffs(85, 233).addBox(-5.0F, -1.4114F, -7.5036F, 7.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 0.0F, 0.0F, 0.384F, 0.0F, 0.0F));

		PartDefinition Back_Left_Wheel = body.addOrReplaceChild("Back_Left_Wheel", CubeListBuilder.create().texOffs(52, 145).addBox(0.0F, -5.7F, -5.61F, 0.0F, 11.0F, 11.0F, new CubeDeformation(0.0F))
		.texOffs(52, 145).addBox(-4.75F, -5.7F, -5.61F, 0.0F, 11.0F, 11.0F, new CubeDeformation(0.0F))
		.texOffs(128, 275).addBox(-0.75F, -1.95F, -1.86F, 2.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(92, 254).addBox(-6.0F, -3.7F, -1.61F, 1.0F, 7.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(40, 215).addBox(-6.0F, -1.7F, -3.61F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(146, 188).addBox(-6.0F, -2.7F, -2.61F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(62, 220).addBox(-6.0F, -1.7F, 2.39F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(146, 194).addBox(-6.0F, -2.7F, 1.39F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(17.7008F, 4.4651F, 28.756F));

		PartDefinition cube_r268 = Back_Left_Wheel.addOrReplaceChild("cube_r268", CubeListBuilder.create().texOffs(94, 286).addBox(-0.5F, 0.0F, -1.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.5F, 1.3F, 3.39F, -0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r269 = Back_Left_Wheel.addOrReplaceChild("cube_r269", CubeListBuilder.create().texOffs(222, 278).addBox(-0.5F, -1.05F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.5F, 3.35F, 1.39F, 0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r270 = Back_Left_Wheel.addOrReplaceChild("cube_r270", CubeListBuilder.create().texOffs(152, 280).addBox(-0.5F, -2.0F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.5F, -1.7F, -3.61F, -0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r271 = Back_Left_Wheel.addOrReplaceChild("cube_r271", CubeListBuilder.create().texOffs(48, 283).addBox(-0.5F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.5F, 1.3F, -3.61F, 0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r272 = Back_Left_Wheel.addOrReplaceChild("cube_r272", CubeListBuilder.create().texOffs(270, 193).addBox(-0.5F, -1.05F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.5F, 3.35F, -1.61F, -0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r273 = Back_Left_Wheel.addOrReplaceChild("cube_r273", CubeListBuilder.create().texOffs(88, 270).addBox(-0.5F, 0.05F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.5F, -3.75F, -1.61F, 0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r274 = Back_Left_Wheel.addOrReplaceChild("cube_r274", CubeListBuilder.create().texOffs(230, 184).addBox(-0.5F, 0.05F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.5F, -3.75F, 1.39F, -0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r275 = Back_Left_Wheel.addOrReplaceChild("cube_r275", CubeListBuilder.create().texOffs(48, 280).addBox(-0.5F, -2.0F, -1.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.5F, -1.7F, 3.39F, 0.7854F, 0.0F, 0.0F));

		PartDefinition rim_r5 = Back_Left_Wheel.addOrReplaceChild("rim_r5", CubeListBuilder.create().texOffs(78, 284).addBox(1.0F, -0.2322F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(70, 284).addBox(1.0F, -4.2322F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 0.05F, 0.14F, -0.7854F, 0.0F, 0.0F));

		PartDefinition rim_r6 = Back_Left_Wheel.addOrReplaceChild("rim_r6", CubeListBuilder.create().texOffs(284, 56).addBox(1.0F, -2.0F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(284, 49).addBox(1.0F, -6.0F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 1.3F, 1.39F, 0.7854F, 0.0F, 0.0F));

		PartDefinition right_wheel_r17 = Back_Left_Wheel.addOrReplaceChild("right_wheel_r17", CubeListBuilder.create().texOffs(89, 240).addBox(-2.0F, -1.4697F, -7.5347F, 7.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 0.0F, 0.7854F, 0.0F, 0.0F));

		PartDefinition right_wheel_r18 = Back_Left_Wheel.addOrReplaceChild("right_wheel_r18", CubeListBuilder.create().texOffs(127, 219).addBox(-2.0F, -1.4114F, -7.5036F, 7.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 0.0F, -2.7576F, 0.0F, 0.0F));

		PartDefinition right_wheel_r19 = Back_Left_Wheel.addOrReplaceChild("right_wheel_r19", CubeListBuilder.create().texOffs(67, 240).addBox(-2.0F, -1.5F, -7.51F, 7.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 0.0F, 3.1416F, 0.0F, 0.0F));

		PartDefinition right_wheel_r20 = Back_Left_Wheel.addOrReplaceChild("right_wheel_r20", CubeListBuilder.create().texOffs(239, 235).addBox(-2.0F, -1.4697F, -7.5347F, 7.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 0.0F, -2.3562F, 0.0F, 0.0F));

		PartDefinition right_wheel_r21 = Back_Left_Wheel.addOrReplaceChild("right_wheel_r21", CubeListBuilder.create().texOffs(239, 131).addBox(-2.0F, -1.5226F, -7.5449F, 7.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 0.0F, -1.9635F, 0.0F, 0.0F));

		PartDefinition right_wheel_r22 = Back_Left_Wheel.addOrReplaceChild("right_wheel_r22", CubeListBuilder.create().texOffs(239, 82).addBox(-2.0F, -1.5F, -7.51F, 7.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 0.0F, -1.5708F, 0.0F, 0.0F));

		PartDefinition right_wheel_r23 = Back_Left_Wheel.addOrReplaceChild("right_wheel_r23", CubeListBuilder.create().texOffs(217, 131).addBox(-2.0F, -1.4114F, -7.5036F, 7.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 0.0F, -1.1868F, 0.0F, 0.0F));

		PartDefinition right_wheel_r24 = Back_Left_Wheel.addOrReplaceChild("right_wheel_r24", CubeListBuilder.create().texOffs(45, 239).addBox(-2.0F, -1.4697F, -7.5347F, 7.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 0.0F, -0.7854F, 0.0F, 0.0F));

		PartDefinition right_wheel_r25 = Back_Left_Wheel.addOrReplaceChild("right_wheel_r25", CubeListBuilder.create().texOffs(239, 40).addBox(-2.0F, -1.5226F, -7.5449F, 7.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 0.0F, -0.3927F, 0.0F, 0.0F));

		PartDefinition right_wheel_r26 = Back_Left_Wheel.addOrReplaceChild("right_wheel_r26", CubeListBuilder.create().texOffs(239, 33).addBox(-3.5F, -1.472F, -7.5999F, 7.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.5F, 0.0F, 0.0F, -0.0087F, 0.0F, 0.0F));

		PartDefinition right_wheel_r27 = Back_Left_Wheel.addOrReplaceChild("right_wheel_r27", CubeListBuilder.create().texOffs(239, 16).addBox(-2.0F, -1.5F, -7.51F, 7.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 0.0F, 1.5708F, 0.0F, 0.0F));

		PartDefinition right_wheel_r28 = Back_Left_Wheel.addOrReplaceChild("right_wheel_r28", CubeListBuilder.create().texOffs(217, 44).addBox(-2.0F, -1.4114F, -7.5036F, 7.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 0.0F, 1.9548F, 0.0F, 0.0F));

		PartDefinition right_wheel_r29 = Back_Left_Wheel.addOrReplaceChild("right_wheel_r29", CubeListBuilder.create().texOffs(237, 157).addBox(-2.0F, -1.4697F, -7.5347F, 7.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 0.0F, 2.3562F, 0.0F, 0.0F));

		PartDefinition right_wheel_r30 = Back_Left_Wheel.addOrReplaceChild("right_wheel_r30", CubeListBuilder.create().texOffs(237, 124).addBox(-2.0F, -1.5226F, -7.5449F, 7.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 0.0F, 2.7489F, 0.0F, 0.0F));

		PartDefinition right_wheel_r31 = Back_Left_Wheel.addOrReplaceChild("right_wheel_r31", CubeListBuilder.create().texOffs(237, 65).addBox(-2.0F, -1.5226F, -7.5449F, 7.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 0.0F, 1.1781F, 0.0F, 0.0F));

		PartDefinition right_wheel_r32 = Back_Left_Wheel.addOrReplaceChild("right_wheel_r32", CubeListBuilder.create().texOffs(125, 167).addBox(-2.0F, -1.4114F, -7.5036F, 7.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 0.0F, 0.384F, 0.0F, 0.0F));

		PartDefinition Back_Right_Wheel = body.addOrReplaceChild("Back_Right_Wheel", CubeListBuilder.create().texOffs(74, 145).addBox(0.0F, -5.7F, -5.61F, 0.0F, 11.0F, 11.0F, new CubeDeformation(0.0F))
		.texOffs(74, 145).addBox(4.75F, -5.7F, -5.61F, 0.0F, 11.0F, 11.0F, new CubeDeformation(0.0F))
		.texOffs(276, 113).addBox(-1.25F, -1.95F, -1.86F, 2.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(92, 254).mirror().addBox(5.0F, -3.7F, -1.61F, 1.0F, 7.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(40, 215).mirror().addBox(5.0F, -1.7F, -3.61F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(146, 188).mirror().addBox(5.0F, -2.7F, -2.61F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(62, 220).mirror().addBox(5.0F, -1.7F, 2.39F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(146, 194).mirror().addBox(5.0F, -2.7F, 1.39F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-18.2992F, 4.4651F, 28.756F));

		PartDefinition cube_r276 = Back_Right_Wheel.addOrReplaceChild("cube_r276", CubeListBuilder.create().texOffs(94, 286).mirror().addBox(-0.5F, 0.0F, -1.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(5.5F, 1.3F, 3.39F, -0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r277 = Back_Right_Wheel.addOrReplaceChild("cube_r277", CubeListBuilder.create().texOffs(222, 278).mirror().addBox(-0.5F, -1.05F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(5.5F, 3.35F, 1.39F, 0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r278 = Back_Right_Wheel.addOrReplaceChild("cube_r278", CubeListBuilder.create().texOffs(152, 280).mirror().addBox(-0.5F, -2.0F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(5.5F, -1.7F, -3.61F, -0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r279 = Back_Right_Wheel.addOrReplaceChild("cube_r279", CubeListBuilder.create().texOffs(48, 283).mirror().addBox(-0.5F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(5.5F, 1.3F, -3.61F, 0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r280 = Back_Right_Wheel.addOrReplaceChild("cube_r280", CubeListBuilder.create().texOffs(270, 193).mirror().addBox(-0.5F, -1.05F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(5.5F, 3.35F, -1.61F, -0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r281 = Back_Right_Wheel.addOrReplaceChild("cube_r281", CubeListBuilder.create().texOffs(88, 270).mirror().addBox(-0.5F, 0.05F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(5.5F, -3.75F, -1.61F, 0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r282 = Back_Right_Wheel.addOrReplaceChild("cube_r282", CubeListBuilder.create().texOffs(230, 184).mirror().addBox(-0.5F, 0.05F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(5.5F, -3.75F, 1.39F, -0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r283 = Back_Right_Wheel.addOrReplaceChild("cube_r283", CubeListBuilder.create().texOffs(48, 280).mirror().addBox(-0.5F, -2.0F, -1.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(5.5F, -1.7F, 3.39F, 0.7854F, 0.0F, 0.0F));

		PartDefinition rim_r7 = Back_Right_Wheel.addOrReplaceChild("rim_r7", CubeListBuilder.create().texOffs(284, 163).addBox(-3.0F, -0.2322F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(152, 284).addBox(-3.0F, -4.2322F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 0.05F, 0.14F, -0.7854F, 0.0F, 0.0F));

		PartDefinition rim_r8 = Back_Right_Wheel.addOrReplaceChild("rim_r8", CubeListBuilder.create().texOffs(284, 137).addBox(-3.0F, -2.0F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(284, 99).addBox(-3.0F, -6.0F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 1.3F, 1.39F, 0.7854F, 0.0F, 0.0F));

		PartDefinition left_wheel_r17 = Back_Right_Wheel.addOrReplaceChild("left_wheel_r17", CubeListBuilder.create().texOffs(243, 145).addBox(-5.0F, -1.4697F, -7.5347F, 7.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 0.0F, 0.0F, 0.7854F, 0.0F, 0.0F));

		PartDefinition left_wheel_r18 = Back_Right_Wheel.addOrReplaceChild("left_wheel_r18", CubeListBuilder.create().texOffs(221, 75).addBox(-5.0F, -1.4114F, -7.5036F, 7.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 0.0F, 0.0F, -2.7576F, 0.0F, 0.0F));

		PartDefinition left_wheel_r19 = Back_Right_Wheel.addOrReplaceChild("left_wheel_r19", CubeListBuilder.create().texOffs(243, 72).addBox(-5.0F, -1.5F, -7.51F, 7.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 0.0F, 0.0F, 3.1416F, 0.0F, 0.0F));

		PartDefinition left_wheel_r20 = Back_Right_Wheel.addOrReplaceChild("left_wheel_r20", CubeListBuilder.create().texOffs(243, 8).addBox(-5.0F, -1.4697F, -7.5347F, 7.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 0.0F, 0.0F, -2.3562F, 0.0F, 0.0F));

		PartDefinition left_wheel_r21 = Back_Right_Wheel.addOrReplaceChild("left_wheel_r21", CubeListBuilder.create().texOffs(243, 1).addBox(-5.0F, -1.5226F, -7.5449F, 7.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 0.0F, 0.0F, -1.9635F, 0.0F, 0.0F));

		PartDefinition left_wheel_r22 = Back_Right_Wheel.addOrReplaceChild("left_wheel_r22", CubeListBuilder.create().texOffs(1, 243).addBox(-5.0F, -1.5F, -7.51F, 7.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 0.0F, 0.0F, -1.5708F, 0.0F, 0.0F));

		PartDefinition left_wheel_r23 = Back_Right_Wheel.addOrReplaceChild("left_wheel_r23", CubeListBuilder.create().texOffs(41, 221).addBox(-5.0F, -1.4114F, -7.5036F, 7.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 0.0F, 0.0F, -1.1868F, 0.0F, 0.0F));

		PartDefinition left_wheel_r24 = Back_Right_Wheel.addOrReplaceChild("left_wheel_r24", CubeListBuilder.create().texOffs(221, 242).addBox(-5.0F, -1.4697F, -7.5347F, 7.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 0.0F, 0.0F, -0.7854F, 0.0F, 0.0F));

		PartDefinition left_wheel_r25 = Back_Right_Wheel.addOrReplaceChild("left_wheel_r25", CubeListBuilder.create().texOffs(199, 242).addBox(-5.0F, -1.5226F, -7.5449F, 7.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 0.0F, 0.0F, -0.3927F, 0.0F, 0.0F));

		PartDefinition left_wheel_r26 = Back_Right_Wheel.addOrReplaceChild("left_wheel_r26", CubeListBuilder.create().texOffs(177, 242).addBox(-3.5F, -1.472F, -7.5999F, 7.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.5F, 0.0F, 0.0F, -0.0087F, 0.0F, 0.0F));

		PartDefinition left_wheel_r27 = Back_Right_Wheel.addOrReplaceChild("left_wheel_r27", CubeListBuilder.create().texOffs(23, 242).addBox(-5.0F, -1.5F, -7.51F, 7.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 0.0F, 0.0F, 1.5708F, 0.0F, 0.0F));

		PartDefinition left_wheel_r28 = Back_Right_Wheel.addOrReplaceChild("left_wheel_r28", CubeListBuilder.create().texOffs(191, 220).addBox(-5.0F, -1.4114F, -7.5036F, 7.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 0.0F, 0.0F, 1.9548F, 0.0F, 0.0F));

		PartDefinition left_wheel_r29 = Back_Right_Wheel.addOrReplaceChild("left_wheel_r29", CubeListBuilder.create().texOffs(155, 241).addBox(-5.0F, -1.4697F, -7.5347F, 7.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 0.0F, 0.0F, 2.3562F, 0.0F, 0.0F));

		PartDefinition left_wheel_r30 = Back_Right_Wheel.addOrReplaceChild("left_wheel_r30", CubeListBuilder.create().texOffs(133, 240).addBox(-5.0F, -1.5226F, -7.5449F, 7.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 0.0F, 0.0F, 2.7489F, 0.0F, 0.0F));

		PartDefinition left_wheel_r31 = Back_Right_Wheel.addOrReplaceChild("left_wheel_r31", CubeListBuilder.create().texOffs(111, 240).addBox(-5.0F, -1.5226F, -7.5449F, 7.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 0.0F, 0.0F, 1.1781F, 0.0F, 0.0F));

		PartDefinition left_wheel_r32 = Back_Right_Wheel.addOrReplaceChild("left_wheel_r32", CubeListBuilder.create().texOffs(149, 219).addBox(-5.0F, -1.4114F, -7.5036F, 7.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 0.0F, 0.0F, 0.384F, 0.0F, 0.0F));

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
