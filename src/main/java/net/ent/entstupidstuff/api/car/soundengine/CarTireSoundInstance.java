package net.ent.entstupidstuff.api.car.soundengine;

import net.ent.entstupidstuff.api.car.CarEntity;
import net.minecraft.client.resources.sounds.AbstractTickableSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;

 
public class CarTireSoundInstance extends AbstractTickableSoundInstance
        implements AbstractCarSoundInstance {
 
    private static final float FADE_IN_RATE  = 0.12f;
    private static final float FADE_OUT_RATE = 0.08f;
    private static final float VOLUME_MAX    = 0.9f;
    private static final float PITCH_BASE    = 0.95f;
    private static final float PITCH_RANGE   = 0.2f;
 
    private final CarEntity car;
    private float fadeFactor = 0f;
 
    public CarTireSoundInstance(CarEntity car, SoundEvent sound) {
        super(sound, SoundSource.NEUTRAL, SoundInstance.createUnseededRandom());
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
 
        boolean drifting = car.isDrifting();
        boolean burning  = car.isBurningOut();
        float   speed    = Math.abs(car.getForwardSpeed());
        // Burnout bypasses the speed threshold — tyres screech at standstill
        boolean active   = (drifting && speed > 0.15f) || burning;
 
        fadeFactor = active
            ? Math.min(VOLUME_MAX, fadeFactor + FADE_IN_RATE)
            : Math.max(0f,          fadeFactor - FADE_OUT_RATE);
        volume = fadeFactor;
 
        if (active) pitch = PITCH_BASE + (speed / 1.0f) * PITCH_RANGE;
 
        if (fadeFactor <= 0f && !active) stop();
    }
 
    private void syncPosition() { this.x = car.getX(); this.y = car.getY(); this.z = car.getZ(); }
    public CarEntity getCar() { return car; }
}