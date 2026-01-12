package net.ent.entstupidstuff.datagen;

import java.util.concurrent.CompletableFuture;
import java.util.ArrayList;

import net.ent.entstupidstuff.block.BlockFactory;
import net.ent.entstupidstuff.block.ModBlocks;
import net.ent.entstupidstuff.item.ItemFactory;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CampfireCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SmokingRecipe;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class ModRecipeProvider extends FabricRecipeProvider{

    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    public static RecipeOutput exporter;

    //A way to enable and disable shit //TODO: Implement
    Boolean enableWeapons = true;
    Boolean enableClaymore = false;
    Boolean enableHammer = true;
    Boolean enableGlaive = false;
    Boolean enableBattleAxe = false; //Disabled
    Boolean enableLongSword = false;
    Boolean enableCannon = true;
    Boolean enableETC = true;

    Boolean enableFungal = true;
    Boolean enableMaple = true;
    Boolean enablePhantom = true;
    Boolean enableFir = true;
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
    protected RecipeProvider createRecipeProvider(HolderLookup.Provider wrapperLookup, RecipeOutput recipeExporter) {
        return new RecipeProvider(wrapperLookup, recipeExporter) {

            @Override
            public void buildRecipes() {

                if (enableWeapons) {

                    if (enableClaymore) {
                        createClaymore(Items.STICK, ItemFactory.callItem("wooden" + "_claymore"), ItemTags.PLANKS);
                        createClaymore(Items.COBBLESTONE, ItemFactory.callItem("stone" + "_claymore"), ItemTags.STONE_TOOL_MATERIALS);
                        createClaymore(Items.IRON_INGOT, ItemFactory.callItem("iron" + "_claymore"));
                        createClaymore(Items.GOLD_INGOT, ItemFactory.callItem("golden" + "_claymore"));
                        createClaymore(Items.DIAMOND, ItemFactory.callItem("diamond" + "_claymore"));

                        if (enableNetherite)
                            netheriteSmithing(ItemFactory.callItem("diamond_claymore"), RecipeCategory.COMBAT, ItemFactory.callItem("netherite_claymore"));
                    }

                    if (enableGlaive) {
                        createGlaive(Items.STICK, ItemFactory.callItem("wooden" + "_glaive"), ItemTags.PLANKS);
                        createGlaive(Items.COBBLESTONE, ItemFactory.callItem("stone" + "_glaive"), ItemTags.STONE_TOOL_MATERIALS);
                        createGlaive(Items.IRON_INGOT, ItemFactory.callItem("iron" + "_glaive"));
                        createGlaive(Items.GOLD_INGOT, ItemFactory.callItem("golden" + "_glaive"));
                        createGlaive(Items.DIAMOND, ItemFactory.callItem("diamond" + "_glaive"));

                        if (enableNetherite)
                            netheriteSmithing(ItemFactory.callItem("diamond_glaive"), RecipeCategory.COMBAT, ItemFactory.callItem("netherite_glaive"));
                    }

                    if (enableHammer) {
                        createHammerUpdated(Items.STICK, ItemFactory.callItem("wooden" + "_hammer"), ItemTags.PLANKS);
                        createHammerUpdated(Items.COBBLESTONE, ItemFactory.callItem("stone" + "_hammer"), ItemTags.STONE_TOOL_MATERIALS);
                        createHammerUpdated(Items.IRON_INGOT, ItemFactory.callItem("iron" + "_hammer"));
                        createHammerUpdated(Items.GOLD_INGOT, ItemFactory.callItem("golden" + "_hammer"));
                        createHammerUpdated(Items.DIAMOND, ItemFactory.callItem("diamond" + "_hammer"));

                        if (enableHammer)
                            netheriteSmithing(ItemFactory.callItem("diamond_hammer"), RecipeCategory.COMBAT, ItemFactory.callItem("netherite_hammer"));
                    }

                    if (enableBattleAxe) {
                        createBattleAxeUpdated(Items.STICK, ItemFactory.callItem("wooden" + "_battleaxe"), ItemTags.PLANKS);
                        createBattleAxeUpdated(Items.COBBLESTONE, ItemFactory.callItem("stone" + "_battleaxe"), ItemTags.STONE_TOOL_MATERIALS);
                        createBattleAxeUpdated(Items.IRON_INGOT, ItemFactory.callItem("iron" + "_battleaxe"));
                        createBattleAxeUpdated(Items.GOLD_INGOT, ItemFactory.callItem("golden" + "_battleaxe"));
                        createBattleAxeUpdated(Items.DIAMOND, ItemFactory.callItem("diamond" + "_battleaxe"));

                        if (enableNetherite)
                            netheriteSmithing(ItemFactory.callItem("diamond_battleaxe"), RecipeCategory.COMBAT, ItemFactory.callItem("netherite_battleaxe"));
                    }

                    if (enableLongSword) {
                        createLongSwordUpdated(Items.STICK, ItemFactory.callItem("wooden" + "_long_sword"), ItemTags.PLANKS);
                        createLongSwordUpdated(Items.COBBLESTONE, ItemFactory.callItem("stone" + "_long_sword"), ItemTags.STONE_TOOL_MATERIALS);
                        createLongSwordUpdated(Items.IRON_INGOT, ItemFactory.callItem("iron" + "_long_sword"));
                        createLongSwordUpdated(Items.GOLD_INGOT, ItemFactory.callItem("golden" + "_long_sword"));
                        createLongSwordUpdated(Items.DIAMOND, ItemFactory.callItem("diamond" + "_long_sword"));

                        if (enableNetherite)
                            netheriteSmithing(ItemFactory.callItem("diamond_long_sword"), RecipeCategory.COMBAT, ItemFactory.callItem("netherite_long_sword"));
                    }

                    if (enableCannon) {
                        //Will be future area for Cannon
                    }
                }

                generateMosaic("oak", BlockFactory.callBlock("oak" + "_mosaic"), Blocks.OAK_SLAB);
                generateMosaic("spruce", BlockFactory.callBlock("spruce" + "_mosaic"), Blocks.SPRUCE_SLAB);
                generateMosaic("jungle", BlockFactory.callBlock("jungle" + "_mosaic"), Blocks.JUNGLE_SLAB);
                generateMosaic("birch", BlockFactory.callBlock("birch" + "_mosaic"), Blocks.BIRCH_SLAB);
                generateMosaic("dark_oak", BlockFactory.callBlock("dark_oak" + "_mosaic"), Blocks.DARK_OAK_SLAB);
                generateMosaic("acacia", BlockFactory.callBlock("acacia" + "_mosaic"), Blocks.ACACIA_SLAB);
                generateMosaic("mangrove", BlockFactory.callBlock("mangrove" + "_mosaic"), Blocks.MANGROVE_SLAB);
                generateMosaic("cherry", BlockFactory.callBlock("cherry" + "_mosaic"), Blocks.CHERRY_SLAB);
                generateMosaic("warped", BlockFactory.callBlock("warped" + "_mosaic"), Blocks.WARPED_SLAB);
                generateMosaic("crimson", BlockFactory.callBlock("crimson" + "_mosaic"), Blocks.CRIMSON_SLAB);
                

                //Modded Wood
                if (enableFungal)
                    generateWoodFamily("fungal", null);
                if (enableRedwood)
                    generateWoodFamily("redwood", null);
                if (enableFir)
                    generateWoodFamily("fir", null);
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
                    Item FUNGAL_PLANK = BlockFactory.callBlock("fungal_planks").asItem();
                    shapeless(RecipeCategory.BUILDING_BLOCKS, FUNGAL_PLANK, 4)
                        .requires(Ingredient.of(Blocks.MUSHROOM_STEM, Blocks.RED_MUSHROOM_BLOCK, Blocks.BROWN_MUSHROOM_BLOCK, BlockFactory.callBlock("blue_mushroom_block")))
                        .group("planks")
                        .unlockedBy(getHasName(Blocks.MUSHROOM_STEM), has(Blocks.MUSHROOM_STEM))
                        .unlockedBy(getHasName(Blocks.RED_MUSHROOM_BLOCK), has(Blocks.RED_MUSHROOM_BLOCK))
                        .unlockedBy(getHasName(Blocks.BROWN_MUSHROOM_BLOCK), has(Blocks.BROWN_MUSHROOM_BLOCK))
                    .save(output);
                }

                if (enableRedwood) {
                    Item REDWOOD_PLANK = BlockFactory.callBlock("redwood_planks").asItem();
                    Block REDWOOD_LOG = BlockFactory.callBlock("redwood_log");
                    shapeless(RecipeCategory.BUILDING_BLOCKS, REDWOOD_PLANK, 4)
                        .requires(REDWOOD_LOG.asItem())
                        .group("planks")
                        .unlockedBy(getHasName(REDWOOD_LOG), has(REDWOOD_LOG))
                    .save(output);
                }

                if (enableFir) {
                    Item FIR_PLANK = BlockFactory.callBlock("fir_planks").asItem();
                    Block FIR_LOG = BlockFactory.callBlock("fir_log");
                    shapeless(RecipeCategory.BUILDING_BLOCKS, FIR_PLANK, 4)
                        .requires(FIR_LOG.asItem())
                        .group("planks")
                        .unlockedBy(getHasName(FIR_LOG), has(FIR_LOG))
                    .save(output);
                }

                if (enableMaple) {
                    Item MAPLE_PLANK = BlockFactory.callBlock("maple_planks").asItem();
                    Block MAPLE_LOG = BlockFactory.callBlock("maple_log");
                    shapeless(RecipeCategory.BUILDING_BLOCKS, MAPLE_PLANK, 4)
                        .requires(MAPLE_LOG.asItem())
                        .group("planks")
                        .unlockedBy(getHasName(MAPLE_LOG), has(MAPLE_LOG))
                    .save(output);
                }

                if (enablePhantom) {
                    Item PHANTOM_PLANK = BlockFactory.callBlock("phantom_planks").asItem();
                    Block PHATOM_LOG = BlockFactory.callBlock("phantom_log");
                    shapeless(RecipeCategory.BUILDING_BLOCKS, PHANTOM_PLANK, 4)
                        .requires(PHATOM_LOG.asItem())
                        .group("planks")
                        .unlockedBy(getHasName(PHATOM_LOG), has(PHATOM_LOG))
                    .save(output);
                }
                
                if (enableAdditionalStone) {
                    createStoneVarient();
                }

                if (enableLimestoneRecipes) {
                    createLimestoneVarients();
                }



                //Vanilla GlassDoor
                if (enableVanillaGlassDoor) {
                    createGlassDoorRecipe(BlockFactory.callBlock("oak" + "_glass_door"), Blocks.OAK_DOOR, Blocks.OAK_PLANKS.asItem(), true);
                    createGlassDoorRecipe(BlockFactory.callBlock("spruce" + "_glass_door"), Blocks.SPRUCE_DOOR, Blocks.SPRUCE_PLANKS.asItem(), true);
                    createGlassDoorRecipe(BlockFactory.callBlock("jungle" + "_glass_door"), Blocks.JUNGLE_DOOR, Blocks.JUNGLE_PLANKS.asItem(), true);
                    createGlassDoorRecipe(BlockFactory.callBlock("birch" + "_glass_door"), Blocks.BIRCH_DOOR, Blocks.BIRCH_PLANKS.asItem(), true);
                    createGlassDoorRecipe(BlockFactory.callBlock("dark_oak" + "_glass_door"), Blocks.DARK_OAK_DOOR, Blocks.DARK_OAK_PLANKS.asItem(), true);
                    createGlassDoorRecipe(BlockFactory.callBlock("acacia" + "_glass_door"), Blocks.ACACIA_DOOR, Blocks.ACACIA_PLANKS.asItem(), true);
                    createGlassDoorRecipe(BlockFactory.callBlock("mangrove" + "_glass_door"), Blocks.MANGROVE_DOOR, Blocks.MANGROVE_PLANKS.asItem(), true);
                    createGlassDoorRecipe(BlockFactory.callBlock("cherry" + "_glass_door"), Blocks.CHERRY_DOOR, Blocks.CHERRY_PLANKS.asItem(), true);
                    createGlassDoorRecipe(BlockFactory.callBlock("bamboo" + "_glass_door"), Blocks.BAMBOO_DOOR, Blocks.BAMBOO_PLANKS.asItem(), true);
                    createGlassDoorRecipe(BlockFactory.callBlock("warped" + "_glass_door"), Blocks.WARPED_DOOR, Blocks.WARPED_PLANKS.asItem(), true);
                    createGlassDoorRecipe(BlockFactory.callBlock("crimson" + "_glass_door"), Blocks.CRIMSON_DOOR, Blocks.CRIMSON_PLANKS.asItem(), true);
                    //createGlassDoorRecipe(BlockFactoryUpt.callBlock("pale_oak" + "_glass_door"), Blocks.PALE_OAK_DOOR, Blocks.PALE_OAK_PLANKS.asItem()); //FUTURE UPDATE
            
                    createGlassDoorRecipe(BlockFactory.callBlock("iron" + "_glass_door"), Blocks.IRON_DOOR, Items.IRON_INGOT, false);
                    createGlassDoorRecipe(BlockFactory.callBlock("copper" + "_glass_door"), Blocks.COPPER_DOOR, Items.COPPER_INGOT, false);
                    createGlassDoorRecipe(BlockFactory.callBlock("exposed_copper" + "_glass_door"), Blocks.EXPOSED_COPPER_DOOR, Items.COPPER_INGOT, false);
                    createGlassDoorRecipe(BlockFactory.callBlock("oxidized_copper" + "_glass_door"), Blocks.OXIDIZED_COPPER_DOOR, Items.COPPER_INGOT, false);
                    createGlassDoorRecipe(BlockFactory.callBlock("weathered_copper" + "_glass_door"), Blocks.WEATHERED_COPPER_DOOR, Items.COPPER_INGOT, false);
                    createGlassDoorRecipe(BlockFactory.callBlock("waxed_copper" + "_glass_door"), Blocks.WAXED_COPPER_DOOR, Items.COPPER_INGOT, false);
                    createGlassDoorRecipe(BlockFactory.callBlock("waxed_exposed_copper" + "_glass_door"), Blocks.WAXED_EXPOSED_COPPER_DOOR, Items.COPPER_INGOT, false);
                    createGlassDoorRecipe(BlockFactory.callBlock("waxed_oxidized_copper" + "_glass_door"), Blocks.WAXED_OXIDIZED_COPPER_DOOR, Items.COPPER_INGOT, false);
                    createGlassDoorRecipe(BlockFactory.callBlock("waxed_weathered_copper" + "_glass_door"), Blocks.WAXED_WEATHERED_COPPER_DOOR, Items.COPPER_INGOT, false);

                }
                



                //Vanilla GlassTrapDoor
                if (enableVanillaGlassTrapDoor) {
                    createGlassTrapDoorRecipe(BlockFactory.callBlock("oak" + "_glass_trapdoor"), Blocks.OAK_TRAPDOOR, Blocks.OAK_PLANKS.asItem(), true);
                    createGlassTrapDoorRecipe(BlockFactory.callBlock("spruce" + "_glass_trapdoor"), Blocks.SPRUCE_TRAPDOOR, Blocks.SPRUCE_PLANKS.asItem(), true);
                    createGlassTrapDoorRecipe(BlockFactory.callBlock("jungle" + "_glass_trapdoor"), Blocks.JUNGLE_TRAPDOOR, Blocks.JUNGLE_PLANKS.asItem(), true);
                    createGlassTrapDoorRecipe(BlockFactory.callBlock("birch" + "_glass_trapdoor"), Blocks.BIRCH_TRAPDOOR, Blocks.BIRCH_PLANKS.asItem(), true);
                    createGlassTrapDoorRecipe(BlockFactory.callBlock("dark_oak" + "_glass_trapdoor"), Blocks.DARK_OAK_TRAPDOOR, Blocks.DARK_OAK_PLANKS.asItem(), true);
                    createGlassTrapDoorRecipe(BlockFactory.callBlock("acacia" + "_glass_trapdoor"), Blocks.ACACIA_TRAPDOOR, Blocks.ACACIA_PLANKS.asItem(), true);
                    createGlassTrapDoorRecipe(BlockFactory.callBlock("mangrove" + "_glass_trapdoor"), Blocks.MANGROVE_TRAPDOOR, Blocks.MANGROVE_PLANKS.asItem(), true);
                    createGlassTrapDoorRecipe(BlockFactory.callBlock("cherry" + "_glass_trapdoor"), Blocks.CHERRY_TRAPDOOR, Blocks.CHERRY_PLANKS.asItem(), true);
                    createGlassTrapDoorRecipe(BlockFactory.callBlock("bamboo" + "_glass_trapdoor"), Blocks.BAMBOO_TRAPDOOR, Blocks.BAMBOO_PLANKS.asItem(), true);
                    createGlassTrapDoorRecipe(BlockFactory.callBlock("warped" + "_glass_trapdoor"), Blocks.WARPED_TRAPDOOR, Blocks.WARPED_PLANKS.asItem(), true);
                    createGlassTrapDoorRecipe(BlockFactory.callBlock("crimson" + "_glass_trapdoor"), Blocks.CRIMSON_TRAPDOOR, Blocks.CRIMSON_PLANKS.asItem(), true);
                    //createGlassTrapDoorRecipe(BlockFactoryUpt.callBlock("pale_oak" + "_glass_trapdoor"), Blocks.PALE_OAK_TRAPDOOR, Blocks.PALE_OAK_PLANKS.asItem()); //FUTURE UPDATE
            
                    createGlassTrapDoorRecipe(BlockFactory.callBlock("iron" + "_glass_trapdoor"), Blocks.IRON_TRAPDOOR, Items.IRON_INGOT, false);
                    createGlassTrapDoorRecipe(BlockFactory.callBlock("copper" + "_glass_trapdoor"), Blocks.COPPER_TRAPDOOR, Items.COPPER_INGOT, false);
                    createGlassTrapDoorRecipe(BlockFactory.callBlock("exposed_copper" + "_glass_trapdoor"), Blocks.EXPOSED_COPPER_TRAPDOOR, Items.COPPER_INGOT, false);
                    createGlassTrapDoorRecipe(BlockFactory.callBlock("oxidized_copper" + "_glass_trapdoor"), Blocks.OXIDIZED_COPPER_TRAPDOOR, Items.COPPER_INGOT, false);
                    createGlassTrapDoorRecipe(BlockFactory.callBlock("weathered_copper" + "_glass_trapdoor"), Blocks.WEATHERED_COPPER_TRAPDOOR, Items.COPPER_INGOT, false);
                    createGlassTrapDoorRecipe(BlockFactory.callBlock("waxed_copper" + "_glass_trapdoor"), Blocks.WAXED_COPPER_TRAPDOOR, Items.COPPER_INGOT, false);
                    createGlassTrapDoorRecipe(BlockFactory.callBlock("waxed_exposed_copper" + "_glass_trapdoor"), Blocks.WAXED_EXPOSED_COPPER_TRAPDOOR, Items.COPPER_INGOT, false);
                    createGlassTrapDoorRecipe(BlockFactory.callBlock("waxed_oxidized_copper" + "_glass_trapdoor"), Blocks.WAXED_OXIDIZED_COPPER_TRAPDOOR, Items.COPPER_INGOT, false);
                    createGlassTrapDoorRecipe(BlockFactory.callBlock("waxed_weathered_copper" + "_glass_trapdoor"), Blocks.WAXED_WEATHERED_COPPER_TRAPDOOR, Items.COPPER_INGOT, false);
                }
                

                //Food
                shaped(RecipeCategory.FOOD, ItemFactory.callItem("baguette"))
                    .define('#', Items.BREAD)
                    .pattern("#")
                    .pattern("#")
                    .pattern("#")
                    .unlockedBy("has_bread", has(Items.BREAD))
                .save(output);

                Item MARSHMELLOW_TOASTED = ItemFactory.callItem("toasted_marshmellow");
                Item MARSHMELLOW_RAW = ItemFactory.callItem("raw_marshmellow"); 

                SimpleCookingRecipeBuilder.smelting(Ingredient.of(MARSHMELLOW_RAW), RecipeCategory.FOOD, MARSHMELLOW_TOASTED, 0.35F, 200)
                    .unlockedBy("has_marshmellow", has(MARSHMELLOW_RAW))
                .save(output);

                simpleCookingRecipe("smoker", RecipeSerializer.SMOKING_RECIPE, SmokingRecipe::new, 100, MARSHMELLOW_RAW, MARSHMELLOW_TOASTED, 0.35F);
                simpleCookingRecipe("campfire_cooking", RecipeSerializer.CAMPFIRE_COOKING_RECIPE, CampfireCookingRecipe::new, 600, MARSHMELLOW_RAW, MARSHMELLOW_TOASTED, 0.35F);

                shapeless(RecipeCategory.FOOD, ItemFactory.MARSHMELLOW_RAW, 1)
                    .requires(Items.STICK,1)
                    .requires(Items.HONEYCOMB,1)
                    .requires(Items.SUGAR, 1)
                    .unlockedBy(getHasName(Items.STICK), has(Items.STICK))
                    .unlockedBy(getHasName(Items.HONEY_BLOCK), has(Items.HONEY_BLOCK))
                    .unlockedBy(getHasName(Items.SUGAR), has(Items.SUGAR))
                .save(output);

                Item ANCIENT_DEBRIS_NUGGET = ItemFactory.callItem("ancient_debris_nugget");

                nineBlockStorageRecipesWithCustomPacking(
                    RecipeCategory.MISC, ANCIENT_DEBRIS_NUGGET, RecipeCategory.MISC, Items.ANCIENT_DEBRIS, "ancient_debris_from_nuggets", "ancient_debris"
                );

                //offerReversibleCompactingRecipesWithCompactingRecipeGroup(
                //	exporter, RecipeCategory.MISC, ANCIENT_DEBRIS_NUGGET, RecipeCategory.MISC, Items.ANCIENT_DEBRIS, "ancient_debris_from_nuggets", "ancient_debris"
                //);

                //Alligator Gar

                simpleCookingRecipe("smoker", RecipeSerializer.SMOKING_RECIPE, SmokingRecipe::new, 100, ItemFactory.ALLIGATOR_GAR, ItemFactory.COOKED_ALLIGATOR_GAR, 0.35F);
                simpleCookingRecipe("campfire_cooking", RecipeSerializer.CAMPFIRE_COOKING_RECIPE, CampfireCookingRecipe::new, 600, ItemFactory.ALLIGATOR_GAR, ItemFactory.COOKED_ALLIGATOR_GAR, 0.35F);

                SimpleCookingRecipeBuilder.smelting(Ingredient.of(ItemFactory.ALLIGATOR_GAR), RecipeCategory.FOOD, ItemFactory.COOKED_ALLIGATOR_GAR, 0.35F, 200)
                    .unlockedBy("has_alligator_gar", has(ItemFactory.ALLIGATOR_GAR))
                .save(output);

                //Mackerel

                simpleCookingRecipe("smoker", RecipeSerializer.SMOKING_RECIPE, SmokingRecipe::new, 100, ItemFactory.MACKEREL, ItemFactory.COOKED_MACKEREL, 0.35F);
                simpleCookingRecipe("campfire_cooking", RecipeSerializer.CAMPFIRE_COOKING_RECIPE, CampfireCookingRecipe::new, 600, ItemFactory.MACKEREL, ItemFactory.COOKED_MACKEREL, 0.35F);

                SimpleCookingRecipeBuilder.smelting(Ingredient.of(ItemFactory.MACKEREL), RecipeCategory.FOOD, ItemFactory.COOKED_MACKEREL, 0.35F, 200)
                    .unlockedBy("has_mackerel", has(ItemFactory.MACKEREL))
                .save(output);
            
                //Bass

                simpleCookingRecipe("smoker", RecipeSerializer.SMOKING_RECIPE, SmokingRecipe::new, 100, ItemFactory.BASS, ItemFactory.COOKED_BASS, 0.35F);
                simpleCookingRecipe("campfire_cooking", RecipeSerializer.CAMPFIRE_COOKING_RECIPE, CampfireCookingRecipe::new, 600, ItemFactory.BASS, ItemFactory.COOKED_BASS, 0.35F);

                SimpleCookingRecipeBuilder.smelting(Ingredient.of(ItemFactory.BASS), RecipeCategory.FOOD, ItemFactory.COOKED_BASS, 0.35F, 200)
                    .unlockedBy("has_bass", has(ItemFactory.BASS))
                .save(output);
            
                //Perch

                simpleCookingRecipe("smoker", RecipeSerializer.SMOKING_RECIPE, SmokingRecipe::new, 100, ItemFactory.PERCH, ItemFactory.COOKED_PERCH, 0.35F);
                simpleCookingRecipe("campfire_cooking", RecipeSerializer.CAMPFIRE_COOKING_RECIPE, CampfireCookingRecipe::new, 600, ItemFactory.PERCH, ItemFactory.COOKED_PERCH, 0.35F);

                SimpleCookingRecipeBuilder.smelting(Ingredient.of(ItemFactory.PERCH), RecipeCategory.FOOD, ItemFactory.COOKED_PERCH, 0.35F, 200)
                    .unlockedBy("has_perch", has(ItemFactory.PERCH))
                .save(output);

            
                //Snapper

                simpleCookingRecipe("smoker", RecipeSerializer.SMOKING_RECIPE, SmokingRecipe::new, 100, ItemFactory.SNAPPER, ItemFactory.COOKED_SNAPPER, 0.35F);
                simpleCookingRecipe("campfire_cooking", RecipeSerializer.CAMPFIRE_COOKING_RECIPE, CampfireCookingRecipe::new, 600, ItemFactory.SNAPPER, ItemFactory.COOKED_SNAPPER, 0.35F);

                SimpleCookingRecipeBuilder.smelting(Ingredient.of(ItemFactory.SNAPPER), RecipeCategory.FOOD, ItemFactory.COOKED_SNAPPER, 0.35F, 200)
                    .unlockedBy("has_snapper", has(ItemFactory.SNAPPER))
                .save(output);
            
                //MahiMahi

                simpleCookingRecipe("smoker", RecipeSerializer.SMOKING_RECIPE, SmokingRecipe::new, 100, ItemFactory.MAHIMAHI, ItemFactory.COOKED_MAHIMAHI, 0.35F);
                simpleCookingRecipe("campfire_cooking", RecipeSerializer.CAMPFIRE_COOKING_RECIPE, CampfireCookingRecipe::new, 600, ItemFactory.MAHIMAHI, ItemFactory.COOKED_MAHIMAHI, 0.35F);

                SimpleCookingRecipeBuilder.smelting(Ingredient.of(ItemFactory.MAHIMAHI), RecipeCategory.FOOD, ItemFactory.COOKED_MAHIMAHI, 0.35F, 200)
                    .unlockedBy("has_mahimahi", has(ItemFactory.MAHIMAHI))
                .save(output);



                
                //Shields

                if (enableShield) {
                    //ComplexRecipeJsonBuilder.create((category) -> new ShieldDecorationRecipeExtra(category, ItemFactory.WOODEN_OAK_SHIELD)).offerTo(exporter, "wooden_oak_shield_decoration");
                    //ComplexRecipeJsonBuilder.create((category) -> new ShieldDecorationRecipeExtra(category, ItemFactory.WOODEN_SPRUCE_SHIELD)).offerTo(exporter, "wooden_spruce_shield_decoration");
                    //ComplexRecipeJsonBuilder.create((category) -> new ShieldDecorationRecipeExtra(category, ItemFactory.WOODEN_BIRCH_SHIELD)).offerTo(exporter, "wooden_birch_shield_decoration");
                    //ComplexRecipeJsonBuilder.create((category) -> new ShieldDecorationRecipeExtra(category, ItemFactory.WOODEN_JUNGLE_SHIELD)).offerTo(exporter, "wooden_jungle_shield_decoration");
                    //ComplexRecipeJsonBuilder.create((category) -> new ShieldDecorationRecipeExtra(category, ItemFactory.WOODEN_BAMBOO_SHIELD)).offerTo(exporter, "wooden_bamboo_shield_decoration");
                    //ComplexRecipeJsonBuilder.create((category) -> new ShieldDecorationRecipeExtra(category, ItemFactory.WOODEN_ACACIA_SHIELD)).offerTo(exporter, "wooden_acacia_shield_decoration");
                    //ComplexRecipeJsonBuilder.create((category) -> new ShieldDecorationRecipeExtra(category, ItemFactory.WOODEN_DARK_OAK_SHIELD)).offerTo(exporter, "wooden_dark_oak_shield_decoration");
                    //ComplexRecipeJsonBuilder.create((category) -> new ShieldDecorationRecipeExtra(category, ItemFactory.WOODEN_MANGROVE_SHIELD)).offerTo(exporter, "wooden_mangrove_shield_decoration");
                    //ComplexRecipeJsonBuilder.create((category) -> new ShieldDecorationRecipeExtra(category, ItemFactory.WOODEN_CHERRY_SHIELD)).offerTo(exporter, "wooden_cherry_shield_decoration");

                    //ComplexRecipeJsonBuilder.create((category) -> new ShieldDecorationRecipeExtra(category, ItemFactory.STONE_SHIELD)).offerTo(exporter, "stone_shield_decoration");
                    //ComplexRecipeJsonBuilder.create((category) -> new ShieldDecorationRecipeExtra(category, ItemFactory.STONE_BLACKSTONE_SHIELD)).offerTo(exporter, "stone_blackstone_shield_decoration");
                    //ComplexRecipeJsonBuilder.create((category) -> new ShieldDecorationRecipeExtra(category, ItemFactory.STONE_DEEPSLATE_SHIELD)).offerTo(exporter, "stone_deepslate_shield_decoration");
                    //ComplexRecipeJsonBuilder.create((category) -> new ShieldDecorationRecipeExtra(category, ItemFactory.GOLDEN_SHIELD)).offerTo(exporter, "golden_shield_decoration");
                    //ComplexRecipeJsonBuilder.create((category) -> new ShieldDecorationRecipeExtra(category, ItemFactory.DIAMOND_SHIELD)).offerTo(exporter, "diamond_shield_decoration");

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

                    netheriteSmithing(ItemFactory.callItem("diamond_shield"), RecipeCategory.COMBAT, ItemFactory.callItem("stone_shield"));

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

                offerNetheriteUpgradeRecipe(ItemFactory.callItem("diamond_long_sword"), RecipeCategory.COMBAT, ItemFactory.callItem("netherite_long_sword"));
                offerNetheriteUpgradeRecipe(ItemFactory.callItem("diamond_dagger"), RecipeCategory.COMBAT, ItemFactory.callItem("netherite_dagger"));
                offerNetheriteUpgradeRecipe(ItemFactory.callItem("diamond_hammer"), RecipeCategory.COMBAT, ItemFactory.callItem("netherite_hammer"));

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
                createShapeless(RecipeCategory.BUILDING_BLOCKS, FUNGAL_PLANK, 4)
                    .input(Ingredient.ofItems(Blocks.MUSHROOM_STEM, Blocks.RED_MUSHROOM_BLOCK, Blocks.BROWN_MUSHROOM_BLOCK))
                    .group("planks")
                    .criterion(hasItem(Blocks.MUSHROOM_STEM), conditionsFromItem(Blocks.MUSHROOM_STEM))
                    .criterion(hasItem(Blocks.RED_MUSHROOM_BLOCK), conditionsFromItem(Blocks.RED_MUSHROOM_BLOCK))
                    .criterion(hasItem(Blocks.BROWN_MUSHROOM_BLOCK), conditionsFromItem(Blocks.BROWN_MUSHROOM_BLOCK))
                .offerTo(exporter);

                Item REDWOOD_PLANK = BlockFactoryUpt.callBlock("redwood_planks").asItem();
                Block REDWOOD_LOG = BlockFactoryUpt.callBlock("redwood_log");
                createShapeless(RecipeCategory.BUILDING_BLOCKS, REDWOOD_PLANK, 4)
                    .input(REDWOOD_LOG.asItem())
                    .group("planks")
                    .criterion(hasItem(REDWOOD_LOG), conditionsFromItem(REDWOOD_LOG))
                .offerTo(exporter);

                Item FIR_PLANK = BlockFactoryUpt.callBlock("fir_planks").asItem();
                Block FIR_LOG = BlockFactoryUpt.callBlock("fir_log");
                createShapeless(RecipeCategory.BUILDING_BLOCKS, FIR_PLANK, 4)
                    .input(FIR_LOG.asItem())
                    .group("planks")
                    .criterion(hasItem(FIR_LOG), conditionsFromItem(FIR_LOG))
                .offerTo(exporter);
                */

                /* 
                generateWoodFamily("redwood", null);
                generateWoodFamily("fir", null);

                createStoneVarient();

                //Block IRON_GLASSDOOR = ModBlocks.MOD_DOOR("iron" + "_glass", null);

                createShapeless(RecipeCategory.BUILDING_BLOCKS, IRON_GLASSDOOR, 1)
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
                //generateWoodFamily("fir", null);

                //createPlanks(BlockFactoryUpt.callBlock("redwood_planks"), Ingredient.ofItems(BlockFactoryUpt.callBlock("redwood_log")));
                //createPlanks(BlockFactoryUpt.callBlock("fir_planks"), Ingredient.ofItems(BlockFactoryUpt.callBlock("fir_log")));
                
            }
        
            //==============
            //   RECIPES
            //==============

            public void createGlassDoorRecipe(Block GLASSDOOR, Block DOOR, Item UNLOCK_Item, Boolean wooden) {
                if (wooden) {
                    shapeless(RecipeCategory.REDSTONE, GLASSDOOR, 1)
                    .requires(DOOR,1)
                    .requires(Blocks.GLASS_PANE, 1)
                    .group("wooden_glass_door")
                    .unlockedBy(getHasName(DOOR.asItem()), has(DOOR.asItem()))
                    .save(output);
                }
                else {
                    shapeless(RecipeCategory.REDSTONE, GLASSDOOR, 1)
                    .requires(DOOR,1)
                    .requires(Blocks.GLASS_PANE, 1)
                    .group("glass_door")
                    .unlockedBy(getHasName(DOOR.asItem()), has(DOOR.asItem()))
                    .save(output);  
                }

            }

            public void createGlassTrapDoorRecipe(Block GLASSTRAPDOOR, Block TRAPDOOR, Item UNLOCK_Item, Boolean wooden) {
                if (wooden) {
                    shapeless(RecipeCategory.REDSTONE, GLASSTRAPDOOR, 1)
                    .requires(TRAPDOOR,1)
                    .requires(Blocks.GLASS_PANE, 1)
                    .group("wooden_glass_trapdoor")
                    .unlockedBy(getHasName(TRAPDOOR.asItem()), has(TRAPDOOR.asItem()))
                    .save(output); 
                }
                else {
                    shapeless(RecipeCategory.REDSTONE, GLASSTRAPDOOR, 1)
                    .requires(TRAPDOOR,1)
                    .requires(Blocks.GLASS_PANE, 1)
                    .group("glass_trapdoor")
                    .unlockedBy(getHasName(TRAPDOOR.asItem()), has(TRAPDOOR.asItem()))
                    .save(output); 
                }


            }

            public void createShield(TagKey<Item> UNLOCK_Item, ItemLike result) {
                shaped(RecipeCategory.COMBAT, result)
                .define('X', ItemTags.PLANKS)
                .define('#', UNLOCK_Item)
                .pattern("X#X")
                .pattern("XXX")
                .pattern(" X ")
                //.group("long_sword")
                .unlockedBy("has_log", has(ItemTags.PLANKS))
                .save(output);
            }

            public void createShield(Item material, ItemLike result) {
                shaped(RecipeCategory.COMBAT, result)
                .define('X', ItemTags.PLANKS)
                .define('#', material)
                .pattern("X#X")
                .pattern("XXX")
                .pattern(" X ")
                //.group("long_sword")
                .unlockedBy(getHasName(material), has(ItemTags.PLANKS))
                .save(output);
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
            public void createClaymore(Item UNLOCK, ItemLike result, TagKey<Item> ITEMTAGS){
                shaped(RecipeCategory.COMBAT, result)
                .define('#', ITEMTAGS)
                .define('S', Items.STICK)
                .pattern(" # ")
                .pattern("###")
                .pattern("#S#")
                //.group("long_sword")
                .unlockedBy(getHasName(UNLOCK), has(ITEMTAGS))
                .save(output);
            }

            public void createClaymore(Item MATERIAL, ItemLike result){
                shaped(RecipeCategory.COMBAT, result)
                .define('#', MATERIAL)
                .define('S', Items.STICK)
                .pattern(" # ")
                .pattern("###")
                .pattern("#S#")
                //.group("long_sword")
                .unlockedBy(getHasName(MATERIAL), has(MATERIAL))
                .save(output);
            }

            public void createHammerUpdated(Item UNLOCK, ItemLike result, TagKey<Item> ITEMTAGS){
                shaped(RecipeCategory.COMBAT, result)
                .define('#', ITEMTAGS)
                .define('S', Items.STICK)
                .pattern("###")
                .pattern("###")
                .pattern(" S ")
                //.group("long_sword")
                .unlockedBy(getHasName(UNLOCK), has(ITEMTAGS))
                .save(output);
            }

            public void createHammerUpdated(Item MATERIAL, ItemLike result){
                shaped(RecipeCategory.COMBAT, result)
                .define('#', MATERIAL)
                .define('S', Items.STICK)
                .pattern("###")
                .pattern("###")
                .pattern(" S ")
                //.group("long_sword")
                .unlockedBy(getHasName(MATERIAL), has(MATERIAL))
                .save(output);
            }

            public void createLongSwordUpdated(Item UNLOCK, ItemLike result, TagKey<Item> ITEMTAGS){
                shaped(RecipeCategory.COMBAT, result)
                .define('#', ITEMTAGS)
                .define('S', Items.STICK)
                .pattern(" # ")
                .pattern("###")
                .pattern(" S ")
                //.group("long_sword")
                .unlockedBy(getHasName(UNLOCK), has(ITEMTAGS))
                .save(output);
            }

            public void createLongSwordUpdated(Item MATERIAL, ItemLike result){
                shaped(RecipeCategory.COMBAT, result)
                .define('#', MATERIAL)
                .define('S', Items.STICK)
                .pattern(" # ")
                .pattern("###")
                .pattern(" S ")
                //.group("long_sword")
                .unlockedBy(getHasName(MATERIAL), has(MATERIAL))
                .save(output);
            }

            public void createGlaive(Item UNLOCK, ItemLike result, TagKey<Item> ITEMTAGS){
                shaped(RecipeCategory.COMBAT, result)
                .define('#', ITEMTAGS)
                .define('S', Items.STICK)
                .pattern("  #")
                .pattern(" S ")
                .pattern("S  ")
                //.group("long_sword")
                .unlockedBy(getHasName(UNLOCK), has(ITEMTAGS))
                .save(output);
            }

            public void createGlaive(Item MATERIAL, ItemLike result){
                shaped(RecipeCategory.COMBAT, result)
                .define('#', MATERIAL)
                .define('S', Items.STICK)
                .pattern("  #")
                .pattern(" S ")
                .pattern("S  ")
                //.group("long_sword")
                .unlockedBy(getHasName(MATERIAL), has(MATERIAL))
                .save(output);
            }

            public  void createBattleAxeUpdated(Item UNLOCK, ItemLike result, TagKey<Item> ITEMTAGS){
                shaped(RecipeCategory.COMBAT, result)
                .define('#', ITEMTAGS)
                .define('S', Items.STICK)
                .pattern("###")
                .pattern("#S#")
                .pattern(" S ")
                //.group("long_sword")
                .unlockedBy(getHasName(UNLOCK), has(ITEMTAGS))
                .save(output);
            }

            public void createBattleAxeUpdated(Item MATERIAL, ItemLike result){
                shaped(RecipeCategory.COMBAT, result)
                .define('#', MATERIAL)
                .define('S', Items.STICK)
                .pattern("###")
                .pattern("#S#")
                .pattern(" S ")
                //.group("long_sword")
                .unlockedBy(getHasName(MATERIAL), has(MATERIAL))
                .save(output);
            }

            /*Legacy Weapons Recipes */

            public void createLongSword(Item UNLOCK, ItemLike result, TagKey<Item> ITEMTAGS){
                shaped(RecipeCategory.COMBAT, result)
                .define('#', ITEMTAGS)
                .define('X', Items.STICK)
                .pattern(" # ")
                .pattern("###")
                .pattern("#X#")
                //.group("long_sword")
                .unlockedBy(getHasName(UNLOCK), has(ITEMTAGS))
                .save(output);

            }

            public void createLongSword(Item MATERIAL, ItemLike result){
                shaped(RecipeCategory.COMBAT, result)
                .define('#', MATERIAL)
                .define('X', Items.STICK)
                .pattern(" # ")
                .pattern("###")
                .pattern("#X#")
                //.group("long_sword")
                .unlockedBy(getHasName(MATERIAL), has(MATERIAL))
                .save(output);

                
            }

            public void createBattleAxe(Item UNLOCK, ItemLike result, TagKey<Item> ITEMTAGS){
                shaped(RecipeCategory.COMBAT, result)
                .define('#', ITEMTAGS)
                .define('X', Items.STICK)
                .pattern("###")
                .pattern("#X#")
                .pattern(" X ")
                //.group("battle_axe")
                .unlockedBy(getHasName(UNLOCK), has(ITEMTAGS))
                .save(output);
            }

            public void createBattleAxe(Item MATERIAL, ItemLike result){
                shaped(RecipeCategory.COMBAT, result)
                .define('#', MATERIAL)
                .define('X', Items.STICK)
                .pattern("###")
                .pattern("#X#")
                .pattern(" X ")
                //.group("battle_axe")
                .unlockedBy(getHasName(MATERIAL), has(MATERIAL))
                .save(output);

                
            }

            public void createKatana(Item UNLOCK, ItemLike result, TagKey<Item> ITEMTAGS){
                shaped(RecipeCategory.COMBAT, result)
                .define('#', ITEMTAGS)
                .define('X', Items.STICK)
                .pattern("  #")
                .pattern(" # ")
                .pattern("X  ")
                //.group("katana")
                .unlockedBy(getHasName(UNLOCK), has(ITEMTAGS))
                .save(output);
            }

            public void createKatana(Item MATERIAL, ItemLike result){
                shaped(RecipeCategory.COMBAT, result)
                .define('#', MATERIAL)
                .define('X', Items.STICK)
                .pattern("  #")
                .pattern(" # ")
                .pattern("X  ")
                //.group("katana")
                .unlockedBy(getHasName(MATERIAL), has(MATERIAL))
                .save(output);

                
            }

            public void createDagger(Item UNLOCK, ItemLike result, TagKey<Item> ITEMTAGS){
                shaped(RecipeCategory.COMBAT, result)
                .define('#', ITEMTAGS)
                .define('X', Items.STICK)
                .pattern("  #")
                .pattern(" X ")
                .pattern("   ")
                //.group("dagger")
                .unlockedBy(getHasName(UNLOCK), has(ITEMTAGS))
                .save(output);
            }

            public void createDagger(Item MATERIAL, ItemLike result){
                shaped(RecipeCategory.COMBAT, result)
                .define('#', MATERIAL)
                .define('X', Items.STICK)
                .pattern("  #")
                .pattern(" X ")
                .pattern("   ")
                //.group("dagger")
                .unlockedBy(getHasName(MATERIAL), has(MATERIAL))
                .save(output);

                
            }

            public void createHammer(Item UNLOCK, ItemLike result, TagKey<Item> ITEMTAGS){
                shaped(RecipeCategory.COMBAT, result)
                .define('#', ITEMTAGS)
                .define('X', Items.STICK)
                .pattern("###")
                .pattern("###")
                .pattern(" X ")
                //.group("hammer")
                .unlockedBy(getHasName(UNLOCK), has(ITEMTAGS))
                .save(output);
            }

            public void createHammer(Item MATERIAL, ItemLike result){
                shaped(RecipeCategory.COMBAT, result)
                .define('#', MATERIAL)
                .define('X', Items.STICK)
                .pattern("###")
                .pattern("###")
                .pattern(" X ")
                //.group("hammer")
                .unlockedBy(getHasName(MATERIAL), has(MATERIAL))
                .save(output);

                
            }

            public void smeltToIron(){

                Item Iron1 = ItemFactory.callItem("iron_long_sword");
                Item Iron2 = ItemFactory.callItem("iron_long_sword");
                Item Iron3 = ItemFactory.callItem("iron_long_sword");
                
                SimpleCookingRecipeBuilder.smelting(
                    Ingredient.of(Iron1, Iron2, Iron3),
                    RecipeCategory.MISC,
                    Items.IRON_NUGGET,
                    0.1F,
                    200
                )
                .unlockedBy(getHasName(Iron1), has(Iron1))
                .unlockedBy(getHasName(Iron2), has(Iron2))
                .unlockedBy(getHasName(Iron3), has(Iron3))
                .save(output, getSmeltingRecipeName(Items.IRON_NUGGET));

            }

            public void smeltToGold(){

                Item Gold1 = ItemFactory.callItem("golden_long_sword");
                Item Gold2 = ItemFactory.callItem("golden_long_sword");
                Item Gold3 = ItemFactory.callItem("golden_long_sword");
                
                SimpleCookingRecipeBuilder.smelting(
                    Ingredient.of(Gold1, Gold2, Gold3),
                    RecipeCategory.MISC,
                    Items.GOLD_NUGGET,
                    0.1F,
                    200
                )
                .unlockedBy(getHasName(Gold1), has(Gold1))
                .unlockedBy(getHasName(Gold2), has(Gold2))
                .unlockedBy(getHasName(Gold3), has(Gold3))
                .save(output, getSmeltingRecipeName(Items.GOLD_NUGGET));

            }


            public void generateMosaic(String FamilyBase, Block Blocks, Block BlockSlab) {

                mosaicBuilder(RecipeCategory.DECORATIONS,  BlockFactory.callBlock(FamilyBase + "_mosaic"), BlockSlab);
                
                stairBuilder(BlockFactory.callBlock(FamilyBase + "_mosaic_stairs").asItem(), Ingredient.of(Blocks))
                    .group("wooden_stairs")
                    .unlockedBy(getHasName(BlockFactory.callBlock(FamilyBase + "_mosaic")), has(BlockFactory.callBlock(FamilyBase + "_mosaic")))
                    .save(output);

                slabBuilder(RecipeCategory.BUILDING_BLOCKS, BlockFactory.callBlock(FamilyBase + "_mosaic_slab").asItem(), Ingredient.of(Blocks))
                    .group("wooden_slab")
                    .unlockedBy(getHasName(BlockFactory.callBlock(FamilyBase + "_mosaic")), has(BlockFactory.callBlock(FamilyBase + "_mosaic")))
                    .save(output);

            }



            // Updated so us all of Us
            public void generateWoodFamily(String FamilyBase, String varient){

                if (varient == null) {varient = "";}
                else {varient = "_" + varient;}

                Item FamilyHead = BlockFactory.callBlock(FamilyBase + "_planks" + varient).asItem();
                Ingredient FamilyI = Ingredient.of(FamilyHead);

                trapdoorBuilder(BlockFactory.callBlock(FamilyBase + "_trapdoor" + varient).asItem(), FamilyI)
                    .group("wooden_trapdoor")
                    .unlockedBy(getHasName(FamilyHead), has(FamilyHead))
                    .save(output);

                fenceBuilder(BlockFactory.callBlock(FamilyBase + "_fence" + varient).asItem(), FamilyI)
                    .group("wooden_fence")
                    .unlockedBy(getHasName(FamilyHead), has(FamilyHead))
                    .save(output);
            
                fenceGateBuilder(BlockFactory.callBlock(FamilyBase + "_fence_gate" + varient).asItem(), FamilyI)
                    .group("wooden_fence_gate")
                    .unlockedBy(getHasName(FamilyHead), has(FamilyHead))
                    .save(output);
            
                pressurePlateBuilder(RecipeCategory.BUILDING_BLOCKS, BlockFactory.callBlock(FamilyBase + "_pressure_plate" + varient).asItem(), FamilyI)
                    .group("wooden_pressure_plate")
                    .unlockedBy(getHasName(FamilyHead), has(FamilyHead))
                    .save(output);
            
                slabBuilder(RecipeCategory.BUILDING_BLOCKS, BlockFactory.callBlock(FamilyBase + "_slab" + varient).asItem(), FamilyI)
                    .group("wooden_slab")
                    .unlockedBy(getHasName(FamilyHead), has(FamilyHead))
                    .save(output);
            
                stairBuilder(BlockFactory.callBlock(FamilyBase + "_stairs" + varient).asItem(), FamilyI)
                    .group("wooden_stairs")
                    .unlockedBy(getHasName(FamilyHead), has(FamilyHead))
                    .save(output);
            
                buttonBuilder(BlockFactory.callBlock(FamilyBase + "_button" + varient).asItem(), FamilyI)
                    .group("wooden_button")
                    .unlockedBy(getHasName(FamilyHead), has(FamilyHead))
                    .save(output);
            
                mosaicBuilder(RecipeCategory.DECORATIONS,  BlockFactory.callBlock(FamilyBase + "_mosaic" + varient), BlockFactory.callBlock(FamilyBase + "_slab" + varient));
                
                stairBuilder(BlockFactory.callBlock(FamilyBase + "_mosaic_stairs" + varient).asItem(), FamilyI)
                    .group("wooden_stairs")
                    .unlockedBy(getHasName(BlockFactory.callBlock(FamilyBase + "_mosaic" + varient)), has(BlockFactory.callBlock(FamilyBase + "_mosaic" + varient)))
                    .save(output);

                slabBuilder(RecipeCategory.BUILDING_BLOCKS, BlockFactory.callBlock(FamilyBase + "_mosaic_slab" + varient).asItem(), FamilyI)
                    .group("wooden_slab")
                    .unlockedBy(getHasName(BlockFactory.callBlock(FamilyBase + "_mosaic" + varient)), has(BlockFactory.callBlock(FamilyBase + "_mosaic" + varient)))
                    .save(output);

                //createGlassDoorRecipe(FamilyBase, varient, "wooden");

                Item GLASS_DOOR = BlockFactory.callBlock(FamilyBase + "_glass_door" + varient).asItem();
                Item DOOR = BlockFactory.callBlock(FamilyBase + "_door" + varient).asItem();

                Item GLASS_TRAPDOOR = BlockFactory.callBlock(FamilyBase + "_glass_trapdoor" + varient).asItem();
                Item TRAPDOOR = BlockFactory.callBlock(FamilyBase + "_trapdoor" + varient).asItem();

                shapeless(RecipeCategory.REDSTONE, GLASS_DOOR, 1)
                    .requires(DOOR,1)
                    .requires(Blocks.GLASS_PANE, 1)
                    .group("wooden_glass_door")
                    .unlockedBy(getHasName(DOOR), has(DOOR))
                    .save(output);

                shapeless(RecipeCategory.REDSTONE, GLASS_TRAPDOOR, 1)
                    .requires(TRAPDOOR,1)
                    .requires(Blocks.GLASS_PANE, 1)
                    .group("wooden_glass_trapdoor")
                    .unlockedBy(getHasName(TRAPDOOR), has(TRAPDOOR))
                    .save(output);


                doorBuilder(BlockFactory.callBlock(FamilyBase + "_door" + varient).asItem(), FamilyI)
                    .group("wooden_door")
                    .unlockedBy(getHasName(FamilyHead), has(FamilyHead))
                    .save(output);
            }

            public RecipeBuilder createGlassDoorRecipeTODO(ItemLike output, Ingredient input) {
                return shaped(RecipeCategory.REDSTONE, output, 3).define('#', input)
                .pattern("##")
                .pattern("##")
                .pattern("##");
            }

            public void generateColorPlank(String color){

                Item result = BlockFactory.callBlock("fungal_planks_" + color).asItem();
                Item MATERIAL = BlockFactory.callBlock("fungal_planks").asItem();
                Item DYE = BlockFactory.getDye(color).asItem();

                shaped(RecipeCategory.BUILDING_BLOCKS, result, 8)
                .define('#', MATERIAL)
                .define('D', DYE)
                .pattern("###")
                .pattern("#D#")
                .pattern("###")
                .group("colored_planks")
                .unlockedBy(getHasName(MATERIAL), has(MATERIAL))
                .unlockedBy(getHasName(DYE), has(DYE))
                .save(output);

                //Dyable Plank System

                ArrayList<Item> cplankGroup = new ArrayList<Item>();
                for (String Icolor : BlockFactory.COLORS){cplankGroup.add(ModBlocks.COLOR_PLANK(Icolor, "planks").asItem());}
            
                ArrayList<Item> plankFinal = new ArrayList<Item>();
                
                for (Item cplank : cplankGroup) {
                    if (cplank != result) {
                        plankFinal.add(cplank);
                    }
                }

                Ingredient cplankI = Ingredient.of(
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


                shapeless(RecipeCategory.BUILDING_BLOCKS, result, 1)
                    .requires(cplankI,1)
                    .requires(DYE, 1)
                    .group("colored_planks")
                    .unlockedBy(getHasName(MATERIAL), has(MATERIAL))
                    .save(output, "dye" + getItemName(result));   


            }

            public void generateWoolFamily(String FamilyBase, String varient){

                Item wool = BlockFactory.getWoolColor(varient).asItem();

                shaped(RecipeCategory.BUILDING_BLOCKS, BlockFactory.callBlock(FamilyBase + "_" + varient))
                    .define('#', wool)
                    .pattern(" ##")
                    .pattern(" ##")
                    .pattern("   ")
                    .group("textured_wool")
                    .unlockedBy(getHasName(wool), has(wool))
                    .save(output);
                //Add Carpets
            }

            public void createGlassExtras(Block ModdedFam, Block PLANK, Block DOOR, Block TRAPDOOR, String GROUP) {
                Item GLASS_DOOR = BlockFactory.callBlock(ModdedFam + "_glass_door").asItem();
                Item GLASS_TRAPDOOR = BlockFactory.callBlock(ModdedFam + "_glass_trapdoor").asItem();

                shapeless(RecipeCategory.REDSTONE, GLASS_DOOR, 1)
                    .requires(DOOR,1)
                    .requires(Blocks.GLASS_PANE, 1)
                    .group(GROUP)
                    .unlockedBy(getHasName(PLANK), has(PLANK))
                    .save(output, getItemName(GLASS_DOOR));

                shapeless(RecipeCategory.REDSTONE, GLASS_TRAPDOOR, 1)
                    .requires(TRAPDOOR,1)
                    .requires(Blocks.GLASS_PANE, 1)
                    .group(GROUP)
                    .unlockedBy(getHasName(PLANK), has(PLANK))
                    .save(output, getItemName(GLASS_TRAPDOOR));
            }





            public void createVanillaGlassDoor(String FamilyBase) {
                Block PLANK = ModBlocks.VANILLA_DOOR(FamilyBase);
                Block DOOR = ModBlocks.MOD_DOOR(FamilyBase + "_glass", null);

                shapeless(RecipeCategory.BUILDING_BLOCKS, DOOR, 1)
                    .requires(PLANK,1)
                    .requires(Blocks.GLASS_PANE, 1)
                    .group("colored_planks")
                    .unlockedBy(getHasName(PLANK), has(PLANK))
                    .save(output, getItemName(DOOR)); 
            }

            public void createVanillaGlassDoorC(String FamilyBase) {
                Block PLANK = ModBlocks.COPPER_DOOR(FamilyBase);
                Block DOOR = ModBlocks.MOD_DOOR(FamilyBase + "_glass", null);

                shapeless(RecipeCategory.BUILDING_BLOCKS, DOOR, 1)
                    .requires(PLANK,1)
                    .requires(Blocks.GLASS_PANE, 1)
                    .group("cooper_doors")
                    .unlockedBy(getHasName(PLANK), has(PLANK))
                    .save(output, getItemName(DOOR)); 
            }

            public void createVanillaGlassDoorI() {
                Block PLANK = Blocks.IRON_DOOR;
                Block DOOR = ModBlocks.MOD_DOOR("iron" + "_glass", null);

                shapeless(RecipeCategory.BUILDING_BLOCKS, DOOR, 1)
                    .requires(PLANK,1)
                    .requires(Blocks.GLASS_PANE, 1)
                    .group("colored_planks")
                    .unlockedBy(getHasName(PLANK), has(PLANK))
                    .save(output, getItemName(DOOR)); 
            }

            public void createPlanks(Block Planks, Ingredient Log){
                shapeless(RecipeCategory.BUILDING_BLOCKS, Planks, 4)
                    .requires(Log)
                    .group("planks")
                    .unlockedBy(getHasName(Blocks.MUSHROOM_STEM), has(Blocks.MUSHROOM_STEM))
                    .unlockedBy(getHasName(Blocks.RED_MUSHROOM_BLOCK), has(Blocks.RED_MUSHROOM_BLOCK))
                    .unlockedBy(getHasName(Blocks.BROWN_MUSHROOM_BLOCK), has(Blocks.BROWN_MUSHROOM_BLOCK))
                    .save(output);
            }

            public void createLimestoneVarients() {

                Block LIMESTONE = BlockFactory.callBlock("limestone");
                Block POLISHED_LIMESTONE = BlockFactory.callBlock("polished_limestone");
                Block POLISHED_LIMESTONE_BRICKS = BlockFactory.callBlock("polished_limestone_bricks");


                //Limestone (Stonecutter)

                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, BlockFactory.callBlock("limestone_" + "slab").asItem(), LIMESTONE.asItem(), 2);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, BlockFactory.callBlock("limestone_" + "stairs").asItem(), LIMESTONE.asItem());
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, BlockFactory.callBlock("limestone_" + "wall").asItem(), LIMESTONE.asItem());

                //Limestone (Crafting Table)

                slabBuilder(RecipeCategory.BUILDING_BLOCKS, BlockFactory.callBlock("limestone_" + "slab").asItem(), 
                    Ingredient.of(LIMESTONE.asItem())).unlockedBy(getHasName(LIMESTONE.asItem()), has(LIMESTONE.asItem()))
                .save(output);

                stairBuilder(BlockFactory.callBlock("limestone_" + "stairs").asItem(), 
                    Ingredient.of(LIMESTONE.asItem())).unlockedBy(getHasName(LIMESTONE.asItem()), has(LIMESTONE.asItem()))
                .save(output);

                wall(RecipeCategory.DECORATIONS, BlockFactory.callBlock("limestone_" + "wall").asItem(), LIMESTONE.asItem());

                //Polished Limestone (Stonecutter)

                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, BlockFactory.callBlock("polished_limestone").asItem(), LIMESTONE);

                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, BlockFactory.callBlock("polished_limestone_" + "slab").asItem(), LIMESTONE.asItem(), 2);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, BlockFactory.callBlock("polished_limestone_" + "slab").asItem(), POLISHED_LIMESTONE.asItem(), 2);;

                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, BlockFactory.callBlock("polished_limestone_" + "stairs").asItem(), LIMESTONE.asItem());
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, BlockFactory.callBlock("polished_limestone_" + "stairs").asItem(), POLISHED_LIMESTONE.asItem());

                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, BlockFactory.callBlock("polished_limestone_" + "wall").asItem(), LIMESTONE.asItem());
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, BlockFactory.callBlock("polished_limestone_" + "wall").asItem(), POLISHED_LIMESTONE.asItem());

                //Polished Limestone (Crafting Table)

                slabBuilder(RecipeCategory.BUILDING_BLOCKS, BlockFactory.callBlock("polished_limestone_" + "slab").asItem(), 
                    Ingredient.of(LIMESTONE.asItem())).unlockedBy(getHasName(LIMESTONE.asItem()), has(LIMESTONE.asItem()))
                .save(output);

                stairBuilder(BlockFactory.callBlock("polished_limestone_" + "stairs").asItem(), 
                    Ingredient.of(LIMESTONE.asItem())).unlockedBy(getHasName(LIMESTONE.asItem()), has(LIMESTONE.asItem()))
                .save(output);

                wall(RecipeCategory.DECORATIONS, BlockFactory.callBlock("polished_limestone_" + "wall").asItem(), LIMESTONE.asItem());
                

                // Limestone Bricks

                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, POLISHED_LIMESTONE_BRICKS.asItem(), POLISHED_LIMESTONE.asItem());
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, POLISHED_LIMESTONE_BRICKS.asItem(), LIMESTONE.asItem());

                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, BlockFactory.callBlock("polished_limestone_brick_" + "slab").asItem(), POLISHED_LIMESTONE.asItem(), 2);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, BlockFactory.callBlock("polished_limestone_brick_" + "slab").asItem(), POLISHED_LIMESTONE_BRICKS.asItem(), 2);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, BlockFactory.callBlock("polished_limestone_brick_" + "slab").asItem(), LIMESTONE.asItem(), 2);

                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, BlockFactory.callBlock("polished_limestone_brick_" + "stairs").asItem(), POLISHED_LIMESTONE.asItem());
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, BlockFactory.callBlock("polished_limestone_brick_" + "stairs").asItem(), POLISHED_LIMESTONE_BRICKS.asItem());
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, BlockFactory.callBlock("polished_limestone_brick_" + "stairs").asItem(), LIMESTONE.asItem());

                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, BlockFactory.callBlock("polished_limestone_brick_" + "wall").asItem(), POLISHED_LIMESTONE.asItem());
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, BlockFactory.callBlock("polished_limestone_brick_" + "wall").asItem(), POLISHED_LIMESTONE_BRICKS.asItem());
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, BlockFactory.callBlock("polished_limestone_brick_" + "wall").asItem(), LIMESTONE.asItem());

                /*CookingRecipeJsonBuilder.createSmelting(
                    Ingredient.ofItems(POLISHED_LIMESTONE_BRICKS.asItem()), RecipeCategory.BUILDING_BLOCKS, BlockFactoryUpt.callBlock("cracked_polished_limestone_brick").asItem(), 0.1F, 200
                )
                .criterion(hasItem(POLISHED_LIMESTONE_BRICKS.asItem().asItem()), conditionsFromItem(POLISHED_LIMESTONE_BRICKS.asItem().asItem()))
                .offerTo(exporter, "smelted_" + "cracked_polished_limestone_brick");*/

                chiseledBuilder(RecipeCategory.BUILDING_BLOCKS, BlockFactory.callBlock("polished_limestone_brick_chiseled"), Ingredient.of(BlockFactory.callBlock("polished_limestone_brick_slab")))
                .unlockedBy(getHasName(POLISHED_LIMESTONE_BRICKS.asItem().asItem()), has(POLISHED_LIMESTONE_BRICKS.asItem()))
                .save(output);


            }

            public void createStoneVarient() {

                Block ANDERSITE_BRICKS = BlockFactory.callBlock("andesite_bricks");
                Block DIORITE_BRICKS = BlockFactory.callBlock("diorite_bricks");
                Block GRANITE_BRICKS = BlockFactory.callBlock("granite_bricks");

                //Andesite
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, ANDERSITE_BRICKS.asItem(), Blocks.ANDESITE);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, ANDERSITE_BRICKS.asItem(), Blocks.POLISHED_ANDESITE);

                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, BlockFactory.callBlock("andesite_brick_slab").asItem(), ANDERSITE_BRICKS.asItem(), 2);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, BlockFactory.callBlock("andesite_brick_slab").asItem(), Blocks.ANDESITE, 2);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, BlockFactory.callBlock("andesite_brick_slab").asItem(), Blocks.POLISHED_ANDESITE, 2);

                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, BlockFactory.callBlock("andesite_brick_stairs").asItem(), ANDERSITE_BRICKS.asItem());
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, BlockFactory.callBlock("andesite_brick_stairs").asItem(), Blocks.ANDESITE);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, BlockFactory.callBlock("andesite_brick_stairs").asItem(), Blocks.POLISHED_ANDESITE);
                
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, BlockFactory.callBlock("andesite_brick_wall").asItem(), ANDERSITE_BRICKS.asItem());
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, BlockFactory.callBlock("andesite_brick_wall").asItem(), Blocks.ANDESITE);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, BlockFactory.callBlock("andesite_brick_wall").asItem(), Blocks.POLISHED_ANDESITE);

                stonecutterResultFromBase(RecipeCategory.DECORATIONS, BlockFactory.callBlock("andesite_brick_chiseled").asItem(), ANDERSITE_BRICKS.asItem());
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, BlockFactory.callBlock("andesite_brick_chiseled").asItem(), Blocks.ANDESITE);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, BlockFactory.callBlock("andesite_brick_chiseled").asItem(), Blocks.POLISHED_ANDESITE);

                slabBuilder(RecipeCategory.BUILDING_BLOCKS, BlockFactory.callBlock("andesite_brick_slab").asItem(), Ingredient.of(ANDERSITE_BRICKS.asItem())).unlockedBy(getHasName(ANDERSITE_BRICKS.asItem()), has(ANDERSITE_BRICKS.asItem()))
                .save(output);
                stairBuilder(BlockFactory.callBlock("andesite_brick_stairs").asItem(), Ingredient.of(ANDERSITE_BRICKS.asItem())).unlockedBy(getHasName(ANDERSITE_BRICKS.asItem()), has(ANDERSITE_BRICKS.asItem()))
                .save(output);
                wall(RecipeCategory.DECORATIONS, BlockFactory.callBlock("andesite_brick_wall").asItem(), ANDERSITE_BRICKS.asItem());

                SimpleCookingRecipeBuilder.smelting(
                    Ingredient.of(ANDERSITE_BRICKS), RecipeCategory.BUILDING_BLOCKS, BlockFactory.callBlock("cracked_andesite_bricks").asItem(), 0.1F, 200
                )
                .unlockedBy(getHasName(ANDERSITE_BRICKS.asItem()), has(ANDERSITE_BRICKS.asItem()))
                .save(output, "smelted_" + "cracked_andesite_bricks");

                chiseledBuilder(RecipeCategory.BUILDING_BLOCKS, BlockFactory.callBlock("andesite_brick_chiseled"), Ingredient.of(BlockFactory.callBlock("andesite_brick_slab")))
                .unlockedBy(getHasName(ANDERSITE_BRICKS.asItem()), has(ANDERSITE_BRICKS))
                .save(output);


                //Granite
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, GRANITE_BRICKS.asItem(), Blocks.GRANITE);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, GRANITE_BRICKS.asItem(), Blocks.POLISHED_GRANITE);

                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, BlockFactory.callBlock("granite_brick_slab").asItem(), GRANITE_BRICKS.asItem(), 2);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, BlockFactory.callBlock("granite_brick_slab").asItem(), Blocks.GRANITE, 2);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, BlockFactory.callBlock("granite_brick_slab").asItem(), Blocks.POLISHED_GRANITE, 2);

                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, BlockFactory.callBlock("granite_brick_stairs").asItem(), GRANITE_BRICKS.asItem());
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, BlockFactory.callBlock("granite_brick_stairs").asItem(), Blocks.GRANITE);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, BlockFactory.callBlock("granite_brick_stairs").asItem(), Blocks.POLISHED_GRANITE);
                
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, BlockFactory.callBlock("granite_brick_wall").asItem(), GRANITE_BRICKS.asItem());
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, BlockFactory.callBlock("granite_brick_wall").asItem(), Blocks.GRANITE);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, BlockFactory.callBlock("granite_brick_wall").asItem(), Blocks.POLISHED_GRANITE);

                stonecutterResultFromBase(RecipeCategory.DECORATIONS, BlockFactory.callBlock("granite_brick_chiseled").asItem(), GRANITE_BRICKS.asItem());
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, BlockFactory.callBlock("granite_brick_chiseled").asItem(), Blocks.GRANITE);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, BlockFactory.callBlock("granite_brick_chiseled").asItem(), Blocks.POLISHED_GRANITE);

                slabBuilder(RecipeCategory.BUILDING_BLOCKS, BlockFactory.callBlock("granite_brick_slab").asItem(), Ingredient.of(GRANITE_BRICKS.asItem())).unlockedBy(getHasName(GRANITE_BRICKS.asItem()), has(GRANITE_BRICKS.asItem()))
                .save(output);
                stairBuilder(BlockFactory.callBlock("granite_brick_stairs").asItem(), Ingredient.of(GRANITE_BRICKS.asItem())).unlockedBy(getHasName(GRANITE_BRICKS.asItem()), has(GRANITE_BRICKS.asItem()))
                .save(output);
                wall(RecipeCategory.DECORATIONS, BlockFactory.callBlock("granite_brick_wall").asItem(), GRANITE_BRICKS.asItem());

                SimpleCookingRecipeBuilder.smelting(
                    Ingredient.of(GRANITE_BRICKS), RecipeCategory.BUILDING_BLOCKS, BlockFactory.callBlock("cracked_granite_bricks").asItem(), 0.1F, 200
                )
                .unlockedBy(getHasName(GRANITE_BRICKS.asItem()), has(GRANITE_BRICKS.asItem()))
                .save(output, "smelted_" + "cracked_granite_bricks");

                chiseledBuilder(RecipeCategory.BUILDING_BLOCKS, BlockFactory.callBlock("granite_brick_chiseled"), Ingredient.of(BlockFactory.callBlock("granite_brick_slab")))
                .unlockedBy(getHasName(GRANITE_BRICKS.asItem()), has(GRANITE_BRICKS))
                .save(output);
                

                //Diorite
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, DIORITE_BRICKS.asItem(), Blocks.DIORITE);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, DIORITE_BRICKS.asItem(), Blocks.POLISHED_DIORITE);

                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, BlockFactory.callBlock("diorite_brick_slab").asItem(), DIORITE_BRICKS.asItem(), 2);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, BlockFactory.callBlock("diorite_brick_slab").asItem(), Blocks.DIORITE, 2);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, BlockFactory.callBlock("diorite_brick_slab").asItem(), Blocks.POLISHED_DIORITE, 2);

                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, BlockFactory.callBlock("diorite_brick_stairs").asItem(), DIORITE_BRICKS.asItem());
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, BlockFactory.callBlock("diorite_brick_stairs").asItem(), Blocks.DIORITE);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, BlockFactory.callBlock("diorite_brick_stairs").asItem(), Blocks.POLISHED_DIORITE);
                
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, BlockFactory.callBlock("diorite_brick_wall").asItem(), DIORITE_BRICKS.asItem());
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, BlockFactory.callBlock("diorite_brick_wall").asItem(), Blocks.DIORITE);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, BlockFactory.callBlock("diorite_brick_wall").asItem(), Blocks.POLISHED_DIORITE);

                stonecutterResultFromBase(RecipeCategory.DECORATIONS, BlockFactory.callBlock("diorite_brick_chiseled").asItem(), DIORITE_BRICKS.asItem());
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, BlockFactory.callBlock("diorite_brick_chiseled").asItem(), Blocks.DIORITE);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, BlockFactory.callBlock("diorite_brick_chiseled").asItem(), Blocks.POLISHED_DIORITE);

                slabBuilder(RecipeCategory.BUILDING_BLOCKS, BlockFactory.callBlock("diorite_brick_slab").asItem(), Ingredient.of(DIORITE_BRICKS.asItem())).unlockedBy(getHasName(DIORITE_BRICKS.asItem()), has(DIORITE_BRICKS.asItem()))
                .save(output);
                stairBuilder(BlockFactory.callBlock("diorite_brick_stairs").asItem(), Ingredient.of(DIORITE_BRICKS.asItem())).unlockedBy(getHasName(DIORITE_BRICKS.asItem()), has(DIORITE_BRICKS.asItem()))
                .save(output);
                wall(RecipeCategory.DECORATIONS, BlockFactory.callBlock("diorite_brick_wall").asItem(), DIORITE_BRICKS.asItem());

                SimpleCookingRecipeBuilder.smelting(
                    Ingredient.of(DIORITE_BRICKS), RecipeCategory.BUILDING_BLOCKS, BlockFactory.callBlock("cracked_diorite_bricks").asItem(), 0.1F, 200
                )
                .unlockedBy(getHasName(DIORITE_BRICKS.asItem()), has(DIORITE_BRICKS.asItem()))
                .save(output, "smelted_" + "cracked_diorite_bricks");

                chiseledBuilder(RecipeCategory.BUILDING_BLOCKS, BlockFactory.callBlock("diorite_brick_chiseled"), Ingredient.of(BlockFactory.callBlock("diorite_brick_slab")))
                    .unlockedBy(getHasName(DIORITE_BRICKS.asItem()), has(DIORITE_BRICKS))
                .save(output);


                wall(RecipeCategory.DECORATIONS, BlockFactory.callBlock("polished_andesite_wall").asItem(), Blocks.POLISHED_ANDESITE.asItem());
                wall(RecipeCategory.DECORATIONS, BlockFactory.callBlock("polished_granite_wall").asItem(), Blocks.POLISHED_GRANITE.asItem());
                wall(RecipeCategory.DECORATIONS, BlockFactory.callBlock("polished_diorite_wall").asItem(), Blocks.POLISHED_DIORITE.asItem());

                stonecutterResultFromBase(RecipeCategory.DECORATIONS, BlockFactory.callBlock("polished_andesite_wall").asItem(), Blocks.POLISHED_ANDESITE);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, BlockFactory.callBlock("polished_granite_wall").asItem(), Blocks.POLISHED_GRANITE);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS, BlockFactory.callBlock("polished_diorite_wall").asItem(), Blocks.POLISHED_DIORITE);



                shaped(RecipeCategory.BUILDING_BLOCKS, ANDERSITE_BRICKS, 4)
                    .define('#', Blocks.POLISHED_ANDESITE)
                    .pattern("##")
                    .pattern("##")
                    .unlockedBy(getHasName(Blocks.POLISHED_ANDESITE), has(Blocks.POLISHED_ANDESITE))
                    .save(output);
                
                shaped(RecipeCategory.BUILDING_BLOCKS, DIORITE_BRICKS, 4)
                    .define('#', Blocks.POLISHED_DIORITE)
                    .pattern("##")
                    .pattern("##")
                    .unlockedBy(getHasName(Blocks.POLISHED_DIORITE), has(Blocks.POLISHED_DIORITE))
                    .save(output);

                shaped(RecipeCategory.BUILDING_BLOCKS, GRANITE_BRICKS, 4)
                    .define('#', Blocks.POLISHED_GRANITE)
                    .pattern("##")
                    .pattern("##")
                    .unlockedBy(getHasName(Blocks.POLISHED_GRANITE), has(Blocks.POLISHED_GRANITE))
                    .save(output);
                    
            }

            /* Legacy */

            @Deprecated
            public void createWoodGroup(String baseItemName, String endTag){
                Ingredient baseItemI = Ingredient.of(BlockFactory.callBlock(baseItemName + "_planks" + endTag).asItem());
                Item baseItem = (BlockFactory.callBlock(baseItemName + "_planks" + endTag).asItem());

                trapdoorBuilder(BlockFactory.callBlock(baseItemName + "_trapdoor" + endTag).asItem(), baseItemI)
                .group("wooden_trapdoor")
                .unlockedBy(getHasName(baseItem), has(baseItem))
                .save(output);

                fenceBuilder(BlockFactory.callBlock(baseItemName + "_fence" + endTag).asItem(), baseItemI)
                .group("wooden_fence")
                .unlockedBy(getHasName(baseItem), has(baseItem))
                .save(output);

                fenceGateBuilder(BlockFactory.callBlock(baseItemName + "_fence_gate" + endTag).asItem(), baseItemI)
                .group("wooden_fence_gate")
                .unlockedBy(getHasName(baseItem), has(baseItem))
                .save(output);

                pressurePlateBuilder(RecipeCategory.BUILDING_BLOCKS, BlockFactory.callBlock(baseItemName + "_pressure_plate" + endTag).asItem(), baseItemI)
                .group("wooden_pressure_plate")
                .unlockedBy(getHasName(baseItem), has(baseItem))
                .save(output);

                slabBuilder(RecipeCategory.BUILDING_BLOCKS, BlockFactory.callBlock(baseItemName + "_slab" + endTag).asItem(), baseItemI)
                .group("wooden_slab")
                .unlockedBy(getHasName(baseItem), has(baseItem))
                .save(output);

                stairBuilder(BlockFactory.callBlock(baseItemName + "_stairs" + endTag).asItem(), baseItemI)
                .group("wooden_stairs")
                .unlockedBy(getHasName(baseItem), has(baseItem))
                .save(output);

                buttonBuilder(BlockFactory.callBlock(baseItemName + "_button" + endTag).asItem(), baseItemI)
                .group("wooden_button")
                .unlockedBy(getHasName(baseItem), has(baseItem))
                .save(output);

                doorBuilder(BlockFactory.callBlock(baseItemName + "_door" + endTag).asItem(), baseItemI)
                .group("wooden_door")
                .unlockedBy(getHasName(baseItem), has(baseItem))
                .save(output);
            }

            //Recipe Create for Color Planks
            @Deprecated
            public void createColorPlanks(Item dye, ItemLike outputItem, String color) {

                shaped(RecipeCategory.BUILDING_BLOCKS, outputItem, 8)
                .define('#', BlockFactory.callBlock("fungal_planks").asItem())
                .define('D', dye)
                .pattern("###")
                .pattern("#D#")
                .pattern("###")
                .group("colored_planks")
                .unlockedBy(getHasName(BlockFactory.callBlock("fungal_planks").asItem()), has(BlockFactory.callBlock("fungal_planks").asItem()))
                .unlockedBy(getHasName(dye), has(dye))
                .save(output);
                
                createWoodGroup("fungal", "_" + color);
            }

            //Recipe Create for Modded Weapons - Wood & Stone
            @Deprecated
            public void createModdedWeapon(Item Material, String baseMat, TagKey<Item> matTag) {

                createLongSword(Material, ItemFactory.callItem(baseMat + "_long_sword"), matTag);
                createDagger(Material, ItemFactory.callItem(baseMat + "_dagger"), matTag);
                createHammer(Material, ItemFactory.callItem(baseMat + "_hammer"), matTag);

            }

            //Recipe Create for Modded Weapons - Gold, Iron & Diamond
            @Deprecated
            public void createModdedWeapon(Item Material, String baseMat) {

                createLongSword(Material, ItemFactory.callItem(baseMat + "_long_sword"));
                createDagger(Material, ItemFactory.callItem(baseMat + "_dagger"));
                createHammer(Material, ItemFactory.callItem(baseMat + "_hammer"));

                boolean Enabled = false;

                if ("iron" == baseMat && Enabled){
                    generateMeltIron(Ingredient.of(
                        ItemFactory.callItem(baseMat + "_long_sword"),
                        ItemFactory.callItem(baseMat + "_dagger"),
                        ItemFactory.callItem(baseMat + "_hammer")
                    ));   
                } 
                else if ("golden" == baseMat && Enabled){
                    generateMeltGold(Ingredient.of(
                        ItemFactory.callItem(baseMat + "_long_sword"),
                        ItemFactory.callItem(baseMat + "_dagger"),
                        ItemFactory.callItem(baseMat + "_hammer")
                    )); 
                }

            }

            //Recipe Melt to Gold Nuggets
            @Deprecated
            public void generateMeltGold(Ingredient SmeltItems) {
                SimpleCookingRecipeBuilder.smelting(
                    SmeltItems,
                    RecipeCategory.MISC,
                    Items.GOLD_NUGGET,
                    0.1F,
                    200
                )
                .unlockedBy(getHasName(ItemFactory.callItem(Items.GOLD_INGOT + "_long_sword")), has(ItemFactory.callItem(Items.GOLD_INGOT + "_long_sword")))
                .unlockedBy(getHasName(ItemFactory.callItem(Items.GOLD_INGOT + "_dagger")), has(ItemFactory.callItem(Items.GOLD_INGOT + "_dagger")))
                .unlockedBy(getHasName(ItemFactory.callItem(Items.GOLD_INGOT + "_hammer")), has(ItemFactory.callItem(Items.GOLD_INGOT + "_hammer")))
                .save(output, getSmeltingRecipeName(Items.GOLD_NUGGET));
            }

            //Recipe Melt to Iron Nuggets
            @Deprecated
            public void generateMeltIron(Ingredient SmeltItems) {
                SimpleCookingRecipeBuilder.smelting(
                    SmeltItems,
                    RecipeCategory.MISC,
                    Items.IRON_NUGGET,
                    0.1F,
                    200
                )
                .unlockedBy(getHasName(ItemFactory.callItem(Items.IRON_INGOT + "_long_sword")), has(ItemFactory.callItem(Items.IRON_INGOT + "_long_sword")))
                .unlockedBy(getHasName(ItemFactory.callItem(Items.IRON_INGOT + "_dagger")), has(ItemFactory.callItem(Items.IRON_INGOT + "_dagger")))
                .unlockedBy(getHasName(ItemFactory.callItem(Items.IRON_INGOT + "_hammer")), has(ItemFactory.callItem(Items.IRON_INGOT + "_hammer")))
                .save(output, getSmeltingRecipeName(Items.IRON_NUGGET));
            }
        
        };
    }

    @Override
    public String getName() {
        return "EntStupidStuff Recipes";
    }

}
