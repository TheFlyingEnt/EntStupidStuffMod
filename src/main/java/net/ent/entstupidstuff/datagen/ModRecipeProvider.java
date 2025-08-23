package net.ent.entstupidstuff.datagen;

import java.util.concurrent.CompletableFuture;
import java.util.ArrayList;

import net.ent.entstupidstuff.block.BlockFactoryUpt;
import net.ent.entstupidstuff.block.ModBlocks;
import net.ent.entstupidstuff.datagen.recipes.ShieldDecorationRecipeExtra;
import net.ent.entstupidstuff.item.ItemFactory;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.recipe.ComplexRecipeJsonBuilder;
import net.minecraft.data.server.recipe.CookingRecipeJsonBuilder;
import net.minecraft.data.server.recipe.CraftingRecipeJsonBuilder;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.RecipeProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.CampfireCookingRecipe;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SmokingRecipe;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper.WrapperLookup;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;

public class ModRecipeProvider extends FabricRecipeProvider{

    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    public static RecipeExporter exporter;

    //A way to enable and disable shit //TODO: Implement
    Boolean enableWeapons = true;
    Boolean enableClaymore = true;
    Boolean enableHammer = true;
    Boolean enableGlaive = true;
    Boolean enableBattleAxe = false; //Disabled
    Boolean enableLongSword = true;
    Boolean enableCannon = true;
    Boolean enableETC = true;

    Boolean enableFungal = true;
    Boolean enableMaple = true;
    Boolean enablePhantom = true;
    Boolean enableDesertIron = true;
    Boolean enableRedwood = true;

    Boolean enableAdditionalStone = true;
    Boolean enableLimestoneRecipes = true;

    Boolean enableGlassDoor = true;
    Boolean enableGlassTrapDoor = true;

    Boolean enableModdedGlassDoor = true;
    Boolean enableModdedGlassTrapDoor = true;

    Boolean enableVanillaGlassDoor = true;
    Boolean enableVanillaGlassTrapDoor = true;

    Boolean enableNetherite = true;

    Boolean enableTexturedWool = true;

    Boolean enableShield = false;




    @Override
    public void generate(RecipeExporter exporter2) {
        
        exporter = exporter2;

        //smeltToGold(); //Hardcoded
        //smeltToIron(); //Hardcoded 

        //Combat
        if (enableWeapons) {

            if (enableClaymore) {
                createClaymore(Items.STICK, ItemFactory.callItem("wooden" + "_claymore"), ItemTags.PLANKS);
                createClaymore(Items.COBBLESTONE, ItemFactory.callItem("stone" + "_claymore"), ItemTags.STONE_TOOL_MATERIALS);
                createClaymore(Items.IRON_INGOT, ItemFactory.callItem("iron" + "_claymore"));
                createClaymore(Items.GOLD_INGOT, ItemFactory.callItem("golden" + "_claymore"));
                createClaymore(Items.DIAMOND, ItemFactory.callItem("diamond" + "_claymore"));

                if (enableNetherite)
                    offerNetheriteUpgradeRecipe(exporter, ItemFactory.callItem("diamond_claymore"), RecipeCategory.COMBAT, ItemFactory.callItem("netherite_claymore"));
            }

            if (enableGlaive) {
                createGlaive(Items.STICK, ItemFactory.callItem("wooden" + "_glaive"), ItemTags.PLANKS);
                createGlaive(Items.COBBLESTONE, ItemFactory.callItem("stone" + "_glaive"), ItemTags.STONE_TOOL_MATERIALS);
                createGlaive(Items.IRON_INGOT, ItemFactory.callItem("iron" + "_glaive"));
                createGlaive(Items.GOLD_INGOT, ItemFactory.callItem("golden" + "_glaive"));
                createGlaive(Items.DIAMOND, ItemFactory.callItem("diamond" + "_glaive"));

                if (enableNetherite)
                    offerNetheriteUpgradeRecipe(exporter, ItemFactory.callItem("diamond_glaive"), RecipeCategory.COMBAT, ItemFactory.callItem("netherite_glaive"));
            }

            if (enableHammer) {
                createHammerUpdated(Items.STICK, ItemFactory.callItem("wooden" + "_hammer"), ItemTags.PLANKS);
                createHammerUpdated(Items.COBBLESTONE, ItemFactory.callItem("stone" + "_hammer"), ItemTags.STONE_TOOL_MATERIALS);
                createHammerUpdated(Items.IRON_INGOT, ItemFactory.callItem("iron" + "_hammer"));
                createHammerUpdated(Items.GOLD_INGOT, ItemFactory.callItem("golden" + "_hammer"));
                createHammerUpdated(Items.DIAMOND, ItemFactory.callItem("diamond" + "_hammer"));

                if (enableHammer)
                    offerNetheriteUpgradeRecipe(exporter, ItemFactory.callItem("diamond_hammer"), RecipeCategory.COMBAT, ItemFactory.callItem("netherite_hammer"));
            }

            if (enableBattleAxe) {
                createBattleAxeUpdated(Items.STICK, ItemFactory.callItem("wooden" + "_battleaxe"), ItemTags.PLANKS);
                createBattleAxeUpdated(Items.COBBLESTONE, ItemFactory.callItem("stone" + "_battleaxe"), ItemTags.STONE_TOOL_MATERIALS);
                createBattleAxeUpdated(Items.IRON_INGOT, ItemFactory.callItem("iron" + "_battleaxe"));
                createBattleAxeUpdated(Items.GOLD_INGOT, ItemFactory.callItem("golden" + "_battleaxe"));
                createBattleAxeUpdated(Items.DIAMOND, ItemFactory.callItem("diamond" + "_battleaxe"));

                if (enableNetherite)
                    offerNetheriteUpgradeRecipe(exporter, ItemFactory.callItem("diamond_battleaxe"), RecipeCategory.COMBAT, ItemFactory.callItem("netherite_battleaxe"));
            }

            if (enableLongSword) {
                createLongSwordUpdated(Items.STICK, ItemFactory.callItem("wooden" + "_long_sword"), ItemTags.PLANKS);
                createLongSwordUpdated(Items.COBBLESTONE, ItemFactory.callItem("stone" + "_long_sword"), ItemTags.STONE_TOOL_MATERIALS);
                createLongSwordUpdated(Items.IRON_INGOT, ItemFactory.callItem("iron" + "_long_sword"));
                createLongSwordUpdated(Items.GOLD_INGOT, ItemFactory.callItem("golden" + "_long_sword"));
                createLongSwordUpdated(Items.DIAMOND, ItemFactory.callItem("diamond" + "_long_sword"));

                if (enableNetherite)
                    offerNetheriteUpgradeRecipe(exporter, ItemFactory.callItem("diamond_long_sword"), RecipeCategory.COMBAT, ItemFactory.callItem("netherite_long_sword"));
            }

            if (enableCannon) {
                //Will be future area for Cannon
            }
        }

        generateMosaic("oak", Blocks.OAK_PLANKS, Blocks.OAK_SLAB);
        generateMosaic("spruce", Blocks.SPRUCE_PLANKS, Blocks.SPRUCE_SLAB);
        generateMosaic("jungle", Blocks.JUNGLE_PLANKS, Blocks.JUNGLE_SLAB);
        generateMosaic("birch", Blocks.BIRCH_PLANKS, Blocks.BIRCH_SLAB);
        generateMosaic("dark_oak", Blocks.DARK_OAK_PLANKS, Blocks.DARK_OAK_SLAB);
        generateMosaic("acacia", Blocks.ACACIA_PLANKS, Blocks.ACACIA_SLAB);
        generateMosaic("mangrove", Blocks.MANGROVE_PLANKS, Blocks.MANGROVE_SLAB);
        generateMosaic("cherry", Blocks.CHERRY_PLANKS, Blocks.CHERRY_SLAB);
        generateMosaic("warped", Blocks.WARPED_PLANKS, Blocks.WARPED_SLAB);
        generateMosaic("crimson", Blocks.CRIMSON_PLANKS, Blocks.CRIMSON_SLAB);
        

        //Modded Wood
        if (enableFungal)
            generateWoodFamily("fungal", null);
        if (enableRedwood)
            generateWoodFamily("redwood", null);
        if (enableDesertIron)
            generateWoodFamily("desert_iron", null);
        if (enableMaple)
            generateWoodFamily("maple", null);
        if (enablePhantom) {
            generateWoodFamily("phantom", null);
        }

        //Modded Wood - Fungal Colored
        if (enableFungal) {
            generateColorPlank("white");
            generateColorPlank("light_gray");
            generateColorPlank("gray");
            generateColorPlank("black");
            generateColorPlank("brown");
            generateColorPlank("red");
            generateColorPlank("orange");
            generateColorPlank("yellow");
            generateColorPlank("lime");
            generateColorPlank("green");
            generateColorPlank("cyan");
            generateColorPlank("light_blue");
            generateColorPlank("blue");
            generateColorPlank("purple");
            generateColorPlank("magenta");
            generateColorPlank("pink");

            generateWoodFamily("fungal","white");
            generateWoodFamily("fungal","light_gray");
            generateWoodFamily("fungal","gray");
            generateWoodFamily("fungal","black");
            generateWoodFamily("fungal","brown");
            generateWoodFamily("fungal","red");
            generateWoodFamily("fungal","orange");
            generateWoodFamily("fungal","yellow");
            generateWoodFamily("fungal","lime");
            generateWoodFamily("fungal","green");
            generateWoodFamily("fungal","cyan");
            generateWoodFamily("fungal","light_blue");
            generateWoodFamily("fungal","blue");
            generateWoodFamily("fungal","purple");
            generateWoodFamily("fungal","magenta");
            generateWoodFamily("fungal","pink");
        }

        //Textured Wool
        if (enableTexturedWool) {
            generateWoolFamily("textured_wool","white");
            generateWoolFamily("textured_wool","light_gray");
            generateWoolFamily("textured_wool","gray");
            generateWoolFamily("textured_wool","black");
            generateWoolFamily("textured_wool","brown");
            generateWoolFamily("textured_wool","red");
            generateWoolFamily("textured_wool","orange");
            generateWoolFamily("textured_wool","yellow");
            generateWoolFamily("textured_wool","lime");
            generateWoolFamily("textured_wool","green");
            generateWoolFamily("textured_wool","cyan");
            generateWoolFamily("textured_wool","light_blue");
            generateWoolFamily("textured_wool","blue");
            generateWoolFamily("textured_wool","purple");
            generateWoolFamily("textured_wool","magenta");
            generateWoolFamily("textured_wool","pink");
        }

        // Modded: Logs -> Planks
        if (enableFungal) {
            Item FUNGAL_PLANK = BlockFactoryUpt.callBlock("fungal_planks").asItem();
            ShapelessRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, FUNGAL_PLANK, 4)
			    .input(Ingredient.ofItems(Blocks.MUSHROOM_STEM, Blocks.RED_MUSHROOM_BLOCK, Blocks.BROWN_MUSHROOM_BLOCK))
			    .group("planks")
			    .criterion(hasItem(Blocks.MUSHROOM_STEM), conditionsFromItem(Blocks.MUSHROOM_STEM))
                .criterion(hasItem(Blocks.RED_MUSHROOM_BLOCK), conditionsFromItem(Blocks.RED_MUSHROOM_BLOCK))
                .criterion(hasItem(Blocks.BROWN_MUSHROOM_BLOCK), conditionsFromItem(Blocks.BROWN_MUSHROOM_BLOCK))
		    .offerTo(exporter);
        }

