package net.ent.entstupidstuff.datagen;

import java.util.concurrent.CompletableFuture;
import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.item.ItemFactory;
import net.ent.entstupidstuff.registry.EntityFactory;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricEntityLootTableProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.EnchantedCountIncreaseFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.functions.SmeltItemFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemKilledByPlayerCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceWithEnchantedBonusCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

public class ModEntityLootTableGenerator extends FabricEntityLootTableProvider /*SimpleFabricLootTableProvider*/{

    private CompletableFuture<HolderLookup.Provider> registryLookup;

    public ModEntityLootTableGenerator(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(output, registryLookup);
        this.registryLookup = registryLookup;
    }

    public static final ResourceKey<LootTable> ZOMBIE_SCORCHED = ResourceKey.create(Registries.LOOT_TABLE, ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "entities/zombie_scorched"));
	public static final ResourceKey<LootTable> ZOMBIE_FROSTBITTEN = ResourceKey.create(Registries.LOOT_TABLE, ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "entities/zombie_frostbitten"));
	public static final ResourceKey<LootTable> ZOMBIE_SLIMED = ResourceKey.create(Registries.LOOT_TABLE, ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "entities/zombie_slimed"));
	public static final ResourceKey<LootTable> ANCIENT_DROWNED = ResourceKey.create(Registries.LOOT_TABLE, ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "entities/ancient_drowned"));

	public static final ResourceKey<LootTable> ALLIGATOR_GAR = ResourceKey.create(Registries.LOOT_TABLE, ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "entities/alligator_gar"));
	public static final ResourceKey<LootTable> ZEBRA_FISH = ResourceKey.create(Registries.LOOT_TABLE, ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "entities/zebra_fish"));
	public static final ResourceKey<LootTable> MACKEREL = ResourceKey.create(Registries.LOOT_TABLE, ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "entities/mackerel"));
	public static final ResourceKey<LootTable> BASS = ResourceKey.create(Registries.LOOT_TABLE, ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "entities/bass"));
	public static final ResourceKey<LootTable> FURTROUT = ResourceKey.create(Registries.LOOT_TABLE, ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "entities/furtrout"));
	public static final ResourceKey<LootTable> KOI = ResourceKey.create(Registries.LOOT_TABLE, ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "entities/koi"));
	public static final ResourceKey<LootTable> SNAPPER = ResourceKey.create(Registries.LOOT_TABLE, ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "entities/snapper"));
	public static final ResourceKey<LootTable> PERCH = ResourceKey.create(Registries.LOOT_TABLE, ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "entities/perch"));
	public static final ResourceKey<LootTable> MAHIMAHI = ResourceKey.create(Registries.LOOT_TABLE, ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "entities/maihmahi"));


	public static final ResourceKey<LootTable> SUNKEN_SKELETON = ResourceKey.create(Registries.LOOT_TABLE, ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "entities/sunken_skeleton"));
	public static final ResourceKey<LootTable> METAL_SKELETON = ResourceKey.create(Registries.LOOT_TABLE, ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "entities/metal_skeleton"));
	public static final ResourceKey<LootTable> PHANTOM_SKELETON = ResourceKey.create(Registries.LOOT_TABLE, ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "entities/phantom_skeleton"));

	@Override
	public void generate() {
		this.add(EntityFactory.ZOMBIE_SCORCHED, LootTable.lootTable()
            .withPool(
				LootPool.lootPool()
					.setRolls(ConstantValue.exactly(1.0F))
					.add(
						LootItem.lootTableItem(Items.ROTTEN_FLESH)
							.apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F)))
							.apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registryLookup.join(), UniformGenerator.between(0.0F, 1.0F)))
					)
			)
			.withPool(
				LootPool.lootPool()
					.setRolls(ConstantValue.exactly(1.0F))
					.add(LootItem.lootTableItem(Items.COAL))
					.when(LootItemKilledByPlayerCondition.killedByPlayer())
					.when(LootItemRandomChanceWithEnchantedBonusCondition.randomChanceAndLootingBoost(this.registryLookup.join(), 0.025F, 0.01F))
			)
        );

		this.add(EntityFactory.ZOMBIE_FROSTBITTEN, LootTable.lootTable()
            .withPool(
				LootPool.lootPool()
					.setRolls(ConstantValue.exactly(1.0F))
					.add(
						LootItem.lootTableItem(Items.ROTTEN_FLESH)
							.apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F)))
							.apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registryLookup.join(), UniformGenerator.between(0.0F, 1.0F)))
					)
			)
			.withPool(
				LootPool.lootPool()
					.setRolls(ConstantValue.exactly(1.0F))
					.add(LootItem.lootTableItem(Items.SNOWBALL))
					.when(LootItemKilledByPlayerCondition.killedByPlayer())
					.when(LootItemRandomChanceWithEnchantedBonusCondition.randomChanceAndLootingBoost(this.registryLookup.join(), 0.025F, 0.01F))
			)
        );

		this.add(EntityFactory.ZOMBIE_SLIMED, LootTable.lootTable()
            .withPool(
				LootPool.lootPool()
					.setRolls(ConstantValue.exactly(1.0F))
					.add(
						LootItem.lootTableItem(Items.ROTTEN_FLESH)
							.apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F)))
							.apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registryLookup.join(), UniformGenerator.between(0.0F, 1.0F)))
					)
			)
			.withPool(
				LootPool.lootPool()
					.setRolls(ConstantValue.exactly(1.0F))
					.add(LootItem.lootTableItem(Items.SLIME_BALL))
					.when(LootItemKilledByPlayerCondition.killedByPlayer())
					.when(LootItemRandomChanceWithEnchantedBonusCondition.randomChanceAndLootingBoost(this.registryLookup.join(), 0.025F, 0.01F))
			)
        );

		// Fish

		this.add(EntityFactory.ALLIGATOR_GAR, LootTable.lootTable()
            .withPool(
				LootPool.lootPool()
					.setRolls(ConstantValue.exactly(1.0F))
					.add(LootItem.lootTableItem(ItemFactory.ALLIGATOR_GAR).apply(SmeltItemFunction.smelted().when(this.shouldSmeltLoot())))
				)
			.withPool(
				LootPool.lootPool()
					.setRolls(ConstantValue.exactly(1.0F))
					.add(LootItem.lootTableItem(Items.BONE_MEAL))
					.when(LootItemRandomChanceCondition.randomChance(0.05F))
			)
        );

		this.add(EntityFactory.ZEBRA_FISH, LootTable.lootTable()
            .withPool(
				LootPool.lootPool()
					.setRolls(ConstantValue.exactly(1.0F))
					.add(LootItem.lootTableItem(ItemFactory.ZEBRA_FISH).apply(SmeltItemFunction.smelted().when(this.shouldSmeltLoot())))
				)
			.withPool(
				LootPool.lootPool()
					.setRolls(ConstantValue.exactly(1.0F))
					.add(LootItem.lootTableItem(Items.BONE_MEAL))
					.when(LootItemRandomChanceCondition.randomChance(0.05F))
			)
        );

		this.add(EntityFactory.MACKEREL, LootTable.lootTable()
            .withPool(
				LootPool.lootPool()
					.setRolls(ConstantValue.exactly(1.0F))
					.add(LootItem.lootTableItem(ItemFactory.MACKEREL).apply(SmeltItemFunction.smelted().when(this.shouldSmeltLoot())))
				)
			.withPool(
				LootPool.lootPool()
					.setRolls(ConstantValue.exactly(1.0F))
					.add(LootItem.lootTableItem(Items.BONE_MEAL))
					.when(LootItemRandomChanceCondition.randomChance(0.05F))
			)
        );

		this.add(EntityFactory.BASS, LootTable.lootTable()
            .withPool(
				LootPool.lootPool()
					.setRolls(ConstantValue.exactly(1.0F))
					.add(LootItem.lootTableItem(ItemFactory.BASS).apply(SmeltItemFunction.smelted().when(this.shouldSmeltLoot())))
				)
			.withPool(
				LootPool.lootPool()
					.setRolls(ConstantValue.exactly(1.0F))
					.add(LootItem.lootTableItem(Items.BONE_MEAL))
					.when(LootItemRandomChanceCondition.randomChance(0.05F))
			)
        );

		this.add(EntityFactory.FURTROUT, LootTable.lootTable()
            .withPool(
				LootPool.lootPool()
					.setRolls(ConstantValue.exactly(1.0F))
					.add(LootItem.lootTableItem(Items.COD).apply(SmeltItemFunction.smelted().when(this.shouldSmeltLoot())))
				)
			.withPool(
				LootPool.lootPool()
					.setRolls(ConstantValue.exactly(1.0F))
					.add(LootItem.lootTableItem(Items.BONE_MEAL))
					.when(LootItemRandomChanceCondition.randomChance(0.05F))
			)
			.withPool(
				LootPool.lootPool()
					.setRolls(ConstantValue.exactly(1.0F))
					.add(LootItem.lootTableItem(Items.WHITE_WOOL))
					.when(LootItemRandomChanceCondition.randomChance(0.05F))
			)
        );

		this.add(EntityFactory.KOI, LootTable.lootTable()
            .withPool(
				LootPool.lootPool()
					.setRolls(ConstantValue.exactly(1.0F))
					.add(LootItem.lootTableItem(ItemFactory.KOI).apply(SmeltItemFunction.smelted().when(this.shouldSmeltLoot())))
				)
			.withPool(
				LootPool.lootPool()
					.setRolls(ConstantValue.exactly(1.0F))
					.add(LootItem.lootTableItem(Items.BONE_MEAL))
					.when(LootItemRandomChanceCondition.randomChance(0.05F))
			)
        );

		this.add(EntityFactory.PERCH, LootTable.lootTable()
            .withPool(
				LootPool.lootPool()
					.setRolls(ConstantValue.exactly(1.0F))
					.add(LootItem.lootTableItem(ItemFactory.PERCH).apply(SmeltItemFunction.smelted().when(this.shouldSmeltLoot())))
				)
			.withPool(
				LootPool.lootPool()
					.setRolls(ConstantValue.exactly(1.0F))
					.add(LootItem.lootTableItem(Items.BONE_MEAL))
					.when(LootItemRandomChanceCondition.randomChance(0.05F))
			)
        );

		this.add(EntityFactory.SNAPPER, LootTable.lootTable()
            .withPool(
				LootPool.lootPool()
					.setRolls(ConstantValue.exactly(1.0F))
					.add(LootItem.lootTableItem(ItemFactory.SNAPPER).apply(SmeltItemFunction.smelted().when(this.shouldSmeltLoot())))
				)
			.withPool(
				LootPool.lootPool()
					.setRolls(ConstantValue.exactly(1.0F))
					.add(LootItem.lootTableItem(Items.BONE_MEAL))
					.when(LootItemRandomChanceCondition.randomChance(0.05F))
			)
        );

		this.add(EntityFactory.MAHIMAHI, LootTable.lootTable()
            .withPool(
				LootPool.lootPool()
					.setRolls(ConstantValue.exactly(1.0F))
					.add(LootItem.lootTableItem(ItemFactory.MAHIMAHI).apply(SmeltItemFunction.smelted().when(this.shouldSmeltLoot())))
				)
			.withPool(
				LootPool.lootPool()
					.setRolls(ConstantValue.exactly(1.0F))
					.add(LootItem.lootTableItem(Items.BONE_MEAL))
					.when(LootItemRandomChanceCondition.randomChance(0.05F))
			)
        );


		this.add(EntityFactory.METAL_SKELETON, LootTable.lootTable()
			.withPool(
					LootPool.lootPool()
					.setRolls(ConstantValue.exactly(1.0F))
					.add(
							LootItem.lootTableItem(Items.BONE)
							.apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F)))
							.apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registryLookup.join(), UniformGenerator.between(0.0F, 1.0F)))
					)
			)
			.withPool(
				LootPool.lootPool()
					.setRolls(ConstantValue.exactly(1.0F))
					.add(
                        LootItem.lootTableItem(Items.GOLD_NUGGET)
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F)))
							.apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registryLookup.join(), UniformGenerator.between(0.0F, 1.0F)))
                        )
					.when(LootItemKilledByPlayerCondition.killedByPlayer())
			)
		);
		
	}


    
}
