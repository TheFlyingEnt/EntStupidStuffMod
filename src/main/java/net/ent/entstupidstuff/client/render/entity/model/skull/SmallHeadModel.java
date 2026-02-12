package net.ent.entstupidstuff.client.render.entity.model.skull;

import net.minecraft.client.model.SkullModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;

public class SmallHeadModel extends SkullModel{

    public SmallHeadModel(ModelPart modelPart) {
        super(modelPart);
    }

    public static LayerDefinition createSmallMobHeadLayer() {
		MeshDefinition meshDefinition = createHeadModel();
		return LayerDefinition.create(meshDefinition, 32, 32);
	}
    
}
