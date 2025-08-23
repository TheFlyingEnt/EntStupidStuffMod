package net.ent.entstupidstuff.entity.mob;

import org.jetbrains.annotations.Nullable;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.Difficulty;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;

public class FrostbittenZombieEntity extends ZombieEntity{

    private static final TrackedData<Integer> VARIANT = DataTracker.registerData(FrostbittenZombieEntity.class, TrackedDataHandlerRegistry.INTEGER);

    @SuppressWarnings("unused")
    private Variant variant;

    public enum Variant {
        FROSTBITTEN(0, "frostbitten"),
        NORMAL(1, "normal");

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
        	float varientRC = varientR.nextInt(3) + 1;

			if (varientRC == 1) {
            	return Variant.FROSTBITTEN;
        	} else {
            	return Variant.NORMAL;
            }

		}

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

    public FrostbittenZombieEntity(EntityType<? extends FrostbittenZombieEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public boolean tryAttack(Entity target) {
        boolean successful = super.tryAttack(target);
        if (successful) {
            // Check if the target is an instance of LivingEntity to ensure it can be set on fire
            if (target instanceof LivingEntity) {
                ((LivingEntity) target).addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 20 * 1, 1));
                ((LivingEntity) target).addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 20 * 1, 1));
                target.setFrozenTicks(100 * 1);
            }
        }
        return successful;
    }

    @Override
	protected ItemStack getSkull() {
		return ItemStack.EMPTY;
	}

    public static boolean canSpawnIn(EntityType<? extends HostileEntity> type, ServerWorldAccess world, SpawnReason spawnReason, BlockPos pos, Random random) {
		return world.getDifficulty() != Difficulty.PEACEFUL
			&& (SpawnReason.isTrialSpawner(spawnReason) || isSpawnDark(world, pos, random))
			&& canMobSpawn(type, world, spawnReason, pos, random) && pos.getY() < 0 && pos.getY() >= world.getBottomY();
	}

	//Varientation Code:
    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putInt("Variant", this.getVariant().getId());
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        this.setVariant(Variant.byId(nbt.getInt("Variant")));

    }

	public void setVariant(FrostbittenZombieEntity.Variant variant) {
		this.variant = variant; // Ensure the field is updated
		this.dataTracker.set(VARIANT, variant.getId());
	}

	public Variant getVariant() {
		return Variant.byId(this.dataTracker.get(VARIANT)); // Ensure it retrieves from dataTracker
	}
    
}
