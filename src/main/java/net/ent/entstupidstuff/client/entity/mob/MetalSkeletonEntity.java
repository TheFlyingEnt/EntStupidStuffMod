package net.ent.entstupidstuff.client.entity.mob;

import org.jetbrains.annotations.Nullable;

import com.mojang.serialization.Codec;

import net.ent.entstupidstuff.client.entity.generic.GenericSkeletonCrossbow;
import net.ent.entstupidstuff.sound.SoundFactory;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.projectile.AbstractThrownPotion;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

public class MetalSkeletonEntity extends GenericSkeletonCrossbow{

	@SuppressWarnings("unused")
	private /*final*/ MetalSkeletonVariant variant;

	private static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(MetalSkeletonEntity.class, EntityDataSerializers.INT);


	public enum MetalSkeletonVariant {
		DEFAULT(0, "default"),
		BLUE(1, "blue"),
		RED(2, "red");
	
		private static final MetalSkeletonVariant[] VALUES = values();
		private final int id;
		private final String name;

		public static final Codec<MetalSkeletonVariant> INDEX_CODEC = Codec.INT.xmap(
            MetalSkeletonVariant::byId,
            MetalSkeletonVariant::getId
        );
	
		MetalSkeletonVariant(int id, String name) {
			this.id = id;
			this.name = name;
		}
	
		public int getId() {
			return id;
		}
	
		public String getName() {
			return name;
		}
	
		public static MetalSkeletonVariant byId(int id) {
			return VALUES[Math.max(0, Math.min(id, VALUES.length - 1))];
		}

		public static MetalSkeletonVariant getRandom(RandomSource random) {
			//return VALUES[random.nextInt(VALUES.length)];
			RandomSource varientR = RandomSource.create();
        	float varientRC = varientR.nextInt(3) + 1;

			if (varientRC == 1) {
            	return MetalSkeletonVariant.BLUE;
        	} else if (varientRC == 2) {
            	return MetalSkeletonVariant.RED;
        	} else {
            	return MetalSkeletonVariant.DEFAULT;
        	}

			

		}
	}

	/*public MetalSkeletonVariant getVariant() {
        return variant;
    }*/

	@Override
	protected void defineSynchedData(SynchedEntityData.Builder builder) {
		super.defineSynchedData(builder);
        //this.dataTracker.startTracking(VARIANT, MetalSkeletonVariant.DEFAULT.getId());
		builder.define(VARIANT, 0);
    }

	@Override
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor world, DifficultyInstance difficulty, EntitySpawnReason spawnReason, @Nullable SpawnGroupData entityData) {

		MetalSkeletonVariant randomVariant = MetalSkeletonVariant.getRandom(this.getRandom());
        this.setVariant(randomVariant);

        return super.finalizeSpawn(world, difficulty, spawnReason, entityData);
	}

	/*public void setVariant(MetalSkeletonEntity.MetalSkeletonVariant variant) {
		this.dataTracker.set(VARIANT, variant.getId());
	}*/

    public MetalSkeletonEntity(EntityType<? extends GenericSkeletonCrossbow> entityType, Level world) {
        super(entityType, world);

		/*Random varientR = Random.create();
        float varientRC = varientR.nextInt(3) + 1;

		if (varientRC == 1) {
            variant = MetalSkeletonVariant.BLUE;
        } else if (varientRC == 2) {
            variant = MetalSkeletonVariant.RED;
        } else {
            variant = MetalSkeletonVariant.DEFAULT;
        }*/

		/*if (!world.isClient()) {
			this.variant = MetalSkeletonVariant.byId(Random.create().nextInt(3));
		}*/

		//System.out.println("MetalSkeleton Spawned: " + variant.name);

    }

    @Override
	protected SoundEvent getAmbientSound() {
		return SoundFactory.ENTITY_METAL_SKELETON_AMBIENT;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundFactory.ENTITY_METAL_SKELETON_HURT;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundFactory.ENTITY_METAL_SKELETON_DEATH;
	}

	@Override
    public boolean hurtServer(ServerLevel world, DamageSource source, float amount) {

		// Check if the damage source is a potion
    	if (source.getDirectEntity() instanceof AbstractThrownPotion potionEntity) {
        	// Check if the potion is Healing or Harming
        	if (potionEntity == Potions.HEALING) {
            	// Healing potion causes damage to the skeleton
            	return super.hurtServer(world, source, amount * 1.5f); // Takes more damage from healing potions (increase if needed)
        	} else if (potionEntity == Potions.HARMING) {
            	// Harming potion heals the skeleton
            	this.heal(amount * 1.5f); // Heals with harming potions (increase healing if needed)
            	return true; // No damage is dealt, just healing
        	}
    	}


        if (source.is(DamageTypeTags.IS_EXPLOSION) || source.is(DamageTypeTags.IS_PROJECTILE)) {
            // Weakness to explosions and fireworks (taking full or increased damage)
            return super.hurtServer(world, source, amount * 1.5f);
        } else if (source.is(DamageTypeTags.BYPASSES_ARMOR)) {
            // Keep normal damage for armor-piercing attacks
            return super.hurtServer(world, source, amount);
        } else {
            // High resistance to melee (reducing melee damage)
            return super.hurtServer(world, source, amount * 0.5f);
        }
    }

	@Override
	protected void populateDefaultEquipmentSlots(RandomSource random, DifficultyInstance localDifficulty) {

		RandomSource varientR = RandomSource.create();
        float varientRC = varientR.nextInt(3) + 1;

		if (varientRC == 1) {
            this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.CROSSBOW));
        } else if (varientRC == 2) {
            this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.IRON_SWORD));
        } else {
            this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.BOW));
        }

		
	}

	/*public void setVariant(MetalSkeletonVariant variant) {
        this.dataTracker.set(VARIANT, variant.getId());
    }*/

	@Override
    public void addAdditionalSaveData(ValueOutput view) {
        super.addAdditionalSaveData(view);
        view.putInt("Variant", this.getVariant().getId());
    }

    @Override
    protected void readAdditionalSaveData(ValueInput view) {
        super.readAdditionalSaveData(view);
		this.setVariant((MetalSkeletonEntity.MetalSkeletonVariant)view.read("Variant", MetalSkeletonEntity.MetalSkeletonVariant.INDEX_CODEC).orElse(MetalSkeletonEntity.MetalSkeletonVariant.DEFAULT));
    }

	public void setVariant(MetalSkeletonEntity.MetalSkeletonVariant variant) {
		this.variant = variant; // Ensure the field is updated
		this.entityData.set(VARIANT, variant.getId());
	}

	public MetalSkeletonVariant getVariant() {
		return MetalSkeletonVariant.byId(this.entityData.get(VARIANT)); // Ensure it retrieves from dataTracker
	}

	

	/*
	 * Melee Attacks: Takes only 50% of the original damage.
	 * Explosions & Fireworks: Takes 50% more damage.
	 * Armor-Piercing Attacks: Takes normal damage.
	 */
}
