package net.ent.entstupidstuff.api.car;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
 
import net.ent.entstupidstuff.sound.SoundFactory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.sounds.SoundManager;
import net.minecraft.world.entity.Entity;

import net.ent.entstupidstuff.sound.SoundFactory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.sounds.SoundManager;
import net.minecraft.world.entity.Entity;
 
/**
 * CarSoundManager — five layered sound instances per car.
 *
 * Speed crossfade zones (blocks/tick):
 *
 *   0      0.03    0.80  0.85   1.0
 *   |────────|──────────|──|────|
 *   Engine:  ████████████████████  (always with passenger, pitch=RPM)
 *   Accel:          ████████        (throttle held, speed < 0.85)
 *   Top:                  ████████  (speed > 0.80, pitch=0.92)
 *   Crossfade overlap:    ███        (0.80–0.85, both at partial volume)
 *   Decel:          (braking only)
 *   Tyre:           (drifting only)
 *
 * Pitch matching at crossover (speed=0.85):
 *   gear_one at pitch=1.034 → 2309 Hz centroid
 *   gear_top at pitch=0.92  → 2316 Hz centroid  ← ~0.3% difference, inaudible
 */
public class CarSoundManager {
 
    private static final Map<Integer, CarEngineSoundInstance>   engineSounds  = new HashMap<>();
    private static final Map<Integer, CarAccelSoundInstance>    accelSounds   = new HashMap<>();
    private static final Map<Integer, CarDeaccelSoundInstance>  deaccelSounds = new HashMap<>();
    private static final Map<Integer, CarTopSpeedSoundInstance> topSounds     = new HashMap<>();
    private static final Map<Integer, CarTireSoundInstance>     tireSounds    = new HashMap<>();
 
    public static void tick() {
        Minecraft mc = Minecraft.getInstance();
        if (!(mc.level instanceof ClientLevel level) || mc.player == null) return;
 
        SoundManager sm = mc.getSoundManager();
 
        for (Entity entity : level.entitiesForRendering()) {
            if (!(entity instanceof CarEntity car)) continue;
 
            int id = car.getId();
 
            // ── 1. Engine idle / RPM ──────────────────────────────────
            CarEngineSoundInstance engine = engineSounds.get(id);
            if (engine == null || engine.isStopped()) {
                if (car.getFirstPassenger() != null) {
                    engine = new CarEngineSoundInstance(
                        car, SoundFactory.ENTITY_VEHICLE_DODGEVIPERGTS_IDLE);
                    sm.play(engine);
                    engineSounds.put(id, engine);
                }
            }
 
            // ── 2. Acceleration roar — Gear_1 (fades out at 0.85) ────
            CarAccelSoundInstance accel = accelSounds.get(id);
            if (accel == null || accel.isStopped()) {
                if (car.getFirstPassenger() != null) {
                    accel = new CarAccelSoundInstance(
                        car, SoundFactory.ENTITY_VEHICLE_DODGEVIPERGTS_GEAR_1);
                    sm.play(accel);
                    accelSounds.put(id, accel);
                }
            }
 
            // ── 3. Deceleration / brake ───────────────────────────────
            CarDeaccelSoundInstance deaccel = deaccelSounds.get(id);
            if (deaccel == null || deaccel.isStopped()) {
                if (car.getFirstPassenger() != null) {
                    deaccel = new CarDeaccelSoundInstance(
                        car, SoundFactory.ENTITY_VEHICLE_DODGEVIPERGTS_BREAK);
                    sm.play(deaccel);
                    deaccelSounds.put(id, deaccel);
                }
            }
 
            // ── 4. Top speed — Gear_Top (fades in at 0.80, pitch=0.92) ──
            CarTopSpeedSoundInstance top = topSounds.get(id);
            if (top == null || top.isStopped()) {
                if (car.getFirstPassenger() != null) {
                    top = new CarTopSpeedSoundInstance(
                        car, SoundFactory.ENTITY_VEHICLE_DODGEVIPERGTS_GEAR_TOP);
                    sm.play(top);
                    topSounds.put(id, top);
                }
            }
 
            // ── 5. Tyre screech ───────────────────────────────────────
            CarTireSoundInstance tyre = tireSounds.get(id);
            if (tyre == null || tyre.isStopped()) {
                if (car.isDrifting() || Math.abs(car.getForwardSpeed()) > 0.15f) {
                    tyre = new CarTireSoundInstance(
                        car, SoundFactory.ENTITY_VEHICLE_TIRES_SQUAL_LOOP);
                    sm.play(tyre);
                    tireSounds.put(id, tyre);
                }
            }
        }
 
        pruneMap(engineSounds,  level);
        pruneMap(accelSounds,   level);
        pruneMap(deaccelSounds, level);
        pruneMap(topSounds,     level);
        pruneMap(tireSounds,    level);
    }
 
    private static <T extends AbstractCarSoundInstance> void pruneMap(
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
        accelSounds.values().forEach(sm::stop);
        deaccelSounds.values().forEach(sm::stop);
        topSounds.values().forEach(sm::stop);
        tireSounds.values().forEach(sm::stop);
        engineSounds.clear();
        accelSounds.clear();
        deaccelSounds.clear();
        topSounds.clear();
        tireSounds.clear();
    }
}