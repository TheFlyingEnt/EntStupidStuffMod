package net.ent.entstupidstuff.datagen;

import net.ent.entstupidstuff.registry.EntityFactory;
import net.minecraft.data.server.loottable.EntityLootTableGenerator;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.KilledByPlayerLootCondition;
import net.minecraft.loot.condition.RandomChanceWithEnchantedBonusLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.EnchantedCountIncreaseLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.resource.featuretoggle.FeatureFlags;

public class OLD_ModEntityLootTableGenerator extends EntityLootTableGenerator{
    public OLD_ModEntityLootTableGenerator(RegistryWrapper.WrapperLookup registryLookup) {
		super(FeatureFlags.FEATURE_MANAGER.getFeatureSet(), registryLookup);
	}

    @Override
    public void generate() {
        this.register(
			EntityFactory.ZOMBIE_SCORCHED,
			LootTable.builder()
				.pool(
					LootPool.builder()
						.rolls(ConstantLootNumberProvider.create(1.0F))
						.with(
							ItemEntry.builder(Items.ROTTEN_FLESH)
								.apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(0.0F, 2.0F)))
								.apply(EnchantedCountIncreaseLootFunction.builder(this.registryLookup, UniformLootNumberProvider.create(0.0F, 1.0F)))
						)
				)
				.pool(
					LootPool.builder()
						.rolls(ConstantLootNumberProvider.create(1.0F))
						.with(ItemEntry.builder(Items.COAL))
						.conditionally(KilledByPlayerLootCondition.builder())
						.conditionally(RandomChanceWithEnchantedBonusLootCondition.builder(this.registryLookup, 0.025F, 0.01F))
				)
		);

        this.register(
			EntityFactory.GOLD_SKELETON,
			LootTable.builder()
				.pool(
					LootPool.builder()
						.rolls(ConstantLootNumberProvider.create(1.0F))
						.with(
							ItemEntry.builder(Items.ROTTEN_FLESH)
								.apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(0.0F, 2.0F)))
								.apply(EnchantedCountIncreaseLootFunction.builder(this.registryLookup, UniformLootNumberProvider.create(0.0F, 1.0F)))
						)
				)
				.pool(
					LootPool.builder()
						.rolls(ConstantLootNumberProvider.create(1.0F))
						.with(
                            ItemEntry.builder(Items.GOLD_NUGGET)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(0.0F, 2.0F)))
								.apply(EnchantedCountIncreaseLootFunction.builder(this.registryLookup, UniformLootNumberProvider.create(0.0F, 1.0F)))
                            )
						.conditionally(KilledByPlayerLootCondition.builder())
				)
		);
    }
    
}
