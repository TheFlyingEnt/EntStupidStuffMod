package net.ent.entstupidstuff.api.car.soundengine;

import net.minecraft.sounds.SoundEvent;

/**
 * Per-car sound profile — each car subclass returns one of these from
 * getSoundProfile(). Contains all sound events and tuning parameters
 * so different cars sound completely different.
 *
 * Sound events:
 *   idle     — base engine loop (always playing when occupied, pitch=RPM)
 *   accel    — layered on top during acceleration (gear whine / roar)
 *   decel    — brake / engine braking sound
 *   topSpeed — high-speed cruising layer (fades in at high speed)
 *   tireSqueal — tyre screech during drift / burnout
 *
 * Pitch tuning:
 *   Each sound layer has its own pitch range [low, high] that maps
 *   to RPM 0–1. Different cars need different ranges because:
 *     - A V10 at 7400 RPM sounds deep → pitch 0.6–1.9
 *     - An F1 V6 at 15000 RPM sounds high → pitch 0.8–2.0
 *     - A Civic hybrid is quiet → pitch 0.7–1.4
 *
 * Volume tuning:
 *   engineVolume scales the overall engine volume. A quiet Civic hybrid
 *   at 0.7 vs a screaming F1 car at 1.0.
 *
 * Distance:
 *   hearingDistance controls how far away bystanders can hear the car.
 *   Default Minecraft LINEAR attenuation cuts off at 16 blocks.
 *   We use this value to scale volume for distant listeners.
 */
