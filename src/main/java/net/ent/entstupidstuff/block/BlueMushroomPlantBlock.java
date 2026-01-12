package net.ent.entstupidstuff.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.MushroomPlantBlock;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldView;
import net.minecraft.world.gen.feature.ConfiguredFeature;

public class BlueMushroomPlantBlock extends MushroomPlantBlock{

    public BlueMushroomPlantBlock(RegistryKey<ConfiguredFeature<?, ?>> featureKey, Settings settings) {
        super(featureKey, settings);
    }

    @Override
	protected boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
		BlockPos blockPos = pos.down();
		BlockState blockState = world.getBlockState(blockPos);
        Boolean valid = false;

        if (blockState.getBlock() == Blocks.MUD || blockState.getBlock() == BlockFactory.callBlock("shroomium")) {
            valid = true;
        }

		return valid ? true : world.getBaseLightLevel(pos, 0) < 13 && this.canPlantOnTop(blockState, world, blockPos);
	}
    
}
