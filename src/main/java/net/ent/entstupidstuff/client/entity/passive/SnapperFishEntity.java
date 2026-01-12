package net.ent.entstupidstuff.client.entity.passive;

import net.ent.entstupidstuff.item.ItemFactory;
import net.ent.entstupidstuff.sound.SoundFactory;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.AbstractSchoolingFish;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class SnapperFishEntity extends AbstractSchoolingFish{

    public SnapperFishEntity(EntityType<? extends AbstractSchoolingFish> entityType, Level world) {
        super(entityType, world);
    }

    @Override
    public ItemStack getBucketItemStack() {
        return new ItemStack(ItemFactory.SNAPPER_BUCKET);
    }

    @Override
    protected SoundEvent getAmbientSound() {
      return SoundFactory.ENTITY_SNAPPER_AMBIENT;
    }

    @Override
    protected SoundEvent getDeathSound() {
      return SoundFactory.ENTITY_SNAPPER_DEATH;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
      return SoundFactory.ENTITY_SNAPPER_HURT;
    }
    @Override
    protected SoundEvent getFlopSound() {
      return SoundFactory.ENTITY_SNAPPER_FLOP;
    }
    
}
