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

public class MahiMahiModel extends EntityModel<LivingEntityRenderState>{
    private final ModelPart tailFin;

    public MahiMahiModel(ModelPart root) {
        super(root);
      this.tailFin = root.getChild("tail_fin");
    }

    public static LayerDefinition getTexturedModelData() {
		MeshDefinition modelData = new MeshDefinition();
		PartDefinition modelPartData = modelData.getRoot();
		PartDefinition head = modelPartData.addOrReplaceChild("head", CubeListBuilder.create().texOffs(1, 1).addBox(-2.0F, -2.0F, -5.0F, 4.0F, 6.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 21.0F, -11.0F));

		PartDefinition body = modelPartData.addOrReplaceChild("body", CubeListBuilder.create().texOffs(1, 31).addBox(-2.0F, -3.0F, -4.0F, 4.0F, 6.0F, 11.0F, new CubeDeformation(0.0F))
		.texOffs(36, 0).addBox(-2.0F, -2.0F, 7.0F, 4.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 22.0F, -7.0F));

		PartDefinition left_fin = modelPartData.addOrReplaceChild("left_fin", CubeListBuilder.create().texOffs(36, 32).addBox(-3.0F, 0.0F, 0.0F, 3.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, 23.0F, -10.0F));

		PartDefinition right_fin = modelPartData.addOrReplaceChild("right_fin", CubeListBuilder.create().texOffs(36, 26).addBox(0.0F, 0.0F, -1.0F, 3.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, 23.0F, -9.0F));

		PartDefinition top_fin = modelPartData.addOrReplaceChild("top_fin", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, -5.0F, -13.0F, 0.0F, 12.0F, 18.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 20.0F, 1.0F));

		PartDefinition tail_fin = modelPartData.addOrReplaceChild("tail_fin", CubeListBuilder.create().texOffs(35, 10).addBox(0.0F, -5.0F, -1.0F, 0.0F, 10.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 22.0F, 7.0F));
		return LayerDefinition.create(modelData, 64, 64);
	}

    @Override
    public void setupAnim(LivingEntityRenderState livingEntityRenderState) {
      super.setupAnim(livingEntityRenderState);
      float f = livingEntityRenderState.isInWater ? 1.0F : 1.5F;
      this.tailFin.yRot = -f * 0.45F * Mth.sin(0.6F * livingEntityRenderState.ageInTicks);
   }
    
}
