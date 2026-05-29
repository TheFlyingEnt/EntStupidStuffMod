package net.ent.entstupidstuff.api.car.soundengine;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.AbstractTickableSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.ent.entstupidstuff.api.car.BaseCarEntity;

/**
 * CarRadioSoundInstance — plays a music disc sound that follows the car.
 *
 * Uses NONE attenuation and manual distance falloff so bystanders can
 * hear music as the car drives past. Volume scales down from full at
 * 8 blocks to silent at 32 blocks.
 *
 * Stops when:
 *   - Car is removed
 *   - No music disc in radio slot
 *   - Car has no passengers
 */
public class CarRadioSoundInstance extends AbstractTickableSoundInstance
        implements AbstractCarSoundInstance {

    private static final float RADIO_DISTANCE = 32f;
    private static final float VOLUME_MAX     = 0.8f;

    private final BaseCarEntity car;

    public CarRadioSoundInstance(BaseCarEntity car, SoundEvent music) {
        super(music, SoundSource.RECORDS, SoundInstance.createUnseededRandom());
        this.car         = car;
        this.looping     = false; // music discs play once
        this.delay       = 0;
        this.volume      = VOLUME_MAX;
        this.pitch       = 1.0f;
        this.attenuation = Attenuation.NONE;
        syncPosition();
    }

    @Override public boolean canPlaySound()   { return !car.isRemoved(); }
    @Override public boolean canStartSilent() { return true; }

    @Override
    public void tick() {
        if (car.isRemoved()) { stop(); return; }
        syncPosition();

        // Stop if no disc or no passengers
        if (!car.hasRadioDisc() || car.getFirstPassenger() == null) {
            stop();
            return;
        }

        // Distance falloff — music heard from 32 blocks
        var mc = Minecraft.getInstance();
        if (mc.player == null) { volume = 0; return; }
        double dist = mc.player.distanceTo(car);
        if (dist >= RADIO_DISTANCE) {
            volume = 0;
        } else if (dist <= 8.0) {
            volume = VOLUME_MAX;
        } else {
            volume = VOLUME_MAX * (1f - (float)((dist - 8.0) / (RADIO_DISTANCE - 8.0)));
        }

        float[] mix = CabinSoundMix.apply(car, CabinSoundMix.Layer.RADIO, volume, pitch);
        volume = mix[0]; pitch = mix[1];

    }

    private void syncPosition() { x = car.getX(); y = car.getY(); z = car.getZ(); }
    public BaseCarEntity getCar() { return car; }
}