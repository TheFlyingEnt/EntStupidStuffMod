package net.ent.entstupidstuff.block;

import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.block.base.BlueMushroomPlantBlock;
import net.ent.entstupidstuff.block.base.GrateSlabBlock;
import net.ent.entstupidstuff.block.base.GrateStairsBlock;
import net.ent.entstupidstuff.block.base.HorizontalBannerBlock;
import net.ent.entstupidstuff.block.base.HorizontalWallBannerBlock;
import net.ent.entstupidstuff.block.base.MushroomAuraBlock;
import net.ent.entstupidstuff.block.base.MushroomSporeBlossomBlock;
import net.ent.entstupidstuff.block.base.MushroombedBlock;
import net.ent.entstupidstuff.block.base.PointedIceBlock;
import net.ent.entstupidstuff.block.base.ShroomiumBlock;
import net.ent.entstupidstuff.block.base.SilkwormVineBlock;
import net.ent.entstupidstuff.block.base.SilkwormVinePlantBlock;
import net.ent.entstupidstuff.block.base.SpinBlock;
import net.ent.entstupidstuff.block.base.TransparentMushroomBlock;
import net.ent.entstupidstuff.effects.ModEffects;
import net.ent.entstupidstuff.item.ModGroup;
import net.ent.entstupidstuff.particle.ParticleTypesFactory;
import net.ent.entstupidstuff.world.ModConfiguredFeatures;
import net.ent.entstupidstuff.world.tree.SaplingGeneratorFactory;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.OxidizableBlocksRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CarpetBlock;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.FlowerBedBlock;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.IronBarsBlock;
import net.minecraft.world.level.block.LanternBlock;
import net.minecraft.world.level.block.SkullBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.TorchBlock;
import net.minecraft.world.level.block.TransparentBlock;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.WallSkullBlock;
import net.minecraft.world.level.block.WallTorchBlock;
import net.minecraft.world.level.block.WaterloggedTransparentBlock;
import net.minecraft.world.level.block.WeatheringCopper;
import net.minecraft.world.level.block.WeatheringCopperDoorBlock;
import net.minecraft.world.level.block.WeatheringCopperTrapDoorBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

public class BlockFactory {

    /*
     *  This is the Updated BlockFactory
     *  Design to be more Inline with Minecraft's System
     * 
     *  While this system is not the most optimized, For Read ability:
     *  It was designed like this
     * 
     * 1/4/25 - Updated Completed, Require Testing on All
     */

    public static final Map<ResourceLocation, Block> BlockList = new LinkedHashMap<>();
    public static final Map<ResourceLocation, Block> BlockItem = new LinkedHashMap<>(); 
    public static final Map<ResourceLocation, Item> ItemList = new LinkedHashMap<>();

    public static final Map<DyeColor, HorizontalBannerBlock> HORIZONTAL_BANNERS = new EnumMap<>(DyeColor.class);
    public static final Map<DyeColor, HorizontalWallBannerBlock> HORIZONTAL_WALL_BANNERS = new EnumMap<>(DyeColor.class);

    public final static String[] RECIPES_COLORS = {"black", "blue", "brown", "cyan", "gray", "green", "light_blue", "light_gray", "lime", "magenta", "orange", "pink", "purple", "red", "yellow", "white"};

    public final static String[] COLORS = {"white", "light_gray", "gray", "black", "brown", "red", "orange", "yellow", "lime", "green", "cyan", "light_blue", "blue", "purple", "magenta", "pink"};

    public static Block getWoolColor(String covColor) {
        if (covColor == "white") {return Blocks.WHITE_WOOL;}
        if (covColor == "light_gray") {return Blocks.LIGHT_GRAY_WOOL;}
        if (covColor == "gray") {return Blocks.GRAY_WOOL;}
        if (covColor == "black") {return Blocks.BLACK_WOOL;}
        if (covColor == "brown") {return Blocks.BROWN_WOOL;}
        if (covColor == "red") {return Blocks.RED_WOOL;}
        if (covColor == "orange") {return Blocks.ORANGE_WOOL;}
        if (covColor == "yellow") {return Blocks.YELLOW_WOOL;}
        if (covColor == "lime") {return Blocks.LIME_WOOL;}
        if (covColor == "green") {return Blocks.GREEN_WOOL;}
        if (covColor == "cyan") {return Blocks.CYAN_WOOL;}
        if (covColor == "light_blue") {return Blocks.LIGHT_BLUE_WOOL;}
        if (covColor == "blue") {return Blocks.BLUE_WOOL;}
        if (covColor == "purple") {return Blocks.PURPLE_WOOL;}
        if (covColor == "magenta") {return Blocks.MAGENTA_WOOL;}
        if (covColor == "pink") {return Blocks.PINK_WOOL;}
        else {return Blocks.WHITE_WOOL;}
    }
    public static Item getDye(String covColor) {
        if (covColor == "white") {return Items.WHITE_DYE;}
        if (covColor == "light_gray") {return Items.LIGHT_GRAY_DYE;}
        if (covColor == "gray") {return Items.GRAY_DYE;}
        if (covColor == "black") {return Items.BLACK_DYE;}
        if (covColor == "brown") {return Items.BROWN_DYE;}
        if (covColor == "red") {return Items.RED_DYE;}
        if (covColor == "orange") {return Items.ORANGE_DYE;}
        if (covColor == "yellow") {return Items.YELLOW_DYE;}
        if (covColor == "lime") {return Items.LIME_DYE;}
        if (covColor == "green") {return Items.GREEN_DYE;}
        if (covColor == "cyan") {return Items.CYAN_DYE;}
        if (covColor == "light_blue") {return Items.LIGHT_BLUE_DYE;}
        if (covColor == "blue") {return Items.BLUE_DYE;}
        if (covColor == "purple") {return Items.PURPLE_DYE;}
        if (covColor == "magenta") {return Items.MAGENTA_DYE;}
        if (covColor == "pink") {return Items.PINK_DYE;}
        else {return Items.WHITE_DYE;}
    }

    //Start of Code

    @SuppressWarnings("unused")
    public static void onInitialize() {

        // # Added REDWOOD Natural + Planks
        Block REDWOOD_PLANKS = register("redwood" + "_planks" + "", Block::new, (BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).mapColor((MapColor.CRIMSON_NYLIUM))));
        BlockGroupFactory.groupWoodFamilty("redwood", "", REDWOOD_PLANKS, true, MapColor.CRIMSON_NYLIUM, MapColor.CRIMSON_NYLIUM);
        BlockFactoryHelper.addSaplings("redwood", SaplingGeneratorFactory.FIR); //TODO: Change to Redwood when Ready
        BlockFactoryHelper.LeavesFamily("redwood", "", true);

        // # Added FIR Natural + Planks
        Block FIR_PLANKS = register("fir" + "_planks" + "", Block::new, (BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).mapColor((MapColor.TERRACOTTA_GRAY))));
        BlockGroupFactory.groupWoodFamilty("fir", "", FIR_PLANKS, true, MapColor.TERRACOTTA_GRAY, MapColor.PODZOL);
        BlockFactoryHelper.addSaplings("fir", SaplingGeneratorFactory.FIR);
        BlockFactoryHelper.LeavesFamily("fir", "", true);

        // # Added MAPLE Natural + Planks
        Block MAPLE_PLANKS = register("maple" + "_planks" + "", Block::new, (BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).mapColor((MapColor.PODZOL))));
        BlockGroupFactory.groupWoodFamilty("maple", "", MAPLE_PLANKS, true, MapColor.DIRT, MapColor.PODZOL);
        BlockFactoryHelper.addSaplings("maple", SaplingGeneratorFactory.MAPLE);
        BlockFactoryHelper.LeavesFamily("maple", "", true, MapColor.COLOR_ORANGE, ParticleTypesFactory.MAPLE_LEAVES);
        register("orange_petals", FlowerBedBlock::new,
            BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollision().sound(SoundType.PINK_PETALS).pushReaction(PushReaction.DESTROY)
        );

        // # Added PHANTOM Natural + Planks + Lantern
        Block PHANTOM_PLANKS = register("phantom" + "_planks" + "", Block::new, (BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).mapColor((MapColor.GLOW_LICHEN)).noOcclusion()));
        BlockGroupFactory.groupWoodFamilty("phantom", "", PHANTOM_PLANKS, true, MapColor.GLOW_LICHEN, MapColor.PLANT);
        Block PHANTOM_LANTERN = register("phantom_lantern", (settings) -> new LanternBlock(settings.mapColor(MapColor.METAL).forceSolidOn().requiresCorrectToolForDrops().strength(3.5F).sound(SoundType.LANTERN).lightLevel((state) -> {
            return 10;
        }).noOcclusion().pushReaction(PushReaction.DESTROY)), BlockBehaviour.Properties.of());

        // # Added Fungal Natural + Planks (Regular + Colored)
        BlockGroupFactory.groupFungalFamily("", MapColor.WOOL);
        BlockGroupFactory.groupFungalFamily("_white", MapColor.SNOW);
        BlockGroupFactory.groupFungalFamily("_light_gray", MapColor.COLOR_LIGHT_GRAY);
        BlockGroupFactory.groupFungalFamily("_gray", MapColor.COLOR_GRAY);
        BlockGroupFactory.groupFungalFamily("_black", MapColor.COLOR_BLACK);
        BlockGroupFactory.groupFungalFamily("_brown", MapColor.COLOR_BROWN);
        BlockGroupFactory.groupFungalFamily("_red", MapColor.COLOR_RED);
        BlockGroupFactory.groupFungalFamily("_orange", MapColor.COLOR_ORANGE);
        BlockGroupFactory.groupFungalFamily("_yellow", MapColor.COLOR_YELLOW);
        BlockGroupFactory.groupFungalFamily("_lime", MapColor.COLOR_LIGHT_GREEN);
        BlockGroupFactory.groupFungalFamily("_green", MapColor.COLOR_GREEN);
        BlockGroupFactory.groupFungalFamily("_cyan", MapColor.COLOR_CYAN);
        BlockGroupFactory.groupFungalFamily("_light_blue", MapColor.COLOR_LIGHT_BLUE);
        BlockGroupFactory.groupFungalFamily("_blue", MapColor.COLOR_BLUE);
        BlockGroupFactory.groupFungalFamily("_purple", MapColor.COLOR_PURPLE);
        BlockGroupFactory.groupFungalFamily("_magenta", MapColor.COLOR_MAGENTA);
        BlockGroupFactory.groupFungalFamily("_pink", MapColor.COLOR_PINK);

        // # Added Blue Mushroom Family
        Block BLUE_MUSHROOM = register_custom_mushroom(
		"blue_mushroom",
		(settings) -> new BlueMushroomPlantBlock(
			ModConfiguredFeatures.HUGE_BLUE_MUSHROOM_KEY, settings
		),
		BlockBehaviour.Properties.of()
			.mapColor(MapColor.COLOR_BLUE)
			.noCollision()
			.randomTicks()
			.instabreak()
			.sound(SoundType.GRASS)
			.hasPostProcess(Blocks::always)
			.pushReaction(PushReaction.DESTROY)
		);

        Block POTTED_BLUE_MUSHROOM = register(
            "potted_blue_mushroom", settings -> new FlowerPotBlock(BLUE_MUSHROOM, settings), Blocks.flowerPotProperties()
        );

        Block BLUE_MUSHROOM_BLOCK = register(
            "blue_mushroom_block",
            TransparentMushroomBlock::new,
            BlockBehaviour.Properties.of()
                .mapColor(MapColor.COLOR_BLUE)
                .instrument(NoteBlockInstrument.BASS)
                .strength(0.2F)
                .sound(SoundType.WOOD)
                .lightLevel(state -> 5)
                .ignitedByLava()
                .noOcclusion()
                        .isRedstoneConductor(Blocks::never)
                        .isViewBlocking(Blocks::never)
        );

        Block CRYSTAL = register(
            "blue_crystal_block",
            TransparentBlock::new,
            BlockBehaviour.Properties.of()
                .instrument(NoteBlockInstrument.XYLOPHONE)
                .strength(0.3F)
                .noOcclusion()
                .isValidSpawn(Blocks::never)
                .isRedstoneConductor(Blocks::never)
                .isSuffocating(Blocks::never)
                .isViewBlocking(Blocks::never)
                .sound(SoundType.AMETHYST)
                .requiresCorrectToolForDrops()
                .lightLevel(state -> 1)
        );

        Block AZURE_FLOWER_BED = register(
            "azure_flower_bed",
            MushroombedBlock::new,
            BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLUE).noCollision().sound(SoundType.FUNGUS).pushReaction(PushReaction.DESTROY).lightLevel(state -> 5)
        );

        Block FUNGAL_SPORE_BLOSSOM = register(
            "fungal_spore_blossom",
            MushroomSporeBlossomBlock::new,
            BlockBehaviour.Properties.of()
                .mapColor(MapColor.WARPED_STEM)
                .instabreak()
                .noCollision()
                .sound(SoundType.SPORE_BLOSSOM)
                .pushReaction(PushReaction.DESTROY)
                .lightLevel(state -> 4)
        );

        // # Adding Andersite, Diorite and Granite
        Block ANDERSITE_BRICKS = register("andesite_bricks", (settings) -> new Block(settings), BlockBehaviour.Properties.ofFullCopy(Blocks.POLISHED_ANDESITE));
        BlockGroupFactory.groupStoneFamily("andesite_brick", ANDERSITE_BRICKS, MapColor.STONE, true);
        register("polished_andesite" + "_wall", (settings) -> new WallBlock(settings), BlockBehaviour.Properties.ofFullCopy(Blocks.POLISHED_ANDESITE));

        Block GRANITE_BRICKS = register("granite_bricks", (settings) -> new Block(settings), BlockBehaviour.Properties.ofFullCopy(Blocks.POLISHED_GRANITE));
        BlockGroupFactory.groupStoneFamily("granite_brick", GRANITE_BRICKS, MapColor.DIRT, true);
        register("polished_granite" + "_wall", (settings) -> new WallBlock(settings), BlockBehaviour.Properties.ofFullCopy(Blocks.POLISHED_GRANITE));

        Block DIORITE_BRICKS = register("diorite_bricks", (settings) -> new Block(settings), BlockBehaviour.Properties.ofFullCopy(Blocks.POLISHED_DIORITE));
        BlockGroupFactory.groupStoneFamily("diorite_brick", DIORITE_BRICKS, MapColor.QUARTZ, true);
        register("polished_diorite" + "_wall", (settings) -> new WallBlock(settings), BlockBehaviour.Properties.ofFullCopy(Blocks.POLISHED_DIORITE));

        // # Adding Limestone and Limestone Bricks
        Block LIMESTONE = register("limestone", (settings) -> new Block(settings), BlockBehaviour.Properties.ofFullCopy(Blocks.LIGHT_GRAY_TERRACOTTA));
        BlockGroupFactory.groupStoneFamily("limestone", LIMESTONE, MapColor.TERRACOTTA_LIGHT_GRAY, false);

        Block POLISHED_LIMESTONE = register("polished_limestone", (settings) -> new Block(settings), BlockBehaviour.Properties.ofFullCopy(Blocks.LIGHT_GRAY_TERRACOTTA));
        BlockGroupFactory.groupStoneFamily("polished_limestone", POLISHED_LIMESTONE, MapColor.TERRACOTTA_LIGHT_GRAY, false);

        Block LIMESTONE_BRICKS = register("polished_limestone_bricks", (settings) -> new Block(settings), BlockBehaviour.Properties.ofFullCopy(Blocks.LIGHT_GRAY_TERRACOTTA));
        BlockGroupFactory.groupStoneFamily("polished_limestone_brick", LIMESTONE_BRICKS, MapColor.TERRACOTTA_LIGHT_GRAY, true);

        // # Adding IronGates
        Block IRON_GRATE = register("iron_grate",
            (settings) -> new WaterloggedTransparentBlock(settings.requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.METAL).noOcclusion()),
            BlockBehaviour.Properties.of()
        );

        Block IRON_GRATE_STAIRS = register("iron_grate_stairs",
            (settings) -> new GrateStairsBlock(IRON_GRATE.defaultBlockState(), settings),
            BlockBehaviour.Properties.ofFullCopy(IRON_GRATE)
        );

        Block IRON_GRATE_SLAB = register("iron_grate_slab",
            (settings) -> new GrateSlabBlock(settings),
            BlockBehaviour.Properties.ofFullCopy(IRON_GRATE)
        );

        // # Adding StringGates
        Block STRING_GATE = register("string_gate", 
            (settings) -> new IronBarsBlock(settings.noOcclusion()
                        .isValidSpawn(Blocks::never)
                        .isRedstoneConductor(Blocks::never)
                        .isSuffocating(Blocks::never)
                        .isViewBlocking(Blocks::never)),
            BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BARS)
        );

        Block STRING_BLOCK = register("string_block", 
            (settings) -> new TransparentBlock(settings.noOcclusion()
                        .isValidSpawn(Blocks::never)
                        .isRedstoneConductor(Blocks::never)
                        .isSuffocating(Blocks::never)
                        .isViewBlocking(Blocks::never)),
            BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL)
        );

        // # Adding Abyssal Stone and Abyssal Stone Bricks
        //Block ABYSSAL_STONE = register("abyssal_stone", (settings) -> new Block(settings), AbstractBlock.Settings.copy(Blocks.BLACKSTONE));
        BlockGroupFactory.groupStoneFamily("abyssal_stone", ABYSSAL_STONE, MapColor.WARPED_STEM, false);

        Block POLISHED_ABYSSAL_STONE = register("polished_abyssal_stone", (settings) -> new Block(settings), BlockBehaviour.Properties.ofFullCopy(Blocks.BLACKSTONE));
        BlockGroupFactory.groupStoneFamily("polished_abyssal_stone", POLISHED_ABYSSAL_STONE, MapColor.WARPED_STEM, false);

        Block ABYSSAL_STONE_BRICKS = register("polished_abyssal_stone_bricks", (settings) -> new Block(settings), BlockBehaviour.Properties.ofFullCopy(Blocks.BLACKSTONE));
        BlockGroupFactory.groupStoneFamily("polished_abyssal_stone_brick", ABYSSAL_STONE_BRICKS, MapColor.WARPED_STEM, true);

        Block POLISHED_ABYSSAL_STONE_SEAWEED = BlockFactory.register("polished_abyssal_stone_seaweed",
            (settings) -> new Block(settings),
            BlockBehaviour.Properties.ofFullCopy(POLISHED_ABYSSAL_STONE)
        );

        // # Adding Thalassite Ore and Thalassite Block
        Block THALASSITE_ORE = register(
		    "thalassite_ore",
		    settings -> new DropExperienceBlock(UniformInt.of(3, 7), settings),
		    BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(3.0F, 3.0F)
	    );

        Block THALASSITE_BLOCK = register(
            "thalassite_block", 
            (settings) -> new Block(settings), 
            BlockBehaviour.Properties.ofFullCopy(Blocks.BLACKSTONE).mapColor(MapColor.WARPED_STEM).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.METAL)
        );

        // # Adding Hardend Sandstone
        Block HARDEND_SANDSTONE = register("hardend_sandstone", (settings) -> new Block(settings), BlockBehaviour.Properties.ofFullCopy(Blocks.BLACKSTONE));
        BlockGroupFactory.groupStoneFamily("hardend_sandstone", HARDEND_SANDSTONE, MapColor.SAND, false);

        Block DATE = register_dates("date", DateBlock::new, Properties.of().mapColor(MapColor.PLANT).randomTicks().strength(0.2F, 3.0F).sound(SoundType.WOOD).noOcclusion().pushReaction(PushReaction.DESTROY));

        // # Vanilla Additions 
        addVanilla();

        // # Legacy (Textured Wool)
        onInitializeOLD();

        // # Adding Glowing Silk Wool + :

        Block GSW_WHITE_WOOL = register("glowing_silk_wool_white", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL).lightLevel(state -> 3));
        Block GSW_LIGHT_GRAY_WOOL = register("glowing_silk_wool_light_gray", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.LIGHT_GRAY_WOOL).lightLevel(state -> 3));
        Block GSW_GRAY_WOOL = register("glowing_silk_wool_gray", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GRAY_WOOL).lightLevel(state -> 3));
        Block GSW_BLACK_WOOL = register("glowing_silk_wool_black", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BLACK_WOOL).lightLevel(state -> 3));
        Block GSW_BROWN_WOOL = register("glowing_silk_wool_brown", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BROWN_WOOL).lightLevel(state -> 3));
        Block GSW_RED_WOOL = register("glowing_silk_wool_red", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.RED_WOOL).lightLevel(state -> 3));
        Block GSW_ORANGE_WOOL = register("glowing_silk_wool_orange", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.ORANGE_WOOL).lightLevel(state -> 3));
        Block GSW_YELLOW_WOOL = register("glowing_silk_wool_yellow", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.YELLOW_WOOL).lightLevel(state -> 3));
        Block GSW_LIME_WOOL = register("glowing_silk_wool_lime", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.LIME_WOOL).lightLevel(state -> 3));
        Block GSW_GREEN_WOOL = register("glowing_silk_wool_green", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GREEN_WOOL).lightLevel(state -> 3));
        Block GSW_CYAN_WOOL = register("glowing_silk_wool_cyan", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CYAN_WOOL).lightLevel(state -> 3));
        Block GSW_LIGHT_BLUE_WOOL = register("glowing_silk_wool_light_blue", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.LIGHT_BLUE_WOOL).lightLevel(state -> 3));
        Block GSW_BLUE_WOOL = register("glowing_silk_wool_blue", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BLUE_WOOL).lightLevel(state -> 3));
        Block GSW_PURPLE_WOOL = register("glowing_silk_wool_purple", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.PURPLE_WOOL).lightLevel(state -> 3));
        Block GSW_MAGENTA_WOOL = register("glowing_silk_wool_magenta", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.MAGENTA_WOOL).lightLevel(state -> 3));
        Block GSW_PINK_WOOL = register("glowing_silk_wool_pink", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.PINK_WOOL).lightLevel(state -> 3));

        Block GSW_WHITE_WOOL_CARPET = register("glowing_silk_wool_white_carpet", CarpetBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_CARPET).lightLevel(state -> 3));
        Block GSW_LIGHT_GRAY_WOOL_CARPET = register("glowing_silk_wool_light_gray_carpet", CarpetBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.LIGHT_GRAY_CARPET).lightLevel(state -> 3));
        Block GSW_GRAY_WOOL_CARPET = register("glowing_silk_wool_gray_carpet", CarpetBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GRAY_CARPET).lightLevel(state -> 3));
        Block GSW_BLACK_WOOL_CARPET = register("glowing_silk_wool_black_carpet", CarpetBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BLACK_CARPET).lightLevel(state -> 3));
        Block GSW_BROWN_WOOL_CARPET = register("glowing_silk_wool_brown_carpet", CarpetBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BROWN_CARPET).lightLevel(state -> 3));
        Block GSW_RED_WOOL_CARPET = register("glowing_silk_wool_red_carpet", CarpetBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.RED_CARPET).lightLevel(state -> 3));
        Block GSW_ORANGE_WOOL_CARPET = register("glowing_silk_wool_orange_carpet", CarpetBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.ORANGE_CARPET).lightLevel(state -> 3));
        Block GSW_YELLOW_WOOL_CARPET = register("glowing_silk_wool_yellow_carpet", CarpetBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.YELLOW_CARPET).lightLevel(state -> 3));
        Block GSW_LIME_WOOL_CARPET = register("glowing_silk_wool_lime_carpet", CarpetBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.LIME_CARPET).lightLevel(state -> 3));
        Block GSW_GREEN_WOOL_CARPET = register("glowing_silk_wool_green_carpet", CarpetBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GREEN_CARPET).lightLevel(state -> 3));
        Block GSW_CYAN_WOOL_CARPET = register("glowing_silk_wool_cyan_carpet", CarpetBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CYAN_CARPET).lightLevel(state -> 3));
        Block GSW_LIGHT_BLUE_WOOL_CARPET = register("glowing_silk_wool_light_blue_carpet", CarpetBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.LIGHT_BLUE_CARPET).lightLevel(state -> 3));
        Block GSW_BLUE_WOOL_CARPET = register("glowing_silk_wool_blue_carpet", CarpetBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BLUE_CARPET).lightLevel(state -> 3));
        Block GSW_PURPLE_WOOL_CARPET = register("glowing_silk_wool_purple_carpet", CarpetBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.PURPLE_CARPET).lightLevel(state -> 3));
        Block GSW_MAGENTA_WOOL_CARPET = register("glowing_silk_wool_magenta_carpet", CarpetBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.MAGENTA_CARPET).lightLevel(state -> 3));
        Block GSW_PINK_WOOL_CARPET = register("glowing_silk_wool_pink_carpet", CarpetBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.PINK_CARPET).lightLevel(state -> 3));

        FlammableBlockRegistry.getDefaultInstance().add(GSW_WHITE_WOOL, 30, 60);
        FlammableBlockRegistry.getDefaultInstance().add(GSW_LIGHT_GRAY_WOOL, 30, 60);
        FlammableBlockRegistry.getDefaultInstance().add(GSW_GRAY_WOOL, 30, 60);
        FlammableBlockRegistry.getDefaultInstance().add(GSW_BLACK_WOOL, 30, 60);
        FlammableBlockRegistry.getDefaultInstance().add(GSW_BROWN_WOOL, 30, 60);
        FlammableBlockRegistry.getDefaultInstance().add(GSW_RED_WOOL, 30, 60);
        FlammableBlockRegistry.getDefaultInstance().add(GSW_ORANGE_WOOL, 30, 60);
        FlammableBlockRegistry.getDefaultInstance().add(GSW_YELLOW_WOOL, 30, 60);
        FlammableBlockRegistry.getDefaultInstance().add(GSW_LIME_WOOL, 30, 60);
        FlammableBlockRegistry.getDefaultInstance().add(GSW_GREEN_WOOL, 30, 60);
        FlammableBlockRegistry.getDefaultInstance().add(GSW_CYAN_WOOL, 30, 60);
        FlammableBlockRegistry.getDefaultInstance().add(GSW_LIGHT_BLUE_WOOL, 30, 60);
        FlammableBlockRegistry.getDefaultInstance().add(GSW_BLUE_WOOL, 30, 60);
        FlammableBlockRegistry.getDefaultInstance().add(GSW_PURPLE_WOOL, 30, 60);
        FlammableBlockRegistry.getDefaultInstance().add(GSW_MAGENTA_WOOL, 30, 60);
        FlammableBlockRegistry.getDefaultInstance().add(GSW_PINK_WOOL, 30, 60);

        FlammableBlockRegistry.getDefaultInstance().add(GSW_WHITE_WOOL_CARPET, 30, 60);
        FlammableBlockRegistry.getDefaultInstance().add(GSW_LIGHT_GRAY_WOOL_CARPET, 30, 60);
        FlammableBlockRegistry.getDefaultInstance().add(GSW_GRAY_WOOL_CARPET, 30, 60);
        FlammableBlockRegistry.getDefaultInstance().add(GSW_BLACK_WOOL_CARPET, 30, 60);
        FlammableBlockRegistry.getDefaultInstance().add(GSW_BROWN_WOOL_CARPET, 30, 60);
        FlammableBlockRegistry.getDefaultInstance().add(GSW_RED_WOOL_CARPET, 30, 60);
        FlammableBlockRegistry.getDefaultInstance().add(GSW_ORANGE_WOOL_CARPET, 30, 60);
        FlammableBlockRegistry.getDefaultInstance().add(GSW_YELLOW_WOOL_CARPET, 30, 60);
        FlammableBlockRegistry.getDefaultInstance().add(GSW_LIME_WOOL_CARPET, 30, 60);
        FlammableBlockRegistry.getDefaultInstance().add(GSW_GREEN_WOOL_CARPET, 30, 60);
        FlammableBlockRegistry.getDefaultInstance().add(GSW_CYAN_WOOL_CARPET, 30, 60);
        FlammableBlockRegistry.getDefaultInstance().add(GSW_LIGHT_BLUE_WOOL_CARPET, 30, 60);
        FlammableBlockRegistry.getDefaultInstance().add(GSW_BLUE_WOOL_CARPET, 30, 60);
        FlammableBlockRegistry.getDefaultInstance().add(GSW_PURPLE_WOOL_CARPET, 30, 60);
        FlammableBlockRegistry.getDefaultInstance().add(GSW_MAGENTA_WOOL_CARPET, 30, 60);
        FlammableBlockRegistry.getDefaultInstance().add(GSW_PINK_WOOL_CARPET, 30, 60);

        HorizontalBannersFamily();

        //TODO: Add Glowing Bed, Glowing Banners

        Block SPIN_BLOCK = register("spin_block", (settings) -> new SpinBlock(settings), BlockBehaviour.Properties.of().strength(1f).noCollision());


    }

    //public static final Block GSW_WHITE_BED = registerWithoutItem("glowing_white_bed", settings -> new BedBlock(DyeColor.WHITE,settings), BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_BED));

    /*public static final Block GSW_WHITE_BED = registerWithoutItem("glowing_white_bed", properties -> new BedBlock(DyeColor.WHITE, properties), BlockBehaviour.Properties.of()
		.mapColor(blockState -> blockState.getValue(BedBlock.PART) == BedPart.FOOT ? DyeColor.WHITE.getMapColor() : MapColor.WOOL)
		.sound(SoundType.WOOD)
		.strength(0.2F)
		.noOcclusion()
		.ignitedByLava()
	.pushReaction(PushReaction.DESTROY));*/

    public static void addVanilla() {
        OxidizableFamily();
        register("iron" + "_glass_door", (settings) -> new DoorBlock(BlockSetType.IRON, settings), BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_DOOR));
        register("iron" + "_glass_trapdoor", (settings) -> new TrapDoorBlock(BlockSetType.IRON, settings), BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_TRAPDOOR));

        Block OAK_DOOR = register("oak_glass_door", (settings) -> new DoorBlock(BlockSetType.OAK, settings), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR));
        Block SPRUCE_DOOR = register("spruce_glass_door", (settings) -> new DoorBlock(BlockSetType.OAK, settings), BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_DOOR));
        Block JUNGLE_DOOR = register("jungle_glass_door", (settings) -> new DoorBlock(BlockSetType.OAK, settings), BlockBehaviour.Properties.ofFullCopy(Blocks.JUNGLE_DOOR));
        Block BIRCH_DOOR = register("birch_glass_door", (settings) -> new DoorBlock(BlockSetType.OAK, settings), BlockBehaviour.Properties.ofFullCopy(Blocks.BIRCH_DOOR));
        Block DARK_OAK_DOOR = register("dark_oak_glass_door", (settings) -> new DoorBlock(BlockSetType.OAK, settings), BlockBehaviour.Properties.ofFullCopy(Blocks.DARK_OAK_DOOR));
        Block ACACIA_DOOR = register("acacia_glass_door", (settings) -> new DoorBlock(BlockSetType.OAK, settings), BlockBehaviour.Properties.ofFullCopy(Blocks.ACACIA_DOOR));
        Block MANGROVE_DOOR = register("mangrove_glass_door", (settings) -> new DoorBlock(BlockSetType.OAK, settings), BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_DOOR));
        Block CHERRY_DOOR = register("cherry_glass_door", (settings) -> new DoorBlock(BlockSetType.OAK, settings), BlockBehaviour.Properties.ofFullCopy(Blocks.CHERRY_DOOR));
        Block BAMBOO_DOOR = register("bamboo_glass_door", (settings) -> new DoorBlock(BlockSetType.OAK, settings), BlockBehaviour.Properties.ofFullCopy(Blocks.BAMBOO_DOOR));
        Block PALE_OAK_DOOR = register("pale_oak_glass_door", (settings) -> new DoorBlock(BlockSetType.OAK, settings), BlockBehaviour.Properties.ofFullCopy(Blocks.PALE_OAK_DOOR));

        register("crimson_glass_door", (settings) -> new DoorBlock(BlockSetType.OAK, settings), BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_DOOR));
        register("warped_glass_door", (settings) -> new DoorBlock(BlockSetType.OAK, settings), BlockBehaviour.Properties.ofFullCopy(Blocks.WARPED_DOOR));

        FlammableBlockRegistry.getDefaultInstance().add(OAK_DOOR, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(SPRUCE_DOOR, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(JUNGLE_DOOR, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(BIRCH_DOOR, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(DARK_OAK_DOOR, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(ACACIA_DOOR, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(MANGROVE_DOOR, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(CHERRY_DOOR, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(BAMBOO_DOOR, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(PALE_OAK_DOOR, 5, 20);

        Block OAK_TRAPDOOR = register("oak_glass_trapdoor", (settings) -> new TrapDoorBlock(BlockSetType.OAK, settings), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR));
        Block SPRUCE_TRAPDOOR = register("spruce_glass_trapdoor", (settings) -> new TrapDoorBlock(BlockSetType.OAK, settings), BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_TRAPDOOR));
        Block JUNGLE_TRAPDOOR = register("jungle_glass_trapdoor", (settings) -> new TrapDoorBlock(BlockSetType.OAK, settings), BlockBehaviour.Properties.ofFullCopy(Blocks.JUNGLE_TRAPDOOR));
        Block BIRCH_TRAPDOOR = register("birch_glass_trapdoor", (settings) -> new TrapDoorBlock(BlockSetType.OAK, settings), BlockBehaviour.Properties.ofFullCopy(Blocks.BIRCH_TRAPDOOR));
        Block DARK_OAK_TRAPDOOR = register("dark_oak_glass_trapdoor", (settings) -> new TrapDoorBlock(BlockSetType.OAK, settings), BlockBehaviour.Properties.ofFullCopy(Blocks.DARK_OAK_TRAPDOOR));
        Block ACACIA_TRAPDOOR = register("acacia_glass_trapdoor", (settings) -> new TrapDoorBlock(BlockSetType.OAK, settings), BlockBehaviour.Properties.ofFullCopy(Blocks.ACACIA_TRAPDOOR));
        Block MANGROVE_TRAPDOOR = register("mangrove_glass_trapdoor", (settings) -> new TrapDoorBlock(BlockSetType.OAK, settings), BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_TRAPDOOR));
        Block CHERRY_TRAPDOOR = register("cherry_glass_trapdoor", (settings) -> new TrapDoorBlock(BlockSetType.OAK, settings), BlockBehaviour.Properties.ofFullCopy(Blocks.CHERRY_TRAPDOOR));
        Block BAMBOO_TRAPDOOR = register("bamboo_glass_trapdoor", (settings) -> new TrapDoorBlock(BlockSetType.OAK, settings), BlockBehaviour.Properties.ofFullCopy(Blocks.BAMBOO_TRAPDOOR));
        Block PALE_OAK_TRAPDOOR = register("pale_oak_glass_trapdoor", (settings) -> new TrapDoorBlock(BlockSetType.OAK, settings), BlockBehaviour.Properties.ofFullCopy(Blocks.PALE_OAK_TRAPDOOR));

        register("crimson_glass_trapdoor", (settings) -> new TrapDoorBlock(BlockSetType.OAK, settings), BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_TRAPDOOR));
        register("warped_glass_trapdoor", (settings) -> new TrapDoorBlock(BlockSetType.OAK, settings), BlockBehaviour.Properties.ofFullCopy(Blocks.WARPED_TRAPDOOR));

        FlammableBlockRegistry.getDefaultInstance().add(OAK_TRAPDOOR, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(SPRUCE_TRAPDOOR, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(JUNGLE_TRAPDOOR, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(BIRCH_TRAPDOOR, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(DARK_OAK_TRAPDOOR, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(ACACIA_TRAPDOOR, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(MANGROVE_TRAPDOOR, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(CHERRY_TRAPDOOR, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(BAMBOO_TRAPDOOR, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(PALE_OAK_TRAPDOOR, 5, 20);

        BlockFactoryHelper.MosicFamily("oak", "", Blocks.OAK_PLANKS, true);
        BlockFactoryHelper.MosicFamily("spruce", "", Blocks.SPRUCE_PLANKS, true);
        BlockFactoryHelper.MosicFamily("jungle", "", Blocks.JUNGLE_PLANKS, true);
        BlockFactoryHelper.MosicFamily("birch", "", Blocks.BIRCH_PLANKS, true);
        BlockFactoryHelper.MosicFamily("dark_oak", "", Blocks.DARK_OAK_PLANKS, true);
        BlockFactoryHelper.MosicFamily("acacia", "", Blocks.ACACIA_PLANKS, true);
        BlockFactoryHelper.MosicFamily("mangrove", "", Blocks.MANGROVE_PLANKS, true);
        BlockFactoryHelper.MosicFamily("cherry", "", Blocks.CHERRY_PLANKS, true);
        BlockFactoryHelper.MosicFamily("pale_oak", "", Blocks.CHERRY_PLANKS, true);

        BlockFactoryHelper.MosicFamily("crimson", "", Blocks.CRIMSON_PLANKS, false);
        BlockFactoryHelper.MosicFamily("warped", "", Blocks.WARPED_PLANKS, false);


    }

    public static void onInitializeOLD() {

        //Textured Wools
        Block TEXTURED_WHITE_WOOL = register("textured_wool_white", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL));
        Block TEXTURED_LIGHT_GRAY_WOOL = register("textured_wool_light_gray", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.LIGHT_GRAY_WOOL));
        Block TEXTURED_GRAY_WOOL = register("textured_wool_gray", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GRAY_WOOL));
        Block TEXTURED_BLACK_WOOL = register("textured_wool_black", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BLACK_WOOL));
        Block TEXTURED_BROWN_WOOL = register("textured_wool_brown", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BROWN_WOOL));
        Block TEXTURED_RED_WOOL = register("textured_wool_red", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.RED_WOOL));
        Block TEXTURED_ORANGE_WOOL = register("textured_wool_orange", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.ORANGE_WOOL));
        Block TEXTURED_YELLOW_WOOL = register("textured_wool_yellow", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.YELLOW_WOOL));
        Block TEXTURED_LIME_WOOL = register("textured_wool_lime", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.LIME_WOOL));
        Block TEXTURED_GREEN_WOOL = register("textured_wool_green", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GREEN_WOOL));
        Block TEXTURED_CYAN_WOOL = register("textured_wool_cyan", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CYAN_WOOL));
        Block TEXTURED_LIGHT_BLUE_WOOL = register("textured_wool_light_blue", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.LIGHT_BLUE_WOOL));
        Block TEXTURED_BLUE_WOOL = register("textured_wool_blue", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BLUE_WOOL));
        Block TEXTURED_PURPLE_WOOL = register("textured_wool_purple", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.PURPLE_WOOL));
        Block TEXTURED_MAGENTA_WOOL = register("textured_wool_magenta", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.MAGENTA_WOOL));
        Block TEXTURED_PINK_WOOL = register("textured_wool_pink", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.PINK_WOOL));

        FlammableBlockRegistry.getDefaultInstance().add(TEXTURED_WHITE_WOOL, 30, 60);
        FlammableBlockRegistry.getDefaultInstance().add(TEXTURED_LIGHT_GRAY_WOOL, 30, 60);
        FlammableBlockRegistry.getDefaultInstance().add(TEXTURED_GRAY_WOOL, 30, 60);
        FlammableBlockRegistry.getDefaultInstance().add(TEXTURED_BLACK_WOOL, 30, 60);
        FlammableBlockRegistry.getDefaultInstance().add(TEXTURED_BROWN_WOOL, 30, 60);
        FlammableBlockRegistry.getDefaultInstance().add(TEXTURED_RED_WOOL, 30, 60);
        FlammableBlockRegistry.getDefaultInstance().add(TEXTURED_ORANGE_WOOL, 30, 60);
        FlammableBlockRegistry.getDefaultInstance().add(TEXTURED_YELLOW_WOOL, 30, 60);
        FlammableBlockRegistry.getDefaultInstance().add(TEXTURED_LIME_WOOL, 30, 60);
        FlammableBlockRegistry.getDefaultInstance().add(TEXTURED_GREEN_WOOL, 30, 60);
        FlammableBlockRegistry.getDefaultInstance().add(TEXTURED_CYAN_WOOL, 30, 60);
        FlammableBlockRegistry.getDefaultInstance().add(TEXTURED_LIGHT_BLUE_WOOL, 30, 60);
        FlammableBlockRegistry.getDefaultInstance().add(TEXTURED_BLUE_WOOL, 30, 60);
        FlammableBlockRegistry.getDefaultInstance().add(TEXTURED_PURPLE_WOOL, 30, 60);
        FlammableBlockRegistry.getDefaultInstance().add(TEXTURED_MAGENTA_WOOL, 30, 60);
        FlammableBlockRegistry.getDefaultInstance().add(TEXTURED_PINK_WOOL, 30, 60);

    }

    public static void OxidizableFamily() { /* Make this Dynamic */
        Block COPPER = register("copper_glass_door",
            settings -> new WeatheringCopperDoorBlock(BlockSetType.COPPER, WeatheringCopper.WeatherState.UNAFFECTED, settings),
            BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_DOOR));

        Block EXPOSED_COPPER = register("exposed_copper_glass_door",
            settings -> new WeatheringCopperDoorBlock(BlockSetType.COPPER, WeatheringCopper.WeatherState.EXPOSED, settings),
            BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_DOOR));

        Block OXIDIZED_COPPER = register("oxidized_copper_glass_door",
            settings -> new WeatheringCopperDoorBlock(BlockSetType.COPPER, WeatheringCopper.WeatherState.OXIDIZED, settings),
            BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_DOOR));

        Block WEATHERED_COPPER = register("weathered_copper_glass_door",
            settings -> new WeatheringCopperDoorBlock(BlockSetType.COPPER, WeatheringCopper.WeatherState.WEATHERED, settings),
            BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_DOOR));

        Block WAXED_COPPER = register("waxed_copper_glass_door",
            settings -> new DoorBlock(BlockSetType.COPPER, settings),
            BlockBehaviour.Properties.ofFullCopy(COPPER));

        Block WAXED_EXPOSED_COPPER = register("waxed_exposed_copper_glass_door",
            settings -> new DoorBlock(BlockSetType.COPPER, settings),
            BlockBehaviour.Properties.ofFullCopy(EXPOSED_COPPER));

        Block WAXED_OXIDIZED_COPPER = register("waxed_oxidized_copper_glass_door",
            settings -> new DoorBlock(BlockSetType.COPPER, settings),
            BlockBehaviour.Properties.ofFullCopy(OXIDIZED_COPPER));

        Block WAXED_WEATHERED_COPPER = register("waxed_weathered_copper_glass_door",
            settings -> new DoorBlock(BlockSetType.COPPER, settings),
            BlockBehaviour.Properties.ofFullCopy(WEATHERED_COPPER));

        OxidizableBlocksRegistry.registerOxidizableBlockPair(COPPER, EXPOSED_COPPER);
        OxidizableBlocksRegistry.registerOxidizableBlockPair(EXPOSED_COPPER, OXIDIZED_COPPER);
        OxidizableBlocksRegistry.registerOxidizableBlockPair(OXIDIZED_COPPER, WEATHERED_COPPER);

        OxidizableBlocksRegistry.registerWaxableBlockPair(COPPER, WAXED_COPPER);
        OxidizableBlocksRegistry.registerWaxableBlockPair(EXPOSED_COPPER, WAXED_EXPOSED_COPPER);
        OxidizableBlocksRegistry.registerWaxableBlockPair(OXIDIZED_COPPER, WAXED_OXIDIZED_COPPER);
        OxidizableBlocksRegistry.registerWaxableBlockPair(WEATHERED_COPPER, WAXED_WEATHERED_COPPER);

        // Trapdoors

        Block COPPER_1 = register("copper_glass_trapdoor",
            settings -> new WeatheringCopperTrapDoorBlock(BlockSetType.COPPER, WeatheringCopper.WeatherState.UNAFFECTED, settings),
            BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_TRAPDOOR));

        Block EXPOSED_COPPER_1 = register("exposed_copper_glass_trapdoor",
            settings -> new WeatheringCopperTrapDoorBlock(BlockSetType.COPPER, WeatheringCopper.WeatherState.EXPOSED, settings),
            BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_TRAPDOOR));

        Block OXIDIZED_COPPER_1 = register("oxidized_copper_glass_trapdoor",
            settings -> new WeatheringCopperTrapDoorBlock(BlockSetType.COPPER, WeatheringCopper.WeatherState.OXIDIZED, settings),
            BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_TRAPDOOR));

        Block WEATHERED_COPPER_1 = register("weathered_copper_glass_trapdoor",
            settings -> new WeatheringCopperTrapDoorBlock(BlockSetType.COPPER, WeatheringCopper.WeatherState.WEATHERED, settings),
            BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_TRAPDOOR));

        Block WAXED_COPPER_1 = register("waxed_copper_glass_trapdoor",
            settings -> new TrapDoorBlock(BlockSetType.COPPER, settings),
            BlockBehaviour.Properties.ofFullCopy(COPPER_1));

        Block WAXED_EXPOSED_COPPER_1 = register("waxed_exposed_copper_glass_trapdoor",
            settings -> new TrapDoorBlock(BlockSetType.COPPER, settings),
            BlockBehaviour.Properties.ofFullCopy(EXPOSED_COPPER_1));

        Block WAXED_OXIDIZED_COPPER_1 = register("waxed_oxidized_copper_glass_trapdoor",
            settings -> new TrapDoorBlock(BlockSetType.COPPER, settings),
            BlockBehaviour.Properties.ofFullCopy(OXIDIZED_COPPER_1));

        Block WAXED_WEATHERED_COPPER_1 = register("waxed_weathered_copper_glass_trapdoor",
            settings -> new TrapDoorBlock(BlockSetType.COPPER, settings),
            BlockBehaviour.Properties.ofFullCopy(WEATHERED_COPPER_1));

        OxidizableBlocksRegistry.registerOxidizableBlockPair(COPPER_1, EXPOSED_COPPER_1);
        OxidizableBlocksRegistry.registerOxidizableBlockPair(EXPOSED_COPPER_1, OXIDIZED_COPPER_1);
        OxidizableBlocksRegistry.registerOxidizableBlockPair(OXIDIZED_COPPER_1, WEATHERED_COPPER_1);

        OxidizableBlocksRegistry.registerWaxableBlockPair(COPPER_1, WAXED_COPPER_1);
        OxidizableBlocksRegistry.registerWaxableBlockPair(EXPOSED_COPPER_1, WAXED_EXPOSED_COPPER_1);
        OxidizableBlocksRegistry.registerWaxableBlockPair(OXIDIZED_COPPER_1, WAXED_OXIDIZED_COPPER_1);
        OxidizableBlocksRegistry.registerWaxableBlockPair(WEATHERED_COPPER_1, WAXED_WEATHERED_COPPER_1);
    }

    public static void HorizontalBannersFamily() {
        for (DyeColor color : DyeColor.values()) {
            Block block = registerWithoutItem(
                color.getName() + "_horizontal_banner",
                properties -> new HorizontalBannerBlock(color, properties),
                BlockBehaviour.Properties.of()
                    .mapColor(MapColor.WOOD)
                    .forceSolidOn()
                    .instrument(NoteBlockInstrument.BASS)
                    .noCollision()
                    .strength(1.0F)
                    .sound(SoundType.WOOD)
                    .ignitedByLava()
            );

            Block block_wall = registerWithoutItem(
                color.getName() + "_wall_horizontal_banner",
                properties -> new HorizontalWallBannerBlock(color, properties),
                wallVariant(block, true)
                    .mapColor(MapColor.WOOD)
                    .forceSolidOn()
                    .instrument(NoteBlockInstrument.BASS)
                    .noCollision()
                    .strength(1.0F)
                    .sound(SoundType.WOOD)
                    .ignitedByLava()
            );

            HORIZONTAL_BANNERS.put(color, (HorizontalBannerBlock) block);
            HORIZONTAL_WALL_BANNERS.put(color, (HorizontalWallBannerBlock) block_wall);

            ResourceLocation blockId = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, color.getName() + "_horizontal_banner");
            ResourceLocation blockId2 = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, color.getName() + "_wall_horizontal_banner");
            BlockList.put(blockId, block);
            BlockList.put(blockId2, block_wall);

        }
    }











    // ## Static Block Registated

    // # Adding PHANTOM TORCH
    public static final Block PHANTOM_TORCH = registerWithoutItem(
        "phantom_torch",
        settings -> new TorchBlock(ParticleTypes.FLAME, settings),
		BlockBehaviour.Properties.of().noCollision().instabreak().lightLevel(state -> 14).sound(SoundType.WOOD).pushReaction(PushReaction.DESTROY)
	);

    public static final Block PHANTOM_WALL_TORCH = registerWithoutItem(
        "phantom_wall_torch",
        settings -> new WallTorchBlock(ParticleTypes.FLAME, settings),
        wallVariant(PHANTOM_TORCH, true).noCollision().instabreak().lightLevel(blockStatex -> 14).sound(SoundType.WOOD).pushReaction(PushReaction.DESTROY)
		//copyLootTable(PHANTOM_TORCH, true).noCollision().instabreak().lightLevel(state -> 14).sound(SoundType.WOOD).pushReaction(PushReaction.DESTROY)
	);

        // # Skull/Heads

    public static final Block DROWNED_HEAD = registerWithoutItem(
        "drown_head",
        properties -> new SkullBlock(ModSkullStype.DROWNED, properties),
        BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.ZOMBIE).strength(1.0F).pushReaction(PushReaction.DESTROY)
    );

    public static final Block DROWNED_WALL_HEAD = registerWithoutItem(
        "drown_wall_head",
        properties -> new WallSkullBlock(ModSkullStype.DROWNED, properties),
        wallVariant(DROWNED_HEAD, true).strength(1.0F).pushReaction(PushReaction.DESTROY)
    );

    public static final Block BLAZE_HEAD = registerWithoutItem(
        "blaze_head",
        properties -> new SkullBlock(ModSkullStype.BLAZE, properties),
        BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.ZOMBIE).strength(1.0F).pushReaction(PushReaction.DESTROY)
    );

    public static final Block BLAZE_WALL_HEAD = registerWithoutItem(
        "blaze_wall_head",
        properties -> new WallSkullBlock(ModSkullStype.BLAZE, properties),
        wallVariant(BLAZE_HEAD, true).strength(1.0F).pushReaction(PushReaction.DESTROY)
    );

    public static final Block BREEZE_HEAD = registerWithoutItem(
        "breeze_head",
        properties -> new SkullBlock(ModSkullStype.BREEZE, properties),
        BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.ZOMBIE).strength(1.0F).pushReaction(PushReaction.DESTROY)
    );

    public static final Block BREEZE_WALL_HEAD = registerWithoutItem(
        "breeze_wall_head",
        properties -> new WallSkullBlock(ModSkullStype.BREEZE, properties),
        wallVariant(BREEZE_HEAD, true).strength(1.0F).pushReaction(PushReaction.DESTROY)
    );

    public static final Block HUSK_HEAD = registerWithoutItem(
        "husk_head",
        properties -> new SkullBlock(ModSkullStype.HUSK, properties),
        BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.ZOMBIE).strength(1.0F).pushReaction(PushReaction.DESTROY)
    );

    public static final Block HUSK_WALL_HEAD = registerWithoutItem(
        "husk_wall_head",
        properties -> new WallSkullBlock(ModSkullStype.HUSK, properties),
        wallVariant(HUSK_HEAD, true).strength(1.0F).pushReaction(PushReaction.DESTROY)
    );

    public static final Block STRAY_SKULL = registerWithoutItem(
        "stray_skull",
        properties -> new SkullBlock(ModSkullStype.STRAY, properties),
        BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.ZOMBIE).strength(1.0F).pushReaction(PushReaction.DESTROY)
    );

    public static final Block STRAY_WALL_SKULL = registerWithoutItem(
        "stray_wall_skull",
        properties -> new WallSkullBlock(ModSkullStype.STRAY, properties),
        wallVariant(STRAY_SKULL, true).strength(1.0F).pushReaction(PushReaction.DESTROY)
    );

    public static final Block BOGGED_SKULL = registerWithoutItem(
        "bogged_skull",
        properties -> new SkullBlock(ModSkullStype.BOGGED, properties),
        BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.ZOMBIE).strength(1.0F).pushReaction(PushReaction.DESTROY)
    );

    public static final Block BOGGED_WALL_SKULL = registerWithoutItem(
        "bogged_wall_skull",
        properties -> new WallSkullBlock(ModSkullStype.BOGGED, properties),
        wallVariant(BOGGED_SKULL, true).strength(1.0F).pushReaction(PushReaction.DESTROY)
    );

    public static final Block ZOMBIE_LOBBER_HEAD = registerWithoutItem(
        "zombie_lobber_head",
        properties -> new SkullBlock(ModSkullStype.ZOMBIE_LOBBER, properties),
        BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.ZOMBIE).strength(1.0F).pushReaction(PushReaction.DESTROY)
    );

    public static final Block ZOMBIE_LOBBER_WALL_HEAD = registerWithoutItem(
        "zombie_lobber_wall_head",
        properties -> new WallSkullBlock(ModSkullStype.ZOMBIE_LOBBER, properties),
        wallVariant(ZOMBIE_LOBBER_HEAD, true).strength(1.0F).pushReaction(PushReaction.DESTROY)
    );

    public static final Block ZOMBIE_SCORCHED_HEAD = registerWithoutItem(
        "zombie_scorched_head",
        properties -> new SkullBlock(ModSkullStype.ZOMBIE_SCORCHED, properties),
        BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.ZOMBIE).strength(1.0F).pushReaction(PushReaction.DESTROY)
    );

    public static final Block ZOMBIE_SCORCHED_WALL_HEAD = registerWithoutItem(
        "zombie_scorched_wall_head",
        properties -> new WallSkullBlock(ModSkullStype.ZOMBIE_SCORCHED, properties),
        wallVariant(ZOMBIE_SCORCHED_HEAD, true).strength(1.0F).pushReaction(PushReaction.DESTROY)
    );

    public static final Block ZOMBIE_SLIMED_HEAD = registerWithoutItem(
        "zombie_slimed_head",
        properties -> new SkullBlock(ModSkullStype.ZOMBIE_SLIMED, properties),
        BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.ZOMBIE).strength(1.0F).pushReaction(PushReaction.DESTROY)
    );

    public static final Block ZOMBIE_SLIMED_WALL_HEAD = registerWithoutItem(
        "zombie_slimed_wall_head",
        properties -> new WallSkullBlock(ModSkullStype.ZOMBIE_SLIMED, properties),
        wallVariant(ZOMBIE_SLIMED_HEAD, true).strength(1.0F).pushReaction(PushReaction.DESTROY)
    );

    public static final Block ZOMBIE_FROSTBITTEN_HEAD = registerWithoutItem(
        "zombie_frostbitten_head",
        properties -> new SkullBlock(ModSkullStype.ZOMBIE_FROSTBITTEN, properties),
        BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.ZOMBIE).strength(1.0F).pushReaction(PushReaction.DESTROY)
    );

    public static final Block ZOMBIE_FROSTBITTEN_WALL_HEAD = registerWithoutItem(
        "zombie_frostbitten_wall_head",
        properties -> new WallSkullBlock(ModSkullStype.ZOMBIE_FROSTBITTEN, properties),
        wallVariant(ZOMBIE_FROSTBITTEN_HEAD, true).strength(1.0F).pushReaction(PushReaction.DESTROY)
    );

    public static final Block ZOMBIE_FUNGAL_HEAD = registerWithoutItem(
        "zombie_fungal_head",
        properties -> new SkullBlock(ModSkullStype.ZOMBIE_FUNGAL, properties),
        BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.ZOMBIE).strength(1.0F).pushReaction(PushReaction.DESTROY)
    );

    public static final Block ZOMBIE_FUNGAL_WALL_HEAD = registerWithoutItem(
        "zombie_fungal_wall_head",
        properties -> new WallSkullBlock(ModSkullStype.ZOMBIE_FUNGAL, properties),
        wallVariant(ZOMBIE_FUNGAL_HEAD, true).strength(1.0F).pushReaction(PushReaction.DESTROY)
    );

    public static final Block SPOREBONE_SKULL = registerWithoutItem(
        "sporebone_skull",
        properties -> new SkullBlock(ModSkullStype.SPOREBONE, properties),
        BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.ZOMBIE).strength(1.0F).pushReaction(PushReaction.DESTROY)
    );

    public static final Block SPOREBONE_SKULL_HEAD = registerWithoutItem(
        "sporebone_wall_skull",
        properties -> new WallSkullBlock(ModSkullStype.SPOREBONE, properties),
        wallVariant(SPOREBONE_SKULL, true).strength(1.0F).pushReaction(PushReaction.DESTROY)
    );

    public static final Block SPOREPER_HEAD = registerWithoutItem(
        "sporeper_head",
        properties -> new SkullBlock(ModSkullStype.SPOREPER, properties),
        BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.ZOMBIE).strength(1.0F).pushReaction(PushReaction.DESTROY)
    );

    public static final Block SPOREPER_WALL_HEAD = registerWithoutItem(
        "sporeper_wall_head",
        properties -> new WallSkullBlock(ModSkullStype.SPOREPER, properties),
        wallVariant(SPOREPER_HEAD, true).strength(1.0F).pushReaction(PushReaction.DESTROY)
    );

    public static final Block SOUL_SKELETON_SKULL = registerWithoutItem(
        "soul_skeleton_skull",
        properties -> new SkullBlock(ModSkullStype.SOUL_SKELETON, properties),
        BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.ZOMBIE).strength(1.0F).pushReaction(PushReaction.DESTROY)
    );

    public static final Block SOUL_SKELETON_WALL_SKULL = registerWithoutItem(
        "soul_skeleton_wall_skull",
        properties -> new WallSkullBlock(ModSkullStype.SOUL_SKELETON, properties),
        wallVariant(SOUL_SKELETON_SKULL, true).strength(1.0F).pushReaction(PushReaction.DESTROY)
    );

    public static final Block CORAL_SKELETON_BRAIN_SKULL = registerWithoutItem(
        "coral_skeleton_brain",
        properties -> new SkullBlock(ModSkullStype.CORAL_SKELETON_BRAIN, properties),
        BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.SKELETON).strength(1.0F).pushReaction(PushReaction.DESTROY)
    );
    public static final Block CORAL_SKELETON_BRAIN_WALL_SKULL = registerWithoutItem(
        "coral_skeleton_brain_wall_skull",
        properties -> new WallSkullBlock(ModSkullStype.CORAL_SKELETON_BRAIN, properties),
        wallVariant(CORAL_SKELETON_BRAIN_SKULL, true).strength(1.0F).pushReaction(PushReaction.DESTROY)
    );

    public static final Block CORAL_SKELETON_FIRE_SKULL = registerWithoutItem(
        "coral_skeleton_fire",
        properties -> new SkullBlock(ModSkullStype.CORAL_SKELETON_FIRE, properties),
        BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.SKELETON).strength(1.0F).pushReaction(PushReaction.DESTROY)
    );

    public static final Block CORAL_SKELETON_FIRE_WALL_SKULL = registerWithoutItem(
        "coral_skeleton_fire_wall_skull",
        properties -> new WallSkullBlock(ModSkullStype.CORAL_SKELETON_FIRE, properties),
        wallVariant(CORAL_SKELETON_FIRE_SKULL, true).strength(1.0F).pushReaction(PushReaction.DESTROY)
    );

    public static final Block CORAL_SKELETON_HORN_SKULL = registerWithoutItem(
        "coral_skeleton_horn",
        properties -> new SkullBlock(ModSkullStype.CORAL_SKELETON_HORN, properties),
        BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.SKELETON).strength(1.0F).pushReaction(PushReaction.DESTROY)
    );

    public static final Block CORAL_SKELETON_HORN_WALL_SKULL = registerWithoutItem(
        "coral_skeleton_horn_wall_skull",
        properties -> new WallSkullBlock(ModSkullStype.CORAL_SKELETON_HORN, properties),
        wallVariant(CORAL_SKELETON_HORN_SKULL, true).strength(1.0F).pushReaction(PushReaction.DESTROY)
    );

    public static final Block CORAL_SKELETON_TUBE_SKULL = registerWithoutItem(
        "coral_skeleton_tube",
        properties -> new SkullBlock(ModSkullStype.CORAL_SKELETON_TUBE, properties),
        BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.SKELETON).strength(1.0F).pushReaction(PushReaction.DESTROY)
    );

    public static final Block CORAL_SKELETON_TUBE_WALL_SKULL = registerWithoutItem(
        "coral_skeleton_tube_wall_skull",
        properties -> new WallSkullBlock(ModSkullStype.CORAL_SKELETON_TUBE, properties),
        wallVariant(CORAL_SKELETON_TUBE_SKULL, true).strength(1.0F).pushReaction(PushReaction.DESTROY)
    );

    public static final Block CORAL_SKELETON_BUBBLE_SKULL = registerWithoutItem(
        "coral_skeleton_bubble",
        properties -> new SkullBlock(ModSkullStype.CORAL_SKELETON_BUBBLE, properties),
        BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.SKELETON).strength(1.0F).pushReaction(PushReaction.DESTROY)
    );

    public static final Block CORAL_SKELETON_BUBBLE_WALL_SKULL = registerWithoutItem(
        "coral_skeleton_bubble_wall_skull",
        properties -> new WallSkullBlock(ModSkullStype.CORAL_SKELETON_BUBBLE, properties),
        wallVariant(CORAL_SKELETON_BUBBLE_SKULL, true).strength(1.0F).pushReaction(PushReaction.DESTROY)
    );

    public static final Block CORAL_SKELETON_UNUSED_SKULL = registerWithoutItem(
        "coral_skeleton_unused",
        properties -> new SkullBlock(ModSkullStype.CORAL_SKELETON_UNUSED, properties),
        BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.SKELETON).strength(1.0F).pushReaction(PushReaction.DESTROY)
    );

    public static final Block CORAL_SKELETON_UNUSED_WALL_SKULL = registerWithoutItem(
        "coral_skeleton_unused_wall_skull",
        properties -> new WallSkullBlock(ModSkullStype.CORAL_SKELETON_UNUSED, properties),
        wallVariant(CORAL_SKELETON_UNUSED_SKULL, true).strength(1.0F).pushReaction(PushReaction.DESTROY)
    );

    public static final Block METAL_SKELETON_DEFAULT_SKULL = registerWithoutItem(
        "metal_skeleton_default",
        properties -> new SkullBlock(ModSkullStype.METAL_SKELETON_DEFAULT, properties),
        BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.SKELETON).strength(1.0F).pushReaction(PushReaction.DESTROY)
    );

    public static final Block METAL_SKELETON_DEFAULT_WALL_SKULL = registerWithoutItem(
        "metal_skeleton_default_wall_skull",
        properties -> new WallSkullBlock(ModSkullStype.METAL_SKELETON_DEFAULT, properties),
        wallVariant(METAL_SKELETON_DEFAULT_SKULL, true).strength(1.0F).pushReaction(PushReaction.DESTROY)
    );

    public static final Block METAL_SKELETON_RED_SKULL = registerWithoutItem(
        "metal_skeleton_red",
        properties -> new SkullBlock(ModSkullStype.METAL_SKELETON_RED, properties),
        BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.SKELETON).strength(1.0F).pushReaction(PushReaction.DESTROY)
    );

    public static final Block METAL_SKELETON_RED_WALL_SKULL = registerWithoutItem(
        "metal_skeleton_red_wall_skull",
        properties -> new WallSkullBlock(ModSkullStype.METAL_SKELETON_RED, properties),
        wallVariant(METAL_SKELETON_RED_SKULL, true).strength(1.0F).pushReaction(PushReaction.DESTROY)
    );

    public static final Block METAL_SKELETON_BLUE_SKULL = registerWithoutItem(
        "metal_skeleton_blue",
        properties -> new SkullBlock(ModSkullStype.METAL_SKELETON_BLUE, properties),
        BlockBehaviour.Properties.of().instrument(NoteBlockInstrument.SKELETON).strength(1.0F).pushReaction(PushReaction.DESTROY)
    );

    public static final Block METAL_SKELETON_BLUE_WALL_SKULL = registerWithoutItem(
        "metal_skeleton_blue_wall_skull",
        properties -> new WallSkullBlock(ModSkullStype.METAL_SKELETON_BLUE, properties),
        wallVariant(METAL_SKELETON_BLUE_SKULL, true).strength(1.0F).pushReaction(PushReaction.DESTROY)
    );




    // # Adding POINTED_ICE
    public static final Block POINTED_ICE = register(
		"pointed_ice",
		PointedIceBlock::new,
			BlockBehaviour.Properties.of()
				.mapColor(MapColor.ICE)
				.forceSolidOn()
				.noOcclusion()
				.sound(SoundType.GLASS)
				.randomTicks()
				.strength(1.5F, 3.0F)
				.dynamicShape()
				.offsetType(BlockBehaviour.OffsetType.XZ)
				.pushReaction(PushReaction.DESTROY)
			    .isRedstoneConductor((state, world, pos) -> false)
		
	);

    // # Adding DARK_ENCHANTMENT_TABLE
    public static final Block DARK_ENCHANTMENT_TABLE = register(
        "dark_enchantment_table", 
        DarkEnchantingTableBlock::new,
		    BlockBehaviour.Properties.of()
                .mapColor(MapColor.COLOR_RED)
                .instrument(NoteBlockInstrument.BASEDRUM)
                .requiresCorrectToolForDrops().lightLevel(state -> 7)
                .strength(5.0F, 1200.0F)
    );

    // # adding Abyssal_stone
    public static final Block ABYSSAL_STONE = register(
        "abyssal_stone", (settings) -> new Block(settings), BlockBehaviour.Properties.ofFullCopy(Blocks.BLACKSTONE)
    );

    public static final Block SHROOMIUM_BLOCK = register(
        "shroomium",
        ShroomiumBlock::new,
        BlockBehaviour.Properties.ofFullCopy(Blocks.MUD)
        //BlockBehaviour.Properties.of()
            .mapColor(MapColor.WARPED_NYLIUM)
            .isValidSpawn(Blocks::always)
            .isRedstoneConductor(Blocks::always)
            .isViewBlocking(Blocks::always)
            .isSuffocating(Blocks::always)
            .sound(SoundType.MUD)
            
    );

    // # Adding DARK_ENCHANTMENT_TABLE
    public static final Block MUSHROOM_AURA_BLOCK = register(
        "mushroom_aura_block", 
        MushroomAuraBlock::new,
		    BlockBehaviour.Properties.of()
                .instabreak()
                .noCollision()
                .sound(SoundType.SPORE_BLOSSOM)
                .pushReaction(PushReaction.DESTROY)
                .lightLevel(state -> 3)
                .noOcclusion()
    );

    public static final Block MUSHROOM_AURA_BLOCK_2 = register(
        "mushroom_aura_block_2", 
        MushroomAuraBlock_2::new,
		    BlockBehaviour.Properties.of()
                .instabreak()
                .noCollision()
                .sound(SoundType.SPORE_BLOSSOM)
                .pushReaction(PushReaction.DESTROY)
                .lightLevel(state -> 3)
                .noOcclusion()
    );

    // # Adding SILKWORM_VINES
    public static final Block SILKWORM_VINES = register(
		"silkworm_vines",
		SilkwormVineBlock::new,
		BlockBehaviour.Properties.of()
			.mapColor(MapColor.NETHER)
			.randomTicks()
			.noCollision()
			.instabreak()
			.sound(SoundType.WEEPING_VINES)
			.pushReaction(PushReaction.DESTROY)
            .lightLevel(state -> 5)
            .noOcclusion()
	);
	public static final Block SILKWORM_VINES_PLANT = registerWithoutItem(
		"silkworm_vines_plant",
		SilkwormVinePlantBlock::new,
		BlockBehaviour.Properties.of().mapColor(MapColor.NETHER).noCollision().instabreak().sound(SoundType.WEEPING_VINES).pushReaction(PushReaction.DESTROY).lightLevel(state -> 5)
	);





    // ## Utility

    private static BlockBehaviour.Properties wallVariant(Block block, boolean bl) {
		BlockBehaviour.Properties properties2 = BlockBehaviour.Properties.of().overrideLootTable(block.getLootTable());
		if (bl) {
			properties2 = properties2.overrideDescription(block.getDescriptionId());
		}

		return properties2;
	}

    public static Block callBlock(String id) {
        try {
            ResourceLocation identifier = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, id);
            return BlockList.get(identifier);
        } catch (Exception e) {
            EntStupidStuff.LOGGER.error("An Error occurred in callBlock()... Tried to call: " + id, e);
            return null;
        }
    }

    public static ResourceKey<Block> keyOf(String id) {
        return ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, id));
    }


    // ## Registation V4 - Merged both register3 Methods,  fORMALLY REGISTATION4

    public static Block register(String id, Function<BlockBehaviour.Properties, Block> factory, BlockBehaviour.Properties settings) {

        ResourceKey<Block> key = keyOf(id);

		Block block = (Block)factory.apply(settings.setId(key));

        ResourceLocation blockId = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, id);
        BlockList.put(blockId, block);

        System.out.println("Adding Block: " + blockId);

        registerBlockItem(id, block);
        ModGroup.addToDefault(id);

		return Registry.register(BuiltInRegistries.BLOCK, key, block);
	}

    private static void registerBlockItem(String name, Block block) {

        BlockItem item = new BlockItem(block, new Item.Properties().useBlockDescriptionPrefix()
            .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, name))));

        Registry.register(BuiltInRegistries.ITEM, ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, name), item);

        ItemList.put(ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, name), item);
        System.out.println("Adding Item: " + item);
    }

    public static Block registerWithoutItem(String id, Function<BlockBehaviour.Properties, Block> factory, BlockBehaviour.Properties settings) {
        ResourceKey<Block> key = keyOf(id);
		Block block = (Block)factory.apply(settings.setId(key));

        ResourceLocation blockId = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, id);
        BlockList.put(blockId, block);

        System.out.println("Adding Block without Item: " + blockId);
		return Registry.register(BuiltInRegistries.BLOCK, key, block);
	}


    // ## Registation V4 (Custom)

    public static Block register_customBanner(String id, Function<BlockBehaviour.Properties, Block> factory, BlockBehaviour.Properties settings) {
        ResourceKey<Block> key = keyOf(id);
		Block block = (Block)factory.apply(settings.setId(key));

        ResourceLocation blockId = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, id);
        BlockList.put(blockId, block);

        System.out.println("Adding Block without Item: (Horizontal Banner): " + blockId);

        registerBlockItem_Mushroom(id, block);

		return Registry.register(BuiltInRegistries.BLOCK, key, block);
	}
    
    public static Block register_custom_mushroom(String id, Function<BlockBehaviour.Properties, Block> factory, BlockBehaviour.Properties settings) {
        ResourceKey<Block> key = keyOf(id);
		Block block = (Block)factory.apply(settings.setId(key));

        ResourceLocation blockId = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, id);
        BlockList.put(blockId, block);

        System.out.println("Adding Block (custom_mushroom): " + blockId);

        registerBlockItem_Mushroom(id, block);
        ModGroup.addToDefault(id);

		return Registry.register(BuiltInRegistries.BLOCK, key, block);
	}

    private static void registerBlockItem_Mushroom(String name, Block block) {

        BlockItem item = new BlockItem(block, new Item.Properties().food(FOOD_SHROOM, CONS_SHROOM).useBlockDescriptionPrefix()
        .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, name))));

        Registry.register(BuiltInRegistries.ITEM, ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, name), item);

        ItemList.put(ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, name), item);
        System.out.println("Adding Item: (custom_mushroom)" + item);
    }

    public static Block register_dates(String id, Function<BlockBehaviour.Properties, Block> factory, BlockBehaviour.Properties settings) {
        ResourceKey<Block> key = keyOf(id);
		Block block = (Block)factory.apply(settings.setId(key));

        ResourceLocation blockId = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, id);
        BlockList.put(blockId, block);

        System.out.println("Adding Block (dates): " + blockId);

        registerBlockItem_Date(id, block);
        ModGroup.addToDefault(id);

		return Registry.register(BuiltInRegistries.BLOCK, key, block);
	}

    private static void registerBlockItem_Date(String name, Block block) {

        BlockItem item = new BlockItem(block, new Item.Properties().food(FOOD_SHROOM, CONS_SHROOM).useBlockDescriptionPrefix()
        .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, name))));

        Registry.register(BuiltInRegistries.ITEM, ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, name), item);

        ItemList.put(ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, name), item);
        System.out.println("Adding Item: (dates)" + item);
    }

    // ## Adding Food Times

    public static final FoodProperties FOOD_SHROOM = new FoodProperties.Builder().nutrition(1).saturationModifier(0.1F).alwaysEdible().build();

    public static final Consumable CONS_SHROOM = Consumable.builder()
        .consumeSeconds(1.6F).animation(ItemUseAnimation.EAT).sound(SoundEvents.GENERIC_EAT).hasConsumeParticles(true)
        .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(ModEffects.HALLUC, 600, 0), 0.8F))
    .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.NAUSEA, 600, 0), 0.8F)).build();


}
