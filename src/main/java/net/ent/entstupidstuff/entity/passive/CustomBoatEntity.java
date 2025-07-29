package net.ent.entstupidstuff.entity.passive;

import org.jetbrains.annotations.Nullable;

import net.ent.entstupidstuff.client.render.entity.model.CustomBoatPart;
import net.minecraft.entity.Entity;
import net.minecraft.entity.Entity.RemovalReason;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.RideableInventory;
import net.minecraft.entity.mob.PiglinBrain;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.entity.vehicle.VehicleInventory;
import net.minecraft.inventory.StackReference;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.loot.LootTable;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryKey;
import net.minecraft.screen.GenericContainerScreenHandler;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

public class CustomBoatEntity extends BoatEntity implements RideableInventory, VehicleInventory {
	private static final int INVENTORY_SIZE = 54;
	private DefaultedList<ItemStack> inventory = DefaultedList.ofSize(INVENTORY_SIZE, ItemStack.EMPTY);
	@Nullable private RegistryKey<LootTable> lootTable;
	private long lootTableSeed;

	private final CustomBoatPart[] parts;

	public CustomBoatEntity(EntityType<? extends BoatEntity> entityType, World world) {
		super(entityType, world);
		this.parts = new CustomBoatPart[]{
			new CustomBoatPart(this, "front", 1.0f, 100f),
			new CustomBoatPart(this, "back", 1.0f, 100f),
			new CustomBoatPart(this, "left", 0.5f, 100f),
			new CustomBoatPart(this, "right", 0.5f, 100f)
		};
	}

	public CustomBoatEntity(World world, double x, double y, double z) {
		this(EntityType.CHEST_BOAT, world); // Replace with your custom EntityType if needed
		this.setPosition(x, y, z);
		this.prevX = x;
		this.prevY = y;
		this.prevZ = z;
	}

	@Override
	public void tick() {
		super.tick();
		updatePartPositions();
	}

	private void updatePartPositions() {
		Vec3d forward = this.getRotationVector().normalize();
		Vec3d left = forward.crossProduct(new Vec3d(0, 1, 0));
		Vec3d basePos = this.getPos();

		this.parts[0].refreshPositionAndAngles(basePos.add(forward.multiply(1.5)), this.getYaw(), 0); // Front
		this.parts[1].refreshPositionAndAngles(basePos.subtract(forward.multiply(1.5)), this.getYaw(), 0); // Back
		this.parts[2].refreshPositionAndAngles(basePos.subtract(left.multiply(1.0)), this.getYaw(), 0); // Left
		this.parts[3].refreshPositionAndAngles(basePos.add(left.multiply(1.0)), this.getYaw(), 0); // Right
	}

	public boolean damagePart(CustomBoatPart part, net.minecraft.entity.damage.DamageSource source, float amount) {
		return this.damage(source, amount);
	}

	//@Override
	public Entity[] getParts() {
		return this.parts;
	}

	@Override
	protected float getPassengerHorizontalOffset() {
		return 0.15F;
	}

	@Override
	protected int getMaxPassengers() {
		return 4;
	}

	@Override
	protected void writeCustomDataToNbt(NbtCompound nbt) {
		super.writeCustomDataToNbt(nbt);
		this.writeInventoryToNbt(nbt, this.getRegistryManager());
	}

	@Override
	protected void readCustomDataFromNbt(NbtCompound nbt) {
		super.readCustomDataFromNbt(nbt);
		this.readInventoryFromNbt(nbt, this.getRegistryManager());
	}

	@Override
	public void killAndDropSelf(net.minecraft.entity.damage.DamageSource source) {
		this.killAndDropItem(this.asItem());
		this.onBroken(source, this.getWorld(), this);
	}

	@Override
	public void remove(RemovalReason reason) {
		if (!this.getWorld().isClient && reason.shouldDestroy()) {
			ItemScatterer.spawn(this.getWorld(), this, this);
		}
		super.remove(reason);
	}

	@Override
	public ActionResult interact(PlayerEntity player, Hand hand) {
		if (!player.shouldCancelInteraction()) {
			ActionResult result = super.interact(player, hand);
			if (result != ActionResult.PASS) return result;
		}

		if (this.canAddPassenger(player) && !player.shouldCancelInteraction()) {
			return ActionResult.PASS;
		} else {
			ActionResult result = this.open(player);
			if (result.isAccepted()) {
				this.emitGameEvent(GameEvent.CONTAINER_OPEN, player);
				PiglinBrain.onGuardedBlockInteracted(player, true);
			}
			return result;
		}
	}

	@Override
	public void openInventory(PlayerEntity player) {
		player.openHandledScreen(this);
		if (!player.getWorld().isClient) {
			this.emitGameEvent(GameEvent.CONTAINER_OPEN, player);
			PiglinBrain.onGuardedBlockInteracted(player, true);
		}
	}

	@Override
	public Item asItem() {
		return Items.OAK_CHEST_BOAT; // Replace with your custom item
	}

	@Override
	public void clear() {
		this.clearInventory();
	}

	@Override
	public int size() {
		return INVENTORY_SIZE;
	}

	@Override
	public ItemStack getStack(int slot) {
		return this.getInventoryStack(slot);
	}

	@Override
	public ItemStack removeStack(int slot, int amount) {
		return this.removeInventoryStack(slot, amount);
	}

	@Override
	public ItemStack removeStack(int slot) {
		return this.removeInventoryStack(slot);
	}

	@Override
	public void setStack(int slot, ItemStack stack) {
		this.setInventoryStack(slot, stack);
	}

	@Override
	public StackReference getStackReference(int mappedIndex) {
		return this.getInventoryStackReference(mappedIndex);
	}

	@Override
	public void markDirty() {}

	@Override
	public boolean canPlayerUse(PlayerEntity player) {
		return this.canPlayerAccess(player);
	}

	@Nullable
	@Override
	public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
		if (this.lootTable != null && player.isSpectator()) return null;
		this.generateLoot(player);
		return GenericContainerScreenHandler.createGeneric9x6(syncId, inv, this);
	}

	public void generateLoot(@Nullable PlayerEntity player) {
		this.generateInventoryLoot(player);
	}

	@Nullable
	@Override
	public RegistryKey<LootTable> getLootTable() {
		return this.lootTable;
	}

	@Override
	public void setLootTable(@Nullable RegistryKey<LootTable> lootTable) {
		this.lootTable = lootTable;
	}

	@Override
	public long getLootTableSeed() {
		return this.lootTableSeed;
	}

	@Override
	public void setLootTableSeed(long seed) {
		this.lootTableSeed = seed;
	}

	@Override
	public DefaultedList<ItemStack> getInventory() {
		return this.inventory;
	}

	@Override
	public void resetInventory() {
		this.inventory = DefaultedList.ofSize(INVENTORY_SIZE, ItemStack.EMPTY);
	}

	@Override
	public void onClose(PlayerEntity player) {
		this.getWorld().emitGameEvent(GameEvent.CONTAINER_CLOSE, this.getPos(), GameEvent.Emitter.of(player));
	}
}



