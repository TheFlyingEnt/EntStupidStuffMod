package net.ent.entstupidstuff.client.entity.passive;

import java.util.EnumSet;
import java.util.List;
import java.util.function.IntFunction;
import java.util.Arrays;

import org.jetbrains.annotations.Nullable;

import com.mojang.serialization.Codec;

import io.netty.buffer.ByteBuf;
import net.ent.entstupidstuff.client.entity.Jarredable;
import net.ent.entstupidstuff.component.ModDataComponentTypes;
import net.ent.entstupidstuff.item.ItemFactory;
import net.ent.entstupidstuff.item.ModItemTags;
import net.minecraft.component.ComponentType;
import net.minecraft.component.ComponentsAccess;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.entity.AnimationState;
import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Flutterer;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.control.FlightMoveControl;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.LookAroundGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.passive.AxolotlEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.Util;
import net.minecraft.util.function.ValueLists;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.Heightmap;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.biome.Biome;

public class ButterflyEntity extends PathAwareEntity implements Flutterer, Jarredable {

    private Variant variant;
    private static final TrackedData<Integer> VARIANT = DataTracker.registerData(ButterflyEntity.class, TrackedDataHandlerRegistry.INTEGER);
    private static final TrackedData<Boolean> FROM_BUCKET = DataTracker.registerData(ButterflyEntity.class, TrackedDataHandlerRegistry.BOOLEAN);

    // === Variant Enum ===
    public static enum Variant implements StringIdentifiable {
        BIRCH(0, "birch", true, List.of(ModItemTags.SPAWN_BIRCH_BUTTERFLY)),
        EMPEROR(1, "emperor", true, List.of(ModItemTags.SPAWN_EMPEROR_BUTTERFLY)),
        MONARCH(2, "monarch", true, List.of(ModItemTags.SPAWN_MONARCH_BUTTERFLY)),
        YELLOW(3, "yellow", true, List.of(ModItemTags.SPAWN_YELLOW_BUTTERFLY)),
        LUMINOUS(4, "luminous", true, List.of(ModItemTags.SPAWN_LUMINOUS_BUTTERFLY)),
        REDWOOD(5, "redwood", true, List.of(ModItemTags.SPAWN_REDWOOD_BUTTERFLY)),
        BLUE(6, "blue", true, List.of(ModItemTags.SPAWN_BLUE_BUTTERFLY)),
        SEELE(7, "seele", false, List.of(ModItemTags.SPAWN_SEELE_BUTTERFLY)),
        CREEPER(8, "creeper", false, List.of(ModItemTags.SPAWN_CREEPER_BUTTERFLY));

        public static final Variant DEFAULT = BIRCH;

        private static final IntFunction<Variant> INDEX_MAPPER = ValueLists.createIndexToValueFunction(
            Variant::getIndex, values(), ValueLists.OutOfBoundsHandling.ZERO
        );

        public static final Codec<Variant> CODEC = StringIdentifiable.createCodec(Variant::values);
        public static final Codec<Variant> INDEX_CODEC = Codec.INT.xmap(INDEX_MAPPER::apply, Variant::getIndex);
        public static final PacketCodec<ByteBuf, Variant> PACKET_CODEC = PacketCodecs.indexed(INDEX_MAPPER, Variant::getIndex);

        private final int index;
        private final String id;
        private final boolean natural;
        private final List<TagKey<Biome>> allowedBiomeTags;

        private Variant(int index, String id, boolean natural, List<TagKey<Biome>> allowedBiomeTags) {
            this.index = index;
            this.id = id;
            this.natural = natural;
            this.allowedBiomeTags = allowedBiomeTags;
        }

        public int getIndex() { return this.index; }
        public String getId() { return this.id; }
        public boolean isNatural() { return this.natural; }

        public boolean canSpawnInBiome(ServerWorld world, RegistryKey<Biome> biome) {
            RegistryEntry<Biome> entry = world.getRegistryManager().getOrThrow(RegistryKeys.BIOME).getOrThrow(biome);
            return allowedBiomeTags.stream().anyMatch(tag -> entry.isIn(tag));
        }

        public static Variant byIndex(int index) {
            return INDEX_MAPPER.apply(index);
        }

        public static Variant getRandomNatural(Random random) {
            Variant[] list = (Variant[]) Arrays.stream(values()).filter(v -> v.natural).toArray(Variant[]::new);
            return Util.getRandom(list, random);
        }

        public static Variant getRandomForBiome(Random random, ServerWorld world, RegistryKey<Biome> biomeKey) {
            Variant[] validVariants = Arrays.stream(values())
                .filter(v -> biomeKey == null || v.canSpawnInBiome(world, biomeKey))
                .toArray(Variant[]::new);

            if (validVariants.length == 0) {
                validVariants = values(); // fallback
            }

            return Util.getRandom(validVariants, random);
        }

        public static Variant getRandom(Random random) {
            Variant[] list = values();
            return Util.getRandom(list, random);
        }

        @Override
        public String asString() {
            return this.id;
        }
    }


    public final AnimationState flyingAnimationState = new AnimationState();
    public final AnimationState roostingAnimationState = new AnimationState();

    public ButterflyEntity(EntityType<? extends ButterflyEntity> entityType, World world) {
        super(entityType, world);
        this.moveControl = new FlightMoveControl(this, 20, true);
    }

    // === Data NBT ===
    @Override
    public void writeCustomData(WriteView view) {
        super.writeCustomData(view);
        view.put("Variant", ButterflyEntity.Variant.INDEX_CODEC, this.getVariant());
        view.putBoolean("FromJar", this.isFromJar());
    }

