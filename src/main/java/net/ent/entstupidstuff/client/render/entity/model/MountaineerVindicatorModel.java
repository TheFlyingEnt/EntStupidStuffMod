package net.ent.entstupidstuff.client.render.entity.model;

import net.ent.entstupidstuff.client.entity.mob.MountaineerVindicatorEntity;
import net.ent.entstupidstuff.client.render.entity.state.MountaineerVindicatorRenderState;
import net.minecraft.client.model.IllagerModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class MountaineerVindicatorModel<S extends MountaineerVindicatorRenderState> extends IllagerModel<S>{

    private final ModelPart head;
	private final ModelPart hat;
	private final ModelPart arms;
	private final ModelPart leftLeg;
	private final ModelPart rightLeg;
	private final ModelPart rightArm;
	private final ModelPart leftArm;

    private final ModelPart backpack;
    private final ModelPart left_shoulder;
    private final ModelPart arms_sholder_pad;
    private final ModelPart left_sholder_pad;
    private final ModelPart right_boot;
    private final ModelPart left_boot;
    private final ModelPart right_arm_sholder_pad;
    private final ModelPart left_arm_sholder_pad;

    public MountaineerVindicatorModel(ModelPart modelPart) {
        super(modelPart);

        this.head = modelPart.getChild("head");
		this.hat = this.head.getChild("hat");
		this.hat.visible = false;
		this.arms = modelPart.getChild("arms");
		this.leftLeg = modelPart.getChild("left_leg");
		this.rightLeg = modelPart.getChild("right_leg");
		this.leftArm = modelPart.getChild("left_arm");
		this.rightArm = modelPart.getChild("right_arm");

        ModelPart body = modelPart.getChild("body");
        //ModelPart arms = modelPart.getChild("arms");
        //ModelPart rightLeg = modelPart.getChild("right_leg");
        //ModelPart leftLeg = modelPart.getChild("left_leg");
        ModelPart right_arm = modelPart.getChild("right_arm");
        ModelPart left_arm = modelPart.getChild("left_arm");

        this.backpack = body.getChild("backpack");
        this.left_shoulder = arms.getChild("left_shoulder");
        this.arms_sholder_pad = arms.getChild("arms_sholder_pad");
        this.left_sholder_pad = arms.getChild("left_sholder_pad");
        this.right_boot = rightLeg.getChild("right_boot");
        this.left_boot = leftLeg.getChild("left_boot");
        this.right_arm_sholder_pad = right_arm.getChild("right_arm_sholder_pad");
        this.left_arm_sholder_pad = left_arm.getChild("left_arm_sholder_pad");
    }

    public static LayerDefinition createBodyLayer() {
        
		MeshDefinition meshDefinition = new MeshDefinition();
		PartDefinition partDefinition = meshDefinition.getRoot();
		PartDefinition partDefinition2 = partDefinition.addOrReplaceChild(
			"head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -10.0F, -4.0F, 8.0F, 10.0F, 8.0F), PartPose.offset(0.0F, 0.0F, 0.0F)
		);
		partDefinition2.addOrReplaceChild(
			"hat", CubeListBuilder.create().texOffs(32, 0).addBox(-4.0F, -10.0F, -4.0F, 8.0F, 12.0F, 8.0F, new CubeDeformation(0.45F)), PartPose.ZERO
		);
		partDefinition2.addOrReplaceChild(
			"nose", CubeListBuilder.create().texOffs(24, 0).addBox(-1.0F, -1.0F, -6.0F, 2.0F, 4.0F, 2.0F), PartPose.offset(0.0F, -2.0F, 0.0F)
		);

        // # Adding Body

		PartDefinition partDefinitionBody = partDefinition.addOrReplaceChild(
			"body",
			CubeListBuilder.create()
				.texOffs(16, 20)
				.addBox(-4.0F, 0.0F, -3.0F, 8.0F, 12.0F, 6.0F)
				.texOffs(0, 38)
				.addBox(-4.0F, 0.0F, -3.0F, 8.0F, 20.0F, 6.0F, new CubeDeformation(0.5F)),
			PartPose.offset(0.0F, 0.0F, 0.0F)
		);

        partDefinitionBody.addOrReplaceChild("backpack", CubeListBuilder.create().texOffs(32, 3).addBox(0.0F, 0.0F, -3.0F, 7.0F, 11.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(28, 47).addBox(7.0F, 5.0F, -3.0F, 2.0F, 6.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(28, 38).addBox(-2.0F, 7.0F, -3.0F, 2.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.5F, 0.0F, 6.0F));


		PartDefinition partDefinition3 = partDefinition.addOrReplaceChild(
			"arms",
			CubeListBuilder.create().texOffs(44, 22).addBox(-8.0F, -2.0F, -2.0F, 4.0F, 8.0F, 4.0F).texOffs(40, 38).addBox(-4.0F, 2.0F, -2.0F, 8.0F, 4.0F, 4.0F),
			PartPose.offsetAndRotation(0.0F, 3.0F, -1.0F, -0.75F, 0.0F, 0.0F)
		);

		partDefinition3.addOrReplaceChild(
			"left_shoulder", CubeListBuilder.create().texOffs(44, 22).mirror().addBox(4.0F, -2.0F, -2.0F, 4.0F, 8.0F, 4.0F), PartPose.ZERO
		);

        // # Adding Arm Shoulder Pads

        partDefinition3.addOrReplaceChild("arms_sholder_pad", CubeListBuilder.create().texOffs(47, 1).addBox(-2.0F, -3.0F, -2.0F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.45F)), PartPose.offset(-6.0F, 1.0F, 0.0F));
        partDefinition3.addOrReplaceChild("left_sholder_pad", CubeListBuilder.create().texOffs(47, 1).mirror().addBox(-2.0F, -2.0F, -2.0F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.45F)).mirror(false), PartPose.offset(6.0F, 0.0F, 0.0F));
        

        // # Adding Legs + Boots

		PartDefinition partDefinitionRL = partDefinition.addOrReplaceChild(
			"right_leg", CubeListBuilder.create().texOffs(0, 22).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F), PartPose.offset(-2.0F, 12.0F, 0.0F)
		);
		PartDefinition partDefinitionLL = partDefinition.addOrReplaceChild(
			"left_leg", CubeListBuilder.create().texOffs(0, 22).mirror().addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F), PartPose.offset(2.0F, 12.0F, 0.0F)
		);

        //float g = 2.0F;
        float g = 0.0F;
        partDefinitionRL.addOrReplaceChild("right_boot", CubeListBuilder.create().texOffs(44, 34).addBox(-2.0F, 5.0F, -3.0F, 4.0F, 2.0F - g, 1.0F, new CubeDeformation(0.45F))
		.texOffs(47, 10).addBox(-2.0F, 6.0F, -2.0F, 4.0F, 6.0F - g, 4.0F, new CubeDeformation(0.45F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        partDefinitionLL.addOrReplaceChild("left_boot", CubeListBuilder.create().texOffs(47, 10).mirror().addBox(-2.0F, 6.0F, -2.0F, 4.0F, 6.0F - g, 4.0F, new CubeDeformation(0.45F)).mirror(false)
		.texOffs(44, 34).mirror().addBox(-2.0F, 5.0F, -3.0F, 4.0F, 2.0F - g, 1.0F, new CubeDeformation(0.45F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));



        // # Adding Arm(s) + Shoulder Pads

		PartDefinition partDefinitionRA = partDefinition.addOrReplaceChild(
			"right_arm", CubeListBuilder.create().texOffs(40, 46).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F), PartPose.offset(-5.0F, 2.0F, 0.0F)
		);
		PartDefinition partDefinitionLA = partDefinition.addOrReplaceChild(
			"left_arm", CubeListBuilder.create().texOffs(40, 46).mirror().addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F), PartPose.offset(5.0F, 2.0F, 0.0F)
		);

        
        partDefinitionRA.addOrReplaceChild("right_arm_sholder_pad", CubeListBuilder.create().texOffs(47, 1).addBox(-5.0F, -2.0F, -2.0F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.45F)), PartPose.offset(2.0F, 0.0F, 0.0F));
        partDefinitionLA.addOrReplaceChild("left_arm_sholder_pad", CubeListBuilder.create().texOffs(47, 1).mirror().addBox(1.0F, -2.0F, -2.0F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.45F)).mirror(false), PartPose.offset(8.0F, 0.0F, 0.0F));
        

		return LayerDefinition.create(meshDefinition, 64, 64);
	}

    @Override
    public void setupAnim(S illagerRenderState) {
        super.setupAnim(illagerRenderState);
        if (illagerRenderState.variant == MountaineerVindicatorEntity.Variant.DIAMOND) {
            this.backpack.visible = false;
            this.arms_sholder_pad.visible = true;
            this.left_sholder_pad.visible = true;
            this.right_boot.visible = true;
            this.left_boot.visible = true;
            this.right_arm_sholder_pad.visible = true;
            this.left_arm_sholder_pad.visible = true;
        }
        else if (illagerRenderState.variant == MountaineerVindicatorEntity.Variant.GOLD) {
            this.backpack.visible = true;
            this.arms_sholder_pad.visible = false;
            this.left_sholder_pad.visible = false;
            this.right_boot.visible = false;
            this.left_boot.visible = false;
            this.right_arm_sholder_pad.visible = false;
            this.left_arm_sholder_pad.visible = false;
        }
        else {
            this.backpack.visible = false;
            this.arms_sholder_pad.visible = false;
            this.left_sholder_pad.visible = false;
            this.right_boot.visible = false;
            this.left_boot.visible = false;
            this.right_arm_sholder_pad.visible = false;
            this.left_arm_sholder_pad.visible = false;
        }
        

    }
    
}
