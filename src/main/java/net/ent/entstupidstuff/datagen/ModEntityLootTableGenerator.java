package net.ent.entstupidstuff.datagen;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.item.ItemFactory;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.LootTable.Builder;
import net.minecraft.loot.condition.AnyOfLootCondition;
import net.minecraft.loot.condition.EntityPropertiesLootCondition;
import net.minecraft.loot.condition.KilledByPlayerLootCondition;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.condition.RandomChanceWithEnchantedBonusLootCondition;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.EnchantedCountIncreaseLootFunction;
import net.minecraft.loot.function.FurnaceSmeltLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.predicate.NumberRange;
import net.minecraft.predicate.entity.EntityEquipmentPredicate;
import net.minecraft.predicate.entity.EntityFlagsPredicate;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.predicate.item.EnchantmentPredicate;
import net.minecraft.predicate.item.EnchantmentsPredicate;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.predicate.item.ItemSubPredicateTypes;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.EnchantmentTags;
import net.minecraft.util.Identifier;

public class ModEntityLootTableGenerator extends SimpleFabricLootTableProvider{

    private CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup;

    public ModEntityLootTableGenerator(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(output, registryLookup, LootContextTypes.ENTITY);
        this.registryLookup = registryLookup;
    }

    public static final RegistryKey<LootTable> ZOMBIE_SCORCHED = RegistryKey.of(RegistryKeys.LOOT_TABLE, Identifier.of(EntStupidStuff.MOD_ID, "entities/zombie_scorched"));
	public static final RegistryKey<LootTable> ZOMBIE_FROSTBITTEN = RegistryKey.of(RegistryKeys.LOOT_TABLE, Identifier.of(EntStupidStuff.MOD_ID, "entities/zombie_frostbitten"));
	public static final RegistryKey<LootTable> ZOMBIE_SLIMED = RegistryKey.of(RegistryKeys.LOOT_TABLE, Identifier.of(EntStupidStuff.MOD_ID, "entities/zombie_slimed"));
	public static final RegistryKey<LootTable> ANCIENT_DROWNED = RegistryKey.of(RegistryKeys.LOOT_TABLE, Identifier.of(EntStupidStuff.MOD_ID, "entities/ancient_drowned"));

	public static final RegistryKey<LootTable> ALLIGATOR_GAR = RegistryKey.of(RegistryKeys.LOOT_TABLE, Identifier.of(EntStupidStuff.MOD_ID, "entities/alligator_gar"));
	public static final RegistryKey<LootTable> ZEBRA_FISH = RegistryKey.of(RegistryKeys.LOOT_TABLE, Identifier.of(EntStupidStuff.MOD_ID, "entities/zebra_fish"));
	public static final RegistryKey<LootTable> MACKEREL = RegistryKey.of(RegistryKeys.LOOT_TABLE, Identifier.of(EntStupidStuff.MOD_ID, "entities/mackerel"));

	public static final RegistryKey<LootTable> SUNKEN_SKELETON = RegistryKey.of(RegistryKeys.LOOT_TABLE, Identifier.of(EntStupidStuff.MOD_ID, "entities/sunken_skeleton"));
	public static final RegistryKey<LootTable> METAL_SKELETON = RegistryKey.of(RegistryKeys.LOOT_TABLE, Identifier.of(EntStupidStuff.MOD_ID, "entities/metal_skeleton"));
	public static final RegistryKey<LootTable> PHANTOM_SKELETON = RegistryKey.of(RegistryKeys.LOOT_TABLE, Identifier.of(EntStupidStuff.MOD_ID, "entities/phantom_skeleton"));



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

		lootTableBiConsumer.accept(ZOMBIE_SLIMED, LootTable.builder()
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
					.with(ItemEntry.builder(Items.SLIME_BALL))
					.conditionally(KilledByPlayerLootCondition.builder())
					.conditionally(RandomChanceWithEnchantedBonusLootCondition.builder(this.registryLookup.join(), 0.025F, 0.01F))
			)
        );

		// Fish

		lootTableBiConsumer.accept(ALLIGATOR_GAR, LootTable.builder()
            .pool(
				LootPool.builder()
					.rolls(ConstantLootNumberProvider.create(1.0F))
					.with(ItemEntry.builder(ItemFactory.ALLIGATOR_GAR).apply(FurnaceSmeltLootFunction.builder().conditionally(this.createSmeltLootCondition())))
				)
			.pool(
				LootPool.builder()
					.rolls(ConstantLootNumberProvider.create(1.0F))
					.with(ItemEntry.builder(Items.BONE_MEAL))
					.conditionally(RandomChanceLootCondition.builder(0.05F))
			)
        );

		lootTableBiConsumer.accept(ZEBRA_FISH, LootTable.builder()
            .pool(
				LootPool.builder()
					.rolls(ConstantLootNumberProvider.create(1.0F))
					.with(ItemEntry.builder(ItemFactory.ZEBRA_FISH).apply(FurnaceSmeltLootFunction.builder().conditionally(this.createSmeltLootCondition())))
				)
			.pool(
				LootPool.builder()
					.rolls(ConstantLootNumberProvider.create(1.0F))
					.with(ItemEntry.builder(Items.BONE_MEAL))
					.conditionally(RandomChanceLootCondition.builder(0.05F))
			)
        );

		lootTableBiConsumer.accept(MACKEREL, LootTable.builder()
            .pool(
				LootPool.builder()
					.rolls(ConstantLootNumberProvider.create(1.0F))
					.with(ItemEntry.builder(ItemFactory.MACKEREL).apply(FurnaceSmeltLootFunction.builder().conditionally(this.createSmeltLootCondition())))
				)
			.pool(
				LootPool.builder()
					.rolls(ConstantLootNumberProvider.create(1.0F))
					.with(ItemEntry.builder(Items.BONE_MEAL))
					.conditionally(RandomChanceLootCondition.builder(0.05F))
			)
        );


		lootTableBiConsumer.accept(METAL_SKELETON, LootTable.builder()
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

    }

	protected final AnyOfLootCondition.Builder createSmeltLootCondition() {
		RegistryWrapper.WrapperLookup lookup = this.registryLookup.join();
		RegistryWrapper.Impl<Enchantment> impl = lookup.getWrapperOrThrow(RegistryKeys.ENCHANTMENT);
		return AnyOfLootCondition.builder(
			EntityPropertiesLootCondition.builder(
				LootContext.EntityTarget.THIS, EntityPredicate.Builder.create().flags(EntityFlagsPredicate.Builder.create().onFire(true))
			),
			EntityPropertiesLootCondition.builder(
				LootContext.EntityTarget.DIRECT_ATTACKER,
				EntityPredicate.Builder.create()
					.equipment(
						EntityEquipmentPredicate.Builder.create()
							.mainhand(
								ItemPredicate.Builder.create()
									.subPredicate(
										ItemSubPredicateTypes.ENCHANTMENTS,
										EnchantmentsPredicate.enchantments(List.of(new EnchantmentPredicate(impl.getOrThrow(EnchantmentTags.SMELTS_LOOT), NumberRange.IntRange.ANY)))
									)
							)
					)
			)
		);
	}


    
}