public record CarSoundProfile(
    // ── Sound events ──────────────────────────────────────────
    SoundEvent idle,
    SoundEvent accel,
    SoundEvent decel,
    SoundEvent topSpeed,
    SoundEvent tireSqueal,

    // ── Engine pitch range (maps RPM 0–1 to pitch) ────────────
    float enginePitchLow,    // pitch at idle RPM
    float enginePitchHigh,   // pitch at redline

    // ── Accel pitch range ─────────────────────────────────────
    float accelPitchLow,
    float accelPitchHigh,

    // ── Top speed pitch (fixed) ───────────────────────────────
    float topSpeedPitch,

    // ── Volume scaling ────────────────────────────────────────
    float engineVolume,      // 0–1, scales idle + echo volume
    float accelVolume,       // 0–1, scales accel layer
    float decelVolume,       // 0–1, scales decel layer
    float topSpeedVolume,    // 0–1, scales top speed layer
    float tireVolume,        // 0–1, scales tire screech

    // ── Distance ──────────────────────────────────────────────
    float hearingDistance    // blocks — how far the car can be heard
) {

    // ═══════════════════════════════════════════════════════════
    //  PRESETS — common profiles for quick setup
    // ═══════════════════════════════════════════════════════════

    /**
     * American V8 — deep rumble, loud, wide pitch range.
     * Use for: Viper, GT500, muscle cars.
     */
    public static CarSoundProfile americanV8(
            SoundEvent idle, SoundEvent accel, SoundEvent decel,
            SoundEvent topSpeed, SoundEvent tireSqueal) {
        return new CarSoundProfile(
            idle, accel, decel, topSpeed, tireSqueal,
            0.6f, 1.9f,     // engine pitch
            0.75f, 1.40f,   // accel pitch
            0.92f,          // top speed pitch
            1.0f, 1.0f, 1.0f, 1.0f, 0.9f,  // volumes
            48f             // hearing distance
        );
    }

    /**
     * High-rev NA — screaming flat-6 or boxer-4, climbs to high RPM.
     * Use for: GT3, GR86.
     */
    public static CarSoundProfile highRevNA(
            SoundEvent idle, SoundEvent accel, SoundEvent decel,
            SoundEvent topSpeed, SoundEvent tireSqueal) {
        return new CarSoundProfile(
            idle, accel, decel, topSpeed, tireSqueal,
            0.65f, 2.0f,    // engine pitch — wider range for high redline
            0.80f, 1.50f,   // accel pitch
            0.95f,          // top speed pitch
            0.95f, 1.0f, 0.9f, 1.0f, 0.9f,
            44f
        );
    }

    /**
     * Turbo 4-cylinder — muted low end, turbo whistle mid-range.
     * Use for: Civic Type R, turbo hatchbacks.
     */
    public static CarSoundProfile turboFour(
            SoundEvent idle, SoundEvent accel, SoundEvent decel,
            SoundEvent topSpeed, SoundEvent tireSqueal) {
        return new CarSoundProfile(
            idle, accel, decel, topSpeed, tireSqueal,
            0.70f, 1.6f,    // engine pitch — narrower, less dramatic
            0.75f, 1.35f,   // accel pitch
            0.90f,          // top speed pitch
            0.80f, 0.85f, 0.8f, 0.85f, 0.85f,  // quieter overall
            36f
        );
    }

    /**
     * Hybrid / economy — very quiet, smooth, minimal engine character.
     * Use for: Civic Hybrid, economy cars.
     */
    public static CarSoundProfile hybrid(
            SoundEvent idle, SoundEvent accel, SoundEvent decel,
            SoundEvent topSpeed, SoundEvent tireSqueal) {
        return new CarSoundProfile(
            idle, accel, decel, topSpeed, tireSqueal,
            0.70f, 1.4f,    // engine pitch — narrow, undramatic
            0.70f, 1.20f,   // accel pitch — barely audible
            0.85f,          // top speed pitch
            0.65f, 0.60f, 0.7f, 0.70f, 0.80f,  // quiet
            28f
        );
    }

    /**
     * Twin-turbo V6 — mid-range grunt, turbo spool, moderate volume.
     * Use for: Nissan Z, turbo V6 sports cars.
     */
    public static CarSoundProfile twinTurboV6(
            SoundEvent idle, SoundEvent accel, SoundEvent decel,
            SoundEvent topSpeed, SoundEvent tireSqueal) {
        return new CarSoundProfile(
            idle, accel, decel, topSpeed, tireSqueal,
            0.65f, 1.7f,    // engine pitch
            0.75f, 1.40f,   // accel pitch
            0.90f,          // top speed pitch
            0.90f, 0.95f, 0.9f, 0.95f, 0.9f,
            42f
        );
    }

    /**
     * F1 hybrid V6 — high-pitched scream, electric whine, extremely loud.
     * Use for: F1 car, open-wheel racers.
     */
    public static CarSoundProfile f1HybridV6(
            SoundEvent idle, SoundEvent accel, SoundEvent decel,
            SoundEvent topSpeed, SoundEvent tireSqueal) {
        return new CarSoundProfile(
            idle, accel, decel, topSpeed, tireSqueal,
            0.80f, 2.0f,    // engine pitch — high base, extreme top
            0.85f, 1.80f,   // accel pitch — screaming
            1.05f,          // top speed pitch — higher than road cars
            1.0f, 1.0f, 0.9f, 1.0f, 0.85f,
            64f             // F1 cars are heard from very far away
        );
    }

    public static CarSoundProfile normal(
            SoundEvent idle, SoundEvent accel, SoundEvent decel,
            SoundEvent topSpeed, SoundEvent tireSqueal) {
        return new CarSoundProfile(
            idle, accel, decel, topSpeed, tireSqueal,
            1f, 1.5f,    // engine pitch — high base, extreme top
            1f, 1.8f,   // accel pitch — screaming
            1f,          // top speed pitch — higher than road cars
            1.0f, 1.0f, 0.9f, 1.0f, 0.85f,
            64f             // F1 cars are heard from very far away
        );
    }

    //Modified from americanV8
    public static CarSoundProfile americanV82(
            SoundEvent idle, SoundEvent accel, SoundEvent decel,
            SoundEvent topSpeed, SoundEvent tireSqueal) {
        return new CarSoundProfile(
            idle, accel, decel, topSpeed, tireSqueal,
            0.6f, 1.9f,     // engine pitch
            0.75f, 1.40f,   // accel pitch
            0.92f,          // top speed pitch
            3.0f, 3.0f, 3.0f, 3.0f, 1.9f,  // volumes
            48f             // hearing distance
        );
    }

    public static CarSoundProfile f1HybridV6_2(
        SoundEvent idle,
        SoundEvent accel,
        SoundEvent decel,
        SoundEvent topSpeed,
        SoundEvent tireSqueal) {

        return new CarSoundProfile(

            idle,
            accel,
            decel,
            topSpeed,
            tireSqueal,

            // Engine pitch
            0.96f,
            1.05f,

            // Accel pitch
            0.98f,
            1.10f,

            // Top speed
            1.03f,

            // Volumes
            2.8f,
            3.6f,
            2.5f,
            4.2f,
            1.4f,

            // Hearing distance
            96f
        );
    }

    public static CarSoundProfile F1V3(
        SoundEvent idle,
        SoundEvent accel,
        SoundEvent decel,
        SoundEvent topSpeed,
        SoundEvent tireSqueal
    ) {
        return new CarSoundProfile(

            idle,
            accel,
            decel,
            topSpeed,
            tireSqueal,

            // ENGINE
            0.92f,
            1.00f,

            // ACCEL
            0.96f,
            2.16f,

            // TOP SPEED
            1.05f,

            // VOLUMES
            2.4f,
            3.4f,
            2.0f,
            3.0f,
            1.5f,

            64f
        );
    }

    public static CarSoundProfile F1V2(
        SoundEvent idle,
        SoundEvent accel,
        SoundEvent decel,
        SoundEvent topSpeed,
        SoundEvent tireSqueal
    ) {
        return new CarSoundProfile(

            idle,
            accel,
            decel,
            topSpeed,
            tireSqueal,

            // ENGINE
            0.92f,
            1.22f,

            // ACCEL
            0.96f,
            1.16f,

            // TOP SPEED
            1.05f,

            // VOLUMES
            2.4f,
            3.4f,
            2.0f,
            3.0f,
            1.5f,

            64f
        );
    }


}