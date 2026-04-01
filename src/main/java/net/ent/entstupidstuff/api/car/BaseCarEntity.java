package net.ent.entstupidstuff.api.car;

import org.jetbrains.annotations.Nullable;

import net.ent.entstupidstuff.particle.ParticleTypesFactory;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.tags.BlockTags;
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
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.Vec3;

/**
 * BaseCarEntity — generic physics engine for all driveable cars.
 *
 * Every car-specific value is supplied via abstract methods.
 * To create a new car:
 *   1. Extend BaseCarEntity
 *   2. Implement all abstract spec methods
 *   3. Register the entity type with your car model/sounds
 */
public abstract class BaseCarEntity extends VehicleEntity {

    // ═══════════════════════════════════════════════════════════
    //  SEAT  (override in subclass if model differs)
    // ═══════════════════════════════════════════════════════════

    protected double seatHeight()  { return 0.4; }
    protected double seatSide()    { return 0.5; }
    protected double seatForward() { return -0.5; }

    // ═══════════════════════════════════════════════════════════
    //  ABSTRACT SPEC
    // ═══════════════════════════════════════════════════════════

    protected abstract float   idleRpm();
    protected abstract float   redlineRpm();
    protected abstract float   maxReverseRpm();
    protected abstract float   maxReverseSpeed();
    protected abstract float   downshiftRpm();
    protected abstract int     clutchTicks();
    protected abstract int     maxGear();
    protected abstract float[] torqueRpmPoints();
    protected abstract float[] torqueCurve();
    protected abstract float[] gearRatios();
    protected abstract float   finalDrive();
    protected abstract float   tyreCirc();
    protected abstract float   peakDriveForce();
    protected abstract float   frontBias();
    protected abstract float   rearBias();
    protected abstract float   frontDist();
    protected abstract float   rearDist();
    protected abstract double  trackHalf();
    protected abstract float   frontGripMax();
    protected abstract float   rearGripMax();
    protected abstract float   gripStiffness();
    protected abstract float   slipThreshold();
    protected abstract float   slipFalloff();
    protected abstract float   latDecay();
    protected abstract float   longTransfer();
    protected abstract float   latTransfer();
    protected abstract float   maxSteerDeg();
    protected abstract double  peakSteerSpeed();
    protected abstract double  highSteerFraction();
    protected abstract float   driftSteerBoost();
    protected abstract float   handbrakeRearGrip();
    protected abstract float   yawMomentScale();
    protected abstract float   yawDamping();
    protected abstract float   yawMax();
    protected abstract float   driftThreshold();
    protected abstract float   throttleRampOn();
    protected abstract float   throttleRampOff();
    protected abstract double  rollingDrag();
    protected abstract double  aeroDragStart();
    protected abstract double  aeroDragK();
    protected abstract boolean defaultIsRWD();

    // ═══════════════════════════════════════════════════════════
    //  FIXED PHYSICS CONSTANTS
    // ═══════════════════════════════════════════════════════════

    private static final int    TUNNEL_SCAN_HEIGHT = 8;
    private static final double GRAVITY            = 0.08;
    private static final double MAX_FALL_SPEED     = 3.92;
    private static final double GROUND_STICK       = -0.08;

    // ═══════════════════════════════════════════════════════════
    //  SYNCED DATA
    // ═══════════════════════════════════════════════════════════

