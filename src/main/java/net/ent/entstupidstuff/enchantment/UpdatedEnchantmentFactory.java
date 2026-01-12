package net.ent.entstupidstuff.enchantment;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.enchantment.effect.FrostbiteEnchantmentEffect;
import net.ent.entstupidstuff.enchantment.effect.GravityEnchantmentEffect;
import net.ent.entstupidstuff.enchantment.effect.LightningStrikerEnchantmentEffect;
import net.ent.entstupidstuff.item.ModItemTags;
import net.minecraft.advancements.critereon.DamageSourcePredicate;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.EntityTypePredicate;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.item.enchantment.EnchantmentTarget;
import net.minecraft.world.item.enchantment.LevelBasedValue;
import net.minecraft.world.item.enchantment.effects.AddValue;
import net.minecraft.world.item.enchantment.effects.ApplyMobEffect;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.DamageSourceCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition;

public class UpdatedEnchantmentFactory {

    public static final ResourceKey<Enchantment> LIGHTNING_STRIKER =
    ResourceKey.create(Registries.ENCHANTMENT, ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "lightning_striker"));

    public static final ResourceKey<Enchantment> FROSTBITE =
    ResourceKey.create(Registries.ENCHANTMENT, ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "frostbite"));

    public static final ResourceKey<Enchantment> BANEOFRAIDERS =
    ResourceKey.create(Registries.ENCHANTMENT, ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "baneofraiders"));

    public static final ResourceKey<Enchantment> GRAVITY =
    ResourceKey.create(Registries.ENCHANTMENT, ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "gravity"));

    public static final ResourceKey<Enchantment> OSMOSIS =
    ResourceKey.create(Registries.ENCHANTMENT, ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "osmosis"));

    //public static final RegistryKey<Enchantment> EXCAVATOR =
    //RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(EntStupidStuff.MOD_ID, "excavator"));

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
    
        
    public static void bootstrap(BootstrapContext<Enchantment> registry) {
        var enchantments = registry.lookup(Registries.ENCHANTMENT);
        var items = registry.lookup(Registries.ITEM);

        register(registry, OSMOSIS,
                Enchantment.enchantment(
                    Enchantment.definition(
                        items.getOrThrow(ItemTags.TRIDENT_ENCHANTABLE),
                        2,
                        4,
                        Enchantment.dynamicCost(21, 9),
                        Enchantment.dynamicCost(1, 9),
                        4
                    )
                )//.exclusiveSet(enchantments.getOrThrow(EnchantmentTags.TR))
				.withEffect(
					EnchantmentEffectComponents.ARMOR_EFFECTIVENESS,
					new AddValue(LevelBasedValue.perLevel(-1.0F, -0.1F))
				)
        );

        register(registry, LIGHTNING_STRIKER, // Has a 30% chance to summon a lightning strike that damages nearby enemies.
        Enchantment.enchantment(
            Enchantment.definition(
                items.getOrThrow(ModItemTags.HAMMER_ENCHANTABLE),
                items.getOrThrow(ModItemTags.HAMMER_ENCHANTABLE),
                5,
                2,
                Enchantment.dynamicCost(5, 7),
                Enchantment.dynamicCost(25, 9),
                2,
                EquipmentSlotGroup.MAINHAND
            )
        )
        .exclusiveWith(enchantments.getOrThrow(EnchantmentTags.DAMAGE_EXCLUSIVE))
        .withEffect(EnchantmentEffectComponents.POST_ATTACK, EnchantmentTarget.ATTACKER, EnchantmentTarget.VICTIM, new LightningStrikerEnchantmentEffect()));

        register(registry, GRAVITY, // Pulls in Entity towards target
        Enchantment.enchantment(
            Enchantment.definition(
                items.getOrThrow(ModItemTags.HAMMER_ENCHANTABLE),
                items.getOrThrow(ModItemTags.HAMMER_ENCHANTABLE),
                5,
                1,
                Enchantment.dynamicCost(5, 7),
                Enchantment.dynamicCost(25, 9),
                2,
                EquipmentSlotGroup.MAINHAND
            )
        )
        .exclusiveWith(enchantments.getOrThrow(EnchantmentTags.DAMAGE_EXCLUSIVE))
        .withEffect(EnchantmentEffectComponents.POST_ATTACK, EnchantmentTarget.ATTACKER, EnchantmentTarget.VICTIM, new GravityEnchantmentEffect()));

        register(registry, FROSTBITE,
        Enchantment.enchantment(
            Enchantment.definition(
                items.getOrThrow(ItemTags.WEAPON_ENCHANTABLE),
                items.getOrThrow(ItemTags.SWORD_ENCHANTABLE),
                5,
                2,
                Enchantment.dynamicCost(5, 7),
                Enchantment.dynamicCost(25, 9),
                2,
                EquipmentSlotGroup.MAINHAND
            )
        )
        .exclusiveWith(enchantments.getOrThrow(EnchantmentTags.DAMAGE_EXCLUSIVE)) //Enchantments.FIRE_ASPECT
        .withEffect(EnchantmentEffectComponents.POST_ATTACK, EnchantmentTarget.ATTACKER, EnchantmentTarget.VICTIM, new FrostbiteEnchantmentEffect())
        );
        







        register(registry, BANEOFRAIDERS, // Attacks deal extra damage to Illagers.
			Enchantment.enchantment(
				Enchantment.definition(
					items.getOrThrow(ItemTags.WEAPON_ENCHANTABLE),
					items.getOrThrow(ItemTags.SWORD_ENCHANTABLE),
					5,
					5,
					Enchantment.dynamicCost(5, 8),
					Enchantment.dynamicCost(25, 8),
					2,
					EquipmentSlotGroup.MAINHAND
				)
			)
		.exclusiveWith(enchantments.getOrThrow(EnchantmentTags.DAMAGE_EXCLUSIVE))
		.withEffect(
					EnchantmentEffectComponents.DAMAGE,
					new AddValue(LevelBasedValue.perLevel(2.5F)),
					LootItemEntityPropertyCondition.hasProperties(
						LootContext.EntityTarget.THIS,
						EntityPredicate.Builder.entity().entityType(EntityTypePredicate.of(registry.lookup(Registries.ENTITY_TYPE), EntityTypeTags.SENSITIVE_TO_BANE_OF_ARTHROPODS))
					)
				)
				.withEffect(
					EnchantmentEffectComponents.POST_ATTACK,
					EnchantmentTarget.ATTACKER,
					EnchantmentTarget.VICTIM,
					new ApplyMobEffect(
						HolderSet.direct(MobEffects.SLOWNESS),
						LevelBasedValue.constant(1.5F),
						LevelBasedValue.perLevel(1.5F, 0.5F),
						LevelBasedValue.constant(3.0F),
						LevelBasedValue.constant(3.0F)
					),
					LootItemEntityPropertyCondition.hasProperties(
							LootContext.EntityTarget.THIS,
							EntityPredicate.Builder.entity().entityType(EntityTypePredicate.of(registry.lookup(Registries.ENTITY_TYPE), EntityTypeTags.SENSITIVE_TO_BANE_OF_ARTHROPODS))
						)
						.and(DamageSourceCondition.hasDamageSource(DamageSourcePredicate.Builder.damageType().isDirect(true)))
				)
		);
    }

    private static void register(BootstrapContext<Enchantment> registry, ResourceKey<Enchantment> key, Enchantment.Builder builder) {
        registry.register(key, builder.build(key.location()));
    }
}
