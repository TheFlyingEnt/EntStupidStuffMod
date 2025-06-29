package net.ent.entstupidstuff.entity.passive;

import java.util.EnumSet;
import java.util.function.IntFunction;
import java.util.Arrays;

import org.jetbrains.annotations.Nullable;

import com.mojang.serialization.Codec;

import net.ent.entstupidstuff.entity.Jarredable;
import net.ent.entstupidstuff.item.ItemFactory;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.entity.AnimationState;
import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Flutterer;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.control.FlightMoveControl;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.LookAroundGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.FlyingEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.Util;
import net.minecraft.util.function.ValueLists;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.BlockRenderView;
import net.minecraft.world.Heightmap;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

public class ButterflyEntity extends FlyingEntity implements Flutterer, Jarredable {

	private static final TrackedData<Integer> VARIANT = DataTracker.registerData(ButterflyEntity.class, TrackedDataHandlerRegistry.INTEGER);
	private static final TrackedData<Boolean> FROM_BUCKET = DataTracker.registerData(ButterflyEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
	public static final String VARIANT_KEY = "Variant";



	public final AnimationState flyingAnimationState = new AnimationState();
	public final AnimationState roostingAnimationState = new AnimationState();

	public ButterflyEntity(EntityType<? extends ButterflyEntity> entityType, World world) {
		super(entityType, world);
		this.moveControl = new FlightMoveControl(this, 20, true);
		//this.setNoGravity(true);
	}

	@Override
	protected void initDataTracker(DataTracker.Builder builder) {
		super.initDataTracker(builder);
		builder.add(VARIANT, 0);
		builder.add(FROM_BUCKET, false);
	}

	@Override
	public void writeCustomDataToNbt(NbtCompound nbt) {
		super.writeCustomDataToNbt(nbt);
		nbt.putInt("Variant", this.getVariant().getId());
		nbt.putBoolean("FromBucket", this.isFromJar());
	}

	@Override
	public void readCustomDataFromNbt(NbtCompound nbt) {
		super.readCustomDataFromNbt(nbt);
		this.setVariant(Variant.byId(nbt.getInt("Variant")));
		this.setFromJar(nbt.getBoolean("FromBucket"));
	}

	public Variant getVariant() {
		return Variant.byId(this.dataTracker.get(VARIANT));
	}

	public void setVariant(Variant variant) {
		this.dataTracker.set(VARIANT, variant.getId());
	}

	@Override
	public boolean isFromJar() {
		return this.dataTracker.get(FROM_BUCKET);
	}

	@Override
	public void setFromJar(boolean fromBucket) {
		this.dataTracker.set(FROM_BUCKET, fromBucket);
	}

	@Override
	public ActionResult interactMob(PlayerEntity player, Hand hand) {
		return Jarredable.tryJar(player, hand, this).orElse(super.interactMob(player, hand));
	}

	@Override
	public void copyDataToStack(ItemStack stack) {
		Jarredable.copyDataToStack(this, stack);
		NbtComponent.set(DataComponentTypes.BUCKET_ENTITY_DATA, stack, nbt -> {
			nbt.putInt("Variant", this.getVariant().getId());
		});
	}

	@Override
	public void copyDataFromNbt(NbtCompound nbt) {
		Jarredable.copyDataFromNbt(this, nbt);
		this.setVariant(Variant.byId(nbt.getInt("Variant")));
	}

	@Override
	public ItemStack getJarItem() {
		return new ItemStack(ItemFactory.BUTTERFLY_JAR);
	}

	@Override
	public SoundEvent getJarFillSound() {
		return SoundEvents.ITEM_BUCKET_FILL_AXOLOTL;
	}

	@Override
	public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData) {
		if (!(spawnReason == SpawnReason.BUCKET)) {
			this.setVariant(Variant.getRandomNatural(world.getRandom()));
		}
		return super.initialize(world, difficulty, spawnReason, entityData);
	}

	public static DefaultAttributeContainer.Builder createButterflyAttributes() {
		return MobEntity.createMobAttributes()
			.add(EntityAttributes.GENERIC_MAX_HEALTH, 8.0)
			.add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.2)
			.add(EntityAttributes.GENERIC_FLYING_SPEED, 0.35);
	}

	@Override
	protected void initGoals() {
		this.goalSelector.add(1, new BFWanderAroundGoal(this));
		this.goalSelector.add(2, new LookAroundGoal(this));
	}

	/*@Override
	public void tick() {
		super.tick();

		// Vertical bobbing for butterfly flight
		double verticalOffset = Math.sin(this.age * 0.15) * 0.05;
		this.setVelocity(this.getVelocity().add(0, verticalOffset, 0));

		// If colliding with a wall or ground, force slight redirection
		if (this.horizontalCollision || this.verticalCollision) {
			this.getMoveControl().moveTo(
				this.getX() + random.nextGaussian() * 2,
				this.getY() + 1.0,
				this.getZ() + random.nextGaussian() * 2,
				0.3
			);
		}

		updateAnimations();
	}*/

	@Override
