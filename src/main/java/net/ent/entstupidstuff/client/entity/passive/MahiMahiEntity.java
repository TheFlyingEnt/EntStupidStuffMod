package net.ent.entstupidstuff.client.entity.passive;

import java.util.Arrays;
import java.util.function.IntFunction;

import org.jetbrains.annotations.Nullable;

import com.mojang.serialization.Codec;

import io.netty.buffer.ByteBuf;
import net.ent.entstupidstuff.component.ModDataComponentTypes;
import net.ent.entstupidstuff.item.ItemFactory;
import net.ent.entstupidstuff.sound.SoundFactory;
import net.minecraft.component.ComponentType;
import net.minecraft.component.ComponentsAccess;
import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.passive.SchoolingFishEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.sound.SoundEvent;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.Util;
import net.minecraft.util.function.ValueLists;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;

public class MahiMahiEntity extends SchoolingFishEntity{

    public MahiMahiEntity(EntityType<? extends SchoolingFishEntity> entityType, World world) {
        super(entityType, world);
    }

    private static final TrackedData<Integer> VARIANT = DataTracker.registerData(MahiMahiEntity.class, TrackedDataHandlerRegistry.INTEGER);

    public enum Variant implements StringIdentifiable{
        GREEN(0, "Green"),
        BLUE(1, "Blue");

        public static final Variant DEFAULT = GREEN;

        private static final IntFunction<Variant> INDEX_MAPPER = ValueLists.createIndexToValueFunction(
            Variant::getIndex, values(), ValueLists.OutOfBoundsHandling.ZERO
        );

        public static final Codec<Variant> CODEC = StringIdentifiable.createCodec(Variant::values);
        public static final Codec<Variant> INDEX_CODEC = Codec.INT.xmap(INDEX_MAPPER::apply, Variant::getIndex);
        public static final PacketCodec<ByteBuf, Variant> PACKET_CODEC = PacketCodecs.indexed(INDEX_MAPPER, Variant::getIndex);

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

        public static Variant getRandomNatural(Random random) {
            Variant[] list = (Variant[]) Arrays.stream(values()).toArray(Variant[]::new);
            return Util.getRandom(list, random);
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

    @Override
    public ItemStack getBucketItem() {
        return new ItemStack(ItemFactory.MAHIMAHI_BUCKET);
    }

    @Override
    protected SoundEvent getAmbientSound() {
      return SoundFactory.ENTITY_MAHIMAHI_AMBIENT;
    }

    @Override
    protected SoundEvent getDeathSound() {
      return SoundFactory.ENTITY_MAHIMAHI_DEATH;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
      return SoundFactory.ENTITY_MAHIMAHI_HURT;
    }
    @Override
    protected SoundEvent getFlopSound() {
      return SoundFactory.ENTITY_MAHIMAHI_FLOP;
    }

    // === Data NBT ===

    @Override
    public void writeCustomData(WriteView view) {
        super.writeCustomData(view);
        view.put("Variant", MahiMahiEntity.Variant.INDEX_CODEC, this.getVariant());
    }

    @Override
    public void copyDataToStack(ItemStack stack) {
        super.copyDataToStack(stack);
        //Bucketable.copyDataToStack(this, stack);
        stack.copy(ModDataComponentTypes.MAHIMAHI_FISH_VARIANT, this);
   }

    @Override
    protected void readCustomData(ReadView view) {
        super.readCustomData(view);
		this.setVariant((MahiMahiEntity.Variant)view.read("Variant", MahiMahiEntity.Variant.CODEC).orElse(MahiMahiEntity.Variant.DEFAULT));
    }

    public void setVariant(MahiMahiEntity.Variant variant) {
        this.dataTracker.set(VARIANT, variant.getIndex());
    }

    public Variant getVariant() {
        return MahiMahiEntity.Variant.byIndex((Integer)this.dataTracker.get(VARIANT));
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
        builder.add(VARIANT, 0);
    }

    @Override
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData) {

        if (spawnReason == SpawnReason.BUCKET) {
            return (EntityData)entityData;
        } else {
            Variant randomVariant = Variant.getRandom(this.getRandom());
            this.setVariant(randomVariant);
        }

        return super.initialize(world, difficulty, spawnReason, entityData);
    }

    @Nullable
	@Override
	public <T> T get(ComponentType<? extends T> type) {
		return type == ModDataComponentTypes.MAHIMAHI_FISH_VARIANT ? castComponentValue((ComponentType<T>)type, this.getVariant()) : super.get(type);
	}

	@Override
	protected void copyComponentsFrom(ComponentsAccess from) {
		this.copyComponentFrom(from, ModDataComponentTypes.MAHIMAHI_FISH_VARIANT);
		super.copyComponentsFrom(from);
	}

	@Override
	protected <T> boolean setApplicableComponent(ComponentType<T> type, T value) {
		if (type == ModDataComponentTypes.MAHIMAHI_FISH_VARIANT) {
			this.setVariant(castComponentValue(ModDataComponentTypes.MAHIMAHI_FISH_VARIANT, value));
			return true;
		} else {
			return super.setApplicableComponent(type, value);
		}
	}
    
}
