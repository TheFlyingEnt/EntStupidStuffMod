package net.ent.entstupidstuff.client.render.entity.model;

import net.minecraft.client.model.Dilation;
import net.minecraft.client.model.ModelData;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.ModelPartBuilder;
import net.minecraft.client.model.ModelPartData;
import net.minecraft.client.model.ModelTransform;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelPartNames;
import net.minecraft.client.render.entity.state.CreeperEntityRenderState;
import net.minecraft.util.math.MathHelper;

public class SporeperModel extends EntityModel<CreeperEntityRenderState> {
    private final ModelPart head;
	private final ModelPart leftHindLeg;
	private final ModelPart rightHindLeg;
	private final ModelPart leftFrontLeg;
	private final ModelPart rightFrontLeg;
	private static final int HEAD_AND_BODY_Y_PIVOT = 6;

    public SporeperModel(ModelPart modelPart) {
		super(modelPart);
		this.head = modelPart.getChild(EntityModelPartNames.HEAD);
		this.rightHindLeg = modelPart.getChild(EntityModelPartNames.RIGHT_HIND_LEG);
		this.leftHindLeg = modelPart.getChild(EntityModelPartNames.LEFT_HIND_LEG);
		this.rightFrontLeg = modelPart.getChild(EntityModelPartNames.RIGHT_FRONT_LEG);
		this.leftFrontLeg = modelPart.getChild(EntityModelPartNames.LEFT_FRONT_LEG);
	}

    public static TexturedModelData getTexturedModelData(Dilation dilation) {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		modelPartData.addChild(
			EntityModelPartNames.HEAD,
			ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, dilation)
            //Creeper HAT
            .uv(0, 32).cuboid(-7.0F, -11.0F, -7.0F, 14.0F, 5.0F, 14.0F, new Dilation(0.0F)),
			ModelTransform.origin(0.0F, 6.0F, 0.0F)
		);
		modelPartData.addChild(
			EntityModelPartNames.BODY,
			ModelPartBuilder.create().uv(16, 16).cuboid(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, dilation)
			// Creaper Top Layer
            .uv(40, 16).cuboid(-4.0F, 0.0F, -2.0F, 8.0F, 5.0F, 4.0F, new Dilation(0.5F)),
            ModelTransform.origin(0.0F, 6.0F, 0.0F)
		);
		ModelPartBuilder modelPartBuilder = ModelPartBuilder.create().uv(0, 16).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, dilation);
		modelPartData.addChild(EntityModelPartNames.RIGHT_HIND_LEG, modelPartBuilder, ModelTransform.origin(-2.0F, 18.0F, 4.0F));
		modelPartData.addChild(EntityModelPartNames.LEFT_HIND_LEG, modelPartBuilder, ModelTransform.origin(2.0F, 18.0F, 4.0F));
		modelPartData.addChild(EntityModelPartNames.RIGHT_FRONT_LEG, modelPartBuilder, ModelTransform.origin(-2.0F, 18.0F, -4.0F));
		modelPartData.addChild(EntityModelPartNames.LEFT_FRONT_LEG, modelPartBuilder, ModelTransform.origin(2.0F, 18.0F, -4.0F));
		return TexturedModelData.of(modelData, 64, 64);
	}

	public void setAngles(CreeperEntityRenderState creeperEntityRenderState) {
		super.setAngles(creeperEntityRenderState);
		this.head.yaw = creeperEntityRenderState.relativeHeadYaw * (float) (Math.PI / 180.0);
		this.head.pitch = creeperEntityRenderState.pitch * (float) (Math.PI / 180.0);
		float f = creeperEntityRenderState.limbSwingAmplitude;
		float g = creeperEntityRenderState.limbSwingAnimationProgress;
		this.leftHindLeg.pitch = MathHelper.cos(g * 0.6662F) * 1.4F * f;
		this.rightHindLeg.pitch = MathHelper.cos(g * 0.6662F + (float) Math.PI) * 1.4F * f;
		this.leftFrontLeg.pitch = MathHelper.cos(g * 0.6662F + (float) Math.PI) * 1.4F * f;
		this.rightFrontLeg.pitch = MathHelper.cos(g * 0.6662F) * 1.4F * f;
	}

    
}
