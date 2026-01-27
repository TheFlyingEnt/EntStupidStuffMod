package net.ent.entstupidstuff.datagen;

import java.util.concurrent.CompletableFuture;

import net.ent.entstupidstuff.block.BlockFactory;
import net.ent.entstupidstuff.block.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.LootTable;

public class ModLootTableProvider extends FabricBlockLootTableProvider  {

    protected static final float[] NORMAL_LEAVES_SAPLING_CHANCES = new float[]{0.05F, 0.0625F, 0.083333336F, 0.1F};

    public ModLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {

        //Updated
        dropSelf((BlockFactory.callBlock("redwood" + "_planks")));
        groupWoodFamilty("redwood", "", true);

        dropSelf((BlockFactory.callBlock("fir" + "_planks")));
        groupWoodFamilty("fir", "", true);
        
        dropSelf((BlockFactory.callBlock("maple" + "_planks")));
        groupWoodFamilty("maple", "", true);
        this.add(BlockFactory.callBlock("orange_petals"), this.createSegmentedBlockDrops(BlockFactory.callBlock("orange_petals")));
       
        dropSelf((BlockFactory.callBlock("phantom" + "_planks")));
        groupWoodFamilty("phantom", "", false);
        dropSelf((BlockFactory.callBlock("phantom_lantern")));
        
        dropSelf((BlockFactory.callBlock("fungal" + "_planks")));
        BaseFamily("fungal", "");
        InteractionFamily("fungal", "");
        MosicFamily("fungal", "");

        for (String color : BlockFactory.COLORS) {
            dropSelf((BlockFactory.callBlock("fungal" + "_planks" + "_" + color)));
            BaseFamily("fungal", "_" + color);
            InteractionFamily("fungal", "_" + color);
            MosicFamily("fungal", "_" + color);
        }

        this.dropSelf(BlockFactory.callBlock("blue_mushroom"));
        this.dropSelf(BlockFactory.callBlock("potted_blue_mushroom"));

        this.add(BlockFactory.callBlock("blue_mushroom_block"), this.createMushroomBlockDrop(BlockFactory.callBlock("blue_mushroom_block"), BlockFactory.callBlock("blue_mushroom")));
        this.add(BlockFactory.callBlock("shroomium"), this.createSingleItemTableWithSilkTouch(BlockFactory.callBlock("shroomium"), Blocks.MUD));

        dropSelf((BlockFactory.callBlock("crystal_block")));
        this.add(BlockFactory.callBlock("mushroom_bed"), this.createSegmentedBlockDrops(BlockFactory.callBlock("mushroom_bed")));
        dropSelf((BlockFactory.callBlock("fungal_spore_blossom")));
        dropSelf(BlockFactory.MUSHROOM_AURA_BLOCK);

        // # Adding Andersite, Diorite and Granite
        dropSelf((BlockFactory.callBlock("andesite_bricks")));
        groupStoneFamily("andesite_brick", "", true);
        dropSelf((BlockFactory.callBlock("polished_andesite_wall")));

        dropSelf((BlockFactory.callBlock("granite_bricks")));
        groupStoneFamily("granite_brick", "", true);
        dropSelf((BlockFactory.callBlock("polished_granite_wall")));

        dropSelf((BlockFactory.callBlock("diorite_bricks")));
        groupStoneFamily("diorite_brick", "", true);
        dropSelf((BlockFactory.callBlock("polished_diorite_wall")));

        // # Adding Limestone and Limestone Bricks
        dropSelf((BlockFactory.callBlock("limestone")));
        groupStoneFamily("limestone", "", false);

        dropSelf((BlockFactory.callBlock("polished_limestone")));
        groupStoneFamily("polished_limestone", "", false);

        dropSelf((BlockFactory.callBlock("polished_limestone_bricks")));
        groupStoneFamily("polished_limestone_brick", "", true);

        // # Adding IronGates
        dropSelf((BlockFactory.callBlock("iron_grate")));
        dropSelf((BlockFactory.callBlock("iron_grate")));
        add((BlockFactory.callBlock("iron_grate" + "_slab")), createSlabItemTable(BlockFactory.callBlock("iron_grate" + "_slab")));



        // # Vanilla Additions 
        add((BlockFactory.callBlock("iron" + "_glass_door")), createDoorTable(BlockFactory.callBlock("iron" + "_glass_door")));
        dropSelf(BlockFactory.callBlock("iron" + "_glass_trapdoor"));

        add((BlockFactory.callBlock("copper" + "_glass_door")), createDoorTable(BlockFactory.callBlock("copper" + "_glass_door")));
        dropSelf(BlockFactory.callBlock("copper" + "_glass_trapdoor"));
        add((BlockFactory.callBlock("exposed_copper" + "_glass_door")), createDoorTable(BlockFactory.callBlock("exposed_copper" + "_glass_door")));
        dropSelf(BlockFactory.callBlock("exposed_copper" + "_glass_trapdoor"));
        add((BlockFactory.callBlock("oxidized_copper" + "_glass_door")), createDoorTable(BlockFactory.callBlock("oxidized_copper" + "_glass_door")));
        dropSelf(BlockFactory.callBlock("oxidized_copper" + "_glass_trapdoor"));
        add((BlockFactory.callBlock("weathered_copper" + "_glass_door")), createDoorTable(BlockFactory.callBlock("weathered_copper" + "_glass_door")));
        dropSelf(BlockFactory.callBlock("weathered_copper" + "_glass_trapdoor"));

        add((BlockFactory.callBlock("waxed_copper" + "_glass_door")), createDoorTable(BlockFactory.callBlock("waxed_copper" + "_glass_door")));
        dropSelf(BlockFactory.callBlock("waxed_copper" + "_glass_trapdoor"));
        add((BlockFactory.callBlock("waxed_exposed_copper" + "_glass_door")), createDoorTable(BlockFactory.callBlock("waxed_exposed_copper" + "_glass_door")));
        dropSelf(BlockFactory.callBlock("waxed_exposed_copper" + "_glass_trapdoor"));
        add((BlockFactory.callBlock("waxed_oxidized_copper" + "_glass_door")), createDoorTable(BlockFactory.callBlock("waxed_oxidized_copper" + "_glass_door")));
        dropSelf(BlockFactory.callBlock("waxed_oxidized_copper" + "_glass_trapdoor"));
        add((BlockFactory.callBlock("waxed_weathered_copper" + "_glass_door")), createDoorTable(BlockFactory.callBlock("waxed_weathered_copper" + "_glass_door")));
        dropSelf(BlockFactory.callBlock("waxed_weathered_copper" + "_glass_trapdoor"));

        add((BlockFactory.callBlock("oak" + "_glass_door")), createDoorTable(BlockFactory.callBlock("oak" + "_glass_door")));
        dropSelf(BlockFactory.callBlock("oak" + "_glass_trapdoor"));
        add((BlockFactory.callBlock("spruce" + "_glass_door")), createDoorTable(BlockFactory.callBlock("spruce" + "_glass_door")));
        dropSelf(BlockFactory.callBlock("spruce" + "_glass_trapdoor"));
        add((BlockFactory.callBlock("jungle" + "_glass_door")), createDoorTable(BlockFactory.callBlock("jungle" + "_glass_door")));
        dropSelf(BlockFactory.callBlock("jungle" + "_glass_trapdoor"));
        add((BlockFactory.callBlock("birch" + "_glass_door")), createDoorTable(BlockFactory.callBlock("birch" + "_glass_door")));
        dropSelf(BlockFactory.callBlock("birch" + "_glass_trapdoor"));
        add((BlockFactory.callBlock("dark_oak" + "_glass_door")), createDoorTable(BlockFactory.callBlock("dark_oak" + "_glass_door")));
        dropSelf(BlockFactory.callBlock("dark_oak" + "_glass_trapdoor"));
        add((BlockFactory.callBlock("acacia" + "_glass_door")), createDoorTable(BlockFactory.callBlock("acacia" + "_glass_door")));
        dropSelf(BlockFactory.callBlock("acacia" + "_glass_trapdoor"));
        add((BlockFactory.callBlock("mangrove" + "_glass_door")), createDoorTable(BlockFactory.callBlock("mangrove" + "_glass_door")));
        dropSelf(BlockFactory.callBlock("mangrove" + "_glass_trapdoor"));
        add((BlockFactory.callBlock("cherry" + "_glass_door")), createDoorTable(BlockFactory.callBlock("cherry" + "_glass_door")));
        dropSelf(BlockFactory.callBlock("cherry" + "_glass_trapdoor"));
        add((BlockFactory.callBlock("bamboo" + "_glass_door")), createDoorTable(BlockFactory.callBlock("bamboo" + "_glass_door")));
        dropSelf(BlockFactory.callBlock("bamboo" + "_glass_trapdoor"));
        add((BlockFactory.callBlock("pale_oak" + "_glass_door")), createDoorTable(BlockFactory.callBlock("pale_oak" + "_glass_door")));
        dropSelf(BlockFactory.callBlock("pale_oak" + "_glass_trapdoor"));

        add((BlockFactory.callBlock("crimson" + "_glass_door")), createDoorTable(BlockFactory.callBlock("crimson" + "_glass_door")));
        dropSelf(BlockFactory.callBlock("crimson" + "_glass_trapdoor"));
        add((BlockFactory.callBlock("warped" + "_glass_door")), createDoorTable(BlockFactory.callBlock("warped" + "_glass_door")));
        dropSelf(BlockFactory.callBlock("warped" + "_glass_trapdoor"));

        MosicFamily("oak", "");
        MosicFamily("spruce", "");
        MosicFamily("jungle", "");
        MosicFamily("birch", "");
        MosicFamily("dark_oak", "");
        MosicFamily("acacia", "");
        MosicFamily("mangrove", "");
        MosicFamily("cherry", "");
        MosicFamily("pale_oak", "");

        MosicFamily("crimson", "");
        MosicFamily("warped", "");

        this.dropSelf(BlockFactory.PHANTOM_TORCH);
        this.dropSelf(BlockFactory.POINTED_ICE);

        //Minecraft Mobs
        

    }

