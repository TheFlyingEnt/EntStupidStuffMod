package net.ent.entstupidstuff.world.feature;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;

public class CrystalSpikeFeature extends Feature<CrystalSpikeFeatureConfig> {

    public CrystalSpikeFeature(Codec<CrystalSpikeFeatureConfig> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<CrystalSpikeFeatureConfig> context) {
        WorldGenLevel world = context.level();
        BlockPos origin = context.origin();
        RandomSource random = context.random();
        CrystalSpikeFeatureConfig config = context.config();

        // Try to find a suitable starting position nearby if current isn't valid
        BlockPos startPos = findValidStartPos(world, origin, config, random);
        if (startPos == null) {
            return false;
        }

        // Pick directions - prioritize upward/downward for cave generation
        Direction direction;
        float dirRoll = random.nextFloat();
        if (dirRoll < 0.4f) {
            direction = Direction.UP;
        } else if (dirRoll < 0.8f) {
            direction = Direction.DOWN;
        } else {
            // Occasionally horizontal
            direction = Direction.Plane.HORIZONTAL.getRandomDirection(random);
        }

        int length = config.length.sample(random);
        int baseRadius = config.baseRadius.sample(random);
        float taperChance = config.taperChance.sample(random);

        BlockPos.MutableBlockPos mutable = startPos.mutable();
        int placedBlocks = 0;

        for (int step = 0; step < length; step++) {
            if (world.isOutsideBuildHeight(mutable)) break;

            int radius = Math.max(0, baseRadius - step / 3);
            int placed = placeDisk(world, mutable, radius, config.crystalBlock, config);
            placedBlocks += placed;

            // Chance to taper / stop early for natural look
            if (random.nextFloat() < taperChance && step > length / 3) {
                break;
            }

            mutable.move(direction);
        }

        // Return true if we placed at least some blocks
        return placedBlocks > 0;
    }

    /**
     * Tries to find a valid starting position near the origin
     */
    private BlockPos findValidStartPos(
        WorldGenLevel world,
        BlockPos origin,
        CrystalSpikeFeatureConfig config,
        RandomSource random
    ) {
        // First try the origin
        if (world.getBlockState(origin).is(config.replaceable)) {
            return origin;
        }

        // Try nearby positions
        for (int attempt = 0; attempt < 8; attempt++) {
            BlockPos offset = origin.offset(
                random.nextInt(7) - 3,
                random.nextInt(7) - 3,
                random.nextInt(7) - 3
            );

            if (world.getBlockState(offset).is(config.replaceable)) {
                return offset;
            }
        }

        return null;
    }

    /**
     * Places a small disk of crystal blocks around the spike core
     * Returns the number of blocks placed
     */
    private int placeDisk(
        WorldGenLevel world,
        BlockPos center,
        int radius,
        BlockState crystal,
        CrystalSpikeFeatureConfig config
    ) {
        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();
        int placed = 0;

        for (int x = -radius; x <= radius; x++) {
            for (int y = -radius; y <= radius; y++) {
                for (int z = -radius; z <= radius; z++) {

                    if (x * x + y * y + z * z > radius * radius) continue;

                    pos.set(center.getX() + x, center.getY() + y, center.getZ() + z);

                    if (world.isOutsideBuildHeight(pos)) continue;

                    BlockState state = world.getBlockState(pos);

                    // Place if replaceable OR if it's air (for cave generation)
                    if (state.is(config.replaceable) || state.isAir()) {
                        world.setBlock(pos, crystal, Block.UPDATE_ALL);
                        placed++;
                    }
                }
            }
        }

        return placed;
    }
}