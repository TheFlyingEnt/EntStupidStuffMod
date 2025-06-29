package net.ent.entstupidstuff.entity.projectile;

import org.jetbrains.annotations.Nullable;

import net.ent.entstupidstuff.item.ItemFactory;
import net.ent.entstupidstuff.registry.EntityFactory;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;

public class UnderwaterArrowEntity extends PersistentProjectileEntity /*ArrowEntity*/ {

    /*public UnderwaterArrowEntity(World world, LivingEntity owner, ItemStack stack, @Nullable ItemStack shotFrom) {
        super(world, owner, stack, shotFrom);
    }

    public UnderwaterArrowEntity(World world, double x, double y, double z, ItemStack stack, @Nullable ItemStack shotFrom) {
        super(world, x, y, z, stack, shotFrom);
    }*/

    public UnderwaterArrowEntity(World world, double x, double y, double z, ItemStack stack, @Nullable ItemStack shotFrom) {
		super(EntityFactory.UARROW, x, y, z, world, stack, shotFrom);
	}

	public UnderwaterArrowEntity(World world, LivingEntity owner, ItemStack stack, @Nullable ItemStack shotFrom) {
		super(EntityFactory.UARROW, owner, world, stack, shotFrom);
	}

    
    public UnderwaterArrowEntity(EntityType<? extends UnderwaterArrowEntity> entityType, World world) {
        super(entityType, world);
    }

    
    @Override
    protected float getDragInWater() {
        return 0.99F; // Adjust drag for underwater behavior
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        this.setVelocity(this.getVelocity().multiply(-0.1)); // Adjust bounce behavior underwater
        super.onEntityHit(entityHitResult);
    }

    @Override
	protected ItemStack getDefaultItemStack() {
		return new ItemStack(ItemFactory.PRISMERINE_ARROW);
	}

    //Testing Code

    @Override
	protected void setStack(ItemStack stack) {
		super.setStack(stack);
	}

    
}