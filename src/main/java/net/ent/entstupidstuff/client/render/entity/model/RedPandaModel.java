package net.ent.entstupidstuff.client.render.entity.model;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartNames;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;

@SuppressWarnings("unused")
public class RedPandaModel extends EntityModel<LivingEntityRenderState>{
    
    public final ModelPart head;
	private final ModelPart body;
	private final ModelPart rightHindLeg;
	private final ModelPart leftHindLeg;
	private final ModelPart rightFrontLeg;
	private final ModelPart leftFrontLeg;
	//private final ModelPart tail;

    public RedPandaModel(ModelPart root) {
		super(root);
		//super(true, 8.0F, 3.35F);
		this.head = root.getChild(PartNames.HEAD);
		this.body = root.getChild(PartNames.BODY);
		this.rightHindLeg = root.getChild(PartNames.RIGHT_HIND_LEG);
		this.leftHindLeg = root.getChild(PartNames.LEFT_HIND_LEG);
		this.rightFrontLeg = root.getChild(PartNames.RIGHT_FRONT_LEG);
		this.leftFrontLeg = root.getChild(PartNames.LEFT_FRONT_LEG);
		//this.tail = this.body.getChild(EntityModelPartNames.TAIL);
	}

    public static LayerDefinition getTexturedModelData() {
        MeshDefinition modelData = new MeshDefinition();
		PartDefinition modelPartData = modelData.getRoot();

        modelPartData.addOrReplaceChild(
			PartNames.HEAD,
            CubeListBuilder.create()
            .texOffs(0, 19).addBox(-5.0F, -4.0F, -7.0F, 10.0F, 8.0F, 7.0F, new CubeDeformation(0.0F))
            .texOffs(40, 8).addBox(PartNames.NOSE, -2.0F, 1.0F, -13.25F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
            .texOffs(40, 11).addBox(PartNames.LEFT_EAR, -6.0F, -7.0F, -11.25F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
            .texOffs(32, 43).addBox(PartNames.LEFT_EAR, -3.0F, -6.0F, -11.25F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
            .texOffs(36, 43).addBox(PartNames.RIGHT_EAR, 2.0F, -6.0F, -11.25F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		    .texOffs(40, 15).addBox(PartNames.RIGHT_EAR, 3.0F, -7.0F, -11.25F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)),
        PartPose.offset(2.0F, 15.0F, -6.0F));

        modelPartData.addOrReplaceChild(
            PartNames.BODY,
            CubeListBuilder.create()
            .texOffs(0, 0).addBox(-4.0F, -3.0F, 0.0F, 8.0F, 7.0F, 12.0F, new CubeDeformation(0.0F))
            .texOffs(0, 34).addBox(PartNames.TAIL, -2.0F, -2.0F, 0.0F, 4.0F, 4.0F, 12.0F, new CubeDeformation(0.0F)),
            PartPose.offset(2.0F, 15.0F, -6.0F));


        //ModelPartData body = modelPartData.addChild(EntityModelPartNames.BODY, ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -3.0F, 0.0F, 8.0F, 7.0F, 12.0F, new Dilation(0.0F)), ModelTransform.origin(2.0F, 15.0F, -6.0F));

        PartDefinition leftArm = modelPartData.addOrReplaceChild(PartNames.LEFT_FRONT_LEG, CubeListBuilder.create().texOffs(34, 19).mirror().addBox(-1.0F, 0.0F, -1.5F, 3.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-1.0F, 19.0F, -4.5F));

		PartDefinition rightArm = modelPartData.addOrReplaceChild(PartNames.RIGHT_FRONT_LEG, CubeListBuilder.create().texOffs(34, 19).addBox(-1.5F, 0.0F, -1.5F, 3.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(4.5F, 19.0F, -4.5F));

		PartDefinition rightLeg = modelPartData.addOrReplaceChild(PartNames.RIGHT_HIND_LEG, CubeListBuilder.create().texOffs(34, 19).addBox(3.5F, 0.0F, -1.5F, 3.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.5F, 19.0F, 4.5F));

		PartDefinition leftLeg = modelPartData.addOrReplaceChild(PartNames.LEFT_HIND_LEG, CubeListBuilder.create().texOffs(34, 19).mirror().addBox(-1.5F, 0.0F, -1.5F, 3.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-0.5F, 19.0F, 4.5F));

		//ModelPartData tail = modelPartData.addChild(EntityModelPartNames.TAIL, ModelPartBuilder.create().uv(0, 34).cuboid(-2.0F, -2.0F, 0.0F, 4.0F, 4.0F, 12.0F, new Dilation(0.0F)), ModelTransform.origin(2.0F, 14.0F, 6.0F));

        return LayerDefinition.create(modelData, 64, 64);
    }



    
}
