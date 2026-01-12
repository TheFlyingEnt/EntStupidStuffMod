package net.ent.entstupidstuff.client.entity.mob;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.FleeSunGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RestrictSunGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.animal.Turtle;
import net.minecraft.world.entity.animal.wolf.Wolf;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;





public class SoulSkeletonEntity extends Skeleton{

    public SoulSkeletonEntity(EntityType<? extends SoulSkeletonEntity> entityType, Level world) {
		super(entityType, world);
	}

    @Override
	public boolean canFreeze() {
		return false;
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return SoundEvents.SKELETON_AMBIENT;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundEvents.SKELETON_HURT;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundEvents.SKELETON_DEATH;
	}

	//@Override
	//SoundEvent getStepSound() {
	//	return SoundEvents.ENTITY_STRAY_STEP;
	//}

	@Override
	protected AbstractArrow getArrow(ItemStack arrow, float damageModifier, @Nullable ItemStack shotFrom) {
		AbstractArrow persistentProjectileEntity = super.getArrow(arrow, damageModifier, shotFrom);
		if (persistentProjectileEntity instanceof Arrow arrowEntity) {
			//arrowEntity.addEffect(new StatusEffectInstance(StatusEffects.POISON, 100));
			arrowEntity.igniteForSeconds(10);
		}

		return persistentProjectileEntity;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(2, new RestrictSunGoal(this));
		this.goalSelector.addGoal(3, new FleeSunGoal(this, 1.0));
		this.goalSelector.addGoal(3, new AvoidEntityGoal(this, Wolf.class, 6.0F, 1.0, 1.2));
		this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0));
		this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));
		this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
		this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal(this, Player.class, true));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal(this, IronGolem.class, true));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal(this, Turtle.class, 10, true, false, Turtle.BABY_ON_LAND_SELECTOR));
	}

	public static AttributeSupplier.Builder createSoulSkeletonAttributes() {
		return Skeleton.createMonsterAttributes().add(Attributes.MOVEMENT_SPEED, 0.25);
	}

}
