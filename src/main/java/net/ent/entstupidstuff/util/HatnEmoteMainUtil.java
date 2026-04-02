package net.ent.entstupidstuff.util;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.api.emote.EmoteCommand;
import net.ent.entstupidstuff.api.hat.HatAdminCommand;
import net.ent.entstupidstuff.api.hat.HatCommand;
import net.ent.entstupidstuff.api.hat.HatDataManager;
import net.ent.entstupidstuff.api.hat.HatRegistry;
import net.ent.entstupidstuff.api.hat.HatSelectPayload;
import net.ent.entstupidstuff.api.hat.HatSyncPayload;
import net.ent.entstupidstuff.api.hat.HatUnlockHelper;
import net.ent.entstupidstuff.api.hat.ModAttachments;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.server.level.ServerPlayer;

public class HatnEmoteMainUtil {

    public static void onInitialize() {
        // Commands
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            HatCommand.register(dispatcher);
            EmoteCommand.register(dispatcher);
            HatAdminCommand.register(dispatcher);  // Phase 3: /hatadmin grant|revoke|list|grantall
        });
 
        // On join: load unlocks from disk → populate attachment → sync to client
        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
            ServerPlayer joining = handler.getPlayer();
            server.execute(() -> {
                // Phase 3: load persisted JSON unlocks into the runtime attachment
                HatDataManager.load(server, joining);
 
                // Sync active hat
                String hat = joining.getAttachedOrElse(ModAttachments.HAT, "");
                if (!hat.isEmpty()) {
                    ServerPlayNetworking.send(joining, new HatSyncPayload(joining.getUUID(), hat));
                }
 
                // Phase 3: sync unlock set so client GUI shows correct lock state
                HatAdminCommand.syncUnlocks(joining);
            });
        });
 
        // Phase 3: on disconnect, write unlocks to disk
        ServerPlayConnectionEvents.DISCONNECT.register((handler, server) ->
            HatDataManager.save(server, handler.getPlayer())
        );
 
        // C2S hat select — Phase 3 adds unlock validation
        ServerPlayNetworking.registerGlobalReceiver(HatSelectPayload.TYPE, (payload, context) -> {
            ServerPlayer player = context.player();
            String name = payload.hatName();
 
            if (!name.isEmpty()) {
                if (!HatRegistry.isValid(name)) {
                    EntStupidStuff.LOGGER.warn("Player {} sent unknown hat name: '{}'", player.getName().getString(), name);
                    return;
                }
                // Phase 3: reject if the player doesn't have this hat unlocked
                if (!HatUnlockHelper.canWear(player, name)) {
                    EntStupidStuff.LOGGER.warn("Player {} tried to equip locked hat: '{}'", player.getName().getString(), name);
                    return;
                }
            }
 
            player.setAttached(ModAttachments.HAT, name);
            HatSyncPayload sync = new HatSyncPayload(player.getUUID(), name);
            ServerPlayNetworking.send(player, sync);
            PlayerLookup.tracking(player).forEach(observer ->
                ServerPlayNetworking.send(observer, sync)
            );
        });
    }
    
}
