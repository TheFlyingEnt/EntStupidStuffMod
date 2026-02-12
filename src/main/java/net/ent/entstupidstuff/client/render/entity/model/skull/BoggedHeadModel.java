package net.ent.entstupidstuff.client.render.entity.model.skull;

import net.minecraft.client.model.SkullModel;
import net.minecraft.client.model.SkullModelBase;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.ResourceLocation;

public class BoggedHeadModel extends LayeredSkullModel {
    protected final ModelPart head;

    public BoggedHeadModel(ModelPart root, ModelPart outerLayerRoot, ResourceLocation outerTexture) {
        super(root, outerLayerRoot, outerTexture);
        this.head = root.getChild("head");
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

        PartDefinition partDefinition2 = partDefinition.getChild("head").addOrReplaceChild("mushrooms", CubeListBuilder.create(), PartPose.ZERO);
		partDefinition2.addOrReplaceChild(
			"red_mushroom_1",
			CubeListBuilder.create().texOffs(50, 16).addBox(-3.0F, -3.0F, 0.0F, 6.0F, 4.0F, 0.0F),
			PartPose.offsetAndRotation(3.0F, -8.0F, 3.0F, 0.0F, (float) (Math.PI / 4), 0.0F)
		);
		partDefinition2.addOrReplaceChild(
			"red_mushroom_2",
			CubeListBuilder.create().texOffs(50, 16).addBox(-3.0F, -3.0F, 0.0F, 6.0F, 4.0F, 0.0F),
			PartPose.offsetAndRotation(3.0F, -8.0F, 3.0F, 0.0F, (float) (Math.PI * 3.0 / 4.0), 0.0F)
		);
		partDefinition2.addOrReplaceChild(
			"brown_mushroom_1",
			CubeListBuilder.create().texOffs(50, 22).addBox(-3.0F, -3.0F, 0.0F, 6.0F, 4.0F, 0.0F),
			PartPose.offsetAndRotation(-3.0F, -8.0F, -3.0F, 0.0F, (float) (Math.PI / 4), 0.0F)
		);
		partDefinition2.addOrReplaceChild(
			"brown_mushroom_2",
			CubeListBuilder.create().texOffs(50, 22).addBox(-3.0F, -3.0F, 0.0F, 6.0F, 4.0F, 0.0F),
			PartPose.offsetAndRotation(-3.0F, -8.0F, -3.0F, 0.0F, (float) (Math.PI * 3.0 / 4.0), 0.0F)
		);
		partDefinition2.addOrReplaceChild(
			"brown_mushroom_3",
			CubeListBuilder.create().texOffs(50, 28).addBox(-3.0F, -4.0F, 0.0F, 6.0F, 4.0F, 0.0F),
			PartPose.offsetAndRotation(-2.0F, -1.0F, 4.0F, (float) (-Math.PI / 2), 0.0F, (float) (Math.PI / 4))
		);
		partDefinition2.addOrReplaceChild(
			"brown_mushroom_4",
			CubeListBuilder.create().texOffs(50, 28).addBox(-3.0F, -4.0F, 0.0F, 6.0F, 4.0F, 0.0F),
			PartPose.offsetAndRotation(-2.0F, -1.0F, 4.0F, (float) (-Math.PI / 2), 0.0F, (float) (Math.PI * 3.0 / 4.0))
		);
		return LayerDefinition.create(meshDefinition, 64, 32);
	}

	public static LayerDefinition createMobHeadLayer() {
		MeshDefinition meshDefinition = createHeadModel();
		PartDefinition partDefinition = meshDefinition.getRoot();

        PartDefinition partDefinition2 = partDefinition.getChild("head").addOrReplaceChild("mushrooms", CubeListBuilder.create(), PartPose.ZERO);
		partDefinition2.addOrReplaceChild(
			"red_mushroom_1",
			CubeListBuilder.create().texOffs(50, 16).addBox(-3.0F, -3.0F, 0.0F, 6.0F, 4.0F, 0.0F),
			PartPose.offsetAndRotation(3.0F, -8.0F, 3.0F, 0.0F, (float) (Math.PI / 4), 0.0F)
		);
		partDefinition2.addOrReplaceChild(
			"red_mushroom_2",
			CubeListBuilder.create().texOffs(50, 16).addBox(-3.0F, -3.0F, 0.0F, 6.0F, 4.0F, 0.0F),
			PartPose.offsetAndRotation(3.0F, -8.0F, 3.0F, 0.0F, (float) (Math.PI * 3.0 / 4.0), 0.0F)
		);
		partDefinition2.addOrReplaceChild(
			"brown_mushroom_1",
			CubeListBuilder.create().texOffs(50, 22).addBox(-3.0F, -3.0F, 0.0F, 6.0F, 4.0F, 0.0F),
			PartPose.offsetAndRotation(-3.0F, -8.0F, -3.0F, 0.0F, (float) (Math.PI / 4), 0.0F)
		);
		partDefinition2.addOrReplaceChild(
			"brown_mushroom_2",
			CubeListBuilder.create().texOffs(50, 22).addBox(-3.0F, -3.0F, 0.0F, 6.0F, 4.0F, 0.0F),
			PartPose.offsetAndRotation(-3.0F, -8.0F, -3.0F, 0.0F, (float) (Math.PI * 3.0 / 4.0), 0.0F)
		);
		partDefinition2.addOrReplaceChild(
			"brown_mushroom_3",
			CubeListBuilder.create().texOffs(50, 28).addBox(-3.0F, -4.0F, 0.0F, 6.0F, 4.0F, 0.0F),
			PartPose.offsetAndRotation(-2.0F, -1.0F, 4.0F, (float) (-Math.PI / 2), 0.0F, (float) (Math.PI / 4))
		);
		partDefinition2.addOrReplaceChild(
			"brown_mushroom_4",
			CubeListBuilder.create().texOffs(50, 28).addBox(-3.0F, -4.0F, 0.0F, 6.0F, 4.0F, 0.0F),
			PartPose.offsetAndRotation(-2.0F, -1.0F, 4.0F, (float) (-Math.PI / 2), 0.0F, (float) (Math.PI * 3.0 / 4.0))
		);
		return LayerDefinition.create(meshDefinition, 64, 32);
	}

	public void setupAnim(SkullModelBase.State state) {
		super.setupAnim(state);
		this.head.yRot = state.yRot * (float) (Math.PI / 180.0);
		this.head.xRot = state.xRot * (float) (Math.PI / 180.0);
	}
    
}
