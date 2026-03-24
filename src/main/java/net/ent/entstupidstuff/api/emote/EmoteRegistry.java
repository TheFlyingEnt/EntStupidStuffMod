package net.ent.entstupidstuff.api.emote;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import net.minecraft.client.animation.AnimationDefinition;

public class EmoteRegistry {
 
    public record EmoteEntry(AnimationDefinition definition, boolean looping, float durationSeconds) {}
 
    private static final Map<String, EmoteEntry> EMOTES = new LinkedHashMap<>();
 
    // ── Registered emotes ────────────────────────────────────────────────────
    public static final EmoteEntry HELICOPTER  = register("helicopter", EmoteDefinitions.HELICOPTER, true,  0.25F);
    public static final EmoteEntry SURRENDER   = register("surrender",  EmoteDefinitions.SURRENDER,  false, 2.5F);
    public static final EmoteEntry DINNERBONE  = register("dinnerbone", EmoteDefinitions.DINNERBONE, true,  2.0F);
    public static final EmoteEntry CUTTHROAT   = register("cutthroat",  EmoteDefinitions.CUTTHROAT,  false, 2.15F);
    public static final EmoteEntry TANK        = register("tank",       EmoteDefinitions.TANK,       false, 2.0F);  
    public static final EmoteEntry TEST        = register("test",       EmoteDefinitions.TEST,       false, 1.0F);
    public static final EmoteEntry RUNNING_MAN = register("running_man",       EmoteDefinitions.RUNNING_MAN,       false, 3.0F);
    public static final EmoteEntry BLOWN_UP = register("blown_up",       EmoteDefinitions.BLOWN_UP,       false, 4.0F);

    
    public static final EmoteEntry SANIC_FAST = register("sanic_fast",      EmoteDefinitions.SANIC_FAST,       true, 0.45F);
    public static final EmoteEntry CLOUD_WATCHER = register("cloud_watcher",   EmoteDefinitions.CLOUD_WATCHER,       false, 3.3F);
    public static final EmoteEntry GAUNTLET_CHECK = register("gauntlet_check",  EmoteDefinitions.GAUNTLET_CHECK,       false, 1.2F);
    public static final EmoteEntry LEFT_SALUTE = register("left_salute",     EmoteDefinitions.LEFT_SALUTE,       false, 2.0F);
    public static final EmoteEntry G_STYLE = register("g_style",         EmoteDefinitions.G_STYLE,       false, 1.65F);
    public static final EmoteEntry FLOSH = register("flosh",           EmoteDefinitions.BLOWN_UP,       true, 1.3F);
    // ─────────────────────────────────────────────────────────────────────────
 
    private static EmoteEntry register(String name, AnimationDefinition def, boolean looping, float duration) {
        EmoteEntry entry = new EmoteEntry(def, looping, duration);
        EMOTES.put(name, entry);
        return entry;
    }
 
    /** Returns the entry for this emote name, or {@code null} if not registered. */
    public static EmoteEntry get(String name) {
        return EMOTES.get(name);
    }
 
    public static boolean isValid(String name) {
        return EMOTES.containsKey(name);
    }
 
    public static Collection<String> getNames() {
        return EMOTES.keySet();
    }
 
    public static void init() {}
}