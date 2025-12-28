package net.ent.entstupidstuff.item.base;

import net.ent.entstupidstuff.client.entity.projectile.AncientTridentEntity;
import net.minecraft.component.EnchantmentEffectComponentTypes;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity.PickupPermission;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.TridentEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.TridentItem;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Position;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class AncientTridentItem extends TridentItem{

   public AncientTridentItem(Item.Settings settings) {
      super(settings);
   }

   private static boolean isAboutToBreak(ItemStack stack) {
      return stack.getDamage() >= stack.getMaxDamage() - 1;
   }

   @Override
	public boolean onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
		if (user instanceof PlayerEntity playerEntity) {
			int i = this.getMaxUseTime(stack, user) - remainingUseTicks;
			if (i < 10) {
				return false;
			} else {
				float f = EnchantmentHelper.getTridentSpinAttackStrength(stack, playerEntity);
				if (f > 0.0F && !playerEntity.isTouchingWaterOrRain()) {
					return false;
				} else if (stack.willBreakNextUse()) {
					return false;
				} else {
					RegistryEntry<SoundEvent> registryEntry = (RegistryEntry<SoundEvent>)EnchantmentHelper.getEffect(stack, EnchantmentEffectComponentTypes.TRIDENT_SOUND)
						.orElse(SoundEvents.ITEM_TRIDENT_THROW);
					playerEntity.incrementStat(Stats.USED.getOrCreateStat(this));
					if (world instanceof ServerWorld serverWorld) {
						stack.damage(1, playerEntity);
						if (f == 0.0F) {
							ItemStack itemStack = stack.splitUnlessCreative(1, playerEntity);
							TridentEntity tridentEntity = ProjectileEntity.spawnWithVelocity(TridentEntity::new, serverWorld, itemStack, playerEntity, 0.0F, 2.5F, 1.0F);
							if (playerEntity.isInCreativeMode()) {
								tridentEntity.pickupType = PersistentProjectileEntity.PickupPermission.CREATIVE_ONLY;
							}

							world.playSoundFromEntity(null, tridentEntity, registryEntry.value(), SoundCategory.PLAYERS, 1.0F, 1.0F);
							return true;
						}
					}

					if (f > 0.0F) {
						float g = playerEntity.getYaw();
						float h = playerEntity.getPitch();
						float j = -MathHelper.sin(g * (float) (Math.PI / 180.0)) * MathHelper.cos(h * (float) (Math.PI / 180.0));
						float k = -MathHelper.sin(h * (float) (Math.PI / 180.0));
						float l = MathHelper.cos(g * (float) (Math.PI / 180.0)) * MathHelper.cos(h * (float) (Math.PI / 180.0));
						float m = MathHelper.sqrt(j * j + k * k + l * l);
						j *= f / m;
						k *= f / m;
						l *= f / m;
						playerEntity.addVelocity(j, k, l);
						playerEntity.useRiptide(20, 8.0F, stack);
						if (playerEntity.isOnGround()) {
							float n = 1.1999999F;
							playerEntity.move(MovementType.SELF, new Vec3d(0.0, 1.1999999F, 0.0));
						}

						world.playSoundFromEntity(null, playerEntity, registryEntry.value(), SoundCategory.PLAYERS, 1.0F, 1.0F);
						return true;
					} else {
						return false;
					}
				}
			}
		} else {
			return false;
		}
	}

   @Override
   public ProjectileEntity createEntity(World world, Position pos, ItemStack stack, Direction direction) {
      AncientTridentEntity tridentEntity = new AncientTridentEntity(world, pos.getX(), pos.getY(), pos.getZ(), stack.copyWithCount(1));
      tridentEntity.pickupType = PickupPermission.ALLOWED;
      return tridentEntity;
   }
    
}
