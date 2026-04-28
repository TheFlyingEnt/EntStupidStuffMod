package net.ent.entstupidstuff.api.car;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import net.ent.entstupidstuff.api.car.BaseCarEntity;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

/**
 * /carconfig — in-game command for all car toggles.
 *
 * Usage:
 *   /carconfig debug debugMode        — standard drivetrain debug HUD
 *   /carconfig debug advancedDebug    — full per-tick physics snapshot
 *   /carconfig debug scenarioTest     — single-line state label
 *   /carconfig debug disable          — normal speed/RPM/gear HUD
 *
 *   /carconfig realisticSpeed true    — real-life top speeds
 *   /carconfig realisticSpeed false   — original game-tuned speeds
 *
 *   /carconfig surfaceFriction true   — enable ice/gravel/rain grip
 *   /carconfig surfaceFriction false  — bypass surface friction
 *
 *   /carconfig driveType rwd          — force rear-wheel drive
 *   /carconfig driveType fwd          — force front-wheel drive
 *   /carconfig driveType reset        — restore car's default
 *
 * Registration:
 *   Call CarConfigCommand.register(dispatcher) from your mod's
 *   command registration event.
 *
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

            // ── /carconfig debug <mode> ──────────────────────────────────
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

            // ── /carconfig realisticSpeed <true|false> ───────────────────
            .then(Commands.literal("realisticSpeed")
                .then(Commands.argument("enabled", BoolArgumentType.bool())
                    .executes(ctx -> setRealisticSpeed(
                        ctx.getSource(), BoolArgumentType.getBool(ctx, "enabled"))))
            )

            // ── /carconfig surfaceFriction <true|false> ──────────────────
            .then(Commands.literal("surfaceFriction")
                .then(Commands.argument("enabled", BoolArgumentType.bool())
                    .executes(ctx -> setSurfaceFriction(
                        ctx.getSource(), BoolArgumentType.getBool(ctx, "enabled"))))
            )

            // ── /carconfig driveType <rwd|fwd|reset> ─────────────────────
            .then(Commands.literal("driveType")
                .then(Commands.literal("rwd")
                    .executes(ctx -> setDriveType(ctx.getSource(), true)))
                .then(Commands.literal("fwd")
                    .executes(ctx -> setDriveType(ctx.getSource(), false)))
                .then(Commands.literal("reset")
                    .executes(ctx -> resetDriveType(ctx.getSource())))
            )
        );
    }

    // ═══════════════════════════════════════════════════════════
    //  HELPERS
    // ═══════════════════════════════════════════════════════════

    /**
     * Gets the BaseCarEntity the player is currently riding.
     * Returns null and sends an error message if not in a car.
     */
    private static BaseCarEntity getCarOrFail(CommandSourceStack source) {
        if (!(source.getEntity() instanceof ServerPlayer player)) {
            source.sendFailure(Component.literal("This command can only be used by a player."));
            return null;
        }
        if (!(player.getVehicle() instanceof BaseCarEntity car)) {
            source.sendFailure(Component.literal("§cYou must be driving a car to use /carconfig."));
            return null;
        }
        return car;
    }

    // ── Debug mode ───────────────────────────────────────────────────────

    private static int setDebug(CommandSourceStack source, String mode) {
        BaseCarEntity car = getCarOrFail(source);
        if (car == null) return 0;

        // Clear all debug flags first, then set the requested one
        car.debugMode     = false;
        car.advancedDebug = false;
        car.scenarioTest  = false;

        String label;
        switch (mode) {
            case "debugMode":
                car.debugMode = true;
                label = "§eDebug Mode §7(drivetrain focus)";
                break;
            case "advancedDebug":
                car.advancedDebug = true;
                label = "§dAdvanced Debug §7(full physics snapshot)";
                break;
            case "scenarioTest":
                car.scenarioTest = true;
                label = "§6Scenario Test §7(state label)";
                break;
            default:
                label = "§aNormal HUD §7(speed / RPM / gear)";
                break;
        }

        source.sendSuccess(() -> Component.literal("§f[CarConfig] HUD set to: " + label), false);
        return 1;
    }

    // ── Realistic speed ──────────────────────────────────────────────────

    private static int setRealisticSpeed(CommandSourceStack source, boolean enabled) {
        BaseCarEntity car = getCarOrFail(source);
        if (car == null) return 0;

        car.realisticSpeed = enabled;
        String state = enabled
            ? "§c[REAL] §fEnabled §7(×" + String.format("%.2f", car.getRealisticSpeedScaleValue()) + " speed)"
            : "§a[GAME] §fDisabled §7(original tuned speed)";

        source.sendSuccess(() -> Component.literal("§f[CarConfig] Realistic speed: " + state), false);
        return 1;
    }

    // ── Surface friction ─────────────────────────────────────────────────

    private static int setSurfaceFriction(CommandSourceStack source, boolean enabled) {
        BaseCarEntity car = getCarOrFail(source);
        if (car == null) return 0;

        car.surfaceFrictionEnabled = enabled;
        String state = enabled ? "§aEnabled" : "§cDisabled §7(all surfaces = asphalt)";

        source.sendSuccess(() -> Component.literal("§f[CarConfig] Surface friction: " + state), false);
        return 1;
    }

    // ── Drive type ───────────────────────────────────────────────────────

    private static int setDriveType(CommandSourceStack source, boolean rwd) {
        BaseCarEntity car = getCarOrFail(source);
        if (car == null) return 0;

        car.isRWD = rwd;
        String label = rwd ? "§bRWD §7(rear-wheel drive)" : "§eFWD §7(front-wheel drive)";

        source.sendSuccess(() -> Component.literal("§f[CarConfig] Drive type: " + label), false);
        return 1;
    }

    private static int resetDriveType(CommandSourceStack source) {
        BaseCarEntity car = getCarOrFail(source);
        if (car == null) return 0;

        car.isRWD = car.getDefaultIsRWD();
        String label = car.isRWD ? "§bRWD" : "§eFWD";

        source.sendSuccess(() -> Component.literal(
            "§f[CarConfig] Drive type reset to default: " + label), false);
        return 1;
    }
}
