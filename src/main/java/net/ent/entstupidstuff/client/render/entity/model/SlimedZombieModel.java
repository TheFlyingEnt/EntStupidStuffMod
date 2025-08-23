package net.ent.entstupidstuff.client.render.entity.model;

import net.minecraft.client.model.Dilation;
import net.minecraft.client.model.ModelData;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.ModelPartBuilder;
import net.minecraft.client.model.ModelPartData;
import net.minecraft.client.model.ModelTransform;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.DrownedEntityModel;
import net.minecraft.client.render.entity.model.EntityModelPartNames;
import net.minecraft.entity.mob.ZombieEntity;

public class SlimedZombieModel<T extends ZombieEntity> extends DrownedEntityModel<T>{

    public SlimedZombieModel(ModelPart modelPart) {
        super(modelPart);
    }
    
    public static TexturedModelData getTexturedModelData(Dilation dilation) {
		ModelData modelData = BipedEntityModel.getModelData(dilation, 0.0F);
		ModelPartData modelPartData = modelData.getRoot(); //0,33 

		ModelPartData slime = modelData.getRoot().getChild(EntityModelPartNames.HEAD);
		
        slime.addChild(
			"slime",
			ModelPartBuilder.create().uv(0, 32).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(1F)),
			ModelTransform.pivot(0.0F, 0.0F + 0.0F, 0.0F)
		);



		return TexturedModelData.of(modelData, 64, 64);
	}

}
