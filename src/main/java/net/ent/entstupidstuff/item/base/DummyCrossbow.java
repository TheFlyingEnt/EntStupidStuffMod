package net.ent.entstupidstuff.item.base;

import com.mojang.serialization.Codec;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.component.DataComponents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.FireworkRocketEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.item.component.ChargedProjectiles;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import org.joml.Quaternionf;
import org.joml.Vector3f;


import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.CrossbowItem.ChargingSounds;

public class DummyCrossbow extends ProjectileWeaponItem {
   private static final float MAX_CHARGE_DURATION = 1.25F;
   public static final int DEFAULT_RANGE = 8;
   private boolean startSoundPlayed = false;
   private boolean midLoadSoundPlayed = false;
   private static final float START_SOUND_PERCENT = 0.2F;
   private static final float MID_SOUND_PERCENT = 0.5F;
   private static final float ARROW_POWER = 3.15F;
   private static final float FIREWORK_POWER = 1.6F;
   public static final float MOB_ARROW_POWER = 1.6F;
    private static final CrossbowItem.ChargingSounds DEFAULT_SOUNDS = new CrossbowItem.ChargingSounds(
            Optional.of(SoundEvents.CROSSBOW_LOADING_START),
            Optional.of(SoundEvents.CROSSBOW_LOADING_MIDDLE),
            Optional.of(SoundEvents.CROSSBOW_LOADING_END));

   public DummyCrossbow(Item.Properties properties) {
      super(properties);
   }

   public Predicate<ItemStack> getSupportedHeldProjectiles() {
      return ARROW_OR_FIREWORK;
   }

   public Predicate<ItemStack> getAllSupportedProjectiles() {
      return ARROW_ONLY;
   }

   public InteractionResult use(Level level, Player player, InteractionHand interactionHand) {
      ItemStack itemStack = player.getItemInHand(interactionHand);
      ChargedProjectiles chargedProjectiles = (ChargedProjectiles)itemStack.get(DataComponents.CHARGED_PROJECTILES);
      if (chargedProjectiles != null && !chargedProjectiles.isEmpty()) {
         this.performShooting(level, player, interactionHand, itemStack, getShootingPower(chargedProjectiles), 1.0F, (LivingEntity)null);
         return InteractionResult.CONSUME;
      } else if (!player.getProjectile(itemStack).isEmpty()) {
         this.startSoundPlayed = false;
         this.midLoadSoundPlayed = false;
         player.startUsingItem(interactionHand);
         return InteractionResult.CONSUME;
      } else {
         return InteractionResult.FAIL;
      }
   }

   private static float getShootingPower(ChargedProjectiles chargedProjectiles) {
      return chargedProjectiles.contains(Items.FIREWORK_ROCKET) ? 1.6F : 3.15F;
   }

   public boolean releaseUsing(ItemStack itemStack, Level level, LivingEntity livingEntity, int i) {
      int j = this.getUseDuration(itemStack, livingEntity) - i;
      return getPowerForTime(j, itemStack, livingEntity) >= 1.0F && isCharged(itemStack);
   }

   private static boolean tryLoadProjectiles(LivingEntity livingEntity, ItemStack itemStack) {
      List<ItemStack> list = draw(itemStack, livingEntity.getProjectile(itemStack), livingEntity);
      if (!list.isEmpty()) {
         itemStack.set(DataComponents.CHARGED_PROJECTILES, ChargedProjectiles.of(list));
         return true;
      } else {
         return false;
      }
   }

   public static boolean isCharged(ItemStack itemStack) {
      ChargedProjectiles chargedProjectiles = (ChargedProjectiles)itemStack.getOrDefault(DataComponents.CHARGED_PROJECTILES, ChargedProjectiles.EMPTY);
      return !chargedProjectiles.isEmpty();
   }

