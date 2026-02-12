package net.ent.entstupidstuff.block.base;

import net.ent.entstupidstuff.block.BlockFactory;
import net.ent.entstupidstuff.block.entity.BlockEntityFactory;
import net.ent.entstupidstuff.block.entity.MushroomAuraBlockEntity;
import net.ent.entstupidstuff.particle.ParticleTypesFactory;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class MushroomAuraBlock extends Block implements EntityBlock {

    public MushroomAuraBlock(Properties properties) {
        super(properties);
    }

    // # Change Mushroom Placement

    @Override
	protected void randomTick(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, RandomSource randomSource) {
		if (randomSource.nextInt(25) == 0) {
			int i = 5;
			int j = 4;

			for (BlockPos blockPos2 : BlockPos.betweenClosed(blockPos.offset(-4, -1, -4), blockPos.offset(4, 1, 4))) {
				if (serverLevel.getBlockState(blockPos2).is(this)) {
					if (--i <= 0) {
						return;
					}
				}
			}

			BlockPos blockPos3 = blockPos.offset(randomSource.nextInt(3) - 1, randomSource.nextInt(2) - randomSource.nextInt(2), randomSource.nextInt(3) - 1);

			for (int k = 0; k < 4; k++) {
				if (serverLevel.isEmptyBlock(blockPos3) && blockState.canSurvive(serverLevel, blockPos3)) {
					blockPos = blockPos3;
				}

				blockPos3 = blockPos.offset(randomSource.nextInt(3) - 1, randomSource.nextInt(2) - randomSource.nextInt(2), randomSource.nextInt(3) - 1);
			}

			if (serverLevel.isEmptyBlock(blockPos3) && blockState.canSurvive(serverLevel, blockPos3)) {
				serverLevel.setBlock(blockPos3, blockState, 2);
			}
		}
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


	protected boolean mayPlaceOn(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos) {
		return blockState.isSolidRender();
	}

    // # Entity Block

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new MushroomAuraBlockEntity(pos, state);
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState blockState, BlockEntityType<T> blockEntityType) {

        if (blockEntityType == BlockEntityFactory.MUSHROOM_AURA_BLOCK_ENTITY) {
            return (lvl, pos, st, be) ->
                MushroomAuraBlockEntity.tick(lvl, pos, st, (MushroomAuraBlockEntity) be);
        }

        return null;
    }

    // # Particles

    @Override
	public void animateTick(BlockState state, Level world, BlockPos pos, RandomSource random) {
		int i = pos.getX();
		int j = pos.getY();
		int k = pos.getZ();
		double d = i + random.nextDouble();
		double e = j + 0.7;
		double f = k + random.nextDouble();
		//world.addParticle(ParticleTypesFactory.FALLING_MUSHROOM_SPORE_BLOSSOM, d, e, f, 0.0, 0.0, 0.0);
		BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();

		for (int l = 0; l < 14; l++) {
			mutable.set(i + Mth.nextInt(random, -10, 10), j - random.nextInt(10), k + Mth.nextInt(random, -10, 10));
			BlockState blockState = world.getBlockState(mutable);
			if (!blockState.isCollisionShapeFullBlock(world, mutable)) {
				world.addParticle(
					ParticleTypesFactory.FALLING_MUSHROOM_SPORE, //Falling
					mutable.getX() + random.nextDouble(),
					mutable.getY() + random.nextDouble(),
					mutable.getZ() + random.nextDouble(),
					0.0,
					0.0,
					0.0
				);
			}
		}
	}
}
