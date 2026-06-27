package net.ent.entstupidstuff.api.car;

import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;

/**
 * CameraWeightHandler — the "weight feeling" from Forza.
 *
 * In Forza the chase camera is attached to the car by a SPRING, not rigidly.
 * When the car accelerates, the camera falls back. When it brakes, the
 * camera pushes forward. When it turns/drifts, the camera lags behind the
 * rotation and swings wide to the outside of the corner. That lag is what
 * makes a heavy car FEEL heavy — your eye reads the delayed camera motion
 * as mass and inertia.
 *
 * This class computes a spring-damped positional offset (in car-local
 * space) from the car's acceleration and yaw rate, then converts it to
 * a world-space offset that CameraWeightMixin adds to the camera position.
 *
 * Per-car weight (BaseCarEntity.cameraWeight(), 0..1) controls how soft
 * the spring is: heavier cars use a softer spring → more lag and overshoot.
 *
 * All state is static because there is only ONE local camera.
 */
public final class CameraWeightHandler {

    private CameraWeightHandler() {}

    /** Master toggle — flip with /carconfig cameraWeight true/false. */
    public static boolean enabled = true;

    // ── Spring state (car-local offsets, in blocks) ──────────────────
    private static float offForward  = 0f; // +forward / -behind  (accel pulls camera back)
    private static float offLateral  = 0f; // +right / -left      (yaw swings camera out)
    private static float offVertical = 0f; // +up / -down         (accel dips camera)
    private static float velForward  = 0f, velLateral = 0f, velVertical = 0f;

    // ── Per-tick physics inputs (recomputed when the tick advances) ──
    private static float prevSpeed = 0f;
    private static float prevYaw   = 0f;
    private static int   lastTick  = -1;
    private static float accel     = 0f; // longitudinal accel, blocks/tick^2
    private static float yawRate   = 0f; // yaw change, degrees/tick

    // ── Tuning knobs ─────────────────────────────────────────────────
    // How strongly each effect pulls the camera. Raise for more drama.
    //private static final float ACCEL_PULLBACK = 9.0f;  // accel → camera falls back
    /*private static final float ACCEL_PULLBACK = 2.0f;  // accel → camera falls back

    private static final float ACCEL_DIP      = 2.5f;  // accel → camera dips down
    private static final float YAW_SWING       = 0.16f; // yaw rate → lateral swing
    // Clamps so the camera never flies off into walls.
    private static final float MAX_FORWARD  = 2.2f;
    private static final float MAX_LATERAL  = 1.6f;
    private static final float MAX_VERTICAL = 1.0f;*/

    /*private static final float ACCEL_PULLBACK = 7.0f;   // accel → camera falls back (main weight cue)
    private static final float ACCEL_DIP      = 2.0f;   // accel → camera dips down
    private static final float YAW_SWING      = 0.035f; // yaw → lateral swing (SMALL — was 0.16, way too much)
    // Clamps so the camera never flies off into walls.
    private static final float MAX_FORWARD  = 1.8f;
    private static final float MAX_LATERAL  = 0.55f;    // hard cap — was 1.6, that's why it whipped sideways
    private static final float MAX_VERTICAL = 0.7f;*/

    private static final float ACCEL_PULLBACK = 3.5f;   // was 7.0 — too much for a close camera
    private static final float ACCEL_DIP      = 0.0f;   // disable vertical dip — it's adding to the low-camera problem
    private static final float YAW_SWING      = 0.035f;
    private static final float MAX_FORWARD  = 0.8f;     // was 1.8 — cap it tight
    private static final float MAX_LATERAL  = 0.55f;
    private static final float MAX_VERTICAL = 0.0f;     // no vertical

    /**
     * Compute the world-space camera offset for this car this frame.
     * Called every render frame from CameraWeightMixin.
     */
    public static Vec3 computeOffset(BaseCarEntity car, float partialTick) {
        // ── Update accel + yaw rate once per game tick ───────────────
        int tick = car.tickCount;
        if (tick != lastTick) {
            float speed = car.getForwardSpeed();
            float yaw   = car.getYRot();
            float rawAccel = speed - prevSpeed;
            // Low-pass filter the accel so per-tick speed jitter doesn't
            // make the camera bounce. Only sustained accel moves it.
            //accel   = accel * 0.80f + rawAccel * 0.20f;
            accel = accel * 0.92f + rawAccel * 0.08f;
            yawRate = Mth.wrapDegrees(yaw - prevYaw);
            prevSpeed = speed;
            prevYaw   = yaw;
            lastTick  = tick;
        }

        float weight = car.cameraWeight(); // 0.35 (light/F1) .. 1.0 (heavy/GT500)

        // Heavier car = softer spring = more lag + overshoot.
        float stiffness = Mth.lerp(weight, 0.22f, 0.12f); // softer = less overshoot
        float damping   = Mth.lerp(weight, 0.78f, 0.86f); // higher = settles faster, no bounce

        // ── Targets ──────────────────────────────────────────────────
        // Accelerating (accel>0): camera falls BACK  → negative forward offset.
        // Braking      (accel<0): camera pushes FWD  → positive forward offset.
        float targetForward  = -accel * ACCEL_PULLBACK * weight;
        // Dip down slightly under acceleration.
        float targetVertical = -Math.abs(accel) * ACCEL_DIP * weight;
        // Turning: camera lags the rotation and swings to the OUTSIDE.
        // (Negative sign so a LEFT turn swings the camera RIGHT — matches Forza.
        //  If it swings the wrong way for you, flip this sign.)
        float targetLateral  = -yawRate * YAW_SWING * weight;

        // ── Spring integrate (critically-ish damped) ─────────────────
        velForward  += (targetForward  - offForward)  * stiffness; velForward  *= damping; offForward  += velForward;
        velLateral  += (targetLateral  - offLateral)  * stiffness; velLateral  *= damping; offLateral  += velLateral;
        velVertical += (targetVertical - offVertical) * stiffness; velVertical *= damping; offVertical += velVertical;

        offForward  = Mth.clamp(offForward,  -MAX_FORWARD,  MAX_FORWARD);
        offLateral  = Mth.clamp(offLateral,  -MAX_LATERAL,  MAX_LATERAL);
        offVertical = Mth.clamp(offVertical, -MAX_VERTICAL, MAX_VERTICAL);

        // ── Convert car-local offset → world space ───────────────────
        double yRad = Math.toRadians(car.getYRot());
        double sin = Math.sin(yRad), cos = Math.cos(yRad);
        // car forward = (-sin, 0, cos); car right = (cos, 0, sin)
        double wx = offForward * (-sin) + offLateral * cos;
        double wz = offForward *   cos  + offLateral * sin;
        return new Vec3(wx, offVertical, wz);
    }

    /** Called when the player is NOT driving — bleed the springs to rest. */
    public static void relax() {
        offForward  *= 0.80f; offLateral  *= 0.80f; offVertical *= 0.80f;
        velForward  *= 0.80f; velLateral  *= 0.80f; velVertical *= 0.80f;
        if (Math.abs(offForward)  < 0.001f) offForward  = 0f;
        if (Math.abs(offLateral)  < 0.001f) offLateral  = 0f;
        if (Math.abs(offVertical) < 0.001f) offVertical = 0f;
        lastTick = -1; // force fresh accel calc next time we drive
    }
}