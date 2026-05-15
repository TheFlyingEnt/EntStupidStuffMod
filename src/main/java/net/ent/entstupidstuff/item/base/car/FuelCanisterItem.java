package net.ent.entstupidstuff.item.base.car;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;

import java.util.function.Consumer;


/**
 * FuelCanisterItem — damageable fuel canister for the car fuel slot.
 *
 * 1000 durability = ~833 minutes of driving (1 damage / 5 sec).
 * While fuel is present, car gets +10% drive force.
 * Cars drive fine without fuel — it's a performance boost.
 *
 * The fuel gauge in the car GUI shows remaining durability as a bar.
 *
 * Stack size: 1 (damageable items don't stack).
 */
public class FuelCanisterItem extends Item {
 
    public FuelCanisterItem(Item.Properties settings) {
        super(settings
            .durability(1000)
            .stacksTo(1)
        );
    }
 
    @Override
    public void appendHoverText(ItemStack stack, TooltipContext ctx, TooltipDisplay displayComponent, Consumer<Component> textConsumer, TooltipFlag flag) { //tooltip
        int remaining = stack.getMaxDamage() - stack.getDamageValue();
        float pct = (float) remaining / stack.getMaxDamage() * 100f;
 
        int color = pct > 50 ? 0x55FF55 : pct > 20 ? 0xFFFF55 : 0xFF5555;
        String colorCode = pct > 50 ? "§a" : pct > 20 ? "§e" : "§c";
        textConsumer.accept(Component.literal(colorCode + "Fuel: " + String.format("%.0f%%", pct)));
        textConsumer.accept(Component.literal("§7+10% engine power while fueled"));
        textConsumer.accept(Component.literal("§7Place in a car's fuel slot"));
    }
}

