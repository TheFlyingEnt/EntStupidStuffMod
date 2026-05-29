package net.ent.entstupidstuff.api.car;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import net.ent.entstupidstuff.api.car.soundengine.*;
import net.ent.entstupidstuff.sound.SoundFactory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.resources.sounds.AbstractTickableSoundInstance;
import net.minecraft.client.sounds.SoundManager;
import net.minecraft.core.component.DataComponents;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.JukeboxPlayable;

/**
 * CarSoundManager — manages all car sound layers.
 *
 * Layers per car:
 *   1. Engine idle (RPM-pitched loop)
 *   2. Echo (tunnel reverb)
 *   3. Accel (RPM-gated driving sound)
 *   4. Reverse
 *   5. Decel (throttle lift-off)
 *   6. Top speed (high-RPM cruise)
 *   7. Tire screech (drift/burnout)
 *   8. Turbo spool (turbo cars only)
 *   9. Rain ambient (weather-dependent)
 *  10. Radio (music disc)
 *
 * Plus one-shot sounds:
 *   - Gear shift up/down (triggered on gear change detection)
 */
public class CarSoundManager {

    // ── Looping sound instance maps ──────────────────────────────
    private static final Map<Integer, CarEngineSoundInstance>   engineSounds  = new HashMap<>();
    private static final Map<Integer, CarEchoSoundInstance>     echoSounds    = new HashMap<>();
    private static final Map<Integer, CarAccelSoundInstance>    accelSounds   = new HashMap<>();
    private static final Map<Integer, CarReverseSoundInstance>  reverseSounds = new HashMap<>();
    private static final Map<Integer, CarDeaccelSoundInstance>  deaccelSounds = new HashMap<>();
    private static final Map<Integer, CarTopSpeedSoundInstance> topSounds     = new HashMap<>();
    private static final Map<Integer, CarTireSoundInstance>     tireSounds    = new HashMap<>();
    private static final Map<Integer, CarTurboSoundInstance>    turboSounds   = new HashMap<>();
    private static final Map<Integer, CarRainSoundInstance>     rainSounds    = new HashMap<>();
    private static final Map<Integer, CarRadioSoundInstance>    radioSounds   = new HashMap<>();
    private static final Map<Integer, Boolean>                  engineStarted = new HashMap<>();

    // ── Gear shift tracking (for one-shot shift sounds) ──────────
    private static final Map<Integer, Integer> previousGear = new HashMap<>();

