package net.ent.entstupidstuff.api.car;

import org.jetbrains.annotations.Nullable;

import net.ent.entstupidstuff.sound.SoundFactory;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.VehicleEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.Vec3;

public class CarEntity extends VehicleEntity {
 
    // ── Seat tuning ────────────────────────────────────────────
    private static final double SEAT_HEIGHT  =  0.4;
    private static final double SEAT_SIDE    =  0.5;
    private static final double SEAT_FORWARD = -0.5;
 
    // ── Physics constants ──────────────────────────────────────
    private static final double MAX_SPEED             = 1.0;
    private static final double MAX_REVERSE           = 0.35;
    private static final double THROTTLE              = 0.055;
    private static final double REVERSE_THROTTLE      = 0.028;
    private static final double BRAKE_FORCE           = 0.10;
    private static final double ROLLING_DRAG          = 0.975;
    private static final double DRAG_THRESHOLD        = 0.7;
    private static final double AERO_DRAG_EXTRA       = 0.008;
    private static final double NORMAL_GRIP           = 0.12;
    private static final double DRIFT_RETENTION       = 0.93;
    private static final double DRIFT_KICK_FACTOR     = 0.38;
    private static final double DRIFT_SPEED_THRESHOLD = 0.30;
    private static final float  MAX_STEER_DEG         = 4.2f;
    private static final double PEAK_STEER_SPEED      = 0.35;
    private static final double HIGH_SPEED_STEER_FRACTION = 0.55;
    private static final float  DRIFT_STEER_BOOST     = 1.7f;
    private static final double GRAVITY               = 0.08;
    private static final double MAX_FALL_SPEED        = 3.92;
    private static final double GROUND_STICK          = -0.08;
    private static final double HANDBRAKE_DRAG        = 0.82;
 
    // ── RPM constants (normalised 0–1, display multiplies by 8000) ─
    //
    //  Each gear band spans from GEAR_START_RPM to REDLINE_RPM.
    //  The instant drop at each gear boundary IS the shift effect —
    //  no extra code needed; it falls out of the math automatically.
    //
    //  Example at speed = 0.18 (gear 1 → 2 boundary):
    //    gear=1, progress=1.0 → rpm = 0.25 + 1.0*0.75 = 1.00  (8000 RPM)
    //    gear=2, progress=0.0 → rpm = 0.25 + 0.0*0.75 = 0.25  (2000 RPM)
    //    ↑ Instant drop of 6000 RPM — exactly like a real upshift.
    private static final float IDLE_RPM_NORM    = 0.10f;  // 800 RPM at idle
    private static final float GEAR_START_NORM  = 0.25f;  // 2000 RPM at start of each gear
    private static final float REDLINE_NORM     = 1.00f;  // 8000 RPM at redline
    private static final float[] GEAR_THRESHOLDS = { 0.05f, 0.18f, 0.35f, 0.55f, 0.80f };
 
    // ── Synced data ────────────────────────────────────────────
 
