package net.ent.entstupidstuff.datagen;

import java.util.concurrent.CompletableFuture;

import net.ent.entstupidstuff.block.BlockFactory;
import net.ent.entstupidstuff.item.ItemFactory;
import net.ent.entstupidstuff.item.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;

public class ItemTagProvider extends FabricTagProvider.ItemTagProvider {
    /*
     * Vanilla Reference:
     * - VanillaItemTagProvider
     */

    public ItemTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void addTags(Provider wrapperLookup) {

        groupWoodFamilty("redwood", "", true);
        groupWoodFamilty("fir", "", true);
        groupWoodFamilty("maple", "", true);
        groupWoodFamilty("phantom", "", false);
        valueLookupBuilder(ItemTags.LANTERNS).add(
                ItemFactory.callItem("phantom_lantern"));

        groupWoodFamilty("fungal", "", false, false);
        for (String color : BlockFactory.COLORS) {
            groupWoodFamilty("fungal", "_" + color, false, false);
        }

        valueLookupBuilder(ItemTags.WOODEN_DOORS).add(
                ItemFactory.callItem("oak_glass_door"),
                ItemFactory.callItem("spruce_glass_door"),
                ItemFactory.callItem("jungle_glass_door"),
                ItemFactory.callItem("birch_glass_door"),
                ItemFactory.callItem("dark_oak_glass_door"),
                ItemFactory.callItem("acacia_glass_door"),
                ItemFactory.callItem("mangrove_glass_door"),
                ItemFactory.callItem("cherry_glass_door"),
                ItemFactory.callItem("bamboo_glass_door"),
                ItemFactory.callItem("pale_oak_glass_door"),
                ItemFactory.callItem("crimson_glass_door"),
                ItemFactory.callItem("warped_glass_door"));

        valueLookupBuilder(ItemTags.WOODEN_TRAPDOORS).add(
                ItemFactory.callItem("oak_glass_trapdoor"),
                ItemFactory.callItem("spruce_glass_trapdoor"),
                ItemFactory.callItem("jungle_glass_trapdoor"),
                ItemFactory.callItem("birch_glass_trapdoor"),
                ItemFactory.callItem("dark_oak_glass_trapdoor"),
                ItemFactory.callItem("acacia_glass_trapdoor"),
                ItemFactory.callItem("mangrove_glass_trapdoor"),
                ItemFactory.callItem("cherry_glass_trapdoor"),
                ItemFactory.callItem("bamboo_glass_trapdoor"),
                ItemFactory.callItem("pale_oak_glass_trapdoor"),
                ItemFactory.callItem("crimson_glass_trapdoor"),
                ItemFactory.callItem("warped_glass_trapdoor"));

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

        //Combat and Food

        valueLookupBuilder(ModTags.HAMMER)
        .add(ItemFactory.callItem("wooden_hammer"))
        .add(ItemFactory.callItem("stone_hammer"))
        .add(ItemFactory.callItem("iron_hammer"))
        .add(ItemFactory.callItem("golden_hammer"))
        .add(ItemFactory.callItem("diamond_hammer"))
        .add(ItemFactory.callItem("netherite_hammer"));

        this.valueLookupBuilder(ItemTags.CLUSTER_MAX_HARVESTABLES).addTag(ModTags.HAMMER);

        valueLookupBuilder(ItemTags.DURABILITY_ENCHANTABLE)
		.addTag(ModTags.HAMMER)
        .add(ItemFactory.callItem("ancient_trident"));

        valueLookupBuilder(ItemTags.MINING_ENCHANTABLE)
        .addTag(ModTags.HAMMER);

        valueLookupBuilder(ItemTags.MINING_LOOT_ENCHANTABLE)
        .addTag(ModTags.HAMMER);

        valueLookupBuilder(ItemTags.TRIDENT_ENCHANTABLE)
        .add(ItemFactory.callItem("ancient_trident"));

        valueLookupBuilder(ItemTags.CAT_FOOD).add(
            ItemFactory.MACKEREL, 
            ItemFactory.ALLIGATOR_GAR, 
            ItemFactory.BASS
        );

        valueLookupBuilder(ItemTags.PIGLIN_LOVED)
        .add(ItemFactory.callItem("golden_hammer"));

        valueLookupBuilder(ModTags.MUSHROOM_CRAFTING).add(
            Blocks.MUSHROOM_STEM.asItem(),
            Blocks.RED_MUSHROOM_BLOCK.asItem(),
            BlockFactory.callBlock("blue_mushroom_block").asItem(),
            Blocks.BROWN_MUSHROOM_BLOCK.asItem()
        );

        valueLookupBuilder(ModTags.SPOREPER_DROP_MUSIC_DISCS)//.addTag(ItemTags.CREEPER_DROP_MUSIC_DISCS)
        .add(
            Items.MUSIC_DISC_13,
				Items.MUSIC_DISC_CAT,
				Items.MUSIC_DISC_BLOCKS,
				Items.MUSIC_DISC_CHIRP,
				Items.MUSIC_DISC_FAR,
				Items.MUSIC_DISC_MALL,
				Items.MUSIC_DISC_MELLOHI,
				Items.MUSIC_DISC_STAL,
				Items.MUSIC_DISC_STRAD,
				Items.MUSIC_DISC_WARD,
				Items.MUSIC_DISC_11,
				Items.MUSIC_DISC_WAIT,
            ItemFactory.MUSIC_DISC_FUNGALDELIC
        );



        for (String color : BlockFactory.COLORS) {
            valueLookupBuilder(ItemTags.WOOL).add(BlockFactory.callBlock("glowing_silk_wool_" + color).asItem());
            valueLookupBuilder(ItemTags.WOOL_CARPETS).add(BlockFactory.callBlock("glowing_silk_wool_" + color + "_carpet").asItem());
        }

        

    }

