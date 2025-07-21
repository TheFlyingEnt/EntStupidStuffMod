package net.ent.entstupidstuff.entity.mob;

import net.ent.entstupidstuff.item.ItemFactory;
import net.ent.entstupidstuff.sound.SoundFactory;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.passive.FishEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvent;
import net.minecraft.world.World;

public class AlligatorGarEntity extends FishEntity {
   public AlligatorGarEntity(EntityType<? extends AlligatorGarEntity> entityType, World world) {
      super(entityType, world);
   }

   public ItemStack getBucketItem() {
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
