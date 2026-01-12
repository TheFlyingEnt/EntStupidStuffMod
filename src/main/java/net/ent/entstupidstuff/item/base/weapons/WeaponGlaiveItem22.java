package net.ent.entstupidstuff.item.base.weapons;


import net.ent.entstupidstuff.item.base.WeaponUpdatedItem;
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
import net.minecraft.world.phys.Vec3;

public class WeaponGlaiveItem22 extends WeaponUpdatedItem {
    private static final int COOLDOWN_TICKS = 30;
    private static final double REACH = 5.0;
    private static final double BASE_ATTACK_DAMAGE = 5.5;
    private static double ATTACK_DAMAGE;

    public WeaponGlaiveItem22(ToolMaterial toolMaterial, Properties settings) {
        super(toolMaterial, settings.attributes(
            WeaponUpdatedItem.createAttributeModifiers(
                toolMaterial,
                BASE_ATTACK_DAMAGE + toolMaterial.attackDamageBonus(),
                -2.8f,
                2,   // meaningful reach
                1,
                0.0f
            )
        ));
        ATTACK_DAMAGE = BASE_ATTACK_DAMAGE + toolMaterial.attackDamageBonus();
    }

    @Override
    public InteractionResult use(Level world, Player player, InteractionHand hand) {
        if (!world.isClientSide()) {
            player.getCooldowns().addCooldown(player.getMainHandItem(), player.isCreative() ? 3 : COOLDOWN_TICKS);

            // dash a bit
            Vec3 look = player.getViewVector(1f).normalize();
            player.push(look.x * 0.6, 0.15, look.z * 0.6);
            player.hurtMarked = true;

            // long poke
            var hit = ReachHelper.pickAttackTarget(world, player, REACH);
            if (hit instanceof LivingEntity le) {
                le.hurtServer((ServerLevel) world, player.damageSources().playerAttack(player), (float)ATTACK_DAMAGE * 1.0f);
                world.playSound(null, le.blockPosition(), SoundEvents.PLAYER_ATTACK_STRONG, SoundSource.PLAYERS, 1f, 1.2f);
                ((ServerLevel)world).sendParticles(ParticleTypes.CRIT, le.getX(), le.getY() + le.getBbHeight() * 0.5, le.getZ(), 8, 0.2, 0.2, 0.2, 0.0);
            } else {
                world.playSound(null, player.blockPosition(), SoundEvents.PLAYER_ATTACK_SWEEP, SoundSource.PLAYERS, 0.8f, 1.4f);
            }
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public void postHurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        stack.hurtAndBreak(1, attacker, EquipmentSlot.MAINHAND);
    }
}

