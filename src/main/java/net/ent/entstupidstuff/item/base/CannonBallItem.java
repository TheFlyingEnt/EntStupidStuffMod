package net.ent.entstupidstuff.item.base;

import org.jetbrains.annotations.Nullable;

import net.ent.entstupidstuff.client.entity.projectile.CannonballEntity;
import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileItem;
import net.minecraft.world.level.Level;

public class CannonballItem extends Item implements ProjectileItem{

    public CannonballItem(Item.Properties settings) {
        super(settings);
    }

    @Override
    public Projectile asProjectile(Level world, Position pos, ItemStack stack, Direction direction) {
		CannonballEntity arrowEntity = new CannonballEntity(world, pos.x(), pos.y(), pos.z(), stack.copyWithCount(1), null);
        arrowEntity.pickup = AbstractArrow.Pickup.ALLOWED;
		return arrowEntity; 
		//(world, stack.copyWithCount(1), pos.getX(), pos.getY(), pos.getZ(), true);
    }

	public AbstractArrow createArrow(Level world, ItemStack stack, LivingEntity shooter, @Nullable ItemStack shotFrom) {
		return new CannonballEntity(world, shooter, stack.copyWithCount(1), shotFrom);
	}




}
