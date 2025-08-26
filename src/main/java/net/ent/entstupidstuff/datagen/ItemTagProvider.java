package net.ent.entstupidstuff.datagen;

import java.util.concurrent.CompletableFuture;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.block.BlockFactoryUpt;
import net.ent.entstupidstuff.block.ModBlocks;
import net.ent.entstupidstuff.item.ItemFactory;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper.WrapperLookup;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ItemTagProvider extends FabricTagProvider.ItemTagProvider{

    public static final TagKey<Item> LONG_SWORD = of("long_sword");
    public static final TagKey<Item> BATTLE_AXE = of("battle_axe");
    public static final TagKey<Item> DAGGER = of("dagger");
    public static final TagKey<Item> KATANA = of("katana");
    public static final TagKey<Item> HAMMER = of("hammer");
    public static final TagKey<Item> SHIELD = of("shield");

    public ItemTagProvider(FabricDataOutput output, CompletableFuture<WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(WrapperLookup wrapperLookup) {

        addWoodFamily("fungal", null, false);
        for (String color : BlockFactoryUpt.COLORS) {addWoodFamily("fungal", color, false);}
        for (String color : BlockFactoryUpt.COLORS) {addWoolFamily("textured_wool", color);}
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

        getOrCreateTagBuilder(ItemTags.LEAVES)
            .add((BlockFactoryUpt.callBlock("maple" + "_leaves").asItem()));















        
        //Setting For Fungal wood
        /*setWoodGroupTags("fungal", "");

        //Setting for Color Types (Colored Fungal + Texture Wool)
        for (String inputC : MBlockFactoryUpt.COLORS) {
            setWoodGroupTags("fungal", "_" + inputC);
            getOrCreateTagBuilder(ItemTags.WOOL).add(MBlockFactoryUpt.callBlock("textured_wool_" + inputC).asItem());
        }*/
    }

    private static TagKey<Item> of(String id) {
		return TagKey.of(RegistryKeys.ITEM, Identifier.of(EntStupidStuff.MOD_ID, id));
	}

    public void addWoodFamily(String FamilyBase, String varient, Boolean isNatural) {

        if (varient == null) {varient = "";}
        else {varient = "_" + varient;}

        if (isNatural) {

            //Logs: log, logs_that_burn

            getOrCreateTagBuilder(ItemTags.LOGS_THAT_BURN)
                .add((BlockFactoryUpt.callBlock(FamilyBase + "_log" + varient).asItem()));
            getOrCreateTagBuilder(ItemTags.LOGS)
                .add((BlockFactoryUpt.callBlock(FamilyBase + "_log" + varient).asItem()));
            getOrCreateTagBuilder(ItemTags.LOGS_THAT_BURN)
                .add((BlockFactoryUpt.callBlock(FamilyBase + "_wood" + varient).asItem()));
            getOrCreateTagBuilder(ItemTags.LOGS)
                .add((BlockFactoryUpt.callBlock(FamilyBase + "_wood" + varient).asItem()));
            getOrCreateTagBuilder(ItemTags.LOGS_THAT_BURN)
                .add((BlockFactoryUpt.callBlock("stripped_" + FamilyBase + "_log" + varient).asItem()));
            getOrCreateTagBuilder(ItemTags.LOGS)
                .add((BlockFactoryUpt.callBlock("stripped_" + FamilyBase + "_log" + varient).asItem()));
            getOrCreateTagBuilder(ItemTags.LOGS_THAT_BURN)
                .add((BlockFactoryUpt.callBlock("stripped_" + FamilyBase + "_wood" + varient).asItem()));
            getOrCreateTagBuilder(ItemTags.LOGS)
                .add((BlockFactoryUpt.callBlock("stripped_" + FamilyBase + "_wood" + varient).asItem()));

        }

        getOrCreateTagBuilder(ItemTags.PLANKS)
            .add((BlockFactoryUpt.callBlock(FamilyBase + "_planks" + varient).asItem()));
        getOrCreateTagBuilder(ItemTags.WOODEN_BUTTONS)
            .add((BlockFactoryUpt.callBlock(FamilyBase + "_button" + varient).asItem()));
        getOrCreateTagBuilder(ItemTags.WOODEN_DOORS)
            .add((BlockFactoryUpt.callBlock(FamilyBase + "_door" + varient).asItem()));
        getOrCreateTagBuilder(ItemTags.WOODEN_FENCES)
            .add((BlockFactoryUpt.callBlock(FamilyBase + "_fence" + varient).asItem()));
        getOrCreateTagBuilder(ItemTags.WOODEN_FENCES)
            .add((BlockFactoryUpt.callBlock(FamilyBase + "_fence_gate" + varient).asItem()));
        getOrCreateTagBuilder(ItemTags.WOODEN_PRESSURE_PLATES)
            .add((BlockFactoryUpt.callBlock(FamilyBase + "_pressure_plate" + varient).asItem()));
        getOrCreateTagBuilder(ItemTags.WOODEN_SLABS)
            .add((BlockFactoryUpt.callBlock(FamilyBase + "_slab" + varient).asItem()));
        getOrCreateTagBuilder(ItemTags.WOODEN_STAIRS)
            .add((BlockFactoryUpt.callBlock(FamilyBase + "_stairs" + varient).asItem()));
        getOrCreateTagBuilder(ItemTags.WOODEN_TRAPDOORS)
            .add((BlockFactoryUpt.callBlock(FamilyBase + "_trapdoor" + varient).asItem()));
        getOrCreateTagBuilder(ItemTags.WOODEN_DOORS)
            .add((BlockFactoryUpt.callBlock(FamilyBase + "_glass_door" + varient).asItem()));
        //Add Hanging Sign + Sign 

    }

    public void addVanillaGlassDoor(String FamilyBase) {
        getOrCreateTagBuilder(ItemTags.WOODEN_DOORS)
            .add((BlockFactoryUpt.callBlock(FamilyBase + "_glass_door").asItem()));
    }

    public void addWoolFamily(String FamilyBase, String color){
        getOrCreateTagBuilder(ItemTags.WOOL)
            .add((BlockFactoryUpt.callBlock(FamilyBase + "_" + color).asItem()));
    }

    public void addCombatFamily(String tm){
        getOrCreateTagBuilder(LONG_SWORD).add(ItemFactory.callItem(tm + "_long_sword"));
        //getOrCreateTagBuilder(BATTLE_AXE).add(ItemFactory.callItem(tm + "_battle_axe"));
        getOrCreateTagBuilder(DAGGER).add(ItemFactory.callItem(tm + "_dagger"));
        //getOrCreateTagBuilder(KATANA).add(ItemFactory.callItem(tm + "_katana"));
        getOrCreateTagBuilder(HAMMER).add(ItemFactory.callItem(tm + "_hammer"));

        //Enchantments
        getOrCreateTagBuilder(ItemTags.SWORD_ENCHANTABLE).add(ItemFactory.callItem(tm + "_long_sword"));
        getOrCreateTagBuilder(ItemTags.SWORD_ENCHANTABLE).add(ItemFactory.callItem(tm + "_dagger"));
        //getOrCreateTagBuilder(ItemTags.SWORD_ENCHANTABLE).add(ItemFactory.callItem(tm + "_katana"));

        //Battle Axe Enchantments
        //getOrCreateTagBuilder(ItemTags.SHARP_WEAPON_ENCHANTABLE).add(ItemFactory.callItem(tm + "_battle_axe"));

        //WEAPON ENCHANTABLE
        getOrCreateTagBuilder(ItemTags.WEAPON_ENCHANTABLE).add(ItemFactory.callItem(tm + "_long_sword"));
        //getOrCreateTagBuilder(ItemTags.WEAPON_ENCHANTABLE).add(ItemFactory.callItem(tm + "_battle_axe"));
        getOrCreateTagBuilder(ItemTags.WEAPON_ENCHANTABLE).add(ItemFactory.callItem(tm + "_dagger"));
        //getOrCreateTagBuilder(ItemTags.WEAPON_ENCHANTABLE).add(ItemFactory.callItem(tm + "_katana"));
        getOrCreateTagBuilder(ItemTags.WEAPON_ENCHANTABLE).add(ItemFactory.callItem(tm + "_hammer"));

        //FIRE ASPECT ENCHANTABLE
        getOrCreateTagBuilder(ItemTags.FIRE_ASPECT_ENCHANTABLE).add(ItemFactory.callItem(tm + "_long_sword"));
        //getOrCreateTagBuilder(ItemTags.FIRE_ASPECT_ENCHANTABLE).add(ItemFactory.callItem(tm + "_battle_axe"));
        getOrCreateTagBuilder(ItemTags.FIRE_ASPECT_ENCHANTABLE).add(ItemFactory.callItem(tm + "_dagger"));

        //Add Long Bows
    }











    //Used to Set a Wood Blocks Tags
    @Deprecated
    public void setWoodGroupTags(String baseName, String entTag) {

        getOrCreateTagBuilder(ItemTags.PLANKS).add(BlockFactoryUpt.callBlock(baseName + "_planks" + entTag).asItem());
        getOrCreateTagBuilder(ItemTags.WOODEN_STAIRS).add(BlockFactoryUpt.callBlock(baseName + "_stairs" + entTag).asItem());
        getOrCreateTagBuilder(ItemTags.WOODEN_SLABS).add(BlockFactoryUpt.callBlock(baseName + "_slab" + entTag).asItem());
        getOrCreateTagBuilder(ItemTags.WOODEN_BUTTONS).add(BlockFactoryUpt.callBlock(baseName + "_button" + entTag).asItem());
        getOrCreateTagBuilder(ItemTags.WOODEN_PRESSURE_PLATES).add(BlockFactoryUpt.callBlock(baseName + "_pressure_plate" + entTag).asItem());
        getOrCreateTagBuilder(ItemTags.WOODEN_BUTTONS).add(BlockFactoryUpt.callBlock(baseName + "_fence" + entTag).asItem());
        getOrCreateTagBuilder(ItemTags.WOODEN_FENCES).add(BlockFactoryUpt.callBlock(baseName + "_fence_gate" + entTag).asItem());
        getOrCreateTagBuilder(ItemTags.WOODEN_TRAPDOORS).add(BlockFactoryUpt.callBlock(baseName + "_trapdoor" + entTag).asItem());
        getOrCreateTagBuilder(ItemTags.WOODEN_DOORS).add(BlockFactoryUpt.callBlock(baseName + "_door" + entTag).asItem());

        getOrCreateTagBuilder(ItemTags.PLANKS).add(BlockFactoryUpt.callBlock(baseName + "_planks" + entTag).asItem());
        getOrCreateTagBuilder(ItemTags.STAIRS).add(BlockFactoryUpt.callBlock(baseName + "_stairs" + entTag).asItem());
        getOrCreateTagBuilder(ItemTags.SLABS).add(BlockFactoryUpt.callBlock(baseName + "_slab" + entTag).asItem());
        getOrCreateTagBuilder(ItemTags.BUTTONS).add(BlockFactoryUpt.callBlock(baseName + "_button" + entTag).asItem());
        getOrCreateTagBuilder(ItemTags.FENCES).add(BlockFactoryUpt.callBlock(baseName + "_fence" + entTag).asItem());
        getOrCreateTagBuilder(ItemTags.FENCE_GATES).add(BlockFactoryUpt.callBlock(baseName + "_fence_gate" + entTag).asItem());
        getOrCreateTagBuilder(ItemTags.TRAPDOORS).add(BlockFactoryUpt.callBlock(baseName + "_trapdoor" + entTag).asItem());
        getOrCreateTagBuilder(ItemTags.DOORS).add(BlockFactoryUpt.callBlock(baseName + "_door" + entTag).asItem());
    }

}
