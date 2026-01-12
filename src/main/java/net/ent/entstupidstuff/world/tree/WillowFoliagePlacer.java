package net.ent.entstupidstuff.world.tree;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.ent.entstupidstuff.world.ModConfiguredFeatures;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.foliage.FoliagePlacerType;

public class WillowFoliagePlacer extends FoliagePlacer {

    public static final MapCodec<WillowFoliagePlacer> CODEC = RecordCodecBuilder.mapCodec(
        instance -> fillFoliagePlacerFields(instance)
            .apply(instance, WillowFoliagePlacer::new)
    );

    /*public static final Codec<WillowFoliagePlacer> CODEC =
            RecordCodecBuilder.create(instance ->
                    fillFoliagePlacerFields(instance)
                            .apply(instance, WillowFoliagePlacer::new)
            );*/

    public WillowFoliagePlacer(IntProvider radius, IntProvider offset) {
        super(radius, offset);
    }

    @Override
    protected FoliagePlacerType<?> getType() {
        return ModConfiguredFeatures.WILLOW_FOLIAGE_PLACE;
    }

    @Override
    protected void generate(
            TestableWorld world,
            BlockPlacer placer,
            Random random,
            TreeFeatureConfig config,
            int trunkHeight,
            TreeNode node,
            int foliageHeight,
            int radius,
            int offset
    ) {
        BlockPos center = node.getCenter();

        // ── MAIN CANOPY (dense blob)
        for (int y = 0; y >= -2; y--) {
            placeSolidCircle(placer, random, config, center.down(-y), 3 - y);
        }

        // ── HANGING LEAVES (curtains)
        for (int x = -3; x <= 3; x++) {
            for (int z = -3; z <= 3; z++) {
                if (x * x + z * z <= 9 && random.nextFloat() < 0.75f) {
                    BlockPos start = center.add(x, -3, z);
                    placeHangingStrand(placer, random, config, start, random.nextInt(4) + 3);
                }
            }
        }
    }

    private void placeSolidCircle(BlockPlacer placer, Random random,
                                  TreeFeatureConfig config, BlockPos center, int r) {
        for (int x = -r; x <= r; x++) {
            for (int z = -r; z <= r; z++) {
                if (x * x + z * z <= r * r) {
                    BlockPos pos = center.add(x, 0, z);
                    placer.placeBlock(pos, config.foliageProvider.get(random, pos));
                }
            }
        }
    }

    private void placeHangingStrand(BlockPlacer placer, Random random,
                                    TreeFeatureConfig config, BlockPos start, int length) {
        BlockPos pos = start;
        for (int i = 0; i < length; i++) {
            placer.placeBlock(pos, config.foliageProvider.get(random, pos));
            pos = pos.down();
        }
    }

    @Override
    public int getRandomHeight(Random random, int trunkHeight, TreeFeatureConfig config) {
        return 3;
    }

    @Override
    protected boolean isInvalidForLeaves(Random random, int dx, int y, int dz, int radius, boolean giantTrunk) {
        return false;
    }
}
