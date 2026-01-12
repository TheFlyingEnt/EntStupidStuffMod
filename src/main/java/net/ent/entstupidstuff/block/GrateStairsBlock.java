package net.ent.entstupidstuff.block;

import java.util.Map;
import java.util.stream.IntStream;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.WaterloggedTransparentBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.Half;
import net.minecraft.world.level.block.state.properties.StairsShape;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import com.mojang.math.OctahedralGroup;
import com.mojang.math.Quadrant;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

@SuppressWarnings("unused")
public class GrateStairsBlock extends WaterloggedTransparentBlock implements SimpleWaterloggedBlock {

	public static final MapCodec<GrateStairsBlock> CODEC = RecordCodecBuilder.mapCodec(
		instance -> instance.group(BlockState.CODEC.fieldOf("base_state").forGetter(block -> block.baseBlockState), propertiesCodec())
				.apply(instance, GrateStairsBlock::new)
	);
    
	public static final EnumProperty<Direction> FACING;
	public static final EnumProperty<Half> HALF;
	public static final EnumProperty<StairsShape> SHAPE;
	public static final BooleanProperty WATERLOGGED;
	private static final VoxelShape OUTER_SHAPE;
	private static final VoxelShape STRAIGHT_SHAPE;
	private static final VoxelShape INNER_SHAPE;
	private static final Map<Direction, VoxelShape> OUTER_BOTTOM_SHAPES;
	private static final Map<Direction, VoxelShape> STRAIGHT_BOTTOM_SHAPES;
	private static final Map<Direction, VoxelShape> INNER_BOTTOM_SHAPES;
	private static final Map<Direction, VoxelShape> OUTER_TOP_SHAPES;
	private static final Map<Direction, VoxelShape> STRAIGHT_TOP_SHAPES;
	private static final Map<Direction, VoxelShape> INNER_TOP_SHAPES;

	static {
      FACING = HorizontalDirectionalBlock.FACING;
      HALF = BlockStateProperties.HALF;
      SHAPE = BlockStateProperties.STAIRS_SHAPE;
      WATERLOGGED = BlockStateProperties.WATERLOGGED;
      OUTER_SHAPE = Shapes.or(Block.column(16.0, 0.0, 8.0), Block.box(0.0, 8.0, 0.0, 8.0, 16.0, 8.0));
      STRAIGHT_SHAPE = Shapes.or(OUTER_SHAPE, Shapes.rotate(OUTER_SHAPE, OctahedralGroup.fromXYAngles(Quadrant.R0, Quadrant.R90)));
      INNER_SHAPE = Shapes.or(STRAIGHT_SHAPE, Shapes.rotate(STRAIGHT_SHAPE, OctahedralGroup.fromXYAngles(Quadrant.R0, Quadrant.R90)));
      OUTER_BOTTOM_SHAPES = Shapes.rotateHorizontal(OUTER_SHAPE);
      STRAIGHT_BOTTOM_SHAPES = Shapes.rotateHorizontal(STRAIGHT_SHAPE);
      INNER_BOTTOM_SHAPES = Shapes.rotateHorizontal(INNER_SHAPE);
      OUTER_TOP_SHAPES = Shapes.rotateHorizontal(Shapes.rotate(OUTER_SHAPE, OctahedralGroup.INVERT_Y));
      STRAIGHT_TOP_SHAPES = Shapes.rotateHorizontal(Shapes.rotate(STRAIGHT_SHAPE, OctahedralGroup.INVERT_Y));
      INNER_TOP_SHAPES = Shapes.rotateHorizontal(Shapes.rotate(INNER_SHAPE, OctahedralGroup.INVERT_Y));
   }

	private static final int[] SHAPE_INDICES = new int[]{12, 5, 3, 10, 14, 13, 7, 11, 13, 7, 11, 14, 8, 4, 1, 2, 4, 1, 2, 8};
	private final Block baseBlock;
	protected final BlockState baseBlockState;

	@Override
	public MapCodec<? extends GrateStairsBlock> codec() {
		return CODEC;
	}

	private static VoxelShape[] composeShapes(VoxelShape base, VoxelShape northWest, VoxelShape northEast, VoxelShape southWest, VoxelShape southEast) {
		return (VoxelShape[])IntStream.range(0, 16).mapToObj(i -> composeShape(i, base, northWest, northEast, southWest, southEast)).toArray(VoxelShape[]::new);
	}

	private static VoxelShape composeShape(int i, VoxelShape base, VoxelShape northWest, VoxelShape northEast, VoxelShape southWest, VoxelShape southEast) {
		VoxelShape voxelShape = base;
		if ((i & 1) != 0) {
			voxelShape = Shapes.or(base, northWest);
		}

		if ((i & 2) != 0) {
			voxelShape = Shapes.or(voxelShape, northEast);
		}

		if ((i & 4) != 0) {
			voxelShape = Shapes.or(voxelShape, southWest);
		}

		if ((i & 8) != 0) {
			voxelShape = Shapes.or(voxelShape, southEast);
		}

		return voxelShape;
	}

	public GrateStairsBlock(BlockState baseBlockState, BlockBehaviour.Properties settings) {
		super(settings);
		this.registerDefaultState(
			this.stateDefinition
				.any()
				.setValue(FACING, Direction.NORTH)
				.setValue(HALF, Half.BOTTOM)
				.setValue(SHAPE, StairsShape.STRAIGHT)
				.setValue(WATERLOGGED, Boolean.valueOf(false))
		);
		this.baseBlock = baseBlockState.getBlock();
		this.baseBlockState = baseBlockState;
	}

	@Override
	protected boolean useShapeForLightOcclusion(BlockState state) {
		return true;
	}


	protected VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
      boolean bl = state.getValue(HALF) == Half.BOTTOM;
      Direction direction = (Direction)state.getValue(FACING);
      Map var10000;
      switch ((StairsShape) state.getValue(SHAPE)) {
		case STRAIGHT:
			var10000 = bl ? STRAIGHT_BOTTOM_SHAPES : STRAIGHT_TOP_SHAPES;
			break;
		case OUTER_LEFT:
		case OUTER_RIGHT:
			var10000 = bl ? OUTER_BOTTOM_SHAPES : OUTER_TOP_SHAPES;
			break;
		case INNER_LEFT:
		case INNER_RIGHT:
			var10000 = bl ? INNER_BOTTOM_SHAPES : INNER_TOP_SHAPES;
			break;
		default:
			throw new IllegalStateException("Unexpected StairShape: " + ((StairsShape) state.getValue(SHAPE)));
	}

      Direction var10001;
      switch ((StairsShape) state.getValue(SHAPE)) {
		case STRAIGHT:
		case INNER_LEFT:
		case INNER_RIGHT:
			var10001 = direction;
			break;
		case OUTER_LEFT:
			var10001 = direction.getCounterClockWise();
			break;
		case OUTER_RIGHT:
			var10001 = direction.getClockWise();
			break;
		default:
			throw new MatchException(null, null);
	}

      return (VoxelShape)var10000.get(var10001);
   }
	@Override
	public float getExplosionResistance() {
		return this.baseBlock.getExplosionResistance();
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext ctx) {
		Direction direction = ctx.getClickedFace();
		BlockPos blockPos = ctx.getClickedPos();
		FluidState fluidState = ctx.getLevel().getFluidState(blockPos);
		BlockState blockState = this.defaultBlockState()
			.setValue(FACING, ctx.getHorizontalDirection())
			.setValue(
				HALF, direction != Direction.DOWN && (direction == Direction.UP || !(ctx.getClickLocation().y - (double)blockPos.getY() > 0.5)) ? Half.BOTTOM : Half.TOP
			)
			.setValue(WATERLOGGED, Boolean.valueOf(fluidState.getType() == Fluids.WATER));
		return blockState.setValue(SHAPE, getStairShape(blockState, ctx.getLevel(), blockPos));
	}

	@Override
	protected BlockState updateShape(BlockState state, LevelReader world, ScheduledTickAccess tickView, BlockPos pos, Direction direction, BlockPos neighborPos, BlockState neighborState, RandomSource random) {
      if ((Boolean)state.getValue(WATERLOGGED)) {
         tickView.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(world));
      }

      return direction.getAxis().isHorizontal() ? (BlockState)state.setValue(SHAPE, getStairShape(state, world, pos)) : super.updateShape(state, world, tickView, pos, direction, neighborPos, neighborState, random);
   }

	private static StairsShape getStairShape(BlockState state, BlockGetter world, BlockPos pos) {
      Direction direction = (Direction)state.getValue(FACING);
      BlockState blockState = world.getBlockState(pos.relative(direction));
      if (isStairs(blockState) && state.getValue(HALF) == blockState.getValue(HALF)) {
         Direction direction2 = (Direction)blockState.getValue(FACING);
         if (direction2.getAxis() != ((Direction)state.getValue(FACING)).getAxis() && isDifferentOrientation(state, world, pos, direction2.getOpposite())) {
            if (direction2 == direction.getCounterClockWise()) {
               return StairsShape.OUTER_LEFT;
            }

            return StairsShape.OUTER_RIGHT;
         }
      }

      BlockState blockState2 = world.getBlockState(pos.relative(direction.getOpposite()));
      if (isStairs(blockState2) && state.getValue(HALF) == blockState2.getValue(HALF)) {
         Direction direction3 = (Direction)blockState2.getValue(FACING);
         if (direction3.getAxis() != ((Direction)state.getValue(FACING)).getAxis() && isDifferentOrientation(state, world, pos, direction3)) {
            if (direction3 == direction.getCounterClockWise()) {
               return StairsShape.INNER_LEFT;
            }

            return StairsShape.INNER_RIGHT;
         }
      }

      return StairsShape.STRAIGHT;
   }

	private static boolean isDifferentOrientation(BlockState state, BlockGetter world, BlockPos pos, Direction dir) {
		BlockState blockState = world.getBlockState(pos.relative(dir));
		return !isStairs(blockState) || blockState.getValue(FACING) != state.getValue(FACING) || blockState.getValue(HALF) != state.getValue(HALF);
	}

	public static boolean isStairs(BlockState state) {
		return state.getBlock() instanceof GrateStairsBlock;
	}

	@Override
	protected BlockState rotate(BlockState state, Rotation rotation) {
      return (BlockState)state.setValue(FACING, rotation.rotate((Direction)state.getValue(FACING)));
   }

	@SuppressWarnings("incomplete-switch")
	@Override
	protected BlockState mirror(BlockState state, Mirror mirror) {
		Direction direction = (Direction)state.getValue(FACING);
		StairsShape stairShape = state.getValue(SHAPE);
		switch (mirror) {
			case LEFT_RIGHT:
				if (direction.getAxis() == Direction.Axis.Z) {
					switch (stairShape) {
						case INNER_LEFT:
							return state.rotate(Rotation.CLOCKWISE_180).setValue(SHAPE, StairsShape.INNER_RIGHT);
						case INNER_RIGHT:
							return state.rotate(Rotation.CLOCKWISE_180).setValue(SHAPE, StairsShape.INNER_LEFT);
						case OUTER_LEFT:
							return state.rotate(Rotation.CLOCKWISE_180).setValue(SHAPE, StairsShape.OUTER_RIGHT);
						case OUTER_RIGHT:
							return state.rotate(Rotation.CLOCKWISE_180).setValue(SHAPE, StairsShape.OUTER_LEFT);
						default:
							return state.rotate(Rotation.CLOCKWISE_180);
					}
				}
				break;
			case FRONT_BACK:
				if (direction.getAxis() == Direction.Axis.X) {
					switch (stairShape) {
						case INNER_LEFT:
							return state.rotate(Rotation.CLOCKWISE_180).setValue(SHAPE, StairsShape.INNER_LEFT);
						case INNER_RIGHT:
							return state.rotate(Rotation.CLOCKWISE_180).setValue(SHAPE, StairsShape.INNER_RIGHT);
						case OUTER_LEFT:
							return state.rotate(Rotation.CLOCKWISE_180).setValue(SHAPE, StairsShape.OUTER_RIGHT);
						case OUTER_RIGHT:
							return state.rotate(Rotation.CLOCKWISE_180).setValue(SHAPE, StairsShape.OUTER_LEFT);
						case STRAIGHT:
							return state.rotate(Rotation.CLOCKWISE_180);
					}
				}
		}

		return super.mirror(state, mirror);
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(FACING, HALF, SHAPE, WATERLOGGED);
	}

	@Override
	protected FluidState getFluidState(BlockState state) {
		return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
	}

	@Override
	protected boolean isPathfindable(BlockState state, PathComputationType type) {
		return false;
	}
    
}