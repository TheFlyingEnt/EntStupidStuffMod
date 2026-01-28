package net.ent.entstupidstuff.world.gen;

import net.ent.entstupidstuff.client.entity.mob.FrostbittenZombieEntity;
import net.ent.entstupidstuff.client.entity.mob.FungalSkeletonEntity;
import net.ent.entstupidstuff.client.entity.mob.FungalZombieEntity;
import net.ent.entstupidstuff.client.entity.mob.PiglinWarriorEntity;
import net.ent.entstupidstuff.client.entity.mob.SilkmothEntity;
import net.ent.entstupidstuff.client.entity.mob.SlimedZombieEntity;
import net.ent.entstupidstuff.client.entity.mob.SunkenSkeletonEntity;
import net.ent.entstupidstuff.client.entity.passive.ButterflyEntity;
import net.ent.entstupidstuff.item.ModTags;
import net.ent.entstupidstuff.registry.EntityFactory;
import net.ent.entstupidstuff.world.biome.ModBiomes;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.PatrollingMonster;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.Heightmap;

/*
 * Reference Class: SpawnRestriction
 */

public class ModEntitySpawns  {
    public static void addSpawns() {

        //Butterfly
        BiomeModifications.addSpawn(BiomeSelectors.tag(ModTags.SPAWN_BUTTERFLY),
            MobCategory.AMBIENT, EntityFactory.BUTTERFLY, 30, 3, 5); //100, 3, 5
        SpawnPlacements.register(EntityFactory.BUTTERFLY, SpawnPlacementTypes.ON_GROUND,
            Heightmap.Types.MOTION_BLOCKING, ButterflyEntity::isValidNaturalSpawn);

        //Butterfly
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(ModBiomes.UNDERGROUND_BLUE_MUSHROOM),
            MobCategory.AMBIENT, EntityFactory.SILKMOTH, 30, 2, 3); //100, 3, 5
        SpawnPlacements.register(EntityFactory.SILKMOTH, SpawnPlacementTypes.ON_GROUND,
            Heightmap.Types.MOTION_BLOCKING, SilkmothEntity::isValidNaturalSpawn);

