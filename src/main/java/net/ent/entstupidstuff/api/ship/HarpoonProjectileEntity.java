package net.ent.entstupidstuff.api.ship;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

import java.util.List;

/**
 * Sea-of-Thieves-style harpoon projectile.
 *
 * Lifecycle:
 *   1. FLYING — launched from the bow, travels with gravity
 *   2. STUCK  — embedded in a block or entity, tether active
 *   3. REELING — pulling the ship/target together
 *   4. Discarded when released or the ship is destroyed
 *
 * The bow gunner (seat 5) controls fire/reel/release via HarpoonControlPayload.
 */
public class HarpoonProjectileEntity extends Entity {

    // ── Synced data ──
    private static final EntityDataAccessor<Integer> SHIP_ID =
        SynchedEntityData.defineId(HarpoonProjectileEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> STUCK =
        SynchedEntityData.defineId(HarpoonProjectileEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Integer> STUCK_ENTITY_ID =
        SynchedEntityData.defineId(HarpoonProjectileEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> REELING =
        SynchedEntityData.defineId(HarpoonProjectileEntity.class, EntityDataSerializers.BOOLEAN);

    // ── Constants ──
    public  static final double MAX_RANGE    = 30.0;   // max tether length
    private static final double SPEED        = 2.5;    // launch speed (blocks/tick)
    private static final double GRAVITY      = 0.04;
    private static final double REEL_FORCE   = 0.12;   // pull strength per tick
    private static final double DRAG         = 0.98;
    private static final float  HIT_DAMAGE   = 4.0f;   // damage to entities on impact
    private static final int    MAX_LIFE     = 600;     // 30 seconds before auto-despawn

    // ── Fields ──
    private int life = 0;

    public HarpoonProjectileEntity(EntityType<? extends HarpoonProjectileEntity> type, Level level) {
        super(type, level);
        this.noPhysics = false;
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder b) {
        b.define(SHIP_ID, -1);
        b.define(STUCK, false);
        b.define(STUCK_ENTITY_ID, -1);
        b.define(REELING, false);
    }

    @Override protected void readAdditionalSaveData(ValueInput in)  {}
    @Override protected void addAdditionalSaveData(ValueOutput out) {}

    // ── Accessors ──
    public void setShipId(int id)        { entityData.set(SHIP_ID, id); }
    public int  getShipId()              { return entityData.get(SHIP_ID); }
    public boolean isStuck()             { return entityData.get(STUCK); }
    public boolean isReeling()           { return entityData.get(REELING); }
    public void setReeling(boolean r)    { entityData.set(REELING, r); }
    public int  getStuckEntityId()       { return entityData.get(STUCK_ENTITY_ID); }

    public CustomBoatEntity getShip() {
        int id = getShipId();
        if (id < 0) return null;
        return level().getEntity(id) instanceof CustomBoatEntity s ? s : null;
    }

    public Entity getStuckEntity() {
        int id = getStuckEntityId();
        if (id < 0) return null;
        return level().getEntity(id);
    }

    // ── Launch ──
    public void fire(CustomBoatEntity ship, float yaw, float pitch) {
        setShipId(ship.getId());

        // Spawn at the bow of the ship
        double rad = Math.toRadians(ship.getYRot());
        double bowX = ship.getX() - Math.sin(rad) * 5.0;  // 5 blocks forward from center
        double bowZ = ship.getZ() + Math.cos(rad) * 5.0;
        double bowY = ship.getY() + 1.5;
        this.setPos(bowX, bowY, bowZ);

        // Velocity from the gunner's look direction
        double radYaw = Math.toRadians(-yaw);
        double radPitch = Math.toRadians(-pitch);
        double cosP = Math.cos(radPitch);
        this.setDeltaMovement(
            Math.sin(radYaw) * cosP * SPEED,
            Math.sin(radPitch) * SPEED,
            Math.cos(radYaw) * cosP * SPEED
        );
    }

    // ── Tick ──
    @Override
    public void tick() {
        super.tick();

        CustomBoatEntity ship = getShip();
        if (ship == null || ship.isRemoved()) {
            if (!level().isClientSide()) discard();
            return;
        }

        life++;
        if (life > MAX_LIFE && !level().isClientSide()) {
            release();
            return;
        }

        if (isStuck()) {
            tickStuck(ship);
            // Reset reeling flag — the client re-sends REEL every tick while held,
            // so this only stays true on ticks where the gunner is actively pressing.
            // Without this, one REEL press turns on auto-reel forever.
            if (!level().isClientSide() && isReeling()) {
                entityData.set(REELING, false);
            }
        } else {
            tickFlying(ship);
        }
    }

    private void tickFlying(CustomBoatEntity ship) {
        Vec3 vel = getDeltaMovement();

        // Gravity
        vel = vel.add(0, -GRAVITY, 0);

        // Check for hits
        Vec3 from = position();
        Vec3 to = from.add(vel);

        // Entity hit check
        if (!level().isClientSide()) {
            List<Entity> nearby = level().getEntities(this,
                getBoundingBox().expandTowards(vel).inflate(0.5),
                e -> e != ship && !(e instanceof HarpoonProjectileEntity)
                     && !(e instanceof AnchorEntity) && e.isPickable()
                     && !(e instanceof ShipPartEntity sp && sp.parentMob == ship));

            Entity closest = null;
            double closestDist = Double.MAX_VALUE;
            for (Entity e : nearby) {
                AABB box = e.getBoundingBox().inflate(0.3);
                if (box.contains(from) || box.clip(from, to).isPresent()) {
                    double d = from.distanceToSqr(e.position());
                    if (d < closestDist) { closest = e; closestDist = d; }
                }
            }

            if (closest != null) {
                onHitEntity(closest);
                return;
            }

            // Block hit check (simple: if we'd enter a solid block)
            net.minecraft.core.BlockPos nextPos = net.minecraft.core.BlockPos.containing(to);
            if (level().getBlockState(nextPos).isSolid()) {
                onHitBlock(to);
                return;
            }
        }

        // Move
        setDeltaMovement(vel);
        move(MoverType.SELF, vel);
        setDeltaMovement(getDeltaMovement().scale(DRAG));

        // Auto-despawn if too far from ship
        if (distanceTo(ship) > MAX_RANGE * 1.5 && !level().isClientSide()) {
            release();
        }
    }

    private void onHitEntity(Entity target) {
        // If we hit a ship's sub-part, attach to the parent ship instead
        if (target instanceof ShipPartEntity part && part.parentMob != null) {
            target = part.parentMob;
        }

        // Don't harpoon our own ship
        if (target instanceof CustomBoatEntity boat && boat.getId() == getShipId()) return;

        entityData.set(STUCK, true);
        entityData.set(STUCK_ENTITY_ID, target.getId());
        setDeltaMovement(Vec3.ZERO);

        // Damage on impact (don't damage ships — that's what cannons are for)
        if (target instanceof net.minecraft.world.entity.LivingEntity && level() instanceof ServerLevel sl) {
            target.hurt(level().damageSources().generic(), HIT_DAMAGE);
        }

        level().playSound(null, blockPosition(), SoundEvents.TRIDENT_HIT, SoundSource.NEUTRAL, 1f, 0.8f);
    }

    private void onHitBlock(Vec3 hitPos) {
        entityData.set(STUCK, true);
        entityData.set(STUCK_ENTITY_ID, -1);
        setPos(hitPos);
        setDeltaMovement(Vec3.ZERO);

        level().playSound(null, blockPosition(), SoundEvents.TRIDENT_HIT_GROUND, SoundSource.NEUTRAL, 1f, 1f);
    }

    private void tickStuck(CustomBoatEntity myShip) {
        Entity stuckTo = getStuckEntity();

        // Follow the stuck entity's position
        if (stuckTo != null) {
            if (stuckTo.isRemoved()) { release(); return; }
            setPos(stuckTo.getX(), stuckTo.getY() + stuckTo.getBbHeight() * 0.5, stuckTo.getZ());
        }

        if (level().isClientSide()) return;

        // Distance from my ship to the harpoon point
        double dx = myShip.getX() - getX();
        double dz = myShip.getZ() - getZ();
        double dist = Math.sqrt(dx * dx + dz * dz);

        // ── BIDIRECTIONAL TETHER ──────────────────────────────────────
        // Neither side can escape beyond MAX_RANGE. Correction is split
        // between both ends so a faster ship naturally drags a slower one.
        if (dist > MAX_RANGE) {
            double nx = dx / dist, nz = dz / dist;
            double over = dist - MAX_RANGE;

            if (stuckTo instanceof CustomBoatEntity otherShip) {
                // Ship-to-ship: split correction 50/50
                double half = over * 0.5;
                myShip.setPos(myShip.getX() - nx * half, myShip.getY(), myShip.getZ() - nz * half);
                otherShip.setPos(otherShip.getX() + nx * half, otherShip.getY(), otherShip.getZ() + nz * half);

                // Dampen outward velocity on both
                Vec3 mv = myShip.getDeltaMovement();
                double out1 = mv.x * nx + mv.z * nz;
                if (out1 > 0) myShip.setDeltaMovement(mv.x - nx * out1 * 0.5, mv.y, mv.z - nz * out1 * 0.5);

                Vec3 ov = otherShip.getDeltaMovement();
                double out2 = -(ov.x * nx + ov.z * nz);
                if (out2 > 0) otherShip.setDeltaMovement(ov.x + nx * out2 * 0.5, ov.y, ov.z + nz * out2 * 0.5);

            } else if (stuckTo != null) {
                // Entity: leash-style constraint (entity can't run away)
                double half = over * 0.5;
                myShip.setPos(myShip.getX() - nx * half, myShip.getY(), myShip.getZ() - nz * half);
                stuckTo.setPos(stuckTo.getX() + nx * half, stuckTo.getY(), stuckTo.getZ() + nz * half);

                // Kill outward velocity on the ship
                Vec3 mv = myShip.getDeltaMovement();
                double out1 = mv.x * nx + mv.z * nz;
                if (out1 > 0) myShip.setDeltaMovement(mv.x - nx * out1, mv.y, mv.z - nz * out1);

            } else {
                // Block: only the ship is constrained (block can't move)
                myShip.setPos(myShip.getX() - nx * over, myShip.getY(), myShip.getZ() - nz * over);
                Vec3 mv = myShip.getDeltaMovement();
                double outward = mv.x * nx + mv.z * nz;
                if (outward > 0) myShip.setDeltaMovement(mv.x - nx * outward, mv.y, mv.z - nz * outward);
            }
        }

        // ── REEL: only when the bow gunner is actively holding right-click ──
        // No automatic pulling — entities/ships just dangle on the tether until reeled.
        if (isReeling()) {
            if (stuckTo instanceof CustomBoatEntity otherShip) {
                // Ship-to-ship: pull them toward me (70%) + pull me toward them (30%)
                if (dist > 2.0) {
                    double inv = REEL_FORCE / dist;
                    otherShip.setDeltaMovement(otherShip.getDeltaMovement().add(
                        dx * inv * 0.7, 0, dz * inv * 0.7));
                    myShip.setDeltaMovement(myShip.getDeltaMovement().add(
                        -dx * inv * 0.3, 0, -dz * inv * 0.3));
                }

            } else if (stuckTo != null) {
                // Entity: pull entity toward ship
                double edx = myShip.getX() - stuckTo.getX();
                double edz = myShip.getZ() - stuckTo.getZ();
                double eDist = Math.sqrt(edx * edx + edz * edz);
                if (eDist > 2.0) {
                    double inv = REEL_FORCE / eDist;
                    stuckTo.setDeltaMovement(stuckTo.getDeltaMovement().add(edx * inv, 0.02, edz * inv));
                }

            } else {
                // Block: pull my ship toward the harpoon point
                if (dist > 2.0) {
                    double inv = REEL_FORCE / dist;
                    myShip.setDeltaMovement(myShip.getDeltaMovement().add(-dx * inv, 0, -dz * inv));
                }
            }
        }
    }

    /** Release the harpoon — despawn and clear the ship's reference. */
    public void release() {
        CustomBoatEntity ship = getShip();
        if (ship != null) {
            ship.clearHarpoon();
        }
        level().playSound(null, blockPosition(), SoundEvents.CHAIN_BREAK, SoundSource.NEUTRAL, 0.8f, 1.2f);
        discard();
    }

    @Override public boolean isPickable() { return false; }

    @Override
    public boolean hurtServer(ServerLevel sl, DamageSource src, float amt) {
        return false;
    }
}