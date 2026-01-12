package net.ent.entstupidstuff.effects;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public class SunkenEffect extends MobEffect {
    public SunkenEffect() {
        super(MobEffectCategory.HARMFUL, 0x5A5A5A); // HARMFUL effect with a gray color
    }

    @Override
    public boolean applyEffectTick(ServerLevel world, LivingEntity entity, int amplifier) {

        if (!(entity instanceof Player player)) return false;

        //player.addVelocity(0, -0.1, 0);
        //player.setVelocity(player.getVelocity().multiply(1, 0.5 + (amplifier * 0.1), 1));
        //player.velocityModified = true;

        if (player.isInWater()) {
            var vel = player.getDeltaMovement();
            double sinkForce = 0.02 + amplifier * 0.01;

            if (!player.isUnderWater()) return true;

            if (!player.isJumping()) {
                player.setDeltaMovement(vel.x, vel.y - sinkForce, vel.z);
            } else {
                player.setDeltaMovement(vel.x, vel.y * 0.9, vel.z);
            }

            player.hurtMarked = true;
        }

        return true;

        /* 
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
            }*
            
        }
        return false;*/
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true;
    }
}
