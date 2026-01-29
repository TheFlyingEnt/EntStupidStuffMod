package net.ent.entstupidstuff.datagen;

import java.util.concurrent.CompletableFuture;

import net.ent.entstupidstuff.block.BlockFactory;
import net.ent.entstupidstuff.block.BlockGroupFactory;
import net.ent.entstupidstuff.block.ModBlocks;
import net.ent.entstupidstuff.effects.ModEffects;
import net.ent.entstupidstuff.item.ItemFactory;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.world.level.material.MapColor;

public class ModLangProvider extends FabricLanguageProvider  {

    public ModLangProvider(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(dataOutput, "en_us", registryLookup);
    }
    

    public ModLangProvider(FabricDataOutput dataOutput, String languageCode, CompletableFuture<Provider> registryLookup) {
        super(dataOutput, "en_us", registryLookup);
    }

    @Override
    public void generateTranslations(Provider registryLookup, TranslationBuilder translationBuilder) {

        // # Sounds

        translationBuilder.add("subtitles.ent." + "entity.metal_skeleton.ambient", "Metal Skeleton groans");
        translationBuilder.add("subtitles.ent." + "entity.metal_skeleton.converted_to_stray", "Metal Skeleton converts to Stray ");
        translationBuilder.add("subtitles.ent." + "entity.metal_skeleton.death", "Metal Skeleton dies");
        translationBuilder.add("subtitles.ent." + "entity.metal_skeleton.hurt", "Metal Skeleton hurts");
        translationBuilder.add("subtitles.ent." + "entity.metal_skeleton.shoot", "Metal Skeleton shoots ");
        translationBuilder.add("subtitles.ent." + "entity.metal_skeleton.step", "Footsteps");

        translationBuilder.add("subtitles.ent." + "combat.hammer.ground", "Hammer Smash");
        translationBuilder.add("subtitles.ent." + "combat.cannon.fire", "Cannon Fire");

        translationBuilder.add("subtitles.ent." + "entity.sporeper.explode", "Sporeper Explodes");
        translationBuilder.add("subtitles.ent." + "entity.generic.underwater_explode", "Underwater Explosion");

        translationBuilder.add("subtitles.ent." + "entity.lobber.ambient", "Zombie groans");
        translationBuilder.add("subtitles.ent." + "entity.lobber.converted_to_zombie", "Lobber converts to Zombie ");
        translationBuilder.add("subtitles.ent." + "entity.lobber.death", "Zombie dies");
        translationBuilder.add("subtitles.ent." + "entity.lobber.hurt", "Zombie hurts");
        translationBuilder.add("subtitles.ent." + "entity.lobber.step", "Footsteps");

        translationBuilder.add("subtitles.ent." + "entity.scorched.ambient", "Scorched groans");
        translationBuilder.add("subtitles.ent." + "entity.scorched.converted_to_zombie", "Scorched converts to Zombie ");
        translationBuilder.add("subtitles.ent." + "entity.scorched.death", "Scorched dies");
        translationBuilder.add("subtitles.ent." + "entity.scorched.hurt", "Scorched hurts");
        translationBuilder.add("subtitles.ent." + "entity.scorched.step", "Footsteps");

        translationBuilder.add("subtitles.ent." + "entity.slimed.ambient", "Slimed groans");
        translationBuilder.add("subtitles.ent." + "entity.slimed.converted_to_zombie", "Slimed converts to Zombie ");
        translationBuilder.add("subtitles.ent." + "entity.slimed.death", "Slimed dies");
        translationBuilder.add("subtitles.ent." + "entity.slimed.hurt", "Slimed hurts");
        translationBuilder.add("subtitles.ent." + "entity.slimed.step", "Footsteps");

        translationBuilder.add("subtitles.ent." + "entity.frostbitten.ambient", "Frostbitten groans");
        translationBuilder.add("subtitles.ent." + "entity.frostbitten.converted_to_zombie", "Frostbitten converts to Zombie ");
        translationBuilder.add("subtitles.ent." + "entity.frostbitten.death", "Frostbitten dies");
        translationBuilder.add("subtitles.ent." + "entity.frostbitten.hurt", "Frostbitten hurts");
        translationBuilder.add("subtitles.ent." + "entity.frostbitten.step", "Footsteps");

        translationBuilder.add("subtitles.ent." + "entity.fungal_skeleton.ambient", "Fungal Skeleton groans");
        translationBuilder.add("subtitles.ent." + "entity.fungal_skeleton.converted_to_stray", "Fungal Skeleton converts to Stray ");
        translationBuilder.add("subtitles.ent." + "entity.fungal_skeleton.death", "Fungal Skeleton dies");
        translationBuilder.add("subtitles.ent." + "entity.fungal_skeleton.hurt", "Fungal Skeleton hurts");
        translationBuilder.add("subtitles.ent." + "entity.fungal_skeleton.shoot", "Fungal Skeleton shoots ");
        translationBuilder.add("subtitles.ent." + "entity.fungal_skeleton.step", "Footsteps");

        translationBuilder.add("subtitles.ent." + "entity.phantom_skeleton.ambient", "Phantom Skeleton groans");
        translationBuilder.add("subtitles.ent." + "entity.phantom_skeleton.converted_to_stray", "Phantom Skeleton converts to Stray ");
        translationBuilder.add("subtitles.ent." + "entity.phantom_skeleton.death", "Phantom Skeleton dies");
        translationBuilder.add("subtitles.ent." + "entity.phantom_skeleton.hurt", "Phantom Skeleton hurts");
        translationBuilder.add("subtitles.ent." + "entity.phantom_skeleton.shoot", "Phantom Skeleton shoots ");
        translationBuilder.add("subtitles.ent." + "entity.phantom_skeleton.step", "Footsteps");

        translationBuilder.add("subtitles.ent." + "entity.alligator_gar.death", "Alligator Gar dies");
        translationBuilder.add("subtitles.ent." + "entity.alligator_gar.hurt", "Alligator Gar hurts");
        translationBuilder.add("subtitles.ent." + "entity.alligator_gar.flop", "Alligator Gar flops");

        translationBuilder.add("subtitles.ent." + "entity.mackerel.death", "Mackerel dies");
        translationBuilder.add("subtitles.ent." + "entity.mackerel.hurt", "Mackerel hurts");
        translationBuilder.add("subtitles.ent." + "entity.mackerel.flop", "Mackerel flops");

        translationBuilder.add("subtitles.ent." + "entity.zebra_fish.death", "Zebra Fish dies");
        translationBuilder.add("subtitles.ent." + "entity.zebra_fish.hurt", "Zebra Fish hurts");
        translationBuilder.add("subtitles.ent." + "entity.zebra_fish.flop", "Zebra Fish flops");

        translationBuilder.add("subtitles.ent." + "entity.bass.death", "Bass dies");
        translationBuilder.add("subtitles.ent." + "entity.bass.hurt", "Bass hurts");
        translationBuilder.add("subtitles.ent." + "entity.bass.flop", "Bass flops");

        translationBuilder.add("subtitles.ent." + "entity.perch.death", "Perch dies");
        translationBuilder.add("subtitles.ent." + "entity.perch.hurt", "Perch hurts");
        translationBuilder.add("subtitles.ent." + "entity.perch.flop", "Perch flops");

        translationBuilder.add("subtitles.ent." + "entity.snapper.death", "Snapper dies");
        translationBuilder.add("subtitles.ent." + "entity.snapper.hurt", "Snapper hurts");
        translationBuilder.add("subtitles.ent." + "entity.snapper.flop", "Snapper flops");

        translationBuilder.add("subtitles.ent." + "entity.koi.death", "Koi dies");
        translationBuilder.add("subtitles.ent." + "entity.koi.hurt", "Koi hurts");
        translationBuilder.add("subtitles.ent." + "entity.koi.flop", "Koi flops");

        translationBuilder.add("subtitles.ent." + "entity.mahimahi.death", "Mahi Mahi dies");
        translationBuilder.add("subtitles.ent." + "entity.mahimahi.hurt", "Mahi Mahi hurts");
        translationBuilder.add("subtitles.ent." + "entity.mahimahi.flop", "Mahi Mahi flops");

        translationBuilder.add("subtitles.ent." + "entity.fur_trout.death", "Fur Trout dies");
        translationBuilder.add("subtitles.ent." + "entity.fur_trout.hurt", "Fur Trout hurts");
        translationBuilder.add("subtitles.ent." + "entity.fur_trout.flop", "Fur Trout flops");

        translationBuilder.add("subtitles.ent." + "entity.lepord_shark.death", "Lepord Shark dies");
        translationBuilder.add("subtitles.ent." + "entity.lepord_shark.hurt", "Lepord Shark hurts");
        translationBuilder.add("subtitles.ent." + "entity.lepord_shark.flop", "Lepord Shark flops");


        // ## Blocks

        translationBuilder.add(BlockFactory.callBlock("redwood" + "_planks"), "Redwood Planks");
        groupWoodFamilty(translationBuilder, "redwood", "", "Redwood", "");
        addSaplings(translationBuilder, "redwood", "Redwood");

        translationBuilder.add(BlockFactory.callBlock("fir" + "_planks"), "Fir Planks");
        groupWoodFamilty(translationBuilder, "fir", "", "Fir", "");
        addSaplings(translationBuilder, "fir", "Fir");

        translationBuilder.add(BlockFactory.callBlock("maple" + "_planks"), "Maple Planks");
        groupWoodFamilty(translationBuilder, "maple", "", "Maple", "");
        addSaplings(translationBuilder, "maple", "Maple");
        translationBuilder.add(BlockFactory.callBlock("orange_petals"), "Orange Petals");

        translationBuilder.add(BlockFactory.callBlock("phantom" + "_planks"), "Phantom Planks");
        groupWoodFamilty(translationBuilder, "phantom", "", "Phantom", "");
        translationBuilder.add(BlockFactory.callBlock("phantom_lantern"), "Phantom Lantern");

        //groupFungalFamily(translationBuilder, "fungal", "Fungal");
        groupFungalFamily(translationBuilder, "", "");
        groupFungalFamily(translationBuilder, "_white", "White ");
        groupFungalFamily(translationBuilder, "_light_gray", "Light Gray ");
        groupFungalFamily(translationBuilder, "_gray", "Gray ");
        groupFungalFamily(translationBuilder, "_black", "Black ");
        groupFungalFamily(translationBuilder, "_brown", "Brown ");
        groupFungalFamily(translationBuilder, "_red", "Red ");
        groupFungalFamily(translationBuilder, "_orange", "Orange ");
        groupFungalFamily(translationBuilder, "_yellow", "Yellow ");
        groupFungalFamily(translationBuilder, "_lime", "Lime ");
        groupFungalFamily(translationBuilder, "_green", "Green ");
        groupFungalFamily(translationBuilder, "_cyan", "Cyan ");
        groupFungalFamily(translationBuilder, "_light_blue", "Light Blue ");
        groupFungalFamily(translationBuilder, "_blue", "Blue ");
        groupFungalFamily(translationBuilder, "_purple", "Purple ");
        groupFungalFamily(translationBuilder, "_magenta", "Magenta ");
        groupFungalFamily(translationBuilder, "_pink", "Pink ");

        translationBuilder.add(BlockFactory.callBlock("blue_mushroom"), "Blue Mushroom");
        translationBuilder.add(BlockFactory.callBlock("potted_blue_mushroom"), "Potted Blue Mushroom");
        translationBuilder.add(BlockFactory.callBlock("blue_mushroom_block"), "Blue Mushroom Block");
        translationBuilder.add(BlockFactory.callBlock("blue_crystal_block"), "Blue Crystal Block");
        translationBuilder.add(BlockFactory.callBlock("azure_flower_bed"), "Azure Flower Bed");
        translationBuilder.add(BlockFactory.callBlock("fungal_spore_blossom"), "Fungal Spore Blossom");

        // # Adding Andersite, Diorite and Granite

        translationBuilder.add(BlockFactory.callBlock("andesite_bricks"), "Andesite Bricks");
        groupStoneFamily(translationBuilder, "andesite_brick", "Andesite Brick", true);
        translationBuilder.add(BlockFactory.callBlock("polished_andesite" + "_wall"), "Polished Andesite Wall");

        translationBuilder.add(BlockFactory.callBlock("granite_bricks"), "Andesite Bricks");
        groupStoneFamily(translationBuilder, "granite_brick", "Andesite Brick", true);
        translationBuilder.add(BlockFactory.callBlock("polished_granite" + "_wall"), "Polished Andesite Wall");

        translationBuilder.add(BlockFactory.callBlock("diorite_bricks"), "Diorite Bricks");
        groupStoneFamily(translationBuilder, "diorite_brick", "Diorite Brick", true);
        translationBuilder.add(BlockFactory.callBlock("polished_diorite" + "_wall"), "Polished Diorite Wall");

        // # Adding Limestone and Limestone Bricks

        translationBuilder.add(BlockFactory.callBlock("limestone"), "Limestone");
        groupStoneFamily(translationBuilder, "limestone", "Limestone", false);

        translationBuilder.add(BlockFactory.callBlock("polished_limestone"), "Polished Limestone");
        groupStoneFamily(translationBuilder, "polished_limestone", "Polished Limestone", false);

        translationBuilder.add(BlockFactory.callBlock("polished_limestone_bricks"), "Polished Limestone Bricks");
        groupStoneFamily(translationBuilder, "polished_limestone_brick", "Polished Limestone Brick", true);

        // # Adding IronGates
        translationBuilder.add(BlockFactory.callBlock("iron_grate"), "Iron Grate");
        translationBuilder.add(BlockFactory.callBlock("iron_grate_stairs"), "Iron Grate Stairs");
        translationBuilder.add(BlockFactory.callBlock("iron_grate_slab"), "Iron Grate Slab");

        // # Adding StringGates

        translationBuilder.add(BlockFactory.callBlock("string_gate"), "String Gate");
        translationBuilder.add(BlockFactory.callBlock("string_block"), "String Block");

        // # Adding Abyssal Stone and Abyssal Stone Bricks

        groupStoneFamily(translationBuilder, "abyssal_stone", "Abyssal Stone", false);

        translationBuilder.add(BlockFactory.callBlock("polished_abyssal_stone"), "Polished Abyssal Stone");
        groupStoneFamily(translationBuilder, "polished_abyssal_stone", "Polished Abyssal Stone", false);

        translationBuilder.add(BlockFactory.callBlock("polished_abyssal_stone_bricks"), "Polished Abyssal Stone Bricks");
        groupStoneFamily(translationBuilder, "polished_abyssal_stone_brick", "Polished Abyssal Stone Brick", true);
        translationBuilder.add(BlockFactory.callBlock("polished_abyssal_stone_seaweed"), "Polished Abyssal Seeweed");

        translationBuilder.add(BlockFactory.callBlock("thalassite_ore"), "Thalassite Ore");
        translationBuilder.add(BlockFactory.callBlock("thalassite_block"), "Block of Thalassite");

        translationBuilder.add(BlockFactory.callBlock("hardend_sandstone"), "Hard Sandstone");
        groupStoneFamily(translationBuilder, "hardend_sandstone", "Hard Sandstone", false);

        translationBuilder.add(BlockFactory.callBlock("date"), "Date");

        // # Oxidizable

        translationBuilder.add(BlockFactory.callBlock("copper" + "_glass_trapdoor"), "Copper Glass Trapdoor");
        translationBuilder.add(BlockFactory.callBlock("exposed_copper" + "_glass_trapdoor"), "Exposed Copper Glass Trapdoor");
        translationBuilder.add(BlockFactory.callBlock("oxidized_copper" + "_glass_trapdoor"), "Oxidized Copper Glass Trapdoor");
        translationBuilder.add(BlockFactory.callBlock("weathered_copper" + "_glass_trapdoor"), "Weathered Copper Glass Trapdoor");

        translationBuilder.add(BlockFactory.callBlock("waxed_copper" + "_glass_trapdoor"), "Waxed Copper Glass Trapdoor");
        translationBuilder.add(BlockFactory.callBlock("waxed_exposed_copper" + "_glass_trapdoor"), "Waxed Exposed Copper Glass Trapdoor");
        translationBuilder.add(BlockFactory.callBlock("waxed_oxidized_copper" + "_glass_trapdoor"), "Waxed Oxidized Copper Glass Trapdoor");
        translationBuilder.add(BlockFactory.callBlock("waxed_weathered_copper" + "_glass_trapdoor"), "Waxed Weathered Copper Glass Trapdoor");

        translationBuilder.add(BlockFactory.callBlock("copper" + "_glass_door"), "Copper Glass Door");
        translationBuilder.add(BlockFactory.callBlock("exposed_copper" + "_glass_door"), "Exposed Copper Glass Door");
        translationBuilder.add(BlockFactory.callBlock("oxidized_copper" + "_glass_door"), "Oxidized Copper Glass Door");
        translationBuilder.add(BlockFactory.callBlock("weathered_copper" + "_glass_door"), "Weathered Copper Glass Door");

        translationBuilder.add(BlockFactory.callBlock("waxed_copper" + "_glass_door"), "Waxed Copper Glass Door");
        translationBuilder.add(BlockFactory.callBlock("waxed_exposed_copper" + "_glass_door"), "Waxed Exposed Copper Glass Door");
        translationBuilder.add(BlockFactory.callBlock("waxed_oxidized_copper" + "_glass_door"), "Waxed Oxidized Copper Glass Door");
        translationBuilder.add(BlockFactory.callBlock("waxed_weathered_copper" + "_glass_door"), "Waxed Weathered Copper Glass Door");

        // # Add Vanilla Glass Door + Glass Trapdoor

        translationBuilder.add(BlockFactory.callBlock("oak_glass_door"), "Oak Glass Door");
        translationBuilder.add(BlockFactory.callBlock("spruce_glass_door"), "Oak Glass Door");
        translationBuilder.add(BlockFactory.callBlock("jungle_glass_door"), "Jungle Glass Door");
        translationBuilder.add(BlockFactory.callBlock("birch_glass_door"), "Birch Glass Door");
        translationBuilder.add(BlockFactory.callBlock("dark_oak_glass_door"), "Dark Oak Glass Door");
        translationBuilder.add(BlockFactory.callBlock("acacia_glass_door"), "Acacia Glass Door");
        translationBuilder.add(BlockFactory.callBlock("mangrove_glass_door"), "Mangrove Glass Door");
        translationBuilder.add(BlockFactory.callBlock("cherry_glass_door"), "Cherry Glass Door");
        translationBuilder.add(BlockFactory.callBlock("bamboo_glass_door"), "Bamboo Glass Door");
        translationBuilder.add(BlockFactory.callBlock("pale_oak_glass_door"), "Pale Oak Glass Door");
        translationBuilder.add(BlockFactory.callBlock("crimson_glass_door"), "Crimson Glass Door");
        translationBuilder.add(BlockFactory.callBlock("warped_glass_door"), "Warped Glass Door");

        translationBuilder.add(BlockFactory.callBlock("oak_glass_trapdoor"), "Oak Glass Trapdoor");
        translationBuilder.add(BlockFactory.callBlock("spruce_glass_trapdoor"), "Oak Glass Trapdoor");
        translationBuilder.add(BlockFactory.callBlock("jungle_glass_trapdoor"), "Jungle Glass Trapdoor");
        translationBuilder.add(BlockFactory.callBlock("birch_glass_trapdoor"), "Birch Glass Trapdoor");
        translationBuilder.add(BlockFactory.callBlock("dark_oak_glass_trapdoor"), "Dark Oak Glass Trapdoor");
        translationBuilder.add(BlockFactory.callBlock("acacia_glass_trapdoor"), "Acacia Glass Trapdoor");
        translationBuilder.add(BlockFactory.callBlock("mangrove_glass_trapdoor"), "Mangrove Glass Trapdoor");
        translationBuilder.add(BlockFactory.callBlock("cherry_glass_trapdoor"), "Cherry Glass Trapdoor");
        translationBuilder.add(BlockFactory.callBlock("bamboo_glass_trapdoor"), "Bamboo Glass Trapdoor");
        translationBuilder.add(BlockFactory.callBlock("pale_oak_glass_trapdoor"), "Pale Oak Glass Trapdoor");
        translationBuilder.add(BlockFactory.callBlock("crimson_glass_trapdoor"), "Crimson Glass Trapdoor");
        translationBuilder.add(BlockFactory.callBlock("warped_glass_trapdoor"), "Warped Glass Trapdoor");

        // # Add Vanilla Mosic

        translationBuilder.add(BlockFactory.callBlock("oak" + "_mosaic"), "Oak" + " Mosaic");
        translationBuilder.add(BlockFactory.callBlock("oak" + "_mosaic_stairs"), "Oak" + "Mosaic Stairs");
        translationBuilder.add(BlockFactory.callBlock("oak" + "_mosaic_slab"), "Oak" + "Mosaic Slab");

        translationBuilder.add(BlockFactory.callBlock("spruce" + "_mosaic"), "Spruce" + " Mosaic");
        translationBuilder.add(BlockFactory.callBlock("spruce" + "_mosaic_stairs"), "Spruce" + "Mosaic Stairs");
        translationBuilder.add(BlockFactory.callBlock("spruce" + "_mosaic_slab"), "Spruce" + "Mosaic Slab");

        translationBuilder.add(BlockFactory.callBlock("jungle" + "_mosaic"), "Jungle" + " Mosaic");
        translationBuilder.add(BlockFactory.callBlock("jungle" + "_mosaic_stairs"), "Jungle" + "Mosaic Stairs");
        translationBuilder.add(BlockFactory.callBlock("jungle" + "_mosaic_slab"), "Jungle" + "Mosaic Slab");

        translationBuilder.add(BlockFactory.callBlock("birch" + "_mosaic"), "Birch" + " Mosaic");
        translationBuilder.add(BlockFactory.callBlock("birch" + "_mosaic_stairs"), "Birch" + "Mosaic Stairs");
        translationBuilder.add(BlockFactory.callBlock("birch" + "_mosaic_slab"), "Birch" + "Mosaic Slab");

        translationBuilder.add(BlockFactory.callBlock("dark_oak" + "_mosaic"), "Dark Oak" + " Mosaic");
        translationBuilder.add(BlockFactory.callBlock("dark_oak" + "_mosaic_stairs"), "Dark Oak" + "Mosaic Stairs");
        translationBuilder.add(BlockFactory.callBlock("dark_oak" + "_mosaic_slab"), "Dark Oak" + "Mosaic Slab");

        translationBuilder.add(BlockFactory.callBlock("acacia" + "_mosaic"), "Acacia" + " Mosaic");
        translationBuilder.add(BlockFactory.callBlock("acacia" + "_mosaic_stairs"), "Acacia" + "Mosaic Stairs");
        translationBuilder.add(BlockFactory.callBlock("acacia" + "_mosaic_slab"), "Acacia" + "Mosaic Slab");

        translationBuilder.add(BlockFactory.callBlock("mangrove" + "_mosaic"), "Mangrove" + " Mosaic");
        translationBuilder.add(BlockFactory.callBlock("mangrove" + "_mosaic_stairs"), "Mangrove" + "Mosaic Stairs");
        translationBuilder.add(BlockFactory.callBlock("mangrove" + "_mosaic_slab"), "Mangrove" + "Mosaic Slab");

        translationBuilder.add(BlockFactory.callBlock("cherry" + "_mosaic"), "Cherry" + " Mosaic");
        translationBuilder.add(BlockFactory.callBlock("cherry" + "_mosaic_stairs"), "Cherry" + "Mosaic Stairs");
        translationBuilder.add(BlockFactory.callBlock("cherry" + "_mosaic_slab"), "Cherry" + "Mosaic Slab");

        translationBuilder.add(BlockFactory.callBlock("pale_oak" + "_mosaic"), "Pale Oak" + " Mosaic");
        translationBuilder.add(BlockFactory.callBlock("pale_oak" + "_mosaic_stairs"), "Pale Oak" + "Mosaic Stairs");
        translationBuilder.add(BlockFactory.callBlock("pale_oak" + "_mosaic_slab"), "Pale Oak" + "Mosaic Slab");

        translationBuilder.add(BlockFactory.callBlock("crimson" + "_mosaic"), "Crimson" + " Mosaic");
        translationBuilder.add(BlockFactory.callBlock("crimson" + "_mosaic_stairs"), "Crimson" + "Mosaic Stairs");
        translationBuilder.add(BlockFactory.callBlock("crimson" + "_mosaic_slab"), "Crimson" + "Mosaic Slab");

        translationBuilder.add(BlockFactory.callBlock("warped" + "_mosaic"), "Warped" + " Mosaic");
        translationBuilder.add(BlockFactory.callBlock("warped" + "_mosaic_stairs"), "Warped" + "Mosaic Stairs");
        translationBuilder.add(BlockFactory.callBlock("warped" + "_mosaic_slab"), "Warped" + "Mosaic Slab");

        translationBuilder.add(BlockFactory.callBlock("phantom_torch"), "Phantom Torch");
        //translationBuilder.add(BlockFactory.callBlock("phantom_wall_torch"), "Phantom Wall Torch (WHY DO YOU HAVE THIS?)");
        translationBuilder.add(BlockFactory.callBlock("pointed_ice"), "Pointed Ice");
        translationBuilder.add(BlockFactory.callBlock("abyssal_stone"), "Abyssal Stone");
        translationBuilder.add(BlockFactory.callBlock("shroomium"), "Shroomium");
        translationBuilder.add(BlockFactory.callBlock("mushroom_aura_block"), "Fungal Spore Emitter");

        // ### Items

        translationBuilder.add(ItemFactory.MARSHMELLOW_RAW, "Marshmellow on a Stick");
        translationBuilder.add(ItemFactory.MARSHMELLOW_TOASTED, "Toasted Marshmellow on a Stick");
        translationBuilder.add(ItemFactory.BAGGUETTE, "La Baguette");

        translationBuilder.add(ItemFactory.BUTTERFLY_JAR, "Butterfly in a Jar");
        translationBuilder.add(ItemFactory.BUTTERFLY_SPAWN_EGG, "Butterfly Spawn Egg");

        translationBuilder.add(ItemFactory.ZOMBIE_LOBBER_SPAWN_EGG, "Lobber Spawn Egg");
        translationBuilder.add(ItemFactory.ZOMBIE_SCORCHED_SPAWN_EGG, "Scorched Spawn Egg");
        translationBuilder.add(ItemFactory.ZOMBIE_FROSTBITE_SPAWN_EGG, "Frostbitten Spawn Egg");
        translationBuilder.add(ItemFactory.ZOMBIE_SLIMED_SPAWN_EGG, "Slimed Spawn Egg");
        translationBuilder.add(ItemFactory.ARMORED_PILLAGER_SPAWN_EGG, "Armored Pillager Spawn Egg");

        translationBuilder.add(ItemFactory.GLOWING_SILK_STRING, "Glowing Silk String");

        translationBuilder.add(ItemFactory.WITHER_BONE, "Glowing Silk String");
        translationBuilder.add(ItemFactory.ANCIENT_DEBRIS_NUGGET, "Glowing Silk String");
        translationBuilder.add(ItemFactory.PIGLIN_WARRIOR_SPAWN, "Glowing Silk String");
        translationBuilder.add(ItemFactory.BLAZING_INFERNO_SPAWN, "Glowing Silk String");
        translationBuilder.add(ItemFactory.SOUL_SKELETON_SPAWN, "Glowing Silk String");

        translationBuilder.add(ItemFactory.ZEBRA_FISH, "Zebra Fish");
        translationBuilder.add(ItemFactory.ZEBRA_FISH_BUCKET, "Zebra Fish bucket");
        translationBuilder.add(ItemFactory.ZEBRA_FISH_SPAWN_EGG, "Zebra Fish Spawn Egg");
        
        translationBuilder.add(ItemFactory.ALLIGATOR_GAR_BUCKET, "Alligator Gar Bucket");
        translationBuilder.add(ItemFactory.ALLIGATOR_GAR, "Alligator Gar");
        translationBuilder.add(ItemFactory.COOKED_ALLIGATOR_GAR, "Cooked Alligator Gar");
        translationBuilder.add(ItemFactory.ALLIGATOR_GAR_SPAWN_EGG, "Alligator Gar Spawn Egg");

        translationBuilder.add(ItemFactory.MACKEREL_BUCKET, "Mackerel bucket");
        translationBuilder.add(ItemFactory.MACKEREL, "Mackerel");
        translationBuilder.add(ItemFactory.COOKED_MACKEREL, "Cooked Mackerel");
        translationBuilder.add(ItemFactory.MACKEREL_SPAWN_EGG, "Mackerel Spawn Egg");
        
        translationBuilder.add(ItemFactory.BASS_BUCKET, "Bass bucket");
        translationBuilder.add(ItemFactory.BASS, "Bass");
        translationBuilder.add(ItemFactory.COOKED_BASS, "Cooked Bass");
        translationBuilder.add(ItemFactory.BASS_SPAWN_EGG, "Bass Spawn Egg");
        
        translationBuilder.add(ItemFactory.FUR_TROUT_BUCKET, "Fur Trout Bucket");
        translationBuilder.add(ItemFactory.FUR_TROUT_SPAWN_EGG, "Fur Trout Spawn Egg");

        translationBuilder.add(ItemFactory.KOI_BUCKET, "Koi Bucket");
        translationBuilder.add(ItemFactory.KOI, "Koi");
        translationBuilder.add(ItemFactory.KOI_SPAWN_EGG, "Koi Spawn Egg");
        
        translationBuilder.add(ItemFactory.PERCH_BUCKET, "Perch Bucket");
        translationBuilder.add(ItemFactory.PERCH, "Perch");
        translationBuilder.add(ItemFactory.COOKED_PERCH, "Cooked Perch");
        translationBuilder.add(ItemFactory.PERCH_SPAWN_EGG, "Perch Spawn Egg");
        
        translationBuilder.add(ItemFactory.MAHIMAHI_BUCKET, "Mahi-Mahi Bucket");
        translationBuilder.add(ItemFactory.MAHIMAHI, "Mahi-Mahi");
        translationBuilder.add(ItemFactory.COOKED_MAHIMAHI, "Cooked Mahi-Mahi");
        translationBuilder.add(ItemFactory.MAHIMAHI_SPAWN_EGG, "Mahi-Mahi Spawn Egg");

        translationBuilder.add(ItemFactory.SNAPPER_BUCKET, "Snapper bucket");
        translationBuilder.add(ItemFactory.SNAPPER, "Snapper");
        translationBuilder.add(ItemFactory.COOKED_SNAPPER, "Cooked Snapper");
        translationBuilder.add(ItemFactory.SNAPPER_SPAWN_EGG, "Snapper Spawn Egg");
        
        translationBuilder.add(ItemFactory.RUM, "Bottle of Rum");
        translationBuilder.add(ItemFactory.CANNON_BALL_ITEM, "Cannonball");
        translationBuilder.add(ItemFactory.CANNON_ITEM, "Cannon");
        translationBuilder.add(ItemFactory.PRISMERINE_ARROW, "(DEVITEM) Prismerine Arrow");
        translationBuilder.add(ItemFactory.FLINTLOCK_CROSSBOW, "Flintlock Crossbow");
        translationBuilder.add(ItemFactory.DOUBLE_BARREL_CROSSBOW, "Double Barrel Crossbow");
        translationBuilder.add(ItemFactory.DUMMY_CROSSBOW, "(DEVITEM) DUMMY CROSSBOW");

        translationBuilder.add(ItemFactory.SUNKEN_SKELETON_SPAWN, "Sunken Skeleton Spawn Egg");
        translationBuilder.add(ItemFactory.SUNKEN_SKELETON2_SPAWN, "Sunken Skeleton (Crossbow) Spawn Egg");
        translationBuilder.add(ItemFactory.SKELETON_PIRATE_CAPTAIN_SPAWN, "Skeleton Pirate Spawn Egg");
        translationBuilder.add(ItemFactory.METAL_SKELETON_SPAWN, "Metallic Skeleton Spawn Egg");

        translationBuilder.add(ItemFactory.ANCIENT_DROWN_SPAWN, "Ancient Drown Spawn Egg");
        translationBuilder.add(ItemFactory.ANCIENT_TRIDENT, "Ancient Trident Spawn Egg");

        translationBuilder.add(ItemFactory.PHANTOM_SKELETON_SPAWN, "Phantom Skeleton Spawn Egg");

        translationBuilder.add(ItemFactory.WOODEN_HAMMER, "Wooden Hammer");
        translationBuilder.add(ItemFactory.STONE_HAMMER, "Stone Hammer");
        translationBuilder.add(ItemFactory.GOLDEN_HAMMER, "Golden Hammer");
        translationBuilder.add(ItemFactory.COPPER_HAMMER, "Copper Hammer");
        translationBuilder.add(ItemFactory.IRON_HAMMER, "Iron Hammer");
        translationBuilder.add(ItemFactory.DIAMOND_HAMMER, "Diamond Hammer");
        translationBuilder.add(ItemFactory.NETHERITE_HAMMER, "Netherite Hammer");

        // # Potion Effects
        translationBuilder.add("effect.entstupidstuff.hallucinating", "Hallucinating");
        translationBuilder.add("effect.entstupidstuff.rgb_shift", "RGB Shift");
        translationBuilder.add("effect.entstupidstuff.creeper", "Creeper");





        // === Legacy Subtitle === //

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


        //Group (Item Group)
        
        translationBuilder.add("item.entstupidstuff." + "deco_group", "Decoration");
        translationBuilder.add("item.entstupidstuff." + "combat_group", "Modded Combat");
        translationBuilder.add("item.entstupidstuff." + "default_group", "Default (Everything)");
        translationBuilder.add("item.entstupidstuff." + "natural_group", "Natural");

        //tooltip

        translationBuilder.add("item.entstupidstuff.double_hand.tooltip", "Double Handed");
        translationBuilder.add("item.entstupidstuff.blunt.tooltip", "Blunt");
        translationBuilder.add("item.entstupidstuff.bleeding.tooltip", "Bleed");

        
    }

