package net.ent.entstupidstuff.mixin;

import org.spongepowered.asm.mixin.Mixin;

import net.ent.entstupidstuff.block.BlockFactory;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.MudBlock;
import net.minecraft.world.level.block.state.BlockState;

@Mixin(MudBlock.class)
public abstract class MudBlockMixin implements BonemealableBlock {

    @Override
    public boolean isValidBonemealTarget(LevelReader world, BlockPos pos, BlockState state) {

        if (!world.getBlockState(pos.above()).propagatesSkylightDown()) {
			return false;
		} else {
			for (BlockPos blockPos : BlockPos.betweenClosed(pos.offset(-1, -1, -1), pos.offset(1, 1, 1))) {
				if (world.getBlockState(blockPos).is(BlockFactory.callBlock("shroomium"))) {
                    return true;
                }
			}

			return false;
		}


        // Example: Only fertilizable if the block above is air/transparent
        //return world.getBlockState(pos.up()).isAir();
    }

    @Override
    public boolean isBonemealSuccess(Level world, RandomSource random, BlockPos pos, BlockState state) {
        // Can always grow when fertilized
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel world, RandomSource random, BlockPos pos, BlockState state) {
        // Example behavior: transform into grass when grown
        world.setBlock(pos, BlockFactory.callBlock("shroomium").defaultBlockState(), 3);
    }

    @Override
    public BonemealableBlock.Type getType() {
        // Neighbor spreading type, like Netherrack
        return BonemealableBlock.Type.NEIGHBOR_SPREADER;
    }
}
