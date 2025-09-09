package net.ent.entstupidstuff.item;

import net.ent.entstupidstuff.EntStupidStuff;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;

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

    

    public static final TagKey<Biome> SPAWN_BUTTERFLY = TagKey.of(
        RegistryKeys.BIOME, 
        Identifier.of(EntStupidStuff.MOD_ID, "spawn_butterfly")
    );

    public static final TagKey<Biome> SPAWN_BLUE_BUTTERFLY = TagKey.of(
        RegistryKeys.BIOME, 
        Identifier.of(EntStupidStuff.MOD_ID, "spawn_blue_butterfly")
    );

    public static final TagKey<Biome> SPAWN_CREEPER_BUTTERFLY = TagKey.of(
        RegistryKeys.BIOME, 
        Identifier.of(EntStupidStuff.MOD_ID, "spawn_creeper_butterfly")
    );

    public static final TagKey<Biome> SPAWN_EMPEROR_BUTTERFLY = TagKey.of(
        RegistryKeys.BIOME, 
        Identifier.of(EntStupidStuff.MOD_ID, "spawn_emperor_butterfly")
    );

    public static final TagKey<Biome> SPAWN_LUMINOUS_BUTTERFLY = TagKey.of(
        RegistryKeys.BIOME, 
        Identifier.of(EntStupidStuff.MOD_ID, "spawn_luminous_butterfly")
    );

    public static final TagKey<Biome> SPAWN_MONARCH_BUTTERFLY = TagKey.of(
        RegistryKeys.BIOME, 
        Identifier.of(EntStupidStuff.MOD_ID, "spawn_monarch_butterfly")
    );

    public static final TagKey<Biome> SPAWN_BIRCH_BUTTERFLY = TagKey.of(
        RegistryKeys.BIOME, 
        Identifier.of(EntStupidStuff.MOD_ID, "spawn_birch_butterfly")
    );

    public static final TagKey<Biome> SPAWN_REDWOOD_BUTTERFLY = TagKey.of(
        RegistryKeys.BIOME, 
        Identifier.of(EntStupidStuff.MOD_ID, "spawn_redwood_butterfly")
    );

    public static final TagKey<Biome> SPAWN_SEELE_BUTTERFLY = TagKey.of(
        RegistryKeys.BIOME, 
        Identifier.of(EntStupidStuff.MOD_ID, "spawn_seele_butterfly")
    );

    public static final TagKey<Biome> SPAWN_YELLOW_BUTTERFLY = TagKey.of(
        RegistryKeys.BIOME, 
        Identifier.of(EntStupidStuff.MOD_ID, "spawn_yellow_butterfly")
    );
    
}
