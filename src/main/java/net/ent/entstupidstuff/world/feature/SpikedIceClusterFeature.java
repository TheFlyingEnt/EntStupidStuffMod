package net.ent.entstupidstuff.world.feature;

import java.util.Optional;
import java.util.OptionalInt;

import com.mojang.serialization.Codec;

import net.ent.entstupidstuff.block.BlockFactory;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.ClampedNormalFloat;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Column;
import net.minecraft.world.level.levelgen.feature.DripstoneUtils;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.DripstoneClusterConfiguration;

public class SpikedIceClusterFeature extends Feature<DripstoneClusterConfiguration> {
	public SpikedIceClusterFeature(Codec<DripstoneClusterConfiguration> codec) {
		super(codec);
	}

	@Override
	public boolean place(FeaturePlaceContext<DripstoneClusterConfiguration> context) {
		WorldGenLevel structureWorldAccess = context.level();
		BlockPos blockPos = context.origin();
		DripstoneClusterConfiguration dripstoneClusterFeatureConfig = context.config();
		RandomSource random = context.random();
		if (!SpikedIceHelper.canGenerate(structureWorldAccess, blockPos)) {
			return false;
		} else {
			int i = dripstoneClusterFeatureConfig.height.sample(random);
			float f = dripstoneClusterFeatureConfig.wetness.sample(random);
			float g = dripstoneClusterFeatureConfig.density.sample(random);
			int j = dripstoneClusterFeatureConfig.radius.sample(random);
			int k = dripstoneClusterFeatureConfig.radius.sample(random);

			for (int l = -j; l <= j; l++) {
				for (int m = -k; m <= k; m++) {
					double d = this.dripstoneChance(j, k, l, m, dripstoneClusterFeatureConfig);
					BlockPos blockPos2 = blockPos.offset(l, 0, m);
					this.generate(structureWorldAccess, random, blockPos2, l, m, f, d, i, g, dripstoneClusterFeatureConfig);
				}
			}

			return true;
		}
	}

	private void generate(
		WorldGenLevel world,
		RandomSource random,
		BlockPos pos,
		int localX,
		int localZ,
		float wetness,
		double dripstoneChance,
		int height,
		float density,
		DripstoneClusterConfiguration config
	) {
		Optional<Column> optional = Column.scan(
			world, pos, config.floorToCeilingSearchRange, DripstoneUtils::isEmptyOrWater, DripstoneUtils::isNeitherEmptyNorWater
		);
		if (!optional.isEmpty()) {
			OptionalInt optionalInt = ((Column)optional.get()).getCeiling();
			OptionalInt optionalInt2 = ((Column)optional.get()).getFloor();
			if (!optionalInt.isEmpty() || !optionalInt2.isEmpty()) {
				boolean bl = random.nextFloat() < wetness;
				Column caveSurface;
				if (bl && optionalInt2.isPresent() && this.canWaterSpawn(world, pos.atY(optionalInt2.getAsInt()))) {
					int i = optionalInt2.getAsInt();
					caveSurface = ((Column)optional.get()).withFloor(OptionalInt.of(i - 1));
					world.setBlock(pos.atY(i), Blocks.WATER.defaultBlockState(), Block.UPDATE_CLIENTS);
				} else {
					caveSurface = (Column)optional.get();
				}

				OptionalInt optionalInt3 = caveSurface.getFloor();
				boolean bl2 = random.nextDouble() < dripstoneChance;
				int l;
				if (optionalInt.isPresent() && bl2 && !this.isLava(world, pos.atY(optionalInt.getAsInt()))) {
					int j = config.dripstoneBlockLayerThickness.sample(random);
					this.placeDripstoneBlocks(world, pos.atY(optionalInt.getAsInt()), j, Direction.UP);
					int k;
					if (optionalInt3.isPresent()) {
						k = Math.min(height, optionalInt.getAsInt() - optionalInt3.getAsInt());
					} else {
						k = height;
					}

					l = this.getHeight(random, localX, localZ, density, k, config);
				} else {
					l = 0;
				}

				boolean bl3 = random.nextDouble() < dripstoneChance;
				int j;
				if (optionalInt3.isPresent() && bl3 && !this.isLava(world, pos.atY(optionalInt3.getAsInt()))) {
					int m = config.dripstoneBlockLayerThickness.sample(random);
					this.placeDripstoneBlocks(world, pos.atY(optionalInt3.getAsInt()), m, Direction.DOWN);
					if (optionalInt.isPresent()) {
						j = Math.max(0, l + Mth.randomBetweenInclusive(random, -config.maxStalagmiteStalactiteHeightDiff, config.maxStalagmiteStalactiteHeightDiff));
					} else {
						j = this.getHeight(random, localX, localZ, density, height, config);
					}
				} else {
					j = 0;
				}

				int t;
				int m;
				if (optionalInt.isPresent() && optionalInt3.isPresent() && optionalInt.getAsInt() - l <= optionalInt3.getAsInt() + j) {
					int n = optionalInt3.getAsInt();
					int o = optionalInt.getAsInt();
					int p = Math.max(o - l, n + 1);
					int q = Math.min(n + j, o - 1);
					int r = Mth.randomBetweenInclusive(random, p, q + 1);
					int s = r - 1;
					m = o - r;
					t = s - n;
				} else {
					m = l;
					t = j;
				}

				boolean bl4 = random.nextBoolean() && m > 0 && t > 0 && caveSurface.getHeight().isPresent() && m + t == caveSurface.getHeight().getAsInt();
				if (optionalInt.isPresent()) {
					SpikedIceHelper.generatePointedDripstone(world, pos.atY(optionalInt.getAsInt() - 1), Direction.DOWN, m, bl4);
				}

				if (optionalInt3.isPresent()) {
					SpikedIceHelper.generatePointedDripstone(world, pos.atY(optionalInt3.getAsInt() + 1), Direction.UP, t, bl4);
				}
			}
		}
	}

