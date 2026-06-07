package net.ent.entstupidstuff.api.casting;

import net.ent.entstupidstuff.component.ModDataComponentTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public class CastingTextureHelper {

    // e.g. iron_sword + knight -> minecraft:textures/item/iron_sword_knight.png
    public static ResourceLocation getToolTexture(ItemStack stack) {
        //ResourceLocation castId = stack.get(ModDataComponentTypes.TOOL_CAST);
        ResourceLocation castId = stack.get(ModDataComponentTypes.TOOL_CAST) != null ? stack.get(ModDataComponentTypes.TOOL_CAST).castId() : null;
        if (castId == null) return null;

        String castName = castId.getPath(); // "knight"
        ResourceLocation itemId = BuiltInRegistries.ITEM.getKey(stack.getItem());
        // itemId.getPath() = "iron_sword"
        String texturePath = "textures/item/" + itemId.getPath() + "_" + castName + ".png";

        return ResourceLocation.fromNamespaceAndPath(itemId.getNamespace(), texturePath);
    }

    // e.g. iron_chestplate + knight -> entstupidstuff:textures/item/iron_chestplate_knight.png
    public static ResourceLocation getArmorItemTexture(ItemStack stack) {
        //ResourceLocation castId = stack.get(ModDataComponentTypes.ARMOR_CAST);
        ResourceLocation castId = stack.get(ModDataComponentTypes.ARMOR_CAST) != null ? stack.get(ModDataComponentTypes.ARMOR_CAST).castId() : null;
        if (castId == null) return null;

        String castName = castId.getPath();
        ResourceLocation itemId = BuiltInRegistries.ITEM.getKey(stack.getItem());
        String texturePath = "textures/item/" + itemId.getPath() + "_" + castName + ".png";

        return ResourceLocation.fromNamespaceAndPath(itemId.getNamespace(), texturePath);
    }

    // e.g. iron + knight + layer1 -> minecraft:textures/models/armor/iron_knight_layer_1.png
    public static ResourceLocation getArmorLayerTexture(ItemStack stack, boolean isLegs) {
        //ResourceLocation castId = stack.get(ModDataComponentTypes.ARMOR_CAST);
        ResourceLocation castId = stack.get(ModDataComponentTypes.ARMOR_CAST) != null ? stack.get(ModDataComponentTypes.ARMOR_CAST).castId() : null;
        if (castId == null) return null;

        String castName = castId.getPath();
        // Derive material name from item name, e.g. "iron_chestplate" -> "iron"
        ResourceLocation itemId = BuiltInRegistries.ITEM.getKey(stack.getItem());
        String itemPath = itemId.getPath(); // "iron_chestplate", "diamond_leggings" etc.
        String materialName = itemPath.substring(0, itemPath.lastIndexOf('_')); // strip "_chestplate" etc.
        // materialName is now "iron", "diamond", "netherite" etc.

        String layer = isLegs ? "layer_2" : "layer_1";
        String texturePath = "textures/models/armor/" + materialName + "_" + castName + "_" + layer + ".png";

        return ResourceLocation.fromNamespaceAndPath(itemId.getNamespace(), texturePath);
    }
}
