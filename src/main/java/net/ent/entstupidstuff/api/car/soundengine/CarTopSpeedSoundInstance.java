package net.ent.entstupidstuff.api.car.soundengine;

import net.minecraft.client.resources.sounds.AbstractTickableSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.ent.entstupidstuff.api.car.BaseCarEntity;

/**
 * Top-speed cruising layer — the high-RPM sustained roar.
 *
 * FIXES:
 *   OLD: activated at absolute speed > 0.80 bl/tick.
 *        F1 hits 0.80 in gear 1 → top speed sound in first gear.
 *        Viper hits 0.80 in gear 4 → premature activation.
 *
 *   NEW: activated at RPM > 80% of redline (with hysteresis).
 *        Only plays when the engine is ACTUALLY at high RPM,
 *        regardless of gear or absolute speed. This means:
 *          - F1 in gear 1 at 80 km/h: RPM=60% → no top speed sound ✓
 *          - F1 in gear 8 at 350 km/h: RPM=92% → top speed sound ✓
 *          - Viper in gear 4 at 90 km/h: RPM=55% → no top speed sound ✓
 *          - Viper in gear 5 at 120 km/h: RPM=85% → top speed sound ✓
 *
 *   Throttle gate: releasing W drops the layer quickly so the
 *   decel layer can take over (lift-off feel).
 */
public class CarTopSpeedSoundInstance extends AbstractTickableSoundInstance
        implements AbstractCarSoundInstance {

    /** RPM above this = top speed layer activates. */
    private static final float RPM_THRESHOLD_IN  = 0.82f;
    /** RPM below this = top speed layer deactivates (hysteresis). */
    private static final float RPM_THRESHOLD_OUT = 0.75f;

    private static final float FADE_IN_RATE      = 0.05f;
    private static final float FADE_OUT_RATE     = 0.04f;
    /** How fast volume drops when throttle is released. */
    private static final float THROTTLE_OFF_FADE = 0.12f;
    /** How fast volume recovers when throttle is re-applied. */
    private static final float THROTTLE_ON_FADE  = 0.08f;

    private final BaseCarEntity car;
    private final CarSoundProfile profile;
    private float fadeFactor     = 0f; // RPM gate
    private float throttleFactor = 0f; // throttle gate

    public CarTopSpeedSoundInstance(BaseCarEntity car, SoundEvent sound) {
        super(sound, SoundSource.NEUTRAL, SoundInstance.createUnseededRandom());
        this.car     = car;
        this.profile = car.getSoundProfile();
        this.looping = true;
        this.delay   = 0;
        this.volume  = 0f;
        this.pitch   = profile.topSpeedPitch();
        this.attenuation = Attenuation.NONE;
        syncPosition();
    }

    @Override public boolean canPlaySound()   { return !car.isRemoved(); }
    @Override public boolean canStartSilent() { return true; }

    @Override
    public void tick() {
        if (car.isRemoved()) { stop(); return; }
        syncPosition();

        float rpm = car.getRPM(); // 0–1 normalized

        // ── RPM gate (hysteresis) ────────────────────────────────
        // Uses RPM percentage instead of absolute speed.
        // Activates at 82% RPM, deactivates at 75% (hysteresis
        // prevents flickering when RPM hovers near threshold).
        boolean rpmActive = fadeFactor > 0f ? rpm > RPM_THRESHOLD_OUT : rpm > RPM_THRESHOLD_IN;
        fadeFactor = rpmActive
            ? Math.min(1f, fadeFactor + FADE_IN_RATE)
            : Math.max(0f, fadeFactor - FADE_OUT_RATE);

        // ── Throttle gate ────────────────────────────────────────
        // On-throttle: volume builds up. Off-throttle: rapid fade.
        // Releasing W drops the top speed roar quickly, letting
        // the decel layer through.
        boolean onThrottle = car.isThrottleOn();
        throttleFactor = onThrottle
            ? Math.min(1f, throttleFactor + THROTTLE_ON_FADE)
            : Math.max(0f, throttleFactor - THROTTLE_OFF_FADE);

        // Final volume = RPM gate × throttle gate × profile × distance
        volume = fadeFactor * throttleFactor * profile.topSpeedVolume()
               * SoundDistanceHelper.falloff(car, profile);

        pitch = profile.topSpeedPitch();

        if (fadeFactor <= 0f && car.getFirstPassenger() == null) stop();

        float[] mix = CabinSoundMix.apply(car, CabinSoundMix.Layer.TOP_SPEED, volume, pitch);
        volume = mix[0]; pitch = mix[1];

    }

    private void syncPosition() { x = car.getX(); y = car.getY(); z = car.getZ(); }
    public BaseCarEntity getCar() { return car; }
}