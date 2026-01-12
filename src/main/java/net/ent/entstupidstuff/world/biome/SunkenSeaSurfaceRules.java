package net.ent.entstupidstuff.world.biome;

import net.ent.entstupidstuff.block.BlockFactory;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.VerticalAnchor;

public class SunkenSeaSurfaceRules {
    
    private static final SurfaceRules.RuleSource ABYSSAL_STONE = makeStateRule(BlockFactory.ABYSSAL_STONE);
    private static final SurfaceRules.RuleSource GRAVEL = makeStateRule(Blocks.GRAVEL);
    private static final SurfaceRules.RuleSource SAND = makeStateRule(Blocks.SAND);
    private static final SurfaceRules.RuleSource WATER = makeStateRule(Blocks.WATER);
    
    public static SurfaceRules.RuleSource makeRules() {
        // Condition: check if we're in the Sunken Sea biome
        SurfaceRules.ConditionSource isSunkenSea = SurfaceRules.isBiome(ModBiomes.SUNKEN_SEA);
        
        // Only apply below Y=0 with a smooth gradient
        SurfaceRules.ConditionSource isBelowY0 = SurfaceRules.verticalGradient(
            "sunken_sea_y0", 
            VerticalAnchor.absolute(-5),  // Gradient starts at -5
            VerticalAnchor.absolute(5)    // Gradient ends at 5 (creates smooth transition around Y=0)
        );
        
        // Water condition
        SurfaceRules.ConditionSource hasWater = SurfaceRules.waterBlockCheck(-1, 0);
        
        // Floor surface (sand on very top)
        SurfaceRules.RuleSource sandFloor = SurfaceRules.ifTrue(
            SurfaceRules.ON_FLOOR,
            SAND
        );
        
        // Gravel layer (just below sand, 1-6 blocks deep)
        SurfaceRules.RuleSource gravelLayer = SurfaceRules.ifTrue(
            SurfaceRules.DEEP_UNDER_FLOOR,
            GRAVEL
        );
        
        // Combine floor layers
        SurfaceRules.RuleSource floorLayers = SurfaceRules.sequence(
            sandFloor,
            gravelLayer
        );
        
        // Combine all rules
        return SurfaceRules.sequence(
            SurfaceRules.ifTrue(
                isSunkenSea,
                SurfaceRules.ifTrue(
                    isBelowY0,
                    SurfaceRules.sequence(
                        // Water first
                        SurfaceRules.ifTrue(hasWater, WATER),
                        // Floor layers (sand/gravel)
                        floorLayers,
                        // Everything else = abyssal stone
                        ABYSSAL_STONE
                    )
                )
            )
        );
    }
    
    private static SurfaceRules.RuleSource makeStateRule(Block block) {
        return SurfaceRules.state(block.defaultBlockState());
    }
}