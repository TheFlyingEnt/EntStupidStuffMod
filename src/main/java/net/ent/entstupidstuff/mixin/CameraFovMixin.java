package net.ent.entstupidstuff.mixin;

import net.ent.entstupidstuff.api.car.BaseCarEntity;
import net.ent.entstupidstuff.api.car.CameraFovHandler;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
 
/**
 * Forza-style dynamic FOV: widens the field of view with speed and punches
 * it wider under acceleration, while the local player is driving a car.
 *
 * Hooks the RETURN of GameRenderer.getFov() and adds the handler's bonus
 * to the value the game computed. Works at any base FOV setting — it adds
 * on top, so your 70 or 110 choice is preserved and just breathes with speed.
 *
 * NOTE: getFov returns double in 1.21.x. If your mappings differ (older
 * versions returned float, or the method name differs), adjust the
 * CallbackInfoReturnable type and the getReturnValueD()/setReturnValue()
 * calls accordingly.
 */
@Mixin(GameRenderer.class)
public abstract class CameraFovMixin {
 
    @Inject(method = "getFov", at = @At("RETURN"), cancellable = true)
    private void entstupidstuff$weightFov(Camera camera, float partialTick,
                                          boolean useFovSetting,
                                          CallbackInfoReturnable<Float> cir) {
        if (!CameraFovHandler.enabled) return;

        // Only the main world FOV — not the held-item / portal FOV passes.
        if (!useFovSetting) return;

        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null) return;

        Entity vehicle = mc.player.getVehicle();
        if (!(vehicle instanceof BaseCarEntity car)) {
            CameraFovHandler.relax();
            return;
        }

        float base = cir.getReturnValueF();
        cir.setReturnValue(CameraFovHandler.apply(car, base));
    }
}

