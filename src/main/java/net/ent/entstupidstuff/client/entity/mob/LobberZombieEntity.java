package net.ent.entstupidstuff.client.entity.mob;

import net.ent.entstupidstuff.item.ItemFactory;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LevelEvent;

public class LobberZombieEntity extends Zombie {

    public LobberZombieEntity(EntityType<? extends /*ZombieEntity*/ LobberZombieEntity> entityType, Level world) {
        super(entityType, world);
    }

    @Override
    public boolean doHurtTarget(ServerLevel world, Entity target) {
        boolean successful = super.doHurtTarget(world, target);
        if (successful && target instanceof Player) {
            target.push(-Mth.sin(this.getYRot() * 0.017453292F) * 0.5F, 0.1D, Mth.cos(this.getYRot() * 0.017453292F) * 0.5F);

            //Concept - Nausa
            float f = this.level().getCurrentDifficultyAt(this.blockPosition()).getEffectiveDifficulty();
            ((LivingEntity)target).addEffect(new MobEffectInstance(MobEffects.NAUSEA, 140 * (int)f), this);
        }
        return successful;
    }

    public static AttributeSupplier.Builder createLobberZombieAttributes() {
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

	

    

    /* Drown Code */
    @Override
    protected void doUnderWaterConversion() {
        this.convertToZombieType(EntityType.DROWNED);
        if (!this.isSilent()) {
            this.level().levelEvent(null, LevelEvent.SOUND_ZOMBIE_TO_DROWNED, this.blockPosition(), 0);
        }
    }

    private int inWaterTime;

    @Override
    public void tick() {
        super.tick();
        if (this.isUnderWater() && !this.isDeadOrDying()) {
            inWaterTime++;
            if (inWaterTime >= 300) { // The time it takes for the zombie to convert to drowned (300 ticks = 15 seconds)
                this.doUnderWaterConversion();
            }
        } else {
            inWaterTime = 0;
        }
    }

    @Override
	protected void populateDefaultEquipmentSlots(RandomSource random, DifficultyInstance localDifficulty) {
		super.populateDefaultEquipmentSlots(random, localDifficulty);
		if (random.nextFloat() < (this.level().getDifficulty() == Difficulty.HARD ? 0.05F : 0.01F)) {
			int i = random.nextInt(3);
			if (i == 0) {
				this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(ItemFactory.callItem("iron_hammer")));
			} else {
				this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.IRON_SWORD));
			}
		}
	}

}
