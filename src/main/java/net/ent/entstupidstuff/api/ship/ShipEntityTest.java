package net.ent.entstupidstuff.api.ship;

import net.ent.entstupidstuff.registry.EntityFactory;
import net.minecraft.core.BlockPos;
import net.minecraft.core.UUIDUtil;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Sea-of-Thieves-style two-deck Sloop.
 *
 * This entity is the "brain": it owns the position, yaw, velocity, and sail
 * state, and it owns a list of {@link ShipCollider} children that provide the
 * actual walkable collision surfaces.
 *
 * The ship itself has no meaningful collision shape — its bounding box is set
 * to the rough outer hull only so culling works. Players walk on, and bump
 * into, the collider children.
 *
 * Coordinate convention (ship-local):
 *   +Z = bow / forward
 *   +X = starboard / right
 *   +Y = up
 *   Origin (0,0,0) at the center of the top deck.
 */
public class ShipEntityTest extends Entity {

    private boolean collidersSpawned = false;

    // ---------------------------------------------------------------- data

    private static final EntityDataAccessor<Float> DATA_YAW =
            SynchedEntityData.defineId(ShipEntityTest.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Float> DATA_FORWARD_SPEED =
            SynchedEntityData.defineId(ShipEntityTest.class, EntityDataSerializers.FLOAT);
    /** 0 = sails fully reefed, 1 = sails fully deployed. */
    private static final EntityDataAccessor<Float> DATA_SAIL_DEPLOY =
            SynchedEntityData.defineId(ShipEntityTest.class, EntityDataSerializers.FLOAT);

    /** Tuning constants — surface them in a config later. */
    private static final float MAX_FORWARD_SPEED = 0.45f;   // blocks/tick
    private static final float ACCELERATION       = 0.005f;
    private static final float TURN_RATE          = 0.6f;   // deg/tick per input unit
    private static final float YAW_DAMPING        = 0.92f;

    /** Strong references to children spawned this session. */
    private final List<ShipCollider> colliders = new ArrayList<>();
    /** Persisted across save/load so we can rebind colliders. */
    private final List<UUID> colliderUuids = new ArrayList<>();

    /** Ship-position delta this tick — used to drag passengers. */
    private Vec3 lastTickDelta = Vec3.ZERO;
    /** Ship-yaw delta this tick — used to rotate passengers around pivot. */
    private float lastTickYawDelta = 0f;

    public ShipEntityTest(EntityType<? extends ShipEntityTest> type, Level level) {
        super(type, level);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        builder.define(DATA_YAW, 0f);
        builder.define(DATA_FORWARD_SPEED, 0f);
        builder.define(DATA_SAIL_DEPLOY, 0f);
    }

    // ---------------------------------------------------------------- lifecycle

    /**
     * Call once when this ship is first placed in the world (e.g. from a
     * "ship-in-a-bottle" item). Spawns and registers all colliders.
     */
    public void spawnColliders() {
        if (level().isClientSide()) return;

        for (SloopLayout.Brick brick : SloopLayout.BRICKS) {
            ShipCollider collider = new ShipCollider(
                    EntityFactory.SHIP_COLLIDER, level());
            collider.bindToShip(this, brick);
            Vec3 worldPos = localToWorld(brick.entityPosition());  // ← changed
            collider.setPos(worldPos.x, worldPos.y, worldPos.z);
            level().addFreshEntity(collider);
            colliders.add(collider);
        }
    }

    @Override
    public void remove(RemovalReason reason) {
        // Take our colliders down with us.
        for (ShipCollider c : colliders) {
            if (!c.isRemoved()) c.discard();
        }
        super.remove(reason);
    }

    // ---------------------------------------------------------------- tick

    @Override
    public void tick() {
        Vec3 prevPos = position();
        float prevYaw = getYRot();

        if (!level().isClientSide()) {
            if (!collidersSpawned) {
                spawnColliders();
                collidersSpawned = true;
            }
            applyControls();
            applyPhysics();
        }

        super.tick();
    }

    private void applyControls() {
        Player driver = findDriver();
        float sailFactor = entityData.get(DATA_SAIL_DEPLOY);
        float forward    = entityData.get(DATA_FORWARD_SPEED);

        // Sails out -> accelerate toward max. Sails reefed -> coast to zero.
        float target = sailFactor * MAX_FORWARD_SPEED;
        forward += Math.signum(target - forward) * Math.min(
                ACCELERATION, Math.abs(target - forward));
        entityData.set(DATA_FORWARD_SPEED, forward);

        // Driver steers with horizontal input (A/D when seated at wheel).
        if (driver != null) {
            float yaw = entityData.get(DATA_YAW);
            // Player.xxa: -1 = left strafe, +1 = right strafe
            yaw += driver.xxa * TURN_RATE;
            entityData.set(DATA_YAW, yaw);
        }
    }

    private void applyPhysics() {
        float yaw = entityData.get(DATA_YAW);
        float speed = entityData.get(DATA_FORWARD_SPEED);

        double rad = Math.toRadians(yaw);
        Vec3 forward = new Vec3(-Math.sin(rad), 0, Math.cos(rad));

        Vec3 velocity = forward.scale(speed)
                               .add(0, computeBuoyancy(), 0);

        setDeltaMovement(velocity);
        move(MoverType.SELF, getDeltaMovement());
        setYRot(yaw);
    }

    private double computeBuoyancy() {
        // V1 placeholder: float on water, sink in air.
        // V3: sample multiple hull points, derive pitch/roll from differential
        // submersion.
        if (isInWater() || isUnderWater()) return 0.04;
        return -0.08;
    }

    private void updateColliderTransforms() {
        if (colliders.size() != SloopLayout.BRICKS.length) {
            // Could happen post-load before rebind. Skip this tick.
            return;
        }
        float yaw = getYRot();
        for (int i = 0; i < colliders.size(); i++) {
            SloopLayout.Brick brick = SloopLayout.BRICKS[i];
            Vec3 worldPos = localToWorld(brick.entityPosition());  // ← changed
            ShipCollider c = colliders.get(i);
            c.setPos(worldPos.x, worldPos.y, worldPos.z);
            c.setYRot(yaw);
        }
    }

    /**
     * Move every player standing on the ship by the same delta the ship
     * moved this tick. Optionally rotate them around the ship's pivot if
     * the ship yawed.
     */
    private void dragPassengers() {
        if (lastTickDelta.lengthSqr() < 1e-8 && Math.abs(lastTickYawDelta) < 1e-4) {
            return;
        }
        AABB searchBox = getBoundingBox().inflate(2.0);
        List<Player> nearby = level().getEntitiesOfClass(Player.class, searchBox);

        for (Player p : nearby) {
            if (!isStandingOnShip(p)) continue;

            // Translate by ship's per-tick delta.
            Vec3 newPos = p.position().add(lastTickDelta);

            // Rotate around ship pivot if we yawed this tick.
            if (Math.abs(lastTickYawDelta) > 1e-4) {
                Vec3 offset = newPos.subtract(position());
                double rad = Math.toRadians(lastTickYawDelta);
                double cos = Math.cos(rad);
                double sin = Math.sin(rad);
                double rx = offset.x * cos - offset.z * sin;
                double rz = offset.x * sin + offset.z * cos;
                newPos = position().add(rx, offset.y, rz);
                p.setYRot(p.getYRot() + lastTickYawDelta);
            }

            p.setPos(newPos.x, newPos.y, newPos.z);
        }
    }

    private boolean isStandingOnShip(Player p) {
        AABB feet = p.getBoundingBox().move(0, -0.05, 0);
        for (ShipCollider c : colliders) {
            if (c.getBoundingBox().intersects(feet)) return true;
        }
        return false;
    }

    private Player findDriver() {
        // Stub: returns the player riding our wheel-seat sub-entity.
        // For v1 you can just return getControllingPassenger() if you make
        // the wheel-seat a passenger of the ShipEntityTest.
        return null;
    }

    // ---------------------------------------------------------------- transforms

    /** Convert a ship-local coordinate to world coordinates. */
    public Vec3 localToWorld(Vec3 local) {
        double rad = Math.toRadians(getYRot());
        double cos = Math.cos(rad);
        double sin = Math.sin(rad);
        double x = local.x * cos - local.z * sin;
        double z = local.x * sin + local.z * cos;
        return position().add(x, local.y, z);
    }

    /** Convert a world coordinate to ship-local coordinates. */
    public Vec3 worldToLocal(Vec3 world) {
        Vec3 rel = world.subtract(position());
        double rad = Math.toRadians(-getYRot());
        double cos = Math.cos(rad);
        double sin = Math.sin(rad);
        double x = rel.x * cos - rel.z * sin;
        double z = rel.x * sin + rel.z * cos;
        return new Vec3(x, rel.y, z);
    }

    // ---------------------------------------------------------------- save/load

    @Override
    protected void readAdditionalSaveData(ValueInput input) {
        entityData.set(DATA_YAW,           input.getFloatOr("Yaw",   0F));
        entityData.set(DATA_FORWARD_SPEED, input.getFloatOr("Speed", 0F));
        entityData.set(DATA_SAIL_DEPLOY,   input.getFloatOr("Sails", 0F));

        colliderUuids.clear();
        input.read("ColliderUUIDs", UUIDUtil.CODEC.listOf())
            .ifPresent(colliderUuids::addAll);
        // NOTE: actual ShipCollider lookup happens later via the level's
        // entity manager once chunks load — see rebindCollidersAfterLoad().
    }

    @Override
    protected void addAdditionalSaveData(ValueOutput output) {
        output.putFloat("Yaw",   entityData.get(DATA_YAW));
        output.putFloat("Speed", entityData.get(DATA_FORWARD_SPEED));
        output.putFloat("Sails", entityData.get(DATA_SAIL_DEPLOY));

        if (!colliderUuids.isEmpty()) {
            output.store("ColliderUUIDs", UUIDUtil.CODEC.listOf(), colliderUuids);
        }
    }

    // ---------------------------------------------------------------- API

    public float getSailDeploy() { return entityData.get(DATA_SAIL_DEPLOY); }
    public void setSailDeploy(float v) {
        entityData.set(DATA_SAIL_DEPLOY, Math.max(0f, Math.min(1f, v)));
    }

    @Override
    public boolean hurtServer(ServerLevel serverLevel, DamageSource damageSource, float f) {
        return false;
    }
}
