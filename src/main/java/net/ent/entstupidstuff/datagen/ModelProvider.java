package net.ent.entstupidstuff.datagen;

import net.ent.entstupidstuff.block.BlockFactoryUpt;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.enums.Thickness;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.BlockStateModelGenerator.BlockTexturePool;
import net.minecraft.data.client.BlockStateVariant;
import net.minecraft.data.client.BlockStateVariantMap;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.minecraft.data.client.TextureMap;
import net.minecraft.data.client.TexturedModel;
import net.minecraft.data.client.VariantSettings;
import net.minecraft.data.client.VariantsBlockStateSupplier;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.Direction;

public class ModelProvider extends FabricModelProvider{

    public ModelProvider(FabricDataOutput output) {
        super(output);
    }
    
    BlockStateModelGenerator blockStateModelGenerator;

    
    

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator2) {

        this.blockStateModelGenerator = blockStateModelGenerator2;

        //Fungal Wood Type

        generateGroupFungalFamilty("fungal", "");
        generateGroupFungalFamilty("fungal", "white");
        generateGroupFungalFamilty("fungal", "light_gray");
        generateGroupFungalFamilty("fungal", "gray");
        generateGroupFungalFamilty("fungal", "black");
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
        
        //Phantom Wood Type

        generateGroupWoodFamilty("phantom", "", true);
        
        //Redwood Wood Type
        
        generateGroupWoodFamilty("redwood", "", true);
        
        //Maple Wood Type

        generateGroupWoodFamilty("maple", "", true);
        blockStateModelGenerator2.registerFlowerPotPlant(BlockFactoryUpt.callBlock("maple_sapling"), BlockFactoryUpt.callBlock("potted_maple_sapling"), BlockStateModelGenerator.TintType.NOT_TINTED);
        
        //Fir Wood Type

        generateGroupWoodFamilty("fir", "", true);
        blockStateModelGenerator2.registerFlowerPotPlant(BlockFactoryUpt.callBlock("fir_sapling"), BlockFactoryUpt.callBlock("potted_fir_sapling"), BlockStateModelGenerator.TintType.NOT_TINTED);
        
        //Vanilla Wood Type

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

        // Vanilla Addition for Glass TD and Door

        generateGlassIntercation("iron", "");
        generateGlassIntercation("copper", "");
        generateGlassIntercation("exposed_copper", "");
        generateGlassIntercation("oxidized_copper", "");
        generateGlassIntercation("weathered_copper", "");

        generateGroupBricksFamilty("andesite", "");
        generateGroupBricksFamilty("diorite", "");
        generateGroupBricksFamilty("granite", "");

        blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.POLISHED_ANDESITE).wall(BlockFactoryUpt.callBlock("polished_" + "andesite" + "_wall"));
        blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.POLISHED_DIORITE).wall(BlockFactoryUpt.callBlock("polished_" + "diorite" + "_wall"));
        blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.POLISHED_GRANITE).wall(BlockFactoryUpt.callBlock("polished_" + "granite" + "_wall"));
        
        registerPointedIce();

        generateBaseAndIntercationFamily("limestone", "", false, true, BlockFactoryUpt.callBlock("limestone"));
        generateBaseAndIntercationFamily("polished_limestone", "", false, true, BlockFactoryUpt.callBlock("polished_limestone"));
        generateGroupBricksFamilty("polished_limestone", "");

        blockStateModelGenerator.registerGlassPane(BlockFactoryUpt.callBlock("string_block"), BlockFactoryUpt.callBlock("string_gate"));
        blockStateModelGenerator.registerLantern(BlockFactoryUpt.callBlock("phantom_lantern"));
        blockStateModelGenerator.registerTorch(BlockFactoryUpt.callBlock("phantom_torch"), BlockFactoryUpt.callBlock("phantom_wall_torch"));


        //addStoneAlt("andesite", true);
        //generateBaseAndIntercationFamily("andesite_brick", "", false, true, BlockFactoryUpt.callBlock("andesite_bricks"));


        // Fungal Wood
        //addWoodFamily("fungal", null, false, false);

        // Fungal (Colored) Wood
        //for (String color : BlockFactoryUpt.COLORS) {addWoodFamily("fungal", color, false, false);}

        // Modded Wood
        //addWoodFamily("phantom", null, false, true);
        //addWoodFamily("redwood", null, true, true);
        //addWoodFamily("desert_iron", null, false, true);
        //addWoodFamily("maple", null, true, true);
        //addWoodFamily("fir", null, true, true);

        

        //createMosaic("oak", "");
        //createMosaic("spruce", "");
        //createMosaic("jungle", "");
        //createMosaic("birch", "");
        //createMosaic("dark_oak", "");
        //createMosaic("acacia", "");
        //createMosaic("mangrove", "");
        //createMosaic("cherry", "");
        //createMosaic("warped", "");
        //createMosaic("crimson", "");

        // Vanilla Wood
        //createVanillaGlassDoor("oak");
        //createVanillaGlassDoor("spruce");
        //createVanillaGlassDoor("jungle");
        //createVanillaGlassDoor("birch");
        //createVanillaGlassDoor("dark_oak");
        //createVanillaGlassDoor("acacia");
        //createVanillaGlassDoor("mangrove");
        //createVanillaGlassDoor("cherry");
        //createVanillaGlassDoor("bamboo");
        //createVanillaGlassDoor("warped");
        //createVanillaGlassDoor("crimson");
        //createVanillaGlassDoor("pale_oak"); //FUTURE UPDATE

        //createVanillaGlassDoor("iron");
        //createVanillaGlassDoor("copper");
        //createVanillaGlassDoor("exposed_copper");
        //createVanillaGlassDoor("oxidized_copper");
        //createVanillaGlassDoor("weathered_copper");
        //createVanillaGlassDoor("waxed_copper"); - These ware Manually Created :)
        //createVanillaGlassDoor("waxed_exposed_copper");
        //createVanillaGlassDoor("waxed_oxidized_copper");
        //createVanillaGlassDoor("waxed_weathered_copper");

        // Vanilla Stone
        //addStoneAlt("andesite", true);
        //addStoneAlt("diorite", true);
        //addStoneAlt("granite", true);

        //blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.POLISHED_ANDESITE).wall(BlockFactoryUpt.callBlock("polished_" + "andesite" + "_wall"));
        //blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.POLISHED_DIORITE).wall(BlockFactoryUpt.callBlock("polished_" + "diorite" + "_wall"));
        //blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.POLISHED_GRANITE).wall(BlockFactoryUpt.callBlock("polished_" + "granite" + "_wall"));

        //String
        //blockStateModelGenerator.registerCubeAllModelTexturePool(BlockFactoryUpt.callBlock("string_block"));
        //blockStateModelGenerator.registerGlassPane(BlockFactoryUpt.callBlock("string_block"), BlockFactoryUpt.callBlock("string_gate"));

        //blockStateModelGenerator.registerLantern(BlockFactoryUpt.callBlock("phantom_lantern"));
        //blockStateModelGenerator.registerTorch(BlockFactoryUpt.callBlock("phantom_torch"), BlockFactoryUpt.callBlock("phantom_wall_torch"));

        //registerPointedIce();

        //addStoneAlt("limestone", false);
        //addStoneAlt("polished_limestone", false);
        //addStoneAlt("polished_limestone", true);

        /*


        addWoodFamily("fungal", null, false); //Normal Fungal Wood
        for (String color : BlockFactoryUpt.COLORS) {addWoodFamily("fungal", color, false);} //Fungal Wood
        for (String color : BlockFactoryUpt.COLORS) {addWoolFamily("textured_wool", color);} //Textured Wool

        //Vanilla Doors:
        createVanillaGlassDoor("iron");
        for (String base : ModBlocks.V_WOOD_VARIENTS) {createVanillaGlassDoor(base);} //Vanilla Doors
        for (String c : ModBlocks.COPPER_VARIENTS) {createVanillaGlassDoor(c);}

        //Stone Bricks
        addStoneAlt("polished_andesite");
        addStoneAlt("polished_diorite");
        addStoneAlt("polished_granite");
        
        //Modded Wood
        addWoodFamily("redwood", null, true);
        addWoodFamily("desert_iron", null, false);
        addWoodFamily("maple", null, true);

        blockStateModelGenerator.registerGlassPane(BlockFactoryUpt.callBlock("string_block"), BlockFactoryUpt.callBlock("string_gate"));

        //blockStateModelGenerator.registerHangingSign(BlockFactoryUpt.callBlock("stripped_redwood_wood"), BlockFactoryUpt.callBlock("redwood_hanging_sign"), BlockFactoryUpt.callBlock("redwood_wall_hanging_sign"));

        //blockStateModelGenerator.registerLog(BlockFactoryUpt.callBlock("redwood_log")).log(BlockFactoryUpt.callBlock("redwood_log")).wood(BlockFactoryUpt.callBlock("redwood_wood"));
        //blockStateModelGenerator.registerLog(BlockFactoryUpt.callBlock("stripped_redwood_log")).log(BlockFactoryUpt.callBlock("stripped_redwood_log")).wood(BlockFactoryUpt.callBlock("stripped_redwood_wood"));

        //addBlockFamily(Blocks.OAK_WOOD, "oak_wood", null);


        















		/*for (Block inputB : MBlockFactory.FULLBLOCKS.values()) {
			blockStateModelGenerator.registerSimpleCubeAll(inputB);  
		}*/
        
        /*createWoodTexG("fungal", blockStateModelGenerator, "");
        for (String inputC : MBlockFactoryUpt.COLORS) { createWoodTexG("fungal", blockStateModelGenerator, "_" + inputC);}
        for (String inputC : MBlockFactoryUpt.COLORS) { createColorT("textured_wool_" + inputC, blockStateModelGenerator);}*/
	}

    public void generateGroupWoodFamilty(String blockName, String suffix, Boolean withLeaves) {
        if (suffix == null) {suffix = "";}
        else if (suffix == "") {suffix = "";}
        else if (suffix == "_") {suffix = "";}
        else {suffix = "_" + suffix;}

        Block MainTexture = BlockFactoryUpt.callBlock(blockName + "_planks" + suffix);
        Block MosicTexture = BlockFactoryUpt.callBlock(blockName + "_mosaic" + suffix);
        generateBaseAndIntercationFamily(blockName, suffix, true, false, MainTexture);
        generateBaseAndIntercationFamily(blockName + "_mosaic", suffix, false, false, MosicTexture);
        generateNatureFamily(blockName, suffix, withLeaves);

    }

    public void generateGroupFungalFamilty(String blockName, String suffix) {
        if (suffix == null) {suffix = "";}
        else if (suffix == "") {suffix = "";}
        else if (suffix == "_") {suffix = "";}
        else {suffix = "_" + suffix;}

        

        Block MainTexture = BlockFactoryUpt.callBlock(blockName + "_planks" + suffix);
        Block MosicTexture = BlockFactoryUpt.callBlock(blockName + "_mosaic" + suffix);
        generateBaseAndIntercationFamily(blockName, suffix, true, false, MainTexture);
        generateBaseAndIntercationFamily(blockName + "_mosaic", suffix, false, false, MosicTexture);

    }

    public void generateGroupBricksFamilty(String blockName, String suffix) {
        if (suffix == null) {suffix = "";}
        else if (suffix == "") {suffix = "";}
        else if (suffix == "_") {suffix = "";}
        else {suffix = "_" + suffix;}

        Block BricksTexture = BlockFactoryUpt.callBlock(blockName + "_bricks" + suffix);
        generateBaseAndIntercationFamily(blockName + "_brick", suffix, false, true, BricksTexture);
        blockStateModelGenerator.registerSimpleCubeAll(BlockFactoryUpt.callBlock("cracked_" + blockName + "_bricks" + suffix));
        blockStateModelGenerator.registerSimpleCubeAll(BlockFactoryUpt.callBlock(blockName + "_brick_chiseled" + suffix));
    }

    public void generateGroupVanillaAddition(String blockName) {
        generateBaseAndIntercationFamily(blockName + "_mosaic", "", false, false, BlockFactoryUpt.callBlock(blockName + "_mosaic"));
        generateGlassIntercation(blockName, "");
    }

    public void generateNatureFamily(String blockName, String suffix, Boolean withLeaves) {
        if (suffix == null) {suffix = "";}
        else if (suffix == "") {suffix = "";}
        else if (suffix == "_") {suffix = "";}

        if (withLeaves) {
            blockStateModelGenerator.registerSingleton(BlockFactoryUpt.callBlock(blockName + "_leaves" + suffix), TexturedModel.LEAVES);
            
        }
        
        blockStateModelGenerator.registerLog(BlockFactoryUpt.callBlock(blockName + "_log" + suffix)).log(BlockFactoryUpt.callBlock(blockName + "_log" + suffix)).wood(BlockFactoryUpt.callBlock(blockName + "_wood" + suffix));
        blockStateModelGenerator.registerLog(BlockFactoryUpt.callBlock("stripped_" + blockName + "_log" + suffix)).log(BlockFactoryUpt.callBlock("stripped_" + blockName + "_log" + suffix)).wood(BlockFactoryUpt.callBlock("stripped_" + blockName + "_wood" + suffix));  
    }

    public void generateBaseAndIntercationFamily(String blockName, String suffix, Boolean generateWoodBase, Boolean generateStoneBase, Block MainTexture) {

        if (suffix == null) {suffix = "";}
        else if (suffix == "") {suffix = "";}
        else if (suffix == "_") {suffix = "";}

        BlockTexturePool blockPool = blockStateModelGenerator.registerCubeAllModelTexturePool(MainTexture);

        blockPool
            .stairs(BlockFactoryUpt.callBlock(blockName + "_stairs" + suffix))
        .slab(BlockFactoryUpt.callBlock(blockName + "_slab" + suffix));

        if (generateWoodBase) {
            blockPool
                .button(BlockFactoryUpt.callBlock(blockName + "_button" + suffix))
                .pressurePlate(BlockFactoryUpt.callBlock(blockName + "_pressure_plate" + suffix))
                .fence(BlockFactoryUpt.callBlock(blockName + "_fence" + suffix))
            .fenceGate(BlockFactoryUpt.callBlock(blockName + "_fence_gate" + suffix));

            blockStateModelGenerator.registerTrapdoor(BlockFactoryUpt.callBlock(blockName + "_trapdoor" + suffix));
            blockStateModelGenerator.registerDoor(BlockFactoryUpt.callBlock(blockName + "_door" + suffix));
            generateGlassIntercation(blockName, suffix);
        }
        else if (generateStoneBase) {
            blockPool
                .wall(BlockFactoryUpt.callBlock(blockName + "_wall"));
        }


    }

    public void generateGlassIntercation(String blockName, String suffix) {
        blockStateModelGenerator.registerTrapdoor(BlockFactoryUpt.callBlock(blockName + "_glass_trapdoor" + suffix));
        blockStateModelGenerator.registerDoor(BlockFactoryUpt.callBlock(blockName + "_glass_door" + suffix));
    }

    








    /* OLD */


	private void registerPointedIce() {
		blockStateModelGenerator.excludeFromSimpleItemModelGeneration(BlockFactoryUpt.callBlock("pointed_ice"));
		BlockStateVariantMap.DoubleProperty<Direction, Thickness> doubleProperty = BlockStateVariantMap.create(Properties.VERTICAL_DIRECTION, Properties.THICKNESS);

		for (Thickness thickness : Thickness.values()) {
			doubleProperty.register(Direction.UP, thickness, this.getDripstoneVariant(Direction.UP, thickness));
		}

		for (Thickness thickness : Thickness.values()) {
			doubleProperty.register(Direction.DOWN, thickness, this.getDripstoneVariant(Direction.DOWN, thickness));
		}

		blockStateModelGenerator.blockStateCollector.accept(VariantsBlockStateSupplier.create(BlockFactoryUpt.callBlock("pointed_ice")).coordinate(doubleProperty));
	}

    public final BlockStateVariant getDripstoneVariant(Direction direction, Thickness thickness) {
		String string = "_" + direction.asString() + "_" + thickness.asString();
		TextureMap textureMap = TextureMap.cross(TextureMap.getSubId(BlockFactoryUpt.callBlock("pointed_ice"), string));
		return BlockStateVariant.create()
			.put(VariantSettings.MODEL, Models.POINTED_DRIPSTONE.upload(BlockFactoryUpt.callBlock("pointed_ice"), string, textureMap, blockStateModelGenerator.modelCollector));
	}


    @Deprecated
    public void createMosaic(String Familybase, String varient) {

        Block MainTexture = BlockFactoryUpt.callBlock(Familybase + "_mosaic" + varient);
        BlockTexturePool blockPool = blockStateModelGenerator.registerCubeAllModelTexturePool(MainTexture);

        blockPool
            .stairs(BlockFactoryUpt.callBlock(Familybase + "_mosaic_stairs" + varient))
            .slab(BlockFactoryUpt.callBlock(Familybase + "_mosaic_slab" + varient));
    }
    
    public void addBlockFamily(Block MainTexture, String Familybase, String varient) {

        if (varient == null) {varient = "";}
        else {varient = "_" + varient;}

        BlockTexturePool blockPool = blockStateModelGenerator.registerCubeAllModelTexturePool(MainTexture);
        blockPool
            .stairs(BlockFactoryUpt.callBlock(Familybase + "_stairs" + varient))
            .slab(BlockFactoryUpt.callBlock(Familybase + "_slab" + varient))
            .button(BlockFactoryUpt.callBlock(Familybase + "_button" + varient))
            .pressurePlate(BlockFactoryUpt.callBlock(Familybase + "_pressure_plate" + varient))
            .fence(BlockFactoryUpt.callBlock(Familybase + "_fence" + varient))
            .fenceGate(BlockFactoryUpt.callBlock(Familybase + "_fence_gate" + varient)
        );
    }

    @Deprecated
    public void addWoodFamily(String Familybase, String varient, Boolean isNatural, Boolean enableLogs) {

        if (varient == null) {varient = "";}
        else {varient = "_" + varient;}

        if (isNatural) {       
            blockStateModelGenerator.registerSingleton(BlockFactoryUpt.callBlock(Familybase + "_leaves" + varient), TexturedModel.LEAVES);
        }
        if (enableLogs) {
            blockStateModelGenerator.registerLog(BlockFactoryUpt.callBlock(Familybase + "_log" + varient)).log(BlockFactoryUpt.callBlock(Familybase + "_log" + varient)).wood(BlockFactoryUpt.callBlock(Familybase + "_wood" + varient));
            blockStateModelGenerator.registerLog(BlockFactoryUpt.callBlock("stripped_" + Familybase + "_log" + varient)).log(BlockFactoryUpt.callBlock("stripped_" +Familybase + "_log" + varient)).wood(BlockFactoryUpt.callBlock("stripped_" +Familybase + "_wood" + varient));
        }


        Block MainTexture = BlockFactoryUpt.callBlock(Familybase + "_planks" + varient);
        BlockTexturePool blockPool = blockStateModelGenerator.registerCubeAllModelTexturePool(MainTexture);

        blockPool
            .stairs(BlockFactoryUpt.callBlock(Familybase + "_stairs" + varient))
            .slab(BlockFactoryUpt.callBlock(Familybase + "_slab" + varient))
            .button(BlockFactoryUpt.callBlock(Familybase + "_button" + varient))
            .pressurePlate(BlockFactoryUpt.callBlock(Familybase + "_pressure_plate" + varient))
            .fence(BlockFactoryUpt.callBlock(Familybase + "_fence" + varient))
            .fenceGate(BlockFactoryUpt.callBlock(Familybase + "_fence_gate" + varient));
            
        blockStateModelGenerator.registerTrapdoor(BlockFactoryUpt.callBlock(Familybase + "_trapdoor" + varient));
        blockStateModelGenerator.registerTrapdoor(BlockFactoryUpt.callBlock(Familybase + "_glass_trapdoor" + varient));
        blockStateModelGenerator.registerDoor(BlockFactoryUpt.callBlock(Familybase + "_door" + varient));
        blockStateModelGenerator.registerDoor(BlockFactoryUpt.callBlock(Familybase + "_glass_door" + varient));

        createMosaic(Familybase, varient);
    }

    @Deprecated
    public void createVanillaGlassDoor(String Familybase) {

        blockStateModelGenerator.registerDoor(BlockFactoryUpt.callBlock(Familybase + "_glass_door"));
        blockStateModelGenerator.registerTrapdoor(BlockFactoryUpt.callBlock(Familybase + "_glass_trapdoor"));

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
        blockStateModelGenerator.registerSimpleCubeAll(BlockFactoryUpt.callBlock(FamilyBase + "_" + color));
    }

    public void addStoneAlt(String FamilyBase, Boolean isBricksOnly) {

        if (isBricksOnly) {
            Block MainTexture = BlockFactoryUpt.callBlock(FamilyBase + "_bricks");
            BlockTexturePool blockPool = blockStateModelGenerator.registerCubeAllModelTexturePool(MainTexture);

            blockPool
                .stairs(BlockFactoryUpt.callBlock(FamilyBase + "_brick_stairs"))
                .slab(BlockFactoryUpt.callBlock(FamilyBase + "_brick_slab"))
                .wall(BlockFactoryUpt.callBlock(FamilyBase + "_brick_wall"));

            blockStateModelGenerator.registerSimpleCubeAll(BlockFactoryUpt.callBlock("cracked_" + FamilyBase + "_bricks"));
            blockStateModelGenerator.registerSimpleCubeAll(BlockFactoryUpt.callBlock(FamilyBase + "_brick_chiseled"));

        } else {
            Block MainTexture = BlockFactoryUpt.callBlock(FamilyBase);
            BlockTexturePool blockPool = blockStateModelGenerator.registerCubeAllModelTexturePool(MainTexture);

            blockPool
                .stairs(BlockFactoryUpt.callBlock(FamilyBase + "_stairs"))
                .slab(BlockFactoryUpt.callBlock(FamilyBase + "_slab"))
                .wall(BlockFactoryUpt.callBlock(FamilyBase + "_wall"));

        }
        

    }







    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        // Unused for Now
    }

    /**
     *  Annocument: New Post-Text subline - Applied to Wood Type
     * [Base Item] + [Type] + [Color]
     * 
     */

    @Deprecated
    public void createColorT(String name, BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(BlockFactoryUpt.callBlock(name));
    }

    @Deprecated
    public void createWoodTexG(String name, BlockStateModelGenerator blockStateModelGenerator, String endTag) { //TODO: Sign, Hanging Sign

        System.out.println("Model: " + name + "_[Type]" + endTag);
        Block block = BlockFactoryUpt.callBlock(name + "_planks" + endTag);
        BlockTexturePool blockPool = blockStateModelGenerator.registerCubeAllModelTexturePool(block);

        blockPool.stairs(BlockFactoryUpt.callBlock(name + "_stairs" + endTag));
        blockPool.slab(BlockFactoryUpt.callBlock(name + "_slab" + endTag));
        blockPool.button(BlockFactoryUpt.callBlock(name + "_button" + endTag));
        blockPool.pressurePlate(BlockFactoryUpt.callBlock(name + "_pressure_plate" + endTag));
        blockPool.fence(BlockFactoryUpt.callBlock(name + "_fence" + endTag));
        blockPool.fenceGate(BlockFactoryUpt.callBlock(name + "_fence_gate" + endTag));
        blockStateModelGenerator.registerTrapdoor(BlockFactoryUpt.callBlock(name + "_trapdoor" + endTag));
        blockStateModelGenerator.registerDoor(BlockFactoryUpt.callBlock(name + "_door" + endTag));
        //blockPool.sign(block)
    }

}
