package net.ent.entstupidstuff.api.car;

import net.ent.entstupidstuff.api.car.CarEntity;
import net.minecraft.client.resources.sounds.AbstractTickableSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;

public class CarTopSpeedSoundInstance extends AbstractTickableSoundInstance
        implements AbstractCarSoundInstance {
 
    /** Start fading in at 80% of MAX_SPEED (0.8 blocks/tick). */
    private static final float THRESHOLD_IN  = 0.80f;
 
    /** Fade out below 70% of MAX_SPEED — small hysteresis band. */
    private static final float THRESHOLD_OUT = 0.70f;
 
    private static final float FADE_IN_RATE  = 0.05f;
    private static final float FADE_OUT_RATE = 0.04f;
    private static final float VOLUME_MAX    = 1.0f;
 
    /** Slight pitch variation to blend with the engine layer. */
    private static final float PITCH_VALUE   = 1.0f;
 
    private final CarEntity car;
    private float fadeFactor = 0f;
 
    public CarTopSpeedSoundInstance(CarEntity car, SoundEvent sound) {
        super(sound, SoundSource.NEUTRAL, SoundInstance.createUnseededRandom());
        this.car         = car;
        this.looping     = true;
        this.delay       = 0;
        this.volume      = 0f;
        this.pitch       = PITCH_VALUE;
        this.attenuation = Attenuation.LINEAR;
        syncPosition();
    }
 
    @Override public boolean canPlaySound()   { return !car.isRemoved(); }
    @Override public boolean canStartSilent() { return true; }
 
    @Override
    public void tick() {
        if (car.isRemoved()) { stop(); return; }
 
        syncPosition();
 
        float speed  = Math.abs(car.getForwardSpeed());
        boolean active = fadeFactor > 0f ? speed > THRESHOLD_OUT : speed > THRESHOLD_IN;
 
        fadeFactor = active
            ? Math.min(VOLUME_MAX, fadeFactor + FADE_IN_RATE)
            : Math.max(0f,          fadeFactor - FADE_OUT_RATE);
 
        volume = fadeFactor;
 
        if (fadeFactor <= 0f && !active) stop();
    }
 
    private void syncPosition() {
        this.x = car.getX();
        this.y = car.getY();
        this.z = car.getZ();
    }
 
    //@Override public boolean isStopped() { return isStopped; }
    public CarEntity getCar() { return car; }
}
