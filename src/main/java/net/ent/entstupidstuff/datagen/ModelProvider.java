package net.ent.entstupidstuff.datagen;

import net.ent.entstupidstuff.block.BlockFactory;
import net.ent.entstupidstuff.item.ItemFactory;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.BlockModelGenerators.BlockFamilyProvider;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.blockstates.MultiPartGenerator;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.blockstates.PropertyDispatch;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TextureSlot;
import net.minecraft.client.data.models.model.TexturedModel;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DripstoneThickness;

public class ModelProvider extends FabricModelProvider{

    public ModelProvider(FabricDataOutput output) {
        super(output);
    }
    
    BlockModelGenerators blockStateModelGenerator;
    ItemModelGenerators itemModelGenerator;
    

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerator2) {
        this.itemModelGenerator = itemModelGenerator2;

        itemModelGenerator.generateFlatItem(ItemFactory.MARSHMELLOW_RAW, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ItemFactory.MARSHMELLOW_TOASTED, ModelTemplates.FLAT_HANDHELD_ITEM);
        //BAGGUETTE is done via Manually

        itemModelGenerator.generateFlatItem(ItemFactory.BUTTERFLY_JAR, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ItemFactory.BUTTERFLY_SPAWN_EGG, ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(ItemFactory.WITHER_BONE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ItemFactory.ANCIENT_DEBRIS_NUGGET, ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(ItemFactory.PIGLIN_WARRIOR_SPAWN, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ItemFactory.BLAZING_INFERNO_SPAWN, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ItemFactory.SOUL_SKELETON_SPAWN, ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(ItemFactory.ZEBRA_FISH_BUCKET, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ItemFactory.ZEBRA_FISH, ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(ItemFactory.ALLIGATOR_GAR_BUCKET, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ItemFactory.ALLIGATOR_GAR, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ItemFactory.COOKED_ALLIGATOR_GAR, ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(ItemFactory.MACKEREL_BUCKET, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ItemFactory.MACKEREL, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ItemFactory.COOKED_MACKEREL, ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(ItemFactory.BASS_BUCKET, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ItemFactory.BASS, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ItemFactory.COOKED_BASS, ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(ItemFactory.FUR_TROUT_BUCKET, ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(ItemFactory.KOI_BUCKET, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ItemFactory.KOI, ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(ItemFactory.PERCH_BUCKET, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ItemFactory.PERCH, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ItemFactory.COOKED_PERCH, ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(ItemFactory.MAHIMAHI_BUCKET, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ItemFactory.MAHIMAHI, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ItemFactory.COOKED_MAHIMAHI, ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(ItemFactory.SNAPPER_BUCKET, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ItemFactory.SNAPPER, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ItemFactory.COOKED_SNAPPER, ModelTemplates.FLAT_ITEM);

        //RUM is done via Manually
        itemModelGenerator.generateFlatItem(ItemFactory.CANNON_BALL_ITEM, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ItemFactory.CANNON_ITEM, ModelTemplates.CROSSBOW);
        itemModelGenerator.generateFlatItem(ItemFactory.PRISMERINE_ARROW, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ItemFactory.FLINTLOCK_CROSSBOW, ModelTemplates.CROSSBOW);
        itemModelGenerator.generateFlatItem(ItemFactory.DOUBLE_BARREL_CROSSBOW, ModelTemplates.CROSSBOW);

        itemModelGenerator.generateFlatItem(ItemFactory.SUNKEN_SKELETON_SPAWN, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ItemFactory.SUNKEN_SKELETON2_SPAWN, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ItemFactory.SKELETON_PIRATE_CAPTAIN_SPAWN, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ItemFactory.METAL_SKELETON_SPAWN, ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(ItemFactory.ANCIENT_DROWN_SPAWN, ModelTemplates.FLAT_ITEM);
        //ANCIENT_TRIDENT is done via Manually

        itemModelGenerator.generateFlatItem(ItemFactory.PHANTOM_SKELETON_SPAWN, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ItemFactory.GLOWING_SILK, ModelTemplates.FLAT_ITEM);

        //itemModelGenerator2.register(ItemFactory.DIAMOND_SHIELD, Models.);

        //ItemModel.Unbaked unbakedShield = ItemModels.basic(itemModelGenerator2.upload(ItemFactory.DIAMOND_SHIELD, Models.GENERATED));
        //ItemModel.Unbaked unbakedBlocking = ItemModels.basic(itemModelGenerator2.registerSubModel(ItemFactory.DIAMOND_SHIELD, "blocking", Models.GENERATED)); //ToFix
        /*itemModelGenerator.output.accept(
        ItemFactory.DIAMOND_SHIELD,
        new ItemAsset(
            new ConditionItemModel.Unbaked(
                new HasComponentProperty(DataComponentTypes.BLOCKS_ATTACKS, true),
                unbakedBlocking,
                unbakedShield
            ),
            new ItemAsset.Properties(false, false)
        ).model()
    );*/


    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockStateModelGenerator2) {

        this.blockStateModelGenerator = blockStateModelGenerator2;

        // # Added REDWOOD Natural + Planks
        generateGroupWoodFamilty("redwood", "", true); System.out.println("ModProvide: " + "Redwood");

        // # Added FIR Natural + Planks
        generateGroupWoodFamilty("fir", "", true);
        blockStateModelGenerator2.createPlant(BlockFactory.callBlock("fir_sapling"), BlockFactory.callBlock("potted_fir_sapling"), BlockModelGenerators.PlantType.NOT_TINTED); //TintType.NOT_TINTED

        // # Added MAPLE Natural + Planks
        generateGroupWoodFamilty("maple", "", true);
        blockStateModelGenerator2.createPlant(BlockFactory.callBlock("maple_sapling"), BlockFactory.callBlock("potted_maple_sapling"), BlockModelGenerators.PlantType.NOT_TINTED);

        // # Added PHANTOM Natural + Planks + Lantern
        generateGroupWoodFamilty("phantom", "", true); System.out.println("ModProvide: " + "Phantom");
        blockStateModelGenerator.createLantern(BlockFactory.callBlock("phantom_lantern"));
        blockStateModelGenerator.createNormalTorch(BlockFactory.callBlock("phantom_torch"), BlockFactory.callBlock("phantom_wall_torch"));

        // # Added Fungal Natural + Planks (Regular + Colored)
        generateGroupFungalFamilty("fungal", "");
        generateGroupFungalFamilty("fungal", "white");
        generateGroupFungalFamilty("fungal", "light_gray");
        generateGroupFungalFamilty("fungal", "gray");
        generateGroupFungalFamilty("fungal", "black");
        generateGroupFungalFamilty("fungal", "brown");
        generateGroupFungalFamilty("fungal", "red");
        generateGroupFungalFamilty("fungal", "orange");
        generateGroupFungalFamilty("fungal", "yellow");
        generateGroupFungalFamilty("fungal", "lime");
        generateGroupFungalFamilty("fungal", "green");
        generateGroupFungalFamilty("fungal", "cyan");
        generateGroupFungalFamilty("fungal", "light_blue");
        generateGroupFungalFamilty("fungal", "blue");
        generateGroupFungalFamilty("fungal", "purple");
        generateGroupFungalFamilty("fungal", "magenta");
        generateGroupFungalFamilty("fungal", "pink");

        // # Added Blue Mushroom Family
        this.registerMushroomBlockCustom(BlockFactory.callBlock("blue_mushroom_block"));
        this.registerMudBottomCustomTop(BlockFactory.callBlock("shroomium"));

        blockStateModelGenerator.createFlowerBed(BlockFactory.callBlock("azure_flower_bed"));
        blockStateModelGenerator.createPlantWithDefaultItem(BlockFactory.callBlock("blue_mushroom"), BlockFactory.callBlock("potted_blue_mushroom"), BlockModelGenerators.PlantType.NOT_TINTED);

        blockStateModelGenerator.family(BlockFactory.callBlock("blue_crystal_block"));
        
        // # Adding Andersite, Diorite and Granite
        generateGroupBricksFamilty("andesite", "");
        generateGroupBricksFamilty("diorite", "");
        generateGroupBricksFamilty("granite", "");

        blockStateModelGenerator.family(Blocks.POLISHED_ANDESITE).wall(BlockFactory.callBlock("polished_" + "andesite" + "_wall"));
        blockStateModelGenerator.family(Blocks.POLISHED_DIORITE).wall(BlockFactory.callBlock("polished_" + "diorite" + "_wall"));
        blockStateModelGenerator.family(Blocks.POLISHED_GRANITE).wall(BlockFactory.callBlock("polished_" + "granite" + "_wall"));

        // # Adding Limestone and Limestone Bricks
        generateBaseAndIntercationFamily("limestone", "", false, true, BlockFactory.callBlock("limestone"));
        generateBaseAndIntercationFamily("polished_limestone", "", false, true, BlockFactory.callBlock("polished_limestone"));
        generateGroupBricksFamilty("polished_limestone", "");

        // # Adding IronGates
        generateBaseAndIntercationFamily("iron_grate", "", false, false, BlockFactory.callBlock("iron_grate"));

        // # Adding StringGates
        blockStateModelGenerator.createGlassBlocks(BlockFactory.callBlock("string_block"), BlockFactory.callBlock("string_gate"));

        // # Adding Abyssal Stone and Abyssal Stone Bricks
        generateBaseAndIntercationFamily("abyssal_stone", "", false, true, BlockFactory.callBlock("abyssal_stone"));
        generateBaseAndIntercationFamily("polished_abyssal_stone", "", false, true, BlockFactory.callBlock("polished_abyssal_stone"));  
        generateGroupBricksFamilty("polished_abyssal_stone", "");
        blockStateModelGenerator.createTrivialCube(BlockFactory.callBlock("polished_abyssal_stone_seaweed"));

        // # Vanilla Additions

        generateGroupVanillaAddition("oak");
        generateGroupVanillaAddition("spruce");
        generateGroupVanillaAddition("jungle");
        generateGroupVanillaAddition("birch");
        generateGroupVanillaAddition("dark_oak");
        generateGroupVanillaAddition("acacia");
        generateGroupVanillaAddition("mangrove");
        generateGroupVanillaAddition("cherry");
        generateGroupVanillaAddition("warped");
        generateGroupVanillaAddition("crimson");
        generateGlassIntercation("bamboo", "");
        generateGroupVanillaAddition("pale_oak");

        generateGlassIntercation("iron", ""); 
        generateGlassIntercation("copper", "");
        generateGlassIntercation("exposed_copper", "");
        generateGlassIntercation("oxidized_copper", "");
        generateGlassIntercation("weathered_copper", "");

        registerPointedIce();

        blockStateModelGenerator.registerSimpleItemModel(BlockFactory.SILKWORM_VINES.asItem(), BlockModelGenerators.PlantType.NOT_TINTED.createItemModel(blockStateModelGenerator, BlockFactory.SILKWORM_VINES));
        blockStateModelGenerator.createCrossBlock(BlockFactory.SILKWORM_VINES, BlockModelGenerators.PlantType.NOT_TINTED);
        blockStateModelGenerator.createCrossBlock(BlockFactory.SILKWORM_VINES_PLANT, BlockModelGenerators.PlantType.NOT_TINTED);

        //blockStateModelGenerator.family(BlockFactory.callBlock("glowing_silk_wool_white"));
        //blockStateModelGenerator.family(BlockFactory.callBlock("glowing_silk_wool_light_gray"));
        //blockStateModelGenerator.family(BlockFactory.callBlock("glowing_silk_wool_gray"));
        //blockStateModelGenerator.family(BlockFactory.callBlock("glowing_silk_wool_black"));
        //blockStateModelGenerator.family(BlockFactory.callBlock("glowing_silk_wool_brown"));
        //blockStateModelGenerator.family(BlockFactory.callBlock("glowing_silk_wool_red"));
        //blockStateModelGenerator.family(BlockFactory.callBlock("glowing_silk_wool_orange"));
        //blockStateModelGenerator.family(BlockFactory.callBlock("glowing_silk_wool_yellow"));
        //blockStateModelGenerator.family(BlockFactory.callBlock("glowing_silk_wool_lime"));
        //blockStateModelGenerator.family(BlockFactory.callBlock("glowing_silk_wool_green"));
        //blockStateModelGenerator.family(BlockFactory.callBlock("glowing_silk_wool_cyan"));
        //blockStateModelGenerator.family(BlockFactory.callBlock("glowing_silk_wool_light_blue"));
        //blockStateModelGenerator.family(BlockFactory.callBlock("glowing_silk_wool_blue"));
        //blockStateModelGenerator.family(BlockFactory.callBlock("glowing_silk_wool_purple"));
        //blockStateModelGenerator.family(BlockFactory.callBlock("glowing_silk_wool_magenta"));
        //blockStateModelGenerator.family(BlockFactory.callBlock("glowing_silk_wool_pink"));

        blockStateModelGenerator.createFullAndCarpetBlocks(BlockFactory.callBlock("glowing_silk_wool_white"), BlockFactory.callBlock("glowing_silk_wool_white_carpet"));
        blockStateModelGenerator.createFullAndCarpetBlocks(BlockFactory.callBlock("glowing_silk_wool_light_gray"), BlockFactory.callBlock("glowing_silk_wool_light_gray_carpet"));
        blockStateModelGenerator.createFullAndCarpetBlocks(BlockFactory.callBlock("glowing_silk_wool_gray"), BlockFactory.callBlock("glowing_silk_wool_gray_carpet"));
        blockStateModelGenerator.createFullAndCarpetBlocks(BlockFactory.callBlock("glowing_silk_wool_black"), BlockFactory.callBlock("glowing_silk_wool_black_carpet"));
        blockStateModelGenerator.createFullAndCarpetBlocks(BlockFactory.callBlock("glowing_silk_wool_brown"), BlockFactory.callBlock("glowing_silk_wool_brown_carpet"));
        blockStateModelGenerator.createFullAndCarpetBlocks(BlockFactory.callBlock("glowing_silk_wool_red"), BlockFactory.callBlock("glowing_silk_wool_red_carpet"));
        blockStateModelGenerator.createFullAndCarpetBlocks(BlockFactory.callBlock("glowing_silk_wool_orange"), BlockFactory.callBlock("glowing_silk_wool_orange_carpet"));
        blockStateModelGenerator.createFullAndCarpetBlocks(BlockFactory.callBlock("glowing_silk_wool_yellow"), BlockFactory.callBlock("glowing_silk_wool_yellow_carpet"));
        blockStateModelGenerator.createFullAndCarpetBlocks(BlockFactory.callBlock("glowing_silk_wool_lime"), BlockFactory.callBlock("glowing_silk_wool_lime_carpet"));
        blockStateModelGenerator.createFullAndCarpetBlocks(BlockFactory.callBlock("glowing_silk_wool_green"), BlockFactory.callBlock("glowing_silk_wool_green_carpet"));
        blockStateModelGenerator.createFullAndCarpetBlocks(BlockFactory.callBlock("glowing_silk_wool_cyan"), BlockFactory.callBlock("glowing_silk_wool_cyan_carpet"));
        blockStateModelGenerator.createFullAndCarpetBlocks(BlockFactory.callBlock("glowing_silk_wool_light_blue"), BlockFactory.callBlock("glowing_silk_wool_light_blue_carpet"));
        blockStateModelGenerator.createFullAndCarpetBlocks(BlockFactory.callBlock("glowing_silk_wool_blue"), BlockFactory.callBlock("glowing_silk_wool_blue_carpet"));
        blockStateModelGenerator.createFullAndCarpetBlocks(BlockFactory.callBlock("glowing_silk_wool_purple"), BlockFactory.callBlock("glowing_silk_wool_purple_carpet"));
        blockStateModelGenerator.createFullAndCarpetBlocks(BlockFactory.callBlock("glowing_silk_wool_magenta"), BlockFactory.callBlock("glowing_silk_wool_magenta_carpet"));
        blockStateModelGenerator.createFullAndCarpetBlocks(BlockFactory.callBlock("glowing_silk_wool_pink"), BlockFactory.callBlock("glowing_silk_wool_pink_carpet"));


        

        


	}
 
    public final void registerMudBottomCustomTop(Block block) {
		TextureMapping textureMap = new TextureMapping()
			.put(TextureSlot.BOTTOM, TextureMapping.getBlockTexture(Blocks.MUD))
			.put(TextureSlot.TOP, TextureMapping.getBlockTexture(block))
			.put(TextureSlot.SIDE, TextureMapping.getBlockTexture(block, "_side"));
		blockStateModelGenerator.blockStateOutput
			.accept(BlockModelGenerators.createSimpleBlock(block, BlockModelGenerators.plainVariant(ModelTemplates.CUBE_BOTTOM_TOP.create(block, textureMap, blockStateModelGenerator.modelOutput))));
	}

    public final void registerMushroomBlockCustom(Block mushroomBlock) {
		MultiVariant weightedVariant = BlockModelGenerators.plainVariant(
			ModelTemplates.SINGLE_FACE.create(mushroomBlock, TextureMapping.defaultTexture(mushroomBlock), blockStateModelGenerator.modelOutput)
		);
		MultiVariant weightedVariant2 = BlockModelGenerators.plainVariant(ResourceLocation.fromNamespaceAndPath("entstupidstuff", "block/blue_mushroom_block_inside"));
		blockStateModelGenerator.blockStateOutput
			.accept(
				MultiPartGenerator.multiPart(mushroomBlock)
					.with(BlockModelGenerators.condition().term(BlockStateProperties.NORTH, true), weightedVariant)
					.with(BlockModelGenerators.condition().term(BlockStateProperties.EAST, true), weightedVariant.with(BlockModelGenerators.Y_ROT_90).with(BlockModelGenerators.UV_LOCK))
					.with(BlockModelGenerators.condition().term(BlockStateProperties.SOUTH, true), weightedVariant.with(BlockModelGenerators.Y_ROT_180).with(BlockModelGenerators.UV_LOCK))
					.with(BlockModelGenerators.condition().term(BlockStateProperties.WEST, true), weightedVariant.with(BlockModelGenerators.Y_ROT_270).with(BlockModelGenerators.UV_LOCK))
					.with(BlockModelGenerators.condition().term(BlockStateProperties.UP, true), weightedVariant.with(BlockModelGenerators.X_ROT_270).with(BlockModelGenerators.UV_LOCK))
					.with(BlockModelGenerators.condition().term(BlockStateProperties.DOWN, true), weightedVariant.with(BlockModelGenerators.X_ROT_90).with(BlockModelGenerators.UV_LOCK))
					.with(BlockModelGenerators.condition().term(BlockStateProperties.NORTH, false), weightedVariant2)
					.with(BlockModelGenerators.condition().term(BlockStateProperties.EAST, false), weightedVariant2.with(BlockModelGenerators.Y_ROT_90))
					.with(BlockModelGenerators.condition().term(BlockStateProperties.SOUTH, false), weightedVariant2.with(BlockModelGenerators.Y_ROT_180))
					.with(BlockModelGenerators.condition().term(BlockStateProperties.WEST, false), weightedVariant2.with(BlockModelGenerators.Y_ROT_270))
					.with(BlockModelGenerators.condition().term(BlockStateProperties.UP, false), weightedVariant2.with(BlockModelGenerators.X_ROT_270))
					.with(BlockModelGenerators.condition().term(BlockStateProperties.DOWN, false), weightedVariant2.with(BlockModelGenerators.X_ROT_90))
			);
		blockStateModelGenerator.registerSimpleItemModel(mushroomBlock, TexturedModel.CUBE.createWithSuffix(mushroomBlock, "_inventory", blockStateModelGenerator.modelOutput));
	}

    

    /////

    public void generateGroupWoodFamilty(String blockName, String suffix, Boolean withLeaves) {
        if (suffix == null) {suffix = "";}
        else if (suffix == "") {suffix = "";}
        else if (suffix == "_") {suffix = "";}
        else {suffix = "_" + suffix;}

        Block MainTexture = BlockFactory.callBlock(blockName + "_planks" + suffix);
        Block MosicTexture = BlockFactory.callBlock(blockName + "_mosaic" + suffix);
        generateBaseAndIntercationFamily(blockName, suffix, true, false, MainTexture);
        generateBaseAndIntercationFamily(blockName + "_mosaic", suffix, false, false, MosicTexture);
        generateNatureFamily(blockName, suffix, withLeaves);

    }

    public void generateGroupFungalFamilty(String blockName, String suffix) {
        if (suffix == null) {suffix = "";}
        else if (suffix == "") {suffix = "";}
        else if (suffix == "_") {suffix = "";}
        else {suffix = "_" + suffix;}

        

        Block MainTexture = BlockFactory.callBlock(blockName + "_planks" + suffix);
        Block MosicTexture = BlockFactory.callBlock(blockName + "_mosaic" + suffix);
        generateBaseAndIntercationFamily(blockName, suffix, true, false, MainTexture);
        generateBaseAndIntercationFamily(blockName + "_mosaic", suffix, false, false, MosicTexture);

    }

    public void generateGroupBricksFamilty(String blockName, String suffix) {
        if (suffix == null) {suffix = "";}
        else if (suffix == "") {suffix = "";}
        else if (suffix == "_") {suffix = "";}
        else {suffix = "_" + suffix;}

        Block BricksTexture = BlockFactory.callBlock(blockName + "_bricks" + suffix);
        generateBaseAndIntercationFamily(blockName + "_brick", suffix, false, true, BricksTexture);
        blockStateModelGenerator.createTrivialCube(BlockFactory.callBlock("cracked_" + blockName + "_bricks" + suffix));
        blockStateModelGenerator.createTrivialCube(BlockFactory.callBlock(blockName + "_brick_chiseled" + suffix));
    }

    public void generateGroupVanillaAddition(String blockName) {
        generateBaseAndIntercationFamily(blockName + "_mosaic", "", false, false, BlockFactory.callBlock(blockName + "_mosaic"));
        generateGlassIntercation(blockName, "");
    }

    public void generateNatureFamily(String blockName, String suffix, Boolean withLeaves) {
        if (suffix == null) {suffix = "";}
        else if (suffix == "") {suffix = "";}
        else if (suffix == "_") {suffix = "";}

        if (withLeaves) {
            blockStateModelGenerator.createTrivialBlock(BlockFactory.callBlock(blockName + "_leaves" + suffix), TexturedModel.LEAVES);
            
        }

        blockStateModelGenerator.woodProvider(BlockFactory.callBlock(blockName + "_log" + suffix)).logWithHorizontal(BlockFactory.callBlock(blockName + "_log" + suffix)).wood(BlockFactory.callBlock(blockName + "_wood" + suffix));
        blockStateModelGenerator.woodProvider(BlockFactory.callBlock("stripped_" + blockName + "_log" + suffix)).logWithHorizontal(BlockFactory.callBlock("stripped_" + blockName + "_log" + suffix)).wood(BlockFactory.callBlock("stripped_" + blockName + "_wood" + suffix));
        
    }

    public void generateBaseAndIntercationFamily(String blockName, String suffix, Boolean generateWoodBase, Boolean generateStoneBase, Block MainTexture) {

        if (suffix == null) {suffix = "";}
        else if (suffix == "") {suffix = "";}
        else if (suffix == "_") {suffix = "";}

        BlockFamilyProvider blockPool = blockStateModelGenerator.family(MainTexture);

        blockPool
            .stairs(BlockFactory.callBlock(blockName + "_stairs" + suffix))
        .slab(BlockFactory.callBlock(blockName + "_slab" + suffix));

        if (generateWoodBase) {
            blockPool
                .button(BlockFactory.callBlock(blockName + "_button" + suffix))
                .pressurePlate(BlockFactory.callBlock(blockName + "_pressure_plate" + suffix))
                .fence(BlockFactory.callBlock(blockName + "_fence" + suffix))
            .fenceGate(BlockFactory.callBlock(blockName + "_fence_gate" + suffix));

            blockStateModelGenerator.createTrapdoor(BlockFactory.callBlock(blockName + "_trapdoor" + suffix));
            blockStateModelGenerator.createDoor(BlockFactory.callBlock(blockName + "_door" + suffix));
            generateGlassIntercation(blockName, suffix);
        }
        else if (generateStoneBase) {
            blockPool
                .wall(BlockFactory.callBlock(blockName + "_wall"));
        }


    }

    public void generateGlassIntercation(String blockName, String suffix) {
        blockStateModelGenerator.createTrapdoor(BlockFactory.callBlock(blockName + "_glass_trapdoor" + suffix));
        blockStateModelGenerator.createDoor(BlockFactory.callBlock(blockName + "_glass_door" + suffix));
    }




    /* OLD */ 

    private void registerPointedIce() {
      PropertyDispatch.C2<MultiVariant, Direction, DripstoneThickness> doubleProperty = PropertyDispatch.initial(BlockStateProperties.VERTICAL_DIRECTION, BlockStateProperties.DRIPSTONE_THICKNESS);
      DripstoneThickness[] var2 = DripstoneThickness.values();
      int var3 = var2.length;

      int var4;
      DripstoneThickness thickness;
      for(var4 = 0; var4 < var3; ++var4) {
         thickness = var2[var4];
         doubleProperty.select(Direction.UP, thickness, this.getDripstoneVariant(Direction.UP, thickness));
      }

      var2 = DripstoneThickness.values();
      var3 = var2.length;

      for(var4 = 0; var4 < var3; ++var4) {
         thickness = var2[var4];
         doubleProperty.select(Direction.DOWN, thickness, this.getDripstoneVariant(Direction.DOWN, thickness));
      }

      blockStateModelGenerator.blockStateOutput.accept(MultiVariantGenerator.dispatch(BlockFactory.callBlock("pointed_ice")).with(doubleProperty));
   }

    public final MultiVariant getDripstoneVariant(Direction direction, DripstoneThickness thickness) {
      String var10000 = direction.getSerializedName();
      String string = "_" + var10000 + "_" + thickness.getSerializedName();
      TextureMapping textureMap = TextureMapping.cross(TextureMapping.getBlockTexture(BlockFactory.POINTED_ICE, string)); 
      //return createWeightedVariant(Models.POINTED_DRIPSTONE.upload(BlockFactory.POINTED_ICE, string, textureMap, blockStateModelGenerator.modelCollector));
      return blockStateModelGenerator.plainVariant(ModelTemplates.POINTED_DRIPSTONE.createWithSuffix(BlockFactory.POINTED_ICE, string, textureMap, blockStateModelGenerator.modelOutput));
   }


    @Deprecated
    public void createMosaic(String Familybase, String varient) {

        Block MainTexture = BlockFactory.callBlock(Familybase + "_mosaic" + varient);
        BlockFamilyProvider blockPool = blockStateModelGenerator.family(MainTexture);

        blockPool
            .stairs(BlockFactory.callBlock(Familybase + "_mosaic_stairs" + varient))
            .slab(BlockFactory.callBlock(Familybase + "_mosaic_slab" + varient));
    }
    
    public void addBlockFamily(Block MainTexture, String Familybase, String varient) {

        if (varient == null) {varient = "";}
        else {varient = "_" + varient;}

        BlockFamilyProvider blockPool = blockStateModelGenerator.family(MainTexture);
        blockPool
            .stairs(BlockFactory.callBlock(Familybase + "_stairs" + varient))
            .slab(BlockFactory.callBlock(Familybase + "_slab" + varient))
            .button(BlockFactory.callBlock(Familybase + "_button" + varient))
            .pressurePlate(BlockFactory.callBlock(Familybase + "_pressure_plate" + varient))
            .fence(BlockFactory.callBlock(Familybase + "_fence" + varient))
            .fenceGate(BlockFactory.callBlock(Familybase + "_fence_gate" + varient)
        );
    }

    @Deprecated
    public void addWoodFamily(String Familybase, String varient, Boolean isNatural, Boolean enableLogs) {

        if (varient == null) {varient = "";}
        else {varient = "_" + varient;}

        if (isNatural) {       
            blockStateModelGenerator.createTrivialBlock(BlockFactory.callBlock(Familybase + "_leaves" + varient), TexturedModel.LEAVES);
        }
        if (enableLogs) {

            blockStateModelGenerator.woodProvider(BlockFactory.callBlock(Familybase + "_log" + varient));
            blockStateModelGenerator.woodProvider(BlockFactory.callBlock("stripped_" + Familybase + "_log" + varient));
        }


        Block MainTexture = BlockFactory.callBlock(Familybase + "_planks" + varient);
        BlockFamilyProvider blockPool = blockStateModelGenerator.family(MainTexture);

        blockPool
            .stairs(BlockFactory.callBlock(Familybase + "_stairs" + varient))
            .slab(BlockFactory.callBlock(Familybase + "_slab" + varient))
            .button(BlockFactory.callBlock(Familybase + "_button" + varient))
            .pressurePlate(BlockFactory.callBlock(Familybase + "_pressure_plate" + varient))
            .fence(BlockFactory.callBlock(Familybase + "_fence" + varient))
            .fenceGate(BlockFactory.callBlock(Familybase + "_fence_gate" + varient));
            
        blockStateModelGenerator.createTrapdoor(BlockFactory.callBlock(Familybase + "_trapdoor" + varient));
        blockStateModelGenerator.createTrapdoor(BlockFactory.callBlock(Familybase + "_glass_trapdoor" + varient));
        blockStateModelGenerator.createDoor(BlockFactory.callBlock(Familybase + "_door" + varient));
        blockStateModelGenerator.createDoor(BlockFactory.callBlock(Familybase + "_glass_door" + varient));

        createMosaic(Familybase, varient);
    }

    @Deprecated
    public void createVanillaGlassDoor(String Familybase) {

        blockStateModelGenerator.createDoor(BlockFactory.callBlock(Familybase + "_glass_door"));
        blockStateModelGenerator.createTrapdoor(BlockFactory.callBlock(Familybase + "_glass_trapdoor"));

        /*if (Familybase == "waxed_copper") {}
        else if (Familybase == "waxed_exposed_copper") {} 
        else if (Familybase == "waxed_oxidized_copper") {} 
        else if (Familybase == "waxed_weathered_copper") {}
        else {
            blockStateModelGenerator.registerDoor(BlockFactoryUpt.callBlock(Familybase + "_glass_door"));
            blockStateModelGenerator.registerTrapdoor(BlockFactoryUpt.callBlock(Familybase + "_glass_trapdoor"));
        }*/
    }

    public void addWoolFamily(String FamilyBase, String color) {
        blockStateModelGenerator.createTrivialCube(BlockFactory.callBlock(FamilyBase + "_" + color));
    }

    public void addStoneAlt(String FamilyBase, Boolean isBricksOnly) {

        if (isBricksOnly) {
            Block MainTexture = BlockFactory.callBlock(FamilyBase + "_bricks");
            BlockFamilyProvider blockPool = blockStateModelGenerator.family(MainTexture);

            blockPool
                .stairs(BlockFactory.callBlock(FamilyBase + "_brick_stairs"))
                .slab(BlockFactory.callBlock(FamilyBase + "_brick_slab"))
                .wall(BlockFactory.callBlock(FamilyBase + "_brick_wall"));

            blockStateModelGenerator.createTrivialCube(BlockFactory.callBlock("cracked_" + FamilyBase + "_bricks"));
            blockStateModelGenerator.createTrivialCube(BlockFactory.callBlock(FamilyBase + "_brick_chiseled"));

        } else {
            Block MainTexture = BlockFactory.callBlock(FamilyBase);
            BlockFamilyProvider blockPool = blockStateModelGenerator.family(MainTexture);

            blockPool
                .stairs(BlockFactory.callBlock(FamilyBase + "_stairs"))
                .slab(BlockFactory.callBlock(FamilyBase + "_slab"))
                .wall(BlockFactory.callBlock(FamilyBase + "_wall"));

        }
        

    }


    /**
     *  Annocument: New Post-Text subline - Applied to Wood Type
     * [Base Item] + [Type] + [Color]
     * 
     */

    @Deprecated
    public void createColorT(String name, BlockModelGenerators blockStateModelGenerator) {
        blockStateModelGenerator.createTrivialCube(BlockFactory.callBlock(name));
    }

    @Deprecated
    public void createWoodTexG(String name, BlockModelGenerators blockStateModelGenerator, String endTag) { //TODO: Sign, Hanging Sign

        System.out.println("Model: " + name + "_[Type]" + endTag);
        Block block = BlockFactory.callBlock(name + "_planks" + endTag);
        BlockFamilyProvider blockPool = blockStateModelGenerator.family(block);

        blockPool.stairs(BlockFactory.callBlock(name + "_stairs" + endTag));
        blockPool.slab(BlockFactory.callBlock(name + "_slab" + endTag));
        blockPool.button(BlockFactory.callBlock(name + "_button" + endTag));
        blockPool.pressurePlate(BlockFactory.callBlock(name + "_pressure_plate" + endTag));
        blockPool.fence(BlockFactory.callBlock(name + "_fence" + endTag));
        blockPool.fenceGate(BlockFactory.callBlock(name + "_fence_gate" + endTag));
        blockStateModelGenerator.createTrapdoor(BlockFactory.callBlock(name + "_trapdoor" + endTag));
        blockStateModelGenerator.createDoor(BlockFactory.callBlock(name + "_door" + endTag));
        //blockPool.sign(block)
    }

}
