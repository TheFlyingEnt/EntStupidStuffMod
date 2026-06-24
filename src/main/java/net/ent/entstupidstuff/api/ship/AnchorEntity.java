package net.ent.entstupidstuff.api.ship;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.Vec3;

/**
 * A dragging anchor. It sinks to the seabed and is towed behind the ship.
 *
 * A plain Entity already has step height 0, so it can never climb a block:
 * the instant it's dragged into a rise/ledge, collision stops it dead — the snag.
 * The ship then feels the chain go taut (CustomBoatEntity.applyChainConstraint)
 * and slows / stops.
 *
 * When the ship starts raising the anchor, this entity flies back toward the
 * ship (ignoring collision) and discards itself on arrival.
 *
 * On the client, chain-link particles are spawned along the line from anchor
 * to ship with a catenary droop, giving a visual chain similar to leads.
 */
public class AnchorEntity extends Entity {

    private static final EntityDataAccessor<Integer> SHIP_ID =
            SynchedEntityData.defineId(AnchorEntity.class, EntityDataSerializers.INT);

    public  static final double CHAIN_LENGTH = 14.0; // max ship<->anchor horizontal distance
    private static final double GRAVITY      = 0.05;
    private static final double FOLLOW_PULL  = 0.45; // how hard it's dragged after the ship
    private static final double GROUND_FR    = 0.55; // grip on the seabed
    private static final double WATER_FR     = 0.80;
    private static final double RAISE_SPEED  = 0.35; // how fast the anchor climbs back to the ship
    private static final double RAISE_ARRIVE = 1.8;  // discard when this close to the ship

    public AnchorEntity(EntityType<? extends AnchorEntity> type, Level level) {
        super(type, level);
    }

    @Override protected void defineSynchedData(SynchedEntityData.Builder b) { b.define(SHIP_ID, -1); }
    @Override protected void readAdditionalSaveData(ValueInput in)  {}
    @Override protected void addAdditionalSaveData(ValueOutput out) {}

    public void setShipId(int id) { this.entityData.set(SHIP_ID, id); }
    public int  getShipId()       { return this.entityData.get(SHIP_ID); }

    public CustomBoatEntity getShip() {
        if (getShipId() < 0) return null;
        Entity e = this.level().getEntity(getShipId());
        return (e instanceof CustomBoatEntity s) ? s : null;
    }

    @Override
    public void tick() {
        super.tick();

        CustomBoatEntity ship = getShip();
        if (ship == null || ship.isRemoved()) {
            if (!level().isClientSide()) this.discard();
            return;
        }

        // ── RAISING: fly back to the ship ──────────────────────────────
        if (ship.isRaisingAnchor()) {
            tickRaise(ship);
            tickChainParticles(ship);
            return;   // skip normal drag behavior
        }

        // ── NORMAL: sink + drag behind the ship ────────────────────────
        Vec3 dm = this.getDeltaMovement();

        // gravity (sink to seabed)
        if (!this.onGround()) {
            dm = dm.add(0.0, -GRAVITY, 0.0);
        }

        // tow drag: pull toward the ship, stronger as the chain stretches
        double dx = ship.getX() - this.getX();
        double dz = ship.getZ() - this.getZ();
        double dist = Math.sqrt(dx * dx + dz * dz);
        if (dist > 1.0) {
            double inv  = 1.0 / dist;
            double pull = FOLLOW_PULL * Math.min(1.0, dist / CHAIN_LENGTH);
            dm = dm.add(dx * inv * pull, 0.0, dz * inv * pull);
        }

        this.setDeltaMovement(dm);
        this.move(MoverType.SELF, this.getDeltaMovement());   // collision here = the snag

        // friction
        Vec3 v = this.getDeltaMovement();
        double fr = this.onGround() ? GROUND_FR : WATER_FR;
        this.setDeltaMovement(v.x * fr, v.y * 0.95, v.z * fr);

        // ── chain particles ────────────────────────────────────────────
        tickChainParticles(ship);
    }

    /** While raising: fly toward the ship, ignoring collision. Discard on arrival. */
    private void tickRaise(CustomBoatEntity ship) {
        double dx = ship.getX() - this.getX();
        double dy = (ship.getY() + 0.8) - this.getY();   // aim slightly above waterline
        double dz = ship.getZ() - this.getZ();
        double dist = Math.sqrt(dx * dx + dy * dy + dz * dz);

        // Close enough → raise complete
        if (dist < RAISE_ARRIVE) {
            if (!level().isClientSide()) this.discard();
            return;
        }

        // Fly toward the ship (no collision so it doesn't snag on the way back)
        double inv = RAISE_SPEED / dist;
        this.noPhysics = true;
        this.setDeltaMovement(dx * inv, dy * inv, dz * inv);
        this.move(MoverType.SELF, this.getDeltaMovement());
    }

    /**
     * Chain visualization: spawn particles along the line from anchor to ship
     * with a catenary (hanging curve) droop. Renders every 3 ticks to avoid spam.
     */
    private void tickChainParticles(CustomBoatEntity ship) {
        if (!level().isClientSide() || tickCount % 3 != 0) return;

        Vec3 start = position().add(0, 0.3, 0);              // anchor attachment point
        Vec3 end   = new Vec3(ship.getX(), ship.getY() + 0.8, ship.getZ());  // ship attachment point
        double totalDist = start.distanceTo(end);
        int segments = Mth.clamp((int) (totalDist * 1.5), 4, 18);

        // Droop: more slack = more sag; taut chain barely droops
        double slack = Math.max(0, CHAIN_LENGTH - totalDist);
        double droop = 0.4 + slack * 0.12;   // minimum droop + slack bonus

        for (int i = 0; i <= segments; i++) {
            double t = i / (double) segments;
            double x = Mth.lerp(t, start.x, end.x);
            double z = Mth.lerp(t, start.z, end.z);
            // catenary approximation: sin curve peaks at midpoint
            double y = Mth.lerp(t, start.y, end.y) - Math.sin(t * Math.PI) * droop;

            this.level().addParticle(ParticleTypes.ELECTRIC_SPARK, x, y, z, 0, 0, 0);
        }
    }

    /** Set on spawn so it doesn't render-streak from the origin. */
    public void place(double x, double y, double z) {
        this.setPos(x, y, z);
        this.xOld = x; this.yOld = y; this.zOld = z;
        this.xo = x;   this.yo = y;   this.zo = z;
    }

    @Override public boolean isPickable() { return false; }

    @Override
    public boolean hurtServer(ServerLevel serverLevel, DamageSource damageSource, float f) {
        return false;
    }
}