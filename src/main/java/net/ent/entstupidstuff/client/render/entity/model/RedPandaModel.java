package net.ent.entstupidstuff.client.render.entity.model;

import com.google.common.collect.ImmutableList;

import net.ent.entstupidstuff.entity.passive.RedPandaEntity;
import net.minecraft.client.model.Dilation;
import net.minecraft.client.model.ModelData;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.ModelPartBuilder;
import net.minecraft.client.model.ModelPartData;
import net.minecraft.client.model.ModelTransform;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.entity.model.AnimalModel;
import net.minecraft.client.render.entity.model.EntityModelPartNames;

public class RedPandaModel<T extends RedPandaEntity> extends AnimalModel<T> {
    
    public final ModelPart head;
	private final ModelPart body;
	private final ModelPart rightHindLeg;
	private final ModelPart leftHindLeg;
	private final ModelPart rightFrontLeg;
	private final ModelPart leftFrontLeg;
	//private final ModelPart tail;

    public RedPandaModel(ModelPart root) {
		super(true, 8.0F, 3.35F);
		this.head = root.getChild(EntityModelPartNames.HEAD);
		this.body = root.getChild(EntityModelPartNames.BODY);
		this.rightHindLeg = root.getChild(EntityModelPartNames.RIGHT_HIND_LEG);
		this.leftHindLeg = root.getChild(EntityModelPartNames.LEFT_HIND_LEG);
		this.rightFrontLeg = root.getChild(EntityModelPartNames.RIGHT_FRONT_LEG);
		this.leftFrontLeg = root.getChild(EntityModelPartNames.LEFT_FRONT_LEG);
		//this.tail = this.body.getChild(EntityModelPartNames.TAIL);
	}

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();

        modelPartData.addChild(
			EntityModelPartNames.HEAD,
            ModelPartBuilder.create()
            .uv(0, 19).cuboid(-5.0F, -4.0F, -7.0F, 10.0F, 8.0F, 7.0F, new Dilation(0.0F))
            .uv(40, 8).cuboid(EntityModelPartNames.NOSE, -2.0F, 1.0F, -13.25F, 4.0F, 2.0F, 1.0F, new Dilation(0.0F))
            .uv(40, 11).cuboid(EntityModelPartNames.LEFT_EAR, -6.0F, -7.0F, -11.25F, 3.0F, 3.0F, 1.0F, new Dilation(0.0F))
            .uv(32, 43).cuboid(EntityModelPartNames.LEFT_EAR, -3.0F, -6.0F, -11.25F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
            .uv(36, 43).cuboid(EntityModelPartNames.RIGHT_EAR, 2.0F, -6.0F, -11.25F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
		    .uv(40, 15).cuboid(EntityModelPartNames.RIGHT_EAR, 3.0F, -7.0F, -11.25F, 3.0F, 3.0F, 1.0F, new Dilation(0.0F)),
        ModelTransform.pivot(2.0F, 15.0F, -6.0F));

        modelPartData.addChild(
            EntityModelPartNames.BODY,
            ModelPartBuilder.create()
            .uv(0, 0).cuboid(-4.0F, -3.0F, 0.0F, 8.0F, 7.0F, 12.0F, new Dilation(0.0F))
            .uv(0, 34).cuboid(EntityModelPartNames.TAIL, -2.0F, -2.0F, 0.0F, 4.0F, 4.0F, 12.0F, new Dilation(0.0F)),
            ModelTransform.pivot(2.0F, 15.0F, -6.0F));


        //ModelPartData body = modelPartData.addChild(EntityModelPartNames.BODY, ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -3.0F, 0.0F, 8.0F, 7.0F, 12.0F, new Dilation(0.0F)), ModelTransform.pivot(2.0F, 15.0F, -6.0F));

        ModelPartData leftArm = modelPartData.addChild(EntityModelPartNames.LEFT_FRONT_LEG, ModelPartBuilder.create().uv(34, 19).mirrored().cuboid(-1.0F, 0.0F, -1.5F, 3.0F, 5.0F, 3.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(-1.0F, 19.0F, -4.5F));

		ModelPartData rightArm = modelPartData.addChild(EntityModelPartNames.RIGHT_FRONT_LEG, ModelPartBuilder.create().uv(34, 19).cuboid(-1.5F, 0.0F, -1.5F, 3.0F, 5.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(4.5F, 19.0F, -4.5F));

		ModelPartData rightLeg = modelPartData.addChild(EntityModelPartNames.RIGHT_HIND_LEG, ModelPartBuilder.create().uv(34, 19).cuboid(3.5F, 0.0F, -1.5F, 3.0F, 5.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(-0.5F, 19.0F, 4.5F));

		ModelPartData leftLeg = modelPartData.addChild(EntityModelPartNames.LEFT_HIND_LEG, ModelPartBuilder.create().uv(34, 19).mirrored().cuboid(-1.5F, 0.0F, -1.5F, 3.0F, 5.0F, 3.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(-0.5F, 19.0F, 4.5F));

		//ModelPartData tail = modelPartData.addChild(EntityModelPartNames.TAIL, ModelPartBuilder.create().uv(0, 34).cuboid(-2.0F, -2.0F, 0.0F, 4.0F, 4.0F, 12.0F, new Dilation(0.0F)), ModelTransform.pivot(2.0F, 14.0F, 6.0F));

        return TexturedModelData.of(modelData, 64, 64);
    }

    @Override
	protected Iterable<ModelPart> getHeadParts() {
		return ImmutableList.<ModelPart>of(this.head);
	}

	@Override
	protected Iterable<ModelPart> getBodyParts() {
		return ImmutableList.<ModelPart>of(this.body, this.rightHindLeg, this.leftHindLeg, this.rightFrontLeg, this.leftFrontLeg);
	}

    @Override
    public void setAngles(T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw,
            float headPitch) {
        
    }


    
}
