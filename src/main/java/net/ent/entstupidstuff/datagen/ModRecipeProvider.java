package net.ent.entstupidstuff.datagen;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import net.ent.entstupidstuff.block.BlockFactory;
import net.ent.entstupidstuff.item.ItemFactory;
import net.ent.entstupidstuff.item.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
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

public class ModRecipeProvider extends FabricRecipeProvider {

    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    public static RecipeOutput exporter;

    // A way to enable and disable shit //TODO: Implement
    Boolean enableWeapons = true;
    Boolean enableClaymore = false;
    Boolean enableHammer = true;
    Boolean enableGlaive = false;
    Boolean enableBattleAxe = false; // Disabled
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

        // ## List
        List<Item> list_dye = List.of(
			Items.BLACK_DYE,
			Items.BLUE_DYE,
			Items.BROWN_DYE,
			Items.CYAN_DYE,
			Items.GRAY_DYE,
			Items.GREEN_DYE,
			Items.LIGHT_BLUE_DYE,
			Items.LIGHT_GRAY_DYE,
			Items.LIME_DYE,
			Items.MAGENTA_DYE,
			Items.ORANGE_DYE,
			Items.PINK_DYE,
			Items.PURPLE_DYE,
			Items.RED_DYE,
			Items.YELLOW_DYE,
			Items.WHITE_DYE
		);

        List<Item> list_bed = List.of(
			Items.BLACK_BED,
			Items.BLUE_BED,
			Items.BROWN_BED,
			Items.CYAN_BED,
			Items.GRAY_BED,
			Items.GREEN_BED,
			Items.LIGHT_BLUE_BED,
			Items.LIGHT_GRAY_BED,
			Items.LIME_BED,
			Items.MAGENTA_BED,
			Items.ORANGE_BED,
			Items.PINK_BED,
			Items.PURPLE_BED,
			Items.RED_BED,
			Items.YELLOW_BED,
			Items.WHITE_BED
		);

        List<Item> list_banner = List.of(
			Items.BLACK_BANNER,
			Items.BLUE_BANNER,
			Items.BROWN_BANNER,
			Items.CYAN_BANNER,
			Items.GRAY_BANNER,
			Items.GREEN_BANNER,
			Items.LIGHT_BLUE_BANNER,
			Items.LIGHT_GRAY_BANNER,
			Items.LIME_BANNER,
			Items.MAGENTA_BANNER,
			Items.ORANGE_BANNER,
			Items.PINK_BANNER,
			Items.PURPLE_BANNER,
			Items.RED_BANNER,
			Items.YELLOW_BANNER,
			Items.WHITE_BANNER
		);

        List<Item> list_harness = List.of(
			Items.BLACK_HARNESS,
			Items.BLUE_HARNESS,
			Items.BROWN_HARNESS,
			Items.CYAN_HARNESS,
			Items.GRAY_HARNESS,
			Items.GREEN_HARNESS,
			Items.LIGHT_BLUE_HARNESS,
			Items.LIGHT_GRAY_HARNESS,
			Items.LIME_HARNESS,
			Items.MAGENTA_HARNESS,
			Items.ORANGE_HARNESS,
			Items.PINK_HARNESS,
			Items.PURPLE_HARNESS,
			Items.RED_HARNESS,
			Items.YELLOW_HARNESS,
			Items.WHITE_HARNESS
		);

        List<Item> list_glowing_silk = List.of(
            ItemFactory.callItem("glowing_silk_wool_black"),
            ItemFactory.callItem("glowing_silk_wool_blue"),
            ItemFactory.callItem("glowing_silk_wool_brown"),
            ItemFactory.callItem("glowing_silk_wool_cyan"),
            ItemFactory.callItem("glowing_silk_wool_gray"),
            ItemFactory.callItem("glowing_silk_wool_green"),
            ItemFactory.callItem("glowing_silk_wool_light_blue"),
            ItemFactory.callItem("glowing_silk_wool_light_gray"),
            ItemFactory.callItem("glowing_silk_wool_lime"),
            ItemFactory.callItem("glowing_silk_wool_magenta"),
            ItemFactory.callItem("glowing_silk_wool_orange"),
            ItemFactory.callItem("glowing_silk_wool_pink"),
            ItemFactory.callItem("glowing_silk_wool_purple"),
            ItemFactory.callItem("glowing_silk_wool_red"),
            ItemFactory.callItem("glowing_silk_wool_yellow"),
            ItemFactory.callItem("glowing_silk_wool_white")
		);

        List<Item> list_glowing_silk_carpet = List.of(
            ItemFactory.callItem("glowing_silk_wool_black_carpet"),
            ItemFactory.callItem("glowing_silk_wool_blue_carpet"),
            ItemFactory.callItem("glowing_silk_wool_brown_carpet"),
            ItemFactory.callItem("glowing_silk_wool_cyan_carpet"),
            ItemFactory.callItem("glowing_silk_wool_gray_carpet"),
            ItemFactory.callItem("glowing_silk_wool_green_carpet"),
            ItemFactory.callItem("glowing_silk_wool_light_blue_carpet"),
            ItemFactory.callItem("glowing_silk_wool_light_gray_carpet"),
            ItemFactory.callItem("glowing_silk_wool_lime_carpet"),
            ItemFactory.callItem("glowing_silk_wool_magenta_carpet"),
            ItemFactory.callItem("glowing_silk_wool_orange_carpet"),
            ItemFactory.callItem("glowing_silk_wool_pink_carpet"),
            ItemFactory.callItem("glowing_silk_wool_purple_carpet"),
            ItemFactory.callItem("glowing_silk_wool_red_carpet"),
            ItemFactory.callItem("glowing_silk_wool_yellow_carpet"),
            ItemFactory.callItem("glowing_silk_wool_white_carpet")
		);

		List<Item> list_wool = List.of(
			Items.BLACK_WOOL,
			Items.BLUE_WOOL,
			Items.BROWN_WOOL,
			Items.CYAN_WOOL,
			Items.GRAY_WOOL,
			Items.GREEN_WOOL,
			Items.LIGHT_BLUE_WOOL,
			Items.LIGHT_GRAY_WOOL,
			Items.LIME_WOOL,
			Items.MAGENTA_WOOL,
			Items.ORANGE_WOOL,
			Items.PINK_WOOL,
			Items.PURPLE_WOOL,
			Items.RED_WOOL,
			Items.YELLOW_WOOL,
			Items.WHITE_WOOL
		);



            // ## Recipes

