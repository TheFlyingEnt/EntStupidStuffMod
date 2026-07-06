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

    // ── CONFIG: static wind toggle ──────────────────────────────────
    /** When true, the wind is FROZEN at STATIC_DIR / STATIC_STRENGTH — it won't
     *  drift, breathe, or respond to weather. Handy for testing trim/sailing
     *  with a predictable wind. Set false for the normal shifting wind. */
    public static final boolean STATIC_WIND = true;
    /** Fixed wind direction (blows-toward, degrees) used when STATIC_WIND. */
    public static final float STATIC_DIR = 45f;
    /** Fixed wind strength (0..1) used when STATIC_WIND. */
    public static final float STATIC_STRENGTH = 0.85f;
    // ────────────────────────────────────────────────────────────────

    // ── TRIM FEEL (tune these to taste) ─────────────────────────────
    /** Half-width of the "perfect" trim zone, in degrees. Within this of the
     *  ideal angle you get full efficiency. SMALLER = tighter sweet spot, trim
     *  becomes a precision skill; LARGER = forgiving. (was 40) */
    public static final float TRIM_GOOD_ZONE = 20f;
    /** Efficiency multiplier at the WORST possible trim (sail on the wrong side).
     *  LOWER = bad trim really costs you speed, so good trim feels rewarding.
     *  1.0 = trim doesn't matter; 0.80 = gentle; 0.55 = punishing. (was 0.80) */
    public static final float TRIM_MIS_FLOOR = 0.55f;
    /** Bonus multiplier for a PERFECTLY trimmed sail (peak of the sweet spot).
     *  >1 gives a satisfying speed "pop" when you nail it. 1.0 = no bonus. */
    public static final float TRIM_PERFECT_BONUS = 1.12f;
    // ────────────────────────────────────────────────────────────────

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

    // ── Weather scaling (calm ↔ storm), tied to MC weather ──
    // 1.0 = clear (normal), >1 = rain/storm (stronger), <1 = calm spell.
    private static float weatherScale = 1.0f;
    private static float weatherTarget = 1.0f;
    private static final float CLEAR_SCALE = 1.0f;   // normal wind on a clear day
    private static final float RAIN_SCALE  = 1.35f;  // rain freshens the wind
    private static final float STORM_SCALE = 1.9f;   // thunderstorm = strong wind
    private static final float CALM_SCALE  = 0.45f;  // occasional dead-calm spell
    private static final float WEATHER_EASE = 0.01f; // how fast weather ramps in

    private WindManager() {}

    /** Deterministic tiny PRNG so we don't allocate a Random every tick. */
    private static float noise() {
        seed = (seed * 0x5DEECE66DL + 0xBL) & ((1L << 48) - 1);
        return (float) ((seed >>> 16) / (double) (1 << 31)) * 2f - 1f;  // -1..1
    }

    /** Tick the global wind. Call once per server tick. */
    public static void tick() {
        if (STATIC_WIND) {          // frozen — hold the fixed values
            windDir = STATIC_DIR;
            windStrength = STATIC_STRENGTH;
            weatherScale = 1.0f;
            return;
        }
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

        // Weather: ease the weather scale toward its target so storms build and
        // fade gradually rather than snapping on.
        float wdiff = weatherTarget - weatherScale;
        weatherScale += Mth.clamp(wdiff, -WEATHER_EASE, WEATHER_EASE);
    }

    /**
     * Set the weather-driven wind target from MC's weather state.
     * Call each server tick with the overworld's rain/thunder flags.
     *   thundering → STORM (strong wind)
     *   raining    → RAIN  (fresh wind)
     *   clear      → CLEAR (normal), with occasional random calm spells
     */
    public static void updateWeather(boolean raining, boolean thundering) {
        if (STATIC_WIND) return;   // weather ignored while wind is frozen
        if (thundering)      weatherTarget = STORM_SCALE;
        else if (raining)    weatherTarget = RAIN_SCALE;
        else {
            // Clear skies: mostly normal, but ~every so often a calm spell.
            // Use the drift noise so it's deterministic-ish and not every tick.
            if (weatherTarget != CALM_SCALE && weatherTarget != CLEAR_SCALE) {
                weatherTarget = CLEAR_SCALE;
            }
            // Small chance each call to enter/leave a calm.
            float r = noise();
            if (weatherTarget == CLEAR_SCALE && r > 0.9995f) weatherTarget = CALM_SCALE;
            else if (weatherTarget == CALM_SCALE && r < -0.999f) weatherTarget = CLEAR_SCALE;
        }
    }

    /** Effective wind strength including weather (calm ↔ storm). 0..~1.9 */
    public static float getEffectiveStrength() {
        return Mth.clamp(windStrength * weatherScale, 0f, 2f);
    }

    /** Current weather scale (1 = normal). For HUD / debug. */
    public static float getWeatherScale() { return weatherScale; }

    // ── Accessors ──
    public static float getWindDir()      { return STATIC_WIND ? STATIC_DIR : windDir; }
    public static float getWindStrength() { return STATIC_WIND ? STATIC_STRENGTH : windStrength; }

    /** Apply a synced value on the client. */
    public static void applySynced(float dir, float strength) {
        if (STATIC_WIND) { windDir = STATIC_DIR; windStrength = STATIC_STRENGTH; return; }
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
        return efficiencyFor(shipYaw, 0f, false);
    }

    /**
     * Trim-aware point-of-sail efficiency.
     *
     * trimAngle is the sail's angle relative to the ship's centerline, in
     * degrees (−90..+90; 0 = fore-aft/centered). The IDEAL trim for a given
     * point of sail is roughly half the wind's relative angle — sheeted in
     * close-hauled upwind, eased out running downwind. How close the player's
     * trim is to ideal scales the efficiency.
     *
     * Forgiving tuning: a wide "good" zone and only a small penalty when off.
     */
    public static float efficiencyFor(float shipYaw, float trimAngle, boolean useTrim) {
        final float UPWIND_FLOOR  = 0.75f;
        final float DOWNWIND_MAX  = 1.35f;

        float relWind = Mth.wrapDegrees(shipYaw - windDir);       // -180..180
        float relAbs  = Math.abs(relWind);                         // 0..180
        // Base point-of-sail: cos maps 0°→+1 (downwind), 180°→-1 (upwind).
        float t = (Mth.cos((float) Math.toRadians(relAbs)) + 1f) * 0.5f;  // 0..1
        float base = Mth.lerp(t, UPWIND_FLOOR, DOWNWIND_MAX);

        float trimMult = 1.0f;
        if (useTrim) {
            // IDEAL sail angle for the current wind.
            // relWind = wind bearing relative to bow (0 = dead downwind/behind,
            // ±180 = dead ahead/upwind). Sail should present its FACE to the wind:
            //   • downwind (relWind≈0)   → sail eased fully OUT (~±TRIM_CAP)
            //   • beam wind (relWind≈±90)→ sail about halfway out
            //   • upwind (relWind≈±180)  → sail sheeted IN (near center)
            final float TRIM_CAP = 75f;   // matches the boat's ±TRIM_MAX
            float relAbsSail = Math.abs(relWind);                 // 0..180
            float idealMag = TRIM_CAP * (1f - relAbsSail / 180f); // 0..TRIM_CAP
            float side = (relWind >= 0f) ? 1f : -1f;              // wind side
            float ideal = idealMag * side;

            // ── Downwind side-ambiguity fix ──
            // Near dead-downwind the "correct side" flips wildly from tiny
            // heading changes (a 2° turn could swap the ideal from +75 to -75).
            // Physically, running downwind you can ease the sail out to EITHER
            // side. So within DOWNWIND_BAND of dead-downwind, accept whichever
            // side the player is ALREADY trimmed toward: measure error against
            // BOTH ±idealMag and take the smaller one.
            final float DOWNWIND_BAND = 45f;   // within this of downwind = either side ok
            float err;
            if (relAbsSail <= DOWNWIND_BAND) {
                float errR = Math.abs(Mth.wrapDegrees(trimAngle - idealMag));
                float errL = Math.abs(Mth.wrapDegrees(trimAngle + idealMag));
                err = Math.min(errR, errL);
            } else {
                err = Math.abs(Mth.wrapDegrees(trimAngle - ideal));  // 0..180
            }

            // Trim quality curve:
            //   err = 0            → PERFECT: full bonus (TRIM_PERFECT_BONUS)
            //   err within zone    → tapering from the bonus down to 1.0 at the
            //                        edge of the good zone (so nailing it "pops")
            //   err beyond zone    → falls off toward the mis-trim floor
            if (err <= TRIM_GOOD_ZONE) {
                // Inside the sweet spot: lerp from the perfect bonus (at 0°) to
                // a clean 1.0 at the zone edge — dead-on gives the speed pop.
                float trimT = err / TRIM_GOOD_ZONE;                 // 0..1
                trimMult = Mth.lerp(trimT, TRIM_PERFECT_BONUS, 1.0f);
            } else {
                // Outside: fall from 1.0 toward the floor as trim gets worse.
                float over = (err - TRIM_GOOD_ZONE) / (180f - TRIM_GOOD_ZONE);  // 0..1
                trimMult = Mth.lerp(over, 1.0f, TRIM_MIS_FLOOR);
            }
        }

        // Effective wind strength (weather-scaled) governs how much any of this
        // matters vs. a flat 1.0.
        float strength = Mth.clamp(getEffectiveStrength(), 0f, 1f);
        return Mth.lerp(strength, 1.0f, base * trimMult);
    }

    /**
     * How FULL the sail is for a ship heading, 0..1.
     *   1.0 = sail bellied out and drawing well (downwind / broad reach)
     *   0.0 = sail luffing (pointed into the wind, edge-on, flapping)
     *
     * This is a VISUAL cue for the fill/luff animation — separate from the
     * arcade efficiency above (which never fully stalls). It follows the true
     * point-of-sail so the sail LOOKS right even though upwind still MOVES you.
     */
    public static float fillFor(float shipYaw) {
        return fillFor(shipYaw, 0f, false);
    }

    /** Trim-aware fill: a well-trimmed sail bellies; a mis-trimmed one luffs. */
    public static float fillFor(float shipYaw, float trimAngle, boolean useTrim) {
        float rel = Math.abs(Mth.wrapDegrees(shipYaw - windDir));  // 0..180
        float t = (Mth.cos((float) Math.toRadians(rel)) + 1f) * 0.5f;  // 0..1
        float fill = Mth.clamp((t - 0.15f) / 0.85f, 0f, 1f);

        if (useTrim) {
            float relWind = Mth.wrapDegrees(shipYaw - windDir);
            final float TRIM_CAP = 75f;
            float relAbs = Math.abs(relWind);
            float idealMag = TRIM_CAP * (1f - relAbs / 180f);
            float ideal = idealMag * (relWind >= 0f ? 1f : -1f);
            // Same downwind either-side rule as efficiencyFor.
            final float DOWNWIND_BAND = 45f;
            float err;
            if (relAbs <= DOWNWIND_BAND) {
                float errR = Math.abs(Mth.wrapDegrees(trimAngle - idealMag));
                float errL = Math.abs(Mth.wrapDegrees(trimAngle + idealMag));
                err = Math.min(errR, errL);
            } else {
                err = Math.abs(Mth.wrapDegrees(trimAngle - ideal));
            }
            // Trim error spills the sail — MORE sensitive than thrust so the
            // player clearly SEES they're mis-trimmed. Full belly within the
            // good zone, dropping toward a slack sail as trim gets worse.
            float trimFill;
            if (err <= TRIM_GOOD_ZONE) {
                trimFill = 1f;
            } else {
                float over = (err - TRIM_GOOD_ZONE) / (90f - TRIM_GOOD_ZONE);
                trimFill = Mth.clamp(1f - over, 0f, 1f);
            }
            fill *= Mth.lerp(0.35f, 1f, trimFill);  // even bad trim keeps some belly
        }

        float strength = Mth.clamp(getEffectiveStrength(), 0f, 1f);
        return fill * Mth.lerp(strength, 0.6f, 1.0f);
    }
}