package net.ent.entstupidstuff.client.render.entity.model;

import net.minecraft.client.model.Dilation;
import net.minecraft.client.model.ModelData;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.ModelPartBuilder;
import net.minecraft.client.model.ModelPartData;
import net.minecraft.client.model.ModelTransform;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class BassModel <T extends Entity> extends SinglePartEntityModel<T>{
    private final ModelPart root;
    private final ModelPart tailFin;

    public BassModel(ModelPart root) {
        this.root = root;
      this.tailFin = root.getChild("tail_fin");
   }

    public ModelPart getPart() {
        return this.root;
    }

    public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData body = modelPartData.addChild("body", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -3.0F, -1.0F, 4.0F, 6.0F, 8.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 21.0F, 0.0F));

		ModelPartData head = modelPartData.addChild("head", ModelPartBuilder.create().uv(0, 22).cuboid(-2.0F, -3.0F, -5.0F, 4.0F, 6.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 21.0F, 0.0F));

		ModelPartData left_fin = modelPartData.addChild("left_fin", ModelPartBuilder.create(), ModelTransform.of(-1.0F, 22.0F, 0.0F, 0.0F, 0.0F, -0.1745F));

		ModelPartData leftFin_r1 = left_fin.addChild("leftFin_r1", ModelPartBuilder.create().uv(26, 2).cuboid(-4.0F, -2.0F, 0.0F, 3.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-0.2F, 1.0F, -1.0F, 0.0F, 0.5236F, 0.1745F));

		ModelPartData right_fin = modelPartData.addChild("right_fin", ModelPartBuilder.create(), ModelTransform.pivot(1.0F, 22.0F, -1.0F));

		ModelPartData rightFin_r1 = right_fin.addChild("rightFin_r1", ModelPartBuilder.create().uv(26, 0).cuboid(1.0F, -3.0F, 0.0F, 3.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.1F, 2.0F, 0.0F, 0.0F, -0.5236F, 0.0F));

		ModelPartData tail_fin = modelPartData.addChild("tail_fin", ModelPartBuilder.create().uv(24, 22).cuboid(0.0F, -3.0F, -1.0F, 0.0F, 6.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 21.0F, 8.0F));

		ModelPartData top_fin = modelPartData.addChild("top_fin", ModelPartBuilder.create().uv(14, 5).cuboid(0.0F, -4.0F, -1.0F, 0.0F, 11.0F, 9.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 19.0F, 0.0F));

		ModelPartData nose = modelPartData.addChild("nose", ModelPartBuilder.create().uv(0, 14).cuboid(-2.0F, -2.0F, -3.0F, 3.0F, 5.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(0.5F, 20.5F, -5.0F));
		return TexturedModelData.of(modelData, 32, 32);
	}

    public void setAngles(T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        float f = 1.0F;
        if (!entity.isTouchingWater()) {
            f = 1.5F;
        }

        this.tailFin.yaw = -f * 0.45F * MathHelper.sin(0.6F * animationProgress);
    }
}
