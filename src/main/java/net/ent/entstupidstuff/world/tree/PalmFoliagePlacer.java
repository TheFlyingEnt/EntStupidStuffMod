package net.ent.entstupidstuff.world.tree;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.ent.entstupidstuff.world.ModConfiguredFeatures;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.foliage.FoliagePlacerType;

public class PalmFoliagePlacer extends FoliagePlacer {

    public static final MapCodec<PalmFoliagePlacer> CODEC = RecordCodecBuilder.mapCodec(
        instance -> fillFoliagePlacerFields(instance)
            .apply(instance, PalmFoliagePlacer::new)
    );

    /*public static final Codec<PalmFoliagePlacer> CODEC =
            RecordCodecBuilder.create(instance -> fillFoliagePlacerFields(instance)
                    .apply(instance, PalmFoliagePlacer::new));*/

    public PalmFoliagePlacer(IntProvider radius, IntProvider offset) {
        super(radius, offset);
    }

    @Override
    protected FoliagePlacerType<?> getType() {
        return ModConfiguredFeatures.PALM_FOLIAGE_PLACE;
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

        // Central crown
        placer.placeBlock(center, config.foliageProvider.get(random, center));

        // Palm fronds
        for (Direction dir : Direction.Type.HORIZONTAL) {
            for (int i = 1; i <= 4; i++) {
                BlockPos pos = center
                        .offset(dir, i)
                        .down(i > 2 ? 1 : 0); // slight droop

                placer.placeBlock(pos, config.foliageProvider.get(random, pos));
            }
        }

        // Diagonal fronds
        placeDiagonal(placer, random, config, center, 1, 1);
        placeDiagonal(placer, random, config, center, -1, 1);
        placeDiagonal(placer, random, config, center, 1, -1);
        placeDiagonal(placer, random, config, center, -1, -1);
    }

    private void placeDiagonal(BlockPlacer placer, Random random, TreeFeatureConfig config,
                               BlockPos center, int xDir, int zDir) {
        for (int i = 1; i <= 3; i++) {
            BlockPos pos = center.add(xDir * i, i > 2 ? -1 : 0, zDir * i);
            placer.placeBlock(pos, config.foliageProvider.get(random, pos));
        }
    }

    @Override
    public int getRandomHeight(Random random, int trunkHeight, TreeFeatureConfig config) {
        return 1;
    }

    @Override
    protected boolean isInvalidForLeaves(Random random, int dx, int y, int dz, int radius, boolean giantTrunk) {
        return false;
    }
}
