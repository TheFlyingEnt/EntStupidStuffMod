package net.ent.entstupidstuff.client.entity.passive;

import java.util.List;
import java.util.function.Supplier;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.AbstractChestBoat;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.loot.LootTable;
import org.jetbrains.annotations.Nullable;

public class CustomBoatEntity extends AbstractChestBoat {
	private static final int INVENTORY_SIZE = 54;
	private NonNullList<ItemStack> inventory = NonNullList.withSize(INVENTORY_SIZE, ItemStack.EMPTY);
	@Nullable private ResourceKey<LootTable> lootTable;
	private long lootTableSeed;

	public CustomBoatEntity(EntityType<? extends CustomBoatEntity> entityType, Level world, Supplier<Item> boatItemSupplier) {
        super(entityType, world, boatItemSupplier);
        //this.resetInventory();
    }

	public CustomBoatEntity(EntityType<? extends CustomBoatEntity> entityType, Level world) {
    	this(entityType, world, () -> Items.OAK_BOAT); // Provide the default boat item
	}

	@Override
    protected int getMaxPassengers() {
        return 4; // Supports 4 passengers
    }

    @Override
    protected float getSinglePassengerXOffset() {
        // Custom horizontal spacing for 4 passengers
        return 0.75f;
    }

    @Override
    public int getContainerSize() {
        return INVENTORY_SIZE;
    }

    @Override
    public NonNullList<ItemStack> getItemStacks() {
        return super.getItemStacks();
    }

    @Override
    public void clearItemStacks() {
        super.clearItemStacks();
        this.inventory = NonNullList.withSize(INVENTORY_SIZE, ItemStack.EMPTY);
    }

    @Override
    public void destroy(ServerLevel world, DamageSource damageSource) {
        super.destroy(world, damageSource);
        // Drops all inventory on destroy
        this.getItemStacks().forEach(stack -> {
            if (!stack.isEmpty()) this.spawnAtLocation(world, stack);
        });
    }

    @Override
    public InteractionResult interact(Player player, InteractionHand hand) {
        InteractionResult result = super.interact(player, hand);
        if (result != InteractionResult.PASS) return result;

        if (!this.hasPassenger(player) && this.canAddPassenger(player)) {
            player.startRiding(this);
            return InteractionResult.SUCCESS;
        }

        return InteractionResult.PASS;
    }

	@Override
	protected double rideHeight(EntityDimensions dimensions) {
		return 0.15F;
	}

	//Passenger Test Code:
	@Override
		protected void positionRider(Entity passenger, Entity.MoveFunction positionUpdater) {
		// Only apply custom positioning for living entities (players/animals)
		List<Entity> passengers = this.getPassengers();
		int passengerIndex = passengers.indexOf(passenger);

		// Driver is always at the back
		if (passengerIndex == passengers.size() - 1) {
			// Driver sits in back center
			float driverOffsetZ = 0.5f; // back of boat
			passenger.setPos(this.getX(), this.getY() + 0.15F + passenger.getBbHeight(), this.getZ() - driverOffsetZ);
			passenger.setYRot(this.getYRot());
			passenger.setYHeadRot(this.getYRot());
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
		double rad = Math.toRadians(this.getYRot());
		double rotatedX = colX * Math.cos(rad) - rowZ * Math.sin(rad);
		double rotatedZ = colX * Math.sin(rad) + rowZ * Math.cos(rad);

		passenger.setPos(this.getX() + rotatedX, this.getY() + 0.15F + passenger.getBbHeight(), this.getZ() + rotatedZ);
		passenger.setYRot(this.getYRot());
		passenger.setYHeadRot(this.getYRot());
	}


}
