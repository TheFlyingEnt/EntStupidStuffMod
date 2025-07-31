package net.ent.entstupidstuff.datagen;

import java.util.concurrent.CompletableFuture;

import net.ent.entstupidstuff.block.BlockFactoryUpt;
import net.ent.entstupidstuff.block.ModBlocks;
import net.ent.entstupidstuff.item.ItemFactory;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.RegistryWrapper.WrapperLookup;

public class ModLangProvider extends FabricLanguageProvider  {

    public ModLangProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, "en_us", registryLookup);
    }
    

    public ModLangProvider(FabricDataOutput dataOutput, String languageCode, CompletableFuture<WrapperLookup> registryLookup) {
        super(dataOutput, "en_us", registryLookup);
    }

    @Override
    public void generateTranslations(WrapperLookup registryLookup, TranslationBuilder translationBuilder) {

        //Launch - Welcome to Stupidity

        translationBuilder.add("item.entstupidstuff." + "raw_marshmellow", "Marshmellow on a Stick");
        translationBuilder.add("item.entstupidstuff." + "toasted_marshmellow", "Toasted Marshmellow on a Stick");
        translationBuilder.add("item.entstupidstuff." + "butterfly_jar", "Butterfly in a Jar");
        translationBuilder.add("item.entstupidstuff." + "baguette", "La Baguette");
        translationBuilder.add("item.entstupidstuff." + "noodle_bowl", "Bowl of Noodles");
        translationBuilder.add("item.entstupidstuff." + "apple_pie", "Apple Pie");

        translationBuilder.add("item.entstupidstuff." + "butterfly_spawn_egg", "Butterfly Spawn Egg");
        translationBuilder.add("item.entstupidstuff." + "zombie_lobber_spawn_egg", "Lobber Spawn Egg");

        translationBuilder.add("item.entstupidstuff." + "string_gate", "String Gate");
        translationBuilder.add("item.entstupidstuff." + "string_block", "String Block");

        //The Fire of the Hunt Update:

        translationBuilder.add("item.entstupidstuff." + "wither_bone", "Wither Bone");
        translationBuilder.add("item.entstupidstuff." + "ancient_debris_nugget", "Ancient Debris Nugget");

        translationBuilder.add("item.entstupidstuff." + "zombie_scorched_spawn_egg", "Scorched Spawn Egg");
        translationBuilder.add("item.entstupidstuff." + "piglin_warrior_spawn_egg", "Piglin Warrior Spawn Egg");
        translationBuilder.add("item.entstupidstuff." + "blazing_inferno_spawn_egg", "Blazing Inferno Spawn Egg");
        translationBuilder.add("item.entstupidstuff." + "soul_skeleton_spawn_egg", "Soul Skeleton Spawn Egg");
        

        // Tale of the Seas Update: (aka The Sea of Dead (Pirate Life) - On Stranger Tides)

        translationBuilder.add("item.entstupidstuff." + "bottle_of_rum", "Bottle of Rum");
        translationBuilder.add("block.entstupidstuff." + "phantom_torch", "Phantom Torch");
        translationBuilder.add("block.entstupidstuff." + "phantom_lantern", "Phantom Lantern");
        translationBuilder.add("item.entstupidstuff." + "ancient_trident", "Ancient Trident");
        translationBuilder.add("item.entstupidstuff." + "cannon", "Cannon");
        translationBuilder.add("item.entstupidstuff." + "cannon_ball", "Cannon ball");
        translationBuilder.add("item.entstupidstuff." + "prismerine_arrow", "Prismerine Arrow");

        translationBuilder.add("subtitles.ent.entity." + "alligator_gar.death", "Alligator Gar Dies");
        translationBuilder.add("subtitles.ent.entity." + "alligator_gar.hurt", "Alligator Gar hurts ");
        translationBuilder.add("subtitles.ent.entity." + "alligator_gar.flop", "Alligator Gar Flops");
        translationBuilder.add("item.entstupidstuff." + "alligator_gar", "Alligator Gar");
        translationBuilder.add("item.entstupidstuff." + "cooked_alligator_gar", "Cooked Alligator Gar");
        translationBuilder.add("item.entstupidstuff." + "alligator_gar_bucket", "Bucket of Alligator Gar");

        translationBuilder.add("subtitles.ent.entity." + "zebra_fish.death", "Zebra Fish Dies");
        translationBuilder.add("subtitles.ent.entity." + "zebra_fish.hurt", "Zebra Fish hurts ");
        translationBuilder.add("subtitles.ent.entity." + "zebra_fish.flop", "Zebra Fish Flops");
        translationBuilder.add("item.entstupidstuff." + "zebra_fish", "Zebra Fish");
        translationBuilder.add("item.entstupidstuff." + "zebra_fish_bucket", "Bucket of Zebra Fish");

        translationBuilder.add("subtitles.ent.entity." + "mackerel.death", "Mackerel Dies");
        translationBuilder.add("subtitles.ent.entity." + "mackerel.hurt", "Mackerel hurts ");
        translationBuilder.add("subtitles.ent.entity." + "mackerel.flop", "Mackerel Flops");
        translationBuilder.add("item.entstupidstuff." + "mackerel", "Mackerel");
        translationBuilder.add("item.entstupidstuff." + "cooked_mackerel", "Cooked Mackerel");
        translationBuilder.add("item.entstupidstuff." + "mackerel_bucket", "Bucket of Mackerel");

        translationBuilder.add("item.entstupidstuff." + "sunken_skeleton_spawn_egg", "Sunken Skeleton (Bow) Spawn Egg");
        translationBuilder.add("item.entstupidstuff." + "sunken_skeleton2_spawn_egg", "Sunken Skeleton (Crossbow) Spawn Egg");
        translationBuilder.add("item.entstupidstuff." + "skeleton_metal" + "_spawn_egg", "Metal Skeleton Spawn Egg");
        translationBuilder.add("item.entstupidstuff." + "skeleton_phantom" + "_spawn_egg", "Phantom Skeleton Spawn Egg");

        // Weapons

        translationBuilder.add(ItemFactory.callItem("wooden_claymore"), "Wooden Claymore");
        translationBuilder.add(ItemFactory.callItem("stone_claymore"), "Stone Claymore");
        translationBuilder.add(ItemFactory.callItem("iron_claymore"), "Iron Claymore");
        translationBuilder.add(ItemFactory.callItem("golden_claymore"), "Golden Claymore");
        translationBuilder.add(ItemFactory.callItem("diamond_claymore"), "Diamond Claymore");
        translationBuilder.add(ItemFactory.callItem("netherite_claymore"), "Netherite Claymore");

        translationBuilder.add(ItemFactory.callItem("wooden_glaive"), "Wooden Glaive");
        translationBuilder.add(ItemFactory.callItem("stone_glaive"), "Stone Glaive");
        translationBuilder.add(ItemFactory.callItem("iron_glaive"), "Iron Glaive");
        translationBuilder.add(ItemFactory.callItem("golden_glaive"), "Golden Glaive");
        translationBuilder.add(ItemFactory.callItem("diamond_glaive"), "Diamond Glaive");
        translationBuilder.add(ItemFactory.callItem("netherite_glaive"), "Netherite Glaive");

        translationBuilder.add(ItemFactory.callItem("wooden_long_sword"), "Wooden Long Sword");
        translationBuilder.add(ItemFactory.callItem("stone_long_sword"), "Stone Long Sword");
        translationBuilder.add(ItemFactory.callItem("iron_long_sword"), "Iron Long Sword");
        translationBuilder.add(ItemFactory.callItem("golden_long_sword"), "Golden Long Sword");
        translationBuilder.add(ItemFactory.callItem("diamond_long_sword"), "Diamond Long Sword");
        translationBuilder.add(ItemFactory.callItem("netherite_long_sword"), "Netherite Long Sword");

        translationBuilder.add(ItemFactory.callItem("wooden_hammer"), "Wooden Hammer");
        translationBuilder.add(ItemFactory.callItem("stone_hammer"), "Stone Hammer");
        translationBuilder.add(ItemFactory.callItem("iron_hammer"), "Iron Hammer");
        translationBuilder.add(ItemFactory.callItem("golden_hammer"), "Golden Hammer");
        translationBuilder.add(ItemFactory.callItem("diamond_hammer"), "Diamond Hammer");
        translationBuilder.add(ItemFactory.callItem("netherite_hammer"), "Netherite Hammer");

        // Shields

        addShield("wooden_oak", "Wooden Oak ", translationBuilder);
        addShield("wooden_spruce", "Wooden Spruce ", translationBuilder);
        addShield("wooden_birch", "Wooden Birch ", translationBuilder);
        addShield("wooden_jungle", "Wooden Jungle ", translationBuilder);
        addShield("wooden_acacia", "Wooden Acacia", translationBuilder);
        addShield("wooden_dark_oak", "Wooden Dark Oak", translationBuilder);
        addShield("wooden_mangrove", "Wooden Mangrove", translationBuilder);
        addShield("wooden_cherry", "Wooden Cherry", translationBuilder);
        addShield("wooden_bamboo", "Wooden Bamboo", translationBuilder);

        addShield("stone", "Stone", translationBuilder);
        addShield("stone_deepslate", "Deepslate", translationBuilder);
        addShield("stone_blackstone", "Blackstone", translationBuilder);
        addShield("golden", "Golden", translationBuilder);
        addShield("diamond", "Diamond", translationBuilder);


        // Wood, Planks, ETC

        translationBuilder.add(BlockFactoryUpt.callBlock("oak" + "_mosaic"), "Oak" + " Mosaic");
        translationBuilder.add(BlockFactoryUpt.callBlock("oak" + "_mosaic_stairs"), "Oak" + "Mosaic Stairs");
        translationBuilder.add(BlockFactoryUpt.callBlock("oak" + "_mosaic_slab"), "Oak" + "Mosaic Slab");

        translationBuilder.add(BlockFactoryUpt.callBlock("spruce" + "_mosaic"), "Spruce" + " Mosaic");
        translationBuilder.add(BlockFactoryUpt.callBlock("spruce" + "_mosaic_stairs"), "Spruce" + "Mosaic Stairs");
        translationBuilder.add(BlockFactoryUpt.callBlock("spruce" + "_mosaic_slab"), "Spruce" + "Mosaic Slab");

        translationBuilder.add(BlockFactoryUpt.callBlock("jungle" + "_mosaic"), "Jungle" + " Mosaic");
        translationBuilder.add(BlockFactoryUpt.callBlock("jungle" + "_mosaic_stairs"), "Jungle" + "Mosaic Stairs");
        translationBuilder.add(BlockFactoryUpt.callBlock("jungle" + "_mosaic_slab"), "Jungle" + "Mosaic Slab");

        translationBuilder.add(BlockFactoryUpt.callBlock("birch" + "_mosaic"), "Birch" + " Mosaic");
        translationBuilder.add(BlockFactoryUpt.callBlock("birch" + "_mosaic_stairs"), "Birch" + "Mosaic Stairs");
        translationBuilder.add(BlockFactoryUpt.callBlock("birch" + "_mosaic_slab"), "Birch" + "Mosaic Slab");

        translationBuilder.add(BlockFactoryUpt.callBlock("dark_oak" + "_mosaic"), "Dark Oak" + " Mosaic");
        translationBuilder.add(BlockFactoryUpt.callBlock("dark_oak" + "_mosaic_stairs"), "Dark Oak" + "Mosaic Stairs");
        translationBuilder.add(BlockFactoryUpt.callBlock("dark_oak" + "_mosaic_slab"), "Dark Oak" + "Mosaic Slab");

        translationBuilder.add(BlockFactoryUpt.callBlock("acacia" + "_mosaic"), "Acacia" + " Mosaic");
        translationBuilder.add(BlockFactoryUpt.callBlock("acacia" + "_mosaic_stairs"), "Acacia" + "Mosaic Stairs");
        translationBuilder.add(BlockFactoryUpt.callBlock("acacia" + "_mosaic_slab"), "Acacia" + "Mosaic Slab");

        translationBuilder.add(BlockFactoryUpt.callBlock("mangrove" + "_mosaic"), "Mangrove" + " Mosaic");
        translationBuilder.add(BlockFactoryUpt.callBlock("mangrove" + "_mosaic_stairs"), "Mangrove" + "Mosaic Stairs");
        translationBuilder.add(BlockFactoryUpt.callBlock("mangrove" + "_mosaic_slab"), "Mangrove" + "Mosaic Slab");

        translationBuilder.add(BlockFactoryUpt.callBlock("cherry" + "_mosaic"), "Cherry" + " Mosaic");
        translationBuilder.add(BlockFactoryUpt.callBlock("cherry" + "_mosaic_stairs"), "Cherry" + "Mosaic Stairs");
        translationBuilder.add(BlockFactoryUpt.callBlock("cherry" + "_mosaic_slab"), "Cherry" + "Mosaic Slab");

        translationBuilder.add(BlockFactoryUpt.callBlock("crimson" + "_mosaic"), "Crimson" + " Mosaic");
        translationBuilder.add(BlockFactoryUpt.callBlock("crimson" + "_mosaic_stairs"), "Crimson" + "Mosaic Stairs");
        translationBuilder.add(BlockFactoryUpt.callBlock("crimson" + "_mosaic_slab"), "Crimson" + "Mosaic Slab");

        translationBuilder.add(BlockFactoryUpt.callBlock("warped" + "_mosaic"), "Warped" + " Mosaic");
        translationBuilder.add(BlockFactoryUpt.callBlock("warped" + "_mosaic_stairs"), "Warped" + "Mosaic Stairs");
        translationBuilder.add(BlockFactoryUpt.callBlock("warped" + "_mosaic_slab"), "Warped" + "Mosaic Slab");

        translationBuilder.add(BlockFactoryUpt.callBlock("fungal_planks"), "Fungal Planks");
        translationBuilder.add(BlockFactoryUpt.callBlock("fungal_stairs"), "Fungal Stairs");
        translationBuilder.add(BlockFactoryUpt.callBlock("fungal_slab"), "Fungal Slab");
        translationBuilder.add(BlockFactoryUpt.callBlock("fungal_fence"), "Fungal Fence");
        translationBuilder.add(BlockFactoryUpt.callBlock("fungal_fence_gate"), "Fungal Fence Gate");
        translationBuilder.add(BlockFactoryUpt.callBlock("fungal_door"), "Fungal Door");
        translationBuilder.add(BlockFactoryUpt.callBlock("fungal_trapdoor"), "Fungal Trapdoor");
        translationBuilder.add(BlockFactoryUpt.callBlock("fungal_pressure_plate"), "Fungal Pressure Plate");
        translationBuilder.add(BlockFactoryUpt.callBlock("fungal_button"), "Fungal Button");
        translationBuilder.add(BlockFactoryUpt.callBlock("fungal_glass_door"), "Fungal Glass Door");
        translationBuilder.add(BlockFactoryUpt.callBlock("fungal_glass_trapdoor"), "Fungal Glass Trapdoor");

        translationBuilder.add(BlockFactoryUpt.callBlock("fungal_mosaic"), "Fungal Mosaic");
        translationBuilder.add(BlockFactoryUpt.callBlock("fungal_mosaic_stairs"), "Fungal Mosaic Stairs");
        translationBuilder.add(BlockFactoryUpt.callBlock("fungal_mosaic_slab"), "Fungal Mosaic Slab");

        for (String color : BlockFactoryUpt.COLORS){
            translationBuilder.add(BlockFactoryUpt.callBlock("fungal_planks_" + color), formatString(color) + " Fungal Planks");
            translationBuilder.add(BlockFactoryUpt.callBlock("fungal_stairs_" + color), formatString(color) + " Fungal Stairs");
            translationBuilder.add(BlockFactoryUpt.callBlock("fungal_slab_" + color), formatString(color) + " Fungal Slab");
            translationBuilder.add(BlockFactoryUpt.callBlock("fungal_fence_" + color), formatString(color) + " Fungal Fence");
            translationBuilder.add(BlockFactoryUpt.callBlock("fungal_fence_gate_" + color), formatString(color) + " Fungal Fence Gate");
            translationBuilder.add(BlockFactoryUpt.callBlock("fungal_door_" + color), formatString(color) + " Fungal Door");
            translationBuilder.add(BlockFactoryUpt.callBlock("fungal_trapdoor_" + color), formatString(color) + " Fungal Trapdoor");
            translationBuilder.add(BlockFactoryUpt.callBlock("fungal_pressure_plate_" + color), formatString(color) + " Fungal Pressure Plate");
            translationBuilder.add(BlockFactoryUpt.callBlock("fungal_button_" + color), formatString(color) + " Fungal Button");
            translationBuilder.add(BlockFactoryUpt.callBlock("fungal_glass_door_" + color), formatString(color) + " Fungal Glass Door");
            translationBuilder.add(BlockFactoryUpt.callBlock("fungal_glass_trapdoor_" + color), formatString(color) + " Fungal Glass Trapdoor");

            translationBuilder.add(BlockFactoryUpt.callBlock("fungal_mosaic_" + color), formatString(color) + " Fungal Mosaic");
            translationBuilder.add(BlockFactoryUpt.callBlock("fungal_mosaic_stairs_" + color), formatString(color) + " Fungal Mosaic Stairs");
            translationBuilder.add(BlockFactoryUpt.callBlock("fungal_mosaic_slab_" + color), formatString(color) + " Fungal Mosaic Slab");
        
            translationBuilder.add(BlockFactoryUpt.callBlock("textured_wool_" + color), formatString(color) + " Quillted Wool");
        }

        translationBuilder.add(BlockFactoryUpt.callBlock("iron_glass_door"),"Iron Glass Door");

        for (String varient : ModBlocks.COPPER_VARIENTS){ //Copper Glass Door
            translationBuilder.add(BlockFactoryUpt.callBlock(varient + "_glass_door"), formatString(varient) + " Glass Door");
        }

        for (String varient : ModBlocks.V_WOOD_VARIENTS){ //Vanilla Wooden Glass Door
            translationBuilder.add(BlockFactoryUpt.callBlock(varient + "_glass_door"), formatString(varient) + " Glass Door");
        }

        //Group (Item Group)
        
        translationBuilder.add("item.entstupidstuff." + "deco_group", "Decoration");
        translationBuilder.add("item.entstupidstuff." + "combat_group", "Modded Combat");
        translationBuilder.add("item.entstupidstuff." + "default_group", "Default (Everything)");
        translationBuilder.add("item.entstupidstuff." + "natural_group", "Natural");

        //Stone Varient

        translationBuilder.add(BlockFactoryUpt.callBlock("diorite_bricks"), "Diorite Bricks");
        translationBuilder.add(BlockFactoryUpt.callBlock("diorite_brick_slab"), "Diorite Brick Slab");
        translationBuilder.add(BlockFactoryUpt.callBlock("diorite_brick_stairs"), "Diorite Brick Stair");
        translationBuilder.add(BlockFactoryUpt.callBlock("diorite_brick_wall"), "Diorite Brick Wall");
        translationBuilder.add(BlockFactoryUpt.callBlock("diorite_brick_chiseled"), "Chiseled Diorite Brick");

        translationBuilder.add(BlockFactoryUpt.callBlock("andesite_bricks"), "Andesite Bricks");
        translationBuilder.add(BlockFactoryUpt.callBlock("andesite_brick_slab"), "Andesite Brick Slab");
        translationBuilder.add(BlockFactoryUpt.callBlock("andesite_brick_stairs"), "Andesite Brick Stair");
        translationBuilder.add(BlockFactoryUpt.callBlock("andesite_brick_wall"), "Andesite Brick Wall");
        translationBuilder.add(BlockFactoryUpt.callBlock("andesite_brick_chiseled"), "Chiseled Andesite Brick");

        translationBuilder.add(BlockFactoryUpt.callBlock("granite_bricks"), "Granite Bricks");
        translationBuilder.add(BlockFactoryUpt.callBlock("granite_brick_slab"), "Granite Brick Slab");
        translationBuilder.add(BlockFactoryUpt.callBlock("granite_brick_stairs"), "Granite Brick Stair");
        translationBuilder.add(BlockFactoryUpt.callBlock("granite_brick_wall"), "Granite Brick Wall");
        translationBuilder.add(BlockFactoryUpt.callBlock("granite_brick_chiseled"), "Chiseled Granite Brick");

        translationBuilder.add(BlockFactoryUpt.callBlock("polished_diorite_wall"), "Polished Diorite Wall");
        translationBuilder.add(BlockFactoryUpt.callBlock("polished_andesite_wall"), "Polished Andesite Wall");
        translationBuilder.add(BlockFactoryUpt.callBlock("polished_granite_wall"), "Polished Granite Wall");


        



       

        //translationBuilder.add("subtitles.ent.entity." + "sword_fish.death", "Sword Fish Dies");
        //translationBuilder.add("subtitles.ent.entity." + "sword_fish.hurt", "Sword Fish hurts ");
        //translationBuilder.add("subtitles.ent.entity." + "sword_fish.flop", "Sword Fish Flops");
        //translationBuilder.add("item.entstupidstuff." + "sword_fish", "Sword Fish");
        //translationBuilder.add("item.entstupidstuff." + "cooked_sword_fish", "Cooked Sword Fish");
        //translationBuilder.add("item.entstupidstuff." + "sword_fish_bucket", "Bucket of Sword Fish");
        
        
        
        


        
        
        
        



        








        

        

        /*translationBuilder.add(ItemFactory.callItem("wooden_dagger"), "Wooden Dagger");
        translationBuilder.add(ItemFactory.callItem("stone_dagger"), "Stone Dagger");
        translationBuilder.add(ItemFactory.callItem("iron_dagger"), "Iron Dagger");
        translationBuilder.add(ItemFactory.callItem("golden_dagger"), "Golden Dagger");
        translationBuilder.add(ItemFactory.callItem("diamond_dagger"), "Diamond Dagger");
        translationBuilder.add(ItemFactory.callItem("netherite_dagger"), "Netherite Dagger");
        */

        

        translationBuilder.add("item.entstupidstuff.double_hand.tooltip", "Double Handed");
        translationBuilder.add("item.entstupidstuff.blunt.tooltip", "Blunt");
        translationBuilder.add("item.entstupidstuff.bleeding.tooltip", "Bleed");

        //Trapdoor
        translationBuilder.add("block.entstupidstuff." + "oak" + "_glass_trapdoor", "Oak" + " Glass Trapdoor");
        translationBuilder.add("block.entstupidstuff." + "spruce" + "_glass_trapdoor", "Spruce" + " Glass Trapdoor");
        translationBuilder.add("block.entstupidstuff." + "jungle" + "_glass_trapdoor", "Jungle" + " Glass Trapdoor");
        translationBuilder.add("block.entstupidstuff." + "birch" + "_glass_trapdoor", "Birch" + " Glass Trapdoor");
        translationBuilder.add("block.entstupidstuff." + "dark_oak" + "_glass_trapdoor", "Dark Oak" + " Glass Trapdoor");
        translationBuilder.add("block.entstupidstuff." + "acacia" + "_glass_trapdoor", "Acacia" + " Glass Trapdoor");
        translationBuilder.add("block.entstupidstuff." + "mangrove" + "_glass_trapdoor", "Mangrove" + " Glass Trapdoor");
        translationBuilder.add("block.entstupidstuff." + "cherry" + "_glass_trapdoor", "Cherry" + " Glass Trapdoor"); 
        translationBuilder.add("block.entstupidstuff." + "bamboo" + "_glass_trapdoor", "Bamboo" + " Glass Trapdoor");
        translationBuilder.add("block.entstupidstuff." + "crimson" + "_glass_trapdoor", "Crimson" + " Glass Trapdoor");
        translationBuilder.add("block.entstupidstuff." + "warped" + "_glass_trapdoor", "Warped" + " Glass Trapdoor");
        translationBuilder.add("block.entstupidstuff." + "copper" + "_glass_trapdoor", "Copper" + " Glass Trapdoor");
        translationBuilder.add("block.entstupidstuff." + "exposed_copper" + "_glass_trapdoor", "Exposed Copper" + " Glass Trapdoor");
        translationBuilder.add("block.entstupidstuff." + "oxidized_copper" + "_glass_trapdoor", "Oxidized Copper" + " Glass Trapdoor");
        translationBuilder.add("block.entstupidstuff." + "weathered_copper" + "_glass_trapdoor", "Weathered Copper" + " Glass Trapdoor");

        translationBuilder.add("block.entstupidstuff." + "waxed_copper" + "_glass_trapdoor", "Waxed Copper" + " Glass Trapdoor");
        translationBuilder.add("block.entstupidstuff." + "waxed_exposed_copper" + "_glass_trapdoor", "Waxed Exposed Copper" + " Glass Trapdoor");
        translationBuilder.add("block.entstupidstuff." + "waxed_oxidized_copper" + "_glass_trapdoor", "Waxed Oxidized Copper" + " Glass Trapdoor");
        translationBuilder.add("block.entstupidstuff." + "waxed_weathered_copper" + "_glass_trapdoor", "Waxed Weathered Copper" + " Glass Trapdoor");





        //Redwood

        translationBuilder.add(BlockFactoryUpt.callBlock("redwood_log"), "Redwood Log");
        translationBuilder.add(BlockFactoryUpt.callBlock("stripped_redwood_log"), "Stripped Redwood Log");
        translationBuilder.add(BlockFactoryUpt.callBlock("redwood_wood"), "Redwood Wood");
        translationBuilder.add(BlockFactoryUpt.callBlock("stripped_redwood_wood"), "Stripped Redwood Wood");

        translationBuilder.add(BlockFactoryUpt.callBlock("redwood_planks"), "Redwood Planks");
        translationBuilder.add(BlockFactoryUpt.callBlock("redwood_stairs"), "Redwood Stairs");
        translationBuilder.add(BlockFactoryUpt.callBlock("redwood_slab"), "Redwood Slab");
        translationBuilder.add(BlockFactoryUpt.callBlock("redwood_fence"), "Redwood Fence");
        translationBuilder.add(BlockFactoryUpt.callBlock("redwood_fence_gate"), "Redwood Fence Gate");
        translationBuilder.add(BlockFactoryUpt.callBlock("redwood_door"), "Redwood Door");
        translationBuilder.add(BlockFactoryUpt.callBlock("redwood_trapdoor"), "Redwood Trapdoor");
        translationBuilder.add(BlockFactoryUpt.callBlock("redwood_pressure_plate"), "Redwood Pressure Plate");
        translationBuilder.add(BlockFactoryUpt.callBlock("redwood_button"), "Redwood Button");
        translationBuilder.add(BlockFactoryUpt.callBlock("redwood_glass_door"), "Redwood Glass Door");
        translationBuilder.add(BlockFactoryUpt.callBlock("redwood_glass_trapdoor"), "Redwood Glass trapdoor");
        
        translationBuilder.add(BlockFactoryUpt.callBlock("redwood_mosaic"), "Redwood Mosaic");
        translationBuilder.add(BlockFactoryUpt.callBlock("redwood_mosaic_stairs"), "Redwood Mosaic Stairs");
        translationBuilder.add(BlockFactoryUpt.callBlock("redwood_mosaic_slab"), "Redwood Mosaic Slab");

        // Desert Iron

        translationBuilder.add(BlockFactoryUpt.callBlock("desert_iron_log"), "Desert Iron Log");
        translationBuilder.add(BlockFactoryUpt.callBlock("stripped_desert_iron_log"), "Stripped Desert Iron Log");
        translationBuilder.add(BlockFactoryUpt.callBlock("desert_iron_wood"), "Desert Iron Wood");
        translationBuilder.add(BlockFactoryUpt.callBlock("stripped_desert_iron_wood"), "Stripped Desert Iron Log");

        translationBuilder.add(BlockFactoryUpt.callBlock("desert_iron_planks"), "Desert Iron Planks");
        translationBuilder.add(BlockFactoryUpt.callBlock("desert_iron_stairs"), "Desert Iron Stairs");
        translationBuilder.add(BlockFactoryUpt.callBlock("desert_iron_slab"), "Desert Iron Slab");
        translationBuilder.add(BlockFactoryUpt.callBlock("desert_iron_fence"), "Desert Iron Fence");
        translationBuilder.add(BlockFactoryUpt.callBlock("desert_iron_fence_gate"), "Desert Iron Fence Gate");
        translationBuilder.add(BlockFactoryUpt.callBlock("desert_iron_door"), "Desert Iron Door");
        translationBuilder.add(BlockFactoryUpt.callBlock("desert_iron_trapdoor"), "Desert Iron Trapdoor");
        translationBuilder.add(BlockFactoryUpt.callBlock("desert_iron_pressure_plate"), "Desert Iron Pressure Plate");
        translationBuilder.add(BlockFactoryUpt.callBlock("desert_iron_button"), "Desert Iron Button");
        translationBuilder.add(BlockFactoryUpt.callBlock("desert_iron_glass_door"), "Desert Iron Glass Door");
        translationBuilder.add(BlockFactoryUpt.callBlock("desert_iron_glass_trapdoor"), "Desert Iron Glass Trapdoor");

        //translationBuilder.add(BlockFactoryUpt.callBlock("desert_mosaic"), "Desert Mosaic");
        //translationBuilder.add(BlockFactoryUpt.callBlock("desert_mosaic_stairs"), "Desert Mosaic Stairs");
        //translationBuilder.add(BlockFactoryUpt.callBlock("desert_mosaic_slab"), "Desert Mosaic Slab");

        // Maple

        translationBuilder.add(BlockFactoryUpt.callBlock("maple_log"), "Maple Log");
        translationBuilder.add(BlockFactoryUpt.callBlock("stripped_maple_log"), "Stripped Maple Log");
        translationBuilder.add(BlockFactoryUpt.callBlock("maple_wood"), "Maple Wood");
        translationBuilder.add(BlockFactoryUpt.callBlock("stripped_maple_wood"), "Stripped Maple Wood");

        translationBuilder.add(BlockFactoryUpt.callBlock("maple_planks"), "Maple Planks");
        translationBuilder.add(BlockFactoryUpt.callBlock("maple_stairs"), "Maple Stairs");
        translationBuilder.add(BlockFactoryUpt.callBlock("maple_slab"), "Maple Slab");
        translationBuilder.add(BlockFactoryUpt.callBlock("maple_fence"), "Maple Fence");
        translationBuilder.add(BlockFactoryUpt.callBlock("maple_fence_gate"), "Maple Fence Gate");
        translationBuilder.add(BlockFactoryUpt.callBlock("maple_door"), "Maple Door");
        translationBuilder.add(BlockFactoryUpt.callBlock("maple_trapdoor"), "Maple Trapdoor");
        translationBuilder.add(BlockFactoryUpt.callBlock("maple_pressure_plate"), "Maple Pressure Plate");
        translationBuilder.add(BlockFactoryUpt.callBlock("maple_button"), "Maple Button");
        translationBuilder.add(BlockFactoryUpt.callBlock("maple_glass_door"), "Maple Glass Door");
        translationBuilder.add(BlockFactoryUpt.callBlock("maple_glass_trapdoor"), "Maple Glass trapdoor");

        translationBuilder.add(BlockFactoryUpt.callBlock("maple_mosaic"), "Maple Mosaic Planks");
        translationBuilder.add(BlockFactoryUpt.callBlock("maple_mosaic_stairs"), "Maple Mosaic Stairs");
        translationBuilder.add(BlockFactoryUpt.callBlock("maple_mosaic_slab"), "Maple Mosaic Slab");

        //Phantom

        translationBuilder.add(BlockFactoryUpt.callBlock("phantom_log"), "Phantom Log");
        translationBuilder.add(BlockFactoryUpt.callBlock("stripped_phantom_log"), "Stripped Phantom Log");
        translationBuilder.add(BlockFactoryUpt.callBlock("phantom_wood"), "Phantom Wood");
        translationBuilder.add(BlockFactoryUpt.callBlock("stripped_phantom_wood"), "Stripped Phantom Wood");

        translationBuilder.add(BlockFactoryUpt.callBlock("phantom_planks"), "Phantom Planks");
        translationBuilder.add(BlockFactoryUpt.callBlock("phantom_stairs"), "Phantom Stairs");
        translationBuilder.add(BlockFactoryUpt.callBlock("phantom_slab"), "Phantom Slab");
        translationBuilder.add(BlockFactoryUpt.callBlock("phantom_fence"), "Phantom Fence");
        translationBuilder.add(BlockFactoryUpt.callBlock("phantom_fence_gate"), "Phantom Fence Gate");
        translationBuilder.add(BlockFactoryUpt.callBlock("phantom_door"), "Phantom Door");
        translationBuilder.add(BlockFactoryUpt.callBlock("phantom_trapdoor"), "Phantom Trapdoor");
        translationBuilder.add(BlockFactoryUpt.callBlock("phantom_pressure_plate"), "Phantom Pressure Plate");
        translationBuilder.add(BlockFactoryUpt.callBlock("phantom_button"), "Phantom Button");
        translationBuilder.add(BlockFactoryUpt.callBlock("phantom_glass_door"), "Phantom Glass Door");
        translationBuilder.add(BlockFactoryUpt.callBlock("phantom_glass_trapdoor"), "Phantom Glass trapdoor");

        translationBuilder.add(BlockFactoryUpt.callBlock("phantom_mosaic"), "Phantom Mosaic Planks");
        translationBuilder.add(BlockFactoryUpt.callBlock("phantom_mosaic_stairs"), "Phantom Mosaic Stairs");
        translationBuilder.add(BlockFactoryUpt.callBlock("phantom_mosaic_slab"), "Phantom Mosaic Slab");

        /*translationBuilder.add(SIMPLE_ITEM, "Simple Item");
        translationBuilder.add(SIMPLE_BLOCK, "Simple Block");
        translationBuilder.add(SIMPLE_ITEM_GROUP, "Simple Item Group");*/
     
        // Load an existing language file.
        /*try {
            Path existingFilePath = dataOutput.getModContainer().findPath("assets/entstupidstuff/lang/en_us.existing.json").get();
            translationBuilder.add(existingFilePath);
        } catch (Exception e) {
            throw new RuntimeException("Failed to add existing language file!", e);
        }*/
    }

    public static String formatString(String input) {
    String[] parts = input.split("_");
    StringBuilder result = new StringBuilder();

    for (String part : parts) {
        if (!part.isEmpty()) {
            result.append(Character.toUpperCase(part.charAt(0)));
            result.append(part.substring(1).toLowerCase());
            result.append(" ");
        }
    }

    return result.toString().trim();
}

    public void addShield(String item, String FullName, TranslationBuilder translationBuilder) {
        
        translationBuilder.add("item.entstupidstuff." + item + "_shield", FullName + " Shield");

        translationBuilder.add("item.entstupidstuff." + item + "_shield" + ".white", "White " + FullName + "Shield");
        translationBuilder.add("item.entstupidstuff." + item + "_shield" + ".light_gray", "Light Gray " + FullName + "Shield");
        translationBuilder.add("item.entstupidstuff." + item + "_shield" + ".gray", "Gray " + FullName + "Shield");
        translationBuilder.add("item.entstupidstuff." + item + "_shield" + ".black", "Black " + FullName + "Shield");
        translationBuilder.add("item.entstupidstuff." + item + "_shield" + ".red", "Red " + FullName + "Shield");
        translationBuilder.add("item.entstupidstuff." + item + "_shield" + ".orange", "Orange " + FullName + "Shield");
        translationBuilder.add("item.entstupidstuff." + item + "_shield" + ".yellow", "Yellow " + FullName + "Shield");
        translationBuilder.add("item.entstupidstuff." + item + "_shield" + ".lime", "Lime " + FullName + "Shield");
        translationBuilder.add("item.entstupidstuff." + item + "_shield" + ".green", "Green " + FullName + "Shield");
        translationBuilder.add("item.entstupidstuff." + item + "_shield" + ".cyan", "Cyan " + FullName + "Shield");
        translationBuilder.add("item.entstupidstuff." + item + "_shield" + ".light_blue", "Light Blue " + FullName + "Shield");
        translationBuilder.add("item.entstupidstuff." + item + "_shield" + ".blue", "Blue " + FullName + "Shield");
        translationBuilder.add("item.entstupidstuff." + item + "_shield" + ".purple", "Purple " + FullName + "Shield");
        translationBuilder.add("item.entstupidstuff." + item + "_shield" + ".magenta", "Magenta " + FullName + "Shield");
        translationBuilder.add("item.entstupidstuff." + item + "_shield" + ".pink", "Pink " + FullName + "Shield");


    }
 
    

}
