package net.ent.entstupidstuff.sound;

import net.ent.entstupidstuff.EntStupidStuff;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

public class SoundFactory {

    //public static final SoundEvent MUSIC_DISC_PIRATE_TAVERN = registerSoundEvent("music_disc.pirate_tavern");
    public static final Holder.Reference<SoundEvent> MUSIC_DISC_PIRATE_TAVERN = registerReference("music_disc.pirate_tavern");
    public static final Holder.Reference<SoundEvent> MUSIC_DISC_FUNGALDELIC = registerReference("music_disc.fungaldelic");
    public static final Holder.Reference<SoundEvent> MUSIC_DISC_CANIBEHONEST = registerReference("music_disc.canibehonest");
    public static final Holder.Reference<SoundEvent> MUSIC_DISC_CANIBEHONEST_HIGH = registerReference("music_disc.canibehonest_high");
    public static final Holder.Reference<SoundEvent> MUSIC_DISC_CANIBEHONEST_LOW = registerReference("music_disc.canibehonest_low");

    public static final SoundEvent ENTITY_METAL_SKELETON_AMBIENT = registerSoundEvent("entity.metal_skeleton.ambient");
	public static final SoundEvent ENTITY_METAL_SKELETON_CONVERTED_TO_STRAY = registerSoundEvent("entity.metal_skeleton.converted_to_stray");
	public static final SoundEvent ENTITY_METAL_SKELETON_DEATH = registerSoundEvent("entity.metal_skeleton.death");
	public static final SoundEvent ENTITY_METAL_SKELETON_HURT = registerSoundEvent("entity.metal_skeleton.hurt");
	public static final SoundEvent ENTITY_METAL_SKELETON_SHOOT = registerSoundEvent("entity.metal_skeleton.shoot");
	public static final SoundEvent ENTITY_METAL_SKELETON_STEP = registerSoundEvent("entity.metal_skeleton.step");

    public static final SoundEvent COMBAT_HAMMER_GROUND = registerSoundEvent("combat.hammer.ground"); //TODO: change to item.hammer.smash_ground
    public static final SoundEvent COMBAT_CANNON_FIRE = registerSoundEvent("combat.cannon.fire"); //TODO: change to item.cannon.fire
    //public static final SoundEvent COMBAT_CANNON_HIT = registerSoundEvent("item.cannon.hit"); //TODO: change to item.cannon.hit
    //public static final Holder<SoundEvent> COMBAT_CANNON_LOADING_END = registerForHolder("item.cannon.loading_end"); //TODO: ADD SOUND
    //public static final Holder<SoundEvent> COMBAT_CANNON_LOADING_MIDDLE = registerForHolder("item.cannon.loading_middle"); //TODO: ADD SOUND
    //public static final Holder<SoundEvent> COMBAT_CANNON_LOADING_START = registerForHolder("item.cannon.loading_start"); //TODO: ADD SOUND

    //public static final SoundEvent COMBAT_DB_CROSSBOW_FIRE = registerSoundEvent("combat.db_crossbow.fire"); //TODO: ADD SOUND
    //public static final SoundEvent COMBAT_DB_CROSSBOW_HIT = registerSoundEvent("item.db_crossbow.hit"); //TODO: ADD SOUND
    //public static final Holder<SoundEvent> COMBAT_DB_CROSSBOW_LOADING_END = registerForHolder("item.db_crossbow.loading_end"); //TODO: ADD SOUND
    //public static final Holder<SoundEvent> COMBAT_DB_CROSSBOW_LOADING_MIDDLE = registerForHolder("item.db_crossbow.loading_middle"); //TODO: ADD SOUND
    //public static final Holder<SoundEvent> COMBAT_DB_CROSSBOW_LOADING_START = registerForHolder("item.db_crossbow.loading_start"); //TODO: ADD SOUND

    //public static final SoundEvent COMBAT_FL_CROSSBOW_FIRE = registerSoundEvent("combat.fl_crossbow.fire"); //TODO: ADD SOUND
    //public static final SoundEvent COMBAT_FL_CROSSBOW_HIT = registerSoundEvent("item.fl_crossbow.hit"); //TODO: ADD SOUND
    //public static final Holder<SoundEvent> COMBAT_FL_CROSSBOW_LOADING_END = registerForHolder("item.fl_crossbow.loading_end"); //TODO: ADD SOUND
    //public static final Holder<SoundEvent> COMBAT_FL_CROSSBOW_LOADING_MIDDLE = registerForHolder("item.fl_crossbow.loading_middle"); //TODO: ADD SOUND
    //public static final Holder<SoundEvent> COMBAT_FL_CROSSBOW_LOADING_START = registerForHolder("item.fl_crossbow.loading_start"); //TODO: ADD SOUND

