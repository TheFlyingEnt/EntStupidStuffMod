package net.ent.entstupidstuff.item.base.car;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;

import java.util.function.Consumer;
 
/**
 * TireItem — damageable wheel item for car wheel slots.
 *
 * 500 durability = ~83 minutes of driving (1 damage / 10 sec).
 * Drifting wears 3× faster (~28 minutes of pure drifting).
 *
 * Grip degrades below 25% durability (125 hits remaining).
 * At 0 durability the item breaks and the wheel slot is empty,
 * reducing that axle's grip to 30%.
 *
 * Stack size: 1 (damageable items don't stack).
 */
public class TireItem extends Item {
 
    public TireItem(Item.Properties settings) {
        super(settings
            .durability(500)
            .stacksTo(1)
        );
    }
 
    @Override
    public void appendHoverText(ItemStack stack, TooltipContext ctx, TooltipDisplay displayComponent, Consumer<Component> textConsumer, TooltipFlag flag) { //tooltip
        int remaining = stack.getMaxDamage() - stack.getDamageValue();
        float pct = (float) remaining / stack.getMaxDamage() * 100f;
        
 
        if (pct > 25f) {
            textConsumer.accept(Component.literal("§aTread: " + String.format("%.0f%%", pct) + " — Full grip"));
        } else {
            textConsumer.accept(Component.literal("§cTread: " + String.format("%.0f%%", pct) + " — Grip degrading!"));
        }
        textConsumer.accept(Component.literal("§7Place in a car's wheel slot"));
    }
}

