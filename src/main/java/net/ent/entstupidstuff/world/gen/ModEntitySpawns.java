package net.ent.entstupidstuff.world.gen;

import net.ent.entstupidstuff.entity.mob.FrostbittenZombieEntity;
import net.ent.entstupidstuff.entity.mob.SlimedZombieEntity;
import net.ent.entstupidstuff.entity.mob.SunkenSkeletonEntity;
import net.ent.entstupidstuff.entity.passive.AlligatorGarEntity;
import net.ent.entstupidstuff.entity.passive.BassEntity;
import net.ent.entstupidstuff.entity.passive.ButterflyEntity;
import net.ent.entstupidstuff.entity.passive.KoiEntity;
import net.ent.entstupidstuff.entity.passive.MackerelEntity;
import net.ent.entstupidstuff.entity.passive.ZebraFishEntity;
import net.ent.entstupidstuff.item.ModItemTags;
import net.ent.entstupidstuff.registry.EntityFactory;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.SpawnLocationTypes;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.PatrolEntity;
import net.minecraft.entity.passive.CodEntity;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.BiomeKeys;

public class ModEntitySpawns  {
    public static void addSpawns() {

        //Butterfly
        BiomeModifications.addSpawn(BiomeSelectors.tag(ModItemTags.SPAWN_BUTTERFLY),
            SpawnGroup.AMBIENT, EntityFactory.BUTTERFLY, 30, 3, 5); //100, 3, 5
        SpawnRestriction.register(EntityFactory.BUTTERFLY, SpawnLocationTypes.ON_GROUND,
            Heightmap.Type.MOTION_BLOCKING, ButterflyEntity::isValidNaturalSpawn);

        //Lobber
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.DRIPSTONE_CAVES, BiomeKeys.LUSH_CAVES),
            SpawnGroup.MONSTER, EntityFactory.LOBBER_ZOMBIE, 50, 2, 3); //30,2,3
        SpawnRestriction.register(EntityFactory.LOBBER_ZOMBIE, SpawnLocationTypes.ON_GROUND,
            Heightmap.Type.MOTION_BLOCKING, HostileEntity::canSpawnInDark);

        //SCORCHED
        BiomeModifications.addSpawn(BiomeSelectors.excludeByKey(
            BiomeKeys.NETHER_WASTES, BiomeKeys.CRIMSON_FOREST, BiomeKeys.SOUL_SAND_VALLEY, BiomeKeys.BASALT_DELTAS, BiomeKeys.WARPED_FOREST, 
            BiomeKeys.END_BARRENS, BiomeKeys.END_HIGHLANDS, BiomeKeys.END_MIDLANDS, BiomeKeys.THE_END, BiomeKeys.SMALL_END_ISLANDS, 
            BiomeKeys.THE_VOID, BiomeKeys.DEEP_DARK),
            SpawnGroup.MONSTER, EntityFactory.ZOMBIE_SCORCHED, 500, 2, 3); //50, 2, 3
        SpawnRestriction.register(EntityFactory.ZOMBIE_SCORCHED, SpawnLocationTypes.IN_LAVA,
            Heightmap.Type.MOTION_BLOCKING, HostileEntity::canSpawnIgnoreLightLevel);

        //SOUL SKELETON
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.SOUL_SAND_VALLEY),
            SpawnGroup.MONSTER, EntityFactory.SOUL_SKELETON, 100, 2, 3);
        SpawnRestriction.register(EntityFactory.SOUL_SKELETON, SpawnLocationTypes.ON_GROUND,
            Heightmap.Type.MOTION_BLOCKING, HostileEntity::canSpawnInDark);

        // ARMORED ILLAGERS
        SpawnRestriction.register(EntityFactory.ARMORED_PILLAGER, SpawnLocationTypes.ON_GROUND, 
            Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, PatrolEntity::canSpawn);

        // ALLIGATOR GAR
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.MANGROVE_SWAMP, BiomeKeys.SWAMP),
            SpawnGroup.AMBIENT, EntityFactory.ALLIGATOR_GAR, 30, 5, 8); //100, 3, 5
        SpawnRestriction.register(EntityFactory.ALLIGATOR_GAR, SpawnLocationTypes.IN_WATER,
            Heightmap.Type.MOTION_BLOCKING, AlligatorGarEntity::canMobSpawn);

        // MACKEREL
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.LUKEWARM_OCEAN, BiomeKeys.DEEP_LUKEWARM_OCEAN, BiomeKeys.OCEAN, BiomeKeys.DEEP_OCEAN, BiomeKeys.COLD_OCEAN, BiomeKeys.DEEP_COLD_OCEAN),
            SpawnGroup.AMBIENT, EntityFactory.MACKEREL, 10, 4, 7); //100, 3, 5
        SpawnRestriction.register(EntityFactory.MACKEREL, SpawnLocationTypes.IN_WATER,
            Heightmap.Type.MOTION_BLOCKING, MackerelEntity::canMobSpawn);

        // BASS
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.RIVER, BiomeKeys.TAIGA, BiomeKeys.OLD_GROWTH_PINE_TAIGA, BiomeKeys.OLD_GROWTH_SPRUCE_TAIGA),
            SpawnGroup.AMBIENT, EntityFactory.BASS, 30, 2, 5); //100, 3, 5
        SpawnRestriction.register(EntityFactory.BASS, SpawnLocationTypes.IN_WATER,
            Heightmap.Type.MOTION_BLOCKING, BassEntity::canMobSpawn);

        // FUR TROUT
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.FROZEN_OCEAN, BiomeKeys.FROZEN_RIVER, BiomeKeys.COLD_OCEAN, BiomeKeys.DEEP_COLD_OCEAN),
            SpawnGroup.AMBIENT, EntityFactory.FURTROUT, 30, 2, 5); //100, 3, 5
        SpawnRestriction.register(EntityFactory.FURTROUT, SpawnLocationTypes.IN_WATER,
            Heightmap.Type.MOTION_BLOCKING, CodEntity::canMobSpawn);

        // KOI #1 (UnCommon)
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.WARM_OCEAN, BiomeKeys.CHERRY_GROVE, BiomeKeys.MEADOW),
            SpawnGroup.AMBIENT, EntityFactory.KOI, 5, 2, 3); //100, 3, 5
        SpawnRestriction.register(EntityFactory.KOI, SpawnLocationTypes.IN_WATER,
            Heightmap.Type.MOTION_BLOCKING, KoiEntity::canMobSpawn);

        // Zebra Fish
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.WARM_OCEAN, BiomeKeys.DEEP_LUKEWARM_OCEAN),
            SpawnGroup.AMBIENT, EntityFactory.ZEBRA_FISH, 20, 3, 5); //100, 3, 5
        SpawnRestriction.register(EntityFactory.ZEBRA_FISH, SpawnLocationTypes.IN_WATER,
            Heightmap.Type.MOTION_BLOCKING, ZebraFishEntity::canMobSpawn);

        //Sunken Skeleton
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.OCEAN, BiomeKeys.DEEP_OCEAN, BiomeKeys.DEEP_LUKEWARM_OCEAN, BiomeKeys.WARM_OCEAN),
            SpawnGroup.MONSTER, EntityFactory.SUNKEN_SKELETON, 30, 2, 3);
        SpawnRestriction.register(EntityFactory.SUNKEN_SKELETON, SpawnLocationTypes.IN_WATER,
            Heightmap.Type.MOTION_BLOCKING, SunkenSkeletonEntity::canSpawnInDark);

        //Zombie Frostbitten
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.JAGGED_PEAKS, BiomeKeys.FROZEN_PEAKS, BiomeKeys.GROVE, BiomeKeys.SNOWY_SLOPES, BiomeKeys.SNOWY_TAIGA, BiomeKeys.FROZEN_RIVER, BiomeKeys.SNOWY_BEACH, BiomeKeys.SNOWY_PLAINS, BiomeKeys.ICE_SPIKES),
            SpawnGroup.MONSTER, EntityFactory.ZOMBIE_FROSTBITTEN, 30, 1, 3);
        SpawnRestriction.register(EntityFactory.ZOMBIE_FROSTBITTEN, SpawnLocationTypes.ON_GROUND,
            Heightmap.Type.MOTION_BLOCKING, FrostbittenZombieEntity::canSpawnInDark);

        //Zombie Slimed
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.OCEAN, BiomeKeys.SWAMP, BiomeKeys.MANGROVE_SWAMP),
            SpawnGroup.MONSTER, EntityFactory.ZOMBIE_SLIMED, 30, 1, 3);
        SpawnRestriction.register(EntityFactory.ZOMBIE_SLIMED, SpawnLocationTypes.ON_GROUND,
            Heightmap.Type.MOTION_BLOCKING, SlimedZombieEntity::canSpawnInDark);
        // Add Lobber Zombie
        // Add Fire Zombie
        // Drunken Skeleton

    }
    
}
