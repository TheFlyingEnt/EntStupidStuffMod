package net.ent.entstupidstuff.world.gen;

import net.ent.entstupidstuff.entity.passive.ButterflyEntity;
import net.ent.entstupidstuff.registry.EntityFactory;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.SpawnLocationTypes;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.PatrolEntity;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.BiomeKeys;

public class ModEntitySpawns  {
    public static void addSpawns() {

        //Butterfly
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.FLOWER_FOREST, BiomeKeys.BIRCH_FOREST),
            SpawnGroup.AMBIENT, EntityFactory.BUTTERFLY, 100, 3, 5); //100, 3, 5
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
            Heightmap.Type.MOTION_BLOCKING, HostileEntity::canSpawnInDark);

        //SOUL SKELETON
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.SOUL_SAND_VALLEY),
            SpawnGroup.MONSTER, EntityFactory.SOUL_SKELETON, 100, 2, 3);
        SpawnRestriction.register(EntityFactory.SOUL_SKELETON, SpawnLocationTypes.ON_GROUND,
            Heightmap.Type.MOTION_BLOCKING, HostileEntity::canSpawnInDark);

        // ARMORED ILLAGERS
        SpawnRestriction.register(EntityFactory.ARMORED_PILLAGER, SpawnLocationTypes.ON_GROUND, 
            Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, PatrolEntity::canSpawn);


        // Add Lobber Zombie
        // Add Fire Zombie
        // Drunken Skeleton

    }
    
}
