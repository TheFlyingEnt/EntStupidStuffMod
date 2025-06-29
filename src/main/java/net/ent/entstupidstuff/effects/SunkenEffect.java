package net.ent.entstupidstuff.effects;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.player.PlayerEntity;

public class SunkenEffect extends StatusEffect {
    public SunkenEffect() {
        super(StatusEffectCategory.HARMFUL, 0x5A5A5A); // HARMFUL effect with a gray color
    }

    @Override
    public boolean applyUpdateEffect(LivingEntity entity, int amplifier) {
        if (entity instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) entity;

            // Apply the weight effect in water
            //if (player.isTouchingWater()) {
                player.addVelocity(0, -0.1, 0);
                //player.setVelocity(player.getVelocity().multiply(1, 0, 1)); // Nullify upward motion
                player.setVelocity(player.getVelocity().multiply(1, 0.5 + (amplifier * 0.1), 1)); // Nullify upward motion
                return true;
            //}

            // Apply the weight effect on land
            /*if (player.isOnGround()) {
                player.jump();
                player.setVelocity(player.getVelocity().multiply(1, 0, 1)); // Nullify upward motion
                return true;
            }*/
            
        }
        return false;
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true; // Apply every tick
    }
}