            @Override
            public void buildRecipes() {

                // Updated:

                // # Added REDWOOD Natural + Planks
                groupWoodFamilty("redwood", "", true);

                // # Added FIR Natural + Planks
                groupWoodFamilty("fir", "", true);

                // # Added MAPLE Natural + Planks
                groupWoodFamilty("maple", "", true);

                // # Added PHANTOM Natural + Planks + Lantern + Torch
                groupWoodFamilty("phantom", "", true);
                // TODO: ADD PHANTOM LANTERN
                // TODO: ADD PHANTOM TORCH

                // # Added Fungal Natural + Planks (Regular + Colored)
                groupWoodFamilty("fungal", "", false);
                shapeless(RecipeCategory.BUILDING_BLOCKS, BlockFactory.callBlock("fungal_planks"), 4)
                    .requires(ModTags.MUSHROOM_CRAFTING)
                    .group("planks")
                    .unlockedBy(getHasName(Blocks.MUSHROOM_STEM), has(Blocks.MUSHROOM_STEM))
                .save(output);

                List<Item> list_planks = new ArrayList<>();
                List<Item> list_stairs = new ArrayList<>();
                List<Item> list_slab = new ArrayList<>();
                List<Item> list_fence = new ArrayList<>();
                List<Item> list_fence_gate = new ArrayList<>();
                List<Item> list_door = new ArrayList<>();
                List<Item> list_glass_door = new ArrayList<>();
                List<Item> list_trapdoor = new ArrayList<>();
                List<Item> list_glass_trapdoor = new ArrayList<>();
                List<Item> list_pressure_plate = new ArrayList<>();
                List<Item> list_button = new ArrayList<>();
                List<Item> list_mosaic = new ArrayList<>();
                List<Item> list_mosaic_stairs = new ArrayList<>();
                List<Item> list_mosaic_slab = new ArrayList<>();


                for (String color : BlockFactory.RECIPES_COLORS) {
                    list_planks.add(ItemFactory.callItem("fungal" + "_planks_" + color));
                    list_stairs.add(ItemFactory.callItem("fungal" + "_stairs_" + color));
                    list_slab.add(ItemFactory.callItem("fungal" + "_slab_" + color));
                    list_fence.add(ItemFactory.callItem("fungal" + "_fence_" + color));
                    list_fence_gate.add(ItemFactory.callItem("fungal" + "_fence_gate_" + color));
                    list_door.add(ItemFactory.callItem("fungal" + "_door_" + color));
                    list_glass_door.add(ItemFactory.callItem("fungal" + "_glass_door_" + color));
                    list_trapdoor.add(ItemFactory.callItem("fungal" + "_trapdoor_" + color));
                    list_glass_trapdoor.add(ItemFactory.callItem("fungal" + "_glass_trapdoor_" + color));
                    list_pressure_plate.add(ItemFactory.callItem("fungal" + "_pressure_plate_" + color));
                    list_button.add(ItemFactory.callItem("fungal" + "_button_" + color));
                    list_mosaic.add(ItemFactory.callItem("fungal" + "_mosaic_" + color));
                    list_mosaic_stairs.add(ItemFactory.callItem("fungal" + "_mosaic_stairs_" + color));
                    list_mosaic_slab.add(ItemFactory.callItem("fungal" + "_mosaic_slab_" + color));

                    //this.colorWithDye(list_dye, List.of(), null, "fungal", recipeCategory);
                    //List.of(

                }

                list_planks.add(ItemFactory.callItem("fungal" + "_planks"));
                list_stairs.add(ItemFactory.callItem("fungal" + "_stairs"));
                list_slab.add(ItemFactory.callItem("fungal" + "_slab"));
                list_fence.add(ItemFactory.callItem("fungal" + "_fence"));
                list_fence_gate.add(ItemFactory.callItem("fungal" + "_fence_gate"));
                list_door.add(ItemFactory.callItem("fungal" + "_door"));
                list_glass_door.add(ItemFactory.callItem("fungal" + "_glass_door"));
                list_trapdoor.add(ItemFactory.callItem("fungal" + "_trapdoor"));
                list_glass_trapdoor.add(ItemFactory.callItem("fungal" + "_glass_trapdoor"));
                list_pressure_plate.add(ItemFactory.callItem("fungal" + "_pressure_plate"));
                list_button.add(ItemFactory.callItem("fungal" + "_button"));
                list_mosaic.add(ItemFactory.callItem("fungal" + "_mosaic"));
                list_mosaic_stairs.add(ItemFactory.callItem("fungal" + "_mosaic_stairs"));
                list_mosaic_slab.add(ItemFactory.callItem("fungal" + "_mosaic_slab"));

                this.colorItemWithDye(list_dye, list_planks, "fungal_planks_dyed", RecipeCategory.BUILDING_BLOCKS);
                this.colorItemWithDye(list_dye, list_stairs, "fungal_stairs_dyed", RecipeCategory.BUILDING_BLOCKS);
                this.colorItemWithDye(list_dye, list_slab, "fungal_slab_dyed", RecipeCategory.BUILDING_BLOCKS);
                this.colorItemWithDye(list_dye, list_fence, "fungal_fence_dyed", RecipeCategory.BUILDING_BLOCKS);
                this.colorItemWithDye(list_dye, list_fence_gate, "fungal_fence_gate_dyed", RecipeCategory.BUILDING_BLOCKS);
                this.colorItemWithDye(list_dye, list_door, "fungal_door_dyed", RecipeCategory.BUILDING_BLOCKS);
                this.colorItemWithDye(list_dye, list_glass_door, "fungal_glass_door_dyed", RecipeCategory.BUILDING_BLOCKS);
                this.colorItemWithDye(list_dye, list_trapdoor, "fungal_trapdoor_dyed", RecipeCategory.BUILDING_BLOCKS);
                this.colorItemWithDye(list_dye, list_glass_trapdoor, "fungal_glass_trapdoor_dyed", RecipeCategory.BUILDING_BLOCKS);
                this.colorItemWithDye(list_dye, list_pressure_plate, "fungal_pressure_plate_dyed", RecipeCategory.BUILDING_BLOCKS);
                this.colorItemWithDye(list_dye, list_button, "fungal_button_dyed", RecipeCategory.BUILDING_BLOCKS);
                this.colorItemWithDye(list_dye, list_mosaic, "fungal_mosaic_dyed", RecipeCategory.BUILDING_BLOCKS);
                this.colorItemWithDye(list_dye, list_mosaic_stairs, "fungal_mosaic_stairs_dyed", RecipeCategory.BUILDING_BLOCKS);
                this.colorItemWithDye(list_dye, list_mosaic_slab, "fungal_mosaic_slab_dyed", RecipeCategory.BUILDING_BLOCKS);



                // # Adding Andersite, Diorite and Granite
                if (enableAdditionalStone) {
                    createStoneVarient();
                }

                // # Adding Limestone
                if (enableLimestoneRecipes) {
                    groupFromScratchStone("limestone");
                }

                // # Vanilla Additions
                if (enableVanillaGlassDoor) {
                    createGlassDoorRecipe(BlockFactory.callBlock("oak" + "_glass_door"), Blocks.OAK_DOOR,
                            Blocks.OAK_PLANKS.asItem(), true);
                    createGlassDoorRecipe(BlockFactory.callBlock("spruce" + "_glass_door"), Blocks.SPRUCE_DOOR,
                            Blocks.SPRUCE_PLANKS.asItem(), true);
                    createGlassDoorRecipe(BlockFactory.callBlock("jungle" + "_glass_door"), Blocks.JUNGLE_DOOR,
                            Blocks.JUNGLE_PLANKS.asItem(), true);
                    createGlassDoorRecipe(BlockFactory.callBlock("birch" + "_glass_door"), Blocks.BIRCH_DOOR,
                            Blocks.BIRCH_PLANKS.asItem(), true);
                    createGlassDoorRecipe(BlockFactory.callBlock("dark_oak" + "_glass_door"), Blocks.DARK_OAK_DOOR,
                            Blocks.DARK_OAK_PLANKS.asItem(), true);
                    createGlassDoorRecipe(BlockFactory.callBlock("acacia" + "_glass_door"), Blocks.ACACIA_DOOR,
                            Blocks.ACACIA_PLANKS.asItem(), true);
                    createGlassDoorRecipe(BlockFactory.callBlock("mangrove" + "_glass_door"), Blocks.MANGROVE_DOOR,
                            Blocks.MANGROVE_PLANKS.asItem(), true);
                    createGlassDoorRecipe(BlockFactory.callBlock("cherry" + "_glass_door"), Blocks.CHERRY_DOOR,
                            Blocks.CHERRY_PLANKS.asItem(), true);
                    createGlassDoorRecipe(BlockFactory.callBlock("bamboo" + "_glass_door"), Blocks.BAMBOO_DOOR,
                            Blocks.BAMBOO_PLANKS.asItem(), true);
                    createGlassDoorRecipe(BlockFactory.callBlock("warped" + "_glass_door"), Blocks.WARPED_DOOR,
                            Blocks.WARPED_PLANKS.asItem(), true);
                    createGlassDoorRecipe(BlockFactory.callBlock("crimson" + "_glass_door"), Blocks.CRIMSON_DOOR,
                            Blocks.CRIMSON_PLANKS.asItem(), true);
                    createGlassDoorRecipe(BlockFactory.callBlock("pale_oak" + "_glass_door"), Blocks.PALE_OAK_DOOR,
                            Blocks.PALE_OAK_PLANKS.asItem(), true); // FUTURE UPDATE

                    createGlassDoorRecipe(BlockFactory.callBlock("iron" + "_glass_door"), Blocks.IRON_DOOR,
                            Items.IRON_INGOT, false);
                    createGlassDoorRecipe(BlockFactory.callBlock("copper" + "_glass_door"), Blocks.COPPER_DOOR,
                            Items.COPPER_INGOT, false);
                    createGlassDoorRecipe(BlockFactory.callBlock("exposed_copper" + "_glass_door"),
                            Blocks.EXPOSED_COPPER_DOOR, Items.COPPER_INGOT, false);
                    createGlassDoorRecipe(BlockFactory.callBlock("oxidized_copper" + "_glass_door"),
                            Blocks.OXIDIZED_COPPER_DOOR, Items.COPPER_INGOT, false);
                    createGlassDoorRecipe(BlockFactory.callBlock("weathered_copper" + "_glass_door"),
                            Blocks.WEATHERED_COPPER_DOOR, Items.COPPER_INGOT, false);
                    createGlassDoorRecipe(BlockFactory.callBlock("waxed_copper" + "_glass_door"),
                            Blocks.WAXED_COPPER_DOOR, Items.COPPER_INGOT, false);
                    createGlassDoorRecipe(BlockFactory.callBlock("waxed_exposed_copper" + "_glass_door"),
                            Blocks.WAXED_EXPOSED_COPPER_DOOR, Items.COPPER_INGOT, false);
                    createGlassDoorRecipe(BlockFactory.callBlock("waxed_oxidized_copper" + "_glass_door"),
                            Blocks.WAXED_OXIDIZED_COPPER_DOOR, Items.COPPER_INGOT, false);
                    createGlassDoorRecipe(BlockFactory.callBlock("waxed_weathered_copper" + "_glass_door"),
                            Blocks.WAXED_WEATHERED_COPPER_DOOR, Items.COPPER_INGOT, false);

                    smeltToIron(BlockFactory.callBlock("iron" + "_glass_door").asItem(), "iron" + "_glass_door");
                    smeltToCopper(BlockFactory.callBlock("copper" + "_glass_door").asItem(), "copper" + "_glass_door");
                    smeltToCopper(BlockFactory.callBlock("exposed_copper" + "_glass_door").asItem(),
                            "exposed_copper" + "_glass_door");
                    smeltToCopper(BlockFactory.callBlock("oxidized_copper" + "_glass_door").asItem(),
                            "oxidized_copper" + "_glass_door");
                    smeltToCopper(BlockFactory.callBlock("weathered_copper" + "_glass_door").asItem(),
                            "weathered_copper" + "_glass_door");
                    smeltToCopper(BlockFactory.callBlock("waxed_copper" + "_glass_door").asItem(),
                            "waxed_copper" + "_glass_door");
                    smeltToCopper(BlockFactory.callBlock("waxed_exposed_copper" + "_glass_door").asItem(),
                            "waxed_exposed_copper" + "_glass_door");
                    smeltToCopper(BlockFactory.callBlock("waxed_oxidized_copper" + "_glass_door").asItem(),
                            "waxed_oxidized_copper" + "_glass_door");
                    smeltToCopper(BlockFactory.callBlock("waxed_weathered_copper" + "_glass_door").asItem(),
                            "waxed_weathered_copper" + "_glass_door");

                }

                if (enableVanillaGlassTrapDoor) {
                    createGlassTrapDoorRecipe(BlockFactory.callBlock("oak" + "_glass_trapdoor"), Blocks.OAK_TRAPDOOR,
                            Blocks.OAK_PLANKS.asItem(), true);
                    createGlassTrapDoorRecipe(BlockFactory.callBlock("spruce" + "_glass_trapdoor"),
                            Blocks.SPRUCE_TRAPDOOR, Blocks.SPRUCE_PLANKS.asItem(), true);
                    createGlassTrapDoorRecipe(BlockFactory.callBlock("jungle" + "_glass_trapdoor"),
                            Blocks.JUNGLE_TRAPDOOR, Blocks.JUNGLE_PLANKS.asItem(), true);
                    createGlassTrapDoorRecipe(BlockFactory.callBlock("birch" + "_glass_trapdoor"),
                            Blocks.BIRCH_TRAPDOOR, Blocks.BIRCH_PLANKS.asItem(), true);
                    createGlassTrapDoorRecipe(BlockFactory.callBlock("dark_oak" + "_glass_trapdoor"),
                            Blocks.DARK_OAK_TRAPDOOR, Blocks.DARK_OAK_PLANKS.asItem(), true);
                    createGlassTrapDoorRecipe(BlockFactory.callBlock("acacia" + "_glass_trapdoor"),
                            Blocks.ACACIA_TRAPDOOR, Blocks.ACACIA_PLANKS.asItem(), true);
                    createGlassTrapDoorRecipe(BlockFactory.callBlock("mangrove" + "_glass_trapdoor"),
                            Blocks.MANGROVE_TRAPDOOR, Blocks.MANGROVE_PLANKS.asItem(), true);
                    createGlassTrapDoorRecipe(BlockFactory.callBlock("cherry" + "_glass_trapdoor"),
                            Blocks.CHERRY_TRAPDOOR, Blocks.CHERRY_PLANKS.asItem(), true);
                    createGlassTrapDoorRecipe(BlockFactory.callBlock("bamboo" + "_glass_trapdoor"),
                            Blocks.BAMBOO_TRAPDOOR, Blocks.BAMBOO_PLANKS.asItem(), true);
                    createGlassTrapDoorRecipe(BlockFactory.callBlock("warped" + "_glass_trapdoor"),
                            Blocks.WARPED_TRAPDOOR, Blocks.WARPED_PLANKS.asItem(), true);
                    createGlassTrapDoorRecipe(BlockFactory.callBlock("crimson" + "_glass_trapdoor"),
                            Blocks.CRIMSON_TRAPDOOR, Blocks.CRIMSON_PLANKS.asItem(), true);
                    createGlassTrapDoorRecipe(BlockFactory.callBlock("pale_oak" + "_glass_trapdoor"),
                            Blocks.PALE_OAK_TRAPDOOR, Blocks.PALE_OAK_PLANKS.asItem(), true);

                    createGlassTrapDoorRecipe(BlockFactory.callBlock("iron" + "_glass_trapdoor"), Blocks.IRON_TRAPDOOR,
                            Items.IRON_INGOT, false);
                    createGlassTrapDoorRecipe(BlockFactory.callBlock("copper" + "_glass_trapdoor"),
                            Blocks.COPPER_TRAPDOOR, Items.COPPER_INGOT, false);
                    createGlassTrapDoorRecipe(BlockFactory.callBlock("exposed_copper" + "_glass_trapdoor"),
                            Blocks.EXPOSED_COPPER_TRAPDOOR, Items.COPPER_INGOT, false);
                    createGlassTrapDoorRecipe(BlockFactory.callBlock("oxidized_copper" + "_glass_trapdoor"),
                            Blocks.OXIDIZED_COPPER_TRAPDOOR, Items.COPPER_INGOT, false);
                    createGlassTrapDoorRecipe(BlockFactory.callBlock("weathered_copper" + "_glass_trapdoor"),
                            Blocks.WEATHERED_COPPER_TRAPDOOR, Items.COPPER_INGOT, false);
                    createGlassTrapDoorRecipe(BlockFactory.callBlock("waxed_copper" + "_glass_trapdoor"),
                            Blocks.WAXED_COPPER_TRAPDOOR, Items.COPPER_INGOT, false);
                    createGlassTrapDoorRecipe(BlockFactory.callBlock("waxed_exposed_copper" + "_glass_trapdoor"),
                            Blocks.WAXED_EXPOSED_COPPER_TRAPDOOR, Items.COPPER_INGOT, false);
                    createGlassTrapDoorRecipe(BlockFactory.callBlock("waxed_oxidized_copper" + "_glass_trapdoor"),
                            Blocks.WAXED_OXIDIZED_COPPER_TRAPDOOR, Items.COPPER_INGOT, false);
                    createGlassTrapDoorRecipe(BlockFactory.callBlock("waxed_weathered_copper" + "_glass_trapdoor"),
                            Blocks.WAXED_WEATHERED_COPPER_TRAPDOOR, Items.COPPER_INGOT, false);

                    smeltToIron(BlockFactory.callBlock("iron" + "_glass_trapdoor").asItem(),
                            "iron" + "_glass_trapdoor");
                    smeltToCopper(BlockFactory.callBlock("copper" + "_glass_trapdoor").asItem(),
                            "copper" + "_glass_trapdoor");
                    smeltToCopper(BlockFactory.callBlock("exposed_copper" + "_glass_trapdoor").asItem(),
                            "exposed_copper" + "_glass_trapdoor");
                    smeltToCopper(BlockFactory.callBlock("oxidized_copper" + "_glass_trapdoor").asItem(),
                            "oxidized_copper" + "_glass_trapdoor");
                    smeltToCopper(BlockFactory.callBlock("weathered_copper" + "_glass_trapdoor").asItem(),
                            "weathered_copper" + "_glass_trapdoor");
                    smeltToCopper(BlockFactory.callBlock("waxed_copper" + "_glass_trapdoor").asItem(),
                            "waxed_copper" + "_glass_trapdoor");
                    smeltToCopper(BlockFactory.callBlock("waxed_exposed_copper" + "_glass_trapdoor").asItem(),
                            "waxed_exposed_copper" + "_glass_trapdoor");
                    smeltToCopper(BlockFactory.callBlock("waxed_oxidized_copper" + "_glass_trapdoor").asItem(),
                            "waxed_oxidized_copper" + "_glass_trapdoor");
                    smeltToCopper(BlockFactory.callBlock("waxed_weathered_copper" + "_glass_trapdoor").asItem(),
                            "waxed_weathered_copper" + "_glass_trapdoor");
                }

                generateMosaic("oak", BlockFactory.callBlock("oak" + "_mosaic"), Blocks.OAK_SLAB);
                generateMosaic("spruce", BlockFactory.callBlock("spruce" + "_mosaic"), Blocks.SPRUCE_SLAB);
                generateMosaic("jungle", BlockFactory.callBlock("jungle" + "_mosaic"), Blocks.JUNGLE_SLAB);
                generateMosaic("birch", BlockFactory.callBlock("birch" + "_mosaic"), Blocks.BIRCH_SLAB);
                generateMosaic("dark_oak", BlockFactory.callBlock("dark_oak" + "_mosaic"), Blocks.DARK_OAK_SLAB);
                generateMosaic("acacia", BlockFactory.callBlock("acacia" + "_mosaic"), Blocks.ACACIA_SLAB);
                generateMosaic("mangrove", BlockFactory.callBlock("mangrove" + "_mosaic"), Blocks.MANGROVE_SLAB);
                generateMosaic("cherry", BlockFactory.callBlock("cherry" + "_mosaic"), Blocks.CHERRY_SLAB);
                generateMosaic("pale_oak", BlockFactory.callBlock("pale_oak" + "_mosaic"), Blocks.PALE_OAK_SLAB);
                generateMosaic("warped", BlockFactory.callBlock("warped" + "_mosaic"), Blocks.WARPED_SLAB);
                generateMosaic("crimson", BlockFactory.callBlock("crimson" + "_mosaic"), Blocks.CRIMSON_SLAB);

                // # Food Recipes

                shaped(RecipeCategory.FOOD, ItemFactory.callItem("baguette"))
                        .define('#', Items.BREAD)
                        .pattern("#")
                        .pattern("#")
                        .pattern("#")
                        .unlockedBy("has_bread", has(Items.BREAD))
                        .save(output);

                Item MARSHMELLOW_TOASTED = ItemFactory.callItem("toasted_marshmellow");
                Item MARSHMELLOW_RAW = ItemFactory.callItem("raw_marshmellow");

                SimpleCookingRecipeBuilder
                        .smelting(Ingredient.of(MARSHMELLOW_RAW), RecipeCategory.FOOD, MARSHMELLOW_TOASTED, 0.35F, 200)
                        .unlockedBy("has_marshmellow", has(MARSHMELLOW_RAW))
                        .save(output);

                simpleCookingRecipe("smoker", RecipeSerializer.SMOKING_RECIPE, SmokingRecipe::new, 100, MARSHMELLOW_RAW,
                        MARSHMELLOW_TOASTED, 0.35F);
                simpleCookingRecipe("campfire_cooking", RecipeSerializer.CAMPFIRE_COOKING_RECIPE,
                        CampfireCookingRecipe::new, 600, MARSHMELLOW_RAW, MARSHMELLOW_TOASTED, 0.35F);

                shapeless(RecipeCategory.FOOD, ItemFactory.MARSHMELLOW_RAW, 1)
                        .requires(Items.STICK, 1)
                        .requires(Items.HONEYCOMB, 1)
                        .requires(Items.SUGAR, 1)
                        .unlockedBy(getHasName(Items.STICK), has(Items.STICK))
                        .unlockedBy(getHasName(Items.HONEY_BLOCK), has(Items.HONEY_BLOCK))
                        .unlockedBy(getHasName(Items.SUGAR), has(Items.SUGAR))
                        .save(output);

                // Alligator Gar

                simpleCookingRecipe("smoker", RecipeSerializer.SMOKING_RECIPE, SmokingRecipe::new, 100,
                        ItemFactory.ALLIGATOR_GAR, ItemFactory.COOKED_ALLIGATOR_GAR, 0.35F);
                simpleCookingRecipe("campfire_cooking", RecipeSerializer.CAMPFIRE_COOKING_RECIPE,
                        CampfireCookingRecipe::new, 600, ItemFactory.ALLIGATOR_GAR, ItemFactory.COOKED_ALLIGATOR_GAR,
                        0.35F);

                SimpleCookingRecipeBuilder
                        .smelting(Ingredient.of(ItemFactory.ALLIGATOR_GAR), RecipeCategory.FOOD,
                                ItemFactory.COOKED_ALLIGATOR_GAR, 0.35F, 200)
                        .unlockedBy("has_alligator_gar", has(ItemFactory.ALLIGATOR_GAR))
                        .save(output);

                // Mackerel

                simpleCookingRecipe("smoker", RecipeSerializer.SMOKING_RECIPE, SmokingRecipe::new, 100,
                        ItemFactory.MACKEREL, ItemFactory.COOKED_MACKEREL, 0.35F);
                simpleCookingRecipe("campfire_cooking", RecipeSerializer.CAMPFIRE_COOKING_RECIPE,
                        CampfireCookingRecipe::new, 600, ItemFactory.MACKEREL, ItemFactory.COOKED_MACKEREL, 0.35F);

                SimpleCookingRecipeBuilder
                        .smelting(Ingredient.of(ItemFactory.MACKEREL), RecipeCategory.FOOD, ItemFactory.COOKED_MACKEREL,
                                0.35F, 200)
                        .unlockedBy("has_mackerel", has(ItemFactory.MACKEREL))
                        .save(output);

                // Bass

                simpleCookingRecipe("smoker", RecipeSerializer.SMOKING_RECIPE, SmokingRecipe::new, 100,
                        ItemFactory.BASS, ItemFactory.COOKED_BASS, 0.35F);
                simpleCookingRecipe("campfire_cooking", RecipeSerializer.CAMPFIRE_COOKING_RECIPE,
                        CampfireCookingRecipe::new, 600, ItemFactory.BASS, ItemFactory.COOKED_BASS, 0.35F);

                SimpleCookingRecipeBuilder
                        .smelting(Ingredient.of(ItemFactory.BASS), RecipeCategory.FOOD, ItemFactory.COOKED_BASS, 0.35F,
                                200)
                        .unlockedBy("has_bass", has(ItemFactory.BASS))
                        .save(output);

                // Perch

                simpleCookingRecipe("smoker", RecipeSerializer.SMOKING_RECIPE, SmokingRecipe::new, 100,
                        ItemFactory.PERCH, ItemFactory.COOKED_PERCH, 0.35F);
                simpleCookingRecipe("campfire_cooking", RecipeSerializer.CAMPFIRE_COOKING_RECIPE,
                        CampfireCookingRecipe::new, 600, ItemFactory.PERCH, ItemFactory.COOKED_PERCH, 0.35F);

                SimpleCookingRecipeBuilder
                        .smelting(Ingredient.of(ItemFactory.PERCH), RecipeCategory.FOOD, ItemFactory.COOKED_PERCH,
                                0.35F, 200)
                        .unlockedBy("has_perch", has(ItemFactory.PERCH))
                        .save(output);

                // Snapper

                simpleCookingRecipe("smoker", RecipeSerializer.SMOKING_RECIPE, SmokingRecipe::new, 100,
                        ItemFactory.SNAPPER, ItemFactory.COOKED_SNAPPER, 0.35F);
                simpleCookingRecipe("campfire_cooking", RecipeSerializer.CAMPFIRE_COOKING_RECIPE,
                        CampfireCookingRecipe::new, 600, ItemFactory.SNAPPER, ItemFactory.COOKED_SNAPPER, 0.35F);

                SimpleCookingRecipeBuilder
                        .smelting(Ingredient.of(ItemFactory.SNAPPER), RecipeCategory.FOOD, ItemFactory.COOKED_SNAPPER,
                                0.35F, 200)
                        .unlockedBy("has_snapper", has(ItemFactory.SNAPPER))
                        .save(output);

                // MahiMahi

                simpleCookingRecipe("smoker", RecipeSerializer.SMOKING_RECIPE, SmokingRecipe::new, 100,
                        ItemFactory.MAHIMAHI, ItemFactory.COOKED_MAHIMAHI, 0.35F);
                simpleCookingRecipe("campfire_cooking", RecipeSerializer.CAMPFIRE_COOKING_RECIPE,
                        CampfireCookingRecipe::new, 600, ItemFactory.MAHIMAHI, ItemFactory.COOKED_MAHIMAHI, 0.35F);

                SimpleCookingRecipeBuilder
                        .smelting(Ingredient.of(ItemFactory.MAHIMAHI), RecipeCategory.FOOD, ItemFactory.COOKED_MAHIMAHI,
                                0.35F, 200)
                        .unlockedBy("has_mahimahi", has(ItemFactory.MAHIMAHI))
                        .save(output);

                // # Weapon: Hammer
                createHammerUpdated(Items.STICK, "wooden" + "_hammer", ItemTags.PLANKS);
                createHammerUpdated(Items.COBBLESTONE, "stone" + "_hammer", ItemTags.STONE_TOOL_MATERIALS);
                createHammerUpdated(Items.COPPER_INGOT, "copper" + "_hammer");
                createHammerUpdated(Items.IRON_INGOT, "iron" + "_hammer");
                createHammerUpdated(Items.GOLD_INGOT, "golden" + "_hammer");
                createHammerUpdated(Items.DIAMOND, "diamond" + "_hammer");
                netheriteSmithing(ItemFactory.callItem("diamond_hammer"), RecipeCategory.COMBAT,
                        ItemFactory.callItem("netherite_hammer"));


                // # Glowing Silk Wool

                shaped(RecipeCategory.MISC, ItemFactory.GLOWING_SILK)
                    .define('#', ItemFactory.callItem("silkworm_vines"))
                    .pattern("##")
                    .pattern("##")
                    .unlockedBy(getHasName(ItemFactory.callItem("silkworm_vines")), has(ItemFactory.callItem("silkworm_vines")))
                .save(output);

                shaped(RecipeCategory.BUILDING_BLOCKS, BlockFactory.callBlock("glowing_silk_wool_light_blue").asItem())
                    .define('#', ItemFactory.GLOWING_SILK)
                    .pattern("##")
                    .pattern("##")
                    .unlockedBy(getHasName(ItemFactory.GLOWING_SILK), has(ItemFactory.GLOWING_SILK))
                .save(output);

                int i = 0;
                for (String color : BlockFactory.RECIPES_COLORS) {
                    ItemLike WOOL = ItemFactory.callItem("glowing_silk_wool_" + color);
                    this.carpet(ItemFactory.callItem("glowing_silk_wool_" + color + "_carpet"), WOOL);
                    this.bedFromPlanksAndWool(list_bed.get(i), WOOL);
                    this.banner(list_banner.get(i), WOOL);
                    this.harness(list_harness.get(i), MARSHMELLOW_RAW);
                    i++;
                }

                this.colorItemWithDye(list_dye, list_glowing_silk, "glowing_silk_wool", RecipeCategory.BUILDING_BLOCKS);
                this.colorItemWithDye(list_dye, list_glowing_silk_carpet, "glowing_silk_wool_carpet", RecipeCategory.BUILDING_BLOCKS);



    

                
















            }

