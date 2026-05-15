package net.ent.entstupidstuff.api.car.soundengine;

import net.minecraft.client.resources.sounds.AbstractTickableSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.ent.entstupidstuff.api.car.BaseCarEntity;

/**
 * Top-speed cruising layer — the high-gear roar at speed.
 *
 * OLD BUG: only checked speed, not throttle. Releasing W at top speed
 * kept this layer at full volume until the car coasted below 0.70 bl/tick,
 * which took several seconds. The sound felt "stuck on".
 *
 * NEW: two-factor system — speed gate AND throttle gate.
 *
 *   Speed gate:   fades in above 0.80, out below 0.70 (hysteresis)
 *   Throttle gate: on-throttle = full volume; off-throttle = rapid fade
 *
 *   Releasing W at top speed now fades the top speed layer in ~5 ticks
 *   while the decel layer takes over. Re-pressing W fades it back in.
 *   This gives the "lift-off drop" that real cars have.
 *
 * Forza/GT approach adapted for Minecraft:
 *   Real games crossfade between "on-load" and "off-load" recordings.
 *   We simulate this by fading the top speed layer (on-load) out when
 *   throttle is released, letting the decel layer (off-load) through.
 */
public class CarTopSpeedSoundInstance extends AbstractTickableSoundInstance
        implements AbstractCarSoundInstance {

    private static final float THRESHOLD_IN      = 0.80f;
    private static final float THRESHOLD_OUT     = 0.70f;
    private static final float FADE_IN_RATE      = 0.05f;
    private static final float FADE_OUT_RATE     = 0.04f;
    /** How fast volume drops when throttle is released at speed. */
    private static final float THROTTLE_OFF_FADE = 0.12f;
    /** How fast volume recovers when throttle is re-applied. */
    private static final float THROTTLE_ON_FADE  = 0.08f;

    private final BaseCarEntity car;
    private final CarSoundProfile profile;
    private float fadeFactor     = 0f; // speed gate: 0=below threshold, 1=above
    private float throttleFactor = 0f; // throttle gate: 0=off, 1=on

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

        float speed = Math.abs(car.getForwardSpeed());

        // ── Speed gate (hysteresis) ──────────────────────────────
        boolean speedActive = fadeFactor > 0f ? speed > THRESHOLD_OUT : speed > THRESHOLD_IN;
        fadeFactor = speedActive
            ? Math.min(1f, fadeFactor + FADE_IN_RATE)
            : Math.max(0f, fadeFactor - FADE_OUT_RATE);

        // ── Throttle gate ────────────────────────────────────────
        // On-throttle: volume builds up. Off-throttle: rapid fade.
        // This is the "lift-off" feel — releasing W drops the top
        // speed roar quickly, letting the decel layer through.
        boolean onThrottle = car.isThrottleOn();
        throttleFactor = onThrottle
            ? Math.min(1f, throttleFactor + THROTTLE_ON_FADE)
            : Math.max(0f, throttleFactor - THROTTLE_OFF_FADE);

        // Final volume = speed gate × throttle gate × profile × distance
        volume = fadeFactor * throttleFactor * profile.topSpeedVolume()
               * SoundDistanceHelper.falloff(car, profile);

        pitch = profile.topSpeedPitch();

        if (fadeFactor <= 0f && car.getFirstPassenger() == null) stop();
    }

    private void syncPosition() { x = car.getX(); y = car.getY(); z = car.getZ(); }
    public BaseCarEntity getCar() { return car; }
}