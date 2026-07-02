package net.ent.entstupidstuff.api.ship;

import net.ent.entstupidstuff.registry.EntityFactory;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jetbrains.annotations.Nullable;

public class ShipPartEntity extends Entity {

    @Nullable public CustomBoatEntity parentMob;   // nullable, set when server creates
    private EntityDimensions size;

    public ShipPartEntity(EntityType<ShipPartEntity> type, Level world) {
        super(type, world);
        this.parentMob = null;
        this.size = type.getDimensions();
        this.noPhysics = true;
    }

    // Server-side constructor (called from CustomBoatEntity)
    public ShipPartEntity(CustomBoatEntity parent, float width, float height) {
        super(EntityFactory.SHIP_PART, parent.level());
        this.parentMob = parent;
        this.size = EntityDimensions.scalable(width, height);
        this.refreshDimensions();
        this.noPhysics = true;
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {}

    @Override
    protected void readAdditionalSaveData(ValueInput in) {}

    @Override
    protected void addAdditionalSaveData(ValueOutput out) {}

    @Override
    public boolean isPickable() {
        return true;
    }

   @Override
    public boolean hurtServer(ServerLevel level, DamageSource source, float amount) {
        if (this.parentMob == null) return false;
        return this.isInvulnerableToBase(source) ? false
            : this.parentMob.hurtServer(level, source, amount);
    }

    @Override
    public boolean is(Entity entity) {
        return this == entity || (this.parentMob != null && this.parentMob == entity);
    }

    @Override
    public EntityDimensions getDimensions(Pose pose) {
        return this.size;
    }

    @Nullable
    @Override
    public ItemStack getPickResult() {
        return this.parentMob != null ? this.parentMob.getPickResult() : null;
    }

    @Override
    public boolean shouldBeSaved() {
        return false;
    }

    /**
     * Parts are collidable so entities (including players) can stand on them.
     * Only the parent boat and sibling parts are excluded.
     */

    @Override
    public boolean canBeCollidedWith(@Nullable Entity entity) {
        if (entity == this.parentMob) return false;
        if (entity instanceof ShipPartEntity other && other.parentMob == this.parentMob) return false;
        // Don't push passengers or deck walkers on our own ship
        if (this.parentMob != null && this.parentMob.isCrewMember(entity)) return false;
        return true;
    }
    
    /*@Override
    public boolean canBeCollidedWith(@Nullable Entity entity) {
        if (entity == this.parentMob) return false;
        if (entity instanceof ShipPartEntity other && other.parentMob == this.parentMob) return false;
        return true;
    }*/

    @Override
    public boolean isPushable() {
        return false;
    }
}