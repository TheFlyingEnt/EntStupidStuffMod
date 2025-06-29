package net.ent.entstupidstuff.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.BipedEntityModel;

import net.minecraft.entity.LivingEntity;

import net.minecraft.item.Items;
import net.minecraft.util.Arm;
import net.minecraft.util.Hand;


@Mixin(BipedEntityModel.class)
public abstract class BipedEntityModelMixin<T extends LivingEntity> {

    /*
     * Hammer:
     * - Has Two Attacks:
     * - Left Click: Big Knockback
     * - Right Click: Create an AOE Attack
     * 
     * If the person has an item on the off Hand, Right Click Attack is Disabled
     * 
     * 
     */


     /*
        - I need the modify the Following:
        positionLeftArm, positionRightArm and ArmPose
        and BipedEntityModel.ArmPose getArmPose in player render



      */

    @Shadow private ModelPart leftArm;
    @Shadow private ModelPart rightArm;
    @Shadow private ModelPart head;
    @Shadow private ModelPart body;
    @Shadow public float leaningPitch;

    @Shadow private ModelPart leftLeg;
    @Shadow private ModelPart rightLeg;

    @SuppressWarnings("unused")
    private Arm getPreferredArm2(T entity) {
		Arm arm = entity.getMainArm();
		return entity.preferredHand == Hand.MAIN_HAND ? arm : arm.getOpposite();
	}


    protected ModelPart getArm2(Arm arm) {
		return arm == Arm.LEFT ? this.leftArm : this.rightArm;
	}

    /*
     * This Method is used for the Attacking Animation
     */
    @Inject(method = "animateArms", at = @At("HEAD"), cancellable = true)
    private void onAnimateArms(T entity, float animationProgress, CallbackInfo ci) {

        if (!(entity.handSwingProgress <= 0.0F)) {

            /*if (entity.isHolding(ItemFactory.ClaymoreTest)) {
                System.out.println("Holding Claymore Attack");
                Arm arm = this.getPreferredArm2(entity);
                ModelPart modelPart = this.getArm2(arm);
                float f = entity.handSwingProgress;

                this.body.yaw = MathHelper.sin(MathHelper.sqrt(f) * (float) (Math.PI * 2)) * 0.2F;
			    if (arm == Arm.LEFT) {
				    this.body.yaw *= -1.0F;
			    }

                this.rightArm.pivotZ = MathHelper.sin(this.body.yaw) * 5.0F;
			    this.rightArm.pivotX = -MathHelper.cos(this.body.yaw) * 5.0F;

			    this.leftArm.pivotZ = -MathHelper.sin(this.body.yaw) * 5.0F;
			    this.leftArm.pivotX = MathHelper.cos(this.body.yaw) * 5.0F;

			    this.rightArm.yaw = this.rightArm.yaw + this.body.yaw;
			    this.leftArm.yaw = this.leftArm.yaw + this.body.yaw;
			    this.leftArm.pitch = this.leftArm.pitch + this.body.yaw;

                /*
			    f = 1.0F - entity.handSwingProgress;
			    f *= f;
			    f *= f;
			    f = 1.0F - f;
			    float g = MathHelper.sin(f * (float) Math.PI);
			    float h = MathHelper.sin(entity.handSwingProgress * (float) Math.PI) * -(this.head.pitch - 0.7F) * 0.75F;

                //Change thi Below to Animation provided
                modelPart.pitch -= g * 1.2F + h;
			    modelPart.yaw = modelPart.yaw + this.body.yaw * 2.0F;
			    modelPart.roll = modelPart.roll + MathHelper.sin(entity.handSwingProgress * (float) Math.PI) * -0.4F;***

                float interpolationFactor = f; // Ranges from 0 to 1
                /*float startPitch = -104.8747F;
                float startYaw = -20.1972F;
                float startRoll = 28.3506F;
                float endPitch = 31.884F;
                float endYaw = 27.1869F;
                float endRoll = 21.4201F;*88

                float startPitch = 1.0848239501F;
                float startYaw = 4.8811889481F;
                float startRoll = 65.0462389524F;
                float endPitch = -105.8848186279F;
                float endYaw = -43.9056767369F;
                float endRoll = 77.1939614605F;

                float interpolatedPitch = MathHelper.lerp(interpolationFactor, startPitch, endPitch);
                float interpolatedYaw = MathHelper.lerp(interpolationFactor, startYaw, endYaw);
                float interpolatedRoll = MathHelper.lerp(interpolationFactor, startRoll, endRoll);

                modelPart.pitch = (float) Math.toRadians(interpolatedPitch);
                modelPart.yaw = (float) Math.toRadians(interpolatedYaw);
                modelPart.roll = (float) Math.toRadians(interpolatedRoll);



            }*/

			/*Arm arm = this.getPreferredArm(entity);
			ModelPart modelPart = this.getArm(arm);
			float f = entity.handSwingProgress;

			this.body.yaw = MathHelper.sin(MathHelper.sqrt(f) * (float) (Math.PI * 2)) * 0.2F;
			if (arm == Arm.LEFT) {
				this.body.yaw *= -1.0F;
			}

			this.rightArm.pivotZ = MathHelper.sin(this.body.yaw) * 5.0F;
			this.rightArm.pivotX = -MathHelper.cos(this.body.yaw) * 5.0F;

			this.leftArm.pivotZ = -MathHelper.sin(this.body.yaw) * 5.0F;
			this.leftArm.pivotX = MathHelper.cos(this.body.yaw) * 5.0F;

			this.rightArm.yaw = this.rightArm.yaw + this.body.yaw;
			this.leftArm.yaw = this.leftArm.yaw + this.body.yaw;
			this.leftArm.pitch = this.leftArm.pitch + this.body.yaw;
			f = 1.0F - entity.handSwingProgress;
			f *= f;
			f *= f;
			f = 1.0F - f;
			float g = MathHelper.sin(f * (float) Math.PI);
			float h = MathHelper.sin(entity.handSwingProgress * (float) Math.PI) * -(this.head.pitch - 0.7F) * 0.75F;
			modelPart.pitch -= g * 1.2F + h;
			modelPart.yaw = modelPart.yaw + this.body.yaw * 2.0F;
			modelPart.roll = modelPart.roll + MathHelper.sin(entity.handSwingProgress * (float) Math.PI) * -0.4F;*/
		}






        /*ItemStack mainHandItem = entity.getStackInHand(Hand.MAIN_HAND);
        ItemStack offHandItem = entity.getStackInHand(Hand.OFF_HAND);

        // Ent Comment: If the player has an empty hand: This runs (First Person)
        // If the player has a item , this runs (Third Person)

        if (!(entity.handSwingProgress <= 0.0F)) {

            if (entity.isHolding(Items.IRON_AXE)) { //Swing
                System.out.println("Hand Animation Ran:" + entity.handSwingProgress);
                //this.rightArm.pitch = (float) Math.toRadians(90);
                this.body.pitch = (float) Math.toRadians(-20);
                this.rightArm.pitch = (float) Math.toRadians(-205);
                ci.cancel();
            }

           
            /*if (!mainHandItem.isEmpty() && !offHandItem.isEmpty()) {

            }*

        }*/

        /*
         * if (isHammer(mainHandItem)) {
         * BipedEntityModel<T> model = (BipedEntityModel<T>) (Object) this;
         * 
         * // Hammer strike animation: Arms swing down
         * model.rightArm.pitch = (float) Math.toRadians(90);
         * model.rightArm.yaw = 0;
         * model.rightArm.roll = 0;
         * 
         * model.leftArm.pitch = (float) Math.toRadians(90);
         * model.leftArm.yaw = 0;
         * model.leftArm.roll = 0;
         * 
         * //ci.cancel(); // Prevent normal animation from applying
         * }
         */

    }

