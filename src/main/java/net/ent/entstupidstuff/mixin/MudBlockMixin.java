package net.ent.entstupidstuff.mixin;

import org.spongepowered.asm.mixin.Mixin;

import net.ent.entstupidstuff.block.BlockFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.Fertilizable;
import net.minecraft.block.MudBlock;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;

@Mixin(MudBlock.class)
public abstract class MudBlockMixin implements Fertilizable {

    @Override
    public boolean isFertilizable(WorldView world, BlockPos pos, BlockState state) {

        if (!world.getBlockState(pos.up()).isTransparent()) {
			return false;
		} else {
			for (BlockPos blockPos : BlockPos.iterate(pos.add(-1, -1, -1), pos.add(1, 1, 1))) {
				if (world.getBlockState(blockPos).isOf(BlockFactory.callBlock("shroomium"))) {
                    return true;
                }
			}

			return false;
		}


        // Example: Only fertilizable if the block above is air/transparent
        //return world.getBlockState(pos.up()).isAir();
    }

    @Override
    public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
        // Can always grow when fertilized
        return true;
    }

    @Override
    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        // Example behavior: transform into grass when grown
        world.setBlockState(pos, BlockFactory.callBlock("shroomium").getDefaultState(), 3);
    }

    @Override
    public Fertilizable.FertilizableType getFertilizableType() {
        // Neighbor spreading type, like Netherrack
        return Fertilizable.FertilizableType.NEIGHBOR_SPREADER;
    }
}
