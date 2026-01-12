package net.ent.entstupidstuff.world.tree;

import java.util.List;
import java.util.function.BiConsumer;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.ent.entstupidstuff.world.ModConfiguredFeatures;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;

public class FirTrunkPlacer  extends TrunkPlacer {
    public static final MapCodec<FirTrunkPlacer> CODEC = RecordCodecBuilder.mapCodec(
		instance -> trunkPlacerParts(instance).apply(instance, FirTrunkPlacer::new)
	);

    public FirTrunkPlacer(int baseHeight, int firstRandomHeight, int secondRandomHeight) {
        super(baseHeight, firstRandomHeight, secondRandomHeight);
    }

    @Override
    protected TrunkPlacerType<?> type() {
        return ModConfiguredFeatures.FIR_TRUNK_PLACER;
    }

    @Override
    public List<FoliagePlacer.FoliageAttachment> placeTrunk(
            LevelSimulatedReader world,
            BiConsumer<BlockPos, BlockState> replacer,
            RandomSource random,
            int height,
            BlockPos start,
            TreeConfiguration config
    ) {
        // Build straight trunk
        /*for (int y = 0; y < height; y++) {
            placeLog(world, placer, random, start.up(y), config);
        }*/

        setDirtAt(world, replacer, random, start.below(), config);

        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();
        for (int y = 0; y < height; y++) {
            mutable.setWithOffset(start, 0, y, 0); // relative to start
            this.placeLogIfFree(world, replacer, random, mutable, config);
        }


        // Return one foliage attachment at the top of the trunk
        return List.of(new FoliagePlacer.FoliageAttachment(start.above(height), 0, false));
    }
}
