package net.ent.entstupidstuff.api.car;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

/**
 * /carconfig — in-game command for all car toggles.
 *
 * GLOBAL (apply to all cars, no need to be driving):
 *   /carconfig debug debugMode          — drivetrain debug HUD
 *   /carconfig debug advancedDebug      — full physics snapshot
 *   /carconfig debug scenarioTest       — single-line state label
 *   /carconfig debug disable            — normal speed/RPM/gear HUD
 *
 *   /carconfig realisticSpeed true      — real-life top speeds (all cars)
 *   /carconfig realisticSpeed false     — original game-tuned speeds
 *
 *   /carconfig forzaTurning true        — smooth Forza-style keyboard steering
 *   /carconfig forzaTurning false       — raw digital A/D = instant full lock
 *
 * PER-CAR (must be driving):
 *   /carconfig surfaceFriction true     — enable ice/gravel/rain grip
 *   /carconfig surfaceFriction false    — bypass surface friction
 *
 *   /carconfig driveType rwd            — force rear-wheel drive
 *   /carconfig driveType fwd            — force front-wheel drive
 *   /carconfig driveType reset          — restore car's default
 *
 * Registration:
 *   Fabric:
 *     CommandRegistrationCallback.EVENT.register((dispatcher, access, env) ->
 *         CarConfigCommand.register(dispatcher));
 *
 *   NeoForge:
 *     @SubscribeEvent
 *     public static void onRegisterCommands(RegisterCommandsEvent event) {
 *         CarConfigCommand.register(event.getDispatcher());
 *     }
 */
public class CarConfigCommand {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("carconfig")

            // ── GLOBAL: /carconfig debug <mode> ──────────────────────────
            .then(Commands.literal("debug")
                .then(Commands.literal("debugMode")
                    .executes(ctx -> setDebug(ctx.getSource(), "debugMode")))
                .then(Commands.literal("advancedDebug")
                    .executes(ctx -> setDebug(ctx.getSource(), "advancedDebug")))
                .then(Commands.literal("scenarioTest")
                    .executes(ctx -> setDebug(ctx.getSource(), "scenarioTest")))
                .then(Commands.literal("disable")
                    .executes(ctx -> setDebug(ctx.getSource(), "disable")))
            )

            // ── GLOBAL: /carconfig realisticSpeed <true|false> ───────────
            .then(Commands.literal("realisticSpeed")
                .then(Commands.argument("enabled", BoolArgumentType.bool())
                    .executes(ctx -> setRealisticSpeed(
                        ctx.getSource(), BoolArgumentType.getBool(ctx, "enabled"))))
            )

            // ── GLOBAL: /carconfig forzaTurning <true|false> ─────────────
            .then(Commands.literal("forzaTurning")
                .then(Commands.argument("enabled", BoolArgumentType.bool())
                    .executes(ctx -> setForzaTurning(
                        ctx.getSource(), BoolArgumentType.getBool(ctx, "enabled"))))
            )

            // ── PER-CAR: /carconfig surfaceFriction <true|false> ─────────
            .then(Commands.literal("surfaceFriction")
                .then(Commands.argument("enabled", BoolArgumentType.bool())
                    .executes(ctx -> setSurfaceFriction(
                        ctx.getSource(), BoolArgumentType.getBool(ctx, "enabled"))))
            )

            // ── PER-CAR: /carconfig driveType <rwd|fwd|reset> ────────────
            .then(Commands.literal("driveType")
                .then(Commands.literal("rwd")
                    .executes(ctx -> setDriveType(ctx.getSource(), true)))
                .then(Commands.literal("fwd")
                    .executes(ctx -> setDriveType(ctx.getSource(), false)))
                .then(Commands.literal("reset")
                    .executes(ctx -> resetDriveType(ctx.getSource())))
            )

