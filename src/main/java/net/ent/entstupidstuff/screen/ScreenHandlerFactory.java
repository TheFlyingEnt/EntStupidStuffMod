package net.ent.entstupidstuff.screen;

import net.ent.entstupidstuff.EntStupidStuff;
//import net.ent.entstupidstuff.hat.HatScreenHandler;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;

public class ScreenHandlerFactory {
    public static final MenuType<DarkEnchantmentScreenHandler> DARK_ENCHANTING_TABLE_HANDLER =
            Registry.register(BuiltInRegistries.MENU, ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "dark_enchanting_table"),
            new MenuType<>(DarkEnchantmentScreenHandler::new, FeatureFlags.VANILLA_SET));

    /*public static final MenuType<HatScreenHandler> HAT_SCREEN_HANDLER =
            Registry.register(BuiltInRegistries.MENU, ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "hat_screen"),
            new MenuType<>(HatScreenHandler::new, FeatureFlags.VANILLA_SET));*/

    public static void registerScreenHandlers() {
        EntStupidStuff.LOGGER.info("Registering Screen Handlers for " + EntStupidStuff.MOD_ID);
    }
    
}
