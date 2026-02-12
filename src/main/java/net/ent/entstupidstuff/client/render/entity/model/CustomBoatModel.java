package net.ent.entstupidstuff.client.render.entity.model;

import com.google.common.collect.ImmutableList;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.AbstractBoatModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

@Environment(EnvType.CLIENT)
@SuppressWarnings("unused")
public class CustomBoatModel extends AbstractBoatModel{

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
		super(root);
    	this.leftPaddle = root.getChild("left_paddle");
    	this.rightPaddle = root.getChild("right_paddle");
    	this.waterPatch = root.getChild("water_patch");
    	this.parts = this.getParts(root).build();
	}

    
   @SuppressWarnings({ "unchecked", "rawtypes" })
    protected ImmutableList.Builder<ModelPart> getParts(ModelPart root) {
    	ImmutableList.Builder<ModelPart> builder = new ImmutableList.Builder();
    	builder.add(new ModelPart[]{root.getChild("bottom"), root.getChild("back"), root.getChild("front"), root.getChild("right"), root.getChild("left"), this.leftPaddle, this.rightPaddle});
    	return builder;
   }

    public static void addParts(PartDefinition modelPartData) {
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
		.uv(0, 136).cuboid(-77.1252F, 0.538F, -36.0526F, 104.0F, 10.0F, 6.0F, new Dilation(0.0F)), ModelTransform.origin(6.5626F, -2.269F, 0.0263F));*/

		PartDefinition bottom = modelPartData.addOrReplaceChild("bottom", CubeListBuilder.create().texOffs(0, 46).addBox(-18.0F, -7.0F, -19.75F, 28.0F, 7.0F, 42.0F, new CubeDeformation(0.0F))
		.texOffs(224, 107).addBox(6.0F, -20.0F, -2.75F, 8.0F, 16.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(254, 21).addBox(8.0F, -36.0F, -0.75F, 4.0F, 16.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(256, 107).addBox(8.0F, -52.0F, -0.75F, 4.0F, 16.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(40, 257).addBox(8.0F, -68.0F, -0.75F, 4.0F, 16.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(232, 257).addBox(8.0F, -84.0F, -0.75F, 4.0F, 16.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(108, 156).addBox(12.0F, -88.0F, -23.75F, 4.0F, 4.0F, 50.0F, new CubeDeformation(0.0F))
		.texOffs(0, 168).addBox(12.0F, -42.0F, -23.75F, 4.0F, 4.0F, 50.0F, new CubeDeformation(0.0F))
		.texOffs(146, 0).addBox(16.0F, -84.0F, -23.75F, 4.0F, 8.0F, 50.0F, new CubeDeformation(0.0F))
		.texOffs(108, 95).addBox(16.0F, -53.0F, -23.75F, 4.0F, 11.0F, 50.0F, new CubeDeformation(0.0F))
		.texOffs(0, 95).addBox(20.0F, -76.0F, -23.75F, 4.0F, 23.0F, 50.0F, new CubeDeformation(0.0F))
		.texOffs(56, 257).addBox(8.0F, -100.0F, -0.75F, 4.0F, 16.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(248, 257).addBox(8.0F, -116.0F, -0.75F, 4.0F, 16.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(140, 58).addBox(2.0F, -96.0F, -6.75F, 16.0F, 4.0F, 16.0F, new CubeDeformation(0.0F))
		.texOffs(232, 250).addBox(2.0F, -100.0F, -8.75F, 16.0F, 5.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(72, 222).addBox(18.0F, -100.0F, -6.75F, 2.0F, 5.0F, 16.0F, new CubeDeformation(0.0F))
		.texOffs(224, 86).addBox(0.0F, -100.0F, -6.75F, 2.0F, 5.0F, 16.0F, new CubeDeformation(0.0F))
		.texOffs(254, 0).addBox(2.0F, -100.0F, 9.25F, 16.0F, 5.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(10.0F, -4.0F, -19.75F, 31.0F, 4.0F, 42.0F, new CubeDeformation(0.0F))
		.texOffs(200, 210).addBox(41.0F, -6.0F, -15.75F, 12.0F, 6.0F, 34.0F, new CubeDeformation(0.0F)), PartPose.offset(-14.0F, 6.0F, -1.25F));

		PartDefinition front_string = bottom.addOrReplaceChild("front_string", CubeListBuilder.create(), PartPose.offset(11.0F, -96.0F, -23.75F));

		PartDefinition cube_r1 = front_string.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(216, 58).addBox(-1.0F, 0.0F, 24.0F, 2.0F, 105.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.7854F));

		PartDefinition back_string = bottom.addOrReplaceChild("back_string", CubeListBuilder.create(), PartPose.offset(8.0F, -96.0F, -23.75F));

		PartDefinition cube_r2 = back_string.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 222).addBox(-1.0F, -85.0F, 24.0F, 2.0F, 84.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -2.7489F));

		PartDefinition front = modelPartData.addOrReplaceChild("front", CubeListBuilder.create().texOffs(40, 237).addBox(14.25F, -12.0F, -13.75F, 4.0F, 8.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(8, 237).addBox(13.25F, -13.0F, -23.75F, 6.0F, 11.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(224, 131).addBox(14.25F, -12.0F, -35.75F, 4.0F, 8.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offset(24.75F, 6.0F, 18.75F));

		PartDefinition front_bow = front.addOrReplaceChild("front_bow", CubeListBuilder.create(), PartPose.offset(19.25F, -9.0F, -1.75F));

		PartDefinition cube_r3 = front_bow.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(72, 243).addBox(-2.0F, -4.0F, -21.0F, 7.0F, 9.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(216, 165).addBox(-1.0F, -3.0F, -20.0F, 33.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.3927F));

		PartDefinition back = modelPartData.addOrReplaceChild("back", CubeListBuilder.create().texOffs(108, 210).addBox(13.0F, -18.0F, -40.75F, 4.0F, 14.0F, 42.0F, new CubeDeformation(0.0F))
		.texOffs(224, 58).addBox(8.0F, -18.0F, -29.75F, 5.0F, 8.0F, 20.0F, new CubeDeformation(0.0F)), PartPose.offset(-49.0F, 6.0F, 19.75F));

		PartDefinition left = modelPartData.addOrReplaceChild("left", CubeListBuilder.create().texOffs(224, 152).addBox(2.25F, -12.0F, -40.5F, 12.0F, 8.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(140, 78).addBox(-28.75F, -12.0F, -38.5F, 31.0F, 8.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(216, 189).addBox(-56.75F, -15.0F, -36.5F, 28.0F, 11.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(254, 14).addBox(-56.75F, -18.0F, -36.5F, 13.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(24.75F, 6.0F, 57.5F));

		PartDefinition right = modelPartData.addOrReplaceChild("right", CubeListBuilder.create().texOffs(200, 250).addBox(-9.0F, -12.0F, -36.5F, 12.0F, 8.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(216, 177).addBox(-40.0F, -12.0F, -38.5F, 31.0F, 8.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(8, 222).addBox(-68.0F, -15.0F, -40.5F, 28.0F, 11.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(254, 7).addBox(-68.0F, -18.0F, -40.5F, 13.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(36.0F, 6.0F, 15.5F));

		modelPartData.addOrReplaceChild(
			"left_paddle",
			CubeListBuilder.create().texOffs(62, 0).addBox(-1.0F, 0.0F, -5.0F, 2.0F, 2.0F, 18.0F).addBox(-1.001F, -3.0F, 8.0F, 1.0F, 6.0F, 7.0F),
			PartPose.offsetAndRotation(3.0F, -5.0F, 9.0F, 0.0F, 0.0F, (float) (Math.PI / 16))
		);

		modelPartData.addOrReplaceChild(
			"right_paddle",
			CubeListBuilder.create().texOffs(62, 20).addBox(-1.0F, 0.0F, -5.0F, 2.0F, 2.0F, 18.0F).addBox(0.001F, -3.0F, 8.0F, 1.0F, 6.0F, 7.0F),
			PartPose.offsetAndRotation(3.0F, -5.0F, -9.0F, 0.0F, (float) Math.PI, (float) (Math.PI / 16))
		);

		//Water Patch
		modelPartData.addOrReplaceChild("water_patch", CubeListBuilder.create().texOffs(-71, -40).addBox(-32.0F, -27.0F, -21.0F, 71.0F, 4.0F, 42.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

	}

	public static LayerDefinition getTexturedModelData() {
		MeshDefinition modelData = new MeshDefinition();
		PartDefinition modelPartData = modelData.getRoot();
		addParts(modelPartData);
		return LayerDefinition.create(modelData, 512, 512);
	}

	public static LayerDefinition getBaseTexturedModelData() {
		MeshDefinition modelData = new MeshDefinition();
		PartDefinition modelPartData = modelData.getRoot();
		modelPartData.addOrReplaceChild(
			"water_patch",
			CubeListBuilder.create().texOffs(0, 0).addBox(-14.0F, -9.0F, -3.0F, 28.0F, 16.0F, 3.0F),
			PartPose.offsetAndRotation(0.0F, -3.0F, 1.0F, (float) (Math.PI / 2), 0.0F, 0.0F)
		);
		return LayerDefinition.create(modelData, 0, 0);
	}
}