            public void createGlassDoorRecipe(Block GLASSDOOR, Block DOOR, Item UNLOCK_Item, Boolean wooden) {
                if (wooden) {
                    shapeless(RecipeCategory.REDSTONE, GLASSDOOR, 1)
                            .requires(DOOR, 1)
                            .requires(Blocks.GLASS_PANE, 1)
                            .group("wooden_glass_door")
                            .unlockedBy(getHasName(DOOR.asItem()), has(DOOR.asItem()))
                            .save(output);
                } else {
                    shapeless(RecipeCategory.REDSTONE, GLASSDOOR, 1)
                            .requires(DOOR, 1)
                            .requires(Blocks.GLASS_PANE, 1)
                            .group("glass_door")
                            .unlockedBy(getHasName(DOOR.asItem()), has(DOOR.asItem()))
                            .save(output);
                }

            }

            public void createGlassTrapDoorRecipe(Block GLASSTRAPDOOR, Block TRAPDOOR, Item UNLOCK_Item,
                    Boolean wooden) {
                if (wooden) {
                    shapeless(RecipeCategory.REDSTONE, GLASSTRAPDOOR, 1)
                            .requires(TRAPDOOR, 1)
                            .requires(Blocks.GLASS_PANE, 1)
                            .group("wooden_glass_trapdoor")
                            .unlockedBy(getHasName(TRAPDOOR.asItem()), has(TRAPDOOR.asItem()))
                            .save(output);
                } else {
                    shapeless(RecipeCategory.REDSTONE, GLASSTRAPDOOR, 1)
                            .requires(TRAPDOOR, 1)
                            .requires(Blocks.GLASS_PANE, 1)
                            .group("glass_trapdoor")
                            .unlockedBy(getHasName(TRAPDOOR.asItem()), has(TRAPDOOR.asItem()))
                            .save(output);
                }

            }

