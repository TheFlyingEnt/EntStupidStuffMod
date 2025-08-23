package net.ent.entstupidstuff.world.tree;

import java.util.List;
import java.util.function.BiConsumer;

import com.google.common.collect.ImmutableList;
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

public class ThreexThreeTrunkPlacer extends TrunkPlacer {
    public static final MapCodec<ThreexThreeTrunkPlacer> CODEC = RecordCodecBuilder.mapCodec(
		instance -> fillTrunkPlacerFields(instance).apply(instance, ThreexThreeTrunkPlacer::new)
	);

	public ThreexThreeTrunkPlacer(int i, int j, int k) {
		super(i, j, k);
	}

	@Override
	protected TrunkPlacerType<?> getType() {
		return ConfiguredFeaturesFactory.THREE_BY_THREE_TRUNK;
	}

	private void setLog(
		TestableWorld world,
		BiConsumer<BlockPos, BlockState> replacer,
		Random random,
		BlockPos.Mutable tmpPos,
		TreeFeatureConfig config,
		BlockPos startPos,
		int dx,
		int dy,
		int dz
	) {
		tmpPos.set(startPos, dx, dy, dz);
		this.trySetState(world, replacer, random, tmpPos, config);
	}

    @Override
    public List<FoliagePlacer.TreeNode> generate(
        TestableWorld world, BiConsumer<BlockPos, BlockState> replacer, Random random,
        int height, BlockPos startPos, TreeFeatureConfig config
    ) {
        BlockPos.Mutable mutable = new BlockPos.Mutable();

        // --- 1) Build jagged 3x3 trunk ---
        for (int y = 0; y < height; y++) {
            int remaining = height - y;

            if (remaining > height / 4) { // bottom 3/4 full 3×3
                for (int dx = 0; dx < 3; dx++)
                    for (int dz = 0; dz < 3; dz++)
                        this.setLog(world, replacer, random, mutable, config, startPos, dx, y, dz);
            } else if (remaining > height / 8) { // 3x3 minus corners
                for (int dx = 0; dx < 3; dx++)
                    for (int dz = 0; dz < 3; dz++)
                        if (!(dx==0 && dz==0) && !(dx==0 && dz==2)
                            && !(dx==2 && dz==0) && !(dx==2 && dz==2))
                            this.setLog(world, replacer, random, mutable, config, startPos, dx, y, dz);
            } else { // top 2x2 core
                for (int dx = 1; dx <= 2; dx++)
                    for (int dz = 1; dz <= 2; dz++)
                        this.setLog(world, replacer, random, mutable, config, startPos, dx-1, y, dz-1);
            }
        }

        // --- 2) Generate branches along upper trunk ---
        ImmutableList.Builder<FoliagePlacer.TreeNode> nodes = ImmutableList.builder();
        int minBranchHeight = height / 4;
        int maxBranchHeight = height - 2;
        int numBranches = Math.max(3, height / 3);

        for (int i = 0; i < numBranches; i++) {
            int y = minBranchHeight + random.nextInt(maxBranchHeight - minBranchHeight + 1);

            // Scale branch length with height (lower = shorter, upper = longer)
            float heightFactor = (float)y / height;
            int maxOffset = 3 + Math.round(2 * heightFactor); // 3–5 blocks horizontally
            int offsetX = (random.nextBoolean() ? 1 : -1) * (1 + random.nextInt(maxOffset));
            int offsetZ = (random.nextBoolean() ? 1 : -1) * (1 + random.nextInt(maxOffset));

            int dy = 1 + random.nextInt(3); // slight upward tilt
            BlockPos branchStart = startPos.add(1, y, 1); // trunk center
            BlockPos branchEnd = branchStart.add(offsetX, dy, offsetZ);

            // Place angled branch logs
            placeBranchLogs(world, replacer, random, mutable, config, branchStart, branchEnd);

            // Add foliage node at branch tip
            nodes.add(new FoliagePlacer.TreeNode(branchEnd, 0, false));
        }

        // --- 3) Top foliage node ---
        BlockPos topCenter = startPos.add(1, height, 1);
        nodes.add(new FoliagePlacer.TreeNode(topCenter, 0, true));

        return nodes.build();
    }

    // --- helper to place angled branch logs ---
    private void placeBranchLogs(
        TestableWorld world,
        BiConsumer<BlockPos, BlockState> replacer,
        Random random,
        BlockPos.Mutable mutable,
        TreeFeatureConfig config,
        BlockPos start,
        BlockPos end
    ) {
        int dx = end.getX() - start.getX();
        int dy = end.getY() - start.getY();
        int dz = end.getZ() - start.getZ();

        int steps = Math.max(1, Math.max(Math.abs(dx), Math.max(Math.abs(dy), Math.abs(dz))));

        for (int i = 0; i <= steps; i++) {
            int x = start.getX() + dx * i / steps;
            int y = start.getY() + dy * i / steps;
            int z = start.getZ() + dz * i / steps;
            mutable.set(x, y, z);
            this.trySetState(world, replacer, random, mutable, config);
        }
    }


}
