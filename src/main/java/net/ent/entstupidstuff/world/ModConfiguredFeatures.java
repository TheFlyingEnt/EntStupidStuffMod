package net.ent.entstupidstuff.world;

import java.util.List;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.block.BlockFactory;
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
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.structure.rule.BlockMatchRuleTest;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.structure.rule.TagMatchRuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.Pool;
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

public class ModConfiguredFeatures {

	//Registry for Placers

	public static final TrunkPlacerType<ThreexThreeTrunkPlacer> THREE_BY_THREE_TRUNK =
        Registry.register(Registries.TRUNK_PLACER_TYPE, Identifier.of(EntStupidStuff.MOD_ID, "three_by_three_trunk"), new TrunkPlacerType<>(ThreexThreeTrunkPlacer.CODEC));

    public static final FoliagePlacerType<RedwoodFoliagePlacer> REDWOOD_FOLIAGE_PLACER =
    	Registry.register(Registries.FOLIAGE_PLACER_TYPE, Identifier.of(EntStupidStuff.MOD_ID, "redwood_foliage"), new FoliagePlacerType<>(RedwoodFoliagePlacer.CODEC));

    public static final FoliagePlacerType<FirFoliagePlacer> FIR_FOLIAGE_PLACER =
    	Registry.register(Registries.FOLIAGE_PLACER_TYPE, Identifier.of(EntStupidStuff.MOD_ID, "fir_foliage"), new FoliagePlacerType<>(FirFoliagePlacer.CODEC));

    public static final TrunkPlacerType<FirTrunkPlacer> FIR_TRUNK_PLACER =
        Registry.register(Registries.TRUNK_PLACER_TYPE, Identifier.of(EntStupidStuff.MOD_ID, "fir_trunk"), new TrunkPlacerType<FirTrunkPlacer>(FirTrunkPlacer.CODEC));
		
    // Registry for ConfiguredFeatures

    public static final RegistryKey<ConfiguredFeature<?, ?>> MAPLE_KEY = registerKey("maple");
	public static final RegistryKey<ConfiguredFeature<?, ?>> FIR_KEY = registerKey("fir");

	public static final RegistryKey<ConfiguredFeature<?, ?>> ORE_LIMESTONE = registerKey("ore_limestone");
	public static final RegistryKey<ConfiguredFeature<?, ?>> ORE_LIMESTONE_LOWER = registerKey("ore_limestone_lower");
	public static final RegistryKey<ConfiguredFeature<?, ?>> ORE_LIMESTONE_UPPER = registerKey("ore_limestone_upper");
	public static final RegistryKey<ConfiguredFeature<?, ?>> ORE_PACKED_ICE = registerKey("ore_packed_ice");
	public static final RegistryKey<ConfiguredFeature<?, ?>> ORE_PACKED_ICE_LOWER = registerKey("ore_packed_ice_lower");
	public static final RegistryKey<ConfiguredFeature<?, ?>> ORE_SNOW = registerKey("ore_snow");
	public static final RegistryKey<ConfiguredFeature<?, ?>> ORE_SNOW_UPPER = registerKey("ore_snow_upper");

	public static final RegistryKey<ConfiguredFeature<?, ?>> SPIKED_ICE_CLUSTER = registerKey("spiked_ice_cluster");
    public static final RegistryKey<ConfiguredFeature<?, ?>> LARGE_SPIKED_ICE = registerKey("larger_spiked_ice");
    public static final RegistryKey<ConfiguredFeature<?, ?>> SMALL_SPIKED_ICE = registerKey("spiked_ice");

	public static final RegistryKey<ConfiguredFeature<?, ?>> HUGE_BLUE_MUSHROOM_KEY = registerKey("huge_blue_mushroom");


    // Registry for Features

    public static final Feature<DripstoneClusterFeatureConfig> SPIKED_ICE_CLUSTER_FEATURE =
        Registry.register(
            Registries.FEATURE,
            Identifier.of(EntStupidStuff.MOD_ID, "spiked_ice_cluster"),
            new SpikedIceClusterFeature(DripstoneClusterFeatureConfig.CODEC)
        );

