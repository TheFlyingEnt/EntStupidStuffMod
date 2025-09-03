package net.ent.entstupidstuff.world;

import java.util.List;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.block.BlockFactory;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.ClampedNormalIntProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.PlacedFeatures;
import net.minecraft.world.gen.feature.VegetationPlacedFeatures;
import net.minecraft.world.gen.placementmodifier.BiomePlacementModifier;
import net.minecraft.world.gen.placementmodifier.CountPlacementModifier;
import net.minecraft.world.gen.placementmodifier.HeightRangePlacementModifier;
import net.minecraft.world.gen.placementmodifier.RandomOffsetPlacementModifier;
import net.minecraft.world.gen.placementmodifier.SquarePlacementModifier;

public class ModPlacedFeatures {
    public static final RegistryKey<PlacedFeature> MAPLE_TREE_PLACED_KEY = RegistryKey.of(
            RegistryKeys.PLACED_FEATURE,
            Identifier.of(EntStupidStuff.MOD_ID, "maple_tree_placed")
    );

    public static final RegistryKey<PlacedFeature> FIR_TREE_PLACED_KEY = RegistryKey.of(
            RegistryKeys.PLACED_FEATURE,
            Identifier.of(EntStupidStuff.MOD_ID, "fir_tree_placed")
    );

    public static final RegistryKey<PlacedFeature> ORE_LIMESTONE_PLACED_KEY = RegistryKey.of(
            RegistryKeys.PLACED_FEATURE,
            Identifier.of(EntStupidStuff.MOD_ID, "ore_limestone")
    );

    public static final RegistryKey<PlacedFeature> ORE_LIMESTONE_LOWER_PLACED_KEY = RegistryKey.of(
            RegistryKeys.PLACED_FEATURE,
            Identifier.of(EntStupidStuff.MOD_ID, "ore_limestone_lower")
    );

    public static final RegistryKey<PlacedFeature> ORE_LIMESTONE_UPPER_PLACED_KEY = RegistryKey.of(
            RegistryKeys.PLACED_FEATURE,
            Identifier.of(EntStupidStuff.MOD_ID, "ore_limestone_upper")
    );

    public static final RegistryKey<PlacedFeature> ORE_PACKED_ICE_PLACED_KEY = RegistryKey.of(
            RegistryKeys.PLACED_FEATURE,
            Identifier.of(EntStupidStuff.MOD_ID, "ore_packed_ice")
    );

    public static final RegistryKey<PlacedFeature> ORE_PACKED_ICE_LOWER_PLACED_KEY = RegistryKey.of(
            RegistryKeys.PLACED_FEATURE,
            Identifier.of(EntStupidStuff.MOD_ID, "ore_packed_ice_lower")
    );

    public static final RegistryKey<PlacedFeature> ORE_SNOW_PLACED_KEY = RegistryKey.of(
            RegistryKeys.PLACED_FEATURE,
            Identifier.of(EntStupidStuff.MOD_ID, "ore_snow")
    );

    public static final RegistryKey<PlacedFeature> ORE_SNOW_UPPER_PLACED_KEY = RegistryKey.of(
            RegistryKeys.PLACED_FEATURE,
            Identifier.of(EntStupidStuff.MOD_ID, "ore_snow_upper")
    );

    public static final RegistryKey<PlacedFeature> SPIKED_ICE_PLACED_KEY = RegistryKey.of(
            RegistryKeys.PLACED_FEATURE,
            Identifier.of(EntStupidStuff.MOD_ID, "spiked_ice")
    );

    public static final RegistryKey<PlacedFeature> LARGE_SPIKED_ICE_PLACED_KEY = RegistryKey.of(
            RegistryKeys.PLACED_FEATURE,
            Identifier.of(EntStupidStuff.MOD_ID, "large_spiked_ice")
    );

    public static final RegistryKey<PlacedFeature> SPIKED_ICE_CLUSTER_PLACED_KEY = RegistryKey.of(
            RegistryKeys.PLACED_FEATURE,
            Identifier.of(EntStupidStuff.MOD_ID, "spiked_ice_cluster")
    );

    public static void bootstrap(Registerable<PlacedFeature> context) {
        context.register(MAPLE_TREE_PLACED_KEY, new PlacedFeature(
                context.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE).getOrThrow(ModConfiguredFeatures.MAPLE_KEY),
                VegetationPlacedFeatures.treeModifiersWithWouldSurvive(
                        PlacedFeatures.createCountExtraModifier(5, 0.1f, 1), // base count, chance, extra
                        BlockFactory.callBlock("maple_sapling")
                )
        ));

