package net.ent.entstupidstuff.item.base.weapons;

import java.util.List;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.component.Tool;
import net.minecraft.world.item.component.Weapon;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public record ModdedToolMaterial(
	TagKey<Block> incorrectBlocksForDrops, int durability, float speed, float attackDamageBonus, int enchantmentValue, TagKey<Item> repairItems
) {

    private Item.Properties applyBaseSettings(Item.Properties settings) {
		return settings.durability(this.durability).repairable(this.repairItems).enchantable(this.enchantmentValue);
	}

    public Item.Properties applySwordSettings(Item.Properties settings, float attackDamage, float attackSpeed) {
		HolderGetter<Block> registryEntryLookup = BuiltInRegistries.acquireBootstrapRegistrationLookup(BuiltInRegistries.BLOCK);
		return this.applyBaseSettings(settings)
			.component(
				DataComponents.TOOL,
				new Tool(
					List.of(
						Tool.Rule.minesAndDrops(HolderSet.direct(Blocks.COBWEB.builtInRegistryHolder()), 15.0F),
						Tool.Rule.overrideSpeed(registryEntryLookup.getOrThrow(BlockTags.SWORD_INSTANTLY_MINES), Float.MAX_VALUE),
						Tool.Rule.overrideSpeed(registryEntryLookup.getOrThrow(BlockTags.SWORD_EFFICIENT), 1.5F)
					),
					1.0F,
					2,
					false
				)
			)
			.attributes(this.createSwordAttributeModifiers(attackDamage, attackSpeed))
			.component(DataComponents.WEAPON, new Weapon(1));
	}



    
	private ItemAttributeModifiers createSwordAttributeModifiers(float attackDamage, float attackSpeed) {
		return ItemAttributeModifiers.builder()
			.add(
				Attributes.ATTACK_DAMAGE,
				new AttributeModifier(Item.BASE_ATTACK_DAMAGE_ID, attackDamage + this.attackDamageBonus, AttributeModifier.Operation.ADD_VALUE),
				EquipmentSlotGroup.MAINHAND
			)
			.add(
				Attributes.ATTACK_SPEED,
				new AttributeModifier(Item.BASE_ATTACK_SPEED_ID, attackSpeed, AttributeModifier.Operation.ADD_VALUE),
				EquipmentSlotGroup.MAINHAND
			)
			.build();
	}
    
}
