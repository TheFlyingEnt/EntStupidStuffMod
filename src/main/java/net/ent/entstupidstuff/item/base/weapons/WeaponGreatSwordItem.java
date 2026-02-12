package net.ent.entstupidstuff.item.base.weapons;

import java.util.List;

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
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class WeaponGreatSwordItem extends WeaponUpdatedItem {
    private static final int COOLDOWN_TICKS = 60;
    private static final float CONE_ANGLE_DOT = 0.5f; // ~60° cone
    private static final double SWEEP_RANGE = 4.0;
    private static final float KNOCKBACK = 0.85f;
    private static final double BASE_ATTACK_DAMAGE = 7.0;
    private static double ATTACK_DAMAGE;

    public WeaponGreatSwordItem(ToolMaterial toolMaterial, Properties settings) {
        super(toolMaterial, settings.attributes(
            WeaponUpdatedItem.createAttributeModifiers(
                toolMaterial,
                BASE_ATTACK_DAMAGE + toolMaterial.attackDamageBonus(),
                -3.2f,        // slow swing
                1,            // reach bonus (mild)
                2,            // sweep level
                0.25f         // kb bonus
            )
        ));
        ATTACK_DAMAGE = BASE_ATTACK_DAMAGE + toolMaterial.attackDamageBonus();
    }

    @Override
    public InteractionResult use(Level world, Player player, InteractionHand hand) {
        if (!world.isClientSide()) {
            player.getCooldowns().addCooldown(player.getMainHandItem(), player.isCreative() ? 3 : COOLDOWN_TICKS);

            Vec3 eye = player.getEyePosition(1f);
            Vec3 look = player.getViewVector(1f).normalize();

            List<LivingEntity> targets = world.getEntitiesOfClass(
                LivingEntity.class,
                new AABB(player.blockPosition()).inflate(SWEEP_RANGE),
                e -> e != player && e.isAlive()
            );

            for (LivingEntity e : targets) {
                Vec3 dir = e.position().subtract(eye).normalize();
                if (dir.dot(look) >= CONE_ANGLE_DOT && player.distanceToSqr(e) <= SWEEP_RANGE * SWEEP_RANGE) {
                    e.hurtServer((ServerLevel) world, player.damageSources().playerAttack(player), (float)ATTACK_DAMAGE * 1.10f);
                    e.knockback(KNOCKBACK, player.getX() - e.getX(), player.getZ() - e.getZ());
                }
            }

            world.playSound(null, player.blockPosition(), SoundEvents.PLAYER_ATTACK_SWEEP, SoundSource.PLAYERS, 1f, 0.8f);
            ((ServerLevel)world).sendParticles(ParticleTypes.SWEEP_ATTACK, player.getX(), player.getY() + 1.0, player.getZ(), 10, 2.0, 0.5, 2.0, 0.0);
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public void postHurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        stack.hurtAndBreak(1, attacker, EquipmentSlot.MAINHAND);
    }

    
}

