package net.ent.entstupidstuff.client.entity.mob;

import net.ent.entstupidstuff.effects.ModEffects;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.WorldEvents;

public class FungalZombieEntity extends ZombieEntity{

    public FungalZombieEntity(EntityType<? extends ZombieEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
	public boolean tryAttack(ServerWorld world, Entity target) {
		boolean bl = super.tryAttack(world, target);
		if (bl && this.getMainHandStack().isEmpty() && target instanceof LivingEntity) {
			float f = this.getEntityWorld().getLocalDifficulty(this.getBlockPos()).getLocalDifficulty();
			((LivingEntity)target).addStatusEffect(new StatusEffectInstance(ModEffects.RGB_SHIFT, 140 * (int)f), this);
            ((LivingEntity)target).addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 140 * (int)f), this);
		}

		return bl;
	}

	@Override
	protected boolean canConvertInWater() {
		return true;
	}

	@Override
	protected void convertInWater() {
		this.convertTo(EntityType.ZOMBIE);
		if (!this.isSilent()) {
			this.getEntityWorld().syncWorldEvent(null, WorldEvents.HUSK_CONVERTS_TO_ZOMBIE, this.getBlockPos(), 0);
		}
	}
    
}