            public void createHammerUpdated(Item UNLOCK, String MainItem, TagKey<Item> ITEMTAGS) {
                ItemLike result = ItemFactory.callItem(MainItem).asItem();
                shaped(RecipeCategory.COMBAT, result)
                        .define('#', ITEMTAGS)
                        .define('S', Items.STICK)
                        .pattern("###")
                        .pattern("###")
                        .pattern(" S ")
                        .group("combat_hammer")
                        .unlockedBy(getHasName(UNLOCK), has(ITEMTAGS))
                        .save(output);
            }

            public void createHammerUpdated(Item MATERIAL, String MainItem) {
                ItemLike result = ItemFactory.callItem(MainItem).asItem();
                shaped(RecipeCategory.COMBAT, result)
                        .define('#', MATERIAL)
                        .define('S', Items.STICK)
                        .pattern("###")
                        .pattern("###")
                        .pattern(" S ")
                        .group("combat_hammer")
                        .unlockedBy(getHasName(MATERIAL), has(MATERIAL))
                        .save(output);

                if (MATERIAL.equals(Items.IRON_INGOT)) {
                    smeltToIron(result.asItem(), MainItem);
                } else if (MATERIAL.equals(Items.GOLD_INGOT)) {
                    smeltToGold(result.asItem(), MainItem);
                } else if (MATERIAL.equals(Items.COPPER_INGOT)) {
                    smeltToCopper(result.asItem(), MainItem);
                }
            }

