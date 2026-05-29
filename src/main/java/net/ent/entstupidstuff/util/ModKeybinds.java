package net.ent.entstupidstuff.util;

import net.ent.entstupidstuff.api.car.BaseCarEntity;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import org.lwjgl.glfw.GLFW;

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

    public static void register() {
        KeyBindingHelper.registerKeyBinding(SHIFT_UP);
        KeyBindingHelper.registerKeyBinding(SHIFT_DOWN);
    }

    /**
     * Call every client tick. Detects key presses and sets
     * shift flags on the car entity the player is driving.
     */
    public static void tick() {
        if (!BaseCarEntity.manualTransmission) return;

        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null) return;
        if (!(mc.player.getVehicle() instanceof BaseCarEntity car)) return;

        // consumeClick() returns true once per press (edge detection)
        if (SHIFT_UP.consumeClick()) {
            car.requestShiftUp();
        }
        if (SHIFT_DOWN.consumeClick()) {
            car.requestShiftDown();
        }
    }

    private ModKeybinds() {}
}
