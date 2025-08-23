package net.ent.entstupidstuff.datagen;

import java.util.concurrent.CompletableFuture;

import net.ent.entstupidstuff.block.BlockFactoryUpt;
import net.ent.entstupidstuff.block.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Blocks;
import net.minecraft.registry.RegistryWrapper;

public class ModLootTableProvider extends FabricBlockLootTableProvider  {

    public ModLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {

        generateWoodType("fungal", null, false);
        for (String color : BlockFactoryUpt.COLORS) {generateWoodType("fungal", color, false);}
        for (String color : BlockFactoryUpt.COLORS) {addDrop((BlockFactoryUpt.callBlock("textured_wool_" + color)));};
        attachVanillaGlassDoor();

        generateWoodType("redwood", null, true);
        generateWoodType("desert_iron", null, true);
        generateWoodType("phantom", null, true);
        generateWoodType("maple", null, true);
        generateWoodType("fir", null, true);

        addDrop(BlockFactoryUpt.callBlock("maple_leaves"), block -> this.oakLeavesDrops(block, BlockFactoryUpt.callBlock("maple_sapling"), SAPLING_DROP_CHANCE));
        addDrop(BlockFactoryUpt.callBlock("fir_leaves"), block -> this.oakLeavesDrops(block, BlockFactoryUpt.callBlock("fir_sapling"), SAPLING_DROP_CHANCE));

        generateStone("andesite_brick");
        generateStone("diorite_brick");
        generateStone("granite_brick");
        addDrop((BlockFactoryUpt.callBlock("polished_andesite" + "_wall")));
        addDrop((BlockFactoryUpt.callBlock("polished_diorite" + "_wall")));
        addDrop((BlockFactoryUpt.callBlock("polished_granite" + "_wall")));

        //Minecraft Mobs
        

    }

    public void attachVanillaGlassDoor(){
        for (String FamilyBase : ModBlocks.V_WOOD_VARIENTS) {
            addDrop((BlockFactoryUpt.callBlock(FamilyBase + "_glass_door")), doorDrops(BlockFactoryUpt.callBlock(FamilyBase + "_glass_door")));
            addDrop((BlockFactoryUpt.callBlock(FamilyBase + "_glass_trapdoor")));
        }

        addDrop((BlockFactoryUpt.callBlock("iron" + "_glass_door")), doorDrops(BlockFactoryUpt.callBlock("iron" + "_glass_door")));

        for (String FamilyBase : ModBlocks.COPPER_VARIENTS) {
            addDrop((BlockFactoryUpt.callBlock(FamilyBase + "_glass_door")), doorDrops(BlockFactoryUpt.callBlock(FamilyBase + "_glass_door")));
            addDrop((BlockFactoryUpt.callBlock(FamilyBase + "_glass_trapdoor")));
        }
    }

    public void generateWoodType(String FamilyBase, String varient, boolean natural){
        if (varient == null) {varient = "";}
        else {varient = "_" + varient;}

        if (natural) {
            addDrop((BlockFactoryUpt.callBlock(FamilyBase + "_log" + varient)));
            addDrop((BlockFactoryUpt.callBlock("stripped_" + FamilyBase + "_log" + varient)));
            addDrop((BlockFactoryUpt.callBlock(FamilyBase + "_wood" + varient)));
            addDrop((BlockFactoryUpt.callBlock("stripped_" + FamilyBase + "_wood" + varient)));
        }

        addDrop((BlockFactoryUpt.callBlock(FamilyBase + "_planks" + varient)));
        addDrop((BlockFactoryUpt.callBlock(FamilyBase + "_trapdoor" + varient)));
        addDrop((BlockFactoryUpt.callBlock(FamilyBase + "_fence" + varient)));
        addDrop((BlockFactoryUpt.callBlock(FamilyBase + "_fence_gate" + varient)));
        addDrop((BlockFactoryUpt.callBlock(FamilyBase + "_pressure_plate" + varient)));
        addDrop((BlockFactoryUpt.callBlock(FamilyBase + "_slab" + varient)), slabDrops(BlockFactoryUpt.callBlock(FamilyBase + "_slab" + varient)));
        addDrop((BlockFactoryUpt.callBlock(FamilyBase + "_stairs" + varient)));
        addDrop((BlockFactoryUpt.callBlock(FamilyBase + "_button" + varient)));
        addDrop((BlockFactoryUpt.callBlock(FamilyBase + "_door" + varient)), doorDrops(BlockFactoryUpt.callBlock(FamilyBase + "_door" + varient)));
        addDrop((BlockFactoryUpt.callBlock(FamilyBase + "_glass_door" + varient)), doorDrops(BlockFactoryUpt.callBlock(FamilyBase + "_glass_door" + varient)));
        addDrop((BlockFactoryUpt.callBlock(FamilyBase + "_glass_trapdoor" + varient)));

    }

    public void generateStone(String FamilyBase) {
        addDrop((BlockFactoryUpt.callBlock(FamilyBase + "_wall")));
        addDrop((BlockFactoryUpt.callBlock(FamilyBase + "_slab")));
        addDrop((BlockFactoryUpt.callBlock(FamilyBase + "_stairs")));
        addDrop((BlockFactoryUpt.callBlock(FamilyBase + "s")));
        addDrop((BlockFactoryUpt.callBlock("cracked_" + FamilyBase + "s")));
        addDrop((BlockFactoryUpt.callBlock(FamilyBase + "_chiseled")));

    }

}
