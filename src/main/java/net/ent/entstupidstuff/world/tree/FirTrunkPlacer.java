package net.ent.entstupidstuff.world.tree;

import java.util.List;
import java.util.function.BiConsumer;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.ent.entstupidstuff.world.ConfiguredFeaturesFactory;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.trunk.TrunkPlacer;
import net.minecraft.world.gen.trunk.TrunkPlacerType;

public class FirTrunkPlacer  extends TrunkPlacer {
    public static final MapCodec<FirTrunkPlacer> CODEC = RecordCodecBuilder.mapCodec(
		instance -> fillTrunkPlacerFields(instance).apply(instance, FirTrunkPlacer::new)
	);

    public FirTrunkPlacer(int baseHeight, int firstRandomHeight, int secondRandomHeight) {
        super(baseHeight, firstRandomHeight, secondRandomHeight);
    }

    @Override
    protected TrunkPlacerType<?> getType() {
        return ConfiguredFeaturesFactory.FIR_TRUNK_PLACER;
    }

    @Override
    public List<FoliagePlacer.TreeNode> generate(
            TestableWorld world,
            BiConsumer<BlockPos, BlockState> replacer,
            Random random,
            int height,
            BlockPos start,
            TreeFeatureConfig config
    ) {
        // Build straight trunk
        /*for (int y = 0; y < height; y++) {
            placeLog(world, placer, random, start.up(y), config);
        }*/

        setToDirt(world, replacer, random, start.down(), config);

        BlockPos.Mutable mutable = new BlockPos.Mutable();
        for (int y = 0; y < height; y++) {
            mutable.set(start, 0, y, 0); // relative to start
            this.trySetState(world, replacer, random, mutable, config);
        }


        // Return one foliage attachment at the top of the trunk
        return List.of(new FoliagePlacer.TreeNode(start.up(height), 0, false));
    }
}
