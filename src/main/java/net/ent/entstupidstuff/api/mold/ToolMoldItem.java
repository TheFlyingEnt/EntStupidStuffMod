package net.ent.entstupidstuff.api.mold;

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class ToolMoldItem extends Item {

    private final String moldName;

    public ToolMoldItem(String moldName, Properties properties) {
        super(properties);
        this.moldName = moldName;
    }

    public String getMoldName() { return moldName; }

    public static boolean isValidTarget(ItemStack stack) {
        // Tools have the TOOL component (pickaxe, shovel, axe, hoe)
        // Swords/weapons have WEAPON component or just attack damage
        return stack.has(DataComponents.TOOL);
        //    || stack.getItem().getDefaultAttributeModifiers()
        //        .containsKey(Attributes.ATTACK_DAMAGE);
    }
}
