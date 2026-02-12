package net.ent.entstupidstuff.item;

import net.ent.entstupidstuff.EntStupidStuff;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;

public class ModTags {

    // # Enchantment

    public static final TagKey<Enchantment> HAMMER_ENCHANTMENTS = TagKey.create(
        Registries.ENCHANTMENT,
        ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "enchantable/hammer")
    );

    public static final TagKey<Item> HAMMER = TagKey.create(
        Registries.ITEM,
        ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "hammer")
    );

    public static final TagKey<Enchantment> HAMMER_EXCLUSIVE_SET = TagKey.create(
        Registries.ENCHANTMENT,
        ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "exclusive_set/hammer")
    );

    public static final TagKey<Item> MUSHROOM_CRAFTING = TagKey.create(
        Registries.ITEM, 
        ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "mushroom_crafting")
    );

    public static final TagKey<Item> SPOREPER_DROP_MUSIC_DISCS = TagKey.create(
        Registries.ITEM, 
        ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "sporeper_drop_music_discs")
    );
    

    // # Biomes

    public static final TagKey<Biome> SPAWN_BUTTERFLY = TagKey.create(
        Registries.BIOME, 
        ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "spawn_butterfly")
    );

    public static final TagKey<Biome> SPAWN_BLUE_BUTTERFLY = TagKey.create(
        Registries.BIOME, 
        ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "spawn_blue_butterfly")
    );

    public static final TagKey<Biome> SPAWN_CREEPER_BUTTERFLY = TagKey.create(
        Registries.BIOME, 
        ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "spawn_creeper_butterfly")
    );

    public static final TagKey<Biome> SPAWN_EMPEROR_BUTTERFLY = TagKey.create(
        Registries.BIOME, 
        ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "spawn_emperor_butterfly")
    );

    public static final TagKey<Biome> SPAWN_LUMINOUS_BUTTERFLY = TagKey.create(
        Registries.BIOME, 
        ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "spawn_luminous_butterfly")
    );

    public static final TagKey<Biome> SPAWN_MONARCH_BUTTERFLY = TagKey.create(
        Registries.BIOME, 
        ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "spawn_monarch_butterfly")
    );

    public static final TagKey<Biome> SPAWN_BIRCH_BUTTERFLY = TagKey.create(
        Registries.BIOME, 
        ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "spawn_birch_butterfly")
    );

    public static final TagKey<Biome> SPAWN_REDWOOD_BUTTERFLY = TagKey.create(
        Registries.BIOME, 
        ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "spawn_redwood_butterfly")
    );

    public static final TagKey<Biome> SPAWN_SEELE_BUTTERFLY = TagKey.create(
        Registries.BIOME, 
        ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "spawn_seele_butterfly")
    );

    public static final TagKey<Biome> SPAWN_YELLOW_BUTTERFLY = TagKey.create(
        Registries.BIOME, 
        ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "spawn_yellow_butterfly")
    );

    // # Blocks

    public static final TagKey<Block> SHROOMIUM_REPLACE = TagKey.create(
        Registries.BLOCK, 
        ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "shroomium_replace")
    );

    public static final TagKey<Block> SILKMOTH_SPAWNABLE_ON = TagKey.create(
        Registries.BLOCK, 
        ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "silkmoth_spawnable_on")
    );
    
}
