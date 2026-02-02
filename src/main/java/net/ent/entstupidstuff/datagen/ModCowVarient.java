package net.ent.entstupidstuff.datagen;

import net.ent.entstupidstuff.EntStupidStuff;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.animal.CowVariant;
import net.minecraft.world.entity.variant.BiomeCheck;
import net.minecraft.world.entity.variant.ModelAndTexture;
import net.minecraft.world.entity.variant.SpawnPrioritySelectors;
import net.minecraft.world.level.biome.Biome;

public class ModCowVarient {

    public static final ResourceKey<CowVariant> TEMPERATE_DAIRY = createKey(ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID,"temperate_dairy"));
    public static final ResourceKey<CowVariant> TEMPERATE_PINTO = createKey(ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID,"temperate_pinto"));
    public static final ResourceKey<CowVariant> TEMPERATE_CREAM = createKey(ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID,"temperate_cream"));
	public static final ResourceKey<CowVariant> WARM_ASHEN = createKey(ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID,"warm_ashen"));;
    public static final ResourceKey<CowVariant> WARM_COOKIE = createKey(ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID,"warm_cookie"));;
	public static final ResourceKey<CowVariant> COLD_UMBRA = createKey(ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID,"cold_umbra"));
    public static final ResourceKey<CowVariant> COLD_WOOLY = createKey(ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID,"cold_wooly"));

    private static ResourceKey<CowVariant> createKey(ResourceLocation resourceLocation) {
		return ResourceKey.create(Registries.COW_VARIANT, resourceLocation);
	}

	public static void bootstrap(BootstrapContext<CowVariant> bootstrapContext) {
		register(bootstrapContext, TEMPERATE_DAIRY, CowVariant.ModelType.NORMAL, "temperate_cow_dairy", SpawnPrioritySelectors.fallback(0));
        register(bootstrapContext, TEMPERATE_PINTO, CowVariant.ModelType.NORMAL, "temperate_cow_pinto", SpawnPrioritySelectors.fallback(0));
        register(bootstrapContext, TEMPERATE_CREAM, CowVariant.ModelType.NORMAL, "temperate_cow_cream", SpawnPrioritySelectors.fallback(0));
		register(bootstrapContext, WARM_ASHEN, CowVariant.ModelType.WARM, "warm_cow_ashen", BiomeTags.SPAWNS_WARM_VARIANT_FARM_ANIMALS);
        register(bootstrapContext, WARM_COOKIE, CowVariant.ModelType.WARM, "warm_cow_cookie", BiomeTags.SPAWNS_WARM_VARIANT_FARM_ANIMALS);
		register(bootstrapContext, COLD_UMBRA, CowVariant.ModelType.COLD, "cold_cow_umbra", BiomeTags.SPAWNS_COLD_VARIANT_FARM_ANIMALS);
        register(bootstrapContext, COLD_WOOLY, CowVariant.ModelType.COLD, "cold_cow_wooly", BiomeTags.SPAWNS_COLD_VARIANT_FARM_ANIMALS);
	}

	private static void register(
		BootstrapContext<CowVariant> bootstrapContext, ResourceKey<CowVariant> resourceKey, CowVariant.ModelType modelType, String string, TagKey<Biome> tagKey
	) {
		HolderSet<Biome> holderSet = bootstrapContext.lookup(Registries.BIOME).getOrThrow(tagKey);
		register(bootstrapContext, resourceKey, modelType, string, SpawnPrioritySelectors.single(new BiomeCheck(holderSet), 1));
	}

	private static void register(
		BootstrapContext<CowVariant> bootstrapContext,
		ResourceKey<CowVariant> resourceKey,
		CowVariant.ModelType modelType,
		String string,
		SpawnPrioritySelectors spawnPrioritySelectors
	) {
		ResourceLocation resourceLocation = ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID,"entity/cow/" + string);
		bootstrapContext.register(resourceKey, new CowVariant(new ModelAndTexture<>(modelType, resourceLocation), spawnPrioritySelectors));
	}
    
}