            .then(Commands.literal("manualTransmission")
                .then(Commands.argument("enabled", BoolArgumentType.bool())
                    .executes(ctx -> {
                        boolean enabled = BoolArgumentType.getBool(ctx, "enabled");
                        BaseCarEntity.manualTransmission = enabled;
                        ctx.getSource().sendSuccess(
                            () -> Component.literal(enabled
                                ? "§d[MAN] Manual transmission ON — use R to shift up, F to shift down"
                                : "§7[AUTO] Automatic transmission restored"),
                            false);
                        return 1;
                    })
                )
            )

            .then(Commands.literal("perCarSteering")
                .then(Commands.argument("enabled", BoolArgumentType.bool())
                    .executes(ctx -> {
                        boolean enabled = BoolArgumentType.getBool(ctx, "enabled");
                        BaseCarEntity.perCarSteering = enabled;
                        ctx.getSource().sendSuccess(
                            () -> Component.literal(enabled
                                ? "§b[STEER] Per-car steering sensitivity ON"
                                : "§7[STEER] Universal steering sensitivity (0.6/0.55)"),
                            false);
                        return 1;
                    })
                )
            )

            .then(Commands.literal("bodykit")
                .then(Commands.argument("kit", StringArgumentType.word())
                    .executes(ctx -> {
                        var player = ctx.getSource().getPlayerOrException();
                        if (!(player.getVehicle() instanceof BaseCarEntity car)) {
                            ctx.getSource().sendFailure(Component.literal("Must be driving a car"));
                            return 0;
                        }
                        String kit = StringArgumentType.getString(ctx, "kit");
                        // Validate
                        if (!kit.equals("none")) {
                            boolean valid = false;
                            for (String k : car.availableBodyKits()) {
                                if (k.equals(kit)) { valid = true; break; }
                            }
                            if (!valid) {
                                ctx.getSource().sendFailure(Component.literal(
                                    "Available kits: none, " + String.join(", ", car.availableBodyKits())));
                                return 0;
                            }
                        }
                        //car.getEntityData().set(DATA_BODYKIT, kit);
                        car.setCurrentBodyKit(kit);
                        ctx.getSource().sendSuccess(
                            () -> Component.literal("§a[BODYKIT] Set to: " + kit), false);
                        return 1;
                    })
                )
            )

            .then(Commands.literal("carCollision")
                .then(Commands.argument("enabled", BoolArgumentType.bool())
                    .executes(ctx -> {
                        boolean enabled = BoolArgumentType.getBool(ctx, "enabled");
                        BaseCarEntity.carCollisionEnabled = enabled;
                        ctx.getSource().sendSuccess(
                            () -> Component.literal(enabled
                                ? "§c[COLLISION] Car-to-car collision ON — cars bounce off each other"
                                : "§7[COLLISION] Car-to-car collision OFF — cars pass through each other"),
                            false);
                        return 1;
                    })
                )
            )

