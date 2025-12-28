package net.ent.entstupidstuff.client.entity.passive;

import net.ent.entstupidstuff.item.ItemFactory;
import net.ent.entstupidstuff.sound.SoundFactory;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.passive.SchoolingFishEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvent;
import net.minecraft.world.World;

public class MackerelEntity extends SchoolingFishEntity {
   public MackerelEntity(EntityType<? extends MackerelEntity> entityType, World world) {
      super(entityType, world);
   }

   public ItemStack getBucketItem() {
      return new ItemStack(ItemFactory.callItem("mackerel_bucket"));
   }

   protected SoundEvent getAmbientSound() {
      return SoundFactory.ENTITY_MACKEREL_AMBIENT;
   }

   protected SoundEvent getDeathSound() {
      return SoundFactory.ENTITY_MACKEREL_DEATH;
   }

   protected SoundEvent getHurtSound(DamageSource source) {
      return SoundFactory.ENTITY_MACKEREL_HURT;
   }

   protected SoundEvent getFlopSound() {
      return SoundFactory.ENTITY_MACKEREL_FLOP;
   }
}
