package net.ent.entstupidstuff.effects;

import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class BleedingEffect extends MobEffect {
    public BleedingEffect() {
        super(MobEffectCategory.HARMFUL, 0x8B0000); // Dark red color
    }

    protected BleedingEffect(MobEffectCategory statusEffectCategory, int i) {
		super(statusEffectCategory, 0x8B0000);
	}

    protected BleedingEffect(MobEffectCategory statusEffectCategory, int i, ParticleOptions j) {
		super(statusEffectCategory, i);
	}

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true;
    }

}


