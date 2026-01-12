package net.ent.entstupidstuff.client.entity.mob;

import java.util.EnumSet;

import org.jetbrains.annotations.Nullable;

import net.ent.entstupidstuff.item.ItemFactory;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.RangedAttackGoal;
import net.minecraft.world.entity.ai.goal.ZombieAttackGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation;
import net.minecraft.world.entity.ai.util.DefaultRandomPos;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.animal.Turtle;
import net.minecraft.world.entity.animal.axolotl.Axolotl;
import net.minecraft.world.entity.monster.Drowned;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.monster.ZombifiedPiglin;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ThrownTrident;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.pathfinder.PathType;
import net.minecraft.world.phys.Vec3;

public class AncientDrownedEntity extends Drowned{

    public static final float field_30460 = 0.03F;
	boolean targetingUnderwater;
	protected final WaterBoundPathNavigation waterNavigation;
	protected final GroundPathNavigation landNavigation;

	public AncientDrownedEntity(EntityType<? extends AncientDrownedEntity> entityType, Level world) {
		super(entityType, world);
		this.moveControl = new AncientDrownedEntity.DrownedMoveControl(this);
		this.setPathfindingMalus(PathType.WATER, 0.0F);
		this.waterNavigation = new WaterBoundPathNavigation(this, world);
		this.landNavigation = new GroundPathNavigation(this, world);
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Zombie.createAttributes().add(Attributes.STEP_HEIGHT, 1.0);
	}

