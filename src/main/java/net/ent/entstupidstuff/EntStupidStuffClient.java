package net.ent.entstupidstuff;

import net.ent.entstupidstuff.api.car.CarSoundManager;
import net.ent.entstupidstuff.api.car.menu.CarScreen;
import net.ent.entstupidstuff.api.casting.ArmorCastProperty;
import net.ent.entstupidstuff.api.casting.ToolCastingProperty;
import net.ent.entstupidstuff.api.emote.EmoteRegistry;
import net.ent.entstupidstuff.api.hat.ModAttachments;
import net.ent.entstupidstuff.api.ship.CustomBoatEntity;
import net.ent.entstupidstuff.api.ship.DeckOffsetPayload;
import net.ent.entstupidstuff.api.ship.DeckSync;
import net.ent.entstupidstuff.api.ship.ShipHud;
import net.ent.entstupidstuff.api.ship.ShipScreen;
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
import net.ent.entstupidstuff.util.ModKeybinds;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.item.properties.select.SelectItemModelProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;



public class EntStupidStuffClient implements ClientModInitializer {

    private static boolean wasDeckAnchored = false;


    @SuppressWarnings("deprecation")
    @Override
    public void onInitializeClient() {

        ModEntityModelLayers.onInitialize();
        ModRenderLayers.onInitializeClient();
        ModSpecialModelTypes.onInit();
        ParticlesClient.initalize();

        EmoteRegistry.init();

        CastingClientUtil.onInitializeClient();
        HatnEmoteClientUtil.onInitializeClient();
        ModKeybinds.register();

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
        MenuScreens.register(ScreenHandlerFactory.SHIP_MENU, ShipScreen::new);

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
        ClientTickEvents.END_CLIENT_TICK.register(client -> ModKeybinds.tick());

        /*ClientTickEvents.END_CLIENT_TICK.register(mc -> {
            LocalPlayer p = mc.player;
            if (p == null || p.isPassenger() || mc.level == null) return;

            AABB box = p.getBoundingBox().inflate(4.0, 2.0, 4.0);
            for (CustomBoatEntity boat : mc.level.getEntitiesOfClass(CustomBoatEntity.class, box)) {
                if (boat.isOnDeck(p)) {
                    boat.carryEntity(p);   // client owns the local player → no rubber-band
                    break;
                }
            }
        });*/

        // in onInitializeClient():
        ClientTickEvents.END_CLIENT_TICK.register(mc -> {
            if (mc.level == null) return;
            LocalPlayer p = mc.player;

            // (A) local walker: carry immediately (no round-trip) + report offset
            boolean anchored = false;
            if (p != null && !p.isPassenger()) {
                CustomBoatEntity boat = DeckSync.findDeckBoat(mc.level, p);
                if (boat != null && !boat.isSinking()) {
                    boat.carryEntity(p);
                    DeckSync.Anchor a = DeckSync.compute(boat, p);
                    ClientPlayNetworking.send(new DeckOffsetPayload(boat.getId(), a.x(), a.y(), a.z()));
                    anchored = true;
                }
            }
            if (!anchored && wasDeckAnchored) {
                ClientPlayNetworking.send(new DeckOffsetPayload(-1, 0, 0, 0));  // stepped off → clear
            }
            wasDeckAnchored = anchored;

            // (B) remote walkers: rebuild their position from the local smooth boat pos
            for (Entity e : mc.level.entitiesForRendering()) {
                if (e == p) continue;
                DeckSync.Anchor a = e.getAttached(ModAttachments.DECK_ANCHOR);
                if (a != null && a.boatId() >= 0) DeckSync.apply(mc.level, e, a);
            }
        });

        ShipHud.register();
        EntStupidStuff.LOGGER.info("Registering ShipHud...");

        /*HudRenderCallback.EVENT.register((g, tickDelta) -> {
            Minecraft mc = Minecraft.getInstance();
            if (mc.player == null) return;
            // TEST: always show this, even off the boat
            g.drawString(mc.font, "HUD TEST", 10, 10, 0xFFFF0000);
            // ... rest of the code
        });*/


    }
}
