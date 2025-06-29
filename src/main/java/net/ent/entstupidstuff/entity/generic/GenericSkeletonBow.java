package net.ent.entstupidstuff.entity.generic;

import java.util.EnumSet;

import org.jetbrains.annotations.Nullable;

import net.ent.entstupidstuff.entity.ai.TrackTargetGoal;
import net.ent.entstupidstuff.entity.ai.UnderwaterBowAttackGoal;
import net.ent.entstupidstuff.entity.projectile.UnderwaterArrowEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.ai.control.MoveControl;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.ai.goal.AvoidSunlightGoal;
import net.minecraft.entity.ai.goal.EscapeSunlightGoal;
import net.minecraft.entity.ai.goal.FleeEntityGoal;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.LookAroundGoal;
import net.minecraft.entity.ai.goal.LookAtEntityGoal;
import net.minecraft.entity.ai.goal.RevengeGoal;
import net.minecraft.entity.ai.goal.WanderAroundFarGoal;
import net.minecraft.entity.ai.pathing.MobNavigation;
import net.minecraft.entity.ai.pathing.PathNodeType;
import net.minecraft.entity.ai.pathing.SwimNavigation;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.SkeletonEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.passive.TurtleEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.RangedWeaponItem;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.World;

/*
*   This class is used as a refenece to Sea Skeleton
*   Any Mob refering to this will have the ability to Shoot Arrows Underwater
*   with a Bow
*/

public class GenericSkeletonBow extends SkeletonEntity{

    protected final SwimNavigation waterNavigation;
    protected final MobNavigation landNavigation;


    public GenericSkeletonBow(EntityType<? extends GenericSkeletonBow> entityType, World world) {
        super(entityType, world);
        this.moveControl = new GenericSkeletonBow.GenericSkeletonBowMoveControl(this);
        this.setPathfindingPenalty(PathNodeType.WATER, 0.0F);
        this.waterNavigation = new SwimNavigation(this, world);
        this.landNavigation = new MobNavigation(this, world);
    }



    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
	protected void initGoals() {
		this.goalSelector.add(2, new AvoidSunlightGoal(this));
		this.goalSelector.add(3, new EscapeSunlightGoal(this, 1.0));
		this.goalSelector.add(3, new FleeEntityGoal(this, WolfEntity.class, 6.0F, 1.0, 1.2));
        this.goalSelector.add(4, new UnderwaterBowAttackGoal<>(this, 1.0, 20, 15.0F));
		this.goalSelector.add(5, new WanderAroundFarGoal(this, 1.0));
		this.goalSelector.add(6, new LookAtEntityGoal(this, PlayerEntity.class, 8.0F));
		this.goalSelector.add(6, new LookAroundGoal(this));
        this.goalSelector.add(6, new GenericSkeletonBow.GenericSkeletonBowSwimGoal(this));
        this.goalSelector.add(6, new TrackTargetGoal(this));

		this.targetSelector.add(1, new RevengeGoal(this));
		this.targetSelector.add(2, new ActiveTargetGoal(this, PlayerEntity.class, true));
		this.targetSelector.add(3, new ActiveTargetGoal(this, IronGolemEntity.class, true));
		this.targetSelector.add(3, new ActiveTargetGoal(this, TurtleEntity.class, 10, true, false, TurtleEntity.BABY_TURTLE_ON_LAND_FILTER));
	}

    @Override
	protected void initEquipment(Random random, LocalDifficulty localDifficulty) {
		super.initEquipment(random, localDifficulty);
		this.equipStack(EquipmentSlot.MAINHAND, new ItemStack(Items.BOW));
	}

    @Override
    public void tick() {
        super.tick();
        LivingEntity target = this.getTarget();
        if (target != null) {
            this.lookAtEntity(target, 30.0F, 30.0F);
        }

        if (this.isWet()) {
            this.setAir(300);
        }
    }

