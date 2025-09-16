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

public class SnapperFishModel <T extends Entity> extends SinglePartEntityModel<T>{
    private final ModelPart root;
    private final ModelPart tailFin;

    public SnapperFishModel(ModelPart root) {
        this.root = root;
      this.tailFin = root.getChild("tail_fin");
    }

    public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData head = modelPartData.addChild("head", ModelPartBuilder.create().uv(0, 26).cuboid(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 21.0F, -4.0F));

		ModelPartData body = modelPartData.addChild("body", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -3.0F, -6.0F, 4.0F, 5.0F, 10.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 22.0F, 2.0F));

		ModelPartData left_fin = modelPartData.addChild("left_fin", ModelPartBuilder.create().uv(21, 16).cuboid(-2.0F, -2.0F, 0.0F, 2.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-2.0F, 24.0F, -3.0F, 0.0F, 0.7854F, 0.0F));

		ModelPartData right_fin = modelPartData.addChild("right_fin", ModelPartBuilder.create().uv(23, 16).cuboid(0.0F, -2.0F, 0.0F, 2.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(2.0F, 24.0F, -2.0F, 0.0F, -0.7854F, 0.0F));

		ModelPartData top_fin = modelPartData.addChild("top_fin", ModelPartBuilder.create().uv(0, 6).cuboid(0.0F, -3.0F, -5.0F, 0.0F, 9.0F, 10.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 20.0F, 1.0F));

		ModelPartData tail_fin = modelPartData.addChild("tail_fin", ModelPartBuilder.create().uv(19, -6).cuboid(0.0F, -4.0F, -1.0F, 0.0F, 6.0F, 6.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 22.0F, 7.0F));
		return TexturedModelData.of(modelData, 32, 32);
	}

    @Override
    public ModelPart getPart() {
        return this.root;
    }

    @Override
    public void setAngles(T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        float f = 1.0F;
        if (!entity.isTouchingWater()) {
            f = 1.5F;
        }

        this.tailFin.yaw = -f * 0.45F * MathHelper.sin(0.6F * animationProgress);
    }
    
}
