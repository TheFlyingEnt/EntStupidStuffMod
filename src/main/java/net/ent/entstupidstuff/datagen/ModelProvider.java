package net.ent.entstupidstuff.datagen;

import net.ent.entstupidstuff.block.BlockFactory;
import net.ent.entstupidstuff.item.ItemFactory;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.enums.Thickness;
import net.minecraft.client.data.BlockStateModelGenerator;
import net.minecraft.client.data.BlockStateModelGenerator.BlockTexturePool;
import net.minecraft.client.data.BlockStateVariantMap;
import net.minecraft.client.data.ItemModelGenerator;
import net.minecraft.client.data.Models;
import net.minecraft.client.data.TextureMap;
import net.minecraft.client.data.TexturedModel;
import net.minecraft.client.data.VariantsBlockModelDefinitionCreator;
import net.minecraft.client.render.model.json.WeightedVariant;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.Direction;

public class ModelProvider extends FabricModelProvider{

    public ModelProvider(FabricDataOutput output) {
        super(output);
    }
    
    BlockStateModelGenerator blockStateModelGenerator;
    ItemModelGenerator itemModelGenerator;
    

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator2) {
        this.itemModelGenerator = itemModelGenerator2;

        itemModelGenerator.register(ItemFactory.MARSHMELLOW_RAW, Models.HANDHELD);
        itemModelGenerator.register(ItemFactory.MARSHMELLOW_TOASTED, Models.HANDHELD);
        //BAGGUETTE is done via Manually

        itemModelGenerator.register(ItemFactory.BUTTERFLY_JAR, Models.GENERATED);
        itemModelGenerator.register(ItemFactory.BUTTERFLY_SPAWN_EGG, Models.GENERATED);

        itemModelGenerator.register(ItemFactory.WITHER_BONE, Models.GENERATED);
        itemModelGenerator.register(ItemFactory.ANCIENT_DEBRIS_NUGGET, Models.GENERATED);

        itemModelGenerator.register(ItemFactory.PIGLIN_WARRIOR_SPAWN, Models.GENERATED);
        itemModelGenerator.register(ItemFactory.BLAZING_INFERNO_SPAWN, Models.GENERATED);
        itemModelGenerator.register(ItemFactory.SOUL_SKELETON_SPAWN, Models.GENERATED);

        itemModelGenerator.register(ItemFactory.ZEBRA_FISH_BUCKET, Models.GENERATED);
        itemModelGenerator.register(ItemFactory.ZEBRA_FISH, Models.GENERATED);

        itemModelGenerator.register(ItemFactory.ALLIGATOR_GAR_BUCKET, Models.GENERATED);
        itemModelGenerator.register(ItemFactory.ALLIGATOR_GAR, Models.GENERATED);
        itemModelGenerator.register(ItemFactory.COOKED_ALLIGATOR_GAR, Models.GENERATED);

        itemModelGenerator.register(ItemFactory.MACKEREL_BUCKET, Models.GENERATED);
        itemModelGenerator.register(ItemFactory.MACKEREL, Models.GENERATED);
        itemModelGenerator.register(ItemFactory.COOKED_MACKEREL, Models.GENERATED);

        itemModelGenerator.register(ItemFactory.BASS_BUCKET, Models.GENERATED);
        itemModelGenerator.register(ItemFactory.BASS, Models.GENERATED);
        itemModelGenerator.register(ItemFactory.COOKED_BASS, Models.GENERATED);

        itemModelGenerator.register(ItemFactory.FUR_TROUT_BUCKET, Models.GENERATED);

        itemModelGenerator.register(ItemFactory.KOI_BUCKET, Models.GENERATED);
        itemModelGenerator.register(ItemFactory.KOI, Models.GENERATED);

        itemModelGenerator.register(ItemFactory.PERCH_BUCKET, Models.GENERATED);
        itemModelGenerator.register(ItemFactory.PERCH, Models.GENERATED);
        itemModelGenerator.register(ItemFactory.COOKED_PERCH, Models.GENERATED);

        itemModelGenerator.register(ItemFactory.MAHIMAHI_BUCKET, Models.GENERATED);
        itemModelGenerator.register(ItemFactory.MAHIMAHI, Models.GENERATED);
        itemModelGenerator.register(ItemFactory.COOKED_MAHIMAHI, Models.GENERATED);

        itemModelGenerator.register(ItemFactory.SNAPPER_BUCKET, Models.GENERATED);
        itemModelGenerator.register(ItemFactory.SNAPPER, Models.GENERATED);
        itemModelGenerator.register(ItemFactory.COOKED_SNAPPER, Models.GENERATED);

        //RUM is done via Manually
        itemModelGenerator.register(ItemFactory.CANNON_BALL_ITEM, Models.GENERATED);
        itemModelGenerator.register(ItemFactory.CANNON_ITEM, Models.CROSSBOW);
        itemModelGenerator.register(ItemFactory.PRISMERINE_ARROW, Models.GENERATED);

        itemModelGenerator.register(ItemFactory.SUNKEN_SKELETON_SPAWN, Models.GENERATED);
        itemModelGenerator.register(ItemFactory.SUNKEN_SKELETON2_SPAWN, Models.GENERATED);
        itemModelGenerator.register(ItemFactory.SKELETON_PIRATE_CAPTAIN_SPAWN, Models.GENERATED);
        itemModelGenerator.register(ItemFactory.METAL_SKELETON_SPAWN, Models.GENERATED);

        itemModelGenerator.register(ItemFactory.ANCIENT_DROWN_SPAWN, Models.GENERATED);
        //ANCIENT_TRIDENT is done via Manually

        itemModelGenerator.register(ItemFactory.PHANTOM_SKELETON_SPAWN, Models.GENERATED);

        //itemModelGenerator2.register(ItemFactory.DIAMOND_SHIELD, Models.);

        /*ItemModel.Unbaked unbakedShield = ItemModels.basic(itemModelGenerator2.upload(ItemFactory.DIAMOND_SHIELD, Models.GENERATED));
        ItemModel.Unbaked unbakedBlocking = ItemModels.basic(itemModelGenerator2.registerSubModel(ItemFactory.DIAMOND_SHIELD, "blocking", Models.GENERATED)); //ToFix
        itemModelGenerator.output.accept(
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
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator2) {

        this.blockStateModelGenerator = blockStateModelGenerator2;

        //Fungal Wood Type

        System.out.println("ModProvide: " + "fungal");

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

        generateGroupWoodFamilty("phantom", "", true); System.out.println("ModProvide: " + "Phantom");
        
        //Redwood Wood Type
        
        generateGroupWoodFamilty("redwood", "", true); System.out.println("ModProvide: " + "Redwood");
        
        //Maple Wood Type

        generateGroupWoodFamilty("maple", "", true);
        blockStateModelGenerator2.registerFlowerPotPlant(BlockFactory.callBlock("maple_sapling"), BlockFactory.callBlock("potted_maple_sapling"), BlockStateModelGenerator.CrossType.NOT_TINTED);
        
        //Fir Wood Type

        generateGroupWoodFamilty("fir", "", true);
        blockStateModelGenerator2.registerFlowerPotPlant(BlockFactory.callBlock("fir_sapling"), BlockFactory.callBlock("potted_fir_sapling"), BlockStateModelGenerator.CrossType.NOT_TINTED); //TintType.NOT_TINTED
        
        //Vanilla Wood Type 

        generateGroupVanillaAddition("oak");  System.out.println("ModProvide: " + "VANILLA");
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

        generateGlassIntercation("iron", ""); System.out.println("ModProvide: " + "METAL");
        generateGlassIntercation("copper", "");
        generateGlassIntercation("exposed_copper", "");
        generateGlassIntercation("oxidized_copper", "");
        generateGlassIntercation("weathered_copper", "");

        generateGroupBricksFamilty("andesite", ""); System.out.println("ModProvide: " + "STONE");
        generateGroupBricksFamilty("diorite", "");
        generateGroupBricksFamilty("granite", "");

        blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.POLISHED_ANDESITE).wall(BlockFactory.callBlock("polished_" + "andesite" + "_wall"));
        blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.POLISHED_DIORITE).wall(BlockFactory.callBlock("polished_" + "diorite" + "_wall"));
        blockStateModelGenerator.registerCubeAllModelTexturePool(Blocks.POLISHED_GRANITE).wall(BlockFactory.callBlock("polished_" + "granite" + "_wall"));
        
        registerPointedIce();  System.out.println("ModProvide: " + "ICE");

        generateBaseAndIntercationFamily("limestone", "", false, true, BlockFactory.callBlock("limestone"));
        generateBaseAndIntercationFamily("polished_limestone", "", false, true, BlockFactory.callBlock("polished_limestone"));  System.out.println("ModProvide: " + "LIMESTONE");
        generateGroupBricksFamilty("polished_limestone", "");

        blockStateModelGenerator.registerGlassAndPane(BlockFactory.callBlock("string_block"), BlockFactory.callBlock("string_gate"));
        blockStateModelGenerator.registerLantern(BlockFactory.callBlock("phantom_lantern"));
        blockStateModelGenerator.registerTorch(BlockFactory.callBlock("phantom_torch"), BlockFactory.callBlock("phantom_wall_torch"));

        // Iron Grate
        generateBaseAndIntercationFamily("iron_grate", "", false, false, BlockFactory.callBlock("iron_grate"));

        //Blue Mushrooom
        blockStateModelGenerator2.registerMushroomBlock(BlockFactory.callBlock("blue_mushroom_block"));


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

        blockStateModelGenerator.createLogTexturePool(BlockFactory.callBlock(blockName + "_log" + suffix)).log(BlockFactory.callBlock(blockName + "_log" + suffix)).wood(BlockFactory.callBlock(blockName + "_wood" + suffix));
        blockStateModelGenerator.createLogTexturePool(BlockFactory.callBlock("stripped_" + blockName + "_log" + suffix)).log(BlockFactory.callBlock("stripped_" + blockName + "_log" + suffix)).wood(BlockFactory.callBlock("stripped_" + blockName + "_wood" + suffix));
        
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
      BlockStateVariantMap.DoubleProperty<WeightedVariant, Direction, Thickness> doubleProperty = BlockStateVariantMap.models(Properties.VERTICAL_DIRECTION, Properties.THICKNESS);
      Thickness[] var2 = Thickness.values();
      int var3 = var2.length;

      int var4;
      Thickness thickness;
      for(var4 = 0; var4 < var3; ++var4) {
         thickness = var2[var4];
         doubleProperty.register(Direction.UP, thickness, this.getDripstoneVariant(Direction.UP, thickness));
      }

      var2 = Thickness.values();
      var3 = var2.length;

      for(var4 = 0; var4 < var3; ++var4) {
         thickness = var2[var4];
         doubleProperty.register(Direction.DOWN, thickness, this.getDripstoneVariant(Direction.DOWN, thickness));
      }

      blockStateModelGenerator.blockStateCollector.accept(VariantsBlockModelDefinitionCreator.of(BlockFactory.callBlock("pointed_ice")).with(doubleProperty));
   }

    public final WeightedVariant getDripstoneVariant(Direction direction, Thickness thickness) {
      String var10000 = direction.asString();
      String string = "_" + var10000 + "_" + thickness.asString();
      TextureMap textureMap = TextureMap.cross(TextureMap.getSubId(Blocks.POINTED_DRIPSTONE, string)); 
      //return createWeightedVariant(Models.POINTED_DRIPSTONE.upload(Blocks.POINTED_DRIPSTONE, string, textureMap, blockStateModelGenerator.modelCollector));
      return blockStateModelGenerator.createWeightedVariant(Models.POINTED_DRIPSTONE.upload(Blocks.POINTED_DRIPSTONE, string, textureMap, blockStateModelGenerator.modelCollector));
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

            blockStateModelGenerator.createLogTexturePool(BlockFactory.callBlock(Familybase + "_log" + varient));
            blockStateModelGenerator.createLogTexturePool(BlockFactory.callBlock("stripped_" + Familybase + "_log" + varient));
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
