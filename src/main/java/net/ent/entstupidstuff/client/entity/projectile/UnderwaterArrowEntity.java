package net.ent.entstupidstuff.client.entity.projectile;

import org.jetbrains.annotations.Nullable;

import net.ent.entstupidstuff.item.ItemFactory;
import net.ent.entstupidstuff.registry.EntityFactory;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;

public class UnderwaterArrowEntity extends AbstractArrow /*ArrowEntity*/ {

    /*public UnderwaterArrowEntity(World world, LivingEntity owner, ItemStack stack, @Nullable ItemStack shotFrom) {
        super(world, owner, stack, shotFrom);
    }

    public UnderwaterArrowEntity(World world, double x, double y, double z, ItemStack stack, @Nullable ItemStack shotFrom) {
        super(world, x, y, z, stack, shotFrom);
    }*/

    public UnderwaterArrowEntity(Level world, double x, double y, double z, ItemStack stack, @Nullable ItemStack shotFrom) {
		super(EntityFactory.UARROW, x, y, z, world, stack, shotFrom);
	}

	public UnderwaterArrowEntity(Level world, LivingEntity owner, ItemStack stack, @Nullable ItemStack shotFrom) {
		super(EntityFactory.UARROW, owner, world, stack, shotFrom);
	}

    
    public UnderwaterArrowEntity(EntityType<? extends UnderwaterArrowEntity> entityType, Level world) {
        super(entityType, world);
    }

    
    @Override
    protected float getWaterInertia() {
        return 0.99F; // Adjust drag for underwater behavior
    }

    @Override
    protected void onHitEntity(EntityHitResult entityHitResult) {
        this.setDeltaMovement(this.getDeltaMovement().scale(-0.1)); // Adjust bounce behavior underwater
        super.onHitEntity(entityHitResult);
    }

    @Override
	protected ItemStack getDefaultPickupItem() {
		return new ItemStack(ItemFactory.PRISMERINE_ARROW);
	}

    //Testing Code

    @Override
	protected void setPickupItemStack(ItemStack stack) {
		super.setPickupItemStack(stack);
	}

    
}