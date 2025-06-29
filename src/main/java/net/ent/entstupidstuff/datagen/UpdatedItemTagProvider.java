package net.ent.entstupidstuff.datagen;

import java.util.concurrent.CompletableFuture;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.item.ItemFactory;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper.WrapperLookup;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class UpdatedItemTagProvider extends FabricTagProvider.ItemTagProvider {

    public UpdatedItemTagProvider(FabricDataOutput output, CompletableFuture<WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    public static final TagKey<Item> LONG_SWORD = of("long_sword");
    public static final TagKey<Item> BATTLE_AXE = of("battle_axe");
    public static final TagKey<Item> DAGGER = of("dagger");
    public static final TagKey<Item> KATANA = of("katana");
    public static final TagKey<Item> HAMMER = of("hammer");
    public static final TagKey<Item> SHIELD = of("shield");

    private static TagKey<Item> of(String id) {
        return TagKey.of(RegistryKeys.ITEM, Identifier.of(EntStupidStuff.MOD_ID, id));
    }

    @Override
    protected void configure(WrapperLookup wrapperLookup) {
        this.getOrCreateTagBuilder(ItemTags.PIGLIN_LOVED)
                .addTag(ItemTags.GOLD_ORES)
                .add(
                        ItemFactory.callItem("golden_hammer") // TODO: Add Remaining Golden Weapons
                );

        /*this.getOrCreateTagBuilder(ItemTags.SWORD_ENCHANTABLE).addTag(ItemTags.SWORDS);
        this.getOrCreateTagBuilder(ItemTags.FIRE_ASPECT_ENCHANTABLE).addTag(ItemTags.SWORD_ENCHANTABLE).add(Items.MACE);
        this.getOrCreateTagBuilder(ItemTags.SHARP_WEAPON_ENCHANTABLE).addTag(ItemTags.SWORDS).addTag(ItemTags.AXES);
        this.getOrCreateTagBuilder(ItemTags.WEAPON_ENCHANTABLE).addTag(ItemTags.SHARP_WEAPON_ENCHANTABLE)
                .add(Items.MACE);
        this.getOrCreateTagBuilder(ItemTags.MACE_ENCHANTABLE).add(Items.MACE);
        this.getOrCreateTagBuilder(ItemTags.MINING_ENCHANTABLE)
                .addTag(ItemTags.AXES)
                .addTag(ItemTags.PICKAXES)
                .addTag(ItemTags.SHOVELS)
                .addTag(ItemTags.HOES)
                .add(Items.SHEARS);
        this.getOrCreateTagBuilder(ItemTags.MINING_LOOT_ENCHANTABLE).addTag(ItemTags.AXES).addTag(ItemTags.PICKAXES)
                .addTag(ItemTags.SHOVELS).addTag(ItemTags.HOES);
        this.getOrCreateTagBuilder(ItemTags.FISHING_ENCHANTABLE).add(Items.FISHING_ROD);
        this.getOrCreateTagBuilder(ItemTags.TRIDENT_ENCHANTABLE).add(Items.TRIDENT);
        this.getOrCreateTagBuilder(ItemTags.DURABILITY_ENCHANTABLE)
                .addTag(ItemTags.FOOT_ARMOR)
                .addTag(ItemTags.LEG_ARMOR)
                .addTag(ItemTags.CHEST_ARMOR)
                .addTag(ItemTags.HEAD_ARMOR)
                .add(Items.ELYTRA)
                .add(Items.SHIELD)
                .addTag(ItemTags.SWORDS)
                .addTag(ItemTags.AXES)
                .addTag(ItemTags.PICKAXES)
                .addTag(ItemTags.SHOVELS)
                .addTag(ItemTags.HOES)
                .add(Items.BOW)
                .add(Items.CROSSBOW)
                .add(Items.TRIDENT)
                .add(Items.FLINT_AND_STEEL)
                .add(Items.SHEARS)
                .add(Items.BRUSH)
                .add(Items.FISHING_ROD)
                .add(Items.CARROT_ON_A_STICK, Items.WARPED_FUNGUS_ON_A_STICK)
                .add(Items.MACE);
        this.getOrCreateTagBuilder(ItemTags.BOW_ENCHANTABLE).add(Items.BOW);
        this.getOrCreateTagBuilder(ItemTags.EQUIPPABLE_ENCHANTABLE)
                .addTag(ItemTags.FOOT_ARMOR)
                .addTag(ItemTags.LEG_ARMOR)
                .addTag(ItemTags.CHEST_ARMOR)
                .addTag(ItemTags.HEAD_ARMOR)
                .add(Items.ELYTRA)
                .addTag(ItemTags.SKULLS)
                .add(Items.CARVED_PUMPKIN);
        this.getOrCreateTagBuilder(ItemTags.CROSSBOW_ENCHANTABLE).add(Items.CROSSBOW);
        */

        
    }

}
