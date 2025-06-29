package net.ent.entstupidstuff.client.render.entity.model;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableList.Builder;

import net.ent.entstupidstuff.entity.passive.CustomBoatEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.Dilation;
import net.minecraft.client.model.ModelData;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.ModelPartBuilder;
import net.minecraft.client.model.ModelPartData;
import net.minecraft.client.model.ModelTransform;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.entity.model.CompositeEntityModel;
import net.minecraft.client.render.entity.model.ModelWithWaterPatch;
import net.minecraft.util.math.MathHelper;

@Environment(EnvType.CLIENT)
public class CustomBoatModel extends CompositeEntityModel<CustomBoatEntity> implements ModelWithWaterPatch{

		private static final String LEFT_PADDLE = "left_paddle";
	/**
	 * The key of the right paddle model part, whose value is {@value}.
	 */
	private static final String RIGHT_PADDLE = "right_paddle";
	/**
	 * The key of the water patch model part, whose value is {@value}.
	 */
	private static final String WATER_PATCH = "water_patch";
	/**
	 * The key of the bottom model part, whose value is {@value}.
	 */
	private static final String BOTTOM = "bottom";
	/**
	 * The key of the back model part, whose value is {@value}.
	 */
	private static final String BACK = "back";
	/**
	 * The key of the front model part, whose value is {@value}.
	 */
	private static final String FRONT = "front";
	/**
	 * The key of the right model part, whose value is {@value}.
	 */
	private static final String RIGHT = "right";
	/**
	 * The key of the left model part, whose value is {@value}.
	 */
	private static final String LEFT = "left";
	private final ModelPart leftPaddle;
	private final ModelPart rightPaddle;
	private final ModelPart waterPatch;
	private final ImmutableList<ModelPart> parts;

		public CustomBoatModel(ModelPart root) {
		this.leftPaddle = root.getChild("left_paddle");
		this.rightPaddle = root.getChild("right_paddle");
		this.waterPatch = root.getChild("water_patch");
		this.parts = this.getParts(root).build();
	}

	protected Builder<ModelPart> getParts(ModelPart root) {
		Builder<ModelPart> builder = new Builder<>();
		builder.add(
			root.getChild("bottom")
		);
		return builder;
	}

