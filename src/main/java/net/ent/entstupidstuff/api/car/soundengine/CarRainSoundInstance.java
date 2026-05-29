package net.ent.entstupidstuff.api.car.soundengine;

import net.minecraft.client.resources.sounds.AbstractTickableSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.ent.entstupidstuff.api.car.BaseCarEntity;

/**
 * Rain ambient sound — plays when weather is raining and car is occupied.
 *
 * Volume scales with speed — parked in rain is quiet patter,
 * driving in rain is loud spray from the tires hitting water.
 *
 * Pitch stays mostly constant with slight variation from speed
 * to simulate the changing character of water spray.
 */
public class CarRainSoundInstance extends AbstractTickableSoundInstance
        implements AbstractCarSoundInstance {

    private static final float VOLUME_IDLE    = 0.15f;  // parked in rain
    private static final float VOLUME_DRIVING = 0.55f;  // driving in rain
    private static final float PITCH_BASE     = 0.90f;
    private static final float PITCH_SPEED    = 0.20f;  // slight pitch rise at speed

    private static final float FADE_IN_RATE  = 0.03f;  // rain fades in slowly
    private static final float FADE_OUT_RATE = 0.05f;  // fades out when rain stops

    private final BaseCarEntity car;
    private final CarSoundProfile profile;
    private float fadeFactor = 0f;

    public CarRainSoundInstance(BaseCarEntity car, SoundEvent sound) {
        super(sound, SoundSource.WEATHER, SoundInstance.createUnseededRandom());
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

        boolean raining = car.level().isRaining();
        boolean occupied = car.getFirstPassenger() != null;

        boolean active = raining && occupied;

        fadeFactor = active
            ? Math.min(1f, fadeFactor + FADE_IN_RATE)
            : Math.max(0f, fadeFactor - FADE_OUT_RATE);

        // Volume: louder when driving (tire spray + wind pushing rain)
        float speed = Math.abs(car.getForwardSpeed());
        float speedVolume = VOLUME_IDLE + Math.min(1f, speed / 0.5f) * (VOLUME_DRIVING - VOLUME_IDLE);
        volume = fadeFactor * speedVolume * SoundDistanceHelper.falloff(car, profile);

        // Slight pitch rise at speed — water spray gets higher-pitched
        pitch = PITCH_BASE + Math.min(1f, speed / 0.8f) * PITCH_SPEED;

        if (fadeFactor <= 0f && !occupied) stop();

        float[] mix = CabinSoundMix.apply(car, CabinSoundMix.Layer.RAIN, volume, pitch);
        volume = mix[0]; pitch = mix[1];

    }

    private void syncPosition() { x = car.getX(); y = car.getY(); z = car.getZ(); }
    public BaseCarEntity getCar() { return car; }
}
