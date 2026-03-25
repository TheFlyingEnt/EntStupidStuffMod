package net.ent.entstupidstuff.api.car;

import net.ent.entstupidstuff.api.car.CarEntity;
import net.minecraft.client.resources.sounds.AbstractTickableSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;

public class CarAccelSoundInstance extends AbstractTickableSoundInstance
        implements AbstractCarSoundInstance {
 
    private static final float PITCH_LOW  = 0.75f;
    private static final float PITCH_HIGH = 1.40f;
 
    private static final float FADE_IN_RATE  = 0.08f;
    private static final float FADE_OUT_RATE = 0.06f;
    private static final float VOLUME_MAX    = 1.0f;
    private static final float MIN_SPEED     = 0.03f;
 
    /**
     * Speed above which this sound fades out even with throttle held.
     * Must be slightly above CarTopSpeedSoundInstance.THRESHOLD_IN (0.80)
     * to create a crossfade overlap rather than a hard cut.
     */
    private static final float ACCEL_FADE_START = 0.85f;
 
    private final CarEntity car;
    private float fadeFactor = 0f;
 
    public CarAccelSoundInstance(CarEntity car, SoundEvent sound) {
        super(sound, SoundSource.NEUTRAL, SoundInstance.createUnseededRandom());
        this.car         = car;
        this.looping     = true;
        this.delay       = 0;
        this.volume      = 0f;
        this.pitch       = PITCH_LOW;
        this.attenuation = Attenuation.LINEAR;
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
 
        boolean active = car.isThrottleOn() && speed > MIN_SPEED && speed < ACCEL_FADE_START;
 
        fadeFactor = active
            ? Math.min(VOLUME_MAX, fadeFactor + FADE_IN_RATE)
            : Math.max(0f,          fadeFactor - FADE_OUT_RATE);
        volume = fadeFactor;
 
        pitch = PITCH_LOW + rpm * (PITCH_HIGH - PITCH_LOW);
 
        if (fadeFactor <= 0f && !active) stop();
    }
 
    private void syncPosition() { this.x = car.getX(); this.y = car.getY(); this.z = car.getZ(); }
    public CarEntity getCar() { return car; }
}