package net.ent.entstupidstuff.world.biome;


import net.ent.entstupidstuff.EntStupidStuff;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.SurfaceRules;
import terrablender.api.Regions;
import terrablender.api.SurfaceRuleManager;
import terrablender.api.TerraBlenderApi;

public class ModTerrablenderAPI implements TerraBlenderApi {
    @Override
    public void onTerraBlenderInitialized() {
        Regions.register(new ModOverworldRegion(ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "overworld"), 4));

        /*SurfaceRuleManager.addSurfaceRules(
            SurfaceRuleManager.RuleCategory.OVERWORLD,
            EntStupidStuff.MOD_ID,
            UndergroundBlueMushroomSurfaceRules.makeRules()
        );*/

        /*SurfaceRuleManager.addSurfaceRules(
            SurfaceRuleManager.RuleCategory.OVERWORLD,
            EntStupidStuff.MOD_ID,
            SurfaceRules.ifTrue(
                SurfaceRules.isBiome(ModBiomes.UNDERGROUND_BLUE_MUSHROOM),
                UndergroundBlueMushroomSurfaceRules.makeRules()
            )
        );*/
    }
}
