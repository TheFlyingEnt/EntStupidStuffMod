package net.ent.entstupidstuff.world.tree;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.ent.entstupidstuff.world.ConfiguredFeaturesFactory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.foliage.FoliagePlacerType;

public class FirFoliagePlacer  extends FoliagePlacer {
    public static final MapCodec<FirFoliagePlacer> CODEC = RecordCodecBuilder.mapCodec(
        instance -> fillFoliagePlacerFields(instance)
            .apply(instance, FirFoliagePlacer::new)
    );

    public FirFoliagePlacer(IntProvider radius, IntProvider offset) {
        super(radius, offset);
    }

    @Override
    protected FoliagePlacerType<?> getType() {
        return ConfiguredFeaturesFactory.FIR_FOLIAGE_PLACER;
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

        BlockPos blockPos = treeNode.getCenter();
		int i = random.nextInt(2);
		int j = 1;
		int k = 0;

        // Go upward from start to top
        for (int l = offset; l >= -foliageHeight; l--) {
            int layer = l ; // which layer we’re on
            int layerRadius;

            // Example taper rules (match your screenshot):
            if (layer == 0) layerRadius = 3;   // bottom wide
            else if (layer <= 2) layerRadius = 2;
            else if (layer <= 4) layerRadius = 1;
            else layerRadius = 0; // very top spike

            // Place leaves in a square radius, skipping corners for roundness
            for (int dx = -layerRadius; dx <= layerRadius; dx++) {
                for (int dz = -layerRadius; dz <= layerRadius; dz++) {
                    if (Math.abs(dx) + Math.abs(dz) > layerRadius) continue; // diamond shape
                    BlockPos leafPos = blockPos.add(dx, l, dz);

                    // Don't overwrite trunk
                    if (!leafPos.equals(blockPos.add(0, l, 0))) {
                        placeFoliageBlock(world, placer, random, config, leafPos);
                    }
                }
            }
        }

        /*BlockPos blockPos = node.getCenter();
        int i = random.nextInt(2);

        int[][] leafOffsets = new int[][] {
            {-3, 0}, {3, 0}, {0, -3}, {0, 3}, // edges (cross)
            {-1, -1}, {-1, 1}, {1, -1}, {1, 1} // diagonals near log
        };

        
        BlockPos center = node.getCenter();

        // Cone shape like your .schem
        for (int y = 0; y < foliageHeight; y++) {
            int layerRadius = Math.max(1, radius - y / 2);
            for (int dx = -layerRadius; dx <= layerRadius; dx++) {
                for (int dz = -layerRadius; dz <= layerRadius; dz++) {
                    if (Math.abs(dx) + Math.abs(dz) <= layerRadius) {
                        BlockPos leafPos = center.add(dx, -y, dz);
                        placeFoliageBlock(world, placer, random, config, leafPos);
                    }
                }
            }
        }*/




        /*BlockPos pos = node.getCenter();

        // Fixed base layer: leaves start 3 blocks above trunk
        int foliageBase = -8;

        for (int y = foliageBase; y <= trunkHeight; y++) {
            int relY = y - foliageBase;

            // Dynamic radius shrink with height → conical shape
            int layerRadius = Math.max(1, radius - (relY / 2));

            // Slight random shrink for natural look
            if (random.nextFloat() < 0.2f) layerRadius = Math.max(1, layerRadius - 1);

            // Skip every 4th layer for gaps
            if (relY % 4 == 0 && relY != 0) continue;

            placeLeavesLayer(world, placer, random, config, pos.up(y), layerRadius);
        }*/
    }

    private void placeLeavesLayer(
            TestableWorld world,
            BlockPlacer placer,
            Random random,
            TreeFeatureConfig config,
            BlockPos center,
            int radius
    ) {
        for (int dx = -radius; dx <= radius; dx++) {
            for (int dz = -radius; dz <= radius; dz++) {
                // Diamond shape + small random gaps
                if (!isInvalidForLeaves(random, dx, 0, dz, radius, false)) {
                    if (random.nextFloat() > 0.1f) { // 10% chance to leave a gap
                        BlockPos leafPos = center.add(dx, 0, dz);
                        placeFoliageBlock(world, placer, random, config, leafPos);
                    }
                }
            }
        }
    }

    @Override
    protected boolean isInvalidForLeaves(
            Random random,
            int dx,
            int y,
            int dz,
            int radius,
            boolean giantTrunk
    ) {
        // Diamond/circular shape foliage
        return Math.abs(dx) + Math.abs(dz) > radius;
    }

    @Override
    public int getRandomHeight(Random random, int trunkHeight, TreeFeatureConfig config) {
        // foliage height scales with trunk dynamically
        return trunkHeight - 2 - random.nextInt(3);
    }
}
