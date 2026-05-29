package net.ent.entstupidstuff.api.car.soundengine;

import net.ent.entstupidstuff.api.car.BaseCarEntity;
import net.minecraft.client.Minecraft;
 
/**
 * CabinSoundMix — applies interior vs exterior sound mixing.
 *
 * Same audio files, different pitch/volume multipliers based on
 * whether the listener is inside the car or standing outside.
 *
 * Interior (driver/passenger): sounds are muffled, bassy, louder.
 *   - You're sitting right next to the engine, enclosed by cabin.
 *   - Tire/wind sounds are muffled (outside the cabin).
 *   - Turbo whistle is amplified (resonates in engine bay).
 *
 * Exterior (bystander): sounds are sharper, thinner.
 *   - Normal distance falloff applies.
 *   - Tire/wind are at full volume (you're in the open air).
 *
 * Open cockpit (F1): no cabin = no muffling.
 *   - Driver hears full exterior volume — like real F1 drivers.
 *
 * Usage in any sound instance's tick():
 *   float[] mix = CabinSoundMix.apply(car, Layer.ENGINE, volume, pitch);
 *   volume = mix[0];
 *   pitch  = mix[1];
 */
public final class CabinSoundMix {
 
    /** Sound layer type — determines how much interior mixing is applied. */
    public enum Layer {
        /** Engine idle — heavy bass boost inside. */
        ENGINE(0.93f, 1.20f, 1.00f, 1.00f),
        /** Acceleration — moderate bass boost. */
        ACCEL(0.95f, 1.15f, 1.00f, 1.00f),
        /** Deceleration — moderate bass. */
        DECEL(0.94f, 1.10f, 1.00f, 1.00f),
        /** Top speed cruise — slight bass. */
        TOP_SPEED(0.97f, 1.10f, 1.00f, 1.00f),
        /** Reverse — same as engine. */
        REVERSE(0.93f, 1.15f, 1.00f, 1.00f),
        /** Echo/tunnel — unaffected (already a spatial effect). */
        ECHO(1.00f, 1.00f, 1.00f, 1.00f),
        /** Tire squeal — heavily muffled inside. */
        TIRES(1.00f, 0.55f, 1.00f, 1.00f),
        /** Turbo spool — louder inside (resonates). */
        TURBO(0.98f, 1.30f, 1.00f, 0.70f),
        /** Wind noise — heavily muffled inside cabin. */
        WIND(1.00f, 0.35f, 1.00f, 1.00f),
        /** Rain — moderately muffled (rain on roof vs open air). */
        RAIN(1.00f, 0.65f, 1.00f, 1.00f),
        /** Radio — full volume inside, distance-based outside. */
        RADIO(1.00f, 1.00f, 1.00f, 1.00f);
 
        /** Pitch multiplier when listener is INSIDE the car. */
        public final float interiorPitch;
        /** Volume multiplier when listener is INSIDE the car. */
        public final float interiorVolume;
        /** Pitch multiplier when listener is OUTSIDE the car. */
        public final float exteriorPitch;
        /** Volume multiplier when listener is OUTSIDE the car. */
        public final float exteriorVolume;
 
        Layer(float iPitch, float iVol, float ePitch, float eVol) {
            this.interiorPitch  = iPitch;
            this.interiorVolume = iVol;
            this.exteriorPitch  = ePitch;
            this.exteriorVolume = eVol;
        }
    }
 
    /**
     * Applies interior/exterior mixing to the given volume and pitch.
     *
     * @param car    the car entity
     * @param layer  which sound layer (determines mixing amounts)
     * @param volume the base volume before mixing
     * @param pitch  the base pitch before mixing
     * @return float[2]: [0] = mixed volume, [1] = mixed pitch
     */
    public static float[] apply(BaseCarEntity car, Layer layer, float volume, float pitch) {
        boolean inside = isListenerInCar(car);
 
        // Open cockpit (F1): no cabin muffling — full exterior mix always
        if (inside && car.isOpenCockpit()) {
            inside = false;
        }
 
        if (inside) {
            return new float[] {
                volume * layer.interiorVolume,
                pitch  * layer.interiorPitch
            };
        } else {
            return new float[] {
                volume * layer.exteriorVolume,
                pitch  * layer.exteriorPitch
            };
        }
    }
 
    /**
     * Checks if the local player should hear interior sounds.
     *
     * Interior mix ONLY when:
     *   - Player is riding this car (driver or passenger)
     *   - AND camera is in FIRST PERSON
     *
     * Third person (F5) while riding = exterior mix.
     * This matches real racing games — chase cam sounds different
     * from cockpit cam because you're "outside" the car.
     */
    private static boolean isListenerInCar(BaseCarEntity car) {
        var mc = Minecraft.getInstance();
        if (mc.player == null || mc.player.getVehicle() != car) return false;
 
        // Only interior mix in first person — third person = exterior
        return mc.options.getCameraType() == net.minecraft.client.CameraType.FIRST_PERSON;
    }
 
    private CabinSoundMix() {}
}

