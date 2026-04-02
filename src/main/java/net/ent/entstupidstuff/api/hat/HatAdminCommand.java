package net.ent.entstupidstuff.api.hat;

import java.util.Collection;
import java.util.Set;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

public class HatAdminCommand {
 
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(
            Commands.literal("hatadmin")
                .requires(src -> src.hasPermission(2))
 
                // /hatadmin grant <players> <hat>
                .then(Commands.literal("grant")
                    .then(Commands.argument("players", EntityArgument.players())
                        .then(Commands.argument("hat", StringArgumentType.word())
                            .suggests((ctx, b) -> { HatRegistry.getNames().forEach(b::suggest); return b.buildFuture(); })
                            .executes(ctx -> executeGrant(
                                ctx.getSource(),
                                EntityArgument.getPlayers(ctx, "players"),
                                StringArgumentType.getString(ctx, "hat")
                            ))
                        )
                    )
                )
 
                // /hatadmin revoke <players> <hat>
                .then(Commands.literal("revoke")
                    .then(Commands.argument("players", EntityArgument.players())
                        .then(Commands.argument("hat", StringArgumentType.word())
                            .suggests((ctx, b) -> { HatRegistry.getNames().forEach(b::suggest); return b.buildFuture(); })
                            .executes(ctx -> executeRevoke(
                                ctx.getSource(),
                                EntityArgument.getPlayers(ctx, "players"),
                                StringArgumentType.getString(ctx, "hat")
                            ))
                        )
                    )
                )
 
                // /hatadmin list <player>
                .then(Commands.literal("list")
                    .then(Commands.argument("player", EntityArgument.player())
                        .executes(ctx -> executeList(
                            ctx.getSource(),
                            EntityArgument.getPlayer(ctx, "player")
                        ))
                    )
                )
 
                // /hatadmin grantall <players>
                .then(Commands.literal("grantall")
                    .then(Commands.argument("players", EntityArgument.players())
                        .executes(ctx -> executeGrantAll(
                            ctx.getSource(),
                            EntityArgument.getPlayers(ctx, "players")
                        ))
                    )
                )
        );
    }
 
    private static int executeGrant(CommandSourceStack src, Collection<ServerPlayer> players, String hat) {
        if (!HatRegistry.isValid(hat)) {
            src.sendFailure(Component.literal("Unknown hat: " + hat));
            return 0;
        }
        int count = 0;
        for (ServerPlayer player : players) {
            if (HatUnlockHelper.grant(player, hat)) {
                HatDataManager.save(src.getServer(), player);
                syncUnlocks(player);
                src.sendSuccess(() -> Component.literal("Granted '" + hat + "' to " + player.getName().getString()), true);
                count++;
            } else {
                src.sendSuccess(() -> Component.literal(player.getName().getString() + " already has '" + hat + "'"), false);
            }
        }
        return count;
    }
 
    private static int executeRevoke(CommandSourceStack src, Collection<ServerPlayer> players, String hat) {
        if (!HatRegistry.isValid(hat)) {
            src.sendFailure(Component.literal("Unknown hat: " + hat));
            return 0;
        }
        int count = 0;
        for (ServerPlayer player : players) {
            if (HatUnlockHelper.revoke(player, hat)) {
                // If the hat was removed from their head, sync that too
                if (player.getAttachedOrElse(ModAttachments.HAT, "").isEmpty()) {
                    HatSyncPayload hatSync = new HatSyncPayload(player.getUUID(), "");
                    ServerPlayNetworking.send(player, hatSync);
                    PlayerLookup.tracking(player).forEach(o -> ServerPlayNetworking.send(o, hatSync));
                }
                HatDataManager.save(src.getServer(), player);
                syncUnlocks(player);
                src.sendSuccess(() -> Component.literal("Revoked '" + hat + "' from " + player.getName().getString()), true);
                count++;
            } else {
                src.sendSuccess(() -> Component.literal(player.getName().getString() + " doesn't have '" + hat + "'"), false);
            }
        }
        return count;
    }
 
    private static int executeList(CommandSourceStack src, ServerPlayer player) {
        Set<String> unlocked = player.getAttachedOrElse(ModAttachments.UNLOCKED_HATS, Set.of());
        src.sendSuccess(() -> Component.literal(
            player.getName().getString() + " unlocked hats: " +
            (unlocked.isEmpty() ? "(none)" : String.join(", ", unlocked))
        ), false);
        return unlocked.size();
    }
 
    private static int executeGrantAll(CommandSourceStack src, Collection<ServerPlayer> players) {
        int total = 0;
        for (ServerPlayer player : players) {
            for (String hat : HatRegistry.getNames()) {
                if (HatRegistry.getSource(hat).requiresUnlock()) {
                    HatUnlockHelper.grant(player, hat);
                    total++;
                }
            }
            HatDataManager.save(src.getServer(), player);
            syncUnlocks(player);
            src.sendSuccess(() -> Component.literal("Granted all hats to " + player.getName().getString()), true);
        }
        return total;
    }
 
    /** Sends the player's current unlock set to their own client. */
    public static void syncUnlocks(ServerPlayer player) {
        Set<String> unlocked = player.getAttachedOrElse(ModAttachments.UNLOCKED_HATS, Set.of());
        ServerPlayNetworking.send(player, new UnlockSyncPayload(unlocked));
    }
}