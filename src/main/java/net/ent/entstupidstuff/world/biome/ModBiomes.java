package net.ent.entstupidstuff.world.biome;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.registry.EntityFactory;
import net.ent.entstupidstuff.world.ModPlacedFeatures;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.MusicSound;
import net.minecraft.sound.MusicType;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeEffects;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.carver.ConfiguredCarvers;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import net.minecraft.world.gen.feature.OceanPlacedFeatures;

public class ModBiomes {
    public static final RegistryKey<Biome> MAPLE_FOREST = RegistryKey.of(RegistryKeys.BIOME,
            Identifier.of(EntStupidStuff.MOD_ID, "maple_forest"));
    public static final RegistryKey<Biome> ICY_CAVES = RegistryKey.of(RegistryKeys.BIOME,
            Identifier.of(EntStupidStuff.MOD_ID, "icy_caves"));
    public static final RegistryKey<Biome> UNDERGROUND_BLUE_MUSHROOM = RegistryKey.of(RegistryKeys.BIOME,
            Identifier.of(EntStupidStuff.MOD_ID, "underground_blue_mushroom"));
    public static final RegistryKey<Biome> SUNKEN_SEA = RegistryKey.of(RegistryKeys.BIOME,
            Identifier.of(EntStupidStuff.MOD_ID, "sunken_sea"));

    public static void boostrap(Registerable<Biome> context) {
        //context.register(MAPLE_FOREST, mapleforest(context));
        context.register(ICY_CAVES, icyCaves(context));
        context.register(MAPLE_FOREST, mapleForest(context));
        context.register(UNDERGROUND_BLUE_MUSHROOM, undergroundBlueMushroom(context));
        context.register(SUNKEN_SEA, createSunkenSea(context));
    }

    //biomeBuilder.feature(GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.MAPLE_TREE_PLACED_KEY);

    private static void addBasicFeatures(GenerationSettings.LookupBackedBuilder generationSettings) {
		DefaultBiomeFeatures.addLandCarvers(generationSettings);
		DefaultBiomeFeatures.addAmethystGeodes(generationSettings);
		DefaultBiomeFeatures.addDungeons(generationSettings);
		DefaultBiomeFeatures.addMineables(generationSettings);
		DefaultBiomeFeatures.addSprings(generationSettings);
		DefaultBiomeFeatures.addFrozenTopLayer(generationSettings);
	}

    private static Biome createSunkenSea(Registerable<Biome> context) {

        // === Spawn Settings ===
        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();

        spawnSettings.spawn(SpawnGroup.WATER_AMBIENT, 10,
                new SpawnSettings.SpawnEntry(EntityType.COD, 4, 8));
        spawnSettings.spawn(SpawnGroup.WATER_AMBIENT, 8,
                new SpawnSettings.SpawnEntry(EntityType.SALMON, 2, 5));

        spawnSettings.spawn(SpawnGroup.MONSTER, 20, 
                new SpawnSettings.SpawnEntry(EntityType.DROWNED, 1, 3));
        spawnSettings.spawn(SpawnGroup.MONSTER, 5,
                new SpawnSettings.SpawnEntry(EntityType.GUARDIAN, 1, 2));

        // === Generation Settings ===
        GenerationSettings.LookupBackedBuilder generation =
                new GenerationSettings.LookupBackedBuilder(
                        context.getRegistryLookup(RegistryKeys.PLACED_FEATURE),
                        context.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER)
                );

        // Terrain
        generation.feature(GenerationStep.Feature.UNDERGROUND_ORES, ModPlacedFeatures.THALASSITE_ORE_PLACE_KEY);

        // Coral & vegetation
        //generation.feature(GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.SUNKEN_CORAL);
        //generation.feature(GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.SEAWEED_PATCH);

        addBasicFeatures(generation);
		DefaultBiomeFeatures.addDefaultOres(generation);
		DefaultBiomeFeatures.addDefaultDisks(generation);
		DefaultBiomeFeatures.addWaterBiomeOakTrees(generation);
		DefaultBiomeFeatures.addDefaultFlowers(generation);
		DefaultBiomeFeatures.addDefaultGrass(generation);
		DefaultBiomeFeatures.addDefaultMushrooms(generation);
		DefaultBiomeFeatures.addDefaultVegetation(generation, true);

		generation
			.feature(GenerationStep.Feature.VEGETAL_DECORATION, OceanPlacedFeatures.WARM_OCEAN_VEGETATION)
			.feature(GenerationStep.Feature.VEGETAL_DECORATION, OceanPlacedFeatures.SEAGRASS_WARM)
			.feature(GenerationStep.Feature.VEGETAL_DECORATION, OceanPlacedFeatures.SEA_PICKLE);