    public static void tick() {
        Minecraft mc = Minecraft.getInstance();
        if (!(mc.level instanceof ClientLevel level) || mc.player == null) return;

        SoundManager sm = mc.getSoundManager();

        for (Entity entity : level.entitiesForRendering()) {
            if (!(entity instanceof BaseCarEntity car)) continue;

            int id = car.getId();
            CarSoundProfile p = car.getSoundProfile();

            // ── 0. Engine start one-shot ──────────────────────────────
            // Plays ONCE when the car goes from empty to occupied.
            // Uses DATA_ENGINE_JUST_STARTED which is true for exactly
            // one tick when the first player enters.
            if (car.isEngineJustStarted()) {
                Boolean alreadyStarted = engineStarted.get(id);
                if (alreadyStarted == null || !alreadyStarted) {
                    // Play engine start sound at car's position
                    float vol = 1.0f * SoundDistanceHelper.falloff(car, p);
                    level.playLocalSound(
                        car.getX(), car.getY(), car.getZ(),
                        car.engineStartSound(),
                        SoundSource.NEUTRAL,
                        vol, 1.0f, false
                    );
                    engineStarted.put(id, true);
                }
            }
 
            // Reset when car becomes empty (so start plays again next time)
            if (car.getFirstPassenger() == null) {
                engineStarted.put(id, false);
            }


            // ── 1. Engine idle / RPM ──────────────────────────────────
            ensureLayer(engineSounds, id, car, sm,
                car.getFirstPassenger() != null,
                () -> new CarEngineSoundInstance(car, p.idle()));

            // ── 2. Echo — tunnel reverb ──────────────────────────────
            ensureLayer(echoSounds, id, car, sm,
                car.isTunneled() || car.getFirstPassenger() != null,
                () -> new CarEchoSoundInstance(car, p.idle()));

            // ── 3. Acceleration ───────────────────────────────────────
            ensureLayer(accelSounds, id, car, sm,
                car.getFirstPassenger() != null,
                () -> new CarAccelSoundInstance(car, p.accel()));

            // ── 4. Reverse ────────────────────────────────────────────
            ensureLayer(reverseSounds, id, car, sm,
                car.getFirstPassenger() != null,
                () -> new CarReverseSoundInstance(car, p.idle()));

            // ── 5. Deceleration / brake ───────────────────────────────
            ensureLayer(deaccelSounds, id, car, sm,
                car.getFirstPassenger() != null,
                () -> new CarDeaccelSoundInstance(car, p.decel()));

            // ── 6. Top speed ──────────────────────────────────────────
            ensureLayer(topSounds, id, car, sm,
                car.getFirstPassenger() != null,
                () -> new CarTopSpeedSoundInstance(car, p.topSpeed()));

            // ── 7. Tyre screech ───────────────────────────────────────
            ensureLayer(tireSounds, id, car, sm,
                car.isDrifting() || car.isBurningOut(),
                () -> new CarTireSoundInstance(car, p.tireSqueal()));

            // ── 8. Turbo spool (turbo cars only) ──────────────────────
            if (car.isTurbo()) {
                ensureLayer(turboSounds, id, car, sm,
                    car.getFirstPassenger() != null,
                    () -> new CarTurboSoundInstance(car, SoundFactory.ENTITY_VEHICLE_TURBO_SPOOL));
            }

            // ── 9. Rain ambient ───────────────────────────────────────
            ensureLayer(rainSounds, id, car, sm,
                car.level().isRaining() && car.getFirstPassenger() != null,
                () -> new CarRainSoundInstance(car, SoundFactory.ENTITY_VEHICLE_CAR_RAIN));

            // ── 10. Gear shift one-shots ──────────────────────────────
            if (car.getFirstPassenger() != null) {
                int currentGear = car.getCurrentGear();
                Integer prevGear = previousGear.get(id);

                if (prevGear != null && prevGear != currentGear && currentGear > 0) {
                    float speed = Math.abs(car.getForwardSpeed());
                    if (speed > 0.03f) { // don't play shift sound when parked
                        SoundEvent shiftSound = currentGear > prevGear
                            ? SoundFactory.ENTITY_VEHICLE_SHIFT_UP
                            : SoundFactory.ENTITY_VEHICLE_SHIFT_DOWN;

                        // Play one-shot at car's position
                        float vol = 0.6f * SoundDistanceHelper.falloff(car, p);
                        float pitch = 0.9f + car.getRPM() * 0.2f; // slight pitch variation with RPM
                        level.playLocalSound(
                            car.getX(), car.getY(), car.getZ(),
                            shiftSound, SoundSource.NEUTRAL,
                            vol, pitch, false
                        );
                    }
                }
                previousGear.put(id, currentGear);
            }

            // ── 11. Radio — music disc ────────────────────────────────
            CarRadioSoundInstance radio = radioSounds.get(id);
            if (car.hasRadioDisc() && car.getFirstPassenger() != null) {
                if (radio == null || radio.isStopped()) {
                    ItemStack discStack = car.getSyncedRadioDisc();
                    JukeboxPlayable playable = discStack.get(DataComponents.JUKEBOX_PLAYABLE);
                    if (playable != null) {
                        var optionalHolder = playable.song().unwrap(level.registryAccess());
                        if (optionalHolder.isPresent()) {
                            var soundEvent = optionalHolder.get().value().soundEvent().value();
                            radio = new CarRadioSoundInstance(car, soundEvent);
                            sm.play(radio);
                            radioSounds.put(id, radio);
                        }
                    }
                }
            } else if (radio != null && !radio.isStopped()) {
                sm.stop(radio);
                radioSounds.remove(id);
            }
        }

        // ── Prune dead/removed instances ─────────────────────────────
        pruneMap(engineSounds,  level);
        pruneMap(echoSounds,    level);
        pruneMap(accelSounds,   level);
        pruneMap(reverseSounds, level);
        pruneMap(deaccelSounds, level);
        pruneMap(topSounds,     level);
        pruneMap(tireSounds,    level);
        pruneMap(turboSounds,   level);
        pruneMap(rainSounds,    level);
        pruneMap(radioSounds,   level);

        // Prune gear tracking for removed entities
        previousGear.entrySet().removeIf(e -> level.getEntity(e.getKey()) == null);
    }

    /**
     * Ensures a looping sound instance exists for a car.
     * Creates it if missing/stopped AND the condition is true.
     */
    @SuppressWarnings("unchecked")
    private static <T extends AbstractTickableSoundInstance> void ensureLayer(
            Map<Integer, T> map, int id, BaseCarEntity car, SoundManager sm,
            boolean createCondition, java.util.function.Supplier<T> factory) {
        T instance = map.get(id);
        if (instance == null || instance.isStopped()) {
            if (createCondition) {
                instance = factory.get();
                sm.play(instance);
                map.put(id, instance);
            }
        }
    }

    private static <T extends AbstractTickableSoundInstance> void pruneMap(
            Map<Integer, T> map, ClientLevel level) {
        Iterator<Map.Entry<Integer, T>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            var entry = it.next();
            if (entry.getValue().isStopped() || level.getEntity(entry.getKey()) == null)
                it.remove();
        }
    }

    public static void stopAll() {
        Minecraft mc = Minecraft.getInstance();
        SoundManager sm = mc.getSoundManager();

        engineSounds.values().forEach(sm::stop);
        echoSounds.values().forEach(sm::stop);
        accelSounds.values().forEach(sm::stop);
        reverseSounds.values().forEach(sm::stop);
        deaccelSounds.values().forEach(sm::stop);
        topSounds.values().forEach(sm::stop);
        tireSounds.values().forEach(sm::stop);
        turboSounds.values().forEach(sm::stop);
        rainSounds.values().forEach(sm::stop);
        radioSounds.values().forEach(sm::stop);

        engineSounds.clear();
        echoSounds.clear();
        accelSounds.clear();
        reverseSounds.clear();
        deaccelSounds.clear();
        topSounds.clear();
        tireSounds.clear();
        turboSounds.clear();
        rainSounds.clear();
        radioSounds.clear();
        previousGear.clear();
        engineStarted.clear();
    }
}