package net.ent.entstupidstuff.mixin;

import net.ent.entstupidstuff.event.callback.AttackCallbackAll;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class LivingEntityAttackMixin {

    @Inject(method = "damage", at = @At("HEAD"), cancellable = true)
    private void onDamage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> info) {
        if (source.getAttacker() instanceof LivingEntity attacker && (Object) this instanceof LivingEntity target) {
            World world = target.getWorld();
            Hand hand = attacker.getMainHandStack().isEmpty() ? Hand.OFF_HAND : Hand.MAIN_HAND;
            ActionResult result = AttackCallbackAll.EVENT.invoker().interact(attacker, world, hand, target, null);
            if (result != ActionResult.PASS) {
                info.cancel();
            }
        }
    }
}