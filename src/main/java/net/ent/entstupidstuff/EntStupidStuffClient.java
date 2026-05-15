package net.ent.entstupidstuff;

import net.ent.entstupidstuff.api.car.CarSoundManager;
import net.ent.entstupidstuff.api.car.menu.CarScreen;
import net.ent.entstupidstuff.api.casting.ArmorCastProperty;
import net.ent.entstupidstuff.api.casting.ToolCastingProperty;
import net.ent.entstupidstuff.block.ModRenderLayers;
import net.ent.entstupidstuff.block.blockentity.BlockEntityFactory;
import net.ent.entstupidstuff.client.ModEntityModelLayers;
import net.ent.entstupidstuff.client.ParticlesClient;
import net.ent.entstupidstuff.client.render.ModSpecialModelTypes;
import net.ent.entstupidstuff.client.render.entity.renderer.HorizontalBannerRenderer;
import net.ent.entstupidstuff.item.base.CannonItem;
import net.ent.entstupidstuff.screen.DarkEnchantingTableScreen;
import net.ent.entstupidstuff.screen.ScreenHandlerFactory;
import net.ent.entstupidstuff.util.CastingClientUtil;
import net.ent.entstupidstuff.util.HatnEmoteClientUtil;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.item.properties.select.SelectItemModelProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;


public class EntStupidStuffClient implements ClientModInitializer {


    @SuppressWarnings("deprecation")
    @Override
    public void onInitializeClient() {

        ModEntityModelLayers.onInitialize();
        ModRenderLayers.onInitializeClient();
        ModSpecialModelTypes.onInit();
        ParticlesClient.initalize();

        CastingClientUtil.onInitializeClient();
        HatnEmoteClientUtil.onInitializeClient();

        HudRenderCallback.EVENT.register((drawContext, tickDelta) -> {
            Minecraft client = Minecraft.getInstance();
            if (client.player != null) {
                ItemStack stack = client.player.getMainHandItem();
                if (stack.getItem() instanceof CannonItem && CannonItem.isCharged(stack)) {
                    //int x = drawContext.guiWidth() / 2;
                    //int y = drawContext.guiHeight() / 2;
                    //Identifier TEX = Identifier.of("entstupidstuff", "textures/gui/cannon_crosshair.png");
                    //Identifier TEX = Identifier.of("entstupidstuff", "textures/gui/sprites/hud/crosshair_cannon.png");

                    //drawContext.drawTexture(TEX, x - 8, y - 8, 0, 0, 16, 16, 16, 16);
                }
            }
        });

        MenuScreens.register(ScreenHandlerFactory.DARK_ENCHANTING_TABLE_HANDLER, DarkEnchantingTableScreen::new);
        MenuScreens.register(ScreenHandlerFactory.CAR_MENU, CarScreen::new);

        BlockEntityRenderers.register(
            BlockEntityFactory.HORIZONTAL_BANNER,
            HorizontalBannerRenderer::new
        );

        SelectItemModelProperties.ID_MAPPER.put(
            ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "tool_cast"),
            ToolCastingProperty.TYPE
        );
        SelectItemModelProperties.ID_MAPPER.put(
            ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "armor_cast"),
            ArmorCastProperty.TYPE
        );

        ClientTickEvents.END_CLIENT_TICK.register(client -> CarSoundManager.tick());
    }
}
