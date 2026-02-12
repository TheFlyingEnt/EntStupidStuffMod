package net.ent.entstupidstuff.client.entity.mob;

import org.jetbrains.annotations.Nullable;

import com.mojang.serialization.Codec;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Vindicator;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

public class MountaineerVindicatorEntity extends Vindicator {

    private static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(MountaineerVindicatorEntity.class, EntityDataSerializers.INT);

    private Variant variant;

    public MountaineerVindicatorEntity(EntityType<? extends Vindicator> entityType, Level level) {
        super(entityType, level);
    }

    // # Variant code:
    public enum Variant {
        DIAMOND(0, "diamond"),
        GOLD(1, "gold"),
        DEFAULT(2, "default");

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

        public static Variant getRandom(RandomSource random) {
			//return VALUES[random.nextInt(VALUES.length)];
			RandomSource varientR = RandomSource.create();
    
            int variantRandom = varientR.nextInt(5);

			if (variantRandom <= 3) {
            	return Variant.DEFAULT;
        	} else if (variantRandom <= 5) {
            	return Variant.GOLD;
        	} else {
            	return Variant.DIAMOND;
            }

		}
  
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(VARIANT, 0);
    }

  
    @Override
        public SpawnGroupData finalizeSpawn(ServerLevelAccessor world, DifficultyInstance difficulty, EntitySpawnReason spawnReason, @Nullable SpawnGroupData entityData) {
        Variant randomVariant = Variant.getRandom(this.getRandom());
        this.setVariant(randomVariant);
        return super.finalizeSpawn(world, difficulty, spawnReason, entityData);
    }

    @Override
    public void addAdditionalSaveData(ValueOutput view) {
        super.addAdditionalSaveData(view);
        view.putInt("Variant", this.getVariant().getId());
    }

    @Override
    protected void readAdditionalSaveData(ValueInput view) {
        super.readAdditionalSaveData(view);
        this.setVariant((MountaineerVindicatorEntity.Variant)view.read("Variant", MountaineerVindicatorEntity.Variant.INDEX_CODEC).orElse(MountaineerVindicatorEntity.Variant.GOLD));
    }

    public void setVariant(MountaineerVindicatorEntity.Variant variant) {
        this.variant = variant; // Ensure the field is updated
        this.entityData.set(VARIANT, variant.getId());
    }

    public Variant getVariant() {
        return Variant.byId(this.entityData.get(VARIANT)); // Ensure it retrieves from dataTracker
    }

    // # Adding Armor Stats & Equipment:
    @Override
    protected void populateDefaultEquipmentSlots(RandomSource random, DifficultyInstance localDifficulty) {
        super.populateDefaultEquipmentSlots(random, localDifficulty);
        this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.IRON_AXE));
        applyArmorStats();
    }

    private void applyArmorStats() {
        if (this.variant == Variant.DIAMOND) {
            this.getAttribute(Attributes.ARMOR).setBaseValue(20.0); // Diamond armor value
            this.getAttribute(Attributes.ARMOR_TOUGHNESS).setBaseValue(2.0);
             this.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(5.0);
        } else if (this.variant == Variant.GOLD) {
            this.getAttribute(Attributes.ARMOR).setBaseValue(11.0); // Gold armor value
            this.getAttribute(Attributes.ARMOR_TOUGHNESS).setBaseValue(0.0);
             this.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(3.0);
        }
    }



}
    