    //New
    public void groupWoodFamilty(String MainName, String Variant, Boolean withLeaves) {
        BaseFamily(MainName, Variant);
        InteractionFamily(MainName, Variant);
        NatureFamily(MainName, Variant, withLeaves);
        MosicFamily(MainName, Variant);
    }

    public void BaseFamily(String MainName, String Variant) {
        dropSelf(BlockFactory.callBlock(MainName + "_stairs" + Variant));
        add((BlockFactory.callBlock(MainName + "_slab" + Variant)), createSlabItemTable(BlockFactory.callBlock(MainName + "_slab" + Variant)));
    }

    public void InteractionFamily(String MainName, String Variant) {
        dropSelf(BlockFactory.callBlock(MainName + "_fence" + Variant));
        dropSelf(BlockFactory.callBlock(MainName + "_fence_gate" + Variant));
        add((BlockFactory.callBlock(MainName + "_door" + Variant)), createDoorTable(BlockFactory.callBlock(MainName + "_door" + Variant)));
        add((BlockFactory.callBlock(MainName + "_glass_door" + Variant)), createDoorTable(BlockFactory.callBlock(MainName + "_glass_door" + Variant)));
        dropSelf((BlockFactory.callBlock(MainName + "_trapdoor" + Variant)));
        dropSelf((BlockFactory.callBlock(MainName + "_glass_trapdoor" + Variant)));
        dropSelf((BlockFactory.callBlock(MainName + "_pressure_plate" + Variant)));
        dropSelf((BlockFactory.callBlock(MainName + "_button" + Variant)));
    }

