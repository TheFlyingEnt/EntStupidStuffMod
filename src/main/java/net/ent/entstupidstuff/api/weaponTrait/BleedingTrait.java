package net.ent.entstupidstuff.api.weaponTrait;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.api.IntTrait.ITrait;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;

public class BleedingTrait implements ITrait {

    public static final Identifier BASE_ATTACK_DAMAGE_MODIFIER_ID = EntStupidStuff.id("bleed_damage");

    public static void applyBleedingEffect(PlayerEntity player, LivingEntity target, float baseDamage, boolean DR) {

        float targetHealth = target.getHealth();
        float targetMaxHealth = target.getMaxHealth();

        float damageMultiplier;
        if (DR) 
        {
            damageMultiplier = targetHealth < 0.25 * targetMaxHealth ? 2.0f : 1.0f;
        } 
        else 
        {
            damageMultiplier = 1.0f;
        }

        EntityAttributeInstance attackDamageInstance = player.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE);

        if (attackDamageInstance != null) {
            attackDamageInstance.removeModifier(BASE_ATTACK_DAMAGE_MODIFIER_ID);
                attackDamageInstance.addTemporaryModifier(new EntityAttributeModifier(
                BASE_ATTACK_DAMAGE_MODIFIER_ID,  
                baseDamage * (damageMultiplier), 
                EntityAttributeModifier.Operation.ADD_VALUE)
            );
        }
    }
}

