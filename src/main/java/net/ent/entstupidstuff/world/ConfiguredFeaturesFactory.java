package net.ent.entstupidstuff.world;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.block.BlockFactoryUpt;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.JungleFoliagePlacer;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.trunk.GiantTrunkPlacer;

public class ConfiguredFeaturesFactory {

    public static final RegistryKey<ConfiguredFeature<?, ?>> MAPLE_KEY = registerKey("maple");

    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> context) {

        /*register(context, MAPLE_KEY, Feature.TREE, new TreeFeatureConfig.Builder(
            BlockStateProvider.of(BlockFactoryUpt.callBlock("maple_log")),
            new StraightTrunkPlacer(5, 4, 3),

            BlockStateProvider.of(BlockFactoryUpt.callBlock("maple_leaves")),
            new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(1), 2),

            new TwoLayersFeatureSize(1, 0, 2)).build()
        );*/

        /*register(context, MAPLE_KEY, Feature.TREE, new TreeFeatureConfig.Builder(
            BlockStateProvider.of(BlockFactoryUpt.callBlock("maple_log")),
            new LargeOakTrunkPlacer(3, 11, 0),
    
            BlockStateProvider.of(BlockFactoryUpt.callBlock("maple_leaves")),
            new LargeOakFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(4), 4),
    
            new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(4))).build()
        );*/

        register(context, MAPLE_KEY, Feature.TREE, new TreeFeatureConfig.Builder(
            BlockStateProvider.of(BlockFactoryUpt.callBlock("maple_log")),
            new GiantTrunkPlacer(10, 2, 19),
    
            BlockStateProvider.of(BlockFactoryUpt.callBlock("maple_leaves")),
            new JungleFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 2),
    
            new TwoLayersFeatureSize(1, 1, 2)).build()
        );

    }

    public static RegistryKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, Identifier.of(EntStupidStuff.MOD_ID, name));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<ConfiguredFeature<?, ?>> context, RegistryKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }


}
