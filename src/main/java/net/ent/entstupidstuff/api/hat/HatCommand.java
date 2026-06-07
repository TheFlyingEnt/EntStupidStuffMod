package net.ent.entstupidstuff.api.hat;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
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
                .then(Commands.literal("none")
                    .executes(ctx -> executeRemove(ctx.getSource()))
                )
                .then(Commands.argument("hat_name", StringArgumentType.word())
                    .suggests((ctx, builder) -> {
                        try {
                            ServerPlayer player = ctx.getSource().getPlayerOrException();
                            // Only suggest hats the player can actually wear
                            HatRegistry.getNames().stream()
                                .filter(n -> HatUnlockHelper.canWear(player, n))
                                .forEach(builder::suggest);
                        } catch (Exception e) {
                            HatRegistry.getNames().forEach(builder::suggest);
                        }
                        return builder.buildFuture();
                    })
                    .executes(ctx -> executeEquip(ctx.getSource(), StringArgumentType.getString(ctx, "hat_name")))
                )
        );
    }
 
    private static int executeEquip(CommandSourceStack source, String name) {
        ServerPlayer player;
        try {
            player = source.getPlayerOrException();
        } catch (Exception e) {
            source.sendFailure(Component.literal("This command can only be run by a player."));
            return 0;
        }
 
        if (!HatRegistry.isValid(name)) {
            source.sendFailure(Component.literal("Unknown hat: \"" + name + "\""));
            return 0;
        }
 
        if (!HatUnlockHelper.canWear(player, name)) {
            HatSource source2 = HatRegistry.getSource(name);
            source.sendFailure(Component.literal(
                "You haven't unlocked this hat! (" + source2.displayName().getString() + ")"
            ));
            return 0;
        }
 
        player.setAttached(ModAttachments.HAT, name);
        broadcast(player, name);
        source.sendSuccess(() -> Component.literal("Hat equipped: " + name), false);
        return 1;
    }
 
    private static int executeRemove(CommandSourceStack source) {
        ServerPlayer player;
        try {
            player = source.getPlayerOrException();
        } catch (Exception e) {
            source.sendFailure(Component.literal("This command can only be run by a player."));
            return 0;
        }
 
        player.setAttached(ModAttachments.HAT, "");
        broadcast(player, "");
        source.sendSuccess(() -> Component.literal("Hat removed."), false);
        return 1;
    }
 
    public static void broadcast(ServerPlayer player, String hatName) {
        HatSyncPayload payload = new HatSyncPayload(player.getUUID(), hatName);
        ServerPlayNetworking.send(player, payload);
        PlayerLookup.tracking(player).forEach(o -> ServerPlayNetworking.send(o, payload));
    }
}