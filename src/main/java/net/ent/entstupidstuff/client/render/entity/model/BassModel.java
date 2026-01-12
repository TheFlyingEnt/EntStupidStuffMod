package net.ent.entstupidstuff.client.render.entity.model;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.util.Mth;

public class BassModel extends EntityModel<LivingEntityRenderState> {
    private final ModelPart tailFin;

    public BassModel(ModelPart root) {
		super(root);
      	this.tailFin = root.getChild("tail_fin");
   }

    public ModelPart getPart() {
        return this.root;
    }

    /*public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData body = modelPartData.addChild("body", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -3.0F, -1.0F, 4.0F, 6.0F, 8.0F, new Dilation(0.0F)), ModelTransform.origin(0.0F, 21.0F, 0.0F));

		ModelPartData head = modelPartData.addChild("head", ModelPartBuilder.create().uv(0, 22).cuboid(-2.0F, -3.0F, -5.0F, 4.0F, 6.0F, 4.0F, new Dilation(0.0F)), ModelTransform.origin(0.0F, 21.0F, 0.0F));

		ModelPartData left_fin = modelPartData.addChild("left_fin", ModelPartBuilder.create(), ModelTransform.of(-1.0F, 22.0F, 0.0F, 0.0F, 0.0F, -0.1745F));

		ModelPartData leftFin_r1 = left_fin.addChild("leftFin_r1", ModelPartBuilder.create().uv(26, 2).cuboid(-4.0F, -2.0F, 0.0F, 3.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-0.2F, 1.0F, -1.0F, 0.0F, 0.5236F, 0.1745F));

		ModelPartData right_fin = modelPartData.addChild("right_fin", ModelPartBuilder.create(), ModelTransform.origin(1.0F, 22.0F, -1.0F));

		ModelPartData rightFin_r1 = right_fin.addChild("rightFin_r1", ModelPartBuilder.create().uv(26, 0).cuboid(1.0F, -3.0F, 0.0F, 3.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.1F, 2.0F, 0.0F, 0.0F, -0.5236F, 0.0F));

		ModelPartData tail_fin = modelPartData.addChild("tail_fin", ModelPartBuilder.create().uv(24, 22).cuboid(0.0F, -3.0F, -1.0F, 0.0F, 6.0F, 4.0F, new Dilation(0.0F)), ModelTransform.origin(0.0F, 21.0F, 8.0F));

		ModelPartData top_fin = modelPartData.addChild("top_fin", ModelPartBuilder.create().uv(14, 5).cuboid(0.0F, -4.0F, -1.0F, 0.0F, 11.0F, 9.0F, new Dilation(0.0F)), ModelTransform.origin(0.0F, 19.0F, 0.0F));

		ModelPartData nose = modelPartData.addChild("nose", ModelPartBuilder.create().uv(0, 14).cuboid(-2.0F, -2.0F, -3.0F, 3.0F, 5.0F, 3.0F, new Dilation(0.0F)), ModelTransform.origin(0.5F, 20.5F, -5.0F));
		return TexturedModelData.of(modelData, 32, 32);
	}*/

    public static LayerDefinition getTexturedModelData() {
		MeshDefinition modelData = new MeshDefinition();
		PartDefinition modelPartData = modelData.getRoot();
		PartDefinition body = modelPartData.addOrReplaceChild("body", CubeListBuilder.create().texOffs(2, 2).addBox(-2.0F, -3.0F, -1.0F, 4.0F, 6.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(6, 7).addBox(-2.0F, -2.0F, 5.0F, 4.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 21.0F, 0.0F));

		PartDefinition head = modelPartData.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 22).addBox(-2.0F, -3.0F, -5.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 21.0F, 0.0F));

		PartDefinition left_fin = modelPartData.addOrReplaceChild("left_fin", CubeListBuilder.create(), PartPose.offsetAndRotation(-1.0F, 22.0F, 0.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition leftFin_r1 = left_fin.addOrReplaceChild("leftFin_r1", CubeListBuilder.create().texOffs(26, 2).addBox(-4.0F, -2.0F, 0.0F, 3.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.2F, 1.0F, -1.0F, 0.0F, 0.5236F, 0.1745F));

		PartDefinition right_fin = modelPartData.addOrReplaceChild("right_fin", CubeListBuilder.create(), PartPose.offset(1.0F, 22.0F, -1.0F));

		PartDefinition rightFin_r1 = right_fin.addOrReplaceChild("rightFin_r1", CubeListBuilder.create().texOffs(26, 0).addBox(1.0F, -3.0F, 0.0F, 3.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1F, 2.0F, 0.0F, 0.0F, -0.5236F, 0.0F));

		PartDefinition tail_fin = modelPartData.addOrReplaceChild("tail_fin", CubeListBuilder.create().texOffs(24, 22).addBox(0.0F, -3.0F, -1.0F, 0.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 21.0F, 8.0F));

		PartDefinition top_fin = modelPartData.addOrReplaceChild("top_fin", CubeListBuilder.create().texOffs(14, 5).addBox(0.0F, -4.0F, -1.0F, 0.0F, 11.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 19.0F, 0.0F));

		PartDefinition nose = modelPartData.addOrReplaceChild("nose", CubeListBuilder.create().texOffs(0, 15).addBox(-2.0F, -1.0F, -3.0F, 3.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, 20.5F, -5.0F));
		return LayerDefinition.create(modelData, 32, 32);
	}

    public void setupAnim(LivingEntityRenderState livingEntityRenderState) {
    	super.setupAnim(livingEntityRenderState);
    	float f = livingEntityRenderState.isInWater ? 1.0F : 1.5F;
    	this.tailFin.yRot = -f * 0.45F * Mth.sin(0.6F * livingEntityRenderState.ageInTicks);
   	}
}