   protected void shootProjectile(LivingEntity livingEntity, Projectile projectile, int i, float f, float g, float h, @Nullable LivingEntity livingEntity2) {
      Vector3f vector3f;
      if (livingEntity2 != null) {
         double d = livingEntity2.getX() - livingEntity.getX();
         double e = livingEntity2.getZ() - livingEntity.getZ();
         double j = Math.sqrt(d * d + e * e);
         double k = livingEntity2.getY(0.3333333333333333) - projectile.getY() + j * 0.20000000298023224;
         vector3f = getProjectileShotVector(livingEntity, new Vec3(d, k, e), h);
      } else {
         Vec3 vec3 = livingEntity.getUpVector(1.0F);
         Quaternionf quaternionf = (new Quaternionf()).setAngleAxis((double)(h * 0.017453292F), vec3.x, vec3.y, vec3.z);
         Vec3 vec32 = livingEntity.getViewVector(1.0F);
         vector3f = vec32.toVector3f().rotate(quaternionf);
      }

      projectile.shoot((double)vector3f.x(), (double)vector3f.y(), (double)vector3f.z(), f, g);
      float l = getShotPitch(livingEntity.getRandom(), i);
      livingEntity.level().playSound((Entity)null, livingEntity.getX(), livingEntity.getY(), livingEntity.getZ(), SoundEvents.CROSSBOW_SHOOT, livingEntity.getSoundSource(), 1.0F, l);
   }

   private static Vector3f getProjectileShotVector(LivingEntity livingEntity, Vec3 vec3, float f) {
      Vector3f vector3f = vec3.toVector3f().normalize();
      Vector3f vector3f2 = (new Vector3f(vector3f)).cross(new Vector3f(0.0F, 1.0F, 0.0F));
      if ((double)vector3f2.lengthSquared() <= 1.0E-7) {
         Vec3 vec32 = livingEntity.getUpVector(1.0F);
         vector3f2 = (new Vector3f(vector3f)).cross(vec32.toVector3f());
      }

      Vector3f vector3f3 = (new Vector3f(vector3f)).rotateAxis(1.5707964F, vector3f2.x, vector3f2.y, vector3f2.z);
      return (new Vector3f(vector3f)).rotateAxis(f * 0.017453292F, vector3f3.x, vector3f3.y, vector3f3.z);
   }

   protected Projectile createProjectile(Level level, LivingEntity livingEntity, ItemStack itemStack, ItemStack itemStack2, boolean bl) {
      if (itemStack2.is(Items.FIREWORK_ROCKET)) {
         return new FireworkRocketEntity(level, itemStack2, livingEntity, livingEntity.getX(), livingEntity.getEyeY() - 0.15000000596046448, livingEntity.getZ(), true);
      } else {
         Projectile projectile = super.createProjectile(level, livingEntity, itemStack, itemStack2, bl);
         if (projectile instanceof AbstractArrow) {
            AbstractArrow abstractArrow = (AbstractArrow)projectile;
            abstractArrow.setSoundEvent(SoundEvents.CROSSBOW_HIT);
         }

         return projectile;
      }
   }

   protected int getDurabilityUse(ItemStack itemStack) {
      return itemStack.is(Items.FIREWORK_ROCKET) ? 3 : 1;
   }

   public void performShooting(Level level, LivingEntity livingEntity, InteractionHand interactionHand, ItemStack itemStack, float f, float g, @Nullable LivingEntity livingEntity2) {
      if (level instanceof ServerLevel serverLevel) {
         ChargedProjectiles chargedProjectiles = (ChargedProjectiles)itemStack.set(DataComponents.CHARGED_PROJECTILES, ChargedProjectiles.EMPTY);
         if (chargedProjectiles != null && !chargedProjectiles.isEmpty()) {
            this.shoot(serverLevel, livingEntity, interactionHand, itemStack, chargedProjectiles.getItems(), f, g, livingEntity instanceof Player, livingEntity2);
            if (livingEntity instanceof ServerPlayer) {
               ServerPlayer serverPlayer = (ServerPlayer)livingEntity;
               CriteriaTriggers.SHOT_CROSSBOW.trigger(serverPlayer, itemStack);
               serverPlayer.awardStat(Stats.ITEM_USED.get(itemStack.getItem()));
            }

         }
      }
   }

