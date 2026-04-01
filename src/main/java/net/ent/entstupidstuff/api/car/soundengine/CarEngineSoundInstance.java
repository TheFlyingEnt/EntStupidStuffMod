package net.ent.entstupidstuff.api.car.soundengine;

import net.minecraft.client.resources.sounds.AbstractTickableSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.ent.entstupidstuff.api.car.BaseCarEntity;

public class CarEngineSoundInstance extends AbstractTickableSoundInstance
        implements AbstractCarSoundInstance {
 
    /** Pitch at idle RPM (0.1 normalised → 800 RPM display). */
    private static final float PITCH_IDLE = 0.6f;
    /** Pitch at redline (1.0 normalised → 8000 RPM display). */
    private static final float PITCH_MAX  = 1.9f;
 
    private static final float VOLUME_MAX  = 1.0f;
    private static final float FADE_RATE   = 0.05f;
    private static final float MIN_SPEED   = 0.05f;
 
    private final BaseCarEntity car;
    private float fadeFactor = 0f;
 
    public CarEngineSoundInstance(BaseCarEntity car, SoundEvent idleLoop) {
        super(idleLoop, SoundSource.NEUTRAL, SoundInstance.createUnseededRandom());
        this.car         = car;
        this.looping     = true;
        this.delay       = 0;
        this.volume      = 0f;
        this.pitch       = PITCH_IDLE;
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
        float rpm   = car.getRPM(); // 0–1, physics-computed
 
        // Pitch maps linearly from PITCH_IDLE (at idle RPM) to PITCH_MAX (at redline)
        pitch = PITCH_IDLE + rpm * (PITCH_MAX - PITCH_IDLE);
 
        boolean shouldPlay = car.getFirstPassenger() != null || speed > MIN_SPEED;
        fadeFactor = shouldPlay
            ? Math.min(VOLUME_MAX, fadeFactor + FADE_RATE)
            : Math.max(0f,          fadeFactor - FADE_RATE);
        volume = fadeFactor;
 
        if (fadeFactor <= 0f && !shouldPlay) stop();
    }
 
    private void syncPosition() { this.x = car.getX(); this.y = car.getY(); this.z = car.getZ(); }
    public BaseCarEntity getCar() { return car; }
}