package net.ent.entstupidstuff.datagen;

import java.util.concurrent.CompletableFuture;

import net.ent.entstupidstuff.block.BlockFactory;
import net.ent.entstupidstuff.block.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.registry.RegistryWrapper;

public class ModLootTableProvider extends FabricBlockLootTableProvider  {

    public ModLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {

        generateWoodType("fungal", null, false);
        for (String color : BlockFactory.COLORS) {generateWoodType("fungal", color, false);}
        for (String color : BlockFactory.COLORS) {addDrop((BlockFactory.callBlock("textured_wool_" + color)));};
        attachVanillaGlassDoor();

        generateWoodType("redwood", null, true);
        //generateWoodType("desert_iron", null, true);
        generateWoodType("phantom", null, true);
        generateWoodType("maple", null, true);
        generateWoodType("fir", null, true);

        addDrop(BlockFactory.callBlock("maple_leaves"), block -> this.oakLeavesDrops(block, BlockFactory.callBlock("maple_sapling"), SAPLING_DROP_CHANCE));
        addDrop(BlockFactory.callBlock("fir_leaves"), block -> this.oakLeavesDrops(block, BlockFactory.callBlock("fir_sapling"), SAPLING_DROP_CHANCE));

        generateStone("andesite_brick");
        generateStone("diorite_brick");
        generateStone("granite_brick");
        addDrop((BlockFactory.callBlock("polished_andesite" + "_wall")));
        addDrop((BlockFactory.callBlock("polished_diorite" + "_wall")));
        addDrop((BlockFactory.callBlock("polished_granite" + "_wall")));

        addDrop((BlockFactory.callBlock("iron_grate" + "")));
        addDrop((BlockFactory.callBlock("iron_grate" + "_slab")));
        addDrop((BlockFactory.callBlock("iron_grate" + "_stairs")));

        //Minecraft Mobs
        

    }

    public void attachVanillaGlassDoor(){
        for (String FamilyBase : ModBlocks.V_WOOD_VARIENTS) {
            addDrop((BlockFactory.callBlock(FamilyBase + "_glass_door")), doorDrops(BlockFactory.callBlock(FamilyBase + "_glass_door")));
            addDrop((BlockFactory.callBlock(FamilyBase + "_glass_trapdoor")));
        }

        addDrop((BlockFactory.callBlock("iron" + "_glass_door")), doorDrops(BlockFactory.callBlock("iron" + "_glass_door")));

        for (String FamilyBase : ModBlocks.COPPER_VARIENTS) {
            addDrop((BlockFactory.callBlock(FamilyBase + "_glass_door")), doorDrops(BlockFactory.callBlock(FamilyBase + "_glass_door")));
            addDrop((BlockFactory.callBlock(FamilyBase + "_glass_trapdoor")));
        }
    }

    public void generateWoodType(String FamilyBase, String varient, boolean natural){
        if (varient == null) {varient = "";}
        else {varient = "_" + varient;}

        if (natural) {
            addDrop((BlockFactory.callBlock(FamilyBase + "_log" + varient)));
            addDrop((BlockFactory.callBlock("stripped_" + FamilyBase + "_log" + varient)));
            addDrop((BlockFactory.callBlock(FamilyBase + "_wood" + varient)));
            addDrop((BlockFactory.callBlock("stripped_" + FamilyBase + "_wood" + varient)));
        }

        addDrop((BlockFactory.callBlock(FamilyBase + "_planks" + varient)));
        addDrop((BlockFactory.callBlock(FamilyBase + "_trapdoor" + varient)));
        addDrop((BlockFactory.callBlock(FamilyBase + "_fence" + varient)));
        addDrop((BlockFactory.callBlock(FamilyBase + "_fence_gate" + varient)));
        addDrop((BlockFactory.callBlock(FamilyBase + "_pressure_plate" + varient)));
        addDrop((BlockFactory.callBlock(FamilyBase + "_slab" + varient)), slabDrops(BlockFactory.callBlock(FamilyBase + "_slab" + varient)));
        addDrop((BlockFactory.callBlock(FamilyBase + "_stairs" + varient)));
        addDrop((BlockFactory.callBlock(FamilyBase + "_button" + varient)));
        addDrop((BlockFactory.callBlock(FamilyBase + "_door" + varient)), doorDrops(BlockFactory.callBlock(FamilyBase + "_door" + varient)));
        addDrop((BlockFactory.callBlock(FamilyBase + "_glass_door" + varient)), doorDrops(BlockFactory.callBlock(FamilyBase + "_glass_door" + varient)));
        addDrop((BlockFactory.callBlock(FamilyBase + "_glass_trapdoor" + varient)));

    }

    public void generateStone(String FamilyBase) {
        addDrop((BlockFactory.callBlock(FamilyBase + "_wall")));
        addDrop((BlockFactory.callBlock(FamilyBase + "_slab")));
        addDrop((BlockFactory.callBlock(FamilyBase + "_stairs")));
        addDrop((BlockFactory.callBlock(FamilyBase + "s")));
        addDrop((BlockFactory.callBlock("cracked_" + FamilyBase + "s")));
        addDrop((BlockFactory.callBlock(FamilyBase + "_chiseled")));

    }

}
