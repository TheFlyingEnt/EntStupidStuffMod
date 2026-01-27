package net.ent.entstupidstuff.datagen;

import java.util.concurrent.CompletableFuture;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.enchantment.UpdatedEnchantmentFactory;
import net.ent.entstupidstuff.item.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.tags.EnchantmentTagsProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.world.item.enchantment.Enchantments;

public class ModEnchantmentTagProvider extends EnchantmentTagsProvider {
    public ModEnchantmentTagProvider(FabricDataOutput output, CompletableFuture<Provider> completableFuture) {
        super(output, completableFuture);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void addTags(HolderLookup.Provider registryLookup) {
        this.tag(ModTags.HAMMER_EXCLUSIVE_SET)
            .add(
                new ResourceKey[]{
                    Enchantments.BREACH, 
                    UpdatedEnchantmentFactory.GRAVITY, 
                    UpdatedEnchantmentFactory.LIGHTNING_STRIKER
                });

        this.tag(EnchantmentTags.DAMAGE_EXCLUSIVE)
			.add(
                new ResourceKey[]{
                    Enchantments.SHARPNESS, 
                    Enchantments.SMITE, 
                    Enchantments.BANE_OF_ARTHROPODS, 
                    Enchantments.IMPALING, 
                    Enchantments.DENSITY, 
                    Enchantments.BREACH, 
                    UpdatedEnchantmentFactory.GRAVITY, 
                    UpdatedEnchantmentFactory.LIGHTNING_STRIKER
                }
            );

        this.tag(ModTags.HAMMER_ENCHANTMENTS)
            .add(
                new ResourceKey[]{
                    Enchantments.BREACH, 
                    Enchantments.LOOTING, 
                    Enchantments.MENDING, 
                    Enchantments.SILK_TOUCH, 
                    Enchantments.LOOTING, 
                    Enchantments.UNBREAKING,  
                    Enchantments.EFFICIENCY,  
                    Enchantments.FORTUNE,  
                    UpdatedEnchantmentFactory.GRAVITY, 
                    UpdatedEnchantmentFactory.LIGHTNING_STRIKER
                }
            );
    }
}
