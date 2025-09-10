package net.ent.entstupidstuff.entity.passive;

import net.ent.entstupidstuff.item.ItemFactory;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.CodEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class FurTroutEntity extends CodEntity{

    public FurTroutEntity(EntityType<? extends CodEntity> entityType, World world) {
        super(entityType, world);
    }
    
    @Override
	public ItemStack getBucketItem() {
		return new ItemStack(ItemFactory.FUR_TROUT_BUCKET);
	}
    
}
