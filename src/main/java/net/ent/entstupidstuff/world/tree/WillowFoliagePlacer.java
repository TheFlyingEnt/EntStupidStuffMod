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

public class WillowFoliagePlacer extends FoliagePlacer {

    public static final MapCodec<WillowFoliagePlacer> CODEC = RecordCodecBuilder.mapCodec(
        instance -> foliagePlacerParts(instance)
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
    protected FoliagePlacerType<?> type() {
        return ModConfiguredFeatures.WILLOW_FOLIAGE_PLACE;
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

        // ── MAIN CANOPY (dense blob)
        for (int y = 0; y >= -2; y--) {
            placeSolidCircle(placer, random, config, center.below(-y), 3 - y);
        }

        // ── HANGING LEAVES (curtains)
        for (int x = -3; x <= 3; x++) {
            for (int z = -3; z <= 3; z++) {
                if (x * x + z * z <= 9 && random.nextFloat() < 0.75f) {
                    BlockPos start = center.offset(x, -3, z);
                    placeHangingStrand(placer, random, config, start, random.nextInt(4) + 3);
                }
            }
        }
    }

    private void placeSolidCircle(FoliageSetter placer, RandomSource random,
                                  TreeConfiguration config, BlockPos center, int r) {
        for (int x = -r; x <= r; x++) {
            for (int z = -r; z <= r; z++) {
                if (x * x + z * z <= r * r) {
                    BlockPos pos = center.offset(x, 0, z);
                    placer.set(pos, config.foliageProvider.getState(random, pos));
                }
            }
        }
    }

    private void placeHangingStrand(FoliageSetter placer, RandomSource random,
                                    TreeConfiguration config, BlockPos start, int length) {
        BlockPos pos = start;
        for (int i = 0; i < length; i++) {
            placer.set(pos, config.foliageProvider.getState(random, pos));
            pos = pos.below();
        }
    }

    @Override
    public int foliageHeight(RandomSource random, int trunkHeight, TreeConfiguration config) {
        return 3;
    }

    @Override
    protected boolean shouldSkipLocation(RandomSource random, int dx, int y, int dz, int radius, boolean giantTrunk) {
        return false;
    }
}
