package net.ent.entstupidstuff.world.feature;

import java.util.function.Consumer;

import net.ent.entstupidstuff.block.BlockFactory;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.PointedDripstoneBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DripstoneThickness;
import net.minecraft.world.level.levelgen.feature.DripstoneUtils;

public class SpikedIceHelper {
	protected static double scaleHeightFromRadius(double radius, double scale, double heightScale, double bluntness) {
		if (radius < bluntness) {
			radius = bluntness;
		}

		//double d = 0.384;
		double e = radius / scale * 0.384;
		double f = 0.75 * Math.pow(e, 1.3333333333333333);
		double g = Math.pow(e, 0.6666666666666666);
		double h = 0.3333333333333333 * Math.log(e);
		double i = heightScale * (f - g - h);
		i = Math.max(i, 0.0);
		return i / 0.384 * scale;
	}

	protected static boolean canGenerateBase(WorldGenLevel world, BlockPos pos, int height) {
		if (canGenerateOrLava(world, pos)) {
			return false;
		} else {
			//float f = 6.0F;
			float g = 6.0F / (float)height;

			for (float h = 0.0F; h < (float) (Math.PI * 2); h += g) {
				int i = (int)(Mth.cos(h) * (float)height);
				int j = (int)(Mth.sin(h) * (float)height);
				if (canGenerateOrLava(world, pos.offset(i, 0, j))) {
					return false;
				}
			}

			return true;
		}
	}

	protected static boolean canGenerate(LevelAccessor world, BlockPos pos) {
		return world.isStateAtPosition(pos, DripstoneUtils::isEmptyOrWater);
	}

	protected static boolean canGenerateOrLava(LevelAccessor world, BlockPos pos) {
		return world.isStateAtPosition(pos, DripstoneUtils::isEmptyOrWaterOrLava);
	}

	protected static void getDripstoneThickness(Direction direction, int height, boolean merge, Consumer<BlockState> callback) {
		if (height >= 3) {
			callback.accept(getState(direction, DripstoneThickness.BASE));

			for (int i = 0; i < height - 3; i++) {
				callback.accept(getState(direction, DripstoneThickness.MIDDLE));
			}
		}

		if (height >= 2) {
			callback.accept(getState(direction, DripstoneThickness.FRUSTUM));
		}

		if (height >= 1) {
			callback.accept(getState(direction, merge ? DripstoneThickness.TIP_MERGE : DripstoneThickness.TIP));
		}
	}

	protected static void generatePointedDripstone(LevelAccessor world, BlockPos pos, Direction direction, int height, boolean merge) {
		if (canReplace(world.getBlockState(pos.relative(direction.getOpposite())))) {
			BlockPos.MutableBlockPos mutable = pos.mutable();
			getDripstoneThickness(direction, height, merge, state -> {
				if (state.is(BlockFactory.callBlock("pointed_ice"))) {
					state = state.setValue(PointedDripstoneBlock.WATERLOGGED, Boolean.valueOf(world.isWaterAt(mutable)));
				}

				world.setBlock(mutable, state, Block.UPDATE_CLIENTS);
				mutable.move(direction);
			});
		}
	}

	protected static boolean generateDripstoneBlock(LevelAccessor world, BlockPos pos) {
		BlockState blockState = world.getBlockState(pos);
		if (blockState.is(BlockTags.DRIPSTONE_REPLACEABLE)) {
			world.setBlock(pos, Blocks.PACKED_ICE.defaultBlockState(), Block.UPDATE_CLIENTS);
			return true;
		} else {
			return false;
		}
	}

	private static BlockState getState(Direction direction, DripstoneThickness thickness) {
		return BlockFactory.callBlock("pointed_ice").defaultBlockState().setValue(PointedDripstoneBlock.TIP_DIRECTION, direction).setValue(PointedDripstoneBlock.THICKNESS, thickness);
	}

	public static boolean canReplaceOrLava(BlockState state) {
		return canReplace(state) || state.is(Blocks.LAVA);
	}

	public static boolean canReplace(BlockState state) {
		return state.is(Blocks.PACKED_ICE) || state.is(BlockTags.DRIPSTONE_REPLACEABLE);
	}

	public static boolean canGenerate(BlockState state) {
		return state.isAir() || state.is(Blocks.WATER);
	}

	public static boolean cannotGenerate(BlockState state) {
		return !state.isAir() && !state.is(Blocks.WATER);
	}

	public static boolean canGenerateOrLava(BlockState state) {
		return state.isAir() || state.is(Blocks.WATER) || state.is(Blocks.LAVA);
	}
}