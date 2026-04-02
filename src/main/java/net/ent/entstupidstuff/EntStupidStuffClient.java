package net.ent.entstupidstuff;

import java.util.ArrayList;
import java.util.List;

import org.jetbrains.annotations.Nullable;

import net.ent.entstupidstuff.api.casting.ArmorCastingComponent;
import net.ent.entstupidstuff.api.car.CarSoundManager;
import net.ent.entstupidstuff.api.casting.ArmorCastProperty;
import net.ent.entstupidstuff.api.casting.CastingTemplateItem;
import net.ent.entstupidstuff.api.casting.CastingTooltipCondenser;
import net.ent.entstupidstuff.api.casting.ToolCastingComponent;
import net.ent.entstupidstuff.api.casting.ToolCastingProperty;
import net.ent.entstupidstuff.api.emote.EmoteClientState;
import net.ent.entstupidstuff.api.emote.EmoteSyncPayload;
import net.ent.entstupidstuff.api.hat.HatMenuScreen;
import net.ent.entstupidstuff.api.hat.HatRenderLayer;
import net.ent.entstupidstuff.api.hat.HatSyncPayload;
import net.ent.entstupidstuff.api.hat.ModAttachments;
import net.ent.entstupidstuff.block.ModRenderLayers;
import net.ent.entstupidstuff.block.blockentity.BlockEntityFactory;
import net.ent.entstupidstuff.client.ModEntityModelLayers;
import net.ent.entstupidstuff.client.ParticlesClient;
import net.ent.entstupidstuff.client.render.ModSpecialModelTypes;
import net.ent.entstupidstuff.client.render.entity.renderer.HorizontalBannerRenderer;
import net.ent.entstupidstuff.component.ModDataComponentTypes;
import net.ent.entstupidstuff.item.base.CannonItem;
import net.ent.entstupidstuff.screen.DarkEnchantingTableScreen;
import net.ent.entstupidstuff.screen.ScreenHandlerFactory;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fabricmc.fabric.api.client.rendering.v1.LivingEntityFeatureRendererRegistrationCallback;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.player.AvatarRenderer;
import net.minecraft.client.renderer.entity.state.AvatarRenderState;
import net.minecraft.client.renderer.item.properties.select.SelectItemModelProperties;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;


public class EntStupidStuffClient implements ClientModInitializer {

    @Nullable
    private static Screen pendingScreen = null;

