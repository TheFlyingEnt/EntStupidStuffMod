package net.ent.entstupidstuff.event.callback;

import org.jetbrains.annotations.Nullable;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.world.World;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.EntityHitResult;

public interface AttackCallbackAll {
	Event<AttackCallbackAll> EVENT = EventFactory.createArrayBacked(AttackCallbackAll.class,
			(listeners) -> (attacker, world, hand, entity, hitResult) -> {
				for (AttackCallbackAll event : listeners) {
					ActionResult result = event.interact(attacker, world, hand, entity, hitResult);
					

					if (result != ActionResult.PASS) {
						return result;
					}
				}

				return ActionResult.PASS;
			}
	);

	ActionResult interact(LivingEntity attacker, World world, Hand hand, Entity entity, @Nullable EntityHitResult hitResult);
}