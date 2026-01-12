package net.ent.entstupidstuff.datagen;

import java.util.concurrent.CompletableFuture;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.enchantment.EnchantmentFactory;
import net.ent.entstupidstuff.enchantment.effects.BerserkEnchantmentEffect;
import net.ent.entstupidstuff.enchantment.effects.OldFrostbiteEnchantmentEffect;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceCondition;
import net.minecraft.advancements.critereon.DamageSourcePredicate;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.EntityTypePredicate;
import net.minecraft.advancements.critereon.TagPredicate;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.item.enchantment.EnchantmentTarget;
import net.minecraft.world.item.enchantment.LevelBasedValue;
import net.minecraft.world.item.enchantment.effects.AddValue;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.AllOfCondition;
import net.minecraft.world.level.storage.loot.predicates.DamageSourceCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition;

@Deprecated
public class ModEnchantmentProvider extends FabricDynamicRegistryProvider{

    public ModEnchantmentProvider(FabricDataOutput output, CompletableFuture<Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    TagKey<DamageType> IS_MAGIC = TagKey.create(Registries.DAMAGE_TYPE, ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "arcane_protection_affected"));
    TagKey<Enchantment> TRIDENT_EXCLUSIVE_SET = TagKey.create(Registries.ENCHANTMENT, ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID,"exclusive_set/trident/damage"));
    TagKey<EntityType<?>> SENSITIVE_TO_ILLAGER = TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID,"sensitive_to_smite"));

    @Override
    protected void configure(Provider registries, Entries entries) {

        //Enchantments
        HolderGetter<DamageType> damageTypeLookup = registries.lookupOrThrow(Registries.DAMAGE_TYPE);
        HolderGetter<Enchantment> enchantmentLookup = registries.lookupOrThrow(Registries.ENCHANTMENT);
        HolderGetter<EntityType<?>> entityTypeLookup = registries.lookupOrThrow(Registries.ENTITY_TYPE);
        HolderGetter<Item> itemLookup = registries.lookupOrThrow(Registries.ITEM);
        HolderGetter<Block> blockLookup = registries.lookupOrThrow(Registries.BLOCK);
        register(
                entries,
                EnchantmentFactory.FROSTBITE,
                Enchantment.enchantment(
                    Enchantment.definition(
                        itemLookup.getOrThrow(ItemTags.SWORDS),
                        1,
                        3,
                        Enchantment.dynamicCost(1, 10),
                        Enchantment.dynamicCost(16, 10),
                        2,
                        EquipmentSlotGroup.HAND
                    )
                )/*.exclusiveSet(
                    enchantmentLookup.getOrThrow(McdwEnchantmentTags.HEALING_EXCLUSIVE)
                )*/.withEffect(
                    EnchantmentEffectComponents.POST_ATTACK,
                    EnchantmentTarget.ATTACKER,
                    EnchantmentTarget.VICTIM,
                    new OldFrostbiteEnchantmentEffect(LevelBasedValue.perLevel(0.4f, 0.2f))
                )
        );

        register(
                entries,
                EnchantmentFactory.BERSERK,
                Enchantment.enchantment(
                    Enchantment.definition(
                        itemLookup.getOrThrow(ItemTags.SWORDS),
                        1,
                        3,
                        Enchantment.dynamicCost(1, 10),
                        Enchantment.dynamicCost(16, 10),
                        2,
                        EquipmentSlotGroup.HAND
                    )
                )/*.exclusiveSet(
                    enchantmentLookup.getOrThrow(McdwEnchantmentTags.HEALING_EXCLUSIVE)
                )*/.withEffect(
                    EnchantmentEffectComponents.POST_ATTACK,
					EnchantmentTarget.VICTIM,
					EnchantmentTarget.ATTACKER,
                    new BerserkEnchantmentEffect(LevelBasedValue.perLevel(0.4f, 0.2f))
                )
        );

        register(
                entries,
                EnchantmentFactory.MAGIC_PROTECTION,
                Enchantment.enchantment(
                    Enchantment.definition(
                        itemLookup.getOrThrow(ItemTags.ARMOR_ENCHANTABLE),
                        3,
                        4,
                        Enchantment.dynamicCost(3, 7),
                        Enchantment.dynamicCost(10, 7),
                        2,
                        EquipmentSlotGroup.ARMOR
                    )
                ).exclusiveWith(enchantmentLookup.getOrThrow(EnchantmentTags.ARMOR_EXCLUSIVE))
				.withEffect(
					EnchantmentEffectComponents.DAMAGE_PROTECTION,
					new AddValue(LevelBasedValue.perLevel(2.0F)),
					AllOfCondition.allOf(
						DamageSourceCondition.hasDamageSource(
							DamageSourcePredicate.Builder.damageType()
								.tag(TagPredicate.is(IS_MAGIC))
								.tag(TagPredicate.isNot(DamageTypeTags.BYPASSES_INVULNERABILITY))
						)
					)
				)
        );

        register(
                entries,
                EnchantmentFactory.OSMOSIS,
                Enchantment.enchantment(
                    Enchantment.definition(
                        itemLookup.getOrThrow(ItemTags.TRIDENT_ENCHANTABLE),
                        2,
                        4,
                        Enchantment.dynamicCost(21, 9),
                        Enchantment.dynamicCost(1, 9),
                        4
                    )
                ).exclusiveWith(enchantmentLookup.getOrThrow(TRIDENT_EXCLUSIVE_SET))
				.withEffect(
					EnchantmentEffectComponents.ARMOR_EFFECTIVENESS,
					new AddValue(LevelBasedValue.perLevel(-1.0F, -0.1F))
				)
        );

        register(
			entries,
			EnchantmentFactory.BANEOFRAIDERS,
			Enchantment.enchantment(
					Enchantment.definition(
						itemLookup.getOrThrow(ItemTags.WEAPON_ENCHANTABLE),
						itemLookup.getOrThrow(ItemTags.SWORD_ENCHANTABLE),
						5,
						5,
						Enchantment.dynamicCost(5, 8),
						Enchantment.dynamicCost(25, 8),
						2,
						EquipmentSlotGroup.MAINHAND
					)
				)
				.exclusiveWith(enchantmentLookup.getOrThrow(EnchantmentTags.DAMAGE_EXCLUSIVE))
				.withEffect(
					EnchantmentEffectComponents.DAMAGE,
					new AddValue(LevelBasedValue.perLevel(2.5F)),
					LootItemEntityPropertyCondition.hasProperties(
						LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity().entityType(EntityTypePredicate.of(entityTypeLookup, EntityTypeTags.RAIDERS))
					)
				)
		);

        register(
                entries,
                EnchantmentFactory.WALL_JUMP, //TODO; Fix Enchant Values
                Enchantment.enchantment(
                    Enchantment.definition(
                        itemLookup.getOrThrow(ItemTags.FOOT_ARMOR),
                        1,
                        1,
                        Enchantment.dynamicCost(1, 10),
                        Enchantment.dynamicCost(16, 10),
                        2,
                        EquipmentSlotGroup.ARMOR
                    )
                )
        );
    }

    @Override
    public String getName() {
        return "RUNNING ENCHANTMENT";
    }

    private void register(Entries entries, ResourceKey<Enchantment> key, Enchantment.Builder builder, ResourceCondition...resourceConditions) {
        entries.add(key, builder.build(key.location()), resourceConditions);
    }

}