    public static final Feature<LargeDripstoneFeatureConfig> LARGER_SPIKED_ICE_FEATURE =
        Registry.register(
            Registries.FEATURE,
            Identifier.of(EntStupidStuff.MOD_ID, "large_spiked_ice"),
            new LargerSpikedIceFeature(LargeDripstoneFeatureConfig.CODEC)
        );

    public static final Feature<SmallDripstoneFeatureConfig> SMALL_SPIKED_ICE_FEATURE =
        Registry.register(
            Registries.FEATURE,
            Identifier.of(EntStupidStuff.MOD_ID, "spiked_ice"),
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

		RuleTest stoneReplacables = new TagMatchRuleTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplacables = new TagMatchRuleTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
        RuleTest netherReplacables = new TagMatchRuleTest(BlockTags.BASE_STONE_NETHER);
        RuleTest endReplacables = new BlockMatchRuleTest(Blocks.END_STONE);

		ConfiguredFeatures.register(context, ORE_LIMESTONE, Feature.ORE, new OreFeatureConfig(List.of(OreFeatureConfig.createTarget(stoneReplacables, BlockFactory.callBlock("limestone").getDefaultState())), 64));
		ConfiguredFeatures.register(context, ORE_LIMESTONE_LOWER, Feature.ORE, new OreFeatureConfig(List.of(OreFeatureConfig.createTarget(stoneReplacables, BlockFactory.callBlock("limestone").getDefaultState())), 64));
		ConfiguredFeatures.register(context, ORE_LIMESTONE_UPPER, Feature.ORE, new OreFeatureConfig(List.of(OreFeatureConfig.createTarget(stoneReplacables, BlockFactory.callBlock("limestone").getDefaultState())), 64));
		ConfiguredFeatures.register(context, ORE_PACKED_ICE, Feature.ORE, new OreFeatureConfig(List.of(OreFeatureConfig.createTarget(stoneReplacables, Blocks.PACKED_ICE.getDefaultState())), 64));
		ConfiguredFeatures.register(context, ORE_PACKED_ICE_LOWER, Feature.ORE, new OreFeatureConfig(List.of(OreFeatureConfig.createTarget(stoneReplacables, Blocks.PACKED_ICE.getDefaultState())), 64));
		ConfiguredFeatures.register(context, ORE_SNOW, Feature.ORE, new OreFeatureConfig(List.of(OreFeatureConfig.createTarget(stoneReplacables, Blocks.SNOW_BLOCK.getDefaultState())), 64));
		ConfiguredFeatures.register(context, ORE_SNOW_UPPER, Feature.ORE, new OreFeatureConfig(List.of(OreFeatureConfig.createTarget(stoneReplacables, Blocks.SNOW_BLOCK.getDefaultState())), 64));

		BlockPredicate blockPredicate = BlockPredicate.matchingBlocks(
			Blocks.OAK_SAPLING,
			Blocks.SPRUCE_SAPLING,
			Blocks.BIRCH_SAPLING,
			Blocks.JUNGLE_SAPLING,
			Blocks.ACACIA_SAPLING,
			Blocks.CHERRY_SAPLING,
			Blocks.DARK_OAK_SAPLING,
			Blocks.MANGROVE_PROPAGULE,
			Blocks.DANDELION,
			Blocks.TORCHFLOWER,
			Blocks.POPPY,
			Blocks.BLUE_ORCHID,
			Blocks.ALLIUM,
			Blocks.AZURE_BLUET,
			Blocks.RED_TULIP,
			Blocks.ORANGE_TULIP,
			Blocks.WHITE_TULIP,
			Blocks.PINK_TULIP,
			Blocks.OXEYE_DAISY,
			Blocks.CORNFLOWER,
			Blocks.WITHER_ROSE,
			Blocks.LILY_OF_THE_VALLEY,
			Blocks.BROWN_MUSHROOM,
			Blocks.RED_MUSHROOM,
			Blocks.WHEAT,
			Blocks.SUGAR_CANE,
			Blocks.ATTACHED_PUMPKIN_STEM,
			Blocks.ATTACHED_MELON_STEM,
			Blocks.PUMPKIN_STEM,
			Blocks.MELON_STEM,
			Blocks.LILY_PAD,
			Blocks.NETHER_WART,
			Blocks.COCOA,
			Blocks.CARROTS,
			Blocks.POTATOES,
			Blocks.CHORUS_PLANT,
			Blocks.CHORUS_FLOWER,
			Blocks.TORCHFLOWER_CROP,
			Blocks.PITCHER_CROP,
			Blocks.BEETROOTS,
			Blocks.SWEET_BERRY_BUSH,
			Blocks.WARPED_FUNGUS,
			Blocks.CRIMSON_FUNGUS,
			Blocks.WEEPING_VINES,
			Blocks.WEEPING_VINES_PLANT,
			Blocks.TWISTING_VINES,
			Blocks.TWISTING_VINES_PLANT,
			Blocks.CAVE_VINES,
			Blocks.CAVE_VINES_PLANT,
			Blocks.SPORE_BLOSSOM,
			Blocks.AZALEA,
			Blocks.FLOWERING_AZALEA,
			Blocks.MOSS_CARPET,
			Blocks.PINK_PETALS,
			Blocks.BIG_DRIPLEAF,
			Blocks.BIG_DRIPLEAF_STEM,
			Blocks.SMALL_DRIPLEAF
		);



		//List.of(OreFeatureConfig.createTarget(, BlockFactoryUpt.callBlock("limestone").getDefaultState()))
		//BlockStateProvider.of(BlockFactory.callBlock("blue_mushroom").getDefaultState()

		/*ConfiguredFeatures.register(
			context,
			HUGE_BLUE_MUSHROOM_KEY,
			Feature.HUGE_RED_MUSHROOM,
			new HugeMushroomFeatureConfig(
				BlockStateProvider.of(BlockFactory.callBlock("blue_mushroom_block").getDefaultState().with(MushroomBlock.DOWN, Boolean.valueOf(false))),
				BlockStateProvider.of(
					Blocks.MUSHROOM_STEM.getDefaultState().with(MushroomBlock.UP, Boolean.valueOf(false)).with(MushroomBlock.DOWN, Boolean.valueOf(false))
				),
				2
			)
		);*/

		ConfiguredFeatures.register(
			context,
			HUGE_BLUE_MUSHROOM_KEY,
			Feature.HUGE_FUNGUS,
			new HugeFungusFeatureConfig(
				Blocks.GRASS_BLOCK.getDefaultState(),
				Blocks.MUSHROOM_STEM.getDefaultState(),
				BlockFactory.callBlock("blue_mushroom_block").getDefaultState(),
				Blocks.SHROOMLIGHT.getDefaultState(),
				blockPredicate,
				true
			)
		);

		ConfiguredFeatures.register(
			context,
			FIR_KEY,
			Feature.TREE,
			new TreeFeatureConfig.Builder(
					BlockStateProvider.of(BlockFactory.callBlock("fir_log")),
					new FirTrunkPlacer(7, 3, 5),
					BlockStateProvider.of(BlockFactory.callBlock("fir_leaves")),
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
				BlockStateProvider.of(BlockFactory.callBlock("maple_log")),
				new CherryTrunkPlacer(
					7,
					1,
					0,
					new WeightedListIntProvider(
						Pool.<IntProvider>builder().add(ConstantIntProvider.create(1), 1).add(ConstantIntProvider.create(2), 1).add(ConstantIntProvider.create(3), 1).build()
					),
					UniformIntProvider.create(2, 4),
					UniformIntProvider.create(-4, -3),
					UniformIntProvider.create(-1, 0)
				),
				BlockStateProvider.of(BlockFactory.callBlock("maple_leaves")),
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

        ConfiguredFeatures.register(
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

        ConfiguredFeatures.register(
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

        ConfiguredFeatures.register(
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
