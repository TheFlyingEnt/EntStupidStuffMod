package net.ent.entstupidstuff.entity.mob;

import net.ent.entstupidstuff.item.ItemFactory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.Difficulty;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;

public class ScorchedZombieEntity extends ZombieEntity{

    public ScorchedZombieEntity(EntityType<? extends /*ZombieEntity*/ ScorchedZombieEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public boolean tryAttack(Entity target) {
        boolean successful = super.tryAttack(target);
        if (successful) {
            // Check if the target is an instance of LivingEntity to ensure it can be set on fire
            if (target instanceof LivingEntity) {
                // Set the target on fire for a certain duration (e.g., 5 seconds)
                target.setOnFireFor(5);
            }
        }
        return successful;
    }

    public static DefaultAttributeContainer.Builder createScorchedZombieAttributes() {
        return ZombieEntity.createZombieAttributes()
        .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 35.0D)
        .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.23D)
        .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 3.0D)
        .add(EntityAttributes.GENERIC_ARMOR, 2.0D)
        .add(EntityAttributes.ZOMBIE_SPAWN_REINFORCEMENTS);
    }

    @Override
    protected void initAttributes() {
        super.initAttributes();
        this.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH).setBaseValue(20.0D);
        this.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED).setBaseValue(0.23D);
        this.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE).setBaseValue(3.0D);
        this.getAttributeInstance(EntityAttributes.GENERIC_ARMOR).setBaseValue(2.0D);
    }

    /* Sounds */
    @Override
	protected SoundEvent getAmbientSound() {
		return SoundEvents.ENTITY_HUSK_AMBIENT;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundEvents.ENTITY_HUSK_HURT;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundEvents.ENTITY_HUSK_DEATH;
	}

	@Override
	protected SoundEvent getStepSound() {
		return SoundEvents.ENTITY_HUSK_STEP;
	}

	@Override
	protected boolean canConvertInWater() {
		return true;
	}

	@Override
	protected ItemStack getSkull() {
		return ItemStack.EMPTY;
	}

    /* Drown Code - Replaced with Water Damage + Sun Res */

    @Override
    public void tick() {
        super.tick();
        if (this.isWet()) {
            this.damage(this.getDamageSources().drown(), 1.0F); // Adjust damage amount as needed
        }
        // Prevent sunlight damage
        this.extinguish();
    }

    @Override
    public boolean isWet() {
        return this.isTouchingWater();
    }

    @Override
    protected boolean burnsInDaylight() {
		return true;
	}

    @Override
	public boolean hurtByWater() {
		return true;
	}

    /* Armor and Tools */

    @Override
	protected void initEquipment(Random random, LocalDifficulty localDifficulty) {
		super.initEquipment(random, localDifficulty);
		if (random.nextFloat() < (this.getWorld().getDifficulty() == Difficulty.HARD ? 0.05F : 0.01F)) {
			int i = random.nextInt(3);;
			if (i == 0) {
				this.equipStack(EquipmentSlot.MAINHAND, new ItemStack(ItemFactory.callItem("iron_dagger")));
			} else {
				this.equipStack(EquipmentSlot.MAINHAND, new ItemStack(Items.IRON_PICKAXE));
			}
		}
	}

    //Spawning

    public static boolean canSpawnIn(EntityType<? extends HostileEntity> type, ServerWorldAccess world, SpawnReason spawnReason, BlockPos pos, Random random) {

		return world.getDifficulty() != Difficulty.PEACEFUL
			&& (SpawnReason.isTrialSpawner(spawnReason) || isSpawnDark(world, pos, random))
			&& canMobSpawn(type, world, spawnReason, pos, random) && pos.getY() < 0 && pos.getY() >= world.getBottomY();
	}


    
    

}
