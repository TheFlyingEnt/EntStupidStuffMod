package net.ent.entstupidstuff.effects;

import net.ent.entstupidstuff.particle.ParticleTypesFactory;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class HallucinatingEffect extends MobEffect {

    public HallucinatingEffect() {
        super(
            MobEffectCategory.NEUTRAL,
            1484454,
            ParticleTypesFactory.FALLING_MUSHROOM_SPORE
        );
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true;
    }

    public void onEffectStarted(LivingEntity entity, int amplifier) {
        if (entity.level().isClientSide() && entity instanceof LocalPlayer player) {
            float strength = 0.4f + amplifier * 0.2f;

            player.setYRot(player.getYRot() +
                (float)(player.getRandom().nextGaussian() * strength));
            player.setXRot(player.getXRot() +
                (float)(player.getRandom().nextGaussian() * strength * 0.5f));
        }
    }
}
