package net.ent.entstupidstuff.client.entity.passive;

import java.util.function.IntFunction;

import org.jetbrains.annotations.Nullable;

import com.mojang.serialization.Codec;

import io.netty.buffer.ByteBuf;
import net.ent.entstupidstuff.component.ModDataComponentTypes;
import net.ent.entstupidstuff.item.ItemFactory;
import net.ent.entstupidstuff.sound.SoundFactory;
import net.minecraft.component.ComponentType;
import net.minecraft.component.ComponentsAccess;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.Bucketable;
import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.passive.AxolotlEntity;
import net.minecraft.entity.passive.SchoolingFishEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.sound.SoundEvent;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.function.ValueLists;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;

public class ZebraFishEntity extends SchoolingFishEntity {

   private Variant variant;
   private static final TrackedData<Integer> VARIANT = DataTracker.registerData(ZebraFishEntity.class, TrackedDataHandlerRegistry.INTEGER);

   public enum Variant implements StringIdentifiable{
      STRIPED_NAVY(0, "Striped", "Navy"),
      STRIPED_BLUE(1, "Striped","Blue"),
      LEPORD_NAVY(2, "Lepord", "Navy"),
      LEPORD_BLUE(3, "Lepord", "Blue");
      //LONG_FIN_NAVY(3, "Long Fin", "Nacy");
      //LONG_FIN_BLUE(3, "Long Fin", "Blue");
      //LONG_FIN_GOLDEN(3, "Long Fin", "Golden");

      public static final Variant DEFAULT = STRIPED_BLUE;

      private static final IntFunction<Variant> INDEX_MAPPER = ValueLists.createIndexToValueFunction(
         Variant::getIndex, values(), ValueLists.OutOfBoundsHandling.ZERO
      );

      private final int index;
		private final String pattern;
		private final String color;

      public static final Codec<Variant> CODEC = StringIdentifiable.createCodec(Variant::values);
      public static final Codec<Variant> INDEX_CODEC = Codec.INT.xmap(
         Variant::byId,
         Variant::getIndex
      );

      public static final PacketCodec<ByteBuf, Variant> PACKET_CODEC = PacketCodecs.indexed(INDEX_MAPPER, Variant::getIndex);

      private Variant(int index, String pattern, String color) {
			this.index = index;
			this.pattern = pattern;
         this.color = color;
		}

      public int getIndex() {
			return index;
		}
	
		public String getPattern() {
			return pattern;
		}

      public String getColor() {
			return color;
		}

      private static final Variant[] VALUES = values();
      
	
		public static Variant byId(int id) {
			return VALUES[Math.max(0, Math.min(id, VALUES.length - 1))];
		}

        public static Variant getRandom(Random random) {
			//return VALUES[random.nextInt(VALUES.length)];
			Random varientR = Random.create();
        	//float varientRC = varientR.nextInt(3) + 1;
         float varientRC = varientR.nextInt(11) + 1;

			if (varientRC >= 2 && varientRC <= 5) { //Between 1 to 5
            return Variant.STRIPED_NAVY;
        	} else if (varientRC >= 5 && varientRC <= 10) { //Between 5 to 10
            return Variant.STRIPED_BLUE;
        	}
         else { //is 11
            Random varientR2 = Random.create();
            float varientRC2 = varientR.nextInt(4) + 1;

            if (varientRC == 1) {
               return Variant.STRIPED_NAVY;
        	   } else if (varientRC == 2) {
               return Variant.STRIPED_BLUE;
        	   } else if (varientRC == 3) {
               return Variant.LEPORD_NAVY;
        	   } else {
               return Variant.LEPORD_BLUE;
        	   }

         }

		}

        @Override
      public String asString() {
         return this.color + " " + this.pattern;
      }

    }

   public ZebraFishEntity(EntityType<? extends ZebraFishEntity> entityType, World world) {
      super(entityType, world);
   }

   public ItemStack getBucketItem() {
      return new ItemStack(ItemFactory.callItem("zebra_fish_bucket"));
   }

   protected SoundEvent getAmbientSound() {
      return SoundFactory.ENTITY_ZEBRA_FISH_AMBIENT;
   }

   protected SoundEvent getDeathSound() {
      return SoundFactory.ENTITY_ZEBRA_FISH_DEATH;
   }

   protected SoundEvent getHurtSound(DamageSource source) {
      return SoundFactory.ENTITY_ZEBRA_FISH_HURT;
   }

   protected SoundEvent getFlopSound() {
      return SoundFactory.ENTITY_ZEBRA_FISH_FLOP;
   }

   //Varientation Code:
   @Override
   public void writeCustomData(WriteView view) {
      super.writeCustomData(view);
      view.put("Variant", ZebraFishEntity.Variant.INDEX_CODEC, this.getVariant());
   }

   @Override
   protected void readCustomData(ReadView view) {
      super.readCustomData(view);
      this.setVariant((Variant)view.read("Variant", ZebraFishEntity.Variant.INDEX_CODEC).orElse(ZebraFishEntity.Variant.DEFAULT));
   }


   public void copyDataToStack(ItemStack stack) {
      super.copyDataToStack(stack);
      stack.copy(ModDataComponentTypes.ZEBRA_FISH_VARIANT, this);
   }

	public void setVariant(ZebraFishEntity.Variant variant) {
		this.dataTracker.set(VARIANT, variant.getIndex());
	}

	public ZebraFishEntity.Variant getVariant() {
      return ZebraFishEntity.Variant.byId((Integer)this.dataTracker.get(VARIANT));
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
      }

      if (spawnReason != SpawnReason.BUCKET) {
         Variant randomVariant = Variant.getRandom(this.getRandom());
         this.setVariant(randomVariant);

      }
      
      return super.initialize(world, difficulty, spawnReason, entityData);
	}

   @Nullable
	@Override
	public <T> T get(ComponentType<? extends T> type) {
		return type == ModDataComponentTypes.ZEBRA_FISH_VARIANT ? castComponentValue((ComponentType<T>)type, this.getVariant()) : super.get(type);
	}

	@Override
	protected void copyComponentsFrom(ComponentsAccess from) {
		this.copyComponentFrom(from, ModDataComponentTypes.ZEBRA_FISH_VARIANT);
		super.copyComponentsFrom(from);
	}

	@Override
	protected <T> boolean setApplicableComponent(ComponentType<T> type, T value) {
		if (type == ModDataComponentTypes.ZEBRA_FISH_VARIANT) {
			this.setVariant(castComponentValue(ModDataComponentTypes.ZEBRA_FISH_VARIANT, value));
			return true;
		} else {
			return super.setApplicableComponent(type, value);
		}
	}

   


}
