package net.ent.entstupidstuff.block;

import org.jetbrains.annotations.Nullable;

import com.mojang.serialization.MapCodec;

import net.ent.entstupidstuff.block.entity.DarkEnchantingTableBlockEntity;
import net.ent.entstupidstuff.screen.DarkEnchantmentScreenHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.Nameable;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.EnchantingTableBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class DarkEnchantingTableBlock extends BaseEntityBlock{
    public static final MapCodec<DarkEnchantingTableBlock> CODEC = simpleCodec(DarkEnchantingTableBlock::new);
    protected static final VoxelShape SHAPE = Block.box(0.0, 0.0, 0.0, 16.0, 12.0, 16.0);

    public DarkEnchantingTableBlock(Properties settings) {
        super(settings);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new DarkEnchantingTableBlockEntity(pos, state);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
	protected boolean useShapeForLightOcclusion(BlockState state) {
		return true;
	}

    @Override
	protected VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		return SHAPE;
	}

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level world, BlockPos pos, Player player, BlockHitResult hit) {
        /*if (!world.isClient()) {
            player.openHandledScreen((NamedScreenHandlerFactory) world.getBlockEntity(pos));
        }
        return ActionResult.SUCCESS;*/

        if (!world.isClientSide()) {
            BlockEntity be = world.getBlockEntity(pos);
            if (be instanceof MenuProvider factory) {
                player.openMenu(factory);
            }
        }
        return InteractionResult.SUCCESS;
	}

    @Nullable
	@Override
	protected MenuProvider getMenuProvider(BlockState state, Level world, BlockPos pos) {
		BlockEntity blockEntity = world.getBlockEntity(pos);
		if (blockEntity instanceof EnchantingTableBlockEntity) {
			Component text = ((Nameable)blockEntity).getDisplayName();
			return new SimpleMenuProvider(
				//(syncId, inventory, player) -> new EnchantmentScreenHandler(syncId, inventory, ScreenHandlerContext.create(world, pos)), text
                (syncId, inventory, player) -> new DarkEnchantmentScreenHandler(syncId, inventory, ContainerLevelAccess.create(world, pos)), text
			);
		} else {
			return null;
		}
	}
    
}
