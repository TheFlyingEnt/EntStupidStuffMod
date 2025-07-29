package net.ent.entstupidstuff.client.render;

import net.ent.entstupidstuff.client.render.entity.model.CustomBoatModel;
import net.ent.entstupidstuff.entity.passive.CustomBoatEntity;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.CompositeEntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.ModelWithWaterPatch;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import org.joml.Quaternionf;

public class CustomBoatEntityRenderer extends EntityRenderer<CustomBoatEntity> {
	//private static final Identifier TEXTURE = Identifier.of("entstupidstuff", "textures/entity/customboat_3.png");
	private static final Identifier TEXTURE = Identifier.of("entstupidstuff", "textures/entity/ccustomboat_3.png");
	private final CustomBoatModel model;

	public CustomBoatEntityRenderer(EntityRendererFactory.Context ctx, boolean chest) {
		super(ctx);
		this.shadowRadius = 0.8F;

		// You only need ONE model layer registered: "entstupidstuff:custom_boat"
		EntityModelLayer modelLayer = new EntityModelLayer(Identifier.of("entstupidstuff", "customboat"), "main");
		ModelPart modelPart = ctx.getPart(modelLayer);
		this.model = new CustomBoatModel(modelPart);
	}

	@Override
	public void render(CustomBoatEntity entity, float f, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
		matrices.push();
		matrices.translate(0.0F, 0.375F, 0.0F);
		matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(180.0F - f));

		float wobbleTicks = entity.getDamageWobbleTicks() - tickDelta;
		float wobbleStrength = entity.getDamageWobbleStrength() - tickDelta;
		if (wobbleStrength < 0.0F) wobbleStrength = 0.0F;
		if (wobbleTicks > 0.0F) {
			matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(
				MathHelper.sin(wobbleTicks) * wobbleTicks * wobbleStrength / 10.0F * entity.getDamageWobbleSide()
			));
		}

		float bubbleWobble = entity.interpolateBubbleWobble(tickDelta);
		if (!MathHelper.approximatelyEquals(bubbleWobble, 0.0F)) {
			matrices.multiply(new Quaternionf().setAngleAxis(
				Math.toRadians(bubbleWobble), 1.0F, 0.0F, 1.0F
			));
		}

		matrices.scale(-1.0F, -1.0F, 1.0F);
		matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(90.0F));

		model.setAngles(entity, tickDelta, 0.0F, -0.1F, 0.0F, 0.0F);
		VertexConsumer buffer = vertexConsumers.getBuffer(model.getLayer(TEXTURE));
		model.render(matrices, buffer, light, OverlayTexture.DEFAULT_UV);

		if (!entity.isSubmergedInWater()) {
			VertexConsumer waterBuffer = vertexConsumers.getBuffer(RenderLayer.getWaterMask());
			if (model instanceof ModelWithWaterPatch patch) {
				patch.getWaterPatch().render(matrices, waterBuffer, light, OverlayTexture.DEFAULT_UV);
			}
		}

		matrices.pop();
		super.render(entity, f, tickDelta, matrices, vertexConsumers, light);
	}

	@Override
	public Identifier getTexture(CustomBoatEntity entity) {
		return TEXTURE;
	}
}
