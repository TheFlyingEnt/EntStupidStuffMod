package net.ent.entstupidstuff.item.base;

import net.ent.entstupidstuff.client.entity.projectile.AncientTridentEntity;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.Position;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.AbstractArrow.Pickup;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ThrownTrident;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TridentItem;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class AncientTridentItem extends TridentItem{

   public AncientTridentItem(Item.Properties settings) {
      super(settings);
   }

   private static boolean isAboutToBreak(ItemStack stack) {
      return stack.getDamageValue() >= stack.getMaxDamage() - 1;
   }

   @Override
	public boolean releaseUsing(ItemStack stack, Level world, LivingEntity user, int remainingUseTicks) {
		if (user instanceof Player playerEntity) {
			int i = this.getUseDuration(stack, user) - remainingUseTicks;
			if (i < 10) {
				return false;
			} else {
				float f = EnchantmentHelper.getTridentSpinAttackStrength(stack, playerEntity);
				if (f > 0.0F && !playerEntity.isInWaterOrRain()) {
					return false;
				} else if (stack.nextDamageWillBreak()) {
					return false;
				} else {
					Holder<SoundEvent> registryEntry = (Holder<SoundEvent>)EnchantmentHelper.pickHighestLevel(stack, EnchantmentEffectComponents.TRIDENT_SOUND)
						.orElse(SoundEvents.TRIDENT_THROW);
					playerEntity.awardStat(Stats.ITEM_USED.get(this));
					if (world instanceof ServerLevel serverWorld) {
						stack.hurtWithoutBreaking(1, playerEntity);
						if (f == 0.0F) {
							ItemStack itemStack = stack.consumeAndReturn(1, playerEntity);
							ThrownTrident tridentEntity = Projectile.spawnProjectileFromRotation(ThrownTrident::new, serverWorld, itemStack, playerEntity, 0.0F, 2.5F, 1.0F);
							if (playerEntity.hasInfiniteMaterials()) {
								tridentEntity.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
							}

							world.playSound(null, tridentEntity, registryEntry.value(), SoundSource.PLAYERS, 1.0F, 1.0F);
							return true;
						}
					}

					if (f > 0.0F) {
						float g = playerEntity.getYRot();
						float h = playerEntity.getXRot();
						float j = -Mth.sin(g * (float) (Math.PI / 180.0)) * Mth.cos(h * (float) (Math.PI / 180.0));
						float k = -Mth.sin(h * (float) (Math.PI / 180.0));
						float l = Mth.cos(g * (float) (Math.PI / 180.0)) * Mth.cos(h * (float) (Math.PI / 180.0));
						float m = Mth.sqrt(j * j + k * k + l * l);
						j *= f / m;
						k *= f / m;
						l *= f / m;
						playerEntity.push(j, k, l);
						playerEntity.startAutoSpinAttack(20, 8.0F, stack);
						if (playerEntity.onGround()) {
							float n = 1.1999999F;
							playerEntity.move(MoverType.SELF, new Vec3(0.0, 1.1999999F, 0.0));
						}

						world.playSound(null, playerEntity, registryEntry.value(), SoundSource.PLAYERS, 1.0F, 1.0F);
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
   public Projectile asProjectile(Level world, Position pos, ItemStack stack, Direction direction) {
      AncientTridentEntity tridentEntity = new AncientTridentEntity(world, pos.x(), pos.y(), pos.z(), stack.copyWithCount(1));
      tridentEntity.pickup = Pickup.ALLOWED;
      return tridentEntity;
   }
    
}
