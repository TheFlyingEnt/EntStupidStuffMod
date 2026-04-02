package net.ent.entstupidstuff.api.hat;

import net.minecraft.network.chat.Component;
 
/**
 * Defines how a hat is obtained. Controls who can wear it.
 *
 * DEFAULT     — available to everyone, no unlock needed
 * BETA_TESTER — granted manually to beta testers via /hatadmin grant
 * ACHIEVEMENT — granted when the player earns a specific achievement/advancement
 * EVENT       — granted during limited-time events via /hatadmin grant
 */

public enum HatSource {
 
    DEFAULT(
        "gui.hatsmod.source.default",
        0xFFAAAAAA
    ),
    BETA_TESTER(
        "gui.hatsmod.source.beta_tester",
        0xFFFFD700   // gold
    ),
    ACHIEVEMENT(
        "gui.hatsmod.source.achievement",
        0xFF55FFFF   // aqua
    ),
    EVENT(
        "gui.hatsmod.source.event",
        0xFFFF55FF   // light purple
    );
 
    private final String langKey;
    private final int    color;    // ARGB for GUI display
 
    HatSource(String langKey, int color) {
        this.langKey = langKey;
        this.color   = color;
    }
 
    public Component displayName() {
        return Component.translatable(langKey);
    }

    public Component badge() {
        String a = Component.translatable(langKey).getString();
        return Component.literal("[" + a + "]");
    }
 
    public int color() {
        return color;
    }
 
    /** DEFAULT hats are always unlocked — no entry in the unlock set needed. */
    public boolean requiresUnlock() {
        return this != DEFAULT;
    }
}