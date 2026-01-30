package net.ent.entstupidstuff.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.GrowingPlantBodyBlock;
import net.minecraft.world.level.block.GrowingPlantHeadBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.phys.shapes.VoxelShape;

public class SilkwormVinePlantBlock extends GrowingPlantBodyBlock {
	public static final MapCodec<SilkwormVinePlantBlock> CODEC = simpleCodec(SilkwormVinePlantBlock::new);
	private static final VoxelShape SHAPE = Block.column(14.0, 0.0, 16.0);

	@Override
	public MapCodec<SilkwormVinePlantBlock> codec() {
		return CODEC;
	}

	public SilkwormVinePlantBlock(BlockBehaviour.Properties properties) {
		super(properties, Direction.DOWN, SHAPE, false);
	}

	@Override
	protected GrowingPlantHeadBlock getHeadBlock() {
		return (GrowingPlantHeadBlock)BlockFactory.SILKWORM_VINES;
	}
}
