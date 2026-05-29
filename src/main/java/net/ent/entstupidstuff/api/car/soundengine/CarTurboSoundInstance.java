package net.ent.entstupidstuff.api.car.soundengine;

import net.minecraft.client.resources.sounds.AbstractTickableSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.ent.entstupidstuff.api.car.BaseCarEntity;

/**
 * Turbo spool whistle — builds with RPM, only for turbo cars.
 *
 * The turbo whistle starts quiet at low RPM and builds to a
 * high-pitched scream near redline. It's louder under throttle
 * and fades when coasting (turbo spools down).
 *
 * Only created for cars where car.isTurbo() returns true.
 */
public class CarTurboSoundInstance extends AbstractTickableSoundInstance
        implements AbstractCarSoundInstance {

    private static final float PITCH_LOW  = 0.50f;  // low RPM: deep hum
    private static final float PITCH_HIGH = 1.80f;  // high RPM: screaming whistle
    private static final float VOLUME_MAX = 0.45f;  // turbo shouldn't overpower engine
    private static final float RPM_MIN    = 0.25f;  // turbo doesn't spool below 25% RPM

    private static final float FADE_IN_RATE  = 0.04f;  // slow spool-up
    private static final float FADE_OUT_RATE = 0.06f;  // slightly faster spool-down

    private final BaseCarEntity car;
    private final CarSoundProfile profile;
    private float fadeFactor = 0f;

    public CarTurboSoundInstance(BaseCarEntity car, SoundEvent sound) {
        super(sound, SoundSource.NEUTRAL, SoundInstance.createUnseededRandom());
        this.car     = car;
        this.profile = car.getSoundProfile();
        this.looping = true;
        this.delay   = 0;
        this.volume  = 0f;
        this.pitch   = PITCH_LOW;
        this.attenuation = Attenuation.NONE;
        syncPosition();
    }

    @Override public boolean canPlaySound()   { return !car.isRemoved(); }
    @Override public boolean canStartSilent() { return true; }

    @Override
    public void tick() {
        if (car.isRemoved()) { stop(); return; }
        syncPosition();

        float rpm = car.getRPM(); // 0–1

        // Turbo spools when throttle is on and RPM is above minimum.
        // Stronger spool at higher RPM (more exhaust gas = more boost).
        boolean active = car.isThrottleOn() && rpm > RPM_MIN
                      && car.getFirstPassenger() != null;

        fadeFactor = active
            ? Math.min(1f, fadeFactor + FADE_IN_RATE)
            : Math.max(0f, fadeFactor - FADE_OUT_RATE);

        // Volume scales with RPM — barely audible at 25%, loud at redline
        float rpmVolume = Math.max(0f, (rpm - RPM_MIN) / (1f - RPM_MIN));
        volume = fadeFactor * rpmVolume * VOLUME_MAX
               * SoundDistanceHelper.falloff(car, profile);

        // Pitch follows RPM — low hum to high whistle
        pitch = PITCH_LOW + rpm * (PITCH_HIGH - PITCH_LOW);

        if (fadeFactor <= 0f && car.getFirstPassenger() == null) stop();

        float[] mix = CabinSoundMix.apply(car, CabinSoundMix.Layer.TURBO, volume, pitch);
        volume = mix[0]; pitch = mix[1];

    }

    private void syncPosition() { x = car.getX(); y = car.getY(); z = car.getZ(); }
    public BaseCarEntity getCar() { return car; }
}