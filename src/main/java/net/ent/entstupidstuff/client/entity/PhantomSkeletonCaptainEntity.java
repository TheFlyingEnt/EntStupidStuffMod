package net.ent.entstupidstuff.client.entity;

import org.jetbrains.annotations.Nullable;

import net.ent.entstupidstuff.client.entity.ai.CannonAttackGoal;
import net.ent.entstupidstuff.client.entity.ai.DoubleBarrelAttackGoal;
import net.ent.entstupidstuff.client.entity.generic.GenericSkeletonCrossbow;
import net.ent.entstupidstuff.client.entity.projectile.CannonballEntity;
import net.ent.entstupidstuff.item.ItemFactory;
import net.ent.entstupidstuff.item.base.CannonItem;
import net.ent.entstupidstuff.item.base.DoubleBarrelCrossbowItem;
import net.ent.entstupidstuff.sound.SoundFactory;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.level.Level;

public class PhantomSkeletonCaptainEntity extends GenericSkeletonCrossbow{

    /*
	 * Melee Attacks: Takes only Normal of the original damage.
	 * Projectile: Takes only 10% of the original damage.
	 * Armor-Piercing Attacks: Takes normal damage.
	 * Takes No Fall Damage
	*/

	public PhantomSkeletonCaptainEntity(EntityType<? extends PhantomSkeletonCaptainEntity> entityType, Level world) {
        super(entityType, world);
    }

	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(4, new CannonAttackGoal<>(this, 1.0, 8.0F));
		this.goalSelector.addGoal(4, new DoubleBarrelAttackGoal<>(this, 1.0, 8.0F));
	}

	@Override
	protected boolean isSunBurnTick() {
		return false;
	}

	@Override
    public boolean hurtServer(ServerLevel world, DamageSource source, float amount) {

		if (source.is(DamageTypeTags.IS_FALL) || source.is(DamageTypeTags.BURN_FROM_STEPPING)) {
			return false;
		}
		else {
			//boolean bl = source.getSource() instanceof PotionEntity;
			if (source.is(DamageTypeTags.IS_PROJECTILE)/* && !bl */) {
				//boolean bl2 = super.damage(world, source, amount);
				return false;
				//return bl2;
			}
			else {
				return super.hurtServer(world, source, amount);
			}
		}
    }

	@Override
	protected void populateDefaultEquipmentSlots(RandomSource random, DifficultyInstance localDifficulty) {
        this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(ItemFactory.CANNON_ITEM));		
	}

	protected AbstractArrow createCannonProjectile(ItemStack arrow, float damageModifier, @Nullable ItemStack shotFrom) {
    	CannonballEntity arrowEntity = new CannonballEntity(this.level(), this.getX(), this.getY()+1.5F, this.getZ(), arrow, shotFrom);
    	return arrowEntity;
	}

	protected AbstractArrow createArrowProjectile(ItemStack arrow, float damageModifier, @Nullable ItemStack shotFrom) {
    	Arrow arrowEntity = new Arrow(this.level(), this.getX(), this.getY()+1.5F, this.getZ(), arrow, shotFrom);
    	return arrowEntity;
	}

	//Custom Shoot Code:

	@Override
	public boolean canFireProjectileWeapon(ProjectileWeaponItem weapon) {
		ItemStack mainHandStack = this.getMainHandItem();
		if (mainHandStack.getItem() instanceof CannonItem) {
			return weapon == ItemFactory.CANNON_ITEM;
		}
		else if (mainHandStack.getItem() instanceof DoubleBarrelCrossbowItem) {
			return weapon == ItemFactory.DOUBLE_BARREL_CROSSBOW;
		}
		else {
			return weapon == Items.CROSSBOW;
		}
	}


	@Override
	public void performRangedAttack(LivingEntity target, float pullProgress) {
		ItemStack mainHandStack = this.getMainHandItem();

		/*if (mainHandStack.getItem() instanceof CrossbowItem) {
			ItemStack itemStack = this.getStackInHand(ProjectileUtil.getHandPossiblyHolding(this, Items.CROSSBOW));
			ItemStack itemStack2 = this.getProjectileType(itemStack);
			PersistentProjectileEntity persistentProjectileEntity = this.createArrowProjectile(itemStack2, pullProgress, itemStack);
			double d = target.getX() - this.getX();
			double e = target.getBodyY(0.3333333333333333) - persistentProjectileEntity.getY();
			double f = target.getZ() - this.getZ();
			double g = Math.sqrt(d * d + f * f);
			persistentProjectileEntity.setVelocity(d, e + g * 0.2F, f, 1.6F, (float)(14 - this.getWorld().getDifficulty().getId() * 4));
			this.playSound(SoundEvents.ENTITY_SKELETON_SHOOT, 1.0F, 1.0F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
			this.getWorld().spawnEntity(persistentProjectileEntity);
		}

		else if (mainHandStack.getItem() instanceof BowItem) {

			ItemStack itemStack = this.getStackInHand(ProjectileUtil.getHandPossiblyHolding(this, Items.BOW));
			ItemStack itemStack2 = this.getProjectileType(itemStack);
			PersistentProjectileEntity persistentProjectileEntity = this.createArrowProjectile(itemStack2, pullProgress, itemStack);
			double d = target.getX() - this.getX();
			double e = target.getBodyY(0.3333333333333333) - persistentProjectileEntity.getY();
			double f = target.getZ() - this.getZ();
			double g = Math.sqrt(d * d + f * f);
			persistentProjectileEntity.setVelocity(d, e + g * 0.2F, f, 1.6F, (float)(14 - this.getWorld().getDifficulty().getId() * 4));
			this.playSound(SoundEvents.ENTITY_SKELETON_SHOOT, 1.0F, 1.0F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
			this.getWorld().spawnEntity(persistentProjectileEntity);
		}

		else*/ /*if (mainHandStack.getItem() instanceof CannonItem) {

			ItemStack itemStack = this.getItemInHand(ProjectileUtil.getWeaponHoldingHand(this, ItemFactory.CANNON_ITEM));
			ItemStack itemStack2 = this.getProjectile(itemStack);
			AbstractArrow persistentProjectileEntity = this.createCannonProjectile(itemStack2, pullProgress, itemStack);
			double d = target.getX() - this.getX();
			double e = target.getY(0.3333333333333333) - persistentProjectileEntity.getY();
			double f = target.getZ() - this.getZ();
			double g = Math.sqrt(d * d + f * f);
			persistentProjectileEntity.shoot(d, e + g * 0.2F, f, 1.6F, (float)(14 - this.level().getDifficulty().getId() * 4));
			this.playSound(SoundEvents.SKELETON_SHOOT, 1.0F, 1.0F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
			this.level().addFreshEntity(persistentProjectileEntity);
		}

		if (mainHandStack.getItem() instanceof DoubleBarrelCrossbowItem) {

			ItemStack itemStack = this.getItemInHand(ProjectileUtil.getWeaponHoldingHand(this, ItemFactory.DOUBLE_BARREL_CROSSBOW));
			ItemStack itemStack2 = this.getProjectile(itemStack);

			AbstractArrow persistentProjectileEntity_1 = this.createArrowProjectile(itemStack2, pullProgress, itemStack);
			AbstractArrow persistentProjectileEntity_2 = this.createArrowProjectile(itemStack2, pullProgress, itemStack);

			double d_1 = target.getX() - this.getX();
			double e_1 = target.getY(0.3333333333333333) - persistentProjectileEntity_1.getY();
			double f_1 = target.getZ() - this.getZ();
			double g_1 = Math.sqrt(d_1 * d_1 + f_1 * f_1);

			double d_2 = target.getX() - this.getX();
			double e_2 = target.getY(0.3333333333333333) - persistentProjectileEntity_2.getY();
			double f_2 = target.getZ() - this.getZ();
			double g_2 = Math.sqrt(d_2 * d_2 + f_2 * f_2);

			persistentProjectileEntity_1.shoot(d_1, e_1 + g_1 * 0.2F, f_1, 1.6F, (float)(14 - this.level().getDifficulty().getId() * 4));

			persistentProjectileEntity_2.shoot(d_2, e_2 + g_2 * 0.2F, f_2, 1.6F, (float)(14 - this.level().getDifficulty().getId() * 4));
			this.playSound(SoundEvents.SKELETON_SHOOT, 1.0F, 1.0F / (this.getRandom().nextFloat() * 0.4F + 0.8F));

			this.level().addFreshEntity(persistentProjectileEntity_1);
			this.level().addFreshEntity(persistentProjectileEntity_2);
		}*/

		super.performRangedAttack(target, pullProgress);

	}

	

	// Sound Effect:

	protected SoundEvent getAmbientSound() {
		return SoundFactory.ENTITY_PHANTOM_SKELETON_AMBIENT;
	}

	protected SoundEvent getHurtSound(DamageSource damageSource) {
		return SoundFactory.ENTITY_PHANTOM_SKELETON_HURT;
	}

	protected SoundEvent getDeathSound() {
		return SoundFactory.ENTITY_PHANTOM_SKELETON_DEATH;
	}

	SoundEvent getStepSound() {
		return SoundFactory.ENTITY_PHANTOM_SKELETON_STEP;
	}



}

