package net.ent.entstupidstuff.api.emote;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.world.entity.AnimationState;

public class EmoteClientState {
 
    public record Entry(String emoteName, AnimationState animationState) {}
 
    private static final Map<Integer, Entry> STATES = new HashMap<>();
 
    /**
     * Starts (or restarts) an emote for the given entity.
     *
     * @param entityId  the entity's runtime ID ({@code entity.getId()})
     * @param emoteName the emote name
     * @param tickCount the player's current {@code tickCount}, used as the
     *                  start reference for {@link AnimationState#getTimeInMillis}
     */
    public static void start(int entityId, String emoteName, int tickCount) {
        AnimationState state = new AnimationState();
        state.start(tickCount);
        STATES.put(entityId, new Entry(emoteName, state));
    }
 
    /** Removes any active emote for this entity. */
    public static void stop(int entityId) {
        STATES.remove(entityId);
    }
 
    /** Returns the active emote entry for this entity, or {@code null}. */
    public static Entry get(int entityId) {
        return STATES.get(entityId);
    }
 
    /** Called on world unload to avoid stale entries across server changes. */
    public static void clear() {
        STATES.clear();
    }
}
 