            .then(Commands.literal("cameraWeight")
                .then(Commands.argument("on", BoolArgumentType.bool())
                    .executes(ctx -> {
                        CameraWeightHandler.enabled =
                        BoolArgumentType.getBool(ctx, "on");
                        ctx.getSource().sendSuccess(() -> net.minecraft.network.chat.Component.literal(
                            "Camera weight: " + (CameraWeightHandler.enabled ? "ON" : "OFF")), false);
                    return 1;
                }))
            )


        );
    }

    // ═══════════════════════════════════════════════════════════
    //  HELPERS
    // ═══════════════════════════════════════════════════════════

    /** Returns the ServerPlayer or sends an error. */
    private static ServerPlayer getPlayerOrFail(CommandSourceStack source) {
        if (!(source.getEntity() instanceof ServerPlayer player)) {
            source.sendFailure(Component.literal("This command can only be used by a player."));
            return null;
        }
        return player;
    }

    /** Returns the BaseCarEntity the player is riding, or sends an error. */
    private static BaseCarEntity getCarOrFail(CommandSourceStack source) {
        ServerPlayer player = getPlayerOrFail(source);
        if (player == null) return null;
        if (!(player.getVehicle() instanceof BaseCarEntity car)) {
            source.sendFailure(Component.literal(
                "§cYou must be driving a car to use this command."));
            return null;
        }
        return car;
    }

    // ═══════════════════════════════════════════════════════════
    //  GLOBAL COMMANDS  (static fields — apply to every car)
    // ═══════════════════════════════════════════════════════════

    private static int setDebug(CommandSourceStack source, String mode) {
        if (getPlayerOrFail(source) == null) return 0;

        // Clear all, then set requested
        BaseCarEntity.debugMode     = false;
        BaseCarEntity.advancedDebug = false;
        BaseCarEntity.scenarioTest  = false;

        String label;
        switch (mode) {
            case "debugMode":
                BaseCarEntity.debugMode = true;
                label = "§eDebug Mode §7(drivetrain focus)";
                break;
            case "advancedDebug":
                BaseCarEntity.advancedDebug = true;
                label = "§dAdvanced Debug §7(full physics snapshot)";
                break;
            case "scenarioTest":
                BaseCarEntity.scenarioTest = true;
                label = "§6Scenario Test §7(state label)";
                break;
            default:
                label = "§aNormal HUD §7(speed / RPM / gear)";
                break;
        }

        final String msg = label;
        source.sendSuccess(() -> Component.literal("§f[CarConfig] HUD: " + msg), false);
        return 1;
    }

    private static int setRealisticSpeed(CommandSourceStack source, boolean enabled) {
        if (getPlayerOrFail(source) == null) return 0;

        BaseCarEntity.realisticSpeed = enabled;
        String state = enabled
            ? "§aEnabled §7(real-life top speeds for all cars)"
            : "§cDisabled §7(original game-tuned speeds)";

        source.sendSuccess(() -> Component.literal(
            "§f[CarConfig] Realistic Speed: " + state), false);
        return 1;
    }

    private static int setForzaTurning(CommandSourceStack source, boolean enabled) {
        if (getPlayerOrFail(source) == null) return 0;

        BaseCarEntity.forzaTurning = enabled;
        String state = enabled
            ? "§aEnabled §7(smooth ramped keyboard steering)"
            : "§cDisabled §7(raw digital A/D = instant full lock)";

        source.sendSuccess(() -> Component.literal(
            "§f[CarConfig] Forza Turning: " + state), false);
        return 1;
    }

    // ═══════════════════════════════════════════════════════════
    //  PER-CAR COMMANDS  (instance fields — must be driving)
    // ═══════════════════════════════════════════════════════════

    private static int setSurfaceFriction(CommandSourceStack source, boolean enabled) {
        BaseCarEntity car = getCarOrFail(source);
        if (car == null) return 0;

        car.surfaceFrictionEnabled = enabled;
        String state = enabled
            ? "§aEnabled §7(ice/gravel/rain active)"
            : "§cDisabled §7(all surfaces = asphalt)";

        source.sendSuccess(() -> Component.literal(
            "§f[CarConfig] Surface Friction: " + state), false);
        return 1;
    }

    private static int setDriveType(CommandSourceStack source, boolean rwd) {
        BaseCarEntity car = getCarOrFail(source);
        if (car == null) return 0;

        car.isRWD = rwd;
        String label = rwd
            ? "§bRWD §7(rear-wheel drive)"
            : "§eFWD §7(front-wheel drive)";

        source.sendSuccess(() -> Component.literal(
            "§f[CarConfig] Drive Type: " + label), false);
        return 1;
    }

    private static int resetDriveType(CommandSourceStack source) {
        BaseCarEntity car = getCarOrFail(source);
        if (car == null) return 0;

        car.isRWD = car.getDefaultIsRWD();
        String label = car.isRWD ? "§bRWD" : "§eFWD";

        source.sendSuccess(() -> Component.literal(
            "§f[CarConfig] Drive Type reset to default: " + label), false);
        return 1;
    }
}