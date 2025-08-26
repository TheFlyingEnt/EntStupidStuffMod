package net.ent.entstupidstuff.datagen;

import net.ent.entstupidstuff.world.ConfiguredFeaturesFactory;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeEffects;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;

public class ModBiomes {
    public static final RegistryKey<Biome> MAPLE_FOREST = RegistryKey.of(RegistryKeys.BIOME,
            Identifier.of("entstupidstuff", "maple_forest"));

    public static void bootstrap(Registerable<Biome> context) {
        context.register(MAPLE_FOREST, mapleForest(context));
    }

    private static Biome mapleForest(Registerable<Biome> context) {
        // Create biome effects (sky, grass, water colors, etc.)
        BiomeEffects effects = new BiomeEffects.Builder()
                .skyColor(0x88CCFF) // soft blue sky
                .waterColor(0x3F76E4)
                .waterFogColor(0x050533)
                .grassColor(0x91BD59)
                .foliageColor(0xE06666) // reddish foliage tint
                .fogColor(0xC0D8FF)
                .build();

        // Spawn settings (animals, mobs)
        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
        spawnSettings.creatureSpawnProbability(0.08f);
        spawnSettings.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.FOX, 6, 2, 4));
        spawnSettings.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.RABBIT, 8, 2, 3));

        // Generation settings (trees, grass, flowers)
        GenerationSettings.LookupBackedBuilder generationSettings =
                new GenerationSettings.LookupBackedBuilder(context.getRegistryLookup(RegistryKeys.PLACED_FEATURE),
                        context.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER));

        // Add vanilla features
        DefaultBiomeFeatures.addLandCarvers(generationSettings);
        //DefaultBiomeFeatures.addDefaultUndergroundStructures(generationSettings);
        DefaultBiomeFeatures.addDungeons(generationSettings);
        DefaultBiomeFeatures.addMineables(generationSettings);
        DefaultBiomeFeatures.addSprings(generationSettings);
        DefaultBiomeFeatures.addFrozenTopLayer(generationSettings);

        // Add your custom maple tree
        generationSettings.feature(GenerationStep.Feature.VEGETAL_DECORATION,
                ModPlacedFeatures.MAPLE_TREE_PLACED_KEY);

        return new Biome.Builder()
                .precipitation(true)
                .temperature(0.7f)
                .downfall(0.8f)
                .effects(effects)
                .spawnSettings(spawnSettings.build())
                .generationSettings(generationSettings.build())
                .build();
    }
}