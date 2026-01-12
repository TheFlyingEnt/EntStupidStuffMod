package net.ent.entstupidstuff.item.base;

import java.util.List;
import java.util.function.Predicate;

import org.jetbrains.annotations.Nullable;
import org.joml.Quaternionf;
import org.joml.Vector3f;

import net.ent.entstupidstuff.client.entity.projectile.CannonballEntity;
import net.ent.entstupidstuff.item.ItemFactory;
import net.ent.entstupidstuff.sound.SoundFactory;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.component.DataComponents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.item.component.ChargedProjectiles;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class CannonItem extends ProjectileWeaponItem{

    public CannonItem(Item.Properties settings) {
        super(settings.stacksTo(1).durability(465).component(DataComponents.CHARGED_PROJECTILES, ChargedProjectiles.EMPTY));
    }

    @Override
    public Predicate<ItemStack> getAllSupportedProjectiles() {
        return stack -> stack.is(ItemFactory.CANNON_BALL_ITEM);
    }

    @Override
	public int getDefaultProjectileRange() {
		return 8;
	}

    @Override
	protected void shootProjectile(LivingEntity shooter, Projectile projectile, int index, float speed, float divergence, float yaw, @Nullable LivingEntity target) {
		Vector3f vector3f;
		if (target != null) {
			double d = target.getX() - shooter.getX();
			double e = target.getZ() - shooter.getZ();
			double f = Math.sqrt(d * d + e * e);
			double g = target.getY(0.3333333333333333) - projectile.getY() + f * 0.2F;
			vector3f = calcVelocity(shooter, new Vec3(d, g, e), yaw);
		} else {
			Vec3 vec3d = shooter.getUpVector(1.0F);
			Quaternionf quaternionf = new Quaternionf().setAngleAxis((double)(yaw * (float) (Math.PI / 180.0)), vec3d.x, vec3d.y, vec3d.z);
			Vec3 vec3d2 = shooter.getViewVector(1.0F);
			vector3f = vec3d2.toVector3f().rotate(quaternionf);
		}

		projectile.shoot((double)vector3f.x(), (double)vector3f.y(), (double)vector3f.z(), speed, divergence);

		shooter.level().playSound(null, shooter.getX(), shooter.getY(), shooter.getZ(), SoundFactory.COMBAT_HAMMER_GROUND, shooter.getSoundSource(), 1.0f, 1.0f); 

	}

    private static Vector3f calcVelocity(LivingEntity shooter, Vec3 direction, float yaw) {
		Vector3f vector3f = direction.toVector3f().normalize();
		Vector3f vector3f2 = new Vector3f(vector3f).cross(new Vector3f(0.0F, 1.0F, 0.0F));
		if ((double)vector3f2.lengthSquared() <= 1.0E-7) {
			Vec3 vec3d = shooter.getUpVector(1.0F);
			vector3f2 = new Vector3f(vector3f).cross(vec3d.toVector3f());
		}

		Vector3f vector3f3 = new Vector3f(vector3f).rotateAxis((float) (Math.PI / 2), vector3f2.x, vector3f2.y, vector3f2.z);
		return new Vector3f(vector3f).rotateAxis(yaw * (float) (Math.PI / 180.0), vector3f3.x, vector3f3.y, vector3f3.z);
	}

    @Override
	protected Projectile createProjectile(Level world, LivingEntity shooter, ItemStack weaponStack, ItemStack projectileStack, boolean critical) {
		if (projectileStack.is(ItemFactory.CANNON_BALL_ITEM)) {
			return new CannonballEntity(world, shooter, projectileStack, weaponStack);
		} else {
			return new CannonballEntity(world, shooter, projectileStack, weaponStack);
		}
	}

    ////

    @Override
	public boolean useOnRelease(ItemStack stack) {
		return stack.is(this);
	}

    private boolean charged = false;
	private boolean loaded = false;

    @Override
    public InteractionResult use(Level world, Player user, InteractionHand hand)
    {

        ItemStack itemStack = user.getItemInHand(hand);
		ChargedProjectiles chargedProjectilesComponent = itemStack.get(DataComponents.CHARGED_PROJECTILES);
		if (chargedProjectilesComponent != null && !chargedProjectilesComponent.isEmpty()) {
			this.shootAll(world, user, hand, itemStack, getSpeed(chargedProjectilesComponent), 1.0F, null);
			return InteractionResult.CONSUME;
		} else if (!user.getProjectile(itemStack).isEmpty()) {
			this.charged = false;
			this.loaded = false;
			user.startUsingItem(hand);
			return InteractionResult.CONSUME;
		} else {
			return InteractionResult.FAIL;
		}

    }

    private static float getSpeed(ChargedProjectiles stack) {
		return stack.contains(ItemFactory.CANNON_BALL_ITEM) ? 1.6F : 3.15F;
	}

    @Override
	public boolean releaseUsing(ItemStack stack, Level world, LivingEntity user, int remainingUseTicks) {
		int i = this.getUseDuration(stack, user) - remainingUseTicks;
		return getPullProgress(i, stack, user) >= 1.0F && isCharged(stack);
	}

    private static boolean loadProjectiles(LivingEntity shooter, ItemStack crossbow) {
		List<ItemStack> list = draw(crossbow, shooter.getProjectile(crossbow), shooter);
		if (!list.isEmpty()) {
			crossbow.set(DataComponents.CHARGED_PROJECTILES, ChargedProjectiles.of(list));
			return true;
		} else {
			return false;
		}
	}

	public static boolean isCharged(ItemStack stack) {
		ChargedProjectiles chargedProjectilesComponent = stack.getOrDefault(DataComponents.CHARGED_PROJECTILES, ChargedProjectiles.EMPTY);
		return !chargedProjectilesComponent.isEmpty();
	}

    private static float getPullProgress(int useTicks, ItemStack stack, LivingEntity user) {
		float f = (float)useTicks / (float)getPullTime(stack, user);
		if (f > 1.0F) {
			f = 1.0F;
		}

		return f;
	}

    public static int getPullTime(ItemStack stack, LivingEntity user) {
		float f = EnchantmentHelper.modifyCrossbowChargingTime(stack, user, 1.25F);
		return Mth.floor(f * 20.0F);
	}

    public void shootAll(Level world, LivingEntity shooter, InteractionHand hand, ItemStack stack, float speed, float divergence, @Nullable LivingEntity target) {
		if (world instanceof ServerLevel serverWorld) {
			ChargedProjectiles chargedProjectilesComponent = stack.set(DataComponents.CHARGED_PROJECTILES, ChargedProjectiles.EMPTY);
			if (chargedProjectilesComponent != null && !chargedProjectilesComponent.isEmpty()) {
				this.shoot(serverWorld, shooter, hand, stack, chargedProjectilesComponent.getItems(), speed, divergence, shooter instanceof Player, target);
				if (shooter instanceof ServerPlayer serverPlayerEntity) {
					CriteriaTriggers.SHOT_CROSSBOW.trigger(serverPlayerEntity, stack);
					serverPlayerEntity.awardStat(Stats.ITEM_USED.get(stack.getItem()));

				}

				if (shooter instanceof Player player && !player.isCreative()) {
	
					player.getCooldowns().addCooldown(player.getMainHandItem(), (20 * 15));
				}
			}
		}
	}

    @Override
	public void onUseTick(Level world, LivingEntity user, ItemStack stack, int remainingUseTicks) {
		if (!world.isClientSide()) {
			float f = (float)(stack.getUseDuration(user) - remainingUseTicks) / (float)getPullTime(stack, user);
			if (f < 0.2F) {
				this.charged = false;
				this.loaded = false;
			}

			if (f >= 0.2F && !this.charged) {
				this.charged = true;
				//loadingSounds.start()
			}

			if (f >= 0.5F && !this.loaded) {
				this.loaded = true;
				loadProjectiles(user, stack);
				//loadingSounds.mid()
			}
		}
	}

    @Override
	public int getUseDuration(ItemStack stack, LivingEntity user) {
		return getPullTime(stack, user) + 3;
	}

    @Override
	public ItemUseAnimation getUseAnimation(ItemStack stack) {
		return ItemUseAnimation.CROSSBOW;
	}

    
}
