package net.ent.entstupidstuff.api.legacy;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;


public class BluntTrait {

    /*public static boolean isWeaponBlunt(PlayerEntity player) {
        return !player.getOffHandStack().isEmpty();
    }*/

    public static void applyBluntEffect(Entity entity) {
        ((LivingEntity) entity).addStatusEffect(new StatusEffectInstance(StatusEffects.MINING_FATIGUE, 20, 4));
    }

}
