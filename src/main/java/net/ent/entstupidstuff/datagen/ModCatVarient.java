package net.ent.entstupidstuff.datagen;

import net.ent.entstupidstuff.EntStupidStuff;
import net.minecraft.core.ClientAsset;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.CatVariant;
import net.minecraft.world.entity.variant.SpawnPrioritySelectors;

public class ModCatVarient {

    public static final ResourceKey<CatVariant> GRAY_TABBY = createKey("gray_tabby");
    public static final ResourceKey<CatVariant> SIMBA = createKey("simba");
    public static final ResourceKey<CatVariant> MUFASA = createKey("mufasa");

    private static ResourceKey<CatVariant> createKey(String string) {
		return ResourceKey.create(Registries.CAT_VARIANT, ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, string));
	}

    public static void bootstrap(BootstrapContext<CatVariant> bootstrapContext) {
		//HolderGetter<Structure> holderGetter = bootstrapContext.lookup(Registries.STRUCTURE);
		registerForAnyConditions(bootstrapContext, GRAY_TABBY, "entity/cat/gray_tabby");
        registerForAnyConditions(bootstrapContext, SIMBA, "entity/cat/simba");
        registerForAnyConditions(bootstrapContext, MUFASA, "entity/cat/mufasa");
	}

	private static void registerForAnyConditions(BootstrapContext<CatVariant> bootstrapContext, ResourceKey<CatVariant> resourceKey, String string) {
		register(bootstrapContext, resourceKey, string, SpawnPrioritySelectors.fallback(0));
	}

	private static void register(
		BootstrapContext<CatVariant> bootstrapContext, ResourceKey<CatVariant> resourceKey, String string, SpawnPrioritySelectors spawnPrioritySelectors
	) {
		bootstrapContext.register(resourceKey, new CatVariant(new ClientAsset.ResourceTexture(ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, string)), spawnPrioritySelectors));
	}
    
}
