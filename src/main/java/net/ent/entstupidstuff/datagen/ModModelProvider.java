package net.ent.entstupidstuff.datagen;

import net.ent.entstupidstuff.block.BlockFactory;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.block.Block;
import net.minecraft.client.data.BlockStateModelGenerator;
import net.minecraft.client.data.BlockStateModelGenerator.BlockTexturePool;
import net.minecraft.client.data.ItemModelGenerator;

public class ModModelProvider extends FabricModelProvider{

    BlockStateModelGenerator blockStateModelGenerator;

    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        this.blockStateModelGenerator = blockStateModelGenerator;

        generateGroupFungalFamilty("fungal", "");
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
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

        BlockTexturePool blockPool = blockStateModelGenerator.registerCubeAllModelTexturePool(MainTexture);

        blockPool
            .stairs(BlockFactory.callBlock(blockName + "_stairs" + suffix))
        .slab(BlockFactory.callBlock(blockName + "_slab" + suffix));

        if (generateWoodBase) {
            blockPool
                .button(BlockFactory.callBlock(blockName + "_button" + suffix))
                .pressurePlate(BlockFactory.callBlock(blockName + "_pressure_plate" + suffix))
                .fence(BlockFactory.callBlock(blockName + "_fence" + suffix))
            .fenceGate(BlockFactory.callBlock(blockName + "_fence_gate" + suffix));

            blockStateModelGenerator.registerTrapdoor(BlockFactory.callBlock(blockName + "_trapdoor" + suffix));
            blockStateModelGenerator.registerDoor(BlockFactory.callBlock(blockName + "_door" + suffix));
            generateGlassIntercation(blockName, suffix);
        }
        else if (generateStoneBase) {
            blockPool
                .wall(BlockFactory.callBlock(blockName + "_wall"));
        }


    }

    public void generateGlassIntercation(String blockName, String suffix) {
        blockStateModelGenerator.registerTrapdoor(BlockFactory.callBlock(blockName + "_glass_trapdoor" + suffix));
        blockStateModelGenerator.registerDoor(BlockFactory.callBlock(blockName + "_glass_door" + suffix));
    }
    
}