        DefaultBiomeFeatures.addLessKelp(generation);

        generation.carver(ConfiguredCarvers.CAVE);
        generation.carver(ConfiguredCarvers.CAVE_EXTRA_UNDERGROUND);

        /*generation.feature(
            GenerationStep.Feature.TOP_LAYER_MODIFICATION, // runs after carving
            ModPlacedFeatures.SUNKEN_WATER_PLACED
        );*/

        /*generation.feature(
            GenerationStep.Feature.LAKES,
            ModPlacedFeatures.SUNKEN_WATER_PLACED
        );*/
        

        // === Biome Effects ===
        BiomeEffects effects = new BiomeEffects.Builder()
                .waterColor(0x1B4F72)
                .waterFogColor(0x0A2A43)
                .fogColor(0x081A2B)
                .skyColor(0x000000) // underground
                .build();

        return new Biome.Builder()
                .precipitation(false)
                .temperature(0.5f)
                .downfall(0.0f)
                .effects(effects)
                .spawnSettings(spawnSettings.build())
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
    public static Biome mapleForest(Registerable<Biome> context) {

        GenerationSettings.LookupBackedBuilder biomeBuilder =
            new GenerationSettings.LookupBackedBuilder(context.getRegistryLookup(RegistryKeys.PLACED_FEATURE),
        context.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER));

        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
        spawnSettings.spawn(SpawnGroup.CREATURE, 5, new SpawnSettings.SpawnEntry(EntityType.FOX, 2, 4))
			.spawn(SpawnGroup.CREATURE, 6, new SpawnSettings.SpawnEntry(EntityType.RABBIT, 2, 3))
			.spawn(SpawnGroup.CREATURE, 5, new SpawnSettings.SpawnEntry(EntityType.SHEEP, 2, 4));
        DefaultBiomeFeatures.addBatsAndMonsters(spawnSettings);
		addBasicFeatures(biomeBuilder);
		DefaultBiomeFeatures.addPlainsTallGrass(biomeBuilder);
		DefaultBiomeFeatures.addDefaultOres(biomeBuilder);
		DefaultBiomeFeatures.addDefaultDisks(biomeBuilder);
        

        DefaultBiomeFeatures.addEmeraldOre(biomeBuilder);
		DefaultBiomeFeatures.addInfestedStone(biomeBuilder);
		MusicSound musicSound = MusicType.createIngameMusic(SoundEvents.MUSIC_OVERWORLD_CHERRY_GROVE);

        //biomeBuilder.feature(GenerationStep.Feature.FLUID_SPRINGS, ModPlacedFeatures.); 
        biomeBuilder.feature(GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.MAPLE_TREE_PLACED_KEY);

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

    public static Biome undergroundBlueMushroom(Registerable<Biome> context) {
        SpawnSettings.Builder builder = new SpawnSettings.Builder();
        DefaultBiomeFeatures.addBatsAndMonsters(builder);

        //Mushrom Creatures
        builder.spawn(SpawnGroup.MONSTER, 150, new SpawnSettings.SpawnEntry(EntityFactory.FUNGAL_SKELETON, 1, 4));
        builder.spawn(SpawnGroup.MONSTER, 150, new SpawnSettings.SpawnEntry(EntityFactory.ZOMBIE_FUNGAL, 1, 4));
        builder.spawn(SpawnGroup.MONSTER, 100, new SpawnSettings.SpawnEntry(EntityFactory.SPOREPER, 1, 2));

        GenerationSettings.LookupBackedBuilder biomeBuilder =
            new GenerationSettings.LookupBackedBuilder(context.getRegistryLookup(RegistryKeys.PLACED_FEATURE),
        context.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER));

        addBasicFeatures(biomeBuilder);
        DefaultBiomeFeatures.addDefaultOres(biomeBuilder);

