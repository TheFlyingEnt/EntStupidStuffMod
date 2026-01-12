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
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.TagKey;
import net.minecraft.util.ByIdMap;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.animal.FlyingAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.Vec3;

public class ButterflyEntity extends PathfinderMob implements FlyingAnimal, Jarredable {

    private Variant variant;
    private static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(ButterflyEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> FROM_BUCKET = SynchedEntityData.defineId(ButterflyEntity.class, EntityDataSerializers.BOOLEAN);

    // === Variant Enum ===
    public static enum Variant implements StringRepresentable {
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

        private static final IntFunction<Variant> INDEX_MAPPER = ByIdMap.continuous(
            Variant::getIndex, values(), ByIdMap.OutOfBoundsStrategy.ZERO
        );

        public static final Codec<Variant> CODEC = StringRepresentable.fromEnum(Variant::values);
        public static final Codec<Variant> INDEX_CODEC = Codec.INT.xmap(INDEX_MAPPER::apply, Variant::getIndex);
        public static final StreamCodec<ByteBuf, Variant> PACKET_CODEC = ByteBufCodecs.idMapper(INDEX_MAPPER, Variant::getIndex);

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

        public boolean canSpawnInBiome(ServerLevel world, ResourceKey<Biome> biome) {
            Holder<Biome> entry = world.registryAccess().lookupOrThrow(Registries.BIOME).getOrThrow(biome);
            return allowedBiomeTags.stream().anyMatch(tag -> entry.is(tag));
        }

        public static Variant byIndex(int index) {
            return INDEX_MAPPER.apply(index);
        }

        public static Variant getRandomNatural(RandomSource random) {
            Variant[] list = (Variant[]) Arrays.stream(values()).filter(v -> v.natural).toArray(Variant[]::new);
            return Util.getRandom(list, random);
        }

        public static Variant getRandomForBiome(RandomSource random, ServerLevel world, ResourceKey<Biome> biomeKey) {
            Variant[] validVariants = Arrays.stream(values())
                .filter(v -> biomeKey == null || v.canSpawnInBiome(world, biomeKey))
                .toArray(Variant[]::new);

            if (validVariants.length == 0) {
                validVariants = values(); // fallback
            }

            return Util.getRandom(validVariants, random);
        }

        public static Variant getRandom(RandomSource random) {
            Variant[] list = values();
            return Util.getRandom(list, random);
        }

        @Override
        public String getSerializedName() {
            return this.id;
        }
    }


    public final AnimationState flyingAnimationState = new AnimationState();
    public final AnimationState roostingAnimationState = new AnimationState();

    public ButterflyEntity(EntityType<? extends ButterflyEntity> entityType, Level world) {
        super(entityType, world);
        this.moveControl = new FlyingMoveControl(this, 20, true);
    }

    // === Data NBT ===
    @Override
    public void addAdditionalSaveData(ValueOutput view) {
        super.addAdditionalSaveData(view);
        view.store("Variant", ButterflyEntity.Variant.INDEX_CODEC, this.getVariant());
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
        stack.copyFrom(ModDataComponentTypes.BUTTERFLY_VARIANT, this);
        /*NbtComponent.set(DataComponentTypes.BUCKET_ENTITY_DATA, stack, (nbtCompound) -> {
        	nbtCompound.putInt("Variant", this.getVariant().getId());
    	});*/
    }

    @Override
    public void readAdditionalSaveData(ValueInput view) {
        super.readAdditionalSaveData(view);
        this.setVariant((ButterflyEntity.Variant) view.read("Variant", ButterflyEntity.Variant.INDEX_CODEC).orElse(ButterflyEntity.Variant.DEFAULT));
        this.setFromJar(view.getBooleanOr("FromJar", false));
    }

    @Override
	public void copyDataFromNbt(CompoundTag nbt) {
		Jarredable.copyDataFromNbt(this, nbt);
		/*if (nbt.contains("BucketVariantTag", 3)) { // INT_TYPE
			this.setVariant(Variant.byId(nbt.getInt("BucketVariantTag")));
		} else if (nbt.contains("Variant", 3)) {
			this.setVariant(Variant.byId(nbt.getInt("Variant")));
		}*/
	}

    private void setVariant(ButterflyEntity.Variant variant) {
        this.entityData.set(VARIANT, variant.getIndex());
    }

    public ButterflyEntity.Variant getVariant() {
        return ButterflyEntity.Variant.byIndex(this.entityData.get(VARIANT));
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(VARIANT, 0);
        builder.define(FROM_BUCKET, false);
    }

    @Override
    public boolean isFromJar() {
        return this.entityData.get(FROM_BUCKET);
    }

    @Override
    public void setFromJar(boolean fromBucket) {
        this.entityData.set(FROM_BUCKET, fromBucket);
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        return Jarredable.tryJar(player, hand, this).orElse(super.mobInteract(player, hand));
    }

    @Override
    public ItemStack getJarItem() {
        return new ItemStack(ItemFactory.BUTTERFLY_JAR);
    }

    @Override
    public SoundEvent getJarFillSound() {
        return SoundEvents.BOTTLE_FILL;
    }

    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor world, DifficultyInstance difficulty, EntitySpawnReason spawnReason, @Nullable SpawnGroupData entityData) {
		
        if (spawnReason != EntitySpawnReason.BUCKET) {
            Variant variant;

            // SpawnEgg or Spawner ignores biome
            if (spawnReason == EntitySpawnReason.SPAWN_ITEM_USE || spawnReason == EntitySpawnReason.SPAWNER) {
                variant = Variant.getRandom(world.getRandom()); // no biome restriction
            } else {
                // Get biome key for biome-restricted spawning
                ServerLevel serverWorld = (ServerLevel) world;
                ResourceKey<Biome> biomeKey = world.getBiome(blockPosition()).unwrapKey().orElse(null);
                variant = Variant.getRandomForBiome(random, serverWorld, biomeKey);
            }

            this.setVariant(variant);
        }
		return super.finalizeSpawn(world, difficulty, spawnReason, entityData);
	}