    private static final EntityDataAccessor<Boolean> DATA_DRIFTING =
        SynchedEntityData.defineId(BaseCarEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Float>   DATA_WHEEL_SPIN =
        SynchedEntityData.defineId(BaseCarEntity.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Float>   DATA_REAR_WHEEL_SPIN =
        SynchedEntityData.defineId(BaseCarEntity.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Float>   DATA_STEER_INPUT =
        SynchedEntityData.defineId(BaseCarEntity.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Float>   DATA_FORWARD_SPEED =
        SynchedEntityData.defineId(BaseCarEntity.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Float>   DATA_ENGINE_RPM =
        SynchedEntityData.defineId(BaseCarEntity.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Integer> DATA_GEAR =
        SynchedEntityData.defineId(BaseCarEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> DATA_THROTTLE =
        SynchedEntityData.defineId(BaseCarEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> DATA_BRAKING =
        SynchedEntityData.defineId(BaseCarEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> DATA_BURNOUT =
        SynchedEntityData.defineId(BaseCarEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> DATA_TUNNELED =
        SynchedEntityData.defineId(BaseCarEntity.class, EntityDataSerializers.BOOLEAN);

    // ═══════════════════════════════════════════════════════════
    //  SERVER-SIDE PHYSICS STATE
    // ═══════════════════════════════════════════════════════════

    private float   engineRPM      = 0f;
    private int     currentGear    = 1;
    private int     clutchTimer    = 0;
    private float   throttleSmooth = 0f;
    private float   frontLat       = 0f;
    private float   rearLat        = 0f;
    private float   overYawRate    = 0f;
    private float   wheelSpin      = 0f;
    private float   rearWheelSpin  = 0f;
    private float   burnoutRPM     = 0f;
    private boolean wasBurningOut    = false;


    // ── Debug snapshot — written each tick so displaySpeed can read them ──
    // These are the last computed values from tickPhysics.
    private float dbgFrontLat       = 0f;
    private float dbgRearLat        = 0f;
    private float dbgOverYawRate    = 0f;
    private float dbgThrottleSmooth = 0f;
    private float dbgDriveForce     = 0f;
    private float dbgRearGripTotal  = 0f;
    private float dbgFrontGripTotal = 0f;
    private float dbgRearGripLat    = 0f;
    private float dbgSurfaceFriction= 1f;
    private float dbgCoastDecay     = 0f;
    private boolean dbgYawAllowed   = false;
    private boolean dbgDrifting      = false;


    // ═══════════════════════════════════════════════════════════
    //  TOGGLES
    // ═══════════════════════════════════════════════════════════

    /**
     * Normal HUD: speed / RPM / gear.
     * debugMode: full physics readout across 4 chat lines.
     * advancedDebug: extended per-tick snapshot across 6 chat lines.
     * scenarioTest: single action-bar line with state label.
     */
    public boolean debugMode      = true;
    public boolean advancedDebug  = false;
    public boolean scenarioTest   = false;

    /** When false, surface/rain grip multipliers are bypassed. */
    public boolean surfaceFrictionEnabled = true;

    /**
     * true  = RWD — drive torque loads rear traction circle → oversteer.
     * false = FWD — drive torque loads front traction circle → understeer.
     */
    public boolean isRWD;

    // ═══════════════════════════════════════════════════════════
    //  CONSTRUCTOR
    // ═══════════════════════════════════════════════════════════

    public BaseCarEntity(EntityType<?> type, Level level) {
        super(type, level);
        this.isRWD = defaultIsRWD();
    }

    @Override public float maxUpStep() { return 1.0f; }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(DATA_DRIFTING,        false);
        builder.define(DATA_WHEEL_SPIN,      0f);
        builder.define(DATA_REAR_WHEEL_SPIN, 0f);
        builder.define(DATA_STEER_INPUT,     0f);
        builder.define(DATA_FORWARD_SPEED,   0f);
        builder.define(DATA_ENGINE_RPM,      0f);
        builder.define(DATA_GEAR,            1);
        builder.define(DATA_THROTTLE,        false);
        builder.define(DATA_BRAKING,         false);
        builder.define(DATA_BURNOUT,         false);
        builder.define(DATA_TUNNELED,        false);
        this.engineRPM  = idleRpm();
        this.burnoutRPM = idleRpm();
    }

    @Override protected Item getDropItem() { return null; }

    // ═══════════════════════════════════════════════════════════
    //  TICK
    // ═══════════════════════════════════════════════════════════

    @Override
    public void tick() {
        super.tick();

        if (!this.isLocalInstanceAuthoritative()) {
            this.setDeltaMovement(Vec3.ZERO);
            return;
        }

        boolean forward = false, backward = false,
                left    = false, right    = false, handbrake = false;

        if (this.getFirstPassenger() instanceof Player player) {
            forward   = player.zza  >  0f;
            backward  = player.zza  <  0f;
            left      = player.xxa  >  0f;
            right     = player.xxa  <  0f;
            handbrake = player.isJumping();
            if (this.tickCount % 2 == 0) displaySpeed(player);
        } else {
            frontLat      *= 0.70f;
            rearLat       *= 0.70f;
            overYawRate   *= 0.70f;
            throttleSmooth = 0f;
            burnoutRPM     = idleRpm();
        }

        tickPhysics(forward, backward, left, right, handbrake);

        Vec3 preMove = this.getDeltaMovement();
        this.move(MoverType.SELF, preMove);

        Vec3 postMove = this.getDeltaMovement();
        boolean steppingUp = postMove.y > 0.01;
        if (!steppingUp && this.onGround()) {
            double bounceX = Math.abs(preMove.x) > 0.01 && Math.abs(postMove.x) < Math.abs(preMove.x) * 0.5
                ? -preMove.x * 0.35 : postMove.x;
            double bounceZ = Math.abs(preMove.z) > 0.01 && Math.abs(postMove.z) < Math.abs(preMove.z) * 0.5
                ? -preMove.z * 0.35 : postMove.z;
            if (bounceX != postMove.x || bounceZ != postMove.z)
                this.setDeltaMovement(bounceX, postMove.y, bounceZ);
        }

        if (this.onGround() && this.getDeltaMovement().y < 0)
            this.setDeltaMovement(this.getDeltaMovement().x, 0.0, this.getDeltaMovement().z);

        this.applyEffectsFromBlocks();

        if (!this.level().isClientSide() && this.tickCount % 5 == 0)
            this.entityData.set(DATA_TUNNELED, detectTunnel());

        if (this.level().isClientSide()) {
            spawnWheelParticles();
            spawnExhaust();
        }
    }

    // ═══════════════════════════════════════════════════════════
    //  PHYSICS ENGINE
    // ═══════════════════════════════════════════════════════════

    private void tickPhysics(boolean forward, boolean backward,
                              boolean left,    boolean right, boolean handbrake) {

        // ── 1. Local velocity ─────────────────────────────────────────────
        double yRad  = Math.toRadians(this.getYRot());
        double sinY  = Math.sin(yRad), cosY = Math.cos(yRad);
        Vec3   vel   = this.getDeltaMovement();

        double localFwd  = -vel.x * sinY + vel.z * cosY;
        double localY    =  vel.y;
        float  speed     = (float) Math.abs(localFwd);
        boolean goingFwd = localFwd >  0.01;
        boolean goingRev = localFwd < -0.01;

        // ── 2. Input flags ────────────────────────────────────────────────
        boolean throttleActive = (forward  && !goingRev) || (backward && !goingFwd);
        boolean brakingActive  = (backward && goingFwd)  || (forward  && goingRev);
        boolean burnout        = forward && (backward || handbrake) && speed < 0.30f;
        float   steerInput     = left ? -1f : right ? 1f : 0f;

        this.entityData.set(DATA_THROTTLE,    throttleActive || burnout);
        this.entityData.set(DATA_BRAKING,     brakingActive);
        this.entityData.set(DATA_BURNOUT,     burnout);
        this.entityData.set(DATA_STEER_INPUT, steerInput);

        // ── 3. Smooth throttle ────────────────────────────────────────────
        float throttleTarget = throttleActive ? 1.0f : 0.0f;
        float ramp = throttleTarget > throttleSmooth ? throttleRampOn() : throttleRampOff();
        throttleSmooth = Mth.clamp(
            throttleSmooth + (throttleTarget - throttleSmooth) * ramp, 0f, 1f);

        // ── 4. Drivetrain chain ───────────────────────────────────────────
        final float[] GR = gearRatios();

        if (burnout) {
            final float BURN_RPM_RISE = 140f;
            if (clutchTimer > 0) {
                clutchTimer--;
                engineRPM = burnoutRPM;
            } else {
                burnoutRPM = Math.min(redlineRpm(), burnoutRPM + BURN_RPM_RISE);
                if (burnoutRPM >= redlineRpm() && currentGear < maxGear()) {
                    currentGear++;
                    clutchTimer = clutchTicks();
                    burnoutRPM *= (GR[currentGear] / GR[currentGear - 1]);
                } else if (burnoutRPM <= downshiftRpm() && currentGear > 1) {
                    currentGear--;
                    clutchTimer = clutchTicks();
                }
                engineRPM = burnoutRPM;
            }
            engineRPM  = Mth.clamp(engineRPM, idleRpm(), redlineRpm());
            burnoutRPM = engineRPM;
            wasBurningOut = true;

        } else if (speed < 0.005f) {
            if (!wasBurningOut) currentGear = 1;
            clutchTimer = 0;
            engineRPM   = idleRpm() + throttleSmooth * 600f;
        } else {
            wasBurningOut = false;
            float wheelRPS = speed / tyreCirc() * 20f;
            float rawRPM   = wheelRPS * GR[currentGear] * finalDrive() * 60f;

            if (clutchTimer > 0) {
                clutchTimer--;
                float blend = clutchTimer / (float) clutchTicks();
                engineRPM = Mth.lerp(blend, rawRPM, engineRPM * 0.72f);
            } else if (goingFwd) {
                if (rawRPM >= redlineRpm() && currentGear < maxGear()) {
                    currentGear++;
                    clutchTimer = clutchTicks();
                    rawRPM = wheelRPS * GR[currentGear] * finalDrive() * 60f;
                } else if (rawRPM <= downshiftRpm() && currentGear > 1) {
                    currentGear--;
                    clutchTimer = clutchTicks();
                    rawRPM = wheelRPS * GR[currentGear] * finalDrive() * 60f;
                }
                engineRPM = rawRPM;
            } else if (goingRev) {
                float revFraction = Math.min(1f, speed / maxReverseSpeed());
                engineRPM = idleRpm() + revFraction * (maxReverseRpm() - idleRpm());
            }
            engineRPM = Math.max(engineRPM, idleRpm() + throttleSmooth * 500f);
            engineRPM = Mth.clamp(engineRPM, idleRpm(), redlineRpm());
        }

        this.entityData.set(DATA_ENGINE_RPM, engineRPM);
        this.entityData.set(DATA_GEAR,       currentGear);

        // ── 5. Drive force ────────────────────────────────────────────────
        float torqueNorm = lookupTorque(engineRPM);
        float gearMult   = GR[currentGear] / GR[1];
        float driveForce = torqueNorm * throttleSmooth * peakDriveForce() * gearMult;
        if (clutchTimer > 0) driveForce = 0f;

        // ── 5b. Surface friction ──────────────────────────────────────────
        float surfaceFriction      = surfaceFrictionEnabled ? computeSurfaceFriction() : 1.0f;
        float adjustedRearGripMax  = rearGripMax()  * surfaceFriction;
        float adjustedFrontGripMax = frontGripMax() * surfaceFriction;
        double surfaceRollingDrag  = rollingDrag() - (1.0 - surfaceFriction) * 0.04;

        // ── 6. Weight transfer ────────────────────────────────────────────
        float wt          = driveForce - (brakingActive ? 0.04f : 0f);
        float rearWeight  = Mth.clamp(rearBias()  + wt * longTransfer(), 0.30f, 0.70f);
        float frontWeight = Mth.clamp(frontBias() - wt * longTransfer(), 0.30f, 0.70f);

        // ── 7. Traction circle ────────────────────────────────────────────
        float rearGripTotal  = rearWeight  * adjustedRearGripMax;
        float frontGripTotal = frontWeight * adjustedFrontGripMax;

        float driveUsedRear  = isRWD  ? Math.min(Math.abs(driveForce), rearGripTotal)  : 0f;
        float driveUsedFront = !isRWD ? Math.min(Math.abs(driveForce), frontGripTotal) : 0f;

        float rearGripLat;
        if (handbrake) {
            rearGripLat = handbrakeRearGrip() * adjustedRearGripMax;
        } else if (isRWD) {
            rearGripLat = (float) Math.sqrt(Math.max(0.0,
                (double) rearGripTotal * rearGripTotal - (double) driveUsedRear * driveUsedRear));
            rearGripLat = Math.max(rearGripLat, rearGripTotal * 0.40f);
        } else {
            rearGripLat = rearGripTotal;
        }

        float frontGripLat;
        if (!isRWD) {
            frontGripLat = (float) Math.sqrt(Math.max(0.0,
                (double) frontGripTotal * frontGripTotal - (double) driveUsedFront * driveUsedFront));
            frontGripLat = Math.max(frontGripLat, frontGripTotal * 0.40f);
        } else {
            frontGripLat = frontGripTotal;
        }

        float latAbs       = Math.max(Math.abs(frontLat), Math.abs(rearLat));
        float latReduction = Mth.clamp(latAbs * latTransfer(), 0f, 0.18f);
        frontGripLat *= (1f - latReduction);
        rearGripLat  *= (1f - latReduction);

        // ── 8. Longitudinal motion ────────────────────────────────────────
        if (burnout) {
            localFwd *= 0.60;
            if (Math.abs(localFwd) < 0.005) localFwd = 0.0;
        } else if (throttleActive) {
            if (forward) {
                double cap = goingRev ? 0.10 : (isRWD ? rearGripTotal : frontGripTotal);
                localFwd = Math.min(localFwd + Math.min(driveForce, cap), 75.0);
            } else {
                localFwd = Math.max(localFwd - 0.028, -0.35);
            }
        }

        if (!burnout && brakingActive) {
            if (goingFwd)      localFwd = Math.max(0.0, localFwd - 0.035);
            else if (goingRev) localFwd = Math.min(0.0, localFwd + 0.035);
        }

        if (!forward && !backward && !handbrake) {
            double eb = 0.004 * (GR[currentGear] / GR[maxGear()]);
            if (goingFwd)      localFwd -= eb;
            else if (goingRev) localFwd += eb;
        }

        if (handbrake) {
            if (goingFwd)      localFwd = Math.max(0.0, localFwd - 0.06);
            else if (goingRev) localFwd = Math.min(0.0, localFwd + 0.06);
        }

        localFwd *= surfaceRollingDrag;
        if (Math.abs(localFwd) > aeroDragStart()) {
            double excess = Math.abs(localFwd) - aeroDragStart();
            localFwd -= Math.signum(localFwd) * excess * aeroDragK() * 20;
        }
        if (Math.abs(localFwd) < 0.001) localFwd = 0.0;

        // ── 9. Steering ───────────────────────────────────────────────────
        boolean drifting = Math.abs(rearLat) > driftThreshold();
        if (speed > 0.01f && steerInput != 0f) {
            float steerDeg = computeSteerAngle(speed, drifting);
            if (!isRWD && frontGripTotal > 0f) {
                float saturation = Mth.clamp(driveUsedFront / frontGripTotal, 0f, 1f);
                steerDeg *= (1f - saturation * 0.20f);
            }
            float revSign = goingRev ? -1f : 1f;
            this.setYRot(this.getYRot() + steerInput * steerDeg * revSign);
        }

        // ── 10. Centripetal lateral + persistent axle model ───────────────
        if (goingFwd && steerInput != 0f) {
            float steerRad       = (float) Math.toRadians(computeSteerAngle(speed, drifting));
            float centripetalLat = (float)(Math.sin(steerRad) * Math.abs(localFwd)) * steerInput;
            frontLat += centripetalLat * (rearDist()  / (frontDist() + rearDist()));
            rearLat  += centripetalLat * (frontDist() / (frontDist() + rearDist()));
        }

        float frontCorrect = computeGripForce(frontLat, frontGripLat);
        frontLat -= frontCorrect;

        float rearCorrect;
        if (handbrake) {
            rearCorrect = rearLat * handbrakeRearGrip() * gripStiffness();
        } else {
            rearCorrect = computeGripForce(rearLat, rearGripLat);
        }
        rearLat -= rearCorrect;

        // Coast decay: prevents passive spinning from centripetal injection alone.
        // Verified mathematically: all cars stay below driftThreshold when coasting.
        float coastDecay = (!handbrake && throttleSmooth < 0.15f)
            ? 0.50f       // coasting: bleed off fast
            : latDecay(); // under power: normal
        frontLat *= coastDecay;
        rearLat  *= coastDecay;
        if (Math.abs(frontLat) < 0.0005f) frontLat = 0f;
        if (Math.abs(rearLat)  < 0.0005f) rearLat  = 0f;

        float overallLat = frontLat * frontBias() + rearLat * rearBias();

        // ── 11. Yaw moment ────────────────────────────────────────────────
        // RWD: fires under throttle or handbrake.
        // FWD: handbrake only — no power oversteer.
        drifting = Math.abs(rearLat) > driftThreshold();
        boolean yawAllowed = handbrake
                          || (isRWD && (throttleSmooth > 0.15f || burnout));
        if (drifting && yawAllowed) {
            float excessDiff = rearLat - frontLat;
            overYawRate += excessDiff * yawMomentScale();
        } else {
            overYawRate *= 0.55f;
        }
        overYawRate *= yawDamping();
        overYawRate  = Mth.clamp(overYawRate, -yawMax(), yawMax());
        this.setYRot(this.getYRot() + overYawRate);

        this.entityData.set(DATA_DRIFTING, drifting || burnout || Math.abs(overYawRate) > 1.2f);

        // ── 12. Gravity ───────────────────────────────────────────────────
        localY = this.onGround() ? GROUND_STICK : Math.max(-MAX_FALL_SPEED, localY - GRAVITY);

        // ── 13. Reproject to world ────────────────────────────────────────
        double nYRad = Math.toRadians(this.getYRot());
        double nSin  = Math.sin(nYRad), nCos = Math.cos(nYRad);
        this.setDeltaMovement(
            localFwd * (-nSin) + overallLat * nCos,
            localY,
            localFwd *   nCos  + overallLat * nSin);

        // ── 14. Wheel spin cosmetics ──────────────────────────────────────
        // RWD burnout: rear wheels spin fast, front wheels idle (speed-based).
        // FWD burnout: front wheels spin fast, rear wheels idle (speed-based).
        float burnoutDeg = burnout ? (engineRPM / redlineRpm()) * 90f : 0f;

        if (burnout && !isRWD) {
            // FWD burnout — front axle gets RPM spin
            wheelSpin += burnoutDeg;
        } else {
            wheelSpin += speed * 180f;
        }
        if (wheelSpin > 360000f) wheelSpin -= 360000f;
        this.entityData.set(DATA_WHEEL_SPIN, wheelSpin);

        if (burnout && isRWD) {
            // RWD burnout — rear axle gets RPM spin
            rearWheelSpin += burnoutDeg;
        } else if (speed > 0.005f) {
            rearWheelSpin += speed * 180f;
        }
        if (rearWheelSpin > 360000f) rearWheelSpin -= 360000f;
        this.entityData.set(DATA_REAR_WHEEL_SPIN, rearWheelSpin);
        this.entityData.set(DATA_FORWARD_SPEED, (float) localFwd);

        // ── Debug snapshot ────────────────────────────────────────────────
        dbgFrontLat        = frontLat;
        dbgRearLat         = rearLat;
        dbgOverYawRate     = overYawRate;
        dbgThrottleSmooth  = throttleSmooth;
        dbgDriveForce      = driveForce;
        dbgRearGripTotal   = rearGripTotal;
        dbgFrontGripTotal  = frontGripTotal;
        dbgRearGripLat     = rearGripLat;
        dbgSurfaceFriction = surfaceFriction;
        dbgCoastDecay      = coastDecay;
        dbgYawAllowed      = yawAllowed;
        dbgDrifting        = drifting;

    }

    // ═══════════════════════════════════════════════════════════
    //  HELPERS
    // ═══════════════════════════════════════════════════════════

    private boolean detectTunnel() {
        if (!this.onGround()) return false;
        BlockPos base = BlockPos.containing(this.getX(), this.getY() + 0.5, this.getZ());
        for (int i = 1; i <= TUNNEL_SCAN_HEIGHT; i++) {
            if (this.level().getBlockState(base.above(i)).isSolid()) return true;
        }
        return false;
    }

    public float getTunnelStrength() {
        if (!this.isTunneled()) return 0f;
        BlockPos base = BlockPos.containing(this.getX(), this.getY() + 0.5, this.getZ());
        for (int i = 1; i <= TUNNEL_SCAN_HEIGHT; i++) {
            if (this.level().getBlockState(base.above(i)).isSolid())
                return 1.0f - (i - 1f) / TUNNEL_SCAN_HEIGHT;
        }
        return 0f;
    }

    private float computeSurfaceFriction() {
        BlockPos underPos = BlockPos.containing(this.getX(), this.getY() - 0.2, this.getZ());
        BlockState under  = this.level().getBlockState(underPos);
        net.minecraft.world.level.block.Block block = under.getBlock();

        float friction;
        if      (block == Blocks.BLUE_ICE)                                           friction = 0.10f;
        else if (block == Blocks.ICE || block == Blocks.PACKED_ICE
              || block == Blocks.FROSTED_ICE)                                        friction = 0.25f;
        else if (block == Blocks.SOUL_SAND || block == Blocks.SOUL_SOIL)             friction = 0.45f;
        else if (block == Blocks.SAND || block == Blocks.RED_SAND
              || block == Blocks.SUSPICIOUS_SAND)                                    friction = 0.55f;
        else if (block == Blocks.GRAVEL || block == Blocks.COARSE_DIRT
              || block == Blocks.DIRT_PATH)                                          friction = 0.72f;
        else if (under.is(BlockTags.DIRT)
              || block == Blocks.GRASS_BLOCK || block == Blocks.PODZOL
              || block == Blocks.FARMLAND   || block == Blocks.MYCELIUM
              || block == Blocks.MUD        || block == Blocks.MUDDY_MANGROVE_ROOTS) friction = 0.65f;
        else                                                                          friction = 1.00f;

        if (this.level().isRaining() && this.level().canSeeSky(underPos.above()))
            friction *= 0.80f;
        return friction;
    }

    private float computeGripForce(float slip, float gripMax) {
        float raw = Mth.clamp(slip, -gripMax, gripMax);
        if (Math.abs(slip) > slipThreshold()) {
            float falloff = slipFalloff() + (1f - slipFalloff()) * (slipThreshold() / Math.abs(slip));
            raw *= falloff;
        }
        return raw * gripStiffness();
    }

    private float lookupTorque(float rpm) {
        float[] rpmPts = torqueRpmPoints();
        float[] trqPts = torqueCurve();
        if (rpm <= rpmPts[0]) return trqPts[0];
        for (int i = 1; i < rpmPts.length; i++) {
            if (rpm <= rpmPts[i]) {
                float t = (rpm - rpmPts[i-1]) / (rpmPts[i] - rpmPts[i-1]);
                return trqPts[i-1] + t * (trqPts[i] - trqPts[i-1]);
            }
        }
        return trqPts[trqPts.length - 1];
    }

    private float computeSteerAngle(float speed, boolean drifting) {
        float steer;
        if (speed <= peakSteerSpeed()) {
            steer = maxSteerDeg() * (float)(speed / peakSteerSpeed());
        } else {
            double t = Mth.clamp(
                (speed - peakSteerSpeed()) / (1.0 - peakSteerSpeed()), 0.0, 1.0);
            steer = maxSteerDeg() * (float)(1.0 - t * (1.0 - highSteerFraction()));
        }
        return drifting ? steer * driftSteerBoost() : steer;
    }

    // ═══════════════════════════════════════════════════════════
    //  PARTICLES
    // ═══════════════════════════════════════════════════════════

    private void spawnWheelParticles() {
        if (!this.onGround()) return;
        boolean burnoutActive = this.isBurningOut();
        double speed = Math.abs(this.getForwardSpeed());
        if (speed < 0.05 && !burnoutActive) return;
        boolean drifting = this.isDrifting();

        double yRad = Math.toRadians(this.getYRot());
        double sinY = Math.sin(yRad), cosY = Math.cos(yRad);
        double fwdX = -sinY, fwdZ = cosY;
        double rgtX =  cosY, rgtZ = sinY;

        // Rearward unit vector — smoke streams away from spinning tires during burnout.
        // Opposite of forward: (+sinY, 0, -cosY).
        double rearX = sinY, rearZ = -cosY;

        double fax = fwdX * frontDist(), faz = fwdZ * frontDist();
        double rax = -fwdX * rearDist(), raz = -fwdZ * rearDist();
        double lox = rgtX * trackHalf(), loz = rgtZ * trackHalf();

        if (burnoutActive) {
            // RWD: rear wheels spin → smoke rear axle.
            // FWD: front wheels spin → smoke front axle.
            if (isRWD) {
                spawnWheelAt(rax + lox, 0.05, raz + loz, true,  rearX, rearZ);
                spawnWheelAt(rax - lox, 0.05, raz - loz, true,  rearX, rearZ);
            } else {
                spawnWheelAt(fax + lox, 0.05, faz + loz, true,  rearX, rearZ);
                spawnWheelAt(fax - lox, 0.05, faz - loz, true,  rearX, rearZ);
            }
        } else {
            spawnWheelAt(fax + lox, 0.05, faz + loz, drifting, rearX, rearZ);
            spawnWheelAt(fax - lox, 0.05, faz - loz, drifting, rearX, rearZ);
            spawnWheelAt(rax + lox, 0.05, raz + loz, drifting, rearX, rearZ);
            spawnWheelAt(rax - lox, 0.05, raz - loz, drifting, rearX, rearZ);
        }

        // Debug flame particles at physics wheel positions — visible when debugMode is on.
        // Remove once wheels are correctly aligned in Blockbench.
        if (debugMode && this.tickCount % 3 == 0) {
            spawnDebugFlame(fax + lox, 0.05, faz + loz);
            spawnDebugFlame(fax - lox, 0.05, faz - loz);
            spawnDebugFlame(rax + lox, 0.05, raz + loz);
            spawnDebugFlame(rax - lox, 0.05, raz - loz);
        }
    }

    private void spawnDebugFlame(double ox, double oy, double oz) {
        Vec3 pos = this.position();
        this.level().addParticle(ParticleTypes.FLAME,
            pos.x + ox, pos.y + oy + 0.3, pos.z + oz, 0, 0.05, 0);
    }

    private void spawnWheelAt(double ox, double oy, double oz,
                               boolean smoking, double rearX, double rearZ) {
        Vec3 pos = this.position();
        double px = pos.x + ox, py = pos.y + oy, pz = pos.z + oz;

        // Block dust — always spawns while moving
        BlockState bs = this.level().getBlockState(BlockPos.containing(px, py - 0.2, pz));
        Vec3 vel = this.getDeltaMovement();
        double dustVx = -vel.x * 0.15 + (random.nextDouble() - 0.5) * 0.04;
        double dustVz = -vel.z * 0.15 + (random.nextDouble() - 0.5) * 0.04;
        this.level().addParticle(new BlockParticleOption(ParticleTypes.BLOCK, bs),
            px, py, pz, dustVx, 0.01, dustVz);

        if (!smoking) return;

        boolean burnout = this.isBurningOut();
        for (int i = 0; i < 4; i++) {
            double spreadX = (random.nextDouble() - 0.5) * 0.25;
            double spreadZ = (random.nextDouble() - 0.5) * 0.25;
            double spreadY = random.nextDouble() * 0.10;

            double smokeVx, smokeVy, smokeVz;
            if (burnout) {
                // Stream rearward from spinning tire — speed scales with RPM fraction.
                double stream = 0.04 + this.getRPM() * 0.06;
                smokeVx = rearX * stream + (random.nextDouble() - 0.5) * 0.02;
                smokeVy = 0.01 + random.nextDouble() * 0.02;
                smokeVz = rearZ * stream + (random.nextDouble() - 0.5) * 0.02;
            } else {
                // Drift smoke: stationary billowing cloud
                smokeVx = (random.nextDouble() - 0.5) * 0.002;
                smokeVy = 0.0;
                smokeVz = (random.nextDouble() - 0.5) * 0.002;
            }

            this.level().addParticle(ParticleTypesFactory.TYRE_SMOKE,
                px + spreadX, py + spreadY, pz + spreadZ,
                smokeVx, smokeVy, smokeVz);
        }
    }

    /** Override in subclass to add exhaust particles. */
    protected void spawnExhaust() {}

    // ═══════════════════════════════════════════════════════════
    //  PASSENGERS
    // ═══════════════════════════════════════════════════════════

    @Override
    public Vec3 getDismountLocationForPassenger(LivingEntity p) {
        return this.position().add(1.5 * (p == this.getFirstPassenger() ? 1 : -1), 0, 0);
    }

    @Override
    protected Vec3 getPassengerAttachmentPoint(Entity p, EntityDimensions dims, float scale) {
        int i = this.getPassengers().indexOf(p);
        double lx = (i == 0) ? seatSide() : -seatSide();
        double yRad = Math.toRadians(this.getYRot());
        double sin = Math.sin(yRad), cos = Math.cos(yRad);
        return new Vec3(lx*cos + seatForward()*(-sin), seatHeight(),
                        lx*sin + seatForward()*   cos);
    }

    @Override protected boolean canAddPassenger(Entity p) { return this.getPassengers().size() < 2; }

    @Override @Nullable
    public LivingEntity getControllingPassenger() {
        return this.getFirstPassenger() instanceof LivingEntity le ? le : null;
    }

    // ═══════════════════════════════════════════════════════════
    //  INTERACTION
    // ═══════════════════════════════════════════════════════════

    @Override
    public InteractionResult interact(Player player, InteractionHand hand) {
        if (player.isSecondaryUseActive()) return InteractionResult.PASS;
        if (!this.level().isClientSide()) player.startRiding(this);
        return InteractionResult.SUCCESS;
    }

    @Override public boolean isPickable() { return !this.isRemoved(); }
    @Override public boolean isPushable()  { return false; }

    // ═══════════════════════════════════════════════════════════
    //  DATA ACCESSORS
    // ═══════════════════════════════════════════════════════════

    public boolean isDrifting()       { return this.entityData.get(DATA_DRIFTING); }
    public float   getWheelSpin()     { return this.entityData.get(DATA_WHEEL_SPIN); }
    public float   getRearWheelSpin() { return this.entityData.get(DATA_REAR_WHEEL_SPIN); }
    public float   getSteerInput()    { return this.entityData.get(DATA_STEER_INPUT); }
    public float   getForwardSpeed()  { return this.entityData.get(DATA_FORWARD_SPEED); }
    public int     getCurrentGear()   { return this.entityData.get(DATA_GEAR); }
    public boolean isThrottleOn()     { return this.entityData.get(DATA_THROTTLE); }
    public boolean isBurningOut()     { return this.entityData.get(DATA_BURNOUT); }
    public boolean isTunneled()       { return this.entityData.get(DATA_TUNNELED); }
    public boolean isBraking()        { return this.entityData.get(DATA_BRAKING); }
    public float   getEngineRPM()     { return this.entityData.get(DATA_ENGINE_RPM); }

    public float getRPM() {
        return Mth.clamp((getEngineRPM() - idleRpm()) / (redlineRpm() - idleRpm()), 0f, 1f);
    }

    // ═══════════════════════════════════════════════════════════
    //  HUD
    // ═══════════════════════════════════════════════════════════

    private void displaySpeed(Player player) {
        float speed  = this.getForwardSpeed();
        float kmh    = Math.abs(speed) * 72f;
        float rpm    = this.getEngineRPM();
        int   gear   = this.getCurrentGear();
        boolean moving = Math.abs(speed) > 0.04f;

        String shiftLabel = speed < -0.04f ? "§cR" : moving ? "§aD" : "§7P";
        String driveType  = isRWD ? "§bRWD" : "§eFWD";

        if (advancedDebug) {
            // ── Advanced debug: full physics snapshot ─────────────────────────
            // Line 1 (action bar) — always visible while driving
            player.displayClientMessage(Component.literal(String.format(
                "§e[ADV] %s §f%.0f km/h  §cRPM:§f%.0f  §e%s[%d]  §7surf:§f%.2f",
                driveType, kmh, rpm, shiftLabel, gear, dbgSurfaceFriction)), true);

            // Lines 2–7 in chat — update every display tick
            // Lateral model
            player.displayClientMessage(Component.literal(String.format(
                "§b[LAT]  §ffrontLat=§e%+.5f  §frearLat=§e%+.5f  §7(thresh=%.3f)",
                dbgFrontLat, dbgRearLat, driftThreshold())), false);

            // Yaw state
            player.displayClientMessage(Component.literal(String.format(
                "§d[YAW]  §foverYawRate=§e%+.4f  §fdrifting=%s  §fyawAllowed=%s",
                dbgOverYawRate,
                dbgDrifting   ? "§cY§f" : "§aN§f",
                dbgYawAllowed ? "§aY§f" : "§cN§f")), false);

            // Drivetrain
            player.displayClientMessage(Component.literal(String.format(
                "§a[DRV]  §fthrottle=§e%.3f  §fdriveF=§e%.5f  §fgearMult=§e%.3f",
                dbgThrottleSmooth, dbgDriveForce,
                gear > 0 ? gearRatios()[gear] / gearRatios()[1] : 0f)), false);

            // Traction circle
            player.displayClientMessage(Component.literal(String.format(
                "§c[TRC]  §frearTotal=§e%.5f  §frearLat=§e%.5f  §ffrontTotal=§e%.5f",
                dbgRearGripTotal, dbgRearGripLat, dbgFrontGripTotal)), false);

            // Coast / decay
            player.displayClientMessage(Component.literal(String.format(
                "§7[DCY]  §fcoastDecay=§e%.2f  §f(%s)  §fburnout=%s  §fbraking=%s",
                dbgCoastDecay,
                dbgCoastDecay < latDecay() ? "§9COASTING§f" : "§aPOWER§f",
                this.isBurningOut() ? "§6Y§f" : "N",
                this.isBraking()    ? "§9Y§f" : "N")), false);

        } else if (debugMode) {
            // ── Standard debug: drivetrain focus ─────────────────────────────
            final float[] GR   = gearRatios();
            final float   FD   = finalDrive();
            final float   TC   = tyreCirc();
            final float   PK   = peakDriveForce();
            final float[] TRPM = torqueRpmPoints();
            final float[] TTRQ = torqueCurve();

            float v     = Math.abs(speed);
            float gMult = gear > 0 ? GR[gear] / GR[1] : 0f;
            float torq  = TTRQ[TTRQ.length - 1];
            for (int i = 1; i < TRPM.length; i++) {
                if (rpm <= TRPM[i]) {
                    float t = (rpm - TRPM[i-1]) / (TRPM[i] - TRPM[i-1]);
                    torq = TTRQ[i-1] + t * (TTRQ[i] - TTRQ[i-1]);
                    break;
                }
            }
            float rawDrive = torq * PK * gMult;
            float effDrive = Math.min(rawDrive, dbgRearGripTotal);
            float drag = (1f - (float)rollingDrag()) * v;
            if (v > aeroDragStart()) drag += (float)((v - aeroDragStart()) * aeroDragK() * 20);
            float net = effDrive - drag;
            float vRedline   = (redlineRpm()   / (GR[gear>0?gear:1]*FD*60f))*TC/20f*72f;
            float vDownshift = (downshiftRpm() / (GR[gear>0?gear:1]*FD*60f))*TC/20f*72f;

            player.displayClientMessage(Component.literal(String.format(
                "§e[DBG] §aSpd:§f%.1f km/h  §cRPM:§f%.0f  §e%s[%d]  §bNet:§f%+.5f",
                kmh, rpm, shiftLabel, gear, net)), true);
            player.displayClientMessage(Component.literal(String.format(
                "§7── §eDrivetrain§7 ── torque=%.3f rawDrive=%.5f effDrive=%.5f drag=%.5f",
                torq, rawDrive, effDrive, drag)), false);
            player.displayClientMessage(Component.literal(String.format(
                "§7── §bTraction§7 ── rearGripTotal=%.5f rearGripLat=%.5f (capped=%s)",
                dbgRearGripTotal, dbgRearGripLat,
                rawDrive > dbgRearGripTotal ? "§cYES" : "§aNO")), false);
            player.displayClientMessage(Component.literal(String.format(
                "§7── §aGear %d band§7: %.1f–%.1f km/h  drift=%s burn=%s",
                gear, vDownshift, vRedline,
                this.isDrifting()   ? "§cY§7" : "N",
                this.isBurningOut() ? "§cY§7" : "N")), false);

        } else if (scenarioTest) {
            // ── Scenario test: one-line state label ───────────────────────────
            String state;
            if      (this.isBurningOut())                          state = "§6BURNOUT §8gear=" + gear + " rpm=" + String.format("%.0f", rpm);
            else if (this.isDrifting() && !isRWD)                  state = "§dFWD SLIDE";
            else if (this.isDrifting())                            state = "§cRWD DRIFT";
            else if (this.isBraking() && Math.abs(speed) > 0.1f)  state = "§9BRAKING";
            else if (moving)                                        state = "§aDRIVING " + shiftLabel + " [" + gear + "]";
            else                                                    state = "§7PARKED";
            player.displayClientMessage(Component.literal(
                driveType + "  §f" + String.format("%.0f km/h", kmh) + "  " + state), true);

        } else {
            // ── Normal HUD ────────────────────────────────────────────────────
            player.displayClientMessage(Component.literal(String.format(
                "§aSpeed: §f%.0f km/h  §7|  §cRPM: §f%.0f  §7|  §e%s §8[%d]",
                kmh, rpm, shiftLabel, gear)), true);
        }
    }

    // ═══════════════════════════════════════════════════════════
    //  NBT
    // ═══════════════════════════════════════════════════════════

    @Override protected void readAdditionalSaveData(ValueInput i) {}
    @Override protected void addAdditionalSaveData(ValueOutput o) {}
}