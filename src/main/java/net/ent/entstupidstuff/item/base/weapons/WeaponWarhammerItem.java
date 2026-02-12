package net.ent.entstupidstuff.item.base.weapons;


import net.ent.entstupidstuff.item.base.combat.WeaponUpdatedItem;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ToolMaterial;

public class WeaponWarhammerItem extends WeaponUpdatedItem {
    private static final double BASE_ATTACK_DAMAGE = 8.0;
    private static double ATTACK_DAMAGE;

    public WeaponWarhammerItem(ToolMaterial toolMaterial, Properties settings) {
        super(toolMaterial, settings.attributes(
            WeaponUpdatedItem.createAttributeModifiers(
                toolMaterial,
                BASE_ATTACK_DAMAGE + toolMaterial.attackDamageBonus(),
                -3.5f,
                0,
                0,
                0.2f
            )
        ));
        ATTACK_DAMAGE = BASE_ATTACK_DAMAGE + toolMaterial.attackDamageBonus();
    }

    /*@Override
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
    }*/

    @Override
    public void postHurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        stack.hurtAndBreak(1, attacker, EquipmentSlot.MAINHAND);
    }
}
