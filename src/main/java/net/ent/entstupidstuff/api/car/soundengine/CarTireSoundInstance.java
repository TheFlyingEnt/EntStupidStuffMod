package net.ent.entstupidstuff.api.car.soundengine;

import net.minecraft.client.resources.sounds.AbstractTickableSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.ent.entstupidstuff.api.car.BaseCarEntity;

public class CarTireSoundInstance extends AbstractTickableSoundInstance
        implements AbstractCarSoundInstance {

    private static final float FADE_IN_RATE  = 0.12f;
    private static final float FADE_OUT_RATE = 0.08f;
    private static final float PITCH_BASE    = 0.95f;
    private static final float PITCH_RANGE   = 0.2f;

    private final BaseCarEntity car;
    private final CarSoundProfile profile;
    private float fadeFactor = 0f;

    public CarTireSoundInstance(BaseCarEntity car, SoundEvent sound) {
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

        boolean drifting = car.isDrifting();
        boolean burning  = car.isBurningOut();
        float   speed    = Math.abs(car.getForwardSpeed());
        boolean active   = (drifting && speed > 0.15f) || burning;

        fadeFactor = active
            ? Math.min(1f, fadeFactor + FADE_IN_RATE)
            : Math.max(0f, fadeFactor - FADE_OUT_RATE);

        volume = fadeFactor * profile.tireVolume() * SoundDistanceHelper.falloff(car, profile);

        if (active) pitch = PITCH_BASE + (speed / 1.0f) * PITCH_RANGE;

        if (fadeFactor <= 0f && car.getFirstPassenger() == null) stop();

        float[] mix = CabinSoundMix.apply(car, CabinSoundMix.Layer.TIRES, volume, pitch);
        volume = mix[0]; pitch = mix[1];

    }

    private void syncPosition() { x = car.getX(); y = car.getY(); z = car.getZ(); }
    public BaseCarEntity getCar() { return car; }
}