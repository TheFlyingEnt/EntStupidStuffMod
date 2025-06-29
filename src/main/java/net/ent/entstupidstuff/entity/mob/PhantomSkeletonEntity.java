package net.ent.entstupidstuff.entity.mob;

import org.jetbrains.annotations.Nullable;

import net.ent.entstupidstuff.entity.ai.CannonAttackGoal;
import net.ent.entstupidstuff.entity.generic.GenericSkeletonCrossbow;
import net.ent.entstupidstuff.entity.projectile.CannonBallEntity;
import net.ent.entstupidstuff.item.ItemFactory;
import net.ent.entstupidstuff.item.base.FinalCannon;
import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.RangedWeaponItem;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.tag.DamageTypeTags;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;

public class PhantomSkeletonEntity extends GenericSkeletonCrossbow{

    /*
	 * Melee Attacks: Takes only Normal of the original damage.
	 * Projectile: Takes only 10% of the original damage.
	 * Armor-Piercing Attacks: Takes normal damage.
	 * Takes No Fall Damage
	*/

	@SuppressWarnings("unused")
	private PhantomSkeletonVariant variant;

	private static final TrackedData<Integer> VARIANT = DataTracker.registerData(PhantomSkeletonEntity.class, TrackedDataHandlerRegistry.INTEGER);

	public enum PhantomSkeletonVariant {
		MELEE(0, "melee"),
		FLINTLOCK(1, "flintlock"),
		CROSSBOW(2, "crossbow");
	
		private static final PhantomSkeletonVariant[] VALUES = values();
		private final int id;
		private final String name;
	
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

		public static PhantomSkeletonVariant getRandom(Random random) {
			Random varientR = Random.create();
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

	public PhantomSkeletonEntity(EntityType<? extends PhantomSkeletonEntity> entityType, World world) {
        super(entityType, world);
    }


















	@Override
	protected void initGoals() {
		super.initGoals();
		this.goalSelector.add(4, new CannonAttackGoal<>(this, 1.0, 8.0F));
	}

	@Override
	protected void initDataTracker(DataTracker.Builder builder) {
		builder.add(VARIANT, 0);
		super.initDataTracker(builder);
    }

	@Override
	public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData) {

		PhantomSkeletonVariant randomVariant = PhantomSkeletonVariant.getRandom(this.getRandom());
        this.setVariant(randomVariant);

        return super.initialize(world, difficulty, spawnReason, entityData);
	}

    

	@Override
	protected boolean isAffectedByDaylight() {
		return false;
	}

	@Override
    public boolean damage(DamageSource source, float amount) {

		if (source.isIn(DamageTypeTags.IS_FALL) || source.isIn(DamageTypeTags.BURN_FROM_STEPPING)) {
			return false;
		}
		else {
			//boolean bl = source.getSource() instanceof PotionEntity;
			if (source.isIn(DamageTypeTags.IS_PROJECTILE)/* && !bl */) {
				//boolean bl2 = super.damage(source, amount);
				return false;
				//return bl2;
			}
			else {
				return super.damage(source, amount);
			}
		}
    }

	@Override
	public boolean handleFallDamage(float fallDistance, float damageMultiplier, DamageSource damageSource) {
    	return false;
	}

	@Override
	protected void initEquipment(Random random, LocalDifficulty localDifficulty) {

		Random varientR = Random.create();
        float varientRC = varientR.nextInt(3) + 1;

		if (varientRC == 1) {
            //this.equipStack(EquipmentSlot.MAINHAND, new ItemStack(ItemFactory.CANNON_ITEM));
			this.equipStack(EquipmentSlot.MAINHAND, new ItemStack(Items.CROSSBOW));
        } else if (varientRC == 2) {
            this.equipStack(EquipmentSlot.MAINHAND, new ItemStack(Items.IRON_SWORD));
        } else {
            this.equipStack(EquipmentSlot.MAINHAND, new ItemStack(Items.BOW));
        }
		
	}

	/*@Override
	protected PersistentProjectileEntity createArrowProjectile(ItemStack arrow, float damageModifier, @Nullable ItemStack shotFrom) {
    	UnderwaterArrowEntity arrowEntity = new UnderwaterArrowEntity(this.getWorld(), this.getX(), this.getY()+1.5F, this.getZ(), arrow, shotFrom);
    	return arrowEntity;
	}*/

	protected PersistentProjectileEntity createCannonProjectile(ItemStack arrow, float damageModifier, @Nullable ItemStack shotFrom) {
    	CannonBallEntity arrowEntity = new CannonBallEntity(this.getWorld(), this.getX(), this.getY()+1.5F, this.getZ(), arrow, shotFrom);
    	return arrowEntity;
	}

	@Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
		nbt.putInt("Variant", this.getVariant().getId());
        super.writeCustomDataToNbt(nbt);
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        this.setVariant(PhantomSkeletonVariant.byId(nbt.getInt("Variant")));
		super.readCustomDataFromNbt(nbt);
        

    }

	public void setVariant(PhantomSkeletonEntity.PhantomSkeletonVariant variant) {
		this.variant = variant;
		this.dataTracker.set(VARIANT, variant.getId());
	}

	public PhantomSkeletonVariant getVariant() {
		return PhantomSkeletonVariant.byId(this.dataTracker.get(VARIANT));
	}

	//Custom Shoot Code:

	@Override
	public boolean canUseRangedWeapon(RangedWeaponItem weapon) {
		ItemStack mainHandStack = this.getMainHandStack();
		if (mainHandStack.getItem() instanceof FinalCannon) {
			return weapon == ItemFactory.CANNON_ITEM;
		}
		else {
			return weapon == Items.CROSSBOW;
		}
	}


	@Override
	public void shootAt(LivingEntity target, float pullProgress) {
		ItemStack mainHandStack = this.getMainHandStack();

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

		else*/ if (mainHandStack.getItem() instanceof FinalCannon) {

			ItemStack itemStack = this.getStackInHand(ProjectileUtil.getHandPossiblyHolding(this, ItemFactory.CANNON_ITEM));
			ItemStack itemStack2 = this.getProjectileType(itemStack);
			PersistentProjectileEntity persistentProjectileEntity = this.createCannonProjectile(itemStack2, pullProgress, itemStack);
			double d = target.getX() - this.getX();
			double e = target.getBodyY(0.3333333333333333) - persistentProjectileEntity.getY();
			double f = target.getZ() - this.getZ();
			double g = Math.sqrt(d * d + f * f);
			persistentProjectileEntity.setVelocity(d, e + g * 0.2F, f, 1.6F, (float)(14 - this.getWorld().getDifficulty().getId() * 4));
			this.playSound(SoundEvents.ENTITY_SKELETON_SHOOT, 1.0F, 1.0F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
			this.getWorld().spawnEntity(persistentProjectileEntity);
		}

		super.shootAt(target, pullProgress);

	}


}
