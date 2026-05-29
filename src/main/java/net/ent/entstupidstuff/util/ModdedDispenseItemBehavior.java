package net.ent.entstupidstuff.util;

import net.ent.entstupidstuff.api.car.BaseCarEntity;
import net.ent.entstupidstuff.api.car.menu.CarMenu;
import net.ent.entstupidstuff.item.ItemFactory;
import net.ent.entstupidstuff.item.base.car.CarWrapItem;
import net.ent.entstupidstuff.sound.SoundFactory;
import net.minecraft.core.BlockPos;
import net.minecraft.core.dispenser.BlockSource;
import net.minecraft.core.dispenser.OptionalDispenseItemBehavior;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.phys.AABB;

public class ModdedDispenseItemBehavior {

    public static void init() {

        DispenserBlock.registerBehavior(
            ItemFactory.TIRE,
            new OptionalDispenseItemBehavior() {

                @Override
                protected ItemStack execute(BlockSource blockSource, ItemStack itemStack) {

                    BlockPos blockPos = blockSource.pos()
                            .relative(blockSource.state().getValue(DispenserBlock.FACING));

                    for (BaseCarEntity car : blockSource.level().getEntitiesOfClass(
                            BaseCarEntity.class,
                            new AABB(blockPos),
                            entity -> entity.isAlive()
                    )) {

                        SimpleContainer inv = car.getCarInventory();

                        // Wheel slots: 4,5,6,7
                        for (int slot = CarMenu.SLOT_WHEEL_FL;
                            slot <= CarMenu.SLOT_WHEEL_RR;
                            slot++) {

                            // Skip filled slots
                            if (!inv.getItem(slot).isEmpty()) {
                                continue;
                            }

                            ItemStack tire = itemStack.copyWithCount(1);

                            inv.setItem(slot, tire);
                            inv.setChanged();

                            // Optional sound
                            blockSource.level().playSound(
                                    null,
                                    car.blockPosition(),
                                    SoundFactory.ENTITY_VEHICLE_POWER_DRILL,
                                    SoundSource.BLOCKS,
                                    0.8f,
                                    1.0f
                            );

                            itemStack.shrink(1);

                            this.setSuccess(true);
                            return itemStack;
                        }
                    }
                    return super.execute(blockSource, itemStack);
                }
            }
        );


        DispenserBlock.registerBehavior(
            ItemFactory.CAR_WRAP,
            new OptionalDispenseItemBehavior() {

                @Override
                protected ItemStack execute(BlockSource blockSource, ItemStack itemStack) {

                    BlockPos blockPos = blockSource.pos()
                            .relative(blockSource.state().getValue(DispenserBlock.FACING));

                    for (BaseCarEntity car : blockSource.level().getEntitiesOfClass(
                            BaseCarEntity.class,
                            new AABB(blockPos),
                            entity -> entity.isAlive()
                    )) {

                        SimpleContainer inv = car.getCarInventory();

                        // Wrap slot already occupied
                        if (!inv.getItem(CarMenu.SLOT_WRAP).isEmpty()) {
                            continue;
                        }

                        // Make sure wrap matches car type
                        String wrapCarType = CarWrapItem.getCarType(itemStack);

                        if (!wrapCarType.equals(car.getCarTypeId())) {
                            continue;
                        }

                        ItemStack wrap = itemStack.copyWithCount(1);

                        inv.setItem(CarMenu.SLOT_WRAP, wrap);
                        inv.setChanged();

                        blockSource.level().playSound(
                                null,
                                car.blockPosition(),
                                SoundFactory.ENTITY_VEHICLE_WRAP,
                                SoundSource.BLOCKS,
                                0.8f,
                                1.0f
                        );

                        itemStack.shrink(1);

                        this.setSuccess(true);
                        return itemStack;
                    }

                    return super.execute(blockSource, itemStack);
                }
            }
        );

        DispenserBlock.registerBehavior(
            ItemFactory.FUEL_CANISTER,
            new OptionalDispenseItemBehavior() {

                @Override
                protected ItemStack execute(BlockSource blockSource, ItemStack itemStack) {

                    BlockPos blockPos = blockSource.pos()
                            .relative(blockSource.state().getValue(DispenserBlock.FACING));

                    for (BaseCarEntity car : blockSource.level().getEntitiesOfClass(
                            BaseCarEntity.class,
                            new AABB(blockPos),
                            entity -> entity.isAlive()
                    )) {

                        SimpleContainer inv = car.getCarInventory();

                        // Fuel slot already occupied
                        if (!inv.getItem(CarMenu.SLOT_FUEL).isEmpty()) {
                            continue;
                        }

                        ItemStack fuel = itemStack.copyWithCount(1);

                        inv.setItem(CarMenu.SLOT_FUEL, fuel);
                        inv.setChanged();

                        blockSource.level().playSound(
                                null,
                                car.blockPosition(),
                                SoundEvents.BARREL_CLOSE,
                                SoundSource.BLOCKS,
                                0.8f,
                                1.0f
                        );

                        itemStack.shrink(1);

                        this.setSuccess(true);
                        return itemStack;
                    }

                    return super.execute(blockSource, itemStack);
                }
            }
        );


    }
    
}
