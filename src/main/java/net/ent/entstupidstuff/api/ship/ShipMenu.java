package net.ent.entstupidstuff.api.ship;

import net.ent.entstupidstuff.screen.ScreenHandlerFactory;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

/**
 * Ship management menu — lets the player equip a bow attachment (harpoon or cannon).
 * One attachment slot + the player's inventory below.
 */
public class ShipMenu extends AbstractContainerMenu {

    /** The ship this menu is managing (may be null on client if detection fails). */
    private final CustomBoatEntity ship;

    /** Backing container for the attachment slot — syncs to/from the ship entity. */
    private final SimpleContainer attachmentContainer;

    // ── Server constructor (called from CustomBoatEntity.interact) ──
    public ShipMenu(int syncId, Inventory playerInv, CustomBoatEntity ship) {
        super(ScreenHandlerFactory.SHIP_MENU, syncId);
        this.ship = ship;

        // Create a 1-slot container for the attachment
        this.attachmentContainer = new SimpleContainer(1) {
            @Override
            public void setChanged() {
                super.setChanged();
                // When the attachment slot changes, update the ship entity
                if (ship != null && !ship.level().isClientSide()) {
                    ItemStack stack = this.getItem(0);
                    ship.setAttachmentStack(stack);
                }
            }
        };

        // Seed the container with the ship's current attachment
        if (ship != null) {
            attachmentContainer.setItem(0, ship.getAttachmentStack().copy());
        }

        buildSlots(playerInv);
    }

    // ── Client constructor (called by MenuType factory) ──
    public ShipMenu(int syncId, Inventory playerInv) {
        super(ScreenHandlerFactory.SHIP_MENU, syncId);

        // Find the ship from the player's context
        Player player = playerInv.player;
        if (player.getVehicle() instanceof CustomBoatEntity s) {
            this.ship = s;
        } else {
            this.ship = DeckSync.findDeckBoat(player.level(), player);
        }

        this.attachmentContainer = new SimpleContainer(1);
        if (ship != null) {
            attachmentContainer.setItem(0, ship.getAttachmentStack().copy());
        }

        buildSlots(playerInv);
    }

    private void buildSlots(Inventory playerInv) {
        // ── Attachment slot (centered at top) ──
        // GUI coords: x=80, y=20 (will be centered by the screen)
        this.addSlot(new AttachmentSlot(attachmentContainer, 0, 80, 20));

        // ── Player inventory (3 rows of 9) ──
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                this.addSlot(new Slot(playerInv, col + row * 9 + 9, 8 + col * 18, 56 + row * 18));
            }
        }

        // ── Player hotbar (1 row of 9) ──
        for (int col = 0; col < 9; col++) {
            this.addSlot(new Slot(playerInv, col, 8 + col * 18, 114));
        }
    }

    @Override
    public ItemStack quickMoveStack(Player player, int slotIndex) {
        Slot slot = this.slots.get(slotIndex);
        if (!slot.hasItem()) return ItemStack.EMPTY;

        ItemStack stack = slot.getItem();
        ItemStack copy = stack.copy();

        if (slotIndex == 0) {
            // Attachment slot → player inventory
            if (!this.moveItemStackTo(stack, 1, 37, true)) return ItemStack.EMPTY;
        } else {
            // Player inventory → attachment slot
            if (AttachmentSlot.isValidAttachment(stack)) {
                if (!this.moveItemStackTo(stack, 0, 1, false)) return ItemStack.EMPTY;
            } else {
                return ItemStack.EMPTY;
            }
        }

        if (stack.isEmpty()) {
            slot.set(ItemStack.EMPTY);
        } else {
            slot.setChanged();
        }
        return copy;
    }

    @Override
    public boolean stillValid(Player player) {
        return ship != null && !ship.isRemoved() && ship.distanceTo(player) < 8.0;
    }

    @Override
    public void removed(Player player) {
        super.removed(player);
        // Sync final state to the ship
        if (ship != null && !ship.level().isClientSide()) {
            ship.setAttachmentStack(attachmentContainer.getItem(0));
        }
    }

    public CustomBoatEntity getShip() { return ship; }

    // ════════════════════════════════════════════════════════════════
    //  ATTACHMENT SLOT — only accepts harpoon/cannon items, max 1
    // ════════════════════════════════════════════════════════════════
    public static class AttachmentSlot extends Slot {
        public AttachmentSlot(Container container, int index, int x, int y) {
            super(container, index, x, y);
        }

        @Override
        public boolean mayPlace(ItemStack stack) {
            return isValidAttachment(stack);
        }

        @Override
        public int getMaxStackSize() {
            return 1;
        }

        /**
         * Check if an item is a valid ship attachment.
         * TODO: Replace these checks with your actual harpoon/cannon items
         * once they're registered in ItemFactory.
         *
         * For now, accepts:
         *   - Items with "harpoon" in their registry name → ATTACHMENT_HARPOON
         *   - Items with "cannon" or "tnt" in their name → ATTACHMENT_CANNON
         *   - Or use: ItemFactory.HARPOON_ATTACHMENT, ItemFactory.CANNON_ATTACHMENT
         */
        public static boolean isValidAttachment(ItemStack stack) {
            if (stack.isEmpty()) return false;
            String id = stack.getItem().toString().toLowerCase();
            return id.contains("harpoon") || id.contains("cannon") || id.contains("ship_attachment");
        }

        /**
         * Determine which attachment type an item represents.
         * Returns ATTACHMENT_NONE if not a valid attachment.
         */
        public static int getAttachmentType(ItemStack stack) {
            if (stack.isEmpty()) return CustomBoatEntity.ATTACHMENT_NONE;
            String id = stack.getItem().toString().toLowerCase();
            if (id.contains("harpoon")) return CustomBoatEntity.ATTACHMENT_HARPOON;
            if (id.contains("cannon")) return CustomBoatEntity.ATTACHMENT_CANNON;
            return CustomBoatEntity.ATTACHMENT_NONE;
        }
    }
}
