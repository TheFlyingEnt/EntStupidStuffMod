package net.ent.entstupidstuff.api.car.soundengine;

import net.minecraft.client.Minecraft;
import net.ent.entstupidstuff.api.car.BaseCarEntity;

/**
 * Shared distance falloff for all car sound instances.
 *
 * Minecraft's default LINEAR attenuation fades to silence at ~16 blocks.
 * Cars need to be heard much further — up to 64 blocks for an F1 car.
 *
 * We set attenuation = NONE on all car sounds and handle falloff manually:
 *   0–8 blocks:   full volume (1.0)
 *   8–hearingDist: smooth linear falloff to 0
 *   beyond:        silent (0.0)
 */
public final class SoundDistanceHelper {
 
    public static float falloff(BaseCarEntity car, CarSoundProfile profile) {
        var mc = Minecraft.getInstance();
        if (mc.player == null) return 1f;
        double dist = mc.player.distanceTo(car);
        float maxDist = profile.hearingDistance();
        if (dist >= maxDist) return 0f;
        if (dist <= 8.0) return 1f;
        return 1f - (float)((dist - 8.0) / (maxDist - 8.0));
    }
 
    private SoundDistanceHelper() {}
}

