package net.ent.entstupidstuff.client.entity.mob;

import net.ent.entstupidstuff.item.ItemFactory;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;

public class ScorchedZombieEntity extends Zombie{

    public ScorchedZombieEntity(EntityType<? extends /*ZombieEntity*/ ScorchedZombieEntity> entityType, Level world) {
        super(entityType, world);
    }

    @Override
    public boolean doHurtTarget(ServerLevel world, Entity target) {
        boolean successful = super.doHurtTarget(world, target);
        if (successful) {
            // Check if the target is an instance of LivingEntity to ensure it can be set on fire
            if (target instanceof LivingEntity) {
                // Set the target on fire for a certain duration (e.g., 5 seconds)
                target.igniteForSeconds(5);
            }
        }
        return successful;
    }

    public static AttributeSupplier.Builder createScorchedZombieAttributes() {
        return Zombie.createAttributes()
        .add(Attributes.FOLLOW_RANGE, 35.0D)
        .add(Attributes.MOVEMENT_SPEED, 0.23D)
        .add(Attributes.ATTACK_DAMAGE, 3.0D)
        .add(Attributes.ARMOR, 2.0D)
        .add(Attributes.SPAWN_REINFORCEMENTS_CHANCE);
    }

    @Override
    protected void randomizeReinforcementsChance() {
        super.randomizeReinforcementsChance();
        this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(20.0D);
        this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.23D);
        this.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(3.0D);
        this.getAttribute(Attributes.ARMOR).setBaseValue(2.0D);
    }

    /* Sounds */
    @Override
	protected SoundEvent getAmbientSound() {
		return SoundEvents.HUSK_AMBIENT;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundEvents.HUSK_HURT;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundEvents.HUSK_DEATH;
	}

	@Override
	protected SoundEvent getStepSound() {
		return SoundEvents.HUSK_STEP;
	}

	@Override
	protected boolean convertsInWater() {
		return true;
	}

	

    /* Drown Code - Replaced with Water Damage + Sun Res */

    @Override
    public void tick() {
        super.tick();
        if (this.isWet()) {
            //this.damage(this.getDamageSources().drown(), 1.0F); // Adjust damage amount as needed // TODO: 1.21.10 Fix this
        }
        // Prevent sunlight damage
        this.clearFire();
    }


    public boolean isWet() {
        return this.isInWaterOrRain();
    }

    @Override
    protected boolean isSunSensitive() {
		return true;
	}

    @Override
	public boolean isSensitiveToWater() {
		return true;
	}

    /* Armor and Tools */

    @Override
	protected void populateDefaultEquipmentSlots(RandomSource random, DifficultyInstance localDifficulty) {
		super.populateDefaultEquipmentSlots(random, localDifficulty);
		if (random.nextFloat() < (this.level().getDifficulty() == Difficulty.HARD ? 0.05F : 0.01F)) {
			int i = random.nextInt(3);;
			if (i == 0) {
				this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(ItemFactory.callItem("iron_dagger")));
			} else {
				this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.IRON_PICKAXE));
			}
		}
	}

    //Spawning

    public static boolean canSpawnIn(EntityType<? extends Monster> type, ServerLevelAccessor world, EntitySpawnReason spawnReason, BlockPos pos, RandomSource random) {

		return world.getDifficulty() != Difficulty.PEACEFUL
			&& (EntitySpawnReason.ignoresLightRequirements(spawnReason) || isDarkEnoughToSpawn(world, pos, random))
			&& checkMobSpawnRules(type, world, spawnReason, pos, random) && pos.getY() < 0 && pos.getY() >= world.getMinY();
	}


    
    

}
