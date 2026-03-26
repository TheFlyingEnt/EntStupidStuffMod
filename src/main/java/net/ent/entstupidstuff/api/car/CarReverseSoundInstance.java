package net.ent.entstupidstuff.api.car;

import net.minecraft.client.resources.sounds.AbstractTickableSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;

public class CarReverseSoundInstance extends AbstractTickableSoundInstance
        implements AbstractCarSoundInstance {
 
    /** Pitch at the start of reverse motion. */
    private static final float PITCH_BASE = 0.72f;
    /** Pitch at maximum reverse speed (0.35 bl/tick). */
    private static final float PITCH_MAX  = 1.10f;
    /** Maximum reverse speed used for pitch normalisation. */
    private static final float MAX_REV_SPEED = 0.35f;
 
    private static final float MIN_SPEED     = 0.02f;
    private static final float FADE_IN_RATE  = 0.10f;
    private static final float FADE_OUT_RATE = 0.08f;
    private static final float VOLUME_MAX    = 0.85f;
 
    private final CarEntity car;
    private float fadeFactor = 0f;
 
    public CarReverseSoundInstance(CarEntity car, SoundEvent idleLoop) {
        super(idleLoop, SoundSource.NEUTRAL, SoundInstance.createUnseededRandom());
        this.car         = car;
        this.looping     = true;
        this.delay       = 0;
        this.volume      = 0f;
        this.pitch       = PITCH_BASE;
        this.attenuation = Attenuation.LINEAR;
        syncPosition();
    }
 
    @Override public boolean canPlaySound()   { return !car.isRemoved(); }
    @Override public boolean canStartSilent() { return true; }
 
    @Override
    public void tick() {
        if (car.isRemoved()) { stop(); return; }
 
        syncPosition();
 
        float signedSpeed = car.getForwardSpeed(); // negative when reversing
 
        // Active only while actually moving backward under throttle
        boolean active = car.isThrottleOn()
                      && signedSpeed < -MIN_SPEED;
 
        fadeFactor = active
            ? Math.min(VOLUME_MAX, fadeFactor + FADE_IN_RATE)
            : Math.max(0f,          fadeFactor - FADE_OUT_RATE);
        volume = fadeFactor;
 
        if (active) {
            // Pitch climbs as reverse speed builds
            float revFraction = Math.min(1f, Math.abs(signedSpeed) / MAX_REV_SPEED);
            pitch = PITCH_BASE + revFraction * (PITCH_MAX - PITCH_BASE);
        }
 
        if (fadeFactor <= 0f && !active) stop();
    }
 
    private void syncPosition() { this.x = car.getX(); this.y = car.getY(); this.z = car.getZ(); }
    public CarEntity getCar() { return car; }
}
