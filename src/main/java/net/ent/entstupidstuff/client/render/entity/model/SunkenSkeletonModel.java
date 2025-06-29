package net.ent.entstupidstuff.client.render.entity.model;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.entity.generic.GenericSkeletonBow;
import net.ent.entstupidstuff.entity.generic.GenericSkeletonCrossbow;
import net.ent.entstupidstuff.entity.mob.SkeletonCrossbowEntity;
import net.ent.entstupidstuff.entity.mob.SunkenSkeletonEntity;
import net.ent.entstupidstuff.entity.mob.SunkenSkeletonEntity.Variant;
import net.minecraft.client.model.Dilation;
import net.minecraft.client.model.ModelData;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.ModelPartBuilder;
import net.minecraft.client.model.ModelTransform;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.CrossbowPosing;
import net.minecraft.client.render.entity.model.EntityModelPartNames;
import net.minecraft.client.render.entity.model.SkeletonEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.mob.SkeletonEntity;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Arm;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

//ENT UPDATE: Changed SunkenSkeletonEntity to SkeletonEntity 8/1 1:00a - Unteated
public class SunkenSkeletonModel<T extends SkeletonEntity> extends /*SkeletonEntityModel<T>*/ BipedEntityModel<T> {

    public ModelPart rightarm;
    public ModelPart leftarm;


    public SunkenSkeletonModel(ModelPart modelPart) {
        super(modelPart);
        this.head.setPivot(this.head.pivotX, this.head.pivotY, this.head.pivotZ);
        this.body.setPivot(this.body.pivotX, this.body.pivotY, this.body.pivotZ);
        this.leftLeg.setPivot(this.leftLeg.pivotX, this.leftLeg.pivotY, this.leftLeg.pivotZ);

        this.rightarm = modelPart.getChild(EntityModelPartNames.RIGHT_ARM);
        this.leftarm = modelPart.getChild(EntityModelPartNames.LEFT_ARM);

    }

