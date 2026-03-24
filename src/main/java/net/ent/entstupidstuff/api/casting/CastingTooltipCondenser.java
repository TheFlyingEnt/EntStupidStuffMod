package net.ent.entstupidstuff.api.casting;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

public class CastingTooltipCondenser {

    // All armor pieces per material
    private static final Map<String, List<Item>> ARMOR_SETS = new LinkedHashMap<>();
    private static final Map<String, List<Item>> TOOL_SETS = new LinkedHashMap<>();

    static {
        ARMOR_SETS.put("Leather Armor",   List.of(Items.LEATHER_HELMET, Items.LEATHER_CHESTPLATE, Items.LEATHER_LEGGINGS, Items.LEATHER_BOOTS));
        ARMOR_SETS.put("Chainmail Armor", List.of(Items.CHAINMAIL_HELMET, Items.CHAINMAIL_CHESTPLATE, Items.CHAINMAIL_LEGGINGS, Items.CHAINMAIL_BOOTS));
        ARMOR_SETS.put("Iron Armor",      List.of(Items.IRON_HELMET, Items.IRON_CHESTPLATE, Items.IRON_LEGGINGS, Items.IRON_BOOTS));
        ARMOR_SETS.put("Copper Armor",    List.of(Items.COPPER_HELMET, Items.COPPER_CHESTPLATE, Items.COPPER_LEGGINGS, Items.COPPER_BOOTS));
        ARMOR_SETS.put("Golden Armor",    List.of(Items.GOLDEN_HELMET, Items.GOLDEN_CHESTPLATE, Items.GOLDEN_LEGGINGS, Items.GOLDEN_BOOTS));
        ARMOR_SETS.put("Diamond Armor",   List.of(Items.DIAMOND_HELMET, Items.DIAMOND_CHESTPLATE, Items.DIAMOND_LEGGINGS, Items.DIAMOND_BOOTS));
        ARMOR_SETS.put("Netherite Armor", List.of(Items.NETHERITE_HELMET, Items.NETHERITE_CHESTPLATE, Items.NETHERITE_LEGGINGS, Items.NETHERITE_BOOTS)); 
        ARMOR_SETS.put("Netherite Armor", List.of(Items.NETHERITE_HELMET, Items.NETHERITE_CHESTPLATE, Items.NETHERITE_LEGGINGS, Items.NETHERITE_BOOTS));

        TOOL_SETS.put("Wooden Tools",    List.of(Items.WOODEN_SWORD, Items.WOODEN_PICKAXE, Items.WOODEN_AXE, Items.WOODEN_SHOVEL, Items.WOODEN_HOE));
        TOOL_SETS.put("Stone Tools",     List.of(Items.STONE_SWORD, Items.STONE_PICKAXE, Items.STONE_AXE, Items.STONE_SHOVEL, Items.STONE_HOE));
        TOOL_SETS.put("Copper Tools",    List.of(Items.COPPER_SWORD, Items.COPPER_PICKAXE, Items.COPPER_AXE, Items.COPPER_SHOVEL, Items.COPPER_HOE));
        TOOL_SETS.put("Iron Tools",      List.of(Items.IRON_SWORD, Items.IRON_PICKAXE, Items.IRON_AXE, Items.IRON_SHOVEL, Items.IRON_HOE));
        TOOL_SETS.put("Golden Tools",    List.of(Items.GOLDEN_SWORD, Items.GOLDEN_PICKAXE, Items.GOLDEN_AXE, Items.GOLDEN_SHOVEL, Items.GOLDEN_HOE));
        TOOL_SETS.put("Diamond Tools",   List.of(Items.DIAMOND_SWORD, Items.DIAMOND_PICKAXE, Items.DIAMOND_AXE, Items.DIAMOND_SHOVEL, Items.DIAMOND_HOE));
        TOOL_SETS.put("Netherite Tools", List.of(Items.NETHERITE_SWORD, Items.NETHERITE_PICKAXE, Items.NETHERITE_AXE, Items.NETHERITE_SHOVEL, Items.NETHERITE_HOE));
    }

    private static final List<Item> ALL_ARMOR = ARMOR_SETS.values().stream()
        .flatMap(Collection::stream).toList();
    private static final List<Item> ALL_TOOLS = TOOL_SETS.values().stream()
        .flatMap(Collection::stream).toList();

    public static List<Component> condense(Set<Item> validItems) {
        List<Item> items = new ArrayList<>(validItems);
        List<Component> result = new ArrayList<>();
        Set<Item> consumed = new HashSet<>();

        // Check "All Armor"
        if (items.containsAll(ALL_ARMOR)) {
            result.add(CommonComponents.space().append(
                Component.translatable("item.entstupidstuff.modifiers.armor").withStyle(ChatFormatting.BLUE)
            ));
            consumed.addAll(ALL_ARMOR);
        } else {
            // Check per-material armor sets
            for (Map.Entry<String, List<Item>> entry : ARMOR_SETS.entrySet()) {
                if (items.containsAll(entry.getValue())) {
                    result.add(CommonComponents.space().append(
                        Component.translatable(entry.getKey()).withStyle(ChatFormatting.BLUE)
                    ));
                    consumed.addAll(entry.getValue());
                }
            }
        }

        // Check "All Tools"
        if (items.containsAll(ALL_TOOLS)) {
            result.add(CommonComponents.space().append(
                Component.translatable("item.entstupidstuff.modifiers.tool").withStyle(ChatFormatting.BLUE)
            ));
            consumed.addAll(ALL_TOOLS);
        } else {
            // Check per-material tool sets
            for (Map.Entry<String, List<Item>> entry : TOOL_SETS.entrySet()) {
                if (items.containsAll(entry.getValue())) {
                    result.add(CommonComponents.space().append(
                        Component.literal(entry.getKey()).withStyle(ChatFormatting.BLUE)
                    ));
                    consumed.addAll(entry.getValue());
                }
            }
        }

        // Add any remaining items that weren't condensed
        for (Item item : items) {
            if (!consumed.contains(item)) {
                result.add(CommonComponents.space().append(
                    item.getName().copy().withStyle(ChatFormatting.BLUE)
                ));
            }
        }

        return result;
    }
}