    //public static final SoundEvent PIGLIN_WARRIOR_AMBIENT = registerSoundEvent("entity.piglin_warrior.ambient");
    //public static final SoundEvent PIGLIN_WARRIOR_ANGRY = registerSoundEvent("entity.piglin_warrior.angry");
    //public static final SoundEvent PIGLIN_WARRIOR_DEATH = registerSoundEvent("entity.piglin_warrior.death");
    //public static final SoundEvent PIGLIN_WARRIOR_HURT = registerSoundEvent("entity.piglin_warrior.hurt");
    //public static final SoundEvent PIGLIN_WARRIOR_STEP = registerSoundEvent("entity.piglin_warrior.step");
    //public static final SoundEvent PIGLIN_WARRIOR_CONVERTED_TO_ZOMBIFIED = registerSoundEvent("entity.piglin_warrior.converted_to_zombified");


    public static final Holder.Reference<SoundEvent> ENTITY_SPOREPER_EXPLODE = registerReference("entity.sporeper.explode"); //TODO: Add Sporeper Hurt and Death Sounds
    public static final Holder.Reference<SoundEvent> ENTITY_GENERIC_UNDERWATER_EXPLODE = registerReference("entity.generic.underwater_explode");

    /*//TODO: Add the Following Mob Sounds:
        ZOMBIE_LOBBER
        ZOMBIE_SCORCHED
        ZOMBIE_SLIMED
        ZOMBIE_FROSTBITTEN
        ZOMBIE_FUNGAL
        SPORE_BONE //name change to Sporekin or Veil?

        BUTTERFLY
        PHANTOM_SKELETON
    */

    public static final SoundEvent ENTITY_LOBBER_AMBIENT = registerSoundEvent("entity.lobber.ambient");
    public static final SoundEvent ENTITY_LOBBER_CONVERTED_TO_ZOMBIE = registerSoundEvent("entity.lobber.converted_to_zombie");
    public static final SoundEvent ENTITY_LOBBER_DEATH = registerSoundEvent("entity.lobber.death");
    public static final SoundEvent ENTITY_LOBBER_HURT = registerSoundEvent("entity.lobber.hurt");
    public static final SoundEvent ENTITY_LOBBER_STEP = registerSoundEvent("entity.lobber.step");

    public static final SoundEvent ENTITY_SCORCHED_AMBIENT = registerSoundEvent("entity.scorched.ambient");
    public static final SoundEvent ENTITY_SCORCHED_CONVERTED_TO_ZOMBIE = registerSoundEvent("entity.scorched.converted_to_zombie");
    public static final SoundEvent ENTITY_SCORCHED_DEATH = registerSoundEvent("entity.scorched.death");
    public static final SoundEvent ENTITY_SCORCHED_HURT = registerSoundEvent("entity.scorched.hurt");
    public static final SoundEvent ENTITY_SCORCHED_STEP = registerSoundEvent("entity.scorched.step");

    public static final SoundEvent ENTITY_SLIMED_AMBIENT = registerSoundEvent("entity.slimed.ambient");
    public static final SoundEvent ENTITY_SLIMED_CONVERTED_TO_ZOMBIE = registerSoundEvent("entity.slimed.converted_to_zombie");
    public static final SoundEvent ENTITY_SLIMED_DEATH = registerSoundEvent("entity.slimed.death");
    public static final SoundEvent ENTITY_SLIMED_HURT = registerSoundEvent("entity.slimed.hurt");
    public static final SoundEvent ENTITY_SLIMED_STEP = registerSoundEvent("entity.slimed.step");

    public static final SoundEvent ENTITY_FROSTBITTEN_AMBIENT = registerSoundEvent("entity.frostbitten.ambient");
    public static final SoundEvent ENTITY_FROSTBITTEN_CONVERTED_TO_ZOMBIE = registerSoundEvent("entity.frostbitten.converted_to_zombie");
    public static final SoundEvent ENTITY_FROSTBITTEN_DEATH = registerSoundEvent("entity.frostbitten.death");
    public static final SoundEvent ENTITY_FROSTBITTEN_HURT = registerSoundEvent("entity.frostbitten.hurt");
    public static final SoundEvent ENTITY_FROSTBITTEN_STEP = registerSoundEvent("entity.frostbitten.step");

