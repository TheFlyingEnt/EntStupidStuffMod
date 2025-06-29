package net.ent.entstupidstuff.item.base;

import net.ent.entstupidstuff.entity.passive.ButterflyEntity;
import net.ent.entstupidstuff.registry.EntityFactory;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ButterflyJarItem extends Item {
	public ButterflyJarItem(Settings settings) {
		super(settings);
	}

	@Override
	public ActionResult useOnBlock(ItemUsageContext context) {
		World world = context.getWorld();
		BlockPos pos = context.getBlockPos().offset(context.getSide());
		PlayerEntity player = context.getPlayer();
		ItemStack stack = context.getStack();

		if (!world.isClient) {
			// Spawn the butterfly
			ButterflyEntity butterfly = EntityFactory.BUTTERFLY.create(world);
			if (butterfly != null) {
				// Load NBT from the item
				//NbtCompound nbt = NbtComponent.get(DataComponentTypes.BUCKET_ENTITY_DATA, stack);

                if (stack.getName() != null) {
                    butterfly.setCustomName(stack.getName());
                    butterfly.setCustomNameVisible(true); // Optional
                }

                NbtComponent nbtComponent = stack.getOrDefault(DataComponentTypes.BUCKET_ENTITY_DATA, NbtComponent.DEFAULT);
                
				if (nbtComponent != null) {
					butterfly.copyDataFromNbt(nbtComponent.copyNbt());
				}

				// Set spawn position
				butterfly.refreshPositionAndAngles(pos, 0.0F, 0.0F);
				world.spawnEntity(butterfly);

				if (!player.getAbilities().creativeMode) {
					stack.decrement(1);
					player.giveItemStack(new ItemStack(Items.GLASS_BOTTLE));
				}

				world.playSound(null, pos, butterfly.getJarFillSound(), SoundCategory.NEUTRAL, 1.0F, 1.0F);
			}
		}

		return ActionResult.success(world.isClient);
	}
}
