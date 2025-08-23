package net.ent.entstupidstuff.block;

import com.mojang.serialization.MapCodec;

import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public class MapleLeavesBlock extends LeavesBlock {
    public static final MapCodec<MapleLeavesBlock> CODEC = createCodec(MapleLeavesBlock::new);

    @Override
	public MapCodec<MapleLeavesBlock> getCodec() {
		return CODEC;
	}

    public MapleLeavesBlock(Settings settings) {
        super(settings);
    }


    @Override
	public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
		super.randomDisplayTick(state, world, pos, random);
		if (random.nextInt(10) == 0) {
			BlockPos blockPos = pos.down();
			BlockState blockState = world.getBlockState(blockPos);
			if (!isFaceFullSquare(blockState.getCollisionShape(world, blockPos), Direction.UP)) {
				//ParticleUtil.spawnParticle(world, pos, random, ParticleTypesFactory.MAPLE_LEAVES);
                //ParticleUtil.spawnParticle(world, pos, random, ParticleTypes.CHERRY_LEAVES);
			}
		}
	}
    
}