            public void smeltToIron(Item SmeltIron, String Name) {
                SimpleCookingRecipeBuilder.smelting(
                        Ingredient.of(SmeltIron),
                        RecipeCategory.MISC,
                        Items.IRON_NUGGET,
                        0.1F,
                        200)
                        .unlockedBy(getHasName(SmeltIron), has(SmeltIron))
                        .save(output, "copper_ingot_from_smelting_" + Name);
            }

            public void smeltToGold(Item SmeltGold, String Name) {
                SimpleCookingRecipeBuilder.smelting(
                        Ingredient.of(SmeltGold),
                        RecipeCategory.MISC,
                        Items.GOLD_NUGGET,
                        0.1F,
                        200)
                        .unlockedBy(getHasName(SmeltGold), has(SmeltGold))
                        .save(output, "copper_ingot_from_smelting_" + Name);
            }

            public void smeltToCopper(Item SmeltCopper, String Name) {
                SimpleCookingRecipeBuilder.smelting(
                        Ingredient.of(SmeltCopper),
                        RecipeCategory.MISC,
                        Items.COPPER_INGOT,
                        0.1F,
                        200)
                        .unlockedBy(getHasName(SmeltCopper), has(SmeltCopper))
                        .save(output, "copper_ingot_from_smelting_" + Name);
            }

