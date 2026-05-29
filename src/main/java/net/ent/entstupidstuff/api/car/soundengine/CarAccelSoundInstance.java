package net.ent.entstupidstuff.api.car.soundengine;

import net.minecraft.client.resources.sounds.AbstractTickableSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.ent.entstupidstuff.api.car.BaseCarEntity;

/**
 * Acceleration sound layer — the main "driving under power" sound.
 *
 * FIXES:
 *   1. Only plays when RPM is above 25% — no more accel sound
 *      when crawling through sand at 2 km/h with the engine barely
 *      above idle. Below 25% RPM, only the idle layer is heard.
 *
 *   2. Pitch blends toward topSpeedPitch in the crossfade zone
 *      (RPM 0.70–0.85) so the handoff to the top speed layer
 *      is seamless — no jarring pitch jump.
 *
 *   3. Fades out based on RPM, not absolute speed. This means
 *      it fades at the right point regardless of which gear
 *      you're in or how fast the car actually goes.
 */
public class CarAccelSoundInstance extends AbstractTickableSoundInstance
        implements AbstractCarSoundInstance {

    /** RPM below this = accel layer silent, only idle plays. */
    private static final float RPM_MIN_ACTIVATE = 0.20f;
    /** RPM above this = accel layer fades out, top speed takes over. */
    private static final float RPM_FADE_OUT     = 0.85f;
    /** RPM where pitch starts blending toward topSpeedPitch. */
    private static final float RPM_BLEND_START  = 0.70f;

    private static final float FADE_IN_RATE  = 0.08f;
    private static final float FADE_OUT_RATE = 0.06f;

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

        float rpm = car.getRPM(); // 0–1 normalized
        boolean burningOut = car.isBurningOut();

        // Active when:
        //   - Throttle on AND RPM above minimum threshold
        //   - OR burning out (burnout always plays accel)
        //   - AND RPM below the top-speed crossfade point
        boolean active = burningOut
            || (car.isThrottleOn() && rpm > RPM_MIN_ACTIVATE && rpm < RPM_FADE_OUT);

        fadeFactor = active
            ? Math.min(1f, fadeFactor + FADE_IN_RATE)
            : Math.max(0f, fadeFactor - FADE_OUT_RATE);

        volume = fadeFactor * profile.accelVolume() * SoundDistanceHelper.falloff(car, profile);

        // ── Pitch with crossfade blending ─────────────────────────
        float rpmPitch = profile.accelPitchLow()
                       + rpm * (profile.accelPitchHigh() - profile.accelPitchLow());

        // Blend toward topSpeedPitch as RPM approaches crossfade zone
        if (rpm > RPM_BLEND_START && rpm <= RPM_FADE_OUT && !burningOut) {
            float blendT = (rpm - RPM_BLEND_START) / (RPM_FADE_OUT - RPM_BLEND_START);
            pitch = rpmPitch + blendT * (profile.topSpeedPitch() - rpmPitch);
        } else {
            pitch = rpmPitch;
        }

        if (fadeFactor <= 0f && car.getFirstPassenger() == null) stop();

        float[] mix = CabinSoundMix.apply(car, CabinSoundMix.Layer.ACCEL, volume, pitch);
        volume = mix[0]; pitch = mix[1];

    }

    private void syncPosition() { x = car.getX(); y = car.getY(); z = car.getZ(); }
    public BaseCarEntity getCar() { return car; }
}