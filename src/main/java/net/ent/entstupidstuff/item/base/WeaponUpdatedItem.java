package net.ent.entstupidstuff.item.base;

import net.ent.entstupidstuff.EntStupidStuff;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterial;
import net.minecraft.util.Identifier;

public class WeaponUpdatedItem extends Item{

    public static final Identifier BASE_ATTACK_RANGE_MODIFIER_ID = EntStupidStuff.id("base_attack_reach");
    public static final Identifier BASE_ATTACK_SWEEP_MODIFIER_ID = EntStupidStuff.id("base_attack_sweep");
	public static final Identifier BASE_ATTACK_KNOCKBACK_MODIFIER_ID = EntStupidStuff.id("base_attack_sweep");

    public WeaponUpdatedItem(ToolMaterial material, Settings settings) {
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
    public static AttributeModifiersComponent createAttributeModifiers(ToolMaterial material, double baseAttackDamage, float attackSpeed, float toolReach, float attackSweep, float attackKnockback) {
		return AttributeModifiersComponent.builder()
			.add( //Attack Damage
				EntityAttributes.ATTACK_DAMAGE,
				new EntityAttributeModifier(
					BASE_ATTACK_DAMAGE_MODIFIER_ID, (double)((float)baseAttackDamage + material.attackDamageBonus()), EntityAttributeModifier.Operation.ADD_VALUE
				),
				AttributeModifierSlot.MAINHAND
			)
			.add( //Attack Speed
				EntityAttributes.ATTACK_SPEED,
				new EntityAttributeModifier(BASE_ATTACK_SPEED_MODIFIER_ID, (double)attackSpeed, EntityAttributeModifier.Operation.ADD_VALUE),
				AttributeModifierSlot.MAINHAND
			)
            .add( //Attack Distance
                EntityAttributes.ENTITY_INTERACTION_RANGE,
                new EntityAttributeModifier(BASE_ATTACK_RANGE_MODIFIER_ID, toolReach, EntityAttributeModifier.Operation.ADD_VALUE),
                AttributeModifierSlot.MAINHAND
            )
			.add( //Attack Sweep
                EntityAttributes.SWEEPING_DAMAGE_RATIO,
                new EntityAttributeModifier(BASE_ATTACK_SWEEP_MODIFIER_ID, attackSweep, EntityAttributeModifier.Operation.ADD_VALUE),
                AttributeModifierSlot.MAINHAND
            )
			.add( //Attack Knockback
                EntityAttributes.ATTACK_KNOCKBACK, 
				new EntityAttributeModifier(BASE_ATTACK_KNOCKBACK_MODIFIER_ID, attackKnockback, EntityAttributeModifier.Operation.ADD_VALUE),
				AttributeModifierSlot.MAINHAND
			) 
			.build();
	}
    
}
