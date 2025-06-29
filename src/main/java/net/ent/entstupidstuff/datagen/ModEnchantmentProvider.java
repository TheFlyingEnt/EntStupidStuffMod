package net.ent.entstupidstuff.datagen;

import java.util.concurrent.CompletableFuture;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.enchantment.EnchantmentFactory;
import net.ent.entstupidstuff.enchantment.effects.BerserkEnchantmentEffect;
import net.ent.entstupidstuff.enchantment.effects.OldFrostbiteEnchantmentEffect;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceCondition;
import net.minecraft.block.Block;
import net.minecraft.component.EnchantmentEffectComponentTypes;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentLevelBasedValue;
import net.minecraft.enchantment.effect.EnchantmentEffectTarget;
import net.minecraft.enchantment.effect.value.AddEnchantmentEffect;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.item.Item;
import net.minecraft.loot.condition.AllOfLootCondition;
import net.minecraft.loot.condition.DamageSourcePropertiesLootCondition;
import net.minecraft.loot.condition.EntityPropertiesLootCondition;
import net.minecraft.loot.context.LootContext;
import net.minecraft.predicate.TagPredicate;
import net.minecraft.predicate.entity.DamageSourcePredicate;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.predicate.entity.EntityTypePredicate;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper.WrapperLookup;
import net.minecraft.registry.tag.DamageTypeTags;
import net.minecraft.registry.tag.EnchantmentTags;
import net.minecraft.registry.tag.EntityTypeTags;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModEnchantmentProvider extends FabricDynamicRegistryProvider{

    public ModEnchantmentProvider(FabricDataOutput output, CompletableFuture<WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    TagKey<DamageType> IS_MAGIC = TagKey.of(RegistryKeys.DAMAGE_TYPE, Identifier.of(EntStupidStuff.MOD_ID, "arcane_protection_affected"));
    TagKey<Enchantment> TRIDENT_EXCLUSIVE_SET = TagKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(EntStupidStuff.MOD_ID,"exclusive_set/trident/damage"));
    TagKey<EntityType<?>> SENSITIVE_TO_ILLAGER = TagKey.of(RegistryKeys.ENTITY_TYPE, Identifier.of(EntStupidStuff.MOD_ID,"sensitive_to_smite"));

    @SuppressWarnings("unused")
    @Override
    protected void configure(WrapperLookup registries, Entries entries) {

        //Enchantments
        RegistryEntryLookup<DamageType> damageTypeLookup = registries.getWrapperOrThrow(RegistryKeys.DAMAGE_TYPE);
        RegistryEntryLookup<Enchantment> enchantmentLookup = registries.getWrapperOrThrow(RegistryKeys.ENCHANTMENT);
        RegistryEntryLookup<Item> itemLookup = registries.getWrapperOrThrow(RegistryKeys.ITEM);
        RegistryEntryLookup<Block> blockLookup = registries.getWrapperOrThrow(RegistryKeys.BLOCK);
        register(
                entries,
                EnchantmentFactory.FROSTBITE,
                Enchantment.builder(
                    Enchantment.definition(
                        itemLookup.getOrThrow(ItemTags.SWORDS),
                        1,
                        3,
                        Enchantment.leveledCost(1, 10),
                        Enchantment.leveledCost(16, 10),
                        2,
                        AttributeModifierSlot.HAND
                    )
                )/*.exclusiveSet(
                    enchantmentLookup.getOrThrow(McdwEnchantmentTags.HEALING_EXCLUSIVE)
                )*/.addEffect(
                    EnchantmentEffectComponentTypes.POST_ATTACK,
                    EnchantmentEffectTarget.ATTACKER,
                    EnchantmentEffectTarget.VICTIM,
                    new OldFrostbiteEnchantmentEffect(EnchantmentLevelBasedValue.linear(0.4f, 0.2f))
                )
        );

        register(
                entries,
                EnchantmentFactory.BERSERK,
                Enchantment.builder(
                    Enchantment.definition(
                        itemLookup.getOrThrow(ItemTags.SWORDS),
                        1,
                        3,
                        Enchantment.leveledCost(1, 10),
                        Enchantment.leveledCost(16, 10),
                        2,
                        AttributeModifierSlot.HAND
                    )
                )/*.exclusiveSet(
                    enchantmentLookup.getOrThrow(McdwEnchantmentTags.HEALING_EXCLUSIVE)
                )*/.addEffect(
                    EnchantmentEffectComponentTypes.POST_ATTACK,
					EnchantmentEffectTarget.VICTIM,
					EnchantmentEffectTarget.ATTACKER,
                    new BerserkEnchantmentEffect(EnchantmentLevelBasedValue.linear(0.4f, 0.2f))
                )
        );

        register(
                entries,
                EnchantmentFactory.MAGIC_PROTECTION,
                Enchantment.builder(
                    Enchantment.definition(
                        itemLookup.getOrThrow(ItemTags.ARMOR_ENCHANTABLE),
                        3,
                        4,
                        Enchantment.leveledCost(3, 7),
                        Enchantment.leveledCost(10, 7),
                        2,
                        AttributeModifierSlot.ARMOR
                    )
                ).exclusiveSet(enchantmentLookup.getOrThrow(EnchantmentTags.ARMOR_EXCLUSIVE_SET))
				.addEffect(
					EnchantmentEffectComponentTypes.DAMAGE_PROTECTION,
					new AddEnchantmentEffect(EnchantmentLevelBasedValue.linear(2.0F)),
					AllOfLootCondition.builder(
						DamageSourcePropertiesLootCondition.builder(
							DamageSourcePredicate.Builder.create()
								.tag(TagPredicate.expected(IS_MAGIC))
								.tag(TagPredicate.unexpected(DamageTypeTags.BYPASSES_INVULNERABILITY))
						)
					)
				)
        );

        register(
                entries,
                EnchantmentFactory.OSMOSIS,
                Enchantment.builder(
                    Enchantment.definition(
                        itemLookup.getOrThrow(ItemTags.TRIDENT_ENCHANTABLE),
                        2,
                        4,
                        Enchantment.leveledCost(21, 9),
                        Enchantment.leveledCost(1, 9),
                        4
                    )
                ).exclusiveSet(enchantmentLookup.getOrThrow(TRIDENT_EXCLUSIVE_SET))
				.addEffect(
					EnchantmentEffectComponentTypes.ARMOR_EFFECTIVENESS,
					new AddEnchantmentEffect(EnchantmentLevelBasedValue.linear(-1.0F, -0.1F))
				)
        );

        register(
			entries,
			EnchantmentFactory.BANEOFRAIDERS,
			Enchantment.builder(
					Enchantment.definition(
						itemLookup.getOrThrow(ItemTags.WEAPON_ENCHANTABLE),
						itemLookup.getOrThrow(ItemTags.SWORD_ENCHANTABLE),
						5,
						5,
						Enchantment.leveledCost(5, 8),
						Enchantment.leveledCost(25, 8),
						2,
						AttributeModifierSlot.MAINHAND
					)
				)
				.exclusiveSet(enchantmentLookup.getOrThrow(EnchantmentTags.DAMAGE_EXCLUSIVE_SET))
				.addEffect(
					EnchantmentEffectComponentTypes.DAMAGE,
					new AddEnchantmentEffect(EnchantmentLevelBasedValue.linear(2.5F)),
					EntityPropertiesLootCondition.builder(
						LootContext.EntityTarget.THIS, EntityPredicate.Builder.create().type(EntityTypePredicate.create(EntityTypeTags.RAIDERS))
					)
				)
		);

        register(
                entries,
                EnchantmentFactory.WALL_JUMP, //TODO; Fix Enchant Values
                Enchantment.builder(
                    Enchantment.definition(
                        itemLookup.getOrThrow(ItemTags.FOOT_ARMOR),
                        1,
                        1,
                        Enchantment.leveledCost(1, 10),
                        Enchantment.leveledCost(16, 10),
                        2,
                        AttributeModifierSlot.ARMOR
                    )
                )
        );
    }

    @Override
    public String getName() {
        return "RUNNING ENCHANTMENT";
    }

















    ////////////////////////////


    /*private void register(Entries entries,RegistryKey<Enchantment> key, Enchantment.Builder builder) {
        register(entries, key, builder);
    }*/


    private void register(Entries entries, RegistryKey<Enchantment> key, Enchantment.Builder builder, ResourceCondition...resourceConditions) {
        entries.add(key, builder.build(key.getValue()), resourceConditions);
    }

}
