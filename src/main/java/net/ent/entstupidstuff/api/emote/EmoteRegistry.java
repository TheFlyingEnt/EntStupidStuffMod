package net.ent.entstupidstuff.api.emote;

import java.util.LinkedHashMap;
import java.util.Map;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.animation.AnimationDefinition;

@Environment(EnvType.CLIENT)
public class EmoteRegistry {

    public record EmoteEntry(AnimationDefinition definition, boolean looping, float durationSeconds) {}

    private static final Map<String, EmoteEntry> EMOTES = new LinkedHashMap<>();

    public static void init() {
        register(EmoteNames.HELICOPTER,     EmoteDefinitions.HELICOPTER,     true,  0.25F);
        register(EmoteNames.SURRENDER,      EmoteDefinitions.SURRENDER,      false, 2.5F);
        register(EmoteNames.DINNERBONE,     EmoteDefinitions.DINNERBONE,     true,  2.0F);
        register(EmoteNames.CUTTHROAT,      EmoteDefinitions.CUTTHROAT,      false, 2.15F);
        register(EmoteNames.TANK,           EmoteDefinitions.TANK,           false, 2.0F);
        register(EmoteNames.TEST,           EmoteDefinitions.TEST,           false, 1.0F);
        register(EmoteNames.RUNNING_MAN,    EmoteDefinitions.RUNNING_MAN,    false, 3.0F);
        register(EmoteNames.BLOWN_UP,       EmoteDefinitions.BLOWN_UP,       false, 4.0F);
        register(EmoteNames.SANIC_FAST,     EmoteDefinitions.SANIC_FAST,     true,  0.45F);
        register(EmoteNames.CLOUD_WATCHER,  EmoteDefinitions.CLOUD_WATCHER,  false, 3.3F);
        register(EmoteNames.GAUNTLET_CHECK, EmoteDefinitions.GAUNTLET_CHECK, false, 1.2F);
        register(EmoteNames.LEFT_SALUTE,    EmoteDefinitions.LEFT_SALUTE,    false, 2.0F);
        register(EmoteNames.G_STYLE,        EmoteDefinitions.G_STYLE,        true,  1.65F);
        register(EmoteNames.FLOSH,          EmoteDefinitions.FLOSH,          true,  1.3F); // fixed
    }

    private static void register(String name, AnimationDefinition def, boolean looping, float duration) {
        EMOTES.put(name, new EmoteEntry(def, looping, duration));
    }

    public static EmoteEntry get(String name) { return EMOTES.get(name); }
}