package net.ent.entstupidstuff.api.ship;

import java.util.function.Supplier;

import net.ent.entstupidstuff.registry.EntityFactory;
import net.ent.entstupidstuff.sound.SoundFactory;
import net.minecraft.core.NonNullList;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.AbstractChestBoat;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.phys.Vec3;

import org.jetbrains.annotations.Nullable;

public class CustomBoatEntity extends AbstractChestBoat {

    // ════════════════════════════════════════════════════════════════
    //  CONSTANTS & TUNING
    // ════════════════════════════════════════════════════════════════
    private static final int INVENTORY_SIZE = 54;

    // --- parts (multi-hitbox) ---
    private static final float PART_OFFSET = 2.0f;
    private static final float PART_WIDTH  = 3.5f;
    private static final float PART_HEIGHT = 0.7f;

    // --- seats ---
    public static final int SEAT_COUNT = 7;
    private static final Vec3[] SEAT_OFFSETS = new Vec3[] {
        new Vec3( 0.0, 0.6, -3.5),  // 0 DRIVER      — stern
        new Vec3(-0.7, 0.0,  0.3),  // 1 LEFT  rear
        new Vec3( 0.7, 0.0,  0.3),  // 2 RIGHT rear
        new Vec3(-0.7, 0.0, -0.5),  // 3 LEFT  fwd
        new Vec3( 0.7, 0.0, -0.5),  // 4 RIGHT fwd
        new Vec3( 0.0, 0.0,  3.0),  // 5 BOW
        new Vec3( 0.0, 9.0, -0.5),  // 6 CROW'S NEST
    };

    // --- sail / anchor ---
    public  static final int   SAIL_MAX        = 3;       // 0 furled, 1=33%, 2=66%, 3=100%
    private static final float MAX_SAIL_THRUST = 0.07f;   // terminal speed ≈ thrust × 10

    // --- attachments (bow slot) ---
    public static final int ATTACHMENT_NONE    = 0;
    public static final int ATTACHMENT_HARPOON = 1;
    public static final int ATTACHMENT_CANNON  = 2;

    // --- deck carry ---
    private static final double DECK_HALF_LEN  = 4.0;   // bow<->stern reach
    private static final double DECK_HALF_WID  = 2.2;   // port<->starboard — match the actual hull width
    private static final double DECK_MIN_Y     = 0.1;   // must be above deck surface, not beside it
    private static final double DECK_MAX_Y     = 2.2;
    private static final double DECK_TOP       = 1.6;
    private static final double STICK_MARGIN   = 0.5;   // small slack to keep someone aboard through bumps

    // --- damage / sink ---
    public  static final float CANNONBALL_DAMAGE = 45f;
    public  static final float RAM_DAMAGE_SCALE  = 120f;
    private static final float FLOOD_THRESHOLD = 0.30f;
    private static final float FLOOD_RATE      = 0.06f;
    private static final float REPAIR_AMOUNT   = 25f;
    private static final int   SINK_DURATION   = 200;      // 10 seconds total
    private static final int   SINK_VISIBLE    = 160;      // visible for 8s, then underwater
    private static final double SINK_SPEED     = 0.045;

    // ── Sink animation fields ──
    private float sinkRoll       = 0f;     // current sideways tilt (radians)
    private float sinkRollTarget = 0f;     // which side to tilt toward
    private float sinkDepth      = 0f;     // cumulative depth sunk

    public float shipMaxHealth() { return 200f; }

    // --- steering (rudder-based, "memory foam" behavior) ---
    private static final float  TURN_RATE_MAX       = 1.2f;   // max yaw change per tick at full rudder + full speed
    private static final double TURN_SPEED_REF      = 0.45;   // forward speed for full rudder authority
    private static final float  RUDDER_SPEED        = 0.06f;  // how fast the rudder deflects while holding A/D
    private static final float  RUDDER_RETURN       = 0.02f;  // how fast it springs back to center (memory foam)
    /** Minimum rudder authority at standstill (0.0 = dead helm, 1.0 = full authority even at rest).
     *  Raise this to let the boat pivot faster at low speed / sails just raised.
     *  Lower it for a heavier, more realistic feel that only bites once you're moving. */
    private static final float  STANDSTILL_AUTHORITY = 0.25f;

    // ── Flooding visual ──
    private float floodDraft = 0f;           // how much lower the boat sits (blocks)
    private static final float MAX_FLOOD_DRAFT = 0.35f;  // max draft when fully flooding
    private static final float FLOOD_DRAFT_RATE = 0.005f; // how fast it sinks per tick

    // --- debug ---
    /** Toggle to show deck boundary particles. Flip in-game or set via /entstupidstuff debug. */
    public static boolean DEBUG_DECK = false;


