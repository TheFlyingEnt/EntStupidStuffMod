package net.ent.entstupidstuff.datagen;

import java.util.concurrent.CompletableFuture;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;

public class ModWorldGenerator extends FabricDynamicRegistryProvider {
    public ModWorldGenerator(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(HolderLookup.Provider registries, Entries entries) {
        entries.addAll(registries.lookupOrThrow(Registries.CONFIGURED_FEATURE));
        entries.addAll(registries.lookupOrThrow(Registries.PLACED_FEATURE));
        entries.addAll(registries.lookupOrThrow(Registries.BIOME));
        entries.addAll(registries.lookupOrThrow(Registries.CAT_VARIANT));
        entries.addAll(registries.lookupOrThrow(Registries.PAINTING_VARIANT));
        entries.addAll(registries.lookupOrThrow(Registries.COW_VARIANT));
    }

    @Override
    public String getName() {
        return "World Gen";
    }
}