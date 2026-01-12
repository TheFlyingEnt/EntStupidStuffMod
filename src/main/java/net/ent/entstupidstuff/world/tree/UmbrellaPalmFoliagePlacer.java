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

public class UmbrellaPalmFoliagePlacer extends FoliagePlacer {

    /*public static final Codec<UmbrellaPalmFoliagePlacer> CODEC =
            RecordCodecBuilder.create(instance ->
                    fillFoliagePlacerFields(instance)
                            .apply(instance, UmbrellaPalmFoliagePlacer::new)
            );*/

    public static final MapCodec<UmbrellaPalmFoliagePlacer> CODEC = RecordCodecBuilder.mapCodec(
        instance -> fillFoliagePlacerFields(instance)
            .apply(instance, UmbrellaPalmFoliagePlacer::new)
    );

    public UmbrellaPalmFoliagePlacer(IntProvider radius, IntProvider offset) {
        super(radius, offset);
    }

    @Override
    protected FoliagePlacerType<?> getType() {
        return ModConfiguredFeatures.UMBRELLA_PALM_FOLIAGE_PLACE;
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

        // ── TOP LAYER (flat disk)
        placeCircle(placer, random, config, center, 2, 0);

        // ── MAIN CANOPY (thick ring)
        placeRing(placer, random, config, center.down(), 3);

        // ── DROOPING EDGE
        placeRing(placer, random, config, center.down(2), 4);
    
        placeInnerConeSupports(placer, random, config, center);


    }

    private void placeInnerConeSupports(BlockPlacer placer, Random random, TreeFeatureConfig config, BlockPos center) {

        int y = -2; // bottom of cone

        int[][] innerCorners = {
                { 2, y,  2},
                { 2, y, -2},
                {-2, y,  2},
                {-2, y, -2}
        };

        for (int[] c : innerCorners) {
            BlockPos pos = center.add(c[0], c[1], c[2]);

            // Primary connector
            placer.placeBlock(pos, config.foliageProvider.get(random, pos));

            // Vertical link upward to main ring
            placer.placeBlock(pos.up(), config.foliageProvider.get(random, pos));
        }
    }


    private void placeCircle(BlockPlacer placer, Random random,
                              TreeFeatureConfig config, BlockPos center, int r, int y) {
        for (int x = -r; x <= r; x++) {
            for (int z = -r; z <= r; z++) {
                if (x * x + z * z <= r * r) {
                    BlockPos pos = center.add(x, y, z);
                    placer.placeBlock(pos, config.foliageProvider.get(random, pos));
                }
            }
        }
    }

    private void placeRing(BlockPlacer placer, Random random,
                            TreeFeatureConfig config, BlockPos center, int r) {
        for (int x = -r; x <= r; x++) {
            for (int z = -r; z <= r; z++) {
                int dist = x * x + z * z;
                if (dist <= r * r && dist >= (r - 1) * (r - 1)) {
                    BlockPos pos = center.add(x, 0, z);
                    placer.placeBlock(pos, config.foliageProvider.get(random, pos));
                }
            }
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

