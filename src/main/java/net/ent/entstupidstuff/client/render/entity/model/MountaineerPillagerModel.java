package net.ent.entstupidstuff.client.render.entity.model;

import net.ent.entstupidstuff.client.render.entity.state.MountaineerPillagerRenderState;
import net.minecraft.client.model.IllagerModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class MountaineerPillagerModel<S extends MountaineerPillagerRenderState> extends IllagerModel<S>{

    public MountaineerPillagerModel(ModelPart modelPart) {
        super(modelPart);
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

        partDefinitionBody.addOrReplaceChild("quiver", CubeListBuilder.create().texOffs(46, 20).addBox(-2.0F, -4.0F, 0.0F, 4.0F, 10.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(30, 42).addBox(-2.5F, -10.0F, 2.0F, 5.0F, 10.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(30, 37).addBox(0.0F, -10.0F, -0.5F, 0.0F, 10.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 4.0F, 3.0F, 0.0F, 0.0F, -0.3927F));


		PartDefinition partDefinition3 = partDefinition.addOrReplaceChild(
			"arms",
			CubeListBuilder.create().texOffs(44, 22).addBox(-8.0F, -2.0F, -2.0F, 4.0F, 8.0F, 4.0F).texOffs(40, 38).addBox(-4.0F, 2.0F, -2.0F, 8.0F, 4.0F, 4.0F),
			PartPose.offsetAndRotation(0.0F, 3.0F, -1.0F, -0.75F, 0.0F, 0.0F)
		);
		partDefinition3.addOrReplaceChild(
			"left_shoulder", CubeListBuilder.create().texOffs(44, 22).mirror().addBox(4.0F, -2.0F, -2.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.ZERO
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

        partDefinitionRL.addOrReplaceChild("right_boot", CubeListBuilder.create().texOffs(44, 34).addBox(-2.0F, 5.0F, -3.0F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.25F))
		.texOffs(47, 10).addBox(-2.0F, 6.0F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.25F)), PartPose.offset(0.0F, 9.0F, 0.0F));

        partDefinitionLL.addOrReplaceChild("left_boot", CubeListBuilder.create().texOffs(47, 10).mirror().addBox(-2.0F, 6.0F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.25F)).mirror(false)
		.texOffs(44, 34).mirror().addBox(-2.0F, 5.0F, -3.0F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.25F)).mirror(false), PartPose.offset(0.0F, 9.0F, 0.0F));

        // # Adding Arm(s) + Shoulder Pads

		PartDefinition partDefinitionRA = partDefinition.addOrReplaceChild(
			"right_arm", CubeListBuilder.create().texOffs(40, 46).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F), PartPose.offset(-5.0F, 2.0F, 0.0F)
		);
		PartDefinition partDefinitionLA = partDefinition.addOrReplaceChild(
			"left_arm", CubeListBuilder.create().texOffs(40, 46).mirror().addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F), PartPose.offset(5.0F, 2.0F, 0.0F)
		);

        partDefinitionRA.addOrReplaceChild("right_arm_sholder_pad", CubeListBuilder.create().texOffs(47, 1).addBox(-5.0F, -2.0F, -2.0F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.25F)), PartPose.offset(2.0F, 0.0F, 0.0F));
        partDefinitionLA.addOrReplaceChild("left_arm_sholder_pad", CubeListBuilder.create().texOffs(47, 1).mirror().addBox(1.0F, -2.0F, -2.0F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.25F)).mirror(false), PartPose.offset(-2.0F, 0.0F, 0.0F));

		return LayerDefinition.create(meshDefinition, 64, 64);
	}
    
}
