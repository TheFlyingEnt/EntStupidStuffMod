package net.ent.entstupidstuff.item;

import net.ent.entstupidstuff.EntStupidStuff;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModItemTags {
     public static final TagKey<Item> HAMMER_ENCHANTABLE = TagKey.of(
        RegistryKeys.ITEM,
        Identifier.of(EntStupidStuff.MOD_ID, "enchantable/hammer")
    );

    public static final TagKey<Item> HAMMER = TagKey.of(
        RegistryKeys.ITEM,
        Identifier.of(EntStupidStuff.MOD_ID, "hammer")
    );

    public static final TagKey<Enchantment> HAMMER_EXCLUSIVE_SET = TagKey.of(
        RegistryKeys.ENCHANTMENT,
        Identifier.of(EntStupidStuff.MOD_ID, "exclusive_set/hammer")
    );
    
}
