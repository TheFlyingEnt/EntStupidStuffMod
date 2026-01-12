package net.ent.entstupidstuff.world.feature;

import java.util.Optional;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.PointedDripstoneConfiguration;
import com.mojang.serialization.Codec;

public class SpikedIceFeature extends Feature<PointedDripstoneConfiguration> {
	public SpikedIceFeature(Codec<PointedDripstoneConfiguration> codec) {
		super(codec);
	}

	@Override
	public boolean place(FeaturePlaceContext<PointedDripstoneConfiguration> context) {
		LevelAccessor worldAccess = context.level();
		BlockPos blockPos = context.origin();
		RandomSource random = context.random();
		PointedDripstoneConfiguration smallDripstoneFeatureConfig = context.config();
		Optional<Direction> optional = getDirection(worldAccess, blockPos, random);
		if (optional.isEmpty()) {
			return false;
		} else {
			BlockPos blockPos2 = blockPos.relative(((Direction)optional.get()).getOpposite());
			generateDripstoneBlocks(worldAccess, random, blockPos2, smallDripstoneFeatureConfig);
			int i = random.nextFloat() < smallDripstoneFeatureConfig.chanceOfTallerDripstone
					&& SpikedIceHelper.canGenerate(worldAccess.getBlockState(blockPos.relative((Direction)optional.get())))
				? 2
				: 1;
			SpikedIceHelper.generatePointedDripstone(worldAccess, blockPos, (Direction)optional.get(), i, false);
			return true;
		}
	}

	private static Optional<Direction> getDirection(LevelAccessor world, BlockPos pos, RandomSource random) {
		boolean bl = SpikedIceHelper.canReplace(world.getBlockState(pos.above()));
		boolean bl2 = SpikedIceHelper.canReplace(world.getBlockState(pos.below()));
		if (bl && bl2) {
			return Optional.of(random.nextBoolean() ? Direction.DOWN : Direction.UP);
		} else if (bl) {
			return Optional.of(Direction.DOWN);
		} else {
			return bl2 ? Optional.of(Direction.UP) : Optional.empty();
		}
	}

	private static void generateDripstoneBlocks(LevelAccessor world, RandomSource random, BlockPos pos, PointedDripstoneConfiguration config) {
		SpikedIceHelper.generateDripstoneBlock(world, pos);

		for (Direction direction : Direction.Plane.HORIZONTAL) {
			if (!(random.nextFloat() > config.chanceOfDirectionalSpread)) {
				BlockPos blockPos = pos.relative(direction);
				SpikedIceHelper.generateDripstoneBlock(world, blockPos);
				if (!(random.nextFloat() > config.chanceOfSpreadRadius2)) {
					BlockPos blockPos2 = blockPos.relative(Direction.getRandom(random));
					SpikedIceHelper.generateDripstoneBlock(world, blockPos2);
					if (!(random.nextFloat() > config.chanceOfSpreadRadius3)) {
						BlockPos blockPos3 = blockPos2.relative(Direction.getRandom(random));
						SpikedIceHelper.generateDripstoneBlock(world, blockPos3);
					}
				}
			}
		}
	}
}

