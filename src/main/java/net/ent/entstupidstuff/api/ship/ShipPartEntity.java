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

    // Role of this hitbox. HULL = normal collision/damage part (front/rear).
    // TRIM_LEFT / TRIM_RIGHT = clickable sail-trim controls on the deck.
    public static final int ROLE_HULL       = 0;
    public static final int ROLE_TRIM_LEFT  = 1;
    public static final int ROLE_TRIM_RIGHT = 2;

    // Synced so the CLIENT knows each part's role + size. Without this, the
    // client rebuilds every part from the registered default (big hull box) and
    // never learns it's a small trim box → wrong size + wrong click behavior.
    private static final net.minecraft.network.syncher.EntityDataAccessor<Integer> DATA_ROLE =
        SynchedEntityData.defineId(ShipPartEntity.class,
            net.minecraft.network.syncher.EntityDataSerializers.INT);
    private static final net.minecraft.network.syncher.EntityDataAccessor<Float> DATA_W =
        SynchedEntityData.defineId(ShipPartEntity.class,
            net.minecraft.network.syncher.EntityDataSerializers.FLOAT);
    private static final net.minecraft.network.syncher.EntityDataAccessor<Float> DATA_H =
        SynchedEntityData.defineId(ShipPartEntity.class,
            net.minecraft.network.syncher.EntityDataSerializers.FLOAT);

    public int role = ROLE_HULL;

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
        this.entityData.set(DATA_W, width);
        this.entityData.set(DATA_H, height);
        this.refreshDimensions();
        this.noPhysics = true;
    }

    // Server-side constructor with a role (trim hitboxes)
    public ShipPartEntity(CustomBoatEntity parent, float width, float height, int role) {
        this(parent, width, height);
        this.role = role;
        this.entityData.set(DATA_ROLE, role);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        builder.define(DATA_ROLE, ROLE_HULL);
        builder.define(DATA_W, 1.0f);
        builder.define(DATA_H, 1.0f);
    }

    @Override
    public void onSyncedDataUpdated(net.minecraft.network.syncher.EntityDataAccessor<?> key) {
        super.onSyncedDataUpdated(key);
        // When the client receives role/size, rebuild the local dimensions so
        // trim boxes are the correct small size and behave as trim controls.
        if (DATA_ROLE.equals(key) || DATA_W.equals(key) || DATA_H.equals(key)) {
            this.role = this.entityData.get(DATA_ROLE);
            this.size = EntityDimensions.scalable(
                this.entityData.get(DATA_W), this.entityData.get(DATA_H));
            this.refreshDimensions();
        }
    }

    /**
     * Clicking a TRIM hitbox angles the sail — but only for the helmsman.
     * The hull parts fall through to the parent boat's normal interact.
     */
    @Override
    public net.minecraft.world.InteractionResult interact(
            net.minecraft.world.entity.player.Player player,
            net.minecraft.world.InteractionHand hand) {
        if (this.parentMob != null && (role == ROLE_TRIM_LEFT || role == ROLE_TRIM_RIGHT)) {
            // Anyone can trim by clicking the box — no need to be at the wheel or
            // even seated. Return SUCCESS on BOTH sides so the click is consumed
            // and never falls through to the boat's interact() (which would open
            // the ship menu). The actual trim happens server-side.
            if (!this.parentMob.isSinking()) {
                if (!this.level().isClientSide()) {
                    this.parentMob.adjustTrim(role == ROLE_TRIM_RIGHT
                        ? +CustomBoatEntity.TRIM_STEP : -CustomBoatEntity.TRIM_STEP);
                    this.level().playSound(null, blockPosition(),
                        net.minecraft.sounds.SoundEvents.WOOD_PLACE,
                        net.minecraft.sounds.SoundSource.BLOCKS, 0.6f, 1.2f);
                }
                return net.minecraft.world.InteractionResult.SUCCESS;
            }
            return net.minecraft.world.InteractionResult.PASS;
        }
        // HULL parts only → relay to the boat (boarding / repair / menu).
        if (this.parentMob != null) {
            return this.parentMob.interact(player, hand);
        }
        return net.minecraft.world.InteractionResult.PASS;
    }

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
     * TRIM hitboxes are click-only — NOT solid — so you don't bump them walking.
     */
    @Override
    public boolean canBeCollidedWith(@Nullable Entity entity) {
        if (role == ROLE_TRIM_LEFT || role == ROLE_TRIM_RIGHT) return false;
        if (entity == this.parentMob) return false;
        if (entity instanceof ShipPartEntity other && other.parentMob == this.parentMob) return false;
        return true;
    }

    @Override
    public boolean isPushable() {
        return false;
    }
}