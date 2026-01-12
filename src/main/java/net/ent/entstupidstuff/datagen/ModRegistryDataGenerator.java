package net.ent.entstupidstuff.datagen;

import java.util.concurrent.CompletableFuture;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;

public class ModRegistryDataGenerator extends FabricDynamicRegistryProvider {
    public ModRegistryDataGenerator(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(HolderLookup.Provider registries, Entries entries) {
        //entries.addAll(registries.getOrThrow(RegistryKeys.TRIM_MATERIAL));
        //entries.addAll(registries.getOrThrow(RegistryKeys.TRIM_PATTERN));
        entries.addAll(registries.lookupOrThrow(Registries.ENCHANTMENT));

        entries.addAll(registries.lookupOrThrow(Registries.CONFIGURED_FEATURE));
        //entries.addAll(registries.getOrThrow(RegistryKeys.PLACED_FEATURE));
    }

    @Override
    public String getName() {
        return "";
    }
    
}
