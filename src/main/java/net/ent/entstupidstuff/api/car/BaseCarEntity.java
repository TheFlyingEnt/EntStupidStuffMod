package net.ent.entstupidstuff.api.car;

import org.jetbrains.annotations.Nullable;

import com.mojang.serialization.Codec;

import net.ent.entstupidstuff.api.car.soundengine.CarSoundProfile;
import net.ent.entstupidstuff.item.base.car.CarWrapItem;
import net.ent.entstupidstuff.particle.ParticleTypesFactory;
import net.ent.entstupidstuff.sound.SoundFactory;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
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
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.InterpolationHandler;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.vehicle.VehicleEntity;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
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

    public float carLength() { return 4.0f; }
    public float carWidth() { return 1.8f; }

    // ═══════════════════════════════════════════════════════════
    //  REALISTIC SPEED  (override in subclass)
    //
    //  Scale factor = real-life top speed / in-game top speed.
    //  When realisticSpeed toggle is ON, world velocity is multiplied
    //  by this value. Internal physics (grip, drift, steering) runs
    //  at the original tuned speed — only the world output is scaled.
    //  This preserves the handling feel perfectly.
    //
    //  Default 1.0 = no change. Override per car with measured values.
    // ═══════════════════════════════════════════════════════════

    protected float realisticSpeedScale() { return 1.0f; }
    public static boolean perCarSteering = true;

    // ═══════════════════════════════════════════════════════════
    //  SOUND PROFILE  (override in subclass for per-car sounds)
    //
    //  Returns a CarSoundProfile containing all sound events and
    //  pitch/volume/distance tuning for this car. Override in
    //  subclass to give each car a unique sound character.
    //
    //  Default returns an americanV8 profile with Viper sounds.
    //  Use the presets (americanV8, highRevNA, turboFour, hybrid,
    //  twinTurboV6, f1HybridV6) or construct a fully custom one.
    // ═══════════════════════════════════════════════════════════

    private CarSoundProfile cachedSoundProfile;

    public CarSoundProfile getSoundProfile() {
        if (cachedSoundProfile == null) {
            cachedSoundProfile = createSoundProfile();
        }
        return cachedSoundProfile;
    }

    public boolean isPointInsideCarShape(Vec3 point) {
        double dx = point.x - this.getX();
        double dz = point.z - this.getZ();
        double yaw = Math.toRadians(this.getYRot());
        double cosY = Math.cos(yaw);
        double sinY = Math.sin(yaw);
 
        // Rotate point into car-local coordinate space
        // localX = left/right (perpendicular to car heading)
        // localZ = front/back (along car heading)
        double localX =  dx * cosY + dz * sinY;
        double localZ = -dx * sinY + dz * cosY;
 
        float halfWidth  = carWidth()  / 2f;
        float halfLength = carLength() / 2f;
 
        return Math.abs(localX) <= halfWidth && Math.abs(localZ) <= halfLength;
    }

    @Nullable
    public Vec3 raycastCarShape(Vec3 eyePos, Vec3 lookDir, double reach) {
        // Test points along the ray in 0.1-block increments
        int steps = (int)(reach / 0.1);
        for (int i = 0; i <= steps; i++) {
            double t = i * 0.1;
            Vec3 point = eyePos.add(lookDir.x * t, lookDir.y * t, lookDir.z * t);
 
            // Quick Y check — skip if too high or too low
            double dy = point.y - this.getY();
            if (dy < -0.1 || dy > 2.0) continue;
 
            if (isPointInsideCarShape(point)) {
                return point;
            }
        }
        return null;
    }



    protected CarSoundProfile createSoundProfile() {
        return CarSoundProfile.americanV8(
            net.ent.entstupidstuff.sound.SoundFactory.ENTITY_VEHICLE_DODGEVIPERGTS_IDLE,
            net.ent.entstupidstuff.sound.SoundFactory.ENTITY_VEHICLE_DODGEVIPERGTS_GEAR_1,
            net.ent.entstupidstuff.sound.SoundFactory.ENTITY_VEHICLE_DODGEVIPERGTS_BREAK,
            net.ent.entstupidstuff.sound.SoundFactory.ENTITY_VEHICLE_DODGEVIPERGTS_GEAR_TOP,
            net.ent.entstupidstuff.sound.SoundFactory.ENTITY_VEHICLE_TIRES_SQUAL_LOOP
        );
    }

    protected SoundEvent engineStartSound() {
        return net.ent.entstupidstuff.sound.SoundFactory.ENTITY_VEHICLE_DODGEVIPERGTS_START;
    }

    public static boolean carCollisionEnabled = true;
    public boolean isOpenCockpit() { return false; }
    private int carCollisionCooldown = 0;
    protected float carMass() { return 0.80f; }

    /**
     * Camera "weight" for the Forza-style spring-lagged chase camera.
     * 0.0 = featherweight (camera glued to car, instant, kart-like)
     * 1.0 = very heavy   (camera lags far behind, big overshoot)
     *
     * Heavier cars use a softer camera spring → more lag under accel,
     * more swing in corners → the car FEELS heavier.
     *
     * Guidelines (match these to carMass()):
     *   0.35 = F1 car      — stiff, planted, instant
     *   0.45 = GR86, Civic — light & nimble
     *   0.55 = Type R, Nissan Z, GR86
     *   0.70 = Viper, GT3  — heavy sports car
     *   1.00 = GT500       — heavy muscle car, lots of lag
     */
    public float cameraWeight() { return 0.6f; }


    private void tickCarCollision() {
        if (carCollisionCooldown > 0) {
            carCollisionCooldown--;
            return;
        }
 
        var nearby = this.level().getEntities(this, this.getBoundingBox().inflate(0.3));
        for (var entity : nearby) {
            if (!(entity instanceof BaseCarEntity other)) continue;
            if (other == this) continue;
 
            handleCarToCarCollision(other);
            break; // handle one collision per tick to prevent cascades
        }
    }

    private void handleCarToCarCollision(BaseCarEntity other) {
        // ── Direction and distance ───────────────────────────────
        Vec3 delta = other.position().subtract(this.position());
        double dist = delta.horizontalDistance();
        if (dist < 0.01) return; // perfectly overlapping — skip
 
        Vec3 normal = new Vec3(delta.x / dist, 0, delta.z / dist);
 
        // ── Approach speed ───────────────────────────────────────
        Vec3 relVel = this.getDeltaMovement().subtract(other.getDeltaMovement());
        double approach = relVel.x * normal.x + relVel.z * normal.z;
        if (approach <= 0) return; // cars moving apart — no collision
 
        // ── Mass-based impulse ───────────────────────────────────
        // Heavier car barely moves. Lighter car gets pushed hard.
        float massA = this.carMass();
        float massB = other.carMass();
        float totalMass = massA + massB;
 
        float bounceCoeff = 0.55f; // energy retained (0=sticky, 1=elastic)
        double impulse = approach * (1f + bounceCoeff);
 
        // Impulse split by mass ratio
        double impulseA = impulse * (massB / totalMass);
        double impulseB = impulse * (massA / totalMass);
 
        // Apply impulse along collision normal
        this.setDeltaMovement(
            this.getDeltaMovement().x - normal.x * impulseA,
            this.getDeltaMovement().y,
            this.getDeltaMovement().z - normal.z * impulseA
        );
        other.setDeltaMovement(
            other.getDeltaMovement().x + normal.x * impulseB,
            other.getDeltaMovement().y,
            other.getDeltaMovement().z + normal.z * impulseB
        );
 
        // ── Separation push (prevent sticking) ──────────────────
        double minSep = 2.2; // minimum distance between car centers
        if (dist < minSep) {
            double pushDist = (minSep - dist) * 0.5;
            Vec3 push = new Vec3(normal.x * pushDist, 0, normal.z * pushDist);
            this.setPos(this.position().subtract(push));
            other.setPos(other.position().add(push));
        }
 
        // ── Spin from angled impacts ─────────────────────────────
        float speed = (float) approach;
        if (speed > 0.10f) {
            // This car's heading vs collision normal
            double yRadA = Math.toRadians(this.getYRot());
            double crossA = (-Math.sin(yRadA)) * normal.z - Math.cos(yRadA) * normal.x;
            float spinA = (float)(crossA * speed * speed * 3.0f);
            spinA = Mth.clamp(spinA, -yawMax() * 1.5f, yawMax() * 1.5f);
            this.overYawRate += spinA;
            this.overYawRate = Mth.clamp(this.overYawRate, -yawMax(), yawMax());
            this.setYRot(this.getYRot() + spinA * 0.5f); // immediate rotation
 
            // Other car's spin (opposite direction)
            double yRadB = Math.toRadians(other.getYRot());
            double crossB = (-Math.sin(yRadB)) * (-normal.z) - Math.cos(yRadB) * (-normal.x);
            float spinB = (float)(crossB * speed * speed * 3.0f);
            spinB = Mth.clamp(spinB, -other.yawMax() * 1.5f, other.yawMax() * 1.5f);
            other.overYawRate += spinB;
            other.overYawRate = Mth.clamp(other.overYawRate, -other.yawMax(), other.yawMax());
            other.setYRot(other.getYRot() + spinB * 0.5f);
        }
 
        // ── Collision effects ────────────────────────────────────
        if (speed > 0.08f) {
            float vol = Math.min(1.0f, speed * 1.5f);
            float pitch = 0.7f + speed * 0.3f;
 
            this.level().playLocalSound(
                this.getX(), this.getY(), this.getZ(),
                net.ent.entstupidstuff.sound.SoundFactory.ENTITY_VEHICLE_HEAVY_CRASH,
                net.minecraft.sounds.SoundSource.NEUTRAL,
                vol, pitch, false
            );
 
            // Spark particles at collision point (midpoint between cars)
            double midX = (this.getX() + other.getX()) / 2;
            double midY = (this.getY() + other.getY()) / 2 + 0.5;
            double midZ = (this.getZ() + other.getZ()) / 2;
            int count = (int) Math.min(12, speed * 10);
            for (int i = 0; i < count; i++) {
                this.level().addParticle(
                    net.minecraft.core.particles.ParticleTypes.CRIT,
                    midX + (this.random.nextDouble() - 0.5) * 1.2,
                    midY + this.random.nextDouble() * 0.5,
                    midZ + (this.random.nextDouble() - 0.5) * 1.2,
                    (this.random.nextDouble() - 0.5) * 0.3,
                    this.random.nextDouble() * 0.15,
                    (this.random.nextDouble() - 0.5) * 0.3
                );
            }
        }
 
        // ── Cooldown — prevent jitter from re-checking next tick ──
        this.carCollisionCooldown = 3;
    }



 
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
    //  SURFACE SENSITIVITY  (override in subclass)
    //
    //  Scales how much non-asphalt surfaces penalise grip.
    //  Default 1.0 = standard road tyres (treaded, all-surface).
    //
    //  Higher values amplify the penalty:
    //    surfaceFriction = 0.72 (gravel), penalty = 0.28
    //    scale 1.0 → effective 0.72  (road car — moderate loss)
    //    scale 2.5 → effective 0.30  (F1 slicks — nearly undriveable)
    //
    //  On asphalt (friction=1.0) the scale has zero effect.
    //  Floor of 0.05 prevents complete zero-grip (always some friction).
    //
    //  Guidelines:
    //    1.0  = all-season / touring tyres (Civic, stock road cars)
    //    1.2  = performance summer tyres (GR86, Type R)
    //    1.4  = max-performance street tyres (Viper, GT500, Nissan Z)
    //    1.6  = semi-slick / track tyres (GT3 Cup 2)
    //    2.5  = full slicks (F1 — almost no off-surface grip)
    // ═══════════════════════════════════════════════════════════

    protected float surfacePenaltyScale() { return 1.0f; }
    private int handbrakeHoldTicks = 0;

    // ═══════════════════════════════════════════════════════════
    //  CRASH RESISTANCE  (override in subclass)
    //
    //  How much speed the car retains after a max-severity crash.
    //  0.0 = car stops dead on impact (paper thin)
    //  0.5 = car retains 50% speed (heavy, tanks through walls)
    //
    //  Heavy cars with strong chassis retain more speed.
    //  Light/fragile cars (F1, GR86) lose almost everything.
    //
    //  Guidelines:
    //    0.08 = F1 car — carbon fibre, disintegrates on contact
    //    0.12 = GR86, Civic — lightweight, poor crash structure
    //    0.15 = Type R, Nissan Z — sports car, moderate
    //    0.20 = GT3, Viper — heavy sports car
    //    0.25 = GT500 — heavy muscle car, tanks through more
    // ═══════════════════════════════════════════════════════════

    protected float crashResistance() { return 0.15f; }
 
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
    private static final EntityDataAccessor<String>  DATA_WRAP =
        SynchedEntityData.defineId(BaseCarEntity.class, EntityDataSerializers.STRING);
    private static final EntityDataAccessor<ItemStack> LICENSE_PLATE =
        SynchedEntityData.defineId(BaseCarEntity.class, EntityDataSerializers.ITEM_STACK);
    private static final EntityDataAccessor<ItemStack> RADIO_DISC  =
        SynchedEntityData.defineId(BaseCarEntity.class, EntityDataSerializers.ITEM_STACK);
    private static final EntityDataAccessor<Integer> DATA_REV_LIGHT_STATE =
        SynchedEntityData.defineId(BaseCarEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<String> DATA_BODYKIT =
        SynchedEntityData.defineId(BaseCarEntity.class, EntityDataSerializers.STRING);
    
    private static final EntityDataAccessor<Boolean> DATA_LEFT_DOOR_OPEN =
        SynchedEntityData.defineId(BaseCarEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> DATA_RIGHT_DOOR_OPEN =
        SynchedEntityData.defineId(BaseCarEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> DATA_HOOD_OPEN =
        SynchedEntityData.defineId(BaseCarEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> DATA_ENGINE_JUST_STARTED =
        SynchedEntityData.defineId(BaseCarEntity.class, EntityDataSerializers.BOOLEAN);

    private static final EntityDataAccessor<Boolean> DATA_HAS_FUEL =
        SynchedEntityData.defineId(BaseCarEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Float> DATA_FRONT_GRIP =
        SynchedEntityData.defineId(BaseCarEntity.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Float> DATA_REAR_GRIP =
        SynchedEntityData.defineId(BaseCarEntity.class, EntityDataSerializers.FLOAT);

    private static final EntityDataAccessor<Integer> DATA_TIRE_ANIM_CORNER =
        SynchedEntityData.defineId(BaseCarEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> DATA_TIRE_ANIM_TICKS =
        SynchedEntityData.defineId(BaseCarEntity.class, EntityDataSerializers.INT);

    private static final EntityDataAccessor<Integer> DATA_TIRE_TICKS_FL =
        SynchedEntityData.defineId(BaseCarEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> DATA_TIRE_TICKS_FR =
        SynchedEntityData.defineId(BaseCarEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> DATA_TIRE_TICKS_RL =
        SynchedEntityData.defineId(BaseCarEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> DATA_TIRE_TICKS_RR =
        SynchedEntityData.defineId(BaseCarEntity.class, EntityDataSerializers.INT);

    private static final EntityDataAccessor<Float> DATA_FUEL_LEVEL =
        SynchedEntityData.defineId(BaseCarEntity.class, EntityDataSerializers.FLOAT);   // 0..1
    private static final EntityDataAccessor<Integer> DATA_WHEEL_PCTS =
        SynchedEntityData.defineId(BaseCarEntity.class, EntityDataSerializers.INT);     // packed FL,FR,RL,RR (0..100 each)


    private static final int TIRE_ANIM_DURATION = 9; // ticks (~1.2 s)
 
    // Tracks which wheel slots had a tire last tick (server) — to detect installs.
    private final boolean[] prevWheelPresent = new boolean[4]; // FL,FR,RL,RR = slots 4,5,6,7



 
    // ═══════════════════════════════════════════════════════════
    //  CAR INVENTORY  (8 slots: plate, fuel, wrap, radio, 4 wheels)
    // ═══════════════════════════════════════════════════════════
 
    private final SimpleContainer carInventory = new SimpleContainer(8) {
        @Override public void setChanged() {
            super.setChanged();
            onInventoryChanged();
        }
    };
 
    private int     fuelTickCounter = 0;
    @SuppressWarnings("unused")
    private boolean radioPlaying    = false;
 
    /** Returns the car's 8-slot inventory. Used by CarMenu. */
    public SimpleContainer getCarInventory() { return carInventory; }
 
    /** Whether a music disc is in the radio slot. */
    public boolean hasRadioDisc() {
        //ItemStack stack = carInventory.getItem(3);
        ItemStack stack = getSyncedRadioDisc();


        return !stack.isEmpty()
                && stack.has(DataComponents.JUKEBOX_PLAYABLE);
    }
 
    /** Called when any inventory slot changes. */
    private void onInventoryChanged() {
        // Wrap changed — sync to all clients for texture swap
        ItemStack wrapStack = carInventory.getItem(2); // slot 2 = wrap
        ItemStack licenceStack = carInventory.getItem(0); // slot 0 = license
        ItemStack radiostack = carInventory.getItem(3); // slot 0 = license
        String wrapId = "default";
        if (!wrapStack.isEmpty()) {
            // Use CarWrapItem data if available, otherwise fall back to item name
            if (wrapStack.getItem() instanceof CarWrapItem) {
                wrapId = CarWrapItem.getWrapId(wrapStack);
            } else {
                wrapId = wrapStack.getHoverName().getString().toLowerCase().replace(" ", "_");
            }
        }
        if (!this.level().isClientSide()) {
            this.entityData.set(DATA_WRAP, wrapId);
            entityData.set(LICENSE_PLATE, licenceStack);
            entityData.set(RADIO_DISC, radiostack);

            for (int i = 0; i < 4; i++) {
                boolean present = !carInventory.getItem(4 + i).isEmpty();
                if (present && !prevWheelPresent[i]) {
                    // This wheel was just installed — start ITS timer.
                    this.entityData.set(tireTicksAccessor(i), TIRE_ANIM_DURATION);
                    this.level().playSound(null, this.blockPosition(),
                        net.minecraft.sounds.SoundEvents.ITEM_FRAME_ADD_ITEM, // swap for a wheel-clunk
                        SoundSource.PLAYERS, 1.0f, 0.8f);
                }
                prevWheelPresent[i] = present;
            }
            syncCarSystemState();





            //setLicensePlate(licenceStack);
        }
    }
 
    /** Returns the current wrap ID for texture selection. */
    public String getCurrentWrap() { return this.entityData.get(DATA_WRAP); }
    public String getCurrentBodyKit() { return this.entityData.get(DATA_BODYKIT); }
    
    public void setCurrentBodyKit(String Value) {this.entityData.set(DATA_BODYKIT, Value); }
 
    /**
     * Override in subclass to define available wrap IDs.
     * Default: only "default" (base texture).
     */
    public String[] availableWraps() { return new String[]{ "default" }; }
    public String[] availableBodyKits() { return new String[]{}; }
 
    /**
     * Returns a string ID for this car type, used in texture paths.
     * Override in each subclass.
     * Path: assets/entstupidstuff/textures/entity/{carTypeId}/{wrapId}.png
     */
    public String getCarTypeId() { return "car"; }

 
    // ═══════════════════════════════════════════════════════════
    //  SERVER-SIDE PHYSICS STATE
    // ═══════════════════════════════════════════════════════════
 
    private float   engineRPM      = 0f;
    private int     currentGear    = 1;
    private float   localSpeed     = 0f; // last computed forward speed — for client HUD

    // ── Client-side physics state (for packet sending) ───────────
    // tickPhysics only sets entityData when sync=true (server-side).
    // On the driver's client, these local fields capture the state
    // so sendPhysicsPacket() can read them.
    private boolean localThrottle  = false;
    private boolean localBraking   = false;
    private boolean localBurnout   = false;
    private boolean localDrifting  = false;
    private int     clutchTimer    = 0;
    private float   throttleSmooth = 0f;
    private float   frontLat       = 0f;
    private float   rearLat        = 0f;
    protected   float   overYawRate    = 0f;
    private float   wheelSpin      = 0f;
    private float   rearWheelSpin  = 0f;
    private float   burnoutRPM      = 0f;
    private boolean wasBurningOut   = false;
    private float   handbrakeSmooth = 0f; // 0=released → 1=fully engaged; ramps to prevent snap
    private float   steerSmooth     = 0f; // -1..+1 ramped steering (Forza-style input smoothing)
    private boolean shiftUpRequested   = false;
    private boolean shiftDownRequested = false;
    private int     revLimiterTicks    = 0; // ticks at rev limiter (for sound/visual)
    private float   lastServerSpeed   = 0f; // last known speed before driver exit — for coasting
    private float   lastServerYRot    = 0f; // last known yaw before driver exit

    /** Called from ModKeybinds when R is pressed. */
    public void requestShiftUp()   { this.shiftUpRequested = true; }
    /** Called from ModKeybinds when F is pressed. */
    public void requestShiftDown() { this.shiftDownRequested = true; }

 
    // ── Cached spec arrays — avoids allocating new float[] every tick ──
    private float[] cachedGearRatios;
    private float[] cachedTorqueRpmPoints;
    private float[] cachedTorqueCurve;
 
 
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
    //  GLOBAL TOGGLES  (static — shared across ALL car instances)
    //
    //  Changed via /carconfig commands. These persist for the
    //  session — switching cars keeps your settings.
    // ═══════════════════════════════════════════════════════════

    /**
     * HUD mode — mutually exclusive. All false = normal HUD.
     *   debugMode:     drivetrain focus (4 chat lines).
     *   advancedDebug: full per-tick physics snapshot (6 chat lines).
     *   scenarioTest:  single action-bar line with state label.
     */
    public static boolean debugMode      = false;
    public static boolean advancedDebug  = false;
    public static boolean scenarioTest   = false;

    /**
     * When true, world velocity is multiplied by realisticSpeedScale().
     * Internal physics (grip, drift, traction) is unchanged — only
     * the car's actual movement speed in the world is scaled.
     */
    public static boolean realisticSpeed = false;

    /**
     * When true, keyboard steering input (A/D) is smoothed with
     * Forza-style ramping — tap = small nudge, hold = full lock.
     * When false, A/D instantly sets steerInput to ±1 (raw digital).
     */
    public static boolean forzaTurning = true;
    protected float steerSpeedReference() { return 0.6f; }
    protected float steerMinSensitivity() { return 0.55f; }

    // ═══════════════════════════════════════════════════════════
    //  PER-CAR TOGGLES  (instance — specific to each car entity)
    // ═══════════════════════════════════════════════════════════

    /** When false, surface/rain grip multipliers are bypassed. */
    public boolean surfaceFrictionEnabled = true;

    /**
     * true  = RWD — drive torque loads rear traction circle → oversteer.
     * false = FWD — drive torque loads front traction circle → understeer.
     */
    public boolean isRWD;

    /**
     * When true, player must shift manually using R (up) and F (down).
     * Rev limiter at redline — no auto-upshift, power cut if you don't shift.
     * Toggle via /carconfig manualTransmission true/false.
     */
    public static boolean manualTransmission = false;

        /**
     * Left-hand drive (LHD) = driver sits on the LEFT side.
     * Right-hand drive (RHD) = driver sits on the RIGHT side (UK, Japan, Australia).
     *
     * This determines which door opens for the driver:
     *   LHD (default): driver → left door,  passenger → right door
     *   RHD:           driver → right door, passenger → left door
     */
    protected boolean isLeftHandDrive() { return true; }
 
 
    /** Whether the hood should open when GUI is accessed. Default true.
     *  F1 cars return false (no hood). */
    protected boolean hasHood() { return true; }

    private int  leftDoorTimer  = 0;   // ticks remaining before door auto-closes
    private int  rightDoorTimer = 0;
    private int  previousPassengerCount = 0;
    private boolean wasOccupied = false; // for engine start detection



 
    // ═══════════════════════════════════════════════════════════
    //  CONSTRUCTOR
    // ═══════════════════════════════════════════════════════════
 
    public BaseCarEntity(EntityType<?> type, Level level) {
        super(type, level);
        this.isRWD = defaultIsRWD();
        this.cachedGearRatios      = gearRatios();
        this.cachedTorqueRpmPoints = torqueRpmPoints();
        this.cachedTorqueCurve     = torqueCurve();
    }
 
    @Override public float maxUpStep() { return 1.0f; }

    /**
     * Called by Minecraft's networking to apply server velocity corrections.
     * When the local player is driving, IGNORE these — the client's physics
     * engine owns the velocity. Server corrections would:
     *   - Cause periodic speed dips (~10 sec from entity tracking updates)
     *   - Cancel collision bounces (server still has pre-crash velocity)
     *
     * For non-driver clients and empty cars, pass through normally.
     */
    @Override
    public void lerpMotion(Vec3 vec3) {
        if (this.level().isClientSide() && this.isLocalInstanceAuthoritative()) {
            // Driver's client — ignore server velocity corrections
            return;
        }
        super.lerpMotion(vec3);
    }

    /**
     * Returns the first passenger as the controlling entity.
     *
     * This makes isLocalInstanceAuthoritative() return true on the
     * driver's client — giving it ownership of the entity's position.
     * The driver's client runs physics + move() for jitter-free movement.
     *
     * The server ALSO runs physics (without move()) purely to set
     * entityData, which syncs to all other clients for sounds/particles.
     */
    @Override
    @Nullable
    public LivingEntity getControllingPassenger() {
        return this.getFirstPassenger() instanceof LivingEntity le ? le : null;
    }

    // ═══════════════════════════════════════════════════════════
    //  CLIENT MOVEMENT PREDICTION
    //
    //  The server runs physics and sends position updates every few
    //  ticks. Without client-side prediction, the car teleports to
    //  each update and freezes in between — visible as jitter.
    //
    //  This method reconstructs movement from synced entityData
    //  (forward speed + yaw) and applies it on the client every tick.
    //  Server position corrections prevent drift over time.
    //  No version-specific lerp API needed — works everywhere.
    // ═══════════════════════════════════════════════════════════

    // ═══════════════════════════════════════════════════════════
    //  CLIENT INTERPOLATION  (for passengers + bystanders)
    //
    //  When getInterpolation() returns non-null, server position
    //  updates go through the handler's interpolateTo() instead of
    //  snapping the entity position instantly. The built-in
    //  InterpolationHandler smoothly lerps between updates.
    //
    //  interpolate() must be called each tick to advance the lerp.
    // ═══════════════════════════════════════════════════════════

    public boolean isTurbo() {
        return false;
    }

    private InterpolationHandler carInterpolation;

    @Override
    @Nullable
    public InterpolationHandler getInterpolation() {
        // Only non-authoritative clients need interpolation.
        // The driver's client runs its own physics — no lerp needed.
        if (this.level().isClientSide() && !this.isLocalInstanceAuthoritative()) {
            if (carInterpolation == null) {
                carInterpolation = new InterpolationHandler(this);
            }
            return carInterpolation;
        }
        return null;
    }
 
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
        builder.define(DATA_WRAP,            "default");
        builder.define(LICENSE_PLATE, ItemStack.EMPTY);
        builder.define(RADIO_DISC, ItemStack.EMPTY);
        builder.define(DATA_REV_LIGHT_STATE,        0);
        builder.define(DATA_BODYKIT, "stock");
        builder.define(DATA_LEFT_DOOR_OPEN,     false);
        builder.define(DATA_RIGHT_DOOR_OPEN,    false);
        builder.define(DATA_HOOD_OPEN,          false);
        builder.define(DATA_ENGINE_JUST_STARTED, false);

        builder.define(DATA_HAS_FUEL,         true);
        builder.define(DATA_FRONT_GRIP,       1.0f);
        builder.define(DATA_REAR_GRIP,        1.0f);
        builder.define(DATA_TIRE_ANIM_CORNER, -1);
        builder.define(DATA_TIRE_ANIM_TICKS,  0);

        builder.define(DATA_TIRE_TICKS_FL, 0);
        builder.define(DATA_TIRE_TICKS_FR, 0);
        builder.define(DATA_TIRE_TICKS_RL, 0);
        builder.define(DATA_TIRE_TICKS_RR, 0);

        builder.define(DATA_FUEL_LEVEL, 1.0f);
        builder.define(DATA_WHEEL_PCTS, packWheelPcts(100, 100, 100, 100));


        this.engineRPM  = idleRpm();
        this.burnoutRPM = idleRpm();

        
    }

    private EntityDataAccessor<Integer> tireTicksAccessor(int corner) {
        return switch (corner) {
            case 0 -> DATA_TIRE_TICKS_FL;
            case 1 -> DATA_TIRE_TICKS_FR;
            case 2 -> DATA_TIRE_TICKS_RL;
            default -> DATA_TIRE_TICKS_RR;
        };
    }

    private static int packWheelPcts(int fl, int fr, int rl, int rr) {
        return ((fl & 0xFF) << 24) | ((fr & 0xFF) << 16) | ((rl & 0xFF) << 8) | (rr & 0xFF);
    }

    private float computeFuelLevel() {
        ItemStack fuel = carInventory.getItem(1);
        if (fuel.isEmpty() || !fuel.isDamageableItem()) return 0f;
        return Mth.clamp(1f - (float) fuel.getDamageValue() / fuel.getMaxDamage(), 0f, 1f);
    }

    private int computeWheelPct(int slot) {
        ItemStack w = carInventory.getItem(slot);
        if (w.isEmpty() || !w.isDamageableItem()) return 0; // 0 = no tire
        return Math.round(Mth.clamp(1f - (float) w.getDamageValue() / w.getMaxDamage(), 0f, 1f) * 100f);
    }


    protected float noFuelPowerFactor() { return 0.22f; }
 
    @Override protected Item getDropItem() { return null; }
 
    // ═══════════════════════════════════════════════════════════
    //  TICK
    // ═══════════════════════════════════════════════════════════
 
    @Override
    public void tick() {
        super.tick();

        // ═══════════════════════════════════════════════════════════
        //  HYBRID PHYSICS: driver's client + server both run physics.
        //
        //  Driver's client: reads local input (player.zza/xxa), runs
        //    physics, calls move(). This gives the driver butter-smooth
        //    movement with zero jitter — no server corrections to fight.
        //
        //  Server: reads input from getLastClientInput(), runs physics,
        //    sets entityData. entityData syncs server→client so ALL other
        //    clients (passengers, bystanders) get speed/RPM/drifting/etc.
        //    Server does NOT call move() — the driver's client owns position
        //    and sends it via vehicle move packets.
        //
        //  Non-driver clients: tickClientMovement() predicts movement
        //    from synced entityData for smooth display.
        // ═══════════════════════════════════════════════════════════

        boolean isServer = !this.level().isClientSide();
        boolean isClient = this.level().isClientSide();

        // ── SERVER ───────────────────────────────────────────────────
        if (isServer) {
            if (this.getFirstPassenger() instanceof ServerPlayer sp) {
                // ── DRIVEN: entityData comes from CarPhysicsPayload ──
                // The driver's client sends exact physics state via a
                // custom C2S packet every tick. applyPhysicsPacket()
                // writes it to entityData → syncs to ALL other clients.
                // No physics runs on the server — zero fighting.
                //
                // We still need to keep deltaMovement in sync so
                // coasting works when the driver exits.
                var input = sp.getLastClientInput();
                boolean forward   = input.forward();
                boolean backward  = input.backward();
                boolean handbrake = input.jump();
                @SuppressWarnings("unused")
                boolean burnout   = forward && (backward || handbrake)
                                    && Math.abs(this.getForwardSpeed()) < 0.30f;

                // Store last known speed for coasting when driver exits.
                // Do NOT call setDeltaMovement() here — entity tracking
                // would broadcast it to the driver's client, overwriting
                // the client's locally-computed velocity. This causes:
                //   - periodic speed dips (~10 sec) from tracking corrections
                //   - collision bounces being immediately cancelled
                lastServerSpeed = this.getForwardSpeed();
                lastServerYRot  = this.getYRot();

            } else if (this.getFirstPassenger() == null) {
                // ── EMPTY: run physics for gravity/coast/decay ────────
                // On first tick with no driver, set deltaMovement from
                // the last known speed so the car coasts.
                if (lastServerSpeed != 0f) {
                    float spdScale = realisticSpeed ? realisticSpeedScale() : 1.0f;
                    double yRad = Math.toRadians(lastServerYRot);
                    this.setDeltaMovement(
                        lastServerSpeed * (-Math.sin(yRad)) * spdScale,
                        this.getDeltaMovement().y,
                        lastServerSpeed * Math.cos(yRad) * spdScale);
                    lastServerSpeed = 0f;
                }
                frontLat      *= 0.70f;
                rearLat       *= 0.70f;
                overYawRate   *= 0.70f;
                throttleSmooth = 0f;
                steerSmooth    = 0f;
                burnoutRPM     = idleRpm();

                tickPhysics(false, false, false, false, false);

                this.move(MoverType.SELF, this.getDeltaMovement());
                if (this.onGround() && this.getDeltaMovement().y < 0)
                    this.setDeltaMovement(this.getDeltaMovement().x, 0, this.getDeltaMovement().z);
            }

            this.applyEffectsFromBlocks();

            if (this.tickCount % 5 == 0)
                this.entityData.set(DATA_TUNNELED, detectTunnel());

            tickTireAnim();
            if (this.tickCount % 10 == 0) syncCarSystemState(); // safety re-sync as durability drops

            // Adding Support for tickCarSystem() - Wheel and Tire Wear:
            if (this.getFirstPassenger() != null) {
                tickCarSystems();
                tickDoorAndHood();
            }

        }

        // ── DRIVER'S CLIENT: full physics + movement (smooth) ────────
        if (isClient && this.isLocalInstanceAuthoritative()) {
            boolean forward = false, backward = false,
                    left    = false, right    = false, handbrake = false;

            /*if (this.getFirstPassenger() instanceof Player player) {
                forward   = player.zza  >  0f;
                backward  = player.zza  <  0f;
                left      = player.xxa  >  0f;
                right     = player.xxa  <  0f;
                handbrake = player.isJumping();
                if (this.tickCount % 2 == 0) displaySpeed(player);
            } else {*/
            if (this.getFirstPassenger() instanceof Player player) {
                forward   = player.zza  >  0f;
                backward  = player.zza  <  0f;
                left      = player.xxa  >  0f;
                right     = player.xxa  <  0f;
                handbrake = player.isJumping();

                // W+S cancel out in zza (Minecraft sums them to 0), so a
                // brake-torque burnout (throttle + brake while stopped) would
                // never register. Read the raw keys and force both flags true
                // in the low-speed burnout zone. Speed-gated so it doesn't
                // fight throttle/brake while actually driving.
                var opts = net.minecraft.client.Minecraft.getInstance().options;
                if (opts.keyUp.isDown() && opts.keyDown.isDown()
                        && Math.abs(this.localSpeed) < 0.30f) {
                    forward  = true;
                    backward = true;
                }

                if (this.tickCount % 2 == 0) displaySpeed(player);
            } else {
                frontLat      *= 0.70f;
                rearLat       *= 0.70f;
                overYawRate   *= 0.70f;
                throttleSmooth = 0f;
                steerSmooth    = 0f;
                burnoutRPM     = idleRpm();
            }

            tickPhysics(forward, backward, left, right, handbrake);

            // ── Movement with sub-stepping ───────────────────────────
            Vec3 fullMove = this.getDeltaMovement();
            double moveLen = fullMove.horizontalDistance();
            int subSteps = Math.max(1, (int) Math.ceil(moveLen / 1.0));

            // Track which axis actually collided (for sub-step accuracy)
            boolean wallHitX = false, wallHitZ = false;

            if (subSteps <= 1) {
                this.move(MoverType.SELF, fullMove);
                Vec3 post = this.getDeltaMovement();
                wallHitX = !this.minorHorizontalCollision
                        && Math.abs(fullMove.x) > 0.01
                        && Math.abs(post.x) < Math.abs(fullMove.x) * 0.5;
                wallHitZ = !this.minorHorizontalCollision
                        && Math.abs(fullMove.z) > 0.01
                        && Math.abs(post.z) < Math.abs(fullMove.z) * 0.5;
            } else {
                Vec3 step = new Vec3(fullMove.x / subSteps, fullMove.y / subSteps, fullMove.z / subSteps);
                for (int s = 0; s < subSteps; s++) {
                    this.setDeltaMovement(step);
                    this.move(MoverType.SELF, step);
                    if (this.horizontalCollision && !this.minorHorizontalCollision) {
                        // Compare post-move against STEP size, not full size.
                        // This correctly identifies which axis actually hit.
                        Vec3 post = this.getDeltaMovement();
                        wallHitX = Math.abs(step.x) > 0.005
                                && Math.abs(post.x) < Math.abs(step.x) * 0.5;
                        wallHitZ = Math.abs(step.z) > 0.005
                                && Math.abs(post.z) < Math.abs(step.z) * 0.5;
                        break;
                    }
                }
                if (!this.horizontalCollision) {
                    this.setDeltaMovement(fullMove);
                }
            }

            // ── Wall collision response ──────────────────────────────
            Vec3 postMove = this.getDeltaMovement();
            boolean steppingUp = postMove.y > 0.01;
            if (!steppingUp && this.onGround() && (wallHitX || wallHitZ)) {
                double impactSpeed = fullMove.horizontalDistance();

                // Bounce + speed retention scale with impact speed
                float bounce, retain;
                if (impactSpeed < 0.15) {
                    bounce = 0.30f; retain = 0.90f;
                } else if (impactSpeed < 0.50) {
                    bounce = 0.28f; retain = 0.80f;
                } else if (impactSpeed < 1.00) {
                    bounce = 0.22f; retain = 0.60f;
                } else {
                    bounce = 0.15f; retain = 0.35f;
                }

                double vx = wallHitX ? -fullMove.x * bounce : fullMove.x * retain;
                double vz = wallHitZ ? -fullMove.z * bounce : fullMove.z * retain;
                this.setDeltaMovement(vx, postMove.y, vz);

                // ── Spin-out ─────────────────────────────────────────
                if (impactSpeed > 0.10) {
                    double yRad    = Math.toRadians(this.getYRot());
                    double carDirX = -Math.sin(yRad);
                    double carDirZ =  Math.cos(yRad);
                    // Wall normal: only on the axis that ACTUALLY hit
                    double wallNX  = wallHitX ? Math.signum(fullMove.x) : 0;
                    double wallNZ  = wallHitZ ? Math.signum(fullMove.z) : 0;
                    double cross   = carDirX * wallNZ - carDirZ * wallNX;

                    float spinAmount = (float)(cross * impactSpeed * impactSpeed * 6.0);

                    // Head-on: random spin nudge
                    if (Math.abs(cross) < 0.15 && impactSpeed > 0.30) {
                        spinAmount += (this.random.nextFloat() - 0.5f) * (float)(impactSpeed * 3.0);
                    }

                    spinAmount = Mth.clamp(spinAmount, -yawMax() * 2f, yawMax() * 2f);

                    // Apply immediately + persist through yaw system
                    this.setYRot(this.getYRot() + spinAmount);
                    overYawRate = Mth.clamp(spinAmount, -yawMax(), yawMax());
                }

                // ── Crash sound + particles ──────────────────────────
                if (impactSpeed > 0.10) {
                    float vol = (float) Math.min(1.0, impactSpeed * 0.8);
                    if (this.level().isClientSide()) {
                        this.playSound(net.minecraft.sounds.SoundEvents.ANVIL_LAND, vol * 0.5f, 0.75f + (float)(impactSpeed * 0.2));
                        this.playSound(SoundFactory.ENTITY_VEHICLE_HEAVY_CRASH, vol * 0.5f, 0.75f + (float)(impactSpeed * 0.2));
                    }

                    //this.playSound(net.minecraft.sounds.SoundEvents.ANVIL_LAND, vol * 0.5f, 0.75f + (float)(impactSpeed * 0.2));
                }
                if (this.level().isClientSide() && impactSpeed > 0.20) {
                    int count = (int) Math.min(15, impactSpeed * 8);
                    float vol = (float) Math.min(1.0, impactSpeed * 0.8);
                    //this.playSound(SoundFactory.ENTITY_VEHICLE_HEAVY_CRASH, vol * 0.5f, 0.75f + (float)(impactSpeed * 0.2));
                     float pitch = 0.75f + (float)(impactSpeed * 0.2);

                    this.level().playLocalSound(
                        this.getX(),
                        this.getY(),
                        this.getZ(),
                        SoundFactory.ENTITY_VEHICLE_HEAVY_CRASH,
                        net.minecraft.sounds.SoundSource.PLAYERS,
                        vol * 2.5f,
                        pitch,
                        false
                    );
                        for (int i = 0; i < count; i++) {
                        this.level().addParticle(
                            net.minecraft.core.particles.ParticleTypes.CRIT,
                            this.getX() + (this.random.nextDouble() - 0.5) * 1.5,
                            this.getY() + 0.2 + this.random.nextDouble() * 0.6,
                            this.getZ() + (this.random.nextDouble() - 0.5) * 1.5,
                            (this.random.nextDouble() - 0.5) * 0.4,
                            this.random.nextDouble() * 0.15,
                            (this.random.nextDouble() - 0.5) * 0.4
                        );
                    }
                }
            }

            if (carCollisionEnabled) tickCarCollision();

            if (this.onGround() && this.getDeltaMovement().y < 0)
                this.setDeltaMovement(this.getDeltaMovement().x, 0.0, this.getDeltaMovement().z);

            this.applyEffectsFromBlocks();

            // Send physics state to server → server writes to entityData
            // → entityData syncs to ALL other clients (sounds, particles, etc.)
            sendPhysicsPacket();
        }

        // ── NON-DRIVER CLIENTS: smooth interpolation ─────────────
        // Use Minecraft's InterpolationHandler for ALL non-driver clients.
        // Server sends correct position/yaw via entity tracking.
        // The handler smoothly lerps toward each update — no prediction
        // drift, no position accumulation errors.
        if (isClient && !this.isLocalInstanceAuthoritative()) {
            if (carInterpolation != null) {
                carInterpolation.interpolate();
            }
        }

        // ── ALL CLIENTS: particles + exhaust ─────────────────────────
        if (isClient) {
            spawnWheelParticles();
            spawnExhaust();
        }

    }
 
    // ═══════════════════════════════════════════════════════════
    //  PHYSICS ENGINE
    // ═══════════════════════════════════════════════════════════
 
    private void tickPhysics(boolean forward, boolean backward,
                              boolean left,    boolean right, boolean handbrake) {
 
        // Only the server writes to entityData. On integrated server,
        // both client and server ticks run on the same entity — if both
        // write entityData, the server's delayed values overwrite the
        // client's correct values every sync cycle, causing oscillation
        // (e.g. speed jumping between 14 and 192 km/h).
        boolean sync = !this.level().isClientSide();

        // ── 1. Local velocity ─────────────────────────────────────────────
        // When realisticSpeed is ON, world velocity is scaled up by spdScale.
        // Divide it back down here so all internal physics runs at the
        // original tuned speed. Multiply back up at Step 13 (reproject).
        float  spdScale = realisticSpeed ? realisticSpeedScale() : 1.0f;
        double yRad  = Math.toRadians(this.getYRot());
        double sinY  = Math.sin(yRad), cosY = Math.cos(yRad);
        Vec3   vel   = this.getDeltaMovement();
 
        double localFwd  = (-vel.x * sinY + vel.z * cosY) / spdScale;
        double localY    =  vel.y;
        float  speed     = (float) Math.abs(localFwd);
        boolean goingFwd = localFwd >  0.01;
        boolean goingRev = localFwd < -0.01;
 
        // ── 2. Input flags ────────────────────────────────────────────────
        boolean throttleActive = (forward  && !goingRev) || (backward && !goingFwd);
        boolean brakingActive  = (backward && goingFwd)  || (forward  && goingRev);
        //boolean burnout = forward && backward && !handbrake && speed < 0.30f;
        boolean burnout = forward && backward && !handbrake && speed < 0.30f;

        // ── 2b. Steering input ─────────────────────────────────────────────
        float steerInput;

        if (forzaTurning) {
            // Forza-style: keys are binary (0/1) but steering ramps smoothly.
            // Tap A briefly = small nudge. Hold A = builds to full lock.
            // Release = re-centers faster than it turns in.
            // Speed-sensitive: high speed slows turn-in rate.
            // Drift boost: counter-steer is more responsive while sliding.
            float steerTarget = left ? -1f : right ? 1f : 0f;

            //float turnInBase    = 0.12f; // base turn-in rate (~8 ticks to full lock)
            //float recenterRate  = 0.22f; // re-center rate  (~5 ticks back to zero)

            float turnInBase    = 0.20f; // base turn-in rate (~8 ticks to full lock)
            float recenterRate  = 0.30f; // re-center rate  (~5 ticks back to zero)

            // Slow turn-in at high speed: 100% at 0 speed → 55% at 0.6+ bl/tick
            //float speedFactor = 1.0f - Mth.clamp(speed / 0.6f, 0f, 1f) * 0.45f; - GENERAL

            float speedRef, minSens;
            if (perCarSteering) {
                speedRef = steerSpeedReference();
                minSens  = steerMinSensitivity();
            } else {
                speedRef = 0.6f;
                minSens  = 0.55f;
            }
            float speedFactor = 1.0f - Mth.clamp(speed / speedRef, 0f, 1f) * (1.0f - minSens);

            float turnInRate  = turnInBase * speedFactor;

            // Boost turn-in during drift for counter-steer responsiveness
            boolean wasDrifting = Math.abs(rearLat) > driftThreshold();
            if (wasDrifting) turnInRate *= 1.5f;

            float steerRate = (steerTarget != 0f) ? turnInRate : recenterRate;
            steerSmooth += (steerTarget - steerSmooth) * steerRate;
            if (Math.abs(steerSmooth) < 0.01f) steerSmooth = 0f;

            steerInput = steerSmooth;
        } else {
            // Raw digital: A/D instantly sets ±1 (original behaviour)
            steerInput = left ? -1f : right ? 1f : 0f;
            steerSmooth = steerInput; // keep in sync so switching modes is seamless
        }

        if (sync) this.entityData.set(DATA_THROTTLE,    throttleActive || burnout);
        if (sync) this.entityData.set(DATA_BRAKING,     brakingActive);
        if (sync) this.entityData.set(DATA_BURNOUT,     burnout);
        
        this.localThrottle = throttleActive || burnout;
        this.localBraking  = brakingActive;
        this.localBurnout  = burnout;
        if (sync) this.entityData.set(DATA_STEER_INPUT, steerInput);
 
        // ── 3. Smooth throttle ────────────────────────────────────────────
        float throttleTarget = throttleActive ? 1.0f : 0.0f;
        float ramp = throttleTarget > throttleSmooth ? throttleRampOn() : throttleRampOff();
        throttleSmooth = Mth.clamp(
            throttleSmooth + (throttleTarget - throttleSmooth) * ramp, 0f, 1f);
 
        // ── 3b. Handbrake engagement ramp ─────────────────────────────────
        // Ramps 0→1 over ~7 ticks on press, releases slightly faster.
        // Prevents the rear grip from dropping instantly — snapping to a
        // 90–180° spin on first press — giving the driver time to steer into the slide.
        /*handbrakeSmooth = handbrake
            ? Math.min(1f, handbrakeSmooth + 0.20f)
            : Math.max(0f, handbrakeSmooth - 0.25f);*/

        handbrakeSmooth = handbrake
            ? Math.min(1f, handbrakeSmooth + 0.18f)
            : Math.max(0f, handbrakeSmooth - 0.25f);

        float steerMagnitude = Math.abs(steerInput);
        float handbrakeGripFactor;
        if (handbrakeSmooth > 0.01f) {
            // Straight: 35% rear grip → holds briefly, then goes unstable
            // Turning:  full lock → immediate drift
            float straightGrip = 0.28f; //0.35f; ----------------------------------------------------------------------------------------------------------------------------
            float driftGrip    = handbrakeRearGrip();
            float targetGrip   = Mth.lerp(steerMagnitude, straightGrip, driftGrip);
            handbrakeGripFactor = Mth.lerp(handbrakeSmooth, 1.0f, targetGrip);
        } else {
            handbrakeGripFactor = 1.0f;
        }
 
        // ── 4. Drivetrain chain - Manuel Update ────────────────────────────

        final float[] GR = cachedGearRatios;
 
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
 
            } else if (manualTransmission) {
                // ── MANUAL TRANSMISSION ──────────────────────────────
                // Player controls shifting via R (up) and F (down).
                // Rev limiter at redline: power cut, RPM bounces.
                // Over-rev protection on downshift.
 
                // Process shift requests (edge-triggered from keybinds)
                if (shiftUpRequested && currentGear < maxGear() && goingFwd) {
                    currentGear++;
                    clutchTimer = clutchTicks();
                    rawRPM = wheelRPS * GR[currentGear] * finalDrive() * 60f;
                }
                if (shiftDownRequested && currentGear > 1) {
                    // Over-rev protection: check if downshift would exceed redline
                    float projectedRPM = wheelRPS * GR[currentGear - 1] * finalDrive() * 60f;
                    if (projectedRPM <= redlineRpm() * 1.05f) {
                        // Safe downshift
                        currentGear--;
                        clutchTimer = clutchTicks();
                        rawRPM = wheelRPS * GR[currentGear] * finalDrive() * 60f;
                    }
                    // If over-rev: downshift rejected silently
                }
                shiftUpRequested   = false;
                shiftDownRequested = false;
 
                engineRPM = rawRPM;
 
                // Rev limiter: at redline, RPM stays pinned and power is cut
                if (engineRPM >= redlineRpm()) {
                    engineRPM = redlineRpm();
                    revLimiterTicks++;
                } else {
                    revLimiterTicks = 0;
                }
 
            } else if (goingFwd) {
                // ── AUTOMATIC TRANSMISSION (original) ────────────────
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
                revLimiterTicks = 0;
 
            } else if (goingRev) {
                float revFraction = Math.min(1f, speed / maxReverseSpeed());
                engineRPM = idleRpm() + revFraction * (maxReverseRpm() - idleRpm());
                revLimiterTicks = 0;
            }
            engineRPM = Math.max(engineRPM, idleRpm() + throttleSmooth * 500f);
            engineRPM = Mth.clamp(engineRPM, idleRpm(), redlineRpm());
        }
 
        // Clear shift requests even if not used (prevents buffering)
        shiftUpRequested   = false;
        shiftDownRequested = false;
 
        if (sync) this.entityData.set(DATA_ENGINE_RPM, engineRPM);
        if (sync) this.entityData.set(DATA_GEAR,       currentGear);



        // ── 4.OLD Drivetrain chain ───────────────────────────────────────────
        /*final float[] GR = cachedGearRatios;
 
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
 
        if (sync) this.entityData.set(DATA_ENGINE_RPM, engineRPM);
        if (sync) this.entityData.set(DATA_GEAR,       currentGear);*/
 
        // ── 5. Drive force ────────────────────────────────────────────────
        /*float torqueNorm = lookupTorque(engineRPM);
        float gearMult   = GR[currentGear] / GR[1];
        float driveForce = torqueNorm * throttleSmooth * peakDriveForce() * gearMult;
        if (clutchTimer > 0) driveForce = 0f;*/

        float torqueNorm = lookupTorque(engineRPM);
        float gearMult   = GR[currentGear] / GR[1];
        float driveForce = torqueNorm * throttleSmooth * peakDriveForce() * gearMult;

        //if (hasFuel()) driveForce *= 1.10f; // +10% power with fuel
        if (!hasFuel()) driveForce *= noFuelPowerFactor();




        
        if (clutchTimer > 0) driveForce = 0f;
 
        // Rev limiter power cut: in manual mode, hitting redline kills power.
        // This makes the car stop accelerating until the player shifts up.
        if (manualTransmission && revLimiterTicks > 0) {
            //driveForce *= 0.005f;//0.15f; // 85% power cut — car barely accelerates
            driveForce  = 0f;
            
        }

 
        // ── 5b. Surface friction ──────────────────────────────────────────
        // Raw friction: 1.0 = asphalt, 0.10 = blue ice, 0.72 = gravel.
        // surfacePenaltyScale amplifies the penalty for slick tyres:
        //   penalty = (1 - raw) * scale, clamped so friction >= 0.05.
        //   On asphalt (raw=1.0) the scale has zero effect.
        float rawFriction     = surfaceFrictionEnabled ? computeSurfaceFriction() : 1.0f;
        float penalty         = (1.0f - rawFriction) * surfacePenaltyScale();
        float surfaceFriction = Math.max(0.05f, 1.0f - penalty);
        //float adjustedRearGripMax  = rearGripMax()  * surfaceFriction;
        //float adjustedFrontGripMax = frontGripMax() * surfaceFriction;

        float adjustedRearGripMax  = rearGripMax()  * surfaceFriction * getRearWheelGrip();
        float adjustedFrontGripMax = frontGripMax() * surfaceFriction * getFrontWheelGrip();


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
 
        /*float rearGripLat;
        {
            float normalRearGripLat;
            if (isRWD) {
                normalRearGripLat = (float) Math.sqrt(Math.max(0.0,
                    (double) rearGripTotal * rearGripTotal - (double) driveUsedRear * driveUsedRear));
                normalRearGripLat = Math.max(normalRearGripLat, rearGripTotal * 0.40f);
            } else {
                normalRearGripLat = rearGripTotal;
            }
            rearGripLat = normalRearGripLat * handbrakeGripFactor;
        }*/

        float rearGripLat;
        {
            float normalRearGripLat;
            if (isRWD) {
                normalRearGripLat = (float) Math.sqrt(Math.max(0.0,
                    (double) rearGripTotal * rearGripTotal - (double) driveUsedRear * driveUsedRear));
                normalRearGripLat = Math.max(normalRearGripLat, rearGripTotal * 0.40f);
            } else {
                normalRearGripLat = rearGripTotal;
            }
            rearGripLat = normalRearGripLat * handbrakeGripFactor;
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
        // Brake/handbrake decel scales with surface grip — ice is nearly
        // impossible to stop on, gravel is noticeably worse than asphalt.
        // Floor at 0.25 so even blue ice has some braking (engine braking helps too).
        float brakeFriction = Math.max(surfaceFriction, 0.25f);
 
        if (burnout) {
            localFwd *= 0.60;
            if (Math.abs(localFwd) < 0.005) localFwd = 0.0;
        } else if (throttleActive && !handbrake) {
            if (forward) {
                double cap = goingRev ? 0.10 : (isRWD ? rearGripTotal : frontGripTotal);
                localFwd = Math.min(localFwd + Math.min(driveForce, cap), 75.0);
            } else {
                // Reverse accel scales with peakDriveForce — heavy/powerful
                // cars push backward faster than economy cars.
                float reverseAccel = peakDriveForce() * 0.04f;
                localFwd = Math.max(localFwd - reverseAccel, -maxReverseSpeed());
            }
        }
 
        if (!burnout && brakingActive) {
            float brakeDecel = 0.035f * brakeFriction;
            if (goingFwd)      localFwd = Math.max(0.0, localFwd - brakeDecel);
            else if (goingRev) localFwd = Math.min(0.0, localFwd + brakeDecel);
        }
 
        if (!forward && !backward && !handbrake) {
            double eb = 0.004 * (GR[currentGear] / GR[maxGear()]);
            if (goingFwd)      localFwd -= eb;
            else if (goingRev) localFwd += eb;
        }
 
        if (handbrake) {
            // Lighter than before — momentum carries the slide.
            float brakeStrength = Mth.lerp(steerMagnitude, 0.055f, 0.015f);
            float handbrakeDecel = brakeStrength * brakeFriction;
            if (goingFwd)      localFwd = Math.max(0.0, localFwd - handbrakeDecel);
            else if (goingRev) localFwd = Math.min(0.0, localFwd + handbrakeDecel);
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
        // Fires for both forward and reverse — without this, reversing around
        // a corner has zero lateral physics (car pivots on a pin).
        // dirSign flips the centripetal direction for reverse geometry.
        if (speed > 0.01f && steerInput != 0f) {
            float steerRad       = (float) Math.toRadians(computeSteerAngle(speed, drifting));
            float dirSign        = goingRev ? -1f : 1f;
            float centripetalLat = (float)(Math.sin(steerRad) * Math.abs(localFwd)) * steerInput * dirSign;
            frontLat += centripetalLat * (rearDist()  / (frontDist() + rearDist()));
            rearLat  += centripetalLat * (frontDist() / (frontDist() + rearDist()));
        }

        if (handbrake && handbrakeSmooth > 0.4f && speed > 0.05f) {
            handbrakeHoldTicks++;
            float buildup = Math.min(1.0f, handbrakeHoldTicks / 25f);

            // Direction priority:
            //  1. If steering — push rear out the way you're turning (drift assist)
            //  2. Else if already rotating — follow that rotation
            //  3. Else (straight) — default bias so it eventually spins out
            float instabilityDir;
            if (Math.abs(steerInput) > 0.05f) {
                instabilityDir = Math.signum(steerInput);
            } else if (Math.abs(overYawRate) > 0.005f) {
                instabilityDir = Math.signum(overYawRate);
            } else {
                instabilityDir = 1f;
            }

            rearLat += instabilityDir * speed * (0.015f + buildup * 0.060f);
        } else {
            handbrakeHoldTicks = 0;
        }
 
        float frontCorrect = computeGripForce(frontLat, frontGripLat);
        frontLat -= frontCorrect;
 
        //float rearCorrect = computeGripForce(rearLat, rearGripLat);
        //rearLat -= rearCorrect;

        float rearCorrect = computeGripForce(rearLat, rearGripLat);
        rearLat -= rearCorrect;
 
        // Coast decay: prevents passive spinning from centripetal injection alone.
        float coastDecay = (handbrakeSmooth < 0.05f && throttleSmooth < 0.15f)
            ? 0.50f       // coasting: bleed off fast
            : latDecay(); // under power or handbrake: normal
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
        } else if (Math.abs(overYawRate) > yawMax() * 0.3f) {
            // High overYawRate from a crash — don't kill it with 0.55×.
            // Just let normal yawDamping handle the decay naturally.
            // This gives crash spin-outs time to play out visually.
        } else {
            overYawRate *= 0.55f;
        }
        overYawRate *= yawDamping();
        overYawRate  = Mth.clamp(overYawRate, -yawMax(), yawMax());
        this.setYRot(this.getYRot() + overYawRate);
 
        //if (sync) this.entityData.set(DATA_DRIFTING, drifting || burnout || Math.abs(overYawRate) > 1.2f);
        //this.localDrifting = drifting || burnout || Math.abs(overYawRate) > 1.2f;

        boolean handbrakeSmoking = handbrake && speed > 0.05f;
        if (sync) this.entityData.set(DATA_DRIFTING, drifting || burnout || handbrakeSmoking || Math.abs(overYawRate) > 1.2f);
        this.localDrifting = drifting || burnout || handbrakeSmoking || Math.abs(overYawRate) > 1.2f;
 
        // ── 12. Gravity ───────────────────────────────────────────────────
        localY = this.onGround() ? GROUND_STICK : Math.max(-MAX_FALL_SPEED, localY - GRAVITY);
 
        // ── 13. Reproject to world ────────────────────────────────────────
        // Multiply by spdScale so world movement matches realistic speed.
        // Internal physics ran at original scale; this is the only place
        // the scale affects actual movement.
        double nYRad = Math.toRadians(this.getYRot());
        double nSin  = Math.sin(nYRad), nCos = Math.cos(nYRad);
        this.setDeltaMovement(
            (localFwd * (-nSin) + overallLat * nCos) * spdScale,
            localY,
            (localFwd *   nCos  + overallLat * nSin) * spdScale);
 
        // ── 14. Wheel spin cosmetics ──────────────────────────────────────
        // RWD burnout: rear wheels spin fast, front wheels idle (speed-based).
        // FWD burnout: front wheels spin fast, rear wheels idle (speed-based).
        // Wheel spin uses world speed (speed * spdScale) so the visual
        // rotation matches how fast the car appears to move.
        float worldSpeed = speed * spdScale;
        float burnoutDeg = burnout ? (engineRPM / redlineRpm()) * 90f : 0f;
 
        if (burnout && !isRWD) {
            // FWD burnout — front axle gets RPM spin
            wheelSpin += burnoutDeg;
        } else {
            wheelSpin += worldSpeed * 180f;
        }
        if (wheelSpin > 360000f) wheelSpin -= 360000f;
        if (sync) this.entityData.set(DATA_WHEEL_SPIN, wheelSpin);
 
        if (burnout && isRWD) {
            // RWD burnout — rear axle gets RPM spin
            rearWheelSpin += burnoutDeg;
        } else if (speed > 0.005f) {
            rearWheelSpin += worldSpeed * 180f;
        }
        if (rearWheelSpin > 360000f) rearWheelSpin -= 360000f;
        if (sync) this.entityData.set(DATA_REAR_WHEEL_SPIN, rearWheelSpin);
        if (sync) this.entityData.set(DATA_FORWARD_SPEED, (float) localFwd);
        this.localSpeed = (float) localFwd; // always store for client HUD

        float rpmPct = Mth.clamp((engineRPM - idleRpm()) / (redlineRpm() - idleRpm()), 0f, 1f);
        int revLightState;
        if      (rpmPct > 0.93f) revLightState = 4; // all lit — SHIFT NOW
        else if (rpmPct > 0.85f) revLightState = 3; // red + orange + yellow
        else if (rpmPct > 0.75f) revLightState = 2; // red + orange
        else if (rpmPct > 0.60f) revLightState = 1; // red only
        else                     revLightState = 0; // all off
 
        if (sync) this.entityData.set(DATA_REV_LIGHT_STATE, revLightState);
 
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
 
    @SuppressWarnings("deprecation")
    private boolean detectTunnel() {
        if (!this.onGround()) return false;
        BlockPos base = BlockPos.containing(this.getX(), this.getY() + 0.5, this.getZ());
        for (int i = 1; i <= TUNNEL_SCAN_HEIGHT; i++) {
            if (this.level().getBlockState(base.above(i)).isSolid()) return true;
        }
        return false;
    }
 
    @SuppressWarnings("deprecation")
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
        float[] rpmPts = cachedTorqueRpmPoints;
        float[] trqPts = cachedTorqueCurve;
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
        double peak = peakSteerSpeed();
        if (peak < 0.001) peak = 0.001; // guard against zero
        if (speed <= peak) {
            steer = maxSteerDeg() * (float)(speed / peak);
        } else {
            double denom = 1.0 - peak;
            if (denom < 0.001) denom = 0.001; // guard against peakSteerSpeed ≈ 1.0
            double t = Mth.clamp((speed - peak) / denom, 0.0, 1.0);
            steer = maxSteerDeg() * (float)(1.0 - t * (1.0 - highSteerFraction()));
        }
        return drifting ? steer * driftSteerBoost() : steer;
    }

        // ═══════════════════════════════════════════════════════════
    //  CAR SYSTEMS  (fuel, wheels, radio)
    // ═══════════════════════════════════════════════════════════
 
    /** Server-side: ticks fuel consumption and wheel wear while driving. */
    private void tickCarSystems() {
        float speed = Math.abs(this.getForwardSpeed());
 
        // ── Fuel consumption ─────────────────────────────────────
        // Fuel canister loses 1 durability every 100 ticks (~5 sec) while moving.
        // Faster speed doesn't burn more — just moving at all consumes fuel.
        // No fuel = no penalty (fuel is optional performance boost, not required).
        if (speed > 0.05f) {
            fuelTickCounter++;
            if (fuelTickCounter >= 100) {
                fuelTickCounter = 0;
                ItemStack fuel = carInventory.getItem(1); // slot 1 = fuel
                if (!fuel.isEmpty() && fuel.isDamageableItem()) {
                    fuel.setDamageValue(fuel.getDamageValue() + 1);
                    if (fuel.getDamageValue() >= fuel.getMaxDamage()) {
                        carInventory.setItem(1, ItemStack.EMPTY);
                    }
                }
            }
        }
 
        // ── Wheel wear ───────────────────────────────────────────
        // Each wheel loses 1 durability every 200 ticks (~10 sec) while moving.
        // Drifting wears wheels 3× faster.
        if (speed > 0.05f && this.tickCount % 200 == 0) {
            int wearAmount = this.isDrifting() ? 3 : 1;
            for (int slot = 4; slot <= 7; slot++) { // slots 4-7 = wheels
                ItemStack wheel = carInventory.getItem(slot);
                if (!wheel.isEmpty() && wheel.isDamageableItem()) {
                    wheel.setDamageValue(wheel.getDamageValue() + wearAmount);
                    if (wheel.getDamageValue() >= wheel.getMaxDamage()) {
                        carInventory.setItem(slot, ItemStack.EMPTY);
                    }
                }
            }
        }
    }
 
    /**
     * Returns a grip multiplier (0.0–1.0) based on wheel condition.
     * Called by tickPhysics to scale front/rear grip.
     *
     * - All 4 wheels present at full durability: 1.0 (100% grip)
     * - Wheels at 25% durability: ~0.75 (traction degrades rapidly below 25%)
     * - Missing wheels: that axle gets 0.3 grip (barely driveable)
     * - No wheels at all: 0.3 (can still limp to pit stop)
     *
     * @param front true for front axle (slots 4-5), false for rear (slots 6-7)
     */

    /*public float getWheelGripMultiplier(boolean front) {
        int slot1 = front ? 4 : 6;
        int slot2 = front ? 5 : 7;
        float avg = (wheelCondition(slot1) + wheelCondition(slot2)) / 2f;
        return Math.max(0.3f, avg);
    }*/

    private float computeAxleGrip(boolean front) {
        int slot1 = front ? 4 : 6;
        int slot2 = front ? 5 : 7;
        float avg = (wheelCondition(slot1) + wheelCondition(slot2)) / 2f;
        return Math.max(0.10f, avg);
    }

    private void syncCarSystemState() {
        if (this.level().isClientSide()) return;
        this.entityData.set(DATA_HAS_FUEL,   computeHasFuel());
        this.entityData.set(DATA_FRONT_GRIP, computeAxleGrip(true));
        this.entityData.set(DATA_REAR_GRIP,  computeAxleGrip(false));
        this.entityData.set(DATA_FUEL_LEVEL, computeFuelLevel());
        this.entityData.set(DATA_WHEEL_PCTS, packWheelPcts(
            computeWheelPct(4), computeWheelPct(5), computeWheelPct(6), computeWheelPct(7)));
    }

    public float getFuelLevel() { return this.entityData.get(DATA_FUEL_LEVEL); }

    public int getWheelPct(int corner) {
        int p = this.entityData.get(DATA_WHEEL_PCTS);
        return switch (corner) {
            case 0 -> (p >> 24) & 0xFF;
            case 1 -> (p >> 16) & 0xFF;
            case 2 -> (p >> 8)  & 0xFF;
            default ->  p        & 0xFF;
        };
    }


 
    /** Returns 0.0–1.0 condition for a wheel slot. Empty = 0.3 (limping). */
    /*private float wheelCondition(int slot) {
        ItemStack wheel = carInventory.getItem(slot);
        if (wheel.isEmpty() || !wheel.isDamageableItem()) return 0.3f;
        float pct = 1f - (float) wheel.getDamageValue() / wheel.getMaxDamage();
        // Full grip until 25% durability, then rapid falloff
        if (pct > 0.25f) return 1.0f;
        return 0.3f + (pct / 0.25f) * 0.7f; // 0.3 at 0%, 1.0 at 25%
    }*/

    private float wheelCondition(int slot) {
        ItemStack wheel = carInventory.getItem(slot);
        if (wheel.isEmpty() || !wheel.isDamageableItem()) return 0.10f; // no tire
        float pct = 1f - (float) wheel.getDamageValue() / wheel.getMaxDamage();
        if (pct > 0.25f) return 1.0f;                  // healthy
        return 0.30f + (pct / 0.25f) * 0.70f;          // worn: grip falls off under 25%
    }

 
    /**
     * Returns true if fuel is present (for optional performance boost).
     * Cars drive without fuel, but having it gives +10% peakDriveForce.
     */
    /*public boolean hasFuel() {
        ItemStack fuel = carInventory.getItem(1);
        return !fuel.isEmpty() && fuel.isDamageableItem()
            && fuel.getDamageValue() < fuel.getMaxDamage();
    }*/

    private boolean computeHasFuel() {
        ItemStack fuel = carInventory.getItem(1);
        return !fuel.isEmpty() && fuel.isDamageableItem()
            && fuel.getDamageValue() < fuel.getMaxDamage();
    }

 
    /** Returns the license plate item, or EMPTY if none. */
    public ItemStack getLicensePlate() {
        return carInventory.getItem(0); // slot 0 = plate
    }

    public void setLicensePlate(ItemStack stack) {
        carInventory.setItem(0, stack);

        if (!level().isClientSide()) {
            entityData.set(LICENSE_PLATE, stack.copy());
        }
    }

    public void setRadioMusic(ItemStack stack) {
        carInventory.setItem(3, stack);

        if (!level().isClientSide()) {
            entityData.set(RADIO_DISC, stack.copy());
        }
    }

    public ItemStack getSyncedLicensePlate() {
        return entityData.get(LICENSE_PLATE);
    }

    public ItemStack getSyncedRadioDisc() {
        return entityData.get(RADIO_DISC);
    }

    public Vec3 licensePlateOffset() { return new Vec3(0, 0.25, 3.35); }
    //public Vec3 licensePlateOffset() { return new Vec3(0, 0.72, 3); }

 
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
            // Normal driving: block dust from all four wheels.
            // Drift smoke: REAR wheels only — they're the ones sliding.
            // (Both RWD oversteer and FWD handbrake slides rotate the rear.)
            spawnWheelAt(fax + lox, 0.05, faz + loz, false,    rearX, rearZ); // front-left: dust only
            spawnWheelAt(fax - lox, 0.05, faz - loz, false,    rearX, rearZ); // front-right: dust only
            spawnWheelAt(rax + lox, 0.05, raz + loz, drifting, rearX, rearZ); // rear-left: dust + drift smoke
            spawnWheelAt(rax - lox, 0.05, raz - loz, drifting, rearX, rearZ); // rear-right: dust + drift smoke
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

    private void tickTireAnim() {
        if (this.level().isClientSide()) return;
        for (int c = 0; c < 4; c++) {
            int ticks = this.entityData.get(tireTicksAccessor(c));
            if (ticks > 0) this.entityData.set(tireTicksAccessor(c), ticks - 1);
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

 
    // ═══════════════════════════════════════════════════════════
    //  INTERACTION
    // ═══════════════════════════════════════════════════════════

    @SuppressWarnings("unused")
    private boolean inventoryOpen = false;
 
        @Override
    public InteractionResult interact(Player player, InteractionHand hand) {
        // ── Rotated hitbox check ─────────────────────────────────
        // The AABB is square and oversized. Before processing the
        // interaction, verify the player is actually looking at the
        // car's real shape (rotated rectangle), not empty air beside it.
        Vec3 eyePos = player.getEyePosition();
        Vec3 lookDir = player.getLookAngle();
        double reach = 4.5; // survival reach distance
 
        Vec3 hit = raycastCarShape(eyePos, lookDir, reach);
        if (hit == null) {
            return InteractionResult.PASS; // missed the actual car shape
        }
 
        // ── Shift-click: open GUI + hood ─────────────────────────
        if (player.isSecondaryUseActive()) {
            if (!this.level().isClientSide() && player instanceof ServerPlayer sp) {
                if (hasHood()) {
                    this.entityData.set(DATA_HOOD_OPEN, true);
                    this.level().playSound(null, this.blockPosition(), SoundFactory.ENTITY_VEHICLE_HOOD_OPEN, SoundSource.PLAYERS, 1.0f, 1.0f);
                }
 
                final BaseCarEntity self = this;
                sp.openMenu(new MenuProvider() {
                    @Override
                    public Component getDisplayName() {
                        return Component.literal("Car Customization");
                    }
 
                    @Override
                    public AbstractContainerMenu createMenu(
                            int id,
                            net.minecraft.world.entity.player.Inventory inv,
                            Player p) {
                        return new net.ent.entstupidstuff.api.car.menu.CarMenu(id, inv, self);
                    }
                });
            }
            return InteractionResult.SUCCESS;
        }
 
        // ── Normal click: ride ───────────────────────────────────
        if (!this.level().isClientSide()) {
            player.startRiding(this);
        }
        return InteractionResult.SUCCESS;
    }


    /** Called by CarMenu when the GUI is closed. Closes the hood. */
    public void closeHood() {
        if (!this.level().isClientSide() && hasHood()) {
            this.entityData.set(DATA_HOOD_OPEN, false);
            this.level().playSound(null, this.blockPosition(), SoundFactory.ENTITY_VEHICLE_HOOD_CLOSE, SoundSource.PLAYERS, 1.0f, 1.0f);
        }
    }


 
    @Override public boolean isPickable() { return !this.isRemoved(); }
    @Override public boolean isPushable()  { return false; }
 
    // ═══════════════════════════════════════════════════════════
    //  NETWORK: physics state sync (C2S packet)
    // ═══════════════════════════════════════════════════════════

    /**
     * Server-side: called by the packet handler when the driver's client
     * sends a CarPhysicsPayload. Writes all physics values to entityData,
     * which auto-syncs to ALL other clients.
     */
    public void applyPhysicsPacket(CarPhysicsPayload p) {
        this.entityData.set(DATA_FORWARD_SPEED, p.forwardSpeed());
        this.entityData.set(DATA_ENGINE_RPM,    p.engineRPM());
        this.entityData.set(DATA_GEAR,          p.gear());
        this.entityData.set(DATA_WHEEL_SPIN,    p.wheelSpin());
        this.entityData.set(DATA_REAR_WHEEL_SPIN, p.rearWheelSpin());
        this.entityData.set(DATA_STEER_INPUT,   p.steerInput());
        this.entityData.set(DATA_THROTTLE,      p.throttle());
        this.entityData.set(DATA_BRAKING,       p.braking());
        this.entityData.set(DATA_BURNOUT,       p.burnout());
        this.entityData.set(DATA_DRIFTING,      p.drifting());

        // Rev light state — computed from packet RPM
        float rpmPct = Mth.clamp((p.engineRPM() - idleRpm()) / (redlineRpm() - idleRpm()), 0f, 1f);
        int revState = 0;
        if      (rpmPct > 0.93f) revState = 4;
        else if (rpmPct > 0.85f) revState = 3;
        else if (rpmPct > 0.75f) revState = 2;
        else if (rpmPct > 0.60f) revState = 1;
        this.entityData.set(DATA_REV_LIGHT_STATE, revState);
    }

    /**
     * Client-side: sends the driver's exact physics state to the server.
     * Called every tick from the driver's client after tickPhysics().
     */
    private void sendPhysicsPacket() {
        net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking.send(
            new CarPhysicsPayload(
                this.getId(),
                this.localSpeed,
                this.engineRPM,
                this.currentGear,
                this.wheelSpin,
                this.rearWheelSpin,
                this.steerSmooth,
                this.localThrottle,
                this.localBraking,
                this.localBurnout,
                this.localDrifting
            )
        );
    }

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
    public int getRevLightState()       { return this.entityData.get(DATA_REV_LIGHT_STATE); }
    public boolean isBraking()        { return this.entityData.get(DATA_BRAKING); }
    public float   getEngineRPM()     { return this.entityData.get(DATA_ENGINE_RPM); }
    public boolean isLeftDoorOpen()  { return this.entityData.get(DATA_LEFT_DOOR_OPEN); }
    public boolean isRightDoorOpen() { return this.entityData.get(DATA_RIGHT_DOOR_OPEN); }
    public boolean isHoodOpen()      { return this.entityData.get(DATA_HOOD_OPEN); }
    public boolean isEngineJustStarted() { return this.entityData.get(DATA_ENGINE_JUST_STARTED); }
    public boolean getIsLeftHandDrive() { return isLeftHandDrive(); }

    public boolean hasFuel()            { return this.entityData.get(DATA_HAS_FUEL); }
    public float getFrontWheelGrip()    { return this.entityData.get(DATA_FRONT_GRIP); }
    public float getRearWheelGrip()     { return this.entityData.get(DATA_REAR_GRIP); }

    public int getTireAnimCorner()      { return this.entityData.get(DATA_TIRE_ANIM_CORNER); }
    public float getTireAnimProgress() {
        int ticks = this.entityData.get(DATA_TIRE_ANIM_TICKS);
        return 1f - (ticks / (float) TIRE_ANIM_DURATION); // 0 at start → 1 at end
    }

    public float getTireAnimProgress(int corner) {
        int ticks = this.entityData.get(tireTicksAccessor(corner));
        if (ticks <= 0) return -1f;
        return 1f - (ticks / (float) TIRE_ANIM_DURATION);
    }




 
    public float getRPM() {
        return Mth.clamp((getEngineRPM() - idleRpm()) / (redlineRpm() - idleRpm()), 0f, 1f);
    }

    /** Public accessor for defaultIsRWD() — used by /carconfig driveType reset. */
    public boolean getDefaultIsRWD() { return defaultIsRWD(); }

    /** Public accessor for realisticSpeedScale() — used by /carconfig realisticSpeed. */
    public float getRealisticSpeedScaleValue() { return realisticSpeedScale(); }
 
    // ═══════════════════════════════════════════════════════════
    //  HUD
    // ═══════════════════════════════════════════════════════════
 
    private void displaySpeed(Player player) {
        // Read from local physics state — NOT entityData.
        // entityData is set by the server from observables and may
        // have slight approximation errors. The local state is exact.
        float speed  = this.localSpeed;
        float spdScale = realisticSpeed ? realisticSpeedScale() : 1.0f;
        float kmh    = Math.abs(speed) * 72f * spdScale;
        float rpm    = this.engineRPM;
        int   gear   = this.currentGear;
        boolean moving = Math.abs(speed) > 0.04f;
 
        String shiftLabel = speed < -0.04f ? "§cR" : moving ? "§aD" : "§7P";
        String driveType  = isRWD ? "§bRWD" : "§eFWD";
        String spdMode    = realisticSpeed ? " §c[REAL]" : "";
        String steerMode  = forzaTurning   ? " §b[FORZA]" : "";

        @SuppressWarnings("unused")
        String transMode = manualTransmission ? " §d[MAN]" : "";
        String revLimiter = (manualTransmission && revLimiterTicks > 0) ? " §c§lSHIFT!" : "";
        @SuppressWarnings("unused")
        String steerSens = perCarSteering ? " §b[PCS]" : "";
 
        if (advancedDebug) {
            // ── Advanced debug: full physics snapshot ─────────────────────────
            // Line 1 (action bar) — always visible while driving
            player.displayClientMessage(Component.literal(String.format(
                "§e[ADV] %s §f%.0f km/h%s%s  §cRPM:§f%.0f  §e%s[%d]  §7surf:§f%.2f",
                driveType, kmh, spdMode, steerMode, rpm, shiftLabel, gear, dbgSurfaceFriction)), true);
 
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
            final float[] GR   = cachedGearRatios;
            final float   FD   = finalDrive();
            final float   TC   = tyreCirc();
            final float   PK   = peakDriveForce();
            final float[] TRPM = cachedTorqueRpmPoints;
            final float[] TTRQ = cachedTorqueCurve;
 
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
            float vRedline   = (redlineRpm()   / (GR[gear>0?gear:1]*FD*60f))*TC/20f*72f * spdScale;
            float vDownshift = (downshiftRpm() / (GR[gear>0?gear:1]*FD*60f))*TC/20f*72f * spdScale;
 
            player.displayClientMessage(Component.literal(String.format(
                "§e[DBG] §aSpd:§f%.1f km/h%s%s  §cRPM:§f%.0f  §e%s[%d]  §bNet:§f%+.5f",
                kmh, spdMode, steerMode, rpm, shiftLabel, gear, net)), true);
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
                driveType + "  §f" + String.format("%.0f km/h", kmh) + spdMode + steerMode + "  " + state), true);
 
        } else {
            // ── Normal HUD ────────────────────────────────────────────────────
            /*player.displayClientMessage(Component.literal(String.format(
                "§aSpeed: §f%.0f km/h%s%s  §7|  §cRPM: §f%.0f  §7|  §e%s §8[%d]",
                kmh, spdMode, steerMode, rpm, shiftLabel, gear)), true);*/

            /*player.displayClientMessage(Component.literal(String.format(
            "§aSpeed: §f%.0f km/h%s%s%s  §7|  §cRPM: §f%.0f  §7|  §e%s §8[%d]%s",
            kmh, spdMode, steerMode, transMode, rpm, shiftLabel, gear, revLimiter)), true);*/

            // Compact version — combine all toggles into one bracket
            /*String modes = (realisticSpeed ? "R" : "") 
                        + (forzaTurning ? "F" : "") 
                        + (manualTransmission ? "M" : "") 
                        + (perCarSteering ? "S" : "");
            String modeTag = modes.isEmpty() ? "" : " §8[§b" + modes + "§8]";

            player.displayClientMessage(Component.literal(String.format(
                "§aSpeed: §f%.0f km/h%s  §7|  §cRPM: §f%.0f  §7|  §e%s §8[%d]%s",
                kmh, modeTag, rpm, shiftLabel, gear, revLimiter)), true);*/

            String modes = (realisticSpeed ? "R" : "")
                        + (forzaTurning ? "F" : "")
                        + (manualTransmission ? "M" : "")
                        + (perCarSteering ? "S" : "");
            String modeTag = modes.isEmpty() ? "" : " §8[§b" + modes + "§8]";

            int fuelPct = Math.round(getFuelLevel() * 100f);
            String fuelCol = fuelPct > 50 ? "§a" : fuelPct > 20 ? "§e" : "§c";

            String tires =
                  wheelTag(getWheelPct(0)) + "§7/" + wheelTag(getWheelPct(1)) + "§7/"
                + wheelTag(getWheelPct(2)) + "§7/" + wheelTag(getWheelPct(3));

            player.displayClientMessage(Component.literal(String.format(
                "§aSpeed: §f%.0f km/h%s  §7|  §cRPM: §f%.0f  §7|  §e%s §8[%d]%s  §7|  §bFuel %s%d%%  §7|  §6T %s",
                kmh, modeTag, rpm, shiftLabel, gear, revLimiter, fuelCol, fuelPct, tires)), true);
 

        }
    }

    private static String wheelTag(int pct) {
        if (pct <= 0) return "§8X";                       // no tire fitted
        String c = pct > 50 ? "§a" : pct > 20 ? "§e" : "§c";
        return c + pct;
    }
 
    // ═══════════════════════════════════════════════════════════
    //  NBT
    // ═══════════════════════════════════════════════════════════
 
    @Override protected void readAdditionalSaveData(ValueInput in) {
        for (int i = 0; i < 8; i++) {
            final int slot = i;
            in.read("CarSlot" + i, ItemStack.CODEC).ifPresent(stack ->
                carInventory.setItem(slot, stack));
        }
        in.read("wrap", Codec.STRING).ifPresent(w -> this.entityData.set(DATA_WRAP, w));
        in.read("bodykit", Codec.STRING).ifPresent(k -> this.entityData.set(DATA_BODYKIT, k));
    }

    @Override protected void addAdditionalSaveData(ValueOutput out) {
        for (int i = 0; i < 8; i++) {
            ItemStack stack = carInventory.getItem(i);
            if (!stack.isEmpty()) {
                out.store("CarSlot" + i, ItemStack.CODEC, stack);
            }
        }
        out.store("wrap", Codec.STRING, this.entityData.get(DATA_WRAP));
        out.store("bodykit", Codec.STRING, this.entityData.get(DATA_BODYKIT));
    }

    @Override
    public boolean canBeCollidedWith(@Nullable Entity entity) {
		return false;
	}

    private void playDoorSound(net.minecraft.sounds.SoundEvent sound) {
        // Server-side broadcast — every nearby client hears it.
        this.level().playSound(null, this.blockPosition(), sound, SoundSource.PLAYERS, 1.0f, 1.0f);
    }

    private void openLeftDoor() {
        leftDoorTimer = 15;
        this.entityData.set(DATA_LEFT_DOOR_OPEN, true);
        playDoorSound(SoundFactory.ENTITY_VEHICLE_CAR_DOOR_OPEN);
    }

    private void openRightDoor() {
        rightDoorTimer = 15;
        this.entityData.set(DATA_RIGHT_DOOR_OPEN, true);
        playDoorSound(SoundFactory.ENTITY_VEHICLE_CAR_DOOR_OPEN);
    }

    private void tickDoorAndHood() {
        int currentPassengers = this.getPassengers().size();
 
        // ── Detect passenger entering ────────────────────────────
        if (currentPassengers > previousPassengerCount) {
            int newIndex = currentPassengers - 1; // 0 = driver, 1 = passenger
            boolean driver = (newIndex == 0);
            if (driver == isLeftHandDrive()) openLeftDoor(); else openRightDoor();
        }
 
        // ── Detect passenger exiting ─────────────────────────────
        if (currentPassengers < previousPassengerCount) {
            boolean passengerLeft = (previousPassengerCount == 2);
            if (passengerLeft == isLeftHandDrive()) openRightDoor(); else openLeftDoor();
        }
 
        previousPassengerCount = currentPassengers;
 
        // ── Door auto-close timers ───────────────────────────────
        if (leftDoorTimer > 0) {
            leftDoorTimer--;
            if (leftDoorTimer == 0) {
                this.entityData.set(DATA_LEFT_DOOR_OPEN, false);
                playDoorSound(SoundFactory.ENTITY_VEHICLE_CAR_DOOR_CLOSE);
            }
        }
        if (rightDoorTimer > 0) {
            rightDoorTimer--;
            if (rightDoorTimer == 0) {
                this.entityData.set(DATA_RIGHT_DOOR_OPEN, false);
                playDoorSound(SoundFactory.ENTITY_VEHICLE_CAR_DOOR_CLOSE);
            }
        }
 
        // ── Engine start tracking ────────────────────────────────
        boolean occupied = currentPassengers > 0;
        if (occupied && !wasOccupied) {
            // Car just became occupied — engine start sound needed.
            // The sound is played client-side by CarSoundManager.
            // We set a flag that the manager reads.
            this.entityData.set(DATA_ENGINE_JUST_STARTED, true);
        } else {
            this.entityData.set(DATA_ENGINE_JUST_STARTED, false);
        }
        wasOccupied = occupied;
    }

    @Override
    public boolean hurtServer(ServerLevel serverLevel, net.minecraft.world.damagesource.DamageSource source, float amount) {
        if (source.getEntity() instanceof Player player) {
            Vec3 eyePos = player.getEyePosition();
            Vec3 lookDir = player.getLookAngle();
            Vec3 hit = raycastCarShape(eyePos, lookDir, 4.5);
            if (hit == null) {
                return false; // swung at empty air beside the car
            }
        }
        return super.hurtServer(serverLevel, source, amount);
    }


}