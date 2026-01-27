package net.ent.entstupidstuff.world.biome;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.registry.EntityFactory;
import net.ent.entstupidstuff.world.ModPlacedFeatures;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.Carvers;
import net.minecraft.data.worldgen.placement.AquaticPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.Music;
import net.minecraft.sounds.Musics;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;

public class ModBiomes {
    public static final ResourceKey<Biome> MAPLE_FOREST = ResourceKey.create(Registries.BIOME,
            ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "maple_forest"));
    public static final ResourceKey<Biome> ICY_CAVES = ResourceKey.create(Registries.BIOME,
            ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "icy_caves"));
    public static final ResourceKey<Biome> UNDERGROUND_BLUE_MUSHROOM = ResourceKey.create(Registries.BIOME,
            ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "underground_blue_mushroom"));
    public static final ResourceKey<Biome> SUNKEN_SEA = ResourceKey.create(Registries.BIOME,
            ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "sunken_sea"));

    public static void boostrap(BootstrapContext<Biome> context) {
        //context.register(MAPLE_FOREST, mapleforest(context));
        context.register(ICY_CAVES, icyCaves(context));
        context.register(MAPLE_FOREST, mapleForest(context));
        context.register(UNDERGROUND_BLUE_MUSHROOM, undergroundBlueMushroom(context));
        context.register(SUNKEN_SEA, createSunkenSea(context));
    }

    //biomeBuilder.feature(GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.MAPLE_TREE_PLACED_KEY);

    private static void addBasicFeatures(BiomeGenerationSettings.Builder generationSettings) {
		BiomeDefaultFeatures.addDefaultCarversAndLakes(generationSettings);
		BiomeDefaultFeatures.addDefaultCrystalFormations(generationSettings);
		BiomeDefaultFeatures.addDefaultMonsterRoom(generationSettings);
		BiomeDefaultFeatures.addDefaultUndergroundVariety(generationSettings);
		BiomeDefaultFeatures.addDefaultSprings(generationSettings);
		BiomeDefaultFeatures.addSurfaceFreezing(generationSettings);
	}

    private static Biome createSunkenSea(BootstrapContext<Biome> context) {

        // === Spawn Settings ===
        MobSpawnSettings.Builder spawnSettings = new MobSpawnSettings.Builder();

        spawnSettings.addSpawn(MobCategory.WATER_AMBIENT, 10,
                new MobSpawnSettings.SpawnerData(EntityType.COD, 4, 8));
        spawnSettings.addSpawn(MobCategory.WATER_AMBIENT, 8,
                new MobSpawnSettings.SpawnerData(EntityType.SALMON, 2, 5));

        spawnSettings.addSpawn(MobCategory.MONSTER, 20, 
                new MobSpawnSettings.SpawnerData(EntityType.DROWNED, 1, 3));
        spawnSettings.addSpawn(MobCategory.MONSTER, 5,
                new MobSpawnSettings.SpawnerData(EntityType.GUARDIAN, 1, 2));

        // === Generation Settings ===
        BiomeGenerationSettings.Builder generation =
                new BiomeGenerationSettings.Builder(
                        context.lookup(Registries.PLACED_FEATURE),
                        context.lookup(Registries.CONFIGURED_CARVER)
                );

        // Terrain
        generation.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ModPlacedFeatures.THALASSITE_ORE_PLACE_KEY);

        // Coral & vegetation
        //generation.feature(GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.SUNKEN_CORAL);
        //generation.feature(GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.SEAWEED_PATCH);

        addBasicFeatures(generation);
		BiomeDefaultFeatures.addDefaultOres(generation);
		BiomeDefaultFeatures.addDefaultSoftDisks(generation);
		BiomeDefaultFeatures.addWaterTrees(generation);
		BiomeDefaultFeatures.addDefaultFlowers(generation);
		BiomeDefaultFeatures.addDefaultGrass(generation);
		BiomeDefaultFeatures.addDefaultMushrooms(generation);
		BiomeDefaultFeatures.addDefaultExtraVegetation(generation, true);

		generation
			.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AquaticPlacements.WARM_OCEAN_VEGETATION)
			.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AquaticPlacements.SEAGRASS_WARM)
			.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AquaticPlacements.SEA_PICKLE);

        BiomeDefaultFeatures.addLukeWarmKelp(generation);

        generation.addCarver(Carvers.CAVE);
        generation.addCarver(Carvers.CAVE_EXTRA_UNDERGROUND);

        /*generation.feature(
            GenerationStep.Feature.TOP_LAYER_MODIFICATION, // runs after carving
            ModPlacedFeatures.SUNKEN_WATER_PLACED
        );*/

        /*generation.feature(
            GenerationStep.Feature.LAKES,
            ModPlacedFeatures.SUNKEN_WATER_PLACED
        );*/
        

        // === Biome Effects ===
        BiomeSpecialEffects effects = new BiomeSpecialEffects.Builder()
                .waterColor(0x1B4F72)
                .waterFogColor(0x0A2A43)
                .fogColor(0x081A2B)
                .skyColor(0x000000) // underground
                .build();

        return new Biome.BiomeBuilder()
                .hasPrecipitation(false)
                .temperature(0.5f)
                .downfall(0.0f)
                .specialEffects(effects)
                .mobSpawnSettings(spawnSettings.build())
                .generationSettings(generation.build())
                .build();
    }




    /* 
    public static Biome sunkenSea(Registerable<Biome> context) {
        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
        spawnSettings.spawn(SpawnGroup.WATER_AMBIENT, 15, new SpawnSettings.SpawnEntry(EntityType.PUFFERFISH, 1, 3));
		DefaultBiomeFeatures.addWarmOceanMobs(spawnSettings, 10, 4); 

        GenerationSettings.LookupBackedBuilder biomeBuilder =
            new GenerationSettings.LookupBackedBuilder(context.getRegistryLookup(RegistryKeys.PLACED_FEATURE),

        context.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER));

        addBasicFeatures(biomeBuilder);
		DefaultBiomeFeatures.addDefaultOres(biomeBuilder);
		DefaultBiomeFeatures.addDefaultDisks(biomeBuilder);
		DefaultBiomeFeatures.addWaterBiomeOakTrees(biomeBuilder);
		DefaultBiomeFeatures.addDefaultFlowers(biomeBuilder);
		DefaultBiomeFeatures.addDefaultGrass(biomeBuilder);
		DefaultBiomeFeatures.addDefaultMushrooms(biomeBuilder);
		DefaultBiomeFeatures.addDefaultVegetation(biomeBuilder, true);

		biomeBuilder
			.feature(GenerationStep.Feature.VEGETAL_DECORATION, OceanPlacedFeatures.WARM_OCEAN_VEGETATION)
			.feature(GenerationStep.Feature.VEGETAL_DECORATION, OceanPlacedFeatures.SEAGRASS_WARM)
			.feature(GenerationStep.Feature.VEGETAL_DECORATION, OceanPlacedFeatures.SEA_PICKLE);

        DefaultBiomeFeatures.addLessKelp(biomeBuilder);

        //createOcean(builder, 4566514, 267827, lookupBackedBuilder);
        //spawnSettings, int waterColor, int waterFogColor, GenerationSettings.biomeBuilder generationSettings
        //createBiome(true, 0.5F, 0.5F, 4566514, 267827, null, null, null, spawnSettings, generationSettings, DEFAULT_MUSIC);

        BiomeEffects effects = new BiomeEffects.Builder()
            .waterColor(0x61AEEE)      // Warm Ocean water color
            .waterFogColor(0x054E81)   // Warm Ocean water fog
            .fogColor(0xC0D8FF)
            .skyColor(0x77ADFF)
            .foliageColor(0xFF8C8C)    // Maple tint
            .grassColor(0x8BC057)
        .build();

        return new Biome.Builder()
            .precipitation(true)
            .temperature(0.7f)
            .downfall(0.8f)
            .effects(effects)
            .spawnSettings(spawnSettings.build())
            .generationSettings(biomeBuilder.build())
            .build();
		
    }
    */
    public static Biome mapleForest(BootstrapContext<Biome> context) {

        BiomeGenerationSettings.Builder biomeBuilder =
            new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE),
        context.lookup(Registries.CONFIGURED_CARVER));

        MobSpawnSettings.Builder spawnSettings = new MobSpawnSettings.Builder();
        spawnSettings.addSpawn(MobCategory.CREATURE, 5, new MobSpawnSettings.SpawnerData(EntityType.FOX, 2, 4))
			.addSpawn(MobCategory.CREATURE, 6, new MobSpawnSettings.SpawnerData(EntityType.RABBIT, 2, 3))
			.addSpawn(MobCategory.CREATURE, 5, new MobSpawnSettings.SpawnerData(EntityType.SHEEP, 2, 4));
        BiomeDefaultFeatures.commonSpawns(spawnSettings);
		addBasicFeatures(biomeBuilder);
		BiomeDefaultFeatures.addPlainGrass(biomeBuilder);
		BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
		BiomeDefaultFeatures.addDefaultSoftDisks(biomeBuilder);
        

        BiomeDefaultFeatures.addExtraEmeralds(biomeBuilder);
		BiomeDefaultFeatures.addInfestedStone(biomeBuilder);
		Music musicSound = Musics.createGameMusic(SoundEvents.MUSIC_BIOME_CHERRY_GROVE);

        //biomeBuilder.feature(GenerationStep.Feature.FLUID_SPRINGS, ModPlacedFeatures.); 
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.MAPLE_TREE_PLACED_KEY);

        BiomeSpecialEffects effects = new BiomeSpecialEffects.Builder()
            .waterColor(0x61AEEE)      // Warm Ocean water color
            .waterFogColor(0x054E81)   // Warm Ocean water fog
            .fogColor(0xC0D8FF)
            .skyColor(0x77ADFF)
            .foliageColorOverride(0xFF8C8C)    // Maple tint
            .grassColorOverride(0x8BC057)
        .build();

        return new Biome.BiomeBuilder()
            .hasPrecipitation(true)
            .temperature(0.7f)
            .downfall(0.8f)
            .specialEffects(effects)
            .mobSpawnSettings(spawnSettings.build())
            .generationSettings(biomeBuilder.build())
            .build();

    }

    public static Biome undergroundBlueMushroom(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder builder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(builder);

        //Mushrom Creatures
        builder.addSpawn(MobCategory.MONSTER, 150, new MobSpawnSettings.SpawnerData(EntityFactory.FUNGAL_SKELETON, 1, 4));
        builder.addSpawn(MobCategory.MONSTER, 150, new MobSpawnSettings.SpawnerData(EntityFactory.ZOMBIE_FUNGAL, 1, 4));
        builder.addSpawn(MobCategory.MONSTER, 100, new MobSpawnSettings.SpawnerData(EntityFactory.SPOREPER, 1, 2));

        BiomeGenerationSettings.Builder biomeBuilder =
            new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE),
        context.lookup(Registries.CONFIGURED_CARVER));

        addBasicFeatures(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);

        //Add Mud Biome Features
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, 
            ModPlacedFeatures.MUD_LAYER_PLACED);

        // Then add shroomium floor on top
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, 
            ModPlacedFeatures.SHROOMIUM_FLOOR_PLACED);

        // Add huge blue mushrooms
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, 
            ModPlacedFeatures.HUGE_BLUE_MUSHROOM_PLACED);

        // Add extra mushroom bed patches
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, 
            ModPlacedFeatures.MUSHROOM_BED_PATCH_PLACED);

        // Add fungal spore blossoms
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, 
            ModPlacedFeatures.FUNGAL_SPORE_BLOSSOM_PLACED);

        // Add fungal aura spore blossoms
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, 
            ModPlacedFeatures.MUSHROOM_AURA_BLOSSOM_PLACED);

        //biomeBuilder.addFeature(
        //    GenerationStep.Decoration.UNDERGROUND_DECORATION,
        //    ModPlacedFeatures.CRYSTAL_SPIKE_PLACED
        //);

            

        Music musicSound = Musics.createGameMusic(SoundEvents.MUSIC_BIOME_LUSH_CAVES);

        BiomeSpecialEffects effects = new BiomeSpecialEffects.Builder()
                .fogColor(12638463)
                .skyColor(7907327)
                .waterColor(4375259)
                .waterFogColor(2710405)
                //.moodSound(new BiomeMoodSound(RegistryEntry.of(SoundEvents.AMBIENT_CAVE), 6000, 8, 2.0))
                .backgroundMusic(musicSound)
        .build();


        return new Biome.BiomeBuilder()
            .hasPrecipitation(true)
            .temperature(0.4f)
            .downfall(0.4f)
            .specialEffects(effects)
            .mobSpawnSettings(builder.build())
            .generationSettings(biomeBuilder.build())
        .build();


    }


    public static Biome icyCaves(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
		BiomeDefaultFeatures.dripstoneCavesSpawns(spawnBuilder);
		BiomeGenerationSettings.Builder biomeBuilder =
            new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE),
        context.lookup(Registries.CONFIGURED_CARVER));

        addBasicFeatures(biomeBuilder);
        BiomeDefaultFeatures.addPlainGrass(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder, true);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ModPlacedFeatures.ORE_LIMESTONE_LOWER_PLACED_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ModPlacedFeatures.ORE_LIMESTONE_PLACED_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ModPlacedFeatures.ORE_LIMESTONE_UPPER_PLACED_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ModPlacedFeatures.ORE_PACKED_ICE_LOWER_PLACED_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ModPlacedFeatures.ORE_PACKED_ICE_PLACED_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ModPlacedFeatures.ORE_SNOW_PLACED_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ModPlacedFeatures.ORE_SNOW_UPPER_PLACED_KEY);
		BiomeDefaultFeatures.addDefaultSoftDisks(biomeBuilder);
		BiomeDefaultFeatures.addPlainVegetation(biomeBuilder);
		BiomeDefaultFeatures.addDefaultMushrooms(biomeBuilder);
		BiomeDefaultFeatures.addDefaultExtraVegetation(biomeBuilder, false);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, ModPlacedFeatures.LARGE_SPIKED_ICE_PLACED_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, ModPlacedFeatures.SPIKED_ICE_CLUSTER_PLACED_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, ModPlacedFeatures.SPIKED_ICE_PLACED_KEY);
        
        Music musicSound = Musics.createGameMusic(SoundEvents.MUSIC_BIOME_DRIPSTONE_CAVES);
		BiomeSpecialEffects effects = new BiomeSpecialEffects.Builder()
                .fogColor(12638463)
                .skyColor(7907327)
                .waterColor(3750089)
                .waterFogColor(329011)
                //.moodSound(new BiomeMoodSound(RegistryEntry.of(SoundEvents.AMBIENT_CAVE), 6000, 8, 2.0))
                .backgroundMusic(musicSound)
        .build();

        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .temperature(0.10f)
                .downfall(0.4f)
                .specialEffects(effects)
                .mobSpawnSettings(spawnBuilder.build())
                .generationSettings(biomeBuilder.build())
        .build();











        /*//DefaultBiomeFeatures.addBatsAndMonsters(spawnBuilder);
        spawnBuilder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.SPIDER, 100, 4, 4));
        spawnBuilder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.ZOMBIE, 95, 4, 4));
        spawnBuilder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.ZOMBIE_VILLAGER, 5, 1, 1));
        spawnBuilder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.SKELETON, 100, 4, 4));
        spawnBuilder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.STRAY, 100, 4, 4));
        spawnBuilder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.CREEPER, 100, 4, 4));
        spawnBuilder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.ENDERMAN, 10, 1, 4));

        spawnBuilder.spawn(SpawnGroup.UNDERGROUND_WATER_CREATURE, new SpawnSettings.SpawnEntry(EntityType.GLOW_SQUID, 10, 4, 6));

        GenerationSettings.LookupBackedBuilder biomeBuilder =
            new GenerationSettings.LookupBackedBuilder(context.getRegistryLookup(RegistryKeys.PLACED_FEATURE),
        context.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER));

        globalOverworldGeneration(biomeBuilder);



        DefaultBiomeFeatures.addDefaultOres(biomeBuilder);


        // Example of adding custom placed features (replace with your actual keys)
        biomeBuilder.feature(GenerationStep.Feature.LAKES, MiscPlacedFeatures.LAKE_LAVA_SURFACE);
        biomeBuilder.feature(GenerationStep.Feature.LOCAL_MODIFICATIONS, UndergroundPlacedFeatures.AMETHYST_GEODE);
        //biomeBuilder.feature(GenerationStep.Feature.LOCAL_MODIFICATIONS, PlacedFeatures .createEntry(Identifier.of(EntStupidStuff.MOD_ID, "large_pointed_ice")));

        biomeBuilder.feature(GenerationStep.Feature.UNDERGROUND_STRUCTURES, UndergroundPlacedFeatures.MONSTER_ROOM);
        biomeBuilder.feature(GenerationStep.Feature.UNDERGROUND_STRUCTURES, UndergroundPlacedFeatures.MONSTER_ROOM_DEEP);

        // Ores (add your limestone/ice/etc.)
        //biomeBuilder.feature(GenerationStep.Feature.UNDERGROUND_ORES, PlacedFeatures.ORE_COAL_UPPER);
        //biomeBuilder.feature(GenerationStep.Feature.UNDERGROUND_ORES, PlacedFeatures.ORE_COAL_LOWER);

        // Spiked ice clusters
        //biomeBuilder.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, PlacedFeatures.createEntry(Identifier.of(EntStupidStuff.MOD_ID, "spiked_ice_cluster")));
        //biomeBuilder.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, PlacedFeatures.createEntry(Identifier.of(EntStupidStuff.MOD_ID, "pointed_ice")));

        // Springs
        biomeBuilder.feature(GenerationStep.Feature.FLUID_SPRINGS, MiscPlacedFeatures.SPRING_WATER);

        // Vegetation
        biomeBuilder.feature(GenerationStep.Feature.VEGETAL_DECORATION, UndergroundPlacedFeatures.GLOW_LICHEN);
        biomeBuilder.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.PATCH_TALL_GRASS_2);
        biomeBuilder.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.TREES_PLAINS);
        biomeBuilder.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.PATCH_GRASS_PLAIN);
        biomeBuilder.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.BROWN_MUSHROOM_NORMAL);
        biomeBuilder.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.RED_MUSHROOM_NORMAL);
        biomeBuilder.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.PATCH_SUGAR_CANE);
        biomeBuilder.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.PATCH_PUMPKIN);

        // Top freeze
        biomeBuilder.feature(GenerationStep.Feature.TOP_LAYER_MODIFICATION, PlacedFeatures.FREEZE_TOP_LAYER);

        // Biome effects
        BiomeEffects effects = new BiomeEffects.Builder()
                .fogColor(12638463)
                .skyColor(7907327)
                .waterColor(3750089)
                .waterFogColor(329011)
                .moodSound(new BiomeMoodSound(RegistryEntry.of(SoundEvents.AMBIENT_CAVE), 6000, 8, 2.0))
                .music(new MusicSound(SoundEvents.MUSIC_OVERWORLD_DRIPSTONE_CAVES, 12000, 24000, false))
        .build();

        return new Biome.Builder()
                .precipitation(true)
                .temperature(0.10f)
                .downfall(0.4f)
                .effects(effects)
                .spawnSettings(spawnBuilder.build())
                .generationSettings(biomeBuilder.build())
        .build();*/
    }
}
