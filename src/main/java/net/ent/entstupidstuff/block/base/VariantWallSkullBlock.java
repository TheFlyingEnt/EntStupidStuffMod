package net.ent.entstupidstuff.block.base;

import net.ent.entstupidstuff.block.blockentity.VariantSkullBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.SkullBlock;
import net.minecraft.world.level.block.WallSkullBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class VariantWallSkullBlock extends WallSkullBlock {
    public VariantWallSkullBlock(SkullBlock.Type type, Properties properties) {
        super(type, properties);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new VariantSkullBlockEntity(pos, state);
    }
}
