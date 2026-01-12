package net.ent.entstupidstuff.client.entity.projectile;

import org.jetbrains.annotations.Nullable;

import net.ent.entstupidstuff.item.ItemFactory;
import net.ent.entstupidstuff.registry.EntityFactory;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;

public class AncientTridentEntity extends AbstractArrow {
   private static final EntityDataAccessor<Byte> LOYALTY;
   private static final EntityDataAccessor<Boolean> ENCHANTED;
   private boolean dealtDamage;
   public int returnTimer;

   public AncientTridentEntity(EntityType<? extends AncientTridentEntity> entityType, Level world) {
      super(entityType, world);
   }

   public AncientTridentEntity(Level world, LivingEntity owner, ItemStack stack) {
      super(EntityFactory.ANCIENT_TRIDENT, owner, world, stack, (ItemStack)null);
      this.entityData.set(LOYALTY, this.getLoyalty(stack));
      this.entityData.set(ENCHANTED, stack.hasFoil());
   }

   public AncientTridentEntity(Level world, double x, double y, double z, ItemStack stack) {
      super(EntityFactory.ANCIENT_TRIDENT, x, y, z, world, stack, stack);
      this.entityData.set(LOYALTY, this.getLoyalty(stack));
      this.entityData.set(ENCHANTED, stack.hasFoil());
   }

   protected void defineSynchedData(SynchedEntityData.Builder builder) {
      super.defineSynchedData(builder);
      builder.define(LOYALTY, (byte)0);
      builder.define(ENCHANTED, false);
   }

   public void tick() {
      if (this.inGroundTime > 4) {
         this.dealtDamage = true;
      }

      Entity entity = this.getOwner();
      int i = (Byte)this.entityData.get(LOYALTY);
      if (i > 0 && (this.dealtDamage || this.isNoPhysics()) && entity != null) {
         if (!this.isOwnerAlive()) {
            if (this.level() instanceof ServerLevel serverWorld && this.pickup == AbstractArrow.Pickup.ALLOWED) {
					this.spawnAtLocation(serverWorld, this.getPickupItem(), 0.1F);
				}

            this.discard();
         } else {
            this.setNoPhysics(true);
            Vec3 vec3d = entity.getEyePosition().subtract(this.position());
            this.setPosRaw(this.getX(), this.getY() + vec3d.y * 0.015 * (double)i, this.getZ());
            if (this.level().isClientSide()) {
               this.yOld = this.getY();
            }

            double d = 0.05 * (double)i;
            this.setDeltaMovement(this.getDeltaMovement().scale(0.95).add(vec3d.normalize().scale(d)));
            if (this.returnTimer == 0) {
               this.playSound(SoundEvents.TRIDENT_RETURN, 10.0F, 1.0F);
            }

            ++this.returnTimer;
         }
      }

      super.tick();
   }

   private boolean isOwnerAlive() {
      Entity entity = this.getOwner();
      if (entity != null && entity.isAlive()) {
         return !(entity instanceof ServerPlayer) || !entity.isSpectator();
      } else {
         return false;
      }
   }

   public boolean isEnchanted() {
      return (Boolean)this.entityData.get(ENCHANTED);
   }

   @Nullable
   protected EntityHitResult findHitEntity(Vec3 currentPosition, Vec3 nextPosition) {
      return this.dealtDamage ? null : super.findHitEntity(currentPosition, nextPosition);
   }

   protected void onHitEntity(EntityHitResult entityHitResult) {
      Entity entity = entityHitResult.getEntity();
      float f = 8.0F;
      Entity entity2 = this.getOwner();
      DamageSource damageSource = this.damageSources().trident(this, (Entity)(entity2 == null ? this : entity2));
      Level var7 = this.level();
      if (var7 instanceof ServerLevel serverWorld) {
         f = EnchantmentHelper.modifyDamage(serverWorld, this.getWeaponItem(), entity, damageSource, f);
      }

      this.dealtDamage = true;
      if (entity.hurtOrSimulate(damageSource, f)) {
         if (entity.getType() == EntityType.ENDERMAN) {
            return;
         }

         var7 = this.level();
         if (var7 instanceof ServerLevel) {
            ServerLevel serverWorld = (ServerLevel)var7;
            EnchantmentHelper.doPostAttackEffectsWithItemSource(serverWorld, entity, damageSource, this.getWeaponItem());
         }

         if (entity instanceof LivingEntity) {
            LivingEntity livingEntity = (LivingEntity)entity;
            this.doKnockback(livingEntity, damageSource);
            this.doPostHurtEffects(livingEntity);
         }
      }

      this.setDeltaMovement(this.getDeltaMovement().multiply(-0.01, -0.1, -0.01));
      this.playSound(SoundEvents.TRIDENT_HIT, 1.0F, 1.0F);
   }

   protected void hitBlockEnchantmentEffects(ServerLevel world, BlockHitResult blockHitResult, ItemStack weaponStack) {
      Vec3 vec3d = blockHitResult.getBlockPos().clampLocationWithin(blockHitResult.getLocation());
      Entity var6 = this.getOwner();
      LivingEntity var10002;
      if (var6 instanceof LivingEntity livingEntity) {
         var10002 = livingEntity;
      } else {
         var10002 = null;
      }

      EnchantmentHelper.onHitBlock(world, weaponStack, var10002, this, (EquipmentSlot)null, vec3d, world.getBlockState(blockHitResult.getBlockPos()), (item) -> {
         this.kill(world);
      });
   }

   public ItemStack getWeaponItem() {
      return this.getPickupItemStackOrigin();
   }

   protected boolean tryPickup(Player player) {
      return super.tryPickup(player) || this.isNoPhysics() && this.ownedBy(player) && player.getInventory().add(this.getPickupItem());
   }

   protected ItemStack getDefaultPickupItem() {
      return new ItemStack(ItemFactory.ANCIENT_TRIDENT);
   }

   protected SoundEvent getDefaultHitGroundSoundEvent() {
      return SoundEvents.TRIDENT_HIT_GROUND;
   }

   public void playerTouch(Player player) {
      if (this.ownedBy(player) || this.getOwner() == null) {
         super.playerTouch(player);
      }

   }

   @Override
   protected void readAdditionalSaveData(ValueInput view) {
      super.readAdditionalSaveData(view);
      this.dealtDamage = view.getBooleanOr("DealtDamage", false);
      this.entityData.set(LOYALTY, this.getLoyalty(this.getPickupItemStackOrigin()));
   }

   @Override
   public void addAdditionalSaveData(ValueOutput view) {
      super.addAdditionalSaveData(view);
      view.putBoolean("DealtDamage", this.dealtDamage);
   }

   private byte getLoyalty(ItemStack stack) {
      Level var3 = this.level();
      if (var3 instanceof ServerLevel serverWorld) {
         return (byte)Mth.clamp(EnchantmentHelper.getTridentReturnToOwnerAcceleration(serverWorld, stack, this), 0, 127);
      } else {
         return 0;
      }
   }

   public void tickDespawn() {
      int i = (Byte)this.entityData.get(LOYALTY);
      if (this.pickup != Pickup.ALLOWED || i <= 0) {
         super.tickDespawn();
      }

   }

   protected float getWaterInertia() {
      return 0.99F;
   }

   public boolean shouldRender(double cameraX, double cameraY, double cameraZ) {
      return true;
   }

   static {
      LOYALTY = SynchedEntityData.defineId(AncientTridentEntity.class, EntityDataSerializers.BYTE);
      ENCHANTED = SynchedEntityData.defineId(AncientTridentEntity.class, EntityDataSerializers.BOOLEAN);
   }
}
