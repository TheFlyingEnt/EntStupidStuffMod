package net.ent.entstupidstuff.datagen;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

import net.ent.entstupidstuff.EntStupidStuff;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.LootTable.Builder;
import net.minecraft.loot.condition.KilledByPlayerLootCondition;
import net.minecraft.loot.condition.RandomChanceWithEnchantedBonusLootCondition;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.EnchantedCountIncreaseLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;

public class ModEntityLootTableGenerator extends SimpleFabricLootTableProvider{

    private CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup;

    public ModEntityLootTableGenerator(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(output, registryLookup, LootContextTypes.ENTITY);
        this.registryLookup = registryLookup;
    }

    public static final RegistryKey<LootTable> ZOMBIE_SCORCHED = RegistryKey.of(RegistryKeys.LOOT_TABLE, Identifier.of(EntStupidStuff.MOD_ID, "entities/zombie_scorched"));
	public static final RegistryKey<LootTable> METAL_SKELETON = RegistryKey.of(RegistryKeys.LOOT_TABLE, Identifier.of(EntStupidStuff.MOD_ID, "entities/metal_skeleton"));
	public static final RegistryKey<LootTable> ZOMBIE_FROSTBITTEN = RegistryKey.of(RegistryKeys.LOOT_TABLE, Identifier.of(EntStupidStuff.MOD_ID, "entities/zombie_frostbitten"));



    @Override
    public void accept(BiConsumer<RegistryKey<LootTable>, Builder> lootTableBiConsumer) {

        lootTableBiConsumer.accept(ZOMBIE_SCORCHED, LootTable.builder()
            .pool(
				LootPool.builder()
					.rolls(ConstantLootNumberProvider.create(1.0F))
					.with(
						ItemEntry.builder(Items.ROTTEN_FLESH)
							.apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(0.0F, 2.0F)))
							.apply(EnchantedCountIncreaseLootFunction.builder(this.registryLookup.join(), UniformLootNumberProvider.create(0.0F, 1.0F)))
					)
			)
			.pool(
				LootPool.builder()
					.rolls(ConstantLootNumberProvider.create(1.0F))
					.with(ItemEntry.builder(Items.COAL))
					.conditionally(KilledByPlayerLootCondition.builder())
					.conditionally(RandomChanceWithEnchantedBonusLootCondition.builder(this.registryLookup.join(), 0.025F, 0.01F))
			)
        );

		lootTableBiConsumer.accept(METAL_SKELETON, 
		LootTable.builder()
				.pool(
					LootPool.builder()
						.rolls(ConstantLootNumberProvider.create(1.0F))
						.with(
							ItemEntry.builder(Items.BONE)
								.apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(0.0F, 2.0F)))
								.apply(EnchantedCountIncreaseLootFunction.builder(this.registryLookup.join(), UniformLootNumberProvider.create(0.0F, 1.0F)))
						)
				)
				.pool(
					LootPool.builder()
						.rolls(ConstantLootNumberProvider.create(1.0F))
						.with(
                            ItemEntry.builder(Items.GOLD_NUGGET)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(0.0F, 2.0F)))
								.apply(EnchantedCountIncreaseLootFunction.builder(this.registryLookup.join(), UniformLootNumberProvider.create(0.0F, 1.0F)))
                            )
						.conditionally(KilledByPlayerLootCondition.builder())
				)
		);

		lootTableBiConsumer.accept(ZOMBIE_FROSTBITTEN, LootTable.builder()
            .pool(
				LootPool.builder()
					.rolls(ConstantLootNumberProvider.create(1.0F))
					.with(
						ItemEntry.builder(Items.ROTTEN_FLESH)
							.apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(0.0F, 2.0F)))
							.apply(EnchantedCountIncreaseLootFunction.builder(this.registryLookup.join(), UniformLootNumberProvider.create(0.0F, 1.0F)))
					)
			)
			.pool(
				LootPool.builder()
					.rolls(ConstantLootNumberProvider.create(1.0F))
					.with(ItemEntry.builder(Items.SNOWBALL))
					.conditionally(KilledByPlayerLootCondition.builder())
					.conditionally(RandomChanceWithEnchantedBonusLootCondition.builder(this.registryLookup.join(), 0.025F, 0.01F))
			)
        );

    }
    
}