    /**
     * Called when creating the jar/bottle ItemStack from the entity (saving entity -> item).
     * Writes both "Variant" and "BucketVariantTag" to the BUCKET_ENTITY_DATA component so
     * both your jar item and vanilla-style loading can read it.
     */
    @Override
    public void copyDataToStack(ItemStack stack) {
        //super.copyDataToStack(stack);
        Jarredable.copyDataToStack(this, stack);
        stack.copy(ModDataComponentTypes.BUTTERFLY_VARIANT, this);
        /*NbtComponent.set(DataComponentTypes.BUCKET_ENTITY_DATA, stack, (nbtCompound) -> {
        	nbtCompound.putInt("Variant", this.getVariant().getId());
    	});*/
    }

    @Override
    public void readCustomData(ReadView view) {
        super.readCustomData(view);
        this.setVariant((ButterflyEntity.Variant) view.read("Variant", ButterflyEntity.Variant.INDEX_CODEC).orElse(ButterflyEntity.Variant.DEFAULT));
        this.setFromJar(view.getBoolean("FromJar", false));
    }

    @Override
	public void copyDataFromNbt(NbtCompound nbt) {
		Jarredable.copyDataFromNbt(this, nbt);
		/*if (nbt.contains("BucketVariantTag", 3)) { // INT_TYPE
			this.setVariant(Variant.byId(nbt.getInt("BucketVariantTag")));
		} else if (nbt.contains("Variant", 3)) {
			this.setVariant(Variant.byId(nbt.getInt("Variant")));
		}*/
	}

    private void setVariant(ButterflyEntity.Variant variant) {
        this.dataTracker.set(VARIANT, variant.getIndex());
    }

    public ButterflyEntity.Variant getVariant() {
        return ButterflyEntity.Variant.byIndex(this.dataTracker.get(VARIANT));
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
        builder.add(VARIANT, 0);
        builder.add(FROM_BUCKET, false);
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
    public ItemStack getJarItem() {
        return new ItemStack(ItemFactory.BUTTERFLY_JAR);
    }

    @Override
    public SoundEvent getJarFillSound() {
        return SoundEvents.ITEM_BOTTLE_FILL;
    }

    @Override
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData) {
		
        if (spawnReason != SpawnReason.BUCKET) {
            Variant variant;

            // SpawnEgg or Spawner ignores biome
            if (spawnReason == SpawnReason.SPAWN_ITEM_USE || spawnReason == SpawnReason.SPAWNER) {
                variant = Variant.getRandom(world.getRandom()); // no biome restriction
            } else {
                // Get biome key for biome-restricted spawning
                ServerWorld serverWorld = (ServerWorld) world;
                RegistryKey<Biome> biomeKey = world.getBiome(getBlockPos()).getKey().orElse(null);
                variant = Variant.getRandomForBiome(random, serverWorld, biomeKey);
            }

            this.setVariant(variant);
        }
		return super.initialize(world, difficulty, spawnReason, entityData);
	}

    public static DefaultAttributeContainer.Builder createButterflyAttributes() {
        return MobEntity.createMobAttributes()
            .add(EntityAttributes.MAX_HEALTH, 8.0)
            .add(EntityAttributes.MOVEMENT_SPEED, 0.2)
            .add(EntityAttributes.FLYING_SPEED, 0.35);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new BFWanderAroundGoal(this));
        this.goalSelector.add(2, new LookAroundGoal(this));
    }

    @Override
    public void tick() {
        super.tick();

        if (this.horizontalCollision || this.verticalCollision) {
            this.getMoveControl().moveTo(
                this.getX() + this.random.nextGaussian() * 2,
                this.getY() + 1.0,
                this.getZ() + this.random.nextGaussian() * 2,
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
        return lightCheck && world.getBlockState(pos.down()).isIn(net.minecraft.registry.tag.BlockTags.ANIMALS_SPAWNABLE_ON);
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

        @Override
        public void tick() {
            if (--cooldown <= 0 || target == null || butterfly.squaredDistanceTo(target) < 1.5) {
                setNewTarget();
                cooldown = 40 + butterfly.random.nextInt(60);
            }

            butterfly.getMoveControl().moveTo(target.x, target.y, target.z, 1.0);

            Vec3d direction = target.subtract(butterfly.getEntityPos());
            double dx = direction.x;
            double dz = direction.z;
            butterfly.setYaw((float)(Math.toDegrees(Math.atan2(dz, dx)) - 90));
            butterfly.setBodyYaw(butterfly.getYaw());
            butterfly.setHeadYaw(butterfly.getYaw());
        }

        private void setNewTarget() {
            double dx = (butterfly.random.nextDouble() - 0.5) * 20;
            double dz = (butterfly.random.nextDouble() - 0.5) * 20;

            int topY = butterfly.getEntityWorld().getTopY(Heightmap.Type.WORLD_SURFACE, butterfly.getBlockX(), butterfly.getBlockZ());

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

    // Flutterer method
    @Override
    public boolean isInAir() {
        return !this.isOnGround();
    }

    @Nullable
	@Override
	public <T> T get(ComponentType<? extends T> type) {
		return type == ModDataComponentTypes.BUTTERFLY_VARIANT ? castComponentValue((ComponentType<T>)type, this.getVariant()) : super.get(type);
	}

	@Override
	protected void copyComponentsFrom(ComponentsAccess from) {
		this.copyComponentFrom(from, ModDataComponentTypes.BUTTERFLY_VARIANT);
		super.copyComponentsFrom(from);
	}

	@Override
	protected <T> boolean setApplicableComponent(ComponentType<T> type, T value) {
		if (type == ModDataComponentTypes.BUTTERFLY_VARIANT) {
			this.setVariant(castComponentValue(ModDataComponentTypes.BUTTERFLY_VARIANT, value));
			return true;
		} else {
			return super.setApplicableComponent(type, value);
		}
	}
}