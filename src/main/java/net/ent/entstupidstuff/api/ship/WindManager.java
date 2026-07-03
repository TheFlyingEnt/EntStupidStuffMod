package net.ent.entstupidstuff.api.ship;

import net.minecraft.util.Mth;

/**
 * Global wind for the whole world.
 *
 * ONE wind direction that slowly rotates over time. The SERVER ticks it and
 * broadcasts it to clients via WindSyncPayload; clients hold the last synced
 * value so the HUD compass and burgee flags match everywhere.
 *
 * Design (per the chosen "arcade" tuning):
 *   • Direction drifts slowly and continuously (a gentle random walk).
 *   • Strength breathes between a floor and a ceiling so gusts feel alive.
 *   • Point-of-sail is a BONUS, never a stall — see CustomBoatEntity
 *     .getSailEfficiency(): downwind = best, upwind = still usable.
 *
 * windDir is the direction the wind BLOWS TOWARD, in degrees, using the same
 * convention as entity yaw (0 = +Z / south, 90 = -X / west), so a ship whose
 * yaw equals windDir is sailing dead downwind.
 */
public final class WindManager {

    // ── Tuning ──
    private static final float DRIFT_PER_TICK   = 0.06f;   // deg/tick base rotation (~72°/min)
    private static final float DRIFT_WOBBLE     = 0.04f;   // extra random jitter on the drift
    private static final float MIN_STRENGTH     = 0.55f;   // never fully calm
    private static final float MAX_STRENGTH     = 1.0f;    // full gust
    private static final float STRENGTH_RATE    = 0.004f;  // how fast strength breathes

    // ── State (server-authoritative) ──
    private static float windDir      = 45f;   // degrees, blows-toward
    private static float windStrength = 0.8f;
    private static float driftBias    = DRIFT_PER_TICK;  // current rotation rate
    private static float strengthTarget = 0.85f;
    private static long  seed = 0x5DEECE66DL;

    private WindManager() {}

    /** Deterministic tiny PRNG so we don't allocate a Random every tick. */
    private static float noise() {
        seed = (seed * 0x5DEECE66DL + 0xBL) & ((1L << 48) - 1);
        return (float) ((seed >>> 16) / (double) (1 << 31)) * 2f - 1f;  // -1..1
    }

    /** Tick the global wind. Call once per server tick. */
    public static void tick() {
        // Direction: slow continuous drift with a wandering bias so it curves
        // rather than spinning at a constant rate.
        driftBias += noise() * DRIFT_WOBBLE * 0.1f;
        driftBias = Mth.clamp(driftBias, DRIFT_PER_TICK * 0.3f, DRIFT_PER_TICK * 1.8f);
        windDir = Mth.wrapDegrees(windDir + driftBias + noise() * DRIFT_WOBBLE);

        // Strength: breathe toward a target, occasionally pick a new target.
        if (Math.abs(windStrength - strengthTarget) < 0.02f) {
            strengthTarget = MIN_STRENGTH + (noise() * 0.5f + 0.5f) * (MAX_STRENGTH - MIN_STRENGTH);
        }
        // Move windStrength toward strengthTarget by at most STRENGTH_RATE.
        float diff = strengthTarget - windStrength;
        windStrength += Mth.clamp(diff, -STRENGTH_RATE, STRENGTH_RATE);
        windStrength = Mth.clamp(windStrength, MIN_STRENGTH, MAX_STRENGTH);
    }

    // ── Accessors ──
    public static float getWindDir()      { return windDir; }
    public static float getWindStrength() { return windStrength; }

    /** Apply a synced value on the client. */
    public static void applySynced(float dir, float strength) {
        windDir = dir;
        windStrength = strength;
    }

    /**
     * Point-of-sail efficiency for a ship heading (arcade curve).
     *
     * shipYaw is the ship's heading in degrees. We compare it to the wind's
     * blow-toward direction:
     *   • angle 0°   = dead downwind  → best (full bonus)
     *   • angle 180° = dead upwind    → worst, but still usable (arcade floor)
     *
     * Returns a multiplier in [UPWIND_FLOOR, DOWNWIND_MAX], applied on top of
     * sail level in getEffectiveThrust().
     */
    public static float efficiencyFor(float shipYaw) {
        final float UPWIND_FLOOR  = 0.75f;  // even straight into the wind you keep 75%
        final float DOWNWIND_MAX  = 1.35f;  // dead downwind gets a 35% boost

        float rel = Math.abs(Mth.wrapDegrees(shipYaw - windDir));  // 0..180
        // cos maps 0°→+1 (downwind), 180°→-1 (upwind); remap to the range.
        float t = (Mth.cos((float) Math.toRadians(rel)) + 1f) * 0.5f;  // 0..1
        float base = Mth.lerp(t, UPWIND_FLOOR, DOWNWIND_MAX);
        // Wind strength scales how much the point-of-sail matters.
        return Mth.lerp(windStrength, 1.0f, base);
    }
}