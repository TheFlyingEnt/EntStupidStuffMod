package net.ent.entstupidstuff.screen;

import net.ent.entstupidstuff.EntStupidStuff;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public class ScreenHandlerFactory {
    public static final ScreenHandlerType<DarkEnchantmentScreenHandler> DARK_ENCHANTING_TABLE_HANDLER =
            Registry.register(Registries.SCREEN_HANDLER, Identifier.of(EntStupidStuff.MOD_ID, "dark_enchanting_table"),
                    new ScreenHandlerType<>(DarkEnchantmentScreenHandler::new, FeatureFlags.VANILLA_FEATURES));

    public static void registerScreenHandlers() {
        EntStupidStuff.LOGGER.info("Registering Screen Handlers for " + EntStupidStuff.MOD_ID);
    }
    
}
