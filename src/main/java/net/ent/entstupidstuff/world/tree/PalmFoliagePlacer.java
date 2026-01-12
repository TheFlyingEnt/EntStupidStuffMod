package net.ent.entstupidstuff.world.tree;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.ent.entstupidstuff.world.ModConfiguredFeatures;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;

public class PalmFoliagePlacer extends FoliagePlacer {

    public static final MapCodec<PalmFoliagePlacer> CODEC = RecordCodecBuilder.mapCodec(
        instance -> foliagePlacerParts(instance)
            .apply(instance, PalmFoliagePlacer::new)
    );

    /*public static final Codec<PalmFoliagePlacer> CODEC =
            RecordCodecBuilder.create(instance -> fillFoliagePlacerFields(instance)
                    .apply(instance, PalmFoliagePlacer::new));*/

    public PalmFoliagePlacer(IntProvider radius, IntProvider offset) {
        super(radius, offset);
    }

    @Override
    protected FoliagePlacerType<?> type() {
        return ModConfiguredFeatures.PALM_FOLIAGE_PLACE;
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

        // Central crown
        placer.set(center, config.foliageProvider.getState(random, center));

        // Palm fronds
        for (Direction dir : Direction.Plane.HORIZONTAL) {
            for (int i = 1; i <= 4; i++) {
                BlockPos pos = center
                        .relative(dir, i)
                        .below(i > 2 ? 1 : 0); // slight droop

                placer.set(pos, config.foliageProvider.getState(random, pos));
            }
        }

        // Diagonal fronds
        placeDiagonal(placer, random, config, center, 1, 1);
        placeDiagonal(placer, random, config, center, -1, 1);
        placeDiagonal(placer, random, config, center, 1, -1);
        placeDiagonal(placer, random, config, center, -1, -1);
    }

    private void placeDiagonal(FoliageSetter placer, RandomSource random, TreeConfiguration config,
                               BlockPos center, int xDir, int zDir) {
        for (int i = 1; i <= 3; i++) {
            BlockPos pos = center.offset(xDir * i, i > 2 ? -1 : 0, zDir * i);
            placer.set(pos, config.foliageProvider.getState(random, pos));
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
