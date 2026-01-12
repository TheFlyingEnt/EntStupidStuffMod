package net.ent.entstupidstuff.client.render.entity.model;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.ZombieModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartNames;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.entity.state.ZombieRenderState;

public class SlimedZombieModel extends ZombieModel<ZombieRenderState>{

    public SlimedZombieModel(ModelPart modelPart) {
        super(modelPart);
    }
    
    public static LayerDefinition getTexturedModelData(CubeDeformation dilation) {
		MeshDefinition modelData = HumanoidModel.createMesh(dilation, 0.0F);
		PartDefinition modelPartData = modelData.getRoot(); //0,33 

		PartDefinition slime = modelData.getRoot().getChild(PartNames.HEAD);
		
        slime.addOrReplaceChild(
			"slime",
			CubeListBuilder.create().texOffs(0, 32).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(1F)),
			PartPose.offset(0.0F, 0.0F + 0.0F, 0.0F)
		);



		return LayerDefinition.create(modelData, 64, 64);
	}

}
