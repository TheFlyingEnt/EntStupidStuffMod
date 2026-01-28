package net.ent.entstupidstuff.world;

import java.util.List;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.block.BlockFactory;
import net.ent.entstupidstuff.item.ModTags;
import net.ent.entstupidstuff.world.feature.CrystalSpikeFeature;
import net.ent.entstupidstuff.world.feature.CrystalSpikeFeatureConfig;
import net.ent.entstupidstuff.world.feature.LargerSpikedIceFeature;
import net.ent.entstupidstuff.world.feature.SmallSpikedIceFeature;
import net.ent.entstupidstuff.world.feature.SpikedIceClusterFeature;
import net.ent.entstupidstuff.world.tree.FirFoliagePlacer;
import net.ent.entstupidstuff.world.tree.FirTrunkPlacer;
import net.ent.entstupidstuff.world.tree.PalmFoliagePlacer;
import net.ent.entstupidstuff.world.tree.RedwoodFoliagePlacer;
import net.ent.entstupidstuff.world.tree.ThreexThreeTrunkPlacer;
import net.ent.entstupidstuff.world.tree.UmbrellaPalmFoliagePlacer;
import net.ent.entstupidstuff.world.tree.WillowFoliagePlacer;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.random.WeightedList;
import net.minecraft.util.valueproviders.ClampedNormalFloat;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.UniformFloat;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.util.valueproviders.WeightedListInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerBedBlock;
import net.minecraft.world.level.block.HugeMushroomBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.HugeFungusConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.DiskConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.DripstoneClusterConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.HugeMushroomFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.LargeDripstoneConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.PointedDripstoneConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleRandomFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.VegetationPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.CherryFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.RuleBasedBlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.CherryTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.minecraft.world.level.levelgen.placement.CaveSurface;
import net.minecraft.world.level.levelgen.placement.EnvironmentScanPlacement;
import net.minecraft.world.level.levelgen.placement.RandomOffsetPlacement;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

public class ModConfiguredFeatures {

	// # Places

	public static final TrunkPlacerType<ThreexThreeTrunkPlacer> THREE_BY_THREE_TRUNK =
        Registry.register(BuiltInRegistries.TRUNK_PLACER_TYPE, ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "three_by_three_trunk"), new TrunkPlacerType<>(ThreexThreeTrunkPlacer.CODEC));

    public static final FoliagePlacerType<RedwoodFoliagePlacer> REDWOOD_FOLIAGE_PLACER =
    	Registry.register(BuiltInRegistries.FOLIAGE_PLACER_TYPE, ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "redwood_foliage"), new FoliagePlacerType<>(RedwoodFoliagePlacer.CODEC));

    public static final FoliagePlacerType<FirFoliagePlacer> FIR_FOLIAGE_PLACER =
    	Registry.register(BuiltInRegistries.FOLIAGE_PLACER_TYPE, ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "fir_foliage"), new FoliagePlacerType<>(FirFoliagePlacer.CODEC));

    public static final TrunkPlacerType<FirTrunkPlacer> FIR_TRUNK_PLACER =
        Registry.register(BuiltInRegistries.TRUNK_PLACER_TYPE, ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "fir_trunk"), new TrunkPlacerType<FirTrunkPlacer>(FirTrunkPlacer.CODEC));

	public static final FoliagePlacerType<PalmFoliagePlacer> PALM_FOLIAGE_PLACE =
        Registry.register(BuiltInRegistries.FOLIAGE_PLACER_TYPE, ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "palm_foliage"), new FoliagePlacerType<>(PalmFoliagePlacer.CODEC));

	public static final FoliagePlacerType<UmbrellaPalmFoliagePlacer> UMBRELLA_PALM_FOLIAGE_PLACE =
        Registry.register(BuiltInRegistries.FOLIAGE_PLACER_TYPE, ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "umbrella_palm_foliage"), new FoliagePlacerType<>(UmbrellaPalmFoliagePlacer.CODEC));

	public static final FoliagePlacerType<WillowFoliagePlacer> WILLOW_FOLIAGE_PLACE =
        Registry.register(BuiltInRegistries.FOLIAGE_PLACER_TYPE, ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "willow"), new FoliagePlacerType<>(WillowFoliagePlacer.CODEC));

    // # ConfiguredFeatures

    public static final ResourceKey<ConfiguredFeature<?, ?>> MAPLE_KEY = registerKey("maple");
	public static final ResourceKey<ConfiguredFeature<?, ?>> FIR_KEY = registerKey("fir");

	public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_LIMESTONE = registerKey("ore_limestone");
	public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_LIMESTONE_LOWER = registerKey("ore_limestone_lower");
	public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_LIMESTONE_UPPER = registerKey("ore_limestone_upper");
	public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_PACKED_ICE = registerKey("ore_packed_ice");
	public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_PACKED_ICE_LOWER = registerKey("ore_packed_ice_lower");
	public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_SNOW = registerKey("ore_snow");
	public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_SNOW_UPPER = registerKey("ore_snow_upper");

	public static final ResourceKey<ConfiguredFeature<?, ?>> SPIKED_ICE_CLUSTER = registerKey("spiked_ice_cluster");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LARGE_SPIKED_ICE = registerKey("larger_spiked_ice");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SMALL_SPIKED_ICE = registerKey("spiked_ice");

	 public static final ResourceKey<ConfiguredFeature<?, ?>> DATE_PALM =
            ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "date_palm"));

    public static final ResourceKey<ConfiguredFeature<?, ?>> DESERT_WILLOW =
            ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "desert_willow"));

	//Blue Mushrom Biome

	public static final ResourceKey<ConfiguredFeature<?, ?>> HUGE_BLUE_MUSHROOM_KEY = registerKey("huge_blue_mushroom");
	public static final ResourceKey<ConfiguredFeature<?, ?>> SHROOMIUM_FLOOR_KEY = registerKey("shroomium_floor");
	public static final ResourceKey<ConfiguredFeature<?, ?>> MUD_LAYER_KEY = registerKey("mud_layer");
	public static final ResourceKey<ConfiguredFeature<?, ?>> SHROOMIUM_FLOOR_VEGETATION_KEY  = registerKey("shroomium_floor_vegetation");
	public static final ResourceKey<ConfiguredFeature<?, ?>> MUSHROOM_SPORE_KEY  = registerKey("mushroom_spore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MUSHROOM_AURA_BLOSSOM_KEY  = registerKey("mushroom_aura_block_key");

	public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_THALASSITE = registerKey("ore_thalassite");
	public static final ResourceKey<ConfiguredFeature<?, ?>> CRYSTAL_SPIKES  = registerKey("crystal_spikes");

    // # Features

	public static final Feature<DripstoneClusterConfiguration> SPIKED_ICE_CLUSTER_FEATURE = Registry.register(
			BuiltInRegistries.FEATURE,
			ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "spiked_ice_cluster"),
			new SpikedIceClusterFeature(DripstoneClusterConfiguration.CODEC));

	public static final Feature<LargeDripstoneConfiguration> LARGER_SPIKED_ICE_FEATURE = Registry.register(
			BuiltInRegistries.FEATURE,
			ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "large_spiked_ice"),
			new LargerSpikedIceFeature(LargeDripstoneConfiguration.CODEC));

	public static final Feature<PointedDripstoneConfiguration> SMALL_SPIKED_ICE_FEATURE = Registry.register(
			BuiltInRegistries.FEATURE,
			ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "spiked_ice"),
			new SmallSpikedIceFeature(PointedDripstoneConfiguration.CODEC));

	public static final Feature<CrystalSpikeFeatureConfig> CRYSTAL_SPIKES_FEATURE =
        Registry.register(
            BuiltInRegistries.FEATURE,
            ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "crystal_spikes"),
            new CrystalSpikeFeature(CrystalSpikeFeatureConfig.CODEC)
        );

    

    /*public static final Feature<SimpleBlockFeatureConfig> BLUE_MUSHROOM_VEGETATION_FEATURE =
        Registry.register(
            Registries.FEATURE,
            Identifier.of(EntStupidStuff.MOD_ID, "spiked_ice"),
            new SimpleBlockFeature(SimpleBlockFeatureConfig.CODEC)
		);
    

		/*
		 * ConfiguredFeatures.register(
			context,
			BLUE_MUSHROOM_VEGETATION,
			Feature.SIMPLE_BLOCK,
			new SimpleBlockFeatureConfig(
				new WeightedBlockStateProvider(
					Pool.<BlockState>builder()
						.add(BlockFactory.callBlock("azure_flower_bed").getDefaultState(), 10)
				)
			)
		);
		 */


    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {

		RuleTest stoneReplacables = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplacables = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
        RuleTest netherReplacables = new TagMatchTest(BlockTags.BASE_STONE_NETHER);
        RuleTest endReplacables = new BlockMatchTest(Blocks.END_STONE);

		FeatureUtils.register(context, ORE_LIMESTONE, Feature.ORE, new OreConfiguration(List.of(OreConfiguration.target(stoneReplacables, BlockFactory.callBlock("limestone").defaultBlockState())), 64));
		FeatureUtils.register(context, ORE_LIMESTONE_LOWER, Feature.ORE, new OreConfiguration(List.of(OreConfiguration.target(stoneReplacables, BlockFactory.callBlock("limestone").defaultBlockState())), 64));
		FeatureUtils.register(context, ORE_LIMESTONE_UPPER, Feature.ORE, new OreConfiguration(List.of(OreConfiguration.target(stoneReplacables, BlockFactory.callBlock("limestone").defaultBlockState())), 64));
		FeatureUtils.register(context, ORE_PACKED_ICE, Feature.ORE, new OreConfiguration(List.of(OreConfiguration.target(stoneReplacables, Blocks.PACKED_ICE.defaultBlockState())), 64));
		FeatureUtils.register(context, ORE_PACKED_ICE_LOWER, Feature.ORE, new OreConfiguration(List.of(OreConfiguration.target(stoneReplacables, Blocks.PACKED_ICE.defaultBlockState())), 64));
		FeatureUtils.register(context, ORE_SNOW, Feature.ORE, new OreConfiguration(List.of(OreConfiguration.target(stoneReplacables, Blocks.SNOW_BLOCK.defaultBlockState())), 64));
		FeatureUtils.register(context, ORE_SNOW_UPPER, Feature.ORE, new OreConfiguration(List.of(OreConfiguration.target(stoneReplacables, Blocks.SNOW_BLOCK.defaultBlockState())), 64));

		FeatureUtils.register(context, ORE_THALASSITE, Feature.ORE, new OreConfiguration(List.of(OreConfiguration.target(stoneReplacables, BlockFactory.callBlock("thalassite_ore").defaultBlockState())), 7));

		BlockPredicate blockPredicate = BlockPredicate.matchesBlocks(
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

		// # Underground Mushroom Biome:

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

		FeatureUtils.register(
			context,
			HUGE_BLUE_MUSHROOM_KEY,
			Feature.SIMPLE_RANDOM_SELECTOR,
			new SimpleRandomFeatureConfiguration(
				HolderSet.direct(
					PlacementUtils.onlyWhenEmpty(
						Feature.HUGE_RED_MUSHROOM,
						new HugeMushroomFeatureConfiguration(
							BlockStateProvider.simple(BlockFactory.callBlock("blue_mushroom_block").defaultBlockState().setValue(HugeMushroomBlock.DOWN, Boolean.valueOf(false))),
							BlockStateProvider.simple(
								Blocks.MUSHROOM_STEM.defaultBlockState().setValue(HugeMushroomBlock.UP, Boolean.valueOf(false)).setValue(HugeMushroomBlock.DOWN, Boolean.valueOf(false))
							),
							2
						)
					),
					PlacementUtils.onlyWhenEmpty(
						Feature.HUGE_FUNGUS,
						new HugeFungusConfiguration(
							BlockFactory.callBlock("shroomium").defaultBlockState(),
							Blocks.MUSHROOM_STEM.defaultBlockState().setValue(HugeMushroomBlock.UP, Boolean.valueOf(false)).setValue(HugeMushroomBlock.DOWN, Boolean.valueOf(false)),
							BlockFactory.callBlock("blue_mushroom_block").defaultBlockState().setValue(HugeMushroomBlock.DOWN, Boolean.valueOf(false)),
							Blocks.AIR.defaultBlockState(),
							blockPredicate,
							true
						)
					)
				)
			)
		);

		

		FeatureUtils.register(
			context,
			CRYSTAL_SPIKES,
			CRYSTAL_SPIKES_FEATURE,
			new CrystalSpikeFeatureConfig(
				BlockFactory.callBlock("blue_crystal_block").defaultBlockState(),
				//Blocks.GLOWSTONE.getDefaultState(),
                //ModBlocks.BLUE_CRYSTAL.getDefaultState(),
				BlockTags.STONE_ORE_REPLACEABLES,
                //ModTags.Blocks.CRYSTAL_REPLACEABLE,
                UniformInt.of(6, 18),   // spike length
                UniformInt.of(1, 3),    // base radius
                UniformFloat.of(0.1F, 0.4F) // taper chance
            )
		);

		Block AZURE_FLOWER_BED = BlockFactory.callBlock("azure_flower_bed");

		FeatureUtils.register(
			context,
			SHROOMIUM_FLOOR_VEGETATION_KEY,
			Feature.FLOWER,
			new RandomPatchConfiguration(
				96, 6, 2, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new WeightedStateProvider(segmentedBlock(AZURE_FLOWER_BED, 1, 4, FlowerBedBlock.AMOUNT, FlowerBedBlock.FACING))))
			)
		);

		FeatureUtils.register(
			context,
			SHROOMIUM_FLOOR_KEY,
			Feature.VEGETATION_PATCH,
			new VegetationPatchConfiguration(
				ModTags.SHROOMIUM_REPLACE,
				BlockStateProvider.simple(BlockFactory.callBlock("shroomium")),
				PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK,
				new SimpleBlockConfiguration(
					new WeightedStateProvider(segmentedBlock(AZURE_FLOWER_BED, 1, 4, FlowerBedBlock.AMOUNT, FlowerBedBlock.FACING))
				)),
				CaveSurface.FLOOR,
				ConstantInt.of(1),
				0.8f, // Coverage
				5, // Spread
				0.08f, // Chance
				UniformInt.of(4, 7), // Range
				0.7f // Vegetation chance
			)
		);

		FeatureUtils.register(context, MUD_LAYER_KEY, Feature.DISK,
		new DiskConfiguration(
			RuleBasedBlockStateProvider.simple(Blocks.MUD),
			BlockPredicate.matchesBlocks(List.of(Blocks.STONE, Blocks.DEEPSLATE, Blocks.DIORITE, 
				Blocks.ANDESITE, Blocks.GRANITE, BlockFactory.callBlock("shroomium"))), // Blocks to replace
			UniformInt.of(2, 4), // Radius (2-4 blocks)
			2 // Half height (2 blocks deep of mud)
		));

		FeatureUtils.register(
			context, MUSHROOM_SPORE_KEY, Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(BlockFactory.callBlock("fungal_spore_blossom")))
		);

        FeatureUtils.register(
			context, MUSHROOM_AURA_BLOSSOM_KEY, Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(BlockFactory.callBlock("mushroom_aura_block")))
		);

		// # ICY CAVE

		FeatureUtils.register(
			context,
			SPIKED_ICE_CLUSTER,
			SPIKED_ICE_CLUSTER_FEATURE,
			new DripstoneClusterConfiguration(
				12,
				UniformInt.of(3, 6),
				UniformInt.of(2, 8),
				1,
				3,
				UniformInt.of(2, 4),
				UniformFloat.of(0.3F, 0.7F),
				ClampedNormalFloat.of(0.1F, 0.3F, 0.1F, 0.9F),
				0.1F,
				3,
				8
			)
		);

        FeatureUtils.register(
			context,
			LARGE_SPIKED_ICE,
			LARGER_SPIKED_ICE_FEATURE,
			new LargeDripstoneConfiguration(
				30,
				UniformInt.of(3, 19),
				UniformFloat.of(0.4F, 2.0F),
				0.33F,
				UniformFloat.of(0.3F, 0.9F),
				UniformFloat.of(0.4F, 1.0F),
				UniformFloat.of(0.0F, 0.3F),
				4,
				0.6F
			)
		);

        FeatureUtils.register(
			context,
			SMALL_SPIKED_ICE,
			Feature.SIMPLE_RANDOM_SELECTOR,
			new SimpleRandomFeatureConfiguration(
				HolderSet.direct(
					PlacementUtils.inlinePlaced(
						SMALL_SPIKED_ICE_FEATURE,
						new PointedDripstoneConfiguration(0.2F, 0.7F, 0.5F, 0.5F),
						EnvironmentScanPlacement.scanningFor(Direction.DOWN, BlockPredicate.solid(), BlockPredicate.ONLY_IN_AIR_OR_WATER_PREDICATE, 12),
						RandomOffsetPlacement.vertical(ConstantInt.of(1))
					),
					PlacementUtils.inlinePlaced(
						Feature.POINTED_DRIPSTONE,
						new PointedDripstoneConfiguration(0.2F, 0.7F, 0.5F, 0.5F),
						EnvironmentScanPlacement.scanningFor(Direction.UP, BlockPredicate.solid(), BlockPredicate.ONLY_IN_AIR_OR_WATER_PREDICATE, 12),
						RandomOffsetPlacement.vertical(ConstantInt.of(-1))
					)
				)
			)
		);

		// # TREES

		FeatureUtils.register(
			context,
			FIR_KEY,
			Feature.TREE,
			new TreeConfiguration.TreeConfigurationBuilder(
					BlockStateProvider.simple(BlockFactory.callBlock("fir_log")),
					new FirTrunkPlacer(7, 3, 5),
					BlockStateProvider.simple(BlockFactory.callBlock("fir_leaves")),
                    //new FirFoliagePlacer(UniformIntProvider.create(2, 3), UniformIntProvider.create(0, 2), UniformIntProvider.create(1, 2)),
					new FirFoliagePlacer(UniformInt.of(2, 3), UniformInt.of(0, 2)),
					new TwoLayersFeatureSize(2, 0, 2)
				)
				.ignoreVines()
				.build()
		);

        FeatureUtils.register(
			context,
			MAPLE_KEY,
			Feature.TREE,
			new TreeConfiguration.TreeConfigurationBuilder(
				BlockStateProvider.simple(BlockFactory.callBlock("maple_log")),
				new CherryTrunkPlacer(
					7,
					1,
					0,
					new WeightedListInt(
						WeightedList.<IntProvider>builder().add(ConstantInt.of(1), 1).add(ConstantInt.of(2), 1).add(ConstantInt.of(3), 1).build()
					),
					UniformInt.of(2, 4),
					UniformInt.of(-4, -3),
					UniformInt.of(-1, 0)
				),
				BlockStateProvider.simple(BlockFactory.callBlock("maple_leaves")),
				new CherryFoliagePlacer(ConstantInt.of(4), ConstantInt.of(0), ConstantInt.of(5), 0.25F, 0.5F, 0.16666667F, 0.33333334F),
				new TwoLayersFeatureSize(1, 0, 2)
			)
            .ignoreVines()
			.build()
		);

		// # Oasis

		FeatureUtils.register(
			context,
			DATE_PALM,
			Feature.TREE,
			new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.OAK_LOG),
                new StraightTrunkPlacer(6, 4, 2),
                BlockStateProvider.simple(Blocks.OAK_LEAVES),
                new UmbrellaPalmFoliagePlacer(
						ConstantInt.of(0),
						ConstantInt.of(0)
				),
                new TwoLayersFeatureSize(1, 0, 2)
            )
            .ignoreVines()
			.build()
		);

		FeatureUtils.register(
			context,
			DESERT_WILLOW,
			Feature.TREE,
			new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.OAK_LOG),
                new StraightTrunkPlacer(5, 3, 1),
                BlockStateProvider.simple(Blocks.OAK_LEAVES),
                new WillowFoliagePlacer(
                        ConstantInt.of(0),
                        ConstantInt.of(0)
                ),
                new TwoLayersFeatureSize(1, 0, 2)
                )
            .ignoreVines()
			.build()
		);

    }

	// # Registry

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?, ?>> context, ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }

	private static WeightedList.Builder<BlockState> segmentedBlock(Block block, int min, int max, IntegerProperty amountProperty, EnumProperty<Direction> facingProperty) {
		WeightedList.Builder<BlockState> builder = WeightedList.builder();

		for (int i = min; i <= max; i++) {
			for (Direction direction : Direction.Plane.HORIZONTAL) {
				builder.add(block.defaultBlockState().setValue(amountProperty, i).setValue(facingProperty, direction), 1);
			}
		}

		return builder;
	}


}
