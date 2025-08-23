package net.ent.entstupidstuff.item.base.weapons;


import net.ent.entstupidstuff.item.base.WeaponUpdatedItem;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.sound.*;

public class WeaponWarhammerItem extends SwordItem {
    private static final double BASE_ATTACK_DAMAGE = 8.0;
    private static double ATTACK_DAMAGE;

    public WeaponWarhammerItem(ToolMaterial toolMaterial, Settings settings) {
        super(toolMaterial, settings.attributeModifiers(
            WeaponUpdatedItem.createAttributeModifiers(
                toolMaterial,
                BASE_ATTACK_DAMAGE + toolMaterial.getAttackDamage(),
                -3.5f,
                0,
                0,
                0.2f
            )
        ));
        ATTACK_DAMAGE = BASE_ATTACK_DAMAGE + toolMaterial.getAttackDamage();
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (attacker instanceof PlayerEntity player) {
            // crude "is armored" check: total armor > 0
            if (target.getArmor() > 0) {
                target.damage(player.getDamageSources().playerAttack(player), 2.0F);
            }
            target.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 60, 1)); // ~3s at 20tps? (60 ticks=3s) adjust if needed
            target.getWorld().playSound(null, target.getBlockPos(), SoundEvents.BLOCK_ANVIL_HIT, SoundCategory.PLAYERS, 0.6f, 1.2f);
        }
        return super.postHit(stack, target, attacker);
    }

    @Override
    public void postDamageEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        stack.damage(1, attacker, EquipmentSlot.MAINHAND);
    }
}
