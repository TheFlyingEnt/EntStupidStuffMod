package net.ent.entstupidstuff.client.render.entity.model.skull;

import net.minecraft.client.model.SkullModelBase;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class CoralSkeletonSkullModel extends SkullModelBase {
    protected final ModelPart head;

    public CoralSkeletonSkullModel(ModelPart modelPart) {
        super(modelPart);
        this.head = modelPart.getChild("head");
    }

    public static LayerDefinition createCoralSkeletonSkullLayer() {
		MeshDefinition meshDefinition = createHeadModel();
		return LayerDefinition.create(meshDefinition, 64, 32);
	}

    public static MeshDefinition createHeadModel() {
		MeshDefinition meshDefinition = new MeshDefinition();
		PartDefinition partDefinition = meshDefinition.getRoot();
		PartDefinition headDefinition = partDefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F), PartPose.ZERO);

        headDefinition.addOrReplaceChild("head_coral", CubeListBuilder.create().texOffs(25, 0).addBox(2.0F, -3.0F, 2.0F, 3.0F, 0.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(22, 0).addBox(-1.0F, -3.0F, 2.0F, 3.0F, 0.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(32, 1).addBox(-1.0F, -15.0F, -4.01F, 9.0F, 9.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
        
		return meshDefinition;
	}

    public void setupAnim(SkullModelBase.State state) {
		super.setupAnim(state);
		this.head.yRot = state.yRot * (float) (Math.PI / 180.0);
		this.head.xRot = state.xRot * (float) (Math.PI / 180.0);
	}
    
}
