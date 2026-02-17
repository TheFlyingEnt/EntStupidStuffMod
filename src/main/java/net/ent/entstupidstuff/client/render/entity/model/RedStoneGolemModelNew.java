package net.ent.entstupidstuff.client.render.entity.model;

import com.mojang.blaze3d.vertex.PoseStack;

import net.ent.entstupidstuff.client.entity.mob.RedStoneGolemEntity;
import net.ent.entstupidstuff.client.render.entity.state.RedStoneGolemRenderState;
import net.minecraft.client.animation.KeyframeAnimation;
import net.minecraft.client.model.ArmedModel;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HeadedModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.world.entity.HumanoidArm;

public class RedStoneGolemModelNew extends EntityModel<RedStoneGolemRenderState> implements ArmedModel<RedStoneGolemRenderState>, HeadedModel {

    private final ModelPart body;
	private final ModelPart upper_body;
	private final ModelPart head;
	private final ModelPart right_arm;
	private final ModelPart right_wrist;
	private final ModelPart right_thumb;
	private final ModelPart right_top_finger;
	private final ModelPart right_botton_finger;
	private final ModelPart left_arm;
	private final ModelPart left_wrist;
	private final ModelPart left_thumb;
	private final ModelPart left_top_finger;
	private final ModelPart left_botton_finger;
	private final ModelPart right_leg;
	private final ModelPart left_leg;
    
    // Baked animations
    private final KeyframeAnimation idleAnimation;
    private final KeyframeAnimation walkAnimation;
    private final KeyframeAnimation swipeAttackAnimation;
    private final KeyframeAnimation basicAttackAnimation;

    public RedStoneGolemModelNew(ModelPart modelPart) {
        super(modelPart);
        this.body = root.getChild("body");
		this.upper_body = this.body.getChild("upper_body");
		this.head = this.upper_body.getChild("head");
		this.right_arm = this.upper_body.getChild("right_arm");
		this.right_wrist = this.right_arm.getChild("right_wrist");
		this.right_thumb = this.right_wrist.getChild("right_thumb");
		this.right_top_finger = this.right_wrist.getChild("right_top_finger");
		this.right_botton_finger = this.right_wrist.getChild("right_botton_finger");
		this.left_arm = this.upper_body.getChild("left_arm");
		this.left_wrist = this.left_arm.getChild("left_wrist");
		this.left_thumb = this.left_wrist.getChild("left_thumb");
		this.left_top_finger = this.left_wrist.getChild("left_top_finger");
		this.left_botton_finger = this.left_wrist.getChild("left_botton_finger");
		this.right_leg = root.getChild("right_leg");
		this.left_leg = root.getChild("left_leg");
        
        // Bake animations
        this.idleAnimation = RedStoneGolemAnimation.idle.bake(modelPart);
        this.walkAnimation = RedStoneGolemAnimation.walk.bake(modelPart);
        this.swipeAttackAnimation = RedStoneGolemAnimation.swipe_attack.bake(modelPart);
        this.basicAttackAnimation = RedStoneGolemAnimation.basic_attack.bake(modelPart);
    }

