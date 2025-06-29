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

    private static SoundEvent registerSoundEvent(String name) {
        Identifier id = Identifier.of(EntStupidStuff.MOD_ID, name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }

    public static void registerSounds() {
        EntStupidStuff.LOGGER.info("Registering Sounds for " + EntStupidStuff.MOD_ID);
    }

    
}
