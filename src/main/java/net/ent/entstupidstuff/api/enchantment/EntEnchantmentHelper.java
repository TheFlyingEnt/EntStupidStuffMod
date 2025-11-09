package net.ent.entstupidstuff.api.enchantment;


import java.util.Optional;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.world.World;

public class EntEnchantmentHelper {

    public static Optional<RegistryEntry.Reference<Enchantment>> getEnchantmentEntry(World world, RegistryKey<Enchantment> key) {
        DynamicRegistryManager drm = world.getRegistryManager();
        Registry<Enchantment> reg = drm.getOrThrow(RegistryKeys.ENCHANTMENT);

        Enchantment ench = reg.get(key.getValue());
        if (ench == null) return Optional.empty();

        int rawId = reg.getRawId(ench);
        if (rawId == -1) return Optional.empty();

        Optional<RegistryEntry.Reference<Enchantment>> optRef = reg.getEntry(rawId).map(ref -> (RegistryEntry.Reference<Enchantment>) ref);
        return optRef;
    }

    public static Enchantment getEnchantments(World world, RegistryKey<Enchantment> enchantmentKey) {
        DynamicRegistryManager drm = world.getRegistryManager();
        Registry<Enchantment> enchantmentRegistry = drm.getOrThrow(RegistryKeys.ENCHANTMENT);
        // Look up by Identifier (RegistryKey#getValue())
        Enchantment ench = enchantmentRegistry.get(enchantmentKey.getValue());
        if (ench == null) {
            throw new IllegalArgumentException("Unknown enchantment: " + enchantmentKey.getValue());
        }
        return ench;
    }

    public static RegistryEntry<Enchantment> getEnchantmentEntry(World world, RegistryKey<Enchantment> enchantmentKey, int l) {
        Registry<Enchantment> registry = world.getRegistryManager().getOrThrow(RegistryKeys.ENCHANTMENT);
        // getOrThrow() gives a proper registry entry reference
        return registry.getOrThrow(enchantmentKey);
    }

    /*public static RegistryEntry<Enchantment> getEnchantments(World world, RegistryKey<Enchantment> Encant ) { //old
        DynamicRegistryManager drm = world.getRegistryManager();
        Registry<Enchantment> reg = drm.getOrThrow(RegistryKeys.ENCHANTMENT);
        Optional<Reference<Enchantment>> optional = reg..getEntry(Encant); 
        RegistryEntry<Enchantment> registryEntry2 = optional.orElseThrow();

        return registryEntry2;
        
    }*/

}
