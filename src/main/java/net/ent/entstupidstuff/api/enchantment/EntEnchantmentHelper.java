package net.ent.entstupidstuff.api.enchantment;


import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.world.World;

public class EntEnchantmentHelper {

    public static RegistryEntry<Enchantment> getEnchantments(World entityWorld, RegistryKey<Enchantment> wallJump) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getEnchantments'");
    }

    /*public static RegistryEntry<Enchantment> getEnchantments(World world, RegistryKey<Enchantment> Encant ) {
        DynamicRegistryManager drm = world.getRegistryManager();
        Registry<Enchantment> reg = drm.getOrThrow(RegistryKeys.ENCHANTMENT);
        Optional<Reference<Enchantment>> optional = reg..getEntry(Encant); 
        RegistryEntry<Enchantment> registryEntry2 = optional.orElseThrow();

        return registryEntry2;
        
    }*/

}
