package net.ent.entstupidstuff.registry;
//SpawnLocationTypes

import net.ent.entstupidstuff.client.entity.mob.ScorchedZombieEntity;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.PatrollingMonster;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.Heightmap;

@Deprecated
public class SpawningFactory {

    public static void regSpawnLobber() {
        SpawnPlacements.register(EntityFactory.ZOMBIE_LOBBER, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);
            BiomeModifications.addSpawn(
            BiomeSelectors.foundInOverworld(),
            //BiomeSelectors.excludeByKey(BiomeKeys.MUSHROOM_FIELDS, BiomeKeys.DESERT),
            /*BiomeSelectors.includeByKey(BiomeKeys.PLAINS, BiomeKeys.FOREST, BiomeKeys.SWAMP, BiomeKeys.DESERT),*/
            MobCategory.MONSTER,
            EntityFactory.ZOMBIE_LOBBER,
            95, // Adjusted weight to match or slightly lower than zombies
            1, 
            4
        );

        BiomeModifications.addSpawn(
            BiomeSelectors.includeByKey(Biomes.DRIPSTONE_CAVES, Biomes.LUSH_CAVES),
            MobCategory.MONSTER,
            EntityFactory.ZOMBIE_LOBBER,
            150, // Higher weight for cave biomes
            1,
            4
        );
    }

    public static void spawnScorched2() {
        SpawnPlacements.register(EntityFactory.ZOMBIE_SCORCHED, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, ScorchedZombieEntity::canSpawnIn);
        BiomeModifications.addSpawn(
            BiomeSelectors.excludeByKey(Biomes.DEEP_DARK),
            //BiomeSelectors.includeByKey(BiomeKeys.PLAINS, BiomeKeys.FOREST, BiomeKeys.SWAMP, BiomeKeys.DESERT),
            MobCategory.MONSTER,
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
        SpawnPlacements.register(EntityFactory.SOUL_SKELETON, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, ScorchedZombieEntity::canSpawnIn);
        BiomeModifications.addSpawn(
            BiomeSelectors.includeByKey(Biomes.SOUL_SAND_VALLEY),
            //BiomeSelectors.includeByKey(BiomeKeys.PLAINS, BiomeKeys.FOREST, BiomeKeys.SWAMP, BiomeKeys.DESERT),
            MobCategory.MONSTER,
            EntityFactory.SOUL_SKELETON,
            200, // Adjusted weight to match or slightly lower than zombies
            1, 
            4
        );
    }

    public static void spawnArmoredPillager(){
        SpawnPlacements.register(EntityFactory.ARMORED_PILLAGER, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, PatrollingMonster::checkPatrollingMonsterSpawnRules);
    }

    //// NEW CODE

    public static void spawnLobber() {
        SpawnPlacements.register(EntityFactory.ZOMBIE_LOBBER, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);

        BiomeModifications.addSpawn(
            BiomeSelectors.includeByKey(Biomes.MUSHROOM_FIELDS),
            //BiomeSelectors.tag(BiomeTags.IS_NETHER).and()
            MobCategory.MONSTER,
            EntityFactory.ZOMBIE_LOBBER,
            100,
            1,
            4
        );

        /*BiomeModifications.BiomeModifications.addSpawn(
            BiomeSelectors.foundInOverworld(),
            SpawnGroup.MONSTER,
            EntityFactory.ZOMBIE_LOBBER,
            100,
            1,
            4
        );*/

        

    }

    public static void spawnSoulSkeleton() {
        SpawnPlacements.register(EntityFactory.SOUL_SKELETON, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);

        BiomeModifications.addSpawn(
            BiomeSelectors.includeByKey(Biomes.SOUL_SAND_VALLEY),
            MobCategory.MONSTER,
            EntityFactory.SOUL_SKELETON,
            100,
            1,
            4
        );

    }

    public static void spawnScorched() {
        SpawnPlacements.register(EntityFactory.ZOMBIE_SCORCHED, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Monster::checkMonsterSpawnRules);

        BiomeModifications.addSpawn(
            BiomeSelectors.excludeByKey(Biomes.MUSHROOM_FIELDS, Biomes.DEEP_DARK),
            MobCategory.MONSTER,
            EntityFactory.ZOMBIE_SCORCHED,
            100,
            1,
            4
        );

        BiomeModifications.addSpawn(
            BiomeSelectors.foundInOverworld(),
            MobCategory.MONSTER,
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
