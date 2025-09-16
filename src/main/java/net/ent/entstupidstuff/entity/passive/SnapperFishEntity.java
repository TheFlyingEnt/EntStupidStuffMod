package net.ent.entstupidstuff.entity.passive;

import net.ent.entstupidstuff.item.ItemFactory;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.passive.SchoolingFishEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;

public class SnapperFishEntity extends SchoolingFishEntity{

    public SnapperFishEntity(EntityType<? extends SchoolingFishEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public ItemStack getBucketItem() {
        return new ItemStack(ItemFactory.SNAPPER_BUCKET);
    }

    @Override
    protected SoundEvent getAmbientSound() {
      return SoundEvents.ENTITY_COD_AMBIENT;
    }

    @Override
    protected SoundEvent getDeathSound() {
      return SoundEvents.ENTITY_COD_DEATH;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
      return SoundEvents.ENTITY_COD_HURT;
    }
    @Override
    protected SoundEvent getFlopSound() {
      return SoundEvents.ENTITY_COD_FLOP;
    }
    
}
