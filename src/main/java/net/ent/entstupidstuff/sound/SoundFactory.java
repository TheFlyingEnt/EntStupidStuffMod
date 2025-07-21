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

    public static final SoundEvent ENTITY_TUNA_AMBIENT = registerSoundEvent("entity.tuna.ambient");
    public static final SoundEvent ENTITY_TUNA_DEATH = registerSoundEvent("entity.tuna.death");
    public static final SoundEvent ENTITY_TUNA_HURT = registerSoundEvent("entity.tuna.hurt");
    public static final SoundEvent ENTITY_TUNA_FLOP = registerSoundEvent("entity.tuna.flop");

    public static final SoundEvent ENTITY_ZEBRA_FISH_AMBIENT = registerSoundEvent("entity.zebra_fish.ambient");
    public static final SoundEvent ENTITY_ZEBRA_FISH_DEATH = registerSoundEvent("entity.zebra_fish.death");
    public static final SoundEvent ENTITY_ZEBRA_FISH_HURT = registerSoundEvent("entity.zebra_fish.hurt");
    public static final SoundEvent ENTITY_ZEBRA_FISH_FLOP = registerSoundEvent("entity.zebra_fish.flop");

    public static final SoundEvent ENTITY_SWORD_FISH_AMBIENT = registerSoundEvent("entity.sword_fish.ambient");
    public static final SoundEvent ENTITY_SWORD_FISH_DEATH = registerSoundEvent("entity.sword_fish.death");
    public static final SoundEvent ENTITY_SWORD_FISH_HURT = registerSoundEvent("entity.sword_fish.hurt");
    public static final SoundEvent ENTITY_SWORD_FISH_FLOP = registerSoundEvent("entity.sword_fish.flop");

    private static SoundEvent registerSoundEvent(String name) {
        Identifier id = Identifier.of(EntStupidStuff.MOD_ID, name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }

    public static void registerSounds() {
        EntStupidStuff.LOGGER.info("Registering Sounds for " + EntStupidStuff.MOD_ID);
    }

    
}
