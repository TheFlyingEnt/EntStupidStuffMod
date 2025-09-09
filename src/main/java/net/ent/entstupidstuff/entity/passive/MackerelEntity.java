package net.ent.entstupidstuff.entity.passive;

import net.ent.entstupidstuff.item.ItemFactory;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.passive.SchoolingFishEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;

public class MackerelEntity extends SchoolingFishEntity {
   public MackerelEntity(EntityType<? extends MackerelEntity> entityType, World world) {
      super(entityType, world);
   }

   public ItemStack getBucketItem() {
      return new ItemStack(ItemFactory.callItem("mackerel_bucket"));
   }

   protected SoundEvent getAmbientSound() {
      return SoundEvents.ENTITY_COD_AMBIENT;
   }

   protected SoundEvent getDeathSound() {
      return SoundEvents.ENTITY_COD_DEATH;
   }

   protected SoundEvent getHurtSound(DamageSource source) {
      return SoundEvents.ENTITY_COD_HURT;
   }

   protected SoundEvent getFlopSound() {
      return SoundEvents.ENTITY_COD_FLOP;
   }
}
