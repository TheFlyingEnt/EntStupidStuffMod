package net.ent.entstupidstuff.block;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.effects.ModEffects;
import net.ent.entstupidstuff.item.ModGroup;
import net.ent.entstupidstuff.world.ModConfiguredFeatures;
import net.ent.entstupidstuff.world.tree.SaplingGeneratorFactory;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.OxidizableBlocksRegistry;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.BlockFamilies;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.FlowerBedBlock;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.IronBarsBlock;
import net.minecraft.world.level.block.LanternBlock;
import net.minecraft.world.level.block.PressurePlateBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.TorchBlock;
import net.minecraft.world.level.block.TransparentBlock;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.WallTorchBlock;
import net.minecraft.world.level.block.WaterloggedTransparentBlock;
import net.minecraft.world.level.block.WeatheringCopper;
import net.minecraft.world.level.block.WeatheringCopperDoorBlock;
import net.minecraft.world.level.block.WeatheringCopperTrapDoorBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.properties.WoodType;
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
        Block REDWOOD_PLANKS = register3("redwood" + "_planks" + "", Block::new, (BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).mapColor((MapColor.CRIMSON_NYLIUM))));
        BlockGroupFactory.groupWoodFamilty("redwood", "", REDWOOD_PLANKS, true, MapColor.CRIMSON_NYLIUM, MapColor.CRIMSON_NYLIUM);

        // # Added FIR Natural + Planks
        Block FIR_PLANKS = register3("fir" + "_planks" + "", Block::new, (BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).mapColor((MapColor.TERRACOTTA_GRAY))));
        BlockGroupFactory.groupWoodFamilty("fir", "", FIR_PLANKS, true, MapColor.TERRACOTTA_GRAY, MapColor.PODZOL);
        BlockFactoryHelper.addSaplings("fir", SaplingGeneratorFactory.FIR);

        // # Added MAPLE Natural + Planks
        Block MAPLE_PLANKS = register3("maple" + "_planks" + "", Block::new, (BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).mapColor((MapColor.PODZOL))));
        BlockGroupFactory.groupWoodFamilty("maple", "", MAPLE_PLANKS, true, MapColor.DIRT, MapColor.PODZOL);
        BlockFactoryHelper.addSaplings("maple", SaplingGeneratorFactory.MAPLE);
        register3("orange_petals", FlowerBedBlock::new,
            BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollision().sound(SoundType.PINK_PETALS).pushReaction(PushReaction.DESTROY)
        );

        // # Added PHANTOM Natural + Planks + Lantern
        Block PHANTOM_PLANKS = register3("phantom" + "_planks" + "", Block::new, (BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).mapColor((MapColor.GLOW_LICHEN)).noOcclusion()));
        BlockGroupFactory.groupWoodFamilty("phantom", "", PHANTOM_PLANKS, true, MapColor.GLOW_LICHEN, MapColor.PLANT);
        Block PHANTOM_LANTERN = register3("phantom_lantern", (settings) -> new LanternBlock(settings.mapColor(MapColor.METAL).forceSolidOn().requiresCorrectToolForDrops().strength(3.5F).sound(SoundType.LANTERN).lightLevel((state) -> {
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
			ModConfiguredFeatures.HUGE_BLUE_MUSHROOM_KEY,settings
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

        Block BLUE_MUSHROOM_BLOCK = register3(
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

        Block SHROOMIUM_BLOCK = register3(
            "shroomium",
            ShroomiumBlock::new,
            BlockBehaviour.Properties.of()
                .mapColor(MapColor.WARPED_NYLIUM)
                .isValidSpawn(Blocks::always)
                .isRedstoneConductor(Blocks::always)
                .isViewBlocking(Blocks::always)
                .isSuffocating(Blocks::always)
                .sound(SoundType.MUD)
        );

        Block CRYSTAL = register3(
            "crystal_block",
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

        Block MUSHROOM_BED = register3(
            "mushroom_bed",
            MushroombedBlock::new,
            BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLUE).noCollision().sound(SoundType.FUNGUS).pushReaction(PushReaction.DESTROY).lightLevel(state -> 1)
        );

        Block POTTED_MUSHROOM_BED = register3(
            "potted_blue_mushroom", settings -> new FlowerPotBlock(BLUE_MUSHROOM, settings), Blocks.flowerPotProperties()
        );

        Block FUNGAL_SPORE_BLOSSOM = register3(
            "fungal_spore_blossom",
            MushroomSporeBlossomBlock::new,
            BlockBehaviour.Properties.of()
                .mapColor(MapColor.WARPED_STEM)
                .instabreak()
                .noCollision()
                .sound(SoundType.SPORE_BLOSSOM)
                .pushReaction(PushReaction.DESTROY)
        );

        // # Adding Andersite, Diorite and Granite
        Block ANDERSITE_BRICKS = register3("andesite_bricks", (settings) -> new Block(settings), BlockBehaviour.Properties.ofFullCopy(Blocks.POLISHED_ANDESITE));
        BlockGroupFactory.groupStoneFamily("andesite_brick", ANDERSITE_BRICKS, MapColor.STONE, true);
        register3("polished_andesite" + "_wall", (settings) -> new WallBlock(settings), BlockBehaviour.Properties.ofFullCopy(Blocks.POLISHED_ANDESITE));

        Block GRANITE_BRICKS = register3("granite_bricks", (settings) -> new Block(settings), BlockBehaviour.Properties.ofFullCopy(Blocks.POLISHED_GRANITE));
        BlockGroupFactory.groupStoneFamily("granite_brick", GRANITE_BRICKS, MapColor.DIRT, true);
        register3("polished_granite" + "_wall", (settings) -> new WallBlock(settings), BlockBehaviour.Properties.ofFullCopy(Blocks.POLISHED_GRANITE));

        Block DIORITE_BRICKS = register3("diorite_bricks", (settings) -> new Block(settings), BlockBehaviour.Properties.ofFullCopy(Blocks.POLISHED_DIORITE));
        BlockGroupFactory.groupStoneFamily("diorite_brick", DIORITE_BRICKS, MapColor.QUARTZ, true);
        register3("polished_diorite" + "_wall", (settings) -> new WallBlock(settings), BlockBehaviour.Properties.ofFullCopy(Blocks.POLISHED_DIORITE));

        // # Adding Limestone and Limestone Bricks
        Block LIMESTONE = register3("limestone", (settings) -> new Block(settings), BlockBehaviour.Properties.ofFullCopy(Blocks.LIGHT_GRAY_TERRACOTTA));
        BlockGroupFactory.groupStoneFamily("limestone", LIMESTONE, MapColor.TERRACOTTA_LIGHT_GRAY, false);

        Block POLISHED_LIMESTONE = register3("polished_limestone", (settings) -> new Block(settings), BlockBehaviour.Properties.ofFullCopy(Blocks.LIGHT_GRAY_TERRACOTTA));
        BlockGroupFactory.groupStoneFamily("polished_limestone", POLISHED_LIMESTONE, MapColor.TERRACOTTA_LIGHT_GRAY, false);

        Block LIMESTONE_BRICKS = register3("polished_limestone_bricks", (settings) -> new Block(settings), BlockBehaviour.Properties.ofFullCopy(Blocks.LIGHT_GRAY_TERRACOTTA));
        BlockGroupFactory.groupStoneFamily("polished_limestone_brick", LIMESTONE_BRICKS, MapColor.TERRACOTTA_LIGHT_GRAY, true);

        // # Adding IronGates
        Block IRON_GRATE = register3("iron_grate",
            (settings) -> new WaterloggedTransparentBlock(settings.requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.METAL).noOcclusion()),
            BlockBehaviour.Properties.of()
        );

        Block IRON_GRATE_STAIRS = register3("iron_grate_stairs",
            (settings) -> new GrateStairsBlock(IRON_GRATE.defaultBlockState(), settings),
            BlockBehaviour.Properties.ofFullCopy(IRON_GRATE)
        );

        Block IRON_GRATE_SLAB = register3("iron_grate_slab",
            (settings) -> new GrateSlabBlock(settings),
            BlockBehaviour.Properties.ofFullCopy(IRON_GRATE)
        );

        // # Adding StringGates
        Block STRING_GATE = register3("string_gate", 
            (settings) -> new IronBarsBlock(settings.noOcclusion()
                        .isValidSpawn(Blocks::never)
                        .isRedstoneConductor(Blocks::never)
                        .isSuffocating(Blocks::never)
                        .isViewBlocking(Blocks::never)),
            BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BARS)
        );

        Block STRING_BLOCK = register3("string_block", 
            (settings) -> new TransparentBlock(settings.noOcclusion()
                        .isValidSpawn(Blocks::never)
                        .isRedstoneConductor(Blocks::never)
                        .isSuffocating(Blocks::never)
                        .isViewBlocking(Blocks::never)),
            BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL)
        );

        // # Adding Abyssal Stone and Abyssal Stone Bricks
        //Block ABYSSAL_STONE = register3("abyssal_stone", (settings) -> new Block(settings), AbstractBlock.Settings.copy(Blocks.BLACKSTONE));
        BlockGroupFactory.groupStoneFamily("abyssal_stone", ABYSSAL_STONE, MapColor.WARPED_STEM, false);

        Block POLISHED_ABYSSAL_STONE = register3("polished_abyssal_stone", (settings) -> new Block(settings), BlockBehaviour.Properties.ofFullCopy(Blocks.BLACKSTONE));
        BlockGroupFactory.groupStoneFamily("polished_abyssal_stone", POLISHED_ABYSSAL_STONE, MapColor.WARPED_STEM, false);

        Block ABYSSAL_STONE_BRICKS = register3("polished_abyssal_stone_bricks", (settings) -> new Block(settings), BlockBehaviour.Properties.ofFullCopy(Blocks.BLACKSTONE));
        BlockGroupFactory.groupStoneFamily("polished_abyssal_stone_brick", ABYSSAL_STONE_BRICKS, MapColor.WARPED_STEM, true);

        Block POLISHED_ABYSSAL_STONE_SEAWEED = BlockFactory.register3("polished_abyssal_stone_seaweed",
            (settings) -> new Block(settings),
            BlockBehaviour.Properties.ofFullCopy(POLISHED_ABYSSAL_STONE)
        );

        // # Adding Thalassite Ore and Thalassite Block
        Block THALASSITE_ORE = register3(
		    "thalassite_ore",
		    settings -> new DropExperienceBlock(UniformInt.of(3, 7), settings),
		    BlockBehaviour.Properties.of().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(3.0F, 3.0F)
	    );

        Block THALASSITE_BLOCK = register3(
            "thalassite_block", 
            (settings) -> new Block(settings), 
            BlockBehaviour.Properties.ofFullCopy(Blocks.BLACKSTONE).mapColor(MapColor.WARPED_STEM).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.METAL)
        );

        // # Adding Hardend Sandstone
        Block HARDEND_SANDSTONE = register3("hardend_sandstone", (settings) -> new Block(settings), BlockBehaviour.Properties.ofFullCopy(Blocks.BLACKSTONE));
        BlockGroupFactory.groupStoneFamily("hardend_sandstone", HARDEND_SANDSTONE, MapColor.SAND, false);

        Block DATE = register_dates("date", DateBlock::new, Properties.of().mapColor(MapColor.PLANT).randomTicks().strength(0.2F, 3.0F).sound(SoundType.WOOD).noOcclusion().pushReaction(PushReaction.DESTROY));

        // # Vanilla Additions 
        addVanilla();

        // # Legacy (Textured Wool)
        onInitializeOLD();

    }

    public static void addVanilla() {
        OxidizableFamily();
        register3("iron" + "_glass_door", (settings) -> new DoorBlock(BlockSetType.IRON, settings), BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_DOOR));
        register3("iron" + "_glass_trapdoor", (settings) -> new TrapDoorBlock(BlockSetType.IRON, settings), BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_TRAPDOOR));

        Block OAK_DOOR = register3("oak_glass_door", (settings) -> new DoorBlock(BlockSetType.OAK, settings), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR));
        Block SPRUCE_DOOR = register3("spruce_glass_door", (settings) -> new DoorBlock(BlockSetType.OAK, settings), BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_DOOR));
        Block JUNGLE_DOOR = register3("jungle_glass_door", (settings) -> new DoorBlock(BlockSetType.OAK, settings), BlockBehaviour.Properties.ofFullCopy(Blocks.JUNGLE_DOOR));
        Block BIRCH_DOOR = register3("birch_glass_door", (settings) -> new DoorBlock(BlockSetType.OAK, settings), BlockBehaviour.Properties.ofFullCopy(Blocks.BIRCH_DOOR));
        Block DARK_OAK_DOOR = register3("dark_oak_glass_door", (settings) -> new DoorBlock(BlockSetType.OAK, settings), BlockBehaviour.Properties.ofFullCopy(Blocks.DARK_OAK_DOOR));
        Block ACACIA_DOOR = register3("acacia_glass_door", (settings) -> new DoorBlock(BlockSetType.OAK, settings), BlockBehaviour.Properties.ofFullCopy(Blocks.ACACIA_DOOR));
        Block MANGROVE_DOOR = register3("mangrove_glass_door", (settings) -> new DoorBlock(BlockSetType.OAK, settings), BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_DOOR));
        Block CHERRY_DOOR = register3("cherry_glass_door", (settings) -> new DoorBlock(BlockSetType.OAK, settings), BlockBehaviour.Properties.ofFullCopy(Blocks.CHERRY_DOOR));
        Block BAMBOO_DOOR = register3("bamboo_glass_door", (settings) -> new DoorBlock(BlockSetType.OAK, settings), BlockBehaviour.Properties.ofFullCopy(Blocks.BAMBOO_DOOR));
        Block PALE_OAK_DOOR = register3("pale_oak_glass_door", (settings) -> new DoorBlock(BlockSetType.OAK, settings), BlockBehaviour.Properties.ofFullCopy(Blocks.PALE_OAK_DOOR));

        register3("crimson_glass_door", (settings) -> new DoorBlock(BlockSetType.OAK, settings), BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_DOOR));
        register3("warped_glass_door", (settings) -> new DoorBlock(BlockSetType.OAK, settings), BlockBehaviour.Properties.ofFullCopy(Blocks.WARPED_DOOR));

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

        Block OAK_TRAPDOOR = register3("oak_glass_trapdoor", (settings) -> new TrapDoorBlock(BlockSetType.OAK, settings), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR));
        Block SPRUCE_TRAPDOOR = register3("spruce_glass_trapdoor", (settings) -> new TrapDoorBlock(BlockSetType.OAK, settings), BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_TRAPDOOR));
        Block JUNGLE_TRAPDOOR = register3("jungle_glass_trapdoor", (settings) -> new TrapDoorBlock(BlockSetType.OAK, settings), BlockBehaviour.Properties.ofFullCopy(Blocks.JUNGLE_TRAPDOOR));
        Block BIRCH_TRAPDOOR = register3("birch_glass_trapdoor", (settings) -> new TrapDoorBlock(BlockSetType.OAK, settings), BlockBehaviour.Properties.ofFullCopy(Blocks.BIRCH_TRAPDOOR));
        Block DARK_OAK_TRAPDOOR = register3("dark_oak_glass_trapdoor", (settings) -> new TrapDoorBlock(BlockSetType.OAK, settings), BlockBehaviour.Properties.ofFullCopy(Blocks.DARK_OAK_TRAPDOOR));
        Block ACACIA_TRAPDOOR = register3("acacia_glass_trapdoor", (settings) -> new TrapDoorBlock(BlockSetType.OAK, settings), BlockBehaviour.Properties.ofFullCopy(Blocks.ACACIA_TRAPDOOR));
        Block MANGROVE_TRAPDOOR = register3("mangrove_glass_trapdoor", (settings) -> new TrapDoorBlock(BlockSetType.OAK, settings), BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_TRAPDOOR));
        Block CHERRY_TRAPDOOR = register3("cherry_glass_trapdoor", (settings) -> new TrapDoorBlock(BlockSetType.OAK, settings), BlockBehaviour.Properties.ofFullCopy(Blocks.CHERRY_TRAPDOOR));
        Block BAMBOO_TRAPDOOR = register3("bamboo_glass_trapdoor", (settings) -> new TrapDoorBlock(BlockSetType.OAK, settings), BlockBehaviour.Properties.ofFullCopy(Blocks.BAMBOO_TRAPDOOR));
        Block PALE_OAK_TRAPDOOR = register3("pale_oak_glass_trapdoor", (settings) -> new TrapDoorBlock(BlockSetType.OAK, settings), BlockBehaviour.Properties.ofFullCopy(Blocks.PALE_OAK_TRAPDOOR));

        register3("crimson_glass_trapdoor", (settings) -> new TrapDoorBlock(BlockSetType.OAK, settings), BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_TRAPDOOR));
        register3("warped_glass_trapdoor", (settings) -> new TrapDoorBlock(BlockSetType.OAK, settings), BlockBehaviour.Properties.ofFullCopy(Blocks.WARPED_TRAPDOOR));

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
        Block TEXTURED_WHITE_WOOL = register3("textured_wool_white", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL));
        Block TEXTURED_LIGHT_GRAY_WOOL = register3("textured_wool_light_gray", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.LIGHT_GRAY_WOOL));
        Block TEXTURED_GRAY_WOOL = register3("textured_wool_gray", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GRAY_WOOL));
        Block TEXTURED_BLACK_WOOL = register3("textured_wool_black", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BLACK_WOOL));
        Block TEXTURED_BROWN_WOOL = register3("textured_wool_brown", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BROWN_WOOL));
        Block TEXTURED_RED_WOOL = register3("textured_wool_red", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.RED_WOOL));
        Block TEXTURED_ORANGE_WOOL = register3("textured_wool_orange", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.ORANGE_WOOL));
        Block TEXTURED_YELLOW_WOOL = register3("textured_wool_yellow", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.YELLOW_WOOL));
        Block TEXTURED_LIME_WOOL = register3("textured_wool_lime", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.LIME_WOOL));
        Block TEXTURED_GREEN_WOOL = register3("textured_wool_green", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GREEN_WOOL));
        Block TEXTURED_CYAN_WOOL = register3("textured_wool_cyan", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CYAN_WOOL));
        Block TEXTURED_LIGHT_BLUE_WOOL = register3("textured_wool_light_blue", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.LIGHT_BLUE_WOOL));
        Block TEXTURED_BLUE_WOOL = register3("textured_wool_blue", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BLUE_WOOL));
        Block TEXTURED_PURPLE_WOOL = register3("textured_wool_purple", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.PURPLE_WOOL));
        Block TEXTURED_MAGENTA_WOOL = register3("textured_wool_magenta", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.MAGENTA_WOOL));
        Block TEXTURED_PINK_WOOL = register3("textured_wool_pink", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.PINK_WOOL));

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



    

    // -------------------------
    // 1.21.10 Style Registration
    // -------------------------

    /*public static Block register(RegistryKey<Block> key, Function<AbstractBlock.Settings, Block> factory, AbstractBlock.Settings settings) {
        Block block = factory.apply(settings.registryKey(key));

        Registry.register(Registries.BLOCK, key, block);
        registerBlockItem(key.getValue(), block);

        if (EntStupidStuff.DEV_MODE)
            System.out.println(key.getValue());

        BlockList.put(key.getValue(), block);
        return block;
    }

    public static Block register(RegistryKey<Block> key, AbstractBlock.Settings settings) {
        return register(key, Block::new, settings);
    }*/

    public static ResourceKey<Block> keyOf(String id) {
        return ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, id));
    }
    /*
    public static Block register(String id, Function<AbstractBlock.Settings, Block> factory, AbstractBlock.Settings settings) {
        return register(keyOf(id), factory, settings);
    }

    public static Block register(String id, AbstractBlock.Settings settings) {
        return register(id, Block::new, settings);
    }*/




    // -------------------------
    // Old-style pre-constructed block registration
    // -------------------------

    /*public static Block register(String id, Block block) {
        RegistryKey<Block> key = keyOf(id);

        Registry.register(Registries.BLOCK, key, block);
        registerBlockItem(key.getValue(), block);

        if (EntStupidStuff.DEV_MODE)
            System.out.println(key.getValue());

        BlockList.put(key.getValue(), block);
        return block;
    }*/

    // -------------------------
    // Non-Registry / Internal Helper (does not create BlockItem)
    // -------------------------
    /*public static Block registerNonReg(String id, Block block) {
        RegistryKey<Block> key = keyOf(id);

        Registry.register(Registries.BLOCK, key, block);
        BlockList.put(key.getValue(), block);
        return block;
    }*/

    // -------------------------
    // Accessor for already registered blocks
    // -------------------------
    public static Block callBlock(String id) {
        try {
            ResourceLocation identifier = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, id);
            return BlockList.get(identifier);
        } catch (Exception e) {
            System.out.println("An Error occurred in callBlock()... Tried to call: " + id);
            return null;
        }
    }

    // -------------------------
    // Private Helper to register BlockItem automatically
    // -------------------------
    /*private static void registerBlockItem(Identifier id, Block block) {
        BlockItem blockItem = new BlockItem(block, new Item.Settings());
        ItemFactory.registerItems(id.getPath(), blockItem);
    }*/

    /* Support */

    @Deprecated
    public static void BlockSystem(Block block, String blockName, String suffix, MapColor mapColor) {

        if (suffix == null) {suffix = "";}
        else {suffix = "_" + suffix;}

        Block STAIRS = register(blockName + "_stairs" + suffix, 
            new StairBlock(block.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS).mapColor((mapColor)))); 

        Block SLAB = register(blockName + "_slab" + suffix, 
            new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SLAB).mapColor((mapColor)))); 

        Block FENCE = register(blockName + "_fence" + suffix, 
            new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE).mapColor((mapColor)))); 

        Block FENCE_GATE = register(blockName + "_fence_gate" + suffix, 
            new FenceGateBlock(WoodType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE).mapColor((mapColor)))); 

        Block P_PLATE = register(blockName + "_pressure_plate" + suffix, 
            new PressurePlateBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PRESSURE_PLATE).mapColor((mapColor)))); 

        Block BUTTON = register(blockName + "_button" + suffix, 
            new ButtonBlock(BlockSetType.OAK, 30, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_BUTTON).mapColor((mapColor)))); 

        BlockFamilies.familyBuilder(block)
            .button(BUTTON)
            .fence(FENCE)
            .fenceGate(FENCE_GATE)
            .pressurePlate(P_PLATE)
            .slab(SLAB)
            .stairs(STAIRS)
		.recipeGroupPrefix("log")
        .recipeUnlockedBy("has_logs")
		.getFamily();

        //ModGroup.addToDeco(blockName + "_stairs" + suffix);
        //ModGroup.addToDeco(blockName + "_slab" + suffix);
        //ModGroup.addToDeco(blockName + "_fence" + suffix);
        //ModGroup.addToDeco(blockName + "_fence_gate" + suffix);
        //ModGroup.addToDeco(blockName + "_pressure_plate" + suffix);
        //ModGroup.addToDeco(blockName + "_button" + suffix);


        FlammableBlockRegistry.getDefaultInstance().add((BlockFactory.callBlock(blockName + "_planks" + suffix)), 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add((BlockFactory.callBlock(blockName + "_stairs" + suffix)), 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add((BlockFactory.callBlock(blockName + "_slab" + suffix)), 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add((BlockFactory.callBlock(blockName + "_fence" + suffix)), 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add((BlockFactory.callBlock(blockName + "_fence_gate" + suffix)), 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add((BlockFactory.callBlock(blockName + "_pressure_plate" + suffix)), 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add((BlockFactory.callBlock(blockName + "_button" + suffix)), 5, 20);

        

    }
    
    @Deprecated
    public static void WoodSystem(String blockName, String suffix, MapColor mapColor, Boolean isNatural) {

        if (suffix == null) {suffix = "";}
        else {suffix = "_" + suffix;}

        if (isNatural) {


            Block LOG = register2(blockName + "_log" + suffix, 
            (settings) -> new RotatedPillarBlock(settings),
                Blocks.logProperties(mapColor, mapColor, SoundType.WOOD));

            Block STRIPPED_LOG = register2("stripped_" + blockName + "_log" + suffix, 
            (settings) -> new RotatedPillarBlock(settings),
                Blocks.logProperties(mapColor, mapColor, SoundType.WOOD));

            Block WOOD = register(blockName + "_wood" + suffix, 
                new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(mapColor).instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(SoundType.WOOD).ignitedByLava()));

            Block STRIPPED_WOOD = register("stripped_" + blockName + "_wood" + suffix, 
                new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(mapColor).instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(SoundType.WOOD).ignitedByLava()));

            //Block LEAVES = register(blockName + "_leaves" + suffix, new LeavesBlock(AbstractBlock.Settings.copy(Blocks.OAK_LEAVES)));

            //ModGroup.addToNatural(blockName + "_log" + suffix);
            //ModGroup.addToNatural("stripped_" + blockName + "_log" + suffix);
            //ModGroup.addToNatural(blockName + "_wood" + suffix);
            //ModGroup.addToNatural("stripped_" + blockName + "_wood" + suffix);

            //ModGroup.addToDeco(blockName + "_log" + suffix);
            //ModGroup.addToDeco("stripped_" + blockName + "_log" + suffix);
            //ModGroup.addToDeco(blockName + "_wood" + suffix);
            //ModGroup.addToDeco("stripped_" + blockName + "_wood" + suffix);

            FlammableBlockRegistry.getDefaultInstance().add((BlockFactory.callBlock(blockName + "_log" + suffix)), 5, 5);
            FlammableBlockRegistry.getDefaultInstance().add((BlockFactory.callBlock("stripped_" + blockName + "_log" + suffix)), 5, 5);
            FlammableBlockRegistry.getDefaultInstance().add((BlockFactory.callBlock(blockName + "_wood" + suffix)), 5, 5);
            FlammableBlockRegistry.getDefaultInstance().add((BlockFactory.callBlock("stripped_" + blockName + "_wood" + suffix)), 5, 5);
            FlammableBlockRegistry.getDefaultInstance().add((BlockFactory.callBlock(blockName + "_leaves" + suffix)), 30, 60);
            

        }

        Block PLANKS = register(blockName + "_planks" + suffix, 
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).mapColor((mapColor))));

        Block STAIRS = register(blockName + "_stairs" + suffix, 
            new StairBlock(PLANKS.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS).mapColor((mapColor)))); 

        Block SLAB = register(blockName + "_slab" + suffix, 
            new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SLAB).mapColor((mapColor)))); 

        Block FENCE = register(blockName + "_fence" + suffix, 
            new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE).mapColor((mapColor)))); 

        Block FENCE_GATE = register(blockName + "_fence_gate" + suffix, 
            new FenceGateBlock(WoodType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE).mapColor((mapColor)))); 

        Block DOOR = register(blockName + "_door" + suffix, 
            new DoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR).noOcclusion().mapColor((mapColor))));

        Block GLASS_DOOR = register(blockName + "_glass_door" + suffix, 
            new DoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR).noOcclusion().mapColor((mapColor)))); 

        Block TRAP_DOOR = register(blockName + "_trapdoor" + suffix, 
            new TrapDoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR).mapColor((mapColor))));

        Block GLASS_TRAP_DOOR = register(blockName + "_glass_trapdoor" + suffix, 
            new TrapDoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR).mapColor((mapColor))));

        Block P_PLATE = register(blockName + "_pressure_plate" + suffix, 
            new PressurePlateBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PRESSURE_PLATE).mapColor((mapColor)))); 

        Block BUTTON = register(blockName + "_button" + suffix, 
            new ButtonBlock(BlockSetType.OAK, 30, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_BUTTON).mapColor((mapColor)))); 

        //Mosic
        MosicFamily(blockName, suffix, PLANKS);

        /*Block SIGN = registerNonReg(blockName + "_sign" + suffix,
		    new SignBlock(WoodType.OAK, AbstractBlock.Settings.create().mapColor(mapColor).solid().instrument(NoteBlockInstrument.BASS).noCollision().strength(1.0F).burnable())); //CHECK

        Block WALL_SIGN = registerNonReg(blockName + "_wall_sign" + suffix,
		    new WallSignBlock(WoodType.OAK, AbstractBlock.Settings.create().mapColor(mapColor).solid().instrument(NoteBlockInstrument.BASS).noCollision().strength(1.0F).dropsLike(SIGN).burnable())); //CHECK

        Block HANGING_SIGN = registerNonReg(blockName + "_hanging_sign" + suffix, 
            new HangingSignBlock(WoodType.OAK, AbstractBlock.Settings.create().mapColor(mapColor).solid().instrument(NoteBlockInstrument.BASS).noCollision().strength(1.0F).burnable())); //CHECK

        Block WALL_HANGING_SIGN = registerNonReg(blockName + "_wall_hanging_sign" + suffix, 
            new WallHangingSignBlock(WoodType.OAK,  AbstractBlock.Settings.create().mapColor(mapColor).solid().instrument(NoteBlockInstrument.BASS).noCollision().strength(1.0F).burnable().dropsLike(HANGING_SIGN))); //CHECK
        */
        BlockFamilies.familyBuilder(PLANKS)
            .button(BUTTON)
            .fence(FENCE)
            .fenceGate(FENCE_GATE)
            .pressurePlate(P_PLATE)
            .slab(SLAB)
            .stairs(STAIRS)
            .trapdoor(TRAP_DOOR)
            //.sign(SIGN, WALL_SIGN)
            .door(DOOR).door(GLASS_DOOR)
		.recipeGroupPrefix("wooden")
        .recipeUnlockedBy("has_planks")
		.getFamily();

        //ModGroup.addToDeco(blockName + "_planks" + suffix);
        //ModGroup.addToDeco(blockName + "_stairs" + suffix);
        //ModGroup.addToDeco(blockName + "_slab" + suffix);
        //ModGroup.addToDeco(blockName + "_fence" + suffix);
        //ModGroup.addToDeco(blockName + "_fence_gate" + suffix);
        //ModGroup.addToDeco(blockName + "_door" + suffix);
        //ModGroup.addToDeco(blockName + "_glass_door" + suffix);
        //ModGroup.addToDeco(blockName + "_trapdoor" + suffix);
        //ModGroup.addToDeco(blockName + "_glass_trapdoor" + suffix);
        //ModGroup.addToDeco(blockName + "_pressure_plate" + suffix);
        //ModGroup.addToDeco(blockName + "_button" + suffix);
        ////ModGroup.addToDeco(blockName + "_sign" + suffix);
        ////ModGroup.addToDeco(blockName + "_hanging_sign" + suffix);

        FlammableBlockRegistry.getDefaultInstance().add((BlockFactory.callBlock(blockName + "_planks" + suffix)), 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add((BlockFactory.callBlock(blockName + "_stairs" + suffix)), 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add((BlockFactory.callBlock(blockName + "_slab" + suffix)), 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add((BlockFactory.callBlock(blockName + "_fence" + suffix)), 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add((BlockFactory.callBlock(blockName + "_fence_gate" + suffix)), 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add((BlockFactory.callBlock(blockName + "_door" + suffix)), 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add((BlockFactory.callBlock(blockName + "_glass_door" + suffix)), 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add((BlockFactory.callBlock(blockName + "_trapdoor" + suffix)), 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add((BlockFactory.callBlock(blockName + "_glass_trapdoor" + suffix)), 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add((BlockFactory.callBlock(blockName + "_pressure_plate" + suffix)), 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add((BlockFactory.callBlock(blockName + "_button" + suffix)), 5, 20);


    }

    @Deprecated
    public static void MosicFamily(String blockName, String varient, Block baseBlock){

        /*if (varient == null) {varient = "";}
        else {varient = "_" + varient;}*/

        Block MOSAIC  = register(blockName + "_mosaic" + varient,
            new Block(BlockBehaviour.Properties.ofFullCopy(baseBlock)));

        Block MOSAIC_STAIRS = register(blockName + "_mosaic_stairs" + varient, 
            new StairBlock(baseBlock.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(baseBlock))); 

        Block MOSAIC_SLAB = register(blockName + "_mosaic_slab" + varient, 
            new SlabBlock(BlockBehaviour.Properties.ofFullCopy(baseBlock)));

        FlammableBlockRegistry.getDefaultInstance().add(MOSAIC, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(MOSAIC_STAIRS, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(MOSAIC_SLAB, 5, 20);

        //ModGroup.addToDeco(blockName + "_mosaic" + varient);
        //ModGroup.addToDeco(blockName + "_mosaic_stairs" + varient);
        //ModGroup.addToDeco(blockName + "_mosaic_slab" + varient);
    }

    @Deprecated
    public static void MosicFamily(String blockName, String varient, Block baseBlock, Boolean ent){

        /*if (varient == null) {varient = "";}
        else {varient = "_" + varient;}*/

        Block MOSAIC  = register(blockName + "_mosaic" + varient,
            new Block(BlockBehaviour.Properties.ofFullCopy(baseBlock)));

        Block MOSAIC_STAIRS = register(blockName + "_mosaic_stairs" + varient, 
            new StairBlock(baseBlock.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(baseBlock))); 

        Block MOSAIC_SLAB = register(blockName + "_mosaic_slab" + varient, 
            new SlabBlock(BlockBehaviour.Properties.ofFullCopy(baseBlock)));

        //ModGroup.addToDeco(blockName + "_mosaic" + varient);
        //ModGroup.addToDeco(blockName + "_mosaic_stairs" + varient);
        //ModGroup.addToDeco(blockName + "_mosaic_slab" + varient);
    }

    
    public static void OxidizableFamily() { /* Make this Dynamic */
        Block COPPER = register3("copper_glass_door",
            settings -> new WeatheringCopperDoorBlock(BlockSetType.COPPER, WeatheringCopper.WeatherState.UNAFFECTED, settings),
            BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_DOOR));

        Block EXPOSED_COPPER = register3("exposed_copper_glass_door",
            settings -> new WeatheringCopperDoorBlock(BlockSetType.COPPER, WeatheringCopper.WeatherState.EXPOSED, settings),
            BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_DOOR));

        Block OXIDIZED_COPPER = register3("oxidized_copper_glass_door",
            settings -> new WeatheringCopperDoorBlock(BlockSetType.COPPER, WeatheringCopper.WeatherState.OXIDIZED, settings),
            BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_DOOR));

        Block WEATHERED_COPPER = register3("weathered_copper_glass_door",
            settings -> new WeatheringCopperDoorBlock(BlockSetType.COPPER, WeatheringCopper.WeatherState.WEATHERED, settings),
            BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_DOOR));

        Block WAXED_COPPER = register3("waxed_copper_glass_door",
            settings -> new DoorBlock(BlockSetType.COPPER, settings),
            BlockBehaviour.Properties.ofFullCopy(COPPER));

        Block WAXED_EXPOSED_COPPER = register3("waxed_exposed_copper_glass_door",
            settings -> new DoorBlock(BlockSetType.COPPER, settings),
            BlockBehaviour.Properties.ofFullCopy(EXPOSED_COPPER));

        Block WAXED_OXIDIZED_COPPER = register3("waxed_oxidized_copper_glass_door",
            settings -> new DoorBlock(BlockSetType.COPPER, settings),
            BlockBehaviour.Properties.ofFullCopy(OXIDIZED_COPPER));

        Block WAXED_WEATHERED_COPPER = register3("waxed_weathered_copper_glass_door",
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

        Block COPPER_1 = register3("copper_glass_trapdoor",
            settings -> new WeatheringCopperTrapDoorBlock(BlockSetType.COPPER, WeatheringCopper.WeatherState.UNAFFECTED, settings),
            BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_TRAPDOOR));

        Block EXPOSED_COPPER_1 = register3("exposed_copper_glass_trapdoor",
            settings -> new WeatheringCopperTrapDoorBlock(BlockSetType.COPPER, WeatheringCopper.WeatherState.EXPOSED, settings),
            BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_TRAPDOOR));

        Block OXIDIZED_COPPER_1 = register3("oxidized_copper_glass_trapdoor",
            settings -> new WeatheringCopperTrapDoorBlock(BlockSetType.COPPER, WeatheringCopper.WeatherState.OXIDIZED, settings),
            BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_TRAPDOOR));

        Block WEATHERED_COPPER_1 = register3("weathered_copper_glass_trapdoor",
            settings -> new WeatheringCopperTrapDoorBlock(BlockSetType.COPPER, WeatheringCopper.WeatherState.WEATHERED, settings),
            BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_TRAPDOOR));

        Block WAXED_COPPER_1 = register3("waxed_copper_glass_trapdoor",
            settings -> new TrapDoorBlock(BlockSetType.COPPER, settings),
            BlockBehaviour.Properties.ofFullCopy(COPPER_1));

        Block WAXED_EXPOSED_COPPER_1 = register3("waxed_exposed_copper_glass_trapdoor",
            settings -> new TrapDoorBlock(BlockSetType.COPPER, settings),
            BlockBehaviour.Properties.ofFullCopy(EXPOSED_COPPER_1));

        Block WAXED_OXIDIZED_COPPER_1 = register3("waxed_oxidized_copper_glass_trapdoor",
            settings -> new TrapDoorBlock(BlockSetType.COPPER, settings),
            BlockBehaviour.Properties.ofFullCopy(OXIDIZED_COPPER_1));

        Block WAXED_WEATHERED_COPPER_1 = register3("waxed_weathered_copper_glass_trapdoor",
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


    @Deprecated
    public static void StoneSystem(String blockName, MapColor mapColor, Boolean isBricksOnly, Block familyBlock) { 

        if (isBricksOnly) {
            Block Bricks = register3(blockName + "_bricks",
                (settings) -> new Block(settings),
                BlockBehaviour.Properties.ofFullCopy(familyBlock)
            );

            Block Stairs = register3(blockName + "_brick_stairs",
                (settings) -> new StairBlock(Bricks.defaultBlockState(), settings),
                BlockBehaviour.Properties.ofFullCopy(familyBlock)
            );

            Block Slab = register3(blockName + "_brick_slab",
                (settings) -> new SlabBlock(settings),
                BlockBehaviour.Properties.ofFullCopy(familyBlock)
            );

            Block Wall = register3(blockName + "_brick_wall",
                (settings) -> new WallBlock(settings),
                BlockBehaviour.Properties.ofFullCopy(familyBlock)
            );

            Block Chiseled = register3(blockName + "_brick_chiseled",
                (settings) -> new Block(settings),
                BlockBehaviour.Properties.ofFullCopy(familyBlock)
            );

            Block Cracked = register3("cracked_" + blockName + "_bricks",
                (settings) -> new Block(settings),
                BlockBehaviour.Properties.ofFullCopy(familyBlock)
            );

        } else {  
            Block Normal_Stairs = register3(blockName + "_stairs",
                (settings) -> new StairBlock(familyBlock.defaultBlockState(), settings),
                BlockBehaviour.Properties.ofFullCopy(familyBlock)
            );

            Block Normal_Slab = register3(blockName + "_slab",
                (settings) -> new SlabBlock(settings),
                BlockBehaviour.Properties.ofFullCopy(familyBlock)
            );

            Block Normal_Wall = register3(blockName + "_wall",
                (settings) -> new WallBlock(settings),
                BlockBehaviour.Properties.ofFullCopy(familyBlock)
            );
        }
    }

    @Deprecated
    public static void CustomPhantomWoodSystem() {

        String blockName = "phantom";
        String suffix = "";
        MapColor mapColor = MapColor.GLOW_LICHEN;
        Boolean isNatural = true;

        if (isNatural) {

            Block LOG = register3(blockName + "_log" + suffix,
                settings -> new RotatedPillarBlock(settings
                    .mapColor(state -> state.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? mapColor : mapColor)
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F)
                    .sound(SoundType.WOOD)
                    .ignitedByLava()
                    .noOcclusion()
                ),
                BlockBehaviour.Properties.of());

            Block STRIPPED_LOG = register3("stripped_" + blockName + "_log" + suffix,
                settings -> new RotatedPillarBlock(settings
                    .mapColor(state -> state.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? mapColor : mapColor)
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F)
                    .sound(SoundType.WOOD)
                    .ignitedByLava()
                    .noOcclusion()
                ),
                BlockBehaviour.Properties.of());

            Block WOOD = register3(blockName + "_wood" + suffix,
                settings -> new RotatedPillarBlock(settings
                    .mapColor(mapColor)
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F)
                    .sound(SoundType.WOOD)
                    .ignitedByLava()
                    .noOcclusion()
                ),
                BlockBehaviour.Properties.of());

            Block STRIPPED_WOOD = register3("stripped_" + blockName + "_wood" + suffix,
                settings -> new RotatedPillarBlock(settings
                    .mapColor(mapColor)
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F)
                    .sound(SoundType.WOOD)
                    .ignitedByLava()
                    .noOcclusion()
                ),
                BlockBehaviour.Properties.of());

            FlammableBlockRegistry.getDefaultInstance().add((BlockFactory.callBlock(blockName + "_log" + suffix)), 5, 5);
            FlammableBlockRegistry.getDefaultInstance().add((BlockFactory.callBlock("stripped_" + blockName + "_log" + suffix)), 5, 5);
            FlammableBlockRegistry.getDefaultInstance().add((BlockFactory.callBlock(blockName + "_wood" + suffix)), 5, 5);
            FlammableBlockRegistry.getDefaultInstance().add((BlockFactory.callBlock("stripped_" + blockName + "_wood" + suffix)), 5, 5);
            FlammableBlockRegistry.getDefaultInstance().add((BlockFactory.callBlock(blockName + "_leaves" + suffix)), 30, 60);

        }

        @SuppressWarnings("static-access")
        Block PLANKS = register3(blockName + "_planks" + suffix,
            settings -> new Block(settings.ofFullCopy(Blocks.OAK_PLANKS).mapColor(mapColor).noOcclusion()),
            BlockBehaviour.Properties.of());

        @SuppressWarnings("static-access")
        Block STAIRS = register3(blockName + "_stairs" + suffix,
            settings -> new StairBlock(PLANKS.defaultBlockState(), settings.ofFullCopy(Blocks.OAK_STAIRS).mapColor(mapColor).noOcclusion()),
            BlockBehaviour.Properties.of());

        @SuppressWarnings("static-access")
        Block SLAB = register3(blockName + "_slab" + suffix,
            settings -> new SlabBlock(settings.ofFullCopy(Blocks.OAK_SLAB).mapColor(mapColor).noOcclusion()),
            BlockBehaviour.Properties.of());

        @SuppressWarnings("static-access")
        Block FENCE = register3(blockName + "_fence" + suffix,
            settings -> new FenceBlock(settings.ofFullCopy(Blocks.OAK_FENCE).mapColor(mapColor).noOcclusion()),
            BlockBehaviour.Properties.of());

        @SuppressWarnings("static-access")
        Block FENCE_GATE = register3(blockName + "_fence_gate" + suffix,
            settings -> new FenceGateBlock(WoodType.OAK, settings.ofFullCopy(Blocks.OAK_FENCE_GATE).mapColor(mapColor).noOcclusion()),
            BlockBehaviour.Properties.of());

        @SuppressWarnings("static-access")
        Block DOOR = register3(blockName + "_door" + suffix,
            settings -> new DoorBlock(BlockSetType.OAK, settings.ofFullCopy(Blocks.OAK_DOOR).mapColor(mapColor).noOcclusion()),
            BlockBehaviour.Properties.of());

        @SuppressWarnings("static-access")
        Block GLASS_DOOR = register3(blockName + "_glass_door" + suffix,
            settings -> new DoorBlock(BlockSetType.OAK, settings.ofFullCopy(Blocks.OAK_DOOR).mapColor(mapColor).noOcclusion()),
            BlockBehaviour.Properties.of());

        @SuppressWarnings("static-access")
        Block TRAP_DOOR = register3(blockName + "_trapdoor" + suffix,
            settings -> new TrapDoorBlock(BlockSetType.OAK, settings.ofFullCopy(Blocks.OAK_TRAPDOOR).mapColor(mapColor).noOcclusion()),
            BlockBehaviour.Properties.of());

        @SuppressWarnings("static-access")
        Block GLASS_TRAP_DOOR = register3(blockName + "_glass_trapdoor" + suffix,
            settings -> new TrapDoorBlock(BlockSetType.OAK, settings.ofFullCopy(Blocks.OAK_TRAPDOOR).mapColor(mapColor).noOcclusion()),
            BlockBehaviour.Properties.of());

        @SuppressWarnings("static-access")
        Block P_PLATE = register3(blockName + "_pressure_plate" + suffix,
            settings -> new PressurePlateBlock(BlockSetType.OAK, settings.ofFullCopy(Blocks.OAK_PRESSURE_PLATE).mapColor(mapColor).noOcclusion()),
            BlockBehaviour.Properties.of());

        @SuppressWarnings("static-access")
        Block BUTTON = register3(blockName + "_button" + suffix,
            settings -> new ButtonBlock(BlockSetType.OAK, 30, settings.ofFullCopy(Blocks.OAK_BUTTON).mapColor(mapColor).noOcclusion()),
            BlockBehaviour.Properties.of());

        BlockFamilies.familyBuilder(PLANKS)
            .button(BUTTON)
            .fence(FENCE)
            .fenceGate(FENCE_GATE)
            .pressurePlate(P_PLATE)
            .slab(SLAB)
            .stairs(STAIRS)
            .trapdoor(TRAP_DOOR)
            .door(DOOR)
            .door(GLASS_DOOR)
            .recipeGroupPrefix("wooden")
            .recipeUnlockedBy("has_planks")
            .getFamily();

        @SuppressWarnings("static-access")
        Block MOSAIC = register3(blockName + "_mosaic" + suffix,
            settings -> new Block(settings.ofFullCopy(PLANKS).noOcclusion()),
            BlockBehaviour.Properties.of());

        @SuppressWarnings("static-access")
        Block MOSAIC_STAIRS = register3(blockName + "_mosaic_stairs" + suffix,
            settings -> new StairBlock(PLANKS.defaultBlockState(), settings.ofFullCopy(PLANKS).noOcclusion()),
            BlockBehaviour.Properties.of());

        @SuppressWarnings("static-access")
        Block MOSAIC_SLAB = register3(blockName + "_mosaic_slab" + suffix,
            settings -> new SlabBlock(settings.ofFullCopy(PLANKS).noOcclusion()),
            BlockBehaviour.Properties.of());

        FlammableBlockRegistry.getDefaultInstance().add(MOSAIC, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(MOSAIC_STAIRS, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(MOSAIC_SLAB, 5, 20);

        FlammableBlockRegistry.getDefaultInstance().add((BlockFactory.callBlock(blockName + "_planks" + suffix)), 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add((BlockFactory.callBlock(blockName + "_stairs" + suffix)), 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add((BlockFactory.callBlock(blockName + "_slab" + suffix)), 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add((BlockFactory.callBlock(blockName + "_fence" + suffix)), 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add((BlockFactory.callBlock(blockName + "_fence_gate" + suffix)), 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add((BlockFactory.callBlock(blockName + "_door" + suffix)), 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add((BlockFactory.callBlock(blockName + "_glass_door" + suffix)), 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add((BlockFactory.callBlock(blockName + "_trapdoor" + suffix)), 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add((BlockFactory.callBlock(blockName + "_glass_trapdoor" + suffix)), 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add((BlockFactory.callBlock(blockName + "_pressure_plate" + suffix)), 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add((BlockFactory.callBlock(blockName + "_button" + suffix)), 5, 20);

    }





    // # Adding PHANTOM TORCH
    public static final Block PHANTOM_TORCH = register3(
        "phantom_torch",
        settings -> new TorchBlock(ParticleTypes.FLAME, settings),
		BlockBehaviour.Properties.of().noCollision().instabreak().lightLevel(state -> 14).sound(SoundType.WOOD).pushReaction(PushReaction.DESTROY)
	);

    public static final Block PHANTOM_WALL_TORCH = register3(
        "phantom_wall_torch",
        settings -> new WallTorchBlock(ParticleTypes.FLAME, settings),
		copyLootTable(PHANTOM_TORCH, true).noCollision().instabreak().lightLevel(state -> 14).sound(SoundType.WOOD).pushReaction(PushReaction.DESTROY)
	);

    // # Adding POINTED_ICE
    public static final Block POINTED_ICE = register3(
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
    public static final Block DARK_ENCHANTMENT_TABLE = register3(
        "dark_enchantment_table", 
        DarkEnchantingTableBlock::new,
		    BlockBehaviour.Properties.of()
                .mapColor(MapColor.COLOR_RED)
                .instrument(NoteBlockInstrument.BASEDRUM)
                .requiresCorrectToolForDrops().lightLevel(state -> 7)
                .strength(5.0F, 1200.0F)
    );


    public static final Block ABYSSAL_STONE = register3("abyssal_stone", (settings) -> new Block(settings), BlockBehaviour.Properties.ofFullCopy(Blocks.BLACKSTONE));






    private static BlockBehaviour.Properties copyLootTable(Block block, boolean copyTranslationKey) {
		BlockBehaviour.Properties settings = block.properties();
		BlockBehaviour.Properties settings2 = BlockBehaviour.Properties.of().overrideLootTable(block.getLootTable());
		if (copyTranslationKey) {
			settings2 = settings2.overrideDescription(block.getDescriptionId());
		}

		return settings2;
	}







    //Registation:

    public static Block register(String id, Block block) {
        ResourceLocation blockId = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, id);

        Registry.register(BuiltInRegistries.BLOCK, blockId, block);

        // ✅ Then safely register the block's item using the SAME Identifier
        BlockItem blockItem = new BlockItem(block, new Item.Properties());
        Registry.register(BuiltInRegistries.ITEM, blockId, blockItem);

        if (EntStupidStuff.DEV_MODE) {
            System.out.println("Registered block + item: " + blockId);
        }

        System.out.println("Registered block + item: " + blockId);

        BlockList.put(blockId, block);

        return block;
    }

    private static void registerBlockItem2(String name, Block block) {

        BlockItem item = new BlockItem(block, new Item.Properties().useBlockDescriptionPrefix()
                        .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, name))));

        Registry.register(BuiltInRegistries.ITEM, ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, name), item);

        ItemList.put(ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, name), item);
        System.out.println("Adding Item: " + item);
    }

    public static Block register3(String id, Function<BlockBehaviour.Properties, Block> factory, BlockBehaviour.Properties settings) {
		return register3(keyOf(id), factory, settings, id);
	}

    public static Block register3(ResourceKey<Block> key, Function<BlockBehaviour.Properties, Block> factory, BlockBehaviour.Properties settings, String id) {
		Block block = (Block)factory.apply(settings.setId(key));

        ResourceLocation blockId = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, id);
        BlockList.put(blockId, block);

        System.out.println("Adding Block: " + blockId);

        registerBlockItem2(id, block);
        ModGroup.addToDefault(id);

		return Registry.register(BuiltInRegistries.BLOCK, key, block);
	}

    public static Block register2(String id, Function<BlockBehaviour.Properties, Block> factory, BlockBehaviour.Properties settings) {
		return register2(keyOf(id), factory, settings, id);
	}

    public static Block register2(ResourceKey<Block> key, Function<BlockBehaviour.Properties, Block> factory, BlockBehaviour.Properties settings, String id) {
		Block block = (Block)factory.apply(settings.setId(key));

        ResourceLocation blockId = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, id);
        BlockList.put(blockId, block);

        BlockItem blockItem = new BlockItem(block, new Item.Properties());
        Registry.register(BuiltInRegistries.ITEM, blockId, blockItem);
        ItemList.put(blockId, blockItem);

		return Registry.register(BuiltInRegistries.BLOCK, key, block);
	}

    //Adding for Blue Mushroom

    public static Block register_custom_mushroom(String id, Function<BlockBehaviour.Properties, Block> factory, BlockBehaviour.Properties settings) {
		return register_custom_mushroom(keyOf(id), factory, settings, id);
	}

    public static Block register_custom_mushroom(ResourceKey<Block> key, Function<BlockBehaviour.Properties, Block> factory, BlockBehaviour.Properties settings, String id) {
		Block block = (Block)factory.apply(settings.setId(key));

        ResourceLocation blockId = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, id);
        BlockList.put(blockId, block);

        System.out.println("Adding Block (custom_mushroom): " + blockId);

        registerBlockItem2_custom_mushroom(id, block);
        ModGroup.addToDefault(id);

		return Registry.register(BuiltInRegistries.BLOCK, key, block);
	}

    //Adding for Dates

    public static Block register_dates(String id, Function<BlockBehaviour.Properties, Block> factory, BlockBehaviour.Properties settings) {
		return register_dates(keyOf(id), factory, settings, id);
	}

    public static Block register_dates(ResourceKey<Block> key, Function<BlockBehaviour.Properties, Block> factory, BlockBehaviour.Properties settings, String id) {
		Block block = (Block)factory.apply(settings.setId(key));

        ResourceLocation blockId = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, id);
        BlockList.put(blockId, block);

        System.out.println("Adding Block (dates): " + blockId);

        registerBlockItem2_date(id, block);
        ModGroup.addToDefault(id);

		return Registry.register(BuiltInRegistries.BLOCK, key, block);
	}

    private static void registerBlockItem2_date(String name, Block block) {

        BlockItem item = new BlockItem(block, new Item.Properties().food(FOOD_SHROOM, CONS_SHROOM).useBlockDescriptionPrefix()
                        .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, name))));

        Registry.register(BuiltInRegistries.ITEM, ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, name), item);

        ItemList.put(ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, name), item);
        System.out.println("Adding Item: (dates)" + item);
    }

    //Adding Food Times

    public static final FoodProperties FOOD_SHROOM = new FoodProperties.Builder().nutrition(1).saturationModifier(0.1F).alwaysEdible().build();

    public static final Consumable CONS_SHROOM = Consumable.builder()
        .consumeSeconds(1.6F).animation(ItemUseAnimation.EAT).sound(SoundEvents.GENERIC_EAT).hasConsumeParticles(true)
        .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(ModEffects.RGB_SHIFT, 600, 0), 0.8F))
        .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.NAUSEA, 600, 0), 0.8F)).build();

    private static void registerBlockItem2_custom_mushroom(String name, Block block) {

        BlockItem item = new BlockItem(block, new Item.Properties().food(FOOD_SHROOM, CONS_SHROOM).useBlockDescriptionPrefix()
                        .setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, name))));

        Registry.register(BuiltInRegistries.ITEM, ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, name), item);

        ItemList.put(ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, name), item);
        System.out.println("Adding Item: (custom_mushroom)" + item);
    }

    



}
