package net.ent.entstupidstuff.client.entity.passive;

import net.ent.entstupidstuff.item.ItemFactory;
import net.ent.entstupidstuff.sound.SoundFactory;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.passive.SchoolingFishEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvent;
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
