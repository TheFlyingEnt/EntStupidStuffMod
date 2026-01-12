package net.ent.entstupidstuff.client.entity;

import java.util.Optional;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.Level;

public interface Jarredable {

	boolean isFromJar();

	void setFromJar(boolean fromBucket);

	void copyDataToStack(ItemStack stack);

	void copyDataFromNbt(CompoundTag nbt);

	ItemStack getJarItem();

	SoundEvent getJarFillSound();

	@Deprecated
	static void copyDataToStack(Mob entity, ItemStack stack) {
		stack.set(DataComponents.CUSTOM_NAME, entity.getCustomName());
		CustomData.update(DataComponents.BUCKET_ENTITY_DATA, stack, nbtCompound -> {
			if (entity.isNoAi()) {
				nbtCompound.putBoolean("NoAI", entity.isNoAi());
			}

			if (entity.isSilent()) {
				nbtCompound.putBoolean("Silent", entity.isSilent());
			}

			if (entity.isNoGravity()) {
				nbtCompound.putBoolean("NoGravity", entity.isNoGravity());
			}

			if (entity.hasGlowingTag()) {
				nbtCompound.putBoolean("Glowing", entity.hasGlowingTag());
			}

			if (entity.isInvulnerable()) {
				nbtCompound.putBoolean("Invulnerable", entity.isInvulnerable());
			}

			nbtCompound.putFloat("Health", entity.getHealth());
		});
	}

	@Deprecated
	static void copyDataFromNbt(Mob entity, CompoundTag nbt) {
		nbt.getBoolean("NoAI").ifPresent(entity::setNoAi);
		nbt.getBoolean("Silent").ifPresent(entity::setSilent);
		nbt.getBoolean("NoGravity").ifPresent(entity::setNoGravity);
		nbt.getBoolean("Glowing").ifPresent(entity::setGlowingTag);
		nbt.getBoolean("Invulnerable").ifPresent(entity::setInvulnerable);
		nbt.getFloat("Health").ifPresent(entity::setHealth);
	}

	static <T extends LivingEntity & Jarredable> Optional<InteractionResult> tryJar(Player player, InteractionHand hand, T entity) {
		ItemStack itemStack = player.getItemInHand(hand);
		if (itemStack.getItem() == Items.GLASS_BOTTLE && entity.isAlive()) {
			entity.playSound(entity.getJarFillSound(), 1.0F, 1.0F);
			ItemStack itemStack2 = entity.getJarItem();
			entity.copyDataToStack(itemStack2);
			ItemStack itemStack3 = ItemUtils.createFilledResult(itemStack, player, itemStack2, false);
			player.setItemInHand(hand, itemStack3);
			Level world = entity.level();
			if (!world.isClientSide()) {
				CriteriaTriggers.FILLED_BUCKET.trigger((ServerPlayer)player, itemStack2);
			}

			entity.discard();
			return Optional.of(InteractionResult.SUCCESS);
		} else {
			return Optional.empty();
		}
	}
}