	private boolean isLava(LevelReader world, BlockPos pos) {
		return world.getBlockState(pos).is(Blocks.LAVA);
	}

	private int getHeight(RandomSource random, int localX, int localZ, float density, int height, DripstoneClusterConfiguration config) {
		if (random.nextFloat() > density) {
			return 0;
		} else {
			int i = Math.abs(localX) + Math.abs(localZ);
			float f = (float)Mth.clampedMap((double)i, 0.0, (double)config.maxDistanceFromCenterAffectingHeightBias, (double)height / 2.0, 0.0);
			return (int)clampedGaussian(random, 0.0F, (float)height, f, (float)config.heightDeviation);
		}
	}

	private boolean canWaterSpawn(WorldGenLevel world, BlockPos pos) {
		BlockState blockState = world.getBlockState(pos);
		if (!blockState.is(Blocks.WATER) && !blockState.is(Blocks.PACKED_ICE) && !blockState.is(BlockFactory.callBlock("pointed_ice"))) {
			if (world.getBlockState(pos.above()).getFluidState().is(FluidTags.WATER)) {
				return false;
			} else {
				for (Direction direction : Direction.Plane.HORIZONTAL) {
					if (!this.isStoneOrWater(world, pos.relative(direction))) {
						return false;
					}
				}

				return this.isStoneOrWater(world, pos.below());
			}
		} else {
			return false;
		}
	}

	private boolean isStoneOrWater(LevelAccessor world, BlockPos pos) {
		BlockState blockState = world.getBlockState(pos);
		return blockState.is(BlockTags.BASE_STONE_OVERWORLD) || blockState.getFluidState().is(FluidTags.WATER);
	}

	private void placeDripstoneBlocks(WorldGenLevel world, BlockPos pos, int height, Direction direction) {
		BlockPos.MutableBlockPos mutable = pos.mutable();

		for (int i = 0; i < height; i++) {
			if (!SpikedIceHelper.generateDripstoneBlock(world, mutable)) {
				return;
			}

			mutable.move(direction);
		}
	}

	private double dripstoneChance(int radiusX, int radiusZ, int localX, int localZ, DripstoneClusterConfiguration config) {
		int i = radiusX - Math.abs(localX);
		int j = radiusZ - Math.abs(localZ);
		int k = Math.min(i, j);
		return (double)Mth.clampedMap(
			(float)k, 0.0F, (float)config.maxDistanceFromEdgeAffectingChanceOfDripstoneColumn, config.chanceOfDripstoneColumnAtMaxDistanceFromCenter, 1.0F
		);
	}

	private static float clampedGaussian(RandomSource random, float min, float max, float mean, float deviation) {
		return ClampedNormalFloat.sample(random, mean, deviation, min, max);
	}
}

