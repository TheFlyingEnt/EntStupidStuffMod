package net.ent.entstupidstuff.world.tree;

import java.util.Optional;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.world.ModConfiguredFeatures;
import net.minecraft.world.level.block.grower.TreeGrower;

public class SaplingGeneratorFactory {

    public static final TreeGrower MAPLE = new TreeGrower(EntStupidStuff.MOD_ID + ":maple", Optional.empty(), Optional.of(ModConfiguredFeatures.MAPLE_TREE_KEY), Optional.of(ModConfiguredFeatures.MAPLE_FANCY_TREE_KEY));
    public static final TreeGrower FIR = new TreeGrower(EntStupidStuff.MOD_ID + ":fir", Optional.empty(), Optional.of(ModConfiguredFeatures.FIR_TREE_KEY), Optional.empty());
    
}