    public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(120, 36).addBox(-11.0F, -8.0F, -7.0F, 22.0F, 8.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 4.0F, 0.0F));

		PartDefinition upper_body = body.addOrReplaceChild("upper_body", CubeListBuilder.create().texOffs(49, 90).addBox(-8.0F, -20.0F, -1.0F, 16.0F, 16.0F, 16.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-20.0F, -32.0F, -10.0F, 40.0F, 32.0F, 20.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -8.0F, 0.0F));

		PartDefinition head = upper_body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(124, 8).addBox(-5.0F, -7.0F, -12.0F, 16.0F, 16.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, -24.0F, -10.0F));

		PartDefinition right_arm = upper_body.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(0, 52).addBox(-14.0F, -7.0F, -6.0F, 14.0F, 24.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(-20.0F, -23.0F, 0.0F));

		PartDefinition right_wrist = right_arm.addOrReplaceChild("right_wrist", CubeListBuilder.create().texOffs(3, 88).addBox(-6.0F, 0.0F, -6.0F, 11.0F, 22.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(-11.0F, 17.0F, 0.0F));

		PartDefinition right_thumb = right_wrist.addOrReplaceChild("right_thumb", CubeListBuilder.create().texOffs(97, 55).mirror().addBox(-1.5F, -3.0F, -1.0F, 3.0F, 10.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(2.5F, 22.0F, -2.0F));

		PartDefinition right_top_finger = right_wrist.addOrReplaceChild("right_top_finger", CubeListBuilder.create().texOffs(81, 57).addBox(-1.0F, 0.0F, -3.0F, 3.0F, 8.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, 22.0F, -3.0F));

		PartDefinition right_botton_finger = right_wrist.addOrReplaceChild("right_botton_finger", CubeListBuilder.create().texOffs(81, 57).mirror().addBox(-1.0F, 0.0F, -2.0F, 3.0F, 8.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-3.0F, 22.0F, 2.0F));

		PartDefinition left_arm = upper_body.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(0, 52).mirror().addBox(0.0F, -7.0F, -6.0F, 14.0F, 24.0F, 12.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(20.0F, -23.0F, 0.0F));

		PartDefinition left_wrist = left_arm.addOrReplaceChild("left_wrist", CubeListBuilder.create().texOffs(3, 88).mirror().addBox(-5.0F, 0.0F, -6.0F, 11.0F, 22.0F, 12.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(11.0F, 17.0F, 0.0F));

		PartDefinition left_thumb = left_wrist.addOrReplaceChild("left_thumb", CubeListBuilder.create().texOffs(97, 55).addBox(-1.5F, -3.0F, -1.0F, 3.0F, 10.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.5F, 22.0F, -2.0F));

		PartDefinition left_top_finger = left_wrist.addOrReplaceChild("left_top_finger", CubeListBuilder.create().texOffs(81, 57).mirror().addBox(-2.0F, 0.0F, -3.0F, 3.0F, 8.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(4.0F, 22.0F, -3.0F));

		PartDefinition left_botton_finger = left_wrist.addOrReplaceChild("left_botton_finger", CubeListBuilder.create().texOffs(81, 57).addBox(-2.0F, 0.0F, -2.0F, 3.0F, 8.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, 22.0F, 2.0F));

		PartDefinition right_leg = partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(113, 58).addBox(-6.0F, 0.0F, -6.0F, 12.0F, 20.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(-11.0F, 4.0F, 0.0F));

		PartDefinition left_leg = partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(113, 58).mirror().addBox(-6.0F, 0.0F, -6.0F, 12.0F, 20.0F, 12.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(11.0F, 4.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 256, 256);
	}

    @Override
    public void setupAnim(RedStoneGolemRenderState state) {
        super.setupAnim(state);

        //this.idleAnimation.apply(state.idleAnimationState, state.ageInTicks);
        //this.idleAnimation.apply(state.idleAnimationState, state.ageInTicks);
        
        // Play animations based on state
        // Attack animations take priority
        if (state.attackType == RedStoneGolemEntity.SWEEP_ATTACK) {
            // Play sweep attack animation
            this.swipeAttackAnimation.apply(state.sweepAttackAnimationState, state.ageInTicks);
        } else if (state.attackType == RedStoneGolemEntity.NORMAL_ATTACK) {
            // Play normal attack animation
            this.basicAttackAnimation.apply(state.normalAttackAnimationState, state.ageInTicks);
        } else if (state.walkAnimationSpeed > 0.01F) {
            // Play walking animation - use walkAnimationSpeed to check if moving
            this.walkAnimation.applyWalk(state.walkAnimationPos, state.walkAnimationSpeed, 9.0F, 2.5F);
        } else {
            // Play idle animation
            this.idleAnimation.apply(state.idleAnimationState, state.ageInTicks);
        }
        
        // Apply head looking
        this.head.yRot = state.yRot * ((float) Math.PI / 180F);
        this.head.xRot = state.xRot * ((float) Math.PI / 180F);
    }

    @Override
    public ModelPart getHead() {
        return this.head;
    }

    @Override
    public void translateToHand(RedStoneGolemRenderState entityRenderState, HumanoidArm humanoidArm, PoseStack poseStack) {
        float f = humanoidArm == HumanoidArm.RIGHT ? 1.0F : -1.0F;
        ModelPart arm = humanoidArm == HumanoidArm.RIGHT ? this.right_arm : this.left_arm;
        
        // Translate to the body
        poseStack.translate(0.0F, 0.25F, 0.6875F);
        
        // Translate to upper body
        poseStack.translate(0.0F, -0.5F, 0.0F);
        
        // Translate to arm
        poseStack.translate(f * 1.25F, -1.4375F, 0.0F);
        
        // Apply arm rotation
        arm.translateAndRotate(poseStack);
        
        // Translate to wrist
        ModelPart wrist = humanoidArm == HumanoidArm.RIGHT ? this.right_wrist : this.left_wrist;
        wrist.translateAndRotate(poseStack);
        
        // Translate to end of hand
        poseStack.translate(f * -0.34375F, 1.375F, 0.0F);
    }
}