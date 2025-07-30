package net.ent.entstupidstuff.item.base;

import net.ent.entstupidstuff.entity.projectile.AncientTridentEntity;
import net.minecraft.component.EnchantmentEffectComponentTypes;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity.PickupPermission;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.TridentItem;
import net.minecraft.registry.entry.RegistryEntry;
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
     public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
      if (user instanceof PlayerEntity playerEntity) {
         int var6 = this.getMaxUseTime(stack, user) - remainingUseTicks;
         if (var6 >= 10) {
            float f = EnchantmentHelper.getTridentSpinAttackStrength(stack, playerEntity);
            if (!(f > 0.0F) || playerEntity.isTouchingWaterOrRain()) {
               if (!isAboutToBreak(stack)) {
                  RegistryEntry<SoundEvent> registryEntry = (RegistryEntry)EnchantmentHelper.getEffect(stack, EnchantmentEffectComponentTypes.TRIDENT_SOUND).orElse(SoundEvents.ITEM_TRIDENT_THROW);
                  if (!world.isClient) {
                     stack.damage(1, playerEntity, LivingEntity.getSlotForHand(user.getActiveHand()));
                     if (f == 0.0F) {
                        AncientTridentEntity tridentEntity = new AncientTridentEntity(world, playerEntity, stack);
                        tridentEntity.setVelocity(playerEntity, playerEntity.getPitch(), playerEntity.getYaw(), 0.0F, 2.5F, 1.0F);
                        if (playerEntity.isInCreativeMode()) {
                           tridentEntity.pickupType = PickupPermission.CREATIVE_ONLY;
                        }

                        world.spawnEntity(tridentEntity);
                        world.playSoundFromEntity((PlayerEntity)null, tridentEntity, (SoundEvent)registryEntry.value(), SoundCategory.PLAYERS, 1.0F, 1.0F);
                        if (!playerEntity.isInCreativeMode()) {
                           playerEntity.getInventory().removeOne(stack);
                        }
                     }
                  }

                  playerEntity.incrementStat(Stats.USED.getOrCreateStat(this));
                  if (f > 0.0F) {
                     float g = playerEntity.getYaw();
                     float h = playerEntity.getPitch();
                     float j = -MathHelper.sin(g * 0.017453292F) * MathHelper.cos(h * 0.017453292F);
                     float k = -MathHelper.sin(h * 0.017453292F);
                     float l = MathHelper.cos(g * 0.017453292F) * MathHelper.cos(h * 0.017453292F);
                     float m = MathHelper.sqrt(j * j + k * k + l * l);
                     j *= f / m;
                     k *= f / m;
                     l *= f / m;
                     playerEntity.addVelocity((double)j, (double)k, (double)l);
                     playerEntity.useRiptide(20, 8.0F, stack);
                     if (playerEntity.isOnGround()) {
                        float n = 1.1999999F;
                        playerEntity.move(MovementType.SELF, new Vec3d(0.0, 1.1999999284744263, 0.0));
                     }

                     world.playSoundFromEntity((PlayerEntity)null, playerEntity, (SoundEvent)registryEntry.value(), SoundCategory.PLAYERS, 1.0F, 1.0F);
                  }

               }
            }
         }
      }
   }

    @Override
    public ProjectileEntity createEntity(World world, Position pos, ItemStack stack, Direction direction) {
      AncientTridentEntity tridentEntity = new AncientTridentEntity(world, pos.getX(), pos.getY(), pos.getZ(), stack.copyWithCount(1));
      tridentEntity.pickupType = PickupPermission.ALLOWED;
      return tridentEntity;
   }
    
}
