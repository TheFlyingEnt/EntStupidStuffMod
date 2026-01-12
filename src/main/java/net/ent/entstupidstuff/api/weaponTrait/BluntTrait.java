package net.ent.entstupidstuff.api.weaponTrait;

import net.ent.entstupidstuff.api.IntTrait.ITrait;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;

public class BluntTrait implements ITrait {
    public static void applyBluntEffect(LivingEntity attacker, LivingEntity target) {
        // Apply effects to attacker

        
        
        attacker.addEffect(new MobEffectInstance(MobEffects.SLOWNESS, 40, 4));
        System.out.println(attacker.getName() + " is using Blunt");

        // Apply effects to target
        target.addEffect(new MobEffectInstance(MobEffects.NAUSEA, 100, 1));
        target.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 100, 1));
        target.knockback(1.5, attacker.getX() - target.getX(), attacker.getZ() - target.getZ());
        
        // Create ground particles (omitted for brevity)
        // Custom
    }
}
