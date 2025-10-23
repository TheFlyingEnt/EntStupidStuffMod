package net.ent.entstupidstuff.world.biome;

import net.ent.entstupidstuff.EntStupidStuff;
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
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;

public class ModBiomes {
    public static final RegistryKey<Biome> MAPLE_FOREST = RegistryKey.of(RegistryKeys.BIOME,
            Identifier.of(EntStupidStuff.MOD_ID, "maple_forest"));
    public static final RegistryKey<Biome> ICY_CAVES = RegistryKey.of(RegistryKeys.BIOME,
            Identifier.of(EntStupidStuff.MOD_ID, "icy_caves"));

    public static void boostrap(Registerable<Biome> context) {
        //context.register(MAPLE_FOREST, mapleforest(context));
        context.register(ICY_CAVES, icyCaves(context));
        context.register(MAPLE_FOREST, mapleForest(context));
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
