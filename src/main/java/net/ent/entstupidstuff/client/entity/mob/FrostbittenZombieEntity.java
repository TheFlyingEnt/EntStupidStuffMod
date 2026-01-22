package net.ent.entstupidstuff.client.entity.mob;

import org.jetbrains.annotations.Nullable;

import com.mojang.serialization.Codec;

import net.ent.entstupidstuff.sound.SoundFactory;
import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

public class FrostbittenZombieEntity extends Zombie{

    private static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(FrostbittenZombieEntity.class, EntityDataSerializers.INT);

    @SuppressWarnings("unused")
    private Variant variant;

    public enum Variant {
        FROSTBITTEN(0, "frostbitten"),
        NORMAL(1, "normal");

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
        	float varientRC = varientR.nextInt(3) + 1;

			if (varientRC == 1) {
            	return Variant.FROSTBITTEN;
        	} else {
            	return Variant.NORMAL;
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

    public FrostbittenZombieEntity(EntityType<? extends FrostbittenZombieEntity> entityType, Level world) {
        super(entityType, world);
    }

    @Override
    public boolean doHurtTarget(ServerLevel world, Entity target) {
        boolean successful = super.doHurtTarget(world, target);
        if (successful) {
            if (target instanceof LivingEntity) {
                ((LivingEntity) target).addEffect(new MobEffectInstance(MobEffects.SLOWNESS, 20 * 1, 1));
                ((LivingEntity) target).addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 20 * 1, 1));
                target.setTicksFrozen(100 * 1);
            }
        }
        return successful;
    }

    public static boolean canSpawnIn(EntityType<? extends Monster> type, ServerLevelAccessor world, EntitySpawnReason spawnReason, BlockPos pos, RandomSource random) {
		return world.getDifficulty() != Difficulty.PEACEFUL
			&& (EntitySpawnReason.ignoresLightRequirements(spawnReason) || isDarkEnoughToSpawn(world, pos, random))
			&& checkMobSpawnRules(type, world, spawnReason, pos, random) && pos.getY() < 0 && pos.getY() >= world.getMinY();
	}

	//Varientation Code:
    @Override
    public void addAdditionalSaveData(ValueOutput view) {
        super.addAdditionalSaveData(view);
        view.putInt("Variant", this.getVariant().getId());
    }

    @Override
    protected void readAdditionalSaveData(ValueInput view) {
        super.readAdditionalSaveData(view);
        this.setVariant((FrostbittenZombieEntity.Variant)view.read("Variant", FrostbittenZombieEntity.Variant.INDEX_CODEC).orElse(FrostbittenZombieEntity.Variant.NORMAL));
    }

	public void setVariant(FrostbittenZombieEntity.Variant variant) {
		this.variant = variant; // Ensure the field is updated
		this.entityData.set(VARIANT, variant.getId());
	}

	public Variant getVariant() {
		return Variant.byId(this.entityData.get(VARIANT)); // Ensure it retrieves from dataTracker
	}

    //Sound Code:
    protected SoundEvent getAmbientSound() {
        return SoundFactory.ENTITY_FROSTBITTEN_AMBIENT;
    }

    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return SoundFactory.ENTITY_FROSTBITTEN_HURT;
    }

    protected SoundEvent getDeathSound() {
        return SoundFactory.ENTITY_FROSTBITTEN_DEATH;
    }

    protected SoundEvent getStepSound() {
        return SoundFactory.ENTITY_FROSTBITTEN_STEP;
    }
    
}
