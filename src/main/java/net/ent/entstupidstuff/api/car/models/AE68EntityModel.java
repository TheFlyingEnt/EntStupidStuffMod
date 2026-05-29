package net.ent.entstupidstuff.api.car.models;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.api.car.render.AE68RenderState;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.ResourceLocation;

public class AE68EntityModel extends BaseCarEntityModel<AE68RenderState> {

    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
        ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "ae86"), "main"
    );

    private final ModelPart body;
	private final ModelPart steering_wheel;
	private final ModelPart shifter;
	private final ModelPart Front_Left_Wheel;
	private final ModelPart Front_Right_Wheel;
	private final ModelPart Back_Left_Wheel;
	private final ModelPart Back_Right_Wheel;
	private final ModelPart BodyKits;
    private final ModelPart Popup_Right;
	private final ModelPart Popup_Left;

    public AE68EntityModel(ModelPart root) {
        super(root);
		this.body = root.getChild("body");
		this.steering_wheel = this.body.getChild("steering_wheel");
		this.shifter = this.body.getChild("shifter");
		this.Front_Left_Wheel = this.body.getChild("Front_Left_Wheel");
		this.Front_Right_Wheel = this.body.getChild("Front_Right_Wheel");
		this.Back_Left_Wheel = this.body.getChild("Back_Left_Wheel");
		this.Back_Right_Wheel = this.body.getChild("Back_Right_Wheel");
		this.BodyKits = this.body.getChild("bodykits");
        
		this.Popup_Right = this.body.getChild("Popup_Right");
		this.Popup_Left = this.body.getChild("Popup_Left");
	}

    @SuppressWarnings("unused")
    public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(276, 289).addBox(15.0F, -30.0F, -2.0F, 1.0F, 2.0F, 20.0F, new CubeDeformation(0.0F))
		.texOffs(240, 149).addBox(17.0F, -5.7F, -16.7F, 1.0F, 1.0F, 33.0F, new CubeDeformation(0.0F))
		.texOffs(96, 223).addBox(16.15F, -18.25F, -17.7F, 1.0F, 7.0F, 35.0F, new CubeDeformation(0.0F))
		.texOffs(64, 344).addBox(16.15F, -18.25F, 17.3F, 1.0F, 5.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(164, 295).addBox(16.15F, -18.25F, 32.3F, 1.0F, 5.0F, 9.0F, new CubeDeformation(0.0F))
		.texOffs(268, 311).addBox(16.15F, -18.25F, 20.3F, 1.0F, 2.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(216, 343).addBox(16.15F, -18.25F, -21.7F, 1.0F, 3.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(16, 325).addBox(16.15F, -15.25F, -19.7F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(164, 310).addBox(15.0F, -13.0F, -44.0F, 3.0F, 4.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(294, 345).addBox(15.0F, -13.0F, -45.0F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(144, 314).addBox(15.0F, -9.0F, -42.0F, 1.0F, 3.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(122, 128).addBox(15.0F, -21.0F, -22.0F, 1.0F, 2.0F, 58.0F, new CubeDeformation(0.0F))
		.texOffs(206, 343).addBox(16.1399F, -15.2308F, 41.3F, 1.0F, 3.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(310, 57).addBox(16.9399F, -13.2308F, 33.3F, 1.0F, 2.0F, 13.0F, new CubeDeformation(0.0F))
		.texOffs(88, 312).addBox(16.9398F, -12.0307F, 34.5F, 1.0F, 4.0F, 9.0F, new CubeDeformation(0.0F))
		.texOffs(118, 167).addBox(16.9398F, -12.0307F, 43.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(118, 164).addBox(-17.9398F, -12.0307F, 43.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(214, 277).addBox(-16.4601F, -15.2308F, 41.3F, 33.0F, 3.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(274, 345).addBox(16.0F, -18.3F, 24.8F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(284, 345).addBox(16.0F, -18.3F, -27.95F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(312, 28).addBox(-17.15F, -18.25F, 32.3F, 1.0F, 5.0F, 9.0F, new CubeDeformation(0.0F))
		.texOffs(294, 311).addBox(-17.15F, -18.25F, 20.3F, 1.0F, 2.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(344, 68).addBox(-17.15F, -18.25F, 17.3F, 1.0F, 5.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(338, 68).addBox(-17.15F, -15.25F, -19.7F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(226, 343).addBox(-17.15F, -18.25F, -21.7F, 1.0F, 3.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(240, 0).addBox(-17.15F, -18.25F, -17.7F, 1.0F, 7.0F, 35.0F, new CubeDeformation(0.0F))
		.texOffs(346, 76).addBox(-18.0F, -18.3F, -27.95F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(346, 81).addBox(-18.0F, -18.3F, 24.8F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(68, 312).addBox(-17.9398F, -12.0307F, 34.5F, 1.0F, 4.0F, 9.0F, new CubeDeformation(0.0F))
		.texOffs(310, 42).addBox(-17.9399F, -13.2308F, 33.3F, 1.0F, 2.0F, 13.0F, new CubeDeformation(0.0F))
		.texOffs(236, 343).addBox(-17.1399F, -15.2308F, 41.3F, 1.0F, 3.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(0, 134).addBox(-16.0F, -21.0F, -22.0F, 1.0F, 2.0F, 58.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-16.0F, -19.0F, -22.0F, 1.0F, 13.0F, 58.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).mirror().addBox(15.0F, -19.0F, -22.0F, 1.0F, 13.0F, 58.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(122, 63).addBox(14.0F, -13.0F, -22.0F, 1.0F, 7.0F, 58.0F, new CubeDeformation(0.0F))
		.texOffs(122, 63).mirror().addBox(-15.0F, -13.0F, -22.0F, 1.0F, 7.0F, 58.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(320, 305).addBox(-16.0F, -9.0F, -42.0F, 1.0F, 3.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(18, 347).addBox(-17.0F, -13.0F, -45.0F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(242, 310).addBox(-18.0F, -13.0F, -44.0F, 3.0F, 4.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(240, 223).addBox(-18.0F, -5.7F, -16.7F, 1.0F, 1.0F, 33.0F, new CubeDeformation(0.0F))
		.texOffs(118, 188).addBox(-17.0F, -5.7F, -16.7F, 34.0F, 1.0F, 34.0F, new CubeDeformation(0.0F))
		.texOffs(144, 265).addBox(-17.0F, -17.7F, 17.3F, 34.0F, 13.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(74, 277).addBox(-16.0F, -18.7F, -14.7F, 32.0F, 13.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 290).addBox(-16.0F, -30.0F, -2.0F, 1.0F, 2.0F, 20.0F, new CubeDeformation(0.0F))
		.texOffs(254, 195).addBox(-15.0F, -21.0F, -22.0F, 30.0F, 2.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(214, 268).addBox(-15.0F, -8.0F, -42.0F, 30.0F, 2.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(240, 257).addBox(-15.0F, -13.0F, -45.0F, 30.0F, 4.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(344, 342).addBox(-17.0F, -9.0F, 36.0F, 1.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(246, 345).addBox(16.0F, -9.0F, 36.0F, 1.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(0, 194).addBox(-15.0F, -30.0F, -2.0F, 30.0F, 2.0F, 20.0F, new CubeDeformation(0.0F))
		.texOffs(282, 284).addBox(-15.5F, -22.0F, 38.0F, 31.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(184, 294).addBox(3.0F, -11.25F, 0.0F, 10.0F, 4.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(228, 294).addBox(-13.0F, -11.25F, 0.0F, 10.0F, 4.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(240, 42).addBox(-3.0F, -13.25F, -16.0F, 6.0F, 6.0F, 29.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 23.0F, 0.0F));

		PartDefinition cube_r1 = body.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(160, 343).addBox(-2.0F, -1.5F, -2.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(13.55F, -8.3049F, 37.3146F, 1.5708F, 0.0F, 0.0F));

		PartDefinition cube_r2 = body.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(148, 342).addBox(-2.0F, -1.5F, -2.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(13.55F, -8.3049F, 40.3146F, 1.5708F, 0.0F, 0.0F));

		PartDefinition cube_r3 = body.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(338, 61).addBox(-2.0F, -1.5F, -2.0F, 3.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(13.55F, -8.3049F, 43.3146F, 1.5708F, 0.0F, 0.0F));

		PartDefinition cube_r4 = body.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(266, 345).addBox(22.0F, -3.0F, -31.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(72, 344).addBox(11.0F, -3.0F, -31.0F, 1.0F, 6.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(308, 180).addBox(11.0F, -3.0F, -31.0F, 12.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-26.0F, -30.7F, 15.2F, 0.384F, 0.0F, 0.0F));

		PartDefinition cube_r5 = body.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(72, 265).addBox(22.0F, -3.0F, -36.0F, 30.0F, 6.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(22, 312).addBox(34.0F, 3.0F, -36.0F, 6.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-37.0F, -30.0F, 15.0F, 0.384F, 0.0F, 0.0F));

		PartDefinition cube_r6 = body.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(318, 289).addBox(-2.0F, -7.0F, -11.0F, 6.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -10.25F, 11.0F, 0.384F, 0.0F, 0.0F));

		PartDefinition cube_r7 = body.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(346, 281).addBox(8.0F, -5.0F, 6.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(346, 263).addBox(2.0F, -5.0F, 6.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(312, 20).addBox(1.0F, -10.0F, 5.0F, 10.0F, 5.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(216, 310).addBox(1.0F, -4.0F, 5.0F, 10.0F, 11.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(312, 12).addBox(17.0F, -10.0F, 5.0F, 10.0F, 5.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(346, 209).addBox(18.0F, -5.0F, 6.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(346, 93).addBox(24.0F, -5.0F, 6.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(190, 310).addBox(17.0F, -4.0F, 5.0F, 10.0F, 11.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-14.0F, -18.25F, 8.0F, -0.4014F, 0.0F, 0.0F));

		PartDefinition cube_r8 = body.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(0, 285).addBox(-16.5F, 0.0F, 0.0F, 31.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -22.0F, 42.0F, -1.0036F, 0.0F, 0.0F));

		PartDefinition cube_r9 = body.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(256, 345).addBox(-0.5F, -2.0F, 0.0F, 1.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(32, 345).addBox(-33.5F, -2.0F, 0.0F, 1.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(16.5F, -7.0F, 40.0F, 0.3927F, 0.0F, 0.0F));

		PartDefinition cube_r10 = body.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(208, 288).addBox(-0.5F, 0.0F, 0.0F, 30.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(344, 336).addBox(-1.5F, 0.0F, 0.0F, 1.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(334, 343).addBox(29.5F, 0.0F, 0.0F, 1.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-14.5F, -15.0F, -43.0F, 0.5236F, 0.0F, 0.0F));

		PartDefinition cube_r11 = body.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(288, 268).addBox(-31.5F, 0.0F, 0.0F, 30.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(42, 347).addBox(-32.5F, 0.0F, 0.0F, 1.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(94, 337).addBox(-1.5F, 0.0F, 0.0F, 1.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(16.5F, -15.0F, -43.0F, -0.3927F, 0.0F, 0.0F));

		PartDefinition cube_r12 = body.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(140, 279).addBox(0.5F, -2.0F, 0.0F, 30.0F, 2.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(338, 54).addBox(-0.5F, -2.0F, 0.0F, 1.0F, 2.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(338, 12).addBox(30.5F, -2.0F, 0.0F, 1.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-15.5F, -9.0F, -45.0F, -0.6109F, 0.0F, 0.0F));

		PartDefinition cube_r13 = body.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(254, 205).addBox(0.5F, -0.0274F, 0.031F, 30.0F, 2.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(126, 314).addBox(-0.5F, -0.0274F, 0.031F, 1.0F, 2.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(108, 312).addBox(30.5F, -0.0274F, 0.031F, 1.0F, 2.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-15.5F, -19.55F, -29.9F, 0.1745F, 0.0F, 0.0F));

		PartDefinition cube_r14 = body.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(254, 183).addBox(0.5F, 0.007F, 0.0122F, 30.0F, 2.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(46, 312).addBox(-0.5F, 0.007F, 0.0122F, 1.0F, 2.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(312, 0).addBox(30.5F, 0.007F, 0.0122F, 1.0F, 2.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-15.5F, -17.0F, -39.55F, 0.2618F, 0.0F, 0.0F));

		PartDefinition cube_r15 = body.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(288, 273).addBox(0.5F, 0.0F, -17.0F, 30.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(126, 292).addBox(1.5F, 0.0F, -1.0F, 28.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(308, 77).addBox(-0.5F, 0.0F, -17.0F, 2.0F, 2.0F, 17.0F, new CubeDeformation(0.0F))
		.texOffs(126, 295).addBox(29.5F, 0.0F, -17.0F, 2.0F, 2.0F, 17.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-15.5F, -30.0F, -2.0F, 0.5672F, 0.0F, 0.0F));

		PartDefinition cube_r16 = body.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(288, 281).addBox(1.5F, 0.0F, 18.0F, 28.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(84, 291).addBox(-0.5F, 0.0F, 0.0F, 2.0F, 2.0F, 19.0F, new CubeDeformation(0.0F))
		.texOffs(288, 277).addBox(1.5F, 0.0F, 0.0F, 28.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(42, 291).addBox(29.5F, 0.0F, 0.0F, 2.0F, 2.0F, 19.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-15.5F, -30.0F, 18.0F, -0.3491F, 0.0F, 0.0F));

		PartDefinition cube_r17 = body.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(140, 286).addBox(-30.5F, 0.0F, 0.0F, 30.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(300, 344).addBox(-31.5F, 0.0F, 0.0F, 1.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(0, 344).addBox(-0.5F, 0.0F, 0.0F, 1.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(15.5F, -23.5F, 35.8F, -0.5672F, 0.0F, 0.0F));

		PartDefinition cube_r18 = body.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(26, 341).addBox(-0.499F, -1.0F, -1.0F, 1.0F, 9.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(190, 340).addBox(30.499F, -1.0F, -1.0F, 1.0F, 9.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-15.5F, -28.0F, 11.0F, 0.2618F, 0.0F, 0.0F));

		PartDefinition cube_r19 = body.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(0, 216).addBox(-15.0F, -0.5F, 0.3F, 30.0F, 1.0F, 18.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -17.2F, 18.0F, 0.1745F, 0.0F, 0.0F));

		PartDefinition cube_r20 = body.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(10, 347).addBox(0.0F, 0.0F, 0.0F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-16.0F, -15.0F, -43.0F, -0.4228F, -0.3693F, 0.161F));

		PartDefinition cube_r21 = body.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(118, 155).addBox(-1.0F, -1.0F, 0.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(118, 150).addBox(-1.4F, -1.0F, 0.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-17.0F, -12.0F, -45.0F, 0.0F, 0.7854F, 0.0F));

		PartDefinition cube_r22 = body.addOrReplaceChild("cube_r22", CubeListBuilder.create().texOffs(324, 343).addBox(0.0F, 0.0F, 0.0F, 1.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-16.0F, -15.0F, -43.0F, 0.4915F, -0.1884F, 0.3366F));

		PartDefinition cube_r23 = body.addOrReplaceChild("cube_r23", CubeListBuilder.create().texOffs(0, 312).addBox(0.0F, 0.007F, 0.0122F, 1.0F, 3.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-16.0F, -17.0F, -39.55F, 0.2435F, -0.0971F, 0.3721F));

		PartDefinition cube_r24 = body.addOrReplaceChild("cube_r24", CubeListBuilder.create().texOffs(100, 205).addBox(0.0F, -0.0274F, 0.031F, 1.0F, 3.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-16.0F, -19.55F, -29.9F, 0.1609F, -0.0679F, 0.3959F));

		PartDefinition cube_r25 = body.addOrReplaceChild("cube_r25", CubeListBuilder.create().texOffs(118, 0).addBox(0.0F, 0.0F, -3.0F, 1.0F, 3.0F, 60.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-16.0F, -21.0F, -19.0F, 0.0F, 0.0F, 0.3927F));

		PartDefinition cube_r26 = body.addOrReplaceChild("cube_r26", CubeListBuilder.create().texOffs(54, 324).addBox(0.0101F, 1.0192F, -6.0F, 1.0F, 3.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(40, 324).addBox(33.2899F, 1.0192F, -6.0F, 1.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-17.15F, -19.25F, -21.9F, 0.1745F, 0.0F, 0.0F));

		PartDefinition cube_r27 = body.addOrReplaceChild("cube_r27", CubeListBuilder.create().texOffs(308, 116).addBox(0.0101F, -0.0136F, -15.9878F, 1.0F, 4.0F, 16.0F, new CubeDeformation(0.0F))
		.texOffs(318, 295).addBox(0.0101F, 3.9864F, -13.9878F, 1.0F, 3.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(314, 257).addBox(33.2899F, 3.9864F, -13.9878F, 1.0F, 3.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(308, 96).addBox(33.2899F, -0.0136F, -15.9878F, 1.0F, 4.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-17.15F, -17.0F, -28.9F, 0.2618F, 0.0F, 0.0F));

		PartDefinition cube_r28 = body.addOrReplaceChild("cube_r28", CubeListBuilder.create().texOffs(312, 343).addBox(-1.5F, 0.015F, -3.9758F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(240, 183).addBox(32.5F, 0.015F, -3.9758F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-16.5F, -11.3F, -35.1F, 1.5708F, 0.0F, 0.0F));

		PartDefinition cube_r29 = body.addOrReplaceChild("cube_r29", CubeListBuilder.create().texOffs(138, 342).addBox(0.0F, -3.9927F, -0.7544F, 1.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-17.1399F, -18.238F, 38.7544F, 0.0F, 0.0F, 0.3927F));

		PartDefinition cube_r30 = body.addOrReplaceChild("cube_r30", CubeListBuilder.create().texOffs(342, 126).addBox(-0.4911F, -0.0088F, -0.0224F, 1.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(0, 277).addBox(0.1798F, -0.0088F, -0.0224F, 33.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(54, 339).addBox(32.7796F, -0.0088F, -0.0224F, 1.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-16.6399F, -18.9307F, 44.3F, -1.309F, 0.0F, 0.0F));

		PartDefinition cube_r31 = body.addOrReplaceChild("cube_r31", CubeListBuilder.create().texOffs(172, 343).addBox(-0.5F, 0.0F, -2.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(210, 284).addBox(0.3599F, -1.0F, -2.0F, 34.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(48, 347).addBox(34.3798F, 0.0F, -2.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-17.4399F, -13.2308F, 46.3F, 1.5708F, 0.0F, 0.0F));

		PartDefinition cube_r32 = body.addOrReplaceChild("cube_r32", CubeListBuilder.create().texOffs(300, 337).addBox(-0.4999F, -2.0F, -5.0F, 1.0F, 2.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(254, 215).addBox(0.52F, -2.0F, -5.0F, 34.0F, 2.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(338, 19).addBox(34.3797F, -2.0F, -5.0F, 1.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-17.4399F, -11.2308F, 47.3F, 0.6981F, 0.0F, 0.0F));

		PartDefinition cube_r33 = body.addOrReplaceChild("cube_r33", CubeListBuilder.create().texOffs(330, 336).addBox(-1.0F, 0.0F, -5.0F, 2.0F, 2.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(238, 336).addBox(33.0F, 0.0F, -5.0F, 2.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-17.0F, -18.3F, 24.8F, 0.3927F, 0.0F, 0.0F));

		PartDefinition cube_r34 = body.addOrReplaceChild("cube_r34", CubeListBuilder.create().texOffs(126, 342).addBox(-1.0F, 0.005F, -4.0231F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(178, 340).addBox(33.0F, 0.005F, -4.0231F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-17.0F, -16.4F, 20.2F, 0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r35 = body.addOrReplaceChild("cube_r35", CubeListBuilder.create().texOffs(342, 120).addBox(-1.0F, 0.005F, -3.9926F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(14, 341).addBox(33.0F, 0.005F, -3.9926F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-17.0F, -13.55F, 17.35F, 1.309F, 0.0F, 0.0F));

		PartDefinition cube_r36 = body.addOrReplaceChild("cube_r36", CubeListBuilder.create().texOffs(196, 336).addBox(-1.0F, 0.0227F, -5.002F, 2.0F, 2.0F, 5.1F, new CubeDeformation(0.0F))
		.texOffs(164, 336).addBox(33.0F, 0.0227F, -5.002F, 2.0F, 2.0F, 5.1F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-17.0F, -9.7F, 16.3F, 1.5708F, 0.0F, 0.0F));

		PartDefinition cube_r37 = body.addOrReplaceChild("cube_r37", CubeListBuilder.create().texOffs(336, 322).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 2.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(336, 301).addBox(33.0F, 0.0F, 0.0F, 2.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-17.0F, -18.3F, 27.6F, -0.3927F, 0.0F, 0.0F));

		PartDefinition cube_r38 = body.addOrReplaceChild("cube_r38", CubeListBuilder.create().texOffs(342, 114).addBox(-1.0F, 0.005F, 0.0231F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(78, 341).addBox(33.0F, 0.005F, 0.0231F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-17.0F, -16.4F, 32.2F, -0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r39 = body.addOrReplaceChild("cube_r39", CubeListBuilder.create().texOffs(114, 342).addBox(-1.0F, 0.005F, -0.0074F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(90, 342).addBox(33.0F, 0.005F, -0.0074F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-17.0F, -13.55F, 35.05F, -1.309F, 0.0F, 0.0F));

		PartDefinition cube_r40 = body.addOrReplaceChild("cube_r40", CubeListBuilder.create().texOffs(72, 257).addBox(-1.0F, 0.0227F, 0.002F, 5.0F, 2.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(96, 216).addBox(30.0F, 0.0227F, 0.002F, 5.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-17.0F, -9.7F, 36.1F, -1.5708F, 0.0F, 0.0F));

		PartDefinition cube_r41 = body.addOrReplaceChild("cube_r41", CubeListBuilder.create().texOffs(338, 315).addBox(-1.0F, 0.005F, -0.0075F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(338, 289).addBox(33.0F, 0.005F, -0.0075F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-17.0F, -13.55F, -17.7F, -1.309F, 0.0F, 0.0F));

		PartDefinition cube_r42 = body.addOrReplaceChild("cube_r42", CubeListBuilder.create().texOffs(64, 337).addBox(-1.0F, 0.0227F, 0.002F, 2.0F, 2.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(0, 337).addBox(33.0F, 0.0227F, 0.002F, 2.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-17.0F, -9.7F, -16.65F, -1.5708F, 0.0F, 0.0F));

		PartDefinition cube_r43 = body.addOrReplaceChild("cube_r43", CubeListBuilder.create().texOffs(342, 108).addBox(-1.0F, 0.005F, 0.0231F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(342, 96).addBox(33.0F, 0.005F, 0.0231F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-17.0F, -16.4F, -20.55F, -0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r44 = body.addOrReplaceChild("cube_r44", CubeListBuilder.create().texOffs(316, 336).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 2.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(336, 308).addBox(33.0F, 0.0F, 0.0F, 2.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-17.0F, -18.3F, -25.15F, -0.3927F, 0.0F, 0.0F));

		PartDefinition cube_r45 = body.addOrReplaceChild("cube_r45", CubeListBuilder.create().texOffs(224, 336).addBox(-1.0F, 0.0F, -5.0F, 2.0F, 2.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(210, 336).addBox(33.0F, 0.0F, -5.0F, 2.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-17.0F, -18.3F, -27.95F, 0.3927F, 0.0F, 0.0F));

		PartDefinition cube_r46 = body.addOrReplaceChild("cube_r46", CubeListBuilder.create().texOffs(342, 102).addBox(-1.0F, 0.005F, -4.0231F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(102, 342).addBox(33.0F, 0.005F, -4.0231F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-17.0F, -16.4F, -32.55F, 0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r47 = body.addOrReplaceChild("cube_r47", CubeListBuilder.create().texOffs(180, 330).addBox(-1.0F, 0.005F, -1.9926F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(32, 329).addBox(33.0F, 0.005F, -1.9926F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-17.0F, -13.55F, -35.4F, 1.309F, 0.0F, 0.0F));

		PartDefinition cube_r48 = body.addOrReplaceChild("cube_r48", CubeListBuilder.create().texOffs(0, 235).addBox(0.0F, 0.0F, -16.0F, 1.0F, 7.0F, 35.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-17.15F, -11.25F, -1.7F, 0.0F, 0.0F, -0.2443F));

		PartDefinition cube_r49 = body.addOrReplaceChild("cube_r49", CubeListBuilder.create().texOffs(240, 113).addBox(0.0F, -3.0F, -15.0F, 1.0F, 3.0F, 33.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-18.0F, -5.7F, -1.7F, 0.0F, 0.0F, 0.8029F));

		PartDefinition cube_r50 = body.addOrReplaceChild("cube_r50", CubeListBuilder.create().texOffs(338, 47).addBox(0.0F, -4.9488F, -1.4091F, 2.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-17.1399F, -18.238F, 38.7544F, -0.9599F, 0.0F, 0.3927F));

		PartDefinition cube_r51 = body.addOrReplaceChild("cube_r51", CubeListBuilder.create().texOffs(22, 322).addBox(-3.0F, 0.0227F, -4.002F, 4.0F, 2.0F, 5.1F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-17.0F, -9.7F, 16.1F, 0.4278F, 0.7519F, -1.2086F));

		PartDefinition cube_r52 = body.addOrReplaceChild("cube_r52", CubeListBuilder.create().texOffs(320, 315).addBox(-1.0F, 0.0227F, -4.002F, 4.0F, 2.0F, 5.1F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(17.0F, -9.7F, 16.1F, 0.4278F, -0.7519F, 1.2086F));

		PartDefinition cube_r53 = body.addOrReplaceChild("cube_r53", CubeListBuilder.create().texOffs(338, 40).addBox(-2.0F, -4.9488F, -1.4091F, 2.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(17.1399F, -18.238F, 38.7544F, -0.9599F, 0.0F, -0.3927F));

		PartDefinition cube_r54 = body.addOrReplaceChild("cube_r54", CubeListBuilder.create().texOffs(44, 339).addBox(-1.0F, -3.9927F, -0.7544F, 1.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(17.1399F, -18.238F, 38.7544F, 0.0F, 0.0F, -0.3927F));

		PartDefinition cube_r55 = body.addOrReplaceChild("cube_r55", CubeListBuilder.create().texOffs(0, 71).addBox(-1.0F, 0.0F, -3.0F, 1.0F, 3.0F, 60.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(16.0F, -21.0F, -19.0F, 0.0F, 0.0F, -0.3927F));

		PartDefinition cube_r56 = body.addOrReplaceChild("cube_r56", CubeListBuilder.create().texOffs(100, 194).addBox(-1.0F, -0.0274F, 0.031F, 1.0F, 3.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(16.0F, -19.55F, -29.9F, 0.1609F, 0.0679F, -0.3959F));

		PartDefinition cube_r57 = body.addOrReplaceChild("cube_r57", CubeListBuilder.create().texOffs(308, 244).addBox(-1.0F, 0.007F, 0.0122F, 1.0F, 3.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(16.0F, -17.0F, -39.55F, 0.2435F, 0.0971F, -0.3721F));

		PartDefinition cube_r58 = body.addOrReplaceChild("cube_r58", CubeListBuilder.create().texOffs(196, 343).addBox(-1.0F, 0.0F, 0.0F, 1.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(16.0F, -15.0F, -43.0F, 0.4915F, 0.1884F, -0.3366F));

		PartDefinition cube_r59 = body.addOrReplaceChild("cube_r59", CubeListBuilder.create().texOffs(118, 145).addBox(0.4F, -1.0F, 0.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(118, 140).addBox(0.0F, -1.0F, 0.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(17.0F, -12.0F, -45.0F, 0.0F, -0.7854F, 0.0F));

		PartDefinition cube_r60 = body.addOrReplaceChild("cube_r60", CubeListBuilder.create().texOffs(346, 257).addBox(-1.0F, 0.0F, 0.0F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(16.0F, -15.0F, -43.0F, -0.4228F, 0.3693F, -0.161F));

		PartDefinition cube_r61 = body.addOrReplaceChild("cube_r61", CubeListBuilder.create().texOffs(191, 246).addBox(-1.0F, 0.0F, 0.0F, 1.0F, 7.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(18.15F, -11.25F, -17.7F, 0.0F, -0.0873F, 0.2443F));

		PartDefinition cube_r62 = body.addOrReplaceChild("cube_r62", CubeListBuilder.create().texOffs(168, 223).addBox(-1.0F, 0.0F, -16.0F, 1.0F, 7.0F, 35.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(17.15F, -11.25F, -1.7F, 0.0F, 0.0F, 0.2443F));

		PartDefinition cube_r63 = body.addOrReplaceChild("cube_r63", CubeListBuilder.create().texOffs(240, 77).addBox(-1.0F, -3.0F, -15.0F, 1.0F, 3.0F, 33.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(18.0F, -5.7F, -1.7F, 0.0F, 0.0F, -0.8029F));

		PartDefinition steering_wheel = body.addOrReplaceChild("steering_wheel", CubeListBuilder.create().texOffs(118, 63).addBox(3.0F, -2.629F, 0.7843F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(184, 292).addBox(-3.0F, 2.371F, 0.7843F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(32, 339).addBox(-3.0F, -2.879F, 1.2843F, 6.0F, 6.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(230, 265).addBox(-2.0F, -0.879F, 0.7843F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(118, 134).addBox(-4.0F, -2.629F, 0.7843F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(338, 26).addBox(-3.0F, -3.629F, 0.7843F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(198, 292).addBox(-1.0F, 0.121F, 0.7843F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(100, 325).addBox(-1.0F, -0.879F, -1.2157F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-9.0F, -18.6429F, -11.5F, 0.4363F, 0.0F, 0.0F));

		PartDefinition shifter = body.addOrReplaceChild("shifter", CubeListBuilder.create().texOffs(118, 160).addBox(-0.75F, -2.5F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(40, 322).addBox(-1.75F, -3.5F, -0.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.25F, -12.75F, -6.5F));

		PartDefinition Front_Left_Wheel = body.addOrReplaceChild("Front_Left_Wheel", CubeListBuilder.create().texOffs(72, 235).addBox(0.5F, -5.6F, -5.51F, 0.0F, 11.0F, 11.0F, new CubeDeformation(0.0F))
		.texOffs(151, 367).addBox(0.25F, -1.06F, -4.6F, 1.0F, 2.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(16.0F, -8.3F, -26.79F));

		PartDefinition right_wheel_r1 = Front_Left_Wheel.addOrReplaceChild("right_wheel_r1", CubeListBuilder.create().texOffs(43, 365).addBox(-0.5F, -1.0F, -3.5F, 1.0F, 2.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.75F, -1.01F, -0.1F, -1.5708F, 0.0F, 0.0F));

		PartDefinition right_wheel_r2 = Front_Left_Wheel.addOrReplaceChild("right_wheel_r2", CubeListBuilder.create().texOffs(70, 300).addBox(3.0F, -1.5F, -2.51F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.25F, 1.0F, -0.1F, -1.5708F, 0.0F, 0.0F));

		PartDefinition right_wheel_r3 = Front_Left_Wheel.addOrReplaceChild("right_wheel_r3", CubeListBuilder.create().texOffs(270, 325).addBox(0.0F, -1.4697F, -7.5347F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 0.0F, 0.7854F, 0.0F, 0.0F));

		PartDefinition right_wheel_r4 = Front_Left_Wheel.addOrReplaceChild("right_wheel_r4", CubeListBuilder.create().texOffs(172, 324).addBox(0.0F, -1.4114F, -7.5036F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 0.0F, -2.7576F, 0.0F, 0.0F));

		PartDefinition right_wheel_r5 = Front_Left_Wheel.addOrReplaceChild("right_wheel_r5", CubeListBuilder.create().texOffs(16, 335).addBox(0.0F, -1.5F, -7.51F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 0.0F, 3.1416F, 0.0F, 0.0F));

		PartDefinition right_wheel_r6 = Front_Left_Wheel.addOrReplaceChild("right_wheel_r6", CubeListBuilder.create().texOffs(254, 325).addBox(0.0F, -1.4697F, -7.5347F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 0.0F, -2.3562F, 0.0F, 0.0F));

		PartDefinition right_wheel_r7 = Front_Left_Wheel.addOrReplaceChild("right_wheel_r7", CubeListBuilder.create().texOffs(84, 325).addBox(0.0F, -1.5226F, -7.5449F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 0.0F, -1.9635F, 0.0F, 0.0F));

		PartDefinition right_wheel_r8 = Front_Left_Wheel.addOrReplaceChild("right_wheel_r8", CubeListBuilder.create().texOffs(334, 330).addBox(0.0F, -1.5F, -7.51F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 0.0F, -1.5708F, 0.0F, 0.0F));

		PartDefinition right_wheel_r9 = Front_Left_Wheel.addOrReplaceChild("right_wheel_r9", CubeListBuilder.create().texOffs(156, 324).addBox(0.0F, -1.4114F, -7.5036F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 0.0F, -1.1868F, 0.0F, 0.0F));

		PartDefinition right_wheel_r10 = Front_Left_Wheel.addOrReplaceChild("right_wheel_r10", CubeListBuilder.create().texOffs(68, 325).addBox(0.0F, -1.4697F, -7.5347F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 0.0F, -0.7854F, 0.0F, 0.0F));

		PartDefinition right_wheel_r11 = Front_Left_Wheel.addOrReplaceChild("right_wheel_r11", CubeListBuilder.create().texOffs(0, 325).addBox(0.0F, -1.5226F, -7.5449F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 0.0F, -0.3927F, 0.0F, 0.0F));

		PartDefinition right_wheel_r12 = Front_Left_Wheel.addOrReplaceChild("right_wheel_r12", CubeListBuilder.create().texOffs(334, 295).addBox(-1.5F, -1.472F, -7.5999F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.5F, 0.0F, 0.0F, -0.0087F, 0.0F, 0.0F));

		PartDefinition right_wheel_r13 = Front_Left_Wheel.addOrReplaceChild("right_wheel_r13", CubeListBuilder.create().texOffs(334, 189).addBox(0.0F, -1.5F, -7.51F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 0.0F, 1.5708F, 0.0F, 0.0F));

		PartDefinition right_wheel_r14 = Front_Left_Wheel.addOrReplaceChild("right_wheel_r14", CubeListBuilder.create().texOffs(140, 324).addBox(0.0F, -1.4114F, -7.5036F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 0.0F, 1.9548F, 0.0F, 0.0F));

		PartDefinition right_wheel_r15 = Front_Left_Wheel.addOrReplaceChild("right_wheel_r15", CubeListBuilder.create().texOffs(238, 324).addBox(0.0F, -1.4697F, -7.5347F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 0.0F, 2.3562F, 0.0F, 0.0F));

		PartDefinition right_wheel_r16 = Front_Left_Wheel.addOrReplaceChild("right_wheel_r16", CubeListBuilder.create().texOffs(222, 324).addBox(0.0F, -1.5226F, -7.5449F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 0.0F, 2.7489F, 0.0F, 0.0F));

		PartDefinition right_wheel_r17 = Front_Left_Wheel.addOrReplaceChild("right_wheel_r17", CubeListBuilder.create().texOffs(206, 324).addBox(0.0F, -1.5226F, -7.5449F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 0.0F, 1.1781F, 0.0F, 0.0F));

		PartDefinition right_wheel_r18 = Front_Left_Wheel.addOrReplaceChild("right_wheel_r18", CubeListBuilder.create().texOffs(124, 324).addBox(0.0F, -1.4114F, -7.5036F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 0.0F, 0.384F, 0.0F, 0.0F));

		PartDefinition Front_Right_Wheel = body.addOrReplaceChild("Front_Right_Wheel", CubeListBuilder.create().texOffs(72, 235).mirror().addBox(-0.5F, -5.6F, -5.51F, 0.0F, 11.0F, 11.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(151, 367).mirror().addBox(-1.25F, -1.06F, -4.6F, 1.0F, 2.0F, 9.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-16.0F, -8.3F, -26.79F));

		PartDefinition left_wheel_r1 = Front_Right_Wheel.addOrReplaceChild("left_wheel_r1", CubeListBuilder.create().texOffs(43, 365).mirror().addBox(-0.5F, -1.0F, -3.5F, 1.0F, 2.0F, 9.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-0.75F, -1.01F, -0.1F, -1.5708F, 0.0F, 0.0F));

		PartDefinition left_wheel_r2 = Front_Right_Wheel.addOrReplaceChild("left_wheel_r2", CubeListBuilder.create().texOffs(70, 300).mirror().addBox(-4.0F, -1.5F, -2.51F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.25F, 1.0F, -0.1F, -1.5708F, 0.0F, 0.0F));

		PartDefinition left_wheel_r3 = Front_Right_Wheel.addOrReplaceChild("left_wheel_r3", CubeListBuilder.create().texOffs(270, 325).mirror().addBox(-5.0F, -1.4697F, -7.5347F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.0F, 0.0F, 0.0F, 0.7854F, 0.0F, 0.0F));

		PartDefinition left_wheel_r4 = Front_Right_Wheel.addOrReplaceChild("left_wheel_r4", CubeListBuilder.create().texOffs(172, 324).mirror().addBox(-5.0F, -1.4114F, -7.5036F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.0F, 0.0F, 0.0F, -2.7576F, 0.0F, 0.0F));

		PartDefinition left_wheel_r5 = Front_Right_Wheel.addOrReplaceChild("left_wheel_r5", CubeListBuilder.create().texOffs(16, 335).mirror().addBox(-5.0F, -1.5F, -7.51F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.0F, 0.0F, 0.0F, 3.1416F, 0.0F, 0.0F));

		PartDefinition left_wheel_r6 = Front_Right_Wheel.addOrReplaceChild("left_wheel_r6", CubeListBuilder.create().texOffs(254, 325).mirror().addBox(-5.0F, -1.4697F, -7.5347F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.0F, 0.0F, 0.0F, -2.3562F, 0.0F, 0.0F));

		PartDefinition left_wheel_r7 = Front_Right_Wheel.addOrReplaceChild("left_wheel_r7", CubeListBuilder.create().texOffs(84, 325).mirror().addBox(-5.0F, -1.5226F, -7.5449F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.0F, 0.0F, 0.0F, -1.9635F, 0.0F, 0.0F));

		PartDefinition left_wheel_r8 = Front_Right_Wheel.addOrReplaceChild("left_wheel_r8", CubeListBuilder.create().texOffs(334, 330).mirror().addBox(-5.0F, -1.5F, -7.51F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.0F, 0.0F, 0.0F, -1.5708F, 0.0F, 0.0F));

		PartDefinition left_wheel_r9 = Front_Right_Wheel.addOrReplaceChild("left_wheel_r9", CubeListBuilder.create().texOffs(156, 324).mirror().addBox(-5.0F, -1.4114F, -7.5036F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.0F, 0.0F, 0.0F, -1.1868F, 0.0F, 0.0F));

		PartDefinition left_wheel_r10 = Front_Right_Wheel.addOrReplaceChild("left_wheel_r10", CubeListBuilder.create().texOffs(68, 325).mirror().addBox(-5.0F, -1.4697F, -7.5347F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.0F, 0.0F, 0.0F, -0.7854F, 0.0F, 0.0F));

		PartDefinition left_wheel_r11 = Front_Right_Wheel.addOrReplaceChild("left_wheel_r11", CubeListBuilder.create().texOffs(0, 325).mirror().addBox(-5.0F, -1.5226F, -7.5449F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.0F, 0.0F, 0.0F, -0.3927F, 0.0F, 0.0F));

		PartDefinition left_wheel_r12 = Front_Right_Wheel.addOrReplaceChild("left_wheel_r12", CubeListBuilder.create().texOffs(334, 295).mirror().addBox(-3.5F, -1.472F, -7.5999F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(1.5F, 0.0F, 0.0F, -0.0087F, 0.0F, 0.0F));

		PartDefinition left_wheel_r13 = Front_Right_Wheel.addOrReplaceChild("left_wheel_r13", CubeListBuilder.create().texOffs(334, 189).mirror().addBox(-5.0F, -1.5F, -7.51F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.0F, 0.0F, 0.0F, 1.5708F, 0.0F, 0.0F));

		PartDefinition left_wheel_r14 = Front_Right_Wheel.addOrReplaceChild("left_wheel_r14", CubeListBuilder.create().texOffs(140, 324).mirror().addBox(-5.0F, -1.4114F, -7.5036F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.0F, 0.0F, 0.0F, 1.9548F, 0.0F, 0.0F));

		PartDefinition left_wheel_r15 = Front_Right_Wheel.addOrReplaceChild("left_wheel_r15", CubeListBuilder.create().texOffs(238, 324).mirror().addBox(-5.0F, -1.4697F, -7.5347F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.0F, 0.0F, 0.0F, 2.3562F, 0.0F, 0.0F));

		PartDefinition left_wheel_r16 = Front_Right_Wheel.addOrReplaceChild("left_wheel_r16", CubeListBuilder.create().texOffs(222, 324).mirror().addBox(-5.0F, -1.5226F, -7.5449F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.0F, 0.0F, 0.0F, 2.7489F, 0.0F, 0.0F));

		PartDefinition left_wheel_r17 = Front_Right_Wheel.addOrReplaceChild("left_wheel_r17", CubeListBuilder.create().texOffs(206, 324).mirror().addBox(-5.0F, -1.5226F, -7.5449F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.0F, 0.0F, 0.0F, 1.1781F, 0.0F, 0.0F));

		PartDefinition left_wheel_r18 = Front_Right_Wheel.addOrReplaceChild("left_wheel_r18", CubeListBuilder.create().texOffs(124, 324).mirror().addBox(-5.0F, -1.4114F, -7.5036F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.0F, 0.0F, 0.0F, 0.384F, 0.0F, 0.0F));

		PartDefinition Back_Left_Wheel = body.addOrReplaceChild("Back_Left_Wheel", CubeListBuilder.create().texOffs(308, 158).addBox(0.0F, -5.7F, -5.61F, 0.0F, 11.0F, 11.0F, new CubeDeformation(0.0F))
		.texOffs(151, 367).addBox(0.25F, -1.06F, -4.6F, 1.0F, 2.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(16.0F, -8.3F, 26.21F));

		PartDefinition right_wheel_r19 = Back_Left_Wheel.addOrReplaceChild("right_wheel_r19", CubeListBuilder.create().texOffs(70, 300).addBox(3.0F, -1.5F, -2.51F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.25F, 1.0F, -0.1F, -1.5708F, 0.0F, 0.0F));

		PartDefinition right_wheel_r20 = Back_Left_Wheel.addOrReplaceChild("right_wheel_r20", CubeListBuilder.create().texOffs(43, 365).addBox(-0.5F, -1.0F, -3.5F, 1.0F, 2.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.75F, -1.01F, -0.1F, -1.5708F, 0.0F, 0.0F));

		PartDefinition right_wheel_r21 = Back_Left_Wheel.addOrReplaceChild("right_wheel_r21", CubeListBuilder.create().texOffs(330, 252).addBox(0.0F, -1.4697F, -7.5347F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 0.0F, 0.7854F, 0.0F, 0.0F));

		PartDefinition right_wheel_r22 = Back_Left_Wheel.addOrReplaceChild("right_wheel_r22", CubeListBuilder.create().texOffs(330, 246).addBox(0.0F, -1.4114F, -7.5036F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 0.0F, -2.7576F, 0.0F, 0.0F));

		PartDefinition right_wheel_r23 = Back_Left_Wheel.addOrReplaceChild("right_wheel_r23", CubeListBuilder.create().texOffs(330, 240).addBox(0.0F, -1.5F, -7.51F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 0.0F, 3.1416F, 0.0F, 0.0F));

		PartDefinition right_wheel_r24 = Back_Left_Wheel.addOrReplaceChild("right_wheel_r24", CubeListBuilder.create().texOffs(236, 330).addBox(0.0F, -1.4697F, -7.5347F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 0.0F, -2.3562F, 0.0F, 0.0F));

		PartDefinition right_wheel_r25 = Back_Left_Wheel.addOrReplaceChild("right_wheel_r25", CubeListBuilder.create().texOffs(330, 234).addBox(0.0F, -1.5226F, -7.5449F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 0.0F, -1.9635F, 0.0F, 0.0F));

		PartDefinition right_wheel_r26 = Back_Left_Wheel.addOrReplaceChild("right_wheel_r26", CubeListBuilder.create().texOffs(330, 228).addBox(0.0F, -1.5F, -7.51F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 0.0F, -1.5708F, 0.0F, 0.0F));

		PartDefinition right_wheel_r27 = Back_Left_Wheel.addOrReplaceChild("right_wheel_r27", CubeListBuilder.create().texOffs(330, 222).addBox(0.0F, -1.4114F, -7.5036F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 0.0F, -1.1868F, 0.0F, 0.0F));

		PartDefinition right_wheel_r28 = Back_Left_Wheel.addOrReplaceChild("right_wheel_r28", CubeListBuilder.create().texOffs(220, 330).addBox(0.0F, -1.4697F, -7.5347F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 0.0F, -0.7854F, 0.0F, 0.0F));

		PartDefinition right_wheel_r29 = Back_Left_Wheel.addOrReplaceChild("right_wheel_r29", CubeListBuilder.create().texOffs(330, 207).addBox(0.0F, -1.5226F, -7.5449F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 0.0F, -0.3927F, 0.0F, 0.0F));

		PartDefinition right_wheel_r30 = Back_Left_Wheel.addOrReplaceChild("right_wheel_r30", CubeListBuilder.create().texOffs(204, 330).addBox(-1.5F, -1.472F, -7.5999F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.5F, 0.0F, 0.0F, -0.0087F, 0.0F, 0.0F));

		PartDefinition right_wheel_r31 = Back_Left_Wheel.addOrReplaceChild("right_wheel_r31", CubeListBuilder.create().texOffs(330, 201).addBox(0.0F, -1.5F, -7.51F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 0.0F, 1.5708F, 0.0F, 0.0F));

		PartDefinition right_wheel_r32 = Back_Left_Wheel.addOrReplaceChild("right_wheel_r32", CubeListBuilder.create().texOffs(330, 195).addBox(0.0F, -1.4114F, -7.5036F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 0.0F, 1.9548F, 0.0F, 0.0F));

		PartDefinition right_wheel_r33 = Back_Left_Wheel.addOrReplaceChild("right_wheel_r33", CubeListBuilder.create().texOffs(330, 172).addBox(0.0F, -1.4697F, -7.5347F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 0.0F, 2.3562F, 0.0F, 0.0F));

		PartDefinition right_wheel_r34 = Back_Left_Wheel.addOrReplaceChild("right_wheel_r34", CubeListBuilder.create().texOffs(330, 166).addBox(0.0F, -1.5226F, -7.5449F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 0.0F, 2.7489F, 0.0F, 0.0F));

		PartDefinition right_wheel_r35 = Back_Left_Wheel.addOrReplaceChild("right_wheel_r35", CubeListBuilder.create().texOffs(164, 330).addBox(0.0F, -1.5226F, -7.5449F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 0.0F, 1.1781F, 0.0F, 0.0F));

		PartDefinition right_wheel_r36 = Back_Left_Wheel.addOrReplaceChild("right_wheel_r36", CubeListBuilder.create().texOffs(330, 160).addBox(0.0F, -1.4114F, -7.5036F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 0.0F, 0.384F, 0.0F, 0.0F));

		PartDefinition Back_Right_Wheel = body.addOrReplaceChild("Back_Right_Wheel", CubeListBuilder.create().texOffs(308, 158).mirror().addBox(0.0F, -5.7F, -5.61F, 0.0F, 11.0F, 11.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(151, 367).mirror().addBox(-1.25F, -1.06F, -4.6F, 1.0F, 2.0F, 9.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-16.0F, -8.3F, 26.21F));

		PartDefinition left_wheel_r19 = Back_Right_Wheel.addOrReplaceChild("left_wheel_r19", CubeListBuilder.create().texOffs(70, 300).mirror().addBox(-4.0F, -1.5F, -2.51F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.25F, 1.0F, -0.1F, -1.5708F, 0.0F, 0.0F));

		PartDefinition left_wheel_r20 = Back_Right_Wheel.addOrReplaceChild("left_wheel_r20", CubeListBuilder.create().texOffs(43, 365).mirror().addBox(-0.5F, -1.0F, -3.5F, 1.0F, 2.0F, 9.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-0.75F, -1.01F, -0.1F, -1.5708F, 0.0F, 0.0F));

		PartDefinition left_wheel_r21 = Back_Right_Wheel.addOrReplaceChild("left_wheel_r21", CubeListBuilder.create().texOffs(330, 252).mirror().addBox(-5.0F, -1.4697F, -7.5347F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.0F, 0.0F, 0.0F, 0.7854F, 0.0F, 0.0F));

		PartDefinition left_wheel_r22 = Back_Right_Wheel.addOrReplaceChild("left_wheel_r22", CubeListBuilder.create().texOffs(330, 246).mirror().addBox(-5.0F, -1.4114F, -7.5036F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.0F, 0.0F, 0.0F, -2.7576F, 0.0F, 0.0F));

		PartDefinition left_wheel_r23 = Back_Right_Wheel.addOrReplaceChild("left_wheel_r23", CubeListBuilder.create().texOffs(330, 240).mirror().addBox(-5.0F, -1.5F, -7.51F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.0F, 0.0F, 0.0F, 3.1416F, 0.0F, 0.0F));

		PartDefinition left_wheel_r24 = Back_Right_Wheel.addOrReplaceChild("left_wheel_r24", CubeListBuilder.create().texOffs(236, 330).mirror().addBox(-5.0F, -1.4697F, -7.5347F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.0F, 0.0F, 0.0F, -2.3562F, 0.0F, 0.0F));

		PartDefinition left_wheel_r25 = Back_Right_Wheel.addOrReplaceChild("left_wheel_r25", CubeListBuilder.create().texOffs(330, 234).mirror().addBox(-5.0F, -1.5226F, -7.5449F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.0F, 0.0F, 0.0F, -1.9635F, 0.0F, 0.0F));

		PartDefinition left_wheel_r26 = Back_Right_Wheel.addOrReplaceChild("left_wheel_r26", CubeListBuilder.create().texOffs(330, 228).mirror().addBox(-5.0F, -1.5F, -7.51F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.0F, 0.0F, 0.0F, -1.5708F, 0.0F, 0.0F));

		PartDefinition left_wheel_r27 = Back_Right_Wheel.addOrReplaceChild("left_wheel_r27", CubeListBuilder.create().texOffs(330, 222).mirror().addBox(-5.0F, -1.4114F, -7.5036F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.0F, 0.0F, 0.0F, -1.1868F, 0.0F, 0.0F));

		PartDefinition left_wheel_r28 = Back_Right_Wheel.addOrReplaceChild("left_wheel_r28", CubeListBuilder.create().texOffs(220, 330).mirror().addBox(-5.0F, -1.4697F, -7.5347F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.0F, 0.0F, 0.0F, -0.7854F, 0.0F, 0.0F));

		PartDefinition left_wheel_r29 = Back_Right_Wheel.addOrReplaceChild("left_wheel_r29", CubeListBuilder.create().texOffs(330, 207).mirror().addBox(-5.0F, -1.5226F, -7.5449F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.0F, 0.0F, 0.0F, -0.3927F, 0.0F, 0.0F));

		PartDefinition left_wheel_r30 = Back_Right_Wheel.addOrReplaceChild("left_wheel_r30", CubeListBuilder.create().texOffs(204, 330).mirror().addBox(-3.5F, -1.472F, -7.5999F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(1.5F, 0.0F, 0.0F, -0.0087F, 0.0F, 0.0F));

		PartDefinition left_wheel_r31 = Back_Right_Wheel.addOrReplaceChild("left_wheel_r31", CubeListBuilder.create().texOffs(330, 201).mirror().addBox(-5.0F, -1.5F, -7.51F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.0F, 0.0F, 0.0F, 1.5708F, 0.0F, 0.0F));

		PartDefinition left_wheel_r32 = Back_Right_Wheel.addOrReplaceChild("left_wheel_r32", CubeListBuilder.create().texOffs(330, 195).mirror().addBox(-5.0F, -1.4114F, -7.5036F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.0F, 0.0F, 0.0F, 1.9548F, 0.0F, 0.0F));

		PartDefinition left_wheel_r33 = Back_Right_Wheel.addOrReplaceChild("left_wheel_r33", CubeListBuilder.create().texOffs(330, 172).mirror().addBox(-5.0F, -1.4697F, -7.5347F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.0F, 0.0F, 0.0F, 2.3562F, 0.0F, 0.0F));

		PartDefinition left_wheel_r34 = Back_Right_Wheel.addOrReplaceChild("left_wheel_r34", CubeListBuilder.create().texOffs(330, 166).mirror().addBox(-5.0F, -1.5226F, -7.5449F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.0F, 0.0F, 0.0F, 2.7489F, 0.0F, 0.0F));

		PartDefinition left_wheel_r35 = Back_Right_Wheel.addOrReplaceChild("left_wheel_r35", CubeListBuilder.create().texOffs(164, 330).mirror().addBox(-5.0F, -1.5226F, -7.5449F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.0F, 0.0F, 0.0F, 1.1781F, 0.0F, 0.0F));

		PartDefinition left_wheel_r36 = Back_Right_Wheel.addOrReplaceChild("left_wheel_r36", CubeListBuilder.create().texOffs(330, 160).mirror().addBox(-5.0F, -1.4114F, -7.5036F, 5.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.0F, 0.0F, 0.0F, 0.384F, 0.0F, 0.0F));

		PartDefinition Popup_Right = body.addOrReplaceChild("Popup_Right", CubeListBuilder.create(), PartPose.offset(-11.5F, -17.3F, -37.65F));

		PartDefinition cube_r64 = Popup_Right.addOrReplaceChild("cube_r64", CubeListBuilder.create().texOffs(78, 337).addBox(-28.499F, 1.983F, -0.0294F, 6.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(332, 219).addBox(-28.5F, -0.017F, -3.0294F, 6.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(320, 322).addBox(-28.5F, -0.017F, -2.0294F, 6.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(26.0F, 2.0F, -4.55F, 1.2217F, 0.0F, 0.0F));

		PartDefinition cube_r65 = Popup_Right.addOrReplaceChild("cube_r65", CubeListBuilder.create().texOffs(336, 178).addBox(-28.499F, 0.0F, 2.0F, 6.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(26.0F, 2.9F, -5.45F, 0.5236F, 0.0F, 0.0F));

		PartDefinition cube_r66 = Popup_Right.addOrReplaceChild("cube_r66", CubeListBuilder.create().texOffs(188, 324).addBox(-28.5F, 0.0F, 1.0F, 6.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(26.0F, 2.5F, -5.45F, 0.5236F, 0.0F, 0.0F));

		PartDefinition cube_r67 = Popup_Right.addOrReplaceChild("cube_r67", CubeListBuilder.create().texOffs(334, 183).addBox(-29.5F, 0.007F, 0.0122F, 6.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(27.0F, 0.5F, -2.0F, 0.2618F, 0.0F, 0.0F));

		PartDefinition Popup_Left = body.addOrReplaceChild("Popup_Left", CubeListBuilder.create(), PartPose.offset(10.5F, -17.3F, -37.65F));

		PartDefinition cube_r68 = Popup_Left.addOrReplaceChild("cube_r68", CubeListBuilder.create().texOffs(328, 72).addBox(22.499F, 1.983F, -0.0294F, 6.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(214, 265).addBox(22.5F, -0.017F, -3.0294F, 6.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(108, 322).addBox(22.5F, -0.017F, -2.0294F, 6.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-25.0F, 2.0F, -4.55F, 1.2217F, 0.0F, 0.0F));

		PartDefinition cube_r69 = Popup_Left.addOrReplaceChild("cube_r69", CubeListBuilder.create().texOffs(330, 264).addBox(22.499F, 0.0F, 2.0F, 6.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-25.0F, 2.9F, -5.45F, 0.5236F, 0.0F, 0.0F));

		PartDefinition cube_r70 = Popup_Left.addOrReplaceChild("cube_r70", CubeListBuilder.create().texOffs(310, 72).addBox(22.5F, 0.0F, 1.0F, 6.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-25.0F, 2.5F, -5.45F, 0.5236F, 0.0F, 0.0F));

		PartDefinition cube_r71 = Popup_Left.addOrReplaceChild("cube_r71", CubeListBuilder.create().texOffs(180, 334).addBox(23.5F, 0.007F, 0.0122F, 6.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-26.0F, 0.5F, -2.0F, 0.2618F, 0.0F, 0.0F));

		PartDefinition bodykits = body.addOrReplaceChild("bodykits", CubeListBuilder.create(), PartPose.offset(3.0F, 0.0F, 0.0F));

		PartDefinition street_body_kit = bodykits.addOrReplaceChild("street_body_kit", CubeListBuilder.create().texOffs(284, 345).addBox(14.0F, -18.3F, -27.95F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(274, 345).addBox(14.0F, -18.3F, 24.8F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(216, 343).addBox(14.15F, -18.25F, -21.7F, 1.0F, 3.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(16, 325).addBox(14.15F, -15.25F, -19.7F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(64, 344).addBox(14.15F, -18.25F, 17.3F, 1.0F, 5.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(268, 311).addBox(14.15F, -18.25F, 20.3F, 1.0F, 2.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(268, 311).mirror().addBox(-21.15F, -18.25F, 20.3F, 1.0F, 2.0F, 12.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(64, 344).mirror().addBox(-21.15F, -18.25F, 17.3F, 1.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(16, 325).mirror().addBox(-21.15F, -15.25F, -19.7F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(216, 343).mirror().addBox(-21.15F, -18.25F, -21.7F, 1.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(274, 345).mirror().addBox(-22.0F, -18.3F, 24.8F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(284, 345).mirror().addBox(-22.0F, -18.3F, -27.95F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r72 = street_body_kit.addOrReplaceChild("cube_r72", CubeListBuilder.create().texOffs(122, 249).mirror().addBox(-0.5F, -3.0F, 0.0F, 1.0F, 7.0F, 9.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-20.65F, -15.25F, -17.7F, 0.0F, 0.1222F, 0.0F));

		PartDefinition cube_r73 = street_body_kit.addOrReplaceChild("cube_r73", CubeListBuilder.create().texOffs(0, 337).mirror().addBox(-1.0F, 0.0227F, 0.002F, 2.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(0, 337).addBox(35.0F, 0.0227F, 0.002F, 2.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-21.0F, -9.7F, -16.65F, -1.5708F, 0.0F, 0.0F));

		PartDefinition cube_r74 = street_body_kit.addOrReplaceChild("cube_r74", CubeListBuilder.create().texOffs(338, 289).mirror().addBox(-1.0F, 0.005F, -0.0075F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(338, 289).addBox(35.0F, 0.005F, -0.0075F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-21.0F, -13.55F, -17.7F, -1.309F, 0.0F, 0.0F));

		PartDefinition cube_r75 = street_body_kit.addOrReplaceChild("cube_r75", CubeListBuilder.create().texOffs(342, 96).mirror().addBox(-1.0F, 0.005F, 0.0231F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(342, 96).addBox(35.0F, 0.005F, 0.0231F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-21.0F, -16.4F, -20.55F, -0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r76 = street_body_kit.addOrReplaceChild("cube_r76", CubeListBuilder.create().texOffs(336, 308).mirror().addBox(-1.0F, 0.0F, 0.0F, 2.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(336, 308).addBox(35.0F, 0.0F, 0.0F, 2.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-21.0F, -18.3F, -25.15F, -0.3927F, 0.0F, 0.0F));

		PartDefinition cube_r77 = street_body_kit.addOrReplaceChild("cube_r77", CubeListBuilder.create().texOffs(210, 336).mirror().addBox(-1.0F, 0.0F, -5.0F, 2.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(210, 336).addBox(35.0F, 0.0F, -5.0F, 2.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-21.0F, -18.3F, -27.95F, 0.3927F, 0.0F, 0.0F));

		PartDefinition cube_r78 = street_body_kit.addOrReplaceChild("cube_r78", CubeListBuilder.create().texOffs(102, 342).mirror().addBox(-1.0F, 0.005F, -4.0231F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(102, 342).addBox(35.0F, 0.005F, -4.0231F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-21.0F, -16.4F, -32.55F, 0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r79 = street_body_kit.addOrReplaceChild("cube_r79", CubeListBuilder.create().texOffs(240, 183).mirror().addBox(-1.5F, 0.015F, -3.9758F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(240, 183).addBox(34.5F, 0.015F, -3.9758F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-20.5F, -11.3F, -35.1F, 1.5708F, 0.0F, 0.0F));

		PartDefinition cube_r80 = street_body_kit.addOrReplaceChild("cube_r80", CubeListBuilder.create().texOffs(32, 329).mirror().addBox(-1.0F, 0.005F, -1.9926F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(32, 329).addBox(35.0F, 0.005F, -1.9926F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-21.0F, -13.55F, -35.4F, 1.309F, 0.0F, 0.0F));

		PartDefinition cube_r81 = street_body_kit.addOrReplaceChild("cube_r81", CubeListBuilder.create().texOffs(164, 336).mirror().addBox(-1.0F, 0.0227F, -5.002F, 2.0F, 2.0F, 5.1F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(164, 336).addBox(35.0F, 0.0227F, -5.002F, 2.0F, 2.0F, 5.1F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-21.0F, -9.7F, 16.3F, 1.5708F, 0.0F, 0.0F));

		PartDefinition cube_r82 = street_body_kit.addOrReplaceChild("cube_r82", CubeListBuilder.create().texOffs(14, 341).mirror().addBox(-1.0F, 0.005F, -3.9926F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(14, 341).addBox(35.0F, 0.005F, -3.9926F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-21.0F, -13.55F, 17.35F, 1.309F, 0.0F, 0.0F));

		PartDefinition cube_r83 = street_body_kit.addOrReplaceChild("cube_r83", CubeListBuilder.create().texOffs(178, 340).mirror().addBox(-1.0F, 0.005F, -4.0231F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(178, 340).addBox(35.0F, 0.005F, -4.0231F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-21.0F, -16.4F, 20.2F, 0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r84 = street_body_kit.addOrReplaceChild("cube_r84", CubeListBuilder.create().texOffs(238, 336).mirror().addBox(-1.0F, 0.0F, -5.0F, 2.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(238, 336).addBox(35.0F, 0.0F, -5.0F, 2.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-21.0F, -18.3F, 24.8F, 0.3927F, 0.0F, 0.0F));

		PartDefinition cube_r85 = street_body_kit.addOrReplaceChild("cube_r85", CubeListBuilder.create().texOffs(282, 284).mirror().addBox(-16.5F, -1.0F, 0.0F, 31.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.0F, -21.0F, 42.0F, 0.6109F, 0.0F, 0.0F));

		PartDefinition cube_r86 = street_body_kit.addOrReplaceChild("cube_r86", CubeListBuilder.create().texOffs(336, 301).mirror().addBox(-1.0F, 0.0F, 0.0F, 2.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(336, 301).addBox(35.0F, 0.0F, 0.0F, 2.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-21.0F, -18.3F, 27.6F, -0.3927F, 0.0F, 0.0F));

		PartDefinition cube_r87 = street_body_kit.addOrReplaceChild("cube_r87", CubeListBuilder.create().texOffs(78, 341).mirror().addBox(-1.0F, 0.005F, 0.0231F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(78, 341).addBox(35.0F, 0.005F, 0.0231F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-21.0F, -16.4F, 32.2F, -0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r88 = street_body_kit.addOrReplaceChild("cube_r88", CubeListBuilder.create().texOffs(90, 342).mirror().addBox(-1.0F, 0.005F, -0.0074F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(90, 342).addBox(35.0F, 0.005F, -0.0074F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-21.0F, -13.55F, 35.05F, -1.309F, 0.0F, 0.0F));

		PartDefinition cube_r89 = street_body_kit.addOrReplaceChild("cube_r89", CubeListBuilder.create().texOffs(164, 295).mirror().addBox(0.0F, -2.0F, 0.0F, 1.0F, 5.0F, 9.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-21.15F, -16.25F, 32.3F, 0.0F, 0.1047F, 0.0F));

		PartDefinition cube_r90 = street_body_kit.addOrReplaceChild("cube_r90", CubeListBuilder.create().texOffs(96, 216).mirror().addBox(-1.0F, 0.0227F, 0.002F, 5.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(96, 216).addBox(32.0F, 0.0227F, 0.002F, 5.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-21.0F, -9.7F, 36.1F, -1.5708F, 0.0F, 0.0F));

		PartDefinition cube_r91 = street_body_kit.addOrReplaceChild("cube_r91", CubeListBuilder.create().texOffs(43, 327).mirror().addBox(0.0101F, 1.0192F, -3.0F, 1.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(43, 327).addBox(35.2899F, 1.0192F, -3.0F, 1.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-21.15F, -19.25F, -21.9F, 0.1745F, 0.0F, 0.0F));

		PartDefinition cube_r92 = street_body_kit.addOrReplaceChild("cube_r92", CubeListBuilder.create().texOffs(96, 223).mirror().addBox(0.0F, -3.0F, -35.0F, 1.0F, 7.0F, 35.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-21.15F, -15.25F, 17.3F, 0.0F, -0.0611F, 0.0F));

		PartDefinition cube_r93 = street_body_kit.addOrReplaceChild("cube_r93", CubeListBuilder.create().texOffs(72, 107).mirror().addBox(0.0F, 0.0F, -9.0F, 1.0F, 3.0F, 24.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-19.3F, -20.6F, 23.0F, 0.0F, 0.0F, 0.672F));

		PartDefinition cube_r94 = street_body_kit.addOrReplaceChild("cube_r94", CubeListBuilder.create().texOffs(168, 223).mirror().addBox(0.0F, 0.0F, -35.0F, 1.0F, 7.0F, 35.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-21.15F, -11.25F, 17.3F, 0.0F, -0.0349F, -0.2443F));

		PartDefinition cube_r95 = street_body_kit.addOrReplaceChild("cube_r95", CubeListBuilder.create().texOffs(168, 223).addBox(-1.0F, 0.0F, -35.0F, 1.0F, 7.0F, 35.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(15.15F, -11.25F, 17.3F, 0.0F, 0.0349F, 0.2443F));

		PartDefinition cube_r96 = street_body_kit.addOrReplaceChild("cube_r96", CubeListBuilder.create().texOffs(72, 107).addBox(-1.0F, 0.0F, -9.0F, 1.0F, 3.0F, 24.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(13.3F, -20.6F, 23.0F, 0.0F, 0.0F, -0.672F));

		PartDefinition cube_r97 = street_body_kit.addOrReplaceChild("cube_r97", CubeListBuilder.create().texOffs(96, 223).addBox(-1.0F, -3.0F, -35.0F, 1.0F, 7.0F, 35.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(15.15F, -15.25F, 17.3F, 0.0F, 0.0611F, 0.0F));

		PartDefinition cube_r98 = street_body_kit.addOrReplaceChild("cube_r98", CubeListBuilder.create().texOffs(164, 295).addBox(-1.0F, -2.0F, 0.0F, 1.0F, 5.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(15.15F, -16.25F, 32.3F, 0.0F, -0.1047F, 0.0F));

		PartDefinition cube_r99 = street_body_kit.addOrReplaceChild("cube_r99", CubeListBuilder.create().texOffs(122, 249).addBox(-0.5F, -3.0F, 0.0F, 1.0F, 7.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(14.65F, -15.25F, -17.7F, 0.0F, -0.1222F, 0.0F));

		return LayerDefinition.create(meshdefinition, 512, 512);
	}

    @Override protected ModelPart body()             { return this.body; }
	@Override protected ModelPart frontLeftWheel()   { return this.Front_Left_Wheel; }
	@Override protected ModelPart frontRightWheel()  { return this.Front_Right_Wheel; }
	@Override protected ModelPart backLeftWheel()    { return this.Back_Left_Wheel; }
	@Override protected ModelPart backRightWheel()   { return this.Back_Right_Wheel; }
	@Override protected ModelPart steeringWheel()    { return this.steering_wheel; }
	@Override protected ModelPart shifter()          { return this.shifter; }
    @Override protected ModelPart bodykits()         {return this.BodyKits; }
    @Override protected ModelPart leftDoor()         {return null; }
    @Override protected ModelPart rightDoor()         {return null; }
    @Override protected ModelPart hood()         {return null; }

    
	protected ModelPart popup_left()          { return this.Popup_Left; }
	protected ModelPart popup_right()         { return this.Popup_Right; }

    @Override
    public void setupAnim(AE68RenderState state) {
        super.setupAnim(state);

        if (state.popup) {
            popup_left().xRot = 0f;
            popup_right().xRot = 0f;
        } else {
            popup_left().xRot = (float) Math.toRadians(-70);
            popup_right().xRot = (float) Math.toRadians(-70);

        }

    }

    
}
