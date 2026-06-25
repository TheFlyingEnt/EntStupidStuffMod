package net.ent.entstupidstuff.screen;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.api.car.menu.CarMenu;
import net.ent.entstupidstuff.api.ship.ShipMenu;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;

public class ScreenHandlerFactory {
    public static final MenuType<DarkEnchantmentScreenHandler> DARK_ENCHANTING_TABLE_HANDLER =
            Registry.register(BuiltInRegistries.MENU, ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "dark_enchanting_table"),
            new MenuType<>(DarkEnchantmentScreenHandler::new, FeatureFlags.VANILLA_SET));
    
    public static final MenuType<CarMenu> CAR_MENU  =
            Registry.register(BuiltInRegistries.MENU, ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "car_menu"),
            new MenuType<>(CarMenu::new, FeatureFlags.VANILLA_SET));

    public static final MenuType<ShipMenu> SHIP_MENU = 
            Registry.register(BuiltInRegistries.MENU, ResourceLocation.fromNamespaceAndPath("entstupidstuff", "ship_menu"),
            new MenuType<>(ShipMenu::new, FeatureFlags.DEFAULT_FLAGS));

    public static void registerScreenHandlers() {
        EntStupidStuff.LOGGER.info("Registering Screen Handlers for " + EntStupidStuff.MOD_ID);
    }
    
}
