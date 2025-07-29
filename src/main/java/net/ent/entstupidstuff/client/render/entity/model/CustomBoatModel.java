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
import net.minecraft.entity.boss.dragon.EnderDragonPart;
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

   protected ImmutableList.Builder<ModelPart> getParts(ModelPart root) {
    	ImmutableList.Builder<ModelPart> builder = new ImmutableList.Builder();
    	builder.add(new ModelPart[]{root.getChild("bottom"), root.getChild("back"), root.getChild("front"), root.getChild("right"), root.getChild("left"), this.leftPaddle, this.rightPaddle});
    	return builder;
   }

	public static void addParts(ModelPartData modelPartData) {
		/*modelPartData.addChild(
			"bottom",
			ModelPartBuilder.create().uv(0, 0).cuboid(-14.0F, -9.0F, -3.0F, 28.0F, 16.0F, 3.0F),
			ModelTransform.of(0.0F, 3.0F, 1.0F, (float) (Math.PI / 2), 0.0F, 0.0F)
		);*/
		//New Botton Test
		/*modelPartData.addChild("bottom", ModelPartBuilder.create().uv(0, 0).cuboid(-77.1252F, 10.538F, -30.0526F, 104.0F, 8.0F, 64.0F, new Dilation(0.0F)) // CustomBoatModel
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
		.uv(0, 136).cuboid(-77.1252F, 0.538F, -36.0526F, 104.0F, 10.0F, 6.0F, new Dilation(0.0F)), ModelTransform.pivot(6.5626F, -2.269F, 0.0263F));*/

		ModelPartData bottom = modelPartData.addChild("bottom", ModelPartBuilder.create().uv(0, 46).cuboid(-18.0F, -7.0F, -19.75F, 28.0F, 7.0F, 42.0F, new Dilation(0.0F))
		.uv(224, 107).cuboid(6.0F, -20.0F, -2.75F, 8.0F, 16.0F, 8.0F, new Dilation(0.0F))
		.uv(254, 21).cuboid(8.0F, -36.0F, -0.75F, 4.0F, 16.0F, 4.0F, new Dilation(0.0F))
		.uv(256, 107).cuboid(8.0F, -52.0F, -0.75F, 4.0F, 16.0F, 4.0F, new Dilation(0.0F))
		.uv(40, 257).cuboid(8.0F, -68.0F, -0.75F, 4.0F, 16.0F, 4.0F, new Dilation(0.0F))
		.uv(232, 257).cuboid(8.0F, -84.0F, -0.75F, 4.0F, 16.0F, 4.0F, new Dilation(0.0F))
		.uv(108, 156).cuboid(12.0F, -88.0F, -23.75F, 4.0F, 4.0F, 50.0F, new Dilation(0.0F))
		.uv(0, 168).cuboid(12.0F, -42.0F, -23.75F, 4.0F, 4.0F, 50.0F, new Dilation(0.0F))
		.uv(146, 0).cuboid(16.0F, -84.0F, -23.75F, 4.0F, 8.0F, 50.0F, new Dilation(0.0F))
		.uv(108, 95).cuboid(16.0F, -53.0F, -23.75F, 4.0F, 11.0F, 50.0F, new Dilation(0.0F))
		.uv(0, 95).cuboid(20.0F, -76.0F, -23.75F, 4.0F, 23.0F, 50.0F, new Dilation(0.0F))
		.uv(56, 257).cuboid(8.0F, -100.0F, -0.75F, 4.0F, 16.0F, 4.0F, new Dilation(0.0F))
		.uv(248, 257).cuboid(8.0F, -116.0F, -0.75F, 4.0F, 16.0F, 4.0F, new Dilation(0.0F))
		.uv(140, 58).cuboid(2.0F, -96.0F, -6.75F, 16.0F, 4.0F, 16.0F, new Dilation(0.0F))
		.uv(232, 250).cuboid(2.0F, -100.0F, -8.75F, 16.0F, 5.0F, 2.0F, new Dilation(0.0F))
		.uv(72, 222).cuboid(18.0F, -100.0F, -6.75F, 2.0F, 5.0F, 16.0F, new Dilation(0.0F))
		.uv(224, 86).cuboid(0.0F, -100.0F, -6.75F, 2.0F, 5.0F, 16.0F, new Dilation(0.0F))
		.uv(254, 0).cuboid(2.0F, -100.0F, 9.25F, 16.0F, 5.0F, 2.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(10.0F, -4.0F, -19.75F, 31.0F, 4.0F, 42.0F, new Dilation(0.0F))
		.uv(200, 210).cuboid(41.0F, -6.0F, -15.75F, 12.0F, 6.0F, 34.0F, new Dilation(0.0F)), ModelTransform.pivot(-14.0F, 6.0F, -1.25F));

		ModelPartData front_string = bottom.addChild("front_string", ModelPartBuilder.create(), ModelTransform.pivot(11.0F, -96.0F, -23.75F));

		ModelPartData cube_r1 = front_string.addChild("cube_r1", ModelPartBuilder.create().uv(216, 58).cuboid(-1.0F, 0.0F, 24.0F, 2.0F, 105.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.7854F));

		ModelPartData back_string = bottom.addChild("back_string", ModelPartBuilder.create(), ModelTransform.pivot(8.0F, -96.0F, -23.75F));

		ModelPartData cube_r2 = back_string.addChild("cube_r2", ModelPartBuilder.create().uv(0, 222).cuboid(-1.0F, -85.0F, 24.0F, 2.0F, 84.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -2.7489F));

		ModelPartData front = modelPartData.addChild("front", ModelPartBuilder.create().uv(40, 237).cuboid(14.25F, -12.0F, -13.75F, 4.0F, 8.0F, 12.0F, new Dilation(0.0F))
		.uv(8, 237).cuboid(13.25F, -13.0F, -23.75F, 6.0F, 11.0F, 10.0F, new Dilation(0.0F))
		.uv(224, 131).cuboid(14.25F, -12.0F, -35.75F, 4.0F, 8.0F, 13.0F, new Dilation(0.0F)), ModelTransform.pivot(24.75F, 6.0F, 18.75F));

		ModelPartData front_bow = front.addChild("front_bow", ModelPartBuilder.create(), ModelTransform.pivot(19.25F, -9.0F, -1.75F));

		ModelPartData cube_r3 = front_bow.addChild("cube_r3", ModelPartBuilder.create().uv(72, 243).cuboid(-2.0F, -4.0F, -21.0F, 7.0F, 9.0F, 8.0F, new Dilation(0.0F))
		.uv(216, 165).cuboid(-1.0F, -3.0F, -20.0F, 33.0F, 6.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.3927F));

		ModelPartData back = modelPartData.addChild("back", ModelPartBuilder.create().uv(108, 210).cuboid(13.0F, -18.0F, -40.75F, 4.0F, 14.0F, 42.0F, new Dilation(0.0F))
		.uv(224, 58).cuboid(8.0F, -18.0F, -29.75F, 5.0F, 8.0F, 20.0F, new Dilation(0.0F)), ModelTransform.pivot(-49.0F, 6.0F, 19.75F));

		ModelPartData left = modelPartData.addChild("left", ModelPartBuilder.create().uv(224, 152).cuboid(2.25F, -12.0F, -40.5F, 12.0F, 8.0F, 4.0F, new Dilation(0.0F))
		.uv(140, 78).cuboid(-28.75F, -12.0F, -38.5F, 31.0F, 8.0F, 4.0F, new Dilation(0.0F))
		.uv(216, 189).cuboid(-56.75F, -15.0F, -36.5F, 28.0F, 11.0F, 4.0F, new Dilation(0.0F))
		.uv(254, 14).cuboid(-56.75F, -18.0F, -36.5F, 13.0F, 3.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(24.75F, 6.0F, 57.5F));

		ModelPartData right = modelPartData.addChild("right", ModelPartBuilder.create().uv(200, 250).cuboid(-9.0F, -12.0F, -36.5F, 12.0F, 8.0F, 4.0F, new Dilation(0.0F))
		.uv(216, 177).cuboid(-40.0F, -12.0F, -38.5F, 31.0F, 8.0F, 4.0F, new Dilation(0.0F))
		.uv(8, 222).cuboid(-68.0F, -15.0F, -40.5F, 28.0F, 11.0F, 4.0F, new Dilation(0.0F))
		.uv(254, 7).cuboid(-68.0F, -18.0F, -40.5F, 13.0F, 3.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(36.0F, 6.0F, 15.5F));

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

		//Water Patch
		modelPartData.addChild("water_patch", ModelPartBuilder.create().uv(-71, -40).cuboid(-32.0F, -27.0F, -21.0F, 71.0F, 4.0F, 42.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

	}

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		addParts(modelPartData);
		//return TexturedModelData.of(modelData, 256, 256);
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
