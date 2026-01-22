package net.ent.entstupidstuff.client.entity.mob;

import org.jetbrains.annotations.Nullable;

import com.mojang.serialization.Codec;

import net.ent.entstupidstuff.client.entity.ai.CannonAttackGoal;
import net.ent.entstupidstuff.client.entity.generic.GenericSkeletonCrossbow;
import net.ent.entstupidstuff.client.entity.projectile.CannonballEntity;
import net.ent.entstupidstuff.item.ItemFactory;
import net.ent.entstupidstuff.item.base.CannonItem;
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
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

public class PhantomSkeletonEntity extends GenericSkeletonCrossbow{

    /*
	 * Melee Attacks: Takes only Normal of the original damage.
	 * Projectile: Takes only 10% of the original damage.
	 * Armor-Piercing Attacks: Takes normal damage.
	 * Takes No Fall Damage
	*/

	@SuppressWarnings("unused")
	private PhantomSkeletonVariant variant;

	private static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(PhantomSkeletonEntity.class, EntityDataSerializers.INT);

	public enum PhantomSkeletonVariant {
		MELEE(0, "melee"),
		FLINTLOCK(1, "flintlock"),
		CROSSBOW(2, "crossbow");
	
		private static final PhantomSkeletonVariant[] VALUES = values();
		private final int id;
		private final String name;

		public static final Codec<PhantomSkeletonVariant> INDEX_CODEC = Codec.INT.xmap(
            PhantomSkeletonVariant::byId,
            PhantomSkeletonVariant::getId
        );
	
		PhantomSkeletonVariant(int id, String name) {
			this.id = id;
			this.name = name;
		}
	
		public int getId() {
			return id;
		}
	
		public String getName() {
			return name;
		}
	
		public static PhantomSkeletonVariant byId(int id) {
			return VALUES[Math.max(0, Math.min(id, VALUES.length - 1))];
		}

		public static PhantomSkeletonVariant getRandom(RandomSource random) {
			RandomSource varientR = RandomSource.create();
        	float varientRC = varientR.nextInt(3) + 1;

			if (varientRC == 1) {
            	return PhantomSkeletonVariant.MELEE;
        	} else if (varientRC == 2) {
            	return PhantomSkeletonVariant.FLINTLOCK;
        	} else {
            	return PhantomSkeletonVariant.CROSSBOW;
        	}

		}
	}

	public PhantomSkeletonEntity(EntityType<? extends PhantomSkeletonEntity> entityType, Level world) {
        super(entityType, world);
    }

	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(4, new CannonAttackGoal<>(this, 1.0, 8.0F));
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.Builder builder) {
		builder.define(VARIANT, 0);
		super.defineSynchedData(builder);
    }

	@Override
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor world, DifficultyInstance difficulty, EntitySpawnReason spawnReason, @Nullable SpawnGroupData entityData) {

		PhantomSkeletonVariant randomVariant = PhantomSkeletonVariant.getRandom(this.getRandom());
        this.setVariant(randomVariant);

        return super.finalizeSpawn(world, difficulty, spawnReason, entityData);
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

		RandomSource varientR = RandomSource.create();
        float varientRC = varientR.nextInt(3) + 1;

		if (varientRC == 1) {
            //this.equipStack(EquipmentSlot.MAINHAND, new ItemStack(ItemFactory.CANNON_ITEM));
			this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.CROSSBOW));
        } else if (varientRC == 2) {
            this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.IRON_SWORD));
        } else {
            this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.BOW));
        }
		
	}

	/*@Override
	protected PersistentProjectileEntity createArrowProjectile(ItemStack arrow, float damageModifier, @Nullable ItemStack shotFrom) {
    	UnderwaterArrowEntity arrowEntity = new UnderwaterArrowEntity(this.getWorld(), this.getX(), this.getY()+1.5F, this.getZ(), arrow, shotFrom);
    	return arrowEntity;
	}*/

	protected AbstractArrow createCannonProjectile(ItemStack arrow, float damageModifier, @Nullable ItemStack shotFrom) {
    	CannonballEntity arrowEntity = new CannonballEntity(this.level(), this.getX(), this.getY()+1.5F, this.getZ(), arrow, shotFrom);
    	return arrowEntity;
	}

	@Override
    public void addAdditionalSaveData(ValueOutput view) {
        super.addAdditionalSaveData(view);
        view.putInt("Variant", this.getVariant().getId());
    }

    @Override
    protected void readAdditionalSaveData(ValueInput view) {
        super.readAdditionalSaveData(view);
		this.setVariant((PhantomSkeletonEntity.PhantomSkeletonVariant)view.read("Variant", PhantomSkeletonEntity.PhantomSkeletonVariant.INDEX_CODEC).orElse(PhantomSkeletonEntity.PhantomSkeletonVariant.MELEE));
    }

	public void setVariant(PhantomSkeletonEntity.PhantomSkeletonVariant variant) {
		this.variant = variant;
		this.entityData.set(VARIANT, variant.getId());
	}

	public PhantomSkeletonVariant getVariant() {
		return PhantomSkeletonVariant.byId(this.entityData.get(VARIANT));
	}

	//Custom Shoot Code:

	@Override
	public boolean canFireProjectileWeapon(ProjectileWeaponItem weapon) {
		ItemStack mainHandStack = this.getMainHandItem();
		if (mainHandStack.getItem() instanceof CannonItem) {
			return weapon == ItemFactory.CANNON_ITEM;
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

		else*/ if (mainHandStack.getItem() instanceof CannonItem) {

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
