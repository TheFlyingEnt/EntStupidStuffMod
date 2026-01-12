package net.ent.entstupidstuff.world.biome;


import net.ent.entstupidstuff.EntStupidStuff;
import net.minecraft.util.Identifier;
import terrablender.api.Regions;
import terrablender.api.TerraBlenderApi;

public class ModTerrablenderAPI implements TerraBlenderApi {
    @Override
    public void onTerraBlenderInitialized() {
        Regions.register(new ModOverworldRegion(Identifier.of(EntStupidStuff.MOD_ID, "overworld"), 4));
        //Regions.register(new SunkenSeaRegion());

        /*SurfaceRuleManager.addSurfaceRules(
            SurfaceRuleManager.RuleCategory.OVERWORLD,
            EntStupidStuff.MOD_ID,
            SunkenSeaSurfaceRules.makeRules()  // ← CALLED HERE
        );*/
    }

    
}
