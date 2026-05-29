package net.ent.entstupidstuff.api.emote;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

// No client imports — safe on both sides
public class EmoteNames {

    private static final Set<String> NAMES = new LinkedHashSet<>();

    public static final String HELICOPTER    = reg("helicopter");
    public static final String SURRENDER     = reg("surrender");
    public static final String DINNERBONE    = reg("dinnerbone");
    public static final String CUTTHROAT     = reg("cutthroat");
    public static final String TANK          = reg("tank");
    public static final String TEST          = reg("test");
    public static final String RUNNING_MAN   = reg("running_man");
    public static final String BLOWN_UP      = reg("blown_up");
    public static final String SANIC_FAST    = reg("sanic_fast");
    public static final String CLOUD_WATCHER = reg("cloud_watcher");
    public static final String GAUNTLET_CHECK= reg("gauntlet_check");
    public static final String LEFT_SALUTE   = reg("left_salute");
    public static final String G_STYLE       = reg("g_style");
    public static final String FLOSH         = reg("flosh");

    private static String reg(String name) {
        NAMES.add(name);
        return name;
    }

    public static boolean isValid(String name)      { return NAMES.contains(name); }
    public static Collection<String> getNames()     { return NAMES; }
}
