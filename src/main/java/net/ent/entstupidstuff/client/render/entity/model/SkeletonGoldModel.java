package net.ent.entstupidstuff.client.render.entity.model;

import net.ent.entstupidstuff.entity.mob.SkeletonPirateCaptainEntity;
import net.minecraft.client.model.Dilation;
import net.minecraft.client.model.ModelData;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.ModelPartBuilder;
import net.minecraft.client.model.ModelTransform;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.entity.model.EntityModelPartNames;
import net.minecraft.client.render.entity.model.SkeletonEntityModel;

public class SkeletonGoldModel <T extends SkeletonPirateCaptainEntity> extends SkeletonEntityModel<T>{

    public ModelPart rightarm;
    public ModelPart leftarm;

    public SkeletonGoldModel(ModelPart modelPart) {
        super(modelPart);

        this.head.setPivot(this.head.pivotX, this.head.pivotY, this.head.pivotZ);
        this.body.setPivot(this.body.pivotX, this.body.pivotY, this.body.pivotZ);
        this.leftLeg.setPivot(this.leftLeg.pivotX, this.leftLeg.pivotY, this.leftLeg.pivotZ);

        this.rightarm = modelPart.getChild(EntityModelPartNames.RIGHT_ARM);
        this.leftarm = modelPart.getChild(EntityModelPartNames.LEFT_ARM);
    }

    public static TexturedModelData getTexturedModelData() {

        ModelData modelData = SkeletonEntityModel.getModelData(Dilation.NONE, 0.0F);

		/*ModelPartData modelPartData = */modelData.getRoot();
		//SkeletonEntityModel.addLimbs(modelPartData);

        modelData.getRoot().addChild(
			EntityModelPartNames.HEAD, ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.0F))
            /*.uv(32, 0).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.0F))*/, ModelTransform.pivot(0.0F, 0.0F, 0.0F)
        );
		
        modelData.getRoot().addChild(
            EntityModelPartNames.BODY, ModelPartBuilder.create().uv(16, 16).cuboid(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new Dilation(0.0F))
		    .uv(15, 32).cuboid(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new Dilation(0.25F))
            .uv(1, 48).cuboid(-4.0F, 12.0F, -2.0F, 8.0F, 12.0F, 4.0F, new Dilation(0.25F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F)
        );

        modelData.getRoot().addChild(
            EntityModelPartNames.RIGHT_ARM, ModelPartBuilder.create().uv(56, 16).mirrored().cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 12.0F, 2.0F, new Dilation(0.0F)).mirrored(false)
		    .uv(56, 30).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 12.0F, 2.0F, new Dilation(0.25F)), ModelTransform.pivot(5.0F, 2.0F, 0.0F)
            );

		modelData.getRoot().addChild(
            EntityModelPartNames.LEFT_ARM, ModelPartBuilder.create().uv(40, 16).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 12.0F, 2.0F, new Dilation(0.0F))
		    .uv(40, 30).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 12.0F, 2.0F, new Dilation(0.25F)), ModelTransform.pivot(-5.0F, 2.0F, 0.0F)
        );

		modelData.getRoot().addChild(
            EntityModelPartNames.RIGHT_LEG, ModelPartBuilder.create().uv(48, 48).cuboid(-6.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F))
		    .uv(0, 30).cuboid(-5.0F, 0.0F, -1.0F, 2.0F, 12.0F, 2.0F, new Dilation(0.25F))
		    .uv(0, 16).cuboid(-5.0F, 0.0F, -1.0F, 2.0F, 12.0F, 2.0F, new Dilation(0.25F)), ModelTransform.pivot(2.0F, 12.0F, 0.0F)
        );

		modelData.getRoot().addChild(
            EntityModelPartNames.LEFT_LEG, ModelPartBuilder.create().uv(48, 16).mirrored().cuboid(-1.0F, 0.0F, -1.0F, 2.0F, 12.0F, 2.0F, new Dilation(0.0F)).mirrored(false)
		    .uv(32, 48).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.25F))
		    .uv(48, 30).mirrored().cuboid(-1.0F, 0.0F, -1.0F, 2.0F, 12.0F, 2.0F, new Dilation(0.25F)).mirrored(false), ModelTransform.pivot(2.0F, 12.0F, 0.0F)
        );
		
        return TexturedModelData.of(modelData, 64, 64);

    }    
}
