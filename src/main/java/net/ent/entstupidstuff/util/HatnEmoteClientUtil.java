package net.ent.entstupidstuff.util;

import org.jetbrains.annotations.Nullable;

import net.ent.entstupidstuff.api.car.CarSoundManager;
import net.ent.entstupidstuff.api.emote.EmoteClientState;
import net.ent.entstupidstuff.api.emote.EmoteSyncPayload;
import net.ent.entstupidstuff.api.hat.ClientUnlockState;
import net.ent.entstupidstuff.api.hat.HatMenuScreen;
import net.ent.entstupidstuff.api.hat.HatRenderLayer;
import net.ent.entstupidstuff.api.hat.HatSyncPayload;
import net.ent.entstupidstuff.api.hat.ModAttachments;
import net.ent.entstupidstuff.api.hat.UnlockSyncPayload;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendering.v1.LivingEntityFeatureRendererRegistrationCallback;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.player.AvatarRenderer;
import net.minecraft.client.renderer.entity.state.AvatarRenderState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;

public class HatnEmoteClientUtil {

    @Nullable
    private static Screen pendingScreen = null;
    
    public static void onInitializeClient() {

        // ── Pending screen handler ────────────────────────────────────────────
 
        ClientTickEvents.START_CLIENT_TICK.register(mc -> {
            if (pendingScreen != null && mc.screen == null) {
                mc.setScreen(pendingScreen);
                pendingScreen = null;
            }
        });
 
        // ── Hat Menu command ──────────────────────────────────────────────────
 
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) ->
            dispatcher.register(
                ClientCommandManager.literal("hatmenu")
                    .executes(ctx -> {
                        pendingScreen = new HatMenuScreen();
                        return 1;
                    })
            )
        );
 
        // ── Networking ────────────────────────────────────────────────────────
 
        // Hat sync — updates the hat attachment on the target player entity
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
 
        // Phase 3: Unlock sync — updates ClientUnlockState so the GUI shows lock state
        ClientPlayNetworking.registerGlobalReceiver(UnlockSyncPayload.TYPE, (payload, context) ->
            context.client().execute(() -> ClientUnlockState.update(payload.unlockedHats()))
        );
 
        // Emote sync
        ClientPlayNetworking.registerGlobalReceiver(EmoteSyncPayload.TYPE, (payload, context) ->
            context.client().execute(() -> {
                var level = context.client().level;
                if (level == null) return;
                for (Player p : level.players()) {
                    if (!p.getUUID().equals(payload.playerUuid())) continue;
                    if (payload.emoteName().isEmpty()) {
                        EmoteClientState.stop(p.getId());
                    } else {
                        EmoteClientState.start(p.getId(), payload.emoteName(), p.tickCount);
                    }
                    break;
                }
            })
        );
 
        // On disconnect: clear all client-side state
        ClientPlayConnectionEvents.DISCONNECT.register((handler, client) -> {
            EmoteClientState.clear();
            ClientUnlockState.clear();  // Phase 3: clear cached unlocks
            CarSoundManager.stopAll();
        });
 
        // ── Rendering ─────────────────────────────────────────────────────────
 
        LivingEntityFeatureRendererRegistrationCallback.EVENT.register(
            (entityType, renderer, registrationHelper, context) -> {
                if (entityType == EntityType.PLAYER && renderer instanceof AvatarRenderer) {
                    @SuppressWarnings("unchecked")
                    var parent = (RenderLayerParent<AvatarRenderState, PlayerModel>) renderer;
                    registrationHelper.register(new HatRenderLayer(parent));
                }
            }
        );

    }


}
