package net.ent.entstupidstuff.block;

import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.minecraft.client.render.BlockRenderLayer;


public class ModRenderLayers {


    public static void onInitializeClient() {

        // Fungal Wood
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("fungal" + "_glass_door"), BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("fungal" + "_glass_trapdoor"), BlockRenderLayer.CUTOUT);

        // Fungal (Colored) Wood
        for (String color : BlockFactory.COLORS) {
            BlockRenderLayerMap.putBlock(BlockFactory.callBlock("fungal" + "_glass_door_" + color), BlockRenderLayer.CUTOUT);
            BlockRenderLayerMap.putBlock(BlockFactory.callBlock("fungal" + "_glass_trapdoor_" + color), BlockRenderLayer.CUTOUT);
        }

        // Modded Wood
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("redwood" + "_door"), BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("redwood" + "_trapdoor"), BlockRenderLayer.CUTOUT);


        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("redwood" + "_glass_door"), BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("redwood" + "_glass_trapdoor"), BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("maple" + "_glass_door"), BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("maple" + "_glass_trapdoor"), BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("fir" + "_glass_door"), BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("fir" + "_glass_trapdoor"), BlockRenderLayer.CUTOUT);
        //BlockRenderLayerMap.putBlock(BlockFactoryUpt.callBlock("desert_iron" + "_glass_trapdoor"), BlockRenderLayer.CUTOUT);

        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("potted_" + "maple" + "_sapling"), BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("maple" + "_sapling"), BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("potted_" + "fir" + "_sapling"), BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("fir" + "_sapling"), BlockRenderLayer.CUTOUT);

        // Phantom Wood
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("phantom" + "_planks"), BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("phantom" + "_stairs"), BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("phantom" + "_slab"), BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("phantom" + "_fence"), BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("phantom" + "_fence_gate"), BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("phantom" + "_door"), BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("phantom" + "_trapdoor"), BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("phantom" + "_pressure_plate"), BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("phantom" + "_button"), BlockRenderLayer.CUTOUT);

        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("phantom" + "_log"), BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("stripped_" + "phantom" + "_log"), BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("phantom" + "_wood"), BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("stripped_" + "phantom" + "_wood"), BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("phantom" + "_mosaic"), BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("phantom" + "_mosaic_stairs"), BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("phantom" + "_mosaic_slab"), BlockRenderLayer.CUTOUT);

        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("phantom" + "_glass_door"), BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("phantom" + "_glass_trapdoor"), BlockRenderLayer.CUTOUT);

        // Phantom Wood
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("phantom" + "_planks"), BlockRenderLayer.TRANSLUCENT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("phantom" + "_stairs"), BlockRenderLayer.TRANSLUCENT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("phantom" + "_slab"), BlockRenderLayer.TRANSLUCENT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("phantom" + "_fence"), BlockRenderLayer.TRANSLUCENT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("phantom" + "_fence_gate"), BlockRenderLayer.TRANSLUCENT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("phantom" + "_door"), BlockRenderLayer.TRANSLUCENT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("phantom" + "_trapdoor"), BlockRenderLayer.TRANSLUCENT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("phantom" + "_pressure_plate"), BlockRenderLayer.TRANSLUCENT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("phantom" + "_button"), BlockRenderLayer.TRANSLUCENT);

        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("phantom" + "_log"), BlockRenderLayer.TRANSLUCENT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("stripped_" + "phantom" + "_log"), BlockRenderLayer.TRANSLUCENT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("phantom" + "_wood"), BlockRenderLayer.TRANSLUCENT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("stripped_" + "phantom" + "_wood"), BlockRenderLayer.TRANSLUCENT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("phantom" + "_mosaic"), BlockRenderLayer.TRANSLUCENT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("phantom" + "_mosaic_stairs"), BlockRenderLayer.TRANSLUCENT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("phantom" + "_mosaic_slab"), BlockRenderLayer.TRANSLUCENT);

        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("phantom" + "_glass_door"), BlockRenderLayer.TRANSLUCENT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("phantom" + "_glass_trapdoor"), BlockRenderLayer.TRANSLUCENT);

        //Vanilla Door and TrapDoors
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("oak" + "_glass_door"), BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("spruce" + "_glass_door"), BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("jungle" + "_glass_door"), BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("birch" + "_glass_door"), BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("dark_oak" + "_glass_door"), BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("acacia" + "_glass_door"), BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("mangrove" + "_glass_door"), BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("cherry" + "_glass_door"), BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("bamboo" + "_glass_door"), BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("warped" + "_glass_door"), BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("crimson" + "_glass_door"), BlockRenderLayer.CUTOUT);
        //BlockRenderLayerMap.putBlock(BlockFactoryUpt.callBlock("pale_oak" + "_glass_door"), BlockRenderLayer.CUTOUT); //FUTURE UPDATE

        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("oak" + "_glass_trapdoor"), BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("spruce" + "_glass_trapdoor"), BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("jungle" + "_glass_trapdoor"), BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("birch" + "_glass_trapdoor"), BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("dark_oak" + "_glass_trapdoor"), BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("acacia" + "_glass_trapdoor"), BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("mangrove" + "_glass_trapdoor"), BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("cherry" + "_glass_trapdoor"), BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("bamboo" + "_glass_trapdoor"), BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("warped" + "_glass_trapdoor"), BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("crimson" + "_glass_trapdoor"), BlockRenderLayer.CUTOUT);
        //BlockRenderLayerMap.putBlock(BlockFactoryUpt.callBlock("pale_oak" + "_glass_trapdoor"), BlockRenderLayer.CUTOUT); //FUTURE UPDATE

        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("iron" + "_glass_door"), BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("copper" + "_glass_door"), BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("exposed_copper" + "_glass_door"), BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("oxidized_copper" + "_glass_door"), BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("weathered_copper" + "_glass_door"), BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("waxed_copper" + "_glass_door"), BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("waxed_exposed_copper" + "_glass_door"), BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("waxed_oxidized_copper" + "_glass_door"), BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("waxed_weathered_copper" + "_glass_door"), BlockRenderLayer.CUTOUT);

        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("iron" + "_glass_trapdoor"), BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("copper" + "_glass_trapdoor"), BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("exposed_copper" + "_glass_trapdoor"), BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("oxidized_copper" + "_glass_trapdoor"), BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("weathered_copper" + "_glass_trapdoor"), BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("waxed_copper" + "_glass_trapdoor"), BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("waxed_exposed_copper" + "_glass_trapdoor"), BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("waxed_oxidized_copper" + "_glass_trapdoor"), BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("waxed_weathered_copper" + "_glass_trapdoor"), BlockRenderLayer.CUTOUT);

        //String Blocks
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("string_block"), BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("string_gate"), BlockRenderLayer.CUTOUT_MIPPED);

        //Iron Gates
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("iron_grate"), BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("iron_grate_slab"), BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("iron_grate_stairs"), BlockRenderLayer.CUTOUT);

        //Torch
        //BlockRenderLayerMap.putBlock(BlockFactory.callBlock("phantom_torch"), BlockRenderLayer.CUTOUT);
        //BlockRenderLayerMap.putBlock(BlockFactory.callBlock("phantom_wall_torch"), BlockRenderLayer.CUTOUT);
        //BlockRenderLayerMap.putBlock(BlockFactory.callBlock("phantom_lantern"), BlockRenderLayer.CUTOUT);

        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("pointed_ice"), BlockRenderLayer.CUTOUT);

        

        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("blue_mushroom_block"), BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("blue_mushroom_block"), BlockRenderLayer.TRANSLUCENT);

        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("crystal_block"), BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("crystal_block"), BlockRenderLayer.TRANSLUCENT);

        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("blue_mushroom"), BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("mushroom_bed"), BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(BlockFactory.callBlock("potted_blue_mushroom"), BlockRenderLayer.CUTOUT);







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
