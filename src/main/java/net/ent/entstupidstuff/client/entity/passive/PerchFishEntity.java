package net.ent.entstupidstuff.client.entity.passive;

import java.util.Arrays;
import java.util.function.IntFunction;

import org.jetbrains.annotations.Nullable;

import com.mojang.serialization.Codec;

import io.netty.buffer.ByteBuf;
import net.ent.entstupidstuff.component.ModDataComponentTypes;
import net.ent.entstupidstuff.item.ItemFactory;
import net.ent.entstupidstuff.sound.SoundFactory;
import net.minecraft.Util;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.ByIdMap;
import net.minecraft.util.RandomSource;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.animal.AbstractSchoolingFish;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

public class PerchFishEntity extends AbstractSchoolingFish{

    public PerchFishEntity(EntityType<? extends AbstractSchoolingFish> entityType, Level world) {
        super(entityType, world);
    }

    private Variant variant;
    private static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(PerchFishEntity.class, EntityDataSerializers.INT);

    public enum Variant implements StringRepresentable{
        DARK(0, "Dark"),
        LIGHT(1, "Light");

        public static final Variant DEFAULT = DARK;

        private static final IntFunction<Variant> INDEX_MAPPER = ByIdMap.continuous(
            Variant::getIndex, values(), ByIdMap.OutOfBoundsStrategy.ZERO
        );

        public static final Codec<Variant> CODEC = StringRepresentable.fromEnum(Variant::values);
        public static final Codec<Variant> INDEX_CODEC = Codec.INT.xmap(INDEX_MAPPER::apply, Variant::getIndex);
        public static final StreamCodec<ByteBuf, Variant> PACKET_CODEC = ByteBufCodecs.idMapper(INDEX_MAPPER, Variant::getIndex);

        private final int index;
        private final String id;

        private Variant(int index, String id) {
			this.index = index;
			this.id = id;
		}

        public int getIndex() { return this.index; }
        public String getId() { return this.id; }

        public static Variant byIndex(int index) {
            return INDEX_MAPPER.apply(index);
        }

        public static Variant getRandomNatural(RandomSource random) {
            Variant[] list = (Variant[]) Arrays.stream(values()).toArray(Variant[]::new);
            return Util.getRandom(list, random);
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

    @Override
    public ItemStack getBucketItemStack() {
        return new ItemStack(ItemFactory.PERCH_BUCKET);
    }

    @Override
    protected SoundEvent getAmbientSound() {
      return SoundFactory.ENTITY_PERCH_AMBIENT;
    }

    @Override
    protected SoundEvent getDeathSound() {
      return SoundFactory.ENTITY_PERCH_DEATH;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
      return SoundFactory.ENTITY_PERCH_HURT;
    }

    @Override
    protected SoundEvent getFlopSound() {
      return SoundFactory.ENTITY_PERCH_FLOP;
    }

    // === Data NBT ===

    @Override
    public void addAdditionalSaveData(ValueOutput view) {
        super.addAdditionalSaveData(view);
        view.store("Variant", PerchFishEntity.Variant.INDEX_CODEC, this.getVariant());
    }

    @Override
    public void saveToBucketTag(ItemStack stack) {
        super.saveToBucketTag(stack);
        //Bucketable.copyDataToStack(this, stack);
        stack.copyFrom(ModDataComponentTypes.PERCH_FISH_VARIANT, this);
   }

    @Override
    protected void readAdditionalSaveData(ValueInput view) {
        super.readAdditionalSaveData(view);
		this.setVariant((PerchFishEntity.Variant)view.read("Variant", PerchFishEntity.Variant.CODEC).orElse(PerchFishEntity.Variant.DARK));
        //this.setFromBucket(view.getBoolean("FromBucket", false));
    }



    public void setVariant(PerchFishEntity.Variant variant) {
        this.entityData.set(VARIANT, variant.getIndex());
    }

    public Variant getVariant() {
        return PerchFishEntity.Variant.byIndex((Integer)this.entityData.get(VARIANT));
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(VARIANT, 0);
    }

    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor world, DifficultyInstance difficulty, EntitySpawnReason spawnReason, @Nullable SpawnGroupData entityData) {

        if (spawnReason == EntitySpawnReason.BUCKET) {
            return (SpawnGroupData)entityData;
        } else {
            Variant randomVariant = Variant.getRandom(this.getRandom());
            this.setVariant(randomVariant);
        }

        return super.finalizeSpawn(world, difficulty, spawnReason, entityData);
    }

    @Nullable
	@Override
	public <T> T get(DataComponentType<? extends T> type) {
		return type == ModDataComponentTypes.PERCH_FISH_VARIANT ? castComponentValue((DataComponentType<T>)type, this.getVariant()) : super.get(type);
	}

	@Override
	protected void applyImplicitComponents(DataComponentGetter from) {
		this.applyImplicitComponentIfPresent(from, ModDataComponentTypes.PERCH_FISH_VARIANT);
		super.applyImplicitComponents(from);
	}

	@Override
	protected <T> boolean applyImplicitComponent(DataComponentType<T> type, T value) {
		if (type == ModDataComponentTypes.PERCH_FISH_VARIANT) {
			this.setVariant(castComponentValue(ModDataComponentTypes.PERCH_FISH_VARIANT, value));
			return true;
		} else {
			return super.applyImplicitComponent(type, value);
		}
	}
    
}
