package net.ent.entstupidstuff.mixin;

import net.ent.entstupidstuff.api.ship.CustomBoatEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Sprint-style FOV kick while sailing with a full sail.
 *
 * When the ship is "catching wind" (sail up, bellied, and actually moving),
 * we widen the FOV a little so the camera feels like it's pulling back with
 * speed — the same sensation vanilla gives when you sprint. The kick eases
 * in and out smoothly so it never snaps.
 *
 * Applies whether you're the helmsman or just standing on the deck of a moving
 * ship (anyone aboard feels the speed). It reads CustomBoatEntity
 * .getWindSprintFactor() (0..1) for the strength.
 */
@Mixin(GameRenderer.class)
public abstract class SailFovMixin {

    // Smoothed 0..1 kick. Catching wind JOLTS in fast; releasing eases out slower.
    private float entstupidstuff$fovKick = 0f;

    // ── CUSTOMIZE HERE ──────────────────────────────────────────────
    /** How much extra FOV at full catch, as a fraction of base FOV.
     *  0.12 = +12%. Raise for a wider, more dramatic sprint feel. */
    private static final float MAX_FOV_BONUS = 0.20f;
    /** How FAST the jolt moves from normal → wind FOV (per frame, 0..1).
     *  Higher = snappier jolt. 1.0 would be an instant teleport; 0.30 gives a
     *  quick-but-visible kick. */
    private static final float CATCH_EASE = 0.30f;
    /** How fast the FOV eases back to normal when the sail loses the wind.
     *  Lower than CATCH_EASE so the return feels gentle, not a snap-off. */
    private static final float RELEASE_EASE = 0.08f;
    /** Catch factor above which the sail counts as "caught" and the jolt fires. */
    private static final float CATCH_THRESHOLD = 0.35f;
    /** Max FOV-kick change per frame while catching. Smaller = longer, more
     *  visible glide into the wind FOV. 0.12 ≈ reaches full over ~8+ frames. */
    private static final float CATCH_MAX_STEP = 0.12f;
    /** Max FOV-kick change per frame while releasing. Smaller = gentler settle. */
    private static final float RELEASE_MAX_STEP = 0.04f;
    // ────────────────────────────────────────────────────────────────

    @Inject(method = "getFov", at = @At("RETURN"), cancellable = true)
    private void entstupidstuff$sailFov(CallbackInfoReturnable<Float> cir) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null) return;

        float target = 0f;
        Entity vehicle = mc.player.getVehicle();
        if (vehicle instanceof CustomBoatEntity ship) {
            // Riding (helm or a seat)
            target = ship.getWindSprintFactor();
        } else if (mc.level != null && !mc.player.isPassenger()) {
            // Standing on the deck — anyone aboard a moving ship feels the speed
            CustomBoatEntity deckBoat =
                net.ent.entstupidstuff.api.ship.DeckSync.findDeckBoat(mc.level, mc.player);
            if (deckBoat != null) target = deckBoat.getWindSprintFactor();
        }

        boolean caught = target >= CATCH_THRESHOLD;

        // ── Always TRANSITION between the two FOV states (never jump) ──
        // Ease toward a FIXED goal (1 = caught, 0 = not) rather than the raw
        // catch factor, so gusts jittering the factor don't make the FOV jump.
        // We also CAP the per-frame movement so the change is always a visible
        // glide, in BOTH directions — a fast glide when catching, a slower one
        // when releasing.
        float goal = caught ? 1f : 0f;
        float ease = caught ? CATCH_EASE : RELEASE_EASE;

        float delta = (goal - entstupidstuff$fovKick) * ease;
        // Cap the step so a big gap can't be crossed in a single frame → the
        // transition is always spread over several frames (no snap).
        float maxStep = caught ? CATCH_MAX_STEP : RELEASE_MAX_STEP;
        delta = Mth.clamp(delta, -maxStep, maxStep);
        entstupidstuff$fovKick = Mth.clamp(entstupidstuff$fovKick + delta, 0f, 1f);
        if (!caught && entstupidstuff$fovKick < 0.001f) entstupidstuff$fovKick = 0f;

        if (entstupidstuff$fovKick > 0f) {
            float base = cir.getReturnValueF();
            // Respect the player's "FOV Effects" accessibility slider so
            // motion-sensitive players who lower/disable it aren't affected.
            double fovEffectScale = mc.options.fovEffectScale().get();
            float boosted = (float) (base * (1.0 + MAX_FOV_BONUS * entstupidstuff$fovKick * fovEffectScale));
            cir.setReturnValue(boosted);
        }
    }
}