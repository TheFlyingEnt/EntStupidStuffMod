package net.ent.entstupidstuff.item;

import java.util.LinkedHashMap;
import java.util.Map;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.block.BlockFactoryUpt;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModGroup {

    public static final RegistryKey<ItemGroup> ENTSTUPIDSTUFF_DECO_GROUP = RegistryKey.of(RegistryKeys.ITEM_GROUP, Identifier.of(EntStupidStuff.MOD_ID, "ent_deco_group"));
    public static final RegistryKey<ItemGroup> ENTSTUPIDSTUFF_NATURAL_GROUP = RegistryKey.of(RegistryKeys.ITEM_GROUP, Identifier.of(EntStupidStuff.MOD_ID, "ent_natural_group"));
    public static final RegistryKey<ItemGroup> ENTSTUPIDSTUFF_DEFAULT_GROUP = RegistryKey.of(RegistryKeys.ITEM_GROUP, Identifier.of(EntStupidStuff.MOD_ID, "ent_default_group"));
    public static final RegistryKey<ItemGroup> ENTSTUPIDSTUFF_COMBAT_GROUP = RegistryKey.of(RegistryKeys.ITEM_GROUP, Identifier.of(EntStupidStuff.MOD_ID, "ent_combat_group"));

    public static final Map<Identifier, Item> Natural_Group = new LinkedHashMap<>();

    public static void onInitialize() {

        Registry.register(Registries.ITEM_GROUP, ENTSTUPIDSTUFF_DECO_GROUP, FabricItemGroup.builder()
        .icon(() -> new ItemStack(BlockFactoryUpt.callBlock("fungal_planks_cyan").asItem()))
        .displayName(Text.translatable("item.entstupidstuff.deco_group"))
        .build());

        Registry.register(Registries.ITEM_GROUP, ENTSTUPIDSTUFF_NATURAL_GROUP, FabricItemGroup.builder()
        .icon(() -> new ItemStack(BlockFactoryUpt.callBlock("redwood_log").asItem()))
        .displayName(Text.translatable("item.entstupidstuff.natural_group"))
        .build());

        Registry.register(Registries.ITEM_GROUP, ENTSTUPIDSTUFF_DEFAULT_GROUP, FabricItemGroup.builder()
        .icon(() -> new ItemStack(ItemFactory.callItem("raw_marshmellow"))) //CHECK PLEASE NOW
        .displayName(Text.translatable("item.entstupidstuff.default_group")) //MISC
        .build());

        Registry.register(Registries.ITEM_GROUP, ENTSTUPIDSTUFF_COMBAT_GROUP, FabricItemGroup.builder()
        .icon(() -> new ItemStack(ItemFactory.callItem("diamond_hammer")))
        .displayName(Text.translatable("item.entstupidstuff.combat_group")) //Advance Combat
        .build());

        //addToCombat();

        AddItem();

        /*
         * Natural Group
         * 
         * 
         * Deco - All Decoration Group
         * Natural - All Natural Stuff
         * Default Group - Everything
         * Combat - Combat Stuff
         * Misc - Misc
         * 
         */





    }

    public static void addToDefault(String item){
        Item id = ItemFactory.callItem(item);
        ItemGroupEvents.modifyEntriesEvent(ENTSTUPIDSTUFF_DEFAULT_GROUP).register(entries -> entries.add(id));
    }

    public static void addToNatural(String item){
        ItemConvertible  id = ItemFactory.callItem(item);
        ItemGroupEvents.modifyEntriesEvent(ENTSTUPIDSTUFF_NATURAL_GROUP).register(entries -> entries.add(id));
    }

    public static void addToDeco(String item){
        ItemConvertible id = ItemFactory.callItem(item);
        ItemConvertible id2 = id.asItem();
        ItemGroupEvents.modifyEntriesEvent(ENTSTUPIDSTUFF_DECO_GROUP).register(entries -> entries.add(id2)); //check

    }

    public static void addToCombat(String item){
        ItemConvertible  id = ItemFactory.callItem(item);
        ItemGroupEvents.modifyEntriesEvent(ENTSTUPIDSTUFF_COMBAT_GROUP).register(entries -> entries.add(id));
    }

    public static void LaunchItem() {

        ModGroup.addToCombat("baguette");
        ModGroup.addToCombat("raw_marshmellow");
        ModGroup.addToCombat("toasted_marshmellow");

        ModGroup.addToCombat("wooden_hammer");
        ModGroup.addToCombat("stone_hammer");
        ModGroup.addToCombat("golden_hammer");
        ModGroup.addToCombat("iron_hammer");
        ModGroup.addToCombat("diamond_hammer");
        ModGroup.addToCombat("netherite_hammer");
    }

    public static void AddItem(){

        ModGroup.addToCombat("wooden_dagger");
        ModGroup.addToCombat("stone_dagger");
        ModGroup.addToCombat("golden_dagger");
        ModGroup.addToCombat("iron_dagger");
        ModGroup.addToCombat("diamond_dagger");
        ModGroup.addToCombat("netherite_dagger");

        ModGroup.addToCombat("wooden_hammer");
        ModGroup.addToCombat("stone_hammer");
        ModGroup.addToCombat("golden_hammer");
        ModGroup.addToCombat("iron_hammer");
        ModGroup.addToCombat("diamond_hammer");
        ModGroup.addToCombat("netherite_hammer");

        ModGroup.addToCombat("wooden_long_sword");
        ModGroup.addToCombat("stone_long_sword");
        ModGroup.addToCombat("golden_long_sword");
        ModGroup.addToCombat("iron_long_sword");
        ModGroup.addToCombat("diamond_long_sword");
        ModGroup.addToCombat("netherite_long_sword");

        ModGroup.addToCombat("cannon");
        ModGroup.addToCombat("cannon_ball");
        ModGroup.addToCombat("prismerine_arrow");

        ModGroup.addToCombat("wooden_oak_shield");
        ModGroup.addToCombat("wooden_spruce_shield");
        ModGroup.addToCombat("wooden_birch_shield");
        ModGroup.addToCombat("wooden_jungle_shield");
        ModGroup.addToCombat("wooden_acacia_shield");
        ModGroup.addToCombat("wooden_dark_oak_shield");
        ModGroup.addToCombat("wooden_mangrove_shield");
        ModGroup.addToCombat("wooden_cherry_shield");
        ModGroup.addToCombat("wooden_bamboo_shield");

        ModGroup.addToCombat("diamond_shield");

    }



    
}
