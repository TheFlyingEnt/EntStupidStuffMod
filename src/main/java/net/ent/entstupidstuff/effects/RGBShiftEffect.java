package net.ent.entstupidstuff.effects;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.server.world.ServerWorld;

public class RGBShiftEffect extends StatusEffect {
    
    public RGBShiftEffect() {
        super(
            StatusEffectCategory.NEUTRAL,
            0xFF00FF // Color for the effect icon (magenta)
        );
    }
    
    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }
    
    @Override
    public boolean applyUpdateEffect(ServerWorld world, LivingEntity entity, int amplifier) {
		return true;
	}
}
