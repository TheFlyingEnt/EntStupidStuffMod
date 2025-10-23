package net.ent.entstupidstuff.entity.mob;

import org.jetbrains.annotations.Nullable;

import com.mojang.serialization.Codec;

import net.ent.entstupidstuff.entity.generic.GenericSkeletonCrossbow;
import net.ent.entstupidstuff.sound.SoundFactory;
import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.projectile.thrown.PotionEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.Potions;
import net.minecraft.registry.tag.DamageTypeTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;

public class MetalSkeletonEntity extends GenericSkeletonCrossbow{

	@SuppressWarnings("unused")
	private /*final*/ MetalSkeletonVariant variant;

	private static final TrackedData<Integer> VARIANT = DataTracker.registerData(MetalSkeletonEntity.class, TrackedDataHandlerRegistry.INTEGER);


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

		public static MetalSkeletonVariant getRandom(Random random) {
			//return VALUES[random.nextInt(VALUES.length)];
			Random varientR = Random.create();
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
	protected void initDataTracker(DataTracker.Builder builder) {
		super.initDataTracker(builder);
        //this.dataTracker.startTracking(VARIANT, MetalSkeletonVariant.DEFAULT.getId());
		builder.add(VARIANT, 0);
    }

	@Override
	public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData) {

		MetalSkeletonVariant randomVariant = MetalSkeletonVariant.getRandom(this.getRandom());
        this.setVariant(randomVariant);

        return super.initialize(world, difficulty, spawnReason, entityData);
	}

	/*public void setVariant(MetalSkeletonEntity.MetalSkeletonVariant variant) {
		this.dataTracker.set(VARIANT, variant.getId());
	}*/

    public MetalSkeletonEntity(EntityType<? extends GenericSkeletonCrossbow> entityType, World world) {
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
    public boolean damage(ServerWorld world, DamageSource source, float amount) {

		// Check if the damage source is a potion
    	if (source.getSource() instanceof PotionEntity potionEntity) {
        	// Check if the potion is Healing or Harming
        	if (potionEntity == Potions.HEALING) {
            	// Healing potion causes damage to the skeleton
            	return super.damage(world, source, amount * 1.5f); // Takes more damage from healing potions (increase if needed)
        	} else if (potionEntity == Potions.HARMING) {
            	// Harming potion heals the skeleton
            	this.heal(amount * 1.5f); // Heals with harming potions (increase healing if needed)
            	return true; // No damage is dealt, just healing
        	}
    	}


        if (source.isIn(DamageTypeTags.IS_EXPLOSION) || source.isIn(DamageTypeTags.IS_PROJECTILE)) {
            // Weakness to explosions and fireworks (taking full or increased damage)
            return super.damage(world, source, amount * 1.5f);
        } else if (source.isIn(DamageTypeTags.BYPASSES_ARMOR)) {
            // Keep normal damage for armor-piercing attacks
            return super.damage(world, source, amount);
        } else {
            // High resistance to melee (reducing melee damage)
            return super.damage(world, source, amount * 0.5f);
        }
    }

	@Override
	protected void initEquipment(Random random, LocalDifficulty localDifficulty) {

		Random varientR = Random.create();
        float varientRC = varientR.nextInt(3) + 1;

		if (varientRC == 1) {
            this.equipStack(EquipmentSlot.MAINHAND, new ItemStack(Items.CROSSBOW));
        } else if (varientRC == 2) {
            this.equipStack(EquipmentSlot.MAINHAND, new ItemStack(Items.IRON_SWORD));
        } else {
            this.equipStack(EquipmentSlot.MAINHAND, new ItemStack(Items.BOW));
        }

		
	}

	/*public void setVariant(MetalSkeletonVariant variant) {
        this.dataTracker.set(VARIANT, variant.getId());
    }*/

	@Override
    public void writeCustomData(WriteView view) {
        super.writeCustomData(view);
        view.putInt("Variant", this.getVariant().getId());
    }

    @Override
    protected void readCustomData(ReadView view) {
        super.readCustomData(view);
		this.setVariant((MetalSkeletonEntity.MetalSkeletonVariant)view.read("Variant", MetalSkeletonEntity.MetalSkeletonVariant.INDEX_CODEC).orElse(MetalSkeletonEntity.MetalSkeletonVariant.DEFAULT));
    }

	public void setVariant(MetalSkeletonEntity.MetalSkeletonVariant variant) {
		this.variant = variant; // Ensure the field is updated
		this.dataTracker.set(VARIANT, variant.getId());
	}

	public MetalSkeletonVariant getVariant() {
		return MetalSkeletonVariant.byId(this.dataTracker.get(VARIANT)); // Ensure it retrieves from dataTracker
	}

	

	/*
	 * Melee Attacks: Takes only 50% of the original damage.
	 * Explosions & Fireworks: Takes 50% more damage.
	 * Armor-Piercing Attacks: Takes normal damage.
	 */
}
