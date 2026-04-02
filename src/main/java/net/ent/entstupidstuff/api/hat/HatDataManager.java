package net.ent.entstupidstuff.api.hat;
 
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.ent.entstupidstuff.EntStupidStuff;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.storage.LevelResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
 
/**
 * Persists hat unlock data as human-readable JSON files.
 *
 * Location (relative to world save):
 *   data/entstupidstuff/hat_unlocks/<uuid>.json
 *
 * Example file:
 *   {
 *     "unlocked_hats": [
 *       "baseball_cap_alpha",
 *       "baseball_cap_zeezo2021"
 *     ]
 *   }
 *
 * Files can be edited with any text editor while the server is stopped.
 * Unknown hat names are skipped with a warning rather than crashing.
 */
public class HatDataManager {
 
    private static final Logger LOGGER = LoggerFactory.getLogger(EntStupidStuff.MOD_ID + "/hat_data");
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final String KEY = "unlocked_hats";
 
    private HatDataManager() {}
 
    private static Path getDir(MinecraftServer server) throws IOException {
        Path dir = server.getWorldPath(LevelResource.ROOT)
            .resolve("data")
            .resolve(EntStupidStuff.MOD_ID)
            .resolve("hat_unlocks");
        Files.createDirectories(dir);
        return dir;
    }
 
    private static Path getFile(MinecraftServer server, UUID uuid) throws IOException {
        return getDir(server).resolve(uuid + ".json");
    }
 
    /**
     * Loads unlock data from disk into the player's runtime attachment.
     * Called on player join.
     */
    public static void load(MinecraftServer server, ServerPlayer player) {
        try {
            Path file = getFile(server, player.getUUID());
            if (!Files.exists(file)) {
                player.setAttached(ModAttachments.UNLOCKED_HATS, new HashSet<>());
                return;
            }
 
            JsonObject root = JsonParser.parseString(Files.readString(file)).getAsJsonObject();
            Set<String> unlocked = new HashSet<>();
 
            if (root.has(KEY)) {
                for (var el : root.getAsJsonArray(KEY)) {
                    String name = el.getAsString();
                    if (HatRegistry.isValid(name)) {
                        unlocked.add(name);
                    } else {
                        LOGGER.warn("[HatData] Unknown hat '{}' in {}.json — skipping", name, player.getUUID());
                    }
                }
            }
 
            player.setAttached(ModAttachments.UNLOCKED_HATS, unlocked);
            LOGGER.debug("[HatData] Loaded {} unlock(s) for {}", unlocked.size(), player.getName().getString());
 
        } catch (Exception e) {
            LOGGER.error("[HatData] Failed to load for {}: {}", player.getUUID(), e.getMessage());
            player.setAttached(ModAttachments.UNLOCKED_HATS, new HashSet<>());
        }
    }
 
    /**
     * Writes the player's current unlock set to disk.
     * Called on disconnect and immediately after any grant/revoke.
     */
    public static void save(MinecraftServer server, ServerPlayer player) {
        try {
            Set<String> unlocked = player.getAttachedOrElse(ModAttachments.UNLOCKED_HATS, Set.of());
 
            JsonArray array = new JsonArray();
            unlocked.stream().sorted().forEach(array::add);  // sorted for readable diffs
            JsonObject root = new JsonObject();
            root.add(KEY, array);
 
            Files.writeString(getFile(server, player.getUUID()), GSON.toJson(root));
            LOGGER.debug("[HatData] Saved {} unlock(s) for {}", unlocked.size(), player.getName().getString());
 
        } catch (IOException e) {
            LOGGER.error("[HatData] Failed to save for {}: {}", player.getUUID(), e.getMessage());
        }
    }
}
