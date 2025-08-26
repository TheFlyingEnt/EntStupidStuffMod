package net.ent.entstupidstuff.datagen;

import net.ent.entstupidstuff.block.BlockFactoryUpt;
import net.ent.entstupidstuff.world.ConfiguredFeaturesFactory;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.PlacedFeatures;
import net.minecraft.world.gen.feature.VegetationPlacedFeatures;

public class ModPlacedFeatures {
    public static final RegistryKey<PlacedFeature> MAPLE_TREE_PLACED_KEY = RegistryKey.of(
            RegistryKeys.PLACED_FEATURE,
            Identifier.of("entstupidstuff", "maple_tree_placed")
    );

    public static void bootstrap(Registerable<PlacedFeature> context) {
        context.register(MAPLE_TREE_PLACED_KEY, new PlacedFeature(
                context.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE).getOrThrow(ConfiguredFeaturesFactory.MAPLE_KEY),
                VegetationPlacedFeatures.treeModifiersWithWouldSurvive(
                        PlacedFeatures.createCountExtraModifier(5, 0.1f, 1), // base count, chance, extra
                        BlockFactoryUpt.callBlock("maple_sapling")
                )
        ));
    }
}
