package net.ent.entstupidstuff.registry;
//SpawnLocationTypes

import net.ent.entstupidstuff.entity.mob.ScorchedZombieEntity;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.SpawnLocationTypes;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.PatrolEntity;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.BiomeKeys;

@Deprecated
public class SpawningFactory {

    public static void regSpawnLobber() {
        SpawnRestriction.register(EntityFactory.LOBBER_ZOMBIE, SpawnLocationTypes.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, HostileEntity::canSpawnInDark);
            BiomeModifications.addSpawn(
            BiomeSelectors.foundInOverworld(),
            //BiomeSelectors.excludeByKey(BiomeKeys.MUSHROOM_FIELDS, BiomeKeys.DESERT),
            /*BiomeSelectors.includeByKey(BiomeKeys.PLAINS, BiomeKeys.FOREST, BiomeKeys.SWAMP, BiomeKeys.DESERT),*/
            SpawnGroup.MONSTER,
            EntityFactory.LOBBER_ZOMBIE,
            95, // Adjusted weight to match or slightly lower than zombies
            1, 
            4
        );

        BiomeModifications.addSpawn(
            BiomeSelectors.includeByKey(BiomeKeys.DRIPSTONE_CAVES, BiomeKeys.LUSH_CAVES),
            SpawnGroup.MONSTER,
            EntityFactory.LOBBER_ZOMBIE,
            150, // Higher weight for cave biomes
            1,
            4
        );
    }

    public static void spawnScorched2() {
        SpawnRestriction.register(EntityFactory.ZOMBIE_SCORCHED, SpawnLocationTypes.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, ScorchedZombieEntity::canSpawnIn);
        BiomeModifications.addSpawn(
            BiomeSelectors.excludeByKey(BiomeKeys.DEEP_DARK),
            //BiomeSelectors.includeByKey(BiomeKeys.PLAINS, BiomeKeys.FOREST, BiomeKeys.SWAMP, BiomeKeys.DESERT),
            SpawnGroup.MONSTER,
            EntityFactory.ZOMBIE_SCORCHED,
            200, // Adjusted weight to match or slightly lower than zombies
            1, 
            4
        );

        /*BiomeModifications.addSpawn(
            BiomeSelectors.includeByKey(BiomeKeys.DRIPSTONE_CAVES, BiomeKeys.LUSH_CAVES),
            SpawnGroup.MONSTER,
            EntityFactory.ZOMBIE_SCORCHED,
            150, // Higher weight for cave biomes
            1,
            4
        );*/
    }

    public static void spawnSoulSkeleto2n() {
        SpawnRestriction.register(EntityFactory.SOUL_SKELETON, SpawnLocationTypes.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, ScorchedZombieEntity::canSpawnIn);
        BiomeModifications.addSpawn(
            BiomeSelectors.includeByKey(BiomeKeys.SOUL_SAND_VALLEY),
            //BiomeSelectors.includeByKey(BiomeKeys.PLAINS, BiomeKeys.FOREST, BiomeKeys.SWAMP, BiomeKeys.DESERT),
            SpawnGroup.MONSTER,
            EntityFactory.SOUL_SKELETON,
            200, // Adjusted weight to match or slightly lower than zombies
            1, 
            4
        );
    }

    public static void spawnArmoredPillager(){
        SpawnRestriction.register(EntityFactory.ARMORED_PILLAGER, SpawnLocationTypes.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, PatrolEntity::canSpawn);
    }

    //// NEW CODE

    public static void spawnLobber() {
        SpawnRestriction.register(EntityFactory.LOBBER_ZOMBIE, SpawnLocationTypes.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, HostileEntity::canSpawnInDark);

        BiomeModifications.addSpawn(
            BiomeSelectors.includeByKey(BiomeKeys.MUSHROOM_FIELDS),
            //BiomeSelectors.tag(BiomeTags.IS_NETHER).and()
            SpawnGroup.MONSTER,
            EntityFactory.LOBBER_ZOMBIE,
            100,
            1,
            4
        );

        /*BiomeModifications.BiomeModifications.addSpawn(
            BiomeSelectors.foundInOverworld(),
            SpawnGroup.MONSTER,
            EntityFactory.LOBBER_ZOMBIE,
            100,
            1,
            4
        );*/

        

    }

    public static void spawnSoulSkeleton() {
        SpawnRestriction.register(EntityFactory.SOUL_SKELETON, SpawnLocationTypes.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, HostileEntity::canSpawnInDark);

        BiomeModifications.addSpawn(
            BiomeSelectors.includeByKey(BiomeKeys.SOUL_SAND_VALLEY),
            SpawnGroup.MONSTER,
            EntityFactory.SOUL_SKELETON,
            100,
            1,
            4
        );

    }

    public static void spawnScorched() {
        SpawnRestriction.register(EntityFactory.ZOMBIE_SCORCHED, SpawnLocationTypes.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, HostileEntity::canSpawnInDark);

        BiomeModifications.addSpawn(
            BiomeSelectors.excludeByKey(BiomeKeys.MUSHROOM_FIELDS, BiomeKeys.DEEP_DARK),
            SpawnGroup.MONSTER,
            EntityFactory.ZOMBIE_SCORCHED,
            100,
            1,
            4
        );

        BiomeModifications.addSpawn(
            BiomeSelectors.foundInOverworld(),
            SpawnGroup.MONSTER,
            EntityFactory.ZOMBIE_SCORCHED,
            100,
            1,
            4
        );

    }


    

    public static void onInitialize() {
        //spawnLobber(); - NEED TO FIX
        //spawnScorched(); - NEED TO FIX
        spawnArmoredPillager();
        spawnSoulSkeleton();
    }

}