	public static void addParts(ModelPartData modelPartData) {
		int i = 32;
		int j = 6;
		int k = 20;
		int l = 4;
		int m = 28;
		/*modelPartData.addChild(
			"bottom",
			ModelPartBuilder.create().uv(0, 0).cuboid(-14.0F, -9.0F, -3.0F, 28.0F, 16.0F, 3.0F),
			ModelTransform.of(0.0F, 3.0F, 1.0F, (float) (Math.PI / 2), 0.0F, 0.0F)
		);*/
		//New Botton Test
		modelPartData.addChild("bottom", ModelPartBuilder.create().uv(0, 0).cuboid(-77.1252F, 10.538F, -30.0526F, 104.0F, 8.0F, 64.0F, new Dilation(0.0F))
		.uv(92, 240).cuboid(-33.1252F, -87.462F, -2.0526F, 8.0F, 78.0F, 8.0F, new Dilation(0.0F))
		.uv(140, 212).cuboid(-31.1252F, -79.462F, -24.0526F, 4.0F, 4.0F, 52.0F, new Dilation(0.0F))
		.uv(252, 266).cuboid(-35.1252F, -9.462F, -4.0526F, 12.0F, 24.0F, 12.0F, new Dilation(0.0F))
		.uv(0, 72).cuboid(-77.1252F, 18.538F, -20.0526F, 122.0F, 4.0F, 44.0F, new Dilation(0.0F))
		.uv(140, 152).cuboid(26.8748F, 10.538F, -24.0526F, 24.0F, 8.0F, 52.0F, new Dilation(0.0F))
		.uv(0, 120).cuboid(-77.1252F, 0.538F, 33.9474F, 104.0F, 10.0F, 6.0F, new Dilation(0.0F))
		.uv(124, 268).cuboid(26.8748F, 0.538F, 27.9474F, 18.0F, 10.0F, 6.0F, new Dilation(0.0F))
		.uv(0, 240).cuboid(50.8748F, 0.538F, -18.0526F, 6.0F, 10.0F, 40.0F, new Dilation(0.0F))
		.uv(220, 120).cuboid(56.8748F, 0.538F, -2.0526F, 34.0F, 10.0F, 10.0F, new Dilation(0.0F))
		.uv(172, 268).cuboid(26.8748F, 0.538F, -30.0526F, 18.0F, 10.0F, 6.0F, new Dilation(0.0F))
		.uv(220, 268).cuboid(44.8748F, 0.538F, -24.0526F, 6.0F, 10.0F, 6.0F, new Dilation(0.0F))
		.uv(124, 284).cuboid(44.8748F, 0.538F, 21.9474F, 6.0F, 10.0F, 6.0F, new Dilation(0.0F))
		.uv(252, 238).cuboid(-77.1252F, -7.462F, 33.9474F, 36.0F, 8.0F, 6.0F, new Dilation(0.0F))
		.uv(252, 252).cuboid(-77.1252F, -7.462F, -36.0526F, 36.0F, 8.0F, 6.0F, new Dilation(0.0F))
		.uv(0, 152).cuboid(-83.1252F, -13.462F, -30.0526F, 6.0F, 24.0F, 64.0F, new Dilation(0.0F))
		.uv(0, 136).cuboid(-77.1252F, 0.538F, -36.0526F, 104.0F, 10.0F, 6.0F, new Dilation(0.0F)), ModelTransform.pivot(6.5626F, -2.269F, 0.0263F));


		modelPartData.addChild(
			"back",
			ModelPartBuilder.create().uv(0, 19).cuboid(-13.0F, -7.0F, -1.0F, 18.0F, 6.0F, 2.0F),
			ModelTransform.of(-15.0F, 4.0F, 4.0F, 0.0F, (float) (Math.PI * 3.0 / 2.0), 0.0F)
		);
		modelPartData.addChild(
			"front",
			ModelPartBuilder.create().uv(0, 27).cuboid(-8.0F, -7.0F, -1.0F, 16.0F, 6.0F, 2.0F),
			ModelTransform.of(15.0F, 4.0F, 0.0F, 0.0F, (float) (Math.PI / 2), 0.0F)
		);
		modelPartData.addChild(
			"right",
			ModelPartBuilder.create().uv(0, 35).cuboid(-14.0F, -7.0F, -1.0F, 28.0F, 6.0F, 2.0F),
			ModelTransform.of(0.0F, 4.0F, -9.0F, 0.0F, (float) Math.PI, 0.0F)
		);
		modelPartData.addChild("left", ModelPartBuilder.create().uv(0, 43).cuboid(-14.0F, -7.0F, -1.0F, 28.0F, 6.0F, 2.0F), ModelTransform.pivot(0.0F, 4.0F, 9.0F));
		int n = 20;
		int o = 7;
		int p = 6;
		float f = -5.0F;
		modelPartData.addChild(
			"left_paddle",
			ModelPartBuilder.create().uv(62, 0).cuboid(-1.0F, 0.0F, -5.0F, 2.0F, 2.0F, 18.0F).cuboid(-1.001F, -3.0F, 8.0F, 1.0F, 6.0F, 7.0F),
			ModelTransform.of(3.0F, -5.0F, 9.0F, 0.0F, 0.0F, (float) (Math.PI / 16))
		);
		modelPartData.addChild(
			"right_paddle",
			ModelPartBuilder.create().uv(62, 20).cuboid(-1.0F, 0.0F, -5.0F, 2.0F, 2.0F, 18.0F).cuboid(0.001F, -3.0F, 8.0F, 1.0F, 6.0F, 7.0F),
			ModelTransform.of(3.0F, -5.0F, -9.0F, 0.0F, (float) Math.PI, (float) (Math.PI / 16))
		);
		modelPartData.addChild(
			"water_patch",
			ModelPartBuilder.create().uv(0, 0).cuboid(-14.0F, -9.0F, -3.0F, 28.0F, 16.0F, 3.0F),
			ModelTransform.of(0.0F, -3.0F, 1.0F, (float) (Math.PI / 2), 0.0F, 0.0F)
		);
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		addParts(modelPartData);
		return TexturedModelData.of(modelData, 512, 512);
	}

	public void setAngles(CustomBoatEntity boatEntity, float f, float g, float h, float i, float j) {
		setPaddleAngle(boatEntity, 0, this.leftPaddle, f);
		setPaddleAngle(boatEntity, 1, this.rightPaddle, f);
	}

	public ImmutableList<ModelPart> getParts() {
		return this.parts;
	}

	@Override
	public ModelPart getWaterPatch() {
		return this.waterPatch;
	}

	private static void setPaddleAngle(CustomBoatEntity entity, int sigma, ModelPart part, float angle) {
		float f = entity.interpolatePaddlePhase(sigma, angle);
		part.pitch = MathHelper.clampedLerp((float) (-Math.PI / 3), (float) (-Math.PI / 12), (MathHelper.sin(-f) + 1.0F) / 2.0F);
		part.yaw = MathHelper.clampedLerp((float) (-Math.PI / 4), (float) (Math.PI / 4), (MathHelper.sin(-f + 1.0F) + 1.0F) / 2.0F);
		if (sigma == 1) {
			part.yaw = (float) Math.PI - part.yaw;
		}
	}

    




    
    
    
}
