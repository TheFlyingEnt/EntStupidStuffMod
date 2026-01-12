package net.ent.entstupidstuff.world.tree;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.ent.entstupidstuff.world.ModConfiguredFeatures;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;

public class UmbrellaPalmFoliagePlacer extends FoliagePlacer {

    /*public static final Codec<UmbrellaPalmFoliagePlacer> CODEC =
            RecordCodecBuilder.create(instance ->
                    fillFoliagePlacerFields(instance)
                            .apply(instance, UmbrellaPalmFoliagePlacer::new)
            );*/

    public static final MapCodec<UmbrellaPalmFoliagePlacer> CODEC = RecordCodecBuilder.mapCodec(
        instance -> foliagePlacerParts(instance)
            .apply(instance, UmbrellaPalmFoliagePlacer::new)
    );

    public UmbrellaPalmFoliagePlacer(IntProvider radius, IntProvider offset) {
        super(radius, offset);
    }

    @Override
    protected FoliagePlacerType<?> type() {
        return ModConfiguredFeatures.UMBRELLA_PALM_FOLIAGE_PLACE;
    }

    @Override
    protected void createFoliage(
            LevelSimulatedReader world,
            FoliageSetter placer,
            RandomSource random,
            TreeConfiguration config,
            int trunkHeight,
            FoliageAttachment node,
            int foliageHeight,
            int radius,
            int offset
    ) {
        BlockPos center = node.pos();

        // ── TOP LAYER (flat disk)
        placeCircle(placer, random, config, center, 2, 0);

        // ── MAIN CANOPY (thick ring)
        placeRing(placer, random, config, center.below(), 3);

        // ── DROOPING EDGE
        placeRing(placer, random, config, center.below(2), 4);
    
        placeInnerConeSupports(placer, random, config, center);


    }

    private void placeInnerConeSupports(FoliageSetter placer, RandomSource random, TreeConfiguration config, BlockPos center) {

        int y = -2; // bottom of cone

        int[][] innerCorners = {
                { 2, y,  2},
                { 2, y, -2},
                {-2, y,  2},
                {-2, y, -2}
        };

        for (int[] c : innerCorners) {
            BlockPos pos = center.offset(c[0], c[1], c[2]);

            // Primary connector
            placer.set(pos, config.foliageProvider.getState(random, pos));

            // Vertical link upward to main ring
            placer.set(pos.above(), config.foliageProvider.getState(random, pos));
        }
    }


    private void placeCircle(FoliageSetter placer, RandomSource random,
                              TreeConfiguration config, BlockPos center, int r, int y) {
        for (int x = -r; x <= r; x++) {
            for (int z = -r; z <= r; z++) {
                if (x * x + z * z <= r * r) {
                    BlockPos pos = center.offset(x, y, z);
                    placer.set(pos, config.foliageProvider.getState(random, pos));
                }
            }
        }
    }

    private void placeRing(FoliageSetter placer, RandomSource random,
                            TreeConfiguration config, BlockPos center, int r) {
        for (int x = -r; x <= r; x++) {
            for (int z = -r; z <= r; z++) {
                int dist = x * x + z * z;
                if (dist <= r * r && dist >= (r - 1) * (r - 1)) {
                    BlockPos pos = center.offset(x, 0, z);
                    placer.set(pos, config.foliageProvider.getState(random, pos));
                }
            }
        }
    }

    @Override
    public int foliageHeight(RandomSource random, int trunkHeight, TreeConfiguration config) {
        return 1;
    }

    @Override
    protected boolean shouldSkipLocation(RandomSource random, int dx, int y, int dz, int radius, boolean giantTrunk) {
        return false;
    }
}

