package net.ent.entstupidstuff.api.car.menu;

import net.ent.entstupidstuff.api.car.BaseCarEntity;
import net.ent.entstupidstuff.item.base.car.CarWrapItem;
import net.ent.entstupidstuff.item.base.car.FuelCanisterItem;
import net.ent.entstupidstuff.item.base.car.LicensePlateItem;
import net.ent.entstupidstuff.item.base.car.TireItem;
import net.ent.entstupidstuff.screen.ScreenHandlerFactory;
import net.ent.entstupidstuff.sound.SoundFactory;
import net.minecraft.core.component.DataComponents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
 
/**
 * Car customization menu — 8 slots.
 *
 * Slot layout (car inventory):
 *   0: License Plate   1: Fuel   2: Wrap/Paint   3: Radio (music disc only)
 *   4: Wheel FL   5: Wheel FR   6: Wheel RL   7: Wheel RR
 */
public class CarMenu extends AbstractContainerMenu {
 
    public static final int SLOT_PLATE    = 0;
    public static final int SLOT_FUEL     = 1;
    public static final int SLOT_WRAP     = 2;
    public static final int SLOT_RADIO    = 3;
    public static final int SLOT_WHEEL_FL = 4;
    public static final int SLOT_WHEEL_FR = 5;
    public static final int SLOT_WHEEL_RL = 6;
    public static final int SLOT_WHEEL_RR = 7;
    public static final int CAR_SLOTS     = 8;
 
    private final Container carInv;
    private final int carEntityId;
 
    /** Server constructor. */
    public CarMenu(int id, Inventory playerInv, BaseCarEntity car) {
        super(ScreenHandlerFactory.CAR_MENU, id);
        this.carInv = car.getCarInventory();
        this.carEntityId = car.getId();
        carInv.startOpen(playerInv.player);
 
        // Row 1: Plate, Fuel, Wrap, Radio
        addSlot(new Slot(carInv, SLOT_PLATE, 17,  20){@Override
            public boolean mayPlace(ItemStack stack) {
                return stack.getItem() instanceof LicensePlateItem;
            }}
        );
        addSlot(new Slot(carInv, SLOT_FUEL,  53,  20){@Override
            public boolean mayPlace(ItemStack stack) {
                return stack.getItem() instanceof FuelCanisterItem;
            }}
        );
        addSlot(new Slot(carInv, SLOT_WRAP, 107, 20) {
            @Override
            public boolean mayPlace(ItemStack stack) {

                if (!(stack.getItem() instanceof CarWrapItem)) {
                    return false;
                }

                String wrapCarType = CarWrapItem.getCarType(stack);
                return wrapCarType.equals(car.getCarTypeId());
            }
        
            @Override
            public void setChanged() {
                super.setChanged();
                if (this.hasItem()) playerInv.player.level().playSound(null, playerInv.player.blockPosition(), SoundFactory.ENTITY_VEHICLE_WRAP, SoundSource.PLAYERS, 0.8f, 1.0f);
            }
        });
        addSlot(new Slot(carInv, SLOT_RADIO, 143, 20){@Override
            public boolean mayPlace(ItemStack stack) {
                return stack.has(DataComponents.JUKEBOX_PLAYABLE);
            }}
        );
 
        // Row 2: Wheels in car-shape layout
        addSlot(new Slot(carInv, SLOT_WHEEL_FL, 44, 52){@Override
            public boolean mayPlace(ItemStack stack) {
                return stack.getItem() instanceof TireItem;
            }
        
            @Override
            public void setChanged() {
                super.setChanged();
                if (this.hasItem()) playerInv.player.level().playSound(null, playerInv.player.blockPosition(), SoundFactory.ENTITY_VEHICLE_POWER_DRILL, SoundSource.PLAYERS, 0.8f, 1.0f);
            }}
        );
        addSlot(new Slot(carInv, SLOT_WHEEL_FR, 98, 52){@Override
            public boolean mayPlace(ItemStack stack) {
                return stack.getItem() instanceof TireItem;
            }
        
            @Override
            public void setChanged() {
                super.setChanged();
                if (this.hasItem()) playerInv.player.level().playSound(null, playerInv.player.blockPosition(), SoundFactory.ENTITY_VEHICLE_POWER_DRILL, SoundSource.PLAYERS, 0.8f, 1.0f);
            }}
        );
        addSlot(new Slot(carInv, SLOT_WHEEL_RL, 44, 72){@Override
            public boolean mayPlace(ItemStack stack) {
                return stack.getItem() instanceof TireItem;
            }
        
            @Override
            public void setChanged() {
                super.setChanged();
                if (this.hasItem()) playerInv.player.level().playSound(null, playerInv.player.blockPosition(), SoundFactory.ENTITY_VEHICLE_POWER_DRILL, SoundSource.PLAYERS, 0.8f, 1.0f);
            }}
        );
        addSlot(new Slot(carInv, SLOT_WHEEL_RR, 98, 72){@Override
            public boolean mayPlace(ItemStack stack) {
                return stack.getItem() instanceof TireItem;
            }
        
            @Override
            public void setChanged() {
                super.setChanged();
                if (this.hasItem()) playerInv.player.level().playSound(null, playerInv.player.blockPosition(), SoundFactory.ENTITY_VEHICLE_POWER_DRILL, SoundSource.PLAYERS, 0.8f, 1.0f);
            }}
        );
 
        // Player inventory (3 rows)
        for (int r = 0; r < 3; r++)
            for (int c = 0; c < 9; c++)
                addSlot(new Slot(playerInv, c + r * 9 + 9, 8 + c * 18, 98 + r * 18));
        // Hotbar
        for (int c = 0; c < 9; c++)
            addSlot(new Slot(playerInv, c, 8 + c * 18, 156));
    }
 
