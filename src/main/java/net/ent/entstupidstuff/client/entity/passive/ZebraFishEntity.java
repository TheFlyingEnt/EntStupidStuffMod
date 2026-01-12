package net.ent.entstupidstuff.client.entity.passive;

import java.util.function.IntFunction;

import org.jetbrains.annotations.Nullable;

import com.mojang.serialization.Codec;

import io.netty.buffer.ByteBuf;
import net.ent.entstupidstuff.component.ModDataComponentTypes;
import net.ent.entstupidstuff.item.ItemFactory;
import net.ent.entstupidstuff.sound.SoundFactory;
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

public class ZebraFishEntity extends AbstractSchoolingFish {

   private Variant variant;
   private static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(ZebraFishEntity.class, EntityDataSerializers.INT);

   public enum Variant implements StringRepresentable{
      STRIPED_NAVY(0, "Striped", "Navy"),
      STRIPED_BLUE(1, "Striped","Blue"),
      LEPORD_NAVY(2, "Lepord", "Navy"),
      LEPORD_BLUE(3, "Lepord", "Blue");
      //LONG_FIN_NAVY(3, "Long Fin", "Nacy");
      //LONG_FIN_BLUE(3, "Long Fin", "Blue");
      //LONG_FIN_GOLDEN(3, "Long Fin", "Golden");

      public static final Variant DEFAULT = STRIPED_BLUE;

      private static final IntFunction<Variant> INDEX_MAPPER = ByIdMap.continuous(
         Variant::getIndex, values(), ByIdMap.OutOfBoundsStrategy.ZERO
      );

      private final int index;
		private final String pattern;
		private final String color;

      public static final Codec<Variant> CODEC = StringRepresentable.fromEnum(Variant::values);
      public static final Codec<Variant> INDEX_CODEC = Codec.INT.xmap(
         Variant::byId,
         Variant::getIndex
      );

      public static final StreamCodec<ByteBuf, Variant> PACKET_CODEC = ByteBufCodecs.idMapper(INDEX_MAPPER, Variant::getIndex);

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

        public static Variant getRandom(RandomSource random) {
			//return VALUES[random.nextInt(VALUES.length)];
			RandomSource varientR = RandomSource.create();
        	//float varientRC = varientR.nextInt(3) + 1;
         float varientRC = varientR.nextInt(11) + 1;

			if (varientRC >= 2 && varientRC <= 5) { //Between 1 to 5
            return Variant.STRIPED_NAVY;
        	} else if (varientRC >= 5 && varientRC <= 10) { //Between 5 to 10
            return Variant.STRIPED_BLUE;
        	}
         else { //is 11
            RandomSource varientR2 = RandomSource.create();
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
      public String getSerializedName() {
         return this.color + " " + this.pattern;
      }

    }

   public ZebraFishEntity(EntityType<? extends ZebraFishEntity> entityType, Level world) {
      super(entityType, world);
   }

   public ItemStack getBucketItemStack() {
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
   public void addAdditionalSaveData(ValueOutput view) {
      super.addAdditionalSaveData(view);
      view.store("Variant", ZebraFishEntity.Variant.INDEX_CODEC, this.getVariant());
   }

   @Override
   protected void readAdditionalSaveData(ValueInput view) {
      super.readAdditionalSaveData(view);
      this.setVariant((Variant)view.read("Variant", ZebraFishEntity.Variant.INDEX_CODEC).orElse(ZebraFishEntity.Variant.DEFAULT));
   }


   public void saveToBucketTag(ItemStack stack) {
      super.saveToBucketTag(stack);
      stack.copyFrom(ModDataComponentTypes.ZEBRA_FISH_VARIANT, this);
   }

	public void setVariant(ZebraFishEntity.Variant variant) {
		this.entityData.set(VARIANT, variant.getIndex());
	}

	public ZebraFishEntity.Variant getVariant() {
      return ZebraFishEntity.Variant.byId((Integer)this.entityData.get(VARIANT));
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
      }

      if (spawnReason != EntitySpawnReason.BUCKET) {
         Variant randomVariant = Variant.getRandom(this.getRandom());
         this.setVariant(randomVariant);

      }
      
      return super.finalizeSpawn(world, difficulty, spawnReason, entityData);
	}

   @Nullable
	@Override
	public <T> T get(DataComponentType<? extends T> type) {
		return type == ModDataComponentTypes.ZEBRA_FISH_VARIANT ? castComponentValue((DataComponentType<T>)type, this.getVariant()) : super.get(type);
	}

	@Override
	protected void applyImplicitComponents(DataComponentGetter from) {
		this.applyImplicitComponentIfPresent(from, ModDataComponentTypes.ZEBRA_FISH_VARIANT);
		super.applyImplicitComponents(from);
	}

	@Override
	protected <T> boolean applyImplicitComponent(DataComponentType<T> type, T value) {
		if (type == ModDataComponentTypes.ZEBRA_FISH_VARIANT) {
			this.setVariant(castComponentValue(ModDataComponentTypes.ZEBRA_FISH_VARIANT, value));
			return true;
		} else {
			return super.applyImplicitComponent(type, value);
		}
	}

   


}
