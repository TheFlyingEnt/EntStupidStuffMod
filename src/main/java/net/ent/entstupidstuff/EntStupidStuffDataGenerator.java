package net.ent.entstupidstuff;

import net.ent.entstupidstuff.datagen.BlockTagProvider;
import net.ent.entstupidstuff.datagen.ItemTagProvider;
import net.ent.entstupidstuff.datagen.ModLangProvider;
import net.ent.entstupidstuff.datagen.ModEntityLootTableGenerator;
import net.ent.entstupidstuff.datagen.ModLootTableProvider;
import net.ent.entstupidstuff.datagen.ModRecipeProvider;
import net.ent.entstupidstuff.datagen.ModRegistryDataGenerator;
import net.ent.entstupidstuff.datagen.ModelProvider;
import net.ent.entstupidstuff.enchantment.UpdatedEnchantmentFactory;
import net.ent.entstupidstuff.world.ConfiguredFeaturesFactory;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKeys;


public class EntStupidStuffDataGenerator implements DataGeneratorEntrypoint {
	
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		pack.addProvider(ModEntityLootTableGenerator::new);


		pack.addProvider(ModelProvider::new);
		pack.addProvider(ModLootTableProvider::new);
		pack.addProvider(BlockTagProvider::new);
		pack.addProvider(ModRecipeProvider::new);
		pack.addProvider(ItemTagProvider::new);
		pack.addProvider(ModLangProvider::new);
		pack.addProvider(ModRegistryDataGenerator::new);

		
	}

	@Override
	public void buildRegistry(RegistryBuilder registryBuilder) {
		//registryBuilder.addRegistry(RegistryKeys.TRIM_MATERIAL, ModTrimMaterials::bootstrap);
		//registryBuilder.addRegistry(RegistryKeys.TRIM_PATTERN, ModTrimPatterns::bootstrap);
		registryBuilder.addRegistry(RegistryKeys.ENCHANTMENT, UpdatedEnchantmentFactory::bootstrap);

		//registryBuilder.addRegistry(RegistryKeys.CONFIGURED_FEATURE, ConfiguredFeaturesFactory::bootstrap);
		registryBuilder.addRegistry(RegistryKeys.CONFIGURED_FEATURE, ConfiguredFeaturesFactory::bootstrap);
		//registryBuilder.addRegistry(RegistryKeys.PLACED_FEATURE, ModPlacedFeatures::bootstrap);
	}
}
