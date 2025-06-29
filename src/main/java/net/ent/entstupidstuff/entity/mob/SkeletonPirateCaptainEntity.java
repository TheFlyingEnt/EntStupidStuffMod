package net.ent.entstupidstuff.entity.mob;

import net.ent.entstupidstuff.sound.SoundFactory;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.sound.SoundEvent;
import net.minecraft.world.World;

public class SkeletonPirateCaptainEntity extends SunkenSkeletonEntity{

    public SkeletonPirateCaptainEntity(EntityType<? extends SkeletonPirateCaptainEntity> entityType, World world) {
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
