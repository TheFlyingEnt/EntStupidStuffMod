package net.ent.entstupidstuff.world.biome;

import net.ent.entstupidstuff.block.BlockFactory;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.surfacebuilder.MaterialRules;

public class SunkenSeaSurfaceRules {
    
    private static final MaterialRules.MaterialRule ABYSSAL_STONE = makeStateRule(BlockFactory.ABYSSAL_STONE);
    private static final MaterialRules.MaterialRule GRAVEL = makeStateRule(Blocks.GRAVEL);
    private static final MaterialRules.MaterialRule SAND = makeStateRule(Blocks.SAND);
    private static final MaterialRules.MaterialRule WATER = makeStateRule(Blocks.WATER);
    
    public static MaterialRules.MaterialRule makeRules() {
        // Condition: check if we're in the Sunken Sea biome
        MaterialRules.MaterialCondition isSunkenSea = MaterialRules.biome(ModBiomes.SUNKEN_SEA);
        
        // Only apply below Y=0 with a smooth gradient
        MaterialRules.MaterialCondition isBelowY0 = MaterialRules.verticalGradient(
            "sunken_sea_y0", 
            YOffset.fixed(-5),  // Gradient starts at -5
            YOffset.fixed(5)    // Gradient ends at 5 (creates smooth transition around Y=0)
        );
        
        // Water condition
        MaterialRules.MaterialCondition hasWater = MaterialRules.water(-1, 0);
        
        // Floor surface (sand on very top)
        MaterialRules.MaterialRule sandFloor = MaterialRules.condition(
            MaterialRules.STONE_DEPTH_FLOOR,
            SAND
        );
        
        // Gravel layer (just below sand, 1-6 blocks deep)
        MaterialRules.MaterialRule gravelLayer = MaterialRules.condition(
            MaterialRules.STONE_DEPTH_FLOOR_WITH_SURFACE_DEPTH_RANGE_6,
            GRAVEL
        );
        
        // Combine floor layers
        MaterialRules.MaterialRule floorLayers = MaterialRules.sequence(
            sandFloor,
            gravelLayer
        );
        
        // Combine all rules
        return MaterialRules.sequence(
            MaterialRules.condition(
                isSunkenSea,
                MaterialRules.condition(
                    isBelowY0,
                    MaterialRules.sequence(
                        // Water first
                        MaterialRules.condition(hasWater, WATER),
                        // Floor layers (sand/gravel)
                        floorLayers,
                        // Everything else = abyssal stone
                        ABYSSAL_STONE
                    )
                )
            )
        );
    }
    
    private static MaterialRules.MaterialRule makeStateRule(Block block) {
        return MaterialRules.block(block.getDefaultState());
    }
}