        context.register(FIR_TREE_PLACED_KEY, new PlacedFeature(
                context.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE).getOrThrow(ModConfiguredFeatures.FIR_KEY),
                VegetationPlacedFeatures.treeModifiersWithWouldSurvive(
                        PlacedFeatures.createCountExtraModifier(5, 0.1f, 1), // base count, chance, extra
                        BlockFactory.callBlock("fir_sapling")
                )
        ));

        context.register(
            ORE_LIMESTONE_PLACED_KEY,
            new PlacedFeature(
                context.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE).getOrThrow(ModConfiguredFeatures.ORE_LIMESTONE),
                List.of(
                    CountPlacementModifier.of(2), // "count": 2
                    SquarePlacementModifier.of(), // "in_square"
                    HeightRangePlacementModifier.uniform(YOffset.fixed(0), YOffset.fixed(0)), // uniform range
                    BiomePlacementModifier.of() // "biome"
                )
            )
        );


        context.register(
            ORE_LIMESTONE_LOWER_PLACED_KEY,
            new PlacedFeature(
                context.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE).getOrThrow(ModConfiguredFeatures.ORE_LIMESTONE_LOWER),
                List.of(
                    CountPlacementModifier.of(2), // "count": 2
                    SquarePlacementModifier.of(), // "in_square"
                    HeightRangePlacementModifier.uniform(YOffset.fixed(0), YOffset.fixed(60)), // uniform range
                    BiomePlacementModifier.of() // "biome"
                )
            )
        );

        context.register(
            ORE_LIMESTONE_UPPER_PLACED_KEY,
            new PlacedFeature(
                context.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE).getOrThrow(ModConfiguredFeatures.ORE_LIMESTONE_UPPER),
                List.of(
                    CountPlacementModifier.of(2), // "count": 2
                    SquarePlacementModifier.of(), // "in_square"
                    HeightRangePlacementModifier.uniform(YOffset.fixed(0), YOffset.fixed(60)), // uniform range
                    BiomePlacementModifier.of() // "biome"
                )
            )
        );

        context.register(
        ORE_PACKED_ICE_PLACED_KEY,
        new PlacedFeature(
            context.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE).getOrThrow(ModConfiguredFeatures.ORE_PACKED_ICE),
            List.of(
                CountPlacementModifier.of(2), // count: 2
                SquarePlacementModifier.of(),
                HeightRangePlacementModifier.uniform(YOffset.aboveBottom(0), YOffset.fixed(0)), // min=above_bottom(0), max=0
                BiomePlacementModifier.of()
            )
        ));

        context.register(
        ORE_PACKED_ICE_LOWER_PLACED_KEY,
        new PlacedFeature(
            context.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE).getOrThrow(ModConfiguredFeatures.ORE_PACKED_ICE_LOWER),
            List.of(
                CountPlacementModifier.of(2), // count: 2
                SquarePlacementModifier.of(),
                HeightRangePlacementModifier.uniform(YOffset.aboveBottom(0), YOffset.fixed(60)), // min=above_bottom(0), max=0
                BiomePlacementModifier.of()
            )
        ));

        context.register(
        ORE_SNOW_PLACED_KEY,
        new PlacedFeature(
            context.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE).getOrThrow(ModConfiguredFeatures.ORE_SNOW),
            List.of(
                CountPlacementModifier.of(2),
                SquarePlacementModifier.of(),
                HeightRangePlacementModifier.uniform(YOffset.aboveBottom(0), YOffset.fixed(0)),
                BiomePlacementModifier.of()
                )
            )
        );

        context.register(
        ORE_SNOW_UPPER_PLACED_KEY,
        new PlacedFeature(
            context.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE).getOrThrow(ModConfiguredFeatures.ORE_SNOW_UPPER),
            List.of(
                CountPlacementModifier.of(2),
                SquarePlacementModifier.of(),
                HeightRangePlacementModifier.uniform(YOffset.aboveBottom(0), YOffset.fixed(0)),
                BiomePlacementModifier.of()
                )
            )
        );

        context.register(
            SPIKED_ICE_PLACED_KEY,
            new PlacedFeature(
                context.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE).getOrThrow(ModConfiguredFeatures.SMALL_SPIKED_ICE),
                List.of(
                    CountPlacementModifier.of(UniformIntProvider.create(192, 256)), // original "count" uniform 192-256
                    SquarePlacementModifier.of(),
                    HeightRangePlacementModifier.uniform(YOffset.aboveBottom(0), YOffset.fixed(256)),
                    CountPlacementModifier.of(UniformIntProvider.create(1, 5)), // second count
                    RandomOffsetPlacementModifier.of(
                        ClampedNormalIntProvider.of(0, 3, -10, 10), // xz spread
                        ClampedNormalIntProvider.of(0, 0.6f, -2, 2) // y spread
                    ),
                    BiomePlacementModifier.of()
                )
            )
        );

        context.register(
            SPIKED_ICE_CLUSTER_PLACED_KEY,
            new PlacedFeature(
                context.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE).getOrThrow(ModConfiguredFeatures.SPIKED_ICE_CLUSTER),
                List.of(
                    CountPlacementModifier.of(UniformIntProvider.create(10, 48)),
                    SquarePlacementModifier.of(),
                    HeightRangePlacementModifier.uniform(YOffset.aboveBottom(0), YOffset.fixed(256)),
                    BiomePlacementModifier.of()
                )
            )
        );

        context.register(
            LARGE_SPIKED_ICE_PLACED_KEY,
            new PlacedFeature(
                context.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE).getOrThrow(ModConfiguredFeatures.LARGE_SPIKED_ICE),
                List.of(
                    CountPlacementModifier.of(UniformIntProvider.create(10, 48)),
                    SquarePlacementModifier.of(),
                    HeightRangePlacementModifier.uniform(YOffset.aboveBottom(0), YOffset.fixed(256)),
                    BiomePlacementModifier.of()
                )
            )
        );


    }
}
