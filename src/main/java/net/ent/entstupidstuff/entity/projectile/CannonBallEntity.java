package net.ent.entstupidstuff.entity.projectile;

import org.jetbrains.annotations.Nullable;

import net.ent.entstupidstuff.api.enchantment.EntEnchantmentHelper;
import net.ent.entstupidstuff.item.ItemFactory;
import net.ent.entstupidstuff.registry.EntityFactory;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.data.DataTracker.Builder;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;

public class CannonballEntity extends PersistentProjectileEntity {

	/*
	 * Enhantments:
	 * Flame Like Enchantment
	 * Ghost Like Sunken Enchantment
	 * Sunken Enchantment
	 * AOT Potion Enchantment??
	 */

	private boolean hasFlame;

	public CannonballEntity(EntityType<? extends CannonballEntity> entityType, World world) {
		super(entityType, world);
	}

	public CannonballEntity(World world, LivingEntity owner, ItemStack stack, @Nullable ItemStack shotFrom) { // Player
		super(EntityFactory.CANNON_BALL, owner, world, stack, shotFrom);

		if (shotFrom != null) {
			this.hasFlame =
			EnchantmentHelper.getLevel(EntEnchantmentHelper.getEnchantmentEntry(owner.getEntityWorld(), Enchantments.FLAME, 99999999), stack) > 0;
		}
	}

	public CannonballEntity(World world, double x, double y, double z, ItemStack stack, @Nullable ItemStack shotFrom) { // Mob
		super(EntityFactory.CANNON_BALL, x, y, z, world, stack, shotFrom);
	}

	@Override
	public void setVelocity(double x, double y, double z, float power, float uncertainty) {
		super.setVelocity(x, y, z, power, uncertainty);
	}

	@Override
	protected ItemStack getDefaultItemStack() {
		return new ItemStack(ItemFactory.CANNON_BALL_ITEM);
	}

	@Override
	public void tick() {
		super.tick();
		if (this.getEntityWorld().isClient() && !this.isSubmergedInWater()) {
			if (this.getVelocity().lengthSquared() > 0.01) {
				this.getEntityWorld().addParticleClient(
						ParticleTypes.LARGE_SMOKE,
						this.getX(),
						this.getY(),
						this.getZ(),
						this.random.nextGaussian() * 0.05,
						-this.getVelocity().y * 0.5,
						this.random.nextGaussian() * 0.05);
				this.getEntityWorld().addParticleClient(
						ParticleTypes.FLAME,
						this.getX(),
						this.getY(),
						this.getZ(),
						this.random.nextGaussian() * 0.05,
						-this.getVelocity().y * 0.5,
						this.random.nextGaussian() * 0.05);
			} else {
				if (this.getOwner() instanceof PlayerEntity) {

					this.getEntityWorld().addParticleClient(
							ParticleTypes.CAMPFIRE_SIGNAL_SMOKE,
							this.getX(),
							this.getY() + this.getHeight(),
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
	protected void onCollision(HitResult hitResult) {
		super.onCollision(hitResult);
		if (!this.getEntityWorld().isClient()) {

		}
	}

	@Override
	protected void onBlockHit(BlockHitResult blockHitResult) {
		if (hit == 0) {
			this.getEntityWorld().createExplosion(null, this.getX(), this.getY(), this.getZ(), 2.0F,
					World.ExplosionSourceType.NONE);
			hit = 1;
			this.getEntityWorld().addParticleClient(
					ParticleTypes.EXPLOSION_EMITTER,
					this.getX(),
					this.getY(),
					this.getZ(),
					this.random.nextGaussian() * 0.05,
					-this.getVelocity().y * 0.5,
					this.random.nextGaussian() * 0.05);
		}
		super.onBlockHit(blockHitResult);
	}

	@Override
	protected void onEntityHit(EntityHitResult entityHitResult) {
		super.onEntityHit(entityHitResult);
		if (!this.getEntityWorld().isClient() && hit == 0) {
			this.getEntityWorld().createExplosion(null, this.getX(), this.getY(), this.getZ(), 2.0F,
					World.ExplosionSourceType.NONE);
			hit = 1;
		}

		Entity target = entityHitResult.getEntity();

		if (target instanceof LivingEntity living) {
			// Flame
			if (hasFlame) {
				living.setOnFireFor(5);
			}
		}
	}

	@SuppressWarnings("unused")
	private SoundEvent sound = this.getHitSound();

	public void setSound(SoundEvent sound) {
		this.sound = sound;
	}

	protected SoundEvent getHitSound() {
		return SoundEvents.ENTITY_ARROW_HIT;
	}

	@Override
	protected void initDataTracker(Builder builder) {
		super.initDataTracker(builder);
	}

	public boolean hasFlame() {
		return hasFlame;
	}

}