package net.ent.entstupidstuff.item.base;

import java.util.List;
import java.util.function.Predicate;

import org.jetbrains.annotations.Nullable;
import org.joml.Quaternionf;
import org.joml.Vector3f;

import net.ent.entstupidstuff.entity.projectile.CannonBallEntity;
import net.ent.entstupidstuff.item.ItemFactory;
import net.ent.entstupidstuff.sound.SoundFactory;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ChargedProjectilesComponent;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.RangedWeaponItem;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class FinalCannon extends RangedWeaponItem{

    public FinalCannon(Item.Settings settings) {
        super(settings.maxCount(1).maxDamage(465).component(DataComponentTypes.CHARGED_PROJECTILES, ChargedProjectilesComponent.DEFAULT));
    }

    @Override
    public Predicate<ItemStack> getProjectiles() {
        return stack -> stack.isOf(ItemFactory.CANNON_BALL_ITEM);
    }

    @Override
	public int getRange() {
		return 8;
	}

    @Override
	protected void shoot(LivingEntity shooter, ProjectileEntity projectile, int index, float speed, float divergence, float yaw, @Nullable LivingEntity target) {
		Vector3f vector3f;
		if (target != null) {
			double d = target.getX() - shooter.getX();
			double e = target.getZ() - shooter.getZ();
			double f = Math.sqrt(d * d + e * e);
			double g = target.getBodyY(0.3333333333333333) - projectile.getY() + f * 0.2F;
			vector3f = calcVelocity(shooter, new Vec3d(d, g, e), yaw);
		} else {
			Vec3d vec3d = shooter.getOppositeRotationVector(1.0F);
			Quaternionf quaternionf = new Quaternionf().setAngleAxis((double)(yaw * (float) (Math.PI / 180.0)), vec3d.x, vec3d.y, vec3d.z);
			Vec3d vec3d2 = shooter.getRotationVec(1.0F);
			vector3f = vec3d2.toVector3f().rotate(quaternionf);
		}

		projectile.setVelocity((double)vector3f.x(), (double)vector3f.y(), (double)vector3f.z(), speed, divergence);

		shooter.getWorld().playSound(null, shooter.getX(), shooter.getY(), shooter.getZ(), SoundFactory.COMBAT_HAMMER_GROUND, shooter.getSoundCategory(), 1.0f, 1.0f); 

	}

    private static Vector3f calcVelocity(LivingEntity shooter, Vec3d direction, float yaw) {
		Vector3f vector3f = direction.toVector3f().normalize();
		Vector3f vector3f2 = new Vector3f(vector3f).cross(new Vector3f(0.0F, 1.0F, 0.0F));
		if ((double)vector3f2.lengthSquared() <= 1.0E-7) {
			Vec3d vec3d = shooter.getOppositeRotationVector(1.0F);
			vector3f2 = new Vector3f(vector3f).cross(vec3d.toVector3f());
		}

		Vector3f vector3f3 = new Vector3f(vector3f).rotateAxis((float) (Math.PI / 2), vector3f2.x, vector3f2.y, vector3f2.z);
		return new Vector3f(vector3f).rotateAxis(yaw * (float) (Math.PI / 180.0), vector3f3.x, vector3f3.y, vector3f3.z);
	}

    @Override
	protected ProjectileEntity createArrowEntity(World world, LivingEntity shooter, ItemStack weaponStack, ItemStack projectileStack, boolean critical) {
		if (projectileStack.isOf(ItemFactory.CANNON_BALL_ITEM)) { //TODO: Change to CannonBall Entity when Done
			//return new FireworkRocketEntity(world, projectileStack, shooter, shooter.getX(), shooter.getEyeY() - 0.15F, shooter.getZ(), true);
			//return new CannonBallEntity(world, projectileStack, shooter, shooter.getX(), shooter.getEyeY() - 0.15F, shooter.getZ(), true);
			//return new CannonBallEntity(world, shooter, weaponStack, null);
			return new CannonBallEntity(world, shooter, projectileStack, weaponStack);
		} else {
			ProjectileEntity projectileEntity = super.createArrowEntity(world, shooter, weaponStack, projectileStack, critical);
			if (projectileEntity instanceof PersistentProjectileEntity persistentProjectileEntity) {
				persistentProjectileEntity.setSound(SoundEvents.ITEM_CROSSBOW_HIT);
			}

			return projectileEntity;
		}
	}

    ////

    @Override
	public boolean isUsedOnRelease(ItemStack stack) {
		return stack.isOf(this);
	}

    private boolean charged = false;
	private boolean loaded = false;

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand)
    {

        ItemStack itemStack = user.getStackInHand(hand);
		ChargedProjectilesComponent chargedProjectilesComponent = itemStack.get(DataComponentTypes.CHARGED_PROJECTILES);
		if (chargedProjectilesComponent != null && !chargedProjectilesComponent.isEmpty()) {
			this.shootAll(world, user, hand, itemStack, getSpeed(chargedProjectilesComponent), 1.0F, null);
			return TypedActionResult.consume(itemStack);
		} else if (!user.getProjectileType(itemStack).isEmpty()) {
			this.charged = false;
			this.loaded = false;
			user.setCurrentHand(hand);
			return TypedActionResult.consume(itemStack);
		} else {
			return TypedActionResult.fail(itemStack);
		}

    }

    private static float getSpeed(ChargedProjectilesComponent stack) {
		return stack.contains(ItemFactory.CANNON_BALL_ITEM) ? 1.6F : 3.15F;
	}

    @Override
	public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
		int i = this.getMaxUseTime(stack, user) - remainingUseTicks;
		float f = getPullProgress(i, stack, user);
		if (f >= 1.0F && !isCharged(stack) && loadProjectiles(user, stack)) {
			/*CrossbowItem.LoadingSounds loadingSounds = this.getLoadingSounds(stack);
			loadingSounds.end()
				.ifPresent(
					sound -> world.playSound(
							null,
							user.getX(),
							user.getY(),
							user.getZ(),
							(SoundEvent)sound.value(),
							user.getSoundCategory(),
							1.0F,
							1.0F / (world.getRandom().nextFloat() * 0.5F + 1.0F) + 0.2F
						)
				);*/
		}
	}

    private static boolean loadProjectiles(LivingEntity shooter, ItemStack crossbow) {
		List<ItemStack> list = load(crossbow, shooter.getProjectileType(crossbow), shooter);
		if (!list.isEmpty()) {
			crossbow.set(DataComponentTypes.CHARGED_PROJECTILES, ChargedProjectilesComponent.of(list));
			return true;
		} else {
			return false;
		}
	}

	public static boolean isCharged(ItemStack stack) {
		ChargedProjectilesComponent chargedProjectilesComponent = stack.getOrDefault(DataComponentTypes.CHARGED_PROJECTILES, ChargedProjectilesComponent.DEFAULT);
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
		float f = EnchantmentHelper.getCrossbowChargeTime(stack, user, 1.25F);
		return MathHelper.floor(f * 20.0F);
	}

    public void shootAll(World world, LivingEntity shooter, Hand hand, ItemStack stack, float speed, float divergence, @Nullable LivingEntity target) {
		if (world instanceof ServerWorld serverWorld) {
			ChargedProjectilesComponent chargedProjectilesComponent = stack.set(DataComponentTypes.CHARGED_PROJECTILES, ChargedProjectilesComponent.DEFAULT);
			if (chargedProjectilesComponent != null && !chargedProjectilesComponent.isEmpty()) {
				this.shootAll(serverWorld, shooter, hand, stack, chargedProjectilesComponent.getProjectiles(), speed, divergence, shooter instanceof PlayerEntity, target);
				if (shooter instanceof ServerPlayerEntity serverPlayerEntity) {
					Criteria.SHOT_CROSSBOW.trigger(serverPlayerEntity, stack);
					serverPlayerEntity.incrementStat(Stats.USED.getOrCreateStat(stack.getItem()));

				}

				if (shooter instanceof PlayerEntity player && !player.isCreative()) {
	
					player.getItemCooldownManager().set(player.getMainHandStack().getItem(), (20 * 15));
				}
			}
		}
	}

    @Override
	public void usageTick(World world, LivingEntity user, ItemStack stack, int remainingUseTicks) {
		if (!world.isClient) {
			//CrossbowItem.LoadingSounds loadingSounds = this.getLoadingSounds(stack);
			float f = (float)(stack.getMaxUseTime(user) - remainingUseTicks) / (float)getPullTime(stack, user);
			if (f < 0.2F) {
				this.charged = false;
				this.loaded = false;
			}

			if (f >= 0.2F && !this.charged) {
				this.charged = true;
				//loadingSounds.start()
				//	.ifPresent(sound -> world.playSound(null, user.getX(), user.getY(), user.getZ(), (SoundEvent)sound.value(), SoundCategory.PLAYERS, 0.5F, 1.0F));
			}

			if (f >= 0.5F && !this.loaded) {
				this.loaded = true;
				//loadingSounds.mid()
				//	.ifPresent(sound -> world.playSound(null, user.getX(), user.getY(), user.getZ(), (SoundEvent)sound.value(), SoundCategory.PLAYERS, 0.5F, 1.0F));
			}
		}
	}

    @Override
	public int getMaxUseTime(ItemStack stack, LivingEntity user) {
		return getPullTime(stack, user) + 3;
	}

    @Override
	public UseAction getUseAction(ItemStack stack) {
		return UseAction.CROSSBOW;
	}

    
}
