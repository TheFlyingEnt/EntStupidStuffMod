package net.ent.entstupidstuff.effects;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.particle.ParticleEffect;

public class BleedingEffect extends StatusEffect {
    public BleedingEffect() {
        super(StatusEffectCategory.HARMFUL, 0x8B0000); // Dark red color
    }

    protected BleedingEffect(StatusEffectCategory statusEffectCategory, int i) {
		super(statusEffectCategory, 0x8B0000);
	}

    protected BleedingEffect(StatusEffectCategory statusEffectCategory, int i, ParticleEffect j) {
		super(statusEffectCategory, i);
	}

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

    @Override
    public boolean applyUpdateEffect(LivingEntity entity, int amplifier) {
        if (entity.getWorld().isClient()) {
            entity.damage(entity.getDamageSources().generic(), 0.5f + amplifier);
            return true;
        }

        return false;
    }
}


