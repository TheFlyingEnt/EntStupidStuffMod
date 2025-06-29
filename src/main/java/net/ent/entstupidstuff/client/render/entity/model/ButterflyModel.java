package net.ent.entstupidstuff.client.render.entity.model;

import net.ent.entstupidstuff.entity.passive.ButterflyEntity;
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

public class ButterflyModel<T extends ButterflyEntity> extends SinglePartEntityModel<ButterflyEntity> {
	private final ModelPart butterfly;
	private final ModelPart rightWing;
	private final ModelPart leftWing;
	private final ModelPart root;

	public ButterflyModel(ModelPart root) {
		this.root = root;
		this.butterfly = root.getChild("butterfly");
		this.rightWing = butterfly.getChild("rightWing");
		this.leftWing = butterfly.getChild("leftWing");
	}

	@SuppressWarnings("unused")
	public static TexturedModelData getTexturedModelData() {

		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData butterfly = modelPartData.addChild("butterfly",
				ModelPartBuilder.create().uv(0, 10).cuboid(-0.5F, -1.5F, -3.0F, 1.0F, 1.0F, 7.0F, new Dilation(0.0F))
						.uv(16, 17).cuboid(0.5F, -4.5F, -6.0F, 0.0F, 4.0F, 4.0F, new Dilation(0.0F))
						.uv(0, 18).cuboid(-0.5F, -4.5F, -6.0F, 0.0F, 4.0F, 4.0F, new Dilation(0.0F)),
				ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		ModelPartData lLeg_r1 = butterfly.addChild("lLeg_r1",
				ModelPartBuilder.create().uv(16, 10).cuboid(0.0F, 0.0F, -2.0F, 0.0F, 2.0F, 5.0F, new Dilation(0.0F)),
				ModelTransform.of(0.45F, -0.5F, 0.0F, 0.0F, 0.0F, -0.3927F));

		ModelPartData rLeg_r1 = butterfly.addChild("rLeg_r1",
				ModelPartBuilder.create().uv(16, 10).mirrored()
						.cuboid(0.0F, 0.0F, -2.0F, 0.0F, 2.0F, 5.0F, new Dilation(0.0F)).mirrored(false),
				ModelTransform.of(-0.45F, -0.5F, 0.0F, 0.0F, 0.0F, 0.3927F));

		ModelPartData rightWing = butterfly.addChild("rightWing",
				ModelPartBuilder.create().uv(0, 0).mirrored()
						.cuboid(-6.0F, 0.0F, -4.0F, 6.0F, 0.0F, 10.0F, new Dilation(0.0F)).mirrored(false),
				ModelTransform.pivot(-0.5F, -1.0F, 0.0F));

		ModelPartData leftWing = butterfly.addChild("leftWing",
				ModelPartBuilder.create().uv(0, 0).cuboid(0.05F, 0.0F, -4.0F, 6.0F, 0.0F, 10.0F, new Dilation(0.0F)),
				ModelTransform.pivot(0.45F, -1.0F, 0.0F));
		return TexturedModelData.of(modelData, 32, 32);
	}

	@Override
	public void setAngles(ButterflyEntity entity, float f, float g, float h, float i, float j) {

		// this.animateMovement(ButterflyAnimation.IDLE, limbAngle, limbDistance, 1f,
		// 2.5f);
		// this.updateAnimation(entity.idleAnimationState, ButterflyAnimation.IDLE,
		// animationProgress, 1);

		// this.updateAnimation(entity.flyingAnimationState, ButterflyAnimation.IDLE, h,
		// 3F);
		// this.updateAnimation(entity.roostingAnimationState,
		// ButterflyAnimation.SITTING, h, 3F);

		this.rightWing.pitch = 0.0F;
		boolean bl = entity.isOnGround() && entity.getVelocity().lengthSquared() < 1.0E-7;
		if (bl) {
			this.rightWing.yaw = -0.2618F;
			this.rightWing.roll = 0.0F;
			this.leftWing.pitch = 0.0F;
			this.leftWing.yaw = 0.2618F;
			this.leftWing.roll = 0.0F;

			// System.out.println("1");
		} else {
			float k = h * 120.32113F * (float) (Math.PI / 180.0);
			this.rightWing.yaw = 0.0F;
			this.rightWing.roll = MathHelper.cos(k) * (float) Math.PI * 0.15F;
			this.leftWing.pitch = this.rightWing.pitch;
			this.leftWing.yaw = this.rightWing.yaw;
			this.leftWing.roll = -this.rightWing.roll;
			// System.out.println("2");
		}

	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, int color) {
		// rightWing.render(matrices, vertices, light, overlay);
		// leftWing.render(matrices, vertices, light, overlay);
		butterfly.render(matrices, vertices, light, overlay);
	}

	@Override
	public ModelPart getPart() {
		return this.root;
	}
}
