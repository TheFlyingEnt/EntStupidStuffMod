package net.ent.entstupidstuff.client.entity.mob;

import org.jetbrains.annotations.Nullable;

import net.ent.entstupidstuff.effects.ModEffects;
import net.ent.entstupidstuff.sound.SoundFactory;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class FungalSkeletonEntity extends Skeleton {

	public FungalSkeletonEntity(EntityType<? extends Skeleton> entityType, Level world) {
		super(entityType, world);
	}

	@Override
	protected AbstractArrow getArrow(ItemStack arrow, float damageModifier, @Nullable ItemStack shotFrom) {
		AbstractArrow persistentProjectileEntity = super.getArrow(arrow, damageModifier, shotFrom);
		if (persistentProjectileEntity instanceof Arrow arrowEntity) {
			arrowEntity.addEffect(new MobEffectInstance(MobEffects.POISON, 100));
			arrowEntity.addEffect(new MobEffectInstance(ModEffects.HALLUC, 100));
		}

		return persistentProjectileEntity;
	}

	// Sound Effect:

	protected SoundEvent getAmbientSound() {
		return SoundFactory.ENTITY_FUNGAL_SKELETON_AMBIENT;
	}

	protected SoundEvent getHurtSound(DamageSource damageSource) {
		return SoundFactory.ENTITY_FUNGAL_SKELETON_HURT;
	}

	protected SoundEvent getDeathSound() {
		return SoundFactory.ENTITY_FUNGAL_SKELETON_DEATH;
	}

	SoundEvent getStepSound() {
		return SoundFactory.ENTITY_FUNGAL_SKELETON_STEP;
	}

}
