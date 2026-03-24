package net.ent.entstupidstuff.api.mold;

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.equipment.Equippable;

public class ArmorMoldItem extends Item {

    private final String moldName;

    public ArmorMoldItem(String moldName, Properties properties) {
        super(properties);
        this.moldName = moldName;
    }

    public String getMoldName() { return moldName; }

    public static boolean isValidTarget(ItemStack stack) {
        // Armor has EQUIPPABLE component with an armor slot
        Equippable equippable = stack.get(DataComponents.EQUIPPABLE);
        if (equippable == null) return false;
        EquipmentSlot slot = equippable.slot();
        return slot == EquipmentSlot.HEAD
            || slot == EquipmentSlot.CHEST
            || slot == EquipmentSlot.LEGS
            || slot == EquipmentSlot.FEET;
    }
    
}