	@Override
	protected void addBehaviourGoals() {
		this.goalSelector.addGoal(1, new AncientDrownedEntity.WanderAroundOnSurfaceGoal(this, 1.0));
		this.goalSelector.addGoal(2, new AncientDrownedEntity.TridentAttackGoal(this, 1.0, 40, 10.0F));
		this.goalSelector.addGoal(2, new AncientDrownedEntity.DrownedAttackGoal(this, 1.0, false));
		this.goalSelector.addGoal(5, new AncientDrownedEntity.LeaveWaterGoal(this, 1.0));
		this.goalSelector.addGoal(6, new AncientDrownedEntity.TargetAboveWaterGoal(this, 1.0, this.level().getSeaLevel()));
		this.goalSelector.addGoal(7, new RandomStrollGoal(this, 1.0));
		this.targetSelector.addGoal(1, new HurtByTargetGoal(this, Drowned.class).setAlertOthers(ZombifiedPiglin.class));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal(this, Player.class, 10, true, false, (target, world) -> this.okTarget(target)));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal(this, AbstractVillager.class, false));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal(this, IronGolem.class, true));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal(this, Axolotl.class, true, false));
		this.targetSelector.addGoal(5, new NearestAttackableTargetGoal(this, Turtle.class, 10, true, false, Turtle.BABY_ON_LAND_SELECTOR));
	}


	@Override
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor world, DifficultyInstance difficulty, EntitySpawnReason spawnReason, @Nullable SpawnGroupData entityData) {
		entityData = super.finalizeSpawn(world, difficulty, spawnReason, entityData);
		if (this.getItemBySlot(EquipmentSlot.OFFHAND).isEmpty() && world.getRandom().nextFloat() < 0.03F) {
			this.setItemSlot(EquipmentSlot.OFFHAND, new ItemStack(Items.NAUTILUS_SHELL));
			this.setGuaranteedDrop(EquipmentSlot.OFFHAND);
		}

		return entityData;
	}

	public static boolean checkDrownedSpawnRules(EntityType<Drowned> type, ServerLevelAccessor world, EntitySpawnReason spawnReason, BlockPos pos, RandomSource random) {
		if (!world.getFluidState(pos.below()).is(FluidTags.WATER) && !EntitySpawnReason.isSpawner(spawnReason)) {
			return false;
		} else {
			Holder<Biome> registryEntry = world.getBiome(pos);
			boolean bl = world.getDifficulty() != Difficulty.PEACEFUL
				&& (EntitySpawnReason.ignoresLightRequirements(spawnReason) || isDarkEnoughToSpawn(world, pos, random))
				&& (EntitySpawnReason.isSpawner(spawnReason) || world.getFluidState(pos).is(FluidTags.WATER));
			if (!bl || !EntitySpawnReason.isSpawner(spawnReason) && spawnReason != EntitySpawnReason.REINFORCEMENT) {
				return registryEntry.is(BiomeTags.MORE_FREQUENT_DROWNED_SPAWNS)
					? random.nextInt(15) == 0 && bl
					: random.nextInt(40) == 0 && isDeepEnoughToSpawn(world, pos) && bl;
			} else {
				return true;
			}
		}
	}

	private static boolean isDeepEnoughToSpawn(LevelAccessor world, BlockPos pos) {
		return pos.getY() < world.getSeaLevel() - 5;
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return this.isInWater() ? SoundEvents.DROWNED_AMBIENT_WATER : SoundEvents.DROWNED_AMBIENT;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return this.isInWater() ? SoundEvents.DROWNED_HURT_WATER : SoundEvents.DROWNED_HURT;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return this.isInWater() ? SoundEvents.DROWNED_DEATH_WATER : SoundEvents.DROWNED_DEATH;
	}

	@Override
	protected SoundEvent getStepSound() {
		return SoundEvents.DROWNED_STEP;
	}

	@Override
	protected SoundEvent getSwimSound() {
		return SoundEvents.DROWNED_SWIM;
	}

	boolean wantsToSwim() {
		if (this.targetingUnderwater) {
			return true;
		} else {
			LivingEntity livingEntity = this.getTarget();
			return livingEntity != null && livingEntity.isInWater();
		}
	}

	@Override
	public void performRangedAttack(LivingEntity target, float pullProgress) {
		ItemStack itemStack = this.getMainHandItem();
		ItemStack itemStack2 = itemStack.is(ItemFactory.ANCIENT_TRIDENT) ? itemStack : new ItemStack(ItemFactory.ANCIENT_TRIDENT);
		ThrownTrident tridentEntity = new ThrownTrident(this.level(), this, itemStack2);
		double d = target.getX() - this.getX();
		double e = target.getY(0.3333333333333333) - tridentEntity.getY();
		double f = target.getZ() - this.getZ();
		double g = Math.sqrt(d * d + f * f);
		if (this.level() instanceof ServerLevel serverWorld) {
			Projectile.spawnProjectileUsingShoot(tridentEntity, serverWorld, itemStack2, d, e + g * 0.2F, f, 1.6F, 14 - this.level().getDifficulty().getId() * 4);
		}

		this.playSound(SoundEvents.DROWNED_SHOOT, 1.0F, 1.0F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
	}

	@Override
	protected void populateDefaultEquipmentSlots(RandomSource random, DifficultyInstance localDifficulty) {
		if (random.nextFloat() > 0.9) {
			int i = random.nextInt(16);
			if (i < 10) {
				this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(ItemFactory.ANCIENT_TRIDENT));
			} else {
				this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.FISHING_ROD));
			}
		}
	}

	/* AncientDrowning AI */

	static class DrownedMoveControl extends MoveControl {
		private final AncientDrownedEntity drowned;

		public DrownedMoveControl(AncientDrownedEntity drowned) {
			super(drowned);
			this.drowned = drowned;
		}

		@Override
		public void tick() {
			LivingEntity livingEntity = this.drowned.getTarget();
			if (this.drowned.wantsToSwim() && this.drowned.isInWater()) {
				if (livingEntity != null && livingEntity.getY() > this.drowned.getY() || this.drowned.targetingUnderwater) {
					this.drowned.setDeltaMovement(this.drowned.getDeltaMovement().add(0.0, 0.002, 0.0));
				}

				if (this.operation != MoveControl.Operation.MOVE_TO || this.drowned.getNavigation().isDone()) {
					this.drowned.setSpeed(0.0F);
					return;
				}

				double d = this.wantedX - this.drowned.getX();
				double e = this.wantedY - this.drowned.getY();
				double f = this.wantedZ - this.drowned.getZ();
				double g = Math.sqrt(d * d + e * e + f * f);
				e /= g;
				float h = (float)(Mth.atan2(f, d) * 180.0F / (float)Math.PI) - 90.0F;
				this.drowned.setYRot(this.rotlerp(this.drowned.getYRot(), h, 90.0F));
				this.drowned.yBodyRot = this.drowned.getYRot();
				float i = (float)(this.speedModifier * this.drowned.getAttributeValue(Attributes.MOVEMENT_SPEED));
				float j = Mth.lerp(0.125F, this.drowned.getSpeed(), i);
				this.drowned.setSpeed(j);
				this.drowned.setDeltaMovement(this.drowned.getDeltaMovement().add(j * d * 0.005, j * e * 0.1, j * f * 0.005));
			} else {
				if (!this.drowned.onGround()) {
					this.drowned.setDeltaMovement(this.drowned.getDeltaMovement().add(0.0, -0.008, 0.0));
				}

				super.tick();
			}
		}
	}

	static class DrownedAttackGoal extends ZombieAttackGoal {
		private final AncientDrownedEntity drowned;

		public DrownedAttackGoal(AncientDrownedEntity drowned, double speed, boolean pauseWhenMobIdle) {
			super(drowned, speed, pauseWhenMobIdle);
			this.drowned = drowned;
		}

		@Override
		public boolean canUse() {
			return super.canUse() && this.drowned.okTarget(this.drowned.getTarget());
		}

		@Override
		public boolean canContinueToUse() {
			return super.canContinueToUse() && this.drowned.okTarget(this.drowned.getTarget());
		}
	}

	static class LeaveWaterGoal extends MoveToBlockGoal {
		private final AncientDrownedEntity drowned;

		public LeaveWaterGoal(AncientDrownedEntity drowned, double speed) {
			super(drowned, speed, 8, 2);
			this.drowned = drowned;
		}

		@Override
		public boolean canUse() {
			return super.canUse()
				&& !this.drowned.level().isBrightOutside()
				&& this.drowned.isInWater()
				&& this.drowned.getY() >= (double)(this.drowned.level().getSeaLevel() - 3);
		}

		@Override
		public boolean canContinueToUse() {
			return super.canContinueToUse();
		}

		@Override
		protected boolean isValidTarget(LevelReader world, BlockPos pos) {
			BlockPos blockPos = pos.above();
			return world.isEmptyBlock(blockPos) && world.isEmptyBlock(blockPos.above()) ? world.getBlockState(pos).entityCanStandOn(world, pos, this.drowned) : false;
		}

		@Override
		public void start() {
			this.drowned.setSearchingForLand(false);
			this.drowned.navigation = this.drowned.landNavigation;
			super.start();
		}

		@Override
		public void stop() {
			super.stop();
		}
	}

	static class TargetAboveWaterGoal extends Goal {
		private final AncientDrownedEntity drowned;
		private final double speed;
		private final int minY;
		private boolean foundTarget;

		public TargetAboveWaterGoal(AncientDrownedEntity drowned, double speed, int minY) {
			this.drowned = drowned;
			this.speed = speed;
			this.minY = minY;
		}

		@Override
		public boolean canUse() {
			return !this.drowned.level().isBrightOutside() && this.drowned.isInWater() && this.drowned.getY() < (double)(this.minY - 2);
		}

		@Override
		public boolean canContinueToUse() {
			return this.canUse() && !this.foundTarget;
		}

		@Override
		public void tick() {
			if (this.drowned.getY() < (double)(this.minY - 1) && (this.drowned.getNavigation().isDone() || this.drowned.closeToNextPos())) {
				Vec3 vec3d = DefaultRandomPos.getPosTowards(
					this.drowned, 4, 8, new Vec3(this.drowned.getX(), (double)(this.minY - 1), this.drowned.getZ()), (float) (Math.PI / 2)
				);
				if (vec3d == null) {
					this.foundTarget = true;
					return;
				}

				this.drowned.getNavigation().moveTo(vec3d.x, vec3d.y, vec3d.z, this.speed);
			}
		}

		@Override
		public void start() {
			this.drowned.setSearchingForLand(true);
			this.foundTarget = false;
		}

		@Override
		public void stop() {
			this.drowned.setSearchingForLand(false);
		}
	}

	static class TridentAttackGoal extends RangedAttackGoal {
		private final AncientDrownedEntity drowned;

		public TridentAttackGoal(RangedAttackMob rangedAttackMob, double d, int i, float f) {
			super(rangedAttackMob, d, i, f);
			this.drowned = (AncientDrownedEntity)rangedAttackMob;
		}

		@Override
		public boolean canUse() {
			return super.canUse() && this.drowned.getMainHandItem().is(ItemFactory.ANCIENT_TRIDENT);
		}

		@Override
		public void start() {
			super.start();
			this.drowned.setAggressive(true);
			this.drowned.startUsingItem(InteractionHand.MAIN_HAND);
		}

		@Override
		public void stop() {
			super.stop();
			this.drowned.stopUsingItem();
			this.drowned.setAggressive(false);
		}
	}

	static class WanderAroundOnSurfaceGoal extends Goal {
		private final PathfinderMob mob;
		private double x;
		private double y;
		private double z;
		private final double speed;
		private final Level world;

		public WanderAroundOnSurfaceGoal(PathfinderMob mob, double speed) {
			this.mob = mob;
			this.speed = speed;
			this.world = mob.level();
			this.setFlags(EnumSet.of(Goal.Flag.MOVE));
		}

		@Override
		public boolean canUse() {
			if (!this.world.isBrightOutside()) {
				return false;
			} else if (this.mob.isInWater()) {
				return false;
			} else {
				Vec3 vec3d = this.getWanderTarget();
				if (vec3d == null) {
					return false;
				} else {
					this.x = vec3d.x;
					this.y = vec3d.y;
					this.z = vec3d.z;
					return true;
				}
			}
		}

		@Override
		public boolean canContinueToUse() {
			return !this.mob.getNavigation().isDone();
		}

		@Override
		public void start() {
			this.mob.getNavigation().moveTo(this.x, this.y, this.z, this.speed);
		}

		@Nullable
		private Vec3 getWanderTarget() {
			RandomSource random = this.mob.getRandom();
			BlockPos blockPos = this.mob.blockPosition();

			for (int i = 0; i < 10; i++) {
				BlockPos blockPos2 = blockPos.offset(random.nextInt(20) - 10, 2 - random.nextInt(8), random.nextInt(20) - 10);
				if (this.world.getBlockState(blockPos2).is(Blocks.WATER)) {
					return Vec3.atBottomCenterOf(blockPos2);
				}
			}

			return null;
		}
	}
    
}
