package net.ent.entstupidstuff.client.entity.generic;

import org.jetbrains.annotations.Nullable;

import net.ent.entstupidstuff.client.entity.ai.CannonAttackGoalNew;
import net.ent.entstupidstuff.client.entity.ai.TrackTargetGoal;
import net.ent.entstupidstuff.item.ItemFactory;
import net.ent.entstupidstuff.item.base.CannonItem;
import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.FleeSunGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RangedCrossbowAttackGoal;
import net.minecraft.world.entity.ai.goal.RestrictSunGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.animal.Turtle;
import net.minecraft.world.entity.animal.wolf.Wolf;
import net.minecraft.world.entity.monster.CrossbowAttackMob;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.providers.VanillaEnchantmentProviders;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

/*
*   This class is used as a refenece to Sea Skeleton
*   Any Mob refering to this will have the ability to Shoot Arrows Underwater
*   with a Crossbow with the Ability to Swim!!
*/

public class GenericSkeletonCrossbow extends GenericSkeletonBow implements CrossbowAttackMob{

    private static final EntityDataAccessor<Boolean> IS_CHARGING_CROSSBOW = SynchedEntityData.defineId(GenericSkeletonCrossbow.class, EntityDataSerializers.BOOLEAN);
	private final SimpleContainer inventory = new SimpleContainer(5);

    public GenericSkeletonCrossbow(EntityType<? extends GenericSkeletonCrossbow> entityType, Level world) {
        super(entityType, world);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(2, new RestrictSunGoal(this));
		this.goalSelector.addGoal(3, new FleeSunGoal(this, 1.0));
		this.goalSelector.addGoal(3, new AvoidEntityGoal(this, Wolf.class, 6.0F, 1.0, 1.2));
		this.goalSelector.addGoal(4, new RangedCrossbowAttackGoal<>(this, 1.0, 8.0F));
		this.goalSelector.addGoal(4, new CannonAttackGoalNew<>(this, 1.0, 8.0F));

		this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0));
		this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Mob.class, 8.0F));
		this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
		this.goalSelector.addGoal(6, new GenericSkeletonBow.GenericSkeletonBowSwimGoal(this));
		this.goalSelector.addGoal(6, new TrackTargetGoal(this)); 

		this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal(this, Player.class, true));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal(this, IronGolem.class, true));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal(this, Turtle.class, 10, true, false, Turtle.BABY_ON_LAND_SELECTOR));
	}

    public static AttributeSupplier.Builder createGenericSkeletonCrossbow() {
		return Monster.createMonsterAttributes()
			.add(Attributes.MOVEMENT_SPEED, 0.35F)
			.add(Attributes.MAX_HEALTH, 24.0)
			.add(Attributes.ATTACK_DAMAGE, 5.0)
			.add(Attributes.FOLLOW_RANGE, 32.0);
	}


	@Override
	public boolean canFireProjectileWeapon(ProjectileWeaponItem weapon) {
		if (weapon == Items.CROSSBOW || weapon == ItemFactory.CANNON_ITEM)
			return true;
		return false;
	}


    @Override
	public void addAdditionalSaveData(ValueOutput view) {
      super.addAdditionalSaveData(view);
	}

    @Override
	protected void readAdditionalSaveData(ValueInput view) {
      super.readAdditionalSaveData(view);
		this.setCanPickUpLoot(true);
	}

	@Override
	public float getWalkTargetValue(BlockPos pos, LevelReader world) {
		return 0.0F;
	}

	@Override
	public int getMaxSpawnClusterSize() {
		return 1;
	}

	@Nullable
	@Override
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor world, DifficultyInstance difficulty, EntitySpawnReason spawnReason, @Nullable SpawnGroupData entityData) {
		RandomSource random = world.getRandom();
		this.populateDefaultEquipmentSlots(random, difficulty);
		this.populateDefaultEquipmentEnchantments(world, random, difficulty);
		return super.finalizeSpawn(world, difficulty, spawnReason, entityData);
	}

	@Override
	protected void populateDefaultEquipmentSlots(RandomSource random, DifficultyInstance localDifficulty) {
		this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.CROSSBOW));
	}

	@Override
	protected void enchantSpawnedWeapon(ServerLevelAccessor world, RandomSource random, DifficultyInstance localDifficulty) {
		super.enchantSpawnedWeapon(world, random, localDifficulty);
		if (random.nextInt(300) == 0) {
			ItemStack itemStack = this.getMainHandItem();
			if (itemStack.is(Items.CROSSBOW)) {
				EnchantmentHelper.enchantItemFromProvider(itemStack, world.registryAccess(), VanillaEnchantmentProviders.PILLAGER_SPAWN_CROSSBOW, localDifficulty, random);
			}
		}
	}

    //Crossbow Code:
    public boolean isChargingCrossbow() {
		return this.entityData.get(IS_CHARGING_CROSSBOW);
	}

	@Override
	public void setChargingCrossbow(boolean charging) {
		this.entityData.set(IS_CHARGING_CROSSBOW, charging);
	}

    @Override
	public void onCrossbowAttackPerformed() {
		this.noActionTime = 0;
	}


	public GenericSkeletonCrossbowPose getArmPose() {

        if (this.isChargingCrossbow()) {
			return GenericSkeletonCrossbowPose.CROSSBOW_CHARGE; //|| this.isHolding(ItemFactory.CANNON_ITEM)
		} else {
			return this.isHolding(Items.CROSSBOW) && CrossbowItem.isCharged(this.getWeaponItem()) ? GenericSkeletonCrossbowPose.CROSSBOW_HOLD : GenericSkeletonCrossbowPose.DEFAULT;
		}

	}

    @Override
	protected void defineSynchedData(SynchedEntityData.Builder builder) {
		super.defineSynchedData(builder);
		builder.define(IS_CHARGING_CROSSBOW, false);
	}









    //Sound Events
    @Override
	protected SoundEvent getAmbientSound() {
		return SoundEvents.SKELETON_AMBIENT;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundEvents.SKELETON_DEATH;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundEvents.SKELETON_HURT;
	}

    @Override
	public SlotAccess getSlot(int mappedIndex) {
		int i = mappedIndex - 300;
		return i >= 0 && i < this.inventory.getContainerSize() ? SlotAccess.forContainer(this.inventory, i) : super.getSlot(mappedIndex);
	}

    //Arrow Shooting

	@Override
	public void performRangedAttack(LivingEntity target, float pullProgress) {
		ItemStack mainHandStack = this.getMainHandItem();

		if (mainHandStack.getItem() instanceof CrossbowItem) {
			/*ItemStack itemStack = this.getItemInHand(ProjectileUtil.getWeaponHoldingHand(this, Items.CROSSBOW));
			ItemStack itemStack2 = this.getProjectile(itemStack);
			AbstractArrow persistentProjectileEntity = this.getArrow(itemStack2, pullProgress, itemStack);
			double d = target.getX() - this.getX();
			double e = target.getY(0.3333333333333333) - persistentProjectileEntity.getY();
			double f = target.getZ() - this.getZ();
			double g = Math.sqrt(d * d + f * f);
			persistentProjectileEntity.shoot(d, e + g * 0.2F, f, 1.6F, (float)(14 - this.level().getDifficulty().getId() * 4));
			this.playSound(SoundEvents.SKELETON_SHOOT, 1.0F, 1.0F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
			this.level().addFreshEntity(persistentProjectileEntity);*/
            this.performCrossbowAttack(this, 1.6F);
		}

		else if (mainHandStack.getItem() instanceof CannonItem) {
			ItemStack itemStack = this.getItemInHand(ProjectileUtil.getWeaponHoldingHand(this, ItemFactory.CANNON_ITEM));
			ItemStack itemStack2 = this.getProjectile(itemStack);
			AbstractArrow persistentProjectileEntity = this.createCannonBallProjectile(itemStack2, pullProgress, itemStack);
			
			double d = target.getX() - this.getX();
			double e = target.getY(0.3333333333333333) - persistentProjectileEntity.getY();
			double f = target.getZ() - this.getZ();
			double g = Math.sqrt(d * d + f * f);
			persistentProjectileEntity.shoot(d, e + g * 0.2F, f, 1.6F, (float)(14 - this.level().getDifficulty().getId() * 4));
			this.playSound(SoundEvents.SKELETON_SHOOT, 1.0F, 1.0F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
			this.level().addFreshEntity(persistentProjectileEntity);
		}

		else if (mainHandStack.getItem() instanceof BowItem) {

			ItemStack itemStack = this.getItemInHand(ProjectileUtil.getWeaponHoldingHand(this, Items.BOW));
			ItemStack itemStack2 = this.getProjectile(itemStack);
			AbstractArrow persistentProjectileEntity = this.getArrow(itemStack2, pullProgress, itemStack);
			double d = target.getX() - this.getX();
			double e = target.getY(0.3333333333333333) - persistentProjectileEntity.getY();
			double f = target.getZ() - this.getZ();
			double g = Math.sqrt(d * d + f * f);
			persistentProjectileEntity.shoot(d, e + g * 0.2F, f, 1.6F, (float)(14 - this.level().getDifficulty().getId() * 4));
			this.playSound(SoundEvents.SKELETON_SHOOT, 1.0F, 1.0F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
			this.level().addFreshEntity(persistentProjectileEntity);
		}
	}




}
