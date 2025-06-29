package net.ent.entstupidstuff.event.callback;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.LivingEntity;

public interface AttackCallback {
    Event<AttackCallback> EVENT = EventFactory.createArrayBacked(AttackCallback.class,
            (listeners) -> (attacker, target) -> {
                for (AttackCallback listener : listeners) {
                    listener.onAttack(attacker, target);
                }
            });

    void onAttack(LivingEntity attacker, LivingEntity target);
}
