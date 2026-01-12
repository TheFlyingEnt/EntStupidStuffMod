package net.ent.entstupidstuff.api.enchantment;


import java.util.Optional;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.Level;

public class EntEnchantmentHelper {

    public static Optional<Holder.Reference<Enchantment>> getEnchantmentEntry(Level world, ResourceKey<Enchantment> key) {
        RegistryAccess drm = world.registryAccess();
        Registry<Enchantment> reg = drm.lookupOrThrow(Registries.ENCHANTMENT);

        Enchantment ench = reg.getValue(key.location());
        if (ench == null) return Optional.empty();

        int rawId = reg.getId(ench);
        if (rawId == -1) return Optional.empty();

        Optional<Holder.Reference<Enchantment>> optRef = reg.get(rawId).map(ref -> (Holder.Reference<Enchantment>) ref);
        return optRef;
    }

    public static Enchantment getEnchantments(Level world, ResourceKey<Enchantment> enchantmentKey) {
        RegistryAccess drm = world.registryAccess();
        Registry<Enchantment> enchantmentRegistry = drm.lookupOrThrow(Registries.ENCHANTMENT);
        // Look up by Identifier (RegistryKey#getValue())
        Enchantment ench = enchantmentRegistry.getValue(enchantmentKey.location());
        if (ench == null) {
            throw new IllegalArgumentException("Unknown enchantment: " + enchantmentKey.location());
        }
        return ench;
    }

    public static Holder<Enchantment> getEnchantmentEntry(Level world, ResourceKey<Enchantment> enchantmentKey, int l) {
        Registry<Enchantment> registry = world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT);
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
