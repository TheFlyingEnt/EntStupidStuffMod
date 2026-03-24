package net.ent.entstupidstuff.api.casting;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.ent.entstupidstuff.EntStupidStuff;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.equipment.Equippable;

public class CastingTemplateItem extends Item {

    // Slot description translations
    private static final Map<String, ResourceLocation> TOOL_ICONS = Map.of(
        "sword",   ResourceLocation.withDefaultNamespace("container/slot/sword"),
        "pickaxe", ResourceLocation.withDefaultNamespace("container/slot/pickaxe"),
        "axe",     ResourceLocation.withDefaultNamespace("container/slot/axe"),
        "shovel",  ResourceLocation.withDefaultNamespace("container/slot/shovel"),
        "hoe",     ResourceLocation.withDefaultNamespace("container/slot/hoe")
    );

    private static final Map<String, ResourceLocation> ARMOR_ICONS = Map.of(
        "helmet",     ResourceLocation.withDefaultNamespace("container/slot/helmet"),
        "chestplate", ResourceLocation.withDefaultNamespace("container/slot/chestplate"),
        "leggings",   ResourceLocation.withDefaultNamespace("container/slot/leggings"),
        "boots",      ResourceLocation.withDefaultNamespace("container/slot/boots")
    );

    private static final ResourceLocation LAVA_BUCKET_ICON =
        ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "container/slot/bucket"); // closest vanilla icon

    private final String castName;
    private final Set<Item> validItems;

    public CastingTemplateItem(String castName, Properties properties, Item... validItems) {
        super(properties);
        this.castName = castName;
        this.validItems = Set.of(validItems);
    }

    public String getCastName() { return castName; }

    public boolean canApplyTo(ItemStack stack) {
        return validItems.contains(stack.getItem());
    }

    public boolean isToolItem(ItemStack stack) {
        return stack.has(DataComponents.TOOL);
    }

    public boolean isArmorItem(ItemStack stack) {
        Equippable equippable = stack.get(DataComponents.EQUIPPABLE);
        if (equippable == null) return false;
        EquipmentSlot slot = equippable.slot();
        return slot == EquipmentSlot.HEAD
            || slot == EquipmentSlot.CHEST
            || slot == EquipmentSlot.LEGS
            || slot == EquipmentSlot.FEET;
    }

    public Set<Item> getValidItems() {
        return validItems;
    }

    // Build cycling icons for the base slot based on valid items
    public List<ResourceLocation> getBaseSlotEmptyIcons() {
        List<ResourceLocation> icons = new ArrayList<>();
        Set<ResourceLocation> seen = new LinkedHashSet<>();

        for (Item item : validItems) {
            String id = BuiltInRegistries.ITEM.getKey(item).getPath();
            if (id.contains("helmet"))     seen.add(ARMOR_ICONS.get("helmet"));
            if (id.contains("chestplate")) seen.add(ARMOR_ICONS.get("chestplate"));
            if (id.contains("leggings"))   seen.add(ARMOR_ICONS.get("leggings"));
            if (id.contains("boots"))      seen.add(ARMOR_ICONS.get("boots"));
            if (id.contains("sword"))      seen.add(TOOL_ICONS.get("sword"));
            if (id.contains("pickaxe"))    seen.add(TOOL_ICONS.get("pickaxe"));
            if (id.contains("axe") && !id.contains("pickaxe")) seen.add(TOOL_ICONS.get("axe"));
            if (id.contains("shovel"))     seen.add(TOOL_ICONS.get("shovel"));
            if (id.contains("hoe"))        seen.add(TOOL_ICONS.get("hoe"));
        }

        icons.addAll(seen);
        return icons.isEmpty() ? List.of(ARMOR_ICONS.get("helmet")) : icons;
    }

    // Lava bucket for addition slot
    public List<ResourceLocation> getAdditionSlotEmptyIcons() {
        return List.of(LAVA_BUCKET_ICON);
    }

    public Component getBaseSlotDescription() {
        return Component.translatable("item.entstupidstuff.casting_template.base_slot_description");
    }

    public Component getAdditionSlotDescription() {
        return Component.translatable("item.entstupidstuff.casting_template.addition_slot_description");
    }
}
