package net.ent.entstupidstuff.datagen;

import java.util.concurrent.CompletableFuture;

import net.ent.entstupidstuff.block.BlockFactory;
import net.ent.entstupidstuff.block.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.core.HolderLookup;

public class ModLootTableProvider extends FabricBlockLootTableProvider  {

    public ModLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {

        generateWoodType("fungal", null, false);
        for (String color : BlockFactory.COLORS) {generateWoodType("fungal", color, false);}
        for (String color : BlockFactory.COLORS) {dropSelf((BlockFactory.callBlock("textured_wool_" + color)));};
        attachVanillaGlassDoor();

        generateWoodType("redwood", null, true);
        //generateWoodType("desert_iron", null, true);
        generateWoodType("phantom", null, true);
        generateWoodType("maple", null, true);
        generateWoodType("fir", null, true);

        add(BlockFactory.callBlock("maple_leaves"), block -> this.createOakLeavesDrops(block, BlockFactory.callBlock("maple_sapling"), NORMAL_LEAVES_SAPLING_CHANCES));
        add(BlockFactory.callBlock("fir_leaves"), block -> this.createOakLeavesDrops(block, BlockFactory.callBlock("fir_sapling"), NORMAL_LEAVES_SAPLING_CHANCES));

        generateStone("andesite_brick");
        generateStone("diorite_brick");
        generateStone("granite_brick");
        dropSelf((BlockFactory.callBlock("polished_andesite" + "_wall")));
        dropSelf((BlockFactory.callBlock("polished_diorite" + "_wall")));
        dropSelf((BlockFactory.callBlock("polished_granite" + "_wall")));

        dropSelf((BlockFactory.callBlock("iron_grate" + "")));
        dropSelf((BlockFactory.callBlock("iron_grate" + "_slab")));
        dropSelf((BlockFactory.callBlock("iron_grate" + "_stairs")));

        //Minecraft Mobs
        

    }

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
