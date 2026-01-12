package net.ent.entstupidstuff.datagen;

import java.util.concurrent.CompletableFuture;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.block.BlockFactory;
import net.ent.entstupidstuff.block.ModBlocks;
import net.ent.entstupidstuff.item.ItemFactory;
import net.ent.entstupidstuff.item.ModItemTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class ItemTagProvider extends FabricTagProvider.ItemTagProvider {
    /*
     * Vanilla Reference:
     * - VanillaItemTagProvider
     */

    public ItemTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider wrapperLookup) {

        addWoodFamily("fungal", null, false);
        for (String color : BlockFactory.COLORS) {addWoodFamily("fungal", color, false);}
        for (String color : BlockFactory.COLORS) {addWoolFamily("textured_wool", color);}
        /*for (String tm : MBlockFactory.TOOL_MATERIAL) {addCombatFamily("tm");}*/
        for (String wood : ModBlocks.V_WOOD_VARIENTS) {addVanillaGlassDoor(wood);}
        addVanillaGlassDoor("iron");

        for (String c : ModBlocks.COPPER_VARIENTS) {addVanillaGlassDoor(c);}

        addCombatFamily("wooden");
        addCombatFamily("golden");
        addCombatFamily("stone");
        addCombatFamily("iron");
        addCombatFamily("diamond");
        addCombatFamily("netherite");

        addWoodFamily("redwood", null, true);
        //addWoodFamily("desert_iron", null, true);
        addWoodFamily("maple", null, true);
        addWoodFamily("fir", null, true);
        addWoodFamily("phantom", null, true);

        valueLookupBuilder(ItemTags.LEAVES)
        .add((BlockFactory.callBlock("maple" + "_leaves").asItem()));

        //Combat and Food

        valueLookupBuilder(ModItemTags.HAMMER_ENCHANTABLE).addTag(ModItemTags.HAMMER);

        valueLookupBuilder(ModItemTags.HAMMER)
        .add(ItemFactory.callItem("wooden_hammer"))
        .add(ItemFactory.callItem("stone_hammer"))
        .add(ItemFactory.callItem("iron_hammer"))
        .add(ItemFactory.callItem("golden_hammer"))
        .add(ItemFactory.callItem("diamond_hammer"))
        .add(ItemFactory.callItem("netherite_hammer"));

        this.valueLookupBuilder(ItemTags.CLUSTER_MAX_HARVESTABLES).addTag(ModItemTags.HAMMER);

        valueLookupBuilder(ItemTags.DURABILITY_ENCHANTABLE)
		.addTag(ModItemTags.HAMMER)
        .add(ItemFactory.callItem("ancient_trident"));

        valueLookupBuilder(ItemTags.MINING_ENCHANTABLE)
        .addTag(ModItemTags.HAMMER);

        valueLookupBuilder(ItemTags.MINING_LOOT_ENCHANTABLE)
        .addTag(ModItemTags.HAMMER);

        valueLookupBuilder(ItemTags.TRIDENT_ENCHANTABLE)
        .add(ItemFactory.callItem("ancient_trident"));

        valueLookupBuilder(ItemTags.CAT_FOOD).add(
            ItemFactory.MACKEREL, 
            ItemFactory.ALLIGATOR_GAR, 
            ItemFactory.BASS
        );

        valueLookupBuilder(ItemTags.PIGLIN_LOVED)
        .add(ItemFactory.callItem("golden_hammer"));












        
        //Setting For Fungal wood
        /*setWoodGroupTags("fungal", "");

        //Setting for Color Types (Colored Fungal + Texture Wool)
        for (String inputC : MBlockFactoryUpt.COLORS) {
            setWoodGroupTags("fungal", "_" + inputC);
            valueLookupBuilder(ItemTags.WOOL).add(MBlockFactoryUpt.callBlock("textured_wool_" + inputC).asItem());
        }*/
    }

    private static TagKey<Item> of(String id) {
		return TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, id));
	}

    public void addWoodFamily(String FamilyBase, String varient, Boolean isNatural) {

        if (varient == null) {varient = "";}
        else {varient = "_" + varient;}

        if (isNatural) {

            //Logs: log, logs_that_burn

            valueLookupBuilder(ItemTags.LOGS_THAT_BURN)
                .add((BlockFactory.callBlock(FamilyBase + "_log" + varient).asItem()));
            valueLookupBuilder(ItemTags.LOGS)
                .add((BlockFactory.callBlock(FamilyBase + "_log" + varient).asItem()));
            valueLookupBuilder(ItemTags.LOGS_THAT_BURN)
                .add((BlockFactory.callBlock(FamilyBase + "_wood" + varient).asItem()));
            valueLookupBuilder(ItemTags.LOGS)
                .add((BlockFactory.callBlock(FamilyBase + "_wood" + varient).asItem()));
            valueLookupBuilder(ItemTags.LOGS_THAT_BURN)
                .add((BlockFactory.callBlock("stripped_" + FamilyBase + "_log" + varient).asItem()));
            valueLookupBuilder(ItemTags.LOGS)
                .add((BlockFactory.callBlock("stripped_" + FamilyBase + "_log" + varient).asItem()));
            valueLookupBuilder(ItemTags.LOGS_THAT_BURN)
                .add((BlockFactory.callBlock("stripped_" + FamilyBase + "_wood" + varient).asItem()));
            valueLookupBuilder(ItemTags.LOGS)
                .add((BlockFactory.callBlock("stripped_" + FamilyBase + "_wood" + varient).asItem()));

        }

        valueLookupBuilder(ItemTags.PLANKS)
            .add((BlockFactory.callBlock(FamilyBase + "_planks" + varient).asItem()));
        valueLookupBuilder(ItemTags.WOODEN_BUTTONS)
            .add((BlockFactory.callBlock(FamilyBase + "_button" + varient).asItem()));
        valueLookupBuilder(ItemTags.WOODEN_DOORS)
            .add((BlockFactory.callBlock(FamilyBase + "_door" + varient).asItem()));
        valueLookupBuilder(ItemTags.WOODEN_FENCES)
            .add((BlockFactory.callBlock(FamilyBase + "_fence" + varient).asItem()));
        valueLookupBuilder(ItemTags.WOODEN_FENCES)
            .add((BlockFactory.callBlock(FamilyBase + "_fence_gate" + varient).asItem()));
        valueLookupBuilder(ItemTags.WOODEN_PRESSURE_PLATES)
            .add((BlockFactory.callBlock(FamilyBase + "_pressure_plate" + varient).asItem()));
        valueLookupBuilder(ItemTags.WOODEN_SLABS)
            .add((BlockFactory.callBlock(FamilyBase + "_slab" + varient).asItem()));
        valueLookupBuilder(ItemTags.WOODEN_STAIRS)
            .add((BlockFactory.callBlock(FamilyBase + "_stairs" + varient).asItem()));
        valueLookupBuilder(ItemTags.WOODEN_TRAPDOORS)
            .add((BlockFactory.callBlock(FamilyBase + "_trapdoor" + varient).asItem()));
        valueLookupBuilder(ItemTags.WOODEN_DOORS)
            .add((BlockFactory.callBlock(FamilyBase + "_glass_door" + varient).asItem()));
        //Add Hanging Sign + Sign 

    }

    public void addVanillaGlassDoor(String FamilyBase) {
        valueLookupBuilder(ItemTags.WOODEN_DOORS)
            .add((BlockFactory.callBlock(FamilyBase + "_glass_door").asItem()));
    }

    public void addWoolFamily(String FamilyBase, String color){
        valueLookupBuilder(ItemTags.WOOL)
            .add((BlockFactory.callBlock(FamilyBase + "_" + color).asItem()));
    }

    public void addCombatFamily(String tm){
        //Adding new Tags

        //valueLookupBuilder(LONG_SWORD).add(ItemFactory.callItem(tm + "_long_sword"));
        //valueLookupBuilder(BATTLE_AXE).add(ItemFactory.callItem(tm + "_battle_axe"));
        //valueLookupBuilder(DAGGER).add(ItemFactory.callItem(tm + "_dagger"));
        //valueLookupBuilder(KATANA).add(ItemFactory.callItem(tm + "_katana"));
        //valueLookupBuilder(HAMMER).add(ItemFactory.callItem(tm + "_hammer"));

        //Enchantments
        valueLookupBuilder(ItemTags.SWORD_ENCHANTABLE).add(ItemFactory.callItem(tm + "_long_sword"));
        valueLookupBuilder(ItemTags.SWORD_ENCHANTABLE).add(ItemFactory.callItem(tm + "_dagger"));
        //valueLookupBuilder(ItemTags.SWORD_ENCHANTABLE).add(ItemFactory.callItem(tm + "_katana"));

        //Battle Axe Enchantments
        //valueLookupBuilder(ItemTags.SHARP_WEAPON_ENCHANTABLE).add(ItemFactory.callItem(tm + "_battle_axe"));

        //WEAPON ENCHANTABLE
        valueLookupBuilder(ItemTags.WEAPON_ENCHANTABLE).add(ItemFactory.callItem(tm + "_long_sword"));
        //valueLookupBuilder(ItemTags.WEAPON_ENCHANTABLE).add(ItemFactory.callItem(tm + "_battle_axe"));
        valueLookupBuilder(ItemTags.WEAPON_ENCHANTABLE).add(ItemFactory.callItem(tm + "_dagger"));
        //valueLookupBuilder(ItemTags.WEAPON_ENCHANTABLE).add(ItemFactory.callItem(tm + "_katana"));
        valueLookupBuilder(ItemTags.WEAPON_ENCHANTABLE).add(ItemFactory.callItem(tm + "_hammer"));

        //FIRE ASPECT ENCHANTABLE
        valueLookupBuilder(ItemTags.FIRE_ASPECT_ENCHANTABLE).add(ItemFactory.callItem(tm + "_long_sword"));
        //valueLookupBuilder(ItemTags.FIRE_ASPECT_ENCHANTABLE).add(ItemFactory.callItem(tm + "_battle_axe"));
        valueLookupBuilder(ItemTags.FIRE_ASPECT_ENCHANTABLE).add(ItemFactory.callItem(tm + "_dagger"));

        //Add Long Bows
    }











    //Used to Set a Wood Blocks Tags
    @Deprecated
    public void setWoodGroupTags(String baseName, String entTag) {

        valueLookupBuilder(ItemTags.PLANKS).add(BlockFactory.callBlock(baseName + "_planks" + entTag).asItem());
        valueLookupBuilder(ItemTags.WOODEN_STAIRS).add(BlockFactory.callBlock(baseName + "_stairs" + entTag).asItem());
        valueLookupBuilder(ItemTags.WOODEN_SLABS).add(BlockFactory.callBlock(baseName + "_slab" + entTag).asItem());
        valueLookupBuilder(ItemTags.WOODEN_BUTTONS).add(BlockFactory.callBlock(baseName + "_button" + entTag).asItem());
        valueLookupBuilder(ItemTags.WOODEN_PRESSURE_PLATES).add(BlockFactory.callBlock(baseName + "_pressure_plate" + entTag).asItem());
        valueLookupBuilder(ItemTags.WOODEN_BUTTONS).add(BlockFactory.callBlock(baseName + "_fence" + entTag).asItem());
        valueLookupBuilder(ItemTags.WOODEN_FENCES).add(BlockFactory.callBlock(baseName + "_fence_gate" + entTag).asItem());
        valueLookupBuilder(ItemTags.WOODEN_TRAPDOORS).add(BlockFactory.callBlock(baseName + "_trapdoor" + entTag).asItem());
        valueLookupBuilder(ItemTags.WOODEN_DOORS).add(BlockFactory.callBlock(baseName + "_door" + entTag).asItem());

        valueLookupBuilder(ItemTags.PLANKS).add(BlockFactory.callBlock(baseName + "_planks" + entTag).asItem());
        valueLookupBuilder(ItemTags.STAIRS).add(BlockFactory.callBlock(baseName + "_stairs" + entTag).asItem());
        valueLookupBuilder(ItemTags.SLABS).add(BlockFactory.callBlock(baseName + "_slab" + entTag).asItem());
        valueLookupBuilder(ItemTags.BUTTONS).add(BlockFactory.callBlock(baseName + "_button" + entTag).asItem());
        valueLookupBuilder(ItemTags.FENCES).add(BlockFactory.callBlock(baseName + "_fence" + entTag).asItem());
        valueLookupBuilder(ItemTags.FENCE_GATES).add(BlockFactory.callBlock(baseName + "_fence_gate" + entTag).asItem());
        valueLookupBuilder(ItemTags.TRAPDOORS).add(BlockFactory.callBlock(baseName + "_trapdoor" + entTag).asItem());
        valueLookupBuilder(ItemTags.DOORS).add(BlockFactory.callBlock(baseName + "_door" + entTag).asItem());
    }

}