            public void generateMosaic(String FamilyBase, Block Blocks, Block BlockSlab) {

                mosaicBuilder(RecipeCategory.DECORATIONS, BlockFactory.callBlock(FamilyBase + "_mosaic"), BlockSlab);

                stairBuilder(BlockFactory.callBlock(FamilyBase + "_mosaic_stairs").asItem(), Ingredient.of(Blocks))
                        .group("wooden_stairs")
                        .unlockedBy(getHasName(BlockFactory.callBlock(FamilyBase + "_mosaic")),
                                has(BlockFactory.callBlock(FamilyBase + "_mosaic")))
                        .save(output);

                slabBuilder(RecipeCategory.BUILDING_BLOCKS,
                        BlockFactory.callBlock(FamilyBase + "_mosaic_slab").asItem(), Ingredient.of(Blocks))
                        .group("wooden_slab")
                        .unlockedBy(getHasName(BlockFactory.callBlock(FamilyBase + "_mosaic")),
                                has(BlockFactory.callBlock(FamilyBase + "_mosaic")))
                        .save(output);

            }

            // Updated: //////////////////////////////////

            public void groupWoodFamilty(String MainName, String Varient, Boolean planksWithLogs) {
                // Planks
                Item PLANKS = BlockFactory.callBlock(MainName + "_planks" + Varient).asItem();
                Ingredient PLANKS_I = Ingredient.of(PLANKS);

                if (planksWithLogs) {
                    Block LOG = BlockFactory.callBlock(MainName + "_log");
                    shapeless(RecipeCategory.BUILDING_BLOCKS, PLANKS, 4)
                            .requires(LOG.asItem())
                            .group("planks")
                            .unlockedBy(getHasName(LOG), has(LOG))
                            .save(output);
                }

                stairBuilder(BlockFactory.callBlock(MainName + "_stairs" + Varient).asItem(), PLANKS_I)
                        .group("wooden_stairs")
                        .unlockedBy(getHasName(PLANKS), has(PLANKS))
                        .save(output);

                slabBuilder(RecipeCategory.BUILDING_BLOCKS,
                        BlockFactory.callBlock(MainName + "_slab" + Varient).asItem(), PLANKS_I)
                        .group("wooden_slab")
                        .unlockedBy(getHasName(PLANKS), has(PLANKS))
                        .save(output);

                fenceBuilder(BlockFactory.callBlock(MainName + "_fence" + Varient).asItem(), PLANKS_I)
                        .group("wooden_fence")
                        .unlockedBy(getHasName(PLANKS), has(PLANKS))
                        .save(output);

                fenceGateBuilder(BlockFactory.callBlock(MainName + "_fence_gate" + Varient).asItem(), PLANKS_I)
                        .group("wooden_fence_gate")
                        .unlockedBy(getHasName(PLANKS), has(PLANKS))
                        .save(output);

                doorBuilder(BlockFactory.callBlock(MainName + "_door" + Varient).asItem(), PLANKS_I)
                        .group("wooden_door")
                        .unlockedBy(getHasName(PLANKS), has(PLANKS))
                        .save(output);

                shapeless(RecipeCategory.REDSTONE, BlockFactory.callBlock(MainName + "_glass_door" + Varient).asItem(),
                        1)
                        .requires(BlockFactory.callBlock(MainName + "_door" + Varient).asItem(), 1)
                        .requires(Blocks.GLASS_PANE, 1)
                        .group("wooden_glass_door")
                        .unlockedBy(getHasName(BlockFactory.callBlock(MainName + "_door" + Varient).asItem()),
                                has(BlockFactory.callBlock(MainName + "_door" + Varient).asItem()))
                        .save(output);

                trapdoorBuilder(BlockFactory.callBlock(MainName + "_trapdoor" + Varient).asItem(), PLANKS_I)
                        .group("wooden_trapdoor")
                        .unlockedBy(getHasName(PLANKS), has(PLANKS))
                        .save(output);

                shapeless(RecipeCategory.REDSTONE,
                        BlockFactory.callBlock(MainName + "_glass_trapdoor" + Varient).asItem(), 1)
                        .requires(BlockFactory.callBlock(MainName + "_trapdoor" + Varient).asItem(), 1)
                        .requires(Blocks.GLASS_PANE, 1)
                        .group("wooden_glass_trapdoor")
                        .unlockedBy(getHasName(BlockFactory.callBlock(MainName + "_trapdoor" + Varient).asItem()),
                                has(BlockFactory.callBlock(MainName + "_trapdoor" + Varient).asItem()))
                        .save(output);

                pressurePlateBuilder(RecipeCategory.BUILDING_BLOCKS,
                        BlockFactory.callBlock(MainName + "_pressure_plate" + Varient).asItem(), PLANKS_I)
                        .group("wooden_pressure_plate")
                        .unlockedBy(getHasName(PLANKS), has(PLANKS))
                        .save(output);

                buttonBuilder(BlockFactory.callBlock(MainName + "_button" + Varient).asItem(), PLANKS_I)
                        .group("wooden_button")
                        .unlockedBy(getHasName(PLANKS), has(PLANKS))
                        .save(output);

                MosicFamily(MainName, Varient);

            }

            public void MosicFamily(String MainName, String Varient) {
                Item MOSIC = BlockFactory.callBlock(MainName + "_mosaic" + Varient).asItem();
                Ingredient MOSIC_I = Ingredient.of(MOSIC);

                // mosaicBuilder(RecipeCategory.DECORATIONS, BlockFactory.callBlock(MainName +
                // "_mosaic" + Varient), BlockFactory.callBlock(MainName + "_slab" + Varient));
                this.shaped(RecipeCategory.DECORATIONS, BlockFactory.callBlock(MainName + "_mosaic" + Varient))
                        .define('#', BlockFactory.callBlock(MainName + "_slab" + Varient))
                        .pattern("#")
                        .pattern("#")
                        .unlockedBy(getHasName(BlockFactory.callBlock(MainName + "_slab" + Varient)),
                                this.has(BlockFactory.callBlock(MainName + "_slab" + Varient)))
                        .group("wooden_mosaic")
                        .save(this.output);

                stairBuilder(BlockFactory.callBlock(MainName + "_mosaic_stairs" + Varient).asItem(), MOSIC_I)
                        .group("wooden_mosaic_stairs")
                        .unlockedBy(getHasName(BlockFactory.callBlock(MainName + "_mosaic" + Varient)),
                                has(BlockFactory.callBlock(MainName + "_mosaic" + Varient)))
                        .save(output);

                slabBuilder(RecipeCategory.BUILDING_BLOCKS,
                        BlockFactory.callBlock(MainName + "_mosaic_slab" + Varient).asItem(), MOSIC_I)
                        .group("wooden_mosaic_slab")
                        .unlockedBy(getHasName(BlockFactory.callBlock(MainName + "_mosaic" + Varient)),
                                has(BlockFactory.callBlock(MainName + "_mosaic" + Varient)))
                        .save(output);
            }

            public void groupFungalPlank(String Color) {
                Item result = BlockFactory.callBlock("fungal_planks_" + Color).asItem();
                Item MATERIAL = BlockFactory.callBlock("fungal_planks").asItem();
                Item DYE = BlockFactory.getDye(Color).asItem();

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
            }

