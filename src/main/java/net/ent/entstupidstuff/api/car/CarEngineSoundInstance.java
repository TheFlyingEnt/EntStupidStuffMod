package net.ent.entstupidstuff.api.car;

import net.minecraft.client.resources.sounds.AbstractTickableSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.ent.entstupidstuff.api.car.CarEntity;

public class CarEngineSoundInstance extends AbstractTickableSoundInstance implements AbstractCarSoundInstance {
 
    // ── Pitch range ──────────────────────────────────────────
    /** Pitch at idle (engine just ticking over). */
    private static final float PITCH_IDLE = 0.6f;
    /** Pitch at full redline RPM. */
    private static final float PITCH_MAX  = 1.9f;
 
    // ── RPM simulation targets ───────────────────────────────
    /** normalised RPM target when throttling hard */
    private static final float TARGET_RPM_HIGH = 0.95f;
    /** normalised RPM target when coasting */
    private static final float TARGET_RPM_COAST = 0.25f;
    /** normalised RPM target when stationary */
    private static final float TARGET_RPM_IDLE  = 0.05f;
 
    /** How fast RPM rises (per tick, 0–1 scale). Higher = snappier throttle response. */
    private static final float RPM_RISE_RATE  = 0.04f;
    /** How fast RPM falls when lifting off. */
    private static final float RPM_FALL_RATE  = 0.025f;
 
    /** Sharp RPM drop when a gear change is detected (shift blip dip). */
    private static final float GEAR_SHIFT_DROP = 0.30f;
 
    // ── Volume ───────────────────────────────────────────────
    /** Maximum volume when fully in-world. */
    private static final float VOLUME_MAX  = 1.0f;
    /** Fade-in/out rate per tick (0–1 scale). */
    private static final float FADE_RATE   = 0.05f;
 
    // ── Speed threshold that counts as "throttle applied" ────
    private static final float THROTTLE_SPEED_THRESHOLD = 0.05f;
 
    // ── Gear detection ───────────────────────────────────────
    /** Speed brackets that define gear boundaries (blocks/tick). */
    private static final float[] GEAR_THRESHOLDS = { 0.05f, 0.18f, 0.35f, 0.55f, 0.80f };
 
    // ── State ────────────────────────────────────────────────
    private final CarEntity car;
 
    /** Normalised simulated RPM, 0 = idle, 1 = redline. */
    private float simRpm = TARGET_RPM_IDLE;
 
    /** Current fade multiplier, 0 = silent, 1 = full volume. */
    private float fadeFactor = 0f;
 
    /** Last detected gear, used to trigger the shift blip. */
    private int lastGear = 0;
 
    /** Previous speed sample — used to detect acceleration vs coast. */
    private float prevSpeed = 0f;
 
    // ─────────────────────────────────────────────────────────
 
    public CarEngineSoundInstance(CarEntity car, SoundEvent idleLoop) {
        super(idleLoop, SoundSource.NEUTRAL, SoundInstance.createUnseededRandom());
        this.car      = car;
        this.looping  = true;
        this.delay    = 0;
        this.volume   = 0f;   // start silent; fade in handled in tick()
        this.pitch    = PITCH_IDLE;
        this.attenuation = Attenuation.LINEAR;
        syncPosition();
    }
 
    // ─────────────────────────────────────────────────────────
    //  SoundInstance contract
    // ─────────────────────────────────────────────────────────
 
    @Override
    public boolean canPlaySound() {
        return !car.isRemoved();
    }
 
    @Override
    public boolean canStartSilent() {
        // Allow the sound to start at volume 0 and fade in — prevents the
        // audio engine from discarding it before we've had a chance to set volume
        return true;
    }
 
    // ─────────────────────────────────────────────────────────
    //  Tick
    // ─────────────────────────────────────────────────────────
 
    @Override
    public void tick() {
        car.setRPM(simRpm);
        if (car.isRemoved()) {
            stop();
            return;
        }
 
        // ── 1. Follow the car ──
        syncPosition();
 
        float speed = Math.abs(car.getForwardSpeed());
        int   gear  = getGear(speed);
 
        // ── 2. Gear-shift blip ──
        if (gear != lastGear && gear > lastGear && speed > THROTTLE_SPEED_THRESHOLD) {
            // Upshift: drop RPM then let it climb again
            simRpm = Math.max(0f, simRpm - GEAR_SHIFT_DROP);
        }
        lastGear = gear;
 
        // ── 3. RPM simulation ──
        boolean accelerating = speed > THROTTLE_SPEED_THRESHOLD && speed >= prevSpeed;
        float targetRpm;
 
        if (speed < THROTTLE_SPEED_THRESHOLD) {
            targetRpm = TARGET_RPM_IDLE;
        } else if (accelerating) {
            // Map target RPM within this gear's band (0.5..1.0 of high)
            float gearProgress = getProgressWithinGear(speed, gear);
            targetRpm = TARGET_RPM_COAST + gearProgress * (TARGET_RPM_HIGH - TARGET_RPM_COAST);
        } else {
            targetRpm = TARGET_RPM_COAST;
        }
 
        float rpmRate = (targetRpm > simRpm) ? RPM_RISE_RATE : RPM_FALL_RATE;
        simRpm += (targetRpm - simRpm) * rpmRate * 20f; // *20 = per-second feel
        simRpm  = Math.max(0f, Math.min(1f, simRpm));
 
        prevSpeed = speed;
 
        // ── 4. Apply pitch ──
        pitch = PITCH_IDLE + simRpm * (PITCH_MAX - PITCH_IDLE);
 
        // ── 5. Fade volume in/out ──
        boolean shouldPlay = car.getFirstPassenger() != null || speed > THROTTLE_SPEED_THRESHOLD;
        fadeFactor = shouldPlay
            ? Math.min(VOLUME_MAX, fadeFactor + FADE_RATE)
            : Math.max(0f,         fadeFactor - FADE_RATE);
 
        volume = fadeFactor;
 
        if (fadeFactor <= 0f && !shouldPlay) {
            stop();
        }
    }
 
    // ─────────────────────────────────────────────────────────
    //  Helpers
    // ─────────────────────────────────────────────────────────
 
    private void syncPosition() {
        this.x = car.getX();
        this.y = car.getY();
        this.z = car.getZ();
    }
 
    private int getGear(float speed) {
        for (int i = 0; i < GEAR_THRESHOLDS.length; i++) {
            if (speed < GEAR_THRESHOLDS[i]) return i;
        }
        return GEAR_THRESHOLDS.length;
    }
 
    /**
     * Returns how far through the current gear band the speed is, 0–1.
     * Used to position the RPM target within the gear — the RPM climbs
     * toward redline as the car approaches the top of each gear's range.
     */
    private float getProgressWithinGear(float speed, int gear) {
        float low  = gear > 0 ? GEAR_THRESHOLDS[gear - 1] : 0f;
        float high = gear < GEAR_THRESHOLDS.length ? GEAR_THRESHOLDS[gear] : 1f;
        if (high <= low) return 1f;
        return Math.max(0f, Math.min(1f, (speed - low) / (high - low)));
    }
 
    // ─────────────────────────────────────────────────────────
    //  Public accessors (used by CarSoundManager)
    // ─────────────────────────────────────────────────────────
 
    public CarEntity getCar() {
        return car;
    }
 
    /*public boolean isStopped() {
        return isStopped;
    }*/
}
