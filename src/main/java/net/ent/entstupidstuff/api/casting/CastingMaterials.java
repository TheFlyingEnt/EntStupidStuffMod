package net.ent.entstupidstuff.api.casting;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class CastingMaterials {

    public static final Map<Item, TagKey<Item>> MATERIAL_TAGS = new HashMap<>();
    

    static {
        // Wooden tools
        MATERIAL_TAGS.put(Items.WOODEN_SWORD,    ItemTags.WOODEN_TOOL_MATERIALS);
        MATERIAL_TAGS.put(Items.WOODEN_PICKAXE,  ItemTags.WOODEN_TOOL_MATERIALS);
        MATERIAL_TAGS.put(Items.WOODEN_AXE,      ItemTags.WOODEN_TOOL_MATERIALS);
        MATERIAL_TAGS.put(Items.WOODEN_SHOVEL,   ItemTags.WOODEN_TOOL_MATERIALS);
        MATERIAL_TAGS.put(Items.WOODEN_HOE,      ItemTags.WOODEN_TOOL_MATERIALS);

        // Stone tools
        MATERIAL_TAGS.put(Items.STONE_SWORD,     ItemTags.STONE_TOOL_MATERIALS);
        MATERIAL_TAGS.put(Items.STONE_PICKAXE,   ItemTags.STONE_TOOL_MATERIALS);
        MATERIAL_TAGS.put(Items.STONE_AXE,       ItemTags.STONE_TOOL_MATERIALS);
        MATERIAL_TAGS.put(Items.STONE_SHOVEL,    ItemTags.STONE_TOOL_MATERIALS);
        MATERIAL_TAGS.put(Items.STONE_HOE,       ItemTags.STONE_TOOL_MATERIALS);

        // Copper tools
        MATERIAL_TAGS.put(Items.COPPER_SWORD,    ItemTags.COPPER_TOOL_MATERIALS);
        MATERIAL_TAGS.put(Items.COPPER_PICKAXE,  ItemTags.COPPER_TOOL_MATERIALS);
        MATERIAL_TAGS.put(Items.COPPER_AXE,      ItemTags.COPPER_TOOL_MATERIALS);
        MATERIAL_TAGS.put(Items.COPPER_SHOVEL,   ItemTags.COPPER_TOOL_MATERIALS);
        MATERIAL_TAGS.put(Items.COPPER_HOE,      ItemTags.COPPER_TOOL_MATERIALS);

        // Iron tools
        MATERIAL_TAGS.put(Items.IRON_SWORD,      ItemTags.IRON_TOOL_MATERIALS);
        MATERIAL_TAGS.put(Items.IRON_PICKAXE,    ItemTags.IRON_TOOL_MATERIALS);
        MATERIAL_TAGS.put(Items.IRON_AXE,        ItemTags.IRON_TOOL_MATERIALS);
        MATERIAL_TAGS.put(Items.IRON_SHOVEL,     ItemTags.IRON_TOOL_MATERIALS);
        MATERIAL_TAGS.put(Items.IRON_HOE,        ItemTags.IRON_TOOL_MATERIALS);

        // Gold tools
        MATERIAL_TAGS.put(Items.GOLDEN_SWORD,    ItemTags.GOLD_TOOL_MATERIALS);
        MATERIAL_TAGS.put(Items.GOLDEN_PICKAXE,  ItemTags.GOLD_TOOL_MATERIALS);
        MATERIAL_TAGS.put(Items.GOLDEN_AXE,      ItemTags.GOLD_TOOL_MATERIALS);
        MATERIAL_TAGS.put(Items.GOLDEN_SHOVEL,   ItemTags.GOLD_TOOL_MATERIALS);
        MATERIAL_TAGS.put(Items.GOLDEN_HOE,      ItemTags.GOLD_TOOL_MATERIALS);

        // Diamond tools
        MATERIAL_TAGS.put(Items.DIAMOND_SWORD,   ItemTags.DIAMOND_TOOL_MATERIALS);
        MATERIAL_TAGS.put(Items.DIAMOND_PICKAXE, ItemTags.DIAMOND_TOOL_MATERIALS);
        MATERIAL_TAGS.put(Items.DIAMOND_AXE,     ItemTags.DIAMOND_TOOL_MATERIALS);
        MATERIAL_TAGS.put(Items.DIAMOND_SHOVEL,  ItemTags.DIAMOND_TOOL_MATERIALS);
        MATERIAL_TAGS.put(Items.DIAMOND_HOE,     ItemTags.DIAMOND_TOOL_MATERIALS);

        // Netherite tools
        MATERIAL_TAGS.put(Items.NETHERITE_SWORD,   ItemTags.NETHERITE_TOOL_MATERIALS);
        MATERIAL_TAGS.put(Items.NETHERITE_PICKAXE, ItemTags.NETHERITE_TOOL_MATERIALS);
        MATERIAL_TAGS.put(Items.NETHERITE_AXE,     ItemTags.NETHERITE_TOOL_MATERIALS);
        MATERIAL_TAGS.put(Items.NETHERITE_SHOVEL,  ItemTags.NETHERITE_TOOL_MATERIALS);
        MATERIAL_TAGS.put(Items.NETHERITE_HOE,     ItemTags.NETHERITE_TOOL_MATERIALS);

        // Armor - same tag system
        MATERIAL_TAGS.put(Items.IRON_HELMET,         ItemTags.IRON_TOOL_MATERIALS);
        MATERIAL_TAGS.put(Items.IRON_CHESTPLATE,     ItemTags.IRON_TOOL_MATERIALS);
        MATERIAL_TAGS.put(Items.IRON_LEGGINGS,       ItemTags.IRON_TOOL_MATERIALS);
        MATERIAL_TAGS.put(Items.IRON_BOOTS,          ItemTags.IRON_TOOL_MATERIALS);
        MATERIAL_TAGS.put(Items.DIAMOND_HELMET,      ItemTags.DIAMOND_TOOL_MATERIALS);
        MATERIAL_TAGS.put(Items.DIAMOND_CHESTPLATE,  ItemTags.DIAMOND_TOOL_MATERIALS);
        MATERIAL_TAGS.put(Items.DIAMOND_LEGGINGS,    ItemTags.DIAMOND_TOOL_MATERIALS);
        MATERIAL_TAGS.put(Items.DIAMOND_BOOTS,       ItemTags.DIAMOND_TOOL_MATERIALS);
        MATERIAL_TAGS.put(Items.NETHERITE_HELMET,    ItemTags.NETHERITE_TOOL_MATERIALS);
        MATERIAL_TAGS.put(Items.NETHERITE_CHESTPLATE,ItemTags.NETHERITE_TOOL_MATERIALS);
        MATERIAL_TAGS.put(Items.NETHERITE_LEGGINGS,  ItemTags.NETHERITE_TOOL_MATERIALS);
        MATERIAL_TAGS.put(Items.NETHERITE_BOOTS,     ItemTags.NETHERITE_TOOL_MATERIALS);
        // add more armor as needed
    }

    public static boolean isValidToolMaterial(ItemStack base, ItemStack material) {
        TagKey<Item> tag = MATERIAL_TAGS.get(base.getItem());
        return tag != null && material.is(tag);
    }

    public static boolean isValidArmorMaterial(ItemStack base, ItemStack material) {
        TagKey<Item> tag = MATERIAL_TAGS.get(base.getItem());
        return tag != null && material.is(tag);
    }
}