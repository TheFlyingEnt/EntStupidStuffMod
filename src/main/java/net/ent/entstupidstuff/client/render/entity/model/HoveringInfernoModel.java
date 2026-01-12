package net.ent.entstupidstuff.client.render.entity.model;

import net.ent.entstupidstuff.client.entity.animation.HVAnimation;
import net.ent.entstupidstuff.client.entity.mob.HoveringInfernoEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.util.Mth;

@SuppressWarnings("unused")
public class HoveringInfernoModel extends EntityModel<LivingEntityRenderState> {
	private final ModelPart hovering_inferno;
	private final ModelPart h_head;
	private final ModelPart inferno_shield;

	public HoveringInfernoModel(ModelPart root) {
		super(root);
		this.hovering_inferno = root.getChild("hovering_inferno");
		this.h_head = hovering_inferno.getChild("h_head");
		this.inferno_shield = hovering_inferno.getChild("inferno_shield");
	}

	public static LayerDefinition getTexturedModelData() {
		MeshDefinition modelData = new MeshDefinition();
		PartDefinition modelPartData = modelData.getRoot();
		PartDefinition hovering_inferno = modelPartData.addOrReplaceChild("hovering_inferno", CubeListBuilder.create(), PartPose.offset(0.0F, 8.0F, 0.0F));

		PartDefinition h_head = hovering_inferno.addOrReplaceChild("h_head", CubeListBuilder.create().texOffs(8, 60).addBox(-4.0F, -4.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(32, 0).addBox(-4.5F, -10.5F, -4.5F, 9.0F, 15.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -24.0F, 0.0F));

		PartDefinition inferno_shield = hovering_inferno.addOrReplaceChild("inferno_shield", CubeListBuilder.create().texOffs(0, 34).addBox(-2.0F, -9.7524F, -1.8177F, 4.0F, 30.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -7.2476F, -0.1823F));

		PartDefinition cube_r1 = inferno_shield.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(44, 24).addBox(-6.0F, -10.0F, 0.0F, 12.0F, 22.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.4755F, 9.8177F, 0.3927F, 0.0F, 0.0F));

		PartDefinition cube_r2 = inferno_shield.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 0).addBox(10.0F, -6.0F, 4.0F, 2.0F, 22.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.4755F, -9.4532F, 0.0F, 0.0F, -0.3927F));

		PartDefinition cube_r3 = inferno_shield.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(16, 22).addBox(-12.0F, -6.0F, 4.0F, 2.0F, 22.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.4755F, -9.4532F, 0.0F, 0.0F, 0.3927F));

		PartDefinition cube_r4 = inferno_shield.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(44, 48).addBox(-6.0F, -10.0F, -2.0F, 12.0F, 22.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.4755F, -9.4532F, -0.3927F, 0.0F, 0.0F));
		return LayerDefinition.create(modelData, 128, 128);
	}

	private void setHeadAngles(float headYaw, float headPitch) {
		headYaw = Mth.clamp(headYaw, -30.0F, 30.0F);
		headPitch = Mth.clamp(headPitch, -25.0F, 45.0F);

		this.h_head.yRot = headYaw * 0.017453292F;
		this.h_head.xRot = headPitch * 0.017453292F;
	}
		
}