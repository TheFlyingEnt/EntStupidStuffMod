package net.ent.entstupidstuff.world.tree;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.ent.entstupidstuff.world.ConfiguredFeaturesFactory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.foliage.FoliagePlacerType;

public class RedwoodFoliagePlacer extends FoliagePlacer {
    public static final MapCodec<RedwoodFoliagePlacer> CODEC = RecordCodecBuilder.mapCodec(
        instance -> fillFoliagePlacerFields(instance)
            .and(IntProvider.createValidatingCodec(0, 24).fieldOf("crown_height").forGetter(placer -> placer.crownHeight))
            .apply(instance, RedwoodFoliagePlacer::new)
    );

    private final IntProvider crownHeight;

    public RedwoodFoliagePlacer(IntProvider radius, IntProvider offset, IntProvider crownHeight) {
        super(radius, offset);
        this.crownHeight = crownHeight;
    }

    @Override
    protected FoliagePlacerType<?> getType() {
        return ConfiguredFeaturesFactory.REDWOOD_FOLIAGE_PLACER;
    }

   @Override
    protected void generate(
        TestableWorld world,
        BlockPlacer placer,
        Random random,
        TreeFeatureConfig config,
        int trunkHeight,
        TreeNode treeNode,
        int foliageHeight,
        int radius,
        int offset
    ) {
        BlockPos center = treeNode.getCenter();

        // MAIN CROWN (top)
        int topHeight = foliageHeight; // say 4–6 blocks
        for (int y = 0; y < topHeight; y++) {
            int currentRadius = radius - y/2; // gradually shrink radius upward
            this.generateSquare(world, placer, random, config, center.up(y), currentRadius, 0, treeNode.isGiantTrunk());
        }

        // BRANCH NODES along upper trunk
        int branches = trunkHeight / 3; // 1 branch roughly every 3 blocks
        for (int i = 0; i < branches; i++) {
            int y = i * 3 + 2; // offset from base
            int branchRadius = MathHelper.clamp(radius - i - 1, 2, radius);
            BlockPos branchCenter = center.up(y);
            this.generateSquare(world, placer, random, config, branchCenter, branchRadius, 0, treeNode.isGiantTrunk());

            // optionally skip some leaf blocks for jagged natural look
        }
    }

    @Override
    public int getRandomHeight(Random random, int trunkHeight, TreeFeatureConfig config) {
        return this.crownHeight.get(random);
    }

    @Override
    protected boolean isInvalidForLeaves(Random random, int dx, int y, int dz, int radius, boolean giantTrunk) {
        // jagged effect for edges
        if (dx == radius && dz == radius) {
            return random.nextFloat() < 0.5f;
        }
        return false;
    }
}


