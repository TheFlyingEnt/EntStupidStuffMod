package net.ent.entstupidstuff.world;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.block.BlockFactoryUpt;
import net.ent.entstupidstuff.world.feature.LargerSpikedIceFeature;
import net.ent.entstupidstuff.world.feature.SmallSpikedIceFeature;
import net.ent.entstupidstuff.world.feature.SpikedIceClusterFeature;
import net.ent.entstupidstuff.world.tree.FirFoliagePlacer;
import net.ent.entstupidstuff.world.tree.FirTrunkPlacer;
import net.ent.entstupidstuff.world.tree.RedwoodFoliagePlacer;
import net.ent.entstupidstuff.world.tree.ThreexThreeTrunkPlacer;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DataPool;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.floatprovider.ClampedNormalFloatProvider;
import net.minecraft.util.math.floatprovider.UniformFloatProvider;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.math.intprovider.WeightedListIntProvider;
import net.minecraft.world.gen.blockpredicate.BlockPredicate;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.CherryFoliagePlacer;
import net.minecraft.world.gen.foliage.FoliagePlacerType;
import net.minecraft.world.gen.placementmodifier.EnvironmentScanPlacementModifier;
import net.minecraft.world.gen.placementmodifier.RandomOffsetPlacementModifier;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.trunk.CherryTrunkPlacer;
import net.minecraft.world.gen.trunk.TrunkPlacerType;

public class ConfiguredFeaturesFactory {

    // Reg
    public static final TrunkPlacerType<ThreexThreeTrunkPlacer> THREE_BY_THREE_TRUNK =
        Registry.register(Registries.TRUNK_PLACER_TYPE,
            Identifier.of("entstupidstuff", "three_by_three_trunk"),
            new TrunkPlacerType<>(ThreexThreeTrunkPlacer.CODEC));

    public static final FoliagePlacerType<RedwoodFoliagePlacer> REDWOOD_FOLIAGE_PLACER =
    Registry.register(Registries.FOLIAGE_PLACER_TYPE,
        Identifier.of("entstupidstuff", "redwood_foliage"),
        new FoliagePlacerType<>(RedwoodFoliagePlacer.CODEC));

    public static final FoliagePlacerType<FirFoliagePlacer> FIR_FOLIAGE_PLACER =
    Registry.register(Registries.FOLIAGE_PLACER_TYPE,
        Identifier.of("entstupidstuff", "fir_foliage"),
        new FoliagePlacerType<>(FirFoliagePlacer.CODEC));

    public static final TrunkPlacerType<FirTrunkPlacer> FIR_TRUNK_PLACER =
        Registry.register(Registries.TRUNK_PLACER_TYPE,
            Identifier.of("entstupidstuff", "fir_trunk"),
            new TrunkPlacerType<FirTrunkPlacer>(FirTrunkPlacer.CODEC));

    // Registry for Config Feature

    public static final RegistryKey<ConfiguredFeature<?, ?>> MAPLE_KEY = registerKey("maple");
    public static final RegistryKey<ConfiguredFeature<?, ?>> SPIKED_ICE_CLUSTER = registerKey("spiked_ice_cluster");
    public static final RegistryKey<ConfiguredFeature<?, ?>> LARGE_SPIKED_ICE = registerKey("larger_pointed_ice");
    public static final RegistryKey<ConfiguredFeature<?, ?>> SMALL_SPIKED_ICE = registerKey("pointed_ice");
    public static final RegistryKey<ConfiguredFeature<?, ?>> FIR_KEY = registerKey("fir");

    // Registry for Features

    public static final Feature<DripstoneClusterFeatureConfig> SPIKED_ICE_CLUSTER_FEATURE =
        Registry.register(
            Registries.FEATURE,
            Identifier.of("entstupidstuff", "spiked_ice_cluster"),
            new SpikedIceClusterFeature(DripstoneClusterFeatureConfig.CODEC)
        );

    public static final Feature<LargeDripstoneFeatureConfig> LARGER_SPIKED_ICE_FEATURE =
        Registry.register(
            Registries.FEATURE,
            Identifier.of("entstupidstuff", "large_pointed_ice"),
            new LargerSpikedIceFeature(LargeDripstoneFeatureConfig.CODEC)
        );

