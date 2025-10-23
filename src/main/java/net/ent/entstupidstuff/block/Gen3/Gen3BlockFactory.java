package net.ent.entstupidstuff.block.Gen3;

import java.util.function.Function;

import net.ent.entstupidstuff.EntStupidStuff;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class Gen3BlockFactory {
    /*
    * Block Factory Designed for 1.21.10
    */

    public void onInitialize() {

        //Wood Type Registeration:
        Block REDWOOD_PLANKS = register("redwood" + "_planks" + "", properties -> new Block(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS).mapColor((MapColor.DULL_RED))));

    }

    // -------------------------
    //   Updated Registration
    // -------------------------

    private static Block register(String name, Function<AbstractBlock.Settings, Block> function) {
        Block toRegister = function.apply(AbstractBlock.Settings.create().registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(EntStupidStuff.MOD_ID, name))));
        registerBlockItem(name, toRegister);
        return Registry.register(Registries.BLOCK, Identifier.of(EntStupidStuff.MOD_ID, name), toRegister);
    }

    private static Block registerWithoutItem(String name, Function<AbstractBlock.Settings, Block> function) {
        return Registry.register(Registries.BLOCK, Identifier.of(EntStupidStuff.MOD_ID, name), function.apply(AbstractBlock.Settings.create().registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(EntStupidStuff.MOD_ID, name)))));
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, Identifier.of(EntStupidStuff.MOD_ID, name), new BlockItem(block, new Item.Settings().useBlockPrefixedTranslationKey().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(EntStupidStuff.MOD_ID, name)))));
    }
    
}
