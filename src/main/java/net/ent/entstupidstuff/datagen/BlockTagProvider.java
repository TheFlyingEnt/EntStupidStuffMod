package net.ent.entstupidstuff.datagen;

import java.util.concurrent.CompletableFuture;

import net.ent.entstupidstuff.block.BlockFactory;
import net.ent.entstupidstuff.item.ModTags;
//import net.fabricmc.devlaunchinjector.Main;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.tags.BlockTags;
//import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class BlockTagProvider extends FabricTagProvider.BlockTagProvider {

    public BlockTagProvider(FabricDataOutput output, CompletableFuture<Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void addTags(Provider wrapperLookup) {

        // Added Tags:
        // this.tag(BlockTags.OVERWORLD_NATURAL_LOGS)
        // this.tag(BlockTags.FLOWER_POTS)

        // Updated:
        groupWoodFamilty("redwood", "", true);
        groupWoodFamilty("fir", "", true);
        groupWoodFamilty("maple", "", true);
        groupWoodFamilty("phantom", "", false);
        valueLookupBuilder(BlockTags.LANTERNS).add(
                BlockFactory.callBlock("phantom_lantern"));

        groupWoodFamilty("fungal", "", false, false);
        for (String color : BlockFactory.COLORS) {
            groupWoodFamilty("fungal", "_" + color, false, false);
        }

        valueLookupBuilder(BlockTags.FLOWER_POTS).add(
                BlockFactory.callBlock("potted_blue_mushroom"));

        valueLookupBuilder(BlockTags.MINEABLE_WITH_AXE).add(
                BlockFactory.callBlock("blue_mushroom_block"));

        valueLookupBuilder(BlockTags.REPLACEABLE_BY_MUSHROOMS).add(
                BlockFactory.callBlock("blue_mushroom_block"));

        valueLookupBuilder(BlockTags.MUSHROOM_GROW_BLOCK).add(
                BlockFactory.callBlock("shroomium"));

        valueLookupBuilder(BlockTags.SNIFFER_DIGGABLE_BLOCK).add(
                BlockFactory.callBlock("shroomium"));

        valueLookupBuilder(BlockTags.BIG_DRIPLEAF_PLACEABLE).add(
                BlockFactory.callBlock("shroomium"));

        valueLookupBuilder(BlockTags.MINEABLE_WITH_SHOVEL).add(
                BlockFactory.callBlock("shroomium"));

        valueLookupBuilder(BlockTags.CRYSTAL_SOUND_BLOCKS).add(
                BlockFactory.callBlock("blue_crystal_block"));

        valueLookupBuilder(BlockTags.MINEABLE_WITH_PICKAXE).add(
                BlockFactory.callBlock("blue_crystal_block"));

        valueLookupBuilder(BlockTags.VIBRATION_RESONATORS).add(
                BlockFactory.callBlock("blue_crystal_block"));

        valueLookupBuilder(BlockTags.INSIDE_STEP_SOUND_BLOCKS).add(
                BlockFactory.callBlock("azure_flower_bed"));

        // # Adding Andersite, Diorite and Granite
        valueLookupBuilder(BlockTags.BASE_STONE_OVERWORLD).add(
                BlockFactory.callBlock("andesite_bricks"),
                BlockFactory.callBlock("polished_andesite" + "_wall"),
                BlockFactory.callBlock("granite_bricks"),
                BlockFactory.callBlock("polished_granite" + "_wall"),
                BlockFactory.callBlock("diorite_bricks"),
                BlockFactory.callBlock("polished_diorite" + "_wall"));
        groupStoneFamily("granite_brick", true);
        groupStoneFamily("diorite_brick", true);
        ;
        groupStoneFamily("diorite_brick", true);

        // # Adding Limestone and Limestone Bricks
        valueLookupBuilder(BlockTags.BASE_STONE_OVERWORLD).add(
                BlockFactory.callBlock("limestone"),
                BlockFactory.callBlock("polished_limestone"),
                BlockFactory.callBlock("polished_limestone_bricks"));
        groupStoneFamily("limestone", false);
        groupStoneFamily("polished_limestone", false);
        groupStoneFamily("polished_limestone_brick", true);

        // # Adding IronGates
        valueLookupBuilder(BlockTags.NEEDS_STONE_TOOL).add(
                BlockFactory.callBlock("iron_grate"),
                BlockFactory.callBlock("iron_grate_stairs"),
                BlockFactory.callBlock("iron_grate_slab"));
        valueLookupBuilder(BlockTags.MINEABLE_WITH_PICKAXE).add(
                BlockFactory.callBlock("iron_grate"),
                BlockFactory.callBlock("iron_grate_stairs"),
                BlockFactory.callBlock("iron_grate_slab"));

        // ## Adding Vanilla Additions
        // MOB_INTERACTABLE_DOORS
        // MINEABLE_WITH_PICKAXE

        valueLookupBuilder(BlockTags.MINEABLE_WITH_PICKAXE).add(
                BlockFactory.callBlock("copper_glass_door"),
                BlockFactory.callBlock("exposed_copper_glass_door"),
                BlockFactory.callBlock("oxidized_copper_glass_door"),
                BlockFactory.callBlock("weathered_copper_glass_door"),
                BlockFactory.callBlock("waxed_copper_glass_door"),
                BlockFactory.callBlock("waxed_exposed_copper_glass_door"),
                BlockFactory.callBlock("waxed_oxidized_copper_glass_door"),
                BlockFactory.callBlock("waxed_weathered_copper_glass_door"),
                BlockFactory.callBlock("iron_glass_door"),
                BlockFactory.callBlock("copper_glass_trapdoor"),
                BlockFactory.callBlock("exposed_copper_glass_trapdoor"),
                BlockFactory.callBlock("oxidized_copper_glass_trapdoor"),
                BlockFactory.callBlock("weathered_copper_glass_trapdoor"),
                BlockFactory.callBlock("waxed_copper_glass_trapdoor"),
                BlockFactory.callBlock("waxed_exposed_copper_glass_trapdoor"),
                BlockFactory.callBlock("waxed_oxidized_copper_glass_trapdoor"),
                BlockFactory.callBlock("waxed_weathered_copper_glass_trapdoor"),
                BlockFactory.callBlock("iron_glass_trapdoor"));

        valueLookupBuilder(BlockTags.MOB_INTERACTABLE_DOORS).add(
                BlockFactory.callBlock("copper_glass_door"),
                BlockFactory.callBlock("exposed_copper_glass_door"),
                BlockFactory.callBlock("oxidized_copper_glass_door"),
                BlockFactory.callBlock("weathered_copper_glass_door"),
                BlockFactory.callBlock("waxed_copper_glass_door"),
                BlockFactory.callBlock("waxed_exposed_copper_glass_door"),
                BlockFactory.callBlock("waxed_oxidized_copper_glass_door"),
                BlockFactory.callBlock("waxed_weathered_copper_glass_door"),
                BlockFactory.callBlock("oak_glass_door"),
                BlockFactory.callBlock("spruce_glass_door"),
                BlockFactory.callBlock("jungle_glass_door"),
                BlockFactory.callBlock("birch_glass_door"),
                BlockFactory.callBlock("dark_oak_glass_door"),
                BlockFactory.callBlock("acacia_glass_door"),
                BlockFactory.callBlock("mangrove_glass_door"),
                BlockFactory.callBlock("cherry_glass_door"),
                BlockFactory.callBlock("bamboo_glass_door"),
                BlockFactory.callBlock("pale_oak_glass_door"),
                BlockFactory.callBlock("crimson_glass_door"),
                BlockFactory.callBlock("warped_glass_door"));

        valueLookupBuilder(BlockTags.WOODEN_DOORS).add(
                BlockFactory.callBlock("oak_glass_door"),
                BlockFactory.callBlock("spruce_glass_door"),
                BlockFactory.callBlock("jungle_glass_door"),
                BlockFactory.callBlock("birch_glass_door"),
                BlockFactory.callBlock("dark_oak_glass_door"),
                BlockFactory.callBlock("acacia_glass_door"),
                BlockFactory.callBlock("mangrove_glass_door"),
                BlockFactory.callBlock("cherry_glass_door"),
                BlockFactory.callBlock("bamboo_glass_door"),
                BlockFactory.callBlock("pale_oak_glass_door"),
                BlockFactory.callBlock("crimson_glass_door"),
                BlockFactory.callBlock("warped_glass_door"));

        valueLookupBuilder(BlockTags.WOODEN_TRAPDOORS).add(
                BlockFactory.callBlock("oak_glass_trapdoor"),
                BlockFactory.callBlock("spruce_glass_trapdoor"),
                BlockFactory.callBlock("jungle_glass_trapdoor"),
                BlockFactory.callBlock("birch_glass_trapdoor"),
                BlockFactory.callBlock("dark_oak_glass_trapdoor"),
                BlockFactory.callBlock("acacia_glass_trapdoor"),
                BlockFactory.callBlock("mangrove_glass_trapdoor"),
                BlockFactory.callBlock("cherry_glass_trapdoor"),
                BlockFactory.callBlock("bamboo_glass_trapdoor"),
                BlockFactory.callBlock("pale_oak_glass_trapdoor"),
                BlockFactory.callBlock("crimson_glass_trapdoor"),
                BlockFactory.callBlock("warped_glass_trapdoor"));

        MosicFamily("oak", "");
        MosicFamily("spruce", "");
        MosicFamily("jungle", "");
        MosicFamily("birch", "");
        MosicFamily("dark_oak", "");
        MosicFamily("acacia", "");
        MosicFamily("mangrove", "");
        MosicFamily("cherry", "");
        MosicFamily("pale_oak", "");
        MosicFamily("crimson", "");
        MosicFamily("warped", "");

        // Vanilla Wood
        addVanillaGlassDoorM("iron");
        addVanillaGlassDoorM("copper");
        addVanillaGlassDoorM("exposed_copper");
        addVanillaGlassDoorM("oxidized_copper");
        addVanillaGlassDoorM("weathered_copper");
        addVanillaGlassDoorM("waxed_copper");
        addVanillaGlassDoorM("waxed_exposed_copper");
        addVanillaGlassDoorM("waxed_oxidized_copper");
        addVanillaGlassDoorM("waxed_weathered_copper");

        // # Adding Custom
        valueLookupBuilder(ModTags.SHROOMIUM_REPLACE)
            .addTag(BlockTags.BASE_STONE_OVERWORLD)
            .add(BlockFactory.callBlock("shroomium"))
            .add(Blocks.MUD);

    }

    public void groupWoodFamilty(String MainName, String Varient, Boolean natural) {
        groupWoodFamilty(MainName, Varient, true, natural);
    }

    public void groupWoodFamilty(String MainName, String Varient, Boolean log, Boolean natural) {
        valueLookupBuilder(BlockTags.PLANKS).add(
                BlockFactory.callBlock(MainName + "_planks" + Varient));

        valueLookupBuilder(BlockTags.WOODEN_STAIRS).add(
                BlockFactory.callBlock(MainName + "_stairs" + Varient),
                BlockFactory.callBlock(MainName + "_mosaic_stairs" + Varient));

        valueLookupBuilder(BlockTags.WOODEN_SLABS).add(
                BlockFactory.callBlock(MainName + "_slab" + Varient),
                BlockFactory.callBlock(MainName + "_mosaic_slab" + Varient));

        valueLookupBuilder(BlockTags.WOODEN_FENCES).add(
                BlockFactory.callBlock(MainName + "_fence" + Varient));

        valueLookupBuilder(BlockTags.FENCE_GATES).add(
                BlockFactory.callBlock(MainName + "_fence_gate" + Varient));

        valueLookupBuilder(BlockTags.WOODEN_DOORS).add(
                BlockFactory.callBlock(MainName + "_door" + Varient),
                BlockFactory.callBlock(MainName + "_glass_door" + Varient));

        valueLookupBuilder(BlockTags.WOODEN_TRAPDOORS).add(
                BlockFactory.callBlock(MainName + "_trapdoor" + Varient),
                BlockFactory.callBlock(MainName + "_glass_trapdoor" + Varient));

        valueLookupBuilder(BlockTags.WOODEN_PRESSURE_PLATES).add(
                BlockFactory.callBlock(MainName + "_pressure_plate" + Varient));

        if (log) {
            valueLookupBuilder(BlockTags.LOGS_THAT_BURN).add(
                    BlockFactory.callBlock(MainName + "_log" + Varient),
                    BlockFactory.callBlock("stripped_" + MainName + "_log" + Varient),
                    BlockFactory.callBlock(MainName + "_wood" + Varient),
                    BlockFactory.callBlock("stripped_" + MainName + "_wood" + Varient));

            valueLookupBuilder(BlockTags.LOGS).add(
                    BlockFactory.callBlock(MainName + "_log" + Varient),
                    BlockFactory.callBlock("stripped_" + MainName + "_log" + Varient),
                    BlockFactory.callBlock(MainName + "_wood" + Varient),
                    BlockFactory.callBlock("stripped_" + MainName + "_wood" + Varient));

        }

        MosicFamily(MainName, Varient);

        if (natural) {
            valueLookupBuilder(BlockTags.LEAVES).add(
                    BlockFactory.callBlock(MainName + "_leaves" + Varient));

            valueLookupBuilder(BlockTags.SAPLINGS).add(
                    BlockFactory.callBlock(MainName + "_sapling" + Varient));

            valueLookupBuilder(BlockTags.FLOWER_POTS).add(
                    BlockFactory.callBlock("potted_" + MainName + "_sapling" + Varient));
        }

    }

    public void MosicFamily(String MainName, String Varient) {
        valueLookupBuilder(BlockTags.MINEABLE_WITH_AXE).add(
                BlockFactory.callBlock(MainName + "_mosaic" + Varient),
                BlockFactory.callBlock(MainName + "_mosaic_stairs" + Varient),
                BlockFactory.callBlock(MainName + "_mosaic_slab" + Varient));
    }

    public void groupStoneFamily(String MainName, Boolean Brick) {

        valueLookupBuilder(BlockTags.MINEABLE_WITH_PICKAXE).add(
                BlockFactory.callBlock(MainName + "_stairs"),
                BlockFactory.callBlock(MainName + "_slab"),
                BlockFactory.callBlock(MainName + "_wall"));

        if (Brick) {
            valueLookupBuilder(BlockTags.MINEABLE_WITH_PICKAXE).add(
                    BlockFactory.callBlock(MainName + "_chiseled"),
                    BlockFactory.callBlock("cracked_" + MainName + "s"));
        }

    }


    public void addVanillaGlassDoorM(String FamilyBase) {
        valueLookupBuilder(BlockTags.DOORS)
                .add((BlockFactory.callBlock(FamilyBase + "_glass_door")));

        valueLookupBuilder(BlockTags.MINEABLE_WITH_PICKAXE)
                .add((BlockFactory.callBlock(FamilyBase + "_glass_door")));

        valueLookupBuilder(BlockTags.TRAPDOORS)
                .add((BlockFactory.callBlock(FamilyBase + "_glass_trapdoor")));

        valueLookupBuilder(BlockTags.MINEABLE_WITH_PICKAXE)
                .add((BlockFactory.callBlock(FamilyBase + "_glass_trapdoor")));

        if (FamilyBase != "iron") { // For Copper Doors
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

}
