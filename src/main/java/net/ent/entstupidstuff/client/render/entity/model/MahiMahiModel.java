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

public class MahiMahiModel extends EntityModel<LivingEntityRenderState>{
    private final ModelPart tailFin;

    public MahiMahiModel(ModelPart root) {
        super(root);
      this.tailFin = root.getChild("tail_fin");
    }

    public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData head = modelPartData.addChild("head", ModelPartBuilder.create().uv(1, 1).cuboid(-2.0F, -2.0F, -5.0F, 4.0F, 6.0F, 5.0F, new Dilation(0.0F)), ModelTransform.origin(0.0F, 21.0F, -11.0F));

		ModelPartData body = modelPartData.addChild("body", ModelPartBuilder.create().uv(1, 31).cuboid(-2.0F, -3.0F, -4.0F, 4.0F, 6.0F, 11.0F, new Dilation(0.0F))
		.uv(36, 0).cuboid(-2.0F, -2.0F, 7.0F, 4.0F, 4.0F, 6.0F, new Dilation(0.0F)), ModelTransform.origin(0.0F, 22.0F, -7.0F));

		ModelPartData left_fin = modelPartData.addChild("left_fin", ModelPartBuilder.create().uv(36, 32).cuboid(-3.0F, 0.0F, 0.0F, 3.0F, 0.0F, 6.0F, new Dilation(0.0F)), ModelTransform.origin(-2.0F, 23.0F, -10.0F));

		ModelPartData right_fin = modelPartData.addChild("right_fin", ModelPartBuilder.create().uv(36, 26).cuboid(0.0F, 0.0F, -1.0F, 3.0F, 0.0F, 6.0F, new Dilation(0.0F)), ModelTransform.origin(2.0F, 23.0F, -9.0F));

		ModelPartData top_fin = modelPartData.addChild("top_fin", ModelPartBuilder.create().uv(0, 0).cuboid(0.0F, -5.0F, -13.0F, 0.0F, 12.0F, 18.0F, new Dilation(0.0F)), ModelTransform.origin(0.0F, 20.0F, 1.0F));

		ModelPartData tail_fin = modelPartData.addChild("tail_fin", ModelPartBuilder.create().uv(35, 10).cuboid(0.0F, -5.0F, -1.0F, 0.0F, 10.0F, 6.0F, new Dilation(0.0F)), ModelTransform.origin(0.0F, 22.0F, 7.0F));
		return TexturedModelData.of(modelData, 64, 64);
	}

    @Override
    public void setAngles(LivingEntityRenderState livingEntityRenderState) {
      super.setAngles(livingEntityRenderState);
      float f = livingEntityRenderState.touchingWater ? 1.0F : 1.5F;
      this.tailFin.yaw = -f * 0.45F * MathHelper.sin(0.6F * livingEntityRenderState.age);
   }
    
}
