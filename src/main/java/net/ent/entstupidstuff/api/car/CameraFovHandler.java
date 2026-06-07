package net.ent.entstupidstuff.api.car;

import net.ent.entstupidstuff.api.car.BaseCarEntity;
import net.minecraft.util.Mth;

/**
 * CameraFovHandler — Forza-style dynamic FOV.
 *
 * Forza widens the field of view as the car speeds up, and punches it
 * wider when you accelerate hard, then settles as you cruise. That lens
 * stretch reads as speed and g-force — it's the cheapest, most robust
 * "weight/speed" cue because it never moves the camera (no jitter, no
 * clipping), it only changes the FOV value GameRenderer hands back.
 *
 * Two components, both smoothed:
 *   - speed component: a steady FOV bonus that grows with how fast you go
 *   - accel surge:     a temporary extra punch when you're gaining speed
 *
 * Per-car cameraWeight() controls how laggy the FOV change is: heavier
 * cars ease in/out more slowly → the lens "catches up" → feels heavier.
 */
public final class CameraFovHandler {

    private CameraFovHandler() {}

    public static boolean enabled = true;

    private static float fovBonus  = 0f; // current smoothed FOV addition (degrees)
    private static float accelKick = 0f; // smoothed positive acceleration
    private static float prevSpeed = 0f;
    private static int   lastTick  = -1;

    // ── Tuning knobs ─────────────────────────────────────────────────
    private static final float MAX_SPEED_FOV = 180f;  // degrees added at full speed
    private static final float SPEED_REF     = 3.0f; // world blocks/tick for full speed FOV
    private static final float ACCEL_FOV     = 55f;  // accel surge → extra degrees
    private static final float MAX_TOTAL_FOV = 18f;  // hard cap so it never gets sickening

    public static boolean debugFov = false;          // flip to false to silence
    private static int     debugLastPrintTick = -1;

    /** Returns the adjusted FOV (base + dynamic bonus). */
    public static float apply(BaseCarEntity car, float baseFov) {
        float spdScale = BaseCarEntity.realisticSpeed ? car.getRealisticSpeedScaleValue() : 1.0f;
        float worldSpeed = Math.abs(car.getForwardSpeed()) * spdScale;

        int tick = car.tickCount;
        if (tick != lastTick) {
            float rawAccel = worldSpeed - prevSpeed;
            accelKick = accelKick * 0.85f + Math.max(0f, rawAccel) * 0.15f;
            prevSpeed = worldSpeed;
            lastTick  = tick;
        }

        float weight = car.cameraWeight();
        float speedComp = Mth.clamp(worldSpeed / SPEED_REF, 0f, 1f) * MAX_SPEED_FOV;
        float accelComp = accelKick * ACCEL_FOV;
        float target    = Math.min(speedComp + accelComp, MAX_TOTAL_FOV);

        float smooth = Mth.lerp(weight, 0.16f, 0.08f);
        fovBonus += (target - fovBonus) * smooth;

        // ── DEBUG (throttle to ~5/sec so chat/console isn't flooded) ──
        if (debugFov && tick != debugLastPrintTick && tick % 4 == 0) {
            debugLastPrintTick = tick;
            System.out.printf(
                "[FOV] spd=%.3f bl/t  accelKick=%.4f  speedComp=%.2f  accelComp=%.2f  target=%.2f  bonus=%.2f  base=%.1f  final=%.1f  (w=%.2f)%n",
                worldSpeed, accelKick, speedComp, accelComp, target, fovBonus, baseFov, baseFov + fovBonus, weight);
        }

        return baseFov + fovBonus;
    }

    /** Called when not in a car — bleed the bonus back to zero. */
    public static void relax() {
        fovBonus  *= 0.85f;
        accelKick *= 0.85f;
        if (fovBonus < 0.01f)  fovBonus  = 0f;
        if (accelKick < 0.001f) accelKick = 0f;
        lastTick = -1;
    }
}
