package net.ent.entstupidstuff.block;

import net.ent.entstupidstuff.particle.ParticleTypesFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.SporeBlossomBlock;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public class MushroomSporeBlossomBlock extends SporeBlossomBlock {

    public MushroomSporeBlossomBlock(Settings settings) {
        super(settings);
    }

    @Override
	public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
		int i = pos.getX();
		int j = pos.getY();
		int k = pos.getZ();
		double d = i + random.nextDouble();
		double e = j + 0.7;
		double f = k + random.nextDouble();
		world.addParticleClient(ParticleTypesFactory.FALLING_MUSHROOM_SPORE_BLOSSOM, d, e, f, 0.0, 0.0, 0.0);
		BlockPos.Mutable mutable = new BlockPos.Mutable();

		for (int l = 0; l < 14; l++) {
			mutable.set(i + MathHelper.nextInt(random, -10, 10), j - random.nextInt(10), k + MathHelper.nextInt(random, -10, 10));
			BlockState blockState = world.getBlockState(mutable);
			if (!blockState.isFullCube(world, mutable)) {
				world.addParticleClient(
					ParticleTypesFactory.FALLING_MUSHROOM_SPORE, //Falling
					mutable.getX() + random.nextDouble(),
					mutable.getY() + random.nextDouble(),
					mutable.getZ() + random.nextDouble(),
					0.0,
					0.0,
					0.0
				);
			}
		}
	}
    
}
