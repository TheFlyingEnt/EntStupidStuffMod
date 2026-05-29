package net.ent.entstupidstuff.item.base.car;

import net.ent.entstupidstuff.component.ModDataComponentTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;

import java.util.function.Consumer;
 
/**
 * CarWrapItem — car-specific paint/wrap that changes the car's texture.
 *
 * Each wrap item stores two pieces of data:
 *   carType:  which car it's for (e.g. "nissan_z", "f1_car")
 *   wrapId:   which texture to use (e.g. "gold", "fone_senna")
 *
 * The car renderer uses getCurrentWrap() to select the texture:
 *   assets/entstupidstuff/textures/entity/{carType}/{wrapId}.png
 *
 * Creating wrap items (in your code or datapacks):
 *   ItemStack wrap = CarWrapItem.createWrap("nissan_z", "gold", "Nissan Z Gold");
 *
 * Or via command:
 *   /give @s entstupidstuff:car_wrap{CarType:"nissan_z",WrapId:"gold"} 1
 *   (then rename in anvil for display name)
 *
 * The CarMenu validates that the wrap's carType matches the car entity
 * before applying the texture change.
 */
public class CarWrapItem extends Item {
 
    public CarWrapItem(Item.Properties settings) {
        super(settings.stacksTo(1));
    }
 
    /**
     * Creates a wrap ItemStack with the given car type and wrap ID.
     */
    public static ItemStack createWrap(Item wrapItem, String carType, String wrapId, String displayName) {
        ItemStack stack = new ItemStack(wrapItem);
        stack.set(ModDataComponentTypes.CAR_TYPE, carType);
        stack.set(ModDataComponentTypes.WRAP_ID, wrapId);
        stack.set(net.minecraft.core.component.DataComponents.CUSTOM_NAME, Component.literal(displayName));
        return stack;
    }
 
    /** Gets the car type this wrap is for (e.g. "nissan_z"). */
    public static String getCarType(ItemStack stack) {
        if (stack.isEmpty()) return "";
        String val = stack.get(ModDataComponentTypes.CAR_TYPE);
        return val != null ? val : "";
    }
 
    /** Gets the wrap/texture ID (e.g. "gold", "fone_senna"). */
    public static String getWrapId(ItemStack stack) {
        if (stack.isEmpty()) return "default";
        String val = stack.get(ModDataComponentTypes.WRAP_ID);
        return val != null ? val : "default";
    }
 
    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay displayComponent, Consumer<Component> textConsumer, TooltipFlag type) {
        String carType = getCarType(stack);
        String wrapId  = getWrapId(stack);

        //tooltip.add(Text.translatable("item.entstupidstuff.blunt.tooltip").formatted(Formatting.GRAY));
        MutableComponent carNameText = Component.translatable("item.entstupidstuff.carwrap." + carType);
        MutableComponent wrapIdText = Component.translatable("item.entstupidstuff.carwrap." + carType + "." + wrapId);
 
        if (!carType.isEmpty()) {
            /*String carName = carType.replace("_", " ");
            carName = carName.substring(0, 1).toUpperCase() + carName.substring(1);
            textConsumer.accept(Component.literal("§bFor: §f" + carName));*/
            textConsumer.accept(carNameText);
        }
        //textConsumer.accept(Component.literal("§ePaint: §f" + wrapId.replace("_", " ")));
        textConsumer.accept(wrapIdText);
        textConsumer.accept(Component.literal("§7Place in a car's wrap slot"));
    }
}


