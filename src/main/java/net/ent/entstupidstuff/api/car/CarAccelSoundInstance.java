package net.ent.entstupidstuff.api.car;

import net.ent.entstupidstuff.api.car.CarEntity;
import net.minecraft.client.resources.sounds.AbstractTickableSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;

public class CarAccelSoundInstance extends AbstractTickableSoundInstance implements AbstractCarSoundInstance {
 
    // ── How fast the speed must be increasing to count as "accelerating" ──
    // If (currentSpeed - prevSpeed) per tick exceeds this, fade in.
    private static final float ACCEL_DELTA_THRESHOLD = 0.008f;
 
    // ── Fade rates ─────────────────────────────────────────────
    private static final float FADE_IN_RATE  = 0.08f;
    private static final float FADE_OUT_RATE = 0.06f;
    private static final float VOLUME_MAX    = 1.0f;
 
    // ── Pitch range ────────────────────────────────────────────
    // At low speed the accel sound has a lower pitch (first-gear pull);
    // at high speed it rises (higher gear, more intense).
    private static final float PITCH_LOW  = 0.75f;
    private static final float PITCH_HIGH = 1.35f;
 
    // ── Minimum speed before the accel sound can play at all ──
    private static final float MIN_SPEED = 0.05f;
 
    private final CarEntity car;
    private float fadeFactor = 0f;
    private float prevSpeed  = 0f;
 
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
 
    @Override
    public boolean canPlaySound() {
        return !car.isRemoved();
    }
 
    @Override
    public boolean canStartSilent() {
        return true;
    }
 
    @Override
    public void tick() {
        if (car.isRemoved()) {
            stop();
            return;
        }
 
        syncPosition();
 
        float speed = Math.abs(car.getForwardSpeed());
        float delta = speed - prevSpeed;          // positive = gaining speed
        prevSpeed   = speed;
 
        // Active when speed is above minimum AND the car is still gaining speed
        boolean accelerating = speed > MIN_SPEED && delta > ACCEL_DELTA_THRESHOLD;
 
        fadeFactor = accelerating
            ? Math.min(VOLUME_MAX, fadeFactor + FADE_IN_RATE)
            : Math.max(0f,          fadeFactor - FADE_OUT_RATE);
 
        volume = fadeFactor;
 
        // Pitch tracks speed — gives the sensation of pulling through a gear
        float speedFraction = Math.min(1f, speed / 1.0f);   // normalise to MAX_SPEED = 1.0
        pitch = PITCH_LOW + speedFraction * (PITCH_HIGH - PITCH_LOW);
 
        if (fadeFactor <= 0f && !accelerating) {
            stop();
        }
    }
 
    private void syncPosition() {
        this.x = car.getX();
        this.y = car.getY();
        this.z = car.getZ();
    }
 
    public CarEntity getCar() { return car; }
    //public boolean isStopped() { return isStopped; }
}