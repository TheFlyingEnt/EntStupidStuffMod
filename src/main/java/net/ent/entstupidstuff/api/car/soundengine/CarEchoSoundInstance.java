package net.ent.entstupidstuff.api.car.soundengine;

import net.minecraft.client.resources.sounds.AbstractTickableSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.ent.entstupidstuff.api.car.BaseCarEntity;

public class CarEchoSoundInstance extends AbstractTickableSoundInstance
        implements AbstractCarSoundInstance {

    private static final float VOLUME_MAX    = 0.45f;
    private static final float PITCH_IDLE    = 0.55f;
    private static final float PITCH_MAX     = 1.75f;
    private static final float FADE_IN_RATE  = 0.04f;
    private static final float FADE_OUT_RATE = 0.03f;
    private static final float MIN_SPEED     = 0.02f;

    private final BaseCarEntity car;
    private final CarSoundProfile profile;
    private float fadeFactor = 0f;

    public CarEchoSoundInstance(BaseCarEntity car, SoundEvent idleLoop) {
        super(idleLoop, SoundSource.NEUTRAL, SoundInstance.createUnseededRandom());
        this.car     = car;
        this.profile = car.getSoundProfile();
        this.looping = true;
        this.delay   = 0;
        this.volume  = 0f;
        this.pitch   = PITCH_IDLE;
        this.attenuation = Attenuation.NONE;
        syncPosition();
    }

    @Override public boolean canPlaySound()   { return !car.isRemoved(); }
    @Override public boolean canStartSilent() { return true; }

    @Override
    public void tick() {
        if (car.isRemoved()) { stop(); return; }
        syncPosition();

        float tunnelStrength = car.getTunnelStrength();
        float rpm            = car.getRPM();
        float speed          = Math.abs(car.getForwardSpeed());

        boolean shouldPlay = car.isTunneled()
                          && (car.getFirstPassenger() != null || speed > MIN_SPEED);

        float targetVolume = shouldPlay ? VOLUME_MAX * tunnelStrength : 0f;

        fadeFactor = targetVolume > fadeFactor
            ? Math.min(targetVolume, fadeFactor + FADE_IN_RATE)
            : Math.max(0f,           fadeFactor - FADE_OUT_RATE);

        volume = fadeFactor * profile.engineVolume() * SoundDistanceHelper.falloff(car, profile);
        pitch  = PITCH_IDLE + rpm * (PITCH_MAX - PITCH_IDLE);

        if (fadeFactor <= 0f && car.getFirstPassenger() == null) stop();
    }

    private void syncPosition() { x = car.getX(); y = car.getY(); z = car.getZ(); }
    public BaseCarEntity getCar() { return car; }
}