    /** Client constructor — empty inventory, populated by sync. */
    public CarMenu(int id, Inventory playerInv) {
        super(ScreenHandlerFactory.CAR_MENU, id);
        this.carInv = new SimpleContainer(CAR_SLOTS);
        this.carEntityId = -1;
 
        addSlot(new Slot(carInv, SLOT_PLATE, 17,  20));
        addSlot(new Slot(carInv, SLOT_FUEL,  53,  20));
        addSlot(new Slot(carInv, SLOT_WRAP,  107, 20));
        addSlot(new Slot(carInv, SLOT_RADIO, 143, 20) {
            @Override public boolean mayPlace(ItemStack s) {
                //return s.getItem() instanceof Item; //RecordItem;
                return s.has(DataComponents.JUKEBOX_PLAYABLE);
            }
        });
        addSlot(new Slot(carInv, SLOT_WHEEL_FL, 44, 52));
        addSlot(new Slot(carInv, SLOT_WHEEL_FR, 98, 52));
        addSlot(new Slot(carInv, SLOT_WHEEL_RL, 44, 72));
        addSlot(new Slot(carInv, SLOT_WHEEL_RR, 98, 72));
 
        for (int r = 0; r < 3; r++)
            for (int c = 0; c < 9; c++)
                addSlot(new Slot(playerInv, c + r * 9 + 9, 8 + c * 18, 98 + r * 18));
        for (int c = 0; c < 9; c++)
            addSlot(new Slot(playerInv, c, 8 + c * 18, 156));
    }
 
    @Override
    public boolean stillValid(Player player) {
        if (carEntityId == -1) return true;
        var e = player.level().getEntity(carEntityId);
        return e instanceof BaseCarEntity car && !car.isRemoved() && player.distanceTo(car) < 8.0;
    }
 
    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        Slot slot = this.slots.get(index);

        if (slot == null || !slot.hasItem()) {
            return ItemStack.EMPTY;
        }

        ItemStack stack = slot.getItem();
        ItemStack copy = stack.copy();

        // From car -> player inventory
        if (index < CAR_SLOTS) {

            if (!moveItemStackTo(stack, CAR_SLOTS, this.slots.size(), true)) {
                return ItemStack.EMPTY;
            }

        } else {

            boolean moved = false;

            // License plate
            if (stack.getItem() instanceof LicensePlateItem) {
                moved = moveItemStackTo(stack,
                        SLOT_PLATE,
                        SLOT_PLATE + 1,
                        false);
            }

            // Fuel
            else if (stack.getItem() instanceof FuelCanisterItem) {
                moved = moveItemStackTo(stack,
                        SLOT_FUEL,
                        SLOT_FUEL + 1,
                        false);
            }

            // Wrap
            else if (stack.getItem() instanceof CarWrapItem) {
                moved = moveItemStackTo(stack,
                        SLOT_WRAP,
                        SLOT_WRAP + 1,
                        false);
            }

            // Music disc
            else if (stack.has(DataComponents.JUKEBOX_PLAYABLE)) {
                moved = moveItemStackTo(stack,
                        SLOT_RADIO,
                        SLOT_RADIO + 1,
                        false);
            }

            // Tires
            else if (stack.getItem() instanceof TireItem) {
                moved = moveItemStackTo(stack,
                        SLOT_WHEEL_FL,
                        SLOT_WHEEL_RR + 1,
                        false);
            }

            if (!moved) {
                return ItemStack.EMPTY;
            }
        }

        if (stack.isEmpty()) {
            slot.setByPlayer(ItemStack.EMPTY);
        } else {
            slot.setChanged();
        }

        slot.onTake(player, stack);

        return copy;
    }
 
    @Override
    public void removed(Player player) {
        super.removed(player);
        carInv.stopOpen(player);
 
        // Close hood when GUI closes
        // carEntityId is the entity ID stored from the constructor
        if (!player.level().isClientSide()) {
            var entity = player.level().getEntity(carEntityId);
            if (entity instanceof BaseCarEntity car) {
                car.closeHood();
            }
        }
    }


    
}

