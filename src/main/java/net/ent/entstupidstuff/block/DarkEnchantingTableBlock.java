package net.ent.entstupidstuff.block;

import org.jetbrains.annotations.Nullable;

import com.mojang.serialization.MapCodec;

import net.ent.entstupidstuff.block.entity.DarkEnchantingTableBlockEntity;
import net.ent.entstupidstuff.screen.DarkEnchantingTableHandler;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.EnchantingTableBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.EnchantmentScreenHandler;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Nameable;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class DarkEnchantingTableBlock extends BlockWithEntity{
    public static final MapCodec<DarkEnchantingTableBlock> CODEC = createCodec(DarkEnchantingTableBlock::new);
    protected static final VoxelShape SHAPE = Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 12.0, 16.0);

    public DarkEnchantingTableBlock(Settings settings) {
        super(settings);
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new EnchantingTableBlockEntity(pos, state);
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return CODEC;
    }

    @Override
	protected boolean hasSidedTransparency(BlockState state) {
		return true;
	}

    @Override
	protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
		return SHAPE;
	}

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (world.isClient) {
			return ActionResult.SUCCESS;
		} else {
			player.openHandledScreen(state.createScreenHandlerFactory(world, pos));
			return ActionResult.CONSUME;
		}
	}

    @Nullable
	@Override
	protected NamedScreenHandlerFactory createScreenHandlerFactory(BlockState state, World world, BlockPos pos) {
		BlockEntity blockEntity = world.getBlockEntity(pos);
		if (blockEntity instanceof EnchantingTableBlockEntity) {
			Text text = ((Nameable)blockEntity).getDisplayName();
			return new SimpleNamedScreenHandlerFactory(
				//(syncId, inventory, player) -> new EnchantmentScreenHandler(syncId, inventory, ScreenHandlerContext.create(world, pos)), text
                (syncId, inventory, player) -> new DarkEnchantingTableHandler(syncId, inventory, ScreenHandlerContext.create(world, pos)), text
			);
		} else {
			return null;
		}
	}
    
}
