package net.ent.entstupidstuff.block.base;

import org.jetbrains.annotations.Nullable;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.WaterloggedTransparentBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class GrateSlabBlock extends WaterloggedTransparentBlock{
    public static final MapCodec<GrateSlabBlock> CODEC = simpleCodec(GrateSlabBlock::new);
	public static final EnumProperty<SlabType> TYPE = BlockStateProperties.SLAB_TYPE;
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	protected static final VoxelShape BOTTOM_SHAPE = Block.box(0.0, 0.0, 0.0, 16.0, 8.0, 16.0);
	protected static final VoxelShape TOP_SHAPE = Block.box(0.0, 8.0, 0.0, 16.0, 16.0, 16.0);

	@Override
	public MapCodec<? extends GrateSlabBlock> codec() {
		return CODEC;
	}

	public GrateSlabBlock(BlockBehaviour.Properties settings) {
		super(settings);
		this.registerDefaultState(this.defaultBlockState().setValue(TYPE, SlabType.BOTTOM).setValue(WATERLOGGED, Boolean.valueOf(false)));
	}

	@Override
	protected boolean useShapeForLightOcclusion(BlockState state) {
		return state.getValue(TYPE) != SlabType.DOUBLE;
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(TYPE, WATERLOGGED);
	}

	@Override
	protected VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		SlabType slabType = state.getValue(TYPE);
		switch (slabType) {
			case DOUBLE:
				return Shapes.block();
			case TOP:
				return TOP_SHAPE;
			default:
				return BOTTOM_SHAPE;
		}
	}

	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext ctx) {
		BlockPos blockPos = ctx.getClickedPos();
		BlockState blockState = ctx.getLevel().getBlockState(blockPos);
		if (blockState.is(this)) {
			return blockState.setValue(TYPE, SlabType.DOUBLE).setValue(WATERLOGGED, Boolean.valueOf(false));
		} else {
			FluidState fluidState = ctx.getLevel().getFluidState(blockPos);
			BlockState blockState2 = this.defaultBlockState().setValue(TYPE, SlabType.BOTTOM).setValue(WATERLOGGED, Boolean.valueOf(fluidState.getType() == Fluids.WATER));
			Direction direction = ctx.getClickedFace();
			return direction != Direction.DOWN && (direction == Direction.UP || !(ctx.getClickLocation().y - (double)blockPos.getY() > 0.5))
				? blockState2
				: blockState2.setValue(TYPE, SlabType.TOP);
		}
	}

	@Override
	protected boolean canBeReplaced(BlockState state, BlockPlaceContext context) {
		ItemStack itemStack = context.getItemInHand();
		SlabType slabType = state.getValue(TYPE);
		if (slabType == SlabType.DOUBLE || !itemStack.is(this.asItem())) {
			return false;
		} else if (context.replacingClickedOnBlock()) {
			boolean bl = context.getClickLocation().y - (double)context.getClickedPos().getY() > 0.5;
			Direction direction = context.getClickedFace();
			return slabType == SlabType.BOTTOM
				? direction == Direction.UP || bl && direction.getAxis().isHorizontal()
				: direction == Direction.DOWN || !bl && direction.getAxis().isHorizontal();
		} else {
			return true;
		}
	}

	@Override
	protected FluidState getFluidState(BlockState state) {
		return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
	}

	@Override
    public boolean placeLiquid(LevelAccessor world, BlockPos pos, BlockState state, FluidState fluidState) {
        if (state.getValue(TYPE) != SlabType.DOUBLE) {
            return super.placeLiquid(world, pos, state, fluidState);  // Use Block's or BlockState's implementation
        }
        return false;
    }

	@Override
	protected BlockState updateShape(BlockState state, LevelReader world, ScheduledTickAccess tickView, BlockPos pos, Direction direction, BlockPos neighborPos, BlockState neighborState, RandomSource random) {
      if ((Boolean)state.getValue(WATERLOGGED)) {
         tickView.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(world));
      }

      return super.updateShape(state, world, tickView, pos, direction, neighborPos, neighborState, random);
   }

	@Override
	protected boolean isPathfindable(BlockState state, PathComputationType type) {
		switch (type) {
			case LAND:
				return false;
			case WATER:
				return state.getFluidState().is(FluidTags.WATER);
			case AIR:
				return false;
			default:
				return false;
		}
	}

	@Override
		protected boolean skipRendering(BlockState state, BlockState neighbor, Direction direction) {
			/*if (state.get(TYPE) == SlabType.DOUBLE) {
				return super.isSideInvisible(state, neighbor, direction);
			}*/


			if (neighbor.getBlock() instanceof WaterloggedTransparentBlock || neighbor.getBlock() instanceof GrateSlabBlock) {
				return false;
			}

			return super.skipRendering(state, neighbor, direction);
		}
}
