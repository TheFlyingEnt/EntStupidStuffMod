package net.ent.entstupidstuff.sound;

import net.ent.entstupidstuff.EntStupidStuff;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class SoundFactory {

    public static final SoundEvent ENTITY_METAL_SKELETON_AMBIENT = registerSoundEvent("entity.metal_skeleton.ambient");
	public static final SoundEvent ENTITY_METAL_SKELETON_CONVERTED_TO_STRAY = registerSoundEvent("entity.metal_skeleton.converted_to_stray");
	public static final SoundEvent ENTITY_METAL_SKELETON_DEATH = registerSoundEvent("entity.metal_skeleton.death");
	public static final SoundEvent ENTITY_METAL_SKELETON_HURT = registerSoundEvent("entity.metal_skeleton.hurt");
	public static final SoundEvent ENTITY_METAL_SKELETON_SHOOT = registerSoundEvent("entity.metal_skeleton.shoot");
	public static final SoundEvent ENTITY_METAL_SKELETON_STEP = registerSoundEvent("entity.metal_skeleton.step");

    public static final SoundEvent COMBAT_HAMMER_GROUND = registerSoundEvent("combat.hammer.ground");
    public static final SoundEvent COMBAT_CANNON_FIRE = registerSoundEvent("combat.cannon.fire");

    public static final SoundEvent ENTITY_ALLIGATOR_GAR_AMBIENT = registerSoundEvent("entity.alligator_gar.ambient");
    public static final SoundEvent ENTITY_ALLIGATOR_GAR_DEATH = registerSoundEvent("entity.alligator_gar.death");
    public static final SoundEvent ENTITY_ALLIGATOR_GAR_HURT = registerSoundEvent("entity.alligator_gar.hurt");
    public static final SoundEvent ENTITY_ALLIGATOR_GAR_FLOP = registerSoundEvent("entity.alligator_gar.flop");

    public static final SoundEvent ENTITY_MACKEREL_AMBIENT = registerSoundEvent("entity.mackerel.ambient");
    public static final SoundEvent ENTITY_MACKEREL_DEATH = registerSoundEvent("entity.mackerel.death");
    public static final SoundEvent ENTITY_MACKEREL_HURT = registerSoundEvent("entity.mackerel.hurt");
    public static final SoundEvent ENTITY_MACKEREL_FLOP = registerSoundEvent("entity.mackerel.flop");

    public static final SoundEvent ENTITY_ZEBRA_FISH_AMBIENT = registerSoundEvent("entity.zebra_fish.ambient");
    public static final SoundEvent ENTITY_ZEBRA_FISH_DEATH = registerSoundEvent("entity.zebra_fish.death");
    public static final SoundEvent ENTITY_ZEBRA_FISH_HURT = registerSoundEvent("entity.zebra_fish.hurt");
    public static final SoundEvent ENTITY_ZEBRA_FISH_FLOP = registerSoundEvent("entity.zebra_fish.flop");

    public static final SoundEvent ENTITY_BASS_AMBIENT = registerSoundEvent("entity.bass.ambient");
    public static final SoundEvent ENTITY_BASS_DEATH = registerSoundEvent("entity.bass.death");
    public static final SoundEvent ENTITY_BASS_HURT = registerSoundEvent("entity.bass.hurt");
    public static final SoundEvent ENTITY_BASS_FLOP = registerSoundEvent("entity.bass.flop");

    public static final SoundEvent ENTITY_PERCH_AMBIENT = registerSoundEvent("entity.perch.ambient");
    public static final SoundEvent ENTITY_PERCH_DEATH = registerSoundEvent("entity.perch.death");
    public static final SoundEvent ENTITY_PERCH_HURT = registerSoundEvent("entity.perch.hurt");
    public static final SoundEvent ENTITY_PERCH_FLOP = registerSoundEvent("entity.perch.flop");

    public static final SoundEvent ENTITY_SNAPPER_AMBIENT = registerSoundEvent("entity.snapper.ambient");
    public static final SoundEvent ENTITY_SNAPPER_DEATH = registerSoundEvent("entity.snapper.death");
    public static final SoundEvent ENTITY_SNAPPER_HURT = registerSoundEvent("entity.snapper.hurt");
    public static final SoundEvent ENTITY_SNAPPER_FLOP = registerSoundEvent("entity.snapper.flop");

    public static final SoundEvent ENTITY_KOI_AMBIENT = registerSoundEvent("entity.koi.ambient");
    public static final SoundEvent ENTITY_KOI_DEATH = registerSoundEvent("entity.koi.death");
    public static final SoundEvent ENTITY_KOI_HURT = registerSoundEvent("entity.koi.hurt");
    public static final SoundEvent ENTITY_KOI_FLOP = registerSoundEvent("entity.koi.flop");

    public static final SoundEvent ENTITY_MAHIMAHI_AMBIENT = registerSoundEvent("entity.mahimahi.ambient");
    public static final SoundEvent ENTITY_MAHIMAHI_DEATH = registerSoundEvent("entity.mahimahi.death");
    public static final SoundEvent ENTITY_MAHIMAHI_HURT = registerSoundEvent("entity.mahimahi.hurt");
    public static final SoundEvent ENTITY_MAHIMAHI_FLOP = registerSoundEvent("entity.mahimahi.flop");

    public static final SoundEvent ENTITY_FUR_TROUT_AMBIENT = registerSoundEvent("entity.fur_trout.ambient");
    public static final SoundEvent ENTITY_FUR_TROUT_DEATH = registerSoundEvent("entity.fur_trout.death");
    public static final SoundEvent ENTITY_FUR_TROUT_HURT = registerSoundEvent("entity.fur_trout.hurt");
    public static final SoundEvent ENTITY_FUR_TROUT_FLOP = registerSoundEvent("entity.fur_trout.flop");

    private static SoundEvent registerSoundEvent(String name) {
        Identifier id = Identifier.of(EntStupidStuff.MOD_ID, name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }

    public static void registerSounds() {
        EntStupidStuff.LOGGER.info("Registering Sounds for " + EntStupidStuff.MOD_ID);
    }

    
}
