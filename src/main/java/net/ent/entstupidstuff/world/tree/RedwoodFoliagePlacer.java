package net.ent.entstupidstuff.world.tree;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.ent.entstupidstuff.world.ModConfiguredFeatures;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;

public class RedwoodFoliagePlacer extends FoliagePlacer {
    public static final MapCodec<RedwoodFoliagePlacer> CODEC = RecordCodecBuilder.mapCodec(
        instance -> foliagePlacerParts(instance)
            .and(IntProvider.codec(0, 24).fieldOf("crown_height").forGetter(placer -> placer.crownHeight))
            .apply(instance, RedwoodFoliagePlacer::new)
    );

    private final IntProvider crownHeight;

    public RedwoodFoliagePlacer(IntProvider radius, IntProvider offset, IntProvider crownHeight) {
        super(radius, offset);
        this.crownHeight = crownHeight;
    }

    @Override
    protected FoliagePlacerType<?> type() {
        return ModConfiguredFeatures.REDWOOD_FOLIAGE_PLACER;
    }

   @Override
    protected void createFoliage(
        LevelSimulatedReader world,
        FoliageSetter placer,
        RandomSource random,
        TreeConfiguration config,
        int trunkHeight,
        FoliageAttachment treeNode,
        int foliageHeight,
        int radius,
        int offset
    ) {
        BlockPos center = treeNode.pos();

        // MAIN CROWN (top)
        int topHeight = foliageHeight; // say 4–6 blocks
        for (int y = 0; y < topHeight; y++) {
            int currentRadius = radius - y/2; // gradually shrink radius upward
            this.placeLeavesRow(world, placer, random, config, center.above(y), currentRadius, 0, treeNode.doubleTrunk());
        }

        // BRANCH NODES along upper trunk
        int branches = trunkHeight / 3; // 1 branch roughly every 3 blocks
        for (int i = 0; i < branches; i++) {
            int y = i * 3 + 2; // offset from base
            int branchRadius = Mth.clamp(radius - i - 1, 2, radius);
            BlockPos branchCenter = center.above(y);
            this.placeLeavesRow(world, placer, random, config, branchCenter, branchRadius, 0, treeNode.doubleTrunk());

            // optionally skip some leaf blocks for jagged natural look
        }
    }

    @Override
    public int foliageHeight(RandomSource random, int trunkHeight, TreeConfiguration config) {
        return this.crownHeight.sample(random);
    }

    @Override
    protected boolean shouldSkipLocation(RandomSource random, int dx, int y, int dz, int radius, boolean giantTrunk) {
        // jagged effect for edges
        if (dx == radius && dz == radius) {
            return random.nextFloat() < 0.5f;
        }
        return false;
    }
}


