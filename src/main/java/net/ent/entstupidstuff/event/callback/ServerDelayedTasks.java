package net.ent.entstupidstuff.event.callback;

import java.util.ArrayDeque;
import java.util.Queue;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;

public final class ServerDelayedTasks {
    private static final Queue<Runnable> NEXT_TICK = new ArrayDeque<>();

    public static void scheduleNextTick(Runnable task) {
        NEXT_TICK.add(task);
    }

    public static void init() {
        ServerTickEvents.END_SERVER_TICK.register(server -> {
            while (!NEXT_TICK.isEmpty()) {
                NEXT_TICK.poll().run();
            }
        });
    }
}
