package net.ent.entstupidstuff.client.render.entity.model;

import net.ent.entstupidstuff.client.render.entity.model.zombie.LobberModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.entity.state.ZombieRenderState;

public class SoulJackOLanternModel extends LobberModel {

    private final ModelPart head;
	private final ModelPart body;
	private final ModelPart left_arm;
	//private final ModelPart lantern;
	//private final ModelPart left_fire;
	private final ModelPart right_arm;
	//private final ModelPart right_fire;
	private final ModelPart right_leg;
	private final ModelPart left_leg;

    public SoulJackOLanternModel(ModelPart modelPart) {
        super(modelPart);
        this.head = root.getChild("head");
		this.body = root.getChild("body");
		this.left_arm = root.getChild("left_arm");
		//this.lantern = this.left_arm.getChild("lantern");
		//this.left_fire = this.left_arm.getChild("left_fire");
		this.right_arm = root.getChild("right_arm");
		//this.right_fire = this.right_arm.getChild("right_fire");
		this.right_leg = root.getChild("right_leg");
		this.left_leg = root.getChild("left_leg");

    }

    public static LayerDefinition getTexturedModelData(CubeDeformation dilation) {
		//MeshDefinition modelData = HumanoidModel.createMesh(dilation, 0.0F);
        MeshDefinition modelData = new MeshDefinition();
		PartDefinition partdefinition = modelData.getRoot();

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(32, 32).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(1.0F))
		.texOffs(84, 106).addBox(-5.5F, -9.5F, -5.5F, 11.0F, 11.0F, 11.0F, new CubeDeformation(-0.6F))
		.texOffs(84, 84).addBox(-5.5F, -9.5F, -5.5F, 11.0F, 11.0F, 11.0F, new CubeDeformation(-1.0F))
		.texOffs(5, 50).addBox(-1.0F, -11.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition hat = head.addOrReplaceChild("hat", CubeListBuilder.create().texOffs(0, 48).addBox(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 48).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r1 = body.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(24, 48).addBox(-4.0F, -7.0F, 0.0F, 8.0F, 7.0F, 4.0F, new CubeDeformation(0.25F))
		.texOffs(24, 48).addBox(-4.0F, -7.0F, -1.0F, 8.0F, 7.0F, 4.0F, new CubeDeformation(0.25F)), PartPose.offsetAndRotation(0.0F, 7.0F, -1.0F, 0.1745F, 0.0F, 0.0F));

		PartDefinition left_arm = partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(48, 48).addBox(-4.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(16, 64).addBox(-4.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F))
		.texOffs(92, 88).mirror().addBox(-4.0F, 3.0F, -2.0F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.45F)).mirror(false)
		.texOffs(64, 56).addBox(-4.0F, 6.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.5F))
		.texOffs(64, 64).addBox(-4.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.5F)), PartPose.offset(8.0F, 2.0F, 0.0F));

		PartDefinition lantern = left_arm.addOrReplaceChild("lantern", CubeListBuilder.create().texOffs(104, 52).addBox(-3.0F, 10.75F, -3.0F, 6.0F, 7.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(109, 66).addBox(-2.0F, 8.75F, -2.0F, 4.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 9.25F, 0.0F, 1.5708F, 0.0F, 0.0F));

		PartDefinition cube_r2 = lantern.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(115, 33).addBox(0.0F, -2.0F, -1.5F, 0.0F, 4.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(115, 33).addBox(0.0F, 3.0F, -1.5F, 0.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 3.75F, 0.0F, 0.0F, -0.7854F, 0.0F));

		PartDefinition cube_r3 = lantern.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(115, 33).addBox(0.0F, -2.0F, -1.5F, 0.0F, 4.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(115, 33).addBox(0.0F, 3.0F, -1.5F, 0.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.25F, 0.0F, 0.0F, 0.7854F, 0.0F));

		PartDefinition left_fire = left_arm.addOrReplaceChild("left_fire", CubeListBuilder.create().texOffs(93, 89).mirror().addBox(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.9F)).mirror(false), PartPose.offsetAndRotation(-2.0F, 11.0F, 0.0F, 0.0F, 0.0F, 3.1416F));

		PartDefinition right_arm = partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(0, 64).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(64, 16).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F))
		.texOffs(64, 48).addBox(-3.0F, 6.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.5F))
		.texOffs(92, 88).addBox(-3.0F, 3.0F, -2.0F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.45F))
		.texOffs(64, 72).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.5F)), PartPose.offset(-5.0F, 2.0F, 0.0F));

		PartDefinition right_fire = right_arm.addOrReplaceChild("right_fire", CubeListBuilder.create().texOffs(93, 89).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.9F)), PartPose.offsetAndRotation(-1.0F, 11.0F, 0.0F, 0.0F, 0.0F, -3.1416F));

		PartDefinition right_leg = partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(64, 0).addBox(-2.2F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(89, 89).addBox(-2.2F, 0.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.2F))
		.texOffs(84, 45).addBox(-2.2F, 7.75F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.4F)), PartPose.offset(-1.9F, 12.0F, 0.0F));

		PartDefinition armor_pads_r1 = right_leg.addOrReplaceChild("armor_pads_r1", CubeListBuilder.create().texOffs(64, 37).addBox(-2.0F, 5.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.25F)), PartPose.offsetAndRotation(-0.3F, -2.0F, -0.2F, 0.0F, 0.1222F, 0.0F));

		PartDefinition left_leg = partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(32, 64).addBox(-1.8F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(96, 89).mirror().addBox(-1.8F, 0.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.2F)).mirror(false)
		.texOffs(84, 45).mirror().addBox(-1.8F, 7.75F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.4F)).mirror(false), PartPose.offset(1.9F, 12.0F, 0.0F));

		PartDefinition armor_pads_r2 = left_leg.addOrReplaceChild("armor_pads_r2", CubeListBuilder.create().texOffs(64, 37).mirror().addBox(-2.0F, 5.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.25F)).mirror(false), PartPose.offsetAndRotation(0.3F, -2.0F, -0.2F, 0.0F, -0.1222F, 0.0F));

		return LayerDefinition.create(modelData, 128, 128);

	}

    @Override
    public void setupAnim(ZombieRenderState state) {
        super.setupAnim(state);
        this.right_arm.getChild("right_fire").visible = false;
        this.left_arm.getChild("left_fire").visible = false;
        this.left_arm.getChild("lantern").visible = false;
    }
    
}
