package net.ent.entstupidstuff.event.callback;

import org.jetbrains.annotations.Nullable;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;

public interface AttackCallbackAll {
	Event<AttackCallbackAll> EVENT = EventFactory.createArrayBacked(AttackCallbackAll.class,
			(listeners) -> (attacker, world, hand, entity, hitResult) -> {
				for (AttackCallbackAll event : listeners) {
					InteractionResult result = event.interact(attacker, world, hand, entity, hitResult);
					

					if (result != InteractionResult.PASS) {
						return result;
					}
				}

				return InteractionResult.PASS;
			}
	);

	InteractionResult interact(LivingEntity attacker, Level world, InteractionHand hand, Entity entity, @Nullable EntityHitResult hitResult);
}