    private static final EntityDataAccessor<Boolean> DATA_DRIFTING =
        SynchedEntityData.defineId(CarEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Float> DATA_WHEEL_SPIN =
        SynchedEntityData.defineId(CarEntity.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Float> DATA_STEER_INPUT =
        SynchedEntityData.defineId(CarEntity.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Float> DATA_FORWARD_SPEED =
        SynchedEntityData.defineId(CarEntity.class, EntityDataSerializers.FLOAT);
 
    /**
     * Physics-based RPM, normalised 0–1.
     * Computed from speed + gear in tickPhysics.
     * Sound instances READ this; nothing writes it except tickPhysics.
     */
    private static final EntityDataAccessor<Float> DATA_RPM =
        SynchedEntityData.defineId(CarEntity.class, EntityDataSerializers.FLOAT);
 
    /**
     * True while player holds W and is accelerating forward (or from rest).
     * CarAccelSoundInstance uses this to stay active through the full pull.
     */
    private static final EntityDataAccessor<Boolean> DATA_THROTTLE =
        SynchedEntityData.defineId(CarEntity.class, EntityDataSerializers.BOOLEAN);
 
    /**
     * True while player is actively braking (S while moving forward, or
     * W while reversing). CarDeaccelSoundInstance uses this.
     */
    private static final EntityDataAccessor<Boolean> DATA_BRAKING =
        SynchedEntityData.defineId(CarEntity.class, EntityDataSerializers.BOOLEAN);
 
    // ── Server-side state ──────────────────────────────────────
    private boolean wasDrifting = false;
    private float   wheelSpin   = 0f;
 
    public CarEntity(EntityType<?> type, Level level) {
        super(type, level);
    }
 
    @Override public float maxUpStep() { return 1.0f; }
 
    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(DATA_DRIFTING,      false);
        builder.define(DATA_WHEEL_SPIN,    0f);
        builder.define(DATA_STEER_INPUT,   0f);
        builder.define(DATA_FORWARD_SPEED, 0f);
        builder.define(DATA_RPM,           IDLE_RPM_NORM);
        builder.define(DATA_THROTTLE,      false);
        builder.define(DATA_BRAKING,       false);
    }
 
    @Override protected Item getDropItem() { return null; }
 
    // ── Tick ───────────────────────────────────────────────────
 
    @Override
    public void tick() {
        super.tick();
 
        if (!this.isLocalInstanceAuthoritative()) {
            this.setDeltaMovement(Vec3.ZERO);
            return;
        }
 
        boolean forward = false, backward = false,
                left    = false, right    = false, drift = false;
 
        if (this.getFirstPassenger() instanceof Player player) {
            forward  = player.zza  >  0f;
            backward = player.zza  <  0f;
            left     = player.xxa  >  0f;
            right    = player.xxa  <  0f;
            drift    = player.isJumping();
 
            if (this.tickCount % 2 == 0) {
                displaySpeed(player);
                dataCollection();
            }
        }
 
        tickPhysics(forward, backward, left, right, drift);
 
        this.move(MoverType.SELF, this.getDeltaMovement());
 
        if (this.onGround() && this.getDeltaMovement().y < 0)
            this.setDeltaMovement(this.getDeltaMovement().x, 0.0, this.getDeltaMovement().z);
 
        this.applyEffectsFromBlocks();
 
        if (this.level().isClientSide()) {
            spawnWheelParticles();
            spawnExhaust();
        }
    }
 
    // ── Particles ──────────────────────────────────────────────
 
    private void spawnWheelParticles() {
        if (!this.onGround()) return;
        double speed = Math.abs(this.getForwardSpeed());
        if (speed < 0.05) return;
        boolean drifting = this.isDrifting();
        double yRad = Math.toRadians(this.getYRot());
        double sin = Math.sin(yRad), cos = Math.cos(yRad);
        double side = 0.6, fwd = -0.8, y = 0.05;
        spawnWheelParticleAt( side*cos + fwd*-sin, y,  side*sin + fwd*cos, drifting);
        spawnWheelParticleAt(-side*cos + fwd*-sin, y, -side*sin + fwd*cos, drifting);
    }
 
    private void spawnWheelParticleAt(double ox, double oy, double oz, boolean drifting) {
        Vec3 pos = this.position();
        double px = pos.x+ox, py = pos.y+oy, pz = pos.z+oz;
        Vec3 vel = this.getDeltaMovement();
        double vx = -vel.x*0.5 + (random.nextDouble()-0.5)*0.1;
        double vz = -vel.z*0.5 + (random.nextDouble()-0.5)*0.1;
        if (drifting) { vx *= 1.5; vz *= 1.5; }
        BlockState bs = this.level().getBlockState(BlockPos.containing(px, py-0.2, pz));
        if (drifting)
            this.level().addParticle(ParticleTypes.CAMPFIRE_COSY_SMOKE, px, py, pz, vx, 0.1, vz);
        this.level().addParticle(new BlockParticleOption(ParticleTypes.BLOCK, bs), px, py, pz, vx, 0.1, vz);
    }
 
    private void spawnExhaust() {
        double speed = Math.abs(this.getForwardSpeed());
        if (speed < 0.02) return;
        double yRad = Math.toRadians(this.getYRot());
        double px = this.getX() + (-Math.cos(yRad)*1.2);
        double py = this.getY() + 0.3;
        double pz = this.getZ() + (-Math.sin(yRad)*1.2);
        Vec3 vel = this.getDeltaMovement();
        this.level().addParticle(ParticleTypes.SMOKE, px, py, pz,
            -vel.x*0.2+(random.nextDouble()-0.5)*0.02, 0.02,
            -vel.z*0.2+(random.nextDouble()-0.5)*0.02);
    }
 
    // ── Physics ────────────────────────────────────────────────
 
    private void tickPhysics(boolean forward, boolean backward,
                              boolean left,    boolean right, boolean driftKey) {
        double yRad = Math.toRadians(this.getYRot());
        double sinY = Math.sin(yRad), cosY = Math.cos(yRad);
        Vec3 vel = this.getDeltaMovement();
 
        double localFwd = -vel.x * sinY + vel.z * cosY;
        double localLat =  vel.x * cosY + vel.z * sinY;
        double localY   =  vel.y;
        double speed    = Math.abs(localFwd);
 
        boolean canDrift = speed > DRIFT_SPEED_THRESHOLD && (left || right);
        boolean drifting = driftKey && canDrift;
        this.entityData.set(DATA_DRIFTING, drifting);
 
        // ── Capture input state for sound BEFORE modifying localFwd ──
        // Throttle: W held while going forward or pulling from rest
        boolean throttleActive = forward && localFwd >= -0.05;
        // Braking: S while moving forward, or W while reversing
        boolean brakingActive  = (backward && localFwd > 0.05)
                              || (forward  && localFwd < -0.05);
        this.entityData.set(DATA_THROTTLE, throttleActive);
        this.entityData.set(DATA_BRAKING,  brakingActive);
 
        // ── Throttle / Brake / Reverse ──
        if (forward) {
            localFwd = localFwd < -0.05 ? Math.min(0.0, localFwd + BRAKE_FORCE)
                                        : Math.min(MAX_SPEED, localFwd + THROTTLE);
        } else if (backward) {
            localFwd = localFwd > 0.05 ? Math.max(0.0, localFwd - BRAKE_FORCE)
                                       : Math.max(-MAX_REVERSE, localFwd - REVERSE_THROTTLE);
        }
 
        if (driftKey && !canDrift) localFwd *= HANDBRAKE_DRAG;
        localFwd *= ROLLING_DRAG;
        if (Math.abs(localFwd) > DRAG_THRESHOLD)
            localFwd -= Math.signum(localFwd) * (Math.abs(localFwd) - DRAG_THRESHOLD) * AERO_DRAG_EXTRA * 20;
        if (Math.abs(localFwd) < 0.001) localFwd = 0.0;
 
        // ── Lateral / Grip ──
        if (drifting) {
            localLat *= DRIFT_RETENTION;
            if (!wasDrifting) localLat += (left ? -1.0 : 1.0) * speed * DRIFT_KICK_FACTOR;
        } else {
            localLat *= NORMAL_GRIP;
        }
        wasDrifting = drifting;
        if (Math.abs(localLat) < 0.001) localLat = 0.0;
 
        // ── Steering ──
        float rawSteer = left ? -1f : right ? 1f : 0f;
        this.entityData.set(DATA_STEER_INPUT, rawSteer);
        if (localFwd != 0.0 && (left || right)) {
            float steerDeg    = computeSteerAngle(speed, drifting);
            float reverseSign = (localFwd < 0) ? -1f : 1f;
            if (left)  this.setYRot(this.getYRot() - steerDeg * reverseSign);
            if (right) this.setYRot(this.getYRot() + steerDeg * reverseSign);
        }
 
        // ── Gravity ──
        localY = this.onGround() ? GROUND_STICK : Math.max(-MAX_FALL_SPEED, localY - GRAVITY);
 
        double nYRad = Math.toRadians(this.getYRot());
        double nSin = Math.sin(nYRad), nCos = Math.cos(nYRad);
        this.setDeltaMovement(
            localFwd*(-nSin) + localLat*nCos,
            localY,
            localFwd*nCos    + localLat*nSin
        );
 
        wheelSpin = (wheelSpin + (float)(speed * 180.0)) % 360f;
        this.entityData.set(DATA_WHEEL_SPIN, wheelSpin);
        this.entityData.set(DATA_FORWARD_SPEED, (float)localFwd);
 
        // ── Physics-based RPM ──────────────────────────────────
        //
        //  RPM is computed from speed + gear position.
        //  The drop at each gear boundary is automatic:
        //    gear N end   (progress=1.0) → REDLINE_NORM (1.0)
        //    gear N+1 start (progress=0.0) → GEAR_START_NORM (0.25)
        //  That instant fall IS the gear shift sound effect.
        float fSpeed = (float)speed;
        float rpm;
        if (fSpeed < GEAR_THRESHOLDS[0]) {
            rpm = IDLE_RPM_NORM;
        } else {
            int gear = getGear(fSpeed);
            float progress = getProgressWithinGear(fSpeed, gear);
            rpm = GEAR_START_NORM + progress * (REDLINE_NORM - GEAR_START_NORM);
        }
        this.entityData.set(DATA_RPM, rpm);
    }
 
    private float computeSteerAngle(double speed, boolean drifting) {
        float steer = speed <= PEAK_STEER_SPEED
            ? MAX_STEER_DEG * (float)(speed / PEAK_STEER_SPEED)
            : MAX_STEER_DEG * (float)(1.0 - Mth.clamp(
                (speed - PEAK_STEER_SPEED)/(MAX_SPEED - PEAK_STEER_SPEED), 0.0, 1.0)
                *(1.0 - HIGH_SPEED_STEER_FRACTION));
        return drifting ? steer * DRIFT_STEER_BOOST : steer;
    }
 
    private int getGear(float speed) {
        for (int i = 0; i < GEAR_THRESHOLDS.length; i++)
            if (speed < GEAR_THRESHOLDS[i]) return i;
        return GEAR_THRESHOLDS.length;
    }
 
    private float getProgressWithinGear(float speed, int gear) {
        float low  = gear > 0 ? GEAR_THRESHOLDS[gear-1] : 0f;
        float high = gear < GEAR_THRESHOLDS.length ? GEAR_THRESHOLDS[gear] : 1.0f;
        if (high <= low) return 1f;
        return Mth.clamp((speed - low) / (high - low), 0f, 1f);
    }
 
    // ── Passenger positioning ──────────────────────────────────
 
    @Override
    protected Vec3 getPassengerAttachmentPoint(Entity p, EntityDimensions d, float t) {
        int i = this.getPassengers().indexOf(p);
        double lx = (i == 0) ? SEAT_SIDE : -SEAT_SIDE;
        double yRad = Math.toRadians(this.getYRot());
        double sin = Math.sin(yRad), cos = Math.cos(yRad);
        return new Vec3(lx*cos + SEAT_FORWARD*(-sin), SEAT_HEIGHT, lx*sin + SEAT_FORWARD*cos);
    }
 
    @Override protected boolean canAddPassenger(Entity p) { return this.getPassengers().size() < 2; }
    @Override @Nullable
    public LivingEntity getControllingPassenger() {
        return this.getFirstPassenger() instanceof LivingEntity le ? le : null;
    }
 
    // ── Interaction ────────────────────────────────────────────
 
    @Override
    public InteractionResult interact(Player player, InteractionHand hand) {
        if (player.isSecondaryUseActive()) return InteractionResult.PASS;
        if (!this.level().isClientSide()) player.startRiding(this);
        return InteractionResult.SUCCESS;
    }
 
    @Override public boolean isPickable() { return !this.isRemoved(); }
    @Override public boolean isPushable()  { return false; }
 
    // ── Data accessors ─────────────────────────────────────────
 
    public boolean isDrifting()     { return this.entityData.get(DATA_DRIFTING); }
    public float   getWheelSpin()   { return this.entityData.get(DATA_WHEEL_SPIN); }
    public float   getSteerInput()  { return this.entityData.get(DATA_STEER_INPUT); }
    public float   getForwardSpeed(){ return this.entityData.get(DATA_FORWARD_SPEED); }
 
    /**
     * Physics-based RPM, normalised 0–1.
     * Multiply by 8000 to get display RPM.
     * Sound instances use this directly for pitch.
     */
    public float getRPM() { return this.entityData.get(DATA_RPM); }
 
    /** True while player holds W and is accelerating forward. */
    public boolean isThrottleOn()  { return this.entityData.get(DATA_THROTTLE); }
 
    /** True while player is actively braking. */
    public boolean isBraking()     { return this.entityData.get(DATA_BRAKING); }
 
    // ── HUD display ────────────────────────────────────────────
 
    private void displaySpeed(Player player) {
        float kmh        = Math.abs(this.getForwardSpeed()) * 72f;
        int   displayRPM = (int)(this.getRPM() * 8000f);
        player.displayClientMessage(
            net.minecraft.network.chat.Component.literal(String.format(
                "§aSpeed: §f%.0f km/h §7| §cRPM: §f%d", kmh, displayRPM)),
            true
        );
    }

    private void dataCollection() {
        float kmh        = Math.abs(this.getForwardSpeed()) * 72f;
        int   displayRPM = (int)(this.getRPM() * 8000f);

        System.out.println("Speed: " + kmh + "km/h | RPM: " + displayRPM);

    }
 
    // ── NBT ────────────────────────────────────────────────────
 
    @Override protected void readAdditionalSaveData(ValueInput i) {}
    @Override protected void addAdditionalSaveData(ValueOutput o) {}
}
