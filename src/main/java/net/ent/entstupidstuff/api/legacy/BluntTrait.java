package net.ent.entstupidstuff.api.legacy;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;


public class BluntTrait {

    /*public static boolean isWeaponBlunt(PlayerEntity player) {
        return !player.getOffHandStack().isEmpty();
    }*/

    public static void applyBluntEffect(Entity entity) {
        ((LivingEntity) entity).addEffect(new MobEffectInstance(MobEffects.MINING_FATIGUE, 20, 4));
    }

}
