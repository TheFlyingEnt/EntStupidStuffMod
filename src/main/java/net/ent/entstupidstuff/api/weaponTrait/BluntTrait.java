package net.ent.entstupidstuff.api.weaponTrait;

import net.ent.entstupidstuff.api.IntTrait.ITrait;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

public class BluntTrait implements ITrait {
    public static void applyBluntEffect(LivingEntity attacker, LivingEntity target) {
        // Apply effects to attacker

        
        
        attacker.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 40, 4));
        System.out.println(attacker.getName() + " is using Blunt");

        // Apply effects to target
        target.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 100, 1));
        target.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 100, 1));
        target.takeKnockback(1.5, attacker.getX() - target.getX(), attacker.getZ() - target.getZ());
        
        // Create ground particles (omitted for brevity)
        // Custom
    }
}