    @SuppressWarnings("deprecation")
    @Override
    public void onInitializeClient() {

        ModEntityModelLayers.onInitialize();
        ModRenderLayers.onInitializeClient();
        ModSpecialModelTypes.onInit();
        ParticlesClient.initalize();

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

        //------------------------------------

        ItemTooltipCallback.EVENT.register((stack, context, type, lines) -> {
            if (!(stack.getItem() instanceof CastingTemplateItem casting)) return;

            // Build all our lines in order
            List<Component> toInsert = new ArrayList<>();

            toInsert.add(Component.translatable(
                "item.entstupidstuff.casting_template.title",
                Component.literal(casting.getCastName()).withStyle(ChatFormatting.GOLD)
            ).withStyle(ChatFormatting.GRAY));

            toInsert.add(CommonComponents.EMPTY);

            toInsert.add(Component.translatable(
                "item.entstupidstuff.casting_template.applies_to"
            ).withStyle(ChatFormatting.GRAY));

            /*for (Item item : mold.getValidItems()) {
                toInsert.add(CommonComponents.space().append(
                    item.getName().copy().withStyle(ChatFormatting.BLUE)
                ));
            }*/

            toInsert.addAll(CastingTooltipCondenser.condense(casting.getValidItems()));

            toInsert.add(CommonComponents.EMPTY);

            toInsert.add(Component.translatable(
                "item.entstupidstuff.casting_template.ingredients"
            ).withStyle(ChatFormatting.GRAY));

            toInsert.add(CommonComponents.space().append(
                Component.translatable("item.minecraft.lava_bucket")
                    .withStyle(ChatFormatting.BLUE)
            ));

            // Insert all lines right after item name (index 0)
            // Use reverse order so each insert at index 1 puts them in correct order
            for (int i = toInsert.size() - 1; i >= 0; i--) {
                lines.add(1, toInsert.get(i));
            }
        });

        ItemTooltipCallback.EVENT.register((stack, context, type, lines) -> {
            ArmorCastingComponent armorCast = stack.get(ModDataComponentTypes.ARMOR_CAST);
            ToolCastingComponent toolCast   = stack.get(ModDataComponentTypes.TOOL_CAST);
            if (armorCast == null && toolCast == null) return;

            String castName = armorCast != null
                ? armorCast.castId().getPath()
                : toolCast.castId().getPath();

            Component castLine = CommonComponents.space().append(
                Component.translatable("cast." + EntStupidStuff.MOD_ID + "." + castName)
                    .withStyle(ChatFormatting.BLUE)
            );

            String upgradeHeader = Component.translatable(
                "item.minecraft.smithing_template.upgrade"
            ).getString();

            int insertAt = -1;
            for (int i = 0; i < lines.size(); i++) {
                if (lines.get(i).getString().equals(upgradeHeader)) {
                    // Found the header, now find the last indented line in this section
                    insertAt = i;
                    for (int j = i + 1; j < lines.size(); j++) {
                        if (lines.get(j).getString().startsWith(" ")) {
                            insertAt = j;
                        } else {
                            break;
                        }
                    }
                    break;
                }
            }

            /// ---------------------

            if (insertAt >= 0) {
                lines.add(insertAt + 1, castLine);
            } else {
                // Find "When on X:" line to insert upgrade section before it
                int whenOnIndex = -1;
                String whenOn = Component.translatable("item.modifiers.chest").getString();

                // Check all slot types
                List<String> whenOnKeys = List.of(
                    Component.translatable("item.modifiers.chest").getString(),
                    Component.translatable("item.modifiers.head").getString(),
                    Component.translatable("item.modifiers.legs").getString(),
                    Component.translatable("item.modifiers.feet").getString(),
                    Component.translatable("item.modifiers.mainhand").getString(),
                    Component.translatable("item.modifiers.offhand").getString()
                );

                for (int i = 0; i < lines.size(); i++) {
                    if (whenOnKeys.contains(lines.get(i).getString())) {
                        whenOnIndex = i;
                        break;
                    }
                }

                if (whenOnIndex >= 0) {
                    int insertBefore = whenOnIndex;
                    
                    // Remove ALL empty lines before "When on X:"
                    while (insertBefore > 1 && lines.get(insertBefore - 1).getString().isEmpty()) {
                        lines.remove(insertBefore - 1);
                        insertBefore--;
                    }
                    
                    // Now insert: Upgrade header, mold line, single empty gap
                    lines.add(insertBefore, CommonComponents.EMPTY); // single gap before "When on Chest:"
                    lines.add(insertBefore, castLine);
                    lines.add(insertBefore, Component.translatable("item.minecraft.smithing_template.upgrade")
                        .withStyle(ChatFormatting.GRAY));
                } else {
                    // Last resort - just before the last empty line or at end
                    int lastEmpty = -1;
                    for (int i = lines.size() - 1; i >= 0; i--) {
                        if (lines.get(i).getString().isEmpty()) {
                            lastEmpty = i;
                            break;
                        }
                    }
                    int pos = lastEmpty >= 0 ? lastEmpty : lines.size();
                    lines.add(pos, CommonComponents.EMPTY);
                    lines.add(pos, castLine);
                    lines.add(pos, Component.translatable("item.minecraft.smithing_template.upgrade")
                        .withStyle(ChatFormatting.GRAY));
                    // Only add leading empty if something is above the header
                    if (pos > 1) {
                        lines.add(pos, CommonComponents.EMPTY);
                    }
                }
            }
        });

        ClientTickEvents.START_CLIENT_TICK.register(mc -> {
            if (pendingScreen != null && mc.screen == null) {
                mc.setScreen(pendingScreen);
                pendingScreen = null;
            }
        });

        // ── /hatmenu command (client-side only, opens GUI directly) ───────────
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) ->
            dispatcher.register(
                ClientCommandManager.literal("hatmenu")
                .executes(ctx -> {
                    pendingScreen = new HatMenuScreen();
                    return 1;
                })
            )
        );


        // ── Hat sync ──────────────────────────────────────────────────────────
        ClientPlayNetworking.registerGlobalReceiver(HatSyncPayload.TYPE, (payload, context) ->
            context.client().execute(() -> {
                var level = context.client().level;
                if (level == null) return;
                for (Player p : level.players()) {
                    if (p.getUUID().equals(payload.playerUuid())) {
                        p.setAttached(ModAttachments.HAT, payload.hatName());
                        break;
                    }
                }
            })
        );
 
        // ── Emote sync ────────────────────────────────────────────────────────
        ClientPlayNetworking.registerGlobalReceiver(EmoteSyncPayload.TYPE, (payload, context) ->
            context.client().execute(() -> {
                var level = context.client().level;
                if (level == null) return;
 
                for (Player p : level.players()) {
                    if (!p.getUUID().equals(payload.playerUuid())) continue;
 
                    // Resolve UUID → runtime entity ID here, where we have the
                    // live entity. The mixin only sees renderState.id (int).
                    if (payload.emoteName().isEmpty()) {
                        EmoteClientState.stop(p.getId());
                    } else {
                        EmoteClientState.start(p.getId(), payload.emoteName(), p.tickCount);
                    }
                    break;
                }
            })
        );
 
        // ── Clear emote state on disconnect / world change ────────────────────
        ClientPlayConnectionEvents.DISCONNECT.register((handler, client) ->
            EmoteClientState.clear()
        );
 
        // ── Rendering ─────────────────────────────────────────────────────────
        // In 1.21.10, PlayerRenderer works with PlayerRenderState.
        // We register HatRenderLayer for every PlayerRenderer variant
        // (default + slim skin).
        LivingEntityFeatureRendererRegistrationCallback.EVENT.register(
            (entityType, renderer, registrationHelper, context) -> {
                if (entityType == EntityType.PLAYER && renderer instanceof AvatarRenderer) {
                    @SuppressWarnings("unchecked")
                    var parent = (RenderLayerParent<AvatarRenderState, PlayerModel>) renderer;
                    registrationHelper.register(new HatRenderLayer(parent));
                }
            }
        );


        ClientTickEvents.END_CLIENT_TICK.register(client -> CarSoundManager.tick());

        ClientPlayConnectionEvents.DISCONNECT.register((handler, client) ->
            CarSoundManager.stopAll()
        );

        
    }
}