    public static final Feature<SmallDripstoneFeatureConfig> SMALL_SPIKED_ICE_FEATURE =
        Registry.register(
            Registries.FEATURE,
            Identifier.of("entstupidstuff", "pointed_ice"),
            new SmallSpikedIceFeature(SmallDripstoneFeatureConfig.CODEC)
        );


    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> context) {

        /*register(context, MAPLE_KEY, Feature.TREE, new TreeFeatureConfig.Builder(
            BlockStateProvider.of(BlockFactoryUpt.callBlock("maple_log")),
            new StraightTrunkPlacer(5, 4, 3),

            BlockStateProvider.of(BlockFactoryUpt.callBlock("maple_leaves")),
            new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(1), 2),

            new TwoLayersFeatureSize(1, 0, 2)).build()
        );*/

        /*register(context, MAPLE_KEY, Feature.TREE, new TreeFeatureConfig.Builder(
            BlockStateProvider.of(BlockFactoryUpt.callBlock("maple_log")),
            new LargeOakTrunkPlacer(3, 11, 0),
    
            BlockStateProvider.of(BlockFactoryUpt.callBlock("maple_leaves")),
            new LargeOakFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(4), 4),
    
            new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(4))).build()
        );*/

        /*register(context, MAPLE_KEY, Feature.TREE, new TreeFeatureConfig.Builder(
            BlockStateProvider.of(BlockFactoryUpt.callBlock("maple_log")),
            new GiantTrunkPlacer(10, 2, 19),
    
            BlockStateProvider.of(BlockFactoryUpt.callBlock("maple_leaves")),
            new JungleFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 2),
    
            new TwoLayersFeatureSize(1, 1, 2)).build()
        );*/

		ConfiguredFeatures.register(
			context,
			FIR_KEY,
			Feature.TREE,
			new TreeFeatureConfig.Builder(
					BlockStateProvider.of(BlockFactoryUpt.callBlock("fir_log")),
					new FirTrunkPlacer(7, 3, 5),
					BlockStateProvider.of(BlockFactoryUpt.callBlock("fir_leaves")),
                    //new FirFoliagePlacer(UniformIntProvider.create(2, 3), UniformIntProvider.create(0, 2), UniformIntProvider.create(1, 2)),
					new FirFoliagePlacer(UniformIntProvider.create(2, 3), UniformIntProvider.create(0, 2)),
					new TwoLayersFeatureSize(2, 0, 2)
				)
				.ignoreVines()
				.build()
		);

        ConfiguredFeatures.register(
			context,
			MAPLE_KEY,
			Feature.TREE,
			new TreeFeatureConfig.Builder(
				BlockStateProvider.of(BlockFactoryUpt.callBlock("maple_log")),
				new CherryTrunkPlacer(
					7,
					1,
					0,
					new WeightedListIntProvider(
						DataPool.<IntProvider>builder().add(ConstantIntProvider.create(1), 1).add(ConstantIntProvider.create(2), 1).add(ConstantIntProvider.create(3), 1).build()
					),
					UniformIntProvider.create(2, 4),
					UniformIntProvider.create(-4, -3),
					UniformIntProvider.create(-1, 0)
				),
				BlockStateProvider.of(BlockFactoryUpt.callBlock("maple_leaves")),
				new CherryFoliagePlacer(ConstantIntProvider.create(4), ConstantIntProvider.create(0), ConstantIntProvider.create(5), 0.25F, 0.5F, 0.16666667F, 0.33333334F),
				new TwoLayersFeatureSize(1, 0, 2)
			)
            .ignoreVines()
			.build()
		);

        /*ConfiguredFeatures.register(
			context,
			MAPLE_KEY,
			Feature.TREE,
			new TreeFeatureConfig.Builder(
				BlockStateProvider.of(BlockFactoryUpt.callBlock("maple_log")),
                ////baseHeight, int firstRandomHeight, int secondRandomHeight
				new ThreexThreeTrunkPlacer(24, 2, 24),
				BlockStateProvider.of(BlockFactoryUpt.callBlock("maple_leaves")),
				//new MegaPineFoliagePlacer(ConstantIntProvider.create(0), ConstantIntProvider.create(0), UniformIntProvider.create(13, 17)),
                new RedwoodFoliagePlacer(
                    ConstantIntProvider.create(5),  // radius
                    ConstantIntProvider.create(0),  // offset
                    UniformIntProvider.create(4,6)  // crown height
                ),
                new TwoLayersFeatureSize(0, 0, 4)   // foliage only top 4 layers
			)
			//.decorators(ImmutableList.of(new AlterGroundTreeDecorator(BlockStateProvider.of(Blocks.PODZOL))))
			.build()
		);*/

        register(
			context,
			SPIKED_ICE_CLUSTER,
			SPIKED_ICE_CLUSTER_FEATURE,
			new DripstoneClusterFeatureConfig(
				12,
				UniformIntProvider.create(3, 6),
				UniformIntProvider.create(2, 8),
				1,
				3,
				UniformIntProvider.create(2, 4),
				UniformFloatProvider.create(0.3F, 0.7F),
				ClampedNormalFloatProvider.create(0.1F, 0.3F, 0.1F, 0.9F),
				0.1F,
				3,
				8
			)
		);

        register(
			context,
			LARGE_SPIKED_ICE,
			LARGER_SPIKED_ICE_FEATURE,
			new LargeDripstoneFeatureConfig(
				30,
				UniformIntProvider.create(3, 19),
				UniformFloatProvider.create(0.4F, 2.0F),
				0.33F,
				UniformFloatProvider.create(0.3F, 0.9F),
				UniformFloatProvider.create(0.4F, 1.0F),
				UniformFloatProvider.create(0.0F, 0.3F),
				4,
				0.6F
			)
		);

        register(
			context,
			SMALL_SPIKED_ICE,
			Feature.SIMPLE_RANDOM_SELECTOR,
			new SimpleRandomFeatureConfig(
				RegistryEntryList.of(
					PlacedFeatures.createEntry(
						SMALL_SPIKED_ICE_FEATURE,
						new SmallDripstoneFeatureConfig(0.2F, 0.7F, 0.5F, 0.5F),
						EnvironmentScanPlacementModifier.of(Direction.DOWN, BlockPredicate.solid(), BlockPredicate.IS_AIR_OR_WATER, 12),
						RandomOffsetPlacementModifier.vertically(ConstantIntProvider.create(1))
					),
					PlacedFeatures.createEntry(
						Feature.POINTED_DRIPSTONE,
						new SmallDripstoneFeatureConfig(0.2F, 0.7F, 0.5F, 0.5F),
						EnvironmentScanPlacementModifier.of(Direction.UP, BlockPredicate.solid(), BlockPredicate.IS_AIR_OR_WATER, 12),
						RandomOffsetPlacementModifier.vertically(ConstantIntProvider.create(-1))
					)
				)
			)
		);

    }

    public static RegistryKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, Identifier.of(EntStupidStuff.MOD_ID, name));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<ConfiguredFeature<?, ?>> context, RegistryKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }


}