    @Inject(method = "setAngles", at = @At("HEAD"), cancellable = true)
    private void onSetAngles(T entity, float f, float g, float h, float i, float j, CallbackInfo ci) {
        if (entity.isHolding(Items.DIAMOND_SWORD)) {

            if (entity.isHolding(Items.DIAMOND_SWORD)) {
                System.out.println("setAngle Animation Ran:");
                //this.rightArm.pitch = (float) Math.toRadians(90);
                this.body.pitch = (float) Math.toRadians(-20);
                this.rightArm.pitch = (float) Math.toRadians(-205);
                //ci.cancel();
            } 


        }
    }






    @SuppressWarnings("unused")
    private void positionHammer(T entity, float f, float g, float h) {
        //BipedEntityModel<?> model = (BipedEntityModel<?>) this;
        // Modify the arm's rotation and position when holding the hammer
        // Here, we are assuming the hammer is in the main hand for simplicity
        if (entity.getActiveHand() == Hand.MAIN_HAND) {
            // This Detects if someone is holding an Item
        }
    }

    @SuppressWarnings("unused")
    private float method_280(float f) {
		return -65.0F * f + f * f;
	}

    protected float lerpAngle2(float angleOne, float angleTwo, float magnitude) {
		float f = (magnitude - angleTwo) % (float) (Math.PI * 2);
		if (f < (float) -Math.PI) {
			f += (float) (Math.PI * 2);
		}

		if (f >= (float) Math.PI) {
			f -= (float) (Math.PI * 2);
		}

		return angleTwo + angleOne * f;
	}


    /*private Arm getPreferredArm2(T entity) {
		Arm arm = entity.getMainArm();
		return entity.preferredHand == Hand.MAIN_HAND ? arm : arm.getOpposite();
	}*/


    // updated


    @Shadow public BipedEntityModel.ArmPose leftArmPose = BipedEntityModel.ArmPose.EMPTY;

    @SuppressWarnings("incomplete-switch")
    @Inject(method = "positionLeftArm", at = @At("HEAD"), cancellable = true)
    private void positionLeftArm(T entity, CallbackInfo ci) {
		switch (this.leftArmPose) {
			case ITEM:
            if (entity.getStackInHand(Hand.OFF_HAND).isOf(Items.DIAMOND_AXE)) { // Change this so that If the other Hand if Empty than run this
                System.out.println("This Worked");

                //You can only modify the Arms (Tested Both Arm but not Body)

                //this.body.pitch = this.body.pitch * 0.5F - (float) (Math.PI / 10);
                

				this.leftArm.pitch = this.leftArm.pitch * 0.5F - (float) (Math.PI / 10); //So the Pi /10 =  pi * 1/10 = 36 Degrees
				this.leftArm.yaw = 0.0F; // and this is setting the yaw to 0

                this.rightArm.pitch = this.rightArm.pitch * 0.5F - (float) (Math.PI / 10); //So the Pi /10 =  pi * 1/10 = 36 Degrees
				this.rightArm.yaw = 0.0F; // and this is setting the yaw to 0


                ci.cancel();
            } else {
				this.leftArm.pitch = this.leftArm.pitch * 0.5F - (float) (Math.PI / 10);
				this.leftArm.yaw = 0.0F;
                ci.cancel();
            }
			break;
		}

        // If not in item, run as Normal :)
	}
    

}
