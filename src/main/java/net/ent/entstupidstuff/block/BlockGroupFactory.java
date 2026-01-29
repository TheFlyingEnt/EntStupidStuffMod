package net.ent.entstupidstuff.block;

import net.minecraft.data.BlockFamilies;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

@SuppressWarnings("unused")
public class BlockGroupFactory {

    public static void groupStoneFamily(String MainName, Block MainBlock, MapColor MainColor, Boolean Bricks) {
        //Stairs + Slab + Walls
        BlockFactoryHelper.BaseFamily(MainName, "", MainBlock, false);

        Block Wall = BlockFactory.register4(MainName + "_wall",
            (settings) -> new WallBlock(settings),
            BlockBehaviour.Properties.ofFullCopy(MainBlock)
        );

        if (Bricks) {
            //Chizeled + Cracked
            Block Chiseled = BlockFactory.register4(MainName + "_chiseled",
                    (settings) -> new Block(settings),
                    BlockBehaviour.Properties.ofFullCopy(MainBlock)
                );

            Block Cracked = BlockFactory.register4("cracked_" + MainName + "s",
                (settings) -> new Block(settings),
                BlockBehaviour.Properties.ofFullCopy(MainBlock)
            );
        }
    }
    
    public static void groupWoodFamilty(String MainName, String Varient, Block MainBlock, Boolean flamable, MapColor MainColor, MapColor SecondColor) {
        BlockFactoryHelper.BaseFamily(MainName, Varient, MainBlock, flamable);
        BlockFactoryHelper.InteractionFamily(MainName, Varient, MainBlock, MainColor, flamable, true);
        BlockFactoryHelper.NatureFamily(MainName , Varient, MainColor, SecondColor, flamable, true);
        BlockFactoryHelper.MosicFamily(MainName , Varient, MainBlock, flamable);

        BlockFamilies.familyBuilder(MainBlock)
            .button(BlockFactory.callBlock(MainName + "_" + "button" + Varient))
            .fence(BlockFactory.callBlock(MainName + "_" + "fence" + Varient))
            .fenceGate(BlockFactory.callBlock(MainName + "_" + "fence_gate" + Varient))
            .pressurePlate(BlockFactory.callBlock(MainName + "_" + "button" + Varient))
            .slab(BlockFactory.callBlock(MainName + "_slab" + Varient))
            .stairs(BlockFactory.callBlock(MainName + "_stairs" + Varient))
            .trapdoor(BlockFactory.callBlock(MainName + "_" + "trapdoor" + Varient))
            .trapdoor(BlockFactory.callBlock(MainName + "_" + "glass_trapdoor" + Varient))
            .door(BlockFactory.callBlock(MainName + "_" + "door" + Varient)) 
            .door(BlockFactory.callBlock(MainName + "_" + "glass_door" + Varient))
            .mosaic(BlockFactory.callBlock(MainName + "_" + "mosic" + Varient))
        .recipeGroupPrefix("wooden")
        .recipeUnlockedBy("has_planks")
        .getFamily();
    }

    public static void groupFungalFamily(String Varient, MapColor MainColor) {
        Block FUNGAL_PLANKS = BlockFactory.register4("fungal" + "_planks" + Varient, Block::new, (BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).mapColor((MainColor))));
        
        BlockFactoryHelper.BaseFamily("fungal", Varient, FUNGAL_PLANKS, true);
        BlockFactoryHelper.InteractionFamily("fungal", Varient, FUNGAL_PLANKS, MainColor, true, true);
        BlockFactoryHelper.MosicFamily("fungal" , Varient, FUNGAL_PLANKS, true);

        BlockFamilies.familyBuilder(FUNGAL_PLANKS)
            .button(BlockFactory.callBlock("fungal" + "_" + "button" + Varient))
            .fence(BlockFactory.callBlock("fungal" + "_" + "fence" + Varient))
            .fenceGate(BlockFactory.callBlock("fungal" + "_" + "fence_gate" + Varient))
            .pressurePlate(BlockFactory.callBlock("fungal" + "_" + "pressure_plate" + Varient))
            .slab(BlockFactory.callBlock("fungal" + "_slab" + Varient))
            .stairs(BlockFactory.callBlock("fungal" + "_stairs" + Varient))
            .trapdoor(BlockFactory.callBlock("fungal" + "_" + "trapdoor" + Varient))
            .trapdoor(BlockFactory.callBlock("fungal" + "_" + "glass_trapdoor" + Varient))
            .door(BlockFactory.callBlock("fungal" + "_" + "door" + Varient)) 
            .door(BlockFactory.callBlock("fungal" + "_" + "glass_door" + Varient))
        .recipeGroupPrefix("wooden")
        .recipeUnlockedBy("has_planks")
        .getFamily();

    }
    
}
