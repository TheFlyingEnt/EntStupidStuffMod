package net.ent.entstupidstuff.block.base;

import com.mojang.serialization.MapCodec;

import net.ent.entstupidstuff.world.ModConfiguredFeatures;
import net.ent.entstupidstuff.world.ModPlacedFeatures;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.features.NetherFeatures;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.lighting.LightEngine;

public class ShroomiumBlock extends Block implements BonemealableBlock {

    public static final MapCodec<ShroomiumBlock> CODEC = simpleCodec(ShroomiumBlock::new);

    public ShroomiumBlock(Properties settings) {
        super(settings);
    }

    @Override
	public MapCodec<ShroomiumBlock> codec() {
		return CODEC;
	}

    private static boolean stayAlive(BlockState state, LevelReader world, BlockPos pos) {
		BlockPos blockPos = pos.above();
		BlockState blockState = world.getBlockState(blockPos);
		int i = LightEngine.getLightBlockInto(state, blockState, Direction.UP, blockState.getLightBlock());
		return i < 15;
	}

	@Override
	protected void randomTick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
		if (!stayAlive(state, world, pos)) {
			world.setBlockAndUpdate(pos, Blocks.MUD.defaultBlockState());
		}
	}

    @Override
    public boolean isValidBonemealTarget(LevelReader world, BlockPos pos, BlockState state) {
        return world.getBlockState(pos.above()).isAir();
    }

    @Override
    public boolean isBonemealSuccess(Level world, RandomSource random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel serverLevel, RandomSource randomSource, BlockPos blockPos, BlockState blockState) {
        //Nothing yet: TBA
        BlockState blockState2 = serverLevel.getBlockState(blockPos);
		BlockPos blockPos2 = blockPos.above();
		ChunkGenerator chunkGenerator = serverLevel.getChunkSource().getGenerator();
		Registry<ConfiguredFeature<?, ?>> registry = serverLevel.registryAccess().lookupOrThrow(Registries.CONFIGURED_FEATURE);

        this.place(registry, ModConfiguredFeatures.MUSHROOM_FOREST_VEGETATION_BONEMEAL, serverLevel, chunkGenerator, randomSource, blockPos2);
    }

    private void place(
		Registry<ConfiguredFeature<?, ?>> registry,
		ResourceKey<ConfiguredFeature<?, ?>> resourceKey,
		ServerLevel serverLevel,
		ChunkGenerator chunkGenerator,
		RandomSource randomSource,
		BlockPos blockPos
	) {
		registry.get(resourceKey).ifPresent(reference -> ((ConfiguredFeature)reference.value()).place(serverLevel, chunkGenerator, randomSource, blockPos));
	}

    @Override
	public BonemealableBlock.Type getType() {
		return BonemealableBlock.Type.NEIGHBOR_SPREADER;
	}
    
}
