package net.ent.entstupidstuff.api.cutscene;

import net.minecraft.client.MinecraftClient;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CutsceneManager {
    private static CutsceneScreen currentCutscene = null;
    private static boolean playerMovementDisabled = false;
    private static boolean hideGui = false;

    public static void playCutscene(String filename, boolean disableMovement, boolean hideHud) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client == null) return;

        // Get cutscene file path from config/cutscenes folder
        Path cutscenePath = Paths.get("config", "cutscenes", filename);
        
        playerMovementDisabled = disableMovement;
        hideGui = hideHud;

        // Create and show cutscene screen
        client.execute(() -> {
            currentCutscene = new CutsceneScreen(cutscenePath.toString(), disableMovement, hideHud);
            //currentCutscene = new CutsceneScreen(cutscenePath.toString());
            client.setScreen(currentCutscene);
        });
    }

    public static void stopCutscene() {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client != null && currentCutscene != null) {
            CutsceneScreen screenToClose = currentCutscene;
            currentCutscene = null; // Clear reference FIRST to prevent recursion
            
            client.execute(() -> {
                if (screenToClose != null) {
                    screenToClose.cleanup(); // Use cleanup instead of close
                }
                client.setScreen(null);
            });
        }
    }

    public static boolean isPlaying() {
        return currentCutscene != null;
    }

    public static boolean isPlayerMovementDisabled() {
        return playerMovementDisabled && isPlaying();
    }

    public static boolean shouldHideGui() {
        return hideGui && isPlaying();
    }
}
