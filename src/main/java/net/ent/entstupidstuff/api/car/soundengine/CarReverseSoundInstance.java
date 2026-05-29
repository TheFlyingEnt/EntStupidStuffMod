package net.ent.entstupidstuff.api.car.soundengine;

import net.minecraft.client.resources.sounds.AbstractTickableSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.ent.entstupidstuff.api.car.BaseCarEntity;

public class CarReverseSoundInstance extends AbstractTickableSoundInstance
        implements AbstractCarSoundInstance {

    private static final float PITCH_BASE    = 0.72f;
    private static final float PITCH_MAX     = 1.10f;
    private static final float MAX_REV_SPEED = 0.35f;
    private static final float MIN_SPEED     = 0.02f;
    private static final float FADE_IN_RATE  = 0.10f;
    private static final float FADE_OUT_RATE = 0.08f;
    private static final float VOLUME_MAX    = 0.85f;

    private final BaseCarEntity car;
    private final CarSoundProfile profile;
    private float fadeFactor = 0f;

    public CarReverseSoundInstance(BaseCarEntity car, SoundEvent sound) {
        super(sound, SoundSource.NEUTRAL, SoundInstance.createUnseededRandom());
        this.car     = car;
        this.profile = car.getSoundProfile();
        this.looping = true;
        this.delay   = 0;
        this.volume  = 0f;
        this.pitch   = PITCH_BASE;
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
        boolean active = car.isThrottleOn() && signedSpeed < -MIN_SPEED;

        fadeFactor = active
            ? Math.min(VOLUME_MAX, fadeFactor + FADE_IN_RATE)
            : Math.max(0f,          fadeFactor - FADE_OUT_RATE);

        volume = fadeFactor * profile.engineVolume() * SoundDistanceHelper.falloff(car, profile);

        if (active) {
            float revFraction = Math.min(1f, Math.abs(signedSpeed) / MAX_REV_SPEED);
            pitch = PITCH_BASE + revFraction * (PITCH_MAX - PITCH_BASE);
        }

        if (fadeFactor <= 0f && car.getFirstPassenger() == null) stop();

        float[] mix = CabinSoundMix.apply(car, CabinSoundMix.Layer.REVERSE, volume, pitch);
        volume = mix[0]; pitch = mix[1];

    }

    private void syncPosition() { x = car.getX(); y = car.getY(); z = car.getZ(); }
    public BaseCarEntity getCar() { return car; }
}