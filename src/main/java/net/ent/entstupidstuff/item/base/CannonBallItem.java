package net.ent.entstupidstuff.item.base;

import org.jetbrains.annotations.Nullable;

import net.ent.entstupidstuff.entity.projectile.CannonBallEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ProjectileItem;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Position;
import net.minecraft.world.World;

public class CannonBallItem extends Item implements ProjectileItem{

    public CannonBallItem(Item.Settings settings) {
        super(settings);
    }

    @Override
    public ProjectileEntity createEntity(World world, Position pos, ItemStack stack, Direction direction) {
		CannonBallEntity arrowEntity = new CannonBallEntity(world, pos.getX(), pos.getY(), pos.getZ(), stack.copyWithCount(1), null);
        arrowEntity.pickupType = PersistentProjectileEntity.PickupPermission.ALLOWED;
		return arrowEntity; 
		//(world, stack.copyWithCount(1), pos.getX(), pos.getY(), pos.getZ(), true);
    }

	public PersistentProjectileEntity createArrow(World world, ItemStack stack, LivingEntity shooter, @Nullable ItemStack shotFrom) {
		return new CannonBallEntity(world, shooter, stack.copyWithCount(1), shotFrom);
	}




}