    public static final SoundEvent ENTITY_SPORE_BONE_AMBIENT = registerSoundEvent("entity.sporebone.ambient");
	public static final SoundEvent ENTITY_SPORE_BONE_CONVERTED_TO_STRAY = registerSoundEvent("entity.sporebone.converted_to_stray");
	public static final SoundEvent ENTITY_SPORE_BONE_DEATH = registerSoundEvent("entity.sporebone.death");
	public static final SoundEvent ENTITY_SPORE_BONE_HURT = registerSoundEvent("entity.sporebone.hurt");
	public static final SoundEvent ENTITY_SPORE_BONE_SHOOT = registerSoundEvent("entity.sporebone.shoot");
	public static final SoundEvent ENTITY_SPORE_BONE_STEP = registerSoundEvent("entity.sporebone.step");

    public static final SoundEvent ENTITY_PHANTOM_SKELETON_AMBIENT = registerSoundEvent("entity.phantom_skeleton.ambient");
	public static final SoundEvent ENTITY_PHANTOM_SKELETON_CONVERTED_TO_STRAY = registerSoundEvent("entity.phantom_skeleton.converted_to_stray");
	public static final SoundEvent ENTITY_PHANTOM_SKELETON_DEATH = registerSoundEvent("entity.phantom_skeleton.death");
	public static final SoundEvent ENTITY_PHANTOM_SKELETON_HURT = registerSoundEvent("entity.phantom_skeleton.hurt");
	public static final SoundEvent ENTITY_PHANTOM_SKELETON_SHOOT = registerSoundEvent("entity.phantom_skeleton.shoot");
	public static final SoundEvent ENTITY_PHANTOM_SKELETON_STEP = registerSoundEvent("entity.phantom_skeleton.step");

    //public static final SoundEvent BUTTERFLY_DEATH = registerSoundEvent("entity.butterfly.death");
    //public static final SoundEvent BUTTERFLY_HURT = registerSoundEvent("entity.butterfly.hurt");

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
    
    public static final SoundEvent ENTITY_VEHICLE_DODGEVIPERGTS_BREAK = registerSoundEvent("entity.vehicle.dodgevipergts_break");
    public static final SoundEvent ENTITY_VEHICLE_DODGEVIPERGTS_GEAR_4 = registerSoundEvent("entity.vehicle.dodgevipergts_gear_four");
    public static final SoundEvent ENTITY_VEHICLE_DODGEVIPERGTS_GEAR_1 = registerSoundEvent("entity.vehicle.dodgevipergts_gear_one");
    public static final SoundEvent ENTITY_VEHICLE_DODGEVIPERGTS_GEAR_START = registerSoundEvent("entity.vehicle.dodgevipergts_gear_start");
    public static final SoundEvent ENTITY_VEHICLE_DODGEVIPERGTS_GEAR_3 = registerSoundEvent("entity.vehicle.dodgevipergts_gear_three");
    public static final SoundEvent ENTITY_VEHICLE_DODGEVIPERGTS_GEAR_TOP = registerSoundEvent("entity.vehicle.dodgevipergts_gear_top");
    public static final SoundEvent ENTITY_VEHICLE_DODGEVIPERGTS_GEAR_2 = registerSoundEvent("entity.vehicle.dodgevipergts_gear_two");
    public static final SoundEvent ENTITY_VEHICLE_DODGEVIPERGTS_IDLE = registerSoundEvent("entity.vehicle.dodgevipergts_idle");
    public static final SoundEvent ENTITY_VEHICLE_DODGEVIPERGTS_START = registerSoundEvent("entity.vehicle.dodgevipergts_start");
    public static final SoundEvent ENTITY_VEHICLE_TIRES_SQUAL_LOOP = registerSoundEvent("entity.vehicle.tires_squal_loop");
    public static final SoundEvent ENTITY_VEHICLE_DODGEVIPERGTS_GEAR_ALL = registerSoundEvent("entity.vehicle.dodgevipergts_gear_all");

    public static final SoundEvent ENTITY_VEHICLE_AUDI_GEAR_TOP = registerSoundEvent("entity.vehicle.audi_gear_top");
    public static final SoundEvent ENTITY_VEHICLE_AUDI_GEAR_1 = registerSoundEvent("entity.vehicle.audi_gear_one");
    public static final SoundEvent ENTITY_VEHICLE_AUDI_GEAR_ALL = registerSoundEvent("entity.vehicle.audi_gear_all");
    public static final SoundEvent ENTITY_VEHICLE_AUDI_BREAK = registerSoundEvent("entity.vehicle.audi_gear_break");
    public static final SoundEvent ENTITY_VEHICLE_AUDI_IDLE = registerSoundEvent("entity.vehicle.audi_idle");

