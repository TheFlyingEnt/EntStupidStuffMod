package net.ent.entstupidstuff.item;

import java.util.LinkedHashMap;
import java.util.Map;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.block.BlockFactory;
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

    public static final RegistryKey<ItemGroup> ENTSTUPIDSTUFF_SERVER_GROUP = RegistryKey.of(RegistryKeys.ITEM_GROUP, Identifier.of(EntStupidStuff.MOD_ID, "ent_server_group"));

    public static final RegistryKey<ItemGroup> ENTSTUPIDSTUFF_DECO_GROUP = RegistryKey.of(RegistryKeys.ITEM_GROUP, Identifier.of(EntStupidStuff.MOD_ID, "ent_deco_group"));
    public static final RegistryKey<ItemGroup> ENTSTUPIDSTUFF_NATURAL_GROUP = RegistryKey.of(RegistryKeys.ITEM_GROUP, Identifier.of(EntStupidStuff.MOD_ID, "ent_natural_group"));
    public static final RegistryKey<ItemGroup> ENTSTUPIDSTUFF_DEFAULT_GROUP = RegistryKey.of(RegistryKeys.ITEM_GROUP, Identifier.of(EntStupidStuff.MOD_ID, "ent_default_group"));
    public static final RegistryKey<ItemGroup> ENTSTUPIDSTUFF_COMBAT_GROUP = RegistryKey.of(RegistryKeys.ITEM_GROUP, Identifier.of(EntStupidStuff.MOD_ID, "ent_combat_group"));


    public static final Map<Identifier, Item> Natural_Group = new LinkedHashMap<>();

    public static void onInitialize() {

        Registry.register(Registries.ITEM_GROUP, ENTSTUPIDSTUFF_SERVER_GROUP, FabricItemGroup.builder()
        .icon(() -> new ItemStack(ItemFactory.callItem("toasted_marshmellow").asItem()))
        .displayName(Text.translatable("item.entstupidstuff.server_group"))
        .build());

        if (EntStupidStuff.DEV_MODE) {

            Registry.register(Registries.ITEM_GROUP, ENTSTUPIDSTUFF_DECO_GROUP, FabricItemGroup.builder()
            .icon(() -> new ItemStack(BlockFactory.callBlock("fungal_planks_cyan").asItem()))
            .displayName(Text.translatable("item.entstupidstuff.deco_group"))
            .build());

            Registry.register(Registries.ITEM_GROUP, ENTSTUPIDSTUFF_NATURAL_GROUP, FabricItemGroup.builder()
            .icon(() -> new ItemStack(BlockFactory.callBlock("redwood_log").asItem()))
            .displayName(Text.translatable("item.entstupidstuff.natural_group"))
            .build());

            Registry.register(Registries.ITEM_GROUP, ENTSTUPIDSTUFF_DEFAULT_GROUP, FabricItemGroup.builder()
            .icon(() -> new ItemStack(ItemFactory.callItem("raw_marshmellow")))
            .displayName(Text.translatable("item.entstupidstuff.default_group")) //MISC
            .build());

            Registry.register(Registries.ITEM_GROUP, ENTSTUPIDSTUFF_COMBAT_GROUP, FabricItemGroup.builder()
            .icon(() -> new ItemStack(ItemFactory.callItem("diamond_hammer")))
            .displayName(Text.translatable("item.entstupidstuff.combat_group")) //Advance Combat
            .build());

            //addToCombat();

            AddItem();

        }
        LaunchItem() ;

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

    public static void addToServer(String item){
        ItemConvertible  id = ItemFactory.callItem(item);
        ItemGroupEvents.modifyEntriesEvent(ENTSTUPIDSTUFF_SERVER_GROUP).register(entries -> entries.add(id));
    }

    public static void addToServerBlock(String block){
        ItemConvertible  id = BlockFactory.callBlock(block).asItem();
        ItemGroupEvents.modifyEntriesEvent(ENTSTUPIDSTUFF_SERVER_GROUP).register(entries -> entries.add(id));
    }

    public static void LaunchItem() {

        ModGroup.addToServer("baguette");
        ModGroup.addToServer("raw_marshmellow");
        ModGroup.addToServer("toasted_marshmellow");

        ModGroup.addToServer("butterfly_jar");
        ModGroup.addToServer("butterfly_spawn_egg");

        ModGroup.addToServer("wooden_hammer");
        ModGroup.addToServer("stone_hammer");
        ModGroup.addToServer("golden_hammer");
        ModGroup.addToServer("iron_hammer");
        ModGroup.addToServer("diamond_hammer");
        ModGroup.addToServer("netherite_hammer");
        
        ModGroup.addToServer("zebra_fish_bucket");
        ModGroup.addToServer("zebra_fish");
        ModGroup.addToServer("alligator_gar_bucket");
        ModGroup.addToServer("alligator_gar");
        ModGroup.addToServer("cooked_alligator_gar");
        ModGroup.addToServer("mackerel_bucket");
        ModGroup.addToServer("mackerel");
        ModGroup.addToServer("bass_bucket");
        ModGroup.addToServer("bass");
        ModGroup.addToServer("cooked_bass");
        ModGroup.addToServer("fur_trout_bucket");
        ModGroup.addToServer("koi_bucket");
        ModGroup.addToServer("koi");
        ModGroup.addToServer("perch_bucket");
        ModGroup.addToServer("perch");
        ModGroup.addToServer("cooked_perch");
        ModGroup.addToServer("snapper_bucket");
        ModGroup.addToServer("snapper");
        ModGroup.addToServer("cooked_snapper");
        ModGroup.addToServer("mahimahi_bucket");
        ModGroup.addToServer("mahimahi");
        ModGroup.addToServer("cooked_mahimahi");

        //Spawn Eggs
        ModGroup.addToServer("zombie_lobber_spawn_egg");
        ModGroup.addToServer("zombie_scorched_spawn_egg");
        ModGroup.addToServer("zombie_frostbite_spawn_egg");
        ModGroup.addToServer("zombie_slimed_spawn_egg");
        ModGroup.addToServer("armored_pillager_spawn_egg");
        ModGroup.addToServer("piglin_warrior_spawn_egg");
        ModGroup.addToServer("soul_skeleton_spawn_egg");
        ModGroup.addToServer("bass");

        //Blocks
        addWoodPlain("fungal");
        addFungalWood("white");
        addFungalWood("gray");
        addFungalWood("black");
        addFungalWood("brown");
        addFungalWood("red");
        addFungalWood("orange");
        addFungalWood("yellow");
        addFungalWood("lime");
        addFungalWood("green");
        addFungalWood("cyan");
        addFungalWood("light_blue");
        addFungalWood("blue");
        addFungalWood("purple");
        addFungalWood("magenta");
        addFungalWood("pink");

        addMosaicnInteration("oak");
        addMosaicnInteration("spruce");
        addMosaicnInteration("jungle");
        addMosaicnInteration("birch");
        addMosaicnInteration("dark_oak");
        addMosaicnInteration("acacia");
        addMosaicnInteration("mangrove");
        addMosaicnInteration("cherry");
        ModGroup.addToServerBlock("bamboo" + "_glass_trapdoor");
        ModGroup.addToServerBlock("bamboo" + "_glass_door");

        ModGroup.addToServer("zombie_lobber_spawn_egg");

        //Limestone

        ModGroup.addToServerBlock("limestone");
        ModGroup.addToServerBlock("limestone" + "_stairs");
        ModGroup.addToServerBlock("limestone" + "_slab");
        ModGroup.addToServerBlock("limestone" + "_wall");
        ModGroup.addToServerBlock("polished_limestone");
        ModGroup.addToServerBlock("polished_limestone" + "_stairs");
        ModGroup.addToServerBlock("polished_limestone" + "_slab");
        ModGroup.addToServerBlock("polished_limestone" + "_wall");
        
        ModGroup.addToServerBlock("polished_limestone" + "_bricks");
        ModGroup.addToServerBlock("polished_limestone" + "_brick_stairs");
        ModGroup.addToServerBlock("polished_limestone" + "_brick_slab");
        ModGroup.addToServerBlock("polished_limestone" + "_brick_wall");
        ModGroup.addToServerBlock("polished_limestone" + "_brick_chiseled");
        ModGroup.addToServerBlock("cracked" + "_polished_limestone" + "_bricks");

        //Andesite

        ModGroup.addToServerBlock("polished_andesite" + "_wall");     
        ModGroup.addToServerBlock("andesite" + "_bricks");
        ModGroup.addToServerBlock("andesite" + "_brick_stairs");
        ModGroup.addToServerBlock("andesite" + "_brick_slab");
        ModGroup.addToServerBlock("andesite" + "_brick_wall");
        ModGroup.addToServerBlock("andesite" + "_brick_chiseled");

        //Diorite

        ModGroup.addToServerBlock("polished_diorite" + "_wall");     
        ModGroup.addToServerBlock("diorite" + "_bricks");
        ModGroup.addToServerBlock("diorite" + "_brick_stairs");
        ModGroup.addToServerBlock("diorite" + "_brick_slab");
        ModGroup.addToServerBlock("diorite" + "_brick_wall");
        ModGroup.addToServerBlock("diorite" + "_brick_chiseled");

        //Diorite

        ModGroup.addToServerBlock("polished_granite" + "_wall");     
        ModGroup.addToServerBlock("granite" + "_bricks");
        ModGroup.addToServerBlock("granite" + "_brick_stairs");
        ModGroup.addToServerBlock("granite" + "_brick_slab");
        ModGroup.addToServerBlock("granite" + "_brick_wall");
        ModGroup.addToServerBlock("granite" + "_brick_chiseled");

        
        
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

    public static  void addWoodPlain(String type){
        ModGroup.addToServerBlock(type + "_planks");
        ModGroup.addToServerBlock(type + "_button");
        ModGroup.addToServerBlock(type + "_fence");
        ModGroup.addToServerBlock(type + "_fence_gate");
        ModGroup.addToServerBlock(type + "_pressure_plate");
        ModGroup.addToServerBlock(type + "_slab");
        ModGroup.addToServerBlock(type + "_stairs");
        ModGroup.addToServerBlock(type + "_trapdoor");
        ModGroup.addToServerBlock(type + "_glass_trapdoor");
        ModGroup.addToServerBlock(type + "_door");
        ModGroup.addToServerBlock(type + "_glass_door");

        ModGroup.addToServerBlock(type + "_mosaic");
        ModGroup.addToServerBlock(type + "_mosaic_stairs");
        ModGroup.addToServerBlock(type + "_mosaic_slab");
        
    }

    public static  void addFungalWood(String color){
        ModGroup.addToServerBlock("fungal_planks_" + color);
        ModGroup.addToServerBlock("fungal_button_" + color);
        ModGroup.addToServerBlock("fungal_fence_" + color);
        ModGroup.addToServerBlock("fungal_fence_gate_" + color);
        ModGroup.addToServerBlock("fungal_pressure_plate_" + color);
        ModGroup.addToServerBlock("fungal_slab_" + color);
        ModGroup.addToServerBlock("fungal_stairs_" + color);
        ModGroup.addToServerBlock("fungal_trapdoor_" + color);
        ModGroup.addToServerBlock("fungal_glass_trapdoor_" + color);
        ModGroup.addToServerBlock("fungal_door_" + color);
        ModGroup.addToServerBlock("fungal_glass_door_" + color);

        ModGroup.addToServerBlock("fungal_mosaic_" + color);
        ModGroup.addToServerBlock("fungal_mosaic_stairs_" + color);
        ModGroup.addToServerBlock("fungal_mosaic_slab_" + color);
        
    }

    public static  void addMosaicnInteration(String type){
        ModGroup.addToServerBlock(type + "_mosaic");
        ModGroup.addToServerBlock(type + "_mosaic_stairs");
        ModGroup.addToServerBlock(type + "_mosaic_slab");
        ModGroup.addToServerBlock(type + "_glass_trapdoor");
        ModGroup.addToServerBlock(type + "_glass_door");
    }

}
