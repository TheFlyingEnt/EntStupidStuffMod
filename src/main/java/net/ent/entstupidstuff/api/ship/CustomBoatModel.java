package net.ent.entstupidstuff.api.ship;

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
import net.minecraft.client.renderer.entity.state.BoatRenderState;
import net.minecraft.util.Mth;

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

    private final ModelPart sail_full;
	private final ModelPart sail_4;
	private final ModelPart sail_3;
	private final ModelPart sail_2;
	private final ModelPart sail_1;
    private final ModelPart rudder;

    public int   sailLevel    = 3;
    public float sinkProgress = 0f;
    public float forwardSpeed = 0f;
    public float rudderTurn   = 0f;
    public float waveTime = 0f;

	public CustomBoatModel(ModelPart root) {
		super(root);
    	this.leftPaddle = root.getChild("left_paddle");
    	this.rightPaddle = root.getChild("right_paddle");
    	this.waterPatch = root.getChild("water_patch");
        this.sail_full = root.getChild(BOTTOM).getChild("sail_full");
		this.sail_4 = this.sail_full.getChild("sail_4");
		this.sail_3 = this.sail_full.getChild("sail_3");
		this.sail_2 = this.sail_full.getChild("sail_2");
		this.sail_1 = this.sail_full.getChild("sail_1");
        this.rudder = root.getChild(BOTTOM).getChild("rudder");
    	this.parts = this.getParts(root).build();
	}

    
   @SuppressWarnings({ "unchecked", "rawtypes" })
    protected ImmutableList.Builder<ModelPart> getParts(ModelPart root) {
    	ImmutableList.Builder<ModelPart> builder = new ImmutableList.Builder();
    	builder.add(new ModelPart[]{root.getChild("bottom"), root.getChild("back"), root.getChild("front"), root.getChild("right"), root.getChild("left"), this.leftPaddle, this.rightPaddle});
    	return builder;
   }

    public static void addParts(PartDefinition partdefinition) {
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

        //CCcustomBoat3

		/*PartDefinition bottom = modelPartData.addOrReplaceChild("bottom", CubeListBuilder.create().texOffs(0, 46).addBox(-18.0F, -7.0F, -19.75F, 28.0F, 7.0F, 42.0F, new CubeDeformation(0.0F))
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
		.texOffs(254, 7).addBox(-68.0F, -18.0F, -40.5F, 13.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(36.0F, 6.0F, 15.5F));*/


        /*PartDefinition right = partdefinition.addOrReplaceChild("right", CubeListBuilder.create(), PartPose.offset(23.0F, 33.0F, 2.0F));

		PartDefinition cube_r1 = right.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(302, 521).addBox(-24.5F, -21.3257F, -52.9874F, 4.0F, 11.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-23.0F, -95.6725F, -2.0F, 0.0F, 1.5708F, -1.1257F));

		PartDefinition cube_r2 = right.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(776, 214).addBox(-24.5F, 41.6725F, -33.84F, 4.0F, 16.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(771, 206).addBox(-25.0F, 52.6725F, -30.84F, 1.0F, 2.0F, 9.0F, new CubeDeformation(0.0F))
		.texOffs(889, 151).addBox(-24.0F, 45.6725F, -30.84F, 3.0F, 12.0F, 9.0F, new CubeDeformation(0.0F))
		.texOffs(772, 203).addBox(-25.0F, 43.6725F, -30.84F, 4.0F, 2.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(773, 220).addBox(-25.0F, 47.6725F, -21.84F, 4.0F, 3.0F, 31.0F, new CubeDeformation(0.0F))
		.texOffs(850, 102).addBox(-24.0F, 50.6725F, -21.84F, 3.0F, 7.0F, 31.0F, new CubeDeformation(0.0F))
		.texOffs(786, 214).addBox(-25.0F, 52.6725F, -21.84F, 1.0F, 2.0F, 31.0F, new CubeDeformation(0.0F))
		.texOffs(878, 82).addBox(-24.0F, 45.6725F, 9.16F, 3.0F, 12.0F, 18.0F, new CubeDeformation(0.0F))
		.texOffs(797, 222).addBox(-25.0F, 52.6725F, 9.16F, 1.0F, 2.0F, 18.0F, new CubeDeformation(0.0F))
		.texOffs(805, 240).addBox(-25.0F, 43.6725F, 8.16F, 4.0F, 2.0F, 19.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-23.0F, -95.6725F, -2.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition cube_r3 = right.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(786, 214).addBox(-25.0F, 52.6725F, -9.16F, 1.0F, 2.0F, 31.0F, new CubeDeformation(0.0F))
		.texOffs(850, 102).addBox(-24.0F, 50.6725F, -9.16F, 3.0F, 7.0F, 31.0F, new CubeDeformation(0.0F))
		.texOffs(773, 220).addBox(-25.0F, 47.6725F, -9.16F, 4.0F, 3.0F, 31.0F, new CubeDeformation(0.0F))
		.texOffs(776, 214).addBox(-24.5F, 41.6725F, 30.84F, 4.0F, 16.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(771, 206).addBox(-25.0F, 52.6725F, 21.84F, 1.0F, 2.0F, 9.0F, new CubeDeformation(0.0F))
		.texOffs(889, 151).addBox(-24.0F, 45.6725F, 21.84F, 3.0F, 12.0F, 9.0F, new CubeDeformation(0.0F))
		.texOffs(772, 203).addBox(-25.0F, 43.6725F, 20.84F, 4.0F, 2.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(805, 240).addBox(-25.0F, 43.6725F, -27.16F, 4.0F, 2.0F, 19.0F, new CubeDeformation(0.0F))
		.texOffs(797, 222).addBox(-25.0F, 52.6725F, -27.16F, 1.0F, 2.0F, 18.0F, new CubeDeformation(0.0F))
		.texOffs(878, 82).addBox(-24.0F, 45.6725F, -27.16F, 3.0F, 12.0F, 18.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-23.0F, -95.6725F, -2.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition cube_r4 = right.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(302, 521).addBox(-24.5F, -21.3257F, 50.9874F, 4.0F, 11.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-23.0F, -95.6725F, -2.0F, 0.0F, -1.5708F, -1.1257F));

		PartDefinition front = partdefinition.addOrReplaceChild("front", CubeListBuilder.create(), PartPose.offset(-25.0F, 10.0F, -66.0F));

		PartDefinition cube_r5 = front.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(794, 222).addBox(-1.5F, 66.2296F, -61.2824F, 3.0F, 2.0F, 13.0F, new CubeDeformation(0.0F))
		.texOffs(210, 382).addBox(-2.0F, 65.6916F, -49.0911F, 4.0F, 5.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(800, 208).addBox(-1.5F, 66.2296F, -48.2824F, 3.0F, 4.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(25.0F, -72.6725F, 66.0F, 0.0F, -1.5708F, -0.3927F));

		PartDefinition cube_r6 = front.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(876, 198).addBox(-1.5F, 67.6155F, 19.5364F, 3.0F, 12.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(25.0F, -72.6725F, 66.0F, 0.0F, -1.5708F, -1.1781F));

		PartDefinition cube_r7 = front.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(907, 49).addBox(-40.8597F, 45.6725F, 13.5482F, 3.0F, 12.0F, 27.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(25.7F, -72.6725F, 65.75F, 3.1416F, 0.7854F, 3.1416F));

		PartDefinition cube_r8 = front.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(880, 87).addBox(-32.4907F, 45.6725F, 15.5255F, 3.0F, 12.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(25.4F, -72.6725F, 65.95F, 3.1416F, 1.1781F, 3.1416F));

		PartDefinition cube_r9 = front.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(827, 261).addBox(-33.4907F, 52.6725F, 15.5255F, 1.0F, 2.0F, 13.0F, new CubeDeformation(0.0F))
		.texOffs(773, 213).addBox(-33.4907F, 43.6725F, 15.5255F, 4.0F, 2.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(25.0F, -72.6725F, 66.0F, 3.1416F, 1.1781F, 3.1416F));

		PartDefinition cube_r10 = front.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(826, 215).addBox(-41.8597F, 52.6725F, 13.5482F, 1.0F, 2.0F, 28.0F, new CubeDeformation(0.0F))
		.texOffs(772, 201).addBox(-41.8597F, 43.6725F, 13.5482F, 4.0F, 2.0F, 28.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(25.0F, -72.6725F, 66.0F, 3.1416F, 0.7854F, 3.1416F));

		PartDefinition cube_r11 = front.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(826, 215).addBox(-41.8597F, 52.6725F, -41.5482F, 1.0F, 2.0F, 28.0F, new CubeDeformation(0.0F))
		.texOffs(772, 201).addBox(-41.8597F, 43.6725F, -41.5482F, 4.0F, 2.0F, 28.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(25.0F, -72.6725F, 66.0F, -3.1416F, -0.7854F, 3.1416F));

		PartDefinition cube_r12 = front.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(907, 49).addBox(-40.8597F, 45.6725F, -40.5482F, 3.0F, 12.0F, 27.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(25.7F, -72.6725F, 66.25F, -3.1416F, -0.7854F, 3.1416F));

		PartDefinition cube_r13 = front.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(773, 213).addBox(-33.4907F, 43.6725F, -28.5255F, 4.0F, 2.0F, 13.0F, new CubeDeformation(0.0F))
		.texOffs(827, 261).addBox(-33.4907F, 52.6725F, -28.5255F, 1.0F, 2.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(25.0F, -72.6725F, 66.0F, -3.1416F, -1.1781F, 3.1416F));

		PartDefinition cube_r14 = front.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(880, 87).addBox(-32.4907F, 45.6725F, -28.5255F, 3.0F, 12.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(25.4F, -72.6725F, 66.05F, -3.1416F, -1.1781F, 3.1416F));

		PartDefinition bottom = partdefinition.addOrReplaceChild("bottom", CubeListBuilder.create(), PartPose.offset(-17.0F, 14.0F, -77.0F));

		PartDefinition cube_r15 = bottom.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(780, 112).addBox(-8.0F, 41.6725F, 35.84F, 16.0F, 16.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(785, 232).addBox(-9.0F, 39.6725F, 34.84F, 18.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(768, 192).addBox(-1.5F, 11.1725F, 15.34F, 3.0F, 3.0F, 57.0F, new CubeDeformation(0.0F))
		.texOffs(788, 202).addBox(-2.5F, -67.8275F, 10.34F, 5.0F, 97.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(797, 131).addBox(-3.5F, 28.1725F, 9.34F, 7.0F, 4.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(788, 215).addBox(-3.0F, 29.1725F, 9.84F, 6.0F, 25.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(809, 70).addBox(-21.0F, 49.4225F, 39.84F, 42.0F, 8.0F, 21.0F, new CubeDeformation(0.0F))
		.texOffs(813, 113).addBox(8.0F, 53.4225F, 33.84F, 13.0F, 4.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(832, 121).addBox(-21.0F, 53.4225F, 33.84F, 13.0F, 4.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(833, 62).addBox(-18.0F, 57.4225F, -42.16F, 12.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(835, 54).addBox(-6.0F, 57.4225F, -52.16F, 12.0F, 1.0F, 18.0F, new CubeDeformation(0.0F))
		.texOffs(768, 0).addBox(-21.0F, 57.4225F, -34.16F, 42.0F, 1.0F, 70.0F, new CubeDeformation(0.0F))
		.texOffs(819, 215).addBox(-1.5F, 56.6725F, 58.84F, 3.0F, 8.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(776, 200).addBox(-1.5F, 61.6725F, -53.16F, 3.0F, 3.0F, 54.0F, new CubeDeformation(0.0F))
		.texOffs(770, 205).addBox(-1.5F, 61.6725F, 0.84F, 3.0F, 3.0F, 58.0F, new CubeDeformation(0.0F))
		.texOffs(838, 135).addBox(-16.5F, 57.6725F, 55.59F, 33.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(771, 51).addBox(-18.0F, 60.6725F, -31.16F, 36.0F, 1.0F, 87.0F, new CubeDeformation(0.0F))
		.texOffs(823, 61).addBox(-22.0F, 57.6725F, -25.16F, 4.0F, 4.0F, 79.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(17.0F, -76.6725F, 77.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition cube_r16 = bottom.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(768, 192).addBox(-1.5F, -46.5542F, 35.3072F, 3.0F, 3.0F, 42.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(0.0F, -43.5542F, -80.6929F, 0.0F, 82.0F, 158.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(17.0F, -76.6725F, 77.0F, 0.0F, -1.5708F, 0.3927F));

		PartDefinition cube_r17 = bottom.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(832, 74).addBox(-18.0F, 57.4225F, -44.16F, 4.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(17.0F, -76.6725F, 81.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition cube_r18 = bottom.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(832, 74).addBox(-18.0F, 57.4225F, -44.16F, 4.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(17.0F, -76.6725F, 105.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition cube_r19 = bottom.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(828, 70).addBox(-18.0F, 57.4225F, -48.16F, 4.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(17.0F, -76.6725F, 85.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition cube_r20 = bottom.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(828, 70).addBox(-18.0F, 57.4225F, -48.16F, 4.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(825, 62).addBox(-18.0F, 57.4225F, -42.16F, 12.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(17.0F, -76.6725F, 101.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition cube_r21 = bottom.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(781, 210).addBox(-57.0263F, 25.9687F, 51.84F, 4.0F, 5.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(774, 205).addBox(-57.0263F, 25.9687F, 30.84F, 4.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(17.0F, -76.6725F, 77.0F, 1.5708F, -0.829F, -1.5708F));

		PartDefinition cube_r22 = bottom.addOrReplaceChild("cube_r22", CubeListBuilder.create().texOffs(770, 208).addBox(-1.5F, 71.3202F, -6.8594F, 3.0F, 12.0F, 15.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(17.0F, -76.6725F, 77.0F, 0.0F, -1.5708F, -0.7854F));

		PartDefinition cube_r23 = bottom.addOrReplaceChild("cube_r23", CubeListBuilder.create().texOffs(791, 205).addBox(-1.5F, 72.0931F, -31.3644F, 3.0F, 8.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(17.0F, -76.6725F, 77.0F, 0.0F, -1.5708F, -0.3927F));

		PartDefinition cube_r24 = bottom.addOrReplaceChild("cube_r24", CubeListBuilder.create().texOffs(186, 456).addBox(34.0312F, 57.6725F, -37.9624F, 4.0F, 4.0F, 24.0F, new CubeDeformation(0.0F))
		.texOffs(856, 72).addBox(-53.627F, 57.6725F, 22.5143F, 4.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(17.0F, -76.6725F, 77.0F, 0.0F, -0.7854F, 0.0F));

		PartDefinition cube_r25 = bottom.addOrReplaceChild("cube_r25", CubeListBuilder.create().texOffs(856, 72).addBox(-53.627F, 57.6725F, -30.5143F, 4.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(17.0F, -76.6725F, 77.0F, 0.0F, 0.7854F, 0.0F));

		PartDefinition cube_r26 = bottom.addOrReplaceChild("cube_r26", CubeListBuilder.create().texOffs(823, 61).addBox(-22.0F, 57.6725F, -53.84F, 4.0F, 4.0F, 79.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(17.0F, -76.6725F, 77.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition cube_r27 = bottom.addOrReplaceChild("cube_r27", CubeListBuilder.create().texOffs(873, 115).addBox(-29.8775F, 57.6725F, 14.4431F, 4.0F, 4.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(17.4F, -76.6725F, 76.95F, 3.1416F, 1.1781F, 3.1416F));

		PartDefinition cube_r28 = bottom.addOrReplaceChild("cube_r28", CubeListBuilder.create().texOffs(804, 79).addBox(-38.0312F, 57.6725F, 13.9624F, 4.0F, 4.0F, 24.0F, new CubeDeformation(0.0F))
		.texOffs(0, 463).addBox(-38.0312F, 57.6725F, 13.9624F, 4.0F, 4.0F, 24.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(17.4F, -76.6725F, 77.05F, 3.1416F, 0.7854F, 3.1416F));

		PartDefinition cube_r29 = bottom.addOrReplaceChild("cube_r29", CubeListBuilder.create().texOffs(873, 115).addBox(-29.8775F, 57.6725F, -27.4431F, 4.0F, 4.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(17.4F, -76.6725F, 77.05F, -3.1416F, -1.1781F, 3.1416F));

		PartDefinition cube_r30 = bottom.addOrReplaceChild("cube_r30", CubeListBuilder.create().texOffs(804, 79).addBox(-38.0312F, 57.6725F, -37.9624F, 4.0F, 4.0F, 24.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(17.4F, -76.6725F, 76.95F, -3.1416F, -0.7854F, 3.1416F));

		PartDefinition left = partdefinition.addOrReplaceChild("left", CubeListBuilder.create(), PartPose.offset(-23.0F, 33.0F, 2.0F));

		PartDefinition back = partdefinition.addOrReplaceChild("back", CubeListBuilder.create(), PartPose.offset(-22.3F, 12.3F, 16.0F));

		PartDefinition cube_r31 = back.addOrReplaceChild("cube_r31", CubeListBuilder.create().texOffs(120, 389).addBox(-12.0F, 15.6725F, 50.84F, 24.0F, 0.0F, 28.0F, new CubeDeformation(0.0F))
		.texOffs(785, 216).addBox(-20.5F, 22.9225F, 72.84F, 41.0F, 3.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(773, 201).addBox(-24.5F, 22.9225F, 54.84F, 4.0F, 3.0F, 22.0F, new CubeDeformation(0.0F))
		.texOffs(779, 200).addBox(-24.5F, 22.9225F, 51.84F, 4.0F, 35.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(770, 198).addBox(-25.0F, 52.9225F, 30.84F, 1.0F, 2.0F, 24.0F, new CubeDeformation(0.0F))
		.texOffs(778, 204).addBox(-25.0F, 37.2725F, 40.84F, 5.0F, 2.0F, 14.0F, new CubeDeformation(0.0F))
		.texOffs(863, 126).addBox(-24.0F, 38.9225F, 37.84F, 3.0F, 2.0F, 16.0F, new CubeDeformation(0.0F))
		.texOffs(856, 112).addBox(-24.0F, 40.9225F, 33.84F, 3.0F, 17.0F, 20.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(22.3F, -75.2225F, -16.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition cube_r32 = back.addOrReplaceChild("cube_r32", CubeListBuilder.create().texOffs(774, 233).addBox(-22.0F, 24.0278F, 79.4958F, 40.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(22.3F, -74.9725F, -14.0F, 0.0F, -1.5708F, -0.3927F));

		PartDefinition cube_r33 = back.addOrReplaceChild("cube_r33", CubeListBuilder.create().texOffs(789, 232).addBox(16.5F, -6.8765F, 75.2648F, 3.0F, 25.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(770, 203).addBox(-19.5F, -6.8765F, 75.2648F, 3.0F, 25.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(783, 8).addBox(-20.0F, 11.0278F, 74.4958F, 40.0F, 19.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(22.3F, -74.9725F, -16.0F, 0.0F, -1.5708F, -0.3927F));

		PartDefinition cube_r34 = back.addOrReplaceChild("cube_r34", CubeListBuilder.create().texOffs(903, 105).addBox(-37.7951F, -1.2278F, 51.84F, 9.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(11.3F, -74.9725F, -16.0F, 1.5708F, -0.7854F, -1.5708F));

		PartDefinition cube_r35 = back.addOrReplaceChild("cube_r35", CubeListBuilder.create().texOffs(783, 0).addBox(-27.0842F, 9.8873F, 51.84F, 10.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(11.3F, -74.9725F, -16.0F, 1.5708F, -1.1781F, -1.5708F));

		PartDefinition cube_r36 = back.addOrReplaceChild("cube_r36", CubeListBuilder.create().texOffs(868, 0).addBox(-12.0F, 15.6725F, 51.84F, 24.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(11.3F, -74.9725F, -16.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition cube_r37 = back.addOrReplaceChild("cube_r37", CubeListBuilder.create().texOffs(917, 0).addBox(17.0842F, 9.8873F, 51.84F, 10.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(11.3F, -74.9725F, -16.0F, -1.5708F, -1.1781F, 1.5708F));

		PartDefinition cube_r38 = back.addOrReplaceChild("cube_r38", CubeListBuilder.create().texOffs(882, 0).addBox(28.7951F, -1.2278F, 51.84F, 9.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(11.3F, -74.9725F, -16.0F, -1.5708F, -0.7854F, 1.5708F));

		PartDefinition cube_r39 = back.addOrReplaceChild("cube_r39", CubeListBuilder.create().texOffs(903, 105).addBox(-37.7951F, -1.2278F, 51.84F, 9.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.7F, -74.9725F, -16.0F, 1.5708F, -0.7854F, -1.5708F));

		PartDefinition cube_r40 = back.addOrReplaceChild("cube_r40", CubeListBuilder.create().texOffs(783, 0).addBox(-27.0842F, 9.8873F, 51.84F, 10.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.7F, -74.9725F, -16.0F, 1.5708F, -1.1781F, -1.5708F));

		PartDefinition cube_r41 = back.addOrReplaceChild("cube_r41", CubeListBuilder.create().texOffs(868, 0).addBox(-12.0F, 15.6725F, 51.84F, 24.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.7F, -74.9725F, -16.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition cube_r42 = back.addOrReplaceChild("cube_r42", CubeListBuilder.create().texOffs(917, 0).addBox(17.0842F, 9.8873F, 51.84F, 10.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.7F, -74.9725F, -16.0F, -1.5708F, -1.1781F, 1.5708F));

		PartDefinition cube_r43 = back.addOrReplaceChild("cube_r43", CubeListBuilder.create().texOffs(882, 0).addBox(28.7951F, -1.2278F, 51.84F, 9.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.7F, -74.9725F, -16.0F, -1.5708F, -0.7854F, 1.5708F));

		PartDefinition cube_r44 = back.addOrReplaceChild("cube_r44", CubeListBuilder.create().texOffs(882, 0).addBox(28.7951F, -1.2278F, 51.84F, 9.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(22.55F, -74.9725F, -16.0F, -1.5708F, -0.7854F, 1.5708F));

		PartDefinition cube_r45 = back.addOrReplaceChild("cube_r45", CubeListBuilder.create().texOffs(917, 0).addBox(17.0842F, 9.8873F, 51.84F, 10.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(22.55F, -74.9725F, -16.0F, -1.5708F, -1.1781F, 1.5708F));

		PartDefinition cube_r46 = back.addOrReplaceChild("cube_r46", CubeListBuilder.create().texOffs(868, 0).addBox(-12.0F, 15.6725F, 51.84F, 24.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(22.55F, -74.9725F, -16.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition cube_r47 = back.addOrReplaceChild("cube_r47", CubeListBuilder.create().texOffs(783, 0).addBox(-27.0842F, 9.8873F, 51.84F, 10.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(22.55F, -74.9725F, -16.0F, 1.5708F, -1.1781F, -1.5708F));

		PartDefinition cube_r48 = back.addOrReplaceChild("cube_r48", CubeListBuilder.create().texOffs(442, 198).addBox(-27.0842F, 9.8873F, 50.84F, 10.0F, 0.0F, 28.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(22.3F, -75.2225F, -16.0F, 1.5708F, -1.1781F, -1.5708F));

		PartDefinition cube_r49 = back.addOrReplaceChild("cube_r49", CubeListBuilder.create().texOffs(328, 413).addBox(-42.7951F, -1.2278F, 50.84F, 14.0F, 0.0F, 28.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(22.3F, -75.2225F, -16.0F, 1.5708F, -0.7854F, -1.5708F));

		PartDefinition cube_r50 = back.addOrReplaceChild("cube_r50", CubeListBuilder.create().texOffs(314, 441).addBox(17.0842F, 9.8873F, 50.84F, 10.0F, 0.0F, 28.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(22.3F, -75.2225F, -16.0F, -1.5708F, -1.1781F, 1.5708F));

		PartDefinition cube_r51 = back.addOrReplaceChild("cube_r51", CubeListBuilder.create().texOffs(244, 413).addBox(28.7951F, -1.2278F, 50.84F, 14.0F, 0.0F, 28.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(22.3F, -75.2225F, -16.0F, -1.5708F, -0.7854F, 1.5708F));

		PartDefinition cube_r52 = back.addOrReplaceChild("cube_r52", CubeListBuilder.create().texOffs(903, 105).addBox(-37.7951F, -1.2278F, 51.84F, 9.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(22.55F, -74.9725F, -16.0F, 1.5708F, -0.7854F, -1.5708F));

		PartDefinition cube_r53 = back.addOrReplaceChild("cube_r53", CubeListBuilder.create().texOffs(773, 201).addBox(-24.5F, 22.6725F, -76.84F, 4.0F, 3.0F, 22.0F, new CubeDeformation(0.0F))
		.texOffs(856, 112).addBox(-24.0F, 40.6725F, -53.84F, 3.0F, 17.0F, 20.0F, new CubeDeformation(0.0F))
		.texOffs(770, 198).addBox(-25.0F, 52.6725F, -54.84F, 1.0F, 2.0F, 24.0F, new CubeDeformation(0.0F))
		.texOffs(778, 204).addBox(-25.0F, 37.0225F, -54.84F, 5.0F, 2.0F, 14.0F, new CubeDeformation(0.0F))
		.texOffs(863, 126).addBox(-24.0F, 38.6725F, -53.84F, 3.0F, 2.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(22.3F, -74.9725F, -16.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition cube_r54 = back.addOrReplaceChild("cube_r54", CubeListBuilder.create().texOffs(788, 203).addBox(-44.0833F, 52.6725F, -53.0984F, 1.0F, 2.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(810, 74).addBox(-42.891F, 38.6725F, -52.1313F, 3.0F, 19.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(773, 208).addBox(-44.0889F, 36.9725F, -53.1317F, 5.0F, 2.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(22.3F, -74.9725F, -16.0F, 0.0F, 1.1781F, 0.0F));

		PartDefinition cube_r55 = back.addOrReplaceChild("cube_r55", CubeListBuilder.create().texOffs(788, 203).addBox(-44.0833F, 52.6725F, 41.0984F, 1.0F, 2.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(810, 74).addBox(-42.891F, 38.6725F, 40.1313F, 3.0F, 19.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(773, 208).addBox(-44.0889F, 36.9725F, 41.1317F, 5.0F, 2.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(22.3F, -74.9725F, -16.0F, 0.0F, -1.1781F, 0.0F));

		PartDefinition cube_r56 = back.addOrReplaceChild("cube_r56", CubeListBuilder.create().texOffs(789, 214).addBox(-24.0F, 38.0225F, -44.84F, 38.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.7F, -74.9725F, -21.0F, 0.0F, 1.5708F, 0.0F));
        */
        
        /*
        PartDefinition right = partdefinition.addOrReplaceChild("right", CubeListBuilder.create(), PartPose.offset(23.0F, 33.0F, 2.0F));

		PartDefinition cube_r1 = right.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(444, 369).addBox(-25.0F, 52.6725F, -9.16F, 1.0F, 2.0F, 31.0F, new CubeDeformation(0.0F))
		.texOffs(412, 413).addBox(-24.0F, 50.6725F, -9.16F, 3.0F, 7.0F, 31.0F, new CubeDeformation(0.0F))
		.texOffs(320, 441).addBox(-25.0F, 47.6725F, -9.16F, 4.0F, 3.0F, 31.0F, new CubeDeformation(0.0F))
		.texOffs(76, 429).addBox(-24.5F, 41.6725F, 30.84F, 4.0F, 16.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(526, 406).addBox(-25.0F, 52.6725F, 21.84F, 1.0F, 2.0F, 9.0F, new CubeDeformation(0.0F))
		.texOffs(428, 511).addBox(-24.0F, 45.6725F, 21.84F, 3.0F, 12.0F, 9.0F, new CubeDeformation(0.0F))
		.texOffs(92, 404).addBox(-25.0F, 43.6725F, 20.84F, 4.0F, 2.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(142, 486).addBox(-25.0F, 43.6725F, -27.16F, 4.0F, 2.0F, 19.0F, new CubeDeformation(0.0F))
		.texOffs(228, 497).addBox(-25.0F, 52.6725F, -27.16F, 1.0F, 2.0F, 18.0F, new CubeDeformation(0.0F))
		.texOffs(58, 485).addBox(-24.0F, 45.6725F, -27.16F, 3.0F, 12.0F, 18.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-23.0F, -95.6725F, -2.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition cube_r2 = right.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(46, 487).addBox(-24.5F, -21.3258F, 50.9874F, 4.0F, 11.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-23.0F, -95.6725F, -2.0F, 0.0F, -1.5708F, -1.1257F));

		PartDefinition front = partdefinition.addOrReplaceChild("front", CubeListBuilder.create(), PartPose.offset(-25.0F, 10.0F, -66.0F));

		PartDefinition cube_r3 = front.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(76, 455).addBox(37.8596F, 43.6725F, -41.5482F, 4.0F, 2.0F, 28.0F, new CubeDeformation(0.0F))
		.texOffs(444, 330).addBox(37.8596F, 45.6725F, -40.5482F, 3.0F, 12.0F, 27.0F, new CubeDeformation(0.0F))
		.texOffs(0, 457).addBox(40.8596F, 52.6725F, -41.5482F, 1.0F, 2.0F, 28.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(25.0F, -72.6725F, 66.0F, 0.0F, -0.7854F, 0.0F));

		PartDefinition cube_r4 = front.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(304, 503).addBox(29.4907F, 45.6725F, -28.5255F, 3.0F, 12.0F, 13.0F, new CubeDeformation(0.0F))
		.texOffs(394, 511).addBox(29.4907F, 43.6725F, -28.5255F, 4.0F, 2.0F, 13.0F, new CubeDeformation(0.0F))
		.texOffs(104, 516).addBox(32.4907F, 52.6725F, -28.5255F, 1.0F, 2.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(25.0F, -72.6725F, 66.0F, 0.0F, -1.1781F, 0.0F));

		PartDefinition cube_r5 = front.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(500, 514).addBox(-1.5F, 66.2296F, -61.2824F, 3.0F, 2.0F, 13.0F, new CubeDeformation(0.0F))
		.texOffs(210, 382).addBox(-2.0F, 65.6915F, -49.091F, 4.0F, 5.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(180, 517).addBox(-1.5F, 66.2296F, -48.2824F, 3.0F, 4.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(25.0F, -72.6725F, 66.0F, 0.0F, -1.5708F, -0.3927F));

		PartDefinition cube_r6 = front.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(518, 463).addBox(-1.5F, 67.6154F, 19.5364F, 3.0F, 12.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(25.0F, -72.6725F, 66.0F, 0.0F, -1.5708F, -1.1781F));

		PartDefinition cube_r7 = front.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(140, 456).addBox(-41.8596F, 52.6725F, -41.5482F, 1.0F, 2.0F, 28.0F, new CubeDeformation(0.0F))
		.texOffs(160, 417).addBox(-40.8596F, 45.6725F, -40.5482F, 3.0F, 12.0F, 27.0F, new CubeDeformation(0.0F))
		.texOffs(454, 451).addBox(-41.8596F, 43.6725F, -41.5482F, 4.0F, 2.0F, 28.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(25.0F, -72.6725F, 66.0F, -3.1416F, -0.7854F, 3.1416F));

		PartDefinition cube_r8 = front.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(360, 509).addBox(-33.4907F, 43.6725F, -28.5255F, 4.0F, 2.0F, 13.0F, new CubeDeformation(0.0F))
		.texOffs(76, 516).addBox(-33.4907F, 52.6725F, -28.5255F, 1.0F, 2.0F, 13.0F, new CubeDeformation(0.0F))
		.texOffs(500, 481).addBox(-32.4907F, 45.6725F, -28.5255F, 3.0F, 12.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(25.0F, -72.6725F, 66.0F, -3.1416F, -1.1781F, 3.1416F));

		PartDefinition bottom = partdefinition.addOrReplaceChild("bottom", CubeListBuilder.create(), PartPose.offset(-17.0F, 14.0F, -77.0F));

		PartDefinition cube_r9 = bottom.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(188, 497).addBox(-8.0F, 41.6725F, 35.84F, 16.0F, 16.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(500, 506).addBox(-9.0F, 39.6725F, 34.84F, 18.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(0, 344).addBox(-1.5F, 11.1725F, 15.34F, 3.0F, 3.0F, 57.0F, new CubeDeformation(0.0F))
		.texOffs(224, 344).addBox(-2.5F, -67.8275F, 10.34F, 5.0F, 97.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(444, 402).addBox(-3.5F, 28.1725F, 9.34F, 7.0F, 4.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(336, 503).addBox(-3.0F, 29.1725F, 9.84F, 6.0F, 25.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(316, 198).addBox(-21.0F, 49.4225F, 39.84F, 42.0F, 8.0F, 21.0F, new CubeDeformation(0.0F))
		.texOffs(198, 456).addBox(8.0F, 53.4225F, 33.84F, 13.0F, 4.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(512, 194).addBox(-21.0F, 53.4225F, 33.84F, 13.0F, 4.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(278, 240).addBox(-21.0F, 57.4225F, -53.16F, 42.0F, 1.0F, 89.0F, new CubeDeformation(0.0F))
		.texOffs(412, 526).addBox(-1.5F, 56.6725F, 58.84F, 3.0F, 8.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(316, 0).addBox(-1.5F, 61.6725F, -53.16F, 3.0F, 3.0F, 112.0F, new CubeDeformation(0.0F))
		.texOffs(278, 330).addBox(18.0F, 57.6725F, -25.16F, 4.0F, 4.0F, 79.0F, new CubeDeformation(0.0F))
		.texOffs(406, 232).addBox(-16.5F, 57.6725F, 55.59F, 33.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(0, 240).addBox(-18.0F, 60.6725F, -47.16F, 36.0F, 1.0F, 103.0F, new CubeDeformation(0.0F))
		.texOffs(316, 115).addBox(-22.0F, 57.6725F, -25.16F, 4.0F, 4.0F, 79.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(17.0F, -76.6725F, 77.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition cube_r10 = bottom.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(120, 344).addBox(-1.5F, -46.5542F, 35.3072F, 3.0F, 3.0F, 42.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(0.0F, -43.5542F, -80.6928F, 0.0F, 82.0F, 158.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(17.0F, -76.6725F, 77.0F, 0.0F, -1.5708F, 0.3927F));

		PartDefinition cube_r11 = bottom.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(318, 528).addBox(-57.0263F, 25.9687F, 51.84F, 4.0F, 5.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(390, 441).addBox(-57.0263F, 25.9687F, 30.84F, 4.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(17.0F, -76.6725F, 77.0F, 1.5708F, -0.829F, -1.5708F));

		PartDefinition cube_r12 = bottom.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(418, 484).addBox(-1.5F, 71.3202F, -6.8594F, 3.0F, 12.0F, 15.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(17.0F, -76.6725F, 77.0F, 0.0F, -1.5708F, -0.7854F));

		PartDefinition cube_r13 = bottom.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(476, 518).addBox(-1.5F, 72.0931F, -31.3644F, 3.0F, 8.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(17.0F, -76.6725F, 77.0F, 0.0F, -1.5708F, -0.3927F));

		PartDefinition cube_r14 = bottom.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(508, 383).addBox(25.8776F, 57.6725F, -27.4431F, 4.0F, 4.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(17.0F, -76.6725F, 77.0F, 0.0F, -1.1781F, 0.0F));

		PartDefinition cube_r15 = bottom.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(310, 475).addBox(34.0312F, 57.6725F, -37.9624F, 4.0F, 4.0F, 24.0F, new CubeDeformation(0.0F))
		.texOffs(254, 469).addBox(34.0312F, 57.6725F, -37.9624F, 4.0F, 4.0F, 24.0F, new CubeDeformation(0.0F))
		.texOffs(518, 451).addBox(-53.627F, 57.6725F, 22.5143F, 4.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(17.0F, -76.6725F, 77.0F, 0.0F, -0.7854F, 0.0F));

		PartDefinition cube_r16 = bottom.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(452, 518).addBox(49.627F, 57.6725F, 22.5143F, 4.0F, 4.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(198, 469).addBox(-38.0312F, 57.6725F, -37.9624F, 4.0F, 4.0F, 24.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(17.0F, -76.6725F, 77.0F, -3.1416F, -0.7854F, 3.1416F));

		PartDefinition cube_r17 = bottom.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(508, 366).addBox(-29.8776F, 57.6725F, -27.4431F, 4.0F, 4.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(17.0F, -76.6725F, 77.0F, -3.1416F, -1.1781F, 3.1416F));

		PartDefinition left = partdefinition.addOrReplaceChild("left", CubeListBuilder.create(), PartPose.offset(-23.0F, 33.0F, 2.0F));

		PartDefinition cube_r18 = left.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(390, 451).addBox(24.0F, 52.6725F, -9.16F, 1.0F, 2.0F, 31.0F, new CubeDeformation(0.0F))
		.texOffs(526, 417).addBox(24.0F, 52.6725F, 21.84F, 1.0F, 2.0F, 9.0F, new CubeDeformation(0.0F))
		.texOffs(92, 417).addBox(21.0F, 50.6725F, -9.16F, 3.0F, 7.0F, 31.0F, new CubeDeformation(0.0F))
		.texOffs(442, 198).addBox(21.0F, 47.6725F, -9.16F, 4.0F, 3.0F, 31.0F, new CubeDeformation(0.0F))
		.texOffs(52, 515).addBox(21.0F, 45.6725F, 21.84F, 3.0F, 12.0F, 9.0F, new CubeDeformation(0.0F))
		.texOffs(206, 517).addBox(21.0F, 43.6725F, 20.84F, 4.0F, 2.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(132, 523).addBox(20.5F, 41.6725F, 30.84F, 4.0F, 16.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(0, 487).addBox(21.0F, 43.6725F, -27.16F, 4.0F, 2.0F, 19.0F, new CubeDeformation(0.0F))
		.texOffs(100, 486).addBox(21.0F, 45.6725F, -27.16F, 3.0F, 12.0F, 18.0F, new CubeDeformation(0.0F))
		.texOffs(266, 497).addBox(24.0F, 52.6725F, -27.16F, 1.0F, 2.0F, 18.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(23.0F, -95.6725F, -2.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition cube_r19 = left.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(26, 524).addBox(20.5F, -21.3258F, 50.9874F, 4.0F, 11.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(23.0F, -95.6725F, -2.0F, 0.0F, -1.5708F, -1.1257F));

		PartDefinition back = partdefinition.addOrReplaceChild("back", CubeListBuilder.create(), PartPose.offset(-22.3F, 12.3F, 16.0F));

		PartDefinition cube_r20 = back.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(120, 389).addBox(-12.0F, 15.6725F, 50.84F, 24.0F, 0.0F, 28.0F, new CubeDeformation(0.0F))
		.texOffs(38, 508).addBox(20.5F, 22.6725F, 51.84F, 4.0F, 35.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(316, 227).addBox(-20.5F, 22.6725F, 72.84F, 41.0F, 3.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(366, 484).addBox(20.5F, 22.6725F, 54.84F, 4.0F, 3.0F, 22.0F, new CubeDeformation(0.0F))
		.texOffs(0, 508).addBox(20.0F, 37.0225F, 40.84F, 5.0F, 2.0F, 14.0F, new CubeDeformation(0.0F))
		.texOffs(454, 481).addBox(21.0F, 40.6725F, 33.84F, 3.0F, 17.0F, 20.0F, new CubeDeformation(0.0F))
		.texOffs(504, 348).addBox(21.0F, 38.6725F, 37.84F, 3.0F, 2.0F, 16.0F, new CubeDeformation(0.0F))
		.texOffs(480, 445).addBox(-12.0F, 15.6725F, 51.84F, 24.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(480, 439).addBox(-12.0F, 15.6725F, 62.84F, 24.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(480, 232).addBox(-12.0F, 15.6725F, 74.84F, 24.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(482, 169).addBox(-24.5F, 22.6725F, 54.84F, 4.0F, 3.0F, 22.0F, new CubeDeformation(0.0F))
		.texOffs(210, 344).addBox(-24.5F, 22.6725F, 51.84F, 4.0F, 35.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(482, 142).addBox(24.0F, 52.6725F, 30.84F, 1.0F, 2.0F, 25.0F, new CubeDeformation(0.0F))
		.texOffs(482, 115).addBox(-25.0F, 52.6725F, 30.84F, 1.0F, 2.0F, 25.0F, new CubeDeformation(0.0F))
		.texOffs(142, 507).addBox(-25.0F, 37.0225F, 40.84F, 5.0F, 2.0F, 14.0F, new CubeDeformation(0.0F))
		.texOffs(504, 330).addBox(-24.0F, 38.6725F, 37.84F, 3.0F, 2.0F, 16.0F, new CubeDeformation(0.0F))
		.texOffs(480, 402).addBox(-24.0F, 40.6725F, 33.84F, 3.0F, 17.0F, 20.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(22.3F, -74.9725F, -16.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition cube_r21 = back.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(206, 529).addBox(-37.7951F, -1.2278F, 74.84F, 9.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(328, 413).addBox(-42.7951F, -1.2278F, 50.84F, 14.0F, 0.0F, 28.0F, new CubeDeformation(0.0F))
		.texOffs(294, 528).addBox(-37.7951F, -1.2278F, 62.84F, 9.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(366, 475).addBox(-37.7951F, -1.2278F, 51.84F, 9.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(22.3F, -74.9725F, -16.0F, 1.5708F, -0.7854F, -1.5708F));

		PartDefinition cube_r22 = back.addOrReplaceChild("cube_r22", CubeListBuilder.create().texOffs(316, 234).addBox(-21.0F, 24.0278F, 79.4958F, 38.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(234, 517).addBox(16.5F, -6.8765F, 75.2648F, 3.0F, 25.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(58, 457).addBox(-19.5F, -6.8765F, 75.2648F, 3.0F, 25.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(0, 404).addBox(-20.0F, 11.0278F, 74.4958F, 40.0F, 19.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(22.3F, -74.9725F, -16.0F, 0.0F, -1.5708F, -0.3927F));

		PartDefinition cube_r23 = back.addOrReplaceChild("cube_r23", CubeListBuilder.create().texOffs(512, 218).addBox(39.0889F, 36.9725F, 41.1317F, 5.0F, 2.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(270, 517).addBox(43.0833F, 52.6725F, 41.0985F, 1.0F, 2.0F, 11.0F, new CubeDeformation(0.0F))
		.texOffs(244, 375).addBox(39.891F, 38.6725F, 40.1313F, 3.0F, 19.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(22.3F, -74.9725F, -16.0F, -3.1416F, -1.1781F, 3.1416F));

		PartDefinition cube_r24 = back.addOrReplaceChild("cube_r24", CubeListBuilder.create().texOffs(146, 529).addBox(28.7951F, -1.2278F, 51.84F, 9.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(244, 413).addBox(28.7951F, -1.2278F, 50.84F, 14.0F, 0.0F, 28.0F, new CubeDeformation(0.0F))
		.texOffs(526, 428).addBox(28.7951F, -1.2278F, 62.84F, 9.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(220, 446).addBox(28.7951F, -1.2278F, 74.84F, 9.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(22.3F, -74.9725F, -16.0F, -1.5708F, -0.7854F, 1.5708F));

		PartDefinition cube_r25 = back.addOrReplaceChild("cube_r25", CubeListBuilder.create().texOffs(526, 400).addBox(17.0842F, 9.8873F, 51.84F, 10.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(0, 429).addBox(17.0842F, 9.8873F, 50.84F, 10.0F, 0.0F, 28.0F, new CubeDeformation(0.0F))
		.texOffs(0, 524).addBox(17.0842F, 9.8873F, 62.84F, 10.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(244, 406).addBox(17.0842F, 9.8873F, 74.84F, 10.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(22.3F, -74.9725F, -16.0F, -1.5708F, -1.1781F, 1.5708F));

		PartDefinition cube_r26 = back.addOrReplaceChild("cube_r26", CubeListBuilder.create().texOffs(386, 526).addBox(-27.0842F, 9.8873F, 51.84F, 10.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(244, 441).addBox(-27.0842F, 9.8873F, 50.84F, 10.0F, 0.0F, 28.0F, new CubeDeformation(0.0F))
		.texOffs(360, 524).addBox(-27.0842F, 9.8873F, 62.84F, 10.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(146, 523).addBox(-27.0842F, 9.8873F, 74.84F, 10.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(22.3F, -74.9725F, -16.0F, 1.5708F, -1.1781F, -1.5708F));

		PartDefinition cube_r27 = back.addOrReplaceChild("cube_r27", CubeListBuilder.create().texOffs(246, 517).addBox(-44.0833F, 52.6725F, 41.0985F, 1.0F, 2.0F, 11.0F, new CubeDeformation(0.0F))
		.texOffs(244, 344).addBox(-42.891F, 38.6725F, 40.1313F, 3.0F, 19.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(512, 204).addBox(-44.0889F, 36.9725F, 41.1317F, 5.0F, 2.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(22.3F, -74.9725F, -16.0F, 0.0F, -1.1781F, 0.0F));

        */

		PartDefinition front = partdefinition.addOrReplaceChild("front", CubeListBuilder.create(), PartPose.offset(-25.0F, 10.0F, -66.0F));

		PartDefinition cube_r1 = front.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(794, 222).addBox(-1.5F, 66.2296F, -61.2824F, 3.0F, 2.0F, 13.0F, new CubeDeformation(0.0F))
		.texOffs(210, 382).addBox(-2.0F, 65.6916F, -49.0911F, 4.0F, 5.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(800, 208).addBox(-1.5F, 66.2296F, -48.2824F, 3.0F, 4.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(25.0F, -72.6725F, 66.0F, 0.0F, -1.5708F, -0.3927F));

		PartDefinition cube_r2 = front.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(876, 198).addBox(-1.5F, 67.6155F, 19.5363F, 3.0F, 12.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(25.0F, -72.6725F, 66.0F, 0.0F, -1.5708F, -1.1781F));

		PartDefinition cube_r3 = front.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(907, 49).addBox(-40.8597F, 45.6735F, 13.5482F, 3.0F, 12.0F, 27.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(25.7F, -72.6725F, 65.75F, 3.1416F, 0.7854F, 3.1416F));

		PartDefinition cube_r4 = front.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(880, 87).addBox(-32.4907F, 45.6725F, 15.5255F, 3.0F, 12.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(25.4F, -72.6725F, 65.95F, 3.1416F, 1.1781F, 3.1416F));

		PartDefinition cube_r5 = front.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(827, 261).addBox(-33.4907F, 52.6725F, 15.5255F, 1.0F, 2.0F, 13.0F, new CubeDeformation(0.0F))
		.texOffs(773, 213).addBox(-33.4907F, 43.6725F, 15.5255F, 4.0F, 2.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(25.0F, -72.6725F, 66.0F, 3.1416F, 1.1781F, 3.1416F));

		PartDefinition cube_r6 = front.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(826, 215).addBox(-41.8597F, 52.6725F, 13.5482F, 1.0F, 2.0F, 28.0F, new CubeDeformation(0.0F))
		.texOffs(772, 201).addBox(-41.8597F, 43.6725F, 13.5482F, 4.0F, 2.0F, 28.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(25.0F, -72.6725F, 66.0F, 3.1416F, 0.7854F, 3.1416F));

		PartDefinition cube_r7 = front.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(826, 215).addBox(-41.8597F, 52.6725F, -41.5482F, 1.0F, 2.0F, 28.0F, new CubeDeformation(0.0F))
		.texOffs(772, 201).addBox(-41.8597F, 43.6715F, -41.5482F, 4.0F, 2.0F, 28.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(25.0F, -72.6725F, 66.0F, -3.1416F, -0.7854F, 3.1416F));

		PartDefinition cube_r8 = front.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(907, 49).addBox(-40.8597F, 45.6735F, -40.5482F, 3.0F, 12.0F, 27.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(25.7F, -72.6725F, 66.25F, -3.1416F, -0.7854F, 3.1416F));

		PartDefinition cube_r9 = front.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(773, 213).addBox(-33.4907F, 43.6725F, -28.5255F, 4.0F, 2.0F, 13.0F, new CubeDeformation(0.0F))
		.texOffs(827, 261).addBox(-33.4907F, 52.6725F, -28.5255F, 1.0F, 2.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(25.0F, -72.6725F, 66.0F, -3.1416F, -1.1781F, 3.1416F));

		PartDefinition cube_r10 = front.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(880, 87).addBox(-32.4907F, 45.6725F, -28.5255F, 3.0F, 12.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(25.4F, -72.6725F, 66.05F, -3.1416F, -1.1781F, 3.1416F));

		PartDefinition anchor = front.addOrReplaceChild("anchor", CubeListBuilder.create(), PartPose.offset(57.9739F, -18.1429F, 89.0506F));

		PartDefinition cube_r11 = anchor.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(917, 220).addBox(-33.4907F, 54.6725F, 20.5255F, 1.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-32.9739F, -62.5297F, -22.0506F, 3.1416F, 1.1781F, 3.1416F));

		PartDefinition cube_r12 = anchor.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(926, 201).addBox(-33.4907F, 52.6725F, 15.5255F, 1.0F, 2.0F, 13.0F, new CubeDeformation(0.0F))
		.texOffs(916, 208).addBox(-33.4907F, 61.6725F, 23.5255F, 1.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(931, 208).addBox(-33.4907F, 54.6725F, 20.5255F, 1.0F, 10.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-32.9739F, -58.5297F, -22.0506F, 3.1416F, 1.1781F, 3.1416F));

		PartDefinition cube_r13 = anchor.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(915, 211).addBox(-33.4907F, 59.6725F, 26.5255F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(950, 195).addBox(-33.4907F, 59.6725F, 15.5255F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(930, 213).addBox(-33.4907F, 63.6725F, 17.5255F, 1.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-32.9739F, -60.5297F, -22.0506F, 3.1416F, 1.1781F, 3.1416F));

		PartDefinition right = partdefinition.addOrReplaceChild("right", CubeListBuilder.create(), PartPose.offset(23.0F, 33.0F, 2.0F));

		PartDefinition cube_r14 = right.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(786, 214).addBox(-25.0F, 52.6725F, -9.16F, 1.0F, 2.0F, 31.0F, new CubeDeformation(0.0F))
		.texOffs(850, 102).addBox(-24.0F, 50.6725F, -9.16F, 3.0F, 7.0F, 31.0F, new CubeDeformation(0.0F))
		.texOffs(773, 220).addBox(-25.0F, 47.6725F, -9.16F, 4.0F, 3.0F, 31.0F, new CubeDeformation(0.0F))
		.texOffs(776, 214).addBox(-24.5F, 41.6725F, 30.8401F, 4.0F, 16.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(771, 206).addBox(-25.0F, 52.6725F, 21.84F, 1.0F, 2.0F, 9.0F, new CubeDeformation(0.0F))
		.texOffs(889, 151).addBox(-24.0F, 45.6725F, 21.84F, 3.0F, 12.0F, 9.0F, new CubeDeformation(0.0F))
		.texOffs(772, 203).addBox(-25.0F, 43.6725F, 20.84F, 4.0F, 2.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(805, 240).addBox(-25.0F, 43.6725F, -27.16F, 4.0F, 2.0F, 19.0F, new CubeDeformation(0.0F))
		.texOffs(797, 222).addBox(-25.0F, 52.6725F, -27.16F, 1.0F, 2.0F, 18.0F, new CubeDeformation(0.0F))
		.texOffs(878, 82).addBox(-24.0F, 45.6735F, -27.16F, 3.0F, 12.0F, 18.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-23.0F, -95.6725F, -2.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition cube_r15 = right.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(302, 521).addBox(-24.5F, -21.3257F, 50.9874F, 4.0F, 11.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-23.0F, -95.6725F, -2.0F, 0.0F, -1.5708F, -1.1257F));

		PartDefinition bottom = partdefinition.addOrReplaceChild("bottom", CubeListBuilder.create(), PartPose.offset(-17.0F, 14.0F, -77.0F));

		PartDefinition saill_r1 = bottom.addOrReplaceChild("saill_r1", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, -43.5542F, -80.6929F, 0.0F, 82.0F, 158.0F, new CubeDeformation(0.0F))
		.texOffs(768, 192).addBox(-1.5F, -46.5542F, 35.3072F, 3.0F, 3.0F, 42.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(17.0F, -76.6725F, 77.0F, 0.0F, -1.5708F, 0.3927F));

		PartDefinition cube_r16 = bottom.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(780, 112).addBox(-8.0F, 41.6725F, 35.84F, 16.0F, 16.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(785, 232).addBox(-9.0F, 39.6725F, 34.84F, 18.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(768, 192).addBox(-1.5F, 11.1725F, 15.34F, 3.0F, 3.0F, 57.0F, new CubeDeformation(0.0F))
		.texOffs(788, 202).addBox(-2.5F, -84.8275F, 10.34F, 5.0F, 17.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(788, 202).addBox(-2.5F, -67.8275F, 10.34F, 5.0F, 97.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(799, 142).addBox(-7.5F, -67.8275F, 18.34F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(786, 129).addBox(-7.5F, -63.8275F, 5.34F, 15.0F, 4.0F, 15.0F, new CubeDeformation(0.0F))
		.texOffs(797, 131).addBox(-3.5F, 28.1725F, 9.34F, 7.0F, 4.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(788, 215).addBox(-3.0F, 29.1725F, 9.84F, 6.0F, 29.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(809, 70).addBox(-21.0F, 49.4225F, 39.84F, 42.0F, 8.0F, 21.0F, new CubeDeformation(0.0F))
		.texOffs(813, 113).addBox(8.0F, 53.4225F, 33.84F, 13.0F, 4.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(778, 67).addBox(-21.0F, 53.4225F, -26.16F, 4.0F, 4.0F, 60.0F, new CubeDeformation(0.0F))
		.texOffs(832, 121).addBox(-21.0F, 53.4225F, 33.84F, 13.0F, 4.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(833, 62).addBox(-18.0F, 57.4225F, -42.16F, 12.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(835, 54).addBox(-6.0F, 57.4225F, -52.16F, 12.0F, 1.0F, 18.0F, new CubeDeformation(0.0F))
		.texOffs(768, 0).addBox(-21.0F, 57.4225F, -34.16F, 42.0F, 1.0F, 70.0F, new CubeDeformation(0.0F))
		.texOffs(819, 215).addBox(-1.5F, 56.6725F, 58.84F, 3.0F, 8.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(776, 200).addBox(-1.5F, 61.6725F, -53.16F, 3.0F, 3.0F, 54.0F, new CubeDeformation(0.0F))
		.texOffs(770, 205).addBox(-1.5F, 61.6725F, 0.84F, 3.0F, 3.0F, 58.0F, new CubeDeformation(0.0F))
		.texOffs(838, 135).addBox(-16.5F, 57.6725F, 55.59F, 33.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(771, 51).addBox(-18.0F, 60.6725F, -31.16F, 36.0F, 1.0F, 87.0F, new CubeDeformation(0.0F))
		.texOffs(823, 61).addBox(-22.0F, 57.6725F, -25.16F, 4.0F, 4.0F, 79.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(17.0F, -76.6725F, 77.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition cube_r17 = bottom.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(11, 156).addBox(-0.5F, 12.1725F, 15.34F, 0.0F, 17.0F, 29.0F, new CubeDeformation(0.0F))
		.texOffs(788, 37).addBox(-2.5F, 12.1725F, 11.34F, 4.0F, 17.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(17.5F, -190.6725F, 77.75F, 0.0F, -1.5708F, 0.0F));

		PartDefinition cube_r18 = bottom.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(799, 142).addBox(-7.5F, 28.1725F, 18.34F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(30.0F, -172.6725F, 77.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition cube_r19 = bottom.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(799, 142).addBox(-7.5F, 28.1725F, 18.34F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(26.0F, -172.6725F, 77.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition cube_r20 = bottom.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(799, 142).addBox(-7.5F, 28.1725F, 18.34F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(21.0F, -172.6725F, 77.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition cube_r21 = bottom.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(777, 67).addBox(-22.0F, 53.4225F, -26.16F, 4.0F, 4.0F, 60.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(17.0F, -76.6725F, 116.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition cube_r22 = bottom.addOrReplaceChild("cube_r22", CubeListBuilder.create().texOffs(832, 74).addBox(-18.0F, 57.4225F, -44.16F, 4.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(17.0F, -76.6725F, 81.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition cube_r23 = bottom.addOrReplaceChild("cube_r23", CubeListBuilder.create().texOffs(832, 74).addBox(-18.0F, 57.4225F, -44.16F, 4.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(17.0F, -76.6725F, 105.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition cube_r24 = bottom.addOrReplaceChild("cube_r24", CubeListBuilder.create().texOffs(828, 70).addBox(-18.0F, 57.4225F, -48.16F, 4.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(17.0F, -76.6725F, 85.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition cube_r25 = bottom.addOrReplaceChild("cube_r25", CubeListBuilder.create().texOffs(828, 70).addBox(-18.0F, 57.4225F, -48.16F, 4.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(825, 62).addBox(-18.0F, 57.4225F, -42.16F, 12.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(17.0F, -76.6725F, 101.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition cube_r26 = bottom.addOrReplaceChild("cube_r26", CubeListBuilder.create().texOffs(781, 210).addBox(-57.0263F, 25.9687F, -54.84F, 4.0F, 5.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(774, 205).addBox(-57.0263F, 25.9687F, -33.84F, 4.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(17.0F, -76.6725F, 77.0F, -1.5708F, 0.829F, -1.5708F));

		PartDefinition cube_r27 = bottom.addOrReplaceChild("cube_r27", CubeListBuilder.create().texOffs(781, 210).addBox(-57.0263F, 25.9687F, 51.84F, 4.0F, 5.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(774, 205).addBox(-57.0263F, 25.9687F, 30.84F, 4.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(17.0F, -76.6725F, 77.0F, 1.5708F, -0.829F, -1.5708F));

		PartDefinition cube_r28 = bottom.addOrReplaceChild("cube_r28", CubeListBuilder.create().texOffs(770, 208).addBox(-1.5F, 71.3202F, -6.8594F, 3.0F, 12.0F, 15.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(17.0F, -76.6725F, 77.0F, 0.0F, -1.5708F, -0.7854F));

		PartDefinition cube_r29 = bottom.addOrReplaceChild("cube_r29", CubeListBuilder.create().texOffs(791, 205).addBox(-1.5F, 72.0931F, -31.3643F, 3.0F, 8.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(17.0F, -76.6725F, 77.0F, 0.0F, -1.5708F, -0.3927F));

		PartDefinition cube_r30 = bottom.addOrReplaceChild("cube_r30", CubeListBuilder.create().texOffs(856, 72).addBox(-53.627F, 57.6735F, -30.5143F, 4.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(17.0F, -76.6725F, 77.0F, 0.0F, 0.7854F, 0.0F));

		PartDefinition cube_r31 = bottom.addOrReplaceChild("cube_r31", CubeListBuilder.create().texOffs(856, 72).addBox(-53.627F, 57.6735F, 22.5143F, 4.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(17.0F, -76.6725F, 77.0F, 0.0F, -0.7854F, 0.0F));

		PartDefinition cube_r32 = bottom.addOrReplaceChild("cube_r32", CubeListBuilder.create().texOffs(823, 61).addBox(-22.0F, 57.6725F, -53.84F, 4.0F, 4.0F, 79.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(17.0F, -76.6725F, 77.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition cube_r33 = bottom.addOrReplaceChild("cube_r33", CubeListBuilder.create().texOffs(873, 115).addBox(-29.8775F, 57.6735F, 14.4431F, 4.0F, 4.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(17.4F, -76.6725F, 76.95F, 3.1416F, 1.1781F, 3.1416F));

		PartDefinition cube_r34 = bottom.addOrReplaceChild("cube_r34", CubeListBuilder.create().texOffs(804, 79).addBox(-38.0312F, 57.6725F, 13.9624F, 4.0F, 4.0F, 24.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(17.4F, -76.6725F, 77.05F, 3.1416F, 0.7854F, 3.1416F));

		PartDefinition cube_r35 = bottom.addOrReplaceChild("cube_r35", CubeListBuilder.create().texOffs(873, 115).addBox(-29.8775F, 57.6735F, -27.4431F, 4.0F, 4.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(17.4F, -76.6725F, 77.05F, -3.1416F, -1.1781F, 3.1416F));

		PartDefinition cube_r36 = bottom.addOrReplaceChild("cube_r36", CubeListBuilder.create().texOffs(804, 79).addBox(-38.0312F, 57.6725F, -37.9624F, 4.0F, 4.0F, 24.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(17.4F, -76.6725F, 76.95F, -3.1416F, -0.7854F, 3.1416F));

		PartDefinition sail_full = bottom.addOrReplaceChild("sail_full", CubeListBuilder.create(), PartPose.offset(6.3908F, -97.9179F, 77.5F));

		PartDefinition top_beam_r1 = sail_full.addOrReplaceChild("top_beam_r1", CubeListBuilder.create().texOffs(826, 78).addBox(-39.5F, -54.8275F, 11.34F, 80.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(11.1092F, 21.2453F, -0.5F, 0.0F, -1.5708F, 0.0F));

		PartDefinition sail_4 = sail_full.addOrReplaceChild("sail_4", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r37 = sail_4.addOrReplaceChild("cube_r37", CubeListBuilder.create().texOffs(794, 375).addBox(-39.5F, -0.0316F, -20.9812F, 80.0F, 1.0F, 21.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(11.0092F, 16.6953F, -0.5F, 0.0F, -1.5708F, 2.0944F));

		PartDefinition cube_r38 = sail_4.addOrReplaceChild("cube_r38", CubeListBuilder.create().texOffs(768, 320).addBox(-39.5F, -0.0068F, -17.0303F, 80.0F, 1.0F, 17.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(13.2592F, -0.2047F, -0.5F, 0.0F, -1.5708F, 1.7017F));

		PartDefinition cube_r39 = sail_4.addOrReplaceChild("cube_r39", CubeListBuilder.create().texOffs(768, 320).addBox(-39.5F, -0.0068F, -18.0303F, 80.0F, 1.0F, 18.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(13.2592F, -18.2047F, -0.5F, 0.0F, -1.5708F, 1.5708F));

		PartDefinition cube_r40 = sail_4.addOrReplaceChild("cube_r40", CubeListBuilder.create().texOffs(769, 320).addBox(-39.5F, -0.0775F, -18.91F, 80.0F, 1.0F, 19.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.1408F, -31.5047F, -0.5F, 0.0F, -1.5708F, 0.7854F));

		PartDefinition sail_3 = sail_full.addOrReplaceChild("sail_3", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r41 = sail_3.addOrReplaceChild("cube_r41", CubeListBuilder.create().texOffs(812, 393).addBox(-39.5F, -1.0316F, -2.9812F, 80.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(12.0092F, 8.6953F, -0.5F, 0.0F, -1.5708F, 2.0944F));

		PartDefinition cube_r42 = sail_3.addOrReplaceChild("cube_r42", CubeListBuilder.create().texOffs(768, 320).addBox(-39.5F, -0.0068F, -10.0303F, 80.0F, 1.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(13.2592F, -0.2047F, -0.5F, 0.0F, -1.5708F, 1.7017F));

		PartDefinition cube_r43 = sail_3.addOrReplaceChild("cube_r43", CubeListBuilder.create().texOffs(768, 320).addBox(-39.5F, -0.0068F, -18.0303F, 80.0F, 1.0F, 18.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(13.2592F, -18.2047F, -0.5F, 0.0F, -1.5708F, 1.5708F));

		PartDefinition cube_r44 = sail_3.addOrReplaceChild("cube_r44", CubeListBuilder.create().texOffs(769, 321).addBox(-39.5F, -0.0775F, -18.91F, 80.0F, 1.0F, 19.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.1408F, -31.5047F, -0.5F, 0.0F, -1.5708F, 0.7854F));

		PartDefinition sail_2 = sail_full.addOrReplaceChild("sail_2", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r45 = sail_2.addOrReplaceChild("cube_r45", CubeListBuilder.create().texOffs(811, 392).addBox(-39.5F, -2.0068F, -4.0303F, 80.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(12.7592F, -15.2047F, -0.5F, 0.0F, -1.5708F, 1.7017F));

		PartDefinition cube_r46 = sail_2.addOrReplaceChild("cube_r46", CubeListBuilder.create().texOffs(783, 335).addBox(-39.5F, -0.0068F, -3.0303F, 80.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(13.2592F, -18.2047F, -0.5F, 0.0F, -1.5708F, 1.5708F));

		PartDefinition cube_r47 = sail_2.addOrReplaceChild("cube_r47", CubeListBuilder.create().texOffs(768, 320).addBox(-39.5F, -0.0775F, -18.91F, 80.0F, 1.0F, 19.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.1408F, -31.5047F, -0.5F, 0.0F, -1.5708F, 0.7854F));

		PartDefinition sail_1 = sail_full.addOrReplaceChild("sail_1", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r48 = sail_1.addOrReplaceChild("cube_r48", CubeListBuilder.create().texOffs(810, 391).addBox(-39.5F, -2.0775F, -4.91F, 80.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.1408F, -31.5047F, -0.5F, 0.0F, -1.5708F, 0.7854F));

		PartDefinition rudder = bottom.addOrReplaceChild("rudder", CubeListBuilder.create(), PartPose.offset(-45.9233F, -15.3333F, 77.0F));

		PartDefinition cube_r49 = rudder.addOrReplaceChild("cube_r49", CubeListBuilder.create().texOffs(826, 39).addBox(-1.5F, 59.6725F, 56.84F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(826, 39).addBox(-1.5F, 62.6725F, 56.84F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(56.9233F, -61.3392F, 0.5F, 0.0F, -1.5708F, 0.0F));

		PartDefinition cube_r50 = rudder.addOrReplaceChild("cube_r50", CubeListBuilder.create().texOffs(820, 215).addBox(-0.5F, 56.6725F, 58.84F, 1.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(56.9233F, -61.3392F, 0.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition back = partdefinition.addOrReplaceChild("back", CubeListBuilder.create(), PartPose.offset(-22.3F, 12.3F, 16.0F));

		PartDefinition cube_r51 = back.addOrReplaceChild("cube_r51", CubeListBuilder.create().texOffs(120, 389).addBox(-12.0F, 15.6725F, 50.84F, 24.0F, 0.0F, 28.0F, new CubeDeformation(0.0F))
		.texOffs(785, 216).addBox(-20.5F, 22.9225F, 72.84F, 41.0F, 3.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(773, 201).addBox(-24.5F, 22.9225F, 54.84F, 4.0F, 3.0F, 22.0F, new CubeDeformation(0.0F))
		.texOffs(779, 200).addBox(-24.5F, 22.9225F, 51.84F, 4.0F, 35.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(770, 198).addBox(-25.0F, 52.9225F, 30.84F, 1.0F, 2.0F, 24.0F, new CubeDeformation(0.0F))
		.texOffs(778, 204).addBox(-25.0F, 37.2725F, 40.84F, 5.0F, 2.0F, 14.0F, new CubeDeformation(0.0F))
		.texOffs(863, 126).addBox(-24.0F, 38.9225F, 37.84F, 3.0F, 2.0F, 16.0F, new CubeDeformation(0.0F))
		.texOffs(856, 112).addBox(-24.0F, 40.9225F, 33.84F, 3.0F, 17.0F, 20.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(22.3F, -75.2225F, -16.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition cube_r52 = back.addOrReplaceChild("cube_r52", CubeListBuilder.create().texOffs(774, 233).addBox(-22.0F, 24.0278F, 80.4958F, 40.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(22.3F, -74.9725F, -14.0F, 0.0F, -1.5708F, -0.3927F));

		PartDefinition cube_r53 = back.addOrReplaceChild("cube_r53", CubeListBuilder.create().texOffs(789, 232).addBox(16.5F, -6.8765F, 75.2648F, 3.0F, 25.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(770, 203).addBox(-19.5F, -6.8765F, 75.2648F, 3.0F, 25.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(782, 7).addBox(-20.0F, 11.0278F, 74.4958F, 40.0F, 19.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(22.3F, -74.9725F, -16.0F, 0.0F, -1.5708F, -0.3927F));

		PartDefinition cube_r54 = back.addOrReplaceChild("cube_r54", CubeListBuilder.create().texOffs(802, 6).addBox(-2.0F, -10.5F, -5.5F, 4.0F, 20.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-44.5225F, -23.2462F, -16.0F, 0.0F, -1.5708F, -0.7854F));

		PartDefinition cube_r55 = back.addOrReplaceChild("cube_r55", CubeListBuilder.create().texOffs(903, 105).addBox(-37.7951F, -1.2278F, 51.84F, 9.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(11.3F, -74.9725F, -16.0F, 1.5708F, -0.7854F, -1.5708F));

		PartDefinition cube_r56 = back.addOrReplaceChild("cube_r56", CubeListBuilder.create().texOffs(783, 0).addBox(-27.0842F, 9.8873F, 51.84F, 10.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(11.3F, -74.9725F, -16.0F, 1.5708F, -1.1781F, -1.5708F));

		PartDefinition cube_r57 = back.addOrReplaceChild("cube_r57", CubeListBuilder.create().texOffs(868, 0).addBox(-12.0F, 15.6725F, 51.84F, 24.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(11.3F, -74.9725F, -16.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition cube_r58 = back.addOrReplaceChild("cube_r58", CubeListBuilder.create().texOffs(917, 0).addBox(17.0842F, 9.8873F, 51.84F, 10.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(11.3F, -74.9725F, -16.0F, -1.5708F, -1.1781F, 1.5708F));

		PartDefinition cube_r59 = back.addOrReplaceChild("cube_r59", CubeListBuilder.create().texOffs(882, 0).addBox(28.7951F, -1.2278F, 51.84F, 9.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(11.3F, -74.9725F, -16.0F, -1.5708F, -0.7854F, 1.5708F));

		PartDefinition cube_r60 = back.addOrReplaceChild("cube_r60", CubeListBuilder.create().texOffs(903, 105).addBox(-37.7951F, -1.2278F, 51.84F, 9.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.7F, -74.9725F, -16.0F, 1.5708F, -0.7854F, -1.5708F));

		PartDefinition cube_r61 = back.addOrReplaceChild("cube_r61", CubeListBuilder.create().texOffs(783, 0).addBox(-27.0842F, 9.8873F, 51.84F, 10.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.7F, -74.9725F, -16.0F, 1.5708F, -1.1781F, -1.5708F));

		PartDefinition cube_r62 = back.addOrReplaceChild("cube_r62", CubeListBuilder.create().texOffs(868, 0).addBox(-12.0F, 15.6725F, 51.84F, 24.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.7F, -74.9725F, -16.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition cube_r63 = back.addOrReplaceChild("cube_r63", CubeListBuilder.create().texOffs(917, 0).addBox(17.0842F, 9.8873F, 51.84F, 10.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.7F, -74.9725F, -16.0F, -1.5708F, -1.1781F, 1.5708F));

		PartDefinition cube_r64 = back.addOrReplaceChild("cube_r64", CubeListBuilder.create().texOffs(882, 0).addBox(28.7951F, -1.2278F, 51.84F, 9.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.7F, -74.9725F, -16.0F, -1.5708F, -0.7854F, 1.5708F));

		PartDefinition cube_r65 = back.addOrReplaceChild("cube_r65", CubeListBuilder.create().texOffs(882, 0).addBox(28.7951F, -1.2278F, 51.84F, 9.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(22.55F, -74.9725F, -16.0F, -1.5708F, -0.7854F, 1.5708F));

		PartDefinition cube_r66 = back.addOrReplaceChild("cube_r66", CubeListBuilder.create().texOffs(917, 0).addBox(17.0842F, 9.8873F, 51.84F, 10.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(22.55F, -74.9725F, -16.0F, -1.5708F, -1.1781F, 1.5708F));

		PartDefinition cube_r67 = back.addOrReplaceChild("cube_r67", CubeListBuilder.create().texOffs(868, 0).addBox(-12.0F, 15.6725F, 51.84F, 24.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(22.55F, -74.9725F, -16.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition cube_r68 = back.addOrReplaceChild("cube_r68", CubeListBuilder.create().texOffs(783, 0).addBox(-27.0842F, 9.8873F, 51.84F, 10.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(22.55F, -74.9725F, -16.0F, 1.5708F, -1.1781F, -1.5708F));

		PartDefinition cube_r69 = back.addOrReplaceChild("cube_r69", CubeListBuilder.create().texOffs(442, 198).addBox(-27.0842F, 9.8873F, 50.84F, 10.0F, 0.0F, 28.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(22.3F, -75.2225F, -16.0F, 1.5708F, -1.1781F, -1.5708F));

		PartDefinition cube_r70 = back.addOrReplaceChild("cube_r70", CubeListBuilder.create().texOffs(328, 413).addBox(-42.7951F, -1.2278F, 50.84F, 14.0F, 0.0F, 28.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(22.3F, -75.2225F, -16.0F, 1.5708F, -0.7854F, -1.5708F));

		PartDefinition cube_r71 = back.addOrReplaceChild("cube_r71", CubeListBuilder.create().texOffs(314, 441).addBox(17.0842F, 9.8873F, 50.84F, 10.0F, 0.0F, 28.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(22.3F, -75.2225F, -16.0F, -1.5708F, -1.1781F, 1.5708F));

		PartDefinition cube_r72 = back.addOrReplaceChild("cube_r72", CubeListBuilder.create().texOffs(244, 413).addBox(28.7951F, -1.2278F, 50.84F, 14.0F, 0.0F, 28.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(22.3F, -75.2225F, -16.0F, -1.5708F, -0.7854F, 1.5708F));

		PartDefinition cube_r73 = back.addOrReplaceChild("cube_r73", CubeListBuilder.create().texOffs(903, 105).addBox(-37.7951F, -1.2278F, 51.84F, 9.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(22.55F, -74.9725F, -16.0F, 1.5708F, -0.7854F, -1.5708F));

		PartDefinition cube_r74 = back.addOrReplaceChild("cube_r74", CubeListBuilder.create().texOffs(773, 201).addBox(-24.5F, 22.6725F, -76.84F, 4.0F, 3.0F, 22.0F, new CubeDeformation(0.0F))
		.texOffs(779, 200).addBox(-24.5F, 22.6725F, -54.84F, 4.0F, 35.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(856, 112).addBox(-24.0F, 40.6725F, -53.84F, 3.0F, 17.0F, 20.0F, new CubeDeformation(0.0F))
		.texOffs(770, 198).addBox(-25.0F, 52.6725F, -54.84F, 1.0F, 2.0F, 24.0F, new CubeDeformation(0.0F))
		.texOffs(778, 204).addBox(-25.0F, 37.0225F, -54.84F, 5.0F, 2.0F, 14.0F, new CubeDeformation(0.0F))
		.texOffs(863, 126).addBox(-24.0F, 38.6725F, -53.84F, 3.0F, 2.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(22.3F, -74.9725F, -16.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition cube_r75 = back.addOrReplaceChild("cube_r75", CubeListBuilder.create().texOffs(785, 213).addBox(-2.0F, -3.0F, -10.0F, 4.0F, 3.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-54.54F, -49.3F, 2.5F, 0.0F, 1.5708F, 0.3927F));

		PartDefinition cube_r76 = back.addOrReplaceChild("cube_r76", CubeListBuilder.create().texOffs(785, 213).addBox(-2.0F, -3.0F, 0.0F, 4.0F, 3.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-54.54F, -49.3F, -34.5F, 0.0F, -1.5708F, 0.3927F));

		PartDefinition cube_r77 = back.addOrReplaceChild("cube_r77", CubeListBuilder.create().texOffs(787, 202).addBox(-44.0833F, 52.6725F, -54.0984F, 1.0F, 2.0F, 13.0F, new CubeDeformation(0.0F))
		.texOffs(810, 74).addBox(-42.891F, 38.6725F, -52.1313F, 3.0F, 19.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(773, 208).addBox(-44.0889F, 36.9725F, -53.1317F, 5.0F, 2.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(22.3F, -74.9725F, -16.0F, 0.0F, 1.1781F, 0.0F));

		PartDefinition cube_r78 = back.addOrReplaceChild("cube_r78", CubeListBuilder.create().texOffs(787, 202).addBox(-44.0833F, 52.6725F, 41.0984F, 1.0F, 2.0F, 13.0F, new CubeDeformation(0.0F))
		.texOffs(810, 74).addBox(-42.891F, 38.6725F, 40.1313F, 3.0F, 19.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(773, 208).addBox(-44.0889F, 36.9725F, 41.1317F, 5.0F, 2.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(22.3F, -74.9725F, -16.0F, 0.0F, -1.1781F, 0.0F));

		PartDefinition cube_r79 = back.addOrReplaceChild("cube_r79", CubeListBuilder.create().texOffs(789, 214).addBox(-24.0F, 38.0225F, -44.84F, 38.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.7F, -74.9725F, -21.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition left = partdefinition.addOrReplaceChild("left", CubeListBuilder.create(), PartPose.offset(0.0F, -62.6725F, 0.0F));

		PartDefinition cube_r80 = left.addOrReplaceChild("cube_r80", CubeListBuilder.create().texOffs(302, 521).addBox(-24.5F, -21.3257F, -52.9874F, 4.0F, 11.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 1.5708F, -1.1257F));

		PartDefinition cube_r81 = left.addOrReplaceChild("cube_r81", CubeListBuilder.create().texOffs(776, 214).addBox(-24.5F, 41.6725F, -33.8401F, 4.0F, 16.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(771, 206).addBox(-25.0F, 52.6725F, -30.84F, 1.0F, 2.0F, 9.0F, new CubeDeformation(0.0F))
		.texOffs(889, 151).addBox(-24.0F, 45.6725F, -30.84F, 3.0F, 12.0F, 9.0F, new CubeDeformation(0.0F))
		.texOffs(772, 203).addBox(-25.0F, 43.6725F, -30.84F, 4.0F, 2.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(773, 220).addBox(-25.0F, 47.6725F, -21.84F, 4.0F, 3.0F, 31.0F, new CubeDeformation(0.0F))
		.texOffs(850, 102).addBox(-24.0F, 50.6725F, -21.84F, 3.0F, 7.0F, 31.0F, new CubeDeformation(0.0F))
		.texOffs(786, 214).addBox(-25.0F, 52.6725F, -21.84F, 1.0F, 2.0F, 31.0F, new CubeDeformation(0.0F))
		.texOffs(878, 82).addBox(-24.0F, 45.6735F, 9.16F, 3.0F, 12.0F, 18.0F, new CubeDeformation(0.0F))
		.texOffs(797, 222).addBox(-25.0F, 52.6725F, 9.16F, 1.0F, 2.0F, 18.0F, new CubeDeformation(0.0F))
		.texOffs(805, 240).addBox(-25.0F, 43.6725F, 8.16F, 4.0F, 2.0F, 19.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 1.5708F, 0.0F));


		partdefinition.addOrReplaceChild(
			"left_paddle",
			CubeListBuilder.create().texOffs(62, 0).addBox(-1.0F, 0.0F, -5.0F, 2.0F, 2.0F, 18.0F).addBox(-1.001F, -3.0F, 8.0F, 1.0F, 6.0F, 7.0F),
			PartPose.offsetAndRotation(3.0F, -5.0F, 9.0F, 0.0F, 0.0F, (float) (Math.PI / 16))
		);

		partdefinition.addOrReplaceChild(
			"right_paddle",
			CubeListBuilder.create().texOffs(62, 20).addBox(-1.0F, 0.0F, -5.0F, 2.0F, 2.0F, 18.0F).addBox(0.001F, -3.0F, 8.0F, 1.0F, 6.0F, 7.0F),
			PartPose.offsetAndRotation(3.0F, -5.0F, -9.0F, 0.0F, (float) Math.PI, (float) (Math.PI / 16))
		);

		//Water Patch
		partdefinition.addOrReplaceChild("water_patch", CubeListBuilder.create().texOffs(-71, -40).addBox(-32.0F, -27.0F, -21.0F, 71.0F, 4.0F, 42.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

	}

	public static LayerDefinition getTexturedModelData() {
		MeshDefinition modelData = new MeshDefinition();
		PartDefinition modelPartData = modelData.getRoot();
		addParts(modelPartData);
		return LayerDefinition.create(modelData, 1024, 1024);
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

    private static final float SCALE = 1.15f; // 2.0 = double size, 1.5 = +50%, 0.5 = half

    /*@Override
    public void setupAnim(BoatRenderState state) {
        super.setupAnim(state);                 // keep the paddle animation

        this.root.xScale = SCALE;
        this.root.yScale = SCALE;
        this.root.zScale = SCALE;

        // sail furl state — show exactly one sail mesh for the current level
        this.sail_1.visible = (this.sailLevel == 0);   // furled
        this.sail_2.visible = (this.sailLevel == 1);   // 33%
        this.sail_3.visible = (this.sailLevel == 2);   // 66%
        this.sail_4.visible = (this.sailLevel == 3);   // 100%

        // rudder swings with the helm. wrapDegrees guards against the ±360 spike
        // when yaw crosses the wrap point; clamp keeps it to a sane deflection.
        float turn = Mth.wrapDegrees(this.rudderTurn);
        this.rudder.yRot = Mth.clamp(turn * 0.06f, -0.6f, 0.6f);

        // hull attitude: nose-dive while sinking, slight bow-lift under sail
        //float sp = Math.max(-0.6f, Math.min(0.6f, this.forwardSpeed));
        //this.root.zRot = this.sinkProgress * 0.6f - sp * 0.15f;
    }*/

    @Override
    public void setupAnim(BoatRenderState state) {
        super.setupAnim(state);

        this.root.xScale = SCALE;
        this.root.yScale = SCALE;
        this.root.zScale = SCALE;

        // sail furl state
        this.sail_1.visible = (this.sailLevel == 0);
        this.sail_2.visible = (this.sailLevel == 1);
        this.sail_3.visible = (this.sailLevel == 2);
        this.sail_4.visible = (this.sailLevel == 3);

        // rudder swings with the helm
        this.rudder.yRot = this.rudderTurn * -0.6f;

        // --- hull motion: xRot = ROLL, zRot = PITCH on this rotated model ---
        float t = this.waveTime;

        // idle rocking — kept gentle so she breathes rather than wallows
        float rockRoll  = Mth.sin(t * 0.04f)         * 0.012f;  // ~0.7 deg
        float rockPitch = Mth.sin(t * 0.031f + 1.0f) * 0.009f;

        // heel into the turn — just a hint of lean
        float bank = Mth.clamp(this.rudderTurn * 0.07f, -0.07f, 0.07f);

        this.root.xRot = rockRoll + bank;   // roll
        this.root.zRot = rockPitch;   
    }

}