        //Add Mud Biome Features
        biomeBuilder.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, 
            ModPlacedFeatures.MUD_LAYER_PLACED);

        // Then add shroomium floor on top
        biomeBuilder.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, 
            ModPlacedFeatures.SHROOMIUM_FLOOR_PLACED);

        // Add huge blue mushrooms
        biomeBuilder.feature(GenerationStep.Feature.VEGETAL_DECORATION, 
            ModPlacedFeatures.HUGE_BLUE_MUSHROOM_PLACED);

        // Add extra mushroom bed patches
        biomeBuilder.feature(GenerationStep.Feature.VEGETAL_DECORATION, 
            ModPlacedFeatures.MUSHROOM_BED_PATCH_PLACED);

        // Add fungal spore blossoms
        biomeBuilder.feature(GenerationStep.Feature.VEGETAL_DECORATION, 
            ModPlacedFeatures.FUNGAL_SPORE_BLOSSOM_PLACED);

        biomeBuilder.feature(
            GenerationStep.Feature.UNDERGROUND_DECORATION,
            ModPlacedFeatures.CRYSTAL_SPIKE_PLACED
        );

            

        MusicSound musicSound = MusicType.createIngameMusic(SoundEvents.MUSIC_OVERWORLD_LUSH_CAVES);

        BiomeEffects effects = new BiomeEffects.Builder()
                .fogColor(12638463)
                .skyColor(7907327)
                .waterColor(4375259)
                .waterFogColor(2710405)
                //.moodSound(new BiomeMoodSound(RegistryEntry.of(SoundEvents.AMBIENT_CAVE), 6000, 8, 2.0))
                .music(musicSound)
        .build();


        return new Biome.Builder()
            .precipitation(true)
            .temperature(0.4f)
            .downfall(0.4f)
            .effects(effects)
            .spawnSettings(builder.build())
            .generationSettings(biomeBuilder.build())
        .build();


    }


    public static Biome icyCaves(Registerable<Biome> context) {
        SpawnSettings.Builder spawnBuilder = new SpawnSettings.Builder();
		DefaultBiomeFeatures.addDripstoneCaveMobs(spawnBuilder);
		GenerationSettings.LookupBackedBuilder biomeBuilder =
            new GenerationSettings.LookupBackedBuilder(context.getRegistryLookup(RegistryKeys.PLACED_FEATURE),
        context.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER));

        addBasicFeatures(biomeBuilder);
        DefaultBiomeFeatures.addPlainsTallGrass(biomeBuilder);
        DefaultBiomeFeatures.addDefaultOres(biomeBuilder, true);
        biomeBuilder.feature(GenerationStep.Feature.UNDERGROUND_ORES, ModPlacedFeatures.ORE_LIMESTONE_LOWER_PLACED_KEY);
        biomeBuilder.feature(GenerationStep.Feature.UNDERGROUND_ORES, ModPlacedFeatures.ORE_LIMESTONE_PLACED_KEY);
        biomeBuilder.feature(GenerationStep.Feature.UNDERGROUND_ORES, ModPlacedFeatures.ORE_LIMESTONE_UPPER_PLACED_KEY);
        biomeBuilder.feature(GenerationStep.Feature.UNDERGROUND_ORES, ModPlacedFeatures.ORE_PACKED_ICE_LOWER_PLACED_KEY);
        biomeBuilder.feature(GenerationStep.Feature.UNDERGROUND_ORES, ModPlacedFeatures.ORE_PACKED_ICE_PLACED_KEY);
        biomeBuilder.feature(GenerationStep.Feature.UNDERGROUND_ORES, ModPlacedFeatures.ORE_SNOW_PLACED_KEY);
        biomeBuilder.feature(GenerationStep.Feature.UNDERGROUND_ORES, ModPlacedFeatures.ORE_SNOW_UPPER_PLACED_KEY);
		DefaultBiomeFeatures.addDefaultDisks(biomeBuilder);
		DefaultBiomeFeatures.addPlainsFeatures(biomeBuilder);
		DefaultBiomeFeatures.addDefaultMushrooms(biomeBuilder);
		DefaultBiomeFeatures.addDefaultVegetation(biomeBuilder, false);
        biomeBuilder.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ModPlacedFeatures.LARGE_SPIKED_ICE_PLACED_KEY);
        biomeBuilder.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ModPlacedFeatures.SPIKED_ICE_CLUSTER_PLACED_KEY);
        biomeBuilder.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ModPlacedFeatures.SPIKED_ICE_PLACED_KEY);
        
        MusicSound musicSound = MusicType.createIngameMusic(SoundEvents.MUSIC_OVERWORLD_DRIPSTONE_CAVES);
		BiomeEffects effects = new BiomeEffects.Builder()
                .fogColor(12638463)
                .skyColor(7907327)
                .waterColor(3750089)
                .waterFogColor(329011)
                //.moodSound(new BiomeMoodSound(RegistryEntry.of(SoundEvents.AMBIENT_CAVE), 6000, 8, 2.0))
                .music(musicSound)
        .build();

        return new Biome.Builder()
                .precipitation(true)
                .temperature(0.10f)
                .downfall(0.4f)
                .effects(effects)
                .spawnSettings(spawnBuilder.build())
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
