package net.ent.entstupidstuff.block;

import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.minecraft.client.renderer.chunk.ChunkSectionLayer;


public class ModRenderLayers {


    public static void onInitializeClient() {

        // Fungal Wood
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("fungal" + "_glass_door"), ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("fungal" + "_glass_trapdoor"), ChunkSectionLayer.CUTOUT);

        // Fungal (Colored) Wood
        for (String color : BlockFactory.COLORS) {
            BlockRenderLayerMap.putBlock(BlockFactory.callBlock("fungal" + "_glass_door_" + color), ChunkSectionLayer.CUTOUT);
            BlockRenderLayerMap.putBlock(BlockFactory.callBlock("fungal" + "_glass_trapdoor_" + color), ChunkSectionLayer.CUTOUT);
        }

        // Modded Wood
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("redwood" + "_door"), ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("redwood" + "_trapdoor"), ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("maple" + "_door"), ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("maple" + "_trapdoor"), ChunkSectionLayer.CUTOUT);


        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("redwood" + "_glass_door"), ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("redwood" + "_glass_trapdoor"), ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("maple" + "_glass_door"), ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("maple" + "_glass_trapdoor"), ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("fir" + "_glass_door"), ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("fir" + "_glass_trapdoor"), ChunkSectionLayer.CUTOUT);
        //BlockRenderLayerMap.putBlock(BlockFactoryUpt.callBlock("desert_iron" + "_glass_trapdoor"), BlockRenderLayer.CUTOUT);

        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("potted_" + "maple" + "_sapling"), ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("maple" + "_sapling"), ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("potted_" + "fir" + "_sapling"), ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("fir" + "_sapling"), ChunkSectionLayer.CUTOUT);

        // Phantom Wood
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("phantom" + "_planks"), ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("phantom" + "_stairs"), ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("phantom" + "_slab"), ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("phantom" + "_fence"), ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("phantom" + "_fence_gate"), ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("phantom" + "_door"), ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("phantom" + "_trapdoor"), ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("phantom" + "_pressure_plate"), ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("phantom" + "_button"), ChunkSectionLayer.CUTOUT);

        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("phantom" + "_log"), ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("stripped_" + "phantom" + "_log"), ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("phantom" + "_wood"), ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("stripped_" + "phantom" + "_wood"), ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("phantom" + "_mosaic"), ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("phantom" + "_mosaic_stairs"), ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("phantom" + "_mosaic_slab"), ChunkSectionLayer.CUTOUT);

        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("phantom" + "_glass_door"), ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("phantom" + "_glass_trapdoor"), ChunkSectionLayer.CUTOUT);

        // Phantom Wood
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("phantom" + "_planks"), ChunkSectionLayer.TRANSLUCENT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("phantom" + "_stairs"), ChunkSectionLayer.TRANSLUCENT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("phantom" + "_slab"), ChunkSectionLayer.TRANSLUCENT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("phantom" + "_fence"), ChunkSectionLayer.TRANSLUCENT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("phantom" + "_fence_gate"), ChunkSectionLayer.TRANSLUCENT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("phantom" + "_door"), ChunkSectionLayer.TRANSLUCENT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("phantom" + "_trapdoor"), ChunkSectionLayer.TRANSLUCENT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("phantom" + "_pressure_plate"), ChunkSectionLayer.TRANSLUCENT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("phantom" + "_button"), ChunkSectionLayer.TRANSLUCENT);

        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("phantom" + "_log"), ChunkSectionLayer.TRANSLUCENT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("stripped_" + "phantom" + "_log"), ChunkSectionLayer.TRANSLUCENT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("phantom" + "_wood"), ChunkSectionLayer.TRANSLUCENT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("stripped_" + "phantom" + "_wood"), ChunkSectionLayer.TRANSLUCENT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("phantom" + "_mosaic"), ChunkSectionLayer.TRANSLUCENT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("phantom" + "_mosaic_stairs"), ChunkSectionLayer.TRANSLUCENT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("phantom" + "_mosaic_slab"), ChunkSectionLayer.TRANSLUCENT);

        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("phantom" + "_glass_door"), ChunkSectionLayer.TRANSLUCENT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("phantom" + "_glass_trapdoor"), ChunkSectionLayer.TRANSLUCENT);

        //Vanilla Door and TrapDoors
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("oak" + "_glass_door"), ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("spruce" + "_glass_door"), ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("jungle" + "_glass_door"), ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("birch" + "_glass_door"), ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("dark_oak" + "_glass_door"), ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("acacia" + "_glass_door"), ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("mangrove" + "_glass_door"), ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("cherry" + "_glass_door"), ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("bamboo" + "_glass_door"), ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("warped" + "_glass_door"), ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("crimson" + "_glass_door"), ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("pale_oak" + "_glass_door"), ChunkSectionLayer.CUTOUT);

        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("oak" + "_glass_trapdoor"), ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("spruce" + "_glass_trapdoor"), ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("jungle" + "_glass_trapdoor"), ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("birch" + "_glass_trapdoor"), ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("dark_oak" + "_glass_trapdoor"), ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("acacia" + "_glass_trapdoor"), ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("mangrove" + "_glass_trapdoor"), ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("cherry" + "_glass_trapdoor"), ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("bamboo" + "_glass_trapdoor"), ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("warped" + "_glass_trapdoor"), ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("crimson" + "_glass_trapdoor"), ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("pale_oak" + "_glass_trapdoor"), ChunkSectionLayer.CUTOUT);

        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("iron" + "_glass_door"), ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("copper" + "_glass_door"), ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("exposed_copper" + "_glass_door"), ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("oxidized_copper" + "_glass_door"), ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("weathered_copper" + "_glass_door"), ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("waxed_copper" + "_glass_door"), ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("waxed_exposed_copper" + "_glass_door"), ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("waxed_oxidized_copper" + "_glass_door"), ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("waxed_weathered_copper" + "_glass_door"), ChunkSectionLayer.CUTOUT);

        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("iron" + "_glass_trapdoor"), ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("copper" + "_glass_trapdoor"), ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("exposed_copper" + "_glass_trapdoor"), ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("oxidized_copper" + "_glass_trapdoor"), ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("weathered_copper" + "_glass_trapdoor"), ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("waxed_copper" + "_glass_trapdoor"), ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("waxed_exposed_copper" + "_glass_trapdoor"), ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("waxed_oxidized_copper" + "_glass_trapdoor"), ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("waxed_weathered_copper" + "_glass_trapdoor"), ChunkSectionLayer.CUTOUT);

        //String Blocks
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("string_block"), ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("string_gate"), ChunkSectionLayer.CUTOUT_MIPPED);

        //Iron Gates
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("iron_grate"), ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("iron_grate_slab"), ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("iron_grate_stairs"), ChunkSectionLayer.CUTOUT);

        //Torch
        //BlockRenderLayerMap.putBlock(BlockFactory.callBlock("phantom_torch"), BlockRenderLayer.CUTOUT);
        //BlockRenderLayerMap.putBlock(BlockFactory.callBlock("phantom_wall_torch"), BlockRenderLayer.CUTOUT);
        //BlockRenderLayerMap.putBlock(BlockFactory.callBlock("phantom_lantern"), BlockRenderLayer.CUTOUT);

        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("pointed_ice"), ChunkSectionLayer.CUTOUT);

        

        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("blue_mushroom_block"), ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("blue_mushroom_block"), ChunkSectionLayer.TRANSLUCENT);

        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("blue_crystal_block"), ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("blue_crystal_block"), ChunkSectionLayer.TRANSLUCENT);

        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("blue_mushroom"), ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("azure_flower_bed"), ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("potted_blue_mushroom"), ChunkSectionLayer.CUTOUT);

        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("fungal_spore_blossom"), ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("fungal_spore_blossom"), ChunkSectionLayer.TRANSLUCENT);

        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("mushroom_aura_block"), ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("mushroom_aura_block"), ChunkSectionLayer.TRANSLUCENT);

        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("mushroom_aura_block_2"), ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("mushroom_aura_block_2"), ChunkSectionLayer.TRANSLUCENT);

        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("silkworm_vines"), ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("silkworm_vines"), ChunkSectionLayer.TRANSLUCENT);

        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("silkworm_vines_plant"), ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("silkworm_vines_plant"), ChunkSectionLayer.TRANSLUCENT);

        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("orange_petals"), ChunkSectionLayer.CUTOUT);

        //Change underground_mushroom to Azure_grove







        /*

        BlockRenderLayerMap.putBlock(ModBlocks.MOD_DOOR("fungal", null), BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.MOD_DOOR("fungal_glass", null), BlockRenderLayer.CUTOUT);

        for (String color : BlockFactoryUpt.COLORS) {
            BlockRenderLayerMap.putBlock(BlockFactoryUpt.callBlock("fungal" + "_door_" + color), BlockRenderLayer.CUTOUT);
            BlockRenderLayerMap.putBlock(BlockFactoryUpt.callBlock("fungal_glass" + "_door_" + color), BlockRenderLayer.CUTOUT);
            BlockRenderLayerMap.putBlock(BlockFactoryUpt.callBlock("fungal_glass" + "_trapdoor_" + color), BlockRenderLayer.CUTOUT);
        }

        for (String base : ModBlocks.V_WOOD_VARIENTS) {
            BlockRenderLayerMap.putBlock(ModBlocks.MOD_DOOR(base + "_glass_door", null), BlockRenderLayer.CUTOUT);
        }

        BlockRenderLayerMap.putBlock(ModBlocks.MOD_DOOR("iron" + "_glass_door", null), BlockRenderLayer.CUTOUT);

        for (String base : ModBlocks.COPPER_VARIENTS) {
            BlockRenderLayerMap.putBlock(ModBlocks.MOD_DOOR(base + "_glass_door", null), BlockRenderLayer.CUTOUT);
        }

        //BlockRenderLayerMap.putBlock(BlockFactoryUpt.callBlock("string_gate"), BlockRenderLayer.TRANSLUCENT);
        //BlockRenderLayerMap.putBlock(BlockFactoryUpt.callBlock("string_pane"), BlockRenderLayer.TRANSLUCENT);
        //BlockRenderLayerMap.putBlock(BlockFactoryUpt.callBlock("string_gate"), BlockRenderLayer.CUTOUT);

        BlockRenderLayerMap.putBlock(BlockFactoryUpt.callBlock("string_block"), BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BlockFactoryUpt.callBlock("string_gate"), BlockRenderLayer.CUTOUT);

        BlockRenderLayerMap.putBlock(BlockFactoryUpt.callBlock("redwood_door"), BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BlockFactoryUpt.callBlock("redwood_glass_door"), BlockRenderLayer.CUTOUT);

        BlockRenderLayerMap.putBlock(BlockFactoryUpt.callBlock("desert_iron_glass_door"), BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BlockFactoryUpt.callBlock("desert_iron_door"), BlockRenderLayer.CUTOUT);

        //BlockRenderLayerMap.putBlock(ModBlocks.MOD_DOOR("oak_glass", null), BlockRenderLayer.CUTOUT);
        //BlockRenderLayerMap.putBlock(ModBlocks.MOD_DOOR("spruce_glass", null), BlockRenderLayer.CUTOUT);
        //BlockRenderLayerMap.putBlock(ModBlocks.MOD_DOOR("birch_glass", null), BlockRenderLayer.CUTOUT);
       // BlockRenderLayerMap.putBlock(ModBlocks.MOD_DOOR("jungle_glass", null), BlockRenderLayer.CUTOUT);
        //BlockRenderLayerMap.putBlock(ModBlocks.MOD_DOOR("acacia_glass", null), BlockRenderLayer.CUTOUT);
        //BlockRenderLayerMap.putBlock(ModBlocks.MOD_DOOR("dark_oak_glass", null), BlockRenderLayer.CUTOUT);
        //BlockRenderLayerMap.putBlock(ModBlocks.MOD_DOOR("mangrove_glass", null), BlockRenderLayer.CUTOUT);
        //BlockRenderLayerMap.putBlock(ModBlocks.MOD_DOOR("cherry_glass", null), BlockRenderLayer.CUTOUT);
        //BlockRenderLayerMap.putBlock(ModBlocks.MOD_DOOR("crimson_glass", null), BlockRenderLayer.CUTOUT);
        //BlockRenderLayerMap.putBlock(ModBlocks.MOD_DOOR("warped_glass", null), BlockRenderLayer.CUTOUT);

        /*BlockRenderLayerMap.putBlock(ModBlocks.MOD_TRAPDOOR("oak_glass", null), BlockRenderLayer.CUTOUT);*/
        /*BlockRenderLayerMap.putBlock(ModBlocks.MOD_TRAPDOOR("spruce_glass", null), BlockRenderLayer.CUTOUT);*/
        /*BlockRenderLayerMap.putBlock(ModBlocks.MOD_TRAPDOOR("birch_glass", null), BlockRenderLayer.CUTOUT);*/
        /*BlockRenderLayerMap.putBlock(ModBlocks.MOD_TRAPDOOR("jungle_glass", null), BlockRenderLayer.CUTOUT);*/
        /*BlockRenderLayerMap.putBlock(ModBlocks.MOD_TRAPDOOR("acacia_glass", null), BlockRenderLayer.CUTOUT);*/
        /*BlockRenderLayerMap.putBlock(ModBlocks.MOD_TRAPDOOR("dark_oak_glass", null), BlockRenderLayer.CUTOUT);*/
        /*BlockRenderLayerMap.putBlock(ModBlocks.MOD_TRAPDOOR("mangrove_glass", null), BlockRenderLayer.CUTOUT);*/
        /*BlockRenderLayerMap.putBlock(ModBlocks.MOD_TRAPDOOR("cherry_glass", null), BlockRenderLayer.CUTOUT);*/
        /*BlockRenderLayerMap.putBlock(ModBlocks.MOD_TRAPDOOR("crimson_glass", null), BlockRenderLayer.CUTOUT);*/
        /*BlockRenderLayerMap.putBlock(ModBlocks.MOD_TRAPDOOR("warped_glass", null), BlockRenderLayer.CUTOUT);*/
    }
}
