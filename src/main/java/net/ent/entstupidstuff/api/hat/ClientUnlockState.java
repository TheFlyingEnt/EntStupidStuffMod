package net.ent.entstupidstuff.api.hat;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import net.minecraft.client.Minecraft;
 
/**
 * Client-side cache of the player's unlocked hat set.
 * Populated from {@link com.example.hatsmod.network.UnlockSyncPayload} packets.
 */
public class ClientUnlockState {
 
    private static Set<String> unlockedHats = new HashSet<>();
 
    public static void update(Set<String> hats) {
        unlockedHats = new HashSet<>(hats);
    }
 
    public static void clear() {
        unlockedHats.clear();
    }
 
    /**
     * Returns true if the local player can wear this hat.
     * Mirrors {@link com.example.hatsmod.HatUnlockHelper#canWear} logic on the client.
     */
    public static boolean canWear(String hatName) {
        if (!HatRegistry.isValid(hatName)) return false;
 
        // Creative bypass
        var player = Minecraft.getInstance().player;
        if (player != null && player.isCreative()) return true;
 
        HatSource source = HatRegistry.getSource(hatName);
        if (source == null) return false;
 
        if (!source.requiresUnlock()) return true;
 
        return unlockedHats.contains(hatName);
    }
 
    public static Set<String> getUnlockedHats() {
        return Collections.unmodifiableSet(unlockedHats);
    }
}
