package net.ent.entstupidstuff.item.base.weapons;

import net.ent.entstupidstuff.item.base.combat.WeaponUpdatedItem;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.level.Level;

public class WeaponHalberdItem extends WeaponUpdatedItem {
    private static final int COOLDOWN_TICKS = 36;
    private static final double REACH = 5.0;
    private static final double BASE_ATTACK_DAMAGE = 6.0;
    private static double ATTACK_DAMAGE;

    public WeaponHalberdItem(ToolMaterial toolMaterial, Properties settings) {
        super(toolMaterial, settings.attributes(
            WeaponUpdatedItem.createAttributeModifiers(
                toolMaterial,
                BASE_ATTACK_DAMAGE + toolMaterial.attackDamageBonus(),
                -3.1f,
                2,
                1,
                0.1f
            )
        ));
        ATTACK_DAMAGE = BASE_ATTACK_DAMAGE + toolMaterial.attackDamageBonus();
    }

    /*@Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (target.hasVehicle() || target.hasPassengers()) {
            target.damage(attacker.getDamageSources().mobAttack(attacker), 3.0F);
        }
        return super.postHit(stack, target, attacker);
    }*/

    @Override
    public InteractionResult use(Level world, Player player, InteractionHand hand) {
        if (!world.isClientSide()) {
            player.getCooldowns().addCooldown(player.getMainHandItem(), player.isCreative() ? 3 : COOLDOWN_TICKS);

            

            var hit = ReachHelper.pickAttackTarget(world, player, REACH);
            if (hit instanceof LivingEntity le) {
                le.hurtServer((ServerLevel) world,player.damageSources().playerAttack(player), (float)ATTACK_DAMAGE);
                world.playSound(null, le.blockPosition(), SoundEvents.PLAYER_ATTACK_STRONG, SoundSource.PLAYERS, 1f, 0.9f);
                ((ServerLevel)world).sendParticles(ParticleTypes.SWEEP_ATTACK, le.getX(), le.getY() + 1.0, le.getZ(), 6, 0.3, 0.2, 0.3, 0.0);
            } else {
                world.playSound(null, player.blockPosition(), SoundEvents.PLAYER_ATTACK_SWEEP, SoundSource.PLAYERS, 0.8f, 1.0f);
            }
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public void postHurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        stack.hurtAndBreak(1, attacker, EquipmentSlot.MAINHAND);
    }
}