            public void groupFromScratchStone(String MainBlock) {

                Block STONE = BlockFactory.callBlock(MainBlock);
                Block POLISHED_STONE = BlockFactory.callBlock("polished_" + MainBlock);
                Block POLISHED_STONE_BRICKS = BlockFactory.callBlock("polished_" + MainBlock + "_bricks");

                // Limestone (Stonecutter)

                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS,
                        BlockFactory.callBlock(MainBlock + "_slab").asItem(), STONE.asItem(), 2);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS,
                        BlockFactory.callBlock(MainBlock + "_stairs").asItem(), STONE.asItem());
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS,
                        BlockFactory.callBlock(MainBlock + "_wall").asItem(), STONE.asItem());

                // Limestone (Crafting Table)

                slabBuilder(RecipeCategory.BUILDING_BLOCKS, BlockFactory.callBlock(MainBlock + "_slab").asItem(),
                        Ingredient.of(STONE.asItem()))
                        .unlockedBy(getHasName(STONE.asItem()), has(STONE.asItem()))
                        .save(output);

                stairBuilder(BlockFactory.callBlock(MainBlock + "_stairs").asItem(),
                        Ingredient.of(STONE.asItem()))
                        .unlockedBy(getHasName(STONE.asItem()), has(STONE.asItem()))
                        .save(output);

                wall(RecipeCategory.DECORATIONS, BlockFactory.callBlock(MainBlock + "_wall").asItem(),
                        STONE.asItem());

                // Polished Limestone (Stonecutter)

                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS,
                        BlockFactory.callBlock("polished_" + MainBlock).asItem(), STONE);

                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS,
                        BlockFactory.callBlock("polished_" + MainBlock + "_slab").asItem(), STONE.asItem(), 2);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS,
                        BlockFactory.callBlock("polished_" + MainBlock + "_slab").asItem(), POLISHED_STONE.asItem(),
                        2);

                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS,
                        BlockFactory.callBlock("polished_" + MainBlock + "_stairs").asItem(), STONE.asItem());
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS,
                        BlockFactory.callBlock("polished_" + MainBlock + "_stairs").asItem(), POLISHED_STONE.asItem());

                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS,
                        BlockFactory.callBlock("polished_" + MainBlock + "_wall").asItem(), STONE.asItem());
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS,
                        BlockFactory.callBlock("polished_" + MainBlock + "_wall").asItem(), POLISHED_STONE.asItem());

                // Polished Limestone (Crafting Table)

                slabBuilder(RecipeCategory.BUILDING_BLOCKS,
                        BlockFactory.callBlock("polished_" + MainBlock + "_slab").asItem(),
                        Ingredient.of(STONE.asItem()))
                        .unlockedBy(getHasName(STONE.asItem()), has(STONE.asItem()))
                        .save(output);

                stairBuilder(BlockFactory.callBlock("polished_" + MainBlock + "_stairs").asItem(),
                        Ingredient.of(STONE.asItem()))
                        .unlockedBy(getHasName(STONE.asItem()), has(STONE.asItem()))
                        .save(output);

                wall(RecipeCategory.DECORATIONS, BlockFactory.callBlock("polished_" + MainBlock + "_wall").asItem(),
                        STONE.asItem());

                // Limestone Bricks

                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, POLISHED_STONE_BRICKS.asItem(),
                        POLISHED_STONE.asItem());
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, POLISHED_STONE_BRICKS.asItem(),
                        STONE.asItem());

                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS,
                        BlockFactory.callBlock("polished_" + MainBlock + "_brick_slab").asItem(),
                        POLISHED_STONE.asItem(), 2);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS,
                        BlockFactory.callBlock("polished_" + MainBlock + "_brick_slab").asItem(),
                        POLISHED_STONE_BRICKS.asItem(), 2);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS,
                        BlockFactory.callBlock("polished_" + MainBlock + "_brick_slab").asItem(), STONE.asItem(), 2);

                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS,
                        BlockFactory.callBlock("polished_" + MainBlock + "_brick_stairs").asItem(),
                        POLISHED_STONE.asItem());
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS,
                        BlockFactory.callBlock("polished_" + MainBlock + "_brick_stairs").asItem(),
                        POLISHED_STONE_BRICKS.asItem());
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS,
                        BlockFactory.callBlock("polished_" + MainBlock + "_brick_stairs").asItem(), STONE.asItem());

                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS,
                        BlockFactory.callBlock("polished_" + MainBlock + "_brick_wall").asItem(),
                        POLISHED_STONE.asItem());
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS,
                        BlockFactory.callBlock("polished_" + MainBlock + "_brick_wall").asItem(),
                        POLISHED_STONE_BRICKS.asItem());
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS,
                        BlockFactory.callBlock("polished_" + MainBlock + "_brick_wall").asItem(), STONE.asItem());

                /*
                 * //TODO: Add Cracked Bricks
                 * CookingRecipeJsonBuilder.createSmelting(
                 * Ingredient.ofItems(POLISHED_STONE_BRICKS.asItem()),
                 * RecipeCategory.BUILDING_BLOCKS,
                 * BlockFactoryUpt.callBlock("cracked_polished_limestone_brick").asItem(), 0.1F,
                 * 200
                 * )
                 * .criterion(hasItem(POLISHED_STONE_BRICKS.asItem().asItem()),
                 * conditionsFromItem(POLISHED_STONE_BRICKS.asItem().asItem()))
                 * .offerTo(exporter, "smelted_" + "cracked_polished_limestone_brick");
                 */

                chiseledBuilder(RecipeCategory.BUILDING_BLOCKS,
                        BlockFactory.callBlock("polished_" + MainBlock + "_brick_chiseled"),
                        Ingredient.of(BlockFactory.callBlock("polished_" + MainBlock + "_brick_slab")))
                        .unlockedBy(getHasName(POLISHED_STONE_BRICKS.asItem().asItem()),
                                has(POLISHED_STONE_BRICKS.asItem()))
                        .save(output);

            }

            public void createStoneVarient() {

                Block ANDERSITE_BRICKS = BlockFactory.callBlock("andesite_bricks");
                Block DIORITE_BRICKS = BlockFactory.callBlock("diorite_bricks");
                Block GRANITE_BRICKS = BlockFactory.callBlock("granite_bricks");

                // Andesite
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, ANDERSITE_BRICKS.asItem(), Blocks.ANDESITE);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, ANDERSITE_BRICKS.asItem(),
                        Blocks.POLISHED_ANDESITE);

                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS,
                        BlockFactory.callBlock("andesite_brick_slab").asItem(), ANDERSITE_BRICKS.asItem(), 2);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS,
                        BlockFactory.callBlock("andesite_brick_slab").asItem(), Blocks.ANDESITE, 2);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS,
                        BlockFactory.callBlock("andesite_brick_slab").asItem(), Blocks.POLISHED_ANDESITE, 2);

                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS,
                        BlockFactory.callBlock("andesite_brick_stairs").asItem(), ANDERSITE_BRICKS.asItem());
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS,
                        BlockFactory.callBlock("andesite_brick_stairs").asItem(), Blocks.ANDESITE);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS,
                        BlockFactory.callBlock("andesite_brick_stairs").asItem(), Blocks.POLISHED_ANDESITE);

                stonecutterResultFromBase(RecipeCategory.DECORATIONS,
                        BlockFactory.callBlock("andesite_brick_wall").asItem(), ANDERSITE_BRICKS.asItem());
                stonecutterResultFromBase(RecipeCategory.DECORATIONS,
                        BlockFactory.callBlock("andesite_brick_wall").asItem(), Blocks.ANDESITE);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS,
                        BlockFactory.callBlock("andesite_brick_wall").asItem(), Blocks.POLISHED_ANDESITE);

                stonecutterResultFromBase(RecipeCategory.DECORATIONS,
                        BlockFactory.callBlock("andesite_brick_chiseled").asItem(), ANDERSITE_BRICKS.asItem());
                stonecutterResultFromBase(RecipeCategory.DECORATIONS,
                        BlockFactory.callBlock("andesite_brick_chiseled").asItem(), Blocks.ANDESITE);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS,
                        BlockFactory.callBlock("andesite_brick_chiseled").asItem(), Blocks.POLISHED_ANDESITE);

                slabBuilder(RecipeCategory.BUILDING_BLOCKS, BlockFactory.callBlock("andesite_brick_slab").asItem(),
                        Ingredient.of(ANDERSITE_BRICKS.asItem()))
                        .unlockedBy(getHasName(ANDERSITE_BRICKS.asItem()), has(ANDERSITE_BRICKS.asItem()))
                        .save(output);
                stairBuilder(BlockFactory.callBlock("andesite_brick_stairs").asItem(),
                        Ingredient.of(ANDERSITE_BRICKS.asItem()))
                        .unlockedBy(getHasName(ANDERSITE_BRICKS.asItem()), has(ANDERSITE_BRICKS.asItem()))
                        .save(output);
                wall(RecipeCategory.DECORATIONS, BlockFactory.callBlock("andesite_brick_wall").asItem(),
                        ANDERSITE_BRICKS.asItem());

                SimpleCookingRecipeBuilder.smelting(
                        Ingredient.of(ANDERSITE_BRICKS), RecipeCategory.BUILDING_BLOCKS,
                        BlockFactory.callBlock("cracked_andesite_bricks").asItem(), 0.1F, 200)
                        .unlockedBy(getHasName(ANDERSITE_BRICKS.asItem()), has(ANDERSITE_BRICKS.asItem()))
                        .save(output, "smelted_" + "cracked_andesite_bricks");

                chiseledBuilder(RecipeCategory.BUILDING_BLOCKS, BlockFactory.callBlock("andesite_brick_chiseled"),
                        Ingredient.of(BlockFactory.callBlock("andesite_brick_slab")))
                        .unlockedBy(getHasName(ANDERSITE_BRICKS.asItem()), has(ANDERSITE_BRICKS))
                        .save(output);

                // Granite
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, GRANITE_BRICKS.asItem(), Blocks.GRANITE);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, GRANITE_BRICKS.asItem(),
                        Blocks.POLISHED_GRANITE);

                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS,
                        BlockFactory.callBlock("granite_brick_slab").asItem(), GRANITE_BRICKS.asItem(), 2);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS,
                        BlockFactory.callBlock("granite_brick_slab").asItem(), Blocks.GRANITE, 2);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS,
                        BlockFactory.callBlock("granite_brick_slab").asItem(), Blocks.POLISHED_GRANITE, 2);

                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS,
                        BlockFactory.callBlock("granite_brick_stairs").asItem(), GRANITE_BRICKS.asItem());
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS,
                        BlockFactory.callBlock("granite_brick_stairs").asItem(), Blocks.GRANITE);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS,
                        BlockFactory.callBlock("granite_brick_stairs").asItem(), Blocks.POLISHED_GRANITE);

                stonecutterResultFromBase(RecipeCategory.DECORATIONS,
                        BlockFactory.callBlock("granite_brick_wall").asItem(), GRANITE_BRICKS.asItem());
                stonecutterResultFromBase(RecipeCategory.DECORATIONS,
                        BlockFactory.callBlock("granite_brick_wall").asItem(), Blocks.GRANITE);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS,
                        BlockFactory.callBlock("granite_brick_wall").asItem(), Blocks.POLISHED_GRANITE);

                stonecutterResultFromBase(RecipeCategory.DECORATIONS,
                        BlockFactory.callBlock("granite_brick_chiseled").asItem(), GRANITE_BRICKS.asItem());
                stonecutterResultFromBase(RecipeCategory.DECORATIONS,
                        BlockFactory.callBlock("granite_brick_chiseled").asItem(), Blocks.GRANITE);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS,
                        BlockFactory.callBlock("granite_brick_chiseled").asItem(), Blocks.POLISHED_GRANITE);

                slabBuilder(RecipeCategory.BUILDING_BLOCKS, BlockFactory.callBlock("granite_brick_slab").asItem(),
                        Ingredient.of(GRANITE_BRICKS.asItem()))
                        .unlockedBy(getHasName(GRANITE_BRICKS.asItem()), has(GRANITE_BRICKS.asItem()))
                        .save(output);
                stairBuilder(BlockFactory.callBlock("granite_brick_stairs").asItem(),
                        Ingredient.of(GRANITE_BRICKS.asItem()))
                        .unlockedBy(getHasName(GRANITE_BRICKS.asItem()), has(GRANITE_BRICKS.asItem()))
                        .save(output);
                wall(RecipeCategory.DECORATIONS, BlockFactory.callBlock("granite_brick_wall").asItem(),
                        GRANITE_BRICKS.asItem());

                SimpleCookingRecipeBuilder.smelting(
                        Ingredient.of(GRANITE_BRICKS), RecipeCategory.BUILDING_BLOCKS,
                        BlockFactory.callBlock("cracked_granite_bricks").asItem(), 0.1F, 200)
                        .unlockedBy(getHasName(GRANITE_BRICKS.asItem()), has(GRANITE_BRICKS.asItem()))
                        .save(output, "smelted_" + "cracked_granite_bricks");

                chiseledBuilder(RecipeCategory.BUILDING_BLOCKS, BlockFactory.callBlock("granite_brick_chiseled"),
                        Ingredient.of(BlockFactory.callBlock("granite_brick_slab")))
                        .unlockedBy(getHasName(GRANITE_BRICKS.asItem()), has(GRANITE_BRICKS))
                        .save(output);

                // Diorite
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, DIORITE_BRICKS.asItem(), Blocks.DIORITE);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, DIORITE_BRICKS.asItem(),
                        Blocks.POLISHED_DIORITE);

                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS,
                        BlockFactory.callBlock("diorite_brick_slab").asItem(), DIORITE_BRICKS.asItem(), 2);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS,
                        BlockFactory.callBlock("diorite_brick_slab").asItem(), Blocks.DIORITE, 2);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS,
                        BlockFactory.callBlock("diorite_brick_slab").asItem(), Blocks.POLISHED_DIORITE, 2);

                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS,
                        BlockFactory.callBlock("diorite_brick_stairs").asItem(), DIORITE_BRICKS.asItem());
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS,
                        BlockFactory.callBlock("diorite_brick_stairs").asItem(), Blocks.DIORITE);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS,
                        BlockFactory.callBlock("diorite_brick_stairs").asItem(), Blocks.POLISHED_DIORITE);

                stonecutterResultFromBase(RecipeCategory.DECORATIONS,
                        BlockFactory.callBlock("diorite_brick_wall").asItem(), DIORITE_BRICKS.asItem());
                stonecutterResultFromBase(RecipeCategory.DECORATIONS,
                        BlockFactory.callBlock("diorite_brick_wall").asItem(), Blocks.DIORITE);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS,
                        BlockFactory.callBlock("diorite_brick_wall").asItem(), Blocks.POLISHED_DIORITE);

                stonecutterResultFromBase(RecipeCategory.DECORATIONS,
                        BlockFactory.callBlock("diorite_brick_chiseled").asItem(), DIORITE_BRICKS.asItem());
                stonecutterResultFromBase(RecipeCategory.DECORATIONS,
                        BlockFactory.callBlock("diorite_brick_chiseled").asItem(), Blocks.DIORITE);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS,
                        BlockFactory.callBlock("diorite_brick_chiseled").asItem(), Blocks.POLISHED_DIORITE);

                slabBuilder(RecipeCategory.BUILDING_BLOCKS, BlockFactory.callBlock("diorite_brick_slab").asItem(),
                        Ingredient.of(DIORITE_BRICKS.asItem()))
                        .unlockedBy(getHasName(DIORITE_BRICKS.asItem()), has(DIORITE_BRICKS.asItem()))
                        .save(output);
                stairBuilder(BlockFactory.callBlock("diorite_brick_stairs").asItem(),
                        Ingredient.of(DIORITE_BRICKS.asItem()))
                        .unlockedBy(getHasName(DIORITE_BRICKS.asItem()), has(DIORITE_BRICKS.asItem()))
                        .save(output);
                wall(RecipeCategory.DECORATIONS, BlockFactory.callBlock("diorite_brick_wall").asItem(),
                        DIORITE_BRICKS.asItem());

                SimpleCookingRecipeBuilder.smelting(
                        Ingredient.of(DIORITE_BRICKS), RecipeCategory.BUILDING_BLOCKS,
                        BlockFactory.callBlock("cracked_diorite_bricks").asItem(), 0.1F, 200)
                        .unlockedBy(getHasName(DIORITE_BRICKS.asItem()), has(DIORITE_BRICKS.asItem()))
                        .save(output, "smelted_" + "cracked_diorite_bricks");

                chiseledBuilder(RecipeCategory.BUILDING_BLOCKS, BlockFactory.callBlock("diorite_brick_chiseled"),
                        Ingredient.of(BlockFactory.callBlock("diorite_brick_slab")))
                        .unlockedBy(getHasName(DIORITE_BRICKS.asItem()), has(DIORITE_BRICKS))
                        .save(output);

                wall(RecipeCategory.DECORATIONS, BlockFactory.callBlock("polished_andesite_wall").asItem(),
                        Blocks.POLISHED_ANDESITE.asItem());
                wall(RecipeCategory.DECORATIONS, BlockFactory.callBlock("polished_granite_wall").asItem(),
                        Blocks.POLISHED_GRANITE.asItem());
                wall(RecipeCategory.DECORATIONS, BlockFactory.callBlock("polished_diorite_wall").asItem(),
                        Blocks.POLISHED_DIORITE.asItem());

                stonecutterResultFromBase(RecipeCategory.DECORATIONS,
                        BlockFactory.callBlock("polished_andesite_wall").asItem(), Blocks.POLISHED_ANDESITE);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS,
                        BlockFactory.callBlock("polished_granite_wall").asItem(), Blocks.POLISHED_GRANITE);
                stonecutterResultFromBase(RecipeCategory.DECORATIONS,
                        BlockFactory.callBlock("polished_diorite_wall").asItem(), Blocks.POLISHED_DIORITE);

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
        };
    }

    @Override
    public String getName() {
        return "EntStupidStuff Recipes";
    }

}
