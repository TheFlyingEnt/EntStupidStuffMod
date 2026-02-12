package net.ent.entstupidstuff.block.base;

import com.mojang.serialization.MapCodec;

import net.ent.entstupidstuff.block.BlockFactory;
import net.ent.entstupidstuff.particle.ParticleTypesFactory;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.GrowingPlantHeadBlock;
import net.minecraft.world.level.block.NetherVines;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;

public class SilkwormVineBlock extends GrowingPlantHeadBlock {
	public static final MapCodec<SilkwormVineBlock> CODEC = simpleCodec(SilkwormVineBlock::new);
	private static final VoxelShape SHAPE = Block.column(8.0, 9.0, 16.0);

	@Override
	public MapCodec<SilkwormVineBlock> codec() {
		return CODEC;
	}

	public SilkwormVineBlock(BlockBehaviour.Properties properties) {
		super(properties, Direction.DOWN, SHAPE, false, 0.1);
	}

	@Override
	protected int getBlocksToGrowWhenBonemealed(RandomSource randomSource) {
		return NetherVines.getBlocksToGrowWhenBonemealed(randomSource);
	}

	@Override
	protected Block getBodyBlock() {
        return BlockFactory.SILKWORM_VINES_PLANT;
	}

	@Override
	protected boolean canGrowInto(BlockState blockState) {
		return NetherVines.isValidGrowthState(blockState);
	}

    @Override
	public void animateTick(BlockState blockState, Level level, BlockPos blockPos, RandomSource randomSource) {
		int i = blockPos.getX();
		int j = blockPos.getY();
		int k = blockPos.getZ();
		double d = i + randomSource.nextDouble();
		double e = j + 0.7;
		double f = k + randomSource.nextDouble();
		level.addParticle(ParticleTypesFactory.FALLING_MUSHROOM_SPORE_BLOSSOM, d, e, f, 0.0, 0.0, 0.0);
	}
}
