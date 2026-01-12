package net.ent.entstupidstuff.item.base;

import org.jetbrains.annotations.Nullable;

import net.ent.entstupidstuff.client.entity.projectile.UnderwaterArrowEntity;
import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class PrismerineArrowItem extends ArrowItem/*Item implements ProjectileItem*/{
    
    public PrismerineArrowItem(Item.Properties settings) {
		super(settings);
	}

	@Override
	public AbstractArrow createArrow(Level world, ItemStack stack, LivingEntity shooter, @Nullable ItemStack shotFrom) {
		return new UnderwaterArrowEntity(world, shooter, stack.copyWithCount(1), shotFrom);
	}
	

	@Override
	public Projectile asProjectile(Level world, Position pos, ItemStack stack, Direction direction) {
		UnderwaterArrowEntity arrowEntity = new UnderwaterArrowEntity(world, pos.x(), pos.y(), pos.z(), stack.copyWithCount(1), null);
		arrowEntity.pickup = AbstractArrow.Pickup.ALLOWED;
		return arrowEntity;
	}

}
