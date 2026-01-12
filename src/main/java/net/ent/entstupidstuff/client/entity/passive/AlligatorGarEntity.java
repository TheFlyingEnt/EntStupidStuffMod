package net.ent.entstupidstuff.client.entity.passive;

import net.ent.entstupidstuff.item.ItemFactory;
import net.ent.entstupidstuff.sound.SoundFactory;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.AbstractFish;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class AlligatorGarEntity extends AbstractFish {
   public AlligatorGarEntity(EntityType<? extends AlligatorGarEntity> entityType, Level world) {
      super(entityType, world);
   }

   public ItemStack getBucketItemStack() {
      return new ItemStack(ItemFactory.callItem("alligator_gar_bucket"));
   }

   protected SoundEvent getAmbientSound() {
      return SoundFactory.ENTITY_ALLIGATOR_GAR_AMBIENT;
   }

   protected SoundEvent getDeathSound() {
      return SoundFactory.ENTITY_ALLIGATOR_GAR_DEATH;
   }

   protected SoundEvent getHurtSound(DamageSource source) {
      return SoundFactory.ENTITY_ALLIGATOR_GAR_HURT;
   }

   protected SoundEvent getFlopSound() {
      return SoundFactory.ENTITY_ALLIGATOR_GAR_FLOP;
   }
}