    // ════════════════════════════════════════════════════════════════
    //  SYNCED DATA
    // ════════════════════════════════════════════════════════════════
    private static final EntityDataAccessor<Integer> PART_FRONT_ID =
        SynchedEntityData.defineId(CustomBoatEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> PART_REAR_ID =
        SynchedEntityData.defineId(CustomBoatEntity.class, EntityDataSerializers.INT);

    private static final EntityDataAccessor<Float> DATA_HEALTH =
        SynchedEntityData.defineId(CustomBoatEntity.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Integer> DATA_SINK_TICKS =     // -1 = not sinking
        SynchedEntityData.defineId(CustomBoatEntity.class, EntityDataSerializers.INT);

    private static final EntityDataAccessor<Integer> DATA_SAIL_LEVEL =     // 0..SAIL_MAX
        SynchedEntityData.defineId(CustomBoatEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> DATA_ANCHOR_STATE =   // 0 up, 1 down, 2 raising
        SynchedEntityData.defineId(CustomBoatEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> DATA_ANCHOR_ENTITY =
        SynchedEntityData.defineId(CustomBoatEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Float> DATA_RUDDER =         // -1.0 .. +1.0
        SynchedEntityData.defineId(CustomBoatEntity.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Integer> DATA_ATTACHMENT =    // 0 none, 1 harpoon, 2 cannon
        SynchedEntityData.defineId(CustomBoatEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> DATA_ACTIVE_HARPOON = // entity ID of deployed harpoon, -1 if none
        SynchedEntityData.defineId(CustomBoatEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> DATA_HAS_BANNER =
        SynchedEntityData.defineId(CustomBoatEntity.class, EntityDataSerializers.BOOLEAN);

    private static final EntityDataAccessor<Boolean> DATA_HAS_AMMO =
        SynchedEntityData.defineId(CustomBoatEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Float> DATA_BOW_YAW =
        SynchedEntityData.defineId(CustomBoatEntity.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Float> DATA_BOW_PITCH =
        SynchedEntityData.defineId(CustomBoatEntity.class, EntityDataSerializers.FLOAT);

    private static final EntityDataAccessor<Integer> DATA_CANNON_COOLDOWN =
        SynchedEntityData.defineId(CustomBoatEntity.class, EntityDataSerializers.INT);

    private static final EntityDataAccessor<Float> DATA_FLOOD_DRAFT =
        SynchedEntityData.defineId(CustomBoatEntity.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Float> DATA_SINK_ROLL =
        SynchedEntityData.defineId(CustomBoatEntity.class, EntityDataSerializers.FLOAT);

    @SuppressWarnings("unchecked")
    private static final EntityDataAccessor<Integer>[] SEAT_OCCUPANTS =
        new EntityDataAccessor[SEAT_COUNT];
    static {
        for (int i = 0; i < SEAT_COUNT; i++) {
            SEAT_OCCUPANTS[i] = SynchedEntityData.defineId(
                CustomBoatEntity.class, EntityDataSerializers.INT);
        }
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(PART_FRONT_ID, -1);
        builder.define(PART_REAR_ID, -1);
        builder.define(DATA_HEALTH, shipMaxHealth());
        builder.define(DATA_SINK_TICKS, -1);
        builder.define(DATA_SAIL_LEVEL, 0);
        builder.define(DATA_ANCHOR_STATE, 0);
        builder.define(DATA_ANCHOR_ENTITY, -1);
        builder.define(DATA_RUDDER, 0f);
        builder.define(DATA_ATTACHMENT, ATTACHMENT_NONE);
        builder.define(DATA_ACTIVE_HARPOON, -1);
        builder.define(DATA_HAS_BANNER, false);

        builder.define(DATA_BOW_YAW, 0f);
        builder.define(DATA_BOW_PITCH, 0f);
        builder.define(DATA_HAS_AMMO, false);

        builder.define(DATA_CANNON_COOLDOWN, 0);
        builder.define(DATA_FLOOD_DRAFT, 0f);
        builder.define(DATA_SINK_ROLL, 0f);

        for (int i = 0; i < SEAT_COUNT; i++) builder.define(SEAT_OCCUPANTS[i], -1);
    }


    // ════════════════════════════════════════════════════════════════
    //  FIELDS
    // ════════════════════════════════════════════════════════════════
    private NonNullList<ItemStack> inventory = NonNullList.withSize(INVENTORY_SIZE, ItemStack.EMPTY);
    @Nullable private ResourceKey<LootTable> lootTable;
    private long lootTableSeed;

    public final ShipPartEntity[] parts;
    private boolean partsSpawned = false;

    // per-tick movement (computed both sides, for carry / wake / sail anim)
    private double lastX, lastZ, lastY;
    private float  lastYaw;
    private boolean carrierInit = false;
    private double deckDX, deckDZ, deckDY;
    private float  deckDYaw;

    // anchor (server-only)
    // No timer needed — the AnchorEntity handles raise movement and discards itself on arrival.

    // rudder angle: -1.0 (hard port) to +1.0 (hard starboard)
    // Synced via DATA_RUDDER so all clients see the animation.
    public float  getRudderAngle() { return this.entityData.get(DATA_RUDDER); }
    private void  setRudderAngle(float a) { this.entityData.set(DATA_RUDDER, Mth.clamp(a, -1f, 1f)); }

    // bow attachment: NONE, HARPOON, or CANNON
    public int  getAttachment()       { return this.entityData.get(DATA_ATTACHMENT); }
    public void setAttachment(int type) { this.entityData.set(DATA_ATTACHMENT, type); }
    public boolean hasAttachment()    { return getAttachment() != ATTACHMENT_NONE; }

    // banner flag (controls whether burgee_sail shows the banner pattern)
    public boolean hasBanner()        { return entityData.get(DATA_HAS_BANNER); }
    public void    setBanner(boolean b) { entityData.set(DATA_HAS_BANNER, b); }

    public int getCannonCooldown() { return entityData.get(DATA_CANNON_COOLDOWN); }
    public int getCannonCooldownMax() { return CANNON_COOLDOWN_TICKS; }

    // The actual item in the attachment slot (persisted separately from the chest)
    private ItemStack attachmentStack = ItemStack.EMPTY;
    public ItemStack getAttachmentStack() { return attachmentStack; }
    public void setAttachmentStack(ItemStack stack) {
        this.attachmentStack = stack.isEmpty() ? ItemStack.EMPTY : stack.copy();
        // Sync the attachment TYPE from the item
        setAttachment(ShipMenu.AttachmentSlot.getAttachmentType(this.attachmentStack));
    }

    // ── Active harpoon (synced so client knows when one is deployed) ──
    public @Nullable HarpoonProjectileEntity getActiveHarpoon() {
        int id = entityData.get(DATA_ACTIVE_HARPOON);
        if (id < 0) return null;
        return level().getEntity(id) instanceof HarpoonProjectileEntity h ? h : null;
    }

    public boolean hasActiveHarpoon() {
        HarpoonProjectileEntity h = getActiveHarpoon();
        return h != null && !h.isRemoved();
    }

    public void fireHarpoon(float yaw, float pitch) {
        if (level().isClientSide() || hasActiveHarpoon()) return;
        if (getAttachment() != ATTACHMENT_HARPOON) return;
        if (!consumeAmmo()) return;   // ← NEW: must have ammo

        if (level() instanceof ServerLevel sl) {
            sl.playSound(null, blockPosition(),
                SoundEvents.ARROW_SHOOT, SoundSource.NEUTRAL, 1.0f, 1.3f);
        }

        HarpoonProjectileEntity h = new HarpoonProjectileEntity(
            net.ent.entstupidstuff.registry.EntityFactory.HARPOON, level());
        h.fire(this, yaw, pitch);
        level().addFreshEntity(h);
        entityData.set(DATA_ACTIVE_HARPOON, h.getId());
    }

    public void clearHarpoon() { entityData.set(DATA_ACTIVE_HARPOON, -1); }

    public void releaseHarpoon() {
        HarpoonProjectileEntity h = getActiveHarpoon();
        if (h != null) h.release();
        clearHarpoon();
    }

    // ── Bow gunner tracking ─────────────────────────────────────────

    /** Bow gunner's yaw relative to ship yaw, in radians. Used for swivel. */
    public float getBowRelativeYaw() { return entityData.get(DATA_BOW_YAW); }

    /** Bow gunner's pitch (elevation), in radians. Negative = looking up. */
    public float getBowPitch() { return entityData.get(DATA_BOW_PITCH); }

    /** Interpolated bow yaw for smooth rendering. */
    public float getBowGunnerRelativeYaw(float partialTick) {
        return entityData.get(DATA_BOW_YAW);
    }

    /** Synced ammo state for model rendering. */
    public boolean hasAmmoLoaded() { return entityData.get(DATA_HAS_AMMO); }

    public Entity getBowGunner() {
        int id = entityData.get(SEAT_OCCUPANTS[5]);
        if (id == -1) return null;
        return level().getEntity(id);
    }

    /** Check if the boat's inventory has ammo for the current attachment. */
    public boolean hasAmmoInInventory() {
        if (level().isClientSide()) return entityData.get(DATA_HAS_AMMO);
        int att = getAttachment();
        for (int i = 0; i < getContainerSize(); i++) {
            ItemStack stack = this.getItem(i);
            if (stack.isEmpty()) continue;
            String id = stack.getItem().toString().toLowerCase();
            if (att == ATTACHMENT_HARPOON && id.contains("harpoon")) return true;
            if (att == ATTACHMENT_CANNON  && id.contains("cannon_ball")) return true;
        }
        return false;
    }

    /** Consume one ammo item. Returns true if consumed. */
    public boolean consumeAmmo() {
        if (level().isClientSide()) return false;
        int att = getAttachment();
        for (int i = 0; i < getContainerSize(); i++) {
            ItemStack stack = this.getItem(i);
            if (stack.isEmpty()) continue;
            String id = stack.getItem().toString().toLowerCase();
            boolean match = (att == ATTACHMENT_HARPOON && id.contains("harpoon"))
                        || (att == ATTACHMENT_CANNON  && id.contains("cannon_ball"));
            if (match) {
                stack.shrink(1);
                if (stack.isEmpty()) this.setItem(i, ItemStack.EMPTY);
                return true;
            }
        }
        return false;
    }

    // ── Cannon ──────────────────────────────────────────────────────
    private int cannonCooldown = 0;
    private static final int CANNON_COOLDOWN_TICKS = 30;  // 1.5s between shots

    public boolean canFireCannon() {
        return !level().isClientSide()
            && getAttachment() == ATTACHMENT_CANNON
            && cannonCooldown <= 0
            && !isSinking();
    }

    public void fireCannon(float yaw, float pitch) {
        if (!canFireCannon()) return;
        if (!consumeAmmo()) return;   // must have cannonball in inventory

        ShipCannonballEntity ball = new ShipCannonballEntity(
            net.ent.entstupidstuff.registry.EntityFactory.SHIPCANNONBALL, level());
        ball.fire(this, yaw, pitch);
        level().addFreshEntity(ball);
        cannonCooldown = CANNON_COOLDOWN_TICKS;

        // Recoil
        double rad = Math.toRadians(getYRot());
        setDeltaMovement(getDeltaMovement().add(
            Math.sin(rad) * 0.04, 0, -Math.cos(rad) * 0.04));

        // Fire effects
        if (level() instanceof ServerLevel sl) {
            sl.playSound(null, blockPosition(),
                SoundFactory.COMBAT_CANNON_FIRE, SoundSource.NEUTRAL, 1.0f, 1.3f);
            double bowX = getX() - Math.sin(rad) * 5.0;
            double bowZ = getZ() + Math.cos(rad) * 5.0;
            sl.sendParticles(ParticleTypes.LARGE_SMOKE, bowX, getY() + 2.0, bowZ,
                6, 0.3, 0.2, 0.3, 0.02);
            sl.sendParticles(ParticleTypes.FLAME, bowX, getY() + 2.0, bowZ,
                3, 0.1, 0.1, 0.1, 0.01);
        }
    }

    /**
     * Launch a player out of the cannon — Sea of Thieves style!
     */
    public void launchPlayer(Player player, float yaw, float pitch) {
        if (level().isClientSide() || !isBowGunner(player) || isSinking()) return;
        if (getAttachment() != ATTACHMENT_CANNON) return;
        if (cannonCooldown > 0) return;

        cannonCooldown = CANNON_COOLDOWN_TICKS;
        player.stopRiding();

        double rad = Math.toRadians(getYRot());
        double bowX = getX() - Math.sin(rad) * 5.5;
        double bowZ = getZ() + Math.cos(rad) * 5.5;
        player.setPos(bowX, getY() + 2.5, bowZ);

        double launchSpeed = 1.8;
        double radYaw   = Math.toRadians(-yaw);
        double radPitch = Math.toRadians(-pitch);
        double cosP     = Math.cos(radPitch);
        player.setDeltaMovement(
            Math.sin(radYaw) * cosP * launchSpeed,
            Math.sin(radPitch) * launchSpeed + 0.3,
            Math.cos(radYaw) * cosP * launchSpeed);
        player.fallDistance = 0f;
        player.hurtMarked = true;

        if (level() instanceof ServerLevel sl) {
            sl.playSound(null, blockPosition(),
                SoundEvents.GENERIC_EXPLODE.value(), SoundSource.NEUTRAL, 1.2f, 1.5f);
            sl.sendParticles(ParticleTypes.LARGE_SMOKE, bowX, getY() + 2.0, bowZ,
                10, 0.4, 0.3, 0.4, 0.03);
        }
    }

    // deck mob tracking
    private final java.util.Set<java.util.UUID> deckRiders = new java.util.HashSet<>();

    // ── Velocity preservation across the mount/dismount transition ──
    // With the authoritative-instance physics pattern, momentum is normally
    // carried automatically (the instance that owns physics already holds
    // deltaMovement). We still snapshot it across a driver mount, because the
    // brief authority handoff (server → client) can otherwise drop a tick of
    // velocity. Restored immediately in addPassenger.
    private Vec3 preservedVelocity = null;

    @Override
    protected void removePassenger(Entity passenger) {
        if (isDriver(passenger)) {
            // Save momentum so the server (now authoritative) keeps sailing
            preservedVelocity = getDeltaMovement();
        }
        super.removePassenger(passenger);
        // Re-apply immediately so the handoff tick doesn't dead-stop
        if (preservedVelocity != null && Math.hypot(preservedVelocity.x, preservedVelocity.z) > 0.01) {
            setDeltaMovement(preservedVelocity);
        }
    }

    @Override
    protected void addPassenger(Entity passenger) {
        Vec3 before = getDeltaMovement();
        super.addPassenger(passenger);
        // When the driver boards, hand the current momentum to their client
        // so it doesn't start from zero.
        if (isDriver(passenger)) {
            Vec3 source = (preservedVelocity != null) ? preservedVelocity : before;
            if (Math.hypot(source.x, source.z) > 0.01) {
                setDeltaMovement(source);
            }
            preservedVelocity = null;
        }
    }


    // ════════════════════════════════════════════════════════════════
    //  CONSTRUCTORS
    // ════════════════════════════════════════════════════════════════
    public CustomBoatEntity(EntityType<? extends CustomBoatEntity> type, Level world, Supplier<Item> boatItem) {
        super(type, world, boatItem);
        this.parts = new ShipPartEntity[] {
            new ShipPartEntity(this, PART_WIDTH, PART_HEIGHT),  // front
            new ShipPartEntity(this, PART_WIDTH, PART_HEIGHT)   // rear
        };
    }

    public CustomBoatEntity(EntityType<? extends CustomBoatEntity> type, Level world) {
        this(type, world, () -> Items.OAK_BOAT);
    }


    // ════════════════════════════════════════════════════════════════
    //  CORE OVERRIDES
    // ════════════════════════════════════════════════════════════════
    @Override protected int getMaxPassengers()            { return SEAT_COUNT; }
    @Override protected float getSinglePassengerXOffset() { return 0.75f; }
    @Override public int getContainerSize()               { return INVENTORY_SIZE; }
    @Override protected double rideHeight(EntityDimensions d) { return 0.15F; }

    @Override public NonNullList<ItemStack> getItemStacks() { return super.getItemStacks(); }

    @Override
    public void clearItemStacks() {
        super.clearItemStacks();
        this.inventory = NonNullList.withSize(INVENTORY_SIZE, ItemStack.EMPTY);
    }

    // ── Save/Load: persist attachment + banner across world reloads ──
    @Override
    protected void addAdditionalSaveData(ValueOutput out) {
        super.addAdditionalSaveData(out);
        out.putInt("ShipAttachmentType", getAttachment());
        out.putBoolean("ShipHasBanner", hasBanner());
        // Save the attachment item so the slot isn't empty on rejoin
        if (!attachmentStack.isEmpty()) {
            out.putString("ShipAttachmentItemId",
                net.minecraft.core.registries.BuiltInRegistries.ITEM
                    .getKey(attachmentStack.getItem()).toString());
        }
    }

    @Override
    protected void readAdditionalSaveData(ValueInput in) {
        super.readAdditionalSaveData(in);
        this.setAttachment(in.getInt("ShipAttachmentType").orElse(0));
        this.entityData.set(DATA_HAS_BANNER, in.getBooleanOr("ShipHasBanner", false));
        // Restore the attachment ItemStack from the saved item ID
        in.getString("ShipAttachmentItemId").ifPresent(idStr -> {
            try {
                net.minecraft.resources.ResourceLocation id =
                    net.minecraft.resources.ResourceLocation.parse(idStr);
                net.minecraft.world.item.Item item =
                    net.minecraft.core.registries.BuiltInRegistries.ITEM.getValue(id);
                if (item != null && item != net.minecraft.world.item.Items.AIR) {
                    this.attachmentStack = new ItemStack(item);
                }
            } catch (Exception e) {
                // Invalid ID — leave stack empty, type is still set from the int
            }
        });
    }

    // NOTE: Vanilla controlBoat() is private in AbstractBoat, so we can't override it.
    // ShipControlMixin cancels it at HEAD and calls our steer()/applySailThrust()/
    // applyChainConstraint() instead. See ShipControlMixin.java.

    @Override
    public InteractionResult interact(Player player, InteractionHand hand) {
        // ── Block boarding from underwater ──
        // Players swimming under the hull shouldn't be able to mount.
        // They must be at or above deck level to board.
        if (!player.isShiftKeyDown() && player.isUnderWater() && player.getY() < getY()) {
            return InteractionResult.PASS;
        }

        // Sneak + right-click → open ship management GUI
        if (player.isShiftKeyDown() && !isSinking()) {
            if (!this.level().isClientSide()) {
                player.openMenu(new net.minecraft.world.SimpleMenuProvider(
                    (syncId, inv, p) -> new ShipMenu(syncId, inv, this),
                    net.minecraft.network.chat.Component.literal("Ship")));
            }
            return InteractionResult.SUCCESS;
        }

        // Repair: right-click hull with planks
        ItemStack held = player.getItemInHand(hand);
        if (held.is(ItemTags.PLANKS) && getHealth() < shipMaxHealth() && !isSinking()) {
            if (!this.level().isClientSide()) {
                setHealth(getHealth() + REPAIR_AMOUNT);
                if (!player.getAbilities().instabuild) held.shrink(1);
                this.level().playSound(null, blockPosition(),
                    SoundEvents.WOOD_PLACE, SoundSource.BLOCKS, 1.0f, 1.0f);
            }
            return InteractionResult.SUCCESS;
        }

        // ── Direct bow seat: click near the bow to sit at the attachment ──
        if (hasAttachment() && !isSinking() && !player.isPassenger()) {
            // Transform player pos to ship-local coordinates
            double dx = player.getX() - getX();
            double dz = player.getZ() - getZ();
            double yaw = Math.toRadians(getYRot());
            double cos = Math.cos(-yaw), sin = Math.sin(-yaw);
            double localZ = dx * sin + dz * cos;  // forward in ship space

            // If player is in the front half of the ship AND seat 5 is free
            if (localZ > 1.5 && this.entityData.get(SEAT_OCCUPANTS[5]) == -1) {
                if (!this.level().isClientSide() && this.canAddPassenger(player)) {
                    player.startRiding(this);
                    // Force into bow seat (seat 5) instead of first free
                    int autoSeat = seatOf(player);
                    if (autoSeat != -1 && autoSeat != 5) {
                        this.entityData.set(SEAT_OCCUPANTS[autoSeat], -1);
                    }
                    this.entityData.set(SEAT_OCCUPANTS[5], player.getId());
                }
                return InteractionResult.SUCCESS;
            }
        }

        // Normal boarding (seat swap is a separate keybind)
        InteractionResult result = super.interact(player, hand);
        if (result != InteractionResult.PASS) return result;
        if (this.canAddPassenger(player)) {
            if (!this.level().isClientSide()) player.startRiding(this);
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }

    @Override
    public void destroy(ServerLevel world, DamageSource source) {
        this.getItemStacks().forEach(s -> { if (!s.isEmpty()) this.spawnAtLocation(world, s); });
        this.remove(RemovalReason.KILLED);   // no super call → no boat item drop
    }

    @Override
    public void remove(RemovalReason reason) {
        super.remove(reason);
        if (parts != null) for (ShipPartEntity p : parts) if (p != null) p.remove(reason);
        // also clean up the anchor entity if it exists
        AnchorEntity anchor = getAnchor();
        if (anchor != null) anchor.discard();
    }

    @Override
    public boolean canCollideWith(Entity entity) {
        if (entity instanceof ShipPartEntity part && part.parentMob == this) return false;
        return super.canCollideWith(entity);
    }

    /**
     * Ignore server velocity corrections while the driver's client owns the
     * physics. (Same fix the cars use.)
     *
     * Entity tracking periodically broadcasts the server's velocity to all
     * clients. On the driver's client — which is the authoritative instance
     * running the physics — that correction would OVERWRITE the locally
     * computed velocity, causing periodic speed dips and killing momentum.
     * So on the authoritative client we drop the correction; everyone else
     * (deck walkers, empty ships) interpolates normally.
     */
    @Override
    public void lerpMotion(Vec3 vec3) {
        if (this.level().isClientSide() && isLocalInstanceAuthoritative()) {
            return;   // driver's client owns velocity — ignore server correction
        }
        super.lerpMotion(vec3);
    }

    /**
     * Prevent passengers from pushing/hurting deck walkers.
     * In vanilla, passengers can still push nearby entities.
     * We override this to make the boat "safe" for mixed crews
     * (some riding, some walking the deck).
     */
    @Override
    protected boolean canAddPassenger(Entity passenger) {
        // Don't allow underwater players to board
        if (passenger instanceof Player p && p.isUnderWater() && p.getY() < getY()) {
            return false;
        }
        return this.getPassengers().size() < this.getMaxPassengers();
    }

    /**
     * Check if an entity is a passenger of this boat OR a deck walker.
     * Used to prevent friendly-fire collisions between crew members.
     */
    public boolean isCrewMember(Entity e) {
        if (this.hasPassenger(e)) return true;
        if (deckRiders.contains(e.getUUID())) return true;
        return false;
    }

    public ShipPartEntity[] getSubEntities() { return this.parts; }


    // ════════════════════════════════════════════════════════════════
    //  STEERING  (momentum-based rudder)
    // ════════════════════════════════════════════════════════════════

    /**
     * Called by ShipControlMixin when a helmsman is aboard.
     *
     * "Memory foam" rudder:
     *  - Holding A/D gradually deflects the rudder further from center
     *  - The more you hold, the sharper the turn becomes
     *  - Releasing lets the rudder slowly spring back to center
     *  - The boat's actual turn rate = rudder angle × speed authority
     *
     * The rudder angle is synced via DATA_RUDDER so the model animates on all clients.
     */
    public void steer(boolean left, boolean right) {
        float angle = getRudderAngle();

        // Deflect rudder while holding A/D
        if (left)  angle -= RUDDER_SPEED;
        if (right) angle += RUDDER_SPEED;

        // Spring return to center when released (memory foam)
        if (!left && !right) {
            if (Math.abs(angle) <= RUDDER_RETURN) {
                angle = 0f;
            } else {
                angle -= Math.signum(angle) * RUDDER_RETURN;
            }
        }

        setRudderAngle(angle);  // clamps to [-1, 1] and syncs to clients

        // NOTE: The actual boat rotation + momentum re-aim now happens
        // SERVER-SIDE in tick(), reading this synced rudder angle. We do NOT
        // turn here — steer() runs client-side via the mixin's controlBoat(),
        // and if it also turned, the boat would rotate twice (client + server).
        // steer()'s only job is to set the rudder angle from A/D input.
    }



    // ════════════════════════════════════════════════════════════════
    //  TICK
    // ════════════════════════════════════════════════════════════════
    @Override
    public void tick() {
        super.tick();
        computeDeckTransform();
        tickWake();
        tickDebugDeck();

        // --- spawn multi-hitbox parts on first server tick ---
        if (!this.level().isClientSide() && !partsSpawned) {
            for (ShipPartEntity part : parts) this.level().addFreshEntity(part);
            this.entityData.set(PART_FRONT_ID, parts[0].getId());
            this.entityData.set(PART_REAR_ID,  parts[1].getId());
            partsSpawned = true;
        }

        // --- server-side bookkeeping (NOT movement) ---
        if (!this.level().isClientSide()) {
            updateSeats();
            carryDeckMobs();
            tickDamage();
            tickAnchor();

            // ── Bow gunner swivel + pitch + ammo ──
            entityData.set(DATA_HAS_AMMO, hasAmmoInInventory());

            Entity bowGunner = getBowGunner();
            if (bowGunner != null && hasAttachment()) {
                float rawYawDeg = Mth.wrapDegrees(bowGunner.getYRot() - getYRot());
                float clampedYawDeg = Mth.clamp(rawYawDeg, -MAX_YAW_DEGREES, MAX_YAW_DEGREES);
                entityData.set(DATA_BOW_YAW, (float) Math.toRadians(clampedYawDeg));

                float pitchDeg = bowGunner.getXRot();
                float clampedPitchDeg = Mth.clamp(pitchDeg, MAX_PITCH_UP, MAX_PITCH_DOWN);
                entityData.set(DATA_BOW_PITCH, (float) Math.toRadians(clampedPitchDeg));
            }

            if (cannonCooldown > 0) cannonCooldown--;
            entityData.set(DATA_CANNON_COOLDOWN, cannonCooldown);
        }

        // ── SHIP MOVEMENT — runs on the AUTHORITATIVE instance only ──────
        // This mirrors the working CAR pattern:
        //   isLocalInstanceAuthoritative() is TRUE on:
        //     • the driver's client   (they own the physics, responsive)
        //     • the server when empty (so a driverless ship still sails)
        //   It is FALSE on:
        //     • non-driver clients (deck walkers) → they just interpolate
        //     • the server while a driver is aboard → driver's client owns it
        //
        // Because the SAME code runs on whichever instance is authoritative,
        // there's no fighting: the driver computes velocity, vanilla move()
        // integrates it, and the position syncs to everyone. When the driver
        // leaves, authority hands to the server WITHOUT zeroing velocity
        // (see lerpMotion override), so momentum + turn continue seamlessly.
        // ── AUTHORITY DECISION ──────────────────────────────────────────
        // The debug revealed the core bug: on the SERVER,
        // isLocalInstanceAuthoritative() is ALWAYS true (even while a client
        // drives), because our custom seat system doesn't register the player
        // as a vanilla "controlling passenger". Result: server AND client both
        // ran physics and fought — the server's no-input physics won and the
        // ship barely moved / wouldn't turn.
        //
        // Fix: decide authority from the ACTUAL seat state, which is synced:
        //   • Driver aboard  → the CLIENT (specifically the driver's client)
        //     owns physics; the server does NOT run it.
        //   • No driver      → the SERVER owns physics (drifting ship sails on).
        boolean driverAboard = getControllingPassenger() != null;
        boolean clientSide    = this.level().isClientSide();

        boolean runPhysics;
        if (driverAboard) {
            // Only the driver's client runs it. On the client, that's when this
            // client is the authoritative instance (the local driver). The
            // server must NOT run physics while a driver is aboard.
            runPhysics = clientSide && isLocalInstanceAuthoritative();
        } else {
            // Empty ship → server drives it; clients just interpolate.
            runPhysics = !clientSide;
        }

        if (runPhysics && !isSinking()) {
            float angle = getRudderAngle();
            if (Math.abs(angle) > 0.001f) {
                // Turn from the synced rudder angle
                double spd = getDeltaMovement().horizontalDistance();
                float auth = (float) Math.max(STANDSTILL_AUTHORITY,
                    Math.min(1.0, spd / TURN_SPEED_REF));
                setYRot(getYRot() + angle * TURN_RATE_MAX * auth);

                // Re-aim momentum along the new heading so the ship curves
                double curSpeed = getDeltaMovement().horizontalDistance();
                if (curSpeed > 0.001) {
                    double r = Math.toRadians(getYRot());
                    Vec3 v = getDeltaMovement();
                    setDeltaMovement(-Math.sin(r) * curSpeed, v.y, Math.cos(r) * curSpeed);
                }

                // Spring rudder back to center when no one is actively steering
                if (getControllingPassenger() == null) {
                    angle -= Math.signum(angle) * RUDDER_RETURN;
                    if (Math.abs(angle) < RUDDER_RETURN) angle = 0f;
                    setRudderAngle(angle);
                }
            }

            applySailThrust();
            applyChainConstraint();
        }

        // --- keep the hitbox parts glued fore/aft ---
        ShipPartEntity front = this.level().isClientSide() ? getWorldPart(PART_FRONT_ID) : parts[0];
        ShipPartEntity rear  = this.level().isClientSide() ? getWorldPart(PART_REAR_ID)  : parts[1];
        if (front == null || rear == null) return;
        if (this.level().isClientSide()) { front.parentMob = this; rear.parentMob = this; }

        double rad = Math.toRadians(this.getYRot());
        double sinY = Math.sin(rad), cosY = Math.cos(rad);
        front.setPos(getX() - sinY * PART_OFFSET, getY(), getZ() + cosY * PART_OFFSET);
        rear.setPos (getX() + sinY * PART_OFFSET, getY(), getZ() - cosY * PART_OFFSET);

        applySinkMotion();
        // BUG FIX: Removed ShipHud.healthBar(this) — it was called every tick,
        // built a Component, and threw it away. The HUD renders via HudRenderCallback.

        debugAttachmentParticles();
    }

    private void computeDeckTransform() {
        if (!carrierInit) {
            lastX = getX(); lastZ = getZ(); lastY = getY(); lastYaw = getYRot();
            carrierInit = true;
            deckDX = deckDZ = deckDY = 0; deckDYaw = 0;
            return;
        }
        deckDX = getX() - lastX; deckDZ = getZ() - lastZ; deckDY = getY() - lastY;
        deckDYaw = getYRot() - lastYaw;
        lastX = getX(); lastZ = getZ(); lastY = getY(); lastYaw = getYRot();
    }

    public double getDeckDX()   { return deckDX; }
    public double getDeckDY()   { return deckDY; }
    public double getDeckDZ()   { return deckDZ; }
    public float  getDeckDYaw() { return deckDYaw; }

    public double getHorizontalSpeed() { return Math.hypot(getDeckDX(), getDeckDZ()); }

    /** Signed forward speed: + ahead, - astern. For sail-billow / bow-pitch in the renderer. */
    public float getForwardSpeed() {
        double rad = Math.toRadians(getYRot());
        return (float) (-Math.sin(rad) * getDeckDX() + Math.cos(rad) * getDeckDZ());
    }


    // ════════════════════════════════════════════════════════════════
    //  SAIL & ANCHOR  (unified system — state + entity)
    //
    //  The state (DATA_ANCHOR_STATE) drives the game logic:
    //    0 = up, 1 = down, 2 = raising
    //  The entity (DATA_ANCHOR_ENTITY / AnchorEntity) provides the
    //  physical drag-and-snag behavior from the SeaOfThieves design.
    //  Both are managed together by toggleAnchor().
    // ════════════════════════════════════════════════════════════════
    public int   getSailLevel()    { return this.entityData.get(DATA_SAIL_LEVEL); }
    public float getSailFraction() { return getSailLevel() / (float) SAIL_MAX; } // 0, .33, .66, 1
    public int   getAnchorState()  { return this.entityData.get(DATA_ANCHOR_STATE); }
    public boolean isAnchorHolding() { return getAnchorState() != 0; }  // down OR raising → can't sail
    public boolean isAnchored()      { return getAnchorState() == 1; }
    public boolean isRaisingAnchor() { return getAnchorState() == 2; }
    public boolean isAnchorDeployed() { return entityData.get(DATA_ANCHOR_ENTITY) >= 0; }

    private void setSailLevel(int lvl) { this.entityData.set(DATA_SAIL_LEVEL, Mth.clamp(lvl, 0, SAIL_MAX)); }

    public void raiseSail() { if (!level().isClientSide()) setSailLevel(getSailLevel() + 1); }
    public void lowerSail() { if (!level().isClientSide()) setSailLevel(getSailLevel() - 1); }
    public void furlSail()  { if (!level().isClientSide()) setSailLevel(0); }

    /** WIND HOOK — 1.0 until the wind pass adds point-of-sail + trim. */
    public float getSailEfficiency() { return 1.0f; }

    /** Net forward thrust: sail level × wind. Chain constraint handles anchor stopping. */
    public float getEffectiveThrust() {
        if (isSinking()) return 0f;
        return getSailFraction() * MAX_SAIL_THRUST * getSailEfficiency();
    }

    public @Nullable AnchorEntity getAnchor() {
        int id = entityData.get(DATA_ANCHOR_ENTITY);
        if (id < 0) return null;
        return (level().getEntity(id) instanceof AnchorEntity a) ? a : null;
    }

    /**
     * Anchor toggle:
     *   Up (0) → deploy anchor entity, set state 1. Boat keeps sailing — the
     *            chain constraint is the only thing that stops it, once the
     *            anchor snags on a block.
     *   Deployed (1) → begin raising, set state 2. The AnchorEntity flies
     *                   back toward the ship and discards itself on arrival.
     *   Raising (2) → ignore, wait for it to finish.
     */
    public void toggleAnchor() {
        if (level().isClientSide()) return;
        int s = getAnchorState();
        if (s == 0) {
            dropAnchor();
        } else if (s == 1) {
            beginRaiseAnchor();
        }
    }

    private void dropAnchor() {
        this.entityData.set(DATA_ANCHOR_STATE, 1);
        // NO furlSail() — the boat keeps sailing. The anchor drags behind it
        // until it snags on a block, then the chain constraint stops the ship.

        // Spawn the physical anchor entity
        AnchorEntity a = new AnchorEntity(EntityFactory.ANCHOR, level());
        a.setShipId(getId());
        a.place(getX(), getY() + 0.5, getZ());
        a.setDeltaMovement(0, -0.3, 0);   // initial drop velocity
        level().addFreshEntity(a);
        entityData.set(DATA_ANCHOR_ENTITY, a.getId());

        level().playSound(null, blockPosition(), SoundEvents.CHAIN_PLACE, SoundSource.NEUTRAL, 1f, 0.6f);
    }

    private void beginRaiseAnchor() {
        this.entityData.set(DATA_ANCHOR_STATE, 2);
        // The AnchorEntity detects isRaisingAnchor() on the ship and starts
        // flying back. When it arrives, it discards itself; tickAnchor() notices.
        level().playSound(null, blockPosition(), SoundEvents.CHAIN_PLACE, SoundSource.NEUTRAL, 1f, 1.2f);
    }

    private void tickAnchor() {
        // Raising: the anchor entity moves itself toward the ship.
        // Once it arrives and discards itself, we clear the state.
        if (getAnchorState() == 2) {
            AnchorEntity a = getAnchor();
            if (a == null || a.isRemoved()) {
                entityData.set(DATA_ANCHOR_ENTITY, -1);
                this.entityData.set(DATA_ANCHOR_STATE, 0);
            }
        }

        // Safety: if the entity got removed externally, clear the reference
        if (entityData.get(DATA_ANCHOR_ENTITY) >= 0 && getAnchor() == null) {
            entityData.set(DATA_ANCHOR_ENTITY, -1);
            if (getAnchorState() == 1) this.entityData.set(DATA_ANCHOR_STATE, 0);
        }
    }

    /** Forward drive from the sails. */
    public void applySailThrust() {
        float thrust = getEffectiveThrust();
        if (thrust <= 0f) return;
        double rad = Math.toRadians(getYRot());
        this.setDeltaMovement(this.getDeltaMovement().add(
            -Math.sin(rad) * thrust, 0.0, Math.cos(rad) * thrust));
    }

    /**
     * Rigid chain: she can't get farther than CHAIN_LENGTH from a deployed anchor.
     * BUG FIX: Now runs unconditionally in the server tick section.
     * Previously gated behind getControllingPassenger()==null && !isAnchorHolding(),
     * which meant it could never activate (if the anchor was holding, the gate blocked it).
     */
    public void applyChainConstraint() {
        AnchorEntity a = getAnchor();
        if (a == null) return;

        // light drag while the anchor is plowing the seabed
        if (a.onGround()) {
            Vec3 v = getDeltaMovement();
            setDeltaMovement(v.x * 0.97, v.y, v.z * 0.97);
        }

        double dx = getX() - a.getX(), dz = getZ() - a.getZ();
        double d = Math.sqrt(dx * dx + dz * dz);
        if (d <= AnchorEntity.CHAIN_LENGTH || d < 1e-4) return;

        // yank back to chain length
        double nx = dx / d, nz = dz / d, over = d - AnchorEntity.CHAIN_LENGTH;
        setPos(getX() - nx * over, getY(), getZ() - nz * over);

        // kill outward velocity
        Vec3 v = getDeltaMovement();
        double out = v.x * nx + v.z * nz;
        if (out > 0) setDeltaMovement(v.x - nx * out, v.y, v.z - nz * out);
    }


    // ════════════════════════════════════════════════════════════════
    //  WAKE  (client visuals)
    // ════════════════════════════════════════════════════════════════
    private void tickWake() {
        if (!this.level().isClientSide() || isSinking()) return;
        double speed = getHorizontalSpeed();
        if (speed < 0.06) return;

        double rad = Math.toRadians(this.getYRot());
        double bx = getX() - Math.sin(rad) * 2.2;
        double bz = getZ() + Math.cos(rad) * 2.2;
        int count = speed > 0.2 ? 3 : 1;
        for (int i = 0; i < count; i++) {
            this.level().addParticle(ParticleTypes.SPLASH,
                bx + (this.random.nextDouble() - 0.5) * 1.5, getY() + 0.15,
                bz + (this.random.nextDouble() - 0.5) * 1.5, 0, 0, 0);
        }
    }


    // ════════════════════════════════════════════════════════════════
    //  DEBUG  (toggle with CustomBoatEntity.DEBUG_DECK = true)
    // ════════════════════════════════════════════════════════════════
    private void tickDebugDeck() {
        if (!DEBUG_DECK || !this.level().isClientSide() || this.tickCount % 2 != 0) return;

        double yaw = Math.toRadians(getYRot());
        double cos = Math.cos(yaw), sin = Math.sin(yaw);
        double cx = getX(), cy = getY(), cz = getZ();

        // The 4 corners of the deck floor in local space:
        //   (±DECK_HALF_WID, DECK_MIN_Y, ±DECK_HALF_LEN)
        //   lx = port/starboard, lz = fore/aft
        double[][] corners = {
            { -DECK_HALF_WID, -DECK_HALF_LEN },  // port stern
            {  DECK_HALF_WID, -DECK_HALF_LEN },  // starboard stern
            {  DECK_HALF_WID,  DECK_HALF_LEN },  // starboard bow
            { -DECK_HALF_WID,  DECK_HALF_LEN },  // port bow
        };

        // Draw edges between corners (particles along each edge)
        int pointsPerEdge = 8;
        for (int i = 0; i < 4; i++) {
            double[] a = corners[i];
            double[] b = corners[(i + 1) % 4];
            for (int j = 0; j <= pointsPerEdge; j++) {
                double t = j / (double) pointsPerEdge;
                double lx = a[0] + (b[0] - a[0]) * t;
                double lz = a[1] + (b[1] - a[1]) * t;

                // local → world: R = (cos, sin), F = (-sin, cos)
                double wx = cx + lx * cos - lz * sin;
                double wz = cz + lx * sin + lz * cos;

                // Floor outline (DECK_MIN_Y)
                this.level().addParticle(ParticleTypes.FLAME,
                    wx, cy + DECK_MIN_Y, wz, 0, 0, 0);

                // Ceiling outline (DECK_MAX_Y)
                this.level().addParticle(ParticleTypes.END_ROD,
                    wx, cy + DECK_MAX_Y, wz, 0, 0, 0);
            }
        }

        // Vertical pillars at corners
        for (double[] c : corners) {
            double wx = cx + c[0] * cos - c[1] * sin;
            double wz = cz + c[0] * sin + c[1] * cos;
            for (double dy = DECK_MIN_Y; dy <= DECK_MAX_Y; dy += 0.5) {
                this.level().addParticle(ParticleTypes.ELECTRIC_SPARK,
                    wx, cy + dy, wz, 0, 0, 0);
            }
        }
    }

    // ════════════════════════════════════════════════════════════════
    //  DAMAGE & SINK
    // ════════════════════════════════════════════════════════════════
    public float   getHealth()       { return this.entityData.get(DATA_HEALTH); }
    public float   getHealthPct()    { return getHealth() / shipMaxHealth(); }
    public boolean isFlooding()      { return !isSinking() && getHealth() > 0 && getHealthPct() < FLOOD_THRESHOLD; }
    public boolean isSinking()       { return this.entityData.get(DATA_SINK_TICKS) >= 0; }
    public float   getSinkProgress() { return isSinking() ? Mth.clamp(this.entityData.get(DATA_SINK_TICKS) / (float) SINK_DURATION, 0f, 1f) : 0f; }
    public float   getSinkRoll()     { return entityData.get(DATA_SINK_ROLL); }
    public float   getFloodDraft()   { return entityData.get(DATA_FLOOD_DRAFT); }

    private void setHealth(float h) { this.entityData.set(DATA_HEALTH, Mth.clamp(h, 0f, shipMaxHealth())); }

    @Override
    public boolean hurtServer(ServerLevel level, DamageSource source, float amount) {
        if (this.isRemoved() || isSinking()) return false;
        if (this.isInvulnerableToBase(source)) return false;
        if (source.getEntity() instanceof Player p && p.getAbilities().instabuild) { startSinking(); return true; }
        damageShip(amount);
        return true;
    }

    public void damageShip(float amount) {
        if (this.level().isClientSide() || isSinking() || amount <= 0) return;
        setHealth(getHealth() - amount);
        this.setHurtDir(-this.getHurtDir());
        this.setHurtTime(10);
        this.setDamage(this.getDamage() + amount);
        if (this.level() instanceof ServerLevel sl) {
            sl.sendParticles(ParticleTypes.CRIT, getX(), getY() + 0.5, getZ(), 8, 1.2, 0.4, 1.2, 0.1);
            sl.playSound(null, blockPosition(), SoundEvents.WOOD_BREAK, SoundSource.NEUTRAL, 0.8f, 0.9f);
        }
        if (getHealth() <= 0f) startSinking();
    }

    public void hitByCannonball() { damageShip(CANNONBALL_DAMAGE); }

    private void startSinking() {
        if (isSinking()) return;
        setHealth(0f);
        this.entityData.set(DATA_SINK_TICKS, 0);

        // Randomize which way the boat tilts as it sinks
        sinkRollTarget = (this.random.nextBoolean() ? 1f : -1f) * 0.8f;
        sinkRoll = 0f;
        sinkDepth = 0f;

        if (this.level() instanceof ServerLevel sl)
            sl.playSound(null, blockPosition(), SoundEvents.GENERIC_EXPLODE.value(), SoundSource.NEUTRAL, 1.0f, 0.7f);
    }

    private void tickDamage() {
        // ── Flooding: gradual damage + boat sits lower in water ──
        if (isFlooding()) {
            setHealth(getHealth() - FLOOD_RATE);
            float prevDraft = floodDraft;
            floodDraft = Math.min(floodDraft + FLOOD_DRAFT_RATE, MAX_FLOOD_DRAFT);
            // Apply only the CHANGE in draft, not the total
            // (applying the total each tick is quadratic — boat plunges through the world)
            float delta = floodDraft - prevDraft;
            if (delta > 0f) {
                this.setPos(getX(), getY() - delta, getZ());
            }
            if (this.level() instanceof ServerLevel sl && this.tickCount % 4 == 0)
                sl.sendParticles(ParticleTypes.BUBBLE_COLUMN_UP, getX(), getY(), getZ(), 6, 1.5, 0.1, 1.5, 0.0);
            if (getHealth() <= 0f) startSinking();
        } else if (!isSinking()) {
            // Slowly recover draft when no longer flooding (boat rises back up)
            float prevDraft = floodDraft;
            floodDraft = Math.max(floodDraft - FLOOD_DRAFT_RATE * 2, 0f);
            float delta = prevDraft - floodDraft;
            if (delta > 0f) {
                this.setPos(getX(), getY() + delta, getZ());
            }
        }
        entityData.set(DATA_FLOOD_DRAFT, floodDraft);

        // ── Sinking: phased animation over 10 seconds ──
        if (isSinking()) {
            int t = this.entityData.get(DATA_SINK_TICKS) + 1;
            this.entityData.set(DATA_SINK_TICKS, t);

            // Phase 1 (0-60 ticks, 3s): tilt slowly, barely sink
            // Phase 2 (60-120 ticks, 3s): tilt more, going under
            // Phase 3 (120-160 ticks, 2s): fully tilted, plunging
            // Phase 4 (160-200 ticks, 2s): underwater, disappearing
            float progress = (float) t / SINK_DURATION;

            // Roll: gradually tilt to one side
            sinkRoll = Mth.lerp(Math.min(progress * 2f, 1f), 0f, sinkRollTarget);
            entityData.set(DATA_SINK_ROLL, sinkRoll);

            // Bubbles while sinking
            if (this.level() instanceof ServerLevel sl && t % 3 == 0) {
                sl.sendParticles(ParticleTypes.BUBBLE, getX(), getY() + 0.3, getZ(),
                    20, 2.0, 0.3, 2.0, 0.05);
                if (t % 5 == 0) {
                    sl.sendParticles(ParticleTypes.BUBBLE_COLUMN_UP,
                        getX() + random.nextGaussian() * 2, getY() + 0.5,
                        getZ() + random.nextGaussian() * 2, 3, 0.5, 0, 0.5, 0.01);
                }
            }

            // Eject all passengers when mostly underwater
            if (t == SINK_VISIBLE) {
                this.ejectPassengers();
            }

            if (t >= SINK_DURATION) sinkAndRemove();
        }
    }

    private void applySinkMotion() {
        if (!isSinking()) return;

        int t = this.entityData.get(DATA_SINK_TICKS);

        // Phased sinking speed — slow at first, accelerating
        double sinkRate;
        if (t < 60) {
            sinkRate = 0.005;    // Phase 1: barely sinking
        } else if (t < 120) {
            sinkRate = 0.020;    // Phase 2: going under
        } else {
            sinkRate = 0.045;    // Phase 3+: plunging
        }

        // Check if the hull has hit the ocean floor
        boolean grounded = level().getBlockState(blockPosition().below()).isSolid();
        if (!grounded) {
            this.setPos(getX(), getY() - sinkRate, getZ());
        }

        // Kill forward momentum — dead in the water
        Vec3 dm = this.getDeltaMovement();
        this.setDeltaMovement(dm.x * 0.93, grounded ? 0 : -sinkRate, dm.z * 0.93);
    }

    private void sinkAndRemove() {
        if (this.level() instanceof ServerLevel sl)
            this.getItemStacks().forEach(s -> { if (!s.isEmpty()) this.spawnAtLocation(sl, s); });
        this.remove(RemovalReason.KILLED);
    }


    // ════════════════════════════════════════════════════════════════
    //  SEATS
    // ════════════════════════════════════════════════════════════════
    // ── Cannon/harpoon pivot in ship-local coordinates ──
    // Tuned to match the model's attachment position.
    // The model attachment is at (67, -21.4, 77.3) in bottom-space,
    // which lands ~3.2 blocks forward of the entity center.
    /*private static final double PIVOT_X = 0.0;
    private static final double PIVOT_Z = 3.2;
    private static final double PIVOT_Y = 1.1;    // raised to sit ON the model seat

    // Seat offset FROM the pivot (behind the cannon)
    private static final double SEAT_BEHIND = -0.85;  // ~0.85 blocks behind pivot
    private static final double SEAT_SIDE   = 0.0;    // centered behind cannon*/

    private static final double MODEL_TO_BLOCK = 1.15 / 16.0;  // 0.071875

    // These are computed from the model, not hand-tuned:
    private static final double PIVOT_X =  0.3204 * MODEL_TO_BLOCK; // ≈ 0.023 (centered)
    private static final double PIVOT_Z = 50.0    * MODEL_TO_BLOCK; // ≈ 3.594 (forward)
    private static final double PIVOT_Y =  7.4085 * MODEL_TO_BLOCK; // ≈ 0.532 (height)

    // Seat offset FROM the pivot (seat_loc is 14.9 model units behind
    // the cannon center, which after yRot=-π/2 means 14.9 units in
    // the lateral direction → maps to entity Z offset)
    // The seat orbits around the pivot, so this is the radius.
    //private static final double SEAT_BEHIND = -14.9207 * MODEL_TO_BLOCK; // ≈ -1.072
    //private static final double SEAT_SIDE   =  -2.0    * MODEL_TO_BLOCK; // ≈ -0.144

    private static final double SEAT_BEHIND = -13.0 * (1.15 / 16.0);  // ≈ -0.934
    private static final double SEAT_SIDE   =  -0.0793 * (1.15 / 16.0); // ≈ -0.006

    // ── Tuning constants for cannon/harpoon aim limits ──
    // Yaw: how far left/right the cannon can rotate from center (degrees)
    // 180 = full 360°, 150 = ±150° (can't shoot backward), 90 = forward half only
    private static final float MAX_YAW_DEGREES = 150f;

    // Pitch: elevation limits (degrees, in player xRot convention)
    // Player xRot: negative = looking up, positive = looking down
    private static final float MAX_PITCH_UP   = -45f;   // how far up (negative)
    private static final float MAX_PITCH_DOWN =  20f;    // how far down (positive)

    @Override
    protected void positionRider(Entity passenger, Entity.MoveFunction move) {
        if (!this.hasPassenger(passenger)) return;
        int seat = seatOf(passenger);

        // ── Seat 5 (bow gunner): orbits the cannon pivot ──
        /*if (seat == 5 && hasAttachment()) {
            float cannonYaw = getBowRelativeYaw();
            float cannonPitch = getBowPitch();   // radians, negative = up

            // Rotate the seat offset by the cannon's yaw
            double cosC = Math.cos(cannonYaw);
            double sinC = Math.sin(cannonYaw);
            double localX = PIVOT_X + (SEAT_SIDE * cosC - SEAT_BEHIND * sinC);
            double localZ = PIVOT_Z + (SEAT_SIDE * sinC + SEAT_BEHIND * cosC);

            // Player follows the cannon pitch:
            // The seat is behind the pivot, so when the barrel tilts up,
            // the back of the cannon dips down, and vice versa.
            // sin(pitch) * distance_behind gives the Y change.
            double localY = PIVOT_Y + Math.sin(cannonPitch) * Math.abs(SEAT_BEHIND);

            // Transform ship-local → world (rotate by ship yaw)
            double rad = Math.toRadians(this.getYRot());
            double sin = Math.sin(rad), cos = Math.cos(rad);
            double px = this.getX() + (localX * cos - localZ * sin);
            double pz = this.getZ() + (localX * sin + localZ * cos);
            double py = this.getY() + 0.15 + localY;

            passenger.setPos(px, py, pz);
            return;
        }*/

        if (seat == 5 && hasAttachment()) {
            float cannonYaw = getBowRelativeYaw();

            double cosC = Math.cos(cannonYaw);
            double sinC = Math.sin(cannonYaw);
            double localX = PIVOT_X + (SEAT_SIDE * cosC - SEAT_BEHIND * sinC);
            double localZ = PIVOT_Z + (SEAT_SIDE * sinC + SEAT_BEHIND * cosC);
            double localY = PIVOT_Y;  // no pitch adjustment — seat stays level

            double rad = Math.toRadians(this.getYRot());
            double sin = Math.sin(rad), cos = Math.cos(rad);
            double px = this.getX() + (localX * cos - localZ * sin);
            double pz = this.getZ() + (localX * sin + localZ * cos);
            double py = this.getY() + 0.15 + localY;

            passenger.setPos(px, py, pz);
            return;
        }

        // ── All other seats: fixed offsets ──
        Vec3 off = (seat >= 0) ? SEAT_OFFSETS[seat] : Vec3.ZERO;

        double rad = Math.toRadians(this.getYRot());
        double sin = Math.sin(rad), cos = Math.cos(rad);
        double px = this.getX() + (off.x * cos - off.z * sin);
        double pz = this.getZ() + (off.x * sin + off.z * cos);
        double py = this.getY() + 0.15 + off.y;

        passenger.setPos(px, py, pz);
        this.clampRotation(passenger);

        if (seat == 0 && passenger instanceof LivingEntity) {
            passenger.setPose(Pose.STANDING);
        }
    }

    private void debugAttachmentParticles() {
        if (!level().isClientSide() || !DEBUG_DECK || !hasAttachment()) return;
        if (tickCount % 2 != 0) return;  // every other tick to reduce spam

        double yawRad = Math.toRadians(getYRot());
        double sinY = Math.sin(yawRad), cosY = Math.cos(yawRad);

        // ── PIVOT (orange flame) ──
        double pivotWorldX = getX() + (PIVOT_X * cosY - PIVOT_Z * sinY);
        double pivotWorldZ = getZ() + (PIVOT_X * sinY + PIVOT_Z * cosY);
        double pivotWorldY = getY() + 0.15 + PIVOT_Y;

        level().addParticle(ParticleTypes.FLAME,
            pivotWorldX, pivotWorldY, pivotWorldZ, 0, 0.02, 0);

        // ── SEAT (blue soul flame) ──
        float cannonYaw = getBowRelativeYaw();
        double cosC = Math.cos(cannonYaw), sinC = Math.sin(cannonYaw);
        double seatLocalX = PIVOT_X + (SEAT_SIDE * cosC - SEAT_BEHIND * sinC);
        double seatLocalZ = PIVOT_Z + (SEAT_SIDE * sinC + SEAT_BEHIND * cosC);
        double seatLocalY = PIVOT_Y;

        double seatWorldX = getX() + (seatLocalX * cosY - seatLocalZ * sinY);
        double seatWorldZ = getZ() + (seatLocalX * sinY + seatLocalZ * cosY);
        double seatWorldY = getY() + 0.15 + seatLocalY;

        level().addParticle(ParticleTypes.SOUL_FIRE_FLAME,
            seatWorldX, seatWorldY, seatWorldZ, 0, 0.02, 0);

        // ── ACTUAL PLAYER POSITION (green sparkle) ──
        Entity bowGunner = getBowGunner();
        if (bowGunner != null) {
            level().addParticle(ParticleTypes.HAPPY_VILLAGER,
                bowGunner.getX(), bowGunner.getY() + 1.0, bowGunner.getZ(),
                0, 0, 0);
        }
    }

    @Override
    public LivingEntity getControllingPassenger() {
        // STRICT: only the seat-0 occupant drives. Never fall back to vanilla's
        // "first passenger" logic — otherwise standing on deck (a passenger in a
        // non-driver seat) is mistaken for driving, and the boat's manned/unmanned
        // state flips incorrectly when you swap seats or stand up.
        int id = this.entityData.get(SEAT_OCCUPANTS[0]);
        if (id != -1 && this.level().getEntity(id) instanceof LivingEntity le && this.hasPassenger(le)) return le;
        return null;
    }

    /** Returns true if this entity is in the driver seat (seat 0). */
    public boolean isDriver(Entity e) {
        return this.entityData.get(SEAT_OCCUPANTS[0]) == e.getId();
    }

    /** Returns true if this entity is in the bow seat (seat 5) — controls attachments. */
    public boolean isBowGunner(Entity e) {
        return this.entityData.get(SEAT_OCCUPANTS[5]) == e.getId();
    }

    public void setSeat(Player player, int target) {
        if (this.level().isClientSide() || target < 0 || target >= SEAT_COUNT) return;
        int current = seatOf(player);
        if (current == -1 || current == target) return;

        // Capture velocity before the seat change (driver about to change)
        boolean leavingDriver  = (current == 0);
        boolean enteringDriver = (target == 0);
        Vec3 vel = getDeltaMovement();

        int occupant = this.entityData.get(SEAT_OCCUPANTS[target]);
        this.entityData.set(SEAT_OCCUPANTS[target], player.getId());
        this.entityData.set(SEAT_OCCUPANTS[current], occupant);

        // Preserve momentum across the seat swap so it neither dead-stops
        // (leaving driver → unmanned) nor resets (entering driver → manned)
        if ((leavingDriver || enteringDriver) && Math.hypot(vel.x, vel.z) > 0.01) {
            preservedVelocity = vel;
            setDeltaMovement(vel);
        }
    }

    public void cycleSeat(Player player) {
        int current = seatOf(player);
        if (current == -1) return;
        setSeat(player, (current + 1) % SEAT_COUNT);
    }

    private void updateSeats() {
        for (int i = 0; i < SEAT_COUNT; i++) {
            int id = this.entityData.get(SEAT_OCCUPANTS[i]);
            if (id != -1) {
                Entity e = this.level().getEntity(id);
                if (e == null || !this.hasPassenger(e)) this.entityData.set(SEAT_OCCUPANTS[i], -1);
            }
        }
        for (Entity p : this.getPassengers()) {
            if (seatOf(p) == -1) {
                int free = firstFreeSeat();
                if (free != -1) this.entityData.set(SEAT_OCCUPANTS[free], p.getId());
            }
        }
    }

    private int seatOf(Entity p) {
        for (int i = 0; i < SEAT_COUNT; i++) if (this.entityData.get(SEAT_OCCUPANTS[i]) == p.getId()) return i;
        return -1;
    }

    private int firstFreeSeat() {
        for (int i = 0; i < SEAT_COUNT; i++) if (this.entityData.get(SEAT_OCCUPANTS[i]) == -1) return i;
        return -1;
    }


    // ════════════════════════════════════════════════════════════════
    //  DECK CARRY  (mobs server-side; local player handled client-side)
    // ════════════════════════════════════════════════════════════════
    public boolean isOnDeck(Entity e, boolean alreadyAboard) {
        double margin = alreadyAboard ? STICK_MARGIN : 0.0;
        double dx = e.getX() - getX();
        double dz = e.getZ() - getZ();
        double yaw = Math.toRadians(getYRot());
        double cos = Math.cos(-yaw), sin = Math.sin(-yaw);
        double lx = dx * cos - dz * sin;   // into ship-local
        double lz = dx * sin + dz * cos;
        double dy = e.getY() - getY();
        return Math.abs(lx) <= DECK_HALF_WID + margin    // port/starboard (narrow axis)
            && Math.abs(lz) <= DECK_HALF_LEN + margin    // fore/aft (long axis)
            && dy >= DECK_MIN_Y && dy <= DECK_MAX_Y + margin;
    }

    public void carryRider(Entity e) {
        double dyaw = Math.toRadians(getDeckDYaw());
        double cos = Math.cos(dyaw), sin = Math.sin(dyaw);

        // rotate the rider's offset (measured from the ship's PREVIOUS center) then re-anchor
        double oldX = getX() - getDeckDX();
        double oldZ = getZ() - getDeckDZ();
        double relX = e.getX() - oldX;
        double relZ = e.getZ() - oldZ;
        double rotX = relX * cos - relZ * sin;
        double rotZ = relX * sin + relZ * cos;

        double newX = getX() + rotX;
        double newY = e.getY() + getDeckDY();
        double newZ = getZ() + rotZ;

        double ddx = newX - e.getX();
        double ddy = newY - e.getY();
        double ddz = newZ - e.getZ();

        e.setPos(newX, newY, newZ);
        // shift interpolation history by the SAME delta -> no render slide/vibration
        e.xOld += ddx; e.yOld += ddy; e.zOld += ddz;
        e.xo  += ddx; e.yo  += ddy; e.zo  += ddz;
    }

    /**
     * Carry the local player on the client side.
     *
     * CRITICAL: Do NOT modify xo/yo/zo or xOld/yOld/zOld here.
     *
     * The engine sets xo = currentPos at the start of each tick. After our carry
     * changes currentPos, the renderer interpolates FROM xo (pre-carry) TO
     * currentPos (post-carry) over the frame. Since the boat's own renderer does
     * the same thing (boat.xo → boat.currentPos), the two interpolations match
     * perfectly and the player glides smoothly with the boat.
     *
     * Previously we did xo += delta which made xo == currentPos, killing the
     * interpolation. The player snapped to position each tick while the boat
     * glided smoothly → the mismatch appeared as vibration at 20Hz, and on
     * pause the player "zipped forward" as partialTick converged to 1.0.
     */
    public void carryEntity(Entity e) {
        if (Math.abs(deckDX) < 0.001 && Math.abs(deckDZ) < 0.001
                && Math.abs(deckDY) < 0.001 && Math.abs(deckDYaw) < 0.01f) return;

        if (isSinking()) return;

        // Same math as carryRider: offset from PREVIOUS center → rotate → CURRENT center
        double dyaw = Math.toRadians(deckDYaw);
        double cos = Math.cos(dyaw), sin = Math.sin(dyaw);

        double prevCX = getX() - deckDX;
        double prevCZ = getZ() - deckDZ;

        double relX = e.getX() - prevCX;
        double relZ = e.getZ() - prevCZ;
        double rotX = relX * cos - relZ * sin;
        double rotZ = relX * sin + relZ * cos;

        e.setPos(getX() + rotX, e.getY() + deckDY, getZ() + rotZ);

        if (e instanceof LivingEntity) {
            e.setYRot(e.getYRot() + deckDYaw);
            // Don't touch yRotO — let the renderer interpolate the turn smoothly
        }
    }

    /**
     * BUG FIX: The old code created an unused 'box' AABB and then searched using
     * getBoundingBox().inflate(DECK_HALF_LEN) instead. Also had mismatched braces.
     * Now properly searches using the deck dimensions and has correct brace structure.
     */
    private void carryDeckMobs() {
        // Don't drag entities down with a sinking ship
        if (isSinking()) {
            deckRiders.clear();
            return;
        }

        for (Entity e : this.level().getEntities(this,
                getBoundingBox().inflate(DECK_HALF_LEN, DECK_MAX_Y, DECK_HALF_LEN),
                e -> !(e instanceof Player) && !e.isInWater())) {
            boolean before = deckRiders.contains(e.getUUID());
            if (isOnDeck(e, before)) {
                carryRider(e);
                deckRiders.add(e.getUUID());
            } else {
                deckRiders.remove(e.getUUID());
            }
        }
    }

    @Override
    protected void clampRotation(Entity passenger) {
        // Let the bow gunner look freely in all directions (360° + full pitch)
        // so the cannon/harpoon can aim anywhere
        if (isBowGunner(passenger) && hasAttachment()) {
            return;  // no clamping at all
        }
        // Also let the crow's nest (seat 6) look freely
        if (seatOf(passenger) == 6) {
            return;
        }
        super.clampRotation(passenger);
    }


    // ════════════════════════════════════════════════════════════════
    //  PARTS HELPER
    // ════════════════════════════════════════════════════════════════
    @Nullable
    private ShipPartEntity getWorldPart(EntityDataAccessor<Integer> idAccessor) {
        int id = this.entityData.get(idAccessor);
        if (id == -1) return null;
        Entity e = this.level().getEntity(id);
        return e instanceof ShipPartEntity sp ? sp : null;
    }


}