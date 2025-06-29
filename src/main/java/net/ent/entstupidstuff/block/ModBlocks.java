package net.ent.entstupidstuff.block;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;

public class ModBlocks {

    /**
    * This Purpose of this class is to be a Port for other Part of the Mod to reach certain block types
    */

    public static Block COLOR_PLANK(String color, String type){
        if (BlockFactoryUpt.callBlock("fungal_" + type + "_" + color) != null){
            return BlockFactoryUpt.callBlock("fungal_" + type + "_" + color);
        }
        return null;  
    }

    public static Block MOD_DOOR(String mat, String varient){
        if (varient == null){varient = "";}
        else {varient = "_" + varient;}

        return BlockFactoryUpt.callBlock(mat + "_door" + varient);
    }

    public static String[] V_WOOD_VARIENTS = {"oak", "spruce", "jungle", "birch", "dark_oak", "acacia","mangrove", "cherry", "bamboo", "warped", "crimson"};

    public static String[] COPPER_VARIENTS = {"copper", "exposed_copper", "oxidized_copper", "weathered_copper", "waxed_copper", "waxed_exposed_copper", "waxed_oxidized_copper", "waxed_weathered_copper"};

    public static Block VANILLA_PLANK(String base) {
        if ("oak" == base) return Blocks.OAK_PLANKS;
        else if ("oak" == base) return Blocks.SPRUCE_PLANKS;
        else if ("spruce" == base) return Blocks.JUNGLE_PLANKS;
        else if ("jungle" == base) return Blocks.BIRCH_PLANKS;
        else if ("birch" == base) return Blocks.DARK_OAK_PLANKS;
        else if ("dark_oak" == base) return Blocks.ACACIA_PLANKS;
        else if ("acacia" == base) return Blocks.MANGROVE_PLANKS;
        else if ("mangrove" == base) return Blocks.CHERRY_PLANKS;
        else if ("cherry" == base) return Blocks.BAMBOO_PLANKS;
        else if ("bamboo" == base) return Blocks.BAMBOO_PLANKS;
        else if ("warped" == base) return Blocks.WARPED_PLANKS;
        else if ("crimson" == base) return Blocks.CRIMSON_PLANKS;
        else return null; //Crash Game
    }

    public static Block VANILLA_DOOR(String base) {
        if ("oak" == base) return Blocks.OAK_DOOR;
        else if ("oak" == base) return Blocks.SPRUCE_DOOR;
        else if ("spruce" == base) return Blocks.JUNGLE_DOOR;
        else if ("jungle" == base) return Blocks.BIRCH_DOOR;
        else if ("birch" == base) return Blocks.DARK_OAK_DOOR;
        else if ("dark_oak" == base) return Blocks.ACACIA_DOOR;
        else if ("acacia" == base) return Blocks.MANGROVE_DOOR;
        else if ("mangrove" == base) return Blocks.CHERRY_DOOR;
        else if ("cherry" == base) return Blocks.BAMBOO_DOOR;
        else if ("bamboo" == base) return Blocks.BAMBOO_DOOR;
        else if ("warped" == base) return Blocks.WARPED_DOOR;
        else if ("crimson" == base) return Blocks.CRIMSON_DOOR;
        else return null; //Crash Game
    }

    public static Block COPPER_DOOR(String base) {
        if ("copper" == base) return Blocks.COPPER_DOOR;
        else if ("exposed_copper" == base) return Blocks.EXPOSED_COPPER_DOOR;
        else if ("oxidized_copper" == base) return Blocks.OXIDIZED_COPPER_DOOR;
        else if ("weathered_copper" == base) return Blocks.WEATHERED_COPPER_DOOR;
        else if ("waxed_copper" == base) return Blocks.WAXED_EXPOSED_COPPER_DOOR;
        else if ("waxed_exposed_copper" == base) return Blocks.WAXED_OXIDIZED_COPPER_DOOR;
        else if ("waxed_oxidized_copper" == base) return Blocks.WAXED_WEATHERED_COPPER_DOOR;
        else if ("waxed_weathered_copper" == base) return Blocks.WAXED_EXPOSED_COPPER_DOOR;
        else return null; //Crash Game
    }


    //Stones

    public static final Block POLISHED_ANDESITE_BRICKS = BlockFactoryUpt.callBlock("polished_andesite_bricks");
    public static final Block POLISHED_ANDESITE_BRICKS_STAIRS = BlockFactoryUpt.callBlock("polished_andesite_bricks_stairs");
    public static final Block POLISHED_ANDESITE_BRICKS_SLAB = BlockFactoryUpt.callBlock("polished_andesite_bricks_slab");
    public static final Block POLISHED_ANDESITE_BRICKS_WALL = BlockFactoryUpt.callBlock("polished_andesite_bricks_wall");

}
