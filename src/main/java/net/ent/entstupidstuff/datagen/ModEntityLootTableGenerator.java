package net.ent.entstupidstuff.datagen;

import java.util.concurrent.CompletableFuture;
import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.item.ItemFactory;
import net.ent.entstupidstuff.registry.EntityFactory;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricEntityLootTableProvider;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.KilledByPlayerLootCondition;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.condition.RandomChanceWithEnchantedBonusLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.EnchantedCountIncreaseLootFunction;
import net.minecraft.loot.function.FurnaceSmeltLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;

public class ModEntityLootTableGenerator extends FabricEntityLootTableProvider /*SimpleFabricLootTableProvider*/{

    private CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup;

    public ModEntityLootTableGenerator(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(output, registryLookup);
        this.registryLookup = registryLookup;
    }

    public static final RegistryKey<LootTable> ZOMBIE_SCORCHED = RegistryKey.of(RegistryKeys.LOOT_TABLE, Identifier.of(EntStupidStuff.MOD_ID, "entities/zombie_scorched"));
	public static final RegistryKey<LootTable> ZOMBIE_FROSTBITTEN = RegistryKey.of(RegistryKeys.LOOT_TABLE, Identifier.of(EntStupidStuff.MOD_ID, "entities/zombie_frostbitten"));
	public static final RegistryKey<LootTable> ZOMBIE_SLIMED = RegistryKey.of(RegistryKeys.LOOT_TABLE, Identifier.of(EntStupidStuff.MOD_ID, "entities/zombie_slimed"));
	public static final RegistryKey<LootTable> ANCIENT_DROWNED = RegistryKey.of(RegistryKeys.LOOT_TABLE, Identifier.of(EntStupidStuff.MOD_ID, "entities/ancient_drowned"));

	public static final RegistryKey<LootTable> ALLIGATOR_GAR = RegistryKey.of(RegistryKeys.LOOT_TABLE, Identifier.of(EntStupidStuff.MOD_ID, "entities/alligator_gar"));
	public static final RegistryKey<LootTable> ZEBRA_FISH = RegistryKey.of(RegistryKeys.LOOT_TABLE, Identifier.of(EntStupidStuff.MOD_ID, "entities/zebra_fish"));
	public static final RegistryKey<LootTable> MACKEREL = RegistryKey.of(RegistryKeys.LOOT_TABLE, Identifier.of(EntStupidStuff.MOD_ID, "entities/mackerel"));
	public static final RegistryKey<LootTable> BASS = RegistryKey.of(RegistryKeys.LOOT_TABLE, Identifier.of(EntStupidStuff.MOD_ID, "entities/bass"));
	public static final RegistryKey<LootTable> FURTROUT = RegistryKey.of(RegistryKeys.LOOT_TABLE, Identifier.of(EntStupidStuff.MOD_ID, "entities/furtrout"));
	public static final RegistryKey<LootTable> KOI = RegistryKey.of(RegistryKeys.LOOT_TABLE, Identifier.of(EntStupidStuff.MOD_ID, "entities/koi"));
	public static final RegistryKey<LootTable> SNAPPER = RegistryKey.of(RegistryKeys.LOOT_TABLE, Identifier.of(EntStupidStuff.MOD_ID, "entities/snapper"));
	public static final RegistryKey<LootTable> PERCH = RegistryKey.of(RegistryKeys.LOOT_TABLE, Identifier.of(EntStupidStuff.MOD_ID, "entities/perch"));
	public static final RegistryKey<LootTable> MAHIMAHI = RegistryKey.of(RegistryKeys.LOOT_TABLE, Identifier.of(EntStupidStuff.MOD_ID, "entities/maihmahi"));


	public static final RegistryKey<LootTable> SUNKEN_SKELETON = RegistryKey.of(RegistryKeys.LOOT_TABLE, Identifier.of(EntStupidStuff.MOD_ID, "entities/sunken_skeleton"));
	public static final RegistryKey<LootTable> METAL_SKELETON = RegistryKey.of(RegistryKeys.LOOT_TABLE, Identifier.of(EntStupidStuff.MOD_ID, "entities/metal_skeleton"));
	public static final RegistryKey<LootTable> PHANTOM_SKELETON = RegistryKey.of(RegistryKeys.LOOT_TABLE, Identifier.of(EntStupidStuff.MOD_ID, "entities/phantom_skeleton"));

	@Override
	public void generate() {
		this.register(EntityFactory.ZOMBIE_SCORCHED, LootTable.builder()
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

		this.register(EntityFactory.ZOMBIE_FROSTBITTEN, LootTable.builder()
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

		this.register(EntityFactory.ZOMBIE_SLIMED, LootTable.builder()
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

		this.register(EntityFactory.ALLIGATOR_GAR, LootTable.builder()
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

		this.register(EntityFactory.ZEBRA_FISH, LootTable.builder()
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

		this.register(EntityFactory.MACKEREL, LootTable.builder()
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

		this.register(EntityFactory.BASS, LootTable.builder()
            .pool(
				LootPool.builder()
					.rolls(ConstantLootNumberProvider.create(1.0F))
					.with(ItemEntry.builder(ItemFactory.BASS).apply(FurnaceSmeltLootFunction.builder().conditionally(this.createSmeltLootCondition())))
				)
			.pool(
				LootPool.builder()
					.rolls(ConstantLootNumberProvider.create(1.0F))
					.with(ItemEntry.builder(Items.BONE_MEAL))
					.conditionally(RandomChanceLootCondition.builder(0.05F))
			)
        );

		this.register(EntityFactory.FURTROUT, LootTable.builder()
            .pool(
				LootPool.builder()
					.rolls(ConstantLootNumberProvider.create(1.0F))
					.with(ItemEntry.builder(Items.COD).apply(FurnaceSmeltLootFunction.builder().conditionally(this.createSmeltLootCondition())))
				)
			.pool(
				LootPool.builder()
					.rolls(ConstantLootNumberProvider.create(1.0F))
					.with(ItemEntry.builder(Items.BONE_MEAL))
					.conditionally(RandomChanceLootCondition.builder(0.05F))
			)
			.pool(
				LootPool.builder()
					.rolls(ConstantLootNumberProvider.create(1.0F))
					.with(ItemEntry.builder(Items.WHITE_WOOL))
					.conditionally(RandomChanceLootCondition.builder(0.05F))
			)
        );

		this.register(EntityFactory.KOI, LootTable.builder()
            .pool(
				LootPool.builder()
					.rolls(ConstantLootNumberProvider.create(1.0F))
					.with(ItemEntry.builder(ItemFactory.KOI).apply(FurnaceSmeltLootFunction.builder().conditionally(this.createSmeltLootCondition())))
				)
			.pool(
				LootPool.builder()
					.rolls(ConstantLootNumberProvider.create(1.0F))
					.with(ItemEntry.builder(Items.BONE_MEAL))
					.conditionally(RandomChanceLootCondition.builder(0.05F))
			)
        );

		this.register(EntityFactory.PERCH, LootTable.builder()
            .pool(
				LootPool.builder()
					.rolls(ConstantLootNumberProvider.create(1.0F))
					.with(ItemEntry.builder(ItemFactory.PERCH).apply(FurnaceSmeltLootFunction.builder().conditionally(this.createSmeltLootCondition())))
				)
			.pool(
				LootPool.builder()
					.rolls(ConstantLootNumberProvider.create(1.0F))
					.with(ItemEntry.builder(Items.BONE_MEAL))
					.conditionally(RandomChanceLootCondition.builder(0.05F))
			)
        );

		this.register(EntityFactory.SNAPPER, LootTable.builder()
            .pool(
				LootPool.builder()
					.rolls(ConstantLootNumberProvider.create(1.0F))
					.with(ItemEntry.builder(ItemFactory.SNAPPER).apply(FurnaceSmeltLootFunction.builder().conditionally(this.createSmeltLootCondition())))
				)
			.pool(
				LootPool.builder()
					.rolls(ConstantLootNumberProvider.create(1.0F))
					.with(ItemEntry.builder(Items.BONE_MEAL))
					.conditionally(RandomChanceLootCondition.builder(0.05F))
			)
        );

		this.register(EntityFactory.MAHIMAHI, LootTable.builder()
            .pool(
				LootPool.builder()
					.rolls(ConstantLootNumberProvider.create(1.0F))
					.with(ItemEntry.builder(ItemFactory.MAHIMAHI).apply(FurnaceSmeltLootFunction.builder().conditionally(this.createSmeltLootCondition())))
				)
			.pool(
				LootPool.builder()
					.rolls(ConstantLootNumberProvider.create(1.0F))
					.with(ItemEntry.builder(Items.BONE_MEAL))
					.conditionally(RandomChanceLootCondition.builder(0.05F))
			)
        );


		this.register(EntityFactory.METAL_SKELETON, LootTable.builder()
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


    
}
