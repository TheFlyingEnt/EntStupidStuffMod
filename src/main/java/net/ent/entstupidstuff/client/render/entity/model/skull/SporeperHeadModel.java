package net.ent.entstupidstuff.client.render.entity.model.skull;

import net.minecraft.client.model.SkullModelBase;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartNames;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class SporeperHeadModel extends SkullModelBase {
    protected final ModelPart head;

    public SporeperHeadModel(ModelPart modelPart) {
        super(modelPart);
        this.head = modelPart.getChild("head");
    }

    public static MeshDefinition createHeadModel() {
		MeshDefinition meshDefinition = new MeshDefinition();
		PartDefinition partDefinition = meshDefinition.getRoot();
		partDefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F), PartPose.ZERO);
		return meshDefinition;
	}

	public static LayerDefinition createHumanoidHeadLayer() {
		MeshDefinition meshDefinition = createHeadModel();
		PartDefinition partDefinition = meshDefinition.getRoot();

        //partDefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F), PartPose.ZERO);

        partDefinition.addOrReplaceChild(
			PartNames.HEAD,
			CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F).texOffs(0, 32).addBox(-7.0F, -11.0F, -7.0F, 14.0F, 5.0F, 14.0F),
			PartPose.ZERO
		);
		return LayerDefinition.create(meshDefinition, 64, 64);
	}

	public static LayerDefinition createMobHeadLayer() {
		MeshDefinition meshDefinition = createHeadModel();
		return LayerDefinition.create(meshDefinition, 64, 32);
	}

	public void setupAnim(SkullModelBase.State state) {
		super.setupAnim(state);
		this.head.yRot = state.yRot * (float) (Math.PI / 180.0);
		this.head.xRot = state.xRot * (float) (Math.PI / 180.0);
	}
    
}
