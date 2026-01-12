package net.ent.entstupidstuff.client.entity.mob;

import net.ent.entstupidstuff.sound.SoundFactory;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;

public class SkeletonPirateCaptainEntity extends SunkenSkeletonEntity{

    public SkeletonPirateCaptainEntity(EntityType<? extends SkeletonPirateCaptainEntity> entityType, Level world) {
        super(entityType, world);
    }

    @Override
	protected SoundEvent getAmbientSound() {
		return SoundFactory.ENTITY_METAL_SKELETON_AMBIENT;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundFactory.ENTITY_METAL_SKELETON_HURT;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundFactory.ENTITY_METAL_SKELETON_DEATH;
	}




}
