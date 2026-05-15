package net.ent.entstupidstuff.api.car.soundengine;

import net.minecraft.client.resources.sounds.AbstractTickableSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.ent.entstupidstuff.api.car.BaseCarEntity;

/**
 * Acceleration sound layer — the main "driving" sound.
 *
 * OLD BUG: pitch followed RPM linearly up to accelPitchHigh (e.g. 1.40)
 * then accel faded out and top speed kicked in at topSpeedPitch (0.92).
 * That's a jarring pitch drop from 1.40 → 0.92 at the crossfade.
 *
 * NEW: as speed approaches the crossfade zone (0.60–0.85), the pitch
 * smoothly blends toward topSpeedPitch. By the time accel fades out,
 * its pitch already matches the top speed layer — seamless handoff.
 *
 * Crossfade zones:
 *   0.00–0.60: pure RPM-based pitch (sounds natural in low/mid gears)
 *   0.60–0.85: pitch blends from RPM-based toward topSpeedPitch
 *   0.85+:     volume fades out (top speed layer takes over)
 */
public class CarAccelSoundInstance extends AbstractTickableSoundInstance
        implements AbstractCarSoundInstance {

    private static final float FADE_IN_RATE      = 0.08f;
    private static final float FADE_OUT_RATE     = 0.06f;
    private static final float MIN_SPEED         = 0.03f;
    private static final float ACCEL_FADE_START  = 0.85f;
    /** Speed at which pitch blending toward top speed begins. */
    private static final float BLEND_START       = 0.60f;

    private final BaseCarEntity car;
    private final CarSoundProfile profile;
    private float fadeFactor = 0f;

    public CarAccelSoundInstance(BaseCarEntity car, SoundEvent sound) {
        super(sound, SoundSource.NEUTRAL, SoundInstance.createUnseededRandom());
        this.car     = car;
        this.profile = car.getSoundProfile();
        this.looping = true;
        this.delay   = 0;
        this.volume  = 0f;
        this.pitch   = profile.accelPitchLow();
        this.attenuation = Attenuation.NONE;
        syncPosition();
    }

    @Override public boolean canPlaySound()   { return !car.isRemoved(); }
    @Override public boolean canStartSilent() { return true; }

    @Override
    public void tick() {
        if (car.isRemoved()) { stop(); return; }
        syncPosition();

        float signedSpeed = car.getForwardSpeed();
        float speed       = Math.abs(signedSpeed);
        float rpm         = car.getRPM(); // 0–1

        boolean burningOut = car.isBurningOut();
        boolean active = burningOut
                      || (car.isThrottleOn() && signedSpeed > MIN_SPEED && speed < ACCEL_FADE_START);

        fadeFactor = active
            ? Math.min(1f, fadeFactor + FADE_IN_RATE)
            : Math.max(0f, fadeFactor - FADE_OUT_RATE);

        volume = fadeFactor * profile.accelVolume() * SoundDistanceHelper.falloff(car, profile);

        // ── Pitch with crossfade blending ─────────────────────────
        // Pure RPM-based pitch at low/mid speed
        float rpmPitch = profile.accelPitchLow()
                       + rpm * (profile.accelPitchHigh() - profile.accelPitchLow());

        if (speed > BLEND_START && speed <= ACCEL_FADE_START && !burningOut) {
            // Blend toward topSpeedPitch as speed approaches crossfade
            float blendT = (speed - BLEND_START) / (ACCEL_FADE_START - BLEND_START);
            pitch = rpmPitch + blendT * (profile.topSpeedPitch() - rpmPitch);
        } else {
            pitch = rpmPitch;
        }

        if (fadeFactor <= 0f && car.getFirstPassenger() == null) stop();
    }

    private void syncPosition() { x = car.getX(); y = car.getY(); z = car.getZ(); }
    public BaseCarEntity getCar() { return car; }
}