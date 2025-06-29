package net.ent.entstupidstuff.mixin;

/*import net.ent.entstupidstuff.entity.SunkenSkeletonEntity;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.CrossbowPosing;
import net.minecraft.client.render.entity.model.SkeletonEntityModel;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.CrossbowItem;
import net.minecraft.util.math.MathHelper;
import net.minecraft.entity.ai.RangedAttackMob;


import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SkeletonEntityModel.class)
/*public abstract class SkeletonEntityModelMixin<T extends MobEntity & RangedAttackMob> extends BipedEntityModel<T> {

   /* public SkeletonEntityModelMixin(ModelPart root) {
        super(root);
    }


    @Inject(method = "setAngles", at = @At("HEAD"), cancellable = true)
    public void setAngles(T mobEntity, float f, float g, float h, float i, float j, CallbackInfo ci) {
        if (mobEntity instanceof SunkenSkeletonEntity) {
            SunkenSkeletonEntity.State state = ((SunkenSkeletonEntity) mobEntity).getState();

        System.out.println("SS RUN Time Check");
        System.out.println(state);
        CrossbowPosing.hold(this.rightArm, this.leftArm, this.head, true);

        if (state == SunkenSkeletonEntity.State.CROSSBOW_HOLD) {
            System.out.println("RAN1");
            //CrossbowPosing.hold(this.rightArm, this.leftArm, this.head, true);
            ModelPart modelPart = rightArm;
		    ModelPart modelPart2 = leftArm;
		    modelPart.yaw = (-0.3F) + head.yaw;
		    modelPart2.yaw = ( 0.6F) + head.yaw;
		    modelPart.pitch = (float) (-Math.PI / 2) + head.pitch + 0.1F;
		    modelPart2.pitch = -1.5F + head.pitch;

        } else if (state == SunkenSkeletonEntity.State.CROSSBOW_CHARGE) {
            System.out.println("RAN2");
            //CrossbowPosing.charge(this.rightArm, this.leftArm, mobEntity, true);
            ModelPart modelPart = rightArm;
		    ModelPart modelPart2 = leftArm;
		    modelPart.yaw= -0.8F;
		    modelPart.pitch = -0.97079635F;
		    modelPart2.pitch = modelPart.pitch;
		    float x = (float)CrossbowItem.getPullTime(mobEntity.getActiveItem(), mobEntity);
		    float y = MathHelper.clamp((float)mobEntity.getItemUseTime(), 0.0F, x);
		    float z = y / x;
		    modelPart2.yaw = MathHelper.lerp(z, 0.4F, 0.85F) * (float)(1);
		    modelPart2.pitch = MathHelper.lerp(z, modelPart2.pitch, (float) (-Math.PI / 2));
        }
        }
    }*/
//}
