package net.ent.entstupidstuff.datagen;

import java.util.concurrent.CompletableFuture;

import net.ent.entstupidstuff.enchantment.UpdatedEnchantmentFactory;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.tags.EnchantmentTagsProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.world.item.enchantment.Enchantments;

public class ModEnchantmentTagProvider extends EnchantmentTagsProvider {
    public ModEnchantmentTagProvider(FabricDataOutput output, CompletableFuture<Provider> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider registryLookup) {
        /*builder((ModItemTags.HAMMER_EXCLUSIVE_SET)
            .addOptional(Identifier.of(EntStupidStuff.MOD_ID, "gravity"))
            .addOptional(Identifier.of(EntStupidStuff.MOD_ID, "lightning_striker"))
            .addOptional(Identifier.of("minecraft", "breach"));*/

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

        /*builder((ModItemTags.HAMMER_ENCHANTABLE)
            .addOptional(Identifier.of(EntStupidStuff.MOD_ID, "gravity"))
            .addOptional(Identifier.of(EntStupidStuff.MOD_ID, "lightning_striker"))
            .addOptional(Identifier.of("minecraft", "breach"))
            .addOptional(Identifier.of("minecraft", "looting"))
            .addOptional(Identifier.of("minecraft", "mending"))
            .addOptional(Identifier.of("minecraft", "silk_touch"))
            .addOptional(Identifier.of("minecraft", "looting"))
            .addOptional(Identifier.of("minecraft", "unbreaking"))
            .addOptional(Identifier.of("minecraft", "efficiency"))
            .addOptional(Identifier.of("minecraft", "fortune"));*/
    }
}