    public void groupWoodFamilty(TranslationBuilder translationBuilder, String Main, String Varient, String MainString, String VarientString) {
        BaseFamily(translationBuilder, Main, Varient, MainString, VarientString);
        InteractionFamily(translationBuilder, Main, Varient, MainString, VarientString);
        NatureFamily(translationBuilder, Main, Varient, MainString, VarientString, true);
        MosicFamily(translationBuilder, Main, Varient, MainString, VarientString);
    }

    public void BaseFamily(TranslationBuilder translationBuilder, String blockName, String varient, String MainString, String VarientString) {
        translationBuilder.add(BlockFactory.callBlock(blockName + "_stairs" + varient), VarientString + MainString + " Stairs");
        translationBuilder.add(BlockFactory.callBlock(blockName + "_slab" + varient), VarientString + MainString + " Slab");
    }

    public void InteractionFamily(TranslationBuilder translationBuilder, String blockName, String varient, String MainString, String VarientString) {
        translationBuilder.add(BlockFactory.callBlock(blockName + "_fence" + varient), VarientString + MainString + " Fence");
        translationBuilder.add(BlockFactory.callBlock(blockName + "_fence_gate" + varient), VarientString + MainString + " Fence Gate");
        translationBuilder.add(BlockFactory.callBlock(blockName + "_door" + varient), VarientString + MainString + " Door");
        translationBuilder.add(BlockFactory.callBlock(blockName + "_glass_door" + varient), VarientString + MainString + " Glass Door");
        translationBuilder.add(BlockFactory.callBlock(blockName + "_trapdoor" + varient), VarientString + MainString + " Trapdoor");
        translationBuilder.add(BlockFactory.callBlock(blockName + "_glass_trapdoor" + varient), VarientString + MainString + " Glass Trapdoor");
        translationBuilder.add(BlockFactory.callBlock(blockName + "_pressure_plate" + varient), VarientString + MainString + " Pressure Plate");
        translationBuilder.add(BlockFactory.callBlock(blockName + "_button" + varient), VarientString + MainString + " Button");
    }

