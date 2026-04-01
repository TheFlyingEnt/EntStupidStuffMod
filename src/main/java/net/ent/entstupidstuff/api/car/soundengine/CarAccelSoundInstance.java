package net.ent.entstupidstuff.api.car.soundengine;

import net.ent.entstupidstuff.api.car.BaseCarEntity;
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
    private static final float ACCEL_FADE_START  = 0.85f;
    private final BaseCarEntity car;
    private float fadeFactor = 0f;
 
    public CarAccelSoundInstance(BaseCarEntity car, SoundEvent sound) {
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
 
        // Forward only — reverse handled by CarReverseSoundInstance.
        float signedSpeed = car.getForwardSpeed();
        float speed       = Math.abs(signedSpeed);
        float rpm         = car.getRPM();

        // Active when moving forward under throttle, OR during burnout.
        // Without the burnout check, signedSpeed ≈ 0 → sound is silent
        // the whole time even though the engine is screaming.
        boolean burningOut = car.isBurningOut();
        boolean active = burningOut
                      || (car.isThrottleOn() && signedSpeed > MIN_SPEED && speed < ACCEL_FADE_START);

        fadeFactor = active
            ? Math.min(VOLUME_MAX, fadeFactor + FADE_IN_RATE)
            : Math.max(0f,          fadeFactor - FADE_OUT_RATE);
        volume = fadeFactor;

        // Pitch always follows engineRPM — during burnout getRPM() returns
        // the climbing burnoutRPM so the pitch rises correctly through the rev.
        pitch = PITCH_LOW + rpm * (PITCH_HIGH - PITCH_LOW);

        if (fadeFactor <= 0f && !active) stop();
    }
 
    private void syncPosition() { this.x = car.getX(); this.y = car.getY(); this.z = car.getZ(); }
    public BaseCarEntity getCar() { return car; }
}