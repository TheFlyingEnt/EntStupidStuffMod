package net.ent.entstupidstuff.api.ship;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
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
 * A plain Entity already has step height 0, so it can never climb a block:
 * the instant it's dragged into a rise/ledge, collision stops it dead — the snag.
 * The ship then feels the chain go taut (CustomBoatEntity.applyChainConstraint) and stops.
 */
public class AnchorEntity extends Entity {
 
    private static final EntityDataAccessor<Integer> SHIP_ID =
            SynchedEntityData.defineId(AnchorEntity.class, EntityDataSerializers.INT);
 
    public  static final double CHAIN_LENGTH = 14.0; // max ship<->anchor horizontal distance
    private static final double GRAVITY      = 0.05;
    private static final double FOLLOW_PULL  = 0.45; // how hard it's dragged after the ship
    private static final double GROUND_FR    = 0.55; // grip on the seabed
    private static final double WATER_FR     = 0.80;
 
    public AnchorEntity(EntityType<? extends AnchorEntity> type, Level level) {
        super(type, level);
        // NOTE: no setMaxUpStep — base Entity step height is already 0, which is what makes it snag.
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
 
        Vec3 dm = this.getDeltaMovement();
        if (!this.onGround()) dm = dm.add(0.0, -GRAVITY, 0.0);   // sink
 
        double dx = ship.getX() - this.getX();
        double dz = ship.getZ() - this.getZ();
        double dist = Math.sqrt(dx * dx + dz * dz);
        if (dist > 1.0) {
            double inv  = 1.0 / dist;
            double pull = FOLLOW_PULL * Math.min(1.0, dist / CHAIN_LENGTH);
            dm = dm.add(dx * inv * pull, 0.0, dz * inv * pull);
        }
 
        this.setDeltaMovement(dm);
        this.move(MoverType.SELF, this.getDeltaMovement());      // collision here = the snag
 
        Vec3 v = this.getDeltaMovement();
        double fr = this.onGround() ? GROUND_FR : WATER_FR;
        this.setDeltaMovement(v.x * fr, v.y * 0.95, v.z * fr);
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
