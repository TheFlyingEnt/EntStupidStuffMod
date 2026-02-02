package net.ent.entstupidstuff;

import net.ent.entstupidstuff.datagen.BlockTagProvider;
import net.ent.entstupidstuff.datagen.ItemTagProvider;
import net.ent.entstupidstuff.datagen.ModCatVarient;
import net.ent.entstupidstuff.datagen.ModCowVarient;
import net.ent.entstupidstuff.datagen.ModEnchantmentTagProvider;
import net.ent.entstupidstuff.datagen.ModLangProvider;
import net.ent.entstupidstuff.datagen.ModEntityLootTableGenerator;
import net.ent.entstupidstuff.datagen.ModEntityTagProvider;
import net.ent.entstupidstuff.datagen.ModLootTableProvider;
import net.ent.entstupidstuff.datagen.ModPaintingVariant;
import net.ent.entstupidstuff.datagen.ModPaintingVariantTagsProvider;
import net.ent.entstupidstuff.datagen.ModRecipeProvider;
import net.ent.entstupidstuff.datagen.ModRegistryDataGenerator;
import net.ent.entstupidstuff.datagen.ModWorldGenerator;
import net.ent.entstupidstuff.datagen.ModelProvider;
import net.ent.entstupidstuff.enchantment.UpdatedEnchantmentFactory;
import net.ent.entstupidstuff.world.ModConfiguredFeatures;
import net.ent.entstupidstuff.world.ModPlacedFeatures;
import net.ent.entstupidstuff.world.biome.ModBiomes;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;


public class EntStupidStuffDataGenerator implements DataGeneratorEntrypoint {
	
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		pack.addProvider(ModRegistryDataGenerator::new);

		pack.addProvider(ModEntityLootTableGenerator::new);
		//pack.addProvider(ModFishingLootTableGenerator::new);

        //TODO: Adding Fishs to FishingLootTableGenerator


		pack.addProvider(ModelProvider::new);
		pack.addProvider(ModLootTableProvider::new);
		pack.addProvider(BlockTagProvider::new);
		pack.addProvider(ModRecipeProvider::new);
		pack.addProvider(ItemTagProvider::new);
		pack.addProvider(ModEnchantmentTagProvider::new);
		pack.addProvider(ModLangProvider::new);
		pack.addProvider(ModWorldGenerator::new);
        pack.addProvider(ModEntityTagProvider::new);
        pack.addProvider(ModPaintingVariantTagsProvider::new);

		//pack.addProvider(ModModelProvider::new);


		
	}


	@Override
	public void buildRegistry(RegistrySetBuilder registryBuilder) {
		//registryBuilder.addRegistry(RegistryKeys.TRIM_MATERIAL, ModTrimMaterials::bootstrap);
		//registryBuilder.addRegistry(RegistryKeys.TRIM_PATTERN, ModTrimPatterns::bootstrap);
		registryBuilder.add(Registries.ENCHANTMENT, UpdatedEnchantmentFactory::bootstrap);
		registryBuilder.add(Registries.BIOME, ModBiomes::boostrap);
		registryBuilder.add(Registries.CONFIGURED_FEATURE, ModConfiguredFeatures::bootstrap);
		registryBuilder.add(Registries.PLACED_FEATURE, ModPlacedFeatures::bootstrap); 
        registryBuilder.add(Registries.CAT_VARIANT, ModCatVarient::bootstrap);
        registryBuilder.add(Registries.PAINTING_VARIANT, ModPaintingVariant::bootstrap); 
        registryBuilder.add(Registries.COW_VARIANT, ModCowVarient::bootstrap); 
	}
}
