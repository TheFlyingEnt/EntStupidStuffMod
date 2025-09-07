package net.ent.entstupidstuff.datagen;

import net.ent.entstupidstuff.block.BlockFactory;
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
        
        //Phantom Wood Type

        generateGroupWoodFamilty("phantom", "", true);
        
        //Redwood Wood Type
        
        generateGroupWoodFamilty("redwood", "", true);
        
        //Maple Wood Type

        generateGroupWoodFamilty("maple", "", true);
        blockStateModelGenerator2.registerFlowerPotPlant(BlockFactory.callBlock("maple_sapling"), BlockFactory.callBlock("potted_maple_sapling"), BlockStateModelGenerator.TintType.NOT_TINTED);
        
        //Fir Wood Type

        generateGroupWoodFamilty("fir", "", true);
        blockStateModelGenerator2.registerFlowerPotPlant(BlockFactory.callBlock("fir_sapling"), BlockFactory.callBlock("potted_fir_sapling"), BlockStateModelGenerator.TintType.NOT_TINTED);
        
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
        //generateGroupVanillaAddition("bamboo");
        generateGlassIntercation("bamboo", "");

        // Vanilla Addition for Glass TD and Door

        generateGlassIntercation("iron", "");
        generateGlassIntercation("copper", "");
        generateGlassIntercation("exposed_copper", "");
        generateGlassIntercation("oxidized_copper", "");
        generateGlassIntercation("weathered_copper", "");

        generateGroupBricksFamilty("andesite", "");
        generateGroupBricksFamilty("diorite", "");
        generateGroupBricksFamilty("granite", "");

        blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.POLISHED_ANDESITE).wall(BlockFactory.callBlock("polished_" + "andesite" + "_wall"));
        blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.POLISHED_DIORITE).wall(BlockFactory.callBlock("polished_" + "diorite" + "_wall"));
        blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.POLISHED_GRANITE).wall(BlockFactory.callBlock("polished_" + "granite" + "_wall"));
        
        registerPointedIce();

        generateBaseAndIntercationFamily("limestone", "", false, true, BlockFactory.callBlock("limestone"));
        generateBaseAndIntercationFamily("polished_limestone", "", false, true, BlockFactory.callBlock("polished_limestone"));
        generateGroupBricksFamilty("polished_limestone", "");

        blockStateModelGenerator.registerGlassPane(BlockFactory.callBlock("string_block"), BlockFactory.callBlock("string_gate"));
        blockStateModelGenerator.registerLantern(BlockFactory.callBlock("phantom_lantern"));
        blockStateModelGenerator.registerTorch(BlockFactory.callBlock("phantom_torch"), BlockFactory.callBlock("phantom_wall_torch"));

        // Iron Grate
        generateBaseAndIntercationFamily("iron_grate", "", false, false, BlockFactory.callBlock("iron_grate"));

	}

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
        blockStateModelGenerator.registerSimpleCubeAll(BlockFactory.callBlock("cracked_" + blockName + "_bricks" + suffix));
        blockStateModelGenerator.registerSimpleCubeAll(BlockFactory.callBlock(blockName + "_brick_chiseled" + suffix));
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
            blockStateModelGenerator.registerSingleton(BlockFactory.callBlock(blockName + "_leaves" + suffix), TexturedModel.LEAVES);
            
        }
        
        blockStateModelGenerator.registerLog(BlockFactory.callBlock(blockName + "_log" + suffix)).log(BlockFactory.callBlock(blockName + "_log" + suffix)).wood(BlockFactory.callBlock(blockName + "_wood" + suffix));
        blockStateModelGenerator.registerLog(BlockFactory.callBlock("stripped_" + blockName + "_log" + suffix)).log(BlockFactory.callBlock("stripped_" + blockName + "_log" + suffix)).wood(BlockFactory.callBlock("stripped_" + blockName + "_wood" + suffix));  
    }

    public void generateBaseAndIntercationFamily(String blockName, String suffix, Boolean generateWoodBase, Boolean generateStoneBase, Block MainTexture) {

        if (suffix == null) {suffix = "";}
        else if (suffix == "") {suffix = "";}
        else if (suffix == "_") {suffix = "";}

        BlockTexturePool blockPool = blockStateModelGenerator.registerCubeAllModelTexturePool(MainTexture);

        blockPool
            .stairs(BlockFactory.callBlock(blockName + "_stairs" + suffix))
        .slab(BlockFactory.callBlock(blockName + "_slab" + suffix));

        if (generateWoodBase) {
            blockPool
                .button(BlockFactory.callBlock(blockName + "_button" + suffix))
                .pressurePlate(BlockFactory.callBlock(blockName + "_pressure_plate" + suffix))
                .fence(BlockFactory.callBlock(blockName + "_fence" + suffix))
            .fenceGate(BlockFactory.callBlock(blockName + "_fence_gate" + suffix));

            blockStateModelGenerator.registerTrapdoor(BlockFactory.callBlock(blockName + "_trapdoor" + suffix));
            blockStateModelGenerator.registerDoor(BlockFactory.callBlock(blockName + "_door" + suffix));
            generateGlassIntercation(blockName, suffix);
        }
        else if (generateStoneBase) {
            blockPool
                .wall(BlockFactory.callBlock(blockName + "_wall"));
        }


    }

    public void generateGlassIntercation(String blockName, String suffix) {
        blockStateModelGenerator.registerTrapdoor(BlockFactory.callBlock(blockName + "_glass_trapdoor" + suffix));
        blockStateModelGenerator.registerDoor(BlockFactory.callBlock(blockName + "_glass_door" + suffix));
    }

    








    /* OLD */


	private void registerPointedIce() {
		blockStateModelGenerator.excludeFromSimpleItemModelGeneration(BlockFactory.callBlock("pointed_ice"));
		BlockStateVariantMap.DoubleProperty<Direction, Thickness> doubleProperty = BlockStateVariantMap.create(Properties.VERTICAL_DIRECTION, Properties.THICKNESS);

		for (Thickness thickness : Thickness.values()) {
			doubleProperty.register(Direction.UP, thickness, this.getDripstoneVariant(Direction.UP, thickness));
		}

		for (Thickness thickness : Thickness.values()) {
			doubleProperty.register(Direction.DOWN, thickness, this.getDripstoneVariant(Direction.DOWN, thickness));
		}

		blockStateModelGenerator.blockStateCollector.accept(VariantsBlockStateSupplier.create(BlockFactory.callBlock("pointed_ice")).coordinate(doubleProperty));
	}

    public final BlockStateVariant getDripstoneVariant(Direction direction, Thickness thickness) {
		String string = "_" + direction.asString() + "_" + thickness.asString();
		TextureMap textureMap = TextureMap.cross(TextureMap.getSubId(BlockFactory.callBlock("pointed_ice"), string));
		return BlockStateVariant.create()
			.put(VariantSettings.MODEL, Models.POINTED_DRIPSTONE.upload(BlockFactory.callBlock("pointed_ice"), string, textureMap, blockStateModelGenerator.modelCollector));
	}


    @Deprecated
    public void createMosaic(String Familybase, String varient) {

        Block MainTexture = BlockFactory.callBlock(Familybase + "_mosaic" + varient);
        BlockTexturePool blockPool = blockStateModelGenerator.registerCubeAllModelTexturePool(MainTexture);

        blockPool
            .stairs(BlockFactory.callBlock(Familybase + "_mosaic_stairs" + varient))
            .slab(BlockFactory.callBlock(Familybase + "_mosaic_slab" + varient));
    }
    
    public void addBlockFamily(Block MainTexture, String Familybase, String varient) {

        if (varient == null) {varient = "";}
        else {varient = "_" + varient;}

        BlockTexturePool blockPool = blockStateModelGenerator.registerCubeAllModelTexturePool(MainTexture);
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
            blockStateModelGenerator.registerSingleton(BlockFactory.callBlock(Familybase + "_leaves" + varient), TexturedModel.LEAVES);
        }
        if (enableLogs) {
            blockStateModelGenerator.registerLog(BlockFactory.callBlock(Familybase + "_log" + varient)).log(BlockFactory.callBlock(Familybase + "_log" + varient)).wood(BlockFactory.callBlock(Familybase + "_wood" + varient));
            blockStateModelGenerator.registerLog(BlockFactory.callBlock("stripped_" + Familybase + "_log" + varient)).log(BlockFactory.callBlock("stripped_" +Familybase + "_log" + varient)).wood(BlockFactory.callBlock("stripped_" +Familybase + "_wood" + varient));
        }


        Block MainTexture = BlockFactory.callBlock(Familybase + "_planks" + varient);
        BlockTexturePool blockPool = blockStateModelGenerator.registerCubeAllModelTexturePool(MainTexture);

        blockPool
            .stairs(BlockFactory.callBlock(Familybase + "_stairs" + varient))
            .slab(BlockFactory.callBlock(Familybase + "_slab" + varient))
            .button(BlockFactory.callBlock(Familybase + "_button" + varient))
            .pressurePlate(BlockFactory.callBlock(Familybase + "_pressure_plate" + varient))
            .fence(BlockFactory.callBlock(Familybase + "_fence" + varient))
            .fenceGate(BlockFactory.callBlock(Familybase + "_fence_gate" + varient));
            
        blockStateModelGenerator.registerTrapdoor(BlockFactory.callBlock(Familybase + "_trapdoor" + varient));
        blockStateModelGenerator.registerTrapdoor(BlockFactory.callBlock(Familybase + "_glass_trapdoor" + varient));
        blockStateModelGenerator.registerDoor(BlockFactory.callBlock(Familybase + "_door" + varient));
        blockStateModelGenerator.registerDoor(BlockFactory.callBlock(Familybase + "_glass_door" + varient));

        createMosaic(Familybase, varient);
    }

    @Deprecated
    public void createVanillaGlassDoor(String Familybase) {

        blockStateModelGenerator.registerDoor(BlockFactory.callBlock(Familybase + "_glass_door"));
        blockStateModelGenerator.registerTrapdoor(BlockFactory.callBlock(Familybase + "_glass_trapdoor"));

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
        blockStateModelGenerator.registerSimpleCubeAll(BlockFactory.callBlock(FamilyBase + "_" + color));
    }

    public void addStoneAlt(String FamilyBase, Boolean isBricksOnly) {

        if (isBricksOnly) {
            Block MainTexture = BlockFactory.callBlock(FamilyBase + "_bricks");
            BlockTexturePool blockPool = blockStateModelGenerator.registerCubeAllModelTexturePool(MainTexture);

            blockPool
                .stairs(BlockFactory.callBlock(FamilyBase + "_brick_stairs"))
                .slab(BlockFactory.callBlock(FamilyBase + "_brick_slab"))
                .wall(BlockFactory.callBlock(FamilyBase + "_brick_wall"));

            blockStateModelGenerator.registerSimpleCubeAll(BlockFactory.callBlock("cracked_" + FamilyBase + "_bricks"));
            blockStateModelGenerator.registerSimpleCubeAll(BlockFactory.callBlock(FamilyBase + "_brick_chiseled"));

        } else {
            Block MainTexture = BlockFactory.callBlock(FamilyBase);
            BlockTexturePool blockPool = blockStateModelGenerator.registerCubeAllModelTexturePool(MainTexture);

            blockPool
                .stairs(BlockFactory.callBlock(FamilyBase + "_stairs"))
                .slab(BlockFactory.callBlock(FamilyBase + "_slab"))
                .wall(BlockFactory.callBlock(FamilyBase + "_wall"));

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
        blockStateModelGenerator.registerSimpleCubeAll(BlockFactory.callBlock(name));
    }

    @Deprecated
    public void createWoodTexG(String name, BlockStateModelGenerator blockStateModelGenerator, String endTag) { //TODO: Sign, Hanging Sign

        System.out.println("Model: " + name + "_[Type]" + endTag);
        Block block = BlockFactory.callBlock(name + "_planks" + endTag);
        BlockTexturePool blockPool = blockStateModelGenerator.registerCubeAllModelTexturePool(block);

        blockPool.stairs(BlockFactory.callBlock(name + "_stairs" + endTag));
        blockPool.slab(BlockFactory.callBlock(name + "_slab" + endTag));
        blockPool.button(BlockFactory.callBlock(name + "_button" + endTag));
        blockPool.pressurePlate(BlockFactory.callBlock(name + "_pressure_plate" + endTag));
        blockPool.fence(BlockFactory.callBlock(name + "_fence" + endTag));
        blockPool.fenceGate(BlockFactory.callBlock(name + "_fence_gate" + endTag));
        blockStateModelGenerator.registerTrapdoor(BlockFactory.callBlock(name + "_trapdoor" + endTag));
        blockStateModelGenerator.registerDoor(BlockFactory.callBlock(name + "_door" + endTag));
        //blockPool.sign(block)
    }

}
