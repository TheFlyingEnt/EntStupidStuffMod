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
import net.ent.entstupidstuff.item.ModTags;
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
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.ByIdMap;
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
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.util.AirAndWaterRandomPos;
import net.minecraft.world.entity.ai.util.HoverRandomPos;
import net.minecraft.world.entity.animal.FlyingAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.Vec3;

public class ButterflyEntity extends PathfinderMob implements FlyingAnimal, Jarredable {

    private Variant variant;
    private static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(ButterflyEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> FROM_BUCKET = SynchedEntityData.defineId(ButterflyEntity.class, EntityDataSerializers.BOOLEAN);

    // === Variant Enum ===
    public static enum Variant implements StringRepresentable {
        BIRCH(0, "birch", true, List.of(ModTags.SPAWN_BIRCH_BUTTERFLY)),
        EMPEROR(1, "emperor", true, List.of(ModTags.SPAWN_EMPEROR_BUTTERFLY)),
        MONARCH(2, "monarch", true, List.of(ModTags.SPAWN_MONARCH_BUTTERFLY)),
        YELLOW(3, "yellow", true, List.of(ModTags.SPAWN_YELLOW_BUTTERFLY)),
        LUMINOUS(4, "luminous", true, List.of(ModTags.SPAWN_LUMINOUS_BUTTERFLY)),
        REDWOOD(5, "redwood", true, List.of(ModTags.SPAWN_REDWOOD_BUTTERFLY)),
        BLUE(6, "blue", true, List.of(ModTags.SPAWN_BLUE_BUTTERFLY)),
        SEELE(7, "seele", false, List.of(ModTags.SPAWN_SEELE_BUTTERFLY)),
        CREEPER(8, "creeper", false, List.of(ModTags.SPAWN_CREEPER_BUTTERFLY));

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

    // ## Data NBT
    @Override
    public void addAdditionalSaveData(ValueOutput view) {
        super.addAdditionalSaveData(view);
        view.store("Variant", ButterflyEntity.Variant.INDEX_CODEC, this.getVariant());
        view.putBoolean("FromJar", this.isFromJar());
    }

    @Override
    public void copyDataToStack(ItemStack stack) {
        Jarredable.copyDataToStack(this, stack);
        stack.copyFrom(ModDataComponentTypes.BUTTERFLY_VARIANT, this);
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
        .add(Attributes.FLYING_SPEED, 0.6)
        .add(Attributes.MOVEMENT_SPEED, 0.2)
        .add(Attributes.TEMPT_RANGE, 10.0);
    }

    @Override
    protected void registerGoals() {

        this.goalSelector.addGoal(1, new PanicGoal(this, 2.0));
        this.goalSelector.addGoal(2, new TemptGoal(this, 1.25, (itemStack) -> {
            return itemStack.is(ItemTags.BEE_FOOD);
        }, false));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(5, new ButterflyEntity.ButterflyEntityWanderGoal());
        this.goalSelector.addGoal(6, new FloatGoal(this));

    }

    @Override
    protected PathNavigation createNavigation(Level level) {
        FlyingPathNavigation nav = new FlyingPathNavigation(this, level);
        nav.setCanOpenDoors(false);
        nav.setCanFloat(true);
        return nav;
    }

    @Override
	protected void checkFallDamage(double d, boolean bl, BlockState blockState, BlockPos blockPos) {
	}

    @Override
    public boolean isFlying() {
        return !this.onGround();
    }

    @Override
    public void tick() {
        super.tick();

        if (!this.onGround() && this.getDeltaMovement().y < 0) {
            this.setDeltaMovement(this.getDeltaMovement().multiply(1.0, 0.6, 1.0));
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







   
    // ## Butterfly Goals:

    class ButterflyEntityWanderGoal extends Goal {
		ButterflyEntityWanderGoal() {
			this.setFlags(EnumSet.of(Goal.Flag.MOVE));
		}

		@Override
        public boolean canUse() {
            return !ButterflyEntity.this.isPassenger()
                && ButterflyEntity.this.random.nextInt(7) == 0;
        }

		@Override
		public boolean canContinueToUse() {
			return ButterflyEntity.this.navigation.isInProgress();
		}

		@Override
		public void start() {
			Vec3 vec3 = this.findPos();
			if (vec3 != null) {
				ButterflyEntity.this.navigation.moveTo(ButterflyEntity.this.navigation.createPath(BlockPos.containing(vec3), 1), 1.0);
			}
		}

		@Nullable
		private Vec3 findPos() {
			Vec3 vec32;
			vec32 = ButterflyEntity.this.getViewVector(0.0F);

			int i = 8;
			Vec3 vec33 = HoverRandomPos.getPos(ButterflyEntity.this, 8, 7, vec32.x, vec32.z, (float) (Math.PI / 2), 3, 1);
			return vec33 != null ? vec33 : AirAndWaterRandomPos.getPos(ButterflyEntity.this, 8, 4, -2, vec32.x, vec32.z, (float) (Math.PI / 2));
		}

		private int getWanderThreshold() {
			return 48;
		}
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