package net.ent.entstupidstuff.client.entity.passive;

import java.util.List;
import java.util.function.Supplier;

import org.jetbrains.annotations.Nullable;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.vehicle.AbstractChestBoatEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.loot.LootTable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;

public class CustomBoatEntity extends AbstractChestBoatEntity {
	private static final int INVENTORY_SIZE = 54;
	private DefaultedList<ItemStack> inventory = DefaultedList.ofSize(INVENTORY_SIZE, ItemStack.EMPTY);
	@Nullable private RegistryKey<LootTable> lootTable;
	private long lootTableSeed;

	public CustomBoatEntity(EntityType<? extends CustomBoatEntity> entityType, World world, Supplier<Item> boatItemSupplier) {
        super(entityType, world, boatItemSupplier);
        //this.resetInventory();
    }

	public CustomBoatEntity(EntityType<? extends CustomBoatEntity> entityType, World world) {
    	this(entityType, world, () -> Items.OAK_BOAT); // Provide the default boat item
	}

	@Override
    protected int getMaxPassengers() {
        return 4; // Supports 4 passengers
    }

    @Override
    protected float getPassengerHorizontalOffset() {
        // Custom horizontal spacing for 4 passengers
        return 0.75f;
    }

    @Override
    public int size() {
        return INVENTORY_SIZE;
    }

    @Override
    public DefaultedList<ItemStack> getInventory() {
        return super.getInventory();
    }

    @Override
    public void resetInventory() {
        super.resetInventory();
        this.inventory = DefaultedList.ofSize(INVENTORY_SIZE, ItemStack.EMPTY);
    }

    @Override
    public void killAndDropSelf(ServerWorld world, DamageSource damageSource) {
        super.killAndDropSelf(world, damageSource);
        // Drops all inventory on destroy
        this.getInventory().forEach(stack -> {
            if (!stack.isEmpty()) this.dropStack(world, stack);
        });
    }

    @Override
    public ActionResult interact(PlayerEntity player, Hand hand) {
        ActionResult result = super.interact(player, hand);
        if (result != ActionResult.PASS) return result;

        if (!this.hasPassenger(player) && this.canAddPassenger(player)) {
            player.startRiding(this);
            return ActionResult.SUCCESS;
        }

        return ActionResult.PASS;
    }

	@Override
	protected double getPassengerAttachmentY(EntityDimensions dimensions) {
		return 0.15F;
	}

	//Passenger Test Code:
	@Override
		protected void updatePassengerPosition(Entity passenger, Entity.PositionUpdater positionUpdater) {
		// Only apply custom positioning for living entities (players/animals)
		List<Entity> passengers = this.getPassengerList();
		int passengerIndex = passengers.indexOf(passenger);

		// Driver is always at the back
		if (passengerIndex == passengers.size() - 1) {
			// Driver sits in back center
			float driverOffsetZ = 0.5f; // back of boat
			passenger.setPosition(this.getX(), this.getY() + 0.15F + passenger.getHeight(), this.getZ() - driverOffsetZ);
			passenger.setYaw(this.getYaw());
			passenger.setHeadYaw(this.getYaw());
			return;
		}

		// Passengers
		int passengerCount = passengers.size() - 1; // exclude driver
		float rowZ = 0.0f; // front/back
		float colX = 0.0f; // left/right

		switch (passengerCount) {
			case 1:
				// One passenger sits in front center
				rowZ = -0.5f;
				colX = 0.0f;
				break;
			case 2:
				// Two passengers sit side by side in front
				rowZ = -0.5f;
				colX = passengerIndex % 2 == 0 ? -0.3f : 0.3f;
				break;
			case 3:
				// Three passengers: two in front row, one next to driver
				if (passengerIndex == 0) { colX = -0.3f; rowZ = -0.5f; }
				if (passengerIndex == 1) { colX = 0.3f; rowZ = -0.5f; }
				if (passengerIndex == 2) { colX = 0.0f; rowZ = 0.0f; } // next to driver
				break;
		}

		// Apply rotation of boat
		double rad = Math.toRadians(this.getYaw());
		double rotatedX = colX * Math.cos(rad) - rowZ * Math.sin(rad);
		double rotatedZ = colX * Math.sin(rad) + rowZ * Math.cos(rad);

		passenger.setPosition(this.getX() + rotatedX, this.getY() + 0.15F + passenger.getHeight(), this.getZ() + rotatedZ);
		passenger.setYaw(this.getYaw());
		passenger.setHeadYaw(this.getYaw());
	}


}
