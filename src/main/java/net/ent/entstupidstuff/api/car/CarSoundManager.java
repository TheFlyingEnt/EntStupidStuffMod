package net.ent.entstupidstuff.api.car;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import net.ent.entstupidstuff.api.car.soundengine.AbstractCarSoundInstance;
import net.ent.entstupidstuff.api.car.soundengine.CarAccelSoundInstance;
import net.ent.entstupidstuff.api.car.soundengine.CarDeaccelSoundInstance;
import net.ent.entstupidstuff.api.car.soundengine.CarEchoSoundInstance;
import net.ent.entstupidstuff.api.car.soundengine.CarEngineSoundInstance;
import net.ent.entstupidstuff.api.car.soundengine.CarReverseSoundInstance;
import net.ent.entstupidstuff.api.car.soundengine.CarSoundProfile;
import net.ent.entstupidstuff.api.car.soundengine.CarTireSoundInstance;
import net.ent.entstupidstuff.api.car.soundengine.CarTopSpeedSoundInstance;
import net.ent.entstupidstuff.api.car.soundengine.CarRadioSoundInstance;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.resources.sounds.AbstractTickableSoundInstance;
import net.minecraft.client.sounds.SoundManager;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.JukeboxPlayable;

/**
 * CarSoundManager — seven layered sound instances per car.
 *
 * Now reads sound events from car.getSoundProfile() instead of
 * hardcoded SoundFactory constants. Each car type provides its
 * own sounds via createSoundProfile() override.
 *
 * All sound instances use Attenuation.NONE and handle distance
 * falloff manually via SoundDistanceHelper, allowing cars to be
 * heard from much further away than the default 16-block range.
 */
public class CarSoundManager {

    private static final Map<Integer, CarEngineSoundInstance>   engineSounds  = new HashMap<>();
    private static final Map<Integer, CarEchoSoundInstance>      echoSounds    = new HashMap<>();
    private static final Map<Integer, CarAccelSoundInstance>    accelSounds   = new HashMap<>();
    private static final Map<Integer, CarReverseSoundInstance>  reverseSounds = new HashMap<>();
    private static final Map<Integer, CarDeaccelSoundInstance>  deaccelSounds = new HashMap<>();
    private static final Map<Integer, CarTopSpeedSoundInstance> topSounds     = new HashMap<>();
    private static final Map<Integer, CarTireSoundInstance>     tireSounds    = new HashMap<>();
    private static final Map<Integer, CarRadioSoundInstance>   radioSounds   = new HashMap<>();

