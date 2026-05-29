package net.ent.entstupidstuff.api.emote;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;

import net.ent.entstupidstuff.api.hat.ModAttachments;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

public class EmoteCommand {
 
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(
            Commands.literal("emote")
                // /emote stop
                .then(Commands.literal("stop")
                    .executes(ctx -> executeStop(ctx.getSource()))
                )
                // /emote <name>
                .then(Commands.argument("emote_name", StringArgumentType.word())
                    .suggests((ctx, builder) -> {
                        EmoteNames.getNames().forEach(builder::suggest);
                        return builder.buildFuture();
                    })
                    .executes(ctx -> executePlay(
                        ctx.getSource(),
                        StringArgumentType.getString(ctx, "emote_name")
                    ))
                )
        );
    }
 
    // ── Handlers ─────────────────────────────────────────────────────────────
 
    private static int executePlay(CommandSourceStack source, String name) {
        ServerPlayer player;
        try {
            player = source.getPlayerOrException();
        } catch (Exception e) {
            source.sendFailure(Component.literal("This command can only be run by a player."));
            return 0;
        }
 
        if (!EmoteNames.isValid(name)) {
            source.sendFailure(Component.literal(
                "Unknown emote: \"" + name + "\". Use /emote stop to cancel."
            ));
            return 0;
        }
 
        player.setAttached(ModAttachments.EMOTE, name);
        broadcast(player, name);
 
        source.sendSuccess(() -> Component.literal("Playing emote: " + name), false);
        return 1;
    }
 
    private static int executeStop(CommandSourceStack source) {
        ServerPlayer player;
        try {
            player = source.getPlayerOrException();
        } catch (Exception e) {
            source.sendFailure(Component.literal("This command can only be run by a player."));
            return 0;
        }
 
        player.setAttached(ModAttachments.EMOTE, "");
        broadcast(player, "");
 
        source.sendSuccess(() -> Component.literal("Emote stopped."), false);
        return 1;
    }
 
    /** Sends the emote sync packet to the player and all nearby observers. */
    public static void broadcast(ServerPlayer player, String emoteName) {
        EmoteSyncPayload payload = new EmoteSyncPayload(player.getUUID(), emoteName);
        ServerPlayNetworking.send(player, payload);
        PlayerLookup.tracking(player).forEach(observer ->
            ServerPlayNetworking.send(observer, payload)
        );
    }
}
