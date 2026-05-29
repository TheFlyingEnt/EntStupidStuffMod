package net.ent.entstupidstuff.api.car.soundengine;

import net.minecraft.client.resources.sounds.AbstractTickableSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.ent.entstupidstuff.api.car.BaseCarEntity;

public class CarEngineSoundInstance extends AbstractTickableSoundInstance
        implements AbstractCarSoundInstance {

    private static final float FADE_RATE = 0.05f;
    private static final float MIN_SPEED = 0.05f;

    private final BaseCarEntity car;
    private final CarSoundProfile profile;
    private float fadeFactor = 0f;

    public CarEngineSoundInstance(BaseCarEntity car, SoundEvent sound) {
        super(sound, SoundSource.NEUTRAL, SoundInstance.createUnseededRandom());
        this.car     = car;
        this.profile = car.getSoundProfile();
        this.looping = true;
        this.delay   = 0;
        this.volume  = 0f;
        this.pitch   = profile.enginePitchLow();
        this.attenuation = Attenuation.NONE; // we handle distance ourselves
        syncPosition();
    }

    @Override public boolean canPlaySound()   { return !car.isRemoved(); }
    @Override public boolean canStartSilent() { return true; }

    @Override
    public void tick() {
        if (car.isRemoved()) { stop(); return; }
        syncPosition();

        float speed = Math.abs(car.getForwardSpeed());
        float rpm   = car.getRPM();

        pitch = profile.enginePitchLow() + rpm * (profile.enginePitchHigh() - profile.enginePitchLow());

        boolean shouldPlay = car.getFirstPassenger() != null || speed > MIN_SPEED;
        fadeFactor = shouldPlay
            ? Math.min(1f, fadeFactor + FADE_RATE)
            : Math.max(0f, fadeFactor - FADE_RATE);

        volume = fadeFactor * profile.engineVolume() * SoundDistanceHelper.falloff(car, profile);

        if (fadeFactor <= 0f && car.getFirstPassenger() == null) stop();

        float[] mix = CabinSoundMix.apply(car, CabinSoundMix.Layer.ENGINE, volume, pitch);
        volume = mix[0]; pitch = mix[1];

    }

    private void syncPosition() { x = car.getX(); y = car.getY(); z = car.getZ(); }
    public BaseCarEntity getCar() { return car; }
}