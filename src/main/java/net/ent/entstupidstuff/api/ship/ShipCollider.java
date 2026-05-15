package net.ent.entstupidstuff.api.ship;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.AABB;

/**
 * One solid brick of a ship — a single AABB-sized invisible entity that
 * vanilla collision treats as solid. Players walk on it (deck floors) and
 * bump into it (hull walls).
 *
 * Many of these are spawned per ship — see {@link SloopLayout} for the
 * brick list. The owning {@link ShipEntityTest} updates each brick's position every
 * tick to follow the ship's transform.
 *
 * These entities have no physics of their own. They don't fall, they aren't
 * pushed, they don't tick anything but a "is my parent gone" check.
 */
public class ShipCollider extends Entity {

    private ShipEntityTest parent;
    private SloopLayout.Brick brick;

    public ShipCollider(EntityType<? extends ShipCollider> type, Level level) {
        super(type, level);
        this.noPhysics = true;
        this.setInvisible(true);
        this.setSilent(true);
    }

    /** Called by the parent ShipEntityTest right after spawning us. */
    public void bindToShip(ShipEntityTest parent, SloopLayout.Brick brick) {
        this.parent = parent;
        this.brick  = brick;
        refreshBoundingBox();
    }

    //@Override public boolean canBeCollidedWith() { return true; }
    @Override public boolean isPushable()         { return false; }
    @Override public boolean canCollideWith(Entity other) { return true; }
    @Override public boolean isPickable()         { return false; }
    @Override public boolean isAttackable()       { return false; }

    @Override
    public void tick() {
        // Only do the orphan-cleanup on the server. On the client, parent is
        // always null because we don't sync that reference — but the client
        // doesn't need it anyway, position arrives from the network layer.
        if (!level().isClientSide()
                && (parent == null || parent.isRemoved())) {
            discard();
            return;
        }
        this.tickCount++;
    }

    /**
     * Recompute our bounding box from our world position + brick half-extents.
     *
     * Note: this produces an axis-aligned box even when the parent ship is
     * rotated. For 1×1×1 bricks the visual error is negligible. For larger
     * bricks (e.g. a 6-block hull wall) the rotation looks wrong and players
     * snag at corners — keep your bricks small.
     */
    public void refreshBoundingBox() {
        if (brick == null) return;
        double hx = brick.hx();
        double hy = brick.hy();
        double hz = brick.hz();
        setBoundingBox(new AABB(
                getX() - hx, getY() - hy, getZ() - hz,
                getX() + hx, getY() + hy, getZ() + hz));
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        // No synched fields — client positions us purely from server pos
        // updates and looks up parent/brick by UUID after load.
    }

    @Override
    protected void readAdditionalSaveData(ValueInput tag) {
        // Reload of brick spec is by index from layout, set by parent ShipEntityTest
        // during its own load via rebindCollidersAfterLoad(). Nothing to do
        // here directly.
    }

    @Override
    protected void addAdditionalSaveData(ValueOutput tag) {
        // Brick identity persists implicitly: parent ShipEntityTest saves the list of
        // collider UUIDs in order matching SloopLayout.BRICKS.
    }

    @Override
    public boolean hurtServer(ServerLevel serverLevel, DamageSource damageSource, float f) {
        return false;
    }


}
