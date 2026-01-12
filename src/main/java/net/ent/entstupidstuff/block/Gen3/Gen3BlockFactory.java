package net.ent.entstupidstuff.block.Gen3;

import java.util.function.Function;

import net.ent.entstupidstuff.EntStupidStuff;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

public class Gen3BlockFactory {
    /*
    * Block Factory Designed for 1.21.10
    */

    public void onInitialize() {

        //Wood Type Registeration:
        Block REDWOOD_PLANKS = register("redwood" + "_planks" + "", properties -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).mapColor((MapColor.CRIMSON_NYLIUM))));

    }

    // -------------------------
    //   Updated Registration
    // -------------------------

    private static Block register(String name, Function<BlockBehaviour.Properties, Block> function) {
        Block toRegister = function.apply(BlockBehaviour.Properties.of().setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, name))));
        registerBlockItem(name, toRegister);
        return Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, name), toRegister);
    }

    private static Block registerWithoutItem(String name, Function<BlockBehaviour.Properties, Block> function) {
        return Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, name), function.apply(BlockBehaviour.Properties.of().setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, name)))));
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(BuiltInRegistries.ITEM, ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, name), new BlockItem(block, new Item.Properties().useBlockDescriptionPrefix().setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, name)))));
    }
    
}
