package net.ent.entstupidstuff.client.render.entity.model;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.entity.mob.SkeletonPirateCaptainEntity;
import net.minecraft.client.model.Dilation;
import net.minecraft.client.model.ModelData;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.ModelPartBuilder;
import net.minecraft.client.model.ModelPartData;
import net.minecraft.client.model.ModelTransform;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.entity.model.EntityModelPartNames;
import net.minecraft.client.render.entity.model.SkeletonEntityModel;
import net.minecraft.util.Identifier;

public class SkeletonPirateCaptainModel<T extends SkeletonPirateCaptainEntity> extends SkeletonEntityModel<T> {
	public ModelPart rightarm;
    public ModelPart leftarm;


	public SkeletonPirateCaptainModel(ModelPart modelPart) {
		super(modelPart);
		this.head.setPivot(this.head.pivotX, this.head.pivotY, this.head.pivotZ);
        this.body.setPivot(this.body.pivotX, this.body.pivotY, this.body.pivotZ);
        this.leftLeg.setPivot(this.leftLeg.pivotX, this.leftLeg.pivotY, this.leftLeg.pivotZ);

        this.rightarm = modelPart.getChild(EntityModelPartNames.RIGHT_ARM);
        this.leftarm = modelPart.getChild(EntityModelPartNames.LEFT_ARM);
	}

	
	public static TexturedModelData getTexturedModelData() {

		//TODO: Refactor the Extra Model 
		//so that it works natively. Also convert the Clothing into Skeleton OuterLayer Render used in Strays


		ModelData modelData = SkeletonEntityModel.getModelData(Dilation.NONE, 0.0F);

		ModelPartData modelPartData = modelData.getRoot();
		SkeletonEntityModel.addLimbs(modelPartData);

		//float lower = + 22.0F;

		modelData.getRoot().addChild(
			EntityModelPartNames.HEAD, ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.0F))
			.uv(82, 26).cuboid(-5.0F, -6.0F, -5.1F, 10.0F, 1.0F, 10.0F, new Dilation(0.0F))
			.uv(64, 0).cuboid(-7.0F, -8.0F, -5.1F, 14.0F, 2.0F, 10.0F, new Dilation(0.0F))
			.uv(92, 22).cuboid(-5.0F, -11.0F, -5.1F, 10.0F, 3.0F, 1.0F, new Dilation(0.0F))
			.uv(92, 12).cuboid(-4.0F, -10.0F, -4.1F, 8.0F, 2.0F, 8.0F, new Dilation(0.0F))
			.uv(92, 22).cuboid(-5.0F, -11.0F, 3.9F, 10.0F, 3.0F, 1.0F, new Dilation(0.0F))
			.uv(90, 41).cuboid(-5.0F, -3.0F, -4.1F, 10.0F, 8.0F, 2.0F, new Dilation(0.0F)),
			ModelTransform.pivot(0.0F, 24.0F, 0.0F)
		);

		modelData.getRoot().addChild(
			EntityModelPartNames.RIGHT_ARM, ModelPartBuilder.create().uv(40, 16).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 12.0F, 2.0F, new Dilation(0.0F))
			.uv(112, 22).cuboid(-2.0F, -2.0F, -2.0F, 4.0F, 8.0F, 4.0F, new Dilation(0.0F))
			.uv(112, 37).cuboid(-2.0F, 5.0F, -2.0F, 4.0F, 2.0F, 4.0F, new Dilation(0.5F)), //Changed
			ModelTransform.pivot(-5.0F, 2.0F, 0.0F) //CC
		);

        modelData.getRoot().addChild(
			EntityModelPartNames.LEFT_ARM, ModelPartBuilder.create().uv(48, 16).mirrored().cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 12.0F, 2.0F, new Dilation(0.0F)).mirrored(false)
			.uv(112, 22).mirrored().cuboid(-2.0F, -2.0F, -2.0F, 4.0F, 8.0F, 4.0F, new Dilation(0.0F)).mirrored(false)
			.uv(112, 37).mirrored().cuboid(-2.0F, 5.0F, -2.0F, 4.0F, 2.0F, 4.0F, new Dilation(0.5F)).mirrored(false), //Changed
			ModelTransform.pivot(5.0F, 2.0F, 0.0F) //CC
		);

		modelData.getRoot().addChild(
			EntityModelPartNames.LEFT_LEG,  ModelPartBuilder.create().uv(8, 16).mirrored().cuboid(-1.0F, 0.0F, -1.1F, 2.0F, 12.0F, 2.0F, new Dilation(0.0F)).mirrored(false)
			.uv(112, 0).mirrored().cuboid(-2.0F, 0.0F, -2.1F, 4.0F, 7.0F, 4.0F, new Dilation(0.0F)).mirrored(false)
			.uv(87, 12).cuboid(-1.5F, 7.0F, -1.6F, 3.0F, 2.0F, 3.0F, new Dilation(0.0F)), 
			ModelTransform.pivot(2.0F, 12.0F, 0.0F)
		);

		modelData.getRoot().addChild(
			EntityModelPartNames.RIGHT_LEG,   ModelPartBuilder.create().uv(0, 16).cuboid(-1.0F, 0.0F, -1.1F, 2.0F, 12.0F, 2.0F, new Dilation(0.0F))
			.uv(112, 0).cuboid(-2.0F, 0.0F, -2.1F, 4.0F, 8.0F, 4.0F, new Dilation(0.0F)),
			ModelTransform.pivot(-2.0F, 12.0F, 0.0F)
		);

		modelData.getRoot().addChild(
			EntityModelPartNames.BODY, ModelPartBuilder.create().uv(16, 16).cuboid(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new Dilation(0.0F))
			.uv(64, 37).cuboid(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new Dilation(0.0F))
			.uv(64, 12).cuboid(-4.5F, -2.0F, -2.5F, 9.0F, 19.0F, 5.0F, new Dilation(0.0F)),
			ModelTransform.pivot(0.0F, 0.0F, 0.0F)
		);

		return TexturedModelData.of(modelData, 128, 64);
	}

	public Identifier getTexture(T entity) {
		return Identifier.of(EntStupidStuff.MOD_ID, "textures/entity/skeleton_pirate_captain.png");
	}

}