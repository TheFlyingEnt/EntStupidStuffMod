package net.ent.entstupidstuff.client.entity.mob.skeleton;

import org.jetbrains.annotations.Nullable;

import com.mojang.serialization.Codec;

import net.ent.entstupidstuff.client.entity.generic.GenericSkeletonCrossbow;
import net.ent.entstupidstuff.sound.SoundFactory;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
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

public class CoralSkeletonEntity extends GenericSkeletonCrossbow{

    private CoralSkeletonVariant variant;
    private static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(CoralSkeletonEntity.class, EntityDataSerializers.INT);

    public CoralSkeletonEntity(EntityType<? extends GenericSkeletonCrossbow> entityType, Level world) {
        super(entityType, world);
    }

    public enum CoralSkeletonVariant {
		BRAIN(0, "brain"),
		FIRE(1, "fire"),
		HORN(2, "horn"),
		TUBE(3, "tube"),
		BUBBLE(4, "bubble"),
		UNUSED(5, "unused");
	
		private static final CoralSkeletonVariant[] VALUES = values();
		private final int id;
		private final String name;

		public static final Codec<CoralSkeletonVariant> INDEX_CODEC = Codec.INT.xmap(
            CoralSkeletonVariant::byId,
            CoralSkeletonVariant::getId
        );
	
		CoralSkeletonVariant(int id, String name) {
			this.id = id;
			this.name = name;
		}
	
		public int getId() {
			return id;
		}
	
		public String getName() {
			return name;
		}
	
		public static CoralSkeletonVariant byId(int id) {
			return VALUES[Math.max(0, Math.min(id, VALUES.length - 1))];
		}

		public static CoralSkeletonVariant getRandom(RandomSource random) {
			RandomSource varientR = RandomSource.create();
        	int variantRandom = varientR.nextInt(5);
			if (variantRandom == 0) {
                return CoralSkeletonVariant.BRAIN;
            } else if (variantRandom == 1) {
                return CoralSkeletonVariant.FIRE;
            } else if (variantRandom == 2) {
                return CoralSkeletonVariant.HORN;
            } else if (variantRandom == 3) {
                return CoralSkeletonVariant.TUBE;
            } else {
                return CoralSkeletonVariant.BUBBLE;
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
		CoralSkeletonVariant randomVariant = CoralSkeletonVariant.getRandom(this.getRandom());
        this.setVariant(randomVariant);
        return super.finalizeSpawn(world, difficulty, spawnReason, entityData);
	}

    @Override //TODO: Change to BubleSkeletnSound
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
    	if (source.getDirectEntity() instanceof AbstractThrownPotion potionEntity) {
        	if (potionEntity == Potions.HEALING) {
            	return super.hurtServer(world, source, amount * 1.5f); // Takes more damage from healing potions (increase if needed)
        	} else if (potionEntity == Potions.HARMING) {
            	this.heal(amount * 1.5f);
            	return true;
        	}
    	}

        return super.hurtServer(world, source, amount);
    }

    @Override
	protected void populateDefaultEquipmentSlots(RandomSource random, DifficultyInstance localDifficulty) {
        this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.CROSSBOW));
    }

    @Override
    public void addAdditionalSaveData(ValueOutput view) {
        super.addAdditionalSaveData(view);
        view.putInt("Variant", this.getVariant().getId());
    }

    @Override
    protected void readAdditionalSaveData(ValueInput view) {
        super.readAdditionalSaveData(view);
		this.setVariant((CoralSkeletonEntity.CoralSkeletonVariant)view.read("Variant", CoralSkeletonEntity.CoralSkeletonVariant.INDEX_CODEC).orElse(CoralSkeletonEntity.CoralSkeletonVariant.BRAIN));
    }

	public void setVariant(CoralSkeletonEntity.CoralSkeletonVariant variant) {
		this.variant = variant;
		this.entityData.set(VARIANT, variant.getId());
	}

	public CoralSkeletonVariant getVariant() {
		return CoralSkeletonVariant.byId(this.entityData.get(VARIANT));
	}

    
}
