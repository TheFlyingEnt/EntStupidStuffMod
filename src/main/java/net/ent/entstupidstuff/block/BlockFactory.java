package net.ent.entstupidstuff.block;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.item.ModGroup;
import net.ent.entstupidstuff.world.ModConfiguredFeatures;
import net.ent.entstupidstuff.world.tree.SaplingGeneratorFactory;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.OxidizableBlocksRegistry;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSetType;
import net.minecraft.block.Blocks;
import net.minecraft.block.ButtonBlock;
import net.minecraft.block.DoorBlock;
import net.minecraft.block.FenceBlock;
import net.minecraft.block.FenceGateBlock;
import net.minecraft.block.FlowerPotBlock;
import net.minecraft.block.FlowerbedBlock;
import net.minecraft.block.GrateBlock;
import net.minecraft.block.LanternBlock;
import net.minecraft.block.MapColor;
import net.minecraft.block.MushroomPlantBlock;
import net.minecraft.block.Oxidizable;
import net.minecraft.block.OxidizableDoorBlock;
import net.minecraft.block.OxidizableTrapdoorBlock;
import net.minecraft.block.PaneBlock;
import net.minecraft.block.PillarBlock;
import net.minecraft.block.PressurePlateBlock;
import net.minecraft.block.SaplingBlock;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.SporeBlossomBlock;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.TorchBlock;
import net.minecraft.block.TransparentBlock;
import net.minecraft.block.TrapdoorBlock;
import net.minecraft.block.WallBlock;
import net.minecraft.block.WallTorchBlock;
import net.minecraft.block.WoodType;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.data.family.BlockFamilies;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;

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

    public static final Map<Identifier, Block> BlockList = new LinkedHashMap<>();
    public static final Map<Identifier, Block> BlockItem = new LinkedHashMap<>(); 
    public static final Map<Identifier, Item> ItemList = new LinkedHashMap<>();

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

    public static void onInitializeNewUpdated() {

        Block REDWOOD_PLANKS = register3("redwood" + "_planks" + "", Block::new, (AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).mapColor((MapColor.DULL_RED))));
        groupWoodFamilty("redwood", "", REDWOOD_PLANKS, true, MapColor.DULL_RED, MapColor.DULL_RED);

        Block FIR_PLANKS = register3("fir" + "_planks" + "", Block::new, (AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).mapColor((MapColor.TERRACOTTA_GRAY))));
        groupWoodFamilty("fir", "", FIR_PLANKS, true, MapColor.TERRACOTTA_GRAY, MapColor.SPRUCE_BROWN);
        BlockFactoryHelper.addSaplings("fir", SaplingGeneratorFactory.FIR);

        Block MAPLE_PLANKS = register3("maple" + "_planks" + "", Block::new, (AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).mapColor((MapColor.SPRUCE_BROWN))));
        groupWoodFamilty("maple", "", MAPLE_PLANKS, true, MapColor.DIRT_BROWN, MapColor.SPRUCE_BROWN);
        BlockFactoryHelper.addSaplings("maple", SaplingGeneratorFactory.MAPLE);
        
        register3("orange_petals", FlowerbedBlock::new,
            AbstractBlock.Settings.create().mapColor(MapColor.DARK_GREEN).noCollision().sounds(BlockSoundGroup.FLOWERBED).pistonBehavior(PistonBehavior.DESTROY)
        );
        
        Block PHANTOM_PLANKS = register3("phantom" + "_planks" + "", Block::new, (AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).mapColor((MapColor.LICHEN_GREEN)).nonOpaque()));
        groupWoodFamilty("phantom", "", PHANTOM_PLANKS, true, MapColor.LICHEN_GREEN, MapColor.DARK_GREEN);

        Block PHANTOM_LANTERN = register3("phantom_lantern", (settings) -> new LanternBlock(settings.mapColor(MapColor.IRON_GRAY).solid().requiresTool().strength(3.5F).sounds(BlockSoundGroup.LANTERN).luminance((state) -> {
            return 10;
        }).nonOpaque().pistonBehavior(PistonBehavior.DESTROY)), AbstractBlock.Settings.create());

        groupFungalFamily("", MapColor.WHITE_GRAY);
        groupFungalFamily("_white", MapColor.WHITE);
        groupFungalFamily("_light_gray", MapColor.LIGHT_GRAY);
        groupFungalFamily("_gray", MapColor.GRAY);
        groupFungalFamily("_black", MapColor.BLACK);
        groupFungalFamily("_brown", MapColor.BROWN);
        groupFungalFamily("_red", MapColor.RED);
        groupFungalFamily("_orange", MapColor.ORANGE);
        groupFungalFamily("_yellow", MapColor.YELLOW);
        groupFungalFamily("_lime", MapColor.LIME);
        groupFungalFamily("_green", MapColor.GREEN);
        groupFungalFamily("_cyan", MapColor.CYAN);
        groupFungalFamily("_light_blue", MapColor.LIGHT_BLUE);
        groupFungalFamily("_blue", MapColor.BLUE);
        groupFungalFamily("_purple", MapColor.PURPLE);
        groupFungalFamily("_magenta", MapColor.MAGENTA);
        groupFungalFamily("_pink", MapColor.PINK);

        /*BlockFactoryHelper.MosicFamily("oak", "", Blocks.OAK_PLANKS, true);
        BlockFactoryHelper.MosicFamily("spruce", "", Blocks.SPRUCE_PLANKS, true);
        BlockFactoryHelper.MosicFamily("jungle", "", Blocks.JUNGLE_PLANKS, true);
        BlockFactoryHelper.MosicFamily("birch", "", Blocks.BIRCH_PLANKS, true);
        BlockFactoryHelper.MosicFamily("dark_oak", "", Blocks.DARK_OAK_PLANKS, true);
        BlockFactoryHelper.MosicFamily("acacia", "", Blocks.ACACIA_PLANKS, true);
        BlockFactoryHelper. MosicFamily("mangrove", "", Blocks.MANGROVE_PLANKS, true);
        BlockFactoryHelper.MosicFamily("cherry", "", Blocks.CHERRY_PLANKS, true);
        BlockFactoryHelper.MosicFamily("warped", "", Blocks.WARPED_PLANKS, false);
        BlockFactoryHelper.MosicFamily("crimson", "", Blocks.CRIMSON_PLANKS, false);*/

    }




    public static void onInitializeNew() {

        /*Block REDWOOD_PLANKS = register("redwood" + "_planks" + "", new Block(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).mapColor((MapColor.DULL_RED))));
        groupWoodFamilty("redwood", "", REDWOOD_PLANKS, true, MapColor.DULL_RED, MapColor.DULL_RED);

        Block FIR_PLANKS = register("fir" + "_planks" + "", new Block(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).mapColor((MapColor.TERRACOTTA_GRAY))));
        groupWoodFamilty("fir", "", FIR_PLANKS, true, MapColor.TERRACOTTA_GRAY, MapColor.SPRUCE_BROWN);

        Block MAPLE_PLANKS = register("maple" + "_planks" + "", new Block(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).mapColor((MapColor.SPRUCE_BROWN))));
        groupWoodFamilty("maple", "", MAPLE_PLANKS, true, MapColor.DIRT_BROWN, MapColor.SPRUCE_BROWN);*/
        
        

        Block REDWOOD_PLANKS = register3("redwood" + "_planks" + "", Block::new, (AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).mapColor((MapColor.DULL_RED))));
        BlockFactoryHelper.BaseFamily("redwood", "", REDWOOD_PLANKS, true);
        BlockFactoryHelper.InteractionFamily("redwood", "", REDWOOD_PLANKS, MapColor.DULL_RED, true, true);
        BlockFactoryHelper.NatureFamily("redwood" , "", MapColor.DARK_RED, MapColor.SPRUCE_BROWN, true, true);
        BlockFactoryHelper.MosicFamily("redwood" , "", REDWOOD_PLANKS, true);

        Block FIR_PLANKS = register3("fir" + "_planks" + "", Block::new, (AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).mapColor((MapColor.TERRACOTTA_GRAY))));
        BlockFactoryHelper.InteractionFamily("fir", "", FIR_PLANKS, MapColor.SPRUCE_BROWN, true, true);
        BlockFactoryHelper.BaseFamily("fir", "", FIR_PLANKS, true);
        BlockFactoryHelper.NatureFamily("fir" , "", MapColor.TERRACOTTA_GRAY, MapColor.SPRUCE_BROWN, true, true);
        BlockFactoryHelper.MosicFamily("fir" , "", FIR_PLANKS, true);
        register3("fir" + "_sapling", (settings) -> new SaplingBlock(SaplingGeneratorFactory.FIR, settings), AbstractBlock.Settings.copy(Blocks.OAK_SAPLING));

        Block MAPLE_PLANKS = register3("maple" + "_planks" + "", Block::new, (AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).mapColor((MapColor.SPRUCE_BROWN))));
        BlockFactoryHelper.InteractionFamily("maple", "", MAPLE_PLANKS, MapColor.SPRUCE_BROWN, true, true);
        BlockFactoryHelper.BaseFamily("maple", "", MAPLE_PLANKS, true);
        BlockFactoryHelper.NatureFamily("maple" , "", MapColor.TERRACOTTA_GRAY, MapColor.SPRUCE_BROWN, true, true);
        BlockFactoryHelper.MosicFamily("maple" , "", MAPLE_PLANKS, true);
        register3("maple" + "_sapling", (settings) -> new SaplingBlock(SaplingGeneratorFactory.MAPLE, settings), AbstractBlock.Settings.copy(Blocks.OAK_SAPLING));

        /*Block PHANTOM_PLANKS = register("phantom" + "_planks" + "", new Block(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).mapColor((MapColor.LICHEN_GREEN))));
        BlockFactoryHelper.InteractionFamily("phantom", "", PHANTOM_PLANKS, MapColor.LICHEN_GREEN, true, true);
        BlockFactoryHelper.BaseFamily("phantom", "", PHANTOM_PLANKS, true);
        BlockFactoryHelper.NatureFamily("phantom" , "", MapColor.LICHEN_GREEN, MapColor.LICHEN_GREEN, false, false);
        BlockFactoryHelper.MosicFamily("phantom" , "", PHANTOM_PLANKS, true);*/

        // Fungal Planks

        //groupFungalFamily("", MapColor.WHITE_GRAY);
        /*
        Block FUNGAL_PLANKS = register("fungal" + "_planks" + "", new Block(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).mapColor((MapColor.WHITE_GRAY))));
        BlockFactoryHelper.BaseFamily("fungal", "", FUNGAL_PLANKS, true);
        BlockFactoryHelper.MosicFamily("fungal" , "", FUNGAL_PLANKS, true);
        
        Block FUNGAL_PLANKS_WHITE = register("fungal" + "_planks" + "_white", new Block(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).mapColor((MapColor.OFF_WHITE))));
        BlockFactoryHelper.BaseFamily("fungal", "_white", FUNGAL_PLANKS_WHITE, true);
        BlockFactoryHelper.MosicFamily("fungal" , "_white", FUNGAL_PLANKS_WHITE, true);
         */

    }

    public static void groupFungalFamily(String Varient, MapColor MainColor) {

        Block FUNGAL_PLANKS = register3("fungal" + "_planks" + Varient, Block::new, (AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).mapColor((MainColor))));
        
        BlockFactoryHelper.BaseFamily("fungal", Varient, FUNGAL_PLANKS, true);
        BlockFactoryHelper.InteractionFamily("fungal", Varient, FUNGAL_PLANKS, MainColor, true, true);
        BlockFactoryHelper.MosicFamily("fungal" , Varient, FUNGAL_PLANKS, true);

        BlockFamilies.register(FUNGAL_PLANKS)
            .button(BlockFactory.callBlock("fungal" + "_" + "button" + Varient))
            .fence(BlockFactory.callBlock("fungal" + "_" + "fence" + Varient))
            .fenceGate(BlockFactory.callBlock("fungal" + "_" + "fence_gate" + Varient))
            .pressurePlate(BlockFactory.callBlock("fungal" + "_" + "pressure_plate" + Varient))
            .slab(BlockFactory.callBlock("fungal" + "_slab" + Varient))
            .stairs(BlockFactory.callBlock("fungal" + "_stairs" + Varient))
            .trapdoor(BlockFactory.callBlock("fungal" + "_" + "trapdoor" + Varient))
            .trapdoor(BlockFactory.callBlock("fungal" + "_" + "glass_trapdoor" + Varient))
            .door(BlockFactory.callBlock("fungal" + "_" + "door" + Varient)) 
            .door(BlockFactory.callBlock("fungal" + "_" + "glass_door" + Varient))
        .group("wooden")
        .unlockCriterionName("has_planks")
        .build();

    }

    public static void groupWoodFamilty(String MainName, String Varient, Block MainBlock, Boolean flamable, MapColor MainColor, MapColor SecondColor) {
        
        BlockFactoryHelper.BaseFamily(MainName, Varient, MainBlock, flamable);
        BlockFactoryHelper.InteractionFamily(MainName, Varient, MainBlock, MainColor, flamable, true);
        BlockFactoryHelper.NatureFamily(MainName , Varient, MainColor, SecondColor, flamable, true);
        BlockFactoryHelper.MosicFamily(MainName , Varient, MainBlock, flamable);


        BlockFamilies.register(MainBlock)
            .button(BlockFactory.callBlock(MainName + "_" + "button" + Varient))
            .fence(BlockFactory.callBlock(MainName + "_" + "fence" + Varient))
            .fenceGate(BlockFactory.callBlock(MainName + "_" + "fence_gate" + Varient))
            .pressurePlate(BlockFactory.callBlock(MainName + "_" + "button" + Varient))
            .slab(BlockFactory.callBlock(MainName + "_slab" + Varient))
            .stairs(BlockFactory.callBlock(MainName + "_stairs" + Varient))
            .trapdoor(BlockFactory.callBlock(MainName + "_" + "trapdoor" + Varient))
            .trapdoor(BlockFactory.callBlock(MainName + "_" + "glass_trapdoor" + Varient))
            .door(BlockFactory.callBlock(MainName + "_" + "door" + Varient)) 
            .door(BlockFactory.callBlock(MainName + "_" + "glass_door" + Varient))
        .group("wooden")
        .unlockCriterionName("has_planks")
        .build();
        
    }
    
    @SuppressWarnings("unused")
    public static void onInitialize() {

        onInitializeNewUpdated();

        //Blue Mushroom:
        Block BLUE_MUSHROOM = register3(
		"blue_mushroom",
		(settings) -> new BlueMushroomPlantBlock(
			ModConfiguredFeatures.HUGE_BLUE_MUSHROOM_KEY,settings
		),
		AbstractBlock.Settings.create()
			.mapColor(MapColor.BLUE)
			.noCollision()
			.ticksRandomly()
			.breakInstantly()
			.sounds(BlockSoundGroup.GRASS)
			.postProcess(Blocks::always)
			.pistonBehavior(PistonBehavior.DESTROY)
		);

        Block BLUE_MUSHROOM_BLOCK = register3(
            "blue_mushroom_block",
            TransparentMushroomBlock::new,
            AbstractBlock.Settings.create()
                .mapColor(MapColor.BLUE)
                .instrument(NoteBlockInstrument.BASS)
                .strength(0.2F)
                .sounds(BlockSoundGroup.WOOD)
                .luminance(state -> 5)
                .burnable()
                .nonOpaque()
                        .solidBlock(Blocks::never)
                        .blockVision(Blocks::never)
        );

        Block SHROOMIUM_BLOCK = register3(
            "shroomium",
            ShroomiumBlock::new,
            AbstractBlock.Settings.create()
                .mapColor(MapColor.TEAL)
                .allowsSpawning(Blocks::always)
                .solidBlock(Blocks::always)
                .blockVision(Blocks::always)
                .suffocates(Blocks::always)
                .sounds(BlockSoundGroup.MUD)
        );

        Block MUSHROOM_BED = register3(
            "mushroom_bed",
            MushroombedBlock::new,
            AbstractBlock.Settings.create().mapColor(MapColor.BLUE).noCollision().sounds(BlockSoundGroup.FUNGUS).pistonBehavior(PistonBehavior.DESTROY)
        );

        Block POTTED_MUSHROOM_BED = register3(
            "potted_blue_mushroom", settings -> new FlowerPotBlock(BLUE_MUSHROOM, settings), Blocks.createFlowerPotSettings()
        );

        Block FUNGAL_SPORE_BLOSSOM = register3(
            "fungal_spore_blossom",
            MushroomSporeBlossomBlock::new,
            AbstractBlock.Settings.create()
                .mapColor(MapColor.DARK_AQUA)
                .breakInstantly()
                .noCollision()
                .sounds(BlockSoundGroup.SPORE_BLOSSOM)
                .pistonBehavior(PistonBehavior.DESTROY)
        );
        
        // Adding Vanilla Glassdoor and TrapDoors
        OxidizableFamily();

        register3("iron" + "_glass_door", (settings) -> new DoorBlock(BlockSetType.IRON, settings), AbstractBlock.Settings.copy(Blocks.IRON_DOOR));
        register3("iron" + "_glass_trapdoor", (settings) -> new TrapdoorBlock(BlockSetType.IRON, settings), AbstractBlock.Settings.copy(Blocks.IRON_TRAPDOOR));

        Block OAK_DOOR = register3("oak_glass_door", (settings) -> new DoorBlock(BlockSetType.OAK, settings), AbstractBlock.Settings.copy(Blocks.OAK_DOOR));
        Block SPRUCE_DOOR = register3("spruce_glass_door", (settings) -> new DoorBlock(BlockSetType.OAK, settings), AbstractBlock.Settings.copy(Blocks.SPRUCE_DOOR));
        Block JUNGLE_DOOR = register3("jungle_glass_door", (settings) -> new DoorBlock(BlockSetType.OAK, settings), AbstractBlock.Settings.copy(Blocks.JUNGLE_DOOR));
        Block BIRCH_DOOR = register3("birch_glass_door", (settings) -> new DoorBlock(BlockSetType.OAK, settings), AbstractBlock.Settings.copy(Blocks.BIRCH_DOOR));
        Block DARK_OAK_DOOR = register3("dark_oak_glass_door", (settings) -> new DoorBlock(BlockSetType.OAK, settings), AbstractBlock.Settings.copy(Blocks.DARK_OAK_DOOR));
        Block ACACIA_DOOR = register3("acacia_glass_door", (settings) -> new DoorBlock(BlockSetType.OAK, settings), AbstractBlock.Settings.copy(Blocks.ACACIA_DOOR));
        Block MANGROVE_DOOR = register3("mangrove_glass_door", (settings) -> new DoorBlock(BlockSetType.OAK, settings), AbstractBlock.Settings.copy(Blocks.MANGROVE_DOOR));
        Block CHERRY_DOOR = register3("cherry_glass_door", (settings) -> new DoorBlock(BlockSetType.OAK, settings), AbstractBlock.Settings.copy(Blocks.CHERRY_DOOR));
        Block BAMBOO_DOOR = register3("bamboo_glass_door", (settings) -> new DoorBlock(BlockSetType.OAK, settings), AbstractBlock.Settings.copy(Blocks.BAMBOO_DOOR));

        register3("crimson_glass_door", (settings) -> new DoorBlock(BlockSetType.OAK, settings), AbstractBlock.Settings.copy(Blocks.CRIMSON_DOOR));
        register3("warped_glass_door", (settings) -> new DoorBlock(BlockSetType.OAK, settings), AbstractBlock.Settings.copy(Blocks.WARPED_DOOR));

        FlammableBlockRegistry.getDefaultInstance().add(OAK_DOOR, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(SPRUCE_DOOR, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(JUNGLE_DOOR, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(BIRCH_DOOR, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(DARK_OAK_DOOR, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(ACACIA_DOOR, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(MANGROVE_DOOR, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(CHERRY_DOOR, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(BAMBOO_DOOR, 5, 20);

        Block OAK_TRAPDOOR = register3("oak_glass_trapdoor", (settings) -> new TrapdoorBlock(BlockSetType.OAK, settings), AbstractBlock.Settings.copy(Blocks.OAK_TRAPDOOR));
        Block SPRUCE_TRAPDOOR = register3("spruce_glass_trapdoor", (settings) -> new TrapdoorBlock(BlockSetType.OAK, settings), AbstractBlock.Settings.copy(Blocks.SPRUCE_TRAPDOOR));
        Block JUNGLE_TRAPDOOR = register3("jungle_glass_trapdoor", (settings) -> new TrapdoorBlock(BlockSetType.OAK, settings), AbstractBlock.Settings.copy(Blocks.JUNGLE_TRAPDOOR));
        Block BIRCH_TRAPDOOR = register3("birch_glass_trapdoor", (settings) -> new TrapdoorBlock(BlockSetType.OAK, settings), AbstractBlock.Settings.copy(Blocks.BIRCH_TRAPDOOR));
        Block DARK_OAK_TRAPDOOR = register3("dark_oak_glass_trapdoor", (settings) -> new TrapdoorBlock(BlockSetType.OAK, settings), AbstractBlock.Settings.copy(Blocks.DARK_OAK_TRAPDOOR));
        Block ACACIA_TRAPDOOR = register3("acacia_glass_trapdoor", (settings) -> new TrapdoorBlock(BlockSetType.OAK, settings), AbstractBlock.Settings.copy(Blocks.ACACIA_TRAPDOOR));
        Block MANGROVE_TRAPDOOR = register3("mangrove_glass_trapdoor", (settings) -> new TrapdoorBlock(BlockSetType.OAK, settings), AbstractBlock.Settings.copy(Blocks.MANGROVE_TRAPDOOR));
        Block CHERRY_TRAPDOOR = register3("cherry_glass_trapdoor", (settings) -> new TrapdoorBlock(BlockSetType.OAK, settings), AbstractBlock.Settings.copy(Blocks.CHERRY_TRAPDOOR));
        Block BAMBOO_TRAPDOOR = register3("bamboo_glass_trapdoor", (settings) -> new TrapdoorBlock(BlockSetType.OAK, settings), AbstractBlock.Settings.copy(Blocks.BAMBOO_TRAPDOOR));

        register3("crimson_glass_trapdoor", (settings) -> new TrapdoorBlock(BlockSetType.OAK, settings), AbstractBlock.Settings.copy(Blocks.CRIMSON_TRAPDOOR));
        register3("warped_glass_trapdoor", (settings) -> new TrapdoorBlock(BlockSetType.OAK, settings), AbstractBlock.Settings.copy(Blocks.WARPED_TRAPDOOR));

        FlammableBlockRegistry.getDefaultInstance().add(OAK_TRAPDOOR, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(SPRUCE_TRAPDOOR, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(JUNGLE_TRAPDOOR, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(BIRCH_TRAPDOOR, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(DARK_OAK_TRAPDOOR, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(ACACIA_TRAPDOOR, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(MANGROVE_TRAPDOOR, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(CHERRY_TRAPDOOR, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(BAMBOO_TRAPDOOR, 5, 20);

        BlockFactoryHelper.MosicFamily("oak", "", Blocks.OAK_PLANKS, false);
        BlockFactoryHelper.MosicFamily("spruce", "", Blocks.SPRUCE_PLANKS, false);
        BlockFactoryHelper.MosicFamily("jungle", "", Blocks.JUNGLE_PLANKS, false);
        BlockFactoryHelper.MosicFamily("birch", "", Blocks.BIRCH_PLANKS, false);
        BlockFactoryHelper.MosicFamily("dark_oak", "", Blocks.DARK_OAK_PLANKS, false);
        BlockFactoryHelper.MosicFamily("acacia", "", Blocks.ACACIA_PLANKS, false);
        BlockFactoryHelper.MosicFamily("mangrove", "", Blocks.MANGROVE_PLANKS, false);
        BlockFactoryHelper.MosicFamily("cherry", "", Blocks.CHERRY_PLANKS, false);

        BlockFactoryHelper.MosicFamily("crimson", "", Blocks.CRIMSON_PLANKS, true);
        BlockFactoryHelper.MosicFamily("warped", "", Blocks.WARPED_PLANKS, true);


        //Continue - Adding Stone Varients

        StoneSystem("andesite", MapColor.STONE_GRAY, true, Blocks.POLISHED_ANDESITE);
        StoneSystem("granite", MapColor.DIRT_BROWN, true, Blocks.POLISHED_GRANITE);
        StoneSystem("diorite", MapColor.OFF_WHITE, true, Blocks.POLISHED_DIORITE);
        register3("polished_andesite" + "_wall", (settings) -> new WallBlock(settings), AbstractBlock.Settings.copy(Blocks.POLISHED_ANDESITE));
        register3("polished_granite" + "_wall", (settings) -> new WallBlock(settings), AbstractBlock.Settings.copy(Blocks.POLISHED_GRANITE));
        register3("polished_diorite" + "_wall", (settings) -> new WallBlock(settings), AbstractBlock.Settings.copy(Blocks.POLISHED_DIORITE));


        //BlockFactoryHelper.StoneFamily("andesite", "", Blocks.POLISHED_ANDESITE, false);
        //BlockFactoryHelper.StoneFamily("granite", "", Blocks.POLISHED_GRANITE, false);
        //BlockFactoryHelper.StoneFamily("diorite", "", Blocks.POLISHED_DIORITE, false);
        
        //BlockFactoryHelper.StoneFamily("andesite_brick", "", Blocks.POLISHED_ANDESITE, false);
        //BlockFactoryHelper.StoneFamily("granite_brick", "", Blocks.POLISHED_GRANITE, false);
        //BlockFactoryHelper.StoneFamily("diorite_brick", "", Blocks.POLISHED_DIORITE, false);

        //StoneFamily()

        //BlockFactoryHelper.StoneFamily("polished_andesite", "", Blocks.POLISHED_ANDESITE, true);
        //BlockFactoryHelper.StoneFamily("polished_granite", "", Blocks.POLISHED_GRANITE, true);
        //BlockFactoryHelper.StoneFamily("polished_diorite", "", Blocks.POLISHED_DIORITE, true);

        //Textured Wools
        Block TEXTURED_WHITE_WOOL = register3("textured_wool_white", Block::new, AbstractBlock.Settings.copy(Blocks.WHITE_WOOL));
        Block TEXTURED_LIGHT_GRAY_WOOL = register3("textured_wool_light_gray", Block::new, AbstractBlock.Settings.copy(Blocks.LIGHT_GRAY_WOOL));
        Block TEXTURED_GRAY_WOOL = register3("textured_wool_gray", Block::new, AbstractBlock.Settings.copy(Blocks.GRAY_WOOL));
        Block TEXTURED_BLACK_WOOL = register3("textured_wool_black", Block::new, AbstractBlock.Settings.copy(Blocks.BLACK_WOOL));
        Block TEXTURED_BROWN_WOOL = register3("textured_wool_brown", Block::new, AbstractBlock.Settings.copy(Blocks.BROWN_WOOL));
        Block TEXTURED_RED_WOOL = register3("textured_wool_red", Block::new, AbstractBlock.Settings.copy(Blocks.RED_WOOL));
        Block TEXTURED_ORANGE_WOOL = register3("textured_wool_orange", Block::new, AbstractBlock.Settings.copy(Blocks.ORANGE_WOOL));
        Block TEXTURED_YELLOW_WOOL = register3("textured_wool_yellow", Block::new, AbstractBlock.Settings.copy(Blocks.YELLOW_WOOL));
        Block TEXTURED_LIME_WOOL = register3("textured_wool_lime", Block::new, AbstractBlock.Settings.copy(Blocks.LIME_WOOL));
        Block TEXTURED_GREEN_WOOL = register3("textured_wool_green", Block::new, AbstractBlock.Settings.copy(Blocks.GREEN_WOOL));
        Block TEXTURED_CYAN_WOOL = register3("textured_wool_cyan", Block::new, AbstractBlock.Settings.copy(Blocks.CYAN_WOOL));
        Block TEXTURED_LIGHT_BLUE_WOOL = register3("textured_wool_light_blue", Block::new, AbstractBlock.Settings.copy(Blocks.LIGHT_BLUE_WOOL));
        Block TEXTURED_BLUE_WOOL = register3("textured_wool_blue", Block::new, AbstractBlock.Settings.copy(Blocks.BLUE_WOOL));
        Block TEXTURED_PURPLE_WOOL = register3("textured_wool_purple", Block::new, AbstractBlock.Settings.copy(Blocks.PURPLE_WOOL));
        Block TEXTURED_MAGENTA_WOOL = register3("textured_wool_magenta", Block::new, AbstractBlock.Settings.copy(Blocks.MAGENTA_WOOL));
        Block TEXTURED_PINK_WOOL = register3("textured_wool_pink", Block::new, AbstractBlock.Settings.copy(Blocks.PINK_WOOL));

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

        Block STRING_GATE = register3("string_gate", 
            (settings) -> new PaneBlock(settings.nonOpaque()
                        .allowsSpawning(Blocks::never)
                        .solidBlock(Blocks::never)
                        .suffocates(Blocks::never)
                        .blockVision(Blocks::never)),
            AbstractBlock.Settings.copy(Blocks.IRON_BARS)
        );

        Block STRING_BLOCK = register3("string_block", 
            (settings) -> new TransparentBlock(settings.nonOpaque()
                        .allowsSpawning(Blocks::never)
                        .solidBlock(Blocks::never)
                        .suffocates(Blocks::never)
                        .blockVision(Blocks::never)),
            AbstractBlock.Settings.copy(Blocks.WHITE_WOOL)
        );

        Block LIMESTONE = register3("limestone",
            (settings) -> new Block(settings),
            AbstractBlock.Settings.copy(Blocks.LIGHT_GRAY_TERRACOTTA)
        );

        Block POLISHED_LIMESTONE = register3("polished_limestone",
            (settings) -> new Block(settings),
            AbstractBlock.Settings.copy(Blocks.LIGHT_GRAY_TERRACOTTA)
        );

        StoneSystem("limestone", MapColor.TERRACOTTA_LIGHT_GRAY, false, LIMESTONE);
        StoneSystem("polished_limestone", MapColor.TERRACOTTA_LIGHT_GRAY, false, POLISHED_LIMESTONE);
        StoneSystem("polished_limestone", MapColor.TERRACOTTA_LIGHT_GRAY, true, POLISHED_LIMESTONE);

        Block IRON_GRATE = register3("iron_grate",
            (settings) -> new GrateBlock(settings.requiresTool().strength(5.0F, 6.0F).sounds(BlockSoundGroup.METAL).nonOpaque()),
            AbstractBlock.Settings.create()
        );

        Block IRON_GRATE_STAIRS = register3("iron_grate_stairs",
            (settings) -> new GrateStairsBlock(IRON_GRATE.getDefaultState(), settings),
            AbstractBlock.Settings.copy(IRON_GRATE)
        );

        Block IRON_GRATE_SLAB = register3("iron_grate_slab",
            (settings) -> new GrateSlabBlock(settings),
            AbstractBlock.Settings.copy(IRON_GRATE)
        );

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

    public static RegistryKey<Block> keyOf(String id) {
        return RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(EntStupidStuff.MOD_ID, id));
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
            Identifier identifier = Identifier.of(EntStupidStuff.MOD_ID, id);
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

    public static void BlockSystem(Block block, String blockName, String suffix, MapColor mapColor) {

        if (suffix == null) {suffix = "";}
        else {suffix = "_" + suffix;}

        Block STAIRS = register(blockName + "_stairs" + suffix, 
            new StairsBlock(block.getDefaultState(), AbstractBlock.Settings.copy(Blocks.OAK_STAIRS).mapColor((mapColor)))); 

        Block SLAB = register(blockName + "_slab" + suffix, 
            new SlabBlock(AbstractBlock.Settings.copy(Blocks.OAK_SLAB).mapColor((mapColor)))); 

        Block FENCE = register(blockName + "_fence" + suffix, 
            new FenceBlock(AbstractBlock.Settings.copy(Blocks.OAK_FENCE).mapColor((mapColor)))); 

        Block FENCE_GATE = register(blockName + "_fence_gate" + suffix, 
            new FenceGateBlock(WoodType.OAK, AbstractBlock.Settings.copy(Blocks.OAK_FENCE_GATE).mapColor((mapColor)))); 

        Block P_PLATE = register(blockName + "_pressure_plate" + suffix, 
            new PressurePlateBlock(BlockSetType.OAK, AbstractBlock.Settings.copy(Blocks.OAK_PRESSURE_PLATE).mapColor((mapColor)))); 

        Block BUTTON = register(blockName + "_button" + suffix, 
            new ButtonBlock(BlockSetType.OAK, 30, AbstractBlock.Settings.copy(Blocks.OAK_BUTTON).mapColor((mapColor)))); 

        BlockFamilies.register(block)
            .button(BUTTON)
            .fence(FENCE)
            .fenceGate(FENCE_GATE)
            .pressurePlate(P_PLATE)
            .slab(SLAB)
            .stairs(STAIRS)
		.group("log")
        .unlockCriterionName("has_logs")
		.build();

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
    

    public static void WoodSystem(String blockName, String suffix, MapColor mapColor, Boolean isNatural) {

        if (suffix == null) {suffix = "";}
        else {suffix = "_" + suffix;}

        if (isNatural) {


            Block LOG = register2(blockName + "_log" + suffix, 
            (settings) -> new PillarBlock(settings),
                Blocks.createLogSettings(mapColor, mapColor, BlockSoundGroup.WOOD));

            Block STRIPPED_LOG = register2("stripped_" + blockName + "_log" + suffix, 
            (settings) -> new PillarBlock(settings),
                Blocks.createLogSettings(mapColor, mapColor, BlockSoundGroup.WOOD));

            Block WOOD = register(blockName + "_wood" + suffix, 
                new PillarBlock(AbstractBlock.Settings.create().mapColor(mapColor).instrument(NoteBlockInstrument.BASS).strength(2.0F).sounds(BlockSoundGroup.WOOD).burnable()));

            Block STRIPPED_WOOD = register("stripped_" + blockName + "_wood" + suffix, 
                new PillarBlock(AbstractBlock.Settings.create().mapColor(mapColor).instrument(NoteBlockInstrument.BASS).strength(2.0F).sounds(BlockSoundGroup.WOOD).burnable()));

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
            new Block(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).mapColor((mapColor))));

        Block STAIRS = register(blockName + "_stairs" + suffix, 
            new StairsBlock(PLANKS.getDefaultState(), AbstractBlock.Settings.copy(Blocks.OAK_STAIRS).mapColor((mapColor)))); 

        Block SLAB = register(blockName + "_slab" + suffix, 
            new SlabBlock(AbstractBlock.Settings.copy(Blocks.OAK_SLAB).mapColor((mapColor)))); 

        Block FENCE = register(blockName + "_fence" + suffix, 
            new FenceBlock(AbstractBlock.Settings.copy(Blocks.OAK_FENCE).mapColor((mapColor)))); 

        Block FENCE_GATE = register(blockName + "_fence_gate" + suffix, 
            new FenceGateBlock(WoodType.OAK, AbstractBlock.Settings.copy(Blocks.OAK_FENCE_GATE).mapColor((mapColor)))); 

        Block DOOR = register(blockName + "_door" + suffix, 
            new DoorBlock(BlockSetType.OAK, AbstractBlock.Settings.copy(Blocks.OAK_DOOR).nonOpaque().mapColor((mapColor))));

        Block GLASS_DOOR = register(blockName + "_glass_door" + suffix, 
            new DoorBlock(BlockSetType.OAK, AbstractBlock.Settings.copy(Blocks.OAK_DOOR).nonOpaque().mapColor((mapColor)))); 

        Block TRAP_DOOR = register(blockName + "_trapdoor" + suffix, 
            new TrapdoorBlock(BlockSetType.OAK, AbstractBlock.Settings.copy(Blocks.OAK_TRAPDOOR).mapColor((mapColor))));

        Block GLASS_TRAP_DOOR = register(blockName + "_glass_trapdoor" + suffix, 
            new TrapdoorBlock(BlockSetType.OAK, AbstractBlock.Settings.copy(Blocks.OAK_TRAPDOOR).mapColor((mapColor))));

        Block P_PLATE = register(blockName + "_pressure_plate" + suffix, 
            new PressurePlateBlock(BlockSetType.OAK, AbstractBlock.Settings.copy(Blocks.OAK_PRESSURE_PLATE).mapColor((mapColor)))); 

        Block BUTTON = register(blockName + "_button" + suffix, 
            new ButtonBlock(BlockSetType.OAK, 30, AbstractBlock.Settings.copy(Blocks.OAK_BUTTON).mapColor((mapColor)))); 

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
        BlockFamilies.register(PLANKS)
            .button(BUTTON)
            .fence(FENCE)
            .fenceGate(FENCE_GATE)
            .pressurePlate(P_PLATE)
            .slab(SLAB)
            .stairs(STAIRS)
            .trapdoor(TRAP_DOOR)
            //.sign(SIGN, WALL_SIGN)
            .door(DOOR).door(GLASS_DOOR)
		.group("wooden")
        .unlockCriterionName("has_planks")
		.build();

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


    public static void MosicFamily(String blockName, String varient, Block baseBlock){

        /*if (varient == null) {varient = "";}
        else {varient = "_" + varient;}*/

        Block MOSAIC  = register(blockName + "_mosaic" + varient,
            new Block(AbstractBlock.Settings.copy(baseBlock)));

        Block MOSAIC_STAIRS = register(blockName + "_mosaic_stairs" + varient, 
            new StairsBlock(baseBlock.getDefaultState(), AbstractBlock.Settings.copy(baseBlock))); 

        Block MOSAIC_SLAB = register(blockName + "_mosaic_slab" + varient, 
            new SlabBlock(AbstractBlock.Settings.copy(baseBlock)));

        FlammableBlockRegistry.getDefaultInstance().add(MOSAIC, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(MOSAIC_STAIRS, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(MOSAIC_SLAB, 5, 20);

        //ModGroup.addToDeco(blockName + "_mosaic" + varient);
        //ModGroup.addToDeco(blockName + "_mosaic_stairs" + varient);
        //ModGroup.addToDeco(blockName + "_mosaic_slab" + varient);
    }


    public static void MosicFamily(String blockName, String varient, Block baseBlock, Boolean ent){

        /*if (varient == null) {varient = "";}
        else {varient = "_" + varient;}*/

        Block MOSAIC  = register(blockName + "_mosaic" + varient,
            new Block(AbstractBlock.Settings.copy(baseBlock)));

        Block MOSAIC_STAIRS = register(blockName + "_mosaic_stairs" + varient, 
            new StairsBlock(baseBlock.getDefaultState(), AbstractBlock.Settings.copy(baseBlock))); 

        Block MOSAIC_SLAB = register(blockName + "_mosaic_slab" + varient, 
            new SlabBlock(AbstractBlock.Settings.copy(baseBlock)));

        //ModGroup.addToDeco(blockName + "_mosaic" + varient);
        //ModGroup.addToDeco(blockName + "_mosaic_stairs" + varient);
        //ModGroup.addToDeco(blockName + "_mosaic_slab" + varient);
    }

    
    public static void OxidizableFamily() { /* Make this Dynamic */
        Block COPPER = register3("copper_glass_door",
            settings -> new OxidizableDoorBlock(BlockSetType.COPPER, Oxidizable.OxidationLevel.UNAFFECTED, settings),
            AbstractBlock.Settings.copy(Blocks.COPPER_DOOR));

        Block EXPOSED_COPPER = register3("exposed_copper_glass_door",
            settings -> new OxidizableDoorBlock(BlockSetType.COPPER, Oxidizable.OxidationLevel.EXPOSED, settings),
            AbstractBlock.Settings.copy(Blocks.COPPER_DOOR));

        Block OXIDIZED_COPPER = register3("oxidized_copper_glass_door",
            settings -> new OxidizableDoorBlock(BlockSetType.COPPER, Oxidizable.OxidationLevel.OXIDIZED, settings),
            AbstractBlock.Settings.copy(Blocks.COPPER_DOOR));

        Block WEATHERED_COPPER = register3("weathered_copper_glass_door",
            settings -> new OxidizableDoorBlock(BlockSetType.COPPER, Oxidizable.OxidationLevel.WEATHERED, settings),
            AbstractBlock.Settings.copy(Blocks.COPPER_DOOR));

        Block WAXED_COPPER = register3("waxed_copper_glass_door",
            settings -> new DoorBlock(BlockSetType.COPPER, settings),
            AbstractBlock.Settings.copy(COPPER));

        Block WAXED_EXPOSED_COPPER = register3("waxed_exposed_copper_glass_door",
            settings -> new DoorBlock(BlockSetType.COPPER, settings),
            AbstractBlock.Settings.copy(EXPOSED_COPPER));

        Block WAXED_OXIDIZED_COPPER = register3("waxed_oxidized_copper_glass_door",
            settings -> new DoorBlock(BlockSetType.COPPER, settings),
            AbstractBlock.Settings.copy(OXIDIZED_COPPER));

        Block WAXED_WEATHERED_COPPER = register3("waxed_weathered_copper_glass_door",
            settings -> new DoorBlock(BlockSetType.COPPER, settings),
            AbstractBlock.Settings.copy(WEATHERED_COPPER));

        OxidizableBlocksRegistry.registerOxidizableBlockPair(COPPER, EXPOSED_COPPER);
        OxidizableBlocksRegistry.registerOxidizableBlockPair(EXPOSED_COPPER, OXIDIZED_COPPER);
        OxidizableBlocksRegistry.registerOxidizableBlockPair(OXIDIZED_COPPER, WEATHERED_COPPER);

        OxidizableBlocksRegistry.registerWaxableBlockPair(COPPER, WAXED_COPPER);
        OxidizableBlocksRegistry.registerWaxableBlockPair(EXPOSED_COPPER, WAXED_EXPOSED_COPPER);
        OxidizableBlocksRegistry.registerWaxableBlockPair(OXIDIZED_COPPER, WAXED_OXIDIZED_COPPER);
        OxidizableBlocksRegistry.registerWaxableBlockPair(WEATHERED_COPPER, WAXED_WEATHERED_COPPER);

        // Trapdoors

        Block COPPER_1 = register3("copper_glass_trapdoor",
            settings -> new OxidizableTrapdoorBlock(BlockSetType.COPPER, Oxidizable.OxidationLevel.UNAFFECTED, settings),
            AbstractBlock.Settings.copy(Blocks.COPPER_TRAPDOOR));

        Block EXPOSED_COPPER_1 = register3("exposed_copper_glass_trapdoor",
            settings -> new OxidizableTrapdoorBlock(BlockSetType.COPPER, Oxidizable.OxidationLevel.EXPOSED, settings),
            AbstractBlock.Settings.copy(Blocks.COPPER_TRAPDOOR));

        Block OXIDIZED_COPPER_1 = register3("oxidized_copper_glass_trapdoor",
            settings -> new OxidizableTrapdoorBlock(BlockSetType.COPPER, Oxidizable.OxidationLevel.OXIDIZED, settings),
            AbstractBlock.Settings.copy(Blocks.COPPER_TRAPDOOR));

        Block WEATHERED_COPPER_1 = register3("weathered_copper_glass_trapdoor",
            settings -> new OxidizableTrapdoorBlock(BlockSetType.COPPER, Oxidizable.OxidationLevel.WEATHERED, settings),
            AbstractBlock.Settings.copy(Blocks.COPPER_TRAPDOOR));

        Block WAXED_COPPER_1 = register3("waxed_copper_glass_trapdoor",
            settings -> new TrapdoorBlock(BlockSetType.COPPER, settings),
            AbstractBlock.Settings.copy(COPPER_1));

        Block WAXED_EXPOSED_COPPER_1 = register3("waxed_exposed_copper_glass_trapdoor",
            settings -> new TrapdoorBlock(BlockSetType.COPPER, settings),
            AbstractBlock.Settings.copy(EXPOSED_COPPER_1));

        Block WAXED_OXIDIZED_COPPER_1 = register3("waxed_oxidized_copper_glass_trapdoor",
            settings -> new TrapdoorBlock(BlockSetType.COPPER, settings),
            AbstractBlock.Settings.copy(OXIDIZED_COPPER_1));

        Block WAXED_WEATHERED_COPPER_1 = register3("waxed_weathered_copper_glass_trapdoor",
            settings -> new TrapdoorBlock(BlockSetType.COPPER, settings),
            AbstractBlock.Settings.copy(WEATHERED_COPPER_1));

        OxidizableBlocksRegistry.registerOxidizableBlockPair(COPPER_1, EXPOSED_COPPER_1);
        OxidizableBlocksRegistry.registerOxidizableBlockPair(EXPOSED_COPPER_1, OXIDIZED_COPPER_1);
        OxidizableBlocksRegistry.registerOxidizableBlockPair(OXIDIZED_COPPER_1, WEATHERED_COPPER_1);

        OxidizableBlocksRegistry.registerWaxableBlockPair(COPPER_1, WAXED_COPPER_1);
        OxidizableBlocksRegistry.registerWaxableBlockPair(EXPOSED_COPPER_1, WAXED_EXPOSED_COPPER_1);
        OxidizableBlocksRegistry.registerWaxableBlockPair(OXIDIZED_COPPER_1, WAXED_OXIDIZED_COPPER_1);
        OxidizableBlocksRegistry.registerWaxableBlockPair(WEATHERED_COPPER_1, WAXED_WEATHERED_COPPER_1);
    }



    public static void StoneSystem(String blockName, MapColor mapColor, Boolean isBricksOnly, Block familyBlock) { 

        if (isBricksOnly) {
            Block Bricks = register3(blockName + "_bricks",
                (settings) -> new Block(settings),
                AbstractBlock.Settings.copy(familyBlock)
            );

            Block Stairs = register3(blockName + "_brick_stairs",
                (settings) -> new StairsBlock(Bricks.getDefaultState(), settings),
                AbstractBlock.Settings.copy(familyBlock)
            );

            Block Slab = register3(blockName + "_brick_slab",
                (settings) -> new SlabBlock(settings),
                AbstractBlock.Settings.copy(familyBlock)
            );

            Block Wall = register3(blockName + "_brick_wall",
                (settings) -> new WallBlock(settings),
                AbstractBlock.Settings.copy(familyBlock)
            );

            Block Chiseled = register3(blockName + "_brick_chiseled",
                (settings) -> new Block(settings),
                AbstractBlock.Settings.copy(familyBlock)
            );

            Block Cracked = register3("cracked_" + blockName + "_bricks",
                (settings) -> new Block(settings),
                AbstractBlock.Settings.copy(familyBlock)
            );

        } else {  
            Block Normal_Stairs = register3(blockName + "_stairs",
                (settings) -> new StairsBlock(familyBlock.getDefaultState(), settings),
                AbstractBlock.Settings.copy(familyBlock)
            );

            Block Normal_Slab = register3(blockName + "_slab",
                (settings) -> new SlabBlock(settings),
                AbstractBlock.Settings.copy(familyBlock)
            );

            Block Normal_Wall = register3(blockName + "_wall",
                (settings) -> new WallBlock(settings),
                AbstractBlock.Settings.copy(familyBlock)
            );
        }
    }

    public static void CustomPhantomWoodSystem() {

        String blockName = "phantom";
        String suffix = "";
        MapColor mapColor = MapColor.LICHEN_GREEN;
        Boolean isNatural = true;

        if (isNatural) {

            Block LOG = register3(blockName + "_log" + suffix,
                settings -> new PillarBlock(settings
                    .mapColor(state -> state.get(PillarBlock.AXIS) == Direction.Axis.Y ? mapColor : mapColor)
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F)
                    .sounds(BlockSoundGroup.WOOD)
                    .burnable()
                    .nonOpaque()
                ),
                AbstractBlock.Settings.create());

            Block STRIPPED_LOG = register3("stripped_" + blockName + "_log" + suffix,
                settings -> new PillarBlock(settings
                    .mapColor(state -> state.get(PillarBlock.AXIS) == Direction.Axis.Y ? mapColor : mapColor)
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F)
                    .sounds(BlockSoundGroup.WOOD)
                    .burnable()
                    .nonOpaque()
                ),
                AbstractBlock.Settings.create());

            Block WOOD = register3(blockName + "_wood" + suffix,
                settings -> new PillarBlock(settings
                    .mapColor(mapColor)
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F)
                    .sounds(BlockSoundGroup.WOOD)
                    .burnable()
                    .nonOpaque()
                ),
                AbstractBlock.Settings.create());

            Block STRIPPED_WOOD = register3("stripped_" + blockName + "_wood" + suffix,
                settings -> new PillarBlock(settings
                    .mapColor(mapColor)
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F)
                    .sounds(BlockSoundGroup.WOOD)
                    .burnable()
                    .nonOpaque()
                ),
                AbstractBlock.Settings.create());

            FlammableBlockRegistry.getDefaultInstance().add((BlockFactory.callBlock(blockName + "_log" + suffix)), 5, 5);
            FlammableBlockRegistry.getDefaultInstance().add((BlockFactory.callBlock("stripped_" + blockName + "_log" + suffix)), 5, 5);
            FlammableBlockRegistry.getDefaultInstance().add((BlockFactory.callBlock(blockName + "_wood" + suffix)), 5, 5);
            FlammableBlockRegistry.getDefaultInstance().add((BlockFactory.callBlock("stripped_" + blockName + "_wood" + suffix)), 5, 5);
            FlammableBlockRegistry.getDefaultInstance().add((BlockFactory.callBlock(blockName + "_leaves" + suffix)), 30, 60);

        }

        @SuppressWarnings("static-access")
        Block PLANKS = register3(blockName + "_planks" + suffix,
            settings -> new Block(settings.copy(Blocks.OAK_PLANKS).mapColor(mapColor).nonOpaque()),
            AbstractBlock.Settings.create());

        @SuppressWarnings("static-access")
        Block STAIRS = register3(blockName + "_stairs" + suffix,
            settings -> new StairsBlock(PLANKS.getDefaultState(), settings.copy(Blocks.OAK_STAIRS).mapColor(mapColor).nonOpaque()),
            AbstractBlock.Settings.create());

        @SuppressWarnings("static-access")
        Block SLAB = register3(blockName + "_slab" + suffix,
            settings -> new SlabBlock(settings.copy(Blocks.OAK_SLAB).mapColor(mapColor).nonOpaque()),
            AbstractBlock.Settings.create());

        @SuppressWarnings("static-access")
        Block FENCE = register3(blockName + "_fence" + suffix,
            settings -> new FenceBlock(settings.copy(Blocks.OAK_FENCE).mapColor(mapColor).nonOpaque()),
            AbstractBlock.Settings.create());

        @SuppressWarnings("static-access")
        Block FENCE_GATE = register3(blockName + "_fence_gate" + suffix,
            settings -> new FenceGateBlock(WoodType.OAK, settings.copy(Blocks.OAK_FENCE_GATE).mapColor(mapColor).nonOpaque()),
            AbstractBlock.Settings.create());

        @SuppressWarnings("static-access")
        Block DOOR = register3(blockName + "_door" + suffix,
            settings -> new DoorBlock(BlockSetType.OAK, settings.copy(Blocks.OAK_DOOR).mapColor(mapColor).nonOpaque()),
            AbstractBlock.Settings.create());

        @SuppressWarnings("static-access")
        Block GLASS_DOOR = register3(blockName + "_glass_door" + suffix,
            settings -> new DoorBlock(BlockSetType.OAK, settings.copy(Blocks.OAK_DOOR).mapColor(mapColor).nonOpaque()),
            AbstractBlock.Settings.create());

        @SuppressWarnings("static-access")
        Block TRAP_DOOR = register3(blockName + "_trapdoor" + suffix,
            settings -> new TrapdoorBlock(BlockSetType.OAK, settings.copy(Blocks.OAK_TRAPDOOR).mapColor(mapColor).nonOpaque()),
            AbstractBlock.Settings.create());

        @SuppressWarnings("static-access")
        Block GLASS_TRAP_DOOR = register3(blockName + "_glass_trapdoor" + suffix,
            settings -> new TrapdoorBlock(BlockSetType.OAK, settings.copy(Blocks.OAK_TRAPDOOR).mapColor(mapColor).nonOpaque()),
            AbstractBlock.Settings.create());

        @SuppressWarnings("static-access")
        Block P_PLATE = register3(blockName + "_pressure_plate" + suffix,
            settings -> new PressurePlateBlock(BlockSetType.OAK, settings.copy(Blocks.OAK_PRESSURE_PLATE).mapColor(mapColor).nonOpaque()),
            AbstractBlock.Settings.create());

        @SuppressWarnings("static-access")
        Block BUTTON = register3(blockName + "_button" + suffix,
            settings -> new ButtonBlock(BlockSetType.OAK, 30, settings.copy(Blocks.OAK_BUTTON).mapColor(mapColor).nonOpaque()),
            AbstractBlock.Settings.create());

        BlockFamilies.register(PLANKS)
            .button(BUTTON)
            .fence(FENCE)
            .fenceGate(FENCE_GATE)
            .pressurePlate(P_PLATE)
            .slab(SLAB)
            .stairs(STAIRS)
            .trapdoor(TRAP_DOOR)
            .door(DOOR)
            .door(GLASS_DOOR)
            .group("wooden")
            .unlockCriterionName("has_planks")
            .build();

        @SuppressWarnings("static-access")
        Block MOSAIC = register3(blockName + "_mosaic" + suffix,
            settings -> new Block(settings.copy(PLANKS).nonOpaque()),
            AbstractBlock.Settings.create());

        @SuppressWarnings("static-access")
        Block MOSAIC_STAIRS = register3(blockName + "_mosaic_stairs" + suffix,
            settings -> new StairsBlock(PLANKS.getDefaultState(), settings.copy(PLANKS).nonOpaque()),
            AbstractBlock.Settings.create());

        @SuppressWarnings("static-access")
        Block MOSAIC_SLAB = register3(blockName + "_mosaic_slab" + suffix,
            settings -> new SlabBlock(settings.copy(PLANKS).nonOpaque()),
            AbstractBlock.Settings.create());

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

    public static final Block PHANTOM_TORCH = register3(
        "phantom_torch",
        settings -> new TorchBlock(ParticleTypes.FLAME, settings),
		AbstractBlock.Settings.create().noCollision().breakInstantly().luminance(state -> 14).sounds(BlockSoundGroup.WOOD).pistonBehavior(PistonBehavior.DESTROY)
	);

    // Phantom Wall Torch
    public static final Block PHANTOM_WALL_TORCH = register3(
        "phantom_wall_torch",
        settings -> new WallTorchBlock(ParticleTypes.FLAME, settings),
		copyLootTable(PHANTOM_TORCH, true).noCollision().breakInstantly().luminance(state -> 14).sounds(BlockSoundGroup.WOOD).pistonBehavior(PistonBehavior.DESTROY)
	);

    private static AbstractBlock.Settings copyLootTable(Block block, boolean copyTranslationKey) {
		AbstractBlock.Settings settings = block.getSettings();
		AbstractBlock.Settings settings2 = AbstractBlock.Settings.create().lootTable(block.getLootTableKey());
		if (copyTranslationKey) {
			settings2 = settings2.overrideTranslationKey(block.getTranslationKey());
		}

		return settings2;
	}




    public static final Block POINTED_ICE = register3(
		"pointed_ice",
		PointedIceBlock::new,
			AbstractBlock.Settings.create()
				.mapColor(MapColor.PALE_PURPLE)
				.solid()
				.nonOpaque()
				.sounds(BlockSoundGroup.GLASS)
				.ticksRandomly()
				.strength(1.5F, 3.0F)
				.dynamicBounds()
				.offset(AbstractBlock.OffsetType.XZ)
				.pistonBehavior(PistonBehavior.DESTROY)
			    .solidBlock((state, world, pos) -> false)
		
	);


    //Registation:

    public static Block register(String id, Block block) {
        Identifier blockId = Identifier.of(EntStupidStuff.MOD_ID, id);

        Registry.register(Registries.BLOCK, blockId, block);

        // ✅ Then safely register the block's item using the SAME Identifier
        BlockItem blockItem = new BlockItem(block, new Item.Settings());
        Registry.register(Registries.ITEM, blockId, blockItem);

        if (EntStupidStuff.DEV_MODE) {
            System.out.println("Registered block + item: " + blockId);
        }

        System.out.println("Registered block + item: " + blockId);

        BlockList.put(blockId, block);

        return block;
    }

    private static void registerBlockItem2(String name, Block block) {

        BlockItem item = new BlockItem(block, new Item.Settings().useBlockPrefixedTranslationKey()
                        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(EntStupidStuff.MOD_ID, name))));

        Registry.register(Registries.ITEM, Identifier.of(EntStupidStuff.MOD_ID, name), item);

        ItemList.put(Identifier.of(EntStupidStuff.MOD_ID, name), item);
        System.out.println("Adding Item: " + item);
    }

    public static Block register3(String id, Function<AbstractBlock.Settings, Block> factory, AbstractBlock.Settings settings) {
		return register3(keyOf(id), factory, settings, id);
	}

    public static Block register3(RegistryKey<Block> key, Function<AbstractBlock.Settings, Block> factory, AbstractBlock.Settings settings, String id) {
		Block block = (Block)factory.apply(settings.registryKey(key));

        Identifier blockId = Identifier.of(EntStupidStuff.MOD_ID, id);
        BlockList.put(blockId, block);

        System.out.println("Adding Block: " + blockId);

        registerBlockItem2(id, block);
        ModGroup.addToDefault(id);

		return Registry.register(Registries.BLOCK, key, block);
	}

    public static Block register2(String id, Function<AbstractBlock.Settings, Block> factory, AbstractBlock.Settings settings) {
		return register2(keyOf(id), factory, settings, id);
	}

    public static Block register2(RegistryKey<Block> key, Function<AbstractBlock.Settings, Block> factory, AbstractBlock.Settings settings, String id) {
		Block block = (Block)factory.apply(settings.registryKey(key));

        Identifier blockId = Identifier.of(EntStupidStuff.MOD_ID, id);
        BlockList.put(blockId, block);

        BlockItem blockItem = new BlockItem(block, new Item.Settings());
        Registry.register(Registries.ITEM, blockId, blockItem);
        ItemList.put(blockId, blockItem);

		return Registry.register(Registries.BLOCK, key, block);
	}

    public static final Block ConceptEnchantment2 = BlockFactory.register3("dark_enchantment_table", DarkEnchantingTableBlock::new,
		AbstractBlock.Settings.create().mapColor(MapColor.RED).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().luminance(state -> 7).strength(5.0F, 1200.0F)
    );



}
