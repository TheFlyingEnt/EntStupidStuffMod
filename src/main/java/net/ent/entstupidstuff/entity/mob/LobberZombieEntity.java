package net.ent.entstupidstuff.entity.mob;

import net.ent.entstupidstuff.item.ItemFactory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.Difficulty;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.World;
import net.minecraft.world.WorldEvents;

public class LobberZombieEntity extends ZombieEntity {

    public LobberZombieEntity(EntityType<? extends /*ZombieEntity*/ LobberZombieEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public boolean tryAttack(Entity target) {
        boolean successful = super.tryAttack(target);
        if (successful && target instanceof PlayerEntity) {
            target.addVelocity(-MathHelper.sin(this.getYaw() * 0.017453292F) * 0.5F, 0.1D, MathHelper.cos(this.getYaw() * 0.017453292F) * 0.5F);

            //Concept - Nausa
            float f = this.getWorld().getLocalDifficulty(this.getBlockPos()).getLocalDifficulty();
            ((LivingEntity)target).addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 140 * (int)f), this);
        }
        return successful;
    }

    public static DefaultAttributeContainer.Builder createLobberZombieAttributes() {
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

    

    /* Drown Code */
    @Override
    protected void convertInWater() {
        this.convertTo(EntityType.DROWNED);
        if (!this.isSilent()) {
            this.getWorld().syncWorldEvent(null, WorldEvents.ZOMBIE_CONVERTS_TO_DROWNED, this.getBlockPos(), 0);
        }
    }

    private int inWaterTime;

    @Override
    public void tick() {
        super.tick();
        if (this.isSubmergedInWater() && !this.isDead()) {
            inWaterTime++;
            if (inWaterTime >= 300) { // The time it takes for the zombie to convert to drowned (300 ticks = 15 seconds)
                this.convertInWater();
            }
        } else {
            inWaterTime = 0;
        }
    }

    @Override
	protected void initEquipment(Random random, LocalDifficulty localDifficulty) {
		super.initEquipment(random, localDifficulty);
		if (random.nextFloat() < (this.getWorld().getDifficulty() == Difficulty.HARD ? 0.05F : 0.01F)) {
			int i = random.nextInt(3);
			if (i == 0) {
				this.equipStack(EquipmentSlot.MAINHAND, new ItemStack(ItemFactory.callItem("iron_hammer")));
			} else {
				this.equipStack(EquipmentSlot.MAINHAND, new ItemStack(Items.IRON_SWORD));
			}
		}
	}

}
