package net.ent.entstupidstuff.datagen;

import net.ent.entstupidstuff.block.BlockFactory;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.BlockModelGenerators.BlockFamilyProvider;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.world.level.block.Block;

public class ModModelProvider extends FabricModelProvider{

    BlockModelGenerators blockStateModelGenerator;

    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockStateModelGenerator) {
        this.blockStateModelGenerator = blockStateModelGenerator;

        generateGroupFungalFamilty("fungal", "");
    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerator) {
;
    }

    //Helper Methods:

    public void generateGroupFungalFamilty(String blockName, String suffix) {
        if (suffix == null) {suffix = "";}
        else if (suffix == "") {suffix = "";}
        else if (suffix == "_") {suffix = "";}
        else {suffix = "_" + suffix;}

        

        Block MainTexture = BlockFactory.callBlock(blockName + "_planks" + suffix);
        Block MosicTexture = BlockFactory.callBlock(blockName + "_mosaic" + suffix);
        generateBaseAndIntercationFamily(blockName, suffix, true, false, MainTexture);
        generateBaseAndIntercationFamily(blockName + "_mosaic", suffix, false, false, MosicTexture);

    }

    public void generateBaseAndIntercationFamily(String blockName, String suffix, Boolean generateWoodBase, Boolean generateStoneBase, Block MainTexture) {

        if (suffix == null) {suffix = "";}
        else if (suffix == "") {suffix = "";}
        else if (suffix == "_") {suffix = "";}

        BlockFamilyProvider blockPool = blockStateModelGenerator.family(MainTexture);

        blockPool
            .stairs(BlockFactory.callBlock(blockName + "_stairs" + suffix))
        .slab(BlockFactory.callBlock(blockName + "_slab" + suffix));

        if (generateWoodBase) {
            blockPool
                .button(BlockFactory.callBlock(blockName + "_button" + suffix))
                .pressurePlate(BlockFactory.callBlock(blockName + "_pressure_plate" + suffix))
                .fence(BlockFactory.callBlock(blockName + "_fence" + suffix))
            .fenceGate(BlockFactory.callBlock(blockName + "_fence_gate" + suffix));

            blockStateModelGenerator.createTrapdoor(BlockFactory.callBlock(blockName + "_trapdoor" + suffix));
            blockStateModelGenerator.createDoor(BlockFactory.callBlock(blockName + "_door" + suffix));
            generateGlassIntercation(blockName, suffix);
        }
        else if (generateStoneBase) {
            blockPool
                .wall(BlockFactory.callBlock(blockName + "_wall"));
        }


    }

    public void generateGlassIntercation(String blockName, String suffix) {
        blockStateModelGenerator.createTrapdoor(BlockFactory.callBlock(blockName + "_glass_trapdoor" + suffix));
        blockStateModelGenerator.createDoor(BlockFactory.callBlock(blockName + "_glass_door" + suffix));
    }
    
}