    public void NatureFamily(String MainName, String Variant, Boolean withLeaves) {
        dropSelf((BlockFactory.callBlock(MainName + "_log" + Variant)));
        dropSelf((BlockFactory.callBlock("stripped_" + MainName + "_log" + Variant)));
        dropSelf((BlockFactory.callBlock(MainName + "_wood" + Variant)));
        dropSelf((BlockFactory.callBlock("stripped_" + MainName + "_wood" + Variant)));

        if (withLeaves) {
            this.add(BlockFactory.callBlock(MainName + "_leaves" + Variant), block -> this.createLeavesDrops(block, BlockFactory.callBlock(MainName + "_sapling" + Variant), NORMAL_LEAVES_SAPLING_CHANCES));
            //createLeavesDrops(BlockFactory.callBlock(MainName + "_leaves" + Variant), BlockFactory.callBlock(MainName + "_sapling" + Variant), NORMAL_LEAVES_SAPLING_CHANCES); //Leaves, Sapplings, NORMAL_LEAVES_SAPLING_CHANCES
            dropSelf((BlockFactory.callBlock(MainName + "_sapling" + Variant)));
            dropSelf((BlockFactory.callBlock("potted_" + MainName + "_sapling" + Variant)));
        }
        
    }

    public void MosicFamily(String MainName, String Variant) {
        dropSelf(BlockFactory.callBlock(MainName + "_mosaic" + Variant));
        dropSelf(BlockFactory.callBlock(MainName + "_mosaic_stairs" + Variant));
        add((BlockFactory.callBlock(MainName + "_mosaic_slab" + Variant)), createSlabItemTable(BlockFactory.callBlock(MainName + "_mosaic_slab" + Variant)));
    }