    public void groupWoodFamilty(String MainName, String Varient, Boolean natural) {
        groupWoodFamilty(MainName, Varient, true, natural);
    }

    public void groupWoodFamilty(String MainName, String Varient, Boolean log, Boolean natural) {
        valueLookupBuilder(ItemTags.PLANKS).add(
                ItemFactory.callItem(MainName + "_planks" + Varient));

        valueLookupBuilder(ItemTags.WOODEN_STAIRS).add(
                ItemFactory.callItem(MainName + "_stairs" + Varient),
                ItemFactory.callItem(MainName + "_mosaic_stairs" + Varient));

        valueLookupBuilder(ItemTags.WOODEN_SLABS).add(
                ItemFactory.callItem(MainName + "_slab" + Varient),
                ItemFactory.callItem(MainName + "_mosaic_slab" + Varient));

        valueLookupBuilder(ItemTags.WOODEN_FENCES).add(
                ItemFactory.callItem(MainName + "_fence" + Varient));

        valueLookupBuilder(ItemTags.FENCE_GATES).add(
                ItemFactory.callItem(MainName + "_fence_gate" + Varient));

        valueLookupBuilder(ItemTags.WOODEN_DOORS).add(
                ItemFactory.callItem(MainName + "_door" + Varient),
                ItemFactory.callItem(MainName + "_glass_door" + Varient));

        valueLookupBuilder(ItemTags.WOODEN_TRAPDOORS).add(
                ItemFactory.callItem(MainName + "_trapdoor" + Varient),
                ItemFactory.callItem(MainName + "_glass_trapdoor" + Varient));

        valueLookupBuilder(ItemTags.WOODEN_PRESSURE_PLATES).add(
                ItemFactory.callItem(MainName + "_pressure_plate" + Varient));

        if (log) {
            valueLookupBuilder(ItemTags.LOGS_THAT_BURN).add(
                    ItemFactory.callItem(MainName + "_log" + Varient),
                    ItemFactory.callItem("stripped_" + MainName + "_log" + Varient),
                    ItemFactory.callItem(MainName + "_wood" + Varient),
                    ItemFactory.callItem("stripped_" + MainName + "_wood" + Varient));

            valueLookupBuilder(ItemTags.LOGS).add(
                    ItemFactory.callItem(MainName + "_log" + Varient),
                    ItemFactory.callItem("stripped_" + MainName + "_log" + Varient),
                    ItemFactory.callItem(MainName + "_wood" + Varient),
                    ItemFactory.callItem("stripped_" + MainName + "_wood" + Varient));

        }

        if (natural) {
            valueLookupBuilder(ItemTags.LEAVES).add(
                    ItemFactory.callItem(MainName + "_leaves" + Varient));

            valueLookupBuilder(ItemTags.SAPLINGS).add(
                    ItemFactory.callItem(MainName + "_sapling" + Varient));
        }

    }

    public void addVanillaGlassDoorM(String FamilyBase) {
        valueLookupBuilder(ItemTags.DOORS)
            .add((ItemFactory.callItem(FamilyBase + "_glass_door")));

        valueLookupBuilder(ItemTags.TRAPDOORS)
            .add((ItemFactory.callItem(FamilyBase + "_glass_trapdoor")));
    }

}
