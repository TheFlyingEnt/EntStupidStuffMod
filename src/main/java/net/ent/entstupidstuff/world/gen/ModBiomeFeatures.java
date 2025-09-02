package net.ent.entstupidstuff.world.gen;

import net.ent.entstupidstuff.world.ModPlacedFeatures;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.gen.GenerationStep;

public class ModBiomeFeatures  {

    public static void register() {
        // Limestone ores
        BiomeModifications.addFeature(
            BiomeSelectors.foundInOverworld(),
            GenerationStep.Feature.UNDERGROUND_ORES,
            ModPlacedFeatures.ORE_LIMESTONE_UPPER_PLACED_KEY
        );
        BiomeModifications.addFeature(
            BiomeSelectors.foundInOverworld(),
            GenerationStep.Feature.UNDERGROUND_ORES,
            ModPlacedFeatures.ORE_LIMESTONE_PLACED_KEY
        );
        BiomeModifications.addFeature(
            BiomeSelectors.foundInOverworld(),
            GenerationStep.Feature.UNDERGROUND_ORES,
            ModPlacedFeatures.ORE_LIMESTONE_LOWER_PLACED_KEY
        );

        // Packed ice ores
        BiomeModifications.addFeature(
            BiomeSelectors.foundInOverworld(),
            GenerationStep.Feature.UNDERGROUND_ORES,
            ModPlacedFeatures.ORE_PACKED_ICE_LOWER_PLACED_KEY
        );
        BiomeModifications.addFeature(
            BiomeSelectors.foundInOverworld(),
            GenerationStep.Feature.UNDERGROUND_ORES,
            ModPlacedFeatures.ORE_PACKED_ICE_PLACED_KEY
        );

        // Snow ores
        BiomeModifications.addFeature(
            BiomeSelectors.foundInOverworld(),
            GenerationStep.Feature.UNDERGROUND_ORES,
            ModPlacedFeatures.ORE_SNOW_PLACED_KEY
        );
        BiomeModifications.addFeature(
            BiomeSelectors.foundInOverworld(),
            GenerationStep.Feature.UNDERGROUND_ORES,
            ModPlacedFeatures.ORE_SNOW_UPPER_PLACED_KEY
        );

        // Spiked ice features
        BiomeModifications.addFeature(
            BiomeSelectors.foundInOverworld(),
            GenerationStep.Feature.UNDERGROUND_DECORATION,
            ModPlacedFeatures.SPIKED_ICE_PLACED_KEY
        );
        BiomeModifications.addFeature(
            BiomeSelectors.foundInOverworld(),
            GenerationStep.Feature.UNDERGROUND_DECORATION,
            ModPlacedFeatures.SPIKED_ICE_CLUSTER_PLACED_KEY
        );
        BiomeModifications.addFeature(
            BiomeSelectors.foundInOverworld(),
            GenerationStep.Feature.UNDERGROUND_DECORATION,
            ModPlacedFeatures.LARGE_SPIKED_ICE_PLACED_KEY
        );
    }
    
}
