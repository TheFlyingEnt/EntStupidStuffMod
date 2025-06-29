package net.ent.entstupidstuff.enchantment;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.enchantment.effect.FrostbiteEnchantmentEffect;
import net.ent.entstupidstuff.enchantment.effect.LightningStrikerEnchantmentEffect;
import net.minecraft.component.EnchantmentEffectComponentTypes;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentLevelBasedValue;
import net.minecraft.enchantment.effect.EnchantmentEffectTarget;
import net.minecraft.enchantment.effect.entity.ApplyMobEffectEnchantmentEffect;
import net.minecraft.enchantment.effect.value.AddEnchantmentEffect;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.loot.condition.DamageSourcePropertiesLootCondition;
import net.minecraft.loot.condition.EntityPropertiesLootCondition;
import net.minecraft.loot.context.LootContext;
import net.minecraft.predicate.entity.DamageSourcePredicate;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.predicate.entity.EntityTypePredicate;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.registry.tag.EnchantmentTags;
import net.minecraft.registry.tag.EntityTypeTags;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Identifier;

public class UpdatedEnchantmentFactory {

    public static final RegistryKey<Enchantment> LIGHTNING_STRIKER =
    RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(EntStupidStuff.MOD_ID, "lightning_striker"));

    public static final RegistryKey<Enchantment> FROSTBITE =
    RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(EntStupidStuff.MOD_ID, "frostbite"));

    public static final RegistryKey<Enchantment> BANEOFRAIDERS =
    RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(EntStupidStuff.MOD_ID, "baneofraiders"));

    /*public static final RegistryKey<Enchantment> BANEOFTHEHUNT =
    RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(EntStupidStuff.MOD_ID, "baneofthehunt"));

    public static final RegistryKey<Enchantment> OSMOSIS =
    RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(EntStupidStuff.MOD_ID, "osmosis"));

    public static final RegistryKey<Enchantment> MAGIC_PROTECTION =
    RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(EntStupidStuff.MOD_ID, "magic_protection"));

    public static final RegistryKey<Enchantment> KNOCKBACK_PROTECTION = //Change to Heavy Armor or Heavy?
    RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(EntStupidStuff.MOD_ID, "knockback_protection"));*/

    //MCD

    /*public static final RegistryKey<Enchantment> CRITICAL_HIT =
    RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(EntStupidStuff.MOD_ID, "critical_hit"));

    public static final RegistryKey<Enchantment> GRAVITY =
    RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(EntStupidStuff.MOD_ID, "gravity"));

    public static final RegistryKey<Enchantment> LEECHING =
    RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(EntStupidStuff.MOD_ID, "leeching"));

    public static final RegistryKey<Enchantment> RAMPAGING = // Or Berserk? - Dif Axe Only
    RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(EntStupidStuff.MOD_ID, "rampaging"));

    public static final RegistryKey<Enchantment> SHOCKWAVE =
    RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(EntStupidStuff.MOD_ID, "shockwave"));

    public static final RegistryKey<Enchantment> SWIRLING =
    RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(EntStupidStuff.MOD_ID, "swirling"));

    public static final RegistryKey<Enchantment> STUNNING =
    RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(EntStupidStuff.MOD_ID, "stunning"));

    public static final RegistryKey<Enchantment> VOIDTOUCH =
    RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(EntStupidStuff.MOD_ID, "voidtouch"));

    //MCD Ranged:

    public static final RegistryKey<Enchantment> BONUSSHOT =
    RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(EntStupidStuff.MOD_ID, "bonus_shot"));

    public static final RegistryKey<Enchantment> MULTICHARGE = //Might get Removed
    RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(EntStupidStuff.MOD_ID, "mutlicharge"));

    public static final RegistryKey<Enchantment> RAPIDFIRE =
    RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(EntStupidStuff.MOD_ID, "rapidfire"));

    public static final RegistryKey<Enchantment> RICOCHET =
    RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(EntStupidStuff.MOD_ID, "ricochet"));*/
    
        
    public static void bootstrap(Registerable<Enchantment> registry) {
        var enchantments = registry.getRegistryLookup(RegistryKeys.ENCHANTMENT);
        var items = registry.getRegistryLookup(RegistryKeys.ITEM);

        register(registry, LIGHTNING_STRIKER, // Has a 30% chance to summon a lightning strike that damages nearby enemies.
        Enchantment.builder(
            Enchantment.definition(
                items.getOrThrow(ItemTags.WEAPON_ENCHANTABLE),
                items.getOrThrow(ItemTags.SWORD_ENCHANTABLE),
                5,
                2,
                Enchantment.leveledCost(5, 7),
                Enchantment.leveledCost(25, 9),
                2,
                AttributeModifierSlot.MAINHAND
            )
        )
        .exclusiveSet(enchantments.getOrThrow(EnchantmentTags.DAMAGE_EXCLUSIVE_SET))
        .addEffect(EnchantmentEffectComponentTypes.POST_ATTACK, EnchantmentEffectTarget.ATTACKER, EnchantmentEffectTarget.VICTIM, new LightningStrikerEnchantmentEffect()));

        register(registry, FROSTBITE,
        Enchantment.builder(
            Enchantment.definition(
                items.getOrThrow(ItemTags.WEAPON_ENCHANTABLE),
                items.getOrThrow(ItemTags.SWORD_ENCHANTABLE),
                5,
                2,
                Enchantment.leveledCost(5, 7),
                Enchantment.leveledCost(25, 9),
                2,
                AttributeModifierSlot.MAINHAND
            )
        )
        .exclusiveSet(enchantments.getOrThrow(EnchantmentTags.DAMAGE_EXCLUSIVE_SET)) //Enchantments.FIRE_ASPECT
        .addEffect(EnchantmentEffectComponentTypes.POST_ATTACK, EnchantmentEffectTarget.ATTACKER, EnchantmentEffectTarget.VICTIM, new FrostbiteEnchantmentEffect())
        );
        







        register(registry, BANEOFRAIDERS, // Attacks deal extra damage to Illagers.
			Enchantment.builder(
				Enchantment.definition(
					items.getOrThrow(ItemTags.WEAPON_ENCHANTABLE),
					items.getOrThrow(ItemTags.SWORD_ENCHANTABLE),
					5,
					5,
					Enchantment.leveledCost(5, 8),
					Enchantment.leveledCost(25, 8),
					2,
					AttributeModifierSlot.MAINHAND
				)
			)
		.exclusiveSet(enchantments.getOrThrow(EnchantmentTags.DAMAGE_EXCLUSIVE_SET))
		.addEffect(EnchantmentEffectComponentTypes.DAMAGE,
			new AddEnchantmentEffect(EnchantmentLevelBasedValue.linear(2.5F)),
			EntityPropertiesLootCondition.builder(
				LootContext.EntityTarget.THIS, EntityPredicate.Builder.create().type(EntityTypePredicate.create(EntityTypeTags.SENSITIVE_TO_BANE_OF_ARTHROPODS)) //Change to Illagers
			)
		)
		.addEffect(EnchantmentEffectComponentTypes.POST_ATTACK, EnchantmentEffectTarget.ATTACKER, EnchantmentEffectTarget.VICTIM,
			new ApplyMobEffectEnchantmentEffect(
				RegistryEntryList.of(StatusEffects.SLOWNESS),
				EnchantmentLevelBasedValue.constant(1.5F),
				EnchantmentLevelBasedValue.linear(1.5F, 0.5F),
				EnchantmentLevelBasedValue.constant(3.0F),
				EnchantmentLevelBasedValue.constant(3.0F)
			),
			EntityPropertiesLootCondition.builder( LootContext.EntityTarget.THIS, EntityPredicate.Builder.create().type(EntityTypePredicate.create(EntityTypeTags.SENSITIVE_TO_BANE_OF_ARTHROPODS))) //Change to Illagers
			.and(DamageSourcePropertiesLootCondition.builder(DamageSourcePredicate.Builder.create().isDirect(true)))
		));
    }

    private static void register(Registerable<Enchantment> registry, RegistryKey<Enchantment> key, Enchantment.Builder builder) {
        registry.register(key, builder.build(key.getValue()));
    }
}
