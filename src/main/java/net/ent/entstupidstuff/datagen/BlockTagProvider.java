package net.ent.entstupidstuff.datagen;

import java.util.concurrent.CompletableFuture;

import net.ent.entstupidstuff.block.BlockFactoryUpt;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.minecraft.registry.RegistryWrapper.WrapperLookup;
import net.minecraft.registry.tag.BlockTags;

public class BlockTagProvider extends FabricTagProvider.BlockTagProvider{

    public BlockTagProvider(FabricDataOutput output, CompletableFuture<WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(WrapperLookup wrapperLookup) {

        //Fungal Planks
        addWoodFamily("fungal", null, false, false);
        addWoodFamily("redwood", null, true, true);
        //addWoodFamily("desert_iron", null, true, false);
        addWoodFamily("maple", null, true, true);
        addWoodFamily("phantom", null, true, false);
        addWoodFamily("fir", null, true, true);

        // Fungal (Colored) Wood    
        for (String color : BlockFactoryUpt.COLORS) {addWoodFamily("fungal", color, false, false);}

        // Textured Wool
        for (String color : BlockFactoryUpt.COLORS) {addWoolFamily("textured_wool", color);}

        addVanillaStoneFamily("granite_brick");
        addVanillaStoneFamily("diorite_brick");
        addVanillaStoneFamily("andesite_brick");
        

        getOrCreateTagBuilder(BlockTags.WALLS)
        .add((BlockFactoryUpt.callBlock("polished_andesite" + "_wall")))
        .add((BlockFactoryUpt.callBlock("polished_diorite" + "_wall")))
        .add((BlockFactoryUpt.callBlock("polished_granite" + "_wall")));

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
        
    }

    public void addWoodFamily(String FamilyBase, String varient, Boolean isNatural, boolean alsoLeaves) {

        if (varient == null) {varient = "";}
        else {varient = "_" + varient;}

        if (isNatural) {
            getOrCreateTagBuilder(BlockTags.OVERWORLD_NATURAL_LOGS).add((BlockFactoryUpt.callBlock(FamilyBase + "_log" + varient)));  
            getOrCreateTagBuilder(BlockTags.LOGS_THAT_BURN).add((BlockFactoryUpt.callBlock(FamilyBase + "_log" + varient)));
            getOrCreateTagBuilder(BlockTags.AXE_MINEABLE).add((BlockFactoryUpt.callBlock(FamilyBase + "_log" + varient)));
            getOrCreateTagBuilder(BlockTags.LAVA_POOL_STONE_CANNOT_REPLACE).add((BlockFactoryUpt.callBlock(FamilyBase + "_log" + varient)));
            getOrCreateTagBuilder(BlockTags.LOGS).add((BlockFactoryUpt.callBlock(FamilyBase + "_log" + varient)));
            getOrCreateTagBuilder(BlockTags.SNAPS_GOAT_HORN).add((BlockFactoryUpt.callBlock(FamilyBase + "_log" + varient)));
            getOrCreateTagBuilder(BlockTags.COMPLETES_FIND_TREE_TUTORIAL).add((BlockFactoryUpt.callBlock(FamilyBase + "_log" + varient)));
            getOrCreateTagBuilder(BlockTags.PARROTS_SPAWNABLE_ON).add((BlockFactoryUpt.callBlock(FamilyBase + "_log" + varient)));
            getOrCreateTagBuilder(BlockTags.AXE_MINEABLE).add((BlockFactoryUpt.callBlock(FamilyBase + "_log" + varient)));
            getOrCreateTagBuilder(BlockTags.LOGS).add((BlockFactoryUpt.callBlock(FamilyBase + "_log" + varient)));

            getOrCreateTagBuilder(BlockTags.OVERWORLD_NATURAL_LOGS).add((BlockFactoryUpt.callBlock(FamilyBase + "_wood" + varient)));  
            getOrCreateTagBuilder(BlockTags.LOGS_THAT_BURN).add((BlockFactoryUpt.callBlock(FamilyBase + "_wood" + varient)));
            getOrCreateTagBuilder(BlockTags.AXE_MINEABLE).add((BlockFactoryUpt.callBlock(FamilyBase + "_wood" + varient)));
            getOrCreateTagBuilder(BlockTags.LAVA_POOL_STONE_CANNOT_REPLACE).add((BlockFactoryUpt.callBlock(FamilyBase + "_wood" + varient)));
            getOrCreateTagBuilder(BlockTags.LOGS).add((BlockFactoryUpt.callBlock(FamilyBase + "_wood" + varient)));
            getOrCreateTagBuilder(BlockTags.SNAPS_GOAT_HORN).add((BlockFactoryUpt.callBlock(FamilyBase + "_wood" + varient)));
            getOrCreateTagBuilder(BlockTags.COMPLETES_FIND_TREE_TUTORIAL).add((BlockFactoryUpt.callBlock(FamilyBase + "_wood" + varient)));
            getOrCreateTagBuilder(BlockTags.PARROTS_SPAWNABLE_ON).add((BlockFactoryUpt.callBlock(FamilyBase + "_wood" + varient)));
            getOrCreateTagBuilder(BlockTags.AXE_MINEABLE).add((BlockFactoryUpt.callBlock(FamilyBase + "_wood" + varient)));
            getOrCreateTagBuilder(BlockTags.LOGS).add((BlockFactoryUpt.callBlock(FamilyBase + "_wood" + varient)));

            getOrCreateTagBuilder(BlockTags.OVERWORLD_NATURAL_LOGS).add((BlockFactoryUpt.callBlock("stripped_" + FamilyBase + "_log" + varient)));  
            getOrCreateTagBuilder(BlockTags.LOGS_THAT_BURN).add((BlockFactoryUpt.callBlock("stripped_" + FamilyBase + "_log" + varient)));
            getOrCreateTagBuilder(BlockTags.AXE_MINEABLE).add((BlockFactoryUpt.callBlock("stripped_" + FamilyBase + "_log" + varient)));
            getOrCreateTagBuilder(BlockTags.LAVA_POOL_STONE_CANNOT_REPLACE).add((BlockFactoryUpt.callBlock("stripped_" + FamilyBase + "_log" + varient)));
            getOrCreateTagBuilder(BlockTags.LOGS).add((BlockFactoryUpt.callBlock("stripped_" + FamilyBase + "_log" + varient)));
            getOrCreateTagBuilder(BlockTags.SNAPS_GOAT_HORN).add((BlockFactoryUpt.callBlock("stripped_" + FamilyBase + "_log" + varient)));
            getOrCreateTagBuilder(BlockTags.COMPLETES_FIND_TREE_TUTORIAL).add((BlockFactoryUpt.callBlock("stripped_" + FamilyBase + "_log" + varient)));
            getOrCreateTagBuilder(BlockTags.PARROTS_SPAWNABLE_ON).add((BlockFactoryUpt.callBlock("stripped_" + FamilyBase + "_log" + varient)));
            getOrCreateTagBuilder(BlockTags.AXE_MINEABLE).add((BlockFactoryUpt.callBlock("stripped_" + FamilyBase + "_log" + varient)));
            getOrCreateTagBuilder(BlockTags.LOGS).add((BlockFactoryUpt.callBlock("stripped_" + FamilyBase + "_log" + varient)));

            getOrCreateTagBuilder(BlockTags.OVERWORLD_NATURAL_LOGS).add((BlockFactoryUpt.callBlock("stripped_" + FamilyBase + "_wood" + varient)));  
            getOrCreateTagBuilder(BlockTags.LOGS_THAT_BURN).add((BlockFactoryUpt.callBlock("stripped_" + FamilyBase + "_wood" + varient)));
            getOrCreateTagBuilder(BlockTags.AXE_MINEABLE).add((BlockFactoryUpt.callBlock("stripped_" + FamilyBase + "_wood" + varient)));
            getOrCreateTagBuilder(BlockTags.LAVA_POOL_STONE_CANNOT_REPLACE).add((BlockFactoryUpt.callBlock("stripped_" + FamilyBase + "_wood" + varient)));
            getOrCreateTagBuilder(BlockTags.LOGS).add((BlockFactoryUpt.callBlock("stripped_" + FamilyBase + "_wood" + varient)));
            getOrCreateTagBuilder(BlockTags.SNAPS_GOAT_HORN).add((BlockFactoryUpt.callBlock("stripped_" + FamilyBase + "_wood" + varient)));
            getOrCreateTagBuilder(BlockTags.COMPLETES_FIND_TREE_TUTORIAL).add((BlockFactoryUpt.callBlock("stripped_" + FamilyBase + "_wood" + varient)));
            getOrCreateTagBuilder(BlockTags.PARROTS_SPAWNABLE_ON).add((BlockFactoryUpt.callBlock("stripped_" + FamilyBase + "_wood" + varient)));
            getOrCreateTagBuilder(BlockTags.AXE_MINEABLE).add((BlockFactoryUpt.callBlock("stripped_" + FamilyBase + "_wood" + varient)));
            getOrCreateTagBuilder(BlockTags.LOGS).add((BlockFactoryUpt.callBlock("stripped_" + FamilyBase + "_wood" + varient)));

            if (alsoLeaves) {
                getOrCreateTagBuilder(BlockTags.LAVA_POOL_STONE_CANNOT_REPLACE).add((BlockFactoryUpt.callBlock(FamilyBase + "_leaves" + varient)));
                getOrCreateTagBuilder(BlockTags.HOE_MINEABLE).add((BlockFactoryUpt.callBlock(FamilyBase + "_leaves" + varient))); 
                getOrCreateTagBuilder(BlockTags.LEAVES).add((BlockFactoryUpt.callBlock(FamilyBase + "_leaves" + varient))); 
            }

        }

        getOrCreateTagBuilder(BlockTags.PLANKS)
            .add((BlockFactoryUpt.callBlock(FamilyBase + "_planks" + varient)));
        
        getOrCreateTagBuilder(BlockTags.WOODEN_BUTTONS)
            .add((BlockFactoryUpt.callBlock(FamilyBase + "_button" + varient)));
        getOrCreateTagBuilder(BlockTags.WOODEN_DOORS)
            .add((BlockFactoryUpt.callBlock(FamilyBase + "_door" + varient)));
        getOrCreateTagBuilder(BlockTags.WOODEN_FENCES)
            .add((BlockFactoryUpt.callBlock(FamilyBase + "_fence" + varient)));
        getOrCreateTagBuilder(BlockTags.WOODEN_FENCES)
            .add((BlockFactoryUpt.callBlock(FamilyBase + "_fence_gate" + varient)));
        getOrCreateTagBuilder(BlockTags.WOODEN_PRESSURE_PLATES)
            .add((BlockFactoryUpt.callBlock(FamilyBase + "_pressure_plate" + varient)));
        getOrCreateTagBuilder(BlockTags.WOODEN_SLABS)
            .add((BlockFactoryUpt.callBlock(FamilyBase + "_slab" + varient)));
        getOrCreateTagBuilder(BlockTags.WOODEN_STAIRS)
            .add((BlockFactoryUpt.callBlock(FamilyBase + "_stairs" + varient)));
        getOrCreateTagBuilder(BlockTags.WOODEN_TRAPDOORS)
            .add((BlockFactoryUpt.callBlock(FamilyBase + "_trapdoor" + varient)));
        getOrCreateTagBuilder(BlockTags.WOODEN_DOORS)
            .add((BlockFactoryUpt.callBlock(FamilyBase + "_glass_door" + varient)));
        getOrCreateTagBuilder(BlockTags.WOODEN_TRAPDOORS)
            .add((BlockFactoryUpt.callBlock(FamilyBase + "_glass_trapdoor" + varient)));

        
        getOrCreateTagBuilder(BlockTags.AXE_MINEABLE)
            .add((BlockFactoryUpt.callBlock(FamilyBase + "_planks" + varient)));
        
        getOrCreateTagBuilder(BlockTags.AXE_MINEABLE)
            .add((BlockFactoryUpt.callBlock(FamilyBase + "_button" + varient)));
        getOrCreateTagBuilder(BlockTags.AXE_MINEABLE)
            .add((BlockFactoryUpt.callBlock(FamilyBase + "_door" + varient)));
        getOrCreateTagBuilder(BlockTags.AXE_MINEABLE)
            .add((BlockFactoryUpt.callBlock(FamilyBase + "_fence" + varient)));
        getOrCreateTagBuilder(BlockTags.AXE_MINEABLE)
            .add((BlockFactoryUpt.callBlock(FamilyBase + "_fence_gate" + varient)));
        getOrCreateTagBuilder(BlockTags.AXE_MINEABLE)
            .add((BlockFactoryUpt.callBlock(FamilyBase + "_pressure_plate" + varient)));
        getOrCreateTagBuilder(BlockTags.AXE_MINEABLE)
            .add((BlockFactoryUpt.callBlock(FamilyBase + "_slab" + varient)));
        getOrCreateTagBuilder(BlockTags.AXE_MINEABLE)
            .add((BlockFactoryUpt.callBlock(FamilyBase + "_stairs" + varient)));
        getOrCreateTagBuilder(BlockTags.AXE_MINEABLE)
            .add((BlockFactoryUpt.callBlock(FamilyBase + "_trapdoor" + varient)));
        getOrCreateTagBuilder(BlockTags.AXE_MINEABLE)
            .add((BlockFactoryUpt.callBlock(FamilyBase + "_glass_door" + varient)));
        getOrCreateTagBuilder(BlockTags.AXE_MINEABLE)
            .add((BlockFactoryUpt.callBlock(FamilyBase + "_glass_trapdoor" + varient)));
        
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
        getOrCreateTagBuilder(BlockTags.WOOL)
            .add((BlockFactoryUpt.callBlock(FamilyBase + "_" + color)));

        FlammableBlockRegistry.getDefaultInstance()
            .add((BlockFactoryUpt.callBlock(FamilyBase + "_" + color)), 30, 60);
    }

    public void addVanillaGlassDoor(String FamilyBase){
        getOrCreateTagBuilder(BlockTags.WOODEN_DOORS)
            .add((BlockFactoryUpt.callBlock(FamilyBase + "_glass_door")));

        FlammableBlockRegistry.getDefaultInstance()
            .add((BlockFactoryUpt.callBlock(FamilyBase + "_glass_door")), 5, 20);

        getOrCreateTagBuilder(BlockTags.WOODEN_TRAPDOORS)
            .add((BlockFactoryUpt.callBlock(FamilyBase + "_glass_trapdoor")));

        FlammableBlockRegistry.getDefaultInstance()
            .add((BlockFactoryUpt.callBlock(FamilyBase + "_glass_trapdoor")), 5, 20);
    }

    public void addVanillaGlassDoorM(String FamilyBase){
        getOrCreateTagBuilder(BlockTags.DOORS)
            .add((BlockFactoryUpt.callBlock(FamilyBase + "_glass_door")));

        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
            .add((BlockFactoryUpt.callBlock(FamilyBase + "_glass_door")));

        getOrCreateTagBuilder(BlockTags.TRAPDOORS)
            .add((BlockFactoryUpt.callBlock(FamilyBase + "_glass_trapdoor")));

        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
            .add((BlockFactoryUpt.callBlock(FamilyBase + "_glass_trapdoor")));

        if (FamilyBase != "iron"){ //For Copper Doors
            getOrCreateTagBuilder(BlockTags.MOB_INTERACTABLE_DOORS)
                .add((BlockFactoryUpt.callBlock(FamilyBase + "_glass_door")));

            getOrCreateTagBuilder(BlockTags.INCORRECT_FOR_WOODEN_TOOL)
                .add((BlockFactoryUpt.callBlock(FamilyBase + "_glass_door")));

            getOrCreateTagBuilder(BlockTags.INCORRECT_FOR_GOLD_TOOL)
                .add((BlockFactoryUpt.callBlock(FamilyBase + "_glass_door")));

            getOrCreateTagBuilder(BlockTags.NEEDS_STONE_TOOL)
                .add((BlockFactoryUpt.callBlock(FamilyBase + "_glass_door")));

            getOrCreateTagBuilder(BlockTags.INCORRECT_FOR_WOODEN_TOOL)
                .add((BlockFactoryUpt.callBlock(FamilyBase + "_glass_trapdoor")));

            getOrCreateTagBuilder(BlockTags.INCORRECT_FOR_GOLD_TOOL)
                .add((BlockFactoryUpt.callBlock(FamilyBase + "_glass_trapdoor")));

            getOrCreateTagBuilder(BlockTags.NEEDS_STONE_TOOL)
                .add((BlockFactoryUpt.callBlock(FamilyBase + "_glass_trapdoor")));
        }
    }

    

    public void addVanillaStoneFamily(String FamilyBase) {
        getOrCreateTagBuilder(BlockTags.STAIRS)
            .add((BlockFactoryUpt.callBlock(FamilyBase + "_stairs")));
        getOrCreateTagBuilder(BlockTags.SLABS)
            .add((BlockFactoryUpt.callBlock(FamilyBase + "_slab")));
        getOrCreateTagBuilder(BlockTags.WALLS)
            .add((BlockFactoryUpt.callBlock(FamilyBase + "_wall")));

        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
            .add((BlockFactoryUpt.callBlock(FamilyBase + "s")));
        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
            .add((BlockFactoryUpt.callBlock(FamilyBase + "_stairs")));
        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
            .add((BlockFactoryUpt.callBlock(FamilyBase + "_slab")));
        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
            .add((BlockFactoryUpt.callBlock(FamilyBase + "_wall")));
        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
            .add((BlockFactoryUpt.callBlock("cracked_" + FamilyBase + "s")));
        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
            .add((BlockFactoryUpt.callBlock(FamilyBase + "_chiseled")));
    }

    public void addLimestoneStoneFamily() {

        String FamilyBase = "limestone";

        getOrCreateTagBuilder(BlockTags.STAIRS)
            .add((BlockFactoryUpt.callBlock(FamilyBase + "_stairs")));
        getOrCreateTagBuilder(BlockTags.SLABS)
            .add((BlockFactoryUpt.callBlock(FamilyBase + "_slab")));
        getOrCreateTagBuilder(BlockTags.WALLS)
            .add((BlockFactoryUpt.callBlock(FamilyBase + "_wall")));

        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
            .add((BlockFactoryUpt.callBlock(FamilyBase + "_stairs")));
        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
            .add((BlockFactoryUpt.callBlock(FamilyBase + "_slab")));
        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
            .add((BlockFactoryUpt.callBlock(FamilyBase + "_wall")));

        FamilyBase = "polished_limestone";

        getOrCreateTagBuilder(BlockTags.STAIRS)
            .add((BlockFactoryUpt.callBlock(FamilyBase + "_stairs")));
        getOrCreateTagBuilder(BlockTags.SLABS)
            .add((BlockFactoryUpt.callBlock(FamilyBase + "_slab")));
        getOrCreateTagBuilder(BlockTags.WALLS)
            .add((BlockFactoryUpt.callBlock(FamilyBase + "_wall")));

        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
            .add((BlockFactoryUpt.callBlock(FamilyBase + "_stairs")));
        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
            .add((BlockFactoryUpt.callBlock(FamilyBase + "_slab")));
        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
            .add((BlockFactoryUpt.callBlock(FamilyBase + "_wall")));

        FamilyBase = "polished_limestone_brick";

        getOrCreateTagBuilder(BlockTags.STONE_BRICKS)
            .add((BlockFactoryUpt.callBlock(FamilyBase + "s")));
        getOrCreateTagBuilder(BlockTags.STAIRS)
            .add((BlockFactoryUpt.callBlock(FamilyBase + "_stairs")));
        getOrCreateTagBuilder(BlockTags.SLABS)
            .add((BlockFactoryUpt.callBlock(FamilyBase + "_slab")));
        getOrCreateTagBuilder(BlockTags.WALLS)
            .add((BlockFactoryUpt.callBlock(FamilyBase + "_wall")));



        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
            .add((BlockFactoryUpt.callBlock(FamilyBase + "s")));
        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
            .add((BlockFactoryUpt.callBlock(FamilyBase + "_stairs")));
        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
            .add((BlockFactoryUpt.callBlock(FamilyBase + "_slab")));
        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
            .add((BlockFactoryUpt.callBlock(FamilyBase + "_wall")));
        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
            .add((BlockFactoryUpt.callBlock("cracked_" + FamilyBase + "s")));
        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
            .add((BlockFactoryUpt.callBlock(FamilyBase + "_chiseled")));
    }


    @Deprecated
    public void addWoodTags(String base, String endTag){ 
        getOrCreateTagBuilder(BlockTags.WOODEN_BUTTONS).add((BlockFactoryUpt.callBlock(base + "_button" + endTag)));
        getOrCreateTagBuilder(BlockTags.WOODEN_DOORS).add((BlockFactoryUpt.callBlock(base + "_door" + endTag)));
        getOrCreateTagBuilder(BlockTags.WOODEN_FENCES).add((BlockFactoryUpt.callBlock(base + "_fence" + endTag)));
        getOrCreateTagBuilder(BlockTags.WOODEN_FENCES).add((BlockFactoryUpt.callBlock(base + "_fence_gate" + endTag)));
        getOrCreateTagBuilder(BlockTags.WOODEN_PRESSURE_PLATES).add((BlockFactoryUpt.callBlock(base + "_pressure_plate" + endTag)));
        getOrCreateTagBuilder(BlockTags.WOODEN_SLABS).add((BlockFactoryUpt.callBlock(base + "_slab" + endTag)));
        getOrCreateTagBuilder(BlockTags.WOODEN_STAIRS).add((BlockFactoryUpt.callBlock(base + "_stairs" + endTag)));
        getOrCreateTagBuilder(BlockTags.WOODEN_TRAPDOORS).add((BlockFactoryUpt.callBlock(base + "_trapdoor" + endTag)));

        FlammableBlockRegistry.getDefaultInstance().add((BlockFactoryUpt.callBlock(base + "_button" + endTag)), 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add((BlockFactoryUpt.callBlock(base + "_fence" + endTag)), 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add((BlockFactoryUpt.callBlock(base + "_fence_gate" + endTag)), 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add((BlockFactoryUpt.callBlock(base + "_pressure_plate" + endTag)), 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add((BlockFactoryUpt.callBlock(base + "_slab" + endTag)), 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add((BlockFactoryUpt.callBlock(base + "_stairs" + endTag)), 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add((BlockFactoryUpt.callBlock(base + "_trapdoor" + endTag)), 5, 20);

    }
    
}
