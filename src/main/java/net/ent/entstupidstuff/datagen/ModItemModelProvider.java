package net.ent.entstupidstuff.datagen;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.api.casting.ToolCastingProperty;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ItemModelOutput;
import net.minecraft.client.data.models.model.ItemModelUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

public class ModItemModelProvider extends FabricModelProvider {

    public ModItemModelProvider(FabricDataOutput output) {
        super(output);

    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerator) {
        registerCastableToolItem(itemModelGenerator.itemModelOutput, Items.IRON_SWORD, "iron_sword");
        registerCastableToolItem(itemModelGenerator.itemModelOutput, Items.DIAMOND_SWORD, "diamond_sword");
        registerCastableToolItem(itemModelGenerator.itemModelOutput, Items.IRON_PICKAXE, "iron_pickaxe");
        // etc. for every moldable tool
    }

    private void registerCastableToolItem(ItemModelOutput output, Item item, String baseName) {
        output.accept(item, ItemModelUtils.select(
            new ToolCastingProperty(),
            ItemModelUtils.plainModel(ResourceLocation.withDefaultNamespace("item/" + baseName)), // fallback
            ItemModelUtils.when(
                ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "knight"),
                ItemModelUtils.plainModel(ResourceLocation.withDefaultNamespace("item/" + baseName + "_knight"))
            ),
            ItemModelUtils.when(
                ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "runic"),
                ItemModelUtils.plainModel(ResourceLocation.withDefaultNamespace("item/" + baseName + "_runic"))
            )
        ));
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockStateModelGenerator) {
        //Unused rn
    }

}