    public void groupStoneFamily(String MainName, String Variant, Boolean Bricks) {
        BaseFamily(MainName, Variant);
        dropSelf(BlockFactory.callBlock(MainName + "_wall" + Variant));

        if (Bricks) {
            dropSelf(BlockFactory.callBlock(MainName + "_chiseled" + Variant));
            dropSelf(BlockFactory.callBlock("cracked_" + MainName + "s" + Variant));
        }
        
    }

    @Override
    public void dropSelf(Block block) {
        System.out.println("Adding Lootable " + block);
        super.dropSelf(block);
	}

    @Override
    public void add(Block block, LootTable.Builder builder) {
        System.out.println("Adding Lootable " + block);
        super.add(block, builder);
    }



    //Legacy

    public void attachVanillaGlassDoor(){
        for (String FamilyBase : ModBlocks.V_WOOD_VARIENTS) {
            add((BlockFactory.callBlock(FamilyBase + "_glass_door")), createDoorTable(BlockFactory.callBlock(FamilyBase + "_glass_door")));
            dropSelf((BlockFactory.callBlock(FamilyBase + "_glass_trapdoor")));
        }

        add((BlockFactory.callBlock("iron" + "_glass_door")), createDoorTable(BlockFactory.callBlock("iron" + "_glass_door")));

        for (String FamilyBase : ModBlocks.COPPER_VARIENTS) {
            add((BlockFactory.callBlock(FamilyBase + "_glass_door")), createDoorTable(BlockFactory.callBlock(FamilyBase + "_glass_door")));
            dropSelf((BlockFactory.callBlock(FamilyBase + "_glass_trapdoor")));
        }
    }

    public void generateWoodType(String FamilyBase, String varient, boolean natural){
        if (varient == null) {varient = "";}
        else {varient = "_" + varient;}

        if (natural) {
            dropSelf((BlockFactory.callBlock(FamilyBase + "_log" + varient)));
            dropSelf((BlockFactory.callBlock("stripped_" + FamilyBase + "_log" + varient)));
            dropSelf((BlockFactory.callBlock(FamilyBase + "_wood" + varient)));
            dropSelf((BlockFactory.callBlock("stripped_" + FamilyBase + "_wood" + varient)));
        }

        dropSelf((BlockFactory.callBlock(FamilyBase + "_planks" + varient)));
        dropSelf((BlockFactory.callBlock(FamilyBase + "_trapdoor" + varient)));
        dropSelf((BlockFactory.callBlock(FamilyBase + "_fence" + varient)));
        dropSelf((BlockFactory.callBlock(FamilyBase + "_fence_gate" + varient)));
        dropSelf((BlockFactory.callBlock(FamilyBase + "_pressure_plate" + varient)));
        add((BlockFactory.callBlock(FamilyBase + "_slab" + varient)), createSlabItemTable(BlockFactory.callBlock(FamilyBase + "_slab" + varient)));
        dropSelf((BlockFactory.callBlock(FamilyBase + "_stairs" + varient)));
        dropSelf((BlockFactory.callBlock(FamilyBase + "_button" + varient)));
        add((BlockFactory.callBlock(FamilyBase + "_door" + varient)), createDoorTable(BlockFactory.callBlock(FamilyBase + "_door" + varient)));
        add((BlockFactory.callBlock(FamilyBase + "_glass_door" + varient)), createDoorTable(BlockFactory.callBlock(FamilyBase + "_glass_door" + varient)));
        dropSelf((BlockFactory.callBlock(FamilyBase + "_glass_trapdoor" + varient)));

    }

    public void generateStone(String FamilyBase) {
        dropSelf((BlockFactory.callBlock(FamilyBase + "_wall")));
        dropSelf((BlockFactory.callBlock(FamilyBase + "_slab")));
        dropSelf((BlockFactory.callBlock(FamilyBase + "_stairs")));
        dropSelf((BlockFactory.callBlock(FamilyBase + "s")));
        dropSelf((BlockFactory.callBlock("cracked_" + FamilyBase + "s")));
        dropSelf((BlockFactory.callBlock(FamilyBase + "_chiseled")));

    }

}
