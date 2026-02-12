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

public class ZebraFishModel extends EntityModel<LivingEntityRenderState> {
   private final ModelPart tailFin;

   public ZebraFishModel(ModelPart root) {
      super(root);
      this.tailFin = root.getChild("tail_fin");
   }

   public static LayerDefinition getTexturedModelData() {
      MeshDefinition modelData = new MeshDefinition();
      PartDefinition modelPartData = modelData.getRoot();
      modelPartData.addOrReplaceChild("body", CubeListBuilder.create().texOffs(1, 1).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 22.0F, 0.0F));
      modelPartData.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 18).addBox(-1.0F, -1.0F, -3.0F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 22.0F, 0.0F));
      modelPartData.addOrReplaceChild("nose", CubeListBuilder.create().texOffs(18, 4).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 22.0F, -3.0F));
      modelPartData.addOrReplaceChild("right_fin", CubeListBuilder.create().texOffs(18, 2).addBox(0.0F, 0.0F, 0.0F, 2.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 23.0F, -1.0F, 0.0F, 0.0F, 0.1745F));
      modelPartData.addOrReplaceChild("left_fin", CubeListBuilder.create().texOffs(18, 0).addBox(-2.0F, 0.0F, -1.0F, 2.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 23.0F, 0.0F, 0.0F, 0.0F, -0.1745F));
      modelPartData.addOrReplaceChild("tail_fin", CubeListBuilder.create().texOffs(12, 10).addBox(0.0F, -2.0F, -1.0F, 0.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 22.0F, 7.0F));
      modelPartData.addOrReplaceChild("top_fin", CubeListBuilder.create().texOffs(0, 5).addBox(0.0F, -1.0F, 0.0F, 0.0F, 7.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 20.0F, 0.0F));
      return LayerDefinition.create(modelData, 32, 32);
   }

   public void setupAnim(LivingEntityRenderState livingEntityRenderState) {
      super.setupAnim(livingEntityRenderState);
      float f = livingEntityRenderState.isInWater ? 1.0F : 1.5F;
      this.tailFin.yRot = -f * 0.45F * Mth.sin(0.6F * livingEntityRenderState.ageInTicks);
   }
}