    @Override
	protected PersistentProjectileEntity createArrowProjectile(ItemStack arrow, float damageModifier, @Nullable ItemStack shotFrom) {
    	UnderwaterArrowEntity arrowEntity = new UnderwaterArrowEntity(this.getWorld(), this.getX(), this.getY()+1.5F, this.getZ(), arrow, shotFrom);
    	return arrowEntity;
	}

    
    @Override
    public void updateSwimming() {
        if (!this.getWorld().isClient) {
            if (this.canMoveVoluntarily() && this.isTouchingWater() /*&& this.isTargetingUnderwater()*/) {
                this.navigation = this.waterNavigation;
                this.setSwimming(true);
            } else {
                this.navigation = this.landNavigation;
                this.setSwimming(false);
            }
        }
    }

    @Override
    public void travel(Vec3d movementInput) {
        if (this.isLogicalSideForUpdatingMovement() && this.isTouchingWater() /*&& this.isTargetingUnderwater()*/) {
            this.updateVelocity(0.01F, movementInput);
            this.move(MovementType.SELF, this.getVelocity());
            this.setVelocity(this.getVelocity().multiply(0.9));
        } else {
            super.travel(movementInput);
        }
    }

    @Override
	public void shootAt(LivingEntity target, float pullProgress) {
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

    @Override
	public boolean canUseRangedWeapon(RangedWeaponItem weapon) {
		return weapon == Items.BOW;
	}

    @Override
	public void readCustomDataFromNbt(NbtCompound nbt) {
		super.readCustomDataFromNbt(nbt);
		this.updateAttackType();
	}

	@SuppressWarnings("resource")
    @Override
	public void equipStack(EquipmentSlot slot, ItemStack stack) {
		super.equipStack(slot, stack);
		if (!this.getWorld().isClient) {
			this.updateAttackType();
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
            if (/*this.skeleton.isTargetingUnderwater() &&*/ this.skeleton.isTouchingWater()) {
                if (target != null && target.getY() > this.skeleton.getY() /*|| this.skeleton.targetingUnderwater*/) {
                    this.skeleton.setVelocity(this.skeleton.getVelocity().add(0.0, 0.002, 0.0));
                }

                if (this.state != MoveControl.State.MOVE_TO || this.skeleton.getNavigation().isIdle()) {
                    this.skeleton.setMovementSpeed(0.0F);
                    return;
                }

                double d = this.targetX - this.skeleton.getX();
                double e = this.targetY - this.skeleton.getY();
                double f = this.targetZ - this.skeleton.getZ();
                double g = Math.sqrt(d * d + e * e + f * f);
                e /= g;
                float h = (float)(MathHelper.atan2(f, d) * 180.0F / (float)Math.PI) - 90.0F;
                this.skeleton.setYaw(this.wrapDegrees(this.skeleton.getYaw(), h, 90.0F));
                this.skeleton.bodyYaw = this.skeleton.getYaw();
                float i = (float)(this.speed * this.skeleton.getAttributeValue(EntityAttributes.GENERIC_MOVEMENT_SPEED));
                float j = MathHelper.lerp(0.125F, this.skeleton.getMovementSpeed(), i);
                this.skeleton.setMovementSpeed(j);
                this.skeleton.setVelocity(this.skeleton.getVelocity().add((double)j * d * 0.005, (double)j * e * 0.1, (double)j * f * 0.005));
            } else {
                if (!this.skeleton.isOnGround()) {
                    this.skeleton.setVelocity(this.skeleton.getVelocity().add(0.0, -0.008, 0.0));
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
            this.setControls(EnumSet.of(Goal.Control.MOVE, Goal.Control.JUMP));
        }

        @Override
        public boolean canStart() {
            return this.skeleton.isTouchingWater();
        }

        @Override
        public void start() {
            this.skeleton.getNavigation().startMovingTo(this.skeleton.getX(), this.skeleton.getY(), this.skeleton.getZ(), 1.0D);
        }

        @Override
        public void stop() {
            this.skeleton.setSwimming(false);
        }
    }
}