    public void NatureFamily(TranslationBuilder translationBuilder, String blockName, String varient, String MainString, String VarientString, Boolean withLeaves) {
        translationBuilder.add(BlockFactory.callBlock(blockName + "_log" + varient), VarientString + MainString + " Log");
        translationBuilder.add(BlockFactory.callBlock("stripped_" + blockName + "_log" + varient), "Stripped" + VarientString + MainString + " Log");
        translationBuilder.add(BlockFactory.callBlock(blockName + "_wood" + varient), VarientString + MainString + " Wood");
        translationBuilder.add(BlockFactory.callBlock("stripped_" + blockName + "_wood" + varient), "Stripped" + VarientString + MainString + " Wood");
    
        if (withLeaves) {
            translationBuilder.add(BlockFactory.callBlock(blockName + "_leaves" + varient), VarientString + MainString + " Leaves");
        }
    
    }

    public void addSaplings(TranslationBuilder translationBuilder, String blockName, String MainString) {
        translationBuilder.add(BlockFactory.callBlock(blockName + "_sapling"), MainString + " Sappling");
    }

    public void MosicFamily(TranslationBuilder translationBuilder, String blockName, String varient, String MainString, String VarientString) {
        translationBuilder.add(BlockFactory.callBlock(blockName + "_mosaic" + varient), VarientString + MainString + " Mosaic");
        translationBuilder.add(BlockFactory.callBlock(blockName + "_mosaic_stairs" + varient), VarientString + MainString + " Mosaic Stairs");
        translationBuilder.add(BlockFactory.callBlock(blockName + "_mosaic_slab" + varient), VarientString + MainString + " Mosaic Slab");
    } 

    public void groupFungalFamily(TranslationBuilder translationBuilder, String Varient, String VarientString) {
        BaseFamily(translationBuilder, "fungal", Varient, "Fungal", VarientString);
        InteractionFamily(translationBuilder, "fungal", Varient, "Fungal", VarientString);
        MosicFamily(translationBuilder, "fungal", Varient, "Fungal", VarientString);
    }

    public void groupStoneFamily(TranslationBuilder translationBuilder, String blockName, String MainString, Boolean Bricks) {
        BaseFamily(translationBuilder, blockName, "", MainString, "");
        translationBuilder.add(BlockFactory.callBlock(blockName + "_wall"), "" + MainString + " Wall");

        if (Bricks) {
            translationBuilder.add(BlockFactory.callBlock(blockName + "_chiseled"), "" + MainString + " Mosaic Stairs");
            translationBuilder.add(BlockFactory.callBlock("cracked_" + blockName + "s"), "Cracked " + "" + MainString + "s");
        }

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
