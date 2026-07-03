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
	private static final String RIGHT_PADDLE = "right_paddle";
	private static final String WATER_PATCH = "water_patch";
	private static final String BOTTOM = "bottom";
	private static final String BACK = "back";
	private static final String FRONT = "front";
	private static final String RIGHT = "right";
	private static final String LEFT = "left";
	private final ModelPart leftPaddle;
	private final ModelPart rightPaddle;
	private final ModelPart waterPatch;
	private final ImmutableList<ModelPart> parts;

    private final ModelPart sail_full;
	/*private final ModelPart sail_4; //Fully Closed
	private final ModelPart sail_3; //1/3
	private final ModelPart sail_2; //2/3
	private final ModelPart sail_1; //3/3*/
    private final ModelPart sail_4;
	private final ModelPart caught_wind_4;
	private final ModelPart normal_4;
	private final ModelPart sail_3;
	private final ModelPart caught_wind_3;
	private final ModelPart normal_3;
	private final ModelPart sail_2;
	private final ModelPart caught_wind_2;
	private final ModelPart normal_2;
	private final ModelPart sail_1;
    private final ModelPart rudder;

    private final ModelPart spanker_2;// Fully Close
    private final ModelPart spanker_1;// When Moving
    private final ModelPart burgee_sail;// For wind direction
    private final ModelPart mid_sail;// For wind direction
	private final ModelPart end_sail;// For wind direction
    private final ModelPart wheel; //Boat Wheel
    private final ModelPart anchor; // Toggle off and on when anchor dropped or not

    private final ModelPart attachment; // Toggle off and on when anchor dropped or not
    private final ModelPart cannon; // Toggle off and on when anchor dropped or not
    private final ModelPart cannon_loaded; // Toggle off and on when anchor dropped or not
    private final ModelPart harpoon; // Toggle off and on when anchor dropped or not
    private final ModelPart harpoon_loaded; // Toggle off and on when anchor dropped or not

    public int   sailLevel      = 3;
    public float sinkProgress   = 0f;
    public float forwardSpeed   = 0f;
    public float rudderTurn     = 0f;
    public float waveTime       = 0f;
    public boolean anchorDeployed = false;   // NEW
    public float boatSpeed      = 0f;        // NEW — horizontal speed in blocks/tick
    public boolean hasBanner = false;

	public CustomBoatModel(ModelPart root) {
		super(root);
    	this.leftPaddle = root.getChild("left_paddle");
    	this.rightPaddle = root.getChild("right_paddle");
    	this.waterPatch = root.getChild("water_patch");
        this.sail_full = root.getChild(BOTTOM).getChild("sail_full");
        
        this.sail_4 = this.sail_full.getChild("sail_4");
		this.caught_wind_4 = this.sail_4.getChild("caught_wind_4");
		this.normal_4 = this.sail_4.getChild("normal_4");
		this.sail_3 = this.sail_full.getChild("sail_3");
		this.caught_wind_3 = this.sail_3.getChild("caught_wind_3");
		this.normal_3 = this.sail_3.getChild("normal_3");
		this.sail_2 = this.sail_full.getChild("sail_2");
		this.caught_wind_2 = this.sail_2.getChild("caught_wind_2");
		this.normal_2 = this.sail_2.getChild("normal_2");
		this.sail_1 = this.sail_full.getChild("sail_1");

		//this.sail_4 = this.sail_full.getChild("sail_4");
		//this.sail_3 = this.sail_full.getChild("sail_3");
		//this.sail_2 = this.sail_full.getChild("sail_2");
		//this.sail_1 = this.sail_full.getChild("sail_1");
        this.rudder = root.getChild(BOTTOM).getChild("rudder");
    	this.parts = this.getParts(root).build();

        this.spanker_2 = root.getChild(BOTTOM).getChild("spanker_sail").getChild("spanker_2");
        this.spanker_1 = root.getChild(BOTTOM).getChild("spanker_sail").getChild("spanker_1");

        this.burgee_sail = root.getChild(BOTTOM).getChild("burgee_sail");
        this.mid_sail = this.burgee_sail.getChild("mid_sail");
		this.end_sail = this.mid_sail.getChild("end_sail");

        this.wheel = root.getChild(BOTTOM).getChild("wheel");
        this.anchor = root.getChild(BOTTOM).getChild("anchor");

        this.attachment = root.getChild(BOTTOM).getChild("attachment");
        this.cannon = attachment.getChild("cannon");
        this.harpoon = attachment.getChild("harpoon");
        this.cannon_loaded = cannon.getChild("cannon_loaded");
        this.harpoon_loaded = harpoon.getChild("harpoon_loaded");

	}

    
   @SuppressWarnings({ "unchecked", "rawtypes" })
    protected ImmutableList.Builder<ModelPart> getParts(ModelPart root) {
    	ImmutableList.Builder<ModelPart> builder = new ImmutableList.Builder();
    	builder.add(new ModelPart[]{root.getChild("bottom"), root.getChild("back"), root.getChild("front"), root.getChild("right"), root.getChild("left"), this.leftPaddle, this.rightPaddle});
    	return builder;
   }

    public static void addParts(PartDefinition partdefinition) {
		PartDefinition front = partdefinition.addOrReplaceChild("front", CubeListBuilder.create(), PartPose.offset(-25.0F, 10.0F, -66.0F));

		PartDefinition cube_r1 = front.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(794, 222).addBox(-1.5F, 66.2296F, -61.2824F, 3.0F, 2.0F, 13.0F, new CubeDeformation(0.0F))
		.texOffs(895, 102).addBox(-2.0F, 65.6916F, -49.0911F, 4.0F, 5.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(800, 208).addBox(-1.5F, 66.2296F, -48.2824F, 3.0F, 4.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(25.0F, -68.6725F, 66.0F, 0.0F, -1.5708F, -0.3927F));

		PartDefinition cube_r2 = front.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(876, 198).addBox(-1.5F, 67.6155F, 19.5363F, 3.0F, 12.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(25.0F, -68.6725F, 66.0F, 0.0F, -1.5708F, -1.1781F));

		PartDefinition cube_r3 = front.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(907, 49).addBox(-40.8597F, 45.6735F, 13.5482F, 3.0F, 12.0F, 27.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(25.7F, -68.6725F, 65.75F, 3.1416F, 0.7854F, 3.1416F));

		PartDefinition cube_r4 = front.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(880, 87).addBox(-32.4907F, 45.6725F, 15.5255F, 3.0F, 12.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(25.4F, -68.6725F, 65.95F, 3.1416F, 1.1781F, 3.1416F));

		PartDefinition cube_r5 = front.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(827, 261).addBox(-33.4907F, 52.6725F, 15.5255F, 1.0F, 2.0F, 13.0F, new CubeDeformation(0.0F))
		.texOffs(773, 213).addBox(-33.4907F, 43.6725F, 15.5255F, 4.0F, 2.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(25.0F, -68.6725F, 66.0F, 3.1416F, 1.1781F, 3.1416F));

		PartDefinition cube_r6 = front.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(826, 215).addBox(-41.8597F, 52.6725F, 13.5482F, 1.0F, 2.0F, 28.0F, new CubeDeformation(0.0F))
		.texOffs(772, 201).addBox(-41.8597F, 43.6725F, 13.5482F, 4.0F, 2.0F, 28.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(25.0F, -68.6725F, 66.0F, 3.1416F, 0.7854F, 3.1416F));

		PartDefinition cube_r7 = front.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(826, 215).addBox(-41.8597F, 52.6725F, -41.5482F, 1.0F, 2.0F, 28.0F, new CubeDeformation(0.0F))
		.texOffs(772, 201).addBox(-41.8597F, 43.6715F, -41.5482F, 4.0F, 2.0F, 28.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(25.0F, -68.6725F, 66.0F, -3.1416F, -0.7854F, 3.1416F));

		PartDefinition cube_r8 = front.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(910, 49).addBox(-40.8597F, 45.6735F, -40.5482F, 3.0F, 12.0F, 27.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(25.7F, -68.6725F, 66.25F, -3.1416F, -0.7854F, 3.1416F));

		PartDefinition cube_r9 = front.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(773, 213).addBox(-33.4907F, 43.6725F, -28.5255F, 4.0F, 2.0F, 13.0F, new CubeDeformation(0.0F))
		.texOffs(827, 261).addBox(-33.4907F, 52.6725F, -28.5255F, 1.0F, 2.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(25.0F, -68.6725F, 66.0F, -3.1416F, -1.1781F, 3.1416F));

		PartDefinition cube_r10 = front.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(883, 87).addBox(-32.4907F, 45.6725F, -28.5255F, 3.0F, 12.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(25.4F, -68.6725F, 66.05F, -3.1416F, -1.1781F, 3.1416F));

		PartDefinition right = partdefinition.addOrReplaceChild("right", CubeListBuilder.create(), PartPose.offset(23.0F, 33.0F, 2.0F));

		PartDefinition cube_r11 = right.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(786, 214).addBox(-25.0F, 52.6725F, -9.16F, 1.0F, 2.0F, 31.0F, new CubeDeformation(0.0F))
		.texOffs(854, 102).addBox(-24.0F, 50.6725F, -9.16F, 3.0F, 7.0F, 31.0F, new CubeDeformation(0.0F))
		.texOffs(773, 220).addBox(-25.0F, 47.6725F, -9.16F, 4.0F, 3.0F, 31.0F, new CubeDeformation(0.0F))
		.texOffs(776, 214).addBox(-24.5F, 41.6725F, 30.8401F, 4.0F, 16.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(771, 206).addBox(-25.0F, 52.6725F, 21.84F, 1.0F, 2.0F, 9.0F, new CubeDeformation(0.0F))
		.texOffs(889, 151).addBox(-24.0F, 45.6725F, 21.84F, 3.0F, 12.0F, 9.0F, new CubeDeformation(0.0F))
		.texOffs(772, 203).addBox(-25.0F, 43.6725F, 20.84F, 4.0F, 2.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(805, 240).addBox(-25.0F, 43.6725F, -27.16F, 4.0F, 2.0F, 19.0F, new CubeDeformation(0.0F))
		.texOffs(797, 222).addBox(-25.0F, 52.6725F, -27.16F, 1.0F, 2.0F, 18.0F, new CubeDeformation(0.0F))
		.texOffs(878, 82).addBox(-24.0F, 45.6735F, -27.16F, 3.0F, 12.0F, 18.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-23.0F, -91.6725F, -2.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition cube_r12 = right.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(302, 521).addBox(-24.5F, -21.3257F, 50.9874F, 4.0F, 11.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-23.0F, -91.6725F, -2.0F, 0.0F, -1.5708F, -1.1257F));

		PartDefinition bottom = partdefinition.addOrReplaceChild("bottom", CubeListBuilder.create(), PartPose.offset(-17.0F, 14.0F, -77.0F));

		PartDefinition cube_r13 = bottom.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(780, 112).addBox(-8.0F, 41.6725F, 35.84F, 16.0F, 16.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(794, 234).addBox(-2.0F, 34.6725F, 36.84F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(785, 232).addBox(-9.0F, 39.6725F, 34.84F, 18.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(788, 202).addBox(-2.5F, -84.8275F, 10.34F, 5.0F, 17.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(788, 202).addBox(-2.5F, -67.8275F, 10.34F, 5.0F, 97.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(799, 142).addBox(-7.5F, -67.8275F, 18.34F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(786, 129).addBox(-7.5F, -63.8275F, 5.34F, 15.0F, 4.0F, 15.0F, new CubeDeformation(0.0F))
		.texOffs(797, 131).addBox(-3.5F, 28.1725F, 9.34F, 7.0F, 4.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(788, 215).addBox(-3.0F, 29.1725F, 9.84F, 6.0F, 29.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(825, 93).addBox(-21.0F, 49.4225F, 38.84F, 42.0F, 8.0F, 22.0F, new CubeDeformation(0.0F))
		.texOffs(818, 94).addBox(-21.0F, 53.4225F, 35.84F, 42.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(784, 34).addBox(-21.0F, 53.4225F, -34.16F, 42.0F, 1.0F, 70.0F, new CubeDeformation(0.0F))
		.texOffs(819, 215).addBox(-1.5F, 56.6725F, 58.84F, 3.0F, 8.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(776, 200).addBox(-1.5F, 61.6725F, -53.16F, 3.0F, 3.0F, 54.0F, new CubeDeformation(0.0F))
		.texOffs(770, 205).addBox(-1.5F, 61.6725F, 0.84F, 3.0F, 3.0F, 58.0F, new CubeDeformation(0.0F))
		.texOffs(838, 135).addBox(-16.5F, 57.6725F, 55.59F, 33.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(771, 51).addBox(-18.0F, 60.6725F, -31.16F, 36.0F, 1.0F, 87.0F, new CubeDeformation(0.0F))
		.texOffs(822, 61).addBox(-22.0F, 57.6725F, -25.16F, 4.0F, 4.0F, 79.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(17.0F, -72.6725F, 77.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition cube_r14 = bottom.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(774, 199).addBox(-1.5F, -46.5542F, 34.3072F, 3.0F, 3.0F, 54.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(17.0F, -61.6725F, 77.0F, 0.0F, -1.5708F, 0.3927F));

		PartDefinition cube_r15 = bottom.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(774, 198).addBox(-1.5F, -1.5F, 0.5F, 3.0F, 3.0F, 51.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.16F, -60.0F, 77.0F, 0.0F, -1.5708F, 0.0873F));

		PartDefinition cube_r16 = bottom.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(788, 37).addBox(-2.5F, 12.1725F, 11.34F, 4.0F, 17.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(17.5F, -186.6725F, 77.75F, 0.0F, -1.5708F, 0.0F));

		PartDefinition cube_r17 = bottom.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(799, 142).addBox(-7.5F, 28.1725F, 18.34F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(30.0F, -168.6725F, 77.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition cube_r18 = bottom.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(799, 142).addBox(-7.5F, 28.1725F, 18.34F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(26.0F, -168.6725F, 77.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition cube_r19 = bottom.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(799, 142).addBox(-7.5F, 28.1725F, 18.34F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(21.0F, -168.6725F, 77.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition cube_r20 = bottom.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(832, 75).addBox(-18.0F, 57.4225F, -44.16F, 4.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(17.0F, -75.6725F, 81.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition cube_r21 = bottom.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(832, 75).addBox(-18.0F, 57.4225F, -44.16F, 4.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(17.0F, -75.6725F, 105.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition cube_r22 = bottom.addOrReplaceChild("cube_r22", CubeListBuilder.create().texOffs(849, 517).addBox(-18.0F, 57.4225F, -48.16F, 4.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(17.0F, -76.6725F, 85.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition cube_r23 = bottom.addOrReplaceChild("cube_r23", CubeListBuilder.create().texOffs(781, 210).addBox(-57.0263F, 25.9687F, -54.84F, 4.0F, 5.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(774, 205).addBox(-57.0263F, 25.9687F, -33.84F, 4.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(17.0F, -72.6725F, 77.0F, -1.5708F, 0.829F, -1.5708F));

		PartDefinition cube_r24 = bottom.addOrReplaceChild("cube_r24", CubeListBuilder.create().texOffs(781, 210).addBox(-57.0263F, 25.9687F, 51.84F, 4.0F, 5.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(774, 205).addBox(-57.0263F, 25.9687F, 30.84F, 4.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(17.0F, -72.6725F, 77.0F, 1.5708F, -0.829F, -1.5708F));

		PartDefinition cube_r25 = bottom.addOrReplaceChild("cube_r25", CubeListBuilder.create().texOffs(770, 208).addBox(-1.5F, 71.3202F, -6.8594F, 3.0F, 12.0F, 15.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(17.0F, -72.6725F, 77.0F, 0.0F, -1.5708F, -0.7854F));

		PartDefinition cube_r26 = bottom.addOrReplaceChild("cube_r26", CubeListBuilder.create().texOffs(791, 205).addBox(-1.5F, 72.0931F, -31.3643F, 3.0F, 8.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(17.0F, -72.6725F, 77.0F, 0.0F, -1.5708F, -0.3927F));

		PartDefinition cube_r27 = bottom.addOrReplaceChild("cube_r27", CubeListBuilder.create().texOffs(856, 72).addBox(-53.627F, 57.6735F, -30.5143F, 4.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(17.0F, -72.6725F, 77.0F, 0.0F, 0.7854F, 0.0F));

		PartDefinition cube_r28 = bottom.addOrReplaceChild("cube_r28", CubeListBuilder.create().texOffs(856, 72).addBox(-53.627F, 57.6735F, 22.5143F, 4.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(17.0F, -72.6725F, 77.0F, 0.0F, -0.7854F, 0.0F));

		PartDefinition cube_r29 = bottom.addOrReplaceChild("cube_r29", CubeListBuilder.create().texOffs(861, 49).addBox(-5.0F, 60.6725F, 31.16F, 11.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(867, 49).addBox(-5.0F, 60.6725F, 37.16F, 7.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(872, 55).addBox(-5.0F, 60.6725F, 41.16F, 3.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(871, 52).addBox(-5.0F, 60.6725F, 43.16F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(20.0F, -79.6725F, 65.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition cube_r30 = bottom.addOrReplaceChild("cube_r30", CubeListBuilder.create().texOffs(870, 54).addBox(-7.0F, 60.6725F, -43.16F, 5.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(869, 52).addBox(-7.0F, 60.6725F, -46.16F, 4.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(868, 50).addBox(-7.0F, 60.6725F, -41.16F, 9.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(858, 48).addBox(-7.0F, 60.6725F, -37.16F, 13.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(20.0F, -79.6725F, 89.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition cube_r31 = bottom.addOrReplaceChild("cube_r31", CubeListBuilder.create().texOffs(842, 36).addBox(-7.0F, 60.6725F, -49.16F, 12.0F, 1.0F, 18.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(20.0F, -79.6725F, 77.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition cube_r32 = bottom.addOrReplaceChild("cube_r32", CubeListBuilder.create().texOffs(823, 61).addBox(-22.0F, 57.6725F, -53.84F, 4.0F, 4.0F, 79.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(17.0F, -72.6725F, 77.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition cube_r33 = bottom.addOrReplaceChild("cube_r33", CubeListBuilder.create().texOffs(873, 115).addBox(-29.8775F, 57.6735F, 14.4431F, 4.0F, 4.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(17.4F, -72.6725F, 76.95F, 3.1416F, 1.1781F, 3.1416F));

		PartDefinition cube_r34 = bottom.addOrReplaceChild("cube_r34", CubeListBuilder.create().texOffs(804, 79).addBox(-38.0312F, 57.6725F, 13.9624F, 4.0F, 4.0F, 24.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(17.4F, -72.6725F, 77.05F, 3.1416F, 0.7854F, 3.1416F));

		PartDefinition cube_r35 = bottom.addOrReplaceChild("cube_r35", CubeListBuilder.create().texOffs(866, 115).addBox(-29.8775F, 57.6735F, -27.4431F, 4.0F, 4.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(17.4F, -72.6725F, 77.05F, -3.1416F, -1.1781F, 3.1416F));

		PartDefinition cube_r36 = bottom.addOrReplaceChild("cube_r36", CubeListBuilder.create().texOffs(801, 222).addBox(-3.0F, 5.0F, -8.0004F, 10.0F, 4.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(60.9436F, -27.051F, 75.3204F, 0.0F, -1.5708F, -0.1309F));

		PartDefinition cube_r37 = bottom.addOrReplaceChild("cube_r37", CubeListBuilder.create().texOffs(815, 81).addBox(-34.0312F, 57.6675F, -33.9624F, 23.0F, 4.0F, 23.0F, new CubeDeformation(0.0F))
		.texOffs(810, 80).addBox(-38.0312F, 57.6725F, -37.9624F, 4.0F, 4.0F, 24.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(17.4F, -72.6725F, 76.95F, -3.1416F, -0.7854F, 3.1416F));

		PartDefinition sail_full = bottom.addOrReplaceChild("sail_full", CubeListBuilder.create(), PartPose.offset(6.3908F, -97.9179F, 77.5F));

		PartDefinition top_beam_r1 = sail_full.addOrReplaceChild("top_beam_r1", CubeListBuilder.create().texOffs(824, 78).addBox(-41.5F, -54.8275F, 11.34F, 84.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(11.1092F, 25.2454F, -0.5F, 0.0F, -1.5708F, 0.0F));

		PartDefinition sail_4 = sail_full.addOrReplaceChild("sail_4", CubeListBuilder.create(), PartPose.offset(-7.0F, 5.0F, 2.375F));

		PartDefinition cube_r38 = sail_4.addOrReplaceChild("cube_r38", CubeListBuilder.create().texOffs(894, 418).addBox(-0.3091F, -0.2875F, 22.25F, 24.0F, 2.0F, 15.0F, new CubeDeformation(0.0F))
		.texOffs(895, 419).addBox(-0.3091F, -0.2875F, -41.75F, 24.0F, 2.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(22.875F, -8.8125F, 0.0F, 0.0F, 0.0F, 1.5708F));

		PartDefinition cube_r39 = sail_4.addOrReplaceChild("cube_r39", CubeListBuilder.create().texOffs(883, 418).addBox(-5.285F, -0.2385F, 22.25F, 14.0F, 2.0F, 15.0F, new CubeDeformation(0.0F))
		.texOffs(884, 419).addBox(-5.285F, -0.2385F, -41.75F, 14.0F, 2.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(19.6625F, -16.9375F, 0.0F, 0.0F, 0.0F, 1.1781F));

		PartDefinition cube_r40 = sail_4.addOrReplaceChild("cube_r40", CubeListBuilder.create().texOffs(874, 418).addBox(-7.2403F, -0.2827F, 22.25F, 16.0F, 2.0F, 15.0F, new CubeDeformation(0.0F))
		.texOffs(875, 419).addBox(-7.2403F, -0.2827F, -41.75F, 16.0F, 2.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(11.5375F, -27.75F, 0.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition cube_r41 = sail_4.addOrReplaceChild("cube_r41", CubeListBuilder.create().texOffs(883, 418).addBox(-5.285F, -2.0115F, 22.25F, 14.0F, 2.0F, 15.0F, new CubeDeformation(0.0F))
		.texOffs(884, 419).addBox(-5.285F, -2.0115F, -41.75F, 14.0F, 2.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(19.7625F, 22.9822F, 0.0F, 0.0F, 0.0F, -1.1781F));

		PartDefinition cube_r42 = sail_4.addOrReplaceChild("cube_r42", CubeListBuilder.create().texOffs(876, 418).addBox(-5.2403F, -1.9673F, 22.25F, 14.0F, 2.0F, 15.0F, new CubeDeformation(0.0F))
		.texOffs(877, 419).addBox(-5.2403F, -1.9673F, -41.75F, 14.0F, 2.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(11.575F, 33.7947F, 0.0F, 0.0F, 0.0F, -0.7854F));

		PartDefinition caught_wind_4 = sail_4.addOrReplaceChild("caught_wind_4", CubeListBuilder.create(), PartPose.offset(20.6625F, -17.9375F, -15.125F));

		PartDefinition cube_r43 = caught_wind_4.addOrReplaceChild("cube_r43", CubeListBuilder.create().texOffs(887, 422).addBox(-5.285F, -0.2385F, -12.75F, 15.0F, 2.0F, 11.0F, new CubeDeformation(0.0F))
		.texOffs(887, 422).addBox(-5.285F, -0.2385F, 26.25F, 15.0F, 2.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.1781F));

		PartDefinition cube_r44 = caught_wind_4.addOrReplaceChild("cube_r44", CubeListBuilder.create().texOffs(870, 405).addBox(-5.285F, -0.2385F, 9.25F, 16.0F, 2.0F, 28.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -1.0F, -11.0F, 0.0F, 0.0F, 1.1781F));

		PartDefinition cube_r45 = caught_wind_4.addOrReplaceChild("cube_r45", CubeListBuilder.create().texOffs(861, 405).addBox(-7.2403F, -0.2827F, 9.25F, 16.0F, 2.0F, 28.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.125F, -11.8125F, -11.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition cube_r46 = caught_wind_4.addOrReplaceChild("cube_r46", CubeListBuilder.create().texOffs(878, 422).addBox(-7.2403F, -0.2827F, 26.25F, 16.0F, 2.0F, 11.0F, new CubeDeformation(0.0F))
		.texOffs(878, 422).addBox(-7.2403F, -0.2827F, -12.75F, 16.0F, 2.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.125F, -10.8125F, 0.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition cube_r47 = caught_wind_4.addOrReplaceChild("cube_r47", CubeListBuilder.create().texOffs(898, 422).addBox(-0.3091F, -1.2875F, -27.75F, 24.0F, 3.0F, 11.0F, new CubeDeformation(0.0F))
		.texOffs(898, 422).addBox(-0.3091F, -1.2875F, -66.75F, 24.0F, 3.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.6625F, 9.125F, 54.125F, 0.0F, 0.0F, 1.5708F));

		PartDefinition cube_r48 = caught_wind_4.addOrReplaceChild("cube_r48", CubeListBuilder.create().texOffs(880, 422).addBox(-5.2403F, -0.9673F, -27.75F, 14.0F, 2.0F, 11.0F, new CubeDeformation(0.0F))
		.texOffs(880, 422).addBox(-5.2403F, -0.9673F, -66.75F, 14.0F, 2.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.6875F, 51.7322F, 54.125F, 0.0F, 0.0F, -0.7854F));

		PartDefinition cube_r49 = caught_wind_4.addOrReplaceChild("cube_r49", CubeListBuilder.create().texOffs(863, 405).addBox(-5.2403F, 1.0327F, -27.75F, 14.0F, 2.0F, 28.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.6875F, 50.7322F, 26.125F, 0.0F, 0.0F, -0.7854F));

		PartDefinition cube_r50 = caught_wind_4.addOrReplaceChild("cube_r50", CubeListBuilder.create().texOffs(881, 405).addBox(-0.3091F, -2.2875F, -27.75F, 24.0F, 3.0F, 28.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0625F, 9.125F, 26.125F, 0.0F, 0.0F, 1.5708F));

		PartDefinition cube_r51 = caught_wind_4.addOrReplaceChild("cube_r51", CubeListBuilder.create().texOffs(886, 422).addBox(-6.285F, -0.0115F, -27.75F, 15.0F, 2.0F, 11.0F, new CubeDeformation(0.0F))
		.texOffs(886, 422).addBox(-6.285F, -0.0115F, -66.75F, 15.0F, 2.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.2F, 39.7197F, 54.125F, 0.0F, 0.0F, -1.1781F));

		PartDefinition cube_r52 = caught_wind_4.addOrReplaceChild("cube_r52", CubeListBuilder.create().texOffs(869, 405).addBox(-6.285F, 0.9885F, -16.75F, 15.0F, 2.0F, 28.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.7F, 39.7197F, 15.125F, 0.0F, 0.0F, -1.1781F));

		PartDefinition normal_4 = sail_4.addOrReplaceChild("normal_4", CubeListBuilder.create(), PartPose.offset(23.375F, -9.5625F, 0.0F));

		PartDefinition cube_r53 = normal_4.addOrReplaceChild("cube_r53", CubeListBuilder.create().texOffs(839, 383).addBox(-7.2403F, -0.2827F, -27.75F, 16.0F, 2.0F, 50.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-11.8375F, -18.1875F, 0.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition cube_r54 = normal_4.addOrReplaceChild("cube_r54", CubeListBuilder.create().texOffs(848, 383).addBox(-5.285F, -2.0115F, -27.75F, 14.0F, 2.0F, 50.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.6125F, 32.5447F, 0.0F, 0.0F, 0.0F, -1.1781F));

		PartDefinition cube_r55 = normal_4.addOrReplaceChild("cube_r55", CubeListBuilder.create().texOffs(841, 383).addBox(-5.2403F, -1.9673F, -27.75F, 14.0F, 2.0F, 50.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-11.8F, 43.3572F, 0.0F, 0.0F, 0.0F, -0.7854F));

		PartDefinition cube_r56 = normal_4.addOrReplaceChild("cube_r56", CubeListBuilder.create().texOffs(859, 383).addBox(-0.3091F, -0.2875F, -27.75F, 24.0F, 2.0F, 50.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 0.75F, 0.0F, 0.0F, 0.0F, 1.5708F));

		PartDefinition cube_r57 = normal_4.addOrReplaceChild("cube_r57", CubeListBuilder.create().texOffs(848, 383).addBox(-5.285F, -0.2385F, -27.75F, 14.0F, 2.0F, 50.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.7125F, -7.375F, 0.0F, 0.0F, 0.0F, 1.1781F));

		PartDefinition sail_3 = sail_full.addOrReplaceChild("sail_3", CubeListBuilder.create(), PartPose.offset(-7.0F, 5.0F, 2.375F));

		PartDefinition cube_r58 = sail_3.addOrReplaceChild("cube_r58", CubeListBuilder.create().texOffs(894, 418).addBox(-0.3091F, -0.2875F, 22.25F, 24.0F, 2.0F, 15.0F, new CubeDeformation(0.0F))
		.texOffs(895, 419).addBox(-0.3091F, -0.2875F, -41.75F, 24.0F, 2.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(22.875F, -8.8125F, 0.0F, 0.0F, 0.0F, 1.5708F));

		PartDefinition cube_r59 = sail_3.addOrReplaceChild("cube_r59", CubeListBuilder.create().texOffs(883, 418).addBox(-5.285F, -0.2385F, 22.25F, 14.0F, 2.0F, 15.0F, new CubeDeformation(0.0F))
		.texOffs(884, 419).addBox(-5.285F, -0.2385F, -41.75F, 14.0F, 2.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(19.6625F, -16.9375F, 0.0F, 0.0F, 0.0F, 1.1781F));

		PartDefinition cube_r60 = sail_3.addOrReplaceChild("cube_r60", CubeListBuilder.create().texOffs(874, 418).addBox(-7.2403F, -0.2827F, 22.25F, 16.0F, 2.0F, 15.0F, new CubeDeformation(0.0F))
		.texOffs(875, 419).addBox(-7.2403F, -0.2827F, -41.75F, 16.0F, 2.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(11.5375F, -27.75F, 0.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition cube_r61 = sail_3.addOrReplaceChild("cube_r61", CubeListBuilder.create().texOffs(894, 419).addBox(4.715F, -4.0115F, 23.25F, 4.0F, 4.0F, 14.0F, new CubeDeformation(0.0F))
		.texOffs(894, 419).addBox(4.715F, -4.0115F, -42.75F, 4.0F, 4.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(20.7625F, 22.9822F, 0.0F, 0.0F, 0.0F, -1.1781F));

		PartDefinition caught_wind_3 = sail_3.addOrReplaceChild("caught_wind_3", CubeListBuilder.create(), PartPose.offset(20.6625F, -17.9375F, -15.125F));

		PartDefinition cube_r62 = caught_wind_3.addOrReplaceChild("cube_r62", CubeListBuilder.create().texOffs(887, 422).addBox(-5.285F, -0.2385F, -12.75F, 15.0F, 2.0F, 11.0F, new CubeDeformation(0.0F))
		.texOffs(887, 422).addBox(-5.285F, -0.2385F, 26.25F, 15.0F, 2.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.1781F));

		PartDefinition cube_r63 = caught_wind_3.addOrReplaceChild("cube_r63", CubeListBuilder.create().texOffs(870, 405).addBox(-5.285F, -0.2385F, 9.25F, 16.0F, 2.0F, 28.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -1.0F, -11.0F, 0.0F, 0.0F, 1.1781F));

		PartDefinition cube_r64 = caught_wind_3.addOrReplaceChild("cube_r64", CubeListBuilder.create().texOffs(861, 405).addBox(-7.2403F, -0.2827F, 9.25F, 16.0F, 2.0F, 28.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.125F, -11.8125F, -11.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition cube_r65 = caught_wind_3.addOrReplaceChild("cube_r65", CubeListBuilder.create().texOffs(878, 422).addBox(-7.2403F, -0.2827F, 26.25F, 16.0F, 2.0F, 11.0F, new CubeDeformation(0.0F))
		.texOffs(878, 422).addBox(-7.2403F, -0.2827F, -12.75F, 16.0F, 2.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.125F, -10.8125F, 0.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition cube_r66 = caught_wind_3.addOrReplaceChild("cube_r66", CubeListBuilder.create().texOffs(898, 422).addBox(-0.3091F, -1.2875F, -27.75F, 24.0F, 3.0F, 11.0F, new CubeDeformation(0.0F))
		.texOffs(898, 422).addBox(-0.3091F, -1.2875F, -66.75F, 24.0F, 3.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.6625F, 9.125F, 54.125F, 0.0F, 0.0F, 1.5708F));

		PartDefinition cube_r67 = caught_wind_3.addOrReplaceChild("cube_r67", CubeListBuilder.create().texOffs(881, 405).addBox(-0.3091F, -2.2875F, -27.75F, 24.0F, 3.0F, 28.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0625F, 9.125F, 26.125F, 0.0F, 0.0F, 1.5708F));

		PartDefinition cube_r68 = caught_wind_3.addOrReplaceChild("cube_r68", CubeListBuilder.create().texOffs(878, 403).addBox(4.715F, -2.0115F, -17.75F, 4.0F, 4.0F, 30.0F, new CubeDeformation(0.0F))
		.texOffs(897, 422).addBox(4.715F, -3.0115F, -28.75F, 4.0F, 4.0F, 11.0F, new CubeDeformation(0.0F))
		.texOffs(897, 422).addBox(4.715F, -3.0115F, 12.25F, 4.0F, 4.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1F, 40.9197F, 15.125F, 0.0F, 0.0F, -1.1781F));

		PartDefinition normal_3 = sail_3.addOrReplaceChild("normal_3", CubeListBuilder.create(), PartPose.offset(23.375F, -9.5625F, 0.0F));

		PartDefinition cube_r69 = normal_3.addOrReplaceChild("cube_r69", CubeListBuilder.create().texOffs(839, 383).addBox(-7.2403F, -0.2827F, -27.75F, 16.0F, 2.0F, 50.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-11.8375F, -18.1875F, 0.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition cube_r70 = normal_3.addOrReplaceChild("cube_r70", CubeListBuilder.create().texOffs(859, 383).addBox(-0.3091F, -0.2875F, -27.75F, 24.0F, 2.0F, 50.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 0.75F, 0.0F, 0.0F, 0.0F, 1.5708F));

		PartDefinition cube_r71 = normal_3.addOrReplaceChild("cube_r71", CubeListBuilder.create().texOffs(856, 381).addBox(4.715F, -4.0115F, -28.75F, 4.0F, 4.0F, 52.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.6125F, 32.5447F, 0.0F, 0.0F, 0.0F, -1.1781F));

		PartDefinition cube_r72 = normal_3.addOrReplaceChild("cube_r72", CubeListBuilder.create().texOffs(848, 383).addBox(-5.285F, -0.2385F, -27.75F, 14.0F, 2.0F, 50.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.7125F, -7.375F, 0.0F, 0.0F, 0.0F, 1.1781F));

		PartDefinition sail_2 = sail_full.addOrReplaceChild("sail_2", CubeListBuilder.create(), PartPose.offset(-7.0F, 5.0F, 2.375F));

		PartDefinition cube_r73 = sail_2.addOrReplaceChild("cube_r73", CubeListBuilder.create().texOffs(894, 418).addBox(-0.3091F, -1.2875F, 23.25F, 5.0F, 5.0F, 15.0F, new CubeDeformation(0.0F))
		.texOffs(895, 419).addBox(-0.3091F, -1.2875F, -42.75F, 5.0F, 5.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(23.375F, -9.5625F, 0.0F, 0.0F, 0.0F, 1.5708F));

		PartDefinition cube_r74 = sail_2.addOrReplaceChild("cube_r74", CubeListBuilder.create().texOffs(883, 418).addBox(-5.285F, -0.2385F, 22.25F, 14.0F, 2.0F, 15.0F, new CubeDeformation(0.0F))
		.texOffs(884, 419).addBox(-5.285F, -0.2385F, -41.75F, 14.0F, 2.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(19.6625F, -16.9375F, 0.0F, 0.0F, 0.0F, 1.1781F));

		PartDefinition cube_r75 = sail_2.addOrReplaceChild("cube_r75", CubeListBuilder.create().texOffs(874, 418).addBox(-7.2403F, -0.2827F, 22.25F, 16.0F, 2.0F, 15.0F, new CubeDeformation(0.0F))
		.texOffs(875, 419).addBox(-7.2403F, -0.2827F, -41.75F, 16.0F, 2.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(11.5375F, -27.75F, 0.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition caught_wind_2 = sail_2.addOrReplaceChild("caught_wind_2", CubeListBuilder.create(), PartPose.offset(20.6625F, -17.9375F, -15.125F));

		PartDefinition cube_r76 = caught_wind_2.addOrReplaceChild("cube_r76", CubeListBuilder.create().texOffs(870, 405).addBox(-5.285F, -0.2385F, 9.25F, 15.0F, 2.0F, 28.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -1.0F, -11.0F, 0.0F, 0.0F, 1.1781F));

		PartDefinition cube_r77 = caught_wind_2.addOrReplaceChild("cube_r77", CubeListBuilder.create().texOffs(861, 405).addBox(-7.2403F, -0.2827F, 9.25F, 16.0F, 2.0F, 28.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.125F, -11.8125F, -11.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition cube_r78 = caught_wind_2.addOrReplaceChild("cube_r78", CubeListBuilder.create().texOffs(887, 422).addBox(-5.285F, -0.2385F, 26.25F, 14.0F, 2.0F, 11.0F, new CubeDeformation(0.0F))
		.texOffs(887, 422).addBox(-5.285F, -0.2385F, -12.75F, 14.0F, 2.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.1781F));

		PartDefinition cube_r79 = caught_wind_2.addOrReplaceChild("cube_r79", CubeListBuilder.create().texOffs(878, 422).addBox(-7.2403F, -0.2827F, 26.25F, 16.0F, 2.0F, 11.0F, new CubeDeformation(0.0F))
		.texOffs(878, 422).addBox(-7.2403F, -0.2827F, -12.75F, 16.0F, 2.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.125F, -10.8125F, 0.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition cube_r80 = caught_wind_2.addOrReplaceChild("cube_r80", CubeListBuilder.create().texOffs(879, 403).addBox(-0.3091F, -3.2875F, -17.75F, 5.0F, 5.0F, 30.0F, new CubeDeformation(0.0F))
		.texOffs(898, 422).addBox(-0.3091F, -2.2875F, 12.25F, 5.0F, 5.0F, 11.0F, new CubeDeformation(0.0F))
		.texOffs(898, 422).addBox(-0.3091F, -2.2875F, -28.75F, 5.0F, 5.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.7125F, 8.375F, 15.125F, 0.0F, 0.0F, 1.5708F));

		PartDefinition normal_2 = sail_2.addOrReplaceChild("normal_2", CubeListBuilder.create(), PartPose.offset(23.375F, -9.5625F, 0.0F));

		PartDefinition cube_r81 = normal_2.addOrReplaceChild("cube_r81", CubeListBuilder.create().texOffs(839, 383).addBox(-7.2403F, -0.2827F, -27.75F, 16.0F, 2.0F, 50.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-11.8375F, -18.1875F, 0.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition cube_r82 = normal_2.addOrReplaceChild("cube_r82", CubeListBuilder.create().texOffs(848, 383).addBox(-5.285F, -0.2385F, -27.75F, 14.0F, 2.0F, 50.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.7125F, -7.375F, 0.0F, 0.0F, 0.0F, 1.1781F));

		PartDefinition cube_r83 = normal_2.addOrReplaceChild("cube_r83", CubeListBuilder.create().texOffs(857, 381).addBox(-0.3091F, -1.2875F, -28.75F, 5.0F, 5.0F, 52.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

		PartDefinition sail_1 = sail_full.addOrReplaceChild("sail_1", CubeListBuilder.create(), PartPose.offset(-7.0F, 5.0F, 2.375F));

		PartDefinition cube_r84 = sail_1.addOrReplaceChild("cube_r84", CubeListBuilder.create().texOffs(809, 354).addBox(-8.2403F, -2.2827F, -41.75F, 6.0F, 6.0F, 79.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(11.5375F, -27.75F, 0.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition spanker_sail = bottom.addOrReplaceChild("spanker_sail", CubeListBuilder.create(), PartPose.offset(17.0F, -72.6725F, 76.5F));

		PartDefinition spanker_2 = spanker_sail.addOrReplaceChild("spanker_2", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r85 = spanker_2.addOrReplaceChild("cube_r85", CubeListBuilder.create().texOffs(903, 347).addBox(-0.5F, 7.1725F, 43.34F, 2.0F, 3.0F, 17.0F, new CubeDeformation(0.0F))
		.texOffs(892, 336).addBox(-0.5F, 7.1725F, 15.34F, 2.0F, 4.0F, 28.0F, new CubeDeformation(0.0F))
		.texOffs(878, 322).addBox(-0.5F, -47.8275F, 21.34F, 2.0F, 3.0F, 42.0F, new CubeDeformation(0.0F))
		.texOffs(872, 316).addBox(-0.5F, -44.8275F, 15.34F, 2.0F, 52.0F, 48.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition cube_r86 = spanker_2.addOrReplaceChild("cube_r86", CubeListBuilder.create().texOffs(914, 358).addBox(-0.5F, -58.8275F, 21.34F, 2.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-36.0F, -4.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition cube_r87 = spanker_2.addOrReplaceChild("cube_r87", CubeListBuilder.create().texOffs(906, 350).addBox(-0.5F, -58.8275F, 21.34F, 2.0F, 3.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-28.0F, -1.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition cube_r88 = spanker_2.addOrReplaceChild("cube_r88", CubeListBuilder.create().texOffs(899, 343).addBox(-0.5F, -58.8275F, 21.34F, 2.0F, 3.0F, 21.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-21.0F, 2.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition cube_r89 = spanker_2.addOrReplaceChild("cube_r89", CubeListBuilder.create().texOffs(892, 336).addBox(-0.5F, -58.8275F, 21.34F, 2.0F, 3.0F, 28.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-14.0F, 5.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition cube_r90 = spanker_2.addOrReplaceChild("cube_r90", CubeListBuilder.create().texOffs(885, 329).addBox(-0.5F, -58.8275F, 21.34F, 2.0F, 3.0F, 35.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.0F, 8.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition spanker_1 = spanker_sail.addOrReplaceChild("spanker_1", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r91 = spanker_1.addOrReplaceChild("cube_r91", CubeListBuilder.create().texOffs(824, 343).addBox(-2.5F, -40.5F, 4.5F, 5.0F, 6.0F, 49.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-14.84F, 12.6725F, 0.5F, 0.0F, -1.5708F, 0.0873F));

		PartDefinition cube_r92 = spanker_1.addOrReplaceChild("cube_r92", CubeListBuilder.create().texOffs(914, 358).addBox(-0.5F, -58.8275F, 21.34F, 2.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-36.0F, -4.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition cube_r93 = spanker_1.addOrReplaceChild("cube_r93", CubeListBuilder.create().texOffs(906, 350).addBox(-0.5F, -58.8275F, 21.34F, 2.0F, 3.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-28.0F, -1.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition cube_r94 = spanker_1.addOrReplaceChild("cube_r94", CubeListBuilder.create().texOffs(899, 343).addBox(-0.5F, -58.8275F, 21.34F, 2.0F, 3.0F, 21.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-21.0F, 2.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition cube_r95 = spanker_1.addOrReplaceChild("cube_r95", CubeListBuilder.create().texOffs(892, 336).addBox(-0.5F, -58.8275F, 21.34F, 2.0F, 3.0F, 28.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-14.0F, 5.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition cube_r96 = spanker_1.addOrReplaceChild("cube_r96", CubeListBuilder.create().texOffs(885, 329).addBox(-0.5F, -58.8275F, 21.34F, 2.0F, 3.0F, 35.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.0F, 8.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition cube_r97 = spanker_1.addOrReplaceChild("cube_r97", CubeListBuilder.create().texOffs(878, 322).addBox(-0.5F, -58.8275F, 21.34F, 2.0F, 3.0F, 42.0F, new CubeDeformation(0.0F))
		.texOffs(872, 316).addBox(-0.5F, -55.8275F, 15.34F, 2.0F, 17.0F, 48.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 11.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition rudder = bottom.addOrReplaceChild("rudder", CubeListBuilder.create(), PartPose.offset(-45.9233F, -15.3333F, 77.0F));

		PartDefinition cube_r98 = rudder.addOrReplaceChild("cube_r98", CubeListBuilder.create().texOffs(826, 39).addBox(-1.5F, 59.6725F, 56.84F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(826, 39).addBox(-1.5F, 62.6725F, 56.84F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(56.9233F, -57.3392F, 0.5F, 0.0F, -1.5708F, 0.0F));

		PartDefinition cube_r99 = rudder.addOrReplaceChild("cube_r99", CubeListBuilder.create().texOffs(820, 215).addBox(-0.5F, 56.6725F, 58.84F, 1.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(56.9233F, -57.3392F, 0.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition wheel = bottom.addOrReplaceChild("wheel", CubeListBuilder.create(), PartPose.offset(-24.8917F, -34.9806F, 76.7816F));

		PartDefinition cube_r100 = wheel.addOrReplaceChild("cube_r100", CubeListBuilder.create().texOffs(796, 233).addBox(-2.0F, 38.6725F, 37.84F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(38.3917F, -39.6919F, 5.2184F, 0.0F, -1.5708F, 0.0F));

		PartDefinition cube_r101 = wheel.addOrReplaceChild("cube_r101", CubeListBuilder.create().texOffs(796, 233).addBox(-2.0F, 38.6725F, 37.84F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(38.3917F, -39.6919F, 10.2184F, 0.0F, -1.5708F, 0.0F));

		PartDefinition cube_r102 = wheel.addOrReplaceChild("cube_r102", CubeListBuilder.create().texOffs(796, 233).addBox(-2.0F, 38.6725F, 37.84F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(38.3917F, -39.6919F, -4.2816F, 0.0F, -1.5708F, 0.0F));

		PartDefinition cube_r103 = wheel.addOrReplaceChild("cube_r103", CubeListBuilder.create().texOffs(796, 233).addBox(-2.0F, 38.6725F, 37.84F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(38.3917F, -39.6919F, -9.2816F, 0.0F, -1.5708F, 0.0F));

		PartDefinition cube_r104 = wheel.addOrReplaceChild("cube_r104", CubeListBuilder.create().texOffs(797, 233).addBox(-1.0F, 38.6725F, 37.84F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(797, 233).addBox(-1.0F, 33.6725F, 37.84F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(797, 233).addBox(-1.0F, 24.2725F, 37.84F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(797, 233).addBox(-1.0F, 19.2725F, 37.84F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(38.3917F, -30.4919F, -0.0816F, 0.0F, -1.5708F, 0.0F));

		PartDefinition cube_r105 = wheel.addOrReplaceChild("cube_r105", CubeListBuilder.create().texOffs(786, 114).addBox(-4.0F, 41.6725F, 37.84F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(786, 114).addBox(-4.0F, 56.0725F, 37.84F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(38.8917F, -49.8919F, -0.0816F, 0.0F, -1.5708F, 0.0F));

		PartDefinition cube_r106 = wheel.addOrReplaceChild("cube_r106", CubeListBuilder.create().texOffs(784, 114).addBox(-1.999F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0517F, 3.9806F, 8.2184F, -1.5708F, -0.7854F, 1.5708F));

		PartDefinition cube_r107 = wheel.addOrReplaceChild("cube_r107", CubeListBuilder.create().texOffs(784, 114).addBox(-1.999F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0517F, 3.9806F, -8.2816F, 1.5708F, 0.7854F, 1.5708F));

		PartDefinition cube_r108 = wheel.addOrReplaceChild("cube_r108", CubeListBuilder.create().texOffs(788, 233).addBox(-5.999F, 2.0F, -1.0F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5517F, 3.9806F, -8.2816F, 1.5708F, 0.7854F, 1.5708F));

		PartDefinition cube_r109 = wheel.addOrReplaceChild("cube_r109", CubeListBuilder.create().texOffs(794, 233).addBox(0.001F, 2.0F, -1.0F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.4483F, 3.9806F, -8.2816F, 1.5708F, 0.7854F, 1.5708F));

		PartDefinition cube_r110 = wheel.addOrReplaceChild("cube_r110", CubeListBuilder.create().texOffs(789, 233).addBox(-4.999F, 2.0F, -1.0F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(794, 233).addBox(0.001F, 2.0F, -1.0F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.4483F, 3.9806F, 8.2184F, -1.5708F, -0.7854F, 1.5708F));

		PartDefinition cube_r111 = wheel.addOrReplaceChild("cube_r111", CubeListBuilder.create().texOffs(788, 233).addBox(-5.999F, -4.0F, -1.0F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(794, 233).addBox(0.001F, -4.0F, -1.0F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5517F, -4.0194F, -8.2816F, -1.5708F, 0.7854F, -1.5708F));

		PartDefinition cube_r112 = wheel.addOrReplaceChild("cube_r112", CubeListBuilder.create().texOffs(784, 114).addBox(-1.999F, -6.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0517F, -4.0194F, -8.2816F, -1.5708F, 0.7854F, -1.5708F));

		PartDefinition cube_r113 = wheel.addOrReplaceChild("cube_r113", CubeListBuilder.create().texOffs(788, 233).addBox(-5.999F, -4.0F, -1.0F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(794, 233).addBox(0.001F, -4.0F, -1.0F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.4483F, -4.0194F, 8.2184F, 1.5708F, -0.7854F, -1.5708F));

		PartDefinition cube_r114 = wheel.addOrReplaceChild("cube_r114", CubeListBuilder.create().texOffs(776, 114).addBox(-9.999F, -6.0F, -1.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(776, 114).addBox(-9.999F, -1.0F, -1.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(774, 114).addBox(-11.999F, -6.0F, -1.0F, 2.0F, 7.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(898, 193).addBox(-9.999F, -4.0F, -1.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(779, 114).addBox(-6.999F, -6.0F, -1.0F, 2.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0517F, -4.2194F, 7.9184F, 1.5708F, -0.7854F, -1.5708F));

		PartDefinition cube_r115 = wheel.addOrReplaceChild("cube_r115", CubeListBuilder.create().texOffs(784, 114).addBox(-1.999F, -6.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0517F, -4.0194F, 8.2184F, 1.5708F, -0.7854F, -1.5708F));

		PartDefinition cube_r116 = wheel.addOrReplaceChild("cube_r116", CubeListBuilder.create().texOffs(784, 114).addBox(-6.0F, 43.6725F, 37.84F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(38.8917F, -47.6919F, 12.2184F, 0.0F, -1.5708F, 0.0F));

		PartDefinition cube_r117 = wheel.addOrReplaceChild("cube_r117", CubeListBuilder.create().texOffs(784, 114).addBox(-6.0F, 43.6725F, 37.84F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(38.8917F, -47.6919F, -2.2816F, 0.0F, -1.5708F, 0.0F));

		PartDefinition burgee_sail = bottom.addOrReplaceChild("burgee_sail", CubeListBuilder.create(), PartPose.offset(3.66F, -163.0F, 77.0F));

		PartDefinition cube_r118 = burgee_sail.addOrReplaceChild("cube_r118", CubeListBuilder.create().texOffs(910, 354).addBox(-0.5F, -42.8275F, 14.34F, 2.0F, 18.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(13.34F, 33.3275F, -0.5F, 0.0F, -1.5708F, 0.0F));

		PartDefinition mid_sail = burgee_sail.addOrReplaceChild("mid_sail", CubeListBuilder.create(), PartPose.offset(-11.06F, -1.6725F, 0.0F));

		PartDefinition cube_r119 = mid_sail.addOrReplaceChild("cube_r119", CubeListBuilder.create().texOffs(909, 353).addBox(-0.5F, -42.8275F, 24.34F, 2.0F, 18.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(24.4F, 35.0F, -0.5F, 0.0F, -1.5708F, 0.0F));

		PartDefinition end_sail = mid_sail.addOrReplaceChild("end_sail", CubeListBuilder.create(), PartPose.offset(-10.9F, 1.0F, 0.0F));

		PartDefinition cube_r120 = end_sail.addOrReplaceChild("cube_r120", CubeListBuilder.create().texOffs(912, 356).addBox(-0.5F, -32.8275F, 35.34F, 2.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(902, 346).addBox(-0.5F, -42.8275F, 35.34F, 2.0F, 10.0F, 18.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(35.3F, 34.0F, -0.5F, 0.0F, -1.5708F, 0.0F));

		PartDefinition anchor = bottom.addOrReplaceChild("anchor", CubeListBuilder.create().texOffs(12, 0).addBox(-1.0F, -5.74F, -0.5F, 2.0F, 13.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 13).addBox(-5.0F, -4.74F, -0.5F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(10, 14).addBox(1.0F, -4.74F, -0.5F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(8, 19).addBox(-3.0F, -8.74F, -0.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(14, 19).addBox(1.0F, -8.74F, -0.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 16).addBox(-2.0F, -9.74F, -0.5F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(18, 12).addBox(-1.0F, -10.74F, -0.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(10, 17).addBox(-2.0F, -6.74F, -0.5F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(20, 14).addBox(-2.0F, 4.26F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(12, 22).addBox(-3.0F, 4.26F, -0.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(20, 18).addBox(-5.0F, 2.26F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 18).addBox(-6.0F, -0.74F, -0.5F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(20, 22).addBox(-5.0F, 0.26F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 24).addBox(-8.0F, 0.26F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(18, 0).addBox(-7.0F, -1.74F, -0.5F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(22, 0).addBox(-4.0F, 3.26F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(22, 4).addBox(1.0F, 4.26F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(16, 22).addBox(2.0F, 4.26F, -0.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(8, 22).addBox(3.0F, 3.26F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(22, 8).addBox(4.0F, 2.26F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(4, 24).addBox(4.0F, 0.26F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(4, 18).addBox(5.0F, -0.74F, -0.5F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(18, 6).addBox(6.0F, -1.74F, -0.5F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(24, 12).addBox(7.0F, 0.26F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(49.0F, -17.26F, 99.75F, 0.0F, 0.3927F, 0.0F));

		PartDefinition attachment = bottom.addOrReplaceChild("attachment", CubeListBuilder.create(), PartPose.offset(67.0F, -21.4085F, 77.3204F));

		PartDefinition cannon = attachment.addOrReplaceChild("cannon", CubeListBuilder.create().texOffs(746, 0).addBox(-4.0F, -15.4275F, -9.0793F, 8.0F, 8.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(746, 0).addBox(-4.0F, -15.4275F, 0.9207F, 8.0F, 8.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(749, 23).addBox(-3.0F, -14.4275F, 2.9207F, 6.0F, 6.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(746, 10).addBox(-2.0F, -13.4275F, 5.9207F, 4.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(717, 38).addBox(2.0F, -15.4275F, -25.0793F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(749, 32).addBox(-4.0F, -15.4275F, -25.0793F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(737, 39).addBox(-2.0F, -15.4275F, -25.0793F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(725, 39).addBox(-2.0F, -9.4275F, -25.0793F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(717, 0).addBox(-3.0F, -14.4275F, -23.0793F, 6.0F, 6.0F, 17.0F, new CubeDeformation(0.0F))
		.texOffs(746, 0).addBox(-4.0F, -15.4275F, -17.0793F, 8.0F, 8.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(717, 23).addBox(-4.0F, -15.4275F, -7.0793F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(793, 19).addBox(-3.0F, -7.4275F, -2.0793F, 6.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(785, 11).addBox(-3.0F, -1.4275F, 1.9207F, 6.0F, 1.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(755, 13).addBox(-1.5F, -16.4275F, -5.0793F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 3.0F, 0.0F, 0.0F, -1.5708F, -0.1309F));

		PartDefinition cube_r121 = cannon.addOrReplaceChild("cube_r121", CubeListBuilder.create().texOffs(717, 0).addBox(0.0F, -5.0F, 0.0F, 0.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -16.4275F, -4.0793F, -0.3763F, -0.4755F, -0.0678F));

		PartDefinition cannon_loaded = cannon.addOrReplaceChild("cannon_loaded", CubeListBuilder.create(), PartPose.offset(0.0F, -16.4275F, -4.0793F));

		PartDefinition cube_r122 = cannon_loaded.addOrReplaceChild("cube_r122", CubeListBuilder.create().texOffs(741, 20).addBox(0.0F, -5.0F, 0.0F, 0.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.3763F, -0.4755F, -0.0678F));

		PartDefinition seat_a = attachment.addOrReplaceChild("seat_a", CubeListBuilder.create().texOffs(806, 227).addBox(-5.0F, -17.0F, 7.8F, 10.0F, 15.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-13.0F, 1.5725F, -0.0793F, 0.0F, -1.5708F, -0.2618F));

		PartDefinition seat_r1 = seat_a.addOrReplaceChild("seat_r1", CubeListBuilder.create().texOffs(798, 219).addBox(-3.0F, -2.0F, -6.0F, 10.0F, 2.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, -0.75F, 5.9F, 0.0873F, 0.0F, 0.0F));

		PartDefinition harpoon = attachment.addOrReplaceChild("harpoon", CubeListBuilder.create().texOffs(746, 0).addBox(-4.0F, -14.5152F, 3.1444F, 8.0F, 8.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(746, 0).addBox(-4.0F, -14.5152F, 5.1444F, 8.0F, 8.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(749, 23).addBox(-3.0F, -13.5152F, 7.1444F, 6.0F, 6.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(746, 10).addBox(-2.0F, -12.5152F, 10.1444F, 4.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(717, 38).addBox(2.0F, -14.5152F, -12.8556F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(749, 32).addBox(-4.0F, -14.5152F, -12.8556F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(737, 39).addBox(-2.0F, -14.5152F, -12.8556F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(725, 39).addBox(-2.0F, -8.5152F, -12.8556F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(717, 0).addBox(-3.0F, -13.5152F, -10.8556F, 6.0F, 6.0F, 17.0F, new CubeDeformation(0.0F))
		.texOffs(746, 0).addBox(-4.0F, -14.5152F, -4.8556F, 8.0F, 8.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(793, 19).addBox(-3.0F, -7.4275F, -2.0793F, 6.0F, 9.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(785, 11).addBox(-3.0F, -1.4275F, 1.9207F, 6.0F, 1.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 2.0F, 0.0F, 0.0F, -1.5708F, -0.1309F));

		PartDefinition harpoon_loaded = harpoon.addOrReplaceChild("harpoon_loaded", CubeListBuilder.create().texOffs(756, 55).addBox(-1.0F, 10.2236F, -1.9123F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(736, 57).addBox(-1.0F, 13.2236F, -1.9123F, 2.0F, 13.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(736, 60).addBox(-1.0F, 26.2236F, -1.9123F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(736, 71).addBox(-1.0F, 30.2236F, -1.9123F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(736, 46).mirror().addBox(-4.0F, -0.7764F, -0.9123F, 8.0F, 11.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, -11.5915F, -29.3204F, 1.5708F, 0.0F, 0.0F));

		PartDefinition cube_r123 = harpoon_loaded.addOrReplaceChild("cube_r123", CubeListBuilder.create().texOffs(736, 46).addBox(-3.0877F, 6.7236F, 0.0F, 8.0F, 11.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -7.5F, 0.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition back = partdefinition.addOrReplaceChild("back", CubeListBuilder.create(), PartPose.offset(-22.3F, 12.3F, 16.0F));

		PartDefinition cube_r124 = back.addOrReplaceChild("cube_r124", CubeListBuilder.create().texOffs(771, 330).addBox(-12.0F, 15.6725F, 50.84F, 24.0F, 1.0F, 28.0F, new CubeDeformation(0.0F))
		.texOffs(785, 216).addBox(-20.5F, 22.9225F, 72.84F, 41.0F, 3.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(773, 201).addBox(-24.5F, 22.9225F, 54.84F, 4.0F, 3.0F, 22.0F, new CubeDeformation(0.0F))
		.texOffs(779, 200).addBox(-24.5F, 22.9225F, 51.84F, 4.0F, 35.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(770, 198).addBox(-25.0F, 52.9225F, 30.84F, 1.0F, 2.0F, 24.0F, new CubeDeformation(0.0F))
		.texOffs(778, 204).addBox(-25.0F, 37.2725F, 40.84F, 5.0F, 2.0F, 14.0F, new CubeDeformation(0.0F))
		.texOffs(863, 126).addBox(-24.0F, 38.9225F, 37.84F, 3.0F, 2.0F, 16.0F, new CubeDeformation(0.0F))
		.texOffs(856, 112).addBox(-24.0F, 40.9225F, 33.84F, 3.0F, 17.0F, 20.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(22.3F, -71.2225F, -16.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition cube_r125 = back.addOrReplaceChild("cube_r125", CubeListBuilder.create().texOffs(774, 233).addBox(-22.0F, 24.0278F, 80.4958F, 40.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(22.3F, -70.9725F, -14.0F, 0.0F, -1.5708F, -0.3927F));

		PartDefinition cube_r126 = back.addOrReplaceChild("cube_r126", CubeListBuilder.create().texOffs(789, 232).addBox(16.5F, -6.8765F, 75.2648F, 3.0F, 25.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(770, 203).addBox(-19.5F, -6.8765F, 75.2648F, 3.0F, 25.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(782, 7).addBox(-20.0F, 11.0278F, 74.4958F, 40.0F, 19.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(22.3F, -70.9725F, -16.0F, 0.0F, -1.5708F, -0.3927F));

		PartDefinition cube_r127 = back.addOrReplaceChild("cube_r127", CubeListBuilder.create().texOffs(802, 6).addBox(-2.0F, -10.5F, -5.5F, 4.0F, 20.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-44.5225F, -19.2462F, -16.0F, 0.0F, -1.5708F, -0.7854F));

		PartDefinition cube_r128 = back.addOrReplaceChild("cube_r128", CubeListBuilder.create().texOffs(903, 105).addBox(-37.7951F, -1.2278F, 51.84F, 9.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(11.3F, -70.9725F, -16.0F, 1.5708F, -0.7854F, -1.5708F));

		PartDefinition cube_r129 = back.addOrReplaceChild("cube_r129", CubeListBuilder.create().texOffs(783, 0).addBox(-27.0842F, 9.8873F, 51.84F, 10.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(11.3F, -70.9725F, -16.0F, 1.5708F, -1.1781F, -1.5708F));

		PartDefinition cube_r130 = back.addOrReplaceChild("cube_r130", CubeListBuilder.create().texOffs(868, 0).addBox(-12.0F, 15.6725F, 51.84F, 24.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(11.3F, -70.9725F, -16.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition cube_r131 = back.addOrReplaceChild("cube_r131", CubeListBuilder.create().texOffs(917, 0).addBox(17.0842F, 9.8873F, 51.84F, 10.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(11.3F, -70.9725F, -16.0F, -1.5708F, -1.1781F, 1.5708F));

		PartDefinition cube_r132 = back.addOrReplaceChild("cube_r132", CubeListBuilder.create().texOffs(882, 0).addBox(28.7951F, -1.2278F, 51.84F, 9.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(11.3F, -70.9725F, -16.0F, -1.5708F, -0.7854F, 1.5708F));

		PartDefinition cube_r133 = back.addOrReplaceChild("cube_r133", CubeListBuilder.create().texOffs(903, 105).addBox(-37.7951F, -1.2278F, 51.84F, 9.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.7F, -70.9725F, -16.0F, 1.5708F, -0.7854F, -1.5708F));

		PartDefinition cube_r134 = back.addOrReplaceChild("cube_r134", CubeListBuilder.create().texOffs(783, 0).addBox(-27.0842F, 9.8873F, 51.84F, 10.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.7F, -70.9725F, -16.0F, 1.5708F, -1.1781F, -1.5708F));

		PartDefinition cube_r135 = back.addOrReplaceChild("cube_r135", CubeListBuilder.create().texOffs(868, 0).addBox(-12.0F, 15.6725F, 51.84F, 24.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.7F, -70.9725F, -16.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition cube_r136 = back.addOrReplaceChild("cube_r136", CubeListBuilder.create().texOffs(917, 0).addBox(17.0842F, 9.8873F, 51.84F, 10.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.7F, -70.9725F, -16.0F, -1.5708F, -1.1781F, 1.5708F));

		PartDefinition cube_r137 = back.addOrReplaceChild("cube_r137", CubeListBuilder.create().texOffs(882, 0).addBox(28.7951F, -1.2278F, 51.84F, 9.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.7F, -70.9725F, -16.0F, -1.5708F, -0.7854F, 1.5708F));

		PartDefinition cube_r138 = back.addOrReplaceChild("cube_r138", CubeListBuilder.create().texOffs(882, 0).addBox(28.7951F, -1.2278F, 51.84F, 9.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(22.55F, -70.9725F, -16.0F, -1.5708F, -0.7854F, 1.5708F));

		PartDefinition cube_r139 = back.addOrReplaceChild("cube_r139", CubeListBuilder.create().texOffs(917, 0).addBox(17.0842F, 9.8873F, 51.84F, 10.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(22.55F, -70.9725F, -16.0F, -1.5708F, -1.1781F, 1.5708F));

		PartDefinition cube_r140 = back.addOrReplaceChild("cube_r140", CubeListBuilder.create().texOffs(868, 0).addBox(-12.0F, 15.6725F, 51.84F, 24.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(22.55F, -70.9725F, -16.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition cube_r141 = back.addOrReplaceChild("cube_r141", CubeListBuilder.create().texOffs(783, 0).addBox(-27.0842F, 9.8873F, 51.84F, 10.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(22.55F, -70.9725F, -16.0F, 1.5708F, -1.1781F, -1.5708F));

		PartDefinition cube_r142 = back.addOrReplaceChild("cube_r142", CubeListBuilder.create().texOffs(807, 337).addBox(-38.7951F, -1.2278F, -78.84F, 10.0F, 1.0F, 28.0F, new CubeDeformation(0.0F))
		.texOffs(825, 357).addBox(-40.7951F, -1.2278F, -78.84F, 2.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(825, 357).addBox(-40.7951F, -1.2278F, -58.84F, 2.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(22.3F, -71.2225F, -16.0F, -1.5708F, 0.7854F, -1.5708F));

		PartDefinition cube_r143 = back.addOrReplaceChild("cube_r143", CubeListBuilder.create().texOffs(865, 360).addBox(-27.0842F, 9.8873F, -78.84F, 10.0F, 1.0F, 28.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(22.3F, -71.2225F, -16.0F, -1.5708F, 1.1781F, -1.5708F));

		PartDefinition cube_r144 = back.addOrReplaceChild("cube_r144", CubeListBuilder.create().texOffs(865, 360).addBox(-27.0842F, 9.8873F, 50.84F, 10.0F, 1.0F, 28.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(22.3F, -71.2225F, -16.0F, 1.5708F, -1.1781F, -1.5708F));

		PartDefinition cube_r145 = back.addOrReplaceChild("cube_r145", CubeListBuilder.create().texOffs(825, 357).addBox(-40.7951F, -1.2278F, 50.84F, 2.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(825, 357).addBox(-40.7951F, -1.2278F, 50.84F, 2.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(825, 357).addBox(-40.7951F, -1.2278F, 70.84F, 2.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(807, 337).addBox(-38.7951F, -1.2278F, 50.84F, 10.0F, 1.0F, 28.0F, new CubeDeformation(0.0F))
		.texOffs(807, 337).addBox(-38.7951F, -1.2278F, 50.84F, 10.0F, 1.0F, 28.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(22.3F, -71.2225F, -16.0F, 1.5708F, -0.7854F, -1.5708F));

		PartDefinition cube_r146 = back.addOrReplaceChild("cube_r146", CubeListBuilder.create().texOffs(903, 105).addBox(-37.7951F, -1.2278F, 51.84F, 9.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(22.55F, -70.9725F, -16.0F, 1.5708F, -0.7854F, -1.5708F));

		PartDefinition cube_r147 = back.addOrReplaceChild("cube_r147", CubeListBuilder.create().texOffs(773, 201).addBox(-24.5F, 22.6725F, -76.84F, 4.0F, 3.0F, 22.0F, new CubeDeformation(0.0F))
		.texOffs(779, 200).addBox(-24.5F, 22.6725F, -54.84F, 4.0F, 35.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(856, 112).addBox(-24.0F, 40.6725F, -53.84F, 3.0F, 17.0F, 20.0F, new CubeDeformation(0.0F))
		.texOffs(770, 198).addBox(-25.0F, 52.6725F, -54.84F, 1.0F, 2.0F, 24.0F, new CubeDeformation(0.0F))
		.texOffs(778, 204).addBox(-25.0F, 37.0225F, -54.84F, 5.0F, 2.0F, 14.0F, new CubeDeformation(0.0F))
		.texOffs(863, 126).addBox(-24.0F, 38.6725F, -53.84F, 3.0F, 2.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(22.3F, -70.9725F, -16.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition cube_r148 = back.addOrReplaceChild("cube_r148", CubeListBuilder.create().texOffs(785, 213).addBox(-2.0F, -3.0F, -10.0F, 4.0F, 3.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-54.54F, -45.3F, 2.5F, 0.0F, 1.5708F, 0.3927F));

		PartDefinition cube_r149 = back.addOrReplaceChild("cube_r149", CubeListBuilder.create().texOffs(785, 213).addBox(-2.0F, -3.0F, 0.0F, 4.0F, 3.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-54.54F, -45.3F, -34.5F, 0.0F, -1.5708F, 0.3927F));

		PartDefinition cube_r150 = back.addOrReplaceChild("cube_r150", CubeListBuilder.create().texOffs(787, 202).addBox(-44.0833F, 52.6725F, -54.0984F, 1.0F, 2.0F, 13.0F, new CubeDeformation(0.0F))
		.texOffs(810, 74).addBox(-42.891F, 38.6725F, -52.1313F, 3.0F, 19.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(773, 208).addBox(-44.0889F, 36.9725F, -53.1317F, 5.0F, 2.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(22.3F, -70.9725F, -16.0F, 0.0F, 1.1781F, 0.0F));

		PartDefinition cube_r151 = back.addOrReplaceChild("cube_r151", CubeListBuilder.create().texOffs(787, 202).addBox(-44.0833F, 52.6725F, 41.0984F, 1.0F, 2.0F, 13.0F, new CubeDeformation(0.0F))
		.texOffs(810, 74).addBox(-42.891F, 38.6725F, 40.1313F, 3.0F, 19.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(773, 208).addBox(-44.0889F, 36.9725F, 41.1317F, 5.0F, 2.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(22.3F, -70.9725F, -16.0F, 0.0F, -1.1781F, 0.0F));

		PartDefinition cube_r152 = back.addOrReplaceChild("cube_r152", CubeListBuilder.create().texOffs(789, 214).addBox(-24.0F, 38.0225F, -44.84F, 38.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.7F, -70.9725F, -21.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition left = partdefinition.addOrReplaceChild("left", CubeListBuilder.create(), PartPose.offset(0.0F, -62.6725F, 0.0F));

		PartDefinition cube_r153 = left.addOrReplaceChild("cube_r153", CubeListBuilder.create().texOffs(302, 521).addBox(-24.5F, -21.3257F, -52.9874F, 4.0F, 11.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 4.0F, 0.0F, 0.0F, 1.5708F, -1.1257F));

		PartDefinition cube_r154 = left.addOrReplaceChild("cube_r154", CubeListBuilder.create().texOffs(776, 214).addBox(-24.5F, 41.6725F, -33.8401F, 4.0F, 16.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(771, 206).addBox(-25.0F, 52.6725F, -30.84F, 1.0F, 2.0F, 9.0F, new CubeDeformation(0.0F))
		.texOffs(889, 151).addBox(-24.0F, 45.6725F, -30.84F, 3.0F, 12.0F, 9.0F, new CubeDeformation(0.0F))
		.texOffs(772, 203).addBox(-25.0F, 43.6725F, -30.84F, 4.0F, 2.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(773, 220).addBox(-25.0F, 47.6725F, -21.84F, 4.0F, 3.0F, 31.0F, new CubeDeformation(0.0F))
		.texOffs(850, 102).addBox(-24.0F, 50.6725F, -21.84F, 3.0F, 7.0F, 31.0F, new CubeDeformation(0.0F))
		.texOffs(786, 214).addBox(-25.0F, 52.6725F, -21.84F, 1.0F, 2.0F, 31.0F, new CubeDeformation(0.0F))
		.texOffs(878, 82).addBox(-24.0F, 45.6735F, 9.16F, 3.0F, 12.0F, 18.0F, new CubeDeformation(0.0F))
		.texOffs(797, 222).addBox(-25.0F, 52.6725F, 9.16F, 1.0F, 2.0F, 18.0F, new CubeDeformation(0.0F))
		.texOffs(805, 240).addBox(-25.0F, 43.6725F, 8.16F, 4.0F, 2.0F, 19.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 4.0F, 0.0F, 0.0F, 1.5708F, 0.0F));


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

    @Override
    public void setupAnim(BoatRenderState state) {
        super.setupAnim(state);

        // ── Read from per-entity render state, not shared model fields ──
        int   sailLevel      = 3;
        float rudderTurn     = 0f;
        float waveTime       = 0f;
        boolean anchorDeployed = false;
        float boatSpeed      = 0f;
        boolean hasBanner    = false;
        int   attachmentType = 0;
        float bowRelativeYaw = 0f;
        boolean hasAmmo      = false;
        boolean harpoonDeployed = false;
        float bowPitch = 0f;
        boolean bowOccupied  = false;
        float windRelativeYaw = 0f;
        float windStrength    = 0.8f;
        float sailFill = 1f;

        if (state instanceof CustomBoatRenderState s) {
            sailLevel      = s.sailLevel;
            rudderTurn     = s.rudderTurn;
            waveTime       = s.waveTime;
            anchorDeployed = s.anchorDeployed;
            boatSpeed      = s.boatSpeed;
            hasBanner      = s.hasBanner;
            attachmentType = s.attachmentType;
            bowRelativeYaw = s.bowRelativeYaw;
            hasAmmo        = s.hasAmmo;
            harpoonDeployed = s.harpoonDeployed;
            bowPitch = s.bowPitch;
            bowOccupied  = s.bowOccupied;
            windRelativeYaw = s.windRelativeYaw;
            windStrength    = s.windStrength;
            sailFill = s.sailFill;
        }

        this.root.xScale = SCALE;
        this.root.yScale = SCALE;
        this.root.zScale = SCALE;

        // ── Sail furl state ──
        this.sail_1.visible = (sailLevel == 0);
        this.sail_2.visible = (sailLevel == 1);
        this.sail_3.visible = (sailLevel == 2);
        this.sail_4.visible = (sailLevel == 3);

        // ── Sail fill / luff (visual point-of-sail) ─────────────────────
        // Each raised sail has two child shapes:
        //   caught_wind_# = bellied, drawing wind
        //   normal_#      = flat / slack (luffing)
        // Swap between them based on how full the sail is. A small hysteresis
        // avoids flicker right at the threshold; a light flutter on the whole
        // sail bone sells the "flapping" when luffing.
        boolean caught = sailFill > 0.45f;   // threshold: bellied vs. slack

        // sail_2 → level 1, sail_3 → level 2, sail_4 → level 3
        this.caught_wind_2.visible = (sailLevel == 1) && caught;
        this.normal_2.visible      = (sailLevel == 1) && !caught;
        this.caught_wind_3.visible = (sailLevel == 2) && caught;
        this.normal_3.visible      = (sailLevel == 2) && !caught;
        this.caught_wind_4.visible = (sailLevel == 3) && caught;
        this.normal_4.visible      = (sailLevel == 3) && !caught;

        // Gentle motion on the whole sail bone: a soft breathe when filled,
        // a quicker shiver when luffing. Much subtler than the old swing.
        /*if (sailLevel > 0) {
            float luff   = 1f - sailFill;
            float shiver = luff * (0.05f + windStrength * 0.06f);
            float flap   = Mth.sin(waveTime * 0.8f) * shiver
                         + Mth.sin(waveTime * 0.55f + 1.3f) * shiver * 0.5f;
            float breathe = Mth.sin(waveTime * 0.14f) * 0.015f * sailFill;
            this.sail_full.yRot = flap + breathe;
        } else {
            this.sail_full.yRot = 0f;
        }*/

        // ── Spanker sail ──
        boolean moving = sailLevel > 0 && boatSpeed > 0.02f;
        this.spanker_2.visible = !moving;
        this.spanker_1.visible = moving;

        // ── Rudder ──
        this.rudder.yRot = rudderTurn * -0.6f;

        // ── Wheel ──
        this.wheel.xScale = 0.75f;
        this.wheel.yScale = 0.75f;
        this.wheel.zScale = 0.75f;
        this.wheel.xRot = rudderTurn * -2.0f;

        // ── Anchor ──
        this.anchor.visible = !anchorDeployed;

        // ── Burgee flag ──
        float flutter = 0.1f + Math.min(boatSpeed * 3f, 0.3f);
        float t = waveTime;
        this.burgee_sail.visible = !hasBanner;
        /*this.burgee_sail.yRot = Mth.sin(t * 0.12f) * flutter
                              + Mth.sin(t * 0.07f + 2.0f) * flutter * 0.4f;*/

        /*this.burgee_sail.visible = !hasBanner;
        float windFlutter = 0.06f + windStrength * 0.18f;
        float wob = Mth.sin(waveTime * 0.18f) * windFlutter
                  + Mth.sin(waveTime * 0.11f + 1.7f) * windFlutter * 0.4f;
        // Base alignment to the wind + flutter. (Negate if your flag points the
        // wrong way — model axis conventions vary.)
        this.burgee_sail.yRot = windRelativeYaw + wob;*/

        float windFlutter = 0.05f + windStrength * 0.16f;   // amplitude from wind
        float waveSpeed   = 0.22f + windStrength * 0.10f;    // faster ripple in strong wind
        float phase       = waveTime * waveSpeed;

        // Root: align to wind + a gentle base sway.
        float baseSway = Mth.sin(phase) * windFlutter * 0.5f;
        this.burgee_sail.yRot = windRelativeYaw + baseSway;

        // Mid segment: phase-delayed, a bit more amplitude (ripple building).
        this.mid_sail.yRot = Mth.sin(phase - 0.9f) * windFlutter * 0.9f + Mth.sin(phase * 1.7f - 0.4f) * windFlutter * 0.3f;

        // End segment: further delayed, largest whip (tip of the flag).
        this.end_sail.yRot = Mth.sin(phase - 1.8f) * windFlutter * 1.3f + Mth.sin(phase * 1.9f - 1.1f) * windFlutter * 0.5f;

        // ── Attachment visibility + swivel + pitch ────────────────────
        boolean showCannon  = (attachmentType == CustomBoatEntity.ATTACHMENT_CANNON);
        boolean showHarpoon = (attachmentType == CustomBoatEntity.ATTACHMENT_HARPOON);

        this.cannon.visible  = showCannon;
        this.harpoon.visible = showHarpoon;

        // Loaded state
        if (showCannon) {
            this.cannon_loaded.visible = hasAmmo;
        }
        if (showHarpoon) {
            this.harpoon_loaded.visible = hasAmmo && !harpoonDeployed;
        }

        // ── SWIVEL + CONDITIONAL LIFT ──
        if (showCannon || showHarpoon) {
            this.attachment.visible = true;
            this.attachment.yRot = bowRelativeYaw;

            // Only lift when someone is sitting in the bow seat
            if (bowOccupied) {
                this.attachment.y -= 4.35f;  // 5/16 block up
            }
        } else {
            this.attachment.visible = false;
            this.attachment.yRot = 0f;
        }

        // ── ELEVATION (pitch) ──
        if (showCannon) {
            this.cannon.xRot = bowPitch;  // negated — flip sign if wrong //bowPitch
        }
        if (showHarpoon) {
            this.harpoon.xRot = bowPitch;  // negated — flip sign if wrong
        }

        // ── Hull motion ──
        float rockRoll  = Mth.sin(t * 0.04f)         * 0.012f;
        float rockPitch = Mth.sin(t * 0.031f + 1.0f) * 0.009f;
        float bank = Mth.clamp(rudderTurn * 0.07f, -0.07f, 0.07f);

        if (state instanceof CustomBoatRenderState s && s.sinkProgress > 0.01f) {
            float sp = s.sinkProgress;  // 0 → 1

            // ── SINK ANIMATION ──
            // Tilt sideways (roll) — dramatic lean as it goes down
            float sinkTilt = s.sinkRoll * sp;

            // Bow dips slightly
            float sinkPitch = sp * 0.15f;

            // Combine with normal motion (normal motion fades out)
            float normalFade = 1f - sp;
            this.root.xRot = (rockRoll + bank) * normalFade + sinkTilt;
            this.root.zRot = rockPitch * normalFade + sinkPitch;

            // Slight Y offset to visually push into water
            // (the entity position already handles the actual sinking,
            // this just adds visual "drag into the waves")
            this.root.y += sp * 3f;  // model units, pushes hull down

        } else {
            // Normal sailing
            this.root.xRot = rockRoll + bank;
            this.root.zRot = rockPitch;
        }
    }

}