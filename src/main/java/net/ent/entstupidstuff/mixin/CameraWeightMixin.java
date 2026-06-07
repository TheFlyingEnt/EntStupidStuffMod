package net.ent.entstupidstuff.mixin;

import net.ent.entstupidstuff.api.car.BaseCarEntity;
import net.ent.entstupidstuff.api.car.CameraWeightHandler;
import net.minecraft.client.Camera;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Adds a spring-lagged offset to the camera position when the local player
 * is riding a car, producing the Forza "weight" feeling.
 *
 * Injected at the TAIL of Camera.setup() so it runs AFTER vanilla has
 * positioned the camera (including the third-person collision pull-back).
 * The offset is small enough that clipping is rare; if you see the camera
 * poke through walls during hard cornering, reduce the MAX_* clamps and
 * the *_SWING / *_PULLBACK constants in CameraWeightHandler.
 *
 * Only applies in third-person ("detached"). First-person is left alone
 * to avoid nausea from a moving eye position.
 */
@Mixin(Camera.class)
public abstract class CameraWeightMixin {

    @Shadow public abstract Vec3 getPosition();
    @Shadow protected abstract void setPosition(Vec3 vec3);

    @Inject(method = "setup", at = @At("TAIL"))
    private void entstupidstuff$applyWeight(BlockGetter level, Entity entity,
                                            boolean detached, boolean thirdPersonReverse,
                                            float partialTick, CallbackInfo ci) {
        if (!CameraWeightHandler.enabled) return;

        // Only in third person — the dramatic chase-cam swing.
        if (!detached) {
            CameraWeightHandler.relax();
            return;
        }

        Entity vehicle = entity.getVehicle();
        if (!(vehicle instanceof BaseCarEntity car)) {
            CameraWeightHandler.relax();
            return;
        }

        Vec3 offset = CameraWeightHandler.computeOffset(car, partialTick);
        this.setPosition(this.getPosition().add(offset));
    }
}
