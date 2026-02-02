package net.ent.entstupidstuff.world.biome;

import net.ent.entstupidstuff.block.BlockFactory;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.SurfaceRules;

public class UndergroundBlueMushroomSurfaceRules {

    private static final SurfaceRules.RuleSource SHROOMIUM = SurfaceRules.state(BlockFactory.SHROOMIUM_BLOCK.defaultBlockState());
    private static final SurfaceRules.RuleSource MUD = SurfaceRules.state(Blocks.MUD.defaultBlockState());

    public static SurfaceRules.RuleSource makeRules() {

        return SurfaceRules.sequence(
            // Top floor
            SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, SHROOMIUM),
            SurfaceRules.ifTrue(SurfaceRules.DEEP_UNDER_FLOOR, MUD)
            // Layer 1 under surface
            //SurfaceRules.ifTrue(SurfaceRules.stoneDepthCheck(1, false, CaveSurface.FLOOR), mud),
            // Layer 2 under surface
            //SurfaceRules.ifTrue(SurfaceRules.stoneDepthCheck(2, false, CaveSurface.FLOOR), mud)
        );
    }
}
