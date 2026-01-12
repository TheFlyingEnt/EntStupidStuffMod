package net.ent.entstupidstuff.item.base;

import net.ent.entstupidstuff.EntStupidStuff;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.component.ItemAttributeModifiers;

public class WeaponUpdatedItem extends Item{

    public static final ResourceLocation BASE_ATTACK_RANGE_MODIFIER_ID = EntStupidStuff.id("base_attack_reach");
    public static final ResourceLocation BASE_ATTACK_SWEEP_MODIFIER_ID = EntStupidStuff.id("base_attack_sweep");
	public static final ResourceLocation BASE_ATTACK_KNOCKBACK_MODIFIER_ID = EntStupidStuff.id("base_attack_sweep");

    public WeaponUpdatedItem(ToolMaterial material, Properties settings) {
        super(settings);
    }

    /***
     * @param toolMaterial - Material
     * @param baseAttackDamage - (toolDamage) + toolMaterial Damage
     * @param attackSpeed - Attack Speed
     * @param toolReach   - Attack Reach
     * @param attackSweep - Attack Sweep
     * @param  - Default Speed (4) - #
    */
    public static ItemAttributeModifiers createAttributeModifiers(ToolMaterial material, double baseAttackDamage, float attackSpeed, float toolReach, float attackSweep, float attackKnockback) {
		return ItemAttributeModifiers.builder()
			.add( //Attack Damage
				Attributes.ATTACK_DAMAGE,
				new AttributeModifier(
					BASE_ATTACK_DAMAGE_ID, (double)((float)baseAttackDamage + material.attackDamageBonus()), AttributeModifier.Operation.ADD_VALUE
				),
				EquipmentSlotGroup.MAINHAND
			)
			.add( //Attack Speed
				Attributes.ATTACK_SPEED,
				new AttributeModifier(BASE_ATTACK_SPEED_ID, (double)attackSpeed, AttributeModifier.Operation.ADD_VALUE),
				EquipmentSlotGroup.MAINHAND
			)
            .add( //Attack Distance
                Attributes.ENTITY_INTERACTION_RANGE,
                new AttributeModifier(BASE_ATTACK_RANGE_MODIFIER_ID, toolReach, AttributeModifier.Operation.ADD_VALUE),
                EquipmentSlotGroup.MAINHAND
            )
			.add( //Attack Sweep
                Attributes.SWEEPING_DAMAGE_RATIO,
                new AttributeModifier(BASE_ATTACK_SWEEP_MODIFIER_ID, attackSweep, AttributeModifier.Operation.ADD_VALUE),
                EquipmentSlotGroup.MAINHAND
            )
			.add( //Attack Knockback
                Attributes.ATTACK_KNOCKBACK, 
				new AttributeModifier(BASE_ATTACK_KNOCKBACK_MODIFIER_ID, attackKnockback, AttributeModifier.Operation.ADD_VALUE),
				EquipmentSlotGroup.MAINHAND
			) 
			.build();
	}
    
}
