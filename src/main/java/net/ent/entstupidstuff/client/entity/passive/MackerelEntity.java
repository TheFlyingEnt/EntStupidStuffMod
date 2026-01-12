package net.ent.entstupidstuff.client.entity.passive;

import net.ent.entstupidstuff.item.ItemFactory;
import net.ent.entstupidstuff.sound.SoundFactory;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.AbstractSchoolingFish;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class MackerelEntity extends AbstractSchoolingFish {
   public MackerelEntity(EntityType<? extends MackerelEntity> entityType, Level world) {
      super(entityType, world);
   }

   public ItemStack getBucketItemStack() {
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
