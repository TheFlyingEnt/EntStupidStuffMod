package net.ent.entstupidstuff.api.enchantment;


import java.util.Optional;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.entry.RegistryEntry.Reference;
import net.minecraft.world.World;

public class EntEnchantmentHelper {

    public static RegistryEntry<Enchantment> getEnchantments(World world, RegistryKey<Enchantment> Encant ) {
        DynamicRegistryManager drm = world.getRegistryManager();
        Registry<Enchantment> reg = drm.get(RegistryKeys.ENCHANTMENT);
        Optional<Reference<Enchantment>> optional = reg.getEntry(Encant); 
        RegistryEntry<Enchantment> registryEntry2 = optional.orElseThrow();

        return registryEntry2;
        
    }

}
