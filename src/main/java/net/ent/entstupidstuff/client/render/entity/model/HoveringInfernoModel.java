package net.ent.entstupidstuff.client.render.entity.model;

import net.ent.entstupidstuff.entity.animation.HVAnimation;
import net.ent.entstupidstuff.entity.mob.HoveringInfernoEntity;
import net.minecraft.client.model.Dilation;
import net.minecraft.client.model.ModelData;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.ModelPartBuilder;
import net.minecraft.client.model.ModelPartData;
import net.minecraft.client.model.ModelTransform;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;

@SuppressWarnings("unused")
public class HoveringInfernoModel<T extends HoveringInfernoEntity>  extends SinglePartEntityModel<HoveringInfernoEntity> {
	private final ModelPart hovering_inferno;
	private final ModelPart h_head;
	private final ModelPart inferno_shield;
	private final ModelPart root;

	public HoveringInfernoModel(ModelPart root) {
		this.root = root;
		this.hovering_inferno = root.getChild("hovering_inferno");
		this.h_head = hovering_inferno.getChild("h_head");
		this.inferno_shield = hovering_inferno.getChild("inferno_shield");
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData hovering_inferno = modelPartData.addChild("hovering_inferno", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 8.0F, 0.0F));

		ModelPartData h_head = hovering_inferno.addChild("h_head", ModelPartBuilder.create().uv(8, 60).cuboid(-4.0F, -4.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.0F))
		.uv(32, 0).cuboid(-4.5F, -10.5F, -4.5F, 9.0F, 15.0F, 9.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -24.0F, 0.0F));

		ModelPartData inferno_shield = hovering_inferno.addChild("inferno_shield", ModelPartBuilder.create().uv(0, 34).cuboid(-2.0F, -9.7524F, -1.8177F, 4.0F, 30.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -7.2476F, -0.1823F));

		ModelPartData cube_r1 = inferno_shield.addChild("cube_r1", ModelPartBuilder.create().uv(44, 24).cuboid(-6.0F, -10.0F, 0.0F, 12.0F, 22.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -0.4755F, 9.8177F, 0.3927F, 0.0F, 0.0F));

		ModelPartData cube_r2 = inferno_shield.addChild("cube_r2", ModelPartBuilder.create().uv(0, 0).cuboid(10.0F, -6.0F, 4.0F, 2.0F, 22.0F, 12.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -0.4755F, -9.4532F, 0.0F, 0.0F, -0.3927F));

		ModelPartData cube_r3 = inferno_shield.addChild("cube_r3", ModelPartBuilder.create().uv(16, 22).cuboid(-12.0F, -6.0F, 4.0F, 2.0F, 22.0F, 12.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -0.4755F, -9.4532F, 0.0F, 0.0F, 0.3927F));

		ModelPartData cube_r4 = inferno_shield.addChild("cube_r4", ModelPartBuilder.create().uv(44, 48).cuboid(-6.0F, -10.0F, -2.0F, 12.0F, 22.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -0.4755F, -9.4532F, -0.3927F, 0.0F, 0.0F));
		return TexturedModelData.of(modelData, 128, 128);
	}

	@Override
	public void setAngles(HoveringInfernoEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
	
		this.getPart().traverse().forEach(ModelPart::resetTransform);
		this.setHeadAngles(headYaw, headPitch);

		this.animateMovement(HVAnimation.WALKING, limbAngle, limbDistance, 1f, 2.5f);
		this.updateAnimation(entity.attackAnimationState, HVAnimation.ATTACK, animationProgress, 1);
		this.updateAnimation(entity.idleAnimationState, HVAnimation.IDLE, animationProgress, 1);

	}

	private void setHeadAngles(float headYaw, float headPitch) {
		headYaw = MathHelper.clamp(headYaw, -30.0F, 30.0F);
		headPitch = MathHelper.clamp(headPitch, -25.0F, 45.0F);

		this.h_head.yaw = headYaw * 0.017453292F;
		this.h_head.pitch = headPitch * 0.017453292F;
	}

	@Override
    public ModelPart getPart() {
        return this.root;
    }

	
	@Override
	public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, int color) {
		hovering_inferno.render(matrices, vertices, light, overlay);
	}


		
}