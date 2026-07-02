package net.ent.entstupidstuff.mixin;

import net.ent.entstupidstuff.api.ship.CustomBoatEntity;
import net.minecraft.world.entity.vehicle.AbstractBoat;
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
        if (!((Object) this instanceof CustomBoatEntity ship)) return;  // vanilla boats untouched
        ci.cancel();
        if (ship.isSinking()) return;
 
        // 1. Tell the server about our steering (updates rudder server-side +
        //    on all other clients; keeps the empty case correct after we leave).
        ship.steer(inputLeft, inputRight);
 
        // 2. Set the rudder locally too, so controlShip() below reads fresh input
        //    with no round-trip lag, then run the physics on THIS (authoritative)
        //    client. Vanilla's client move() integrates the velocity we set.
        ship.applySteer(inputLeft, inputRight);
        ship.controlShip();
 
        ship.setPaddleState(false, false);
    }
}






