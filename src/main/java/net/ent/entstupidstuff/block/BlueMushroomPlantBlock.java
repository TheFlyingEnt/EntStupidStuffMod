package net.ent.entstupidstuff.block;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.MushroomBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class BlueMushroomPlantBlock extends MushroomBlock{

    public BlueMushroomPlantBlock(ResourceKey<ConfiguredFeature<?, ?>> featureKey, Properties settings) {
        super(featureKey, settings);
    }

    @Override
	protected boolean canSurvive(BlockState state, LevelReader world, BlockPos pos) {
		BlockPos blockPos = pos.below();
		BlockState blockState = world.getBlockState(blockPos);
        Boolean valid = false;

        if (blockState.getBlock() == Blocks.MUD || blockState.getBlock() == BlockFactory.callBlock("shroomium")) {
            valid = true;
        }

		return valid ? true : world.getRawBrightness(pos, 0) < 13 && this.mayPlaceOn(blockState, world, blockPos);
	}
    
}