        if (enableRedwood) {
            Item REDWOOD_PLANK = BlockFactoryUpt.callBlock("redwood_planks").asItem();
            Block REDWOOD_LOG = BlockFactoryUpt.callBlock("redwood_log");
            ShapelessRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, REDWOOD_PLANK, 4)
			    .input(REDWOOD_LOG.asItem())
			    .group("planks")
			    .criterion(hasItem(REDWOOD_LOG), conditionsFromItem(REDWOOD_LOG))
		    .offerTo(exporter);
        }

        if (enableDesertIron) {
            Item DESERT_IRON_PLANK = BlockFactoryUpt.callBlock("desert_iron_planks").asItem();
            Block DESERT_IRON_LOG = BlockFactoryUpt.callBlock("desert_iron_log");
            ShapelessRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, DESERT_IRON_PLANK, 4)
                .input(DESERT_IRON_LOG.asItem())
                .group("planks")
                .criterion(hasItem(DESERT_IRON_LOG), conditionsFromItem(DESERT_IRON_LOG))
            .offerTo(exporter);
        }

        if (enableMaple) {
            Item MAPLE_PLANK = BlockFactoryUpt.callBlock("maple_planks").asItem();
            Block MAPLE_LOG = BlockFactoryUpt.callBlock("maple_log");
            ShapelessRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, MAPLE_PLANK, 4)
			    .input(MAPLE_LOG.asItem())
			    .group("planks")
			    .criterion(hasItem(MAPLE_LOG), conditionsFromItem(MAPLE_LOG))
		    .offerTo(exporter);
        }

        if (enablePhantom) {
            Item PHANTOM_PLANK = BlockFactoryUpt.callBlock("phantom_planks").asItem();
            Block PHATOM_LOG = BlockFactoryUpt.callBlock("phantom_log");
            ShapelessRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, PHANTOM_PLANK, 4)
			    .input(PHATOM_LOG.asItem())
			    .group("planks")
			    .criterion(hasItem(PHATOM_LOG), conditionsFromItem(PHATOM_LOG))
		    .offerTo(exporter);
        }
        
        if (enableAdditionalStone) {
            createStoneVarient();
        }

        if (enableLimestoneRecipes) {
            createLimestoneVarients();
        }



        //Vanilla GlassDoor
        if (enableVanillaGlassDoor) {
            createGlassDoorRecipe(BlockFactoryUpt.callBlock("oak" + "_glass_door"), Blocks.OAK_DOOR, Blocks.OAK_PLANKS.asItem(), true);
            createGlassDoorRecipe(BlockFactoryUpt.callBlock("spruce" + "_glass_door"), Blocks.SPRUCE_DOOR, Blocks.SPRUCE_PLANKS.asItem(), true);
            createGlassDoorRecipe(BlockFactoryUpt.callBlock("jungle" + "_glass_door"), Blocks.JUNGLE_DOOR, Blocks.JUNGLE_PLANKS.asItem(), true);
            createGlassDoorRecipe(BlockFactoryUpt.callBlock("birch" + "_glass_door"), Blocks.BIRCH_DOOR, Blocks.BIRCH_PLANKS.asItem(), true);
            createGlassDoorRecipe(BlockFactoryUpt.callBlock("dark_oak" + "_glass_door"), Blocks.DARK_OAK_DOOR, Blocks.DARK_OAK_PLANKS.asItem(), true);
            createGlassDoorRecipe(BlockFactoryUpt.callBlock("acacia" + "_glass_door"), Blocks.ACACIA_DOOR, Blocks.ACACIA_PLANKS.asItem(), true);
            createGlassDoorRecipe(BlockFactoryUpt.callBlock("mangrove" + "_glass_door"), Blocks.MANGROVE_DOOR, Blocks.MANGROVE_PLANKS.asItem(), true);
            createGlassDoorRecipe(BlockFactoryUpt.callBlock("cherry" + "_glass_door"), Blocks.CHERRY_DOOR, Blocks.CHERRY_PLANKS.asItem(), true);
            createGlassDoorRecipe(BlockFactoryUpt.callBlock("bamboo" + "_glass_door"), Blocks.BAMBOO_DOOR, Blocks.BAMBOO_PLANKS.asItem(), true);
            createGlassDoorRecipe(BlockFactoryUpt.callBlock("warped" + "_glass_door"), Blocks.WARPED_DOOR, Blocks.WARPED_PLANKS.asItem(), true);
            createGlassDoorRecipe(BlockFactoryUpt.callBlock("crimson" + "_glass_door"), Blocks.CRIMSON_DOOR, Blocks.CRIMSON_PLANKS.asItem(), true);
            //createGlassDoorRecipe(BlockFactoryUpt.callBlock("pale_oak" + "_glass_door"), Blocks.PALE_OAK_DOOR, Blocks.PALE_OAK_PLANKS.asItem()); //FUTURE UPDATE
    
            createGlassDoorRecipe(BlockFactoryUpt.callBlock("iron" + "_glass_door"), Blocks.IRON_DOOR, Items.IRON_INGOT, false);
            createGlassDoorRecipe(BlockFactoryUpt.callBlock("copper" + "_glass_door"), Blocks.COPPER_DOOR, Items.COPPER_INGOT, false);
            createGlassDoorRecipe(BlockFactoryUpt.callBlock("exposed_copper" + "_glass_door"), Blocks.EXPOSED_COPPER_DOOR, Items.COPPER_INGOT, false);
            createGlassDoorRecipe(BlockFactoryUpt.callBlock("oxidized_copper" + "_glass_door"), Blocks.OXIDIZED_COPPER_DOOR, Items.COPPER_INGOT, false);
            createGlassDoorRecipe(BlockFactoryUpt.callBlock("weathered_copper" + "_glass_door"), Blocks.WEATHERED_COPPER_DOOR, Items.COPPER_INGOT, false);
            createGlassDoorRecipe(BlockFactoryUpt.callBlock("waxed_copper" + "_glass_door"), Blocks.WAXED_COPPER_DOOR, Items.COPPER_INGOT, false);
            createGlassDoorRecipe(BlockFactoryUpt.callBlock("waxed_exposed_copper" + "_glass_door"), Blocks.WAXED_EXPOSED_COPPER_DOOR, Items.COPPER_INGOT, false);
            createGlassDoorRecipe(BlockFactoryUpt.callBlock("waxed_oxidized_copper" + "_glass_door"), Blocks.WAXED_OXIDIZED_COPPER_DOOR, Items.COPPER_INGOT, false);
            createGlassDoorRecipe(BlockFactoryUpt.callBlock("waxed_weathered_copper" + "_glass_door"), Blocks.WAXED_WEATHERED_COPPER_DOOR, Items.COPPER_INGOT, false);

        }
        



        //Vanilla GlassTrapDoor
        if (enableVanillaGlassTrapDoor) {
            createGlassTrapDoorRecipe(BlockFactoryUpt.callBlock("oak" + "_glass_trapdoor"), Blocks.OAK_TRAPDOOR, Blocks.OAK_PLANKS.asItem(), true);
            createGlassTrapDoorRecipe(BlockFactoryUpt.callBlock("spruce" + "_glass_trapdoor"), Blocks.SPRUCE_TRAPDOOR, Blocks.SPRUCE_PLANKS.asItem(), true);
            createGlassTrapDoorRecipe(BlockFactoryUpt.callBlock("jungle" + "_glass_trapdoor"), Blocks.JUNGLE_TRAPDOOR, Blocks.JUNGLE_PLANKS.asItem(), true);
            createGlassTrapDoorRecipe(BlockFactoryUpt.callBlock("birch" + "_glass_trapdoor"), Blocks.BIRCH_TRAPDOOR, Blocks.BIRCH_PLANKS.asItem(), true);
            createGlassTrapDoorRecipe(BlockFactoryUpt.callBlock("dark_oak" + "_glass_trapdoor"), Blocks.DARK_OAK_TRAPDOOR, Blocks.DARK_OAK_PLANKS.asItem(), true);
            createGlassTrapDoorRecipe(BlockFactoryUpt.callBlock("acacia" + "_glass_trapdoor"), Blocks.ACACIA_TRAPDOOR, Blocks.ACACIA_PLANKS.asItem(), true);
            createGlassTrapDoorRecipe(BlockFactoryUpt.callBlock("mangrove" + "_glass_trapdoor"), Blocks.MANGROVE_TRAPDOOR, Blocks.MANGROVE_PLANKS.asItem(), true);
            createGlassTrapDoorRecipe(BlockFactoryUpt.callBlock("cherry" + "_glass_trapdoor"), Blocks.CHERRY_TRAPDOOR, Blocks.CHERRY_PLANKS.asItem(), true);
            createGlassTrapDoorRecipe(BlockFactoryUpt.callBlock("bamboo" + "_glass_trapdoor"), Blocks.BAMBOO_TRAPDOOR, Blocks.BAMBOO_PLANKS.asItem(), true);
            createGlassTrapDoorRecipe(BlockFactoryUpt.callBlock("warped" + "_glass_trapdoor"), Blocks.WARPED_TRAPDOOR, Blocks.WARPED_PLANKS.asItem(), true);
            createGlassTrapDoorRecipe(BlockFactoryUpt.callBlock("crimson" + "_glass_trapdoor"), Blocks.CRIMSON_TRAPDOOR, Blocks.CRIMSON_PLANKS.asItem(), true);
            //createGlassTrapDoorRecipe(BlockFactoryUpt.callBlock("pale_oak" + "_glass_trapdoor"), Blocks.PALE_OAK_TRAPDOOR, Blocks.PALE_OAK_PLANKS.asItem()); //FUTURE UPDATE
    
            createGlassTrapDoorRecipe(BlockFactoryUpt.callBlock("iron" + "_glass_trapdoor"), Blocks.IRON_TRAPDOOR, Items.IRON_INGOT, false);
            createGlassTrapDoorRecipe(BlockFactoryUpt.callBlock("copper" + "_glass_trapdoor"), Blocks.COPPER_TRAPDOOR, Items.COPPER_INGOT, false);
            createGlassTrapDoorRecipe(BlockFactoryUpt.callBlock("exposed_copper" + "_glass_trapdoor"), Blocks.EXPOSED_COPPER_TRAPDOOR, Items.COPPER_INGOT, false);
            createGlassTrapDoorRecipe(BlockFactoryUpt.callBlock("oxidized_copper" + "_glass_trapdoor"), Blocks.OXIDIZED_COPPER_TRAPDOOR, Items.COPPER_INGOT, false);
            createGlassTrapDoorRecipe(BlockFactoryUpt.callBlock("weathered_copper" + "_glass_trapdoor"), Blocks.WEATHERED_COPPER_TRAPDOOR, Items.COPPER_INGOT, false);
            createGlassTrapDoorRecipe(BlockFactoryUpt.callBlock("waxed_copper" + "_glass_trapdoor"), Blocks.WAXED_COPPER_TRAPDOOR, Items.COPPER_INGOT, false);
            createGlassTrapDoorRecipe(BlockFactoryUpt.callBlock("waxed_exposed_copper" + "_glass_trapdoor"), Blocks.WAXED_EXPOSED_COPPER_TRAPDOOR, Items.COPPER_INGOT, false);
            createGlassTrapDoorRecipe(BlockFactoryUpt.callBlock("waxed_oxidized_copper" + "_glass_trapdoor"), Blocks.WAXED_OXIDIZED_COPPER_TRAPDOOR, Items.COPPER_INGOT, false);
            createGlassTrapDoorRecipe(BlockFactoryUpt.callBlock("waxed_weathered_copper" + "_glass_trapdoor"), Blocks.WAXED_WEATHERED_COPPER_TRAPDOOR, Items.COPPER_INGOT, false);
        }
        

        //Food
        ShapedRecipeJsonBuilder.create(RecipeCategory.FOOD, ItemFactory.callItem("baguette"))
			.input('#', Items.BREAD)
			.pattern("#")
            .pattern("#")
            .pattern("#")
			.criterion("has_bread", conditionsFromItem(Items.BREAD))
		.offerTo(exporter);

        Item MARSHMELLOW_TOASTED = ItemFactory.callItem("toasted_marshmellow");
        Item MARSHMELLOW_RAW = ItemFactory.callItem("raw_marshmellow"); 

        CookingRecipeJsonBuilder.createSmelting(Ingredient.ofItems(MARSHMELLOW_RAW), RecipeCategory.FOOD, MARSHMELLOW_TOASTED, 0.35F, 200)
			.criterion("has_marshmellow", conditionsFromItem(MARSHMELLOW_RAW))
		.offerTo(exporter);

        offerFoodCookingRecipe(exporter, "smoker", RecipeSerializer.SMOKING, SmokingRecipe::new, 100, MARSHMELLOW_RAW, MARSHMELLOW_TOASTED, 0.35F);
        offerFoodCookingRecipe(exporter, "campfire_cooking", RecipeSerializer.CAMPFIRE_COOKING, CampfireCookingRecipe::new, 600, MARSHMELLOW_RAW, MARSHMELLOW_TOASTED, 0.35F);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, ItemFactory.MARSHMELLOW_RAW, 1)
            .input(Items.STICK,1)
            .input(Items.HONEYCOMB,1)
            .input(Items.SUGAR, 1)
            .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
            .criterion(hasItem(Items.HONEY_BLOCK), conditionsFromItem(Items.HONEY_BLOCK))
            .criterion(hasItem(Items.SUGAR), conditionsFromItem(Items.SUGAR))
        .offerTo(exporter);


        Item ANCIENT_DEBRIS_NUGGET = ItemFactory.callItem("ancient_debris_nugget");

        offerReversibleCompactingRecipesWithCompactingRecipeGroup(
			exporter, RecipeCategory.MISC, ANCIENT_DEBRIS_NUGGET, RecipeCategory.MISC, Items.ANCIENT_DEBRIS, "ancient_debris_from_nuggets", "ancient_debris"
		);

        
        //Shields

        if (enableShield) {
            ComplexRecipeJsonBuilder.create((category) -> new ShieldDecorationRecipeExtra(category, ItemFactory.WOODEN_OAK_SHIELD)).offerTo(exporter, "wooden_oak_shield_decoration");
            ComplexRecipeJsonBuilder.create((category) -> new ShieldDecorationRecipeExtra(category, ItemFactory.WOODEN_SPRUCE_SHIELD)).offerTo(exporter, "wooden_spruce_shield_decoration");
            ComplexRecipeJsonBuilder.create((category) -> new ShieldDecorationRecipeExtra(category, ItemFactory.WOODEN_BIRCH_SHIELD)).offerTo(exporter, "wooden_birch_shield_decoration");
            ComplexRecipeJsonBuilder.create((category) -> new ShieldDecorationRecipeExtra(category, ItemFactory.WOODEN_JUNGLE_SHIELD)).offerTo(exporter, "wooden_jungle_shield_decoration");
            ComplexRecipeJsonBuilder.create((category) -> new ShieldDecorationRecipeExtra(category, ItemFactory.WOODEN_BAMBOO_SHIELD)).offerTo(exporter, "wooden_bamboo_shield_decoration");
            ComplexRecipeJsonBuilder.create((category) -> new ShieldDecorationRecipeExtra(category, ItemFactory.WOODEN_ACACIA_SHIELD)).offerTo(exporter, "wooden_acacia_shield_decoration");
            ComplexRecipeJsonBuilder.create((category) -> new ShieldDecorationRecipeExtra(category, ItemFactory.WOODEN_DARK_OAK_SHIELD)).offerTo(exporter, "wooden_dark_oak_shield_decoration");
            ComplexRecipeJsonBuilder.create((category) -> new ShieldDecorationRecipeExtra(category, ItemFactory.WOODEN_MANGROVE_SHIELD)).offerTo(exporter, "wooden_mangrove_shield_decoration");
            ComplexRecipeJsonBuilder.create((category) -> new ShieldDecorationRecipeExtra(category, ItemFactory.WOODEN_CHERRY_SHIELD)).offerTo(exporter, "wooden_cherry_shield_decoration");

            ComplexRecipeJsonBuilder.create((category) -> new ShieldDecorationRecipeExtra(category, ItemFactory.STONE_SHIELD)).offerTo(exporter, "stone_shield_decoration");
            ComplexRecipeJsonBuilder.create((category) -> new ShieldDecorationRecipeExtra(category, ItemFactory.STONE_BLACKSTONE_SHIELD)).offerTo(exporter, "stone_blackstone_shield_decoration");
            ComplexRecipeJsonBuilder.create((category) -> new ShieldDecorationRecipeExtra(category, ItemFactory.STONE_DEEPSLATE_SHIELD)).offerTo(exporter, "stone_deepslate_shield_decoration");
            ComplexRecipeJsonBuilder.create((category) -> new ShieldDecorationRecipeExtra(category, ItemFactory.GOLDEN_SHIELD)).offerTo(exporter, "golden_shield_decoration");
            ComplexRecipeJsonBuilder.create((category) -> new ShieldDecorationRecipeExtra(category, ItemFactory.DIAMOND_SHIELD)).offerTo(exporter, "diamond_shield_decoration");

            createShield(ItemTags.ACACIA_LOGS, ItemFactory.WOODEN_ACACIA_SHIELD);
            createShield(ItemTags.BAMBOO_BLOCKS, ItemFactory.WOODEN_BAMBOO_SHIELD);
            createShield(ItemTags.BIRCH_LOGS, ItemFactory.WOODEN_BIRCH_SHIELD);
            createShield(ItemTags.CHERRY_LOGS, ItemFactory.WOODEN_CHERRY_SHIELD);
            createShield(ItemTags.DARK_OAK_LOGS, ItemFactory.WOODEN_DARK_OAK_SHIELD);
            createShield(ItemTags.JUNGLE_LOGS, ItemFactory.WOODEN_JUNGLE_SHIELD);
            createShield(ItemTags.MANGROVE_LOGS, ItemFactory.WOODEN_MANGROVE_SHIELD);
            createShield(ItemTags.OAK_LOGS, ItemFactory.WOODEN_OAK_SHIELD);
            createShield(ItemTags.SPRUCE_LOGS, ItemFactory.WOODEN_SPRUCE_SHIELD);

            createShield(Items.COBBLESTONE, ItemFactory.STONE_SHIELD);
            createShield(Items.COBBLED_DEEPSLATE, ItemFactory.STONE_DEEPSLATE_SHIELD);
            createShield(Items.BLACKSTONE, ItemFactory.STONE_BLACKSTONE_SHIELD);
            createShield(Items.GOLD_INGOT, ItemFactory.GOLDEN_SHIELD);
            createShield(Items.DIAMOND, ItemFactory.DIAMOND_SHIELD);

            offerNetheriteUpgradeRecipe(exporter, ItemFactory.callItem("diamond_shield"), RecipeCategory.COMBAT, ItemFactory.callItem("stone_shield"));

        }

        
        /*

        createCombat(Items.STICK, "wooden", ItemTags.PLANKS);
        createCombat(Items.COBBLESTONE, "stone", ItemTags.STONE_TOOL_MATERIALS);
        createCombat(Items.IRON_INGOT, "iron");
        createCombat(Items.GOLD_INGOT, "golden");
        createCombat(Items.DIAMOND, "diamond");

        // createLongSword(Material, ItemFactory.callItem(MatName + "_long_sword"));
        // createDagger(Material, ItemFactory.callItem(MatName + "_dagger"));
        // createHammer(Material, ItemFactory.callItem(MatName + "_hammer"));

        offerNetheriteUpgradeRecipe(exporter, ItemFactory.callItem("diamond_long_sword"), RecipeCategory.COMBAT, ItemFactory.callItem("netherite_long_sword"));
        offerNetheriteUpgradeRecipe(exporter, ItemFactory.callItem("diamond_dagger"), RecipeCategory.COMBAT, ItemFactory.callItem("netherite_dagger"));
        offerNetheriteUpgradeRecipe(exporter, ItemFactory.callItem("diamond_hammer"), RecipeCategory.COMBAT, ItemFactory.callItem("netherite_hammer"));

        generateWoodFamily("fungal", null);

        generateColorPlank("white");
        generateColorPlank("light_gray");
        generateColorPlank("gray");
        generateColorPlank("black");
        generateColorPlank("brown");
        generateColorPlank("red");
        generateColorPlank("orange");
        generateColorPlank("yellow");
        generateColorPlank("lime");
        generateColorPlank("green");
        generateColorPlank("cyan");
        generateColorPlank("light_blue");
        generateColorPlank("blue");
        generateColorPlank("purple");
        generateColorPlank("magenta");
        generateColorPlank("pink");

        generateWoodFamily("fungal","white");
        generateWoodFamily("fungal","light_gray");
        generateWoodFamily("fungal","gray");
        generateWoodFamily("fungal","black");
        generateWoodFamily("fungal","brown");
        generateWoodFamily("fungal","red");
        generateWoodFamily("fungal","orange");
        generateWoodFamily("fungal","yellow");
        generateWoodFamily("fungal","lime");
        generateWoodFamily("fungal","green");
        generateWoodFamily("fungal","cyan");
        generateWoodFamily("fungal","light_blue");
        generateWoodFamily("fungal","blue");
        generateWoodFamily("fungal","purple");
        generateWoodFamily("fungal","magenta");
        generateWoodFamily("fungal","pink");

        generateWoolFamily("textured_wool","white");
        generateWoolFamily("textured_wool","light_gray");
        generateWoolFamily("textured_wool","gray");
        generateWoolFamily("textured_wool","black");
        generateWoolFamily("textured_wool","brown");
        generateWoolFamily("textured_wool","red");
        generateWoolFamily("textured_wool","orange");
        generateWoolFamily("textured_wool","yellow");
        generateWoolFamily("textured_wool","lime");
        generateWoolFamily("textured_wool","green");
        generateWoolFamily("textured_wool","cyan");
        generateWoolFamily("textured_wool","light_blue");
        generateWoolFamily("textured_wool","blue");
        generateWoolFamily("textured_wool","purple");
        generateWoolFamily("textured_wool","magenta");
        generateWoolFamily("textured_wool","pink"); */
        /*
        Item FUNGAL_PLANK = BlockFactoryUpt.callBlock("fungal_planks").asItem();
        ShapelessRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, FUNGAL_PLANK, 4)
			.input(Ingredient.ofItems(Blocks.MUSHROOM_STEM, Blocks.RED_MUSHROOM_BLOCK, Blocks.BROWN_MUSHROOM_BLOCK))
			.group("planks")
			.criterion(hasItem(Blocks.MUSHROOM_STEM), conditionsFromItem(Blocks.MUSHROOM_STEM))
            .criterion(hasItem(Blocks.RED_MUSHROOM_BLOCK), conditionsFromItem(Blocks.RED_MUSHROOM_BLOCK))
            .criterion(hasItem(Blocks.BROWN_MUSHROOM_BLOCK), conditionsFromItem(Blocks.BROWN_MUSHROOM_BLOCK))
		.offerTo(exporter);

        Item REDWOOD_PLANK = BlockFactoryUpt.callBlock("redwood_planks").asItem();
        Block REDWOOD_LOG = BlockFactoryUpt.callBlock("redwood_log");
        ShapelessRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, REDWOOD_PLANK, 4)
			.input(REDWOOD_LOG.asItem())
			.group("planks")
			.criterion(hasItem(REDWOOD_LOG), conditionsFromItem(REDWOOD_LOG))
		.offerTo(exporter);

        Item DESERT_IRON_PLANK = BlockFactoryUpt.callBlock("desert_iron_planks").asItem();
        Block DESERT_IRON_LOG = BlockFactoryUpt.callBlock("desert_iron_log");
        ShapelessRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, DESERT_IRON_PLANK, 4)
            .input(DESERT_IRON_LOG.asItem())
            .group("planks")
            .criterion(hasItem(DESERT_IRON_LOG), conditionsFromItem(DESERT_IRON_LOG))
        .offerTo(exporter);
         */

        /* 
        generateWoodFamily("redwood", null);
        generateWoodFamily("desert_iron", null);

        createStoneVarient();

        //Block IRON_GLASSDOOR = ModBlocks.MOD_DOOR("iron" + "_glass", null);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, IRON_GLASSDOOR, 1)
            .input(Blocks.IRON_DOOR,1)
            .input(Blocks.GLASS_PANE, 1)
            .group("colored_planks")
            .criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT))
            .offerTo(exporter, getItemPath(IRON_GLASSDOOR)); 

        */



        //Old

        //generateWoodFamily("fungal", null);
        //for (String color : BlockFactoryUpt.COLORS) {generateColorPlank(color);}
        //for (String color : BlockFactoryUpt.COLORS) {generateWoodFamily("fungal", color);}
        //for (String color : BlockFactoryUpt.COLORS) {generateWoolFamily("textured_wool", color);}

        /* smeltToCopper(); //Hardcoded */

        //for (String base : ModBlocks.V_WOOD_VARIENTS) {createVanillaGlassDoor(base);} //TOBEREMOVED
        //for (String base : ModBlocks.COPPER_VARIENTS) {createVanillaGlassDoorC(base);} //TOBEREMOVED
        //createVanillaGlassDoorI(); //TOBEREMOVED


        //createPlanks(BlockFactoryUpt.callBlock("fungal_planks"), Ingredient.ofItems(Blocks.MUSHROOM_STEM, Blocks.RED_MUSHROOM_BLOCK, Blocks.BROWN_MUSHROOM_BLOCK));
        
        //createStoneVarient();

        //generateWoodFamily("redwood", null);
        //generateWoodFamily("desert_iron", null);

        //createPlanks(BlockFactoryUpt.callBlock("redwood_planks"), Ingredient.ofItems(BlockFactoryUpt.callBlock("redwood_log")));
        //createPlanks(BlockFactoryUpt.callBlock("desert_iron_planks"), Ingredient.ofItems(BlockFactoryUpt.callBlock("desert_iron_log")));



























    }

    public void createGlassDoorRecipe(Block GLASSDOOR, Block DOOR, Item UNLOCK_Item, Boolean wooden) {

        if (wooden) {
            ShapelessRecipeJsonBuilder.create(RecipeCategory.REDSTONE, GLASSDOOR, 1)
            .input(DOOR,1)
            .input(Blocks.GLASS_PANE, 1)
            .group("wooden_glass_door")
            .criterion(hasItem(DOOR.asItem()), conditionsFromItem(DOOR.asItem()))
            .offerTo(exporter);
        }
        else {
            ShapelessRecipeJsonBuilder.create(RecipeCategory.REDSTONE, GLASSDOOR, 1)
            .input(DOOR,1)
            .input(Blocks.GLASS_PANE, 1)
            .group("glass_door")
            .criterion(hasItem(DOOR.asItem()), conditionsFromItem(DOOR.asItem()))
            .offerTo(exporter);  
        }

    }

    public void createGlassTrapDoorRecipe(Block GLASSTRAPDOOR, Block TRAPDOOR, Item UNLOCK_Item, Boolean wooden) {

        if (wooden) {
            ShapelessRecipeJsonBuilder.create(RecipeCategory.REDSTONE, GLASSTRAPDOOR, 1)
            .input(TRAPDOOR,1)
            .input(Blocks.GLASS_PANE, 1)
            .group("wooden_glass_trapdoor")
            .criterion(hasItem(TRAPDOOR.asItem()), conditionsFromItem(TRAPDOOR.asItem()))
            .offerTo(exporter); 
        }
        else {
            ShapelessRecipeJsonBuilder.create(RecipeCategory.REDSTONE, GLASSTRAPDOOR, 1)
            .input(TRAPDOOR,1)
            .input(Blocks.GLASS_PANE, 1)
            .group("glass_trapdoor")
            .criterion(hasItem(TRAPDOOR.asItem()), conditionsFromItem(TRAPDOOR.asItem()))
            .offerTo(exporter); 
        }


    }

    public void createShield(TagKey<Item> UNLOCK_Item, ItemConvertible result) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, result)
        .input('X', ItemTags.PLANKS)
        .input('#', UNLOCK_Item)
        .pattern("X#X")
        .pattern("XXX")
        .pattern(" X ")
        //.group("long_sword")
        .criterion("has_log", conditionsFromTag(ItemTags.PLANKS))
        .offerTo(exporter);
    }

    public void createShield(Item material, ItemConvertible result) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, result)
        .input('X', ItemTags.PLANKS)
        .input('#', material)
        .pattern("X#X")
        .pattern("XXX")
        .pattern(" X ")
        //.group("long_sword")
        .criterion(hasItem(material), conditionsFromTag(ItemTags.PLANKS))
        .offerTo(exporter);
    }

    public void createCombat(Item UNLOCK, String MatName, TagKey<Item> matTag) {

        createLongSword(UNLOCK, ItemFactory.callItem(MatName + "_long_sword"), matTag);
        createDagger(UNLOCK, ItemFactory.callItem(MatName + "_dagger"), matTag);
        createHammer(UNLOCK, ItemFactory.callItem(MatName + "_hammer"), matTag);
        //createBattleAxe(UNLOCK, ItemFactory.callItem(MatName + "_battle_axe"), matTag);
        //createKatana(UNLOCK, ItemFactory.callItem(MatName + "_katana"), matTag);

    }

    public void createCombat(Item Material, String MatName) {

        createLongSword(Material, ItemFactory.callItem(MatName + "_long_sword"));
        createDagger(Material, ItemFactory.callItem(MatName + "_dagger"));
        createHammer(Material, ItemFactory.callItem(MatName + "_hammer"));
        //createBattleAxe(UNLOCK, ItemFactory.callItem(MatName + "_battle_axe"));
        //createKatana(UNLOCK, ItemFactory.callItem(MatName + "_katana"));

    }

    /* Updated Weapon Recipes */
    public static void createClaymore(Item UNLOCK, ItemConvertible result, TagKey<Item> ITEMTAGS){
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, result)
        .input('#', ITEMTAGS)
        .input('S', Items.STICK)
        .pattern(" # ")
        .pattern("###")
        .pattern("#S#")
        //.group("long_sword")
        .criterion(hasItem(UNLOCK), conditionsFromTag(ITEMTAGS))
        .offerTo(exporter);
    }

    public static void createClaymore(Item MATERIAL, ItemConvertible result){
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, result)
        .input('#', MATERIAL)
        .input('S', Items.STICK)
        .pattern(" # ")
        .pattern("###")
        .pattern("#S#")
        //.group("long_sword")
        .criterion(hasItem(MATERIAL), conditionsFromItem(MATERIAL))
        .offerTo(exporter);
    }

    public static void createHammerUpdated(Item UNLOCK, ItemConvertible result, TagKey<Item> ITEMTAGS){
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, result)
        .input('#', ITEMTAGS)
        .input('S', Items.STICK)
        .pattern("###")
        .pattern("###")
        .pattern(" S ")
        //.group("long_sword")
        .criterion(hasItem(UNLOCK), conditionsFromTag(ITEMTAGS))
        .offerTo(exporter);
    }

    public static void createHammerUpdated(Item MATERIAL, ItemConvertible result){
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, result)
        .input('#', MATERIAL)
        .input('S', Items.STICK)
        .pattern("###")
        .pattern("###")
        .pattern(" S ")
        //.group("long_sword")
        .criterion(hasItem(MATERIAL), conditionsFromItem(MATERIAL))
        .offerTo(exporter);
    }

    public static void createLongSwordUpdated(Item UNLOCK, ItemConvertible result, TagKey<Item> ITEMTAGS){
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, result)
        .input('#', ITEMTAGS)
        .input('S', Items.STICK)
        .pattern(" # ")
        .pattern("###")
        .pattern(" S ")
        //.group("long_sword")
        .criterion(hasItem(UNLOCK), conditionsFromTag(ITEMTAGS))
        .offerTo(exporter);
    }

    public static void createLongSwordUpdated(Item MATERIAL, ItemConvertible result){
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, result)
        .input('#', MATERIAL)
        .input('S', Items.STICK)
        .pattern(" # ")
        .pattern("###")
        .pattern(" S ")
        //.group("long_sword")
        .criterion(hasItem(MATERIAL), conditionsFromItem(MATERIAL))
        .offerTo(exporter);
    }

    public static void createGlaive(Item UNLOCK, ItemConvertible result, TagKey<Item> ITEMTAGS){
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, result)
        .input('#', ITEMTAGS)
        .input('S', Items.STICK)
        .pattern("  #")
        .pattern(" S ")
        .pattern("S  ")
        //.group("long_sword")
        .criterion(hasItem(UNLOCK), conditionsFromTag(ITEMTAGS))
        .offerTo(exporter);
    }

    public static void createGlaive(Item MATERIAL, ItemConvertible result){
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, result)
        .input('#', MATERIAL)
        .input('S', Items.STICK)
        .pattern("  #")
        .pattern(" S ")
        .pattern("S  ")
        //.group("long_sword")
        .criterion(hasItem(MATERIAL), conditionsFromItem(MATERIAL))
        .offerTo(exporter);
    }

        public static void createBattleAxeUpdated(Item UNLOCK, ItemConvertible result, TagKey<Item> ITEMTAGS){
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, result)
        .input('#', ITEMTAGS)
        .input('S', Items.STICK)
        .pattern("###")
        .pattern("#S#")
        .pattern(" S ")
        //.group("long_sword")
        .criterion(hasItem(UNLOCK), conditionsFromTag(ITEMTAGS))
        .offerTo(exporter);
    }

    public static void createBattleAxeUpdated(Item MATERIAL, ItemConvertible result){
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, result)
        .input('#', MATERIAL)
        .input('S', Items.STICK)
        .pattern("###")
        .pattern("#S#")
        .pattern(" S ")
        //.group("long_sword")
        .criterion(hasItem(MATERIAL), conditionsFromItem(MATERIAL))
        .offerTo(exporter);
    }










    /*Legacy Weapons Recipes */

    public static void createLongSword(Item UNLOCK, ItemConvertible result, TagKey<Item> ITEMTAGS){
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, result)
        .input('#', ITEMTAGS)
        .input('X', Items.STICK)
        .pattern(" # ")
        .pattern("###")
        .pattern("#X#")
        //.group("long_sword")
        .criterion(hasItem(UNLOCK), conditionsFromTag(ITEMTAGS))
        .offerTo(exporter);
    }

    public static void createLongSword(Item MATERIAL, ItemConvertible result){
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, result)
        .input('#', MATERIAL)
        .input('X', Items.STICK)
        .pattern(" # ")
        .pattern("###")
        .pattern("#X#")
        //.group("long_sword")
        .criterion(hasItem(MATERIAL), conditionsFromItem(MATERIAL))
        .offerTo(exporter);

        
    }

    public static void createBattleAxe(Item UNLOCK, ItemConvertible result, TagKey<Item> ITEMTAGS){
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, result)
        .input('#', ITEMTAGS)
        .input('X', Items.STICK)
        .pattern("###")
        .pattern("#X#")
        .pattern(" X ")
        //.group("battle_axe")
        .criterion(hasItem(UNLOCK), conditionsFromTag(ITEMTAGS))
        .offerTo(exporter);
    }

    public static void createBattleAxe(Item MATERIAL, ItemConvertible result){
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, result)
        .input('#', MATERIAL)
        .input('X', Items.STICK)
        .pattern("###")
        .pattern("#X#")
        .pattern(" X ")
        //.group("battle_axe")
        .criterion(hasItem(MATERIAL), conditionsFromItem(MATERIAL))
        .offerTo(exporter);

        
    }

    public static void createKatana(Item UNLOCK, ItemConvertible result, TagKey<Item> ITEMTAGS){
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, result)
        .input('#', ITEMTAGS)
        .input('X', Items.STICK)
        .pattern("  #")
        .pattern(" # ")
        .pattern("X  ")
        //.group("katana")
        .criterion(hasItem(UNLOCK), conditionsFromTag(ITEMTAGS))
        .offerTo(exporter);
    }

    public static void createKatana(Item MATERIAL, ItemConvertible result){
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, result)
        .input('#', MATERIAL)
        .input('X', Items.STICK)
        .pattern("  #")
        .pattern(" # ")
        .pattern("X  ")
        //.group("katana")
        .criterion(hasItem(MATERIAL), conditionsFromItem(MATERIAL))
        .offerTo(exporter);

        
    }

    public static void createDagger(Item UNLOCK, ItemConvertible result, TagKey<Item> ITEMTAGS){
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, result)
        .input('#', ITEMTAGS)
        .input('X', Items.STICK)
        .pattern("  #")
        .pattern(" X ")
        .pattern("   ")
        //.group("dagger")
        .criterion(hasItem(UNLOCK), conditionsFromTag(ITEMTAGS))
        .offerTo(exporter);
    }

    public static void createDagger(Item MATERIAL, ItemConvertible result){
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, result)
        .input('#', MATERIAL)
        .input('X', Items.STICK)
        .pattern("  #")
        .pattern(" X ")
        .pattern("   ")
        //.group("dagger")
        .criterion(hasItem(MATERIAL), conditionsFromItem(MATERIAL))
        .offerTo(exporter);

        
    }

    public static void createHammer(Item UNLOCK, ItemConvertible result, TagKey<Item> ITEMTAGS){
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, result)
        .input('#', ITEMTAGS)
        .input('X', Items.STICK)
        .pattern("###")
        .pattern("###")
        .pattern(" X ")
        //.group("hammer")
        .criterion(hasItem(UNLOCK), conditionsFromTag(ITEMTAGS))
        .offerTo(exporter);
    }

    public static void createHammer(Item MATERIAL, ItemConvertible result){
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, result)
        .input('#', MATERIAL)
        .input('X', Items.STICK)
        .pattern("###")
        .pattern("###")
        .pattern(" X ")
        //.group("hammer")
        .criterion(hasItem(MATERIAL), conditionsFromItem(MATERIAL))
        .offerTo(exporter);

        
    }

    public static void smeltToIron(){

        Item Iron1 = ItemFactory.callItem("iron_long_sword");
        Item Iron2 = ItemFactory.callItem("iron_long_sword");
        Item Iron3 = ItemFactory.callItem("iron_long_sword");
        
        CookingRecipeJsonBuilder.createSmelting(
            Ingredient.ofItems(Iron1, Iron2, Iron3),
            RecipeCategory.MISC,
			Items.IRON_NUGGET,
			0.1F,
			200
        )
        .criterion(hasItem(Iron1), conditionsFromItem(Iron1))
        .criterion(hasItem(Iron2), conditionsFromItem(Iron2))
        .criterion(hasItem(Iron3), conditionsFromItem(Iron3))
		.offerTo(exporter, getSmeltingItemPath(Items.IRON_NUGGET));

    }

    public static void smeltToGold(){

        Item Gold1 = ItemFactory.callItem("golden_long_sword");
        Item Gold2 = ItemFactory.callItem("golden_long_sword");
        Item Gold3 = ItemFactory.callItem("golden_long_sword");
        
        CookingRecipeJsonBuilder.createSmelting(
            Ingredient.ofItems(Gold1, Gold2, Gold3),
            RecipeCategory.MISC,
			Items.GOLD_NUGGET,
			0.1F,
			200
        )
        .criterion(hasItem(Gold1), conditionsFromItem(Gold1))
        .criterion(hasItem(Gold2), conditionsFromItem(Gold2))
        .criterion(hasItem(Gold3), conditionsFromItem(Gold3))
		.offerTo(exporter, getSmeltingItemPath(Items.GOLD_NUGGET));

    }


    public static void generateMosaic(String FamilyBase, Block Blocks, Block BlockSlab) {

        offerMosaicRecipe(exporter, RecipeCategory.DECORATIONS,  BlockFactoryUpt.callBlock(FamilyBase + "_mosaic"), BlockSlab);
        
        RecipeProvider
            .createStairsRecipe(BlockFactoryUpt.callBlock(FamilyBase + "_mosaic_stairs").asItem(), Ingredient.ofItems(Blocks))
            .group("wooden_stairs")
            .criterion(hasItem(BlockFactoryUpt.callBlock(FamilyBase + "_mosaic")), conditionsFromItem(BlockFactoryUpt.callBlock(FamilyBase + "_mosaic")))
            .offerTo(exporter);

        RecipeProvider
            .createSlabRecipe(RecipeCategory.BUILDING_BLOCKS, BlockFactoryUpt.callBlock(FamilyBase + "_mosaic_slab").asItem(), Ingredient.ofItems(Blocks))
            .group("wooden_slab")
            .criterion(hasItem(BlockFactoryUpt.callBlock(FamilyBase + "_mosaic")), conditionsFromItem(BlockFactoryUpt.callBlock(FamilyBase + "_mosaic")))
            .offerTo(exporter);

    }



    // Updated so us all of Us
    public static void generateWoodFamily(String FamilyBase, String varient){

        if (varient == null) {varient = "";}
        else {varient = "_" + varient;}

        Item FamilyHead = BlockFactoryUpt.callBlock(FamilyBase + "_planks" + varient).asItem();
        Ingredient FamilyI = Ingredient.ofItems(FamilyHead);

        RecipeProvider
            .createTrapdoorRecipe(BlockFactoryUpt.callBlock(FamilyBase + "_trapdoor" + varient).asItem(), FamilyI)
            .group("wooden_trapdoor")
            .criterion(hasItem(FamilyHead), conditionsFromItem(FamilyHead))
            .offerTo(exporter);

        RecipeProvider
            .createFenceRecipe(BlockFactoryUpt.callBlock(FamilyBase + "_fence" + varient).asItem(), FamilyI)
            .group("wooden_fence")
            .criterion(hasItem(FamilyHead), conditionsFromItem(FamilyHead))
            .offerTo(exporter);
    
        RecipeProvider
            .createFenceGateRecipe(BlockFactoryUpt.callBlock(FamilyBase + "_fence_gate" + varient).asItem(), FamilyI)
            .group("wooden_fence_gate")
            .criterion(hasItem(FamilyHead), conditionsFromItem(FamilyHead))
            .offerTo(exporter);
    
        RecipeProvider
            .createPressurePlateRecipe(RecipeCategory.BUILDING_BLOCKS, BlockFactoryUpt.callBlock(FamilyBase + "_pressure_plate" + varient).asItem(), FamilyI)
            .group("wooden_pressure_plate")
            .criterion(hasItem(FamilyHead), conditionsFromItem(FamilyHead))
            .offerTo(exporter);
    
        RecipeProvider
            .createSlabRecipe(RecipeCategory.BUILDING_BLOCKS, BlockFactoryUpt.callBlock(FamilyBase + "_slab" + varient).asItem(), FamilyI)
            .group("wooden_slab")
            .criterion(hasItem(FamilyHead), conditionsFromItem(FamilyHead))
            .offerTo(exporter);
    
        RecipeProvider
            .createStairsRecipe(BlockFactoryUpt.callBlock(FamilyBase + "_stairs" + varient).asItem(), FamilyI)
            .group("wooden_stairs")
            .criterion(hasItem(FamilyHead), conditionsFromItem(FamilyHead))
            .offerTo(exporter);
    
        RecipeProvider
            .createTransmutationRecipe(BlockFactoryUpt.callBlock(FamilyBase + "_button" + varient).asItem(), FamilyI)
            .group("wooden_button")
            .criterion(hasItem(FamilyHead), conditionsFromItem(FamilyHead))
            .offerTo(exporter);
    
        offerMosaicRecipe(exporter, RecipeCategory.DECORATIONS,  BlockFactoryUpt.callBlock(FamilyBase + "_mosaic" + varient), BlockFactoryUpt.callBlock(FamilyBase + "_slab" + varient));
        
        RecipeProvider
            .createStairsRecipe(BlockFactoryUpt.callBlock(FamilyBase + "_mosaic_stairs" + varient).asItem(), FamilyI)
            .group("wooden_stairs")
            .criterion(hasItem(BlockFactoryUpt.callBlock(FamilyBase + "_mosaic" + varient)), conditionsFromItem(BlockFactoryUpt.callBlock(FamilyBase + "_mosaic" + varient)))
            .offerTo(exporter);

        RecipeProvider
            .createSlabRecipe(RecipeCategory.BUILDING_BLOCKS, BlockFactoryUpt.callBlock(FamilyBase + "_mosaic_slab" + varient).asItem(), FamilyI)
            .group("wooden_slab")
            .criterion(hasItem(BlockFactoryUpt.callBlock(FamilyBase + "_mosaic" + varient)), conditionsFromItem(BlockFactoryUpt.callBlock(FamilyBase + "_mosaic" + varient)))
            .offerTo(exporter);

        //createGlassDoorRecipe(FamilyBase, varient, "wooden");

        Item GLASS_DOOR = BlockFactoryUpt.callBlock(FamilyBase + "_glass_door" + varient).asItem();
        Item DOOR = BlockFactoryUpt.callBlock(FamilyBase + "_door" + varient).asItem();

        Item GLASS_TRAPDOOR = BlockFactoryUpt.callBlock(FamilyBase + "_glass_trapdoor" + varient).asItem();
        Item TRAPDOOR = BlockFactoryUpt.callBlock(FamilyBase + "_trapdoor" + varient).asItem();

        ShapelessRecipeJsonBuilder.create(RecipeCategory.REDSTONE, GLASS_DOOR, 1)
            .input(DOOR,1)
            .input(Blocks.GLASS_PANE, 1)
            .group("wooden_glass_door")
            .criterion(hasItem(DOOR), conditionsFromItem(DOOR))
            .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.REDSTONE, GLASS_TRAPDOOR, 1)
            .input(TRAPDOOR,1)
            .input(Blocks.GLASS_PANE, 1)
            .group("wooden_glass_trapdoor")
            .criterion(hasItem(TRAPDOOR), conditionsFromItem(TRAPDOOR))
            .offerTo(exporter);


        RecipeProvider
            .createDoorRecipe(BlockFactoryUpt.callBlock(FamilyBase + "_door" + varient).asItem(), FamilyI)
            .group("wooden_door")
            .criterion(hasItem(FamilyHead), conditionsFromItem(FamilyHead))
            .offerTo(exporter);
    }

    public static CraftingRecipeJsonBuilder createGlassDoorRecipeTODO(ItemConvertible output, Ingredient input) {
		return 
        ShapedRecipeJsonBuilder.create(RecipeCategory.REDSTONE, output, 3).input('#', input)
        .pattern("##")
        .pattern("##")
        .pattern("##");
	}

    public void generateColorPlank(String color){

        Item result = BlockFactoryUpt.callBlock("fungal_planks_" + color).asItem();
        Item MATERIAL = BlockFactoryUpt.callBlock("fungal_planks").asItem();
        Item DYE = BlockFactoryUpt.getDye(color).asItem();

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, result, 8)
        .input('#', MATERIAL)
        .input('D', DYE)
        .pattern("###")
        .pattern("#D#")
        .pattern("###")
        .group("colored_planks")
        .criterion(hasItem(MATERIAL), conditionsFromItem(MATERIAL))
        .criterion(hasItem(DYE), conditionsFromItem(DYE))
        .offerTo(exporter);

        //Dyable Plank System

        ArrayList<Item> cplankGroup = new ArrayList<Item>();
        for (String Icolor : BlockFactoryUpt.COLORS){cplankGroup.add(ModBlocks.COLOR_PLANK(Icolor, "planks").asItem());}
    
        ArrayList<Item> plankFinal = new ArrayList<Item>();
        
        for (Item cplank : cplankGroup) {
            if (cplank != result) {
                plankFinal.add(cplank);
            }
        }

        Ingredient cplankI = Ingredient.ofItems(
            plankFinal.get(0),
            plankFinal.get(1),
            plankFinal.get(2),
            plankFinal.get(3),
            plankFinal.get(4),
            plankFinal.get(5),
            plankFinal.get(6),
            plankFinal.get(7),
            plankFinal.get(8),
            plankFinal.get(9),
            plankFinal.get(10),
            plankFinal.get(11),
            plankFinal.get(12),
            plankFinal.get(13),
            plankFinal.get(14)
        );;


        ShapelessRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, result, 1)
            .input(cplankI,1)
            .input(DYE, 1)
            .group("colored_planks")
            .criterion(hasItem(MATERIAL), conditionsFromItem(MATERIAL))
            .offerTo(exporter, "dye" + getItemPath(result));   


    }

    public void generateWoolFamily(String FamilyBase, String varient){

        Item wool = BlockFactoryUpt.getWoolColor(varient).asItem();

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, BlockFactoryUpt.callBlock(FamilyBase + "_" + varient))
            .input('#', wool)
            .pattern(" ##")
            .pattern(" ##")
            .pattern("   ")
            .group("textured_wool")
            .criterion(hasItem(wool), conditionsFromItem(wool))
            .offerTo(exporter);
        //Add Carpets
    }

    public static void createGlassExtras(Block ModdedFam, Block PLANK, Block DOOR, Block TRAPDOOR, String GROUP) {
        Item GLASS_DOOR = BlockFactoryUpt.callBlock(ModdedFam + "_glass_door").asItem();
        Item GLASS_TRAPDOOR = BlockFactoryUpt.callBlock(ModdedFam + "_glass_trapdoor").asItem();

        ShapelessRecipeJsonBuilder.create(RecipeCategory.REDSTONE, GLASS_DOOR, 1)
            .input(DOOR,1)
            .input(Blocks.GLASS_PANE, 1)
            .group(GROUP)
            .criterion(hasItem(PLANK), conditionsFromItem(PLANK))
            .offerTo(exporter, getItemPath(GLASS_DOOR));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.REDSTONE, GLASS_TRAPDOOR, 1)
            .input(TRAPDOOR,1)
            .input(Blocks.GLASS_PANE, 1)
            .group(GROUP)
            .criterion(hasItem(PLANK), conditionsFromItem(PLANK))
            .offerTo(exporter, getItemPath(GLASS_TRAPDOOR));
    }





    public static void createVanillaGlassDoor(String FamilyBase) {
        Block PLANK = ModBlocks.VANILLA_DOOR(FamilyBase);
        Block DOOR = ModBlocks.MOD_DOOR(FamilyBase + "_glass", null);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, DOOR, 1)
            .input(PLANK,1)
            .input(Blocks.GLASS_PANE, 1)
            .group("colored_planks")
            .criterion(hasItem(PLANK), conditionsFromItem(PLANK))
            .offerTo(exporter, getItemPath(DOOR)); 
    }

    public static void createVanillaGlassDoorC(String FamilyBase) {
        Block PLANK = ModBlocks.COPPER_DOOR(FamilyBase);
        Block DOOR = ModBlocks.MOD_DOOR(FamilyBase + "_glass", null);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, DOOR, 1)
            .input(PLANK,1)
            .input(Blocks.GLASS_PANE, 1)
            .group("cooper_doors")
            .criterion(hasItem(PLANK), conditionsFromItem(PLANK))
            .offerTo(exporter, getItemPath(DOOR)); 
    }

    public static void createVanillaGlassDoorI() {
        Block PLANK = Blocks.IRON_DOOR;
        Block DOOR = ModBlocks.MOD_DOOR("iron" + "_glass", null);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, DOOR, 1)
            .input(PLANK,1)
            .input(Blocks.GLASS_PANE, 1)
            .group("colored_planks")
            .criterion(hasItem(PLANK), conditionsFromItem(PLANK))
            .offerTo(exporter, getItemPath(DOOR)); 
    }

    public static void createPlanks(Block Planks, Ingredient Log){
        ShapelessRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, Planks, 4)
			.input(Log)
			.group("planks")
			.criterion(hasItem(Blocks.MUSHROOM_STEM), conditionsFromItem(Blocks.MUSHROOM_STEM))
            .criterion(hasItem(Blocks.RED_MUSHROOM_BLOCK), conditionsFromItem(Blocks.RED_MUSHROOM_BLOCK))
            .criterion(hasItem(Blocks.BROWN_MUSHROOM_BLOCK), conditionsFromItem(Blocks.BROWN_MUSHROOM_BLOCK))
			.offerTo(exporter);
    }

    public static void createLimestoneVarients() {

        Block LIMESTONE = BlockFactoryUpt.callBlock("limestone");
        Block POLISHED_LIMESTONE = BlockFactoryUpt.callBlock("polished_limestone");
        Block POLISHED_LIMESTONE_BRICKS = BlockFactoryUpt.callBlock("polished_limestone_bricks");


        //Limestone (Stonecutter)

        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, BlockFactoryUpt.callBlock("limestone_" + "slab").asItem(), LIMESTONE.asItem(), 2);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, BlockFactoryUpt.callBlock("limestone_" + "stairs").asItem(), LIMESTONE.asItem());
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, BlockFactoryUpt.callBlock("limestone_" + "wall").asItem(), LIMESTONE.asItem());

        //Limestone (Crafting Table)

        RecipeProvider.createSlabRecipe(RecipeCategory.BUILDING_BLOCKS, BlockFactoryUpt.callBlock("limestone_" + "slab").asItem(), 
            Ingredient.ofItems(LIMESTONE.asItem())).criterion(hasItem(LIMESTONE.asItem()), conditionsFromItem(LIMESTONE.asItem()))
        .offerTo(exporter);

        RecipeProvider.createStairsRecipe(BlockFactoryUpt.callBlock("limestone_" + "stairs").asItem(), 
            Ingredient.ofItems(LIMESTONE.asItem())).criterion(hasItem(LIMESTONE.asItem()), conditionsFromItem(LIMESTONE.asItem()))
        .offerTo(exporter);

        offerWallRecipe(exporter, RecipeCategory.DECORATIONS, BlockFactoryUpt.callBlock("limestone_" + "wall").asItem(), LIMESTONE.asItem());

        //Polished Limestone (Stonecutter)

        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, BlockFactoryUpt.callBlock("polished_limestone").asItem(), LIMESTONE);

        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, BlockFactoryUpt.callBlock("polished_limestone_" + "slab").asItem(), LIMESTONE.asItem(), 2);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, BlockFactoryUpt.callBlock("polished_limestone_" + "slab").asItem(), POLISHED_LIMESTONE.asItem(), 2);;

        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, BlockFactoryUpt.callBlock("polished_limestone_" + "stairs").asItem(), LIMESTONE.asItem());
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, BlockFactoryUpt.callBlock("polished_limestone_" + "stairs").asItem(), POLISHED_LIMESTONE.asItem());

        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, BlockFactoryUpt.callBlock("polished_limestone_" + "wall").asItem(), LIMESTONE.asItem());
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, BlockFactoryUpt.callBlock("polished_limestone_" + "wall").asItem(), POLISHED_LIMESTONE.asItem());

        //Polished Limestone (Crafting Table)

        RecipeProvider.createSlabRecipe(RecipeCategory.BUILDING_BLOCKS, BlockFactoryUpt.callBlock("polished_limestone_" + "slab").asItem(), 
            Ingredient.ofItems(LIMESTONE.asItem())).criterion(hasItem(LIMESTONE.asItem()), conditionsFromItem(LIMESTONE.asItem()))
        .offerTo(exporter);

        RecipeProvider.createStairsRecipe(BlockFactoryUpt.callBlock("polished_limestone_" + "stairs").asItem(), 
            Ingredient.ofItems(LIMESTONE.asItem())).criterion(hasItem(LIMESTONE.asItem()), conditionsFromItem(LIMESTONE.asItem()))
        .offerTo(exporter);

        offerWallRecipe(exporter, RecipeCategory.DECORATIONS, BlockFactoryUpt.callBlock("polished_limestone_" + "wall").asItem(), LIMESTONE.asItem());
        

        // Limestone Bricks

        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, POLISHED_LIMESTONE_BRICKS.asItem(), POLISHED_LIMESTONE.asItem());
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, POLISHED_LIMESTONE_BRICKS.asItem(), LIMESTONE.asItem());

        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, BlockFactoryUpt.callBlock("polished_limestone_brick_" + "slab").asItem(), POLISHED_LIMESTONE.asItem(), 2);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, BlockFactoryUpt.callBlock("polished_limestone_brick_" + "slab").asItem(), POLISHED_LIMESTONE_BRICKS.asItem(), 2);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, BlockFactoryUpt.callBlock("polished_limestone_brick_" + "slab").asItem(), LIMESTONE.asItem(), 2);

        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, BlockFactoryUpt.callBlock("polished_limestone_brick_" + "stairs").asItem(), POLISHED_LIMESTONE.asItem());
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, BlockFactoryUpt.callBlock("polished_limestone_brick_" + "stairs").asItem(), POLISHED_LIMESTONE_BRICKS.asItem());
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, BlockFactoryUpt.callBlock("polished_limestone_brick_" + "stairs").asItem(), LIMESTONE.asItem());

        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, BlockFactoryUpt.callBlock("polished_limestone_brick_" + "wall").asItem(), POLISHED_LIMESTONE.asItem());
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, BlockFactoryUpt.callBlock("polished_limestone_brick_" + "wall").asItem(), POLISHED_LIMESTONE_BRICKS.asItem());
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, BlockFactoryUpt.callBlock("polished_limestone_brick_" + "wall").asItem(), LIMESTONE.asItem());

        /*CookingRecipeJsonBuilder.createSmelting(
			Ingredient.ofItems(POLISHED_LIMESTONE_BRICKS.asItem()), RecipeCategory.BUILDING_BLOCKS, BlockFactoryUpt.callBlock("cracked_polished_limestone_brick").asItem(), 0.1F, 200
		)
		.criterion(hasItem(POLISHED_LIMESTONE_BRICKS.asItem().asItem()), conditionsFromItem(POLISHED_LIMESTONE_BRICKS.asItem().asItem()))
		.offerTo(exporter, "smelted_" + "cracked_polished_limestone_brick");*/

        RecipeProvider.createChiseledBlockRecipe(RecipeCategory.BUILDING_BLOCKS, BlockFactoryUpt.callBlock("polished_limestone_brick_chiseled"), Ingredient.ofItems(BlockFactoryUpt.callBlock("polished_limestone_brick_slab")))
        .criterion(hasItem(POLISHED_LIMESTONE_BRICKS.asItem().asItem()), conditionsFromItem(POLISHED_LIMESTONE_BRICKS.asItem()))
        .offerTo(exporter);


    }

    public static void createStoneVarient() {

        Block ANDERSITE_BRICKS = BlockFactoryUpt.callBlock("andesite_bricks");
        Block DIORITE_BRICKS = BlockFactoryUpt.callBlock("diorite_bricks");
        Block GRANITE_BRICKS = BlockFactoryUpt.callBlock("granite_bricks");

        //Andesite
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ANDERSITE_BRICKS.asItem(), Blocks.ANDESITE);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ANDERSITE_BRICKS.asItem(), Blocks.POLISHED_ANDESITE);

        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, BlockFactoryUpt.callBlock("andesite_brick_slab").asItem(), ANDERSITE_BRICKS.asItem(), 2);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, BlockFactoryUpt.callBlock("andesite_brick_slab").asItem(), Blocks.ANDESITE, 2);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, BlockFactoryUpt.callBlock("andesite_brick_slab").asItem(), Blocks.POLISHED_ANDESITE, 2);

		offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, BlockFactoryUpt.callBlock("andesite_brick_stairs").asItem(), ANDERSITE_BRICKS.asItem());
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, BlockFactoryUpt.callBlock("andesite_brick_stairs").asItem(), Blocks.ANDESITE);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, BlockFactoryUpt.callBlock("andesite_brick_stairs").asItem(), Blocks.POLISHED_ANDESITE);
		
        offerStonecuttingRecipe(exporter, RecipeCategory.DECORATIONS, BlockFactoryUpt.callBlock("andesite_brick_wall").asItem(), ANDERSITE_BRICKS.asItem());
        offerStonecuttingRecipe(exporter, RecipeCategory.DECORATIONS, BlockFactoryUpt.callBlock("andesite_brick_wall").asItem(), Blocks.ANDESITE);
        offerStonecuttingRecipe(exporter, RecipeCategory.DECORATIONS, BlockFactoryUpt.callBlock("andesite_brick_wall").asItem(), Blocks.POLISHED_ANDESITE);

        offerStonecuttingRecipe(exporter, RecipeCategory.DECORATIONS, BlockFactoryUpt.callBlock("andesite_brick_chiseled").asItem(), ANDERSITE_BRICKS.asItem());
        offerStonecuttingRecipe(exporter, RecipeCategory.DECORATIONS, BlockFactoryUpt.callBlock("andesite_brick_chiseled").asItem(), Blocks.ANDESITE);
        offerStonecuttingRecipe(exporter, RecipeCategory.DECORATIONS, BlockFactoryUpt.callBlock("andesite_brick_chiseled").asItem(), Blocks.POLISHED_ANDESITE);

        RecipeProvider.createSlabRecipe(RecipeCategory.BUILDING_BLOCKS, BlockFactoryUpt.callBlock("andesite_brick_slab").asItem(), Ingredient.ofItems(ANDERSITE_BRICKS.asItem())).criterion(hasItem(ANDERSITE_BRICKS.asItem()), conditionsFromItem(ANDERSITE_BRICKS.asItem()))
        .offerTo(exporter);
        RecipeProvider.createStairsRecipe(BlockFactoryUpt.callBlock("andesite_brick_stairs").asItem(), Ingredient.ofItems(ANDERSITE_BRICKS.asItem())).criterion(hasItem(ANDERSITE_BRICKS.asItem()), conditionsFromItem(ANDERSITE_BRICKS.asItem()))
        .offerTo(exporter);
        offerWallRecipe(exporter, RecipeCategory.DECORATIONS, BlockFactoryUpt.callBlock("andesite_brick_wall").asItem(), ANDERSITE_BRICKS.asItem());

        CookingRecipeJsonBuilder.createSmelting(
			Ingredient.ofItems(ANDERSITE_BRICKS), RecipeCategory.BUILDING_BLOCKS, BlockFactoryUpt.callBlock("cracked_andesite_bricks").asItem(), 0.1F, 200
		)
		.criterion(hasItem(ANDERSITE_BRICKS.asItem()), conditionsFromItem(ANDERSITE_BRICKS.asItem()))
		.offerTo(exporter, "smelted_" + "cracked_andesite_bricks");

        RecipeProvider.createChiseledBlockRecipe(RecipeCategory.BUILDING_BLOCKS, BlockFactoryUpt.callBlock("andesite_brick_chiseled"), Ingredient.ofItems(BlockFactoryUpt.callBlock("andesite_brick_slab")))
        .criterion(hasItem(ANDERSITE_BRICKS.asItem()), conditionsFromItem(ANDERSITE_BRICKS))
        .offerTo(exporter);


        //Granite
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, GRANITE_BRICKS.asItem(), Blocks.GRANITE);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, GRANITE_BRICKS.asItem(), Blocks.POLISHED_GRANITE);

        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, BlockFactoryUpt.callBlock("granite_brick_slab").asItem(), GRANITE_BRICKS.asItem(), 2);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, BlockFactoryUpt.callBlock("granite_brick_slab").asItem(), Blocks.GRANITE, 2);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, BlockFactoryUpt.callBlock("granite_brick_slab").asItem(), Blocks.POLISHED_GRANITE, 2);

		offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, BlockFactoryUpt.callBlock("granite_brick_stairs").asItem(), GRANITE_BRICKS.asItem());
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, BlockFactoryUpt.callBlock("granite_brick_stairs").asItem(), Blocks.GRANITE);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, BlockFactoryUpt.callBlock("granite_brick_stairs").asItem(), Blocks.POLISHED_GRANITE);
		
        offerStonecuttingRecipe(exporter, RecipeCategory.DECORATIONS, BlockFactoryUpt.callBlock("granite_brick_wall").asItem(), GRANITE_BRICKS.asItem());
        offerStonecuttingRecipe(exporter, RecipeCategory.DECORATIONS, BlockFactoryUpt.callBlock("granite_brick_wall").asItem(), Blocks.GRANITE);
        offerStonecuttingRecipe(exporter, RecipeCategory.DECORATIONS, BlockFactoryUpt.callBlock("granite_brick_wall").asItem(), Blocks.POLISHED_GRANITE);

        offerStonecuttingRecipe(exporter, RecipeCategory.DECORATIONS, BlockFactoryUpt.callBlock("granite_brick_chiseled").asItem(), GRANITE_BRICKS.asItem());
        offerStonecuttingRecipe(exporter, RecipeCategory.DECORATIONS, BlockFactoryUpt.callBlock("granite_brick_chiseled").asItem(), Blocks.GRANITE);
        offerStonecuttingRecipe(exporter, RecipeCategory.DECORATIONS, BlockFactoryUpt.callBlock("granite_brick_chiseled").asItem(), Blocks.POLISHED_GRANITE);

        RecipeProvider.createSlabRecipe(RecipeCategory.BUILDING_BLOCKS, BlockFactoryUpt.callBlock("granite_brick_slab").asItem(), Ingredient.ofItems(GRANITE_BRICKS.asItem())).criterion(hasItem(GRANITE_BRICKS.asItem()), conditionsFromItem(GRANITE_BRICKS.asItem()))
        .offerTo(exporter);
        RecipeProvider.createStairsRecipe(BlockFactoryUpt.callBlock("granite_brick_stairs").asItem(), Ingredient.ofItems(GRANITE_BRICKS.asItem())).criterion(hasItem(GRANITE_BRICKS.asItem()), conditionsFromItem(GRANITE_BRICKS.asItem()))
        .offerTo(exporter);
        offerWallRecipe(exporter, RecipeCategory.DECORATIONS, BlockFactoryUpt.callBlock("granite_brick_wall").asItem(), GRANITE_BRICKS.asItem());

        CookingRecipeJsonBuilder.createSmelting(
			Ingredient.ofItems(GRANITE_BRICKS), RecipeCategory.BUILDING_BLOCKS, BlockFactoryUpt.callBlock("cracked_granite_bricks").asItem(), 0.1F, 200
		)
		.criterion(hasItem(GRANITE_BRICKS.asItem()), conditionsFromItem(GRANITE_BRICKS.asItem()))
		.offerTo(exporter, "smelted_" + "cracked_granite_bricks");

        RecipeProvider.createChiseledBlockRecipe(RecipeCategory.BUILDING_BLOCKS, BlockFactoryUpt.callBlock("granite_brick_chiseled"), Ingredient.ofItems(BlockFactoryUpt.callBlock("granite_brick_slab")))
        .criterion(hasItem(GRANITE_BRICKS.asItem()), conditionsFromItem(GRANITE_BRICKS))
        .offerTo(exporter);
        

        //Diorite
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, DIORITE_BRICKS.asItem(), Blocks.DIORITE);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, DIORITE_BRICKS.asItem(), Blocks.POLISHED_DIORITE);

        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, BlockFactoryUpt.callBlock("diorite_brick_slab").asItem(), DIORITE_BRICKS.asItem(), 2);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, BlockFactoryUpt.callBlock("diorite_brick_slab").asItem(), Blocks.DIORITE, 2);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, BlockFactoryUpt.callBlock("diorite_brick_slab").asItem(), Blocks.POLISHED_DIORITE, 2);

		offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, BlockFactoryUpt.callBlock("diorite_brick_stairs").asItem(), DIORITE_BRICKS.asItem());
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, BlockFactoryUpt.callBlock("diorite_brick_stairs").asItem(), Blocks.DIORITE);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, BlockFactoryUpt.callBlock("diorite_brick_stairs").asItem(), Blocks.POLISHED_DIORITE);
		
        offerStonecuttingRecipe(exporter, RecipeCategory.DECORATIONS, BlockFactoryUpt.callBlock("diorite_brick_wall").asItem(), DIORITE_BRICKS.asItem());
        offerStonecuttingRecipe(exporter, RecipeCategory.DECORATIONS, BlockFactoryUpt.callBlock("diorite_brick_wall").asItem(), Blocks.DIORITE);
        offerStonecuttingRecipe(exporter, RecipeCategory.DECORATIONS, BlockFactoryUpt.callBlock("diorite_brick_wall").asItem(), Blocks.POLISHED_DIORITE);

        offerStonecuttingRecipe(exporter, RecipeCategory.DECORATIONS, BlockFactoryUpt.callBlock("diorite_brick_chiseled").asItem(), DIORITE_BRICKS.asItem());
        offerStonecuttingRecipe(exporter, RecipeCategory.DECORATIONS, BlockFactoryUpt.callBlock("diorite_brick_chiseled").asItem(), Blocks.DIORITE);
        offerStonecuttingRecipe(exporter, RecipeCategory.DECORATIONS, BlockFactoryUpt.callBlock("diorite_brick_chiseled").asItem(), Blocks.POLISHED_DIORITE);

        RecipeProvider.createSlabRecipe(RecipeCategory.BUILDING_BLOCKS, BlockFactoryUpt.callBlock("diorite_brick_slab").asItem(), Ingredient.ofItems(DIORITE_BRICKS.asItem())).criterion(hasItem(DIORITE_BRICKS.asItem()), conditionsFromItem(DIORITE_BRICKS.asItem()))
        .offerTo(exporter);
        RecipeProvider.createStairsRecipe(BlockFactoryUpt.callBlock("diorite_brick_stairs").asItem(), Ingredient.ofItems(DIORITE_BRICKS.asItem())).criterion(hasItem(DIORITE_BRICKS.asItem()), conditionsFromItem(DIORITE_BRICKS.asItem()))
        .offerTo(exporter);
        offerWallRecipe(exporter, RecipeCategory.DECORATIONS, BlockFactoryUpt.callBlock("diorite_brick_wall").asItem(), DIORITE_BRICKS.asItem());

        CookingRecipeJsonBuilder.createSmelting(
			Ingredient.ofItems(DIORITE_BRICKS), RecipeCategory.BUILDING_BLOCKS, BlockFactoryUpt.callBlock("cracked_diorite_bricks").asItem(), 0.1F, 200
		)
		.criterion(hasItem(DIORITE_BRICKS.asItem()), conditionsFromItem(DIORITE_BRICKS.asItem()))
		.offerTo(exporter, "smelted_" + "cracked_diorite_bricks");

        RecipeProvider.createChiseledBlockRecipe(RecipeCategory.BUILDING_BLOCKS, BlockFactoryUpt.callBlock("diorite_brick_chiseled"), Ingredient.ofItems(BlockFactoryUpt.callBlock("diorite_brick_slab")))
			.criterion(hasItem(DIORITE_BRICKS.asItem()), conditionsFromItem(DIORITE_BRICKS))
		.offerTo(exporter);


        offerWallRecipe(exporter, RecipeCategory.DECORATIONS, BlockFactoryUpt.callBlock("polished_andesite_wall").asItem(), Blocks.POLISHED_ANDESITE.asItem());
        offerWallRecipe(exporter, RecipeCategory.DECORATIONS, BlockFactoryUpt.callBlock("polished_granite_wall").asItem(), Blocks.POLISHED_GRANITE.asItem());
        offerWallRecipe(exporter, RecipeCategory.DECORATIONS, BlockFactoryUpt.callBlock("polished_diorite_wall").asItem(), Blocks.POLISHED_DIORITE.asItem());

        offerStonecuttingRecipe(exporter, RecipeCategory.DECORATIONS, BlockFactoryUpt.callBlock("polished_andesite_wall").asItem(), Blocks.POLISHED_ANDESITE);
        offerStonecuttingRecipe(exporter, RecipeCategory.DECORATIONS, BlockFactoryUpt.callBlock("polished_granite_wall").asItem(), Blocks.POLISHED_GRANITE);
        offerStonecuttingRecipe(exporter, RecipeCategory.DECORATIONS, BlockFactoryUpt.callBlock("polished_diorite_wall").asItem(), Blocks.POLISHED_DIORITE);



        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ANDERSITE_BRICKS, 4)
			.input('#', Blocks.POLISHED_ANDESITE)
			.pattern("##")
			.pattern("##")
			.criterion(hasItem(Blocks.POLISHED_ANDESITE), conditionsFromItem(Blocks.POLISHED_ANDESITE))
			.offerTo(exporter);
        
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, DIORITE_BRICKS, 4)
			.input('#', Blocks.POLISHED_DIORITE)
			.pattern("##")
			.pattern("##")
			.criterion(hasItem(Blocks.POLISHED_DIORITE), conditionsFromItem(Blocks.POLISHED_DIORITE))
			.offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, GRANITE_BRICKS, 4)
			.input('#', Blocks.POLISHED_GRANITE)
			.pattern("##")
			.pattern("##")
			.criterion(hasItem(Blocks.POLISHED_GRANITE), conditionsFromItem(Blocks.POLISHED_GRANITE))
			.offerTo(exporter);
            
    }



























    ////////////////////////////////////////////


    //Recipe Creator for Long Sword




    ///////////////////


    
    //Recipe Create for Wood Group
    @Deprecated
    public static void createWoodGroup(String baseItemName, String endTag){
        Ingredient baseItemI = Ingredient.ofItems(BlockFactoryUpt.callBlock(baseItemName + "_planks" + endTag).asItem());
        Item baseItem = (BlockFactoryUpt.callBlock(baseItemName + "_planks" + endTag).asItem());

        RecipeProvider.createTrapdoorRecipe(BlockFactoryUpt.callBlock(baseItemName + "_trapdoor" + endTag).asItem(), baseItemI)
        .group("wooden_trapdoor")
        .criterion(hasItem(baseItem), conditionsFromItem(baseItem))
        .offerTo(exporter);

        RecipeProvider.createFenceRecipe(BlockFactoryUpt.callBlock(baseItemName + "_fence" + endTag).asItem(), baseItemI)
        .group("wooden_fence")
        .criterion(hasItem(baseItem), conditionsFromItem(baseItem))
        .offerTo(exporter);

        RecipeProvider.createFenceGateRecipe(BlockFactoryUpt.callBlock(baseItemName + "_fence_gate" + endTag).asItem(), baseItemI)
        .group("wooden_fence_gate")
        .criterion(hasItem(baseItem), conditionsFromItem(baseItem))
        .offerTo(exporter);

        RecipeProvider.createPressurePlateRecipe(RecipeCategory.BUILDING_BLOCKS, BlockFactoryUpt.callBlock(baseItemName + "_pressure_plate" + endTag).asItem(), baseItemI)
        .group("wooden_pressure_plate")
        .criterion(hasItem(baseItem), conditionsFromItem(baseItem))
        .offerTo(exporter);

        RecipeProvider.createSlabRecipe(RecipeCategory.BUILDING_BLOCKS, BlockFactoryUpt.callBlock(baseItemName + "_slab" + endTag).asItem(), baseItemI)
        .group("wooden_slab")
        .criterion(hasItem(baseItem), conditionsFromItem(baseItem))
        .offerTo(exporter);

        RecipeProvider.createStairsRecipe(BlockFactoryUpt.callBlock(baseItemName + "_stairs" + endTag).asItem(), baseItemI)
        .group("wooden_stairs")
        .criterion(hasItem(baseItem), conditionsFromItem(baseItem))
        .offerTo(exporter);

        RecipeProvider.createTransmutationRecipe(BlockFactoryUpt.callBlock(baseItemName + "_button" + endTag).asItem(), baseItemI)
        .group("wooden_button")
        .criterion(hasItem(baseItem), conditionsFromItem(baseItem))
        .offerTo(exporter);

        RecipeProvider.createDoorRecipe(BlockFactoryUpt.callBlock(baseItemName + "_door" + endTag).asItem(), baseItemI)
        .group("wooden_door")
        .criterion(hasItem(baseItem), conditionsFromItem(baseItem))
        .offerTo(exporter);
    }

    //Recipe Create for Color Planks
    @Deprecated
    public static void createColorPlanks(Item dye, ItemConvertible output, String color) {

		ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, output, 8)
        .input('#', BlockFactoryUpt.callBlock("fungal_planks").asItem())
        .input('D', dye)
        .pattern("###")
        .pattern("#D#")
        .pattern("###")
        .group("colored_planks")
        .criterion(hasItem(BlockFactoryUpt.callBlock("fungal_planks").asItem()), conditionsFromItem(BlockFactoryUpt.callBlock("fungal_planks").asItem()))
        .criterion(hasItem(dye), conditionsFromItem(dye))
        .offerTo(exporter);
        
        createWoodGroup("fungal", "_" + color);
	}

    //Recipe Create for Modded Weapons - Wood & Stone
    @Deprecated
    public static void createModdedWeapon(Item Material, String baseMat, TagKey<Item> matTag) {

        createLongSword(Material, ItemFactory.callItem(baseMat + "_long_sword"), matTag);
        createDagger(Material, ItemFactory.callItem(baseMat + "_dagger"), matTag);
        createHammer(Material, ItemFactory.callItem(baseMat + "_hammer"), matTag);

    }

    //Recipe Create for Modded Weapons - Gold, Iron & Diamond
    @Deprecated
    public static void createModdedWeapon(Item Material, String baseMat) {

        createLongSword(Material, ItemFactory.callItem(baseMat + "_long_sword"));
        createDagger(Material, ItemFactory.callItem(baseMat + "_dagger"));
        createHammer(Material, ItemFactory.callItem(baseMat + "_hammer"));

        boolean Enabled = false;

        if ("iron" == baseMat && Enabled){
            generateMeltIron(Ingredient.ofItems(
                ItemFactory.callItem(baseMat + "_long_sword"),
                ItemFactory.callItem(baseMat + "_dagger"),
                ItemFactory.callItem(baseMat + "_hammer")
            ));   
        } 
        else if ("golden" == baseMat && Enabled){
            generateMeltGold(Ingredient.ofItems(
                ItemFactory.callItem(baseMat + "_long_sword"),
                ItemFactory.callItem(baseMat + "_dagger"),
                ItemFactory.callItem(baseMat + "_hammer")
            )); 
        }

    }

    //Recipe Melt to Gold Nuggets
    @Deprecated
    public static void generateMeltGold(Ingredient SmeltItems) {
        CookingRecipeJsonBuilder.createSmelting(
            SmeltItems,
            RecipeCategory.MISC,
			Items.GOLD_NUGGET,
			0.1F,
			200
        )
        .criterion(hasItem(ItemFactory.callItem(Items.GOLD_INGOT + "_long_sword")), conditionsFromItem(ItemFactory.callItem(Items.GOLD_INGOT + "_long_sword")))
        .criterion(hasItem(ItemFactory.callItem(Items.GOLD_INGOT + "_dagger")), conditionsFromItem(ItemFactory.callItem(Items.GOLD_INGOT + "_dagger")))
        .criterion(hasItem(ItemFactory.callItem(Items.GOLD_INGOT + "_hammer")), conditionsFromItem(ItemFactory.callItem(Items.GOLD_INGOT + "_hammer")))
		.offerTo(exporter, getSmeltingItemPath(Items.GOLD_NUGGET));
    }

    //Recipe Melt to Iron Nuggets
    @Deprecated
    public static void generateMeltIron(Ingredient SmeltItems) {
        CookingRecipeJsonBuilder.createSmelting(
            SmeltItems,
            RecipeCategory.MISC,
			Items.IRON_NUGGET,
			0.1F,
			200
        )
        .criterion(hasItem(ItemFactory.callItem(Items.IRON_INGOT + "_long_sword")), conditionsFromItem(ItemFactory.callItem(Items.IRON_INGOT + "_long_sword")))
        .criterion(hasItem(ItemFactory.callItem(Items.IRON_INGOT + "_dagger")), conditionsFromItem(ItemFactory.callItem(Items.IRON_INGOT + "_dagger")))
        .criterion(hasItem(ItemFactory.callItem(Items.IRON_INGOT + "_hammer")), conditionsFromItem(ItemFactory.callItem(Items.IRON_INGOT + "_hammer")))
		.offerTo(exporter, getSmeltingItemPath(Items.IRON_NUGGET));
    }

}
