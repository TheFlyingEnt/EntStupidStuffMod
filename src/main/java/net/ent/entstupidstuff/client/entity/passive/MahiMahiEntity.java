package net.ent.entstupidstuff.client.entity.passive;

import org.jetbrains.annotations.Nullable;

import com.mojang.serialization.Codec;

import net.ent.entstupidstuff.item.ItemFactory;
import net.ent.entstupidstuff.sound.SoundFactory;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.entity.Bucketable;
import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.passive.SchoolingFishEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.sound.SoundEvent;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;

public class MahiMahiEntity extends SchoolingFishEntity{

    public MahiMahiEntity(EntityType<? extends SchoolingFishEntity> entityType, World world) {
        super(entityType, world);
    }

    private Variant variant;
    private static final TrackedData<Integer> VARIANT = DataTracker.registerData(MahiMahiEntity.class, TrackedDataHandlerRegistry.INTEGER);

    public enum Variant {
        GREEN(0, "Green"),
        BLUE(1, "Blue");

        private static final Variant[] VALUES = values();
		private final int id;
		private final String name;

        public static final Codec<Variant> INDEX_CODEC = Codec.INT.xmap(
            Variant::byId,
            Variant::getId
        );

        Variant(int id, String name) {
			this.id = id;
			this.name = name;
		}

        public int getId() {
			return id;
		}
	
		public String getName() {
			return name;
		}
	
		public static Variant byId(int id) {
			return VALUES[Math.max(0, Math.min(id, VALUES.length - 1))];
		}

        public static Variant getRandom(Random random) {
			//return VALUES[random.nextInt(VALUES.length)];
			Random varientR = Random.create();
        	float varientRC = varientR.nextInt(2) + 1;

			if (varientRC == 1) {
                return Variant.GREEN;
        	} else {
                return Variant.BLUE;
        	}

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

    @Override
    public void writeCustomData(WriteView view) {
        super.writeCustomData(view);
        view.putInt("Variant", this.getVariant().getId());
    }

    public void copyDataToStack(ItemStack stack) {
        super.copyDataToStack(stack);
        NbtComponent.set(DataComponentTypes.BUCKET_ENTITY_DATA, stack, (nbtCompound) -> {

            if (this.getVariant() == Variant.GREEN) {
                nbtCompound.putInt("BucketVariantTag", 0);
            }
            else if (this.getVariant() == Variant.BLUE) {
                nbtCompound.putInt("BucketVariantTag", 1);
            }
        });
   }

    @Override
    protected void readCustomData(ReadView view) {
        super.readCustomData(view);
		this.setVariant((MahiMahiEntity.Variant)view.read("Variant", MahiMahiEntity.Variant.INDEX_CODEC).orElse(MahiMahiEntity.Variant.BLUE));
    }

    public void setVariant(MahiMahiEntity.Variant variant) {
        this.variant = variant;
        this.dataTracker.set(VARIANT, variant.getId());
    }

    public Variant getVariant() {
        return Variant.byId(this.dataTracker.get(VARIANT));
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
        builder.add(VARIANT, 0);
    }

    @Override
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData) {

        Variant randomVariant = Variant.getRandom(this.getRandom());
        this.setVariant(randomVariant);

        return super.initialize(world, difficulty, spawnReason, entityData);
    }

    @Override
    public void copyDataFromNbt(NbtCompound nbt) {
        Bucketable.copyDataFromNbt(this, nbt);
    }
    
}
