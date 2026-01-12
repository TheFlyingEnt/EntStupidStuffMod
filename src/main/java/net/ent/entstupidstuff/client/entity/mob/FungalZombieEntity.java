package net.ent.entstupidstuff.client.entity.mob;

import net.ent.entstupidstuff.effects.ModEffects;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LevelEvent;

public class FungalZombieEntity extends Zombie{

    public FungalZombieEntity(EntityType<? extends Zombie> entityType, Level world) {
        super(entityType, world);
    }

    @Override
	public boolean doHurtTarget(ServerLevel world, Entity target) {
		boolean bl = super.doHurtTarget(world, target);
		if (bl && this.getMainHandItem().isEmpty() && target instanceof LivingEntity) {
			float f = this.level().getCurrentDifficultyAt(this.blockPosition()).getEffectiveDifficulty();
			((LivingEntity)target).addEffect(new MobEffectInstance(ModEffects.RGB_SHIFT, 140 * (int)f), this);
            ((LivingEntity)target).addEffect(new MobEffectInstance(MobEffects.NAUSEA, 140 * (int)f), this);
		}

		return bl;
	}

	@Override
	protected boolean convertsInWater() {
		return true;
	}

	@Override
	protected void doUnderWaterConversion() {
		this.convertToZombieType(EntityType.ZOMBIE);
		if (!this.isSilent()) {
			this.level().levelEvent(null, LevelEvent.SOUND_HUSK_TO_ZOMBIE, this.blockPosition(), 0);
		}
	}
    
}
