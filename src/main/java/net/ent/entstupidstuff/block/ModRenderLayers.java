package net.ent.entstupidstuff.block;

import net.minecraft.client.render.RenderLayer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;


public class ModRenderLayers {

    /*
     * BlockFactoryUpt.callBlock
     * BlockFactoryUpt.callBlock
     * 
     */

    


    public static void onInitializeClient() {

        // Fungal Wood
        BlockRenderLayerMap.INSTANCE.putBlock(BlockFactoryUpt.callBlock("fungal" + "_glass_door"), RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockFactoryUpt.callBlock("fungal" + "_glass_trapdoor"), RenderLayer.getCutout());

        // Fungal (Colored) Wood
        for (String color : BlockFactoryUpt.COLORS) {
            BlockRenderLayerMap.INSTANCE.putBlock(BlockFactoryUpt.callBlock("fungal" + "_glass_door_" + color), RenderLayer.getCutout());
            BlockRenderLayerMap.INSTANCE.putBlock(BlockFactoryUpt.callBlock("fungal" + "_glass_trapdoor_" + color), RenderLayer.getCutout());
        }

        // Modded Wood
        BlockRenderLayerMap.INSTANCE.putBlock(BlockFactoryUpt.callBlock("redwood" + "_door"), RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockFactoryUpt.callBlock("redwood" + "_trapdoor"), RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockFactoryUpt.callBlock("redwood" + "_glass_door"), RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockFactoryUpt.callBlock("redwood" + "_glass_trapdoor"), RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockFactoryUpt.callBlock("maple" + "_glass_trapdoor"), RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockFactoryUpt.callBlock("desert_iron" + "_glass_trapdoor"), RenderLayer.getCutout());

        // Phantom Wood
        BlockRenderLayerMap.INSTANCE.putBlock(BlockFactoryUpt.callBlock("phantom" + "_planks"), RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockFactoryUpt.callBlock("phantom" + "_stairs"), RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockFactoryUpt.callBlock("phantom" + "_slab"), RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockFactoryUpt.callBlock("phantom" + "_fence"), RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockFactoryUpt.callBlock("phantom" + "_fence_gate"), RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockFactoryUpt.callBlock("phantom" + "_door"), RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockFactoryUpt.callBlock("phantom" + "_trapdoor"), RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockFactoryUpt.callBlock("phantom" + "_pressure_plate"), RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockFactoryUpt.callBlock("phantom" + "_button"), RenderLayer.getTranslucent());

        BlockRenderLayerMap.INSTANCE.putBlock(BlockFactoryUpt.callBlock("phantom" + "_log"), RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockFactoryUpt.callBlock("stripped_" + "phantom" + "_log"), RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockFactoryUpt.callBlock("phantom" + "_wood"), RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockFactoryUpt.callBlock("stripped_" + "phantom" + "_wood"), RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockFactoryUpt.callBlock("phantom" + "_mosaic"), RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockFactoryUpt.callBlock("phantom" + "_mosaic_stairs"), RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockFactoryUpt.callBlock("phantom" + "_mosaic_slab"), RenderLayer.getTranslucent());

        BlockRenderLayerMap.INSTANCE.putBlock(BlockFactoryUpt.callBlock("phantom" + "_glass_door"), RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockFactoryUpt.callBlock("phantom" + "_glass_trapdoor"), RenderLayer.getTranslucent());

        //Vanilla Door and TrapDoors
        BlockRenderLayerMap.INSTANCE.putBlock(BlockFactoryUpt.callBlock("oak" + "_glass_door"), RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockFactoryUpt.callBlock("spruce" + "_glass_door"), RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockFactoryUpt.callBlock("jungle" + "_glass_door"), RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockFactoryUpt.callBlock("birch" + "_glass_door"), RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockFactoryUpt.callBlock("dark_oak" + "_glass_door"), RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockFactoryUpt.callBlock("acacia" + "_glass_door"), RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockFactoryUpt.callBlock("mangrove" + "_glass_door"), RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockFactoryUpt.callBlock("cherry" + "_glass_door"), RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockFactoryUpt.callBlock("bamboo" + "_glass_door"), RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockFactoryUpt.callBlock("warped" + "_glass_door"), RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockFactoryUpt.callBlock("crimson" + "_glass_door"), RenderLayer.getCutout());
        //BlockRenderLayerMap.INSTANCE.putBlock(BlockFactoryUpt.callBlock("pale_oak" + "_glass_door"), RenderLayer.getCutout()); //FUTURE UPDATE

        BlockRenderLayerMap.INSTANCE.putBlock(BlockFactoryUpt.callBlock("oak" + "_glass_trapdoor"), RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockFactoryUpt.callBlock("spruce" + "_glass_trapdoor"), RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockFactoryUpt.callBlock("jungle" + "_glass_trapdoor"), RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockFactoryUpt.callBlock("birch" + "_glass_trapdoor"), RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockFactoryUpt.callBlock("dark_oak" + "_glass_trapdoor"), RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockFactoryUpt.callBlock("acacia" + "_glass_trapdoor"), RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockFactoryUpt.callBlock("mangrove" + "_glass_trapdoor"), RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockFactoryUpt.callBlock("cherry" + "_glass_trapdoor"), RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockFactoryUpt.callBlock("bamboo" + "_glass_trapdoor"), RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockFactoryUpt.callBlock("warped" + "_glass_trapdoor"), RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockFactoryUpt.callBlock("crimson" + "_glass_trapdoor"), RenderLayer.getCutout());
        //BlockRenderLayerMap.INSTANCE.putBlock(BlockFactoryUpt.callBlock("pale_oak" + "_glass_trapdoor"), RenderLayer.getCutout()); //FUTURE UPDATE

        BlockRenderLayerMap.INSTANCE.putBlock(BlockFactoryUpt.callBlock("iron" + "_glass_door"), RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockFactoryUpt.callBlock("copper" + "_glass_door"), RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockFactoryUpt.callBlock("exposed_copper" + "_glass_door"), RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockFactoryUpt.callBlock("oxidized_copper" + "_glass_door"), RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockFactoryUpt.callBlock("weathered_copper" + "_glass_door"), RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockFactoryUpt.callBlock("waxed_copper" + "_glass_door"), RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockFactoryUpt.callBlock("waxed_exposed_copper" + "_glass_door"), RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockFactoryUpt.callBlock("waxed_oxidized_copper" + "_glass_door"), RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockFactoryUpt.callBlock("waxed_weathered_copper" + "_glass_door"), RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(BlockFactoryUpt.callBlock("iron" + "_glass_trapdoor"), RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockFactoryUpt.callBlock("copper" + "_glass_trapdoor"), RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockFactoryUpt.callBlock("exposed_copper" + "_glass_trapdoor"), RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockFactoryUpt.callBlock("oxidized_copper" + "_glass_trapdoor"), RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockFactoryUpt.callBlock("weathered_copper" + "_glass_trapdoor"), RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockFactoryUpt.callBlock("waxed_copper" + "_glass_trapdoor"), RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockFactoryUpt.callBlock("waxed_exposed_copper" + "_glass_trapdoor"), RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockFactoryUpt.callBlock("waxed_oxidized_copper" + "_glass_trapdoor"), RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockFactoryUpt.callBlock("waxed_weathered_copper" + "_glass_trapdoor"), RenderLayer.getCutout());

        //String Blocks
        BlockRenderLayerMap.INSTANCE.putBlock(BlockFactoryUpt.callBlock("string_block"), RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockFactoryUpt.callBlock("string_gate"), RenderLayer.getCutoutMipped());







        /*

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.MOD_DOOR("fungal", null), RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.MOD_DOOR("fungal_glass", null), RenderLayer.getCutout());

        for (String color : BlockFactoryUpt.COLORS) {
            BlockRenderLayerMap.INSTANCE.putBlock(BlockFactoryUpt.callBlock("fungal" + "_door_" + color), RenderLayer.getCutout());
            BlockRenderLayerMap.INSTANCE.putBlock(BlockFactoryUpt.callBlock("fungal_glass" + "_door_" + color), RenderLayer.getCutout());
            BlockRenderLayerMap.INSTANCE.putBlock(BlockFactoryUpt.callBlock("fungal_glass" + "_trapdoor_" + color), RenderLayer.getCutout());
        }

        for (String base : ModBlocks.V_WOOD_VARIENTS) {
            BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.MOD_DOOR(base + "_glass_door", null), RenderLayer.getCutout());
        }

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.MOD_DOOR("iron" + "_glass_door", null), RenderLayer.getCutout());

        for (String base : ModBlocks.COPPER_VARIENTS) {
            BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.MOD_DOOR(base + "_glass_door", null), RenderLayer.getCutout());
        }

        //BlockRenderLayerMap.INSTANCE.putBlock(BlockFactoryUpt.callBlock("string_gate"), RenderLayer.getTranslucent());
        //BlockRenderLayerMap.INSTANCE.putBlock(BlockFactoryUpt.callBlock("string_pane"), RenderLayer.getTranslucent());
        //BlockRenderLayerMap.INSTANCE.putBlock(BlockFactoryUpt.callBlock("string_gate"), RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(BlockFactoryUpt.callBlock("string_block"), RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockFactoryUpt.callBlock("string_gate"), RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(BlockFactoryUpt.callBlock("redwood_door"), RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockFactoryUpt.callBlock("redwood_glass_door"), RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(BlockFactoryUpt.callBlock("desert_iron_glass_door"), RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BlockFactoryUpt.callBlock("desert_iron_door"), RenderLayer.getCutout());

        //BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.MOD_DOOR("oak_glass", null), RenderLayer.getCutout());
        //BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.MOD_DOOR("spruce_glass", null), RenderLayer.getCutout());
        //BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.MOD_DOOR("birch_glass", null), RenderLayer.getCutout());
       // BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.MOD_DOOR("jungle_glass", null), RenderLayer.getCutout());
        //BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.MOD_DOOR("acacia_glass", null), RenderLayer.getCutout());
        //BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.MOD_DOOR("dark_oak_glass", null), RenderLayer.getCutout());
        //BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.MOD_DOOR("mangrove_glass", null), RenderLayer.getCutout());
        //BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.MOD_DOOR("cherry_glass", null), RenderLayer.getCutout());
        //BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.MOD_DOOR("crimson_glass", null), RenderLayer.getCutout());
        //BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.MOD_DOOR("warped_glass", null), RenderLayer.getCutout());

        /*BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.MOD_TRAPDOOR("oak_glass", null), RenderLayer.getCutout());*/
        /*BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.MOD_TRAPDOOR("spruce_glass", null), RenderLayer.getCutout());*/
        /*BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.MOD_TRAPDOOR("birch_glass", null), RenderLayer.getCutout());*/
        /*BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.MOD_TRAPDOOR("jungle_glass", null), RenderLayer.getCutout());*/
        /*BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.MOD_TRAPDOOR("acacia_glass", null), RenderLayer.getCutout());*/
        /*BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.MOD_TRAPDOOR("dark_oak_glass", null), RenderLayer.getCutout());*/
        /*BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.MOD_TRAPDOOR("mangrove_glass", null), RenderLayer.getCutout());*/
        /*BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.MOD_TRAPDOOR("cherry_glass", null), RenderLayer.getCutout());*/
        /*BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.MOD_TRAPDOOR("crimson_glass", null), RenderLayer.getCutout());*/
        /*BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.MOD_TRAPDOOR("warped_glass", null), RenderLayer.getCutout());*/
    }
}
