package net.ent.entstupidstuff.client.render.entity.model.fish;

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

public class KoiModel extends EntityModel<LivingEntityRenderState>{
    private final ModelPart tailFin;

    public KoiModel(ModelPart root) {
        super(root);
        this.tailFin = root.getChild("tail_fin");
    }

    public static LayerDefinition getTexturedModelData() {
		MeshDefinition modelData = new MeshDefinition();
		PartDefinition modelPartData = modelData.getRoot();
		PartDefinition body = modelPartData.addOrReplaceChild("body", CubeListBuilder.create().texOffs(20, 27).addBox(0.0F, 3.0F, 2.0F, 0.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(2, 2).addBox(-2.0F, -1.0F, 0.0F, 4.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 20.25F, -6.0F));

		PartDefinition head = modelPartData.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 21).addBox(-1.5F, -2.75F, -5.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 22.5F, -4.0F));

		PartDefinition right_wisker = modelPartData.addOrReplaceChild("right_wisker", CubeListBuilder.create().texOffs(11, 14).addBox(0.0F, 0.0F, -2.0F, 3.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.5F, 22.75F, -8.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition left_wisker = modelPartData.addOrReplaceChild("left_wisker", CubeListBuilder.create().texOffs(8, 21).mirror().addBox(-3.0F, 0.0F, -2.0F, 3.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-1.5F, 22.75F, -8.0F, 0.0F, 0.0F, -0.7854F));

		PartDefinition left_fin = modelPartData.addOrReplaceChild("left_fin", CubeListBuilder.create().texOffs(23, 8).addBox(-4.3473F, 1.9696F, -1.0F, 3.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 21.0F, -4.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition right_fin = modelPartData.addOrReplaceChild("right_fin", CubeListBuilder.create().texOffs(23, 11).addBox(1.3473F, 1.9696F, 0.0F, 3.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 21.0F, -5.0F, 0.0F, 0.0F, 0.1745F));

		PartDefinition tail_fin = modelPartData.addOrReplaceChild("tail_fin", CubeListBuilder.create().texOffs(0, 7).addBox(0.0F, -3.0F, 6.0F, 0.0F, 7.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(19, 16).addBox(-1.5F, -1.0F, 0.0F, 3.0F, 4.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(18, 18).addBox(0.0F, -4.0F, 0.0F, 0.0F, 7.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(19, 1).addBox(-1.5F, -1.0F, 3.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 20.25F, 0.0F));

		PartDefinition back_right_fin = tail_fin.addOrReplaceChild("back_right_fin", CubeListBuilder.create().texOffs(28, 15).addBox(0.0F, 0.0F, 0.0F, 0.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 3.0F, 1.0F, 0.0F, 0.0F, -0.7854F));

		PartDefinition back_left_fin = tail_fin.addOrReplaceChild("back_left_fin", CubeListBuilder.create().texOffs(28, 13).addBox(0.0F, 0.0F, 0.0F, 0.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 3.0F, 1.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition top_fin = modelPartData.addOrReplaceChild("top_fin", CubeListBuilder.create().texOffs(2, 23).addBox(0.0F, -6.0F, -2.0F, 0.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 22.25F, -4.0F));
		return LayerDefinition.create(modelData, 32, 32);
	}

    //BIG Koi

    /*public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData body = modelPartData.addChild("body", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -1.0F, -2.0F, 4.0F, 5.0F, 8.0F, new Dilation(0.0F)), ModelTransform.origin(0.0F, 20.0F, -6.0F));

		ModelPartData right_wisker = modelPartData.addChild("right_wisker", ModelPartBuilder.create().uv(11, 14).cuboid(0.0F, 0.0F, -2.0F, 3.0F, 0.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(1.5F, 23.25F, -10.0F, 0.0F, 0.0F, 0.7854F));

		ModelPartData left_wisker = modelPartData.addChild("left_wisker", ModelPartBuilder.create().uv(8, 21).mirrored().cuboid(-3.0F, 0.0F, -2.0F, 3.0F, 0.0F, 3.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(-1.5F, 23.25F, -10.0F, 0.0F, 0.0F, -0.7854F));

		ModelPartData head = modelPartData.addChild("head", ModelPartBuilder.create().uv(0, 21).cuboid(-1.5F, -2.75F, -5.0F, 3.0F, 4.0F, 3.0F, new Dilation(0.0F)), ModelTransform.origin(0.0F, 22.0F, -6.0F));

		ModelPartData left_fin = modelPartData.addChild("left_fin", ModelPartBuilder.create().uv(23, 8).cuboid(-4.3473F, 1.9696F, -1.0F, 3.0F, 0.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(-1.0F, 21.0F, -6.0F, 0.0F, 0.0F, -0.1745F));

		ModelPartData right_fin = modelPartData.addChild("right_fin", ModelPartBuilder.create().uv(23, 11).cuboid(1.3473F, 1.9696F, 0.0F, 3.0F, 0.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, 21.0F, -7.0F, 0.0F, 0.0F, 0.1745F));

		ModelPartData tail_fin = modelPartData.addChild("tail_fin", ModelPartBuilder.create().uv(0, 7).cuboid(0.0F, -3.0F, 7.0F, 0.0F, 7.0F, 7.0F, new Dilation(0.0F))
		.uv(18, 15).cuboid(-1.5F, -1.0F, 0.0F, 3.0F, 4.0F, 4.0F, new Dilation(0.0F))
		.uv(18, 0).cuboid(-1.5F, -1.0F, 4.0F, 3.0F, 3.0F, 4.0F, new Dilation(0.0F))
		.uv(16, 16).cuboid(0.0F, -4.0F, 0.0F, 0.0F, 8.0F, 8.0F, new Dilation(0.0F)), ModelTransform.origin(0.0F, 20.0F, 0.0F));

		ModelPartData top_fin = modelPartData.addChild("top_fin", ModelPartBuilder.create().uv(0, 21).cuboid(0.0F, -4.0F, -2.0F, 0.0F, 3.0F, 8.0F, new Dilation(0.0F)), ModelTransform.origin(0.0F, 20.0F, -6.0F));

		ModelPartData back_right_fin = modelPartData.addChild("back_right_fin", ModelPartBuilder.create().uv(28, 15).cuboid(0.0F, 0.0F, 0.0F, 0.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 24.0F, -3.0F, 0.0F, 0.0F, -0.7854F));

		ModelPartData back_left_fin = modelPartData.addChild("back_left_fin", ModelPartBuilder.create().uv(28, 13).cuboid(0.0F, 0.0F, 0.0F, 0.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 24.0F, -3.0F, 0.0F, 0.0F, 0.7854F));
		return TexturedModelData.of(modelData, 32, 32);
	}*/

    @Override
	public void setupAnim(LivingEntityRenderState livingEntityRenderState) {
      super.setupAnim(livingEntityRenderState);
      float f = livingEntityRenderState.isInWater ? 1.0F : 1.5F;
      this.tailFin.yRot = -f * 0.45F * Mth.sin(0.6F * livingEntityRenderState.ageInTicks);
   }
    
}