/*
public class CustomBoatEntity extends BoatEntity implements RideableInventory, VehicleInventory {
    private static final int INVENTORY_SIZE = 54;
    private DefaultedList<ItemStack> inventory = DefaultedList.ofSize(INVENTORY_SIZE, ItemStack.EMPTY);
    @Nullable
    private RegistryKey<LootTable> lootTable;
    private long lootTableSeed;

    public CustomBoatEntity(EntityType<? extends BoatEntity> entityType, World world) {
        super(entityType, world);
    }

    public CustomBoatEntity(World world, double x, double y, double z) {
        super(EntityType.CHEST_BOAT, world); // Replace with your custom EntityType
        this.setPosition(x, y, z);
        this.prevX = x;
        this.prevY = y;
        this.prevZ = z;
    }

    @Override
    protected float getPassengerHorizontalOffset() {
        return 0.15F;
    }

    @Override
    protected int getMaxPassengers() {
        return 4;
    }

    @Override
    protected void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        this.writeInventoryToNbt(nbt, this.getRegistryManager());
    }

    @Override
    protected void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        this.readInventoryFromNbt(nbt, this.getRegistryManager());
    }

    @Override
    public void killAndDropSelf(net.minecraft.entity.damage.DamageSource source) {
        this.killAndDropItem(this.asItem());
        this.onBroken(source, this.getWorld(), this);
    }

    @Override
    public void remove(RemovalReason reason) {
        if (!this.getWorld().isClient && reason.shouldDestroy()) {
            ItemScatterer.spawn(this.getWorld(), this, this);
        }
        super.remove(reason);
    }

    @Override
    public ActionResult interact(PlayerEntity player, Hand hand) {
        if (!player.shouldCancelInteraction()) {
            ActionResult result = super.interact(player, hand);
            if (result != ActionResult.PASS) return result;
        }

        if (this.canAddPassenger(player) && !player.shouldCancelInteraction()) {
            return ActionResult.PASS;
        } else {
            ActionResult result = this.open(player);
            if (result.isAccepted()) {
                this.emitGameEvent(GameEvent.CONTAINER_OPEN, player);
                PiglinBrain.onGuardedBlockInteracted(player, true);
            }
            return result;
        }
    }

    @Override
    public void openInventory(PlayerEntity player) {
        player.openHandledScreen(this);
        if (!player.getWorld().isClient) {
            this.emitGameEvent(GameEvent.CONTAINER_OPEN, player);
            PiglinBrain.onGuardedBlockInteracted(player, true);
        }
    }

    @Override
    public Item asItem() {
        return Items.OAK_CHEST_BOAT; // Replace with your custom item if needed
    }

    @Override
    public void clear() {
        this.clearInventory();
    }

    @Override
    public int size() {
        return INVENTORY_SIZE;
    }

    @Override
    public ItemStack getStack(int slot) {
        return this.getInventoryStack(slot);
    }

    @Override
    public ItemStack removeStack(int slot, int amount) {
        return this.removeInventoryStack(slot, amount);
    }

    @Override
    public ItemStack removeStack(int slot) {
        return this.removeInventoryStack(slot);
    }

    @Override
    public void setStack(int slot, ItemStack stack) {
        this.setInventoryStack(slot, stack);
    }

    @Override
    public StackReference getStackReference(int mappedIndex) {
        return this.getInventoryStackReference(mappedIndex);
    }

    @Override
    public void markDirty() {}

    @Override
    public boolean canPlayerUse(PlayerEntity player) {
        return this.canPlayerAccess(player);
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
        if (this.lootTable != null && player.isSpectator()) return null;
        this.generateLoot(player);
        return GenericContainerScreenHandler.createGeneric9x6(syncId, inv, this);
    }

    public void generateLoot(@Nullable PlayerEntity player) {
        this.generateInventoryLoot(player);
    }

    @Nullable
    @Override
    public RegistryKey<LootTable> getLootTable() {
        return this.lootTable;
    }

    @Override
    public void setLootTable(@Nullable RegistryKey<LootTable> lootTable) {
        this.lootTable = lootTable;
    }

    @Override
    public long getLootTableSeed() {
        return this.lootTableSeed;
    }

    @Override
    public void setLootTableSeed(long seed) {
        this.lootTableSeed = seed;
    }

    @Override
    public DefaultedList<ItemStack> getInventory() {
        return this.inventory;
    }

    @Override
    public void resetInventory() {
        this.inventory = DefaultedList.ofSize(INVENTORY_SIZE, ItemStack.EMPTY);
    }

    @Override
    public void onClose(PlayerEntity player) {
        this.getWorld().emitGameEvent(GameEvent.CONTAINER_CLOSE, this.getPos(), GameEvent.Emitter.of(player));
    }
} 
*/
