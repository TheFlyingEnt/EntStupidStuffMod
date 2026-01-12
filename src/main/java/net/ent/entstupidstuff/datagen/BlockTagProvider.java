package net.ent.entstupidstuff.datagen;

import java.util.concurrent.CompletableFuture;

import net.ent.entstupidstuff.block.BlockFactory;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.tags.BlockTags;

public class BlockTagProvider extends FabricTagProvider.BlockTagProvider{

    public BlockTagProvider(FabricDataOutput output, CompletableFuture<Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void addTags(Provider wrapperLookup) {

        //Fungal Planks
        addWoodFamily("fungal", null, false, false);
        addWoodFamily("redwood", null, true, true);
        //addWoodFamily("desert_iron", null, true, false);
        addWoodFamily("maple", null, true, true);
        addWoodFamily("phantom", null, true, false);
        addWoodFamily("fir", null, true, true);

        // Fungal (Colored) Wood    
        for (String color : BlockFactory.COLORS) {addWoodFamily("fungal", color, false, false);}

        // Textured Wool
        for (String color : BlockFactory.COLORS) {addWoolFamily("textured_wool", color);}

        addVanillaStoneFamily("granite_brick");
        addVanillaStoneFamily("diorite_brick");
        addVanillaStoneFamily("andesite_brick");
        

         valueLookupBuilder(BlockTags.WALLS)
        .add((BlockFactory.callBlock("polished_andesite" + "_wall")))
        .add((BlockFactory.callBlock("polished_diorite" + "_wall")))
        .add((BlockFactory.callBlock("polished_granite" + "_wall")));

        addLimestoneStoneFamily();

        // Vanilla Wood
        addVanillaGlassDoor("oak");
        addVanillaGlassDoor("spruce");
        addVanillaGlassDoor("jungle");
        addVanillaGlassDoor("birch");
        addVanillaGlassDoor("dark_oak");
        addVanillaGlassDoor("acacia");
        addVanillaGlassDoor("mangrove");
        addVanillaGlassDoor("cherry");
        addVanillaGlassDoor("bamboo");
        addVanillaGlassDoor("warped");
        addVanillaGlassDoor("crimson");
        //addVanillaGlassDoor("pale_oak"); //FUTURE UPDATE

        addVanillaGlassDoorM("iron");
        addVanillaGlassDoorM("copper");
        addVanillaGlassDoorM("exposed_copper");
        addVanillaGlassDoorM("oxidized_copper");
        addVanillaGlassDoorM("weathered_copper");
        addVanillaGlassDoorM("waxed_copper");
        addVanillaGlassDoorM("waxed_exposed_copper");
        addVanillaGlassDoorM("waxed_oxidized_copper");
        addVanillaGlassDoorM("waxed_weathered_copper");

        valueLookupBuilder(BlockTags.MUSHROOM_GROW_BLOCK)
            .add((BlockFactory.callBlock("shroomium")));
        
    }

    public void addWoodFamily(String FamilyBase, String varient, Boolean isNatural, boolean alsoLeaves) {

        if (varient == null) {varient = "";}
        else {varient = "_" + varient;}

        if (isNatural) {
             valueLookupBuilder(BlockTags.OVERWORLD_NATURAL_LOGS).add((BlockFactory.callBlock(FamilyBase + "_log" + varient)));  
             valueLookupBuilder(BlockTags.LOGS_THAT_BURN).add((BlockFactory.callBlock(FamilyBase + "_log" + varient)));
             valueLookupBuilder(BlockTags.MINEABLE_WITH_AXE).add((BlockFactory.callBlock(FamilyBase + "_log" + varient)));
             valueLookupBuilder(BlockTags.LAVA_POOL_STONE_CANNOT_REPLACE).add((BlockFactory.callBlock(FamilyBase + "_log" + varient)));
             valueLookupBuilder(BlockTags.LOGS).add((BlockFactory.callBlock(FamilyBase + "_log" + varient)));
             valueLookupBuilder(BlockTags.SNAPS_GOAT_HORN).add((BlockFactory.callBlock(FamilyBase + "_log" + varient)));
             valueLookupBuilder(BlockTags.COMPLETES_FIND_TREE_TUTORIAL).add((BlockFactory.callBlock(FamilyBase + "_log" + varient)));
             valueLookupBuilder(BlockTags.PARROTS_SPAWNABLE_ON).add((BlockFactory.callBlock(FamilyBase + "_log" + varient)));
             valueLookupBuilder(BlockTags.MINEABLE_WITH_AXE).add((BlockFactory.callBlock(FamilyBase + "_log" + varient)));
             valueLookupBuilder(BlockTags.LOGS).add((BlockFactory.callBlock(FamilyBase + "_log" + varient)));

             valueLookupBuilder(BlockTags.OVERWORLD_NATURAL_LOGS).add((BlockFactory.callBlock(FamilyBase + "_wood" + varient)));  
             valueLookupBuilder(BlockTags.LOGS_THAT_BURN).add((BlockFactory.callBlock(FamilyBase + "_wood" + varient)));
             valueLookupBuilder(BlockTags.MINEABLE_WITH_AXE).add((BlockFactory.callBlock(FamilyBase + "_wood" + varient)));
             valueLookupBuilder(BlockTags.LAVA_POOL_STONE_CANNOT_REPLACE).add((BlockFactory.callBlock(FamilyBase + "_wood" + varient)));
             valueLookupBuilder(BlockTags.LOGS).add((BlockFactory.callBlock(FamilyBase + "_wood" + varient)));
             valueLookupBuilder(BlockTags.SNAPS_GOAT_HORN).add((BlockFactory.callBlock(FamilyBase + "_wood" + varient)));
             valueLookupBuilder(BlockTags.COMPLETES_FIND_TREE_TUTORIAL).add((BlockFactory.callBlock(FamilyBase + "_wood" + varient)));
             valueLookupBuilder(BlockTags.PARROTS_SPAWNABLE_ON).add((BlockFactory.callBlock(FamilyBase + "_wood" + varient)));
             valueLookupBuilder(BlockTags.MINEABLE_WITH_AXE).add((BlockFactory.callBlock(FamilyBase + "_wood" + varient)));
             valueLookupBuilder(BlockTags.LOGS).add((BlockFactory.callBlock(FamilyBase + "_wood" + varient)));

             valueLookupBuilder(BlockTags.OVERWORLD_NATURAL_LOGS).add((BlockFactory.callBlock("stripped_" + FamilyBase + "_log" + varient)));  
             valueLookupBuilder(BlockTags.LOGS_THAT_BURN).add((BlockFactory.callBlock("stripped_" + FamilyBase + "_log" + varient)));
             valueLookupBuilder(BlockTags.MINEABLE_WITH_AXE).add((BlockFactory.callBlock("stripped_" + FamilyBase + "_log" + varient)));
             valueLookupBuilder(BlockTags.LAVA_POOL_STONE_CANNOT_REPLACE).add((BlockFactory.callBlock("stripped_" + FamilyBase + "_log" + varient)));
             valueLookupBuilder(BlockTags.LOGS).add((BlockFactory.callBlock("stripped_" + FamilyBase + "_log" + varient)));
             valueLookupBuilder(BlockTags.SNAPS_GOAT_HORN).add((BlockFactory.callBlock("stripped_" + FamilyBase + "_log" + varient)));
             valueLookupBuilder(BlockTags.COMPLETES_FIND_TREE_TUTORIAL).add((BlockFactory.callBlock("stripped_" + FamilyBase + "_log" + varient)));
             valueLookupBuilder(BlockTags.PARROTS_SPAWNABLE_ON).add((BlockFactory.callBlock("stripped_" + FamilyBase + "_log" + varient)));
             valueLookupBuilder(BlockTags.MINEABLE_WITH_AXE).add((BlockFactory.callBlock("stripped_" + FamilyBase + "_log" + varient)));
             valueLookupBuilder(BlockTags.LOGS).add((BlockFactory.callBlock("stripped_" + FamilyBase + "_log" + varient)));

             valueLookupBuilder(BlockTags.OVERWORLD_NATURAL_LOGS).add((BlockFactory.callBlock("stripped_" + FamilyBase + "_wood" + varient)));  
             valueLookupBuilder(BlockTags.LOGS_THAT_BURN).add((BlockFactory.callBlock("stripped_" + FamilyBase + "_wood" + varient)));
             valueLookupBuilder(BlockTags.MINEABLE_WITH_AXE).add((BlockFactory.callBlock("stripped_" + FamilyBase + "_wood" + varient)));
             valueLookupBuilder(BlockTags.LAVA_POOL_STONE_CANNOT_REPLACE).add((BlockFactory.callBlock("stripped_" + FamilyBase + "_wood" + varient)));
             valueLookupBuilder(BlockTags.LOGS).add((BlockFactory.callBlock("stripped_" + FamilyBase + "_wood" + varient)));
             valueLookupBuilder(BlockTags.SNAPS_GOAT_HORN).add((BlockFactory.callBlock("stripped_" + FamilyBase + "_wood" + varient)));
             valueLookupBuilder(BlockTags.COMPLETES_FIND_TREE_TUTORIAL).add((BlockFactory.callBlock("stripped_" + FamilyBase + "_wood" + varient)));
             valueLookupBuilder(BlockTags.PARROTS_SPAWNABLE_ON).add((BlockFactory.callBlock("stripped_" + FamilyBase + "_wood" + varient)));
             valueLookupBuilder(BlockTags.MINEABLE_WITH_AXE).add((BlockFactory.callBlock("stripped_" + FamilyBase + "_wood" + varient)));
             valueLookupBuilder(BlockTags.LOGS).add((BlockFactory.callBlock("stripped_" + FamilyBase + "_wood" + varient)));

            if (alsoLeaves) {
                 valueLookupBuilder(BlockTags.LAVA_POOL_STONE_CANNOT_REPLACE).add((BlockFactory.callBlock(FamilyBase + "_leaves" + varient)));
                 valueLookupBuilder(BlockTags.MINEABLE_WITH_HOE).add((BlockFactory.callBlock(FamilyBase + "_leaves" + varient))); 
                 valueLookupBuilder(BlockTags.LEAVES).add((BlockFactory.callBlock(FamilyBase + "_leaves" + varient))); 
            }

        }

         valueLookupBuilder(BlockTags.PLANKS)
            .add((BlockFactory.callBlock(FamilyBase + "_planks" + varient)));
        
         valueLookupBuilder(BlockTags.WOODEN_BUTTONS)
            .add((BlockFactory.callBlock(FamilyBase + "_button" + varient)));
         valueLookupBuilder(BlockTags.WOODEN_DOORS)
            .add((BlockFactory.callBlock(FamilyBase + "_door" + varient)));
         valueLookupBuilder(BlockTags.WOODEN_FENCES)
            .add((BlockFactory.callBlock(FamilyBase + "_fence" + varient)));
         valueLookupBuilder(BlockTags.WOODEN_FENCES)
            .add((BlockFactory.callBlock(FamilyBase + "_fence_gate" + varient)));
         valueLookupBuilder(BlockTags.WOODEN_PRESSURE_PLATES)
            .add((BlockFactory.callBlock(FamilyBase + "_pressure_plate" + varient)));
         valueLookupBuilder(BlockTags.WOODEN_SLABS)
            .add((BlockFactory.callBlock(FamilyBase + "_slab" + varient)));
         valueLookupBuilder(BlockTags.WOODEN_STAIRS)
            .add((BlockFactory.callBlock(FamilyBase + "_stairs" + varient)));
         valueLookupBuilder(BlockTags.WOODEN_TRAPDOORS)
            .add((BlockFactory.callBlock(FamilyBase + "_trapdoor" + varient)));
         valueLookupBuilder(BlockTags.WOODEN_DOORS)
            .add((BlockFactory.callBlock(FamilyBase + "_glass_door" + varient)));
         valueLookupBuilder(BlockTags.WOODEN_TRAPDOORS)
            .add((BlockFactory.callBlock(FamilyBase + "_glass_trapdoor" + varient)));

        
         valueLookupBuilder(BlockTags.MINEABLE_WITH_AXE)
            .add((BlockFactory.callBlock(FamilyBase + "_planks" + varient)));
        
         valueLookupBuilder(BlockTags.MINEABLE_WITH_AXE)
            .add((BlockFactory.callBlock(FamilyBase + "_button" + varient)));
         valueLookupBuilder(BlockTags.MINEABLE_WITH_AXE)
            .add((BlockFactory.callBlock(FamilyBase + "_door" + varient)));
         valueLookupBuilder(BlockTags.MINEABLE_WITH_AXE)
            .add((BlockFactory.callBlock(FamilyBase + "_fence" + varient)));
         valueLookupBuilder(BlockTags.MINEABLE_WITH_AXE)
            .add((BlockFactory.callBlock(FamilyBase + "_fence_gate" + varient)));
         valueLookupBuilder(BlockTags.MINEABLE_WITH_AXE)
            .add((BlockFactory.callBlock(FamilyBase + "_pressure_plate" + varient)));
         valueLookupBuilder(BlockTags.MINEABLE_WITH_AXE)
            .add((BlockFactory.callBlock(FamilyBase + "_slab" + varient)));
         valueLookupBuilder(BlockTags.MINEABLE_WITH_AXE)
            .add((BlockFactory.callBlock(FamilyBase + "_stairs" + varient)));
         valueLookupBuilder(BlockTags.MINEABLE_WITH_AXE)
            .add((BlockFactory.callBlock(FamilyBase + "_trapdoor" + varient)));
         valueLookupBuilder(BlockTags.MINEABLE_WITH_AXE)
            .add((BlockFactory.callBlock(FamilyBase + "_glass_door" + varient)));
         valueLookupBuilder(BlockTags.MINEABLE_WITH_AXE)
            .add((BlockFactory.callBlock(FamilyBase + "_glass_trapdoor" + varient)));
        
        //Add Hanging Sign + Sign 

        /*FlammableBlockRegistry.getDefaultInstance()
            .add((BlockFactoryUpt.callBlock(FamilyBase + "_planks" + varient)), 5, 20);
        FlammableBlockRegistry.getDefaultInstance()
            .add((BlockFactoryUpt.callBlock(FamilyBase + "_button" + varient)), 5, 20);
        FlammableBlockRegistry.getDefaultInstance()
            .add((BlockFactoryUpt.callBlock(FamilyBase + "_fence" + varient)), 5, 20);
        FlammableBlockRegistry.getDefaultInstance()
            .add((BlockFactoryUpt.callBlock(FamilyBase + "_fence_gate" + varient)), 5, 20);
        FlammableBlockRegistry.getDefaultInstance()
            .add((BlockFactoryUpt.callBlock(FamilyBase + "_pressure_plate" + varient)), 5, 20);
        FlammableBlockRegistry.getDefaultInstance()
            .add((BlockFactoryUpt.callBlock(FamilyBase + "_slab" + varient)), 5, 20);
        FlammableBlockRegistry.getDefaultInstance()
            .add((BlockFactoryUpt.callBlock(FamilyBase + "_stairs" + varient)), 5, 20);
        FlammableBlockRegistry.getDefaultInstance()
            .add((BlockFactoryUpt.callBlock(FamilyBase + "_trapdoor" + varient)), 5, 20);
        FlammableBlockRegistry.getDefaultInstance()
            .add((BlockFactoryUpt.callBlock(FamilyBase + "_door" + varient)), 5, 20);
        FlammableBlockRegistry.getDefaultInstance()
            .add((BlockFactoryUpt.callBlock(FamilyBase + "_glass_door" + varient)), 5, 20);*/
        //Add Hanging Sign + Sign 


    }

    public void addWoolFamily(String FamilyBase, String color){
         valueLookupBuilder(BlockTags.WOOL)
            .add((BlockFactory.callBlock(FamilyBase + "_" + color)));

        FlammableBlockRegistry.getDefaultInstance()
            .add((BlockFactory.callBlock(FamilyBase + "_" + color)), 30, 60);
    }

    public void addVanillaGlassDoor(String FamilyBase){
         valueLookupBuilder(BlockTags.WOODEN_DOORS)
            .add((BlockFactory.callBlock(FamilyBase + "_glass_door")));

        FlammableBlockRegistry.getDefaultInstance()
            .add((BlockFactory.callBlock(FamilyBase + "_glass_door")), 5, 20);

         valueLookupBuilder(BlockTags.WOODEN_TRAPDOORS)
            .add((BlockFactory.callBlock(FamilyBase + "_glass_trapdoor")));

        FlammableBlockRegistry.getDefaultInstance()
            .add((BlockFactory.callBlock(FamilyBase + "_glass_trapdoor")), 5, 20);
    }

    public void addVanillaGlassDoorM(String FamilyBase){
         valueLookupBuilder(BlockTags.DOORS)
            .add((BlockFactory.callBlock(FamilyBase + "_glass_door")));

         valueLookupBuilder(BlockTags.MINEABLE_WITH_PICKAXE)
            .add((BlockFactory.callBlock(FamilyBase + "_glass_door")));

         valueLookupBuilder(BlockTags.TRAPDOORS)
            .add((BlockFactory.callBlock(FamilyBase + "_glass_trapdoor")));

         valueLookupBuilder(BlockTags.MINEABLE_WITH_PICKAXE)
            .add((BlockFactory.callBlock(FamilyBase + "_glass_trapdoor")));

        if (FamilyBase != "iron"){ //For Copper Doors
             valueLookupBuilder(BlockTags.MOB_INTERACTABLE_DOORS)
                .add((BlockFactory.callBlock(FamilyBase + "_glass_door")));

             valueLookupBuilder(BlockTags.INCORRECT_FOR_WOODEN_TOOL)
                .add((BlockFactory.callBlock(FamilyBase + "_glass_door")));

             valueLookupBuilder(BlockTags.INCORRECT_FOR_GOLD_TOOL)
                .add((BlockFactory.callBlock(FamilyBase + "_glass_door")));

             valueLookupBuilder(BlockTags.NEEDS_STONE_TOOL)
                .add((BlockFactory.callBlock(FamilyBase + "_glass_door")));

             valueLookupBuilder(BlockTags.INCORRECT_FOR_WOODEN_TOOL)
                .add((BlockFactory.callBlock(FamilyBase + "_glass_trapdoor")));

             valueLookupBuilder(BlockTags.INCORRECT_FOR_GOLD_TOOL)
                .add((BlockFactory.callBlock(FamilyBase + "_glass_trapdoor")));

             valueLookupBuilder(BlockTags.NEEDS_STONE_TOOL)
                .add((BlockFactory.callBlock(FamilyBase + "_glass_trapdoor")));
        }
    }

    

    public void addVanillaStoneFamily(String FamilyBase) {
         valueLookupBuilder(BlockTags.STAIRS)
            .add((BlockFactory.callBlock(FamilyBase + "_stairs")));
         valueLookupBuilder(BlockTags.SLABS)
            .add((BlockFactory.callBlock(FamilyBase + "_slab")));
         valueLookupBuilder(BlockTags.WALLS)
            .add((BlockFactory.callBlock(FamilyBase + "_wall")));

         valueLookupBuilder(BlockTags.MINEABLE_WITH_PICKAXE)
            .add((BlockFactory.callBlock(FamilyBase + "s")));
         valueLookupBuilder(BlockTags.MINEABLE_WITH_PICKAXE)
            .add((BlockFactory.callBlock(FamilyBase + "_stairs")));
         valueLookupBuilder(BlockTags.MINEABLE_WITH_PICKAXE)
            .add((BlockFactory.callBlock(FamilyBase + "_slab")));
         valueLookupBuilder(BlockTags.MINEABLE_WITH_PICKAXE)
            .add((BlockFactory.callBlock(FamilyBase + "_wall")));
         valueLookupBuilder(BlockTags.MINEABLE_WITH_PICKAXE)
            .add((BlockFactory.callBlock("cracked_" + FamilyBase + "s")));
         valueLookupBuilder(BlockTags.MINEABLE_WITH_PICKAXE)
            .add((BlockFactory.callBlock(FamilyBase + "_chiseled")));
    }

    public void addLimestoneStoneFamily() {

        String FamilyBase = "limestone";

         valueLookupBuilder(BlockTags.STAIRS)
            .add((BlockFactory.callBlock(FamilyBase + "_stairs")));
         valueLookupBuilder(BlockTags.SLABS)
            .add((BlockFactory.callBlock(FamilyBase + "_slab")));
         valueLookupBuilder(BlockTags.WALLS)
            .add((BlockFactory.callBlock(FamilyBase + "_wall")));

         valueLookupBuilder(BlockTags.MINEABLE_WITH_PICKAXE)
            .add((BlockFactory.callBlock(FamilyBase + "_stairs")));
         valueLookupBuilder(BlockTags.MINEABLE_WITH_PICKAXE)
            .add((BlockFactory.callBlock(FamilyBase + "_slab")));
         valueLookupBuilder(BlockTags.MINEABLE_WITH_PICKAXE)
            .add((BlockFactory.callBlock(FamilyBase + "_wall")));

        FamilyBase = "polished_limestone";

         valueLookupBuilder(BlockTags.STAIRS)
            .add((BlockFactory.callBlock(FamilyBase + "_stairs")));
         valueLookupBuilder(BlockTags.SLABS)
            .add((BlockFactory.callBlock(FamilyBase + "_slab")));
         valueLookupBuilder(BlockTags.WALLS)
            .add((BlockFactory.callBlock(FamilyBase + "_wall")));

         valueLookupBuilder(BlockTags.MINEABLE_WITH_PICKAXE)
            .add((BlockFactory.callBlock(FamilyBase + "_stairs")));
         valueLookupBuilder(BlockTags.MINEABLE_WITH_PICKAXE)
            .add((BlockFactory.callBlock(FamilyBase + "_slab")));
         valueLookupBuilder(BlockTags.MINEABLE_WITH_PICKAXE)
            .add((BlockFactory.callBlock(FamilyBase + "_wall")));

        FamilyBase = "polished_limestone_brick";

         valueLookupBuilder(BlockTags.STONE_BRICKS)
            .add((BlockFactory.callBlock(FamilyBase + "s")));
         valueLookupBuilder(BlockTags.STAIRS)
            .add((BlockFactory.callBlock(FamilyBase + "_stairs")));
         valueLookupBuilder(BlockTags.SLABS)
            .add((BlockFactory.callBlock(FamilyBase + "_slab")));
         valueLookupBuilder(BlockTags.WALLS)
            .add((BlockFactory.callBlock(FamilyBase + "_wall")));



         valueLookupBuilder(BlockTags.MINEABLE_WITH_PICKAXE)
            .add((BlockFactory.callBlock(FamilyBase + "s")));
         valueLookupBuilder(BlockTags.MINEABLE_WITH_PICKAXE)
            .add((BlockFactory.callBlock(FamilyBase + "_stairs")));
         valueLookupBuilder(BlockTags.MINEABLE_WITH_PICKAXE)
            .add((BlockFactory.callBlock(FamilyBase + "_slab")));
         valueLookupBuilder(BlockTags.MINEABLE_WITH_PICKAXE)
            .add((BlockFactory.callBlock(FamilyBase + "_wall")));
         valueLookupBuilder(BlockTags.MINEABLE_WITH_PICKAXE)
            .add((BlockFactory.callBlock("cracked_" + FamilyBase + "s")));
         valueLookupBuilder(BlockTags.MINEABLE_WITH_PICKAXE)
            .add((BlockFactory.callBlock(FamilyBase + "_chiseled")));
    }


    @Deprecated
    public void addWoodTags(String base, String endTag){ 
         valueLookupBuilder(BlockTags.WOODEN_BUTTONS).add((BlockFactory.callBlock(base + "_button" + endTag)));
         valueLookupBuilder(BlockTags.WOODEN_DOORS).add((BlockFactory.callBlock(base + "_door" + endTag)));
         valueLookupBuilder(BlockTags.WOODEN_FENCES).add((BlockFactory.callBlock(base + "_fence" + endTag)));
         valueLookupBuilder(BlockTags.WOODEN_FENCES).add((BlockFactory.callBlock(base + "_fence_gate" + endTag)));
         valueLookupBuilder(BlockTags.WOODEN_PRESSURE_PLATES).add((BlockFactory.callBlock(base + "_pressure_plate" + endTag)));
         valueLookupBuilder(BlockTags.WOODEN_SLABS).add((BlockFactory.callBlock(base + "_slab" + endTag)));
         valueLookupBuilder(BlockTags.WOODEN_STAIRS).add((BlockFactory.callBlock(base + "_stairs" + endTag)));
         valueLookupBuilder(BlockTags.WOODEN_TRAPDOORS).add((BlockFactory.callBlock(base + "_trapdoor" + endTag)));

        FlammableBlockRegistry.getDefaultInstance().add((BlockFactory.callBlock(base + "_button" + endTag)), 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add((BlockFactory.callBlock(base + "_fence" + endTag)), 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add((BlockFactory.callBlock(base + "_fence_gate" + endTag)), 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add((BlockFactory.callBlock(base + "_pressure_plate" + endTag)), 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add((BlockFactory.callBlock(base + "_slab" + endTag)), 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add((BlockFactory.callBlock(base + "_stairs" + endTag)), 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add((BlockFactory.callBlock(base + "_trapdoor" + endTag)), 5, 20);

    }
    
}
