package net.ent.entstupidstuff.world;

import java.util.List;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.block.BlockFactory;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.intprovider.ClampedNormalIntProvider;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.blockpredicate.BlockPredicate;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.PlacedFeatures;
import net.minecraft.world.gen.feature.VegetationPlacedFeatures;
import net.minecraft.world.gen.placementmodifier.BiomePlacementModifier;
import net.minecraft.world.gen.placementmodifier.CountPlacementModifier;
import net.minecraft.world.gen.placementmodifier.EnvironmentScanPlacementModifier;
import net.minecraft.world.gen.placementmodifier.HeightRangePlacementModifier;
import net.minecraft.world.gen.placementmodifier.PlacementModifier;
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

    public static final RegistryKey<PlacedFeature> HUGE_BLUE_MUSHROOM_PLACED_KEY = RegistryKey.of(
            RegistryKeys.PLACED_FEATURE,
            Identifier.of(EntStupidStuff.MOD_ID, "huge_blue_mushroom_placed")
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

    public static final RegistryKey<PlacedFeature> THALASSITE_ORE_PLACE_KEY =
        RegistryKey.of(RegistryKeys.PLACED_FEATURE, Identifier.of(EntStupidStuff.MOD_ID, "thalassite_ore")
    );

    public static final RegistryKey<PlacedFeature> SHROOMIUM_FLOOR_PLACED = registerKey("shroomium_floor_placed");
    public static final RegistryKey<PlacedFeature> SHROOMIUM_FLOOR_VEGETATION_PLACED = registerKey("shroomium_floor_vegetation_placed");
    public static final RegistryKey<PlacedFeature> MUD_LAYER_PLACED = registerKey("mud_layer_placed");
    public static final RegistryKey<PlacedFeature> HUGE_BLUE_MUSHROOM_PLACED = registerKey("huge_blue_mushroom_placed");
    public static final RegistryKey<PlacedFeature> MUSHROOM_BED_PATCH_PLACED = registerKey("mushroom_bed_patch_placed");
    public static final RegistryKey<PlacedFeature> FUNGAL_SPORE_BLOSSOM_PLACED = registerKey("fungal_spore_blossom_placed");

    public static final RegistryKey<PlacedFeature> CRYSTAL_SPIKE_PLACED = registerKey("crystal_spike");

    // # Bootstrap

    public static void bootstrap(Registerable<PlacedFeature> context) {

        // # Tree

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

        // # Icy Biome

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

        // # Sunken Sea

        context.register(
            THALASSITE_ORE_PLACE_KEY,
            new PlacedFeature(
                context.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE).getOrThrow(ModConfiguredFeatures.ORE_THALASSITE),
                List.of(
                    CountPlacementModifier.of(10), // "count": 2
                    SquarePlacementModifier.of(), // "in_square"
                    HeightRangePlacementModifier.uniform(YOffset.aboveBottom(0), YOffset.fixed(256)),
                    BiomePlacementModifier.of() // "biome"
                )
            )
        );

        // # Underground Mushroom Biome

        context.register(
            CRYSTAL_SPIKE_PLACED,
            new PlacedFeature(
                context.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE).getOrThrow(ModConfiguredFeatures.CRYSTAL_SPIKES),
                List.of(
                    CountPlacementModifier.of(UniformIntProvider.create(10, 48)),
                    SquarePlacementModifier.of(),
                    HeightRangePlacementModifier.uniform(YOffset.aboveBottom(0), YOffset.fixed(256)),
                    BiomePlacementModifier.of()
                )
            )
        );

        register(context, SHROOMIUM_FLOOR_VEGETATION_PLACED, 
            context.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE).getOrThrow(ModConfiguredFeatures.SHROOMIUM_FLOOR_VEGETATION_KEY),
            List.of()); // Empty list of placement modifiers

        // Mud layer (placed first, so it's underneath)
        register(context, MUD_LAYER_PLACED, 
            context.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE).getOrThrow(ModConfiguredFeatures.MUD_LAYER_KEY),
            CountPlacementModifier.of(150),
            SquarePlacementModifier.of(),
            PlacedFeatures.BOTTOM_TO_120_RANGE,
            EnvironmentScanPlacementModifier.of(Direction.DOWN, BlockPredicate.solid(), BlockPredicate.IS_AIR, 12),
            RandomOffsetPlacementModifier.vertically(ConstantIntProvider.create(-1)),
            BiomePlacementModifier.of());

        // Shroomium floor (placed after mud)
        register(context, SHROOMIUM_FLOOR_PLACED, 
            context.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE).getOrThrow(ModConfiguredFeatures.SHROOMIUM_FLOOR_KEY),
            CountPlacementModifier.of(125),
            SquarePlacementModifier.of(),
            PlacedFeatures.BOTTOM_TO_120_RANGE,
            EnvironmentScanPlacementModifier.of(Direction.DOWN, BlockPredicate.solid(), BlockPredicate.IS_AIR, 12),
            RandomOffsetPlacementModifier.vertically(ConstantIntProvider.create(1)),
            BiomePlacementModifier.of());

        // Huge blue mushrooms
        register(context, HUGE_BLUE_MUSHROOM_PLACED, 
            context.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE).getOrThrow(ModConfiguredFeatures.HUGE_BLUE_MUSHROOM_KEY),
            CountPlacementModifier.of(20),
            SquarePlacementModifier.of(),
            PlacedFeatures.BOTTOM_TO_120_RANGE,
            EnvironmentScanPlacementModifier.of(Direction.DOWN, BlockPredicate.solid(), BlockPredicate.IS_AIR, 12),
            RandomOffsetPlacementModifier.vertically(ConstantIntProvider.create(1)),
            BiomePlacementModifier.of());

        // Additional mushroom bed patches
        register(context, MUSHROOM_BED_PATCH_PLACED, 
            context.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE).getOrThrow(ModConfiguredFeatures.SHROOMIUM_FLOOR_VEGETATION_KEY),
            CountPlacementModifier.of(50),
            SquarePlacementModifier.of(),
            PlacedFeatures.BOTTOM_TO_120_RANGE,
            EnvironmentScanPlacementModifier.of(Direction.DOWN, BlockPredicate.solid(), BlockPredicate.IS_AIR, 12),
            BiomePlacementModifier.of());

        // Fungal spore blossoms (ceiling decoration)
        register(context, FUNGAL_SPORE_BLOSSOM_PLACED, 
            context.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE).getOrThrow(ModConfiguredFeatures.MUSHROOM_SPORE_KEY),
            CountPlacementModifier.of(10),
            SquarePlacementModifier.of(),
            PlacedFeatures.BOTTOM_TO_120_RANGE,
            EnvironmentScanPlacementModifier.of(Direction.UP, BlockPredicate.solid(), BlockPredicate.IS_AIR, 12),
            RandomOffsetPlacementModifier.vertically(ConstantIntProvider.create(-1)),
            BiomePlacementModifier.of());


    }








    // # Register

    public static RegistryKey<PlacedFeature> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.PLACED_FEATURE, Identifier.of(EntStupidStuff.MOD_ID, name));
    }

    private static void register(Registerable<PlacedFeature> context, RegistryKey<PlacedFeature> key,
                                RegistryEntry<ConfiguredFeature<?, ?>> configuration,
                                List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }

    private static void register(Registerable<PlacedFeature> context, RegistryKey<PlacedFeature> key,
                                RegistryEntry<ConfiguredFeature<?, ?>> configuration,
                                PlacementModifier... modifiers) {
        register(context, key, configuration, List.of(modifiers));
    }

    
}
