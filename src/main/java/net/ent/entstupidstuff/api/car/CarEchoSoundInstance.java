package net.ent.entstupidstuff.api.car;

import net.minecraft.client.resources.sounds.AbstractTickableSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;

/**
 * CarEchoSoundInstance — tunnel reverb/echo effect.
 *
 * Plays the engine idle loop at reduced volume and slightly lower pitch
 * to simulate sound reflecting off a tunnel ceiling. Volume scales with
 * how tight the ceiling is (getTunnelStrength()):
 *   strength 1.0 (1 block above) → VOLUME_MAX (loud echo)
 *   strength 0.0 (8+ blocks / no ceiling) → silent
 *
 * The slight pitch offset (PITCH_OFFSET below 1.0) mimics the way
 * reflected sound arrives slightly delayed and with absorbed high frequencies.
 */
public class CarEchoSoundInstance extends AbstractTickableSoundInstance
        implements AbstractCarSoundInstance {

    /** How much quieter the echo is compared to the direct sound. */
    private static final float VOLUME_MAX    = 0.45f;

    /** Pitch relative to the main engine sound — slightly lower = "roomy" feel. */
    private static final float PITCH_IDLE    = 0.55f;
    private static final float PITCH_MAX     = 1.75f;

    private static final float FADE_IN_RATE  = 0.04f; // slow fade — echo builds gradually
    private static final float FADE_OUT_RATE = 0.03f; // slow fade out when leaving tunnel
    private static final float MIN_SPEED     = 0.02f;

    private final CarEntity car;
    private float fadeFactor = 0f;

    public CarEchoSoundInstance(CarEntity car, SoundEvent idleLoop) {
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

        float tunnelStrength = car.getTunnelStrength(); // 0.0 = open, 1.0 = tight ceiling
        float rpm            = car.getRPM();
        float speed          = Math.abs(car.getForwardSpeed());

        boolean shouldPlay = car.isTunneled()
                          && (car.getFirstPassenger() != null || speed > MIN_SPEED);

        // Target volume scales with how tight the tunnel is
        float targetVolume = shouldPlay ? VOLUME_MAX * tunnelStrength : 0f;

        fadeFactor = targetVolume > fadeFactor
            ? Math.min(targetVolume, fadeFactor + FADE_IN_RATE)
            : Math.max(0f,           fadeFactor - FADE_OUT_RATE);
        volume = fadeFactor;

        // Echo pitch tracks engine RPM but at a lower base — sounds like a delayed reflection
        pitch = PITCH_IDLE + rpm * (PITCH_MAX - PITCH_IDLE);

        if (fadeFactor <= 0f && !shouldPlay) stop();
    }

    private void syncPosition() { this.x = car.getX(); this.y = car.getY(); this.z = car.getZ(); }
    public CarEntity getCar() { return car; }
}