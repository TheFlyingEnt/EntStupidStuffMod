package net.ent.entstupidstuff.api.hat;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;

import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

public class HatCommand {
 
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(
            Commands.literal("hat")
                // /hat none  ─ removes the hat
                .then(Commands.literal("none")
                    .executes(ctx -> executeRemove(ctx))
                )
                // /hat <name>  ─ equips a hat
                .then(Commands.argument("hat_name", StringArgumentType.word())
                    .suggests((ctx, builder) -> {
                        HatRegistry.getNames().forEach(builder::suggest);
                        return builder.buildFuture();
                    })
                    .executes(ctx -> executeEquip(ctx, StringArgumentType.getString(ctx, "hat_name")))
                )
        );
    }
 
    // -----------------------------------------------------------------------
 
    private static int executeEquip(CommandContext<CommandSourceStack> ctx, String name) {
        CommandSourceStack source = ctx.getSource();
 
        ServerPlayer player;
        try {
            player = source.getPlayerOrException();
        } catch (Exception e) {
            source.sendFailure(Component.literal("This command can only be run by a player."));
            return 0;
        }
 
        if (!HatRegistry.isValid(name)) {
            source.sendFailure(Component.literal(
                "Unknown hat: \"" + name + "\". Use /hat none to remove your hat."
            ));
            return 0;
        }
 
        // Store on server
        player.setAttached(ModAttachments.HAT, name);
 
        // Sync to all clients tracking this player (including the player themselves)
        broadcastHat(player, name);
 
        source.sendSuccess(() -> Component.literal("Hat equipped: " + name), false);
        return 1;
    }
 
    private static int executeRemove(CommandContext<CommandSourceStack> ctx) {
        CommandSourceStack source = ctx.getSource();
 
        ServerPlayer player;
        try {
            player = source.getPlayerOrException();
        } catch (Exception e) {
            source.sendFailure(Component.literal("This command can only be run by a player."));
            return 0;
        }
 
        // Clear the attachment
        player.setAttached(ModAttachments.HAT, "");
 
        // Sync removal
        broadcastHat(player, "");
 
        source.sendSuccess(() -> Component.literal("Hat removed."), false);
        return 1;
    }
 
    /**
     * Sends a {@link HatSyncPayload} to the player themselves and every player
     * currently tracking them (i.e. within render distance).
     */
    private static void broadcastHat(ServerPlayer player, String hatName) {
        HatSyncPayload payload = new HatSyncPayload(player.getUUID(), hatName);
 
        // Send to all observers
        PlayerLookup.tracking(player).forEach(observer ->
            ServerPlayNetworking.send(observer, payload)
        );
 
        // Also send to the player themselves so their own view updates
        ServerPlayNetworking.send(player, payload);
    }
}