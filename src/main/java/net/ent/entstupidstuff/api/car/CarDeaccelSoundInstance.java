package net.ent.entstupidstuff.api.car;

import net.minecraft.client.resources.sounds.AbstractTickableSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance.Attenuation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;

public class CarDeaccelSoundInstance extends AbstractTickableSoundInstance
        implements AbstractCarSoundInstance {
 
    private static final float MIN_SPEED     = 0.05f;
    private static final float FADE_IN_RATE  = 0.10f;
    private static final float FADE_OUT_RATE = 0.07f;
    private static final float VOLUME_MAX    = 1.0f;
    private static final float PITCH_LOW     = 0.85f;
    private static final float PITCH_HIGH    = 1.20f;
 
    private final CarEntity car;
    private float fadeFactor = 0f;
    private float prevSpeed  = 0f;
 
    public CarDeaccelSoundInstance(CarEntity car, SoundEvent sound) {
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
 
        float speed  = Math.abs(car.getForwardSpeed());
        float delta  = speed - prevSpeed;
        prevSpeed    = speed;

        // Active on hard braking (S key) OR engine-braking at high speed.
        // The engine-braking check gives the "lift-off roar" in 6th gear
        // that the top-speed sound fades out too slowly to cover on its own.
        boolean braking      = car.isBraking() && speed > MIN_SPEED;
        boolean engineBrake  = !car.isThrottleOn() && speed > 0.50f && delta < -0.004f;
        boolean active       = (braking || engineBrake) && speed > MIN_SPEED;
 
 
        fadeFactor = active
            ? Math.min(VOLUME_MAX, fadeFactor + FADE_IN_RATE)
            : Math.max(0f,          fadeFactor - FADE_OUT_RATE);
        volume = fadeFactor;
 
        float speedFraction = Math.min(1f, speed);
        pitch = PITCH_LOW + speedFraction * (PITCH_HIGH - PITCH_LOW);
 
        if (fadeFactor <= 0f && !active) stop();
    }
 
    private void syncPosition() { this.x = car.getX(); this.y = car.getY(); this.z = car.getZ(); }
    public CarEntity getCar() { return car; }
}