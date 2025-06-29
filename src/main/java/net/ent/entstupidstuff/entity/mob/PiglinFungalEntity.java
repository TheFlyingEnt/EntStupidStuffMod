package net.ent.entstupidstuff.entity.mob;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.entity.EntityStatuses;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.AbstractPiglinEntity;
import net.minecraft.entity.mob.PiglinEntity;
import net.minecraft.entity.mob.WitchEntity;
import net.minecraft.entity.projectile.thrown.PotionEntity;
import net.minecraft.entity.raid.RaiderEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.DamageTypeTags;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

public class PiglinFungalEntity extends PiglinEntity {

    private static final TrackedData<Boolean> DRINKING = DataTracker.registerData(WitchEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    private static final Identifier DRINKING_SPEED_PENALTY_MODIFIER_ID = Identifier.ofVanilla("drinking");
	private static final EntityAttributeModifier DRINKING_SPEED_PENALTY_MODIFIER = new EntityAttributeModifier(
		DRINKING_SPEED_PENALTY_MODIFIER_ID, -0.25, EntityAttributeModifier.Operation.ADD_VALUE
	);
    private int drinkTimeLeft;

    public PiglinFungalEntity(EntityType<? extends AbstractPiglinEntity> entityType, World world) {
        super(entityType, world);
    }

    public void setDrinking(boolean drinking) {
		this.getDataTracker().set(DRINKING, drinking);
	}

	public boolean isDrinking() {
		return this.getDataTracker().get(DRINKING);
	}

    protected void initDataTracker(DataTracker.Builder builder) {
		super.initDataTracker(builder);
		builder.add(DRINKING, false);
	}

    @Override
	public void shootAt(LivingEntity target, float pullProgress) {
		if (!this.isDrinking()) {
			Vec3d vec3d = target.getVelocity();
			double d = target.getX() + vec3d.x - this.getX();
			double e = target.getEyeY() - 1.1F - this.getY();
			double f = target.getZ() + vec3d.z - this.getZ();
			double g = Math.sqrt(d * d + f * f);
			RegistryEntry<Potion> registryEntry = Potions.HARMING;
			if (target instanceof RaiderEntity) {
				if (target.getHealth() <= 4.0F) {
					registryEntry = Potions.HEALING;
				} else {
					registryEntry = Potions.REGENERATION;
				}

				this.setTarget(null);
			} else if (g >= 8.0 && !target.hasStatusEffect(StatusEffects.SLOWNESS)) {
				registryEntry = Potions.SLOWNESS;
			} else if (target.getHealth() >= 8.0F && !target.hasStatusEffect(StatusEffects.POISON)) {
				registryEntry = Potions.POISON;
			} else if (g <= 3.0 && !target.hasStatusEffect(StatusEffects.WEAKNESS) && this.random.nextFloat() < 0.25F) {
				registryEntry = Potions.WEAKNESS;
			}

			PotionEntity potionEntity = new PotionEntity(this.getWorld(), this);
			potionEntity.setItem(PotionContentsComponent.createStack(Items.SPLASH_POTION, registryEntry));
			potionEntity.setPitch(potionEntity.getPitch() - -20.0F);
			potionEntity.setVelocity(d, e + g * 0.2, f, 0.75F, 8.0F);
			if (!this.isSilent()) {
				this.getWorld()
					.playSound(
						null, this.getX(), this.getY(), this.getZ(), SoundEvents.ENTITY_WITCH_THROW, this.getSoundCategory(), 1.0F, 0.8F + this.random.nextFloat() * 0.4F
					);
			}

			this.getWorld().spawnEntity(potionEntity);
		}
    }

    @SuppressWarnings("resource")
	public void tickMovement() {
		if (!this.getWorld().isClient && this.isAlive()) {
			

			if (this.isDrinking()) {
				if (this.drinkTimeLeft-- <= 0) {
					this.setDrinking(false);
					ItemStack itemStack = this.getMainHandStack();
					this.equipStack(EquipmentSlot.MAINHAND, ItemStack.EMPTY);
					PotionContentsComponent potionContentsComponent = itemStack.get(DataComponentTypes.POTION_CONTENTS);
					if (itemStack.isOf(Items.POTION) && potionContentsComponent != null) {
						potionContentsComponent.forEachEffect(this::addStatusEffect);
					}

					this.emitGameEvent(GameEvent.DRINK);
					this.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED).removeModifier(DRINKING_SPEED_PENALTY_MODIFIER.id());
				}
			} else {
				RegistryEntry<Potion> registryEntry = null;
				if (this.random.nextFloat() < 0.15F && this.isSubmergedIn(FluidTags.WATER) && !this.hasStatusEffect(StatusEffects.WATER_BREATHING)) {
					registryEntry = Potions.WATER_BREATHING;
				} else if (this.random.nextFloat() < 0.15F
					&& (this.isOnFire() || this.getRecentDamageSource() != null && this.getRecentDamageSource().isIn(DamageTypeTags.IS_FIRE))
					&& !this.hasStatusEffect(StatusEffects.FIRE_RESISTANCE)) {
					registryEntry = Potions.FIRE_RESISTANCE;
				} else if (this.random.nextFloat() < 0.05F && this.getHealth() < this.getMaxHealth()) {
					registryEntry = Potions.HEALING;
				} else if (this.random.nextFloat() < 0.5F
					&& this.getTarget() != null
					&& !this.hasStatusEffect(StatusEffects.SPEED)
					&& this.getTarget().squaredDistanceTo(this) > 121.0) {
					registryEntry = Potions.SWIFTNESS;
				}

				if (registryEntry != null) {
					this.equipStack(EquipmentSlot.MAINHAND, PotionContentsComponent.createStack(Items.POTION, registryEntry));
					this.drinkTimeLeft = this.getMainHandStack().getMaxUseTime(this);
					this.setDrinking(true);
					if (!this.isSilent()) {
						this.getWorld()
							.playSound(
								null, this.getX(), this.getY(), this.getZ(), SoundEvents.ENTITY_WITCH_DRINK, this.getSoundCategory(), 1.0F, 0.8F + this.random.nextFloat() * 0.4F
							);
					}

					EntityAttributeInstance entityAttributeInstance = this.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED);
					entityAttributeInstance.removeModifier(DRINKING_SPEED_PENALTY_MODIFIER_ID);
					entityAttributeInstance.addTemporaryModifier(DRINKING_SPEED_PENALTY_MODIFIER);
				}
			}

			if (this.random.nextFloat() < 7.5E-4F) {
				this.getWorld().sendEntityStatus(this, EntityStatuses.ADD_WITCH_PARTICLES);
			}
		}

		super.tickMovement();
	}
    

}
