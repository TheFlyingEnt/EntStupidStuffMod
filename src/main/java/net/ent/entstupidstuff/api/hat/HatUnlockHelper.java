package net.ent.entstupidstuff.api.hat;

import net.minecraft.world.entity.player.Player;
import java.util.Set;
 
/**
 * Single authority for hat unlock checks.
 *
 * Rules (in order):
 *   1. Creative mode  → access to everything
 *   2. HatSource.DEFAULT → always unlocked
 *   3. Otherwise → hat name must be in the player's UNLOCKED_HATS attachment
 */
public class HatUnlockHelper {
 
    private HatUnlockHelper() {}
 
    public static boolean canWear(Player player, String hatName) {
        if (!HatRegistry.isValid(hatName)) return false;
        if (player.isCreative()) return true;
 
        HatSource source = HatRegistry.getSource(hatName);
        if (!source.requiresUnlock()) return true;
 
        Set<String> unlocked = player.getAttachedOrElse(ModAttachments.UNLOCKED_HATS, Set.of());
        return unlocked.contains(hatName);
    }
 
    /**
     * Grants a hat. Returns true if newly added, false if already had it.
     */
    public static boolean grant(Player player, String hatName) {
        Set<String> unlocked = player.getAttachedOrCreate(ModAttachments.UNLOCKED_HATS);
        return unlocked.add(hatName);
    }
 
    /**
     * Revokes a hat. Also clears it if currently worn.
     * Returns true if the hat was actually removed.
     */
    public static boolean revoke(Player player, String hatName) {
        Set<String> unlocked = player.getAttachedOrCreate(ModAttachments.UNLOCKED_HATS);
        boolean removed = unlocked.remove(hatName);
        if (removed && hatName.equals(player.getAttachedOrElse(ModAttachments.HAT, ""))) {
            player.setAttached(ModAttachments.HAT, "");
        }
        return removed;
    }
}