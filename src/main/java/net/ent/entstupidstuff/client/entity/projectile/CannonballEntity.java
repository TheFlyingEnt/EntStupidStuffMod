package net.ent.entstupidstuff.client.entity.projectile;

import org.jetbrains.annotations.Nullable;

import net.ent.entstupidstuff.api.enchantment.EntEnchantmentHelper;
import net.ent.entstupidstuff.item.ItemFactory;
import net.ent.entstupidstuff.registry.EntityFactory;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.SynchedEntityData.Builder;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

public class CannonballEntity extends AbstractArrow {

	/*
	 * Enhantments:
	 * Flame Like Enchantment
	 * Ghost Like Sunken Enchantment
	 * Sunken Enchantment
	 * AOT Potion Enchantment??
	 */

	private boolean hasFlame;

	public CannonballEntity(EntityType<? extends CannonballEntity> entityType, Level world) {
		super(entityType, world);
	}

	public CannonballEntity(Level world, LivingEntity owner, ItemStack stack, @Nullable ItemStack shotFrom) { // Player
		super(EntityFactory.CANNON_BALL, owner, world, stack, shotFrom);

		if (shotFrom != null) {
			this.hasFlame =
			EnchantmentHelper.getItemEnchantmentLevel(EntEnchantmentHelper.getEnchantmentEntry(owner.level(), Enchantments.FLAME, 99999999), stack) > 0;
		}
	}

	public CannonballEntity(Level world, double x, double y, double z, ItemStack stack, @Nullable ItemStack shotFrom) { // Mob
		super(EntityFactory.CANNON_BALL, x, y, z, world, stack, shotFrom);
	}

	@Override
	public void shoot(double x, double y, double z, float power, float uncertainty) {
		super.shoot(x, y, z, power, uncertainty);
	}

	@Override
	protected ItemStack getDefaultPickupItem() {
		return new ItemStack(ItemFactory.CANNON_BALL_ITEM);
	}

	@Override
	public void tick() {
		super.tick();
		if (this.level().isClientSide() && !this.isUnderWater()) {
			if (this.getDeltaMovement().lengthSqr() > 0.01) {
				this.level().addParticle(
						ParticleTypes.LARGE_SMOKE,
						this.getX(),
						this.getY(),
						this.getZ(),
						this.random.nextGaussian() * 0.05,
						-this.getDeltaMovement().y * 0.5,
						this.random.nextGaussian() * 0.05);
				this.level().addParticle(
						ParticleTypes.FLAME,
						this.getX(),
						this.getY(),
						this.getZ(),
						this.random.nextGaussian() * 0.05,
						-this.getDeltaMovement().y * 0.5,
						this.random.nextGaussian() * 0.05);
			} else {
				if (this.getOwner() instanceof Player) {

					this.level().addParticle(
							ParticleTypes.CAMPFIRE_SIGNAL_SMOKE,
							this.getX(),
							this.getY() + this.getBbHeight(),
							this.getZ(),
							this.random.nextGaussian() * 0.02,
							0.07,
							this.random.nextGaussian() * 0.02);

				}
			}
		}
	}

	////////////////

	public int hit = 0;

	@Override
	protected void onHit(HitResult hitResult) {
		super.onHit(hitResult);
		if (!this.level().isClientSide()) {

		}
	}

	@Override
	protected void onHitBlock(BlockHitResult blockHitResult) {
		if (hit == 0) {
			this.level().explode(null, this.getX(), this.getY(), this.getZ(), 2.0F,
					Level.ExplosionInteraction.NONE);
			hit = 1;
			this.level().addParticle(
					ParticleTypes.EXPLOSION_EMITTER,
					this.getX(),
					this.getY(),
					this.getZ(),
					this.random.nextGaussian() * 0.05,
					-this.getDeltaMovement().y * 0.5,
					this.random.nextGaussian() * 0.05);
		}
		super.onHitBlock(blockHitResult);
	}

	@Override
	protected void onHitEntity(EntityHitResult entityHitResult) {
		super.onHitEntity(entityHitResult);
		if (!this.level().isClientSide() && hit == 0) {
			this.level().explode(null, this.getX(), this.getY(), this.getZ(), 2.0F,
					Level.ExplosionInteraction.NONE);
			hit = 1;
		}

		Entity target = entityHitResult.getEntity();

		if (target instanceof LivingEntity living) {
			// Flame
			if (hasFlame) {
				living.igniteForSeconds(5);
			}
		}
	}

	@SuppressWarnings("unused")
	private SoundEvent sound = this.getDefaultHitGroundSoundEvent();

	public void setSoundEvent(SoundEvent sound) {
		this.sound = sound;
	}

	protected SoundEvent getDefaultHitGroundSoundEvent() {
		return SoundEvents.ARROW_HIT;
	}

	@Override
	protected void defineSynchedData(Builder builder) {
		super.defineSynchedData(builder);
	}

	public boolean hasFlame() {
		return hasFlame;
	}

}