package net.ent.entstupidstuff.item.base.weapons;


import net.ent.entstupidstuff.effects.ModEffects;
import net.ent.entstupidstuff.item.base.combat.WeaponUpdatedItem;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ToolMaterial;

public class WeaponTwinDaggerItem extends WeaponUpdatedItem {
    private static final double BASE_ATTACK_DAMAGE = 1.5;
    private static double ATTACK_DAMAGE;

    public WeaponTwinDaggerItem(ToolMaterial toolMaterial, Properties settings) {
        super(toolMaterial, settings.attributes(
            WeaponUpdatedItem.createAttributeModifiers(
                toolMaterial,
                BASE_ATTACK_DAMAGE + toolMaterial.attackDamageBonus(),
                -0.5f, // very fast
                0,
                0,
                0.0f
            )
        ));
        ATTACK_DAMAGE = BASE_ATTACK_DAMAGE + toolMaterial.attackDamageBonus();
    }

    /*@Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (attacker instanceof PlayerEntity player) {
            // dual-wield bonus (both items must be daggers)
            boolean mainIsDagger = player.getMainHandStack().getItem() instanceof WeaponTwinDaggerItem;
            boolean offIsDagger  = player.getOffHandStack().getItem() instanceof WeaponTwinDaggerItem;
            if (mainIsDagger && offIsDagger) {
                target.damage(player.getDamageSources().playerAttack(player), (float)ATTACK_DAMAGE * 0.75f);
                player.getWorld().playSound(null, target.getBlockPos(), SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP, SoundCategory.PLAYERS, 0.8f, 1.4f);
            }

            // apply bleeding
            target.addStatusEffect(new StatusEffectInstance(ModEffects.BLEEDING, 200, 1));

            

            //target.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 100, 1));

            //target.addStatusEffect(new StatusEffectInstance(ModEffects.BLEEDING_EFFECT, 100, 0)); // 5s
        }
        return super.postHit(stack, target, attacker);
    }*/

    @Override
    public void postHurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        stack.hurtAndBreak(1, attacker, EquipmentSlot.MAINHAND);
    }
}

