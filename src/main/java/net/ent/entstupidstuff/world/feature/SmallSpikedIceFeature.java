package net.ent.entstupidstuff.world.feature;

import java.util.Optional;

import com.mojang.serialization.Codec;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.SmallDripstoneFeatureConfig;
import net.minecraft.world.gen.feature.util.FeatureContext;

public class SmallSpikedIceFeature extends Feature<SmallDripstoneFeatureConfig> {
	public SmallSpikedIceFeature(Codec<SmallDripstoneFeatureConfig> codec) {
		super(codec);
	}

	@Override
	public boolean generate(FeatureContext<SmallDripstoneFeatureConfig> context) {
		WorldAccess worldAccess = context.getWorld();
		BlockPos blockPos = context.getOrigin();
		Random random = context.getRandom();
		SmallDripstoneFeatureConfig smallDripstoneFeatureConfig = context.getConfig();
		Optional<Direction> optional = getDirection(worldAccess, blockPos, random);
		if (optional.isEmpty()) {
			return false;
		} else {
			BlockPos blockPos2 = blockPos.offset(((Direction)optional.get()).getOpposite());
			generateDripstoneBlocks(worldAccess, random, blockPos2, smallDripstoneFeatureConfig);
			int i = random.nextFloat() < smallDripstoneFeatureConfig.chanceOfTallerDripstone
					&& SpikedIceHelper.canGenerate(worldAccess.getBlockState(blockPos.offset((Direction)optional.get())))
				? 2
				: 1;
			SpikedIceHelper.generatePointedDripstone(worldAccess, blockPos, (Direction)optional.get(), i, false);
			return true;
		}
	}

	private static Optional<Direction> getDirection(WorldAccess world, BlockPos pos, Random random) {
		boolean bl = SpikedIceHelper.canReplace(world.getBlockState(pos.up()));
		boolean bl2 = SpikedIceHelper.canReplace(world.getBlockState(pos.down()));
		if (bl && bl2) {
			return Optional.of(random.nextBoolean() ? Direction.DOWN : Direction.UP);
		} else if (bl) {
			return Optional.of(Direction.DOWN);
		} else {
			return bl2 ? Optional.of(Direction.UP) : Optional.empty();
		}
	}

	private static void generateDripstoneBlocks(WorldAccess world, Random random, BlockPos pos, SmallDripstoneFeatureConfig config) {
		SpikedIceHelper.generateDripstoneBlock(world, pos);

		for (Direction direction : Direction.Type.HORIZONTAL) {
			if (!(random.nextFloat() > config.chanceOfDirectionalSpread)) {
				BlockPos blockPos = pos.offset(direction);
				SpikedIceHelper.generateDripstoneBlock(world, blockPos);
				if (!(random.nextFloat() > config.chanceOfSpreadRadius2)) {
					BlockPos blockPos2 = blockPos.offset(Direction.random(random));
					SpikedIceHelper.generateDripstoneBlock(world, blockPos2);
					if (!(random.nextFloat() > config.chanceOfSpreadRadius3)) {
						BlockPos blockPos3 = blockPos2.offset(Direction.random(random));
						SpikedIceHelper.generateDripstoneBlock(world, blockPos3);
					}
				}
			}
		}
	}
}

