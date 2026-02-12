package net.ent.entstupidstuff.client.render.entity.model;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.SkeletonModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartNames;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.entity.state.SkeletonRenderState;

public class SkeletonPirateCaptainModel<S extends SkeletonRenderState> extends HumanoidModel<S> {
	public ModelPart rightarm;
    public ModelPart leftarm;


	public SkeletonPirateCaptainModel(ModelPart modelPart) {
		super(modelPart);
		this.head.setPos(this.head.x, this.head.y, this.head.z);
        this.body.setPos(this.body.x, this.body.y, this.body.z);
        this.leftLeg.setPos(this.leftLeg.x, this.leftLeg.y, this.leftLeg.z);

        this.rightarm = modelPart.getChild(PartNames.RIGHT_ARM);
        this.leftarm = modelPart.getChild(PartNames.LEFT_ARM);
	}

	
	public static LayerDefinition getTexturedModelData() {

		//TODO: Refactor the Extra Model 
		//so that it works natively. Also convert the Clothing into Skeleton OuterLayer Render used in Strays


		MeshDefinition modelData = SkeletonModel.createMesh(CubeDeformation.NONE, 0.0F);

		PartDefinition modelPartData = modelData.getRoot();
		//SkeletonEntityModel.addLimbs(modelPartData);

		//float lower = + 22.0F;

		modelData.getRoot().addOrReplaceChild(
			PartNames.HEAD, CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
			.texOffs(82, 26).addBox(-5.0F, -6.0F, -5.1F, 10.0F, 1.0F, 10.0F, new CubeDeformation(0.0F))
			.texOffs(64, 0).addBox(-7.0F, -8.0F, -5.1F, 14.0F, 2.0F, 10.0F, new CubeDeformation(0.0F))
			.texOffs(92, 22).addBox(-5.0F, -11.0F, -5.1F, 10.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
			.texOffs(92, 12).addBox(-4.0F, -10.0F, -4.1F, 8.0F, 2.0F, 8.0F, new CubeDeformation(0.0F))
			.texOffs(92, 22).addBox(-5.0F, -11.0F, 3.9F, 10.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
			.texOffs(90, 41).addBox(-5.0F, -3.0F, -4.1F, 10.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)),
			PartPose.offset(0.0F, 24.0F, 0.0F)
		);

		modelData.getRoot().addOrReplaceChild(
			PartNames.RIGHT_ARM, CubeListBuilder.create().texOffs(40, 16).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F))
			.texOffs(112, 22).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F))
			.texOffs(112, 37).addBox(-2.0F, 5.0F, -2.0F, 4.0F, 2.0F, 4.0F, new CubeDeformation(0.5F)), //Changed
			PartPose.offset(-5.0F, 2.0F, 0.0F) //CC
		);

        modelData.getRoot().addOrReplaceChild(
			PartNames.LEFT_ARM, CubeListBuilder.create().texOffs(48, 16).mirror().addBox(-1.0F, -2.0F, -1.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
			.texOffs(112, 22).mirror().addBox(-2.0F, -2.0F, -2.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
			.texOffs(112, 37).mirror().addBox(-2.0F, 5.0F, -2.0F, 4.0F, 2.0F, 4.0F, new CubeDeformation(0.5F)).mirror(false), //Changed
			PartPose.offset(5.0F, 2.0F, 0.0F) //CC
		);

		modelData.getRoot().addOrReplaceChild(
			PartNames.LEFT_LEG,  CubeListBuilder.create().texOffs(8, 16).mirror().addBox(-1.0F, 0.0F, -1.1F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
			.texOffs(112, 0).mirror().addBox(-2.0F, 0.0F, -2.1F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
			.texOffs(87, 12).addBox(-1.5F, 7.0F, -1.6F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), 
			PartPose.offset(2.0F, 12.0F, 0.0F)
		);

		modelData.getRoot().addOrReplaceChild(
			PartNames.RIGHT_LEG,   CubeListBuilder.create().texOffs(0, 16).addBox(-1.0F, 0.0F, -1.1F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F))
			.texOffs(112, 0).addBox(-2.0F, 0.0F, -2.1F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)),
			PartPose.offset(-2.0F, 12.0F, 0.0F)
		);

		modelData.getRoot().addOrReplaceChild(
			PartNames.BODY, CubeListBuilder.create().texOffs(16, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
			.texOffs(64, 37).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
			.texOffs(64, 12).addBox(-4.5F, -2.0F, -2.5F, 9.0F, 19.0F, 5.0F, new CubeDeformation(0.0F)),
			PartPose.offset(0.0F, 0.0F, 0.0F)
		);

		return LayerDefinition.create(modelData, 128, 64);
	}

}