package net.ent.entstupidstuff.client.entity.generic;

import java.util.EnumSet;

import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Overwrite;

import net.ent.entstupidstuff.client.entity.ai.TrackTargetGoal;
import net.ent.entstupidstuff.client.entity.ai.UnderwaterBowAttackGoal;
import net.ent.entstupidstuff.client.entity.projectile.CannonballEntity;
import net.ent.entstupidstuff.client.entity.projectile.UnderwaterArrowEntity;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.FleeSunGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RestrictSunGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.animal.Turtle;
import net.minecraft.world.entity.animal.wolf.Wolf;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.pathfinder.PathType;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.phys.Vec3;

/*
*   This class is used as a refenece to Sea Skeleton
*   Any Mob refering to this will have the ability to Shoot Arrows Underwater
*   with a Bow
*/

public class GenericSkeletonBow extends Skeleton{

    protected final WaterBoundPathNavigation waterNavigation;
    protected final GroundPathNavigation landNavigation;




    public GenericSkeletonBow(EntityType<? extends GenericSkeletonBow> entityType, Level world) {
        super(entityType, world);
        this.moveControl = new GenericSkeletonBow.GenericSkeletonBowMoveControl(this);
        this.setPathfindingMalus(PathType.WATER, 0.0F);
        this.waterNavigation = new WaterBoundPathNavigation(this, world);
        this.landNavigation = new GroundPathNavigation(this, world);
    }



    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
	protected void registerGoals() {
		this.goalSelector.addGoal(2, new RestrictSunGoal(this));
		this.goalSelector.addGoal(3, new FleeSunGoal(this, 1.0));
		this.goalSelector.addGoal(3, new AvoidEntityGoal(this, Wolf.class, 6.0F, 1.0, 1.2));
        this.goalSelector.addGoal(4, new UnderwaterBowAttackGoal<>(this, 1.0, 20, 15.0F));
		this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0));
		this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));
		this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(6, new GenericSkeletonBow.GenericSkeletonBowSwimGoal(this));
        this.goalSelector.addGoal(6, new TrackTargetGoal(this));

		this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal(this, Player.class, true));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal(this, IronGolem.class, true));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal(this, Turtle.class, 10, true, false, Turtle.BABY_ON_LAND_SELECTOR));
	}

    @Override
	protected void populateDefaultEquipmentSlots(RandomSource random, DifficultyInstance localDifficulty) {
		super.populateDefaultEquipmentSlots(random, localDifficulty);
		this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.BOW));
	}

    @Override
    public void tick() {
        super.tick();
        LivingEntity target = this.getTarget();
        if (target != null) {
            this.lookAt(target, 30.0F, 30.0F);
        }

        if (this.isInWaterOrRain()) {
            this.setAirSupply(300);
        }
    }

    @Override
	protected AbstractArrow getArrow(ItemStack arrow, float damageModifier, @Nullable ItemStack shotFrom) {
    	UnderwaterArrowEntity arrowEntity = new UnderwaterArrowEntity(this.level(), this.getX(), this.getY()+1.5F, this.getZ(), arrow, shotFrom);
    	return arrowEntity;
	}

	protected AbstractArrow createCannonBallProjectile(ItemStack arrow, float damageModifier, @Nullable ItemStack shotFrom) {
    	CannonballEntity arrowEntity = new CannonballEntity(this.level(), this.getX(), this.getY()+1.5F, this.getZ(), arrow, shotFrom);
    	return arrowEntity;
	}

    
    @Override
    public void updateSwimming() {
        if (!this.level().isClientSide()) {
            if (this.canSimulateMovement() && this.isInWater() /*&& this.isTargetingUnderwater()*/) {
                this.navigation = this.waterNavigation;
                this.setSwimming(true);
            } else {
                this.navigation = this.landNavigation;
                this.setSwimming(false);
            }
        }
    }

    @Override
    public void travel(Vec3 movementInput) {
        if (this.isLocalInstanceAuthoritative() && this.isInWater() /*&& this.isTargetingUnderwater()*/) {
            this.moveRelative(0.01F, movementInput);
            this.move(MoverType.SELF, this.getDeltaMovement());
            this.setDeltaMovement(this.getDeltaMovement().scale(0.9));
        } else {
            super.travel(movementInput);
        }
    }

    @Override
	public void performRangedAttack(LivingEntity target, float pullProgress) {
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

    @Override
	public boolean canFireProjectileWeapon(ProjectileWeaponItem weapon) {
		return weapon == Items.BOW;
	}

    @Override
	protected void readAdditionalSaveData(ValueInput view) {
		super.readAdditionalSaveData(view);
		this.reassessWeaponGoal();
	}

	@SuppressWarnings("resource")
    @Override
	public void setItemSlot(EquipmentSlot slot, ItemStack stack) {
		super.setItemSlot(slot, stack);
		if (!this.level().isClientSide()) {
			this.reassessWeaponGoal();
		}
	}

    ///////////////////////////
    /*
    *   GenericSkeletonBowMoveControl
    */
    ///////////////////////////

    public class GenericSkeletonBowMoveControl extends MoveControl {
        private final GenericSkeletonBow skeleton;

        public GenericSkeletonBowMoveControl(GenericSkeletonBow skeleton) {
            super(skeleton);
            this.skeleton = skeleton;
        }

        @Override
        public void tick() {
            LivingEntity target = this.skeleton.getTarget();
            if (/*this.skeleton.isTargetingUnderwater() &&*/ this.skeleton.isInWater()) {
                if (target != null && target.getY() > this.skeleton.getY() /*|| this.skeleton.targetingUnderwater*/) {
                    this.skeleton.setDeltaMovement(this.skeleton.getDeltaMovement().add(0.0, 0.002, 0.0));
                }

                if (this.operation != MoveControl.Operation.MOVE_TO || this.skeleton.getNavigation().isDone()) {
                    this.skeleton.setSpeed(0.0F);
                    return;
                }

                double d = this.wantedX - this.skeleton.getX();
                double e = this.wantedY - this.skeleton.getY();
                double f = this.wantedZ - this.skeleton.getZ();
                double g = Math.sqrt(d * d + e * e + f * f);
                e /= g;
                float h = (float)(Mth.atan2(f, d) * 180.0F / (float)Math.PI) - 90.0F;
                this.skeleton.setYRot(this.rotlerp(this.skeleton.getYRot(), h, 90.0F));
                this.skeleton.yBodyRot = this.skeleton.getYRot();
                float i = (float)(this.speedModifier * this.skeleton.getAttributeValue(Attributes.MOVEMENT_SPEED));
                float j = Mth.lerp(0.125F, this.skeleton.getSpeed(), i);
                this.skeleton.setSpeed(j);
                this.skeleton.setDeltaMovement(this.skeleton.getDeltaMovement().add((double)j * d * 0.005, (double)j * e * 0.1, (double)j * f * 0.005));
            } else {
                if (!this.skeleton.onGround()) {
                    this.skeleton.setDeltaMovement(this.skeleton.getDeltaMovement().add(0.0, -0.008, 0.0));
                }
                super.tick();
            }
        }
    }

    ///////////////////////////
    /*
    *   GenericSkeletonBowSwimGoal
    */
    ///////////////////////////

    public class GenericSkeletonBowSwimGoal extends Goal {
        private final GenericSkeletonBow skeleton;

        public GenericSkeletonBowSwimGoal(GenericSkeletonBow skeleton) {
            this.skeleton = skeleton;
            this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.JUMP));
        }

        @Override
        public boolean canUse() {
            return this.skeleton.isInWater();
        }

        @Override
        public void start() {
            this.skeleton.getNavigation().moveTo(this.skeleton.getX(), this.skeleton.getY(), this.skeleton.getZ(), 1.0D);
        }

        @Override
        public void stop() {
            this.skeleton.setSwimming(false);
        }
    }
}
