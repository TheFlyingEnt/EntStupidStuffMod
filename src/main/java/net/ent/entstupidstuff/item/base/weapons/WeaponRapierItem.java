package net.ent.entstupidstuff.item.base.weapons;

import net.ent.entstupidstuff.item.base.WeaponUpdatedItem;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ToolMaterial;

public class WeaponRapierItem extends WeaponUpdatedItem {
    private static final float CRIT_CHANCE = 0.25f;
    private static final double BASE_ATTACK_DAMAGE = 3.5; // fast, low base
    private static double ATTACK_DAMAGE;

    public WeaponRapierItem(ToolMaterial toolMaterial, Properties settings) {
        super(toolMaterial, settings.attributes(
            WeaponUpdatedItem.createAttributeModifiers(
                toolMaterial,
                BASE_ATTACK_DAMAGE + toolMaterial.attackDamageBonus(),
                -1.0f,  // quick
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
            // magic damage to simulate partial armor penetration
            target.damage(player.getDamageSources().magic(), 2.0F);
            if (Random.create().nextFloat() < CRIT_CHANCE) {
                target.damage(player.getDamageSources().playerAttack(player), (float)ATTACK_DAMAGE * 0.75f);
                if (!player.getWorld().isClient) {
                    ((ServerWorld)player.getWorld()).spawnParticles(ParticleTypes.CRIT, target.getX(), target.getY() + target.getHeight()*0.6, target.getZ(), 8, 0.2, 0.2, 0.2, 0.0);
                }
                player.getWorld().playSound(null, target.getBlockPos(), SoundEvents.ENTITY_PLAYER_ATTACK_CRIT, SoundCategory.PLAYERS, 1f, 1f);
            }
        }
        return super.postHit(stack, target, attacker);
    }*/

    @Override
    public void postHurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        stack.hurtAndBreak(1, attacker, EquipmentSlot.MAINHAND);
    }
}

