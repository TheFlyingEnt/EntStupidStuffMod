package net.ent.entstupidstuff.datagen;

import java.util.concurrent.CompletableFuture;

import net.ent.entstupidstuff.enchantment.UpdatedEnchantmentFactory;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.data.tag.EnchantmentTagProvider;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.RegistryWrapper.WrapperLookup; 
import net.minecraft.registry.tag.EnchantmentTags;

public class ModEnchantmentTagProvider extends EnchantmentTagProvider {
    public ModEnchantmentTagProvider(FabricDataOutput output, CompletableFuture<WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup registryLookup) {
        /*builder((ModItemTags.HAMMER_EXCLUSIVE_SET)
            .addOptional(Identifier.of(EntStupidStuff.MOD_ID, "gravity"))
            .addOptional(Identifier.of(EntStupidStuff.MOD_ID, "lightning_striker"))
            .addOptional(Identifier.of("minecraft", "breach"));*/

        this.builder(EnchantmentTags.DAMAGE_EXCLUSIVE_SET)
			.add(
                new RegistryKey[]{
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