    public static void tick() {
        Minecraft mc = Minecraft.getInstance();
        if (!(mc.level instanceof ClientLevel level) || mc.player == null) return;

        SoundManager sm = mc.getSoundManager();

        for (Entity entity : level.entitiesForRendering()) {
            if (!(entity instanceof BaseCarEntity car)) continue;

            int id = car.getId();
            CarSoundProfile p = car.getSoundProfile();

            // ── 1. Engine idle / RPM ──────────────────────────────────
            CarEngineSoundInstance engine = engineSounds.get(id);
            if (engine == null || engine.isStopped()) {
                if (car.getFirstPassenger() != null) {
                    engine = new CarEngineSoundInstance(car, p.idle());
                    sm.play(engine);
                    engineSounds.put(id, engine);
                }
            }

            // ── 1b. Echo — tunnel reverb ──────────────────────────────
            CarEchoSoundInstance echo = echoSounds.get(id);
            if (echo == null || echo.isStopped()) {
                if (car.isTunneled() || car.getFirstPassenger() != null) {
                    echo = new CarEchoSoundInstance(car, p.idle());
                    sm.play(echo);
                    echoSounds.put(id, echo);
                }
            }

            // ── 2. Acceleration roar ──────────────────────────────────
            CarAccelSoundInstance accel = accelSounds.get(id);
            if (accel == null || accel.isStopped()) {
                if (car.getFirstPassenger() != null) {
                    accel = new CarAccelSoundInstance(car, p.accel());
                    sm.play(accel);
                    accelSounds.put(id, accel);
                }
            }

            // ── 2b. Reverse ───────────────────────────────────────────
            CarReverseSoundInstance reverse = reverseSounds.get(id);
            if (reverse == null || reverse.isStopped()) {
                if (car.getFirstPassenger() != null) {
                    reverse = new CarReverseSoundInstance(car, p.idle());
                    sm.play(reverse);
                    reverseSounds.put(id, reverse);
                }
            }

            // ── 3. Deceleration / brake ───────────────────────────────
            CarDeaccelSoundInstance deaccel = deaccelSounds.get(id);
            if (deaccel == null || deaccel.isStopped()) {
                if (car.getFirstPassenger() != null) {
                    deaccel = new CarDeaccelSoundInstance(car, p.decel());
                    sm.play(deaccel);
                    deaccelSounds.put(id, deaccel);
                }
            }

            // ── 4. Top speed layer ────────────────────────────────────
            CarTopSpeedSoundInstance top = topSounds.get(id);
            if (top == null || top.isStopped()) {
                if (car.getFirstPassenger() != null) {
                    top = new CarTopSpeedSoundInstance(car, p.topSpeed());
                    sm.play(top);
                    topSounds.put(id, top);
                }
            }

            // ── 5. Tyre screech ───────────────────────────────────────
            CarTireSoundInstance tyre = tireSounds.get(id);
            if (tyre == null || tyre.isStopped()) {
                if (car.isDrifting() || car.isBurningOut()) {
                    tyre = new CarTireSoundInstance(car, p.tireSqueal());
                    sm.play(tyre);
                    tireSounds.put(id, tyre);
                }
            }

            // ── 6. Radio — music disc ─────────────────────────────────
            //.getSyncedRADIO()
            CarRadioSoundInstance radio = radioSounds.get(id);
            
            //System.out.println("car.getSyncedRadioDisc() " + car.getSyncedRadioDisc());
            //System.out.println("car.hasRadioDisc " + car.hasRadioDisc());
            //System.out.println("car.getFirstPassenger() " + car.getFirstPassenger());

            if (car.hasRadioDisc() && car.getFirstPassenger() != null) {
                
                if (radio == null || radio.isStopped()) {
                    //var discStack = car.getSyncedRadioDisc();
                    ItemStack discStack = car.getSyncedRadioDisc();
                    JukeboxPlayable playable = discStack.get(DataComponents.JUKEBOX_PLAYABLE);

                    var optionalHolder =
                        playable.song().unwrap(level.registryAccess());
                    
                    System.out.println("optionalHolder " + optionalHolder);
                    

                    if (optionalHolder.isPresent()) {

                        var songHolder = optionalHolder.get();

                        var jukeboxSong = songHolder.value();

                        var soundEvent = jukeboxSong.soundEvent();

                        //System.out.println("songHolder " + songHolder);
                        //System.out.println("jukeboxSong " + jukeboxSong);
                        //System.out.println("soundEvent " + soundEvent);

                        if (radio == null || radio.isStopped()) {

                            radio = new CarRadioSoundInstance(
                                    car,
                                    soundEvent.value()
                            );

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

        pruneMap(engineSounds,  level);
        pruneMap(echoSounds,    level);
        pruneMap(accelSounds,   level);
        pruneMap(reverseSounds, level);
        pruneMap(deaccelSounds, level);
        pruneMap(topSounds,     level);
        pruneMap(tireSounds,    level);
        pruneMap(radioSounds,   level);
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
        radioSounds.values().forEach(sm::stop);
        engineSounds.clear();
        echoSounds.clear();
        accelSounds.clear();
        reverseSounds.clear();
        deaccelSounds.clear();
        topSounds.clear();
        tireSounds.clear();
        radioSounds.clear();
    }
}