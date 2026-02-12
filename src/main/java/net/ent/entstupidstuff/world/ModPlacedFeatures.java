package net.ent.entstupidstuff.world;

import java.util.List;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.block.BlockFactory;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.ClampedNormalInt;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.EnvironmentScanPlacement;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.NoiseThresholdCountPlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.placement.RandomOffsetPlacement;

public class ModPlacedFeatures {
    public static final ResourceKey<PlacedFeature> MAPLE_TREE_PLACED_KEY = registerKey("maple_tree_placed");

    public static final ResourceKey<PlacedFeature> MAPLE_FANCY_TREE_PLACED_KEY = registerKey("maple_fancy_tree_placed");

    public static final ResourceKey<PlacedFeature> FIR_TREE_PLACED_KEY = registerKey("fir_tree_placed");

    public static final ResourceKey<PlacedFeature> HUGE_BLUE_MUSHROOM_PLACED_KEY = registerKey("huge_blue_mushroom_placed");

    public static final ResourceKey<PlacedFeature> ORE_LIMESTONE_PLACED_KEY = registerKey("ore_limestone");

    public static final ResourceKey<PlacedFeature> ORE_LIMESTONE_LOWER_PLACED_KEY = registerKey("ore_limestone_lower");

    public static final ResourceKey<PlacedFeature> ORE_LIMESTONE_UPPER_PLACED_KEY = registerKey("ore_limestone_upper");

    public static final ResourceKey<PlacedFeature> ORE_PACKED_ICE_PLACED_KEY = registerKey("ore_packed_ice");

    public static final ResourceKey<PlacedFeature> ORE_PACKED_ICE_LOWER_PLACED_KEY = registerKey("ore_packed_ice_lower");

    public static final ResourceKey<PlacedFeature> ORE_SNOW_PLACED_KEY = registerKey("ore_snow");

    public static final ResourceKey<PlacedFeature> ORE_SNOW_UPPER_PLACED_KEY = registerKey("ore_snow_upper");

    public static final ResourceKey<PlacedFeature> SPIKED_ICE_PLACED_KEY = registerKey("spiked_ice");

    public static final ResourceKey<PlacedFeature> LARGE_SPIKED_ICE_PLACED_KEY = registerKey("large_spiked_ice");

    public static final ResourceKey<PlacedFeature> SPIKED_ICE_CLUSTER_PLACED_KEY = registerKey("spiked_ice_cluster");

    public static final ResourceKey<PlacedFeature> THALASSITE_ORE_PLACE_KEY = registerKey("thalassite_ore");

    public static final ResourceKey<PlacedFeature> DATE_PALM_PLACED = registerKey("date_palm_placed");

    public static final ResourceKey<PlacedFeature> DESERT_WILLOW_PLACED = registerKey("desert_willow_placed");

    public static final ResourceKey<PlacedFeature> SHROOMIUM_FLOOR_PLACED = registerKey("shroomium_floor_placed");
    public static final ResourceKey<PlacedFeature> SHROOMIUM_FLOOR_VEGETATION_PLACED = registerKey("shroomium_floor_vegetation_placed");
    public static final ResourceKey<PlacedFeature> MUD_LAYER_PLACED = registerKey("mud_layer_placed");
    public static final ResourceKey<PlacedFeature> HUGE_BLUE_MUSHROOM_PLACED = registerKey("huge_blue_mushroom_placed");
    public static final ResourceKey<PlacedFeature> AZURE_FLOWER_BED_PATCH_PLACED = registerKey("azure_flower_bed_patch_placed");
    public static final ResourceKey<PlacedFeature> FUNGAL_SPORE_BLOSSOM_PLACED = registerKey("fungal_spore_blossom_placed");
    public static final ResourceKey<PlacedFeature> MUSHROOM_AURA_BLOSSOM_PLACED = registerKey("mushroom_aura_block_placed");
    public static final ResourceKey<PlacedFeature> SILKWORM_VINE_PLACED = registerKey("silk_worm_vine_placed");

    public static final ResourceKey<PlacedFeature> CRYSTAL_SPIKE_PLACED = registerKey("crystal_spike");

    public static final ResourceKey<PlacedFeature> ORANGE_PETALS_BED_PATCH_PLACED = registerKey("orange_petals_bed_patch_placed");

    // # Bootstrap

    public static void bootstrap(BootstrapContext<PlacedFeature> context) {

        // # Tree

        context.register(MAPLE_TREE_PLACED_KEY, new PlacedFeature(
                context.lookup(Registries.CONFIGURED_FEATURE).getOrThrow(ModConfiguredFeatures.MAPLE_TREE_KEY),
                VegetationPlacements.treePlacement(
                        PlacementUtils.countExtra(5, 0.1f, 1), // base count, chance, extra
                        BlockFactory.callBlock("maple_sapling")
                )
        ));

        context.register(MAPLE_FANCY_TREE_PLACED_KEY, new PlacedFeature(
                context.lookup(Registries.CONFIGURED_FEATURE).getOrThrow(ModConfiguredFeatures.MAPLE_FANCY_TREE_KEY),
                VegetationPlacements.treePlacement(
                        PlacementUtils.countExtra(5, 0.1f, 1), // base count, chance, extra
                        BlockFactory.callBlock("maple_sapling")
                )
        ));

        context.register(FIR_TREE_PLACED_KEY, new PlacedFeature(
                context.lookup(Registries.CONFIGURED_FEATURE).getOrThrow(ModConfiguredFeatures.FIR_TREE_KEY),
                VegetationPlacements.treePlacement(
                        PlacementUtils.countExtra(5, 0.1f, 1), // base count, chance, extra
                        BlockFactory.callBlock("fir_sapling")
                )
        ));

        context.register(DATE_PALM_PLACED, new PlacedFeature(
                context.lookup(Registries.CONFIGURED_FEATURE)
                        .getOrThrow(ModConfiguredFeatures.DATE_PALM_TREE_KEY),
                List.of(
                        CountPlacement.of(1),
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                        BiomeFilter.biome()
                )
        ));

        context.register(DESERT_WILLOW_PLACED, new PlacedFeature(
                context.lookup(Registries.CONFIGURED_FEATURE)
                        .getOrThrow(ModConfiguredFeatures.DESERT_WILLOW_TREE_KEY),
                List.of(
                        CountPlacement.of(2),
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                        BiomeFilter.biome()
                )
        ));

        // # Icy Biome

        context.register(
            ORE_LIMESTONE_PLACED_KEY,
            new PlacedFeature(
                context.lookup(Registries.CONFIGURED_FEATURE).getOrThrow(ModConfiguredFeatures.ORE_LIMESTONE),
                List.of(
                    CountPlacement.of(2), // "count": 2
                    InSquarePlacement.spread(), // "in_square"
                    HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(0)), // uniform range
                    BiomeFilter.biome() // "biome"
                )
            )
        );


        context.register(
            ORE_LIMESTONE_LOWER_PLACED_KEY,
            new PlacedFeature(
                context.lookup(Registries.CONFIGURED_FEATURE).getOrThrow(ModConfiguredFeatures.ORE_LIMESTONE_LOWER),
                List.of(
                    CountPlacement.of(2), // "count": 2
                    InSquarePlacement.spread(), // "in_square"
                    HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(60)), // uniform range
                    BiomeFilter.biome() // "biome"
                )
            )
        );

        context.register(
            ORE_LIMESTONE_UPPER_PLACED_KEY,
            new PlacedFeature(
                context.lookup(Registries.CONFIGURED_FEATURE).getOrThrow(ModConfiguredFeatures.ORE_LIMESTONE_UPPER),
                List.of(
                    CountPlacement.of(2), // "count": 2
                    InSquarePlacement.spread(), // "in_square"
                    HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(60)), // uniform range
                    BiomeFilter.biome() // "biome"
                )
            )
        );

        context.register(
        ORE_PACKED_ICE_PLACED_KEY,
        new PlacedFeature(
            context.lookup(Registries.CONFIGURED_FEATURE).getOrThrow(ModConfiguredFeatures.ORE_PACKED_ICE),
            List.of(
                CountPlacement.of(2), // count: 2
                InSquarePlacement.spread(),
                HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(0), VerticalAnchor.absolute(0)), // min=above_bottom(0), max=0
                BiomeFilter.biome()
            )
        ));

        context.register(
        ORE_PACKED_ICE_LOWER_PLACED_KEY,
        new PlacedFeature(
            context.lookup(Registries.CONFIGURED_FEATURE).getOrThrow(ModConfiguredFeatures.ORE_PACKED_ICE_LOWER),
            List.of(
                CountPlacement.of(2), // count: 2
                InSquarePlacement.spread(),
                HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(0), VerticalAnchor.absolute(60)), // min=above_bottom(0), max=0
                BiomeFilter.biome()
            )
        ));

        context.register(
        ORE_SNOW_PLACED_KEY,
        new PlacedFeature(
            context.lookup(Registries.CONFIGURED_FEATURE).getOrThrow(ModConfiguredFeatures.ORE_SNOW),
            List.of(
                CountPlacement.of(2),
                InSquarePlacement.spread(),
                HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(0), VerticalAnchor.absolute(0)),
                BiomeFilter.biome()
                )
            )
        );

        context.register(
        ORE_SNOW_UPPER_PLACED_KEY,
        new PlacedFeature(
            context.lookup(Registries.CONFIGURED_FEATURE).getOrThrow(ModConfiguredFeatures.ORE_SNOW_UPPER),
            List.of(
                CountPlacement.of(2),
                InSquarePlacement.spread(),
                HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(0), VerticalAnchor.absolute(0)),
                BiomeFilter.biome()
                )
            )
        );

        context.register(
            SPIKED_ICE_PLACED_KEY,
            new PlacedFeature(
                context.lookup(Registries.CONFIGURED_FEATURE).getOrThrow(ModConfiguredFeatures.SMALL_SPIKED_ICE),
                List.of(
                    CountPlacement.of(UniformInt.of(192, 256)), // original "count" uniform 192-256
                    InSquarePlacement.spread(),
                    HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(0), VerticalAnchor.absolute(256)),
                    CountPlacement.of(UniformInt.of(1, 5)), // second count
                    RandomOffsetPlacement.of(
                        ClampedNormalInt.of(0, 3, -10, 10), // xz spread
                        ClampedNormalInt.of(0, 0.6f, -2, 2) // y spread
                    ),
                    BiomeFilter.biome()
                )
            )
        );

        context.register(
            SPIKED_ICE_CLUSTER_PLACED_KEY,
            new PlacedFeature(
                context.lookup(Registries.CONFIGURED_FEATURE).getOrThrow(ModConfiguredFeatures.SPIKED_ICE_CLUSTER),
                List.of(
                    CountPlacement.of(UniformInt.of(10, 48)),
                    InSquarePlacement.spread(),
                    HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(0), VerticalAnchor.absolute(256)),
                    BiomeFilter.biome()
                )
            )
        );

        context.register(
            LARGE_SPIKED_ICE_PLACED_KEY,
            new PlacedFeature(
                context.lookup(Registries.CONFIGURED_FEATURE).getOrThrow(ModConfiguredFeatures.LARGE_SPIKED_ICE),
                List.of(
                    CountPlacement.of(UniformInt.of(10, 48)),
                    InSquarePlacement.spread(),
                    HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(0), VerticalAnchor.absolute(256)),
                    BiomeFilter.biome()
                )
            )
        );

        // # Sunken Sea

        context.register(
            THALASSITE_ORE_PLACE_KEY,
            new PlacedFeature(
                context.lookup(Registries.CONFIGURED_FEATURE).getOrThrow(ModConfiguredFeatures.ORE_THALASSITE),
                List.of(
                    CountPlacement.of(10), // "count": 2
                    InSquarePlacement.spread(), // "in_square"
                    HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(0), VerticalAnchor.absolute(256)),
                    BiomeFilter.biome() // "biome"
                )
            )
        );

        // # Underground Mushroom Biome

        context.register(
            CRYSTAL_SPIKE_PLACED,
            new PlacedFeature(
                context.lookup(Registries.CONFIGURED_FEATURE).getOrThrow(ModConfiguredFeatures.CRYSTAL_SPIKES),
                List.of(
                    CountPlacement.of(UniformInt.of(10, 48)),
                    InSquarePlacement.spread(),
                    HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(0), VerticalAnchor.absolute(256)),
                    BiomeFilter.biome()
                )
            )
        );

        register(context, SHROOMIUM_FLOOR_VEGETATION_PLACED, 
            context.lookup(Registries.CONFIGURED_FEATURE).getOrThrow(ModConfiguredFeatures.SHROOMIUM_FLOOR_VEGETATION_KEY),
            List.of()); // Empty list of placement modifiers

        // Mud layer (placed first, so it's underneath)
        register(context, MUD_LAYER_PLACED, 
            context.lookup(Registries.CONFIGURED_FEATURE).getOrThrow(ModConfiguredFeatures.MUD_LAYER_KEY),
            CountPlacement.of(150),
            InSquarePlacement.spread(),
            PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT,
            EnvironmentScanPlacement.scanningFor(Direction.DOWN, BlockPredicate.solid(), BlockPredicate.ONLY_IN_AIR_PREDICATE, 12),
            RandomOffsetPlacement.vertical(ConstantInt.of(-1)),
            BiomeFilter.biome());

        // Shroomium floor (placed after mud)
        register(context, SHROOMIUM_FLOOR_PLACED, 
            context.lookup(Registries.CONFIGURED_FEATURE).getOrThrow(ModConfiguredFeatures.SHROOMIUM_FLOOR_KEY),
            CountPlacement.of(125),
            InSquarePlacement.spread(),
            PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT,
            EnvironmentScanPlacement.scanningFor(Direction.DOWN, BlockPredicate.solid(), BlockPredicate.ONLY_IN_AIR_PREDICATE, 12),
            RandomOffsetPlacement.vertical(ConstantInt.of(1)),
            BiomeFilter.biome());

        // Huge blue mushrooms
        register(context, HUGE_BLUE_MUSHROOM_PLACED, 
            context.lookup(Registries.CONFIGURED_FEATURE).getOrThrow(ModConfiguredFeatures.HUGE_BLUE_MUSHROOM_KEY),
            CountPlacement.of(20),
            InSquarePlacement.spread(),
            PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT,
            EnvironmentScanPlacement.scanningFor(Direction.DOWN, BlockPredicate.solid(), BlockPredicate.ONLY_IN_AIR_PREDICATE, 12),
            RandomOffsetPlacement.vertical(ConstantInt.of(1)),
            BiomeFilter.biome());

        // Additional mushroom bed patches
        register(context, AZURE_FLOWER_BED_PATCH_PLACED, 
            context.lookup(Registries.CONFIGURED_FEATURE).getOrThrow(ModConfiguredFeatures.SHROOMIUM_FLOOR_VEGETATION_KEY),
            CountPlacement.of(50),
            InSquarePlacement.spread(),
            PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT,
            EnvironmentScanPlacement.scanningFor(Direction.DOWN, BlockPredicate.solid(), BlockPredicate.ONLY_IN_AIR_PREDICATE, 12),
            BiomeFilter.biome());

        // Fungal spore blossoms (ceiling decoration)
        register(context, FUNGAL_SPORE_BLOSSOM_PLACED, 
            context.lookup(Registries.CONFIGURED_FEATURE).getOrThrow(ModConfiguredFeatures.MUSHROOM_SPORE_KEY),
            CountPlacement.of(10),
            InSquarePlacement.spread(),
            PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT,
            EnvironmentScanPlacement.scanningFor(Direction.UP, BlockPredicate.solid(), BlockPredicate.ONLY_IN_AIR_PREDICATE, 12),
            RandomOffsetPlacement.vertical(ConstantInt.of(-1)),
            BiomeFilter.biome());

        register(context, MUSHROOM_AURA_BLOSSOM_PLACED, 
            context.lookup(Registries.CONFIGURED_FEATURE).getOrThrow(ModConfiguredFeatures.MUSHROOM_AURA_BLOSSOM_KEY),
            CountPlacement.of(24),
            InSquarePlacement.spread(),
            PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT,
            EnvironmentScanPlacement.scanningFor(Direction.DOWN, BlockPredicate.solid(), BlockPredicate.ONLY_IN_AIR_PREDICATE, 12),
            RandomOffsetPlacement.vertical(ConstantInt.of(1)),
        BiomeFilter.biome());

        register(context, SILKWORM_VINE_PLACED, //Done
            context.lookup(Registries.CONFIGURED_FEATURE).getOrThrow(ModConfiguredFeatures.SILKWORM_VINE_KEY),
            CountPlacement.of(125),
            InSquarePlacement.spread(),
            PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT,
            EnvironmentScanPlacement.scanningFor(Direction.UP, BlockPredicate.solid(), BlockPredicate.ONLY_IN_AIR_PREDICATE, 12),
            RandomOffsetPlacement.vertical(ConstantInt.of(-1)),
        BiomeFilter.biome());

        register(context, ORANGE_PETALS_BED_PATCH_PLACED, 
            context.lookup(Registries.CONFIGURED_FEATURE).getOrThrow(ModConfiguredFeatures.MAPLE_FLOOR_VEGETATION_KEY),
            NoiseThresholdCountPlacement.of(-0.8, 5, 10),
			InSquarePlacement.spread(),
			PlacementUtils.HEIGHTMAP,
			BiomeFilter.biome());


    }








    // # Register

    public static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, name));
    }

    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key,
                                Holder<ConfiguredFeature<?, ?>> configuration,
                                List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }

    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key,
                                Holder<ConfiguredFeature<?, ?>> configuration,
                                PlacementModifier... modifiers) {
        register(context, key, configuration, List.of(modifiers));
    }

    
}
