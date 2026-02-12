package net.ent.entstupidstuff.client.render.entity.model;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartNames;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.entity.state.CreeperRenderState;
import net.minecraft.util.Mth;

public class SporeperModel extends EntityModel<CreeperRenderState> {
    private final ModelPart head;
	private final ModelPart leftHindLeg;
	private final ModelPart rightHindLeg;
	private final ModelPart leftFrontLeg;
	private final ModelPart rightFrontLeg;
    
	public SporeperModel(ModelPart modelPart) {
		super(modelPart);
		this.head = modelPart.getChild(PartNames.HEAD);
		this.rightHindLeg = modelPart.getChild(PartNames.RIGHT_HIND_LEG);
		this.leftHindLeg = modelPart.getChild(PartNames.LEFT_HIND_LEG);
		this.rightFrontLeg = modelPart.getChild(PartNames.RIGHT_FRONT_LEG);
		this.leftFrontLeg = modelPart.getChild(PartNames.LEFT_FRONT_LEG);
	}

    public static LayerDefinition getTexturedModelData(CubeDeformation dilation) {
		MeshDefinition modelData = new MeshDefinition();
		PartDefinition modelPartData = modelData.getRoot();
		modelPartData.addOrReplaceChild(
			PartNames.HEAD,
			CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, dilation)
            //Creeper HAT
            .texOffs(0, 32).addBox(-7.0F, -11.0F, -7.0F, 14.0F, 5.0F, 14.0F, new CubeDeformation(0.0F)),
			PartPose.offset(0.0F, 6.0F, 0.0F)
		);
		modelPartData.addOrReplaceChild(
			PartNames.BODY,
			CubeListBuilder.create().texOffs(16, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, dilation),
            PartPose.offset(0.0F, 6.0F, 0.0F)
		);

        

        modelPartData.addOrReplaceChild(
			"body_upper",
			CubeListBuilder.create() // Creaper Top Layer
            .texOffs(40, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 5.0F, 4.0F, dilation.extend(0.25F)),
            PartPose.offset(0.0F, 6.0F, 0.0F)
		);

		CubeListBuilder modelPartBuilder = CubeListBuilder.create().texOffs(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, dilation);
		modelPartData.addOrReplaceChild(PartNames.RIGHT_HIND_LEG, modelPartBuilder, PartPose.offset(-2.0F, 18.0F, 4.0F));
		modelPartData.addOrReplaceChild(PartNames.LEFT_HIND_LEG, modelPartBuilder, PartPose.offset(2.0F, 18.0F, 4.0F));
		modelPartData.addOrReplaceChild(PartNames.RIGHT_FRONT_LEG, modelPartBuilder, PartPose.offset(-2.0F, 18.0F, -4.0F));
		modelPartData.addOrReplaceChild(PartNames.LEFT_FRONT_LEG, modelPartBuilder, PartPose.offset(2.0F, 18.0F, -4.0F));
		return LayerDefinition.create(modelData, 64, 64);
	}

	public void setupAnim(CreeperRenderState creeperEntityRenderState) {
		super.setupAnim(creeperEntityRenderState);
		this.head.yRot = creeperEntityRenderState.yRot * (float) (Math.PI / 180.0);
		this.head.xRot = creeperEntityRenderState.xRot * (float) (Math.PI / 180.0);
		float f = creeperEntityRenderState.walkAnimationSpeed;
		float g = creeperEntityRenderState.walkAnimationPos;
		this.leftHindLeg.xRot = Mth.cos(g * 0.6662F) * 1.4F * f;
		this.rightHindLeg.xRot = Mth.cos(g * 0.6662F + (float) Math.PI) * 1.4F * f;
		this.leftFrontLeg.xRot = Mth.cos(g * 0.6662F + (float) Math.PI) * 1.4F * f;
		this.rightFrontLeg.xRot = Mth.cos(g * 0.6662F) * 1.4F * f;
	}

    
}
