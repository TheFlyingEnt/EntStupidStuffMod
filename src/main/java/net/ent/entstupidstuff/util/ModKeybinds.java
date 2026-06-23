package net.ent.entstupidstuff.util;

import net.ent.entstupidstuff.api.car.BaseCarEntity;
import net.ent.entstupidstuff.api.ship.CustomBoatEntity;
import net.ent.entstupidstuff.api.ship.SailControlPayload;
import net.ent.entstupidstuff.api.ship.SwapSeatPayload;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import org.lwjgl.glfw.GLFW;

import com.mojang.blaze3d.platform.InputConstants;

/**
 * Registers and handles keybinds for the car mod.
 *
 * Keybinds:
 *   R — Shift Up   (manual transmission mode)
 *   F — Shift Down  (manual transmission mode)
 *
 * Call ModKeybinds.register() from onInitializeClient().
 * Call ModKeybinds.tick() from ClientTickEvents.END_CLIENT_TICK.
 */
public final class ModKeybinds {

    public static final KeyMapping SHIFT_UP = new KeyMapping(
        "key.entstupidstuff.shift_up",
        GLFW.GLFW_KEY_R,
        KeyMapping.Category.MOVEMENT
        //"category.entstupidstuff.car"
    );

    public static final KeyMapping SHIFT_DOWN = new KeyMapping(
        "key.entstupidstuff.shift_down",
        GLFW.GLFW_KEY_F,
        KeyMapping.Category.MOVEMENT//"category.entstupidstuff.car"
    );

    public static final KeyMapping SWAP_SEAT = new KeyMapping(
        "key.entstupidstuff.swap_seat",
        InputConstants.Type.KEYSYM,
        GLFW.GLFW_KEY_LEFT_CONTROL,
        KeyMapping.Category.GAMEPLAY
    );

    public static void register() {
        KeyBindingHelper.registerKeyBinding(SHIFT_UP);
        KeyBindingHelper.registerKeyBinding(SHIFT_DOWN);
        KeyBindingHelper.registerKeyBinding(SWAP_SEAT);
        KeyBindingHelper.registerKeyBinding(ANCHOR);
    }

    public static final KeyMapping ANCHOR = new KeyMapping(
    "key.entstupidstuff.anchor", InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_G, KeyMapping.Category.GAMEPLAY);

    private static int sailCooldown = 0;

    /**
     * Call every client tick. Detects key presses and sets
     * shift flags on the car entity the player is driving.
     */
    public static void tick() {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null) return;
 
        // ── Boat seat swap ─────────────────────────────────────────────
        // Handled FIRST and independently of the car checks below, because
        // when you're in a boat getVehicle() is NOT a BaseCarEntity and the
        // car guards would otherwise return early before we ever get here.
        // The loop always consumes clicks so they don't queue up while
        // you're on foot; it only sends a packet when you're in a boat.
        while (SWAP_SEAT.consumeClick()) {
            if (mc.player.getVehicle() instanceof CustomBoatEntity boat) {
                ClientPlayNetworking.send(new SwapSeatPayload(boat.getId()));
            }
        }

        if (mc.player.getVehicle() instanceof CustomBoatEntity boat) {
            if (sailCooldown > 0) sailCooldown--;
            if (sailCooldown == 0) {
                if (mc.options.keyUp.isDown() && boat.getSailLevel() < CustomBoatEntity.SAIL_MAX) {
                    ClientPlayNetworking.send(new SailControlPayload(boat.getId(), SailControlPayload.RAISE));
                    sailCooldown = 8;                       // ~0.4s per notch → ~1.2s to full sail
                } else if (mc.options.keyDown.isDown() && boat.getSailLevel() > 0) {
                    ClientPlayNetworking.send(new SailControlPayload(boat.getId(), SailControlPayload.LOWER));
                    sailCooldown = 8;
                }
            }
            if (ANCHOR.consumeClick()) {
                ClientPlayNetworking.send(new SailControlPayload(boat.getId(), SailControlPayload.ANCHOR));
            }
        }

 
        // ── Car manual transmission ────────────────────────────────────
        if (!BaseCarEntity.manualTransmission) return;
        if (!(mc.player.getVehicle() instanceof BaseCarEntity car)) return;
 
        if (SHIFT_UP.consumeClick()) {
            car.requestShiftUp();
        }
        if (SHIFT_DOWN.consumeClick()) {
            car.requestShiftDown();
        }
    }



    private ModKeybinds() {}
}
