package net.ent.entstupidstuff.block;

import net.ent.entstupidstuff.particle.ParticleTypesFactory;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.SporeBlossomBlock;
import net.minecraft.world.level.block.state.BlockState;

public class MushroomSporeBlossomBlock extends SporeBlossomBlock {

    public MushroomSporeBlossomBlock(Properties settings) {
        super(settings);
    }

    @Override
	public void animateTick(BlockState state, Level world, BlockPos pos, RandomSource random) {
		int i = pos.getX();
		int j = pos.getY();
		int k = pos.getZ();
		double d = i + random.nextDouble();
		double e = j + 0.7;
		double f = k + random.nextDouble();
		world.addParticle(ParticleTypesFactory.FALLING_MUSHROOM_SPORE_BLOSSOM, d, e, f, 0.0, 0.0, 0.0);
		BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();

		for (int l = 0; l < 14; l++) {
			mutable.set(i + Mth.nextInt(random, -10, 10), j - random.nextInt(10), k + Mth.nextInt(random, -10, 10));
			BlockState blockState = world.getBlockState(mutable);
			if (!blockState.isCollisionShapeFullBlock(world, mutable)) {
				world.addParticle(
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
