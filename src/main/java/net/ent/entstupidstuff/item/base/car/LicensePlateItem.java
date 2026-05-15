package net.ent.entstupidstuff.item.base.car;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;

import java.util.function.Consumer;
 
/**
 * LicensePlateItem — custom license plate with up to 7 characters.
 *
 * How to set plate text:
 *   - Name it in an anvil (e.g. "ABC 1234")
 *   - Or /give with custom name
 *
 * The car entity renderer reads:
 *   LicensePlateItem.getPlateText(car.getLicensePlate())
 * and renders the text on the car's rear face.
 *
 * Text is filtered to A-Z, 0-9, and spaces only.
 */


public class LicensePlateItem extends Item {
 
    public static final int MAX_PLATE_CHARS = 7;
 
    public LicensePlateItem(Item.Properties settings) {
        super(settings.stacksTo(1));
    }
 
    /**
     * Extracts plate text from the stack's display name.
     * Up to 7 uppercase alphanumeric characters.
     */
    public static String getPlateText(ItemStack stack) {
        if (stack.isEmpty()) return "";
        String raw = stack.getHoverName().getString()
                          .toUpperCase()
                          .replaceAll("[^A-Z0-9 ]", "");
        if (raw.isEmpty() || raw.equals("LICENSE PLATE")) return "PLATE";
        return raw.length() > MAX_PLATE_CHARS ? raw.substring(0, MAX_PLATE_CHARS) : raw;
    }
 
    @Override
    public void appendHoverText(ItemStack stack, TooltipContext ctx, TooltipDisplay displayComponent, Consumer<Component> textConsumer, TooltipFlag flag) { //tooltip
        String text = getPlateText(stack);
        textConsumer.accept(Component.literal("§f§l[ " + text + " ]"));
        textConsumer.accept(Component.literal("§7Rename in anvil to set text"));
        textConsumer.accept(Component.literal("§7Max " + MAX_PLATE_CHARS + " characters (A-Z, 0-9)"));
    }
}

