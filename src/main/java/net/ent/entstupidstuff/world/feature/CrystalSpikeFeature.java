package net.ent.entstupidstuff.world.feature;

import com.mojang.serialization.Codec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

public class CrystalSpikeFeature extends Feature<CrystalSpikeFeatureConfig> {

    public CrystalSpikeFeature(Codec<CrystalSpikeFeatureConfig> codec) {
        super(codec);
    }

    @Override
    public boolean generate(FeatureContext<CrystalSpikeFeatureConfig> context) {
        StructureWorldAccess world = context.getWorld();
        BlockPos origin = context.getOrigin();
        Random random = context.getRandom();
        CrystalSpikeFeatureConfig config = context.getConfig();

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
            direction = Direction.Type.HORIZONTAL.random(random);
        }

        int length = config.length.get(random);
        int baseRadius = config.baseRadius.get(random);
        float taperChance = config.taperChance.get(random);

        BlockPos.Mutable mutable = startPos.mutableCopy();
        int placedBlocks = 0;

        for (int step = 0; step < length; step++) {
            if (world.isOutOfHeightLimit(mutable)) break;

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
        StructureWorldAccess world,
        BlockPos origin,
        CrystalSpikeFeatureConfig config,
        Random random
    ) {
        // First try the origin
        if (world.getBlockState(origin).isIn(config.replaceable)) {
            return origin;
        }

        // Try nearby positions
        for (int attempt = 0; attempt < 8; attempt++) {
            BlockPos offset = origin.add(
                random.nextInt(7) - 3,
                random.nextInt(7) - 3,
                random.nextInt(7) - 3
            );

            if (world.getBlockState(offset).isIn(config.replaceable)) {
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
        StructureWorldAccess world,
        BlockPos center,
        int radius,
        BlockState crystal,
        CrystalSpikeFeatureConfig config
    ) {
        BlockPos.Mutable pos = new BlockPos.Mutable();
        int placed = 0;

        for (int x = -radius; x <= radius; x++) {
            for (int y = -radius; y <= radius; y++) {
                for (int z = -radius; z <= radius; z++) {

                    if (x * x + y * y + z * z > radius * radius) continue;

                    pos.set(center.getX() + x, center.getY() + y, center.getZ() + z);

                    if (world.isOutOfHeightLimit(pos)) continue;

                    BlockState state = world.getBlockState(pos);

                    // Place if replaceable OR if it's air (for cave generation)
                    if (state.isIn(config.replaceable) || state.isAir()) {
                        world.setBlockState(pos, crystal, Block.NOTIFY_ALL);
                        placed++;
                    }
                }
            }
        }

        return placed;
    }
}