package net.ent.entstupidstuff.api.ship;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Cannonball projectile — fired from a ship's bow cannon.
 *
 * Lifecycle:
 *   1. FLYING — launched from the bow, arcs with gravity
 *   2. HIT    — on impact: damages target, spawns explosion particles, discards
 *
 * Unlike the harpoon, cannonballs are one-shot: no tether, no reeling.
 * They deal heavy damage to ships (hitByCannonball) and moderate damage to entities.
 *
 * The bow gunner (seat 5) fires via CannonControlPayload.
 */
public class ShipCannonballEntity extends Entity {

    // ── Synced data ──
    private static final EntityDataAccessor<Integer> SHIP_ID =
        SynchedEntityData.defineId(ShipCannonballEntity.class, EntityDataSerializers.INT);

    // ── Constants ──
    private static final double SPEED        = 2.0;    // launch speed (blocks/tick) — slightly slower than harpoon
    private static final double GRAVITY      = 0.06;   // heavier than harpoon — more arc
    private static final double DRAG         = 0.99;
    private static final float  ENTITY_DAMAGE = 12.0f;  // damage to living entities
    private static final int    MAX_LIFE     = 200;     // 10 seconds before auto-despawn
    private static final double MAX_RANGE    = 80.0;    // auto-despawn distance from ship
    private static final float  EXPLOSION_RADIUS = 1.5f; // visual only — no block damage

    // ── Stuck state (hit a block, smoking) ──
    private static final EntityDataAccessor<Boolean> IS_STUCK =
        SynchedEntityData.defineId(ShipCannonballEntity.class, EntityDataSerializers.BOOLEAN);
    private int stuckTimer = 0;
    private static final int STUCK_SMOKE_TICKS = 60;  // 3 seconds of smoking

    // ── Fields ──
    private int life = 0;

    public ShipCannonballEntity(EntityType<? extends ShipCannonballEntity> type, Level level) {
        super(type, level);
        this.noPhysics = false;
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder b) {
        b.define(SHIP_ID, -1);
        b.define(IS_STUCK, false);
    }

    @Override protected void readAdditionalSaveData(ValueInput in)  {}
    @Override protected void addAdditionalSaveData(ValueOutput out) {}

    // ── Accessors ──
    public void setShipId(int id)   { entityData.set(SHIP_ID, id); }
    public int  getShipId()         { return entityData.get(SHIP_ID); }

    public @Nullable CustomBoatEntity getShip() {
        int id = getShipId();
        if (id < 0) return null;
        return level().getEntity(id) instanceof CustomBoatEntity s ? s : null;
    }

    // ── Launch ──────────────────────────────────────────────────────
    /**
     * Fire from a ship's bow in the gunner's look direction.
     * Spawns at the bow position, 5 blocks forward from center.
     */
    public void fire(CustomBoatEntity ship, float yaw, float pitch) {
        setShipId(ship.getId());

        // Spawn at the bow
        double rad = Math.toRadians(ship.getYRot());
        double bowX = ship.getX() - Math.sin(rad) * 5.0;
        double bowZ = ship.getZ() + Math.cos(rad) * 5.0;
        double bowY = ship.getY() + 2.0;  // cannon sits higher than the harpoon mount
        this.setPos(bowX, bowY, bowZ);

        // Velocity from the gunner's look direction
        double radYaw   = Math.toRadians(-yaw);
        double radPitch = Math.toRadians(-pitch);
        double cosP     = Math.cos(radPitch);
        this.setDeltaMovement(
            Math.sin(radYaw) * cosP * SPEED,
            Math.sin(radPitch) * SPEED,
            Math.cos(radYaw) * cosP * SPEED
        );
    }

