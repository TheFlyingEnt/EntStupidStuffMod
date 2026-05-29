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

public class NissanZEntityModel extends BaseCarEntityModel<BaseCarRenderState> {

    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
        ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "nissanz"), "main"
    );

    private final ModelPart body;
	private final ModelPart steering_wheel;
	private final ModelPart shifter;
	private final ModelPart Front_Left_Wheel;
	private final ModelPart Front_Right_Wheel;
	private final ModelPart Back_Left_Wheel;
	private final ModelPart Back_Right_Wheel;

    public NissanZEntityModel(ModelPart root) {
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

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(162, 0).addBox(-3.0F, -2.0F, -13.0F, 2.0F, 2.0F, 79.0F, new CubeDeformation(0.0F))
		.texOffs(174, 264).addBox(-1.0F, -2.0F, 61.0F, 1.0F, 2.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(175, 264).addBox(-36.0F, -2.0F, 61.0F, 1.0F, 2.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(296, 299).addBox(-33.0F, -2.0F, 60.0F, 30.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(162, 81).addBox(-35.0F, -2.0F, -13.0F, 2.0F, 2.0F, 79.0F, new CubeDeformation(0.0F))
		.texOffs(0, 170).addBox(-3.0F, 0.0F, 3.0F, 2.0F, 13.0F, 36.0F, new CubeDeformation(0.0F))
		.texOffs(0, 242).addBox(-4.0F, 8.0F, 3.0F, 3.0F, 5.0F, 34.0F, new CubeDeformation(0.0F))
		.texOffs(74, 242).addBox(-35.0F, 8.0F, 3.0F, 3.0F, 5.0F, 34.0F, new CubeDeformation(0.0F))
		.texOffs(76, 170).addBox(-35.0F, 0.0F, 3.0F, 2.0F, 13.0F, 36.0F, new CubeDeformation(0.0F))
		.texOffs(44, 310).addBox(-3.0F, 0.0F, 42.0F, 2.0F, 3.0F, 15.0F, new CubeDeformation(0.0F))
		.texOffs(204, 338).addBox(-7.75F, 7.5F, 61.0F, 9.0F, 5.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(152, 170).addBox(-3.0F, -2.0F, 0.0F, 2.0F, 7.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(152, 200).addBox(-0.7F, 3.5F, 2.0F, 2.0F, 9.0F, 37.0F, new CubeDeformation(0.0F))
		.texOffs(152, 200).mirror().addBox(-37.3F, 3.5F, 2.0F, 2.0F, 9.0F, 37.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(152, 190).addBox(-3.0F, -7.5F, 36.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(232, 299).addBox(-35.0F, -7.5F, 36.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(162, 283).addBox(-35.7F, 3.5F, 2.0F, 35.0F, 9.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(162, 162).addBox(-35.7F, 11.5F, 2.0F, 35.0F, 1.0F, 37.0F, new CubeDeformation(0.0F))
		.texOffs(134, 299).addBox(-33.7F, -0.5F, 37.0F, 31.0F, 13.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 219).addBox(-33.7F, -0.5F, 39.0F, 31.0F, 3.0F, 20.0F, new CubeDeformation(0.0F))
		.texOffs(124, 322).addBox(-0.7F, 3.5F, 39.0F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(358, 182).addBox(-37.3F, 3.5F, 39.0F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(370, 129).addBox(-2.5F, 6.0F, 3.0F, 4.0F, 7.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(324, 28).addBox(-2.5F, 0.3F, -11.6F, 4.0F, 2.0F, 11.0F, new CubeDeformation(0.0F))
		.texOffs(370, 139).addBox(-2.5F, 6.0F, -17.2F, 4.0F, 7.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(368, 299).addBox(-2.5F, 6.0F, 59.0F, 4.0F, 6.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(370, 149).addBox(-2.5F, 6.0F, 38.8F, 4.0F, 7.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(324, 41).addBox(-2.5F, 0.3F, 44.4F, 4.0F, 2.0F, 11.0F, new CubeDeformation(0.0F))
		.texOffs(254, 348).addBox(-0.75F, 3.5F, 58.0F, 2.0F, 4.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(0, 281).addBox(-35.5F, 3.5F, 58.0F, 35.0F, 4.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(76, 370).addBox(-3.0F, 0.0F, 57.0F, 2.0F, 5.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(324, 104).addBox(-5.0F, 3.4F, -26.0F, 2.0F, 1.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(148, 265).addBox(-33.0F, -2.0F, -13.0F, 30.0F, 3.0F, 15.0F, new CubeDeformation(0.0F))
		.texOffs(204, 330).addBox(-3.6314F, 1.0F, -17.0804F, 4.0F, 3.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(324, 80).addBox(-3.6314F, 0.0F, -14.0804F, 4.0F, 1.0F, 11.0F, new CubeDeformation(0.0F))
		.texOffs(324, 92).addBox(-36.3686F, 0.0F, -14.0804F, 4.0F, 1.0F, 11.0F, new CubeDeformation(0.0F))
		.texOffs(368, 285).addBox(-36.3686F, 1.0F, -17.0804F, 4.0F, 3.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(230, 338).addBox(-37.25F, 7.5F, 61.0F, 9.0F, 5.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(152, 180).addBox(-35.0F, -2.0F, 0.0F, 2.0F, 7.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(202, 370).addBox(-37.5F, 6.0F, 3.0F, 4.0F, 7.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(324, 54).addBox(-37.5F, 0.3F, -11.6F, 4.0F, 2.0F, 11.0F, new CubeDeformation(0.0F))
		.texOffs(214, 370).addBox(-37.5F, 6.0F, -17.2F, 4.0F, 7.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(370, 241).addBox(-37.5F, 6.0F, 59.0F, 4.0F, 6.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(226, 370).addBox(-37.5F, 6.0F, 38.8F, 4.0F, 7.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(324, 67).addBox(-37.5F, 0.3F, 44.4F, 4.0F, 2.0F, 11.0F, new CubeDeformation(0.0F))
		.texOffs(348, 313).addBox(-37.25F, 3.5F, 58.0F, 2.0F, 4.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(90, 370).addBox(-35.0F, 0.0F, 57.0F, 2.0F, 5.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(324, 117).addBox(-33.0F, 3.4F, -26.0F, 2.0F, 1.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(324, 158).addBox(-34.55F, 10.5F, -24.65F, 10.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(352, 125).addBox(-11.55F, 10.5F, -24.65F, 10.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(200, 311).addBox(-24.55F, 11.5F, -24.65F, 13.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(86, 283).addBox(-31.0F, 3.4F, -26.0F, 26.0F, 1.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(134, 314).addBox(-14.0F, 8.75F, 19.0F, 10.0F, 4.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(178, 314).addBox(-32.0F, 8.75F, 19.0F, 10.0F, 4.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(240, 247).addBox(-22.0F, 6.75F, 3.0F, 8.0F, 6.0F, 29.0F, new CubeDeformation(0.0F)), PartPose.offset(18.0F, 4.0F, -20.0F));

		PartDefinition cube_r1 = body.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(348, 325).addBox(-3.0F, -7.0F, -11.0F, 8.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-19.0F, 9.75F, 30.0F, 0.384F, 0.0F, 0.0F));

		PartDefinition cube_r2 = body.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(324, 12).addBox(15.0F, -3.0F, -36.0F, 8.0F, 10.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(0, 300).addBox(4.0F, -4.0F, -34.0F, 32.0F, 7.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-37.0F, -10.0F, 34.0F, 0.384F, 0.0F, 0.0F));

		PartDefinition cube_r3 = body.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(204, 347).addBox(1.0F, -10.0F, 5.0F, 10.0F, 5.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(346, 333).addBox(19.0F, -10.0F, 5.0F, 10.0F, 5.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(150, 296).addBox(20.0F, -5.0F, 6.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(142, 296).addBox(2.0F, -5.0F, 6.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(134, 296).addBox(26.0F, -5.0F, 6.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(78, 293).addBox(8.0F, -5.0F, 6.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(118, 330).addBox(1.0F, -4.0F, 5.0F, 10.0F, 11.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(44, 328).addBox(19.0F, -4.0F, 5.0F, 10.0F, 11.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-33.0F, 1.75F, 27.0F, -0.4014F, 0.0F, 0.0F));

		PartDefinition cube_r4 = body.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(350, 254).addBox(-1.0F, -2.0F, 0.0F, 2.0F, 2.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(308, 202).addBox(-1.0F, -2.0F, 5.0F, 30.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(350, 151).addBox(27.0F, -2.0F, 0.0F, 2.0F, 2.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-32.0F, 4.4F, -26.0F, -1.1781F, 0.0F, 0.0F));

		PartDefinition cube_r5 = body.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(238, 282).addBox(3.0F, 0.0F, -10.0F, 30.0F, 2.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(310, 230).addBox(5.0F, 0.0F, -12.0F, 26.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(332, 228).addBox(5.0F, 1.0F, -14.0F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(332, 228).addBox(29.0F, 1.0F, -14.0F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(310, 228).addBox(7.0F, 0.0F, -14.0F, 22.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(350, 244).addBox(1.0F, 0.0F, -8.0F, 2.0F, 2.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(350, 141).addBox(33.0F, 0.0F, -8.0F, 2.0F, 2.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-36.0F, -2.0F, -13.0F, 0.3927F, 0.0F, 0.0F));

		PartDefinition cube_r6 = body.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(44, 342).addBox(-7.5799F, 13.0061F, -14.7624F, 4.0F, 2.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(20, 342).addBox(27.4201F, 13.0061F, -14.7624F, 4.0F, 2.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-29.9201F, 1.5939F, 75.0624F, -0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r7 = body.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(92, 346).addBox(-7.5799F, -22.9939F, -28.7624F, 4.0F, 2.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(304, 343).addBox(27.4201F, -22.9939F, -28.7624F, 4.0F, 2.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-29.9201F, 1.8939F, 75.3624F, 0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r8 = body.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(68, 346).addBox(-7.5799F, -22.9939F, -28.7624F, 4.0F, 2.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(280, 343).addBox(27.4201F, -22.9939F, -28.7624F, 4.0F, 2.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-29.9201F, 1.8939F, 19.3624F, 0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r9 = body.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(256, 338).addBox(-7.5799F, 13.0061F, -14.7624F, 4.0F, 2.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(20, 332).addBox(27.4201F, 13.0061F, -14.7624F, 4.0F, 2.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-29.9201F, 1.5939F, 19.0624F, -0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r10 = body.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(0, 310).addBox(-3.01F, 2.0F, 6.0F, 4.0F, 4.0F, 18.0F, new CubeDeformation(0.0F))
		.texOffs(102, 219).addBox(-32.99F, 2.0F, 6.0F, 4.0F, 4.0F, 18.0F, new CubeDeformation(0.0F))
		.texOffs(78, 296).addBox(-33.0F, 0.0F, 0.0F, 4.0F, 2.0F, 24.0F, new CubeDeformation(0.0F))
		.texOffs(308, 194).addBox(-29.0F, 0.0F, 18.0F, 26.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(308, 234).addBox(-29.0F, 0.0F, 0.0F, 26.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(240, 294).addBox(-3.0F, 0.0F, 0.0F, 4.0F, 2.0F, 24.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, -8.9F, 38.8F, -0.3054F, 0.0F, 0.0F));

		PartDefinition cube_r11 = body.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(308, 207).addBox(3.0F, 0.0F, -24.0F, 30.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(308, 224).addBox(3.0F, 0.0F, -2.0F, 30.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(296, 307).addBox(1.0F, 0.0F, -24.0F, 2.0F, 2.0F, 24.0F, new CubeDeformation(0.0F))
		.texOffs(306, 162).addBox(33.0F, 0.0F, -24.0F, 2.0F, 2.0F, 24.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-36.0F, -11.0F, 23.0F, 0.3927F, 0.0F, 0.0F));

		PartDefinition cube_r12 = body.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(258, 320).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 2.0F, 16.0F, new CubeDeformation(0.0F))
		.texOffs(148, 247).addBox(1.0F, 0.0F, 0.0F, 30.0F, 2.0F, 16.0F, new CubeDeformation(0.0F))
		.texOffs(222, 320).addBox(31.0F, 0.0F, 0.0F, 2.0F, 2.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-34.0F, -11.0F, 23.0F, -0.1309F, 0.0F, 0.0F));

		PartDefinition cube_r13 = body.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(308, 212).addBox(5.0F, -9.0F, 1.0F, 26.0F, 9.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(308, 239).addBox(5.0F, 0.0F, 1.0F, 26.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(10, 361).addBox(3.0F, 0.0F, 1.0F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(356, 7).addBox(31.0F, 0.0F, 1.0F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-36.0F, 10.0F, -24.0F, -0.3927F, 0.0F, 0.0F));

		PartDefinition cube_r14 = body.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(0, 361).addBox(-1.0F, -2.0354F, 0.0048F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(296, 294).addBox(0.75F, -2.0354F, 0.0048F, 35.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(280, 338).addBox(35.5F, -2.0354F, 0.0048F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-36.25F, 5.7F, 67.85F, 2.3562F, 0.0F, 0.0F));

		PartDefinition cube_r15 = body.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(130, 371).addBox(-1.0F, -3.0354F, 0.0048F, 2.0F, 3.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(0, 293).addBox(0.75F, -3.0354F, 0.0048F, 35.0F, 3.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(192, 348).addBox(35.5F, -3.0354F, 0.0048F, 2.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-36.25F, 9.7F, 67.85F, 1.5708F, 0.0F, 0.0F));

		PartDefinition cube_r16 = body.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(348, 307).addBox(-1.0F, -2.0F, 0.0F, 9.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(78, 322).addBox(7.5F, -2.0F, 2.0F, 21.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(166, 348).addBox(28.5F, -2.0F, 0.0F, 9.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-36.25F, 12.5F, 65.0F, 0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r17 = body.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(324, 144).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 3.0F, 11.0F, new CubeDeformation(0.0F))
		.texOffs(324, 130).addBox(31.0F, 0.0F, 0.0F, 2.0F, 3.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-34.0F, -2.0F, 36.0F, 0.3927F, 0.0F, 0.0F));

		PartDefinition cube_r18 = body.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(62, 370).addBox(-1.0F, -11.0F, 0.0F, 2.0F, 8.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(350, 130).addBox(-1.0F, -3.0F, 0.0F, 2.0F, 3.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(0, 350).addBox(35.0F, -3.0F, 0.0F, 2.0F, 3.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(188, 369).addBox(35.0F, -11.0F, 0.0F, 2.0F, 8.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-36.0F, 4.5F, -21.7F, -1.1781F, 0.0F, 0.0F));

		PartDefinition cube_r19 = body.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(98, 326).addBox(-1.0F, -12.0F, 0.0F, 2.0F, 12.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(78, 326).addBox(31.0F, -12.0F, 0.0F, 2.0F, 12.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-34.0F, 4.4F, -24.0F, -1.1781F, 0.0F, 0.0F));

		PartDefinition cube_r20 = body.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(118, 344).addBox(-2.0F, -0.5F, -4.0F, 4.0F, 2.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(142, 348).addBox(-2.0F, -1.5F, -4.0F, 4.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-33.0F, 2.5F, -20.0F, 0.0F, -0.3927F, 0.0F));

		PartDefinition cube_r21 = body.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(230, 347).addBox(-2.0F, -1.5F, -4.0F, 4.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(328, 343).addBox(-2.0F, -0.5F, -4.0F, 4.0F, 2.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 2.5F, -20.0F, 0.0F, 0.3927F, 0.0F));

		PartDefinition cube_r22 = body.addOrReplaceChild("cube_r22", CubeListBuilder.create().texOffs(307, 188).addBox(-32.0F, -2.0F, 0.0F, 28.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 5.4F, -26.0F, -1.1781F, 0.0F, 0.0F));

		PartDefinition cube_r23 = body.addOrReplaceChild("cube_r23", CubeListBuilder.create().texOffs(352, 143).mirror().addBox(-2.0F, 1.0F, -6.0F, 3.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(352, 143).addBox(31.0F, 1.0F, -6.0F, 3.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-34.0F, -2.0F, -12.75F, 0.3927F, 0.0F, 0.0F));

		PartDefinition cube_r24 = body.addOrReplaceChild("cube_r24", CubeListBuilder.create().texOffs(1, 86).addBox(0.0F, 0.0F, -28.0F, 2.0F, 6.0F, 78.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-35.0F, -2.0F, 15.0F, 0.0F, 0.0F, 0.3927F));

		PartDefinition cube_r25 = body.addOrReplaceChild("cube_r25", CubeListBuilder.create().texOffs(320, 333).addBox(0.0F, -1.5F, -3.0F, 5.0F, 2.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-35.6F, 12.0F, -21.8F, 0.0F, -0.3927F, 0.0F));

		PartDefinition cube_r26 = body.addOrReplaceChild("cube_r26", CubeListBuilder.create().texOffs(294, 333).addBox(-5.0F, -1.5F, -3.0F, 5.0F, 2.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.4F, 12.0F, -21.8F, 0.0F, 0.3927F, 0.0F));

		PartDefinition cube_r27 = body.addOrReplaceChild("cube_r27", CubeListBuilder.create().texOffs(422, 166).addBox(-34.0F, -2.0F, 2.0F, 36.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, -2.9F, 63.15F, -0.3054F, 0.0F, 0.0F));

		PartDefinition cube_r28 = body.addOrReplaceChild("cube_r28", CubeListBuilder.create().texOffs(370, 256).addBox(-2.0F, -1.5F, -2.0F, 3.0F, 4.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(370, 249).addBox(-26.0F, -1.5F, -2.0F, 3.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.45F, 10.6952F, 66.3146F, 1.5708F, 0.0F, 0.0F));

		PartDefinition cube_r29 = body.addOrReplaceChild("cube_r29", CubeListBuilder.create().texOffs(162, 294).addBox(-36.0F, -1.0354F, 0.0048F, 35.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 3.7F, 65.35F, 1.5708F, 0.0F, 0.0F));

		PartDefinition cube_r30 = body.addOrReplaceChild("cube_r30", CubeListBuilder.create().texOffs(318, 286).addBox(-31.0F, -2.0F, 0.0F, 21.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.25F, 12.5F, 64.0F, 0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r31 = body.addOrReplaceChild("cube_r31", CubeListBuilder.create().texOffs(1, 1).addBox(-2.0F, 0.0F, -28.0F, 2.0F, 6.0F, 78.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -2.0F, 15.0F, 0.0F, 0.0F, -0.3927F));

		PartDefinition cube_r32 = body.addOrReplaceChild("cube_r32", CubeListBuilder.create().texOffs(318, 265).addBox(-3.0F, 0.0F, -15.0F, 3.0F, 6.0F, 15.0F, new CubeDeformation(0.0F))
		.texOffs(314, 244).addBox(28.0F, 0.0F, -15.0F, 3.0F, 6.0F, 15.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-32.0F, 8.0F, 18.0F, -0.3927F, 0.0F, 0.0F));

		PartDefinition shifter = body.addOrReplaceChild("shifter", CubeListBuilder.create().texOffs(148, 239).addBox(-0.75F, -2.5F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(114, 281).addBox(-0.75F, -3.5F, -0.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-18.25F, 7.25F, 12.5F));

		PartDefinition steering_wheel = body.addOrReplaceChild("steering_wheel", CubeListBuilder.create().texOffs(146, 219).addBox(3.0F, -2.6071F, -0.5F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(86, 281).addBox(-3.0F, 2.3929F, -0.5F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(222, 314).addBox(-3.0F, -2.8571F, 0.0F, 6.0F, 6.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(152, 198).addBox(-2.0F, -0.8571F, -0.5F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(146, 225).addBox(-4.0F, -2.6071F, -0.5F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(100, 281).addBox(-3.0F, -3.6071F, -0.5F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(146, 237).addBox(-1.0F, 0.1429F, -0.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(70, 306).addBox(-1.0F, -0.8571F, -2.5F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.5F, 1.6071F, 7.5F, 0.2182F, 0.0F, 0.0F));

		PartDefinition Front_Left_Wheel = body.addOrReplaceChild("Front_Left_Wheel", CubeListBuilder.create(), PartPose.offset(-1.0F, 10.7F, -6.39F));

		PartDefinition right_wheel_r1 = Front_Left_Wheel.addOrReplaceChild("right_wheel_r1", CubeListBuilder.create().texOffs(319, 353).addBox(-1.0F, -1.4697F, -7.5347F, 6.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 0.0F, 0.7854F, 0.0F, 0.0F));

		PartDefinition right_wheel_r2 = Front_Left_Wheel.addOrReplaceChild("right_wheel_r2", CubeListBuilder.create().texOffs(87, 363).addBox(-1.0F, -1.4114F, -7.5036F, 6.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 0.0F, -2.7576F, 0.0F, 0.0F));

		PartDefinition right_wheel_r3 = Front_Left_Wheel.addOrReplaceChild("right_wheel_r3", CubeListBuilder.create().texOffs(297, 353).addBox(-1.0F, -1.5F, -7.51F, 6.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 0.0F, 3.1416F, 0.0F, 0.0F));

		PartDefinition right_wheel_r4 = Front_Left_Wheel.addOrReplaceChild("right_wheel_r4", CubeListBuilder.create().texOffs(275, 353).addBox(-1.0F, -1.4697F, -7.5347F, 6.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 0.0F, -2.3562F, 0.0F, 0.0F));

		PartDefinition right_wheel_r5 = Front_Left_Wheel.addOrReplaceChild("right_wheel_r5", CubeListBuilder.create().texOffs(353, 348).addBox(-1.0F, -1.5226F, -7.5449F, 6.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 0.0F, -1.9635F, 0.0F, 0.0F));

		PartDefinition right_wheel_r6 = Front_Left_Wheel.addOrReplaceChild("right_wheel_r6", CubeListBuilder.create().texOffs(152, 368).addBox(-0.5F, -1.0F, -3.5F, 1.0F, 2.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -0.01F, 0.0F, 0.0F, 0.0F, 0.0F));

		PartDefinition right_wheel_r7 = Front_Left_Wheel.addOrReplaceChild("right_wheel_r7", CubeListBuilder.create().texOffs(44, 366).addBox(-0.5F, -1.0F, -3.5F, 1.0F, 2.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -0.01F, 0.0F, -1.5708F, 0.0F, 0.0F));

		PartDefinition right_wheel_r8 = Front_Left_Wheel.addOrReplaceChild("right_wheel_r8", CubeListBuilder.create().texOffs(144, 330).addBox(2.0F, -4.5F, -5.51F, 1.0F, 9.0F, 9.0F, new CubeDeformation(0.0F))
		.texOffs(70, 300).addBox(3.0F, -1.5F, -2.51F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 1.0F, 0.0F, -1.5708F, 0.0F, 0.0F));

		PartDefinition right_wheel_r9 = Front_Left_Wheel.addOrReplaceChild("right_wheel_r9", CubeListBuilder.create().texOffs(353, 341).addBox(-1.0F, -1.5F, -7.51F, 6.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 0.0F, -1.5708F, 0.0F, 0.0F));

		PartDefinition right_wheel_r10 = Front_Left_Wheel.addOrReplaceChild("right_wheel_r10", CubeListBuilder.create().texOffs(65, 363).addBox(-1.0F, -1.4114F, -7.5036F, 6.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 0.0F, -1.1868F, 0.0F, 0.0F));

		PartDefinition right_wheel_r11 = Front_Left_Wheel.addOrReplaceChild("right_wheel_r11", CubeListBuilder.create().texOffs(353, 118).addBox(-1.0F, -1.4697F, -7.5347F, 6.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 0.0F, -0.7854F, 0.0F, 0.0F));

		PartDefinition right_wheel_r12 = Front_Left_Wheel.addOrReplaceChild("right_wheel_r12", CubeListBuilder.create().texOffs(353, 111).addBox(-1.0F, -1.5226F, -7.5449F, 6.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 0.0F, -0.3927F, 0.0F, 0.0F));

		PartDefinition right_wheel_r13 = Front_Left_Wheel.addOrReplaceChild("right_wheel_r13", CubeListBuilder.create().texOffs(353, 104).addBox(-2.5F, -1.472F, -7.5999F, 6.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.5F, 0.0F, 0.0F, -0.0087F, 0.0F, 0.0F));

		PartDefinition right_wheel_r14 = Front_Left_Wheel.addOrReplaceChild("right_wheel_r14", CubeListBuilder.create().texOffs(43, 352).addBox(-1.0F, -1.5F, -7.51F, 6.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 0.0F, 1.5708F, 0.0F, 0.0F));

		PartDefinition right_wheel_r15 = Front_Left_Wheel.addOrReplaceChild("right_wheel_r15", CubeListBuilder.create().texOffs(363, 355).addBox(-1.0F, -1.4114F, -7.5036F, 6.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 0.0F, 1.9548F, 0.0F, 0.0F));

		PartDefinition right_wheel_r16 = Front_Left_Wheel.addOrReplaceChild("right_wheel_r16", CubeListBuilder.create().texOffs(21, 352).addBox(-1.0F, -1.4697F, -7.5347F, 6.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 0.0F, 2.3562F, 0.0F, 0.0F));

		PartDefinition right_wheel_r17 = Front_Left_Wheel.addOrReplaceChild("right_wheel_r17", CubeListBuilder.create().texOffs(353, 19).addBox(-1.0F, -1.5226F, -7.5449F, 6.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 0.0F, 2.7489F, 0.0F, 0.0F));

		PartDefinition right_wheel_r18 = Front_Left_Wheel.addOrReplaceChild("right_wheel_r18", CubeListBuilder.create().texOffs(353, 12).addBox(-1.0F, -1.5226F, -7.5449F, 6.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 0.0F, 1.1781F, 0.0F, 0.0F));

		PartDefinition right_wheel_r19 = Front_Left_Wheel.addOrReplaceChild("right_wheel_r19", CubeListBuilder.create().texOffs(343, 362).addBox(-1.0F, -1.4114F, -7.5036F, 6.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 0.0F, 0.384F, 0.0F, 0.0F));

		PartDefinition Front_Right_Wheel = body.addOrReplaceChild("Front_Right_Wheel", CubeListBuilder.create(), PartPose.offset(-35.0F, 10.7F, -6.39F));

		PartDefinition left_wheel_r1 = Front_Right_Wheel.addOrReplaceChild("left_wheel_r1", CubeListBuilder.create().texOffs(319, 353).mirror().addBox(-5.0F, -1.4697F, -7.5347F, 6.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.0F, 0.0F, 0.0F, 0.7854F, 0.0F, 0.0F));

		PartDefinition left_wheel_r2 = Front_Right_Wheel.addOrReplaceChild("left_wheel_r2", CubeListBuilder.create().texOffs(87, 363).mirror().addBox(-5.0F, -1.4114F, -7.5036F, 6.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.0F, 0.0F, 0.0F, -2.7576F, 0.0F, 0.0F));

		PartDefinition left_wheel_r3 = Front_Right_Wheel.addOrReplaceChild("left_wheel_r3", CubeListBuilder.create().texOffs(297, 353).mirror().addBox(-5.0F, -1.5F, -7.51F, 6.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.0F, 0.0F, 0.0F, 3.1416F, 0.0F, 0.0F));

		PartDefinition left_wheel_r4 = Front_Right_Wheel.addOrReplaceChild("left_wheel_r4", CubeListBuilder.create().texOffs(275, 353).mirror().addBox(-5.0F, -1.4697F, -7.5347F, 6.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.0F, 0.0F, 0.0F, -2.3562F, 0.0F, 0.0F));

		PartDefinition left_wheel_r5 = Front_Right_Wheel.addOrReplaceChild("left_wheel_r5", CubeListBuilder.create().texOffs(353, 348).mirror().addBox(-5.0F, -1.5226F, -7.5449F, 6.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.0F, 0.0F, 0.0F, -1.9635F, 0.0F, 0.0F));

		PartDefinition left_wheel_r6 = Front_Right_Wheel.addOrReplaceChild("left_wheel_r6", CubeListBuilder.create().texOffs(152, 368).mirror().addBox(-0.5F, -1.0F, -3.5F, 1.0F, 2.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-1.0F, -0.01F, 0.0F, 0.0F, 0.0F, 0.0F));

		PartDefinition left_wheel_r7 = Front_Right_Wheel.addOrReplaceChild("left_wheel_r7", CubeListBuilder.create().texOffs(44, 366).mirror().addBox(-0.5F, -1.0F, -3.5F, 1.0F, 2.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-1.0F, -0.01F, 0.0F, -1.5708F, 0.0F, 0.0F));

		PartDefinition left_wheel_r8 = Front_Right_Wheel.addOrReplaceChild("left_wheel_r8", CubeListBuilder.create().texOffs(144, 330).mirror().addBox(-3.0F, -4.5F, -5.51F, 1.0F, 9.0F, 9.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(70, 300).mirror().addBox(-4.0F, -1.5F, -2.51F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.0F, 1.0F, 0.0F, -1.5708F, 0.0F, 0.0F));

		PartDefinition left_wheel_r9 = Front_Right_Wheel.addOrReplaceChild("left_wheel_r9", CubeListBuilder.create().texOffs(353, 341).mirror().addBox(-5.0F, -1.5F, -7.51F, 6.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.0F, 0.0F, 0.0F, -1.5708F, 0.0F, 0.0F));

		PartDefinition left_wheel_r10 = Front_Right_Wheel.addOrReplaceChild("left_wheel_r10", CubeListBuilder.create().texOffs(65, 363).mirror().addBox(-5.0F, -1.4114F, -7.5036F, 6.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.0F, 0.0F, 0.0F, -1.1868F, 0.0F, 0.0F));

		PartDefinition left_wheel_r11 = Front_Right_Wheel.addOrReplaceChild("left_wheel_r11", CubeListBuilder.create().texOffs(353, 118).mirror().addBox(-5.0F, -1.4697F, -7.5347F, 6.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.0F, 0.0F, 0.0F, -0.7854F, 0.0F, 0.0F));

		PartDefinition left_wheel_r12 = Front_Right_Wheel.addOrReplaceChild("left_wheel_r12", CubeListBuilder.create().texOffs(353, 111).mirror().addBox(-5.0F, -1.5226F, -7.5449F, 6.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.0F, 0.0F, 0.0F, -0.3927F, 0.0F, 0.0F));

		PartDefinition left_wheel_r13 = Front_Right_Wheel.addOrReplaceChild("left_wheel_r13", CubeListBuilder.create().texOffs(353, 104).mirror().addBox(-3.5F, -1.472F, -7.5999F, 6.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(1.5F, 0.0F, 0.0F, -0.0087F, 0.0F, 0.0F));

		PartDefinition left_wheel_r14 = Front_Right_Wheel.addOrReplaceChild("left_wheel_r14", CubeListBuilder.create().texOffs(43, 352).mirror().addBox(-5.0F, -1.5F, -7.51F, 6.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.0F, 0.0F, 0.0F, 1.5708F, 0.0F, 0.0F));

		PartDefinition left_wheel_r15 = Front_Right_Wheel.addOrReplaceChild("left_wheel_r15", CubeListBuilder.create().texOffs(363, 355).mirror().addBox(-5.0F, -1.4114F, -7.5036F, 6.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.0F, 0.0F, 0.0F, 1.9548F, 0.0F, 0.0F));

		PartDefinition left_wheel_r16 = Front_Right_Wheel.addOrReplaceChild("left_wheel_r16", CubeListBuilder.create().texOffs(21, 352).mirror().addBox(-5.0F, -1.4697F, -7.5347F, 6.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.0F, 0.0F, 0.0F, 2.3562F, 0.0F, 0.0F));

		PartDefinition left_wheel_r17 = Front_Right_Wheel.addOrReplaceChild("left_wheel_r17", CubeListBuilder.create().texOffs(353, 19).mirror().addBox(-5.0F, -1.5226F, -7.5449F, 6.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.0F, 0.0F, 0.0F, 2.7489F, 0.0F, 0.0F));

		PartDefinition left_wheel_r18 = Front_Right_Wheel.addOrReplaceChild("left_wheel_r18", CubeListBuilder.create().texOffs(353, 12).mirror().addBox(-5.0F, -1.5226F, -7.5449F, 6.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.0F, 0.0F, 0.0F, 1.1781F, 0.0F, 0.0F));

		PartDefinition left_wheel_r19 = Front_Right_Wheel.addOrReplaceChild("left_wheel_r19", CubeListBuilder.create().texOffs(343, 362).mirror().addBox(-5.0F, -1.4114F, -7.5036F, 6.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.0F, 0.0F, 0.0F, 0.384F, 0.0F, 0.0F));

		PartDefinition Back_Left_Wheel = body.addOrReplaceChild("Back_Left_Wheel", CubeListBuilder.create(), PartPose.offset(-1.0F, 10.7F, 49.61F));

		PartDefinition right_wheel_r20 = Back_Left_Wheel.addOrReplaceChild("right_wheel_r20", CubeListBuilder.create().texOffs(319, 353).addBox(-1.0F, -1.4697F, -7.5347F, 6.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 0.0F, 0.7854F, 0.0F, 0.0F));

		PartDefinition right_wheel_r21 = Back_Left_Wheel.addOrReplaceChild("right_wheel_r21", CubeListBuilder.create().texOffs(87, 363).addBox(-1.0F, -1.4114F, -7.5036F, 6.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 0.0F, -2.7576F, 0.0F, 0.0F));

		PartDefinition right_wheel_r22 = Back_Left_Wheel.addOrReplaceChild("right_wheel_r22", CubeListBuilder.create().texOffs(297, 353).addBox(-1.0F, -1.5F, -7.51F, 6.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 0.0F, 3.1416F, 0.0F, 0.0F));

		PartDefinition right_wheel_r23 = Back_Left_Wheel.addOrReplaceChild("right_wheel_r23", CubeListBuilder.create().texOffs(275, 353).addBox(-1.0F, -1.4697F, -7.5347F, 6.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 0.0F, -2.3562F, 0.0F, 0.0F));

		PartDefinition right_wheel_r24 = Back_Left_Wheel.addOrReplaceChild("right_wheel_r24", CubeListBuilder.create().texOffs(353, 348).addBox(-1.0F, -1.5226F, -7.5449F, 6.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 0.0F, -1.9635F, 0.0F, 0.0F));

		PartDefinition right_wheel_r25 = Back_Left_Wheel.addOrReplaceChild("right_wheel_r25", CubeListBuilder.create().texOffs(152, 368).addBox(-0.5F, -1.0F, -3.5F, 1.0F, 2.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -0.01F, 0.0F, 0.0F, 0.0F, 0.0F));

		PartDefinition right_wheel_r26 = Back_Left_Wheel.addOrReplaceChild("right_wheel_r26", CubeListBuilder.create().texOffs(44, 366).addBox(-0.5F, -1.0F, -3.5F, 1.0F, 2.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -0.01F, 0.0F, -1.5708F, 0.0F, 0.0F));

		PartDefinition right_wheel_r27 = Back_Left_Wheel.addOrReplaceChild("right_wheel_r27", CubeListBuilder.create().texOffs(144, 330).addBox(2.0F, -4.5F, -5.51F, 1.0F, 9.0F, 9.0F, new CubeDeformation(0.0F))
		.texOffs(70, 300).addBox(3.0F, -1.5F, -2.51F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 1.0F, 0.0F, -1.5708F, 0.0F, 0.0F));

		PartDefinition right_wheel_r28 = Back_Left_Wheel.addOrReplaceChild("right_wheel_r28", CubeListBuilder.create().texOffs(353, 341).addBox(-1.0F, -1.5F, -7.51F, 6.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 0.0F, -1.5708F, 0.0F, 0.0F));

		PartDefinition right_wheel_r29 = Back_Left_Wheel.addOrReplaceChild("right_wheel_r29", CubeListBuilder.create().texOffs(65, 363).addBox(-1.0F, -1.4114F, -7.5036F, 6.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 0.0F, -1.1868F, 0.0F, 0.0F));

		PartDefinition right_wheel_r30 = Back_Left_Wheel.addOrReplaceChild("right_wheel_r30", CubeListBuilder.create().texOffs(353, 118).addBox(-1.0F, -1.4697F, -7.5347F, 6.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 0.0F, -0.7854F, 0.0F, 0.0F));

		PartDefinition right_wheel_r31 = Back_Left_Wheel.addOrReplaceChild("right_wheel_r31", CubeListBuilder.create().texOffs(353, 111).addBox(-1.0F, -1.5226F, -7.5449F, 6.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 0.0F, -0.3927F, 0.0F, 0.0F));

		PartDefinition right_wheel_r32 = Back_Left_Wheel.addOrReplaceChild("right_wheel_r32", CubeListBuilder.create().texOffs(353, 104).addBox(-2.5F, -1.472F, -7.5999F, 6.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.5F, 0.0F, 0.0F, -0.0087F, 0.0F, 0.0F));

		PartDefinition right_wheel_r33 = Back_Left_Wheel.addOrReplaceChild("right_wheel_r33", CubeListBuilder.create().texOffs(43, 352).addBox(-1.0F, -1.5F, -7.51F, 6.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 0.0F, 1.5708F, 0.0F, 0.0F));

		PartDefinition right_wheel_r34 = Back_Left_Wheel.addOrReplaceChild("right_wheel_r34", CubeListBuilder.create().texOffs(363, 355).addBox(-1.0F, -1.4114F, -7.5036F, 6.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 0.0F, 1.9548F, 0.0F, 0.0F));

		PartDefinition right_wheel_r35 = Back_Left_Wheel.addOrReplaceChild("right_wheel_r35", CubeListBuilder.create().texOffs(21, 352).addBox(-1.0F, -1.4697F, -7.5347F, 6.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 0.0F, 2.3562F, 0.0F, 0.0F));

		PartDefinition right_wheel_r36 = Back_Left_Wheel.addOrReplaceChild("right_wheel_r36", CubeListBuilder.create().texOffs(353, 19).addBox(-1.0F, -1.5226F, -7.5449F, 6.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 0.0F, 2.7489F, 0.0F, 0.0F));

		PartDefinition right_wheel_r37 = Back_Left_Wheel.addOrReplaceChild("right_wheel_r37", CubeListBuilder.create().texOffs(353, 12).addBox(-1.0F, -1.5226F, -7.5449F, 6.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 0.0F, 1.1781F, 0.0F, 0.0F));

		PartDefinition right_wheel_r38 = Back_Left_Wheel.addOrReplaceChild("right_wheel_r38", CubeListBuilder.create().texOffs(343, 362).addBox(-1.0F, -1.4114F, -7.5036F, 6.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 0.0F, 0.384F, 0.0F, 0.0F));

		PartDefinition Back_Right_Wheel = body.addOrReplaceChild("Back_Right_Wheel", CubeListBuilder.create(), PartPose.offset(-35.0F, 10.7F, 49.61F));

		PartDefinition left_wheel_r20 = Back_Right_Wheel.addOrReplaceChild("left_wheel_r20", CubeListBuilder.create().texOffs(319, 353).mirror().addBox(-5.0F, -1.4697F, -7.5347F, 6.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.0F, 0.0F, 0.0F, 0.7854F, 0.0F, 0.0F));

		PartDefinition left_wheel_r21 = Back_Right_Wheel.addOrReplaceChild("left_wheel_r21", CubeListBuilder.create().texOffs(87, 363).mirror().addBox(-5.0F, -1.4114F, -7.5036F, 6.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.0F, 0.0F, 0.0F, -2.7576F, 0.0F, 0.0F));

		PartDefinition left_wheel_r22 = Back_Right_Wheel.addOrReplaceChild("left_wheel_r22", CubeListBuilder.create().texOffs(297, 353).mirror().addBox(-5.0F, -1.5F, -7.51F, 6.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.0F, 0.0F, 0.0F, 3.1416F, 0.0F, 0.0F));

		PartDefinition left_wheel_r23 = Back_Right_Wheel.addOrReplaceChild("left_wheel_r23", CubeListBuilder.create().texOffs(275, 353).mirror().addBox(-5.0F, -1.4697F, -7.5347F, 6.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.0F, 0.0F, 0.0F, -2.3562F, 0.0F, 0.0F));

		PartDefinition left_wheel_r24 = Back_Right_Wheel.addOrReplaceChild("left_wheel_r24", CubeListBuilder.create().texOffs(353, 348).mirror().addBox(-5.0F, -1.5226F, -7.5449F, 6.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.0F, 0.0F, 0.0F, -1.9635F, 0.0F, 0.0F));

		PartDefinition left_wheel_r25 = Back_Right_Wheel.addOrReplaceChild("left_wheel_r25", CubeListBuilder.create().texOffs(152, 368).mirror().addBox(-0.5F, -1.0F, -3.5F, 1.0F, 2.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-1.0F, -0.01F, 0.0F, 0.0F, 0.0F, 0.0F));

		PartDefinition left_wheel_r26 = Back_Right_Wheel.addOrReplaceChild("left_wheel_r26", CubeListBuilder.create().texOffs(44, 366).mirror().addBox(-0.5F, -1.0F, -3.5F, 1.0F, 2.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-1.0F, -0.01F, 0.0F, -1.5708F, 0.0F, 0.0F));

		PartDefinition left_wheel_r27 = Back_Right_Wheel.addOrReplaceChild("left_wheel_r27", CubeListBuilder.create().texOffs(144, 330).mirror().addBox(-3.0F, -4.5F, -5.51F, 1.0F, 9.0F, 9.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(70, 300).mirror().addBox(-4.0F, -1.5F, -2.51F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.0F, 1.0F, 0.0F, -1.5708F, 0.0F, 0.0F));

		PartDefinition left_wheel_r28 = Back_Right_Wheel.addOrReplaceChild("left_wheel_r28", CubeListBuilder.create().texOffs(353, 341).mirror().addBox(-5.0F, -1.5F, -7.51F, 6.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.0F, 0.0F, 0.0F, -1.5708F, 0.0F, 0.0F));

		PartDefinition left_wheel_r29 = Back_Right_Wheel.addOrReplaceChild("left_wheel_r29", CubeListBuilder.create().texOffs(65, 363).mirror().addBox(-5.0F, -1.4114F, -7.5036F, 6.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.0F, 0.0F, 0.0F, -1.1868F, 0.0F, 0.0F));

		PartDefinition left_wheel_r30 = Back_Right_Wheel.addOrReplaceChild("left_wheel_r30", CubeListBuilder.create().texOffs(353, 118).mirror().addBox(-5.0F, -1.4697F, -7.5347F, 6.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.0F, 0.0F, 0.0F, -0.7854F, 0.0F, 0.0F));

		PartDefinition left_wheel_r31 = Back_Right_Wheel.addOrReplaceChild("left_wheel_r31", CubeListBuilder.create().texOffs(353, 111).mirror().addBox(-5.0F, -1.5226F, -7.5449F, 6.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.0F, 0.0F, 0.0F, -0.3927F, 0.0F, 0.0F));

		PartDefinition left_wheel_r32 = Back_Right_Wheel.addOrReplaceChild("left_wheel_r32", CubeListBuilder.create().texOffs(353, 104).mirror().addBox(-3.5F, -1.472F, -7.5999F, 6.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(1.5F, 0.0F, 0.0F, -0.0087F, 0.0F, 0.0F));

		PartDefinition left_wheel_r33 = Back_Right_Wheel.addOrReplaceChild("left_wheel_r33", CubeListBuilder.create().texOffs(43, 352).mirror().addBox(-5.0F, -1.5F, -7.51F, 6.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.0F, 0.0F, 0.0F, 1.5708F, 0.0F, 0.0F));

		PartDefinition left_wheel_r34 = Back_Right_Wheel.addOrReplaceChild("left_wheel_r34", CubeListBuilder.create().texOffs(363, 355).mirror().addBox(-5.0F, -1.4114F, -7.5036F, 6.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.0F, 0.0F, 0.0F, 1.9548F, 0.0F, 0.0F));

		PartDefinition left_wheel_r35 = Back_Right_Wheel.addOrReplaceChild("left_wheel_r35", CubeListBuilder.create().texOffs(21, 352).mirror().addBox(-5.0F, -1.4697F, -7.5347F, 6.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.0F, 0.0F, 0.0F, 2.3562F, 0.0F, 0.0F));

		PartDefinition left_wheel_r36 = Back_Right_Wheel.addOrReplaceChild("left_wheel_r36", CubeListBuilder.create().texOffs(353, 19).mirror().addBox(-5.0F, -1.5226F, -7.5449F, 6.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.0F, 0.0F, 0.0F, 2.7489F, 0.0F, 0.0F));

		PartDefinition left_wheel_r37 = Back_Right_Wheel.addOrReplaceChild("left_wheel_r37", CubeListBuilder.create().texOffs(353, 12).mirror().addBox(-5.0F, -1.5226F, -7.5449F, 6.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.0F, 0.0F, 0.0F, 1.1781F, 0.0F, 0.0F));

		PartDefinition left_wheel_r38 = Back_Right_Wheel.addOrReplaceChild("left_wheel_r38", CubeListBuilder.create().texOffs(343, 362).mirror().addBox(-5.0F, -1.4114F, -7.5036F, 6.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.0F, 0.0F, 0.0F, 0.384F, 0.0F, 0.0F));

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
