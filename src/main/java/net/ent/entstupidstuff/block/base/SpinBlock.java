package net.ent.entstupidstuff.block.base;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class SpinBlock extends Block {

    protected static final VoxelShape SHAPE = Shapes.box(
            0.0D, 0.0D, 0.0D,
            1.0D, 0.1D, 1.0D
    );

    public SpinBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
        super.stepOn(level, pos, state, entity);

        if (!(entity instanceof LivingEntity living)) {
            return;
        }

        // Rotate continuously
        living.setYRot(living.getYRot() + 20.0F);

        // Sync body/head rotation
        living.yBodyRot = living.getYRot();
        living.yHeadRot = living.getYRot();

        // Optional crazy spinning
        living.setXRot((living.getXRot() + 10.0F) % 360F);

        // Mark hurt animation update
        living.hurtMarked = true;
    }


    @Override
    protected void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        super.tick(state, level, pos, random);
    }
}