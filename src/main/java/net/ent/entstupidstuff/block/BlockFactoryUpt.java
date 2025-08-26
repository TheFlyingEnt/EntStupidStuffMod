package net.ent.entstupidstuff.block;

import java.util.LinkedHashMap;
import java.util.Map;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.item.ItemFactory;
import net.ent.entstupidstuff.item.ModGroup;
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
import net.minecraft.block.LanternBlock;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.MapColor;
import net.minecraft.block.Oxidizable;
import net.minecraft.block.OxidizableDoorBlock;
import net.minecraft.block.OxidizableTrapdoorBlock;
import net.minecraft.block.PaneBlock;
import net.minecraft.block.PillarBlock;
import net.minecraft.block.PressurePlateBlock;
import net.minecraft.block.SaplingBlock;
import net.minecraft.block.SlabBlock;
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
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;

public class BlockFactoryUpt {

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

        Block REDWOOD_PLANKS = register("redwood" + "_planks" + "", new Block(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).mapColor((MapColor.DULL_RED))));
        groupWoodFamilty("redwood", "", REDWOOD_PLANKS, true, MapColor.DULL_RED, MapColor.DULL_RED);
        //register("redwood" + "_sapling", new SaplingBlock(SaplingGeneratorFactory.REDWOOD, AbstractBlock.Settings.copy(Blocks.OAK_SAPLING)));

        Block FIR_PLANKS = register("fir" + "_planks" + "", new Block(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).mapColor((MapColor.TERRACOTTA_GRAY))));
        groupWoodFamilty("fir", "", FIR_PLANKS, true, MapColor.TERRACOTTA_GRAY, MapColor.SPRUCE_BROWN);
        BlockFactoryHelper.addSaplings("fir", SaplingGeneratorFactory.FIR);

        Block MAPLE_PLANKS = register("maple" + "_planks" + "", new Block(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).mapColor((MapColor.SPRUCE_BROWN))));
        groupWoodFamilty("maple", "", MAPLE_PLANKS, true, MapColor.DIRT_BROWN, MapColor.SPRUCE_BROWN);
        BlockFactoryHelper.addSaplings("maple", SaplingGeneratorFactory.MAPLE);

        Block PHANTOM_PLANKS = register("phantom" + "_planks" + "", new Block(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).mapColor((MapColor.LICHEN_GREEN)).nonOpaque()));
        groupWoodFamilty("phantom", "", PHANTOM_PLANKS, true, MapColor.LICHEN_GREEN, MapColor.DARK_GREEN);

        Block PHANTOM_LANTERN = register((String)"phantom_lantern", new LanternBlock(AbstractBlock.Settings.create().mapColor(MapColor.IRON_GRAY).solid().requiresTool().strength(3.5F).sounds(BlockSoundGroup.LANTERN).luminance((state) -> {
            return 10;
        }).nonOpaque().pistonBehavior(PistonBehavior.DESTROY)));

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
        
        

        Block REDWOOD_PLANKS = register("redwood" + "_planks" + "", new Block(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).mapColor((MapColor.DULL_RED))));
        BlockFactoryHelper.BaseFamily("redwood", "", REDWOOD_PLANKS, true);
        BlockFactoryHelper.InteractionFamily("redwood", "", REDWOOD_PLANKS, MapColor.DULL_RED, true, true);
        BlockFactoryHelper.NatureFamily("redwood" , "", MapColor.DARK_RED, MapColor.SPRUCE_BROWN, true, true);
        BlockFactoryHelper.MosicFamily("redwood" , "", REDWOOD_PLANKS, true);
        //register("redwood" + "_sapling", new SaplingBlock(SaplingGeneratorFactory.REDWOOD, AbstractBlock.Settings.copy(Blocks.OAK_SAPLING)));

        Block FIR_PLANKS = register("fir" + "_planks" + "", new Block(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).mapColor((MapColor.TERRACOTTA_GRAY))));
        BlockFactoryHelper.InteractionFamily("fir", "", FIR_PLANKS, MapColor.SPRUCE_BROWN, true, true);
        BlockFactoryHelper.BaseFamily("fir", "", FIR_PLANKS, true);
        BlockFactoryHelper.NatureFamily("fir" , "", MapColor.TERRACOTTA_GRAY, MapColor.SPRUCE_BROWN, true, true);
        BlockFactoryHelper.MosicFamily("fir" , "", FIR_PLANKS, true);
        register("fir" + "_sapling", new SaplingBlock(SaplingGeneratorFactory.FIR, AbstractBlock.Settings.copy(Blocks.OAK_SAPLING)));

        Block MAPLE_PLANKS = register("maple" + "_planks" + "", new Block(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).mapColor((MapColor.SPRUCE_BROWN))));
        BlockFactoryHelper.InteractionFamily("maple", "", MAPLE_PLANKS, MapColor.SPRUCE_BROWN, true, true);
        BlockFactoryHelper.BaseFamily("maple", "", MAPLE_PLANKS, true);
        BlockFactoryHelper.NatureFamily("maple" , "", MapColor.TERRACOTTA_GRAY, MapColor.SPRUCE_BROWN, true, true);
        BlockFactoryHelper.MosicFamily("maple" , "", MAPLE_PLANKS, true);
        register("maple" + "_sapling", new SaplingBlock(SaplingGeneratorFactory.MAPLE, AbstractBlock.Settings.copy(Blocks.OAK_SAPLING)));

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

        Block FUNGAL_PLANKS = register("fungal" + "_planks" + Varient, new Block(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).mapColor((MainColor))));
        BlockFactoryHelper.BaseFamily("fungal", Varient, FUNGAL_PLANKS, true);
        BlockFactoryHelper.InteractionFamily("fungal", Varient, FUNGAL_PLANKS, MainColor, true, true);
        BlockFactoryHelper.MosicFamily("fungal" , Varient, FUNGAL_PLANKS, true);

        BlockFamilies.register(FUNGAL_PLANKS)
            .button(BlockFactoryUpt.callBlock("fungal" + "_" + "button" + Varient))
            .fence(BlockFactoryUpt.callBlock("fungal" + "_" + "fence" + Varient))
            .fenceGate(BlockFactoryUpt.callBlock("fungal" + "_" + "fence_gate" + Varient))
            .pressurePlate(BlockFactoryUpt.callBlock("fungal" + "_" + "button" + Varient))
            .slab(BlockFactoryUpt.callBlock("fungal" + "_slab" + Varient))
            .stairs(BlockFactoryUpt.callBlock("fungal" + "_stairs" + Varient))
            .trapdoor(BlockFactoryUpt.callBlock("fungal" + "_" + "trapdoor" + Varient))
            .trapdoor(BlockFactoryUpt.callBlock("fungal" + "_" + "glass_trapdoor" + Varient))
            //.sign(SIGN, WALL_SIGN)
            .door(BlockFactoryUpt.callBlock("fungal" + "_" + "door" + Varient)) 
            .door(BlockFactoryUpt.callBlock("fungal" + "_" + "glass_door" + Varient))
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
            .button(BlockFactoryUpt.callBlock(MainName + "_" + "button" + Varient))
            .fence(BlockFactoryUpt.callBlock(MainName + "_" + "fence" + Varient))
            .fenceGate(BlockFactoryUpt.callBlock(MainName + "_" + "fence_gate" + Varient))
            .pressurePlate(BlockFactoryUpt.callBlock(MainName + "_" + "button" + Varient))
            .slab(BlockFactoryUpt.callBlock(MainName + "_slab" + Varient))
            .stairs(BlockFactoryUpt.callBlock(MainName + "_stairs" + Varient))
            .trapdoor(BlockFactoryUpt.callBlock(MainName + "_" + "trapdoor" + Varient))
            .trapdoor(BlockFactoryUpt.callBlock(MainName + "_" + "glass_trapdoor" + Varient))
            //.sign(SIGN, WALL_SIGN)
            .door(BlockFactoryUpt.callBlock(MainName + "_" + "door" + Varient)) 
            .door(BlockFactoryUpt.callBlock(MainName + "_" + "glass_door" + Varient))
        .group("wooden")
        .unlockCriterionName("has_planks")
        .build();
        
    }
    




    public static void onInitialize() {

        /*

        WoodSystem("redwood", null, MapColor.DULL_RED, true); //Redwood Wood
        WoodSystem("maple", null, MapColor.DULL_RED, true); //Maple Wood
        //WoodSystem("desert_iron", null, MapColor.TERRACOTTA_BROWN, true);
        WoodSystem("fir", null, MapColor.TERRACOTTA_GRAY, true);
        CustomPhantomWoodSystem(); //WoodSystem("phantom", null, MapColor.LICHEN_GREEN, false);

        Block PHANTOM_LANTERN = register((String)"phantom_lantern", new LanternBlock(AbstractBlock.Settings.create().mapColor(MapColor.IRON_GRAY).solid().requiresTool().strength(3.5F).sounds(BlockSoundGroup.LANTERN).luminance((state) -> {
            return 10;
        }).nonOpaque().pistonBehavior(PistonBehavior.DESTROY)));

        Block FIR_LEAVES = register("fir_leaves", new LeavesBlock(AbstractBlock.Settings.copy(Blocks.OAK_LEAVES)));
        Block REDWOOD_LEAVES = register("redwood_leaves", new LeavesBlock(AbstractBlock.Settings.copy(Blocks.OAK_LEAVES)));
        Block MAPLE_LEAVES = register("maple_leaves", new MapleLeavesBlock(AbstractBlock.Settings.copy(Blocks.OAK_LEAVES)));

        register("maple_sapling", new SaplingBlock(SaplingGeneratorFactory.MAPLE, AbstractBlock.Settings.copy(Blocks.OAK_SAPLING)));
        register("redwood_sapling", new SaplingBlock(SaplingGeneratorFactory.MAPLE, AbstractBlock.Settings.copy(Blocks.OAK_SAPLING)));
        register("fir_sapling", new SaplingBlock(SaplingGeneratorFactory.FIR, AbstractBlock.Settings.copy(Blocks.OAK_SAPLING)));

        //onInitializeNew();

        WoodSystem("fungal", null, MapColor.OFF_WHITE, false);

        WoodSystem("fungal", "white", MapColor.OFF_WHITE, false);
        WoodSystem("fungal", "light_gray", MapColor.LIGHT_GRAY, false);
        WoodSystem("fungal", "gray", MapColor.GREEN, false);
        WoodSystem("fungal", "black", MapColor.BLACK, false);
        WoodSystem("fungal", "brown", MapColor.BROWN, false);
        WoodSystem("fungal", "red", MapColor.RED, false);
        WoodSystem("fungal", "orange", MapColor.ORANGE, false);
        WoodSystem("fungal", "yellow", MapColor.YELLOW, false);
        WoodSystem("fungal", "lime", MapColor.LIME, false);
        WoodSystem("fungal", "green", MapColor.GREEN, false);
        WoodSystem("fungal", "cyan", MapColor.CYAN, false);
        WoodSystem("fungal", "light_blue", MapColor.LIGHT_BLUE, false);
        WoodSystem("fungal", "blue", MapColor.BLUE, false);
        WoodSystem("fungal", "purple", MapColor.PURPLE, false);
        WoodSystem("fungal", "magenta", MapColor.MAGENTA, false);
        WoodSystem("fungal", "pink", MapColor.PINK, false);*/

        onInitializeNewUpdated();
        
        // Adding Vanilla Glassdoor and TrapDoors
        OxidizableFamily();

        register("iron" + "_glass_door", new DoorBlock(BlockSetType.IRON, AbstractBlock.Settings.copy(Blocks.IRON_DOOR)));
        register("iron" + "_glass_trapdoor", new TrapdoorBlock(BlockSetType.IRON, AbstractBlock.Settings.copy(Blocks.IRON_TRAPDOOR)));

        Block OAK_DOOR = register("oak_glass_door", new DoorBlock(BlockSetType.OAK, AbstractBlock.Settings.copy(Blocks.OAK_DOOR)));
        Block SPRUCE_DOOR = register("spruce_glass_door", new DoorBlock(BlockSetType.OAK, AbstractBlock.Settings.copy(Blocks.SPRUCE_DOOR)));
        Block JUNGLE_DOOR = register("jungle_glass_door", new DoorBlock(BlockSetType.OAK, AbstractBlock.Settings.copy(Blocks.JUNGLE_DOOR)));
        Block BIRCH_DOOR = register("birch_glass_door", new DoorBlock(BlockSetType.OAK, AbstractBlock.Settings.copy(Blocks.BIRCH_DOOR)));
        Block DARK_OAK_DOOR = register("dark_oak_glass_door", new DoorBlock(BlockSetType.OAK, AbstractBlock.Settings.copy(Blocks.DARK_OAK_DOOR)));
        Block ACACIA_DOOR = register("acacia_glass_door", new DoorBlock(BlockSetType.OAK, AbstractBlock.Settings.copy(Blocks.ACACIA_DOOR)));
        Block MANGROVE_DOOR = register("mangrove_glass_door", new DoorBlock(BlockSetType.OAK, AbstractBlock.Settings.copy(Blocks.MANGROVE_DOOR)));
        Block CHERRY_DOOR = register("cherry_glass_door", new DoorBlock(BlockSetType.OAK, AbstractBlock.Settings.copy(Blocks.CHERRY_DOOR)));
        Block BAMBOO_DOOR = register("bamboo_glass_door", new DoorBlock(BlockSetType.OAK, AbstractBlock.Settings.copy(Blocks.BAMBOO_DOOR)));

        register("crimson_glass_door", new DoorBlock(BlockSetType.OAK, AbstractBlock.Settings.copy(Blocks.CRIMSON_DOOR)));
        register("warped_glass_door", new DoorBlock(BlockSetType.OAK, AbstractBlock.Settings.copy(Blocks.WARPED_DOOR)));

        FlammableBlockRegistry.getDefaultInstance().add(OAK_DOOR, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(SPRUCE_DOOR, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(JUNGLE_DOOR, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(BIRCH_DOOR, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(DARK_OAK_DOOR, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(ACACIA_DOOR, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(MANGROVE_DOOR, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(CHERRY_DOOR, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(BAMBOO_DOOR, 5, 20);

        Block OAK_TRAPDOOR = register("oak_glass_trapdoor", new TrapdoorBlock(BlockSetType.OAK, AbstractBlock.Settings.copy(Blocks.OAK_TRAPDOOR)));
        Block SPRUCE_TRAPDOOR = register("spruce_glass_trapdoor", new TrapdoorBlock(BlockSetType.OAK, AbstractBlock.Settings.copy(Blocks.SPRUCE_TRAPDOOR)));
        Block JUNGLE_TRAPDOOR = register("jungle_glass_trapdoor", new TrapdoorBlock(BlockSetType.OAK, AbstractBlock.Settings.copy(Blocks.JUNGLE_TRAPDOOR)));
        Block BIRCH_TRAPDOOR = register("birch_glass_trapdoor", new TrapdoorBlock(BlockSetType.OAK, AbstractBlock.Settings.copy(Blocks.BIRCH_TRAPDOOR)));
        Block DARK_OAK_TRAPDOOR = register("dark_oak_glass_trapdoor", new TrapdoorBlock(BlockSetType.OAK, AbstractBlock.Settings.copy(Blocks.DARK_OAK_TRAPDOOR)));
        Block ACACIA_TRAPDOOR = register("acacia_glass_trapdoor", new TrapdoorBlock(BlockSetType.OAK, AbstractBlock.Settings.copy(Blocks.ACACIA_TRAPDOOR)));
        Block MANGROVE_TRAPDOOR = register("mangrove_glass_trapdoor", new TrapdoorBlock(BlockSetType.OAK, AbstractBlock.Settings.copy(Blocks.MANGROVE_TRAPDOOR)));
        Block CHERRY_TRAPDOOR = register("cherry_glass_trapdoor", new TrapdoorBlock(BlockSetType.OAK, AbstractBlock.Settings.copy(Blocks.CHERRY_TRAPDOOR)));
        Block BAMBOO_TRAPDOOR = register("bamboo_glass_trapdoor", new TrapdoorBlock(BlockSetType.OAK, AbstractBlock.Settings.copy(Blocks.BAMBOO_TRAPDOOR)));

        register("crimson_glass_trapdoor", new TrapdoorBlock(BlockSetType.OAK, AbstractBlock.Settings.copy(Blocks.CRIMSON_TRAPDOOR)));
        register("warped_glass_trapdoor", new TrapdoorBlock(BlockSetType.OAK, AbstractBlock.Settings.copy(Blocks.WARPED_TRAPDOOR)));

        FlammableBlockRegistry.getDefaultInstance().add(OAK_TRAPDOOR, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(SPRUCE_TRAPDOOR, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(JUNGLE_TRAPDOOR, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(BIRCH_TRAPDOOR, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(DARK_OAK_TRAPDOOR, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(ACACIA_TRAPDOOR, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(MANGROVE_TRAPDOOR, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(CHERRY_TRAPDOOR, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(BAMBOO_TRAPDOOR, 5, 20);

        MosicFamily("oak", "", Blocks.OAK_PLANKS);
        MosicFamily("spruce", "", Blocks.SPRUCE_PLANKS);
        MosicFamily("jungle", "", Blocks.JUNGLE_PLANKS);
        MosicFamily("birch", "", Blocks.BIRCH_PLANKS);
        MosicFamily("dark_oak", "", Blocks.DARK_OAK_PLANKS);
        MosicFamily("acacia", "", Blocks.ACACIA_PLANKS);
        MosicFamily("mangrove", "", Blocks.MANGROVE_PLANKS);
        MosicFamily("cherry", "", Blocks.CHERRY_PLANKS);
        //MosicFamily("pale_oak", PLANKS);

        MosicFamily("crimson", "", Blocks.CRIMSON_PLANKS, true);
        MosicFamily("warped", "", Blocks.WARPED_PLANKS, true);


        //Continue - Adding Stone Varients
        StoneSystem("andesite", MapColor.STONE_GRAY, true, Blocks.POLISHED_ANDESITE);
        StoneSystem("granite", MapColor.DIRT_BROWN, true, Blocks.POLISHED_GRANITE);
        StoneSystem("diorite", MapColor.OFF_WHITE, true, Blocks.POLISHED_DIORITE);
        register("polished_andesite" + "_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.POLISHED_ANDESITE)));
        register("polished_granite" + "_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.POLISHED_GRANITE)));
        register("polished_diorite" + "_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.POLISHED_DIORITE)));

        //Textured Wools
        Block TEXTURED_WHITE_WOOL = register("textured_wool_white", new Block(AbstractBlock.Settings.copy(Blocks.WHITE_WOOL)));
        Block TEXTURED_LIGHT_GRAY_WOOL = register("textured_wool_light_gray", new Block(AbstractBlock.Settings.copy(Blocks.LIGHT_GRAY_WOOL)));
        Block TEXTURED_GRAY_WOOL = register("textured_wool_gray", new Block(AbstractBlock.Settings.copy(Blocks.GRAY_WOOL)));
        Block TEXTURED_BLACK_WOOL = register("textured_wool_black", new Block(AbstractBlock.Settings.copy(Blocks.BLACK_WOOL)));
        Block TEXTURED_BROWN_WOOL = register("textured_wool_brown", new Block(AbstractBlock.Settings.copy(Blocks.BROWN_WOOL)));
        Block TEXTURED_RED_WOOL = register("textured_wool_red", new Block(AbstractBlock.Settings.copy(Blocks.RED_WOOL)));
        Block TEXTURED_ORANGE_WOOL = register("textured_wool_orange", new Block(AbstractBlock.Settings.copy(Blocks.ORANGE_WOOL)));
        Block TEXTURED_YELLOW_WOOL = register("textured_wool_yellow", new Block(AbstractBlock.Settings.copy(Blocks.YELLOW_WOOL)));
        Block TEXTURED_LIME_WOOL = register("textured_wool_lime", new Block(AbstractBlock.Settings.copy(Blocks.LIME_WOOL)));
        Block TEXTURED_GREEN_WOOL = register("textured_wool_green", new Block(AbstractBlock.Settings.copy(Blocks.GREEN_WOOL)));
        Block TEXTURED_CYAN_WOOL = register("textured_wool_cyan", new Block(AbstractBlock.Settings.copy(Blocks.CYAN_WOOL)));
        Block TEXTURED_LIGHT_BLUE_WOOL = register("textured_wool_light_blue", new Block(AbstractBlock.Settings.copy(Blocks.LIGHT_BLUE_WOOL)));
        Block TEXTURED_BLUE_WOOL = register("textured_wool_blue", new Block(AbstractBlock.Settings.copy(Blocks.BLUE_WOOL)));
        Block TEXTURED_PURPLE_WOOL = register("textured_wool_purple", new Block(AbstractBlock.Settings.copy(Blocks.PURPLE_WOOL)));
        Block TEXTURED_MAGENTA_WOOL = register("textured_wool_magenta", new Block(AbstractBlock.Settings.copy(Blocks.MAGENTA_WOOL)));
        Block TEXTURED_PINK_WOOL = register("textured_wool_pink", new Block(AbstractBlock.Settings.copy(Blocks.PINK_WOOL)));

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

        register("string_gate", new PaneBlock(AbstractBlock.Settings.copy(Blocks.IRON_BARS).nonOpaque()
				.allowsSpawning(Blocks::never)
				.solidBlock(Blocks::never)
				.suffocates(Blocks::never)
		.blockVision(Blocks::never)));

        register("string_block", new TransparentBlock(AbstractBlock.Settings.copy(Blocks.WHITE_WOOL).nonOpaque()
				.allowsSpawning(Blocks::never)
				.solidBlock(Blocks::never)
				.suffocates(Blocks::never)
		.blockVision(Blocks::never)));

        Block LIMESTONE = register("limestone", new Block(AbstractBlock.Settings.copy(Blocks.LIGHT_GRAY_TERRACOTTA)));
        Block POLISHED_LIMESTONE = register("polished_limestone", new Block(AbstractBlock.Settings.copy(Blocks.LIGHT_GRAY_TERRACOTTA)));

        StoneSystem("limestone", MapColor.TERRACOTTA_LIGHT_GRAY, false, LIMESTONE);
        StoneSystem("polished_limestone", MapColor.TERRACOTTA_LIGHT_GRAY, false, POLISHED_LIMESTONE);
        StoneSystem("polished_limestone", MapColor.TERRACOTTA_LIGHT_GRAY, true, POLISHED_LIMESTONE);


        //BlockSystem(Blocks.OAK_WOOD, "oak_wood", null, MapColor.OAK_TAN);

        //ItemFactory.BlockItem();


    }
    /* Register Kit */

    public static Block register(String name, Block block) {

        Identifier id = Identifier.of(EntStupidStuff.MOD_ID, name);
        BlockList.put(id, block);
        BlockItem.put(id, block);


        BlockItem blockItem = new BlockItem(block, new Item.Settings());
        ItemFactory.registerItems(name, blockItem);



        System.out.println(id.toString());
        return Registry.register(Registries.BLOCK, id, block);
	}

    private static Block registerBlock(String name, Block block) {
        Identifier id = Identifier.of(EntStupidStuff.MOD_ID, name);
        BlockList.put(id, block);
        
        System.out.println(id.toString());
        return Registry.register(Registries.BLOCK, id, block);
    }


    public static Block registerNonReg(String name, Block block) {

        Identifier id = Identifier.of(EntStupidStuff.MOD_ID, name);
        BlockList.put(id, block);
        return Registry.register(Registries.BLOCK, id, block);
	}

    public static Block callBlock(String blockID) {
        try {
            Identifier id = Identifier.of(EntStupidStuff.MOD_ID, blockID);
            return BlockList.get(id);
        } catch(Exception e) {
            System.out.println("An Error was called in callBlock()... Tried to call: " + blockID);
            return null;
        }

    }

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

        ModGroup.addToDeco(blockName + "_stairs" + suffix);
        ModGroup.addToDeco(blockName + "_slab" + suffix);
        ModGroup.addToDeco(blockName + "_fence" + suffix);
        ModGroup.addToDeco(blockName + "_fence_gate" + suffix);
        ModGroup.addToDeco(blockName + "_pressure_plate" + suffix);
        ModGroup.addToDeco(blockName + "_button" + suffix);


        FlammableBlockRegistry.getDefaultInstance().add((BlockFactoryUpt.callBlock(blockName + "_planks" + suffix)), 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add((BlockFactoryUpt.callBlock(blockName + "_stairs" + suffix)), 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add((BlockFactoryUpt.callBlock(blockName + "_slab" + suffix)), 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add((BlockFactoryUpt.callBlock(blockName + "_fence" + suffix)), 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add((BlockFactoryUpt.callBlock(blockName + "_fence_gate" + suffix)), 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add((BlockFactoryUpt.callBlock(blockName + "_pressure_plate" + suffix)), 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add((BlockFactoryUpt.callBlock(blockName + "_button" + suffix)), 5, 20);

        

    }
    

    public static void WoodSystem(String blockName, String suffix, MapColor mapColor, Boolean isNatural) {

        if (suffix == null) {suffix = "";}
        else {suffix = "_" + suffix;}

        if (isNatural) {
            Block LOG = register(blockName + "_log" + suffix, 
            Blocks.createLogBlock(mapColor, mapColor));

            Block STRIPPED_LOG = register("stripped_" + blockName + "_log" + suffix, 
            Blocks.createLogBlock(mapColor, mapColor));

            Block WOOD = register(blockName + "_wood" + suffix, 
                new PillarBlock(AbstractBlock.Settings.create().mapColor(mapColor).instrument(NoteBlockInstrument.BASS).strength(2.0F).sounds(BlockSoundGroup.WOOD).burnable()));

            Block STRIPPED_WOOD = register("stripped_" + blockName + "_wood" + suffix, 
                new PillarBlock(AbstractBlock.Settings.create().mapColor(mapColor).instrument(NoteBlockInstrument.BASS).strength(2.0F).sounds(BlockSoundGroup.WOOD).burnable()));

            //Block LEAVES = register(blockName + "_leaves" + suffix, new LeavesBlock(AbstractBlock.Settings.copy(Blocks.OAK_LEAVES)));

            ModGroup.addToNatural(blockName + "_log" + suffix);
            ModGroup.addToNatural("stripped_" + blockName + "_log" + suffix);
            ModGroup.addToNatural(blockName + "_wood" + suffix);
            ModGroup.addToNatural("stripped_" + blockName + "_wood" + suffix);

            ModGroup.addToDeco(blockName + "_log" + suffix);
            ModGroup.addToDeco("stripped_" + blockName + "_log" + suffix);
            ModGroup.addToDeco(blockName + "_wood" + suffix);
            ModGroup.addToDeco("stripped_" + blockName + "_wood" + suffix);

            FlammableBlockRegistry.getDefaultInstance().add((BlockFactoryUpt.callBlock(blockName + "_log" + suffix)), 5, 5);
            FlammableBlockRegistry.getDefaultInstance().add((BlockFactoryUpt.callBlock("stripped_" + blockName + "_log" + suffix)), 5, 5);
            FlammableBlockRegistry.getDefaultInstance().add((BlockFactoryUpt.callBlock(blockName + "_wood" + suffix)), 5, 5);
            FlammableBlockRegistry.getDefaultInstance().add((BlockFactoryUpt.callBlock("stripped_" + blockName + "_wood" + suffix)), 5, 5);
            FlammableBlockRegistry.getDefaultInstance().add((BlockFactoryUpt.callBlock(blockName + "_leaves" + suffix)), 30, 60);
            

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

        ModGroup.addToDeco(blockName + "_planks" + suffix);
        ModGroup.addToDeco(blockName + "_stairs" + suffix);
        ModGroup.addToDeco(blockName + "_slab" + suffix);
        ModGroup.addToDeco(blockName + "_fence" + suffix);
        ModGroup.addToDeco(blockName + "_fence_gate" + suffix);
        ModGroup.addToDeco(blockName + "_door" + suffix);
        ModGroup.addToDeco(blockName + "_glass_door" + suffix);
        ModGroup.addToDeco(blockName + "_trapdoor" + suffix);
        ModGroup.addToDeco(blockName + "_glass_trapdoor" + suffix);
        ModGroup.addToDeco(blockName + "_pressure_plate" + suffix);
        ModGroup.addToDeco(blockName + "_button" + suffix);
        //ModGroup.addToDeco(blockName + "_sign" + suffix);
        //ModGroup.addToDeco(blockName + "_hanging_sign" + suffix);

        FlammableBlockRegistry.getDefaultInstance().add((BlockFactoryUpt.callBlock(blockName + "_planks" + suffix)), 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add((BlockFactoryUpt.callBlock(blockName + "_stairs" + suffix)), 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add((BlockFactoryUpt.callBlock(blockName + "_slab" + suffix)), 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add((BlockFactoryUpt.callBlock(blockName + "_fence" + suffix)), 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add((BlockFactoryUpt.callBlock(blockName + "_fence_gate" + suffix)), 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add((BlockFactoryUpt.callBlock(blockName + "_door" + suffix)), 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add((BlockFactoryUpt.callBlock(blockName + "_glass_door" + suffix)), 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add((BlockFactoryUpt.callBlock(blockName + "_trapdoor" + suffix)), 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add((BlockFactoryUpt.callBlock(blockName + "_glass_trapdoor" + suffix)), 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add((BlockFactoryUpt.callBlock(blockName + "_pressure_plate" + suffix)), 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add((BlockFactoryUpt.callBlock(blockName + "_button" + suffix)), 5, 20);


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

        ModGroup.addToDeco(blockName + "_mosaic" + varient);
        ModGroup.addToDeco(blockName + "_mosaic_stairs" + varient);
        ModGroup.addToDeco(blockName + "_mosaic_slab" + varient);
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

        ModGroup.addToDeco(blockName + "_mosaic" + varient);
        ModGroup.addToDeco(blockName + "_mosaic_stairs" + varient);
        ModGroup.addToDeco(blockName + "_mosaic_slab" + varient);
    }

    
    public static void OxidizableFamily() { /* Make this Dynamic */

        Block COPPER = register("copper_glass_door", new OxidizableDoorBlock(BlockSetType.COPPER, Oxidizable.OxidationLevel.UNAFFECTED, AbstractBlock.Settings.copy(Blocks.COPPER_DOOR)));  
        Block EXPOSED_COPPER = register("exposed_copper_glass_door", new OxidizableDoorBlock(BlockSetType.COPPER, Oxidizable.OxidationLevel.EXPOSED, AbstractBlock.Settings.copy(Blocks.COPPER_DOOR)));
        Block OXIDIZED_COPPER = register("oxidized_copper_glass_door", new OxidizableDoorBlock(BlockSetType.COPPER, Oxidizable.OxidationLevel.OXIDIZED, AbstractBlock.Settings.copy(Blocks.COPPER_DOOR)));  
        Block WEATHERED_COPPER = register("weathered_copper_glass_door", new OxidizableDoorBlock(BlockSetType.COPPER, Oxidizable.OxidationLevel.WEATHERED, AbstractBlock.Settings.copy(Blocks.COPPER_DOOR)));    

        Block WAXED_COPPER = register("waxed_copper_glass_door", new DoorBlock(BlockSetType.COPPER, AbstractBlock.Settings.copy(COPPER)));  
        Block WAXED_EXPOSED_COPPER = register("waxed_exposed_copper_glass_door", new DoorBlock(BlockSetType.COPPER, AbstractBlock.Settings.copy(EXPOSED_COPPER)));
        Block WAXED_OXIDIZED_COPPER = register("waxed_oxidized_copper_glass_door", new DoorBlock(BlockSetType.COPPER, AbstractBlock.Settings.copy(OXIDIZED_COPPER)));  
        Block WAXED_WEATHERED_COPPER = register("waxed_weathered_copper_glass_door", new DoorBlock(BlockSetType.COPPER, AbstractBlock.Settings.copy(WEATHERED_COPPER)));    
        
        OxidizableBlocksRegistry.registerOxidizableBlockPair(COPPER, EXPOSED_COPPER);
        OxidizableBlocksRegistry.registerOxidizableBlockPair(EXPOSED_COPPER, OXIDIZED_COPPER);
        OxidizableBlocksRegistry.registerOxidizableBlockPair(OXIDIZED_COPPER, WEATHERED_COPPER);
        
        OxidizableBlocksRegistry.registerWaxableBlockPair(COPPER, WAXED_COPPER);
        OxidizableBlocksRegistry.registerWaxableBlockPair(EXPOSED_COPPER, WAXED_EXPOSED_COPPER);
        OxidizableBlocksRegistry.registerWaxableBlockPair(OXIDIZED_COPPER, WAXED_OXIDIZED_COPPER);
        OxidizableBlocksRegistry.registerWaxableBlockPair(WEATHERED_COPPER, WAXED_WEATHERED_COPPER);

        //Trapdoors

        Block COPPER_1 = register("copper_glass_trapdoor", new OxidizableTrapdoorBlock(BlockSetType.COPPER, Oxidizable.OxidationLevel.UNAFFECTED, AbstractBlock.Settings.copy(Blocks.COPPER_TRAPDOOR)));  
        Block EXPOSED_COPPER_1 = register("exposed_copper_glass_trapdoor", new OxidizableTrapdoorBlock(BlockSetType.COPPER, Oxidizable.OxidationLevel.EXPOSED, AbstractBlock.Settings.copy(Blocks.COPPER_TRAPDOOR)));
        Block OXIDIZED_COPPER_1 = register("oxidized_copper_glass_trapdoor", new OxidizableTrapdoorBlock(BlockSetType.COPPER, Oxidizable.OxidationLevel.OXIDIZED, AbstractBlock.Settings.copy(Blocks.COPPER_TRAPDOOR)));  
        Block WEATHERED_COPPER_1 = register("weathered_copper_glass_trapdoor", new OxidizableTrapdoorBlock(BlockSetType.COPPER, Oxidizable.OxidationLevel.WEATHERED, AbstractBlock.Settings.copy(Blocks.COPPER_TRAPDOOR)));    

        Block WAXED_COPPER_1 = register("waxed_copper_glass_trapdoor", new TrapdoorBlock(BlockSetType.COPPER, AbstractBlock.Settings.copy(COPPER_1)));  
        Block WAXED_EXPOSED_COPPER_1 = register("waxed_exposed_copper_glass_trapdoor", new TrapdoorBlock(BlockSetType.COPPER, AbstractBlock.Settings.copy(EXPOSED_COPPER_1)));
        Block WAXED_OXIDIZED_COPPER_1 = register("waxed_oxidized_copper_glass_trapdoor", new TrapdoorBlock(BlockSetType.COPPER, AbstractBlock.Settings.copy(OXIDIZED_COPPER_1)));  
        Block WAXED_WEATHERED_COPPER_1 = register("waxed_weathered_copper_glass_trapdoor", new TrapdoorBlock(BlockSetType.COPPER, AbstractBlock.Settings.copy(WEATHERED_COPPER_1)));    
        
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
            Block Bricks = register(blockName + "_bricks", new Block(AbstractBlock.Settings.copy(familyBlock)));
            Block Stairs = register(blockName + "_brick_stairs", new StairsBlock(Bricks.getDefaultState(), AbstractBlock.Settings.copy(familyBlock))); 
            Block Slab = register(blockName + "_brick_slab", new SlabBlock(AbstractBlock.Settings.copy(familyBlock)));
            Block Wall = register(blockName + "_brick_wall", new WallBlock(AbstractBlock.Settings.copy(familyBlock)));
            Block Chizeled = register(blockName + "_brick_chiseled", new Block(AbstractBlock.Settings.copy(familyBlock)));
            Block Cracked = register("cracked_" + blockName + "_bricks", new Block(AbstractBlock.Settings.copy(familyBlock)));
        } else {  
            Block Normal_Stairs = register(blockName + "_stairs", new StairsBlock(familyBlock.getDefaultState(), AbstractBlock.Settings.copy(familyBlock))); 
            Block Normal_Slab = register(blockName + "_slab", new SlabBlock(AbstractBlock.Settings.copy(familyBlock)));
            Block Normal_Wall = register(blockName + "_wall", new WallBlock(AbstractBlock.Settings.copy(familyBlock)));
        }

    }

    public static void CustomPhantomWoodSystem() {

        String blockName = "phantom";
        String suffix = "";
        MapColor mapColor = MapColor.LICHEN_GREEN;
        Boolean isNatural = true;

        if (isNatural) {

            Block LOG = register(blockName + "_log" + suffix, 
            new PillarBlock(
			AbstractBlock.Settings.create()
				.mapColor(state -> state.get(PillarBlock.AXIS) == Direction.Axis.Y ? mapColor : mapColor)
				.instrument(NoteBlockInstrument.BASS)
				.strength(2.0F)
				.sounds(BlockSoundGroup.WOOD)
				.burnable()
                .nonOpaque()
		    ));

            Block STRIPPED_LOG = register("stripped_" + blockName + "_log" + suffix, 
            new PillarBlock(
			AbstractBlock.Settings.create()
				.mapColor(state -> state.get(PillarBlock.AXIS) == Direction.Axis.Y ? mapColor : mapColor)
				.instrument(NoteBlockInstrument.BASS)
				.strength(2.0F)
				.sounds(BlockSoundGroup.WOOD)
				.burnable()
                .nonOpaque()
		    ));

            Block WOOD = register(blockName + "_wood" + suffix, 
                new PillarBlock(AbstractBlock.Settings.create().mapColor(mapColor).instrument(NoteBlockInstrument.BASS).strength(2.0F).sounds(BlockSoundGroup.WOOD).burnable().nonOpaque()));

            Block STRIPPED_WOOD = register("stripped_" + blockName + "_wood" + suffix, 
                new PillarBlock(AbstractBlock.Settings.create().mapColor(mapColor).instrument(NoteBlockInstrument.BASS).strength(2.0F).sounds(BlockSoundGroup.WOOD).burnable().nonOpaque()));

            ModGroup.addToNatural(blockName + "_log" + suffix);
            ModGroup.addToNatural("stripped_" + blockName + "_log" + suffix);
            ModGroup.addToNatural(blockName + "_wood" + suffix);
            ModGroup.addToNatural("stripped_" + blockName + "_wood" + suffix);

            ModGroup.addToDeco(blockName + "_log" + suffix);
            ModGroup.addToDeco("stripped_" + blockName + "_log" + suffix);
            ModGroup.addToDeco(blockName + "_wood" + suffix);
            ModGroup.addToDeco("stripped_" + blockName + "_wood" + suffix);

            FlammableBlockRegistry.getDefaultInstance().add((BlockFactoryUpt.callBlock(blockName + "_log" + suffix)), 5, 5);
            FlammableBlockRegistry.getDefaultInstance().add((BlockFactoryUpt.callBlock("stripped_" + blockName + "_log" + suffix)), 5, 5);
            FlammableBlockRegistry.getDefaultInstance().add((BlockFactoryUpt.callBlock(blockName + "_wood" + suffix)), 5, 5);
            FlammableBlockRegistry.getDefaultInstance().add((BlockFactoryUpt.callBlock("stripped_" + blockName + "_wood" + suffix)), 5, 5);
            FlammableBlockRegistry.getDefaultInstance().add((BlockFactoryUpt.callBlock(blockName + "_leaves" + suffix)), 30, 60);
            

        }

        Block PLANKS = register(blockName + "_planks" + suffix, 
            new Block(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).mapColor((mapColor)).nonOpaque()));

        Block STAIRS = register(blockName + "_stairs" + suffix, 
            new StairsBlock(PLANKS.getDefaultState(), AbstractBlock.Settings.copy(Blocks.OAK_STAIRS).mapColor((mapColor)).nonOpaque())); 

        Block SLAB = register(blockName + "_slab" + suffix, 
            new SlabBlock(AbstractBlock.Settings.copy(Blocks.OAK_SLAB).mapColor((mapColor)).nonOpaque())); 

        Block FENCE = register(blockName + "_fence" + suffix, 
            new FenceBlock(AbstractBlock.Settings.copy(Blocks.OAK_FENCE).mapColor((mapColor)).nonOpaque())); 

        Block FENCE_GATE = register(blockName + "_fence_gate" + suffix, 
            new FenceGateBlock(WoodType.OAK, AbstractBlock.Settings.copy(Blocks.OAK_FENCE_GATE).mapColor((mapColor)).nonOpaque())); 

        Block DOOR = register(blockName + "_door" + suffix, 
            new DoorBlock(BlockSetType.OAK, AbstractBlock.Settings.copy(Blocks.OAK_DOOR).nonOpaque().mapColor((mapColor)).nonOpaque()));

        Block GLASS_DOOR = register(blockName + "_glass_door" + suffix, 
            new DoorBlock(BlockSetType.OAK, AbstractBlock.Settings.copy(Blocks.OAK_DOOR).nonOpaque().mapColor((mapColor)).nonOpaque())); 

        Block TRAP_DOOR = register(blockName + "_trapdoor" + suffix, 
            new TrapdoorBlock(BlockSetType.OAK, AbstractBlock.Settings.copy(Blocks.OAK_TRAPDOOR).mapColor((mapColor)).nonOpaque()));

        Block GLASS_TRAP_DOOR = register(blockName + "_glass_trapdoor" + suffix, 
            new TrapdoorBlock(BlockSetType.OAK, AbstractBlock.Settings.copy(Blocks.OAK_TRAPDOOR).mapColor((mapColor)).nonOpaque()));

        Block P_PLATE = register(blockName + "_pressure_plate" + suffix, 
            new PressurePlateBlock(BlockSetType.OAK, AbstractBlock.Settings.copy(Blocks.OAK_PRESSURE_PLATE).mapColor((mapColor)).nonOpaque())); 

        Block BUTTON = register(blockName + "_button" + suffix, 
            new ButtonBlock(BlockSetType.OAK, 30, AbstractBlock.Settings.copy(Blocks.OAK_BUTTON).mapColor((mapColor)).nonOpaque())); 

        BlockFamilies.register(PLANKS)
            .button(BUTTON)
            .fence(FENCE)
            .fenceGate(FENCE_GATE)
            .pressurePlate(P_PLATE)
            .slab(SLAB)
            .stairs(STAIRS)
            .trapdoor(TRAP_DOOR)
            .door(DOOR).door(GLASS_DOOR)
		.group("wooden")
        .unlockCriterionName("has_planks")
		.build();

        Block MOSAIC  = register(blockName + "_mosaic" + suffix,
            new Block(AbstractBlock.Settings.copy(PLANKS).nonOpaque()));

        Block MOSAIC_STAIRS = register(blockName + "_mosaic_stairs" + suffix, 
            new StairsBlock(PLANKS.getDefaultState(), AbstractBlock.Settings.copy(PLANKS).nonOpaque())); 

        Block MOSAIC_SLAB = register(blockName + "_mosaic_slab" + suffix, 
            new SlabBlock(AbstractBlock.Settings.copy(PLANKS).nonOpaque()));

        FlammableBlockRegistry.getDefaultInstance().add(MOSAIC, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(MOSAIC_STAIRS, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(MOSAIC_SLAB, 5, 20);

        ModGroup.addToDeco(blockName + "_planks" + suffix);
        ModGroup.addToDeco(blockName + "_stairs" + suffix);
        ModGroup.addToDeco(blockName + "_slab" + suffix);
        ModGroup.addToDeco(blockName + "_fence" + suffix);
        ModGroup.addToDeco(blockName + "_fence_gate" + suffix);
        ModGroup.addToDeco(blockName + "_door" + suffix);
        ModGroup.addToDeco(blockName + "_glass_door" + suffix);
        ModGroup.addToDeco(blockName + "_trapdoor" + suffix);
        ModGroup.addToDeco(blockName + "_glass_trapdoor" + suffix);
        ModGroup.addToDeco(blockName + "_pressure_plate" + suffix);
        ModGroup.addToDeco(blockName + "_button" + suffix);

        ModGroup.addToDeco(blockName + "_mosaic" + suffix);
        ModGroup.addToDeco(blockName + "_mosaic_stairs" + suffix);
        ModGroup.addToDeco(blockName + "_mosaic_slab" + suffix);

        FlammableBlockRegistry.getDefaultInstance().add((BlockFactoryUpt.callBlock(blockName + "_planks" + suffix)), 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add((BlockFactoryUpt.callBlock(blockName + "_stairs" + suffix)), 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add((BlockFactoryUpt.callBlock(blockName + "_slab" + suffix)), 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add((BlockFactoryUpt.callBlock(blockName + "_fence" + suffix)), 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add((BlockFactoryUpt.callBlock(blockName + "_fence_gate" + suffix)), 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add((BlockFactoryUpt.callBlock(blockName + "_door" + suffix)), 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add((BlockFactoryUpt.callBlock(blockName + "_glass_door" + suffix)), 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add((BlockFactoryUpt.callBlock(blockName + "_trapdoor" + suffix)), 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add((BlockFactoryUpt.callBlock(blockName + "_glass_trapdoor" + suffix)), 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add((BlockFactoryUpt.callBlock(blockName + "_pressure_plate" + suffix)), 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add((BlockFactoryUpt.callBlock(blockName + "_button" + suffix)), 5, 20);

    }

    public static final Block PHANTOM_TORCH = registerBlock(
        "phantom_torch",
        new TorchBlock(
            ParticleTypes.GLOW,
            AbstractBlock.Settings.create().noCollision().breakInstantly().luminance(state -> 10).sounds(BlockSoundGroup.WOOD).pistonBehavior(PistonBehavior.DESTROY)
        )
    );

    public static final Block PHANTOM_WALL_TORCH = registerBlock(
        "phantom_wall_torch",
        new WallTorchBlock(
            ParticleTypes.GLOW,
            AbstractBlock.Settings.create()
                .noCollision()
                .breakInstantly()
                .luminance(state -> 10)
                .sounds(BlockSoundGroup.WOOD)
                .dropsLike(PHANTOM_TORCH)
            .pistonBehavior(PistonBehavior.DESTROY)
        )
    );

    public static final Block POINTED_ICE = register(
		"pointed_ice",
		new PointedIceBlock(
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
			.solidBlock(Blocks::never)
		)
	);



}
