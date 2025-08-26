package net.ent.entstupidstuff.datagen;

import com.mojang.datafixers.util.Pair;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.tag.BiomeTags;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.biome.source.util.MultiNoiseUtil;
import net.minecraft.world.gen.GenerationStep;

import java.util.function.Consumer;

public class ModWorldGeneration {
    public static void bootstrap(Registerable<Biome> context) {
        BiomeModifications.addSpawn(biome -> biome.equals(ModBiomes.MAPLE_FOREST),
                SpawnGroup.CREATURE,
                EntityType.FOX,
                6, 2, 4);

        BiomeModifications.addFeature(biome -> biome.equals(ModBiomes.MAPLE_FOREST),
                GenerationStep.Feature.VEGETAL_DECORATION,
                ModPlacedFeatures.MAPLE_TREE_PLACED_KEY);
    }
    
}