    public static final SoundEvent ENTITY_VEHICLE_F1_TOP_GEAR = registerSoundEvent("entity.vehicle.f1_gear_top");
    public static final SoundEvent ENTITY_VEHICLE_F1_IDLE = registerSoundEvent("entity.vehicle.f1_idle");
    public static final SoundEvent ENTITY_VEHICLE_F1_BREAK = registerSoundEvent("entity.vehicle.f1_decelerate");
    public static final SoundEvent ENTITY_VEHICLE_HEAVY_CRASH = registerSoundEvent("entity.vehicle.heavy_crash");
    public static final SoundEvent ENTITY_VEHICLE_LIGHT_CRASH = registerSoundEvent("entity.vehicle.light_crash");

    public static final SoundEvent ENTITY_VEHICLE_F1_MIX_TEST = registerSoundEvent("entity.vehicle.f1_mix");
    public static final SoundEvent ENTITY_VEHICLE_POWER_DRILL = registerSoundEvent("entity.vehicle.power_drill");
    public static final SoundEvent ENTITY_VEHICLE_WRAP = registerSoundEvent("entity.vehicle.wrap");

    public static final SoundEvent ENTITY_VEHICLE_FERRARI_F40_GEAR_TOP = registerSoundEvent("entity.vehicle.ferrari_gear_top");
    public static final SoundEvent ENTITY_VEHICLE_FERRARI_F40_GEAR_1 = registerSoundEvent("entity.vehicle.ferrari_gear_one");
    public static final SoundEvent ENTITY_VEHICLE_FERRARI_F40_BREAK = registerSoundEvent("entity.vehicle.ferrari_gear_break");
    public static final SoundEvent ENTITY_VEHICLE_FERRARI_F40_IDLE = registerSoundEvent("entity.vehicle.ferrari_idle");

    public static final SoundEvent ENTITY_VEHICLE_F1_DEACC_UPDATED = registerSoundEvent("entity.vehicle.f1_deacc_updated");
    public static final SoundEvent ENTITY_VEHICLE_F1_ACC_UPDATED = registerSoundEvent("entity.vehicle.f1_acc_updated");
    public static final SoundEvent ENTITY_VEHICLE_CAR_RAIN = registerSoundEvent("entity.vehicle.car_rain");
    public static final SoundEvent ENTITY_VEHICLE_TURBO_SPOOL = registerSoundEvent("entity.vehicle.turbo_spool-hide");
    public static final SoundEvent ENTITY_VEHICLE_SHIFT_DOWN = registerSoundEvent("entity.vehicle.shift_down");
    public static final SoundEvent ENTITY_VEHICLE_SHIFT_UP = registerSoundEvent("entity.vehicle.shift_up");

    public static final SoundEvent ENTITY_VEHICLE_HOOD_OPEN = registerSoundEvent("entity.vehicle.hood_open");
    public static final SoundEvent ENTITY_VEHICLE_HOOD_CLOSE = registerSoundEvent("entity.vehicle.hood_close");
    public static final SoundEvent ENTITY_VEHICLE_CAR_DOOR_CLOSE = registerSoundEvent("entity.vehicle.car_door_close");
    public static final SoundEvent ENTITY_VEHICLE_CAR_DOOR_OPEN = registerSoundEvent("entity.vehicle.car_door_open");
    



    private static SoundEvent registerSoundEvent(String name) {
        ResourceLocation id = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, name);
        return Registry.register(BuiltInRegistries.SOUND_EVENT, id, SoundEvent.createVariableRangeEvent(id));
    }

    private static Holder.Reference<SoundEvent> registerReference(String id) {
		return registerReference(ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, id));
	}

    private static Holder.Reference<SoundEvent> registerReference(ResourceLocation id) {
		return registerReference(id, id);
	}

    private static Holder.Reference<SoundEvent> registerReference(ResourceLocation id, ResourceLocation soundId) {
		return Registry.registerForHolder(BuiltInRegistries.SOUND_EVENT, id, SoundEvent.createVariableRangeEvent(soundId));
	}

    public static void registerSounds() {
        EntStupidStuff.LOGGER.info("Registering Sounds for " + EntStupidStuff.MOD_ID);
    }

    
}
