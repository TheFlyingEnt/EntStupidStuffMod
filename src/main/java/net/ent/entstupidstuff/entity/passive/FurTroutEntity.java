package net.ent.entstupidstuff.entity.passive;

import net.ent.entstupidstuff.item.ItemFactory;
import net.ent.entstupidstuff.sound.SoundFactory;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.passive.CodEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvent;
import net.minecraft.world.World;

public class FurTroutEntity extends CodEntity{

    public FurTroutEntity(EntityType<? extends CodEntity> entityType, World world) {
        super(entityType, world);
    }
    
    @Override
	public ItemStack getBucketItem() {
		return new ItemStack(ItemFactory.FUR_TROUT_BUCKET);
	}

    @Override
    protected SoundEvent getAmbientSound() {
      return SoundFactory.ENTITY_FUR_TROUT_AMBIENT;
    }

    @Override
    protected SoundEvent getDeathSound() {
      return SoundFactory.ENTITY_FUR_TROUT_DEATH;
   }

   @Override
   protected SoundEvent getHurtSound(DamageSource source) {
      return SoundFactory.ENTITY_FUR_TROUT_HURT;
   }

   @Override
   protected SoundEvent getFlopSound() {
      return SoundFactory.ENTITY_FUR_TROUT_FLOP;
   }
    
}
