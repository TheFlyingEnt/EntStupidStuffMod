package net.ent.entstupidstuff.mixin;

import net.ent.entstupidstuff.api.ship.CustomBoatEntity;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.vehicle.AbstractBoat;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
 
/**
 * Replaces AbstractBoat.controlBoat() for ships. Thrust now comes from the
 * SAIL LEVEL (persistent throttle), not from holding W — A/D is the rudder,
 * and a held anchor brings her to a hard stop.
 */
@Mixin(AbstractBoat.class)
public abstract class ShipControlMixin {
 
    @Shadow private boolean inputLeft;
    @Shadow private boolean inputRight;
 
    @Inject(method = "controlBoat", at = @At("HEAD"), cancellable = true)
    private void entstupidstuff$shipControl(CallbackInfo ci) {
        if (!((Object) this instanceof CustomBoatEntity ship)) return;   // vanilla boats untouched
        ci.cancel();
        if (ship.isSinking()) return;
 
        ship.steer(inputLeft, inputRight);   // momentum-based turning
        ship.applySailThrust();              // persistent throttle from the sails
        ship.applyChainConstraint();         // anchor chain tug, if deployed
        ship.setPaddleState(false, false);
    }
}