    public Identifier getTexture(T entity) {

		if (entity instanceof SunkenSkeletonEntity || entity instanceof SkeletonCrossbowEntity ) {
			Variant var = ((SunkenSkeletonEntity) entity).getVariant();

        	if (var == Variant.Variant1) 
            	return Identifier.of(EntStupidStuff.MOD_ID, "textures/entity/sunken_skeleton/sunken_skeleton_1.png");
        	else if (var == Variant.Variant2)
            	return Identifier.of(EntStupidStuff.MOD_ID, "textures/entity/sunken_skeleton/sunken_skeleton_2.png");
        	else {
            	return Identifier.of(EntStupidStuff.MOD_ID, "textures/entity/sunken_skeleton/sunken_skeleton_3.png"); 
        	} 
		}
		else {
			return Identifier.of(EntStupidStuff.MOD_ID, "textures/entity/sunken_skeleton/sunken_skeleton_3.png");
		}

        
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = SkeletonEntityModel.getModelData(Dilation.NONE, 0.0F);


        ModelPartBuilder head = ModelPartBuilder.create() //Works
        .uv(0, 0).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.0F))
		.uv(32, 0).cuboid(-1.0F, -16.0F, -4.002F, 10.0F, 10.0F, 0.0F, new Dilation(0.0F))
		.uv(20, 0).cuboid(1.0F, -3.0F, 2.0F, 4.0F, 0.0F, 4.0F, new Dilation(0.0F));

        ModelPartBuilder body = ModelPartBuilder.create()
        .uv(16, 16).cuboid(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F)
        .uv(12, 30).cuboid(2.0F, 0.0F, -2.0F, 2.0F, 2.0F, 0.0F, new Dilation(0.0F))
        .uv(12, 30).cuboid(-6.0F, -8.0F, -2.0F, 2.0F, 2.0F, 0.0F, new Dilation(0.0F));

        ModelPartBuilder right_leg = ModelPartBuilder.create()
        .uv(20, 4).cuboid(-2.0F, 4.0F, -2.0F, 4.0F, 0.0F, 4.0F, new Dilation(0.0F))
		.uv(40, 16).mirrored().cuboid(-1.0F, 0.0F, -1.0F, 2.0F, 12.0F, 2.0F, new Dilation(0.0F)).mirrored(false);

        modelData.getRoot().addChild(
			EntityModelPartNames.RIGHT_ARM, ModelPartBuilder.create().uv(40, 16).cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 12.0F, 2.0F), ModelTransform.pivot(-5.0F, 2.0F, 0.0F)
		);

        modelData.getRoot().addChild(
			EntityModelPartNames.LEFT_ARM,
			ModelPartBuilder.create().uv(40, 16).mirrored().cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 12.0F, 2.0F),
			ModelTransform.pivot(5.0F, 2.0F, 0.0F)
		);

        modelData.getRoot().addChild(
			EntityModelPartNames.LEFT_LEG,
			ModelPartBuilder.create().uv(0, 16).mirrored().cuboid(-1.0F, 0.0F, -1.0F, 2.0F, 12.0F, 2.0F),
			ModelTransform.pivot(2.0F, 12.0F, 0.0F)
		);

        

        modelData.getRoot().addChild(EntityModelPartNames.HEAD, head, ModelTransform.pivot(0.0F, 0, 0.0F));
        modelData.getRoot().addChild(EntityModelPartNames.BODY, body, ModelTransform.pivot(0.0F, 12, 0.0F));
        modelData.getRoot().addChild(EntityModelPartNames.RIGHT_LEG, right_leg, ModelTransform.pivot(-2.0F, 12.0F, 0.0F));

        return TexturedModelData.of(modelData, 64, 32);

    }

    @Override
    public void setAngles(T mobEntity, float f, float g, float h, float i, float j) {
		
		ItemStack itemStack = mobEntity.getMainHandStack();
		super.setAngles(mobEntity, f, g, h, i, j);

        if (mobEntity instanceof GenericSkeletonCrossbow && mobEntity.getMainHandStack().getItem() instanceof CrossbowItem ) {
            GenericSkeletonCrossbow.State state = ((GenericSkeletonCrossbow) mobEntity).getState();
		    if (state == GenericSkeletonCrossbow.State.ATTACKING) {
			    if (mobEntity.getMainHandStack().isEmpty()) {
				    CrossbowPosing.meleeAttack(this.leftArm, this.rightArm, true, this.handSwingProgress, h);
			    } else {
					CrossbowPosing.meleeAttack(this.rightArm, this.leftArm, mobEntity, this.handSwingProgress, h);
				}
			} else if (state == GenericSkeletonCrossbow.State.CROSSBOW_HOLD) {
			    CrossbowPosing.hold(this.rightArm, this.leftArm, this.head, true);
			} else if (state == GenericSkeletonCrossbow.State.CROSSBOW_CHARGE) {
			    CrossbowPosing.charge(this.rightArm, this.leftArm, mobEntity, true);
			}
        }
		else if (mobEntity instanceof GenericSkeletonBow){
			if (mobEntity.isAttacking() && (itemStack.isEmpty() || !itemStack.isOf(Items.BOW))) {
				float k = MathHelper.sin(this.handSwingProgress * (float) Math.PI);
				float l = MathHelper.sin((1.0F - (1.0F - this.handSwingProgress) * (1.0F - this.handSwingProgress)) * (float) Math.PI);
				this.rightArm.roll = 0.0F;
				this.leftArm.roll = 0.0F;
				this.rightArm.yaw = -(0.1F - k * 0.6F);
				this.leftArm.yaw = 0.1F - k * 0.6F;
				this.rightArm.pitch = (float) (-Math.PI / 2);
				this.leftArm.pitch = (float) (-Math.PI / 2);
				this.rightArm.pitch -= k * 1.2F - l * 0.4F;
				this.leftArm.pitch -= k * 1.2F - l * 0.4F;
				CrossbowPosing.swingArms(this.rightArm, this.leftArm, h);
			}
		}
	}

	@Override
	public void setArmAngle(Arm arm, MatrixStack matrices) {
		float f = arm == Arm.RIGHT ? 1.0F : -1.0F;
		ModelPart modelPart = this.getArm(arm);
		modelPart.pivotX += f;
		modelPart.rotate(matrices);
		modelPart.pivotX -= f;
	}

	public void animateModel(T mobEntity, float f, float g, float h) {
		this.rightArmPose = BipedEntityModel.ArmPose.EMPTY;
		this.leftArmPose = BipedEntityModel.ArmPose.EMPTY;
		ItemStack itemStack = mobEntity.getStackInHand(Hand.MAIN_HAND);
		if (itemStack.isOf(Items.BOW) && mobEntity.isAttacking()) {
			if (mobEntity.getMainArm() == Arm.RIGHT) {
				this.rightArmPose = BipedEntityModel.ArmPose.BOW_AND_ARROW;
			} else {
				this.leftArmPose = BipedEntityModel.ArmPose.BOW_AND_ARROW;
			}
		}

		super.animateModel(mobEntity, f, g, h);
	}



}
