package net.ent.entstupidstuff.client.render.entity.model.illager;

import net.ent.entstupidstuff.client.render.entity.state.ArmoredPillagerRenderState;
import net.minecraft.client.model.IllagerModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class PillagerHelmetModel<S extends ArmoredPillagerRenderState> extends IllagerModel<S>{
    //private final ModelPart helmet;
  

    public PillagerHelmetModel(ModelPart modelPart) {
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
		partDefinition.addOrReplaceChild(
			"body",
			CubeListBuilder.create()
				.texOffs(16, 20)
				.addBox(-4.0F, 0.0F, -3.0F, 8.0F, 12.0F, 6.0F)
				.texOffs(0, 38)
				.addBox(-4.0F, 0.0F, -3.0F, 8.0F, 20.0F, 6.0F, new CubeDeformation(0.5F)),
			PartPose.offset(0.0F, 0.0F, 0.0F)
		);
		PartDefinition partDefinition3 = partDefinition.addOrReplaceChild(
			"arms",
			CubeListBuilder.create().texOffs(44, 22).addBox(-8.0F, -2.0F, -2.0F, 4.0F, 8.0F, 4.0F).texOffs(40, 38).addBox(-4.0F, 2.0F, -2.0F, 8.0F, 4.0F, 4.0F),
			PartPose.offsetAndRotation(0.0F, 3.0F, -1.0F, -0.75F, 0.0F, 0.0F)
		);
		partDefinition3.addOrReplaceChild(
			"left_shoulder", CubeListBuilder.create().texOffs(44, 22).mirror().addBox(4.0F, -2.0F, -2.0F, 4.0F, 8.0F, 4.0F), PartPose.ZERO
		);
		partDefinition.addOrReplaceChild(
			"right_leg", CubeListBuilder.create().texOffs(0, 22).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F), PartPose.offset(-2.0F, 12.0F, 0.0F)
		);
		partDefinition.addOrReplaceChild(
			"left_leg", CubeListBuilder.create().texOffs(0, 22).mirror().addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F), PartPose.offset(2.0F, 12.0F, 0.0F)
		);
		partDefinition.addOrReplaceChild(
			"right_arm", CubeListBuilder.create().texOffs(40, 46).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F), PartPose.offset(-5.0F, 2.0F, 0.0F)
		);
		partDefinition.addOrReplaceChild(
			"left_arm", CubeListBuilder.create().texOffs(40, 46).mirror().addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F), PartPose.offset(5.0F, 2.0F, 0.0F)
		);
		return LayerDefinition.create(meshDefinition, 64, 64);

        /*MeshDefinition meshDefinition = new MeshDefinition();
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

        partDefinition2.addOrReplaceChild("helmet", CubeListBuilder.create().texOffs(32, 0).addBox(-4.0F, -10.0F, -4.0F, 8.0F, 10.0F, 8.0F, new CubeDeformation(0.5F))
		.texOffs(38, 24).addBox(0.0F, -5.0F, -4.0F, 6.0F, 0.0F, 10.0F, new CubeDeformation(0.5F, 0F, 0.5F))
		.texOffs(38, 24).mirror().addBox(-6.0F, -5.0F, -4.0F, 6.0F, 0.0F, 10.0F, new CubeDeformation(0.5F, 0F, 0.5F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));

        
		partDefinition.addOrReplaceChild(
			"body",
			CubeListBuilder.create()
				.texOffs(16, 20)
				.addBox(-4.0F, 0.0F, -3.0F, 8.0F, 12.0F, 6.0F)
				.texOffs(0, 38)
				.addBox(-4.0F, 0.0F, -3.0F, 8.0F, 20.0F, 6.0F, new CubeDeformation(0.5F)),
			PartPose.offset(0.0F, 0.0F, 0.0F)
		);
		PartDefinition partDefinition3 = partDefinition.addOrReplaceChild(
			"arms",
			CubeListBuilder.create().texOffs(44, 22).addBox(-8.0F, -2.0F, -2.0F, 4.0F, 8.0F, 4.0F).texOffs(40, 38).addBox(-4.0F, 2.0F, -2.0F, 8.0F, 4.0F, 4.0F),
			PartPose.offsetAndRotation(0.0F, 3.0F, -1.0F, -0.75F, 0.0F, 0.0F)
		);
		partDefinition3.addOrReplaceChild(
			"left_shoulder", CubeListBuilder.create().texOffs(44, 22).mirror().addBox(4.0F, -2.0F, -2.0F, 4.0F, 8.0F, 4.0F), PartPose.ZERO
		);
		partDefinition.addOrReplaceChild(
			"right_leg", CubeListBuilder.create().texOffs(0, 22).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F), PartPose.offset(-2.0F, 12.0F, 0.0F)
		);
		partDefinition.addOrReplaceChild(
			"left_leg", CubeListBuilder.create().texOffs(0, 22).mirror().addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F), PartPose.offset(2.0F, 12.0F, 0.0F)
		);
		partDefinition.addOrReplaceChild(
			"right_arm", CubeListBuilder.create().texOffs(40, 46).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F), PartPose.offset(-5.0F, 2.0F, 0.0F)
		);
		partDefinition.addOrReplaceChild(
			"left_arm", CubeListBuilder.create().texOffs(40, 46).mirror().addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F), PartPose.offset(5.0F, 2.0F, 0.0F)
		);

        return LayerDefinition.create(meshDefinition, 64, 64);*/
    }
    
}
