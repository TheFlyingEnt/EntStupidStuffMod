package net.ent.entstupidstuff.effects;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class RGBShiftEffect extends MobEffect {
    
    public RGBShiftEffect() {
        super(
            MobEffectCategory.NEUTRAL,
            0xFF00FF // Color for the effect icon (magenta)
        );
    }
    
    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true;
    }
    
    @Override
    public boolean applyEffectTick(ServerLevel world, LivingEntity entity, int amplifier) {
		return true;
	}
}
