package net.ent.entstupidstuff.client.render.entity.model;

import net.ent.entstupidstuff.client.render.entity.state.ButterflyRenderState;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;

public class ButterflyModel extends EntityModel<ButterflyRenderState> {
	private final ModelPart butterfly;
	private final ModelPart rightWing;
	private final ModelPart leftWing;
	private final ModelPart root;
	private float bodyPitch;

	public ButterflyModel(ModelPart root) {
		super(root);
		this.root = root;
		this.butterfly = root.getChild("butterfly");
		this.rightWing = butterfly.getChild("rightWing");
		this.leftWing = butterfly.getChild("leftWing");
	}

	@SuppressWarnings("unused")
	public static LayerDefinition getTexturedModelData() {

		MeshDefinition modelData = new MeshDefinition();
		PartDefinition modelPartData = modelData.getRoot();
		PartDefinition butterfly = modelPartData.addOrReplaceChild("butterfly",
				CubeListBuilder.create().texOffs(0, 10).addBox(-0.5F, -1.5F, -3.0F, 1.0F, 1.0F, 7.0F, new CubeDeformation(0.0F))
						.texOffs(16, 17).addBox(0.5F, -4.5F, -6.0F, 0.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
						.texOffs(0, 18).addBox(-0.5F, -4.5F, -6.0F, 0.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition lLeg_r1 = butterfly.addOrReplaceChild("lLeg_r1",
				CubeListBuilder.create().texOffs(16, 10).addBox(0.0F, 0.0F, -2.0F, 0.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.45F, -0.5F, 0.0F, 0.0F, 0.0F, -0.3927F));

		PartDefinition rLeg_r1 = butterfly.addOrReplaceChild("rLeg_r1",
				CubeListBuilder.create().texOffs(16, 10).mirror()
						.addBox(0.0F, 0.0F, -2.0F, 0.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offsetAndRotation(-0.45F, -0.5F, 0.0F, 0.0F, 0.0F, 0.3927F));

		PartDefinition rightWing = butterfly.addOrReplaceChild("rightWing",
				CubeListBuilder.create().texOffs(0, 0).mirror()
						.addBox(-6.0F, 0.0F, -4.0F, 6.0F, 0.0F, 10.0F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offset(-0.5F, -1.0F, 0.0F));

		PartDefinition leftWing = butterfly.addOrReplaceChild("leftWing",
				CubeListBuilder.create().texOffs(0, 0).addBox(0.05F, 0.0F, -4.0F, 6.0F, 0.0F, 10.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.45F, -1.0F, 0.0F));
		return LayerDefinition.create(modelData, 32, 32);
	}

	public void setupAnim(ButterflyRenderState ButterflyRenderState) {
		this.rightWing.xRot = 0.0F;
		this.bodyPitch = ButterflyRenderState.bodyPitch;
		if (!ButterflyRenderState.stoppedOnGround) {
			float f = ButterflyRenderState.ageInTicks * 120.32113F * (float) (Math.PI / 180.0);
			this.rightWing.yRot = 0.0F;
			this.rightWing.zRot = Mth.cos(f) * (float) Math.PI * 0.15F;
			this.leftWing.xRot = this.rightWing.xRot;
			this.leftWing.yRot = this.rightWing.yRot;
			this.leftWing.zRot = -this.rightWing.zRot;
		}

	}

	/*@Override
	public void setupAnim(ButterflyRenderState entity, float f, float g, float h, float i, float j) {

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

		} else {
			float k = h * 120.32113F * (float) (Math.PI / 180.0);
			this.rightWing.yaw = 0.0F;
			this.rightWing.roll = MathHelper.cos(k) * (float) Math.PI * 0.15F;
			this.leftWing.pitch = this.rightWing.pitch;
			this.leftWing.yaw = this.rightWing.yaw;
			this.leftWing.roll = -this.rightWing.roll;
		}

	}

	@Override
	public void submit(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, int color) {
		// rightWing.render(matrices, vertices, light, overlay);
		// leftWing.render(matrices, vertices, light, overlay);
		butterfly.render(matrices, vertices, light, overlay);
	}

	@Override
	public ModelPart getPart() {
		return this.root;
	}*/
}
