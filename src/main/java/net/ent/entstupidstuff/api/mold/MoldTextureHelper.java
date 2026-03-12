package net.ent.entstupidstuff.api.mold;

import net.ent.entstupidstuff.component.ModDataComponentTypes;
import net.ent.entstupidstuff.event.callback.ModDataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public class MoldTextureHelper {

    // e.g. iron_sword + knight -> minecraft:textures/item/iron_sword_knight.png
    public static ResourceLocation getToolTexture(ItemStack stack) {
        ResourceLocation moldId = stack.get(ModDataComponentTypes.TOOL_MOLD);
        if (moldId == null) return null;

        String moldName = moldId.getPath(); // "knight"
        ResourceLocation itemId = BuiltInRegistries.ITEM.getKey(stack.getItem());
        // itemId.getPath() = "iron_sword"
        String texturePath = "textures/item/" + itemId.getPath() + "_" + moldName + ".png";

        return ResourceLocation.fromNamespaceAndPath(itemId.getNamespace(), texturePath);
    }

    // e.g. iron_chestplate + knight -> entstupidstuff:textures/item/iron_chestplate_knight.png
    public static ResourceLocation getArmorItemTexture(ItemStack stack) {
        ResourceLocation moldId = stack.get(ModDataComponentTypes.ARMOR_MOLD);
        if (moldId == null) return null;

        String moldName = moldId.getPath();
        ResourceLocation itemId = BuiltInRegistries.ITEM.getKey(stack.getItem());
        String texturePath = "textures/item/" + itemId.getPath() + "_" + moldName + ".png";

        return ResourceLocation.fromNamespaceAndPath(itemId.getNamespace(), texturePath);
    }

    // e.g. iron + knight + layer1 -> minecraft:textures/models/armor/iron_knight_layer_1.png
    public static ResourceLocation getArmorLayerTexture(ItemStack stack, boolean isLegs) {
        ResourceLocation moldId = stack.get(ModDataComponentTypes.ARMOR_MOLD);
        if (moldId == null) return null;

        String moldName = moldId.getPath();
        // Derive material name from item name, e.g. "iron_chestplate" -> "iron"
        ResourceLocation itemId = BuiltInRegistries.ITEM.getKey(stack.getItem());
        String itemPath = itemId.getPath(); // "iron_chestplate", "diamond_leggings" etc.
        String materialName = itemPath.substring(0, itemPath.lastIndexOf('_')); // strip "_chestplate" etc.
        // materialName is now "iron", "diamond", "netherite" etc.

        String layer = isLegs ? "layer_2" : "layer_1";
        String texturePath = "textures/models/armor/" + materialName + "_" + moldName + "_" + layer + ".png";

        return ResourceLocation.fromNamespaceAndPath(itemId.getNamespace(), texturePath);
    }
}