        //Lobber
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(Biomes.DRIPSTONE_CAVES, Biomes.LUSH_CAVES),
            MobCategory.MONSTER, EntityFactory.ZOMBIE_LOBBER, 50, 2, 3); //30,2,3
        SpawnPlacements.register(EntityFactory.ZOMBIE_LOBBER, SpawnPlacementTypes.ON_GROUND,
            Heightmap.Types.MOTION_BLOCKING, Monster::checkMonsterSpawnRules);

        //SCORCHED
        BiomeModifications.addSpawn(BiomeSelectors.excludeByKey(
            Biomes.NETHER_WASTES, Biomes.CRIMSON_FOREST, Biomes.SOUL_SAND_VALLEY, Biomes.BASALT_DELTAS, Biomes.WARPED_FOREST, 
            Biomes.END_BARRENS, Biomes.END_HIGHLANDS, Biomes.END_MIDLANDS, Biomes.THE_END, Biomes.SMALL_END_ISLANDS, 
            Biomes.THE_VOID, Biomes.DEEP_DARK),
            MobCategory.MONSTER, EntityFactory.ZOMBIE_SCORCHED, 500, 2, 3); //50, 2, 3
        SpawnPlacements.register(EntityFactory.ZOMBIE_SCORCHED, SpawnPlacementTypes.IN_LAVA,
            Heightmap.Types.MOTION_BLOCKING, Monster::checkAnyLightMonsterSpawnRules);

        //SOUL SKELETON
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(Biomes.SOUL_SAND_VALLEY),
            MobCategory.MONSTER, EntityFactory.SOUL_SKELETON, 100, 2, 3);
        SpawnPlacements.register(EntityFactory.SOUL_SKELETON, SpawnPlacementTypes.ON_GROUND,
            Heightmap.Types.MOTION_BLOCKING, Monster::checkMonsterSpawnRules);

        // ARMORED ILLAGERS
        SpawnPlacements.register(EntityFactory.ARMORED_PILLAGER, SpawnPlacementTypes.ON_GROUND, 
            Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, PatrollingMonster::checkPatrollingMonsterSpawnRules);

        // ALLIGATOR GAR
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(Biomes.MANGROVE_SWAMP, Biomes.SWAMP),
            MobCategory.AMBIENT, EntityFactory.ALLIGATOR_GAR, 30, 5, 8); //100, 3, 5
        SpawnPlacements.register(EntityFactory.ALLIGATOR_GAR, SpawnPlacementTypes.IN_WATER,
            Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, WaterAnimal::checkMobSpawnRules);

        // MACKEREL
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(Biomes.LUKEWARM_OCEAN, Biomes.DEEP_LUKEWARM_OCEAN, Biomes.OCEAN, Biomes.DEEP_OCEAN, Biomes.COLD_OCEAN, Biomes.DEEP_COLD_OCEAN),
            MobCategory.AMBIENT, EntityFactory.MACKEREL, 10, 4, 7); //100, 3, 5
        SpawnPlacements.register(EntityFactory.MACKEREL, SpawnPlacementTypes.IN_WATER,
            Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, WaterAnimal::checkMobSpawnRules);

        // BASS
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(Biomes.RIVER, Biomes.TAIGA, Biomes.OLD_GROWTH_PINE_TAIGA, Biomes.OLD_GROWTH_SPRUCE_TAIGA),
            MobCategory.AMBIENT, EntityFactory.BASS, 30, 2, 5); //100, 3, 5
        SpawnPlacements.register(EntityFactory.BASS, SpawnPlacementTypes.IN_WATER,
            Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, WaterAnimal::checkMobSpawnRules);

        // FUR TROUT
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(Biomes.FROZEN_OCEAN, Biomes.FROZEN_RIVER, Biomes.COLD_OCEAN, Biomes.DEEP_COLD_OCEAN),
            MobCategory.AMBIENT, EntityFactory.FURTROUT, 30, 2, 5); //100, 3, 5
        SpawnPlacements.register(EntityFactory.FURTROUT, SpawnPlacementTypes.IN_WATER,
            Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, WaterAnimal::checkMobSpawnRules);

        // KOI #1 (UnCommon)
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(Biomes.WARM_OCEAN, Biomes.CHERRY_GROVE, Biomes.MEADOW),
            MobCategory.AMBIENT, EntityFactory.KOI, 5, 1, 3); //100, 3, 5
        SpawnPlacements.register(EntityFactory.KOI, SpawnPlacementTypes.IN_WATER,
            Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, WaterAnimal::checkMobSpawnRules);

        // Zebra Fish
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(Biomes.WARM_OCEAN, Biomes.DEEP_LUKEWARM_OCEAN),
            MobCategory.AMBIENT, EntityFactory.ZEBRA_FISH, 20, 3, 5); //100, 3, 5
        SpawnPlacements.register(EntityFactory.ZEBRA_FISH, SpawnPlacementTypes.IN_WATER,
            Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, WaterAnimal::checkMobSpawnRules);

        // Mahi Mahi
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(Biomes.WARM_OCEAN, Biomes.DEEP_LUKEWARM_OCEAN),
            MobCategory.AMBIENT, EntityFactory.MAHIMAHI, 20, 1, 3); //100, 3, 5
        SpawnPlacements.register(EntityFactory.MAHIMAHI, SpawnPlacementTypes.IN_WATER,
            Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, WaterAnimal::checkMobSpawnRules);

        // Red Snapper
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(Biomes.WARM_OCEAN, Biomes.DEEP_LUKEWARM_OCEAN),
            MobCategory.AMBIENT, EntityFactory.SNAPPER, 20, 2, 4); //100, 3, 5
        SpawnPlacements.register(EntityFactory.SNAPPER, SpawnPlacementTypes.IN_WATER,
            Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, WaterAnimal::checkMobSpawnRules);

        // Perch
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(Biomes.WARM_OCEAN, Biomes.DEEP_LUKEWARM_OCEAN),
            MobCategory.AMBIENT, EntityFactory.PERCH, 20, 4, 6); //100, 3, 5
        SpawnPlacements.register(EntityFactory.PERCH, SpawnPlacementTypes.IN_WATER,
            Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, WaterAnimal::checkMobSpawnRules);

        //Sunken Skeleton
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(Biomes.OCEAN, Biomes.DEEP_OCEAN, Biomes.DEEP_LUKEWARM_OCEAN, Biomes.WARM_OCEAN),
            MobCategory.MONSTER, EntityFactory.SUNKEN_SKELETON, 30, 2, 3);
        SpawnPlacements.register(EntityFactory.SUNKEN_SKELETON, SpawnPlacementTypes.IN_WATER,
            Heightmap.Types.MOTION_BLOCKING, SunkenSkeletonEntity::checkMonsterSpawnRules);

        //Zombie Frostbitten
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(Biomes.JAGGED_PEAKS, Biomes.FROZEN_PEAKS, Biomes.GROVE, Biomes.SNOWY_SLOPES, Biomes.SNOWY_TAIGA, Biomes.FROZEN_RIVER, Biomes.SNOWY_BEACH, Biomes.SNOWY_PLAINS, Biomes.ICE_SPIKES),
            MobCategory.MONSTER, EntityFactory.ZOMBIE_FROSTBITTEN, 30, 1, 3);
        SpawnPlacements.register(EntityFactory.ZOMBIE_FROSTBITTEN, SpawnPlacementTypes.ON_GROUND,
            Heightmap.Types.MOTION_BLOCKING, FrostbittenZombieEntity::checkMonsterSpawnRules);

        //Zombie Slimed
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(Biomes.SWAMP, Biomes.MANGROVE_SWAMP),
            MobCategory.MONSTER, EntityFactory.ZOMBIE_SLIMED, 30, 1, 3);
        SpawnPlacements.register(EntityFactory.ZOMBIE_SLIMED, SpawnPlacementTypes.ON_GROUND,
            Heightmap.Types.MOTION_BLOCKING, SlimedZombieEntity::checkMonsterSpawnRules);

        //Piglin Warrior
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(Biomes.NETHER_WASTES, Biomes.CRIMSON_FOREST),
            MobCategory.MONSTER, EntityFactory.PIGLIN_WARRIOR, 30, 1, 3);
        SpawnPlacements.register(EntityFactory.PIGLIN_WARRIOR, SpawnPlacementTypes.ON_GROUND,
            Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, PiglinWarriorEntity::canSpawn);

        //Fungal Skeleton
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(ModBiomes.UNDERGROUND_BLUE_MUSHROOM),
            MobCategory.MONSTER, EntityFactory.FUNGAL_SKELETON, 30, 1, 3);
        SpawnPlacements.register(EntityFactory.FUNGAL_SKELETON, SpawnPlacementTypes.ON_GROUND,
            Heightmap.Types.MOTION_BLOCKING, FungalSkeletonEntity::checkMonsterSpawnRules);

        //Zombie Fungal
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(ModBiomes.UNDERGROUND_BLUE_MUSHROOM),
            MobCategory.MONSTER, EntityFactory.ZOMBIE_FUNGAL, 30, 1, 3);
        SpawnPlacements.register(EntityFactory.ZOMBIE_FUNGAL, SpawnPlacementTypes.ON_GROUND,
            Heightmap.Types.MOTION_BLOCKING, FungalZombieEntity::checkMonsterSpawnRules);


        // Add Lobber Zombie
        // Add Fire Zombie
        // Drunken Skeleton

    }
    
}
