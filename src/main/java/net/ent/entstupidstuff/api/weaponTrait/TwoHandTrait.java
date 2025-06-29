package net.ent.entstupidstuff.api.weaponTrait;

import net.ent.entstupidstuff.EntStupidStuff;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
//import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;

public class TwoHandTrait {

    public static boolean isUsingTwoHands(/*PlayerEntity*/ LivingEntity player) {
        return !player.getOffHandStack().isEmpty();
    }

    public static float applyDamageReduction(LivingEntity attacker, LivingEntity target, float baseDamage) {
        double damageMultiplier = 0.25; // 75% damage reduction
        float adjustedDamage = baseDamage * (float) damageMultiplier;
        //target.damage(attacker.getDamageSources().playerAttack((/*PlayerEntity*/ LivingEntity) attacker), adjustedDamage);
        return adjustedDamage;
    }

    public static void applyMiningFatigue(/*PlayerEntity*/ LivingEntity player) {
        player.addStatusEffect(new StatusEffectInstance(StatusEffects.MINING_FATIGUE, 20, 4));
    }

    //Old Implementation:
    public static final Identifier BASE_ATTACK_DAMAGE_MODIFIER_ID = EntStupidStuff.id("two_hand_attack_damage");

    public static void applyDamageReductionOG(LivingEntity attacker, boolean reduceDamage, float baseDamage) {
        //double damageMultiplier = 0.25; // 75% damage reduction
        double damageMultiplier = reduceDamage ? 0.25 : 1.0;
        //float adjustedDamage = baseDamage * (float) damageMultiplier;

        EntityAttributeInstance attackDamageInstance = attacker.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE);

        // Remove the existing modifier if it exists
        if (attackDamageInstance != null) {
            attackDamageInstance.removeModifier(BASE_ATTACK_DAMAGE_MODIFIER_ID);

            attackDamageInstance.addTemporaryModifier(new EntityAttributeModifier(
                BASE_ATTACK_DAMAGE_MODIFIER_ID,  
                baseDamage * (damageMultiplier - 1), 
                EntityAttributeModifier.Operation.ADD_VALUE)
            );
        }
    }


}