    // ── Tick ────────────────────────────────────────────────────────
    @Override
    public void tick() {
        super.tick();

        // ── STUCK: smoking on the ground ──
        if (entityData.get(IS_STUCK)) {
            stuckTimer--;

            // Smoke particles (client side)
            if (level().isClientSide()) {
                // Thick smoke for first second, then tapering off
                float intensity = (float) stuckTimer / STUCK_SMOKE_TICKS;
                if (random.nextFloat() < intensity) {
                    level().addParticle(ParticleTypes.LARGE_SMOKE,
                        getX() + random.nextGaussian() * 0.3,
                        getY() + 0.2 + random.nextFloat() * 0.5,
                        getZ() + random.nextGaussian() * 0.3,
                        0, 0.03, 0);
                }
                if (random.nextFloat() < intensity * 0.3f) {
                    level().addParticle(ParticleTypes.CAMPFIRE_COSY_SMOKE,
                        getX() + random.nextGaussian() * 0.2,
                        getY() + 0.3,
                        getZ() + random.nextGaussian() * 0.2,
                        0, 0.02, 0);
                }
                // Embers in first half
                if (stuckTimer > STUCK_SMOKE_TICKS / 2 && random.nextFloat() < 0.2f) {
                    level().addParticle(ParticleTypes.FLAME,
                        getX() + random.nextGaussian() * 0.15,
                        getY() + 0.1,
                        getZ() + random.nextGaussian() * 0.15,
                        0, 0.01, 0);
                }
            }

            // Despawn after smoke timer
            if (stuckTimer <= 0 && !level().isClientSide()) {
                discard();
            }
            return;  // skip flying logic
        }

        // ── FLYING ──
        life++;
        if (life > MAX_LIFE && !level().isClientSide()) {
            discard();
            return;
        }

        CustomBoatEntity ship = getShip();
        if (ship != null && distanceTo(ship) > MAX_RANGE && !level().isClientSide()) {
            discard();
            return;
        }

        Vec3 vel = getDeltaMovement();
        vel = vel.add(0, -GRAVITY, 0);

        if (!level().isClientSide()) {
            Vec3 from = position();
            Vec3 to   = from.add(vel);

            List<Entity> nearby = level().getEntities(this,
                getBoundingBox().expandTowards(vel).inflate(0.5),
                e -> e.getId() != getShipId()
                     && !(e instanceof ShipCannonballEntity)
                     && !(e instanceof AnchorEntity)
                     && !(e instanceof HarpoonProjectileEntity)
                     && e.isPickable()
                     && !(e instanceof ShipPartEntity sp && sp.parentMob != null
                          && sp.parentMob.getId() == getShipId()));

            Entity closest = null;
            double closestDist = Double.MAX_VALUE;
            for (Entity e : nearby) {
                AABB box = e.getBoundingBox().inflate(0.3);
                if (box.contains(from) || box.clip(from, to).isPresent()) {
                    double d = from.distanceToSqr(e.position());
                    if (d < closestDist) { closest = e; closestDist = d; }
                }
            }

            if (closest != null) { onHitEntity(closest); return; }

            BlockPos nextPos = BlockPos.containing(to);
            if (level().getBlockState(nextPos).isSolid()) {
                onHitBlock(to);
                return;
            }
        }

        setDeltaMovement(vel);
        move(MoverType.SELF, vel);
        setDeltaMovement(getDeltaMovement().scale(DRAG));

        // Trail particles
        if (level().isClientSide() && life > 1) {
            level().addParticle(ParticleTypes.SMOKE,
                getX(), getY(), getZ(), 0, 0, 0);
            if (life % 2 == 0) {
                level().addParticle(ParticleTypes.FLAME,
                    getX(), getY(), getZ(), 0, 0, 0);
            }
        }
    }

    // ── Impact handlers ────────────────────────────────────────────

    private void onHitEntity(Entity target) {
        if (target instanceof ShipPartEntity part && part.parentMob != null) {
            target = part.parentMob;
        }
        if (target instanceof CustomBoatEntity boat && boat.getId() == getShipId()) return;

        if (target instanceof CustomBoatEntity targetShip) {
            targetShip.hitByCannonball();
        } else if (target instanceof LivingEntity living) {
            living.hurt(level().damageSources().generic(), ENTITY_DAMAGE);
            Vec3 vel = getDeltaMovement().normalize().scale(0.8);
            living.push(vel.x, 0.3, vel.z);
        }

        // Explosion on entity hit too
        this.level().explode(null, this.getX(), this.getY(), this.getZ(), 2.0F,
            Level.ExplosionInteraction.NONE);
        spawnImpactEffects();
        discard();
    }

    private void onHitBlock(Vec3 hitPos) {
        // Explosion on impact
        this.level().explode(null, this.getX(), this.getY(), this.getZ(), 2.0F,
            Level.ExplosionInteraction.NONE);

        setPos(hitPos);
        setDeltaMovement(Vec3.ZERO);
        this.noPhysics = true;  // don't fall through the block

        // Don't discard — enter stuck/smoking state
        entityData.set(IS_STUCK, true);
        stuckTimer = STUCK_SMOKE_TICKS;

        // Initial impact effects
        spawnImpactEffects();
    }

    /**
     * Explosion-like particles and sound on impact.
     * No actual block damage — just visual + audio.
     */
    private void spawnImpactEffects() {
        if (level() instanceof ServerLevel sl) {
            // Smoke cloud
            /*sl.sendParticles(ParticleTypes.EXPLOSION, getX(), getY(), getZ(),
                1, 0, 0, 0, 0);
            sl.sendParticles(ParticleTypes.LARGE_SMOKE, getX(), getY(), getZ(),
                8, 0.5, 0.5, 0.5, 0.05);
            sl.sendParticles(ParticleTypes.FLAME, getX(), getY(), getZ(),
                4, 0.3, 0.3, 0.3, 0.05);*/

            double nes = this.random.nextGaussian() * 0.05;

            sl.sendParticles(ParticleTypes.EXPLOSION_EMITTER, getX(), getY(), getZ(),
                4, 0.3, this.random.nextGaussian() * 0.05, -this.getDeltaMovement().y * 0.5, this.random.nextGaussian() * 0.05);

            // Impact sound
            /*sl.playSound(null, blockPosition(),
                SoundEvents.GENERIC_EXPLODE.value(), SoundSource.NEUTRAL,
                1.5f, 0.9f + sl.random.nextFloat() * 0.2f);*/

            sl.playSound(null, blockPosition(),
                SoundEvents.GENERIC_EXPLODE.value(), SoundSource.NEUTRAL, 1.2f, 1.5f);
        }
    }

    @Override public boolean isPickable() { return false; }

    @Override
    public boolean hurtServer(ServerLevel sl, DamageSource src, float amt) {
        return false;
    }
}