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

public class KoiModel <T extends Entity> extends SinglePartEntityModel<T>{
    private final ModelPart root;
    private final ModelPart tailFin;

    public KoiModel(ModelPart root) {
        this.root = root;
        this.tailFin = root.getChild("tail_fin");
    }

    public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData body = modelPartData.addChild("body", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -1.0F, -2.0F, 4.0F, 5.0F, 8.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 20.0F, -6.0F));

		ModelPartData right_wisker = modelPartData.addChild("right_wisker", ModelPartBuilder.create().uv(11, 14).cuboid(0.0F, 0.0F, -2.0F, 3.0F, 0.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(1.5F, 23.25F, -10.0F, 0.0F, 0.0F, 0.7854F));

		ModelPartData left_wisker = modelPartData.addChild("left_wisker", ModelPartBuilder.create().uv(8, 21).mirrored().cuboid(-3.0F, 0.0F, -2.0F, 3.0F, 0.0F, 3.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(-1.5F, 23.25F, -10.0F, 0.0F, 0.0F, -0.7854F));

		ModelPartData head = modelPartData.addChild("head", ModelPartBuilder.create().uv(0, 21).cuboid(-1.5F, -2.75F, -5.0F, 3.0F, 4.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 22.0F, -6.0F));

		ModelPartData left_fin = modelPartData.addChild("left_fin", ModelPartBuilder.create().uv(23, 8).cuboid(-4.3473F, 1.9696F, -1.0F, 3.0F, 0.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(-1.0F, 21.0F, -6.0F, 0.0F, 0.0F, -0.1745F));

		ModelPartData right_fin = modelPartData.addChild("right_fin", ModelPartBuilder.create().uv(23, 11).cuboid(1.3473F, 1.9696F, 0.0F, 3.0F, 0.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, 21.0F, -7.0F, 0.0F, 0.0F, 0.1745F));

		ModelPartData tail_fin = modelPartData.addChild("tail_fin", ModelPartBuilder.create().uv(0, 7).cuboid(0.0F, -3.0F, 7.0F, 0.0F, 7.0F, 7.0F, new Dilation(0.0F))
		.uv(18, 15).cuboid(-1.5F, -1.0F, 0.0F, 3.0F, 4.0F, 4.0F, new Dilation(0.0F))
		.uv(18, 0).cuboid(-1.5F, -1.0F, 4.0F, 3.0F, 3.0F, 4.0F, new Dilation(0.0F))
		.uv(16, 16).cuboid(0.0F, -4.0F, 0.0F, 0.0F, 8.0F, 8.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 20.0F, 0.0F));

		ModelPartData top_fin = modelPartData.addChild("top_fin", ModelPartBuilder.create().uv(0, 21).cuboid(0.0F, -4.0F, -2.0F, 0.0F, 3.0F, 8.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 20.0F, -6.0F));

		ModelPartData back_right_fin = modelPartData.addChild("back_right_fin", ModelPartBuilder.create().uv(28, 15).cuboid(0.0F, 0.0F, 0.0F, 0.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 24.0F, -3.0F, 0.0F, 0.0F, -0.7854F));

		ModelPartData back_left_fin = modelPartData.addChild("back_left_fin", ModelPartBuilder.create().uv(28, 13).cuboid(0.0F, 0.0F, 0.0F, 0.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 24.0F, -3.0F, 0.0F, 0.0F, 0.7854F));
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