    public static AttributeSupplier.Builder createButterflyAttributes() {
        return Mob.createMobAttributes()
            .add(Attributes.MAX_HEALTH, 8.0)
            .add(Attributes.MOVEMENT_SPEED, 0.2)
            .add(Attributes.FLYING_SPEED, 0.35);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new BFWanderAroundGoal(this));
        this.goalSelector.addGoal(2, new RandomLookAroundGoal(this));
    }

    @Override
    public void tick() {
        super.tick();

        if (this.horizontalCollision || this.verticalCollision) {
            this.getMoveControl().setWantedPosition(
                this.getX() + this.random.nextGaussian() * 2,
                this.getY() + 1.0,
                this.getZ() + this.random.nextGaussian() * 2,
                0.3
            );
        }

        updateAnimations();
    }

    private void updateAnimations() {
        if (!this.onGround()) {
            this.roostingAnimationState.stop();
            this.flyingAnimationState.startIfStopped(this.tickCount);
        } else {
            this.flyingAnimationState.stop();
            this.roostingAnimationState.startIfStopped(this.tickCount);
        }
    }

    public static boolean isValidNaturalSpawn(EntityType<? extends ButterflyEntity> type, LevelAccessor world, EntitySpawnReason reason, BlockPos pos, RandomSource random) {
        boolean lightCheck = world.getRawBrightness(pos, 0) > 8;
        return lightCheck && world.getBlockState(pos.below()).is(net.minecraft.tags.BlockTags.ANIMALS_SPAWNABLE_ON);
    }

    // === Wander AI Goal ===
    class BFWanderAroundGoal extends Goal {
        private final ButterflyEntity butterfly;
        private Vec3 target;
        private int cooldown = 0;

        public BFWanderAroundGoal(ButterflyEntity butterfly) {
            this.butterfly = butterfly;
            this.setFlags(EnumSet.of(Flag.MOVE));
        }

        @Override
        public boolean canUse() {
            return true;
        }

        @Override
        public boolean canContinueToUse() {
            return true;
        }

        @Override
        public void tick() {
            if (--cooldown <= 0 || target == null || butterfly.distanceToSqr(target) < 1.5) {
                setNewTarget();
                cooldown = 40 + butterfly.random.nextInt(60);
            }

            butterfly.getMoveControl().setWantedPosition(target.x, target.y, target.z, 1.0);

            Vec3 direction = target.subtract(butterfly.position());
            double dx = direction.x;
            double dz = direction.z;
            butterfly.setYRot((float)(Math.toDegrees(Math.atan2(dz, dx)) - 90));
            butterfly.setYBodyRot(butterfly.getYRot());
            butterfly.setYHeadRot(butterfly.getYRot());
        }

        private void setNewTarget() {
            double dx = (butterfly.random.nextDouble() - 0.5) * 20;
            double dz = (butterfly.random.nextDouble() - 0.5) * 20;

            int topY = butterfly.level().getHeight(Heightmap.Types.WORLD_SURFACE, butterfly.getBlockX(), butterfly.getBlockZ());

            double minY = topY + 1;
            double maxY = topY + 10;
            double dy = butterfly.getY() + (butterfly.random.nextDouble() - 0.5) * 6.0;
            double y = Mth.clamp(dy, minY, maxY);

            this.target = new Vec3(
                butterfly.getX() + dx,
                y,
                butterfly.getZ() + dz
            );
        }
    }

    // Flutterer method
    @Override
    public boolean isFlying() {
        return !this.onGround();
    }

    @Nullable
	@Override
	public <T> T get(DataComponentType<? extends T> type) {
		return type == ModDataComponentTypes.BUTTERFLY_VARIANT ? castComponentValue((DataComponentType<T>)type, this.getVariant()) : super.get(type);
	}

	@Override
	protected void applyImplicitComponents(DataComponentGetter from) {
		this.applyImplicitComponentIfPresent(from, ModDataComponentTypes.BUTTERFLY_VARIANT);
		super.applyImplicitComponents(from);
	}

	@Override
	protected <T> boolean applyImplicitComponent(DataComponentType<T> type, T value) {
		if (type == ModDataComponentTypes.BUTTERFLY_VARIANT) {
			this.setVariant(castComponentValue(ModDataComponentTypes.BUTTERFLY_VARIANT, value));
			return true;
		} else {
			return super.applyImplicitComponent(type, value);
		}
	}
}