   private static float getShotPitch(RandomSource randomSource, int i) {
      return i == 0 ? 1.0F : getRandomShotPitch((i & 1) == 1, randomSource);
   }

   private static float getRandomShotPitch(boolean bl, RandomSource randomSource) {
      float f = bl ? 0.63F : 0.43F;
      return 1.0F / (randomSource.nextFloat() * 0.5F + 1.8F) + f;
   }

   public void onUseTick(Level level, LivingEntity livingEntity, ItemStack itemStack, int i) {
      if (!level.isClientSide()) {
         ChargingSounds chargingSounds = this.getChargingSounds(itemStack);
         float f = (float)(itemStack.getUseDuration(livingEntity) - i) / (float)getChargeDuration(itemStack, livingEntity);
         if (f < 0.2F) {
            this.startSoundPlayed = false;
            this.midLoadSoundPlayed = false;
         }

         if (f >= 0.2F && !this.startSoundPlayed) {
            this.startSoundPlayed = true;
            chargingSounds.start().ifPresent((holder) -> {
               level.playSound((Entity)null, livingEntity.getX(), livingEntity.getY(), livingEntity.getZ(), (SoundEvent)holder.value(), SoundSource.PLAYERS, 0.5F, 1.0F);
            });
         }

         if (f >= 0.5F && !this.midLoadSoundPlayed) {
            this.midLoadSoundPlayed = true;
            chargingSounds.mid().ifPresent((holder) -> {
               level.playSound((Entity)null, livingEntity.getX(), livingEntity.getY(), livingEntity.getZ(), (SoundEvent)holder.value(), SoundSource.PLAYERS, 0.5F, 1.0F);
            });
         }

         if (f >= 1.0F && !isCharged(itemStack) && tryLoadProjectiles(livingEntity, itemStack)) {
            chargingSounds.end().ifPresent((holder) -> {
               level.playSound((Entity)null, livingEntity.getX(), livingEntity.getY(), livingEntity.getZ(), (SoundEvent)holder.value(), livingEntity.getSoundSource(), 1.0F, 1.0F / (level.getRandom().nextFloat() * 0.5F + 1.0F) + 0.2F);
            });
         }
      }

   }

   public int getUseDuration(ItemStack itemStack, LivingEntity livingEntity) {
      return 72000;
   }

   public static int getChargeDuration(ItemStack itemStack, LivingEntity livingEntity) {
      float f = EnchantmentHelper.modifyCrossbowChargingTime(itemStack, livingEntity, 1.25F);
      return Mth.floor(f * 20.0F);
   }

   public ItemUseAnimation getUseAnimation(ItemStack itemStack) {
      return ItemUseAnimation.CROSSBOW;
   }

    ChargingSounds getChargingSounds(ItemStack itemStack) {
        return (ChargingSounds) EnchantmentHelper
                .pickHighestLevel(itemStack, EnchantmentEffectComponents.CROSSBOW_CHARGING_SOUNDS)
                .orElse(DEFAULT_SOUNDS);
    }

   private static float getPowerForTime(int i, ItemStack itemStack, LivingEntity livingEntity) {
      float f = (float)i / (float)getChargeDuration(itemStack, livingEntity);
      if (f > 1.0F) {
         f = 1.0F;
      }

      return f;
   }

   public boolean useOnRelease(ItemStack itemStack) {
      return itemStack.is(this);
   }

   public int getDefaultProjectileRange() {
      return 8;
   }


   public static enum ChargeType implements StringRepresentable {
      NONE("none"),
      ARROW("arrow"),
      ROCKET("rocket");

      public static final Codec<ChargeType> CODEC = StringRepresentable.fromEnum(ChargeType::values);
      private final String name;

      private ChargeType(final String string2) {
         this.name = string2;
      }

      public String getSerializedName() {
         return this.name;
      }
   }
}

