package net.ent.entstupidstuff.entity.mob;

import org.jetbrains.annotations.Nullable;

import net.ent.entstupidstuff.item.ItemFactory;
import net.ent.entstupidstuff.sound.SoundFactory;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.NbtComponent;
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
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;

public class ZebraFishEntity extends SchoolingFishEntity {

   private Variant variant;
   private static final TrackedData<Integer> VARIANT = DataTracker.registerData(ZebraFishEntity.class, TrackedDataHandlerRegistry.INTEGER);
   public enum Variant {
        NAVY(0, "navy"),
        BLUE(1, "blue"),
        LEPORD_NAVY(2, "lepord_navy"),
        LEPORD_BLUE(3, "lepord_blue");

        private static final Variant[] VALUES = values();
		private final int id;
		private final String name;

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
        	//float varientRC = varientR.nextInt(3) + 1;
         float varientRC = varientR.nextInt(11) + 1;

			if (varientRC >= 2 && varientRC <= 5) { //Between 1 to 5
            return Variant.NAVY;
        	} else if (varientRC >= 5 && varientRC <= 10) { //Between 5 to 10
            return Variant.BLUE;
        	}
         else { //is 11
            Random varientR2 = Random.create();
            float varientRC2 = varientR.nextInt(4) + 1;

            if (varientRC == 1) {
               return Variant.NAVY;
        	   } else if (varientRC == 2) {
               return Variant.BLUE;
        	   } else if (varientRC == 3) {
               return Variant.LEPORD_NAVY;
        	   } else {
               return Variant.LEPORD_BLUE;
        	   }

         }

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
   public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putInt("Variant", this.getVariant().getId());
    }

   public void copyDataToStack(ItemStack stack) {
      super.copyDataToStack(stack);
      NbtComponent.set(DataComponentTypes.BUCKET_ENTITY_DATA, stack, (nbtCompound) -> {

         if (this.getVariant() == Variant.NAVY) {
            nbtCompound.putInt("BucketVariantTag", 0);
         }
         else if (this.getVariant() == Variant.BLUE) {
            nbtCompound.putInt("BucketVariantTag", 0);
         }
      });
   }

   @Override
   public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        this.setVariant(Variant.byId(nbt.getInt("Variant")));

    }

	public void setVariant(ZebraFishEntity.Variant variant) {
		this.variant = variant; // Ensure the field is updated
		this.dataTracker.set(VARIANT, variant.getId());
	}

	public Variant getVariant() {
		return Variant.byId(this.dataTracker.get(VARIANT)); // Ensure it retrieves from dataTracker
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


}
