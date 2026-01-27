package net.ent.entstupidstuff.block;

import net.ent.entstupidstuff.block.entity.BlockEntityFactory;
import net.ent.entstupidstuff.block.entity.MushroomAuraBlockEntity_2;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class MushroomAuraBlock_2 extends Block implements EntityBlock {

    public MushroomAuraBlock_2(Properties properties) {
        super(properties);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new MushroomAuraBlockEntity_2(pos, state);
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState blockState, BlockEntityType<T> blockEntityType) {

        if (blockEntityType == BlockEntityFactory.MUSHROOM_AURA_BLOCK_ENTITY_2) {
            return (lvl, pos, st, be) ->
                MushroomAuraBlockEntity_2.tick(lvl, pos, st, (MushroomAuraBlockEntity_2) be);
        }

        return null;
    }
}
