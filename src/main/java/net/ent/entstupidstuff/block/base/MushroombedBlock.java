package net.ent.entstupidstuff.block.base;

import net.ent.entstupidstuff.block.BlockFactory;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerBedBlock;
import net.minecraft.world.level.block.state.BlockState;

public class MushroombedBlock extends FlowerBedBlock{

    public MushroombedBlock(Properties settings) {
        super(settings);
    }

    @Override
    protected boolean mayPlaceOn(BlockState floor, BlockGetter world, BlockPos pos) {
		return floor.is(BlockTags.DIRT) || floor.is(Blocks.FARMLAND) || floor.getBlock() == BlockFactory.callBlock("shroomium");
	}
    
}
