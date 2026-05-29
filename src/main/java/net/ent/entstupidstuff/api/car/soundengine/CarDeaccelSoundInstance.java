package net.ent.entstupidstuff.api.car.soundengine;

import net.minecraft.client.resources.sounds.AbstractTickableSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.ent.entstupidstuff.api.car.BaseCarEntity;

/**
 * Deceleration / engine-braking sound layer.
 *
 * OLD BUG: required speed delta < -0.004 per tick, which normal
 * coast-down never reached. The sound literally never played.
 *
 * NEW: plays whenever the driver lifts off the throttle at speed,
 * or actively brakes. Volume scales with speed so it fades naturally
 * as the car slows to a stop. This is the "lift-off growl" you hear
 * in real cars when you release the gas pedal at highway speed.
 */
public class CarDeaccelSoundInstance extends AbstractTickableSoundInstance
        implements AbstractCarSoundInstance {

    private static final float MIN_SPEED     = 0.10f;  // don't play at crawl speed
    private static final float FADE_IN_RATE  = 0.08f;
    private static final float FADE_OUT_RATE = 0.06f;
    private static final float PITCH_LOW     = 0.80f;
    private static final float PITCH_HIGH    = 1.25f;

    private final BaseCarEntity car;
    private final CarSoundProfile profile;
    private float fadeFactor = 0f;

    public CarDeaccelSoundInstance(BaseCarEntity car, SoundEvent sound) {
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

        float speed = Math.abs(car.getForwardSpeed());

        // Active on:
        //   1. Active braking (S key) at any meaningful speed
        //   2. Lift-off: throttle released while still moving fast
        //      This is the engine-braking growl when you let go of W
        boolean braking  = car.isBraking() && speed > MIN_SPEED;
        boolean liftOff  = !car.isThrottleOn() && !car.isBurningOut() && speed > MIN_SPEED;
        boolean active   = braking || liftOff;

        fadeFactor = active
            ? Math.min(1f, fadeFactor + FADE_IN_RATE)
            : Math.max(0f, fadeFactor - FADE_OUT_RATE);

        // Volume scales with speed — loud at high speed, fades as car slows
        float speedVolume = Math.min(1f, speed / 0.5f);
        volume = fadeFactor * speedVolume * profile.decelVolume()
               * SoundDistanceHelper.falloff(car, profile);

        // Pitch follows speed — high speed = higher pitch decel roar
        float speedFraction = Math.min(1f, speed);
        pitch = PITCH_LOW + speedFraction * (PITCH_HIGH - PITCH_LOW);

        if (fadeFactor <= 0f && car.getFirstPassenger() == null) stop();

        float[] mix = CabinSoundMix.apply(car, CabinSoundMix.Layer.DECEL, volume, pitch);
        volume = mix[0]; pitch = mix[1];

    }

    private void syncPosition() { x = car.getX(); y = car.getY(); z = car.getZ(); }
    public BaseCarEntity getCar() { return car; }
}