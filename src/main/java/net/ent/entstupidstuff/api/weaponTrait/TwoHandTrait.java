package net.ent.entstupidstuff.api.weaponTrait;

import net.ent.entstupidstuff.EntStupidStuff;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class TwoHandTrait {

    public static boolean isUsingTwoHands(/*PlayerEntity*/ LivingEntity player) {
        return !player.getOffhandItem().isEmpty();
    }

    public static float applyDamageReduction(LivingEntity attacker, LivingEntity target, float baseDamage) {
        double damageMultiplier = 0.25; // 75% damage reduction
        float adjustedDamage = baseDamage * (float) damageMultiplier;
        //target.damage(attacker.getDamageSources().playerAttack((/*PlayerEntity*/ LivingEntity) attacker), adjustedDamage);
        return adjustedDamage;
    }

    public static void applyMiningFatigue(/*PlayerEntity*/ LivingEntity player) {
        player.addEffect(new MobEffectInstance(MobEffects.MINING_FATIGUE, 20, 4));
    }

    //Old Implementation:
    public static final ResourceLocation BASE_ATTACK_DAMAGE_MODIFIER_ID = EntStupidStuff.id("two_hand_attack_damage");

    public static void applyDamageReductionOG(LivingEntity attacker, boolean reduceDamage, float baseDamage) {
        //double damageMultiplier = 0.25; // 75% damage reduction
        double damageMultiplier = reduceDamage ? 0.25 : 1.0;
        //float adjustedDamage = baseDamage * (float) damageMultiplier;

        AttributeInstance attackDamageInstance = attacker.getAttribute(Attributes.ATTACK_DAMAGE);

        // Remove the existing modifier if it exists
        if (attackDamageInstance != null) {
            attackDamageInstance.removeModifier(BASE_ATTACK_DAMAGE_MODIFIER_ID);

            attackDamageInstance.addTransientModifier(new AttributeModifier(
                BASE_ATTACK_DAMAGE_MODIFIER_ID,  
                baseDamage * (damageMultiplier - 1), 
                AttributeModifier.Operation.ADD_VALUE)
            );
        }
    }


}
