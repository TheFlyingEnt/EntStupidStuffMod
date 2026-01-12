package net.ent.entstupidstuff.block;

import java.util.Map;

import org.jetbrains.annotations.Nullable;

import com.mojang.serialization.MapCodec;
import java.util.List;
import java.util.stream.IntStream;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class DateBlock extends HorizontalDirectionalBlock implements BonemealableBlock {

   public static final MapCodec<DateBlock> CODEC = simpleCodec(DateBlock::new);
   public static final int MAX_AGE = 2;
   public static final IntegerProperty AGE;
   private static final List<Map<Direction, VoxelShape>> SHAPES;

   public MapCodec<DateBlock> codec() {
      return CODEC;
   }

   public DateBlock(BlockBehaviour.Properties settings) {
      super(settings);
      this.registerDefaultState((BlockState)((BlockState)((BlockState)this.stateDefinition.any()).setValue(FACING, Direction.NORTH)).setValue(AGE, 0));
   }

   protected boolean isRandomlyTicking(BlockState state) {
      return (Integer)state.getValue(AGE) < 2;
   }

   protected void randomTick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
      if (world.random.nextInt(5) == 0) {
         int i = (Integer)state.getValue(AGE);
         if (i < 2) {
            world.setBlock(pos, (BlockState)state.setValue(AGE, i + 1), 2);
         }
      }

   }

   protected boolean canSurvive(BlockState state, LevelReader world, BlockPos pos) {
      BlockState blockState = world.getBlockState(pos.relative((Direction)state.getValue(FACING)));
      return blockState.is(BlockTags.JUNGLE_LOGS);
   }

   protected VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
      return (VoxelShape)((Map)SHAPES.get((Integer)state.getValue(AGE))).get(state.getValue(FACING));
   }

   @Nullable
   public BlockState getStateForPlacement(BlockPlaceContext ctx) {
      BlockState blockState = this.defaultBlockState();
      LevelReader worldView = ctx.getLevel();
      BlockPos blockPos = ctx.getClickedPos();
      Direction[] var5 = ctx.getNearestLookingDirections();
      int var6 = var5.length;

      for(int var7 = 0; var7 < var6; ++var7) {
         Direction direction = var5[var7];
         if (direction.getAxis().isHorizontal()) {
            blockState = (BlockState)blockState.setValue(FACING, direction);
            if (blockState.canSurvive(worldView, blockPos)) {
               return blockState;
            }
         }
      }

      return null;
   }

   protected BlockState updateShape(BlockState state, LevelReader world, ScheduledTickAccess tickView, BlockPos pos, Direction direction, BlockPos neighborPos, BlockState neighborState, RandomSource random) {
      return direction == state.getValue(FACING) && !state.canSurvive(world, pos) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, world, tickView, pos, direction, neighborPos, neighborState, random);
   }

   public boolean isValidBonemealTarget(LevelReader world, BlockPos pos, BlockState state) {
      return (Integer)state.getValue(AGE) < 2;
   }

   public boolean isBonemealSuccess(Level world, RandomSource random, BlockPos pos, BlockState state) {
      return true;
   }

   public void performBonemeal(ServerLevel world, RandomSource random, BlockPos pos, BlockState state) {
      world.setBlock(pos, (BlockState)state.setValue(AGE, (Integer)state.getValue(AGE) + 1), 2);
   }

   protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
      builder.add(new Property[]{FACING, AGE});
   }

   protected boolean isPathfindable(BlockState state, PathComputationType type) {
      return false;
   }

   static {
      AGE = BlockStateProperties.AGE_2;
      SHAPES = IntStream.rangeClosed(0, 2).mapToObj((age) -> {
         return Shapes.rotateHorizontal(Block.column((double)(4 + age * 2), (double)(7 - age * 2), 12.0).move(0.0, 0.0, (double)(age - 5) / 16.0).optimize());
      }).toList();
   }
}

