package net.ent.entstupidstuff.world.tree;

import java.util.Optional;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.world.ConfiguredFeaturesFactory;
import net.minecraft.block.SaplingGenerator;

public class SaplingGeneratorFactory {

    public static final SaplingGenerator MAPLE = new SaplingGenerator(EntStupidStuff.MOD_ID + ":maple", Optional.empty(), Optional.of(ConfiguredFeaturesFactory.MAPLE_KEY), Optional.empty());
    public static final SaplingGenerator FIR = new SaplingGenerator(EntStupidStuff.MOD_ID + ":fir", Optional.empty(), Optional.of(ConfiguredFeaturesFactory.FIR_KEY), Optional.empty());
    
}
