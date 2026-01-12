package net.ent.entstupidstuff.block;


import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.HugeMushroomBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class TransparentMushroomBlock extends HugeMushroomBlock {

    public static final MapCodec<TransparentMushroomBlock> CODEC = simpleCodec(TransparentMushroomBlock::new);

    public TransparentMushroomBlock(Properties settings) {
        super(settings);
    }

	@Override
	protected VoxelShape getVisualShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		return Shapes.empty();
	}

	@Override
	protected float getShadeBrightness(BlockState state, BlockGetter world, BlockPos pos) {
		return 1.0F;
	}

	@Override
	protected boolean propagatesSkylightDown(BlockState state) {
		return true;
	}

    @Override
	protected boolean skipRendering(BlockState state, BlockState stateFrom, Direction direction) {
		return stateFrom.is(this) ? true : super.skipRendering(state, stateFrom, direction);
	}



    
}
