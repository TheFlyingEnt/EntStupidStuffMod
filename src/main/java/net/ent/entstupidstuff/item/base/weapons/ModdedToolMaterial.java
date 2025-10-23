package net.ent.entstupidstuff.item.base.weapons;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.component.type.ToolComponent;
import net.minecraft.component.type.WeaponComponent;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;

public record ModdedToolMaterial(
	TagKey<Block> incorrectBlocksForDrops, int durability, float speed, float attackDamageBonus, int enchantmentValue, TagKey<Item> repairItems
) {

    private Item.Settings applyBaseSettings(Item.Settings settings) {
		return settings.maxDamage(this.durability).repairable(this.repairItems).enchantable(this.enchantmentValue);
	}

    public Item.Settings applySwordSettings(Item.Settings settings, float attackDamage, float attackSpeed) {
		RegistryEntryLookup<Block> registryEntryLookup = Registries.createEntryLookup(Registries.BLOCK);
		return this.applyBaseSettings(settings)
			.component(
				DataComponentTypes.TOOL,
				new ToolComponent(
					List.of(
						ToolComponent.Rule.ofAlwaysDropping(RegistryEntryList.of(Blocks.COBWEB.getRegistryEntry()), 15.0F),
						ToolComponent.Rule.of(registryEntryLookup.getOrThrow(BlockTags.SWORD_INSTANTLY_MINES), Float.MAX_VALUE),
						ToolComponent.Rule.of(registryEntryLookup.getOrThrow(BlockTags.SWORD_EFFICIENT), 1.5F)
					),
					1.0F,
					2,
					false
				)
			)
			.attributeModifiers(this.createSwordAttributeModifiers(attackDamage, attackSpeed))
			.component(DataComponentTypes.WEAPON, new WeaponComponent(1));
	}



    
	private AttributeModifiersComponent createSwordAttributeModifiers(float attackDamage, float attackSpeed) {
		return AttributeModifiersComponent.builder()
			.add(
				EntityAttributes.ATTACK_DAMAGE,
				new EntityAttributeModifier(Item.BASE_ATTACK_DAMAGE_MODIFIER_ID, attackDamage + this.attackDamageBonus, EntityAttributeModifier.Operation.ADD_VALUE),
				AttributeModifierSlot.MAINHAND
			)
			.add(
				EntityAttributes.ATTACK_SPEED,
				new EntityAttributeModifier(Item.BASE_ATTACK_SPEED_MODIFIER_ID, attackSpeed, EntityAttributeModifier.Operation.ADD_VALUE),
				AttributeModifierSlot.MAINHAND
			)
			.build();
	}
    
}