public void tick() {
    super.tick();

    // If colliding with a wall or ground, force slight redirection
    if (this.horizontalCollision || this.verticalCollision) {
        this.getMoveControl().moveTo(
            this.getX() + random.nextGaussian() * 2,
            this.getY() + 1.0,
            this.getZ() + random.nextGaussian() * 2,
            0.3
        );
    }

    updateAnimations();
}

	private void updateAnimations() {
		if (!this.isOnGround()) {
			this.roostingAnimationState.stop();
			this.flyingAnimationState.startIfNotRunning(this.age);
		} else {
			this.flyingAnimationState.stop();
			this.roostingAnimationState.startIfNotRunning(this.age);
		}
	}

	public static boolean isValidNaturalSpawn(EntityType<? extends ButterflyEntity> type, WorldAccess world, SpawnReason reason, BlockPos pos, Random random) {
		boolean lightCheck = world.getBaseLightLevel(pos, 0) > 8;
		return lightCheck && world.getBlockState(pos.down()).isIn(BlockTags.ANIMALS_SPAWNABLE_ON);
	}

	// === Variant Enum ===
	public static enum Variant implements StringIdentifiable {

		//Birch - Spawn in Birch Forest
		//Emperor - Everywhere
		//Monarch - Everywhere
		//Blue - Everywhere
		//Lumious - Everywhere
		//Redwood - Spruce
		//Seele - Super RARE
		//Creeper - Super RARE




		BIRCH(0, "birch", true),
		EMPEROR(1, "emperor", true),
		MONARCH(2, "monarch", true),
		YELLOW(3, "yellow", true),
		LUMINOUS(4, "luminous", true),
		REDWOOD(5, "redwood", true),
		BLUE(6, "blue", true),
		SEELE(7, "seele", true),
		CREEPER(8, "creeper", true);

		private static final IntFunction<Variant> BY_ID = ValueLists.createIdToValueFunction(Variant::getId, values(), ValueLists.OutOfBoundsHandling.ZERO);
		public static final Codec<Variant> CODEC = StringIdentifiable.createCodec(Variant::values);
		private final int id;
		private final String name;
		private final boolean natural;

		private Variant(int id, String name, boolean natural) {
			this.id = id;
			this.name = name;
			this.natural = natural;
		}

		public int getId() { return id; }
		public String getName() { return name; }
		public boolean isNatural() { return natural; }

		@Override public String asString() { return name; }

		public static Variant byId(int id) {
			return BY_ID.apply(id);
		}

		public static Variant getRandomNatural(Random random) {
			return getRandom(random, true);
		}

		private static Variant getRandom(Random random, boolean natural) {
			Variant[] list = Arrays.stream(values())
				.filter(v -> v.natural == natural)
				.toArray(Variant[]::new);
			return Util.getRandom(list, random);
		}
	}

	// === Wander AI Goal ===
	class BFWanderAroundGoal extends Goal {
	private final ButterflyEntity butterfly;
	private Vec3d target;
	private int cooldown = 0;

	public BFWanderAroundGoal(ButterflyEntity butterfly) {
		this.butterfly = butterfly;
		this.setControls(EnumSet.of(Control.MOVE));
	}

	@Override
	public boolean canStart() {
		return true;
	}

	@Override
	public boolean shouldContinue() {
		return true;
	}

	/*@Override
	public void tick() {
		if (--cooldown <= 0 || target == null || butterfly.squaredDistanceTo(target) < 1.5) {
			setNewTarget();
			cooldown = 40 + butterfly.random.nextInt(60);
		}

		Vec3d direction = target.subtract(butterfly.getPos()).normalize().multiply(0.1); // control speed here
		butterfly.setVelocity(direction);  // Directly apply velocity

		// Look where it's flying
		butterfly.setYaw((float)(Math.toDegrees(Math.atan2(direction.z, direction.x)) - 90));
		butterfly.setBodyYaw(butterfly.getYaw());
		butterfly.setHeadYaw(butterfly.getYaw());
	}*/

	@Override
	public void tick() {
    if (--cooldown <= 0 || target == null || butterfly.squaredDistanceTo(target) < 1.5) {
        setNewTarget();
        cooldown = 40 + butterfly.random.nextInt(60);
    }

    butterfly.getMoveControl().moveTo(target.x, target.y, target.z, 1.0); // Use moveControl, not setVelocity

    // Optional: rotate to face direction
    Vec3d direction = target.subtract(butterfly.getPos());
    double dx = direction.x;
    double dz = direction.z;
    butterfly.setYaw((float)(Math.toDegrees(Math.atan2(dz, dx)) - 90));
    butterfly.setBodyYaw(butterfly.getYaw());
    butterfly.setHeadYaw(butterfly.getYaw());

    //System.out.println("Target Y: " + target.y + " | Current Y: " + butterfly.getY());
	}


	/*private void setNewTarget() {
		double dx = (butterfly.random.nextDouble() - 0.5) * 20;
		double dz = (butterfly.random.nextDouble() - 0.5) * 20;
		double dy = (butterfly.random.nextDouble() - 0.5) * 6;

		BlockPos ground = butterfly.getBlockPos();
		double x = butterfly.getX() + dx;
		double y = Math.max(ground.getY() + 2.0, butterfly.getY() + dy);
		double z = butterfly.getZ() + dz;

		this.target = new Vec3d(x, y, z);
	}*/

	private void setNewTarget() {
	double dx = (butterfly.random.nextDouble() - 0.5) * 20;
	double dz = (butterfly.random.nextDouble() - 0.5) * 20;

	// Get top Y at current butterfly position
	int topY = butterfly.getWorld().getTopY(Heightmap.Type.WORLD_SURFACE, butterfly.getBlockX(), butterfly.getBlockZ());

	// Clamp target Y between topY and topY + 10
	double minY = topY + 1;
	double maxY = topY + 10;
	double dy = butterfly.getY() + (butterfly.random.nextDouble() - 0.5) * 6.0;
	double y = MathHelper.clamp(dy, minY, maxY);

	this.target = new Vec3d(
		butterfly.getX() + dx,
		y,
		butterfly.getZ() + dz
	);
}


	}

    @Override
    public boolean isInAir() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isInAir'");
    }
}

