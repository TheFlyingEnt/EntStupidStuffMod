package net.ent.entstupidstuff.client.render.entity.model;

import net.minecraft.client.model.Dilation;
import net.minecraft.client.model.ModelData;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.ModelPartBuilder;
import net.minecraft.client.model.ModelPartData;
import net.minecraft.client.model.ModelTransform;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.util.math.MathHelper;

public class PerchFishModel extends EntityModel<LivingEntityRenderState>{
    private final ModelPart tailFin;

    public PerchFishModel(ModelPart root) {
        super(root);
      this.tailFin = root.getChild("tail_fin");
    }

    public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData head = modelPartData.addChild("head", ModelPartBuilder.create().uv(18, 4).cuboid(-1.0F, -2.0F, -3.0F, 2.0F, 4.0F, 3.0F, new Dilation(0.0F)), ModelTransform.origin(0.0F, 22.0F, 0.0F));

		ModelPartData body = modelPartData.addChild("body", ModelPartBuilder.create().uv(1, 1).cuboid(-1.0F, -2.0F, -2.0F, 2.0F, 4.0F, 6.0F, new Dilation(0.0F)), ModelTransform.origin(0.0F, 22.0F, 2.0F));

		ModelPartData left_fin = modelPartData.addChild("left_fin", ModelPartBuilder.create().uv(14, 15).cuboid(-2.0F, -2.0F, 0.0F, 2.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-1.0F, 24.0F, 0.0F, 0.0F, 0.7854F, 0.0F));

		ModelPartData right_fin = modelPartData.addChild("right_fin", ModelPartBuilder.create().uv(14, 18).cuboid(0.0F, -2.0F, 0.0F, 2.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, 24.0F, 0.0F, 0.0F, -0.7854F, 0.0F));

		ModelPartData top_fin = modelPartData.addChild("top_fin", ModelPartBuilder.create().uv(1, 7).cuboid(0.0F, -2.0F, -1.0F, 0.0F, 8.0F, 6.0F, new Dilation(0.0F)), ModelTransform.origin(0.0F, 20.0F, 1.0F));

		ModelPartData tail_fin = modelPartData.addChild("tail_fin", ModelPartBuilder.create().uv(19, 9).cuboid(0.0F, -2.0F, -1.0F, 0.0F, 4.0F, 4.0F, new Dilation(0.0F)), ModelTransform.origin(0.0F, 22.0F, 7.0F));
		return TexturedModelData.of(modelData, 32, 32);
	}

    @Override
    public void setAngles(LivingEntityRenderState livingEntityRenderState) {
      super.setAngles(livingEntityRenderState);
      float f = livingEntityRenderState.touchingWater ? 1.0F : 1.5F;
      this.tailFin.yaw = -f * 0.45F * MathHelper.sin(0.6F * livingEntityRenderState.age);
   }
    
}
