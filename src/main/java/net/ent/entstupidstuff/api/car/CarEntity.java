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
 
    // # SEAT TUNING
    private static final double SEAT_HEIGHT  =  0.4;
    private static final double SEAT_SIDE    =  0.5;
    private static final double SEAT_FORWARD = -0.5;

    // # PHYSICS TUNING CONSTANTS
 
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
 
    // # SYNCED DATA

 
    private static final EntityDataAccessor<Boolean> DATA_DRIFTING =
        SynchedEntityData.defineId(CarEntity.class, EntityDataSerializers.BOOLEAN);
 
    private static final EntityDataAccessor<Float> DATA_WHEEL_SPIN =
        SynchedEntityData.defineId(CarEntity.class, EntityDataSerializers.FLOAT);
 
    // Steering input this tick: -1 = full left, 0 = centre, +1 = full right.
    // Synced to client so the renderer can animate wheels and steering wheel.
    private static final EntityDataAccessor<Float> DATA_STEER_INPUT = SynchedEntityData.defineId(CarEntity.class, EntityDataSerializers.FLOAT);
 
    // Current forward speed (blocks/tick). Positive = forward, negative = reversing.
    // Synced to client for the gear shifter animation.
    private static final EntityDataAccessor<Float> DATA_FORWARD_SPEED = SynchedEntityData.defineId(CarEntity.class, EntityDataSerializers.FLOAT);

    private boolean wasDrifting = false;
    private float   wheelSpin   = 0f;
 
    public CarEntity(EntityType<?> type, Level level) {
        super(type, level);
    }

    @Override
    public float maxUpStep() {
        return 1.0F; // 1 block step height
    }
 
    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(DATA_DRIFTING,      false);
        builder.define(DATA_WHEEL_SPIN,    0f);
        builder.define(DATA_STEER_INPUT,   0f);
        builder.define(DATA_FORWARD_SPEED, 0f);
    }
 
    @Override
    protected Item getDropItem() {
        return null;
    }
 
    @Override
    public void tick() {
        super.tick();
 
        if (!this.isLocalInstanceAuthoritative()) {
            this.setDeltaMovement(Vec3.ZERO);
            return;
        }
 
        boolean forward  = false;
        boolean backward = false;
        boolean left     = false;
        boolean right    = false;
        boolean drift    = false;
 
        if (this.getFirstPassenger() instanceof Player player) {
            forward  = player.zza  >  0f;
            backward = player.zza  <  0f;
            left     = player.xxa  >  0f;
            right    = player.xxa  <  0f;
            drift    = player.isJumping();
        }
 
        tickPhysics(forward, backward, left, right, drift);
 
        this.move(MoverType.SELF, this.getDeltaMovement());
 
        if (this.onGround() && this.getDeltaMovement().y < 0) {
            this.setDeltaMovement(this.getDeltaMovement().x, 0.0, this.getDeltaMovement().z);
        }
 
        this.applyEffectsFromBlocks();

        // Particles

        if (this.level().isClientSide()) {
            spawnWheelParticles();
            spawnExhaust();
        }


    }

    // # Effect Engine

    

    private void spawnWheelParticles() {
        if (!this.onGround()) return;

        double speed = Math.abs(this.getForwardSpeed());

        // Only spawn if moving
        if (speed < 0.05) return;

        boolean drifting = this.isDrifting();

        // Get rotation
        double yRad = Math.toRadians(this.getYRot());
        double sin = Math.sin(yRad);
        double cos = Math.cos(yRad);

        // Wheel positions (adjust if needed)
        double side = 0.6;
        double forward = -0.8;
        double y = 0.05;

        // Left wheel
        spawnWheelParticleAt(
            side * cos + forward * -sin,
            y,
            side * sin + forward * cos,
            drifting
        );

        // Right wheel
        spawnWheelParticleAt(
            -side * cos + forward * -sin,
            y,
            -side * sin + forward * cos,
            drifting
        );
    }

    private void spawnWheelParticleAt(double offsetX, double offsetY, double offsetZ, boolean drifting) {
        Vec3 pos = this.position();

        double px = pos.x + offsetX;
        double py = pos.y + offsetY;
        double pz = pos.z + offsetZ;

        Vec3 vel = this.getDeltaMovement();

        double vx = -vel.x * 0.5 + (random.nextDouble() - 0.5) * 0.1;
        double vz = -vel.z * 0.5 + (random.nextDouble() - 0.5) * 0.1;

        if (drifting) {
            vx *= 1.5;
            vz *= 1.5;
        }

        BlockPos groundPos = BlockPos.containing(px, py - 0.2, pz);
        BlockState state = this.level().getBlockState(groundPos);

        if (drifting) {
            this.level().addParticle(
                ParticleTypes.CAMPFIRE_COSY_SMOKE,
                px, py, pz,
                vx, 0.1, vz
            );
        }

        this.level().addParticle(
            new BlockParticleOption(ParticleTypes.BLOCK, state),
            px, py, pz,
            vx, 0.1, vz
        );
    }

    private void spawnExhaust() {
        double speed = Math.abs(this.getForwardSpeed());

        // Only when moving or revving
        if (speed < 0.02) return;

        double yRad = Math.toRadians(this.getYRot());
        double sin = Math.sin(yRad);
        double cos = Math.cos(yRad);

        // Behind the car
        double backOffset = 1.2;

        double px = this.getX() + (-cos * backOffset);
        double py = this.getY() + 0.3;
        double pz = this.getZ() + (-sin * backOffset);

        Vec3 vel = this.getDeltaMovement();

        double vx = -vel.x * 0.2 + (random.nextDouble() - 0.5) * 0.02;
        double vz = -vel.z * 0.2 + (random.nextDouble() - 0.5) * 0.02;

        this.level().addParticle(
            ParticleTypes.SMOKE,
            px, py, pz,
            vx, 0.02, vz
        );
    }
 
    // # PHYSICS ENGINE

    private void tickPhysics(
        boolean forward,  boolean backward,
        boolean left,     boolean right,
        boolean driftKey
    ) {
        double yRad = Math.toRadians(this.getYRot());
        double sinY = Math.sin(yRad);
        double cosY = Math.cos(yRad);
 
        Vec3 vel = this.getDeltaMovement();
 
        double localFwd = -vel.x * sinY + vel.z * cosY;
        double localLat =  vel.x * cosY + vel.z * sinY;
        double localY   =  vel.y;
        double speed    = Math.abs(localFwd);
 
        boolean canDrift = speed > DRIFT_SPEED_THRESHOLD && (left || right);
        boolean drifting = driftKey && canDrift;
        this.entityData.set(DATA_DRIFTING, drifting);
 
        // -- Throttle / Brake / Reverse --
        if (forward) {
            localFwd = localFwd < -0.05
                ? Math.min(0.0, localFwd + BRAKE_FORCE)
                : Math.min(MAX_SPEED, localFwd + THROTTLE);
        } else if (backward) {
            localFwd = localFwd > 0.05
                ? Math.max(0.0, localFwd - BRAKE_FORCE)
                : Math.max(-MAX_REVERSE, localFwd - REVERSE_THROTTLE);
        }
 
        if (driftKey && !canDrift) localFwd *= HANDBRAKE_DRAG;
        localFwd *= ROLLING_DRAG;
 
        if (Math.abs(localFwd) > DRAG_THRESHOLD) {
            double excess = Math.abs(localFwd) - DRAG_THRESHOLD;
            localFwd -= Math.signum(localFwd) * excess * AERO_DRAG_EXTRA * 20;
        }
        if (Math.abs(localFwd) < 0.001) localFwd = 0.0;
 
        // -- Lateral / Grip --
        if (drifting) {
            localLat *= DRIFT_RETENTION;
            if (!wasDrifting) localLat += (left ? -1.0 : 1.0) * speed * DRIFT_KICK_FACTOR;
        } else {
            localLat *= NORMAL_GRIP;
        }
        wasDrifting = drifting;
        if (Math.abs(localLat) < 0.001) localLat = 0.0;
 
        // -- Steering --
        // Capture raw steer input (-1/0/+1) before applying it to yaw
        float rawSteer = left ? -1f : right ? 1f : 0f;
        this.entityData.set(DATA_STEER_INPUT, rawSteer);
 
        if (localFwd != 0.0 && (left || right)) {
            float steerDeg    = computeSteerAngle(speed, drifting);
            float reverseSign = (localFwd < 0) ? -1f : 1f;
            if (left)  this.setYRot(this.getYRot() - steerDeg * reverseSign);
            if (right) this.setYRot(this.getYRot() + steerDeg * reverseSign);
        }
 
        // -- Gravity --
        localY = this.onGround()
            ? GROUND_STICK
            : Math.max(-MAX_FALL_SPEED, localY - GRAVITY);
 
        double newYRad = Math.toRadians(this.getYRot());
        double newSinY = Math.sin(newYRad);
        double newCosY = Math.cos(newYRad);
 
        this.setDeltaMovement(
            localFwd * (-newSinY) + localLat * newCosY,
            localY,
            localFwd *   newCosY  + localLat * newSinY
        );
 
        // -- Wheel spin --
        // Accumulate rotation in degrees (180° per block travelled)
        wheelSpin = (wheelSpin + (float)(speed * 180.0)) % 360f;
        this.entityData.set(DATA_WHEEL_SPIN, wheelSpin);
 
        // -- Forward speed for shifter --
        this.entityData.set(DATA_FORWARD_SPEED, (float)localFwd);
    }
 
    private float computeSteerAngle(double speed, boolean drifting) {
        float steer;
        if (speed <= PEAK_STEER_SPEED) {
            steer = MAX_STEER_DEG * (float)(speed / PEAK_STEER_SPEED);
        } else {
            double t = Mth.clamp(
                (speed - PEAK_STEER_SPEED) / (MAX_SPEED - PEAK_STEER_SPEED), 0.0, 1.0
            );
            steer = MAX_STEER_DEG * (float)(1.0 - t * (1.0 - HIGH_SPEED_STEER_FRACTION));
        }
        if (drifting) steer *= DRIFT_STEER_BOOST;
        return steer;
    }
 

    // # PASSENGER POSITIONING

    @Override
    protected Vec3 getPassengerAttachmentPoint(
        Entity passenger,
        EntityDimensions dimensions,
        float partialTick
    ) {
        int index = this.getPassengers().indexOf(passenger);
 
        double localX = (index == 0) ? SEAT_SIDE : -SEAT_SIDE;
        double localZ = SEAT_FORWARD;
 
        double yRad = Math.toRadians(this.getYRot());
        double sinY = Math.sin(yRad);
        double cosY = Math.cos(yRad);
 
        double worldX = localX * cosY + localZ * (-sinY);
        double worldZ = localX * sinY + localZ *   cosY;
 
        return new Vec3(worldX, SEAT_HEIGHT, worldZ);
    }
 
    @Override
    protected boolean canAddPassenger(Entity passenger) {
        return this.getPassengers().size() < 2;
    }
 
    @Override
    @Nullable
    public LivingEntity getControllingPassenger() {
        return this.getFirstPassenger() instanceof LivingEntity le ? le : null;
    }
 
    // # INTERACTION
    @Override
    public InteractionResult interact(Player player, InteractionHand hand) {
        if (player.isSecondaryUseActive()) {
            return InteractionResult.PASS;
        }
        if (!this.level().isClientSide()) {
            player.startRiding(this);
        }
        return InteractionResult.SUCCESS;
    }
 
    // # ENTITY FLAGS
    @Override
    public boolean isPickable() {
        return !this.isRemoved();
    }
 
    @Override
    public boolean isPushable() {
        return false;
    }
 

    // # DATA ACCESSORS  (for renderer)
    public boolean isDrifting() {
        return this.entityData.get(DATA_DRIFTING);
    }
 
    public float getWheelSpin() {
        return this.entityData.get(DATA_WHEEL_SPIN);
    }
 
    /** -1 = full left, 0 = centre, +1 = full right */
    public float getSteerInput() {
        return this.entityData.get(DATA_STEER_INPUT);
    }
 
    /** blocks/tick; positive = forward, negative = reversing */
    public float getForwardSpeed() {
        return this.entityData.get(DATA_FORWARD_SPEED);
    }
 

    // # NBT
    @Override
    protected void readAdditionalSaveData(ValueInput input) { }
 
    @Override
    protected void addAdditionalSaveData(ValueOutput output) { }

    
}
