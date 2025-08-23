package net.ent.entstupidstuff.item.base.weapons;


import net.ent.entstupidstuff.effects.ModEffects;
import net.ent.entstupidstuff.item.base.WeaponUpdatedItem;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;

public class WeaponTwinDaggerItem extends SwordItem {
    private static final double BASE_ATTACK_DAMAGE = 1.5;
    private static double ATTACK_DAMAGE;

    public WeaponTwinDaggerItem(ToolMaterial toolMaterial, Settings settings) {
        super(toolMaterial, settings.attributeModifiers(
            WeaponUpdatedItem.createAttributeModifiers(
                toolMaterial,
                BASE_ATTACK_DAMAGE + toolMaterial.getAttackDamage(),
                -0.5f, // very fast
                0,
                0,
                0.0f
            )
        ));
        ATTACK_DAMAGE = BASE_ATTACK_DAMAGE + toolMaterial.getAttackDamage();
    }

    @Override
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
    }

    @Override
    public void postDamageEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        stack.damage(1, attacker, EquipmentSlot.MAINHAND);
    }
}

