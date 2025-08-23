package net.ent.entstupidstuff.item.base.weapons;


import net.ent.entstupidstuff.item.base.WeaponUpdatedItem;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.*;
import net.minecraft.util.*;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class WeaponGlaiveItem22 extends SwordItem {
    private static final int COOLDOWN_TICKS = 30;
    private static final double REACH = 5.0;
    private static final double BASE_ATTACK_DAMAGE = 5.5;
    private static double ATTACK_DAMAGE;

    public WeaponGlaiveItem22(ToolMaterial toolMaterial, Settings settings) {
        super(toolMaterial, settings.attributeModifiers(
            WeaponUpdatedItem.createAttributeModifiers(
                toolMaterial,
                BASE_ATTACK_DAMAGE + toolMaterial.getAttackDamage(),
                -2.8f,
                2,   // meaningful reach
                1,
                0.0f
            )
        ));
        ATTACK_DAMAGE = BASE_ATTACK_DAMAGE + toolMaterial.getAttackDamage();
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        if (!world.isClient) {
            player.getItemCooldownManager().set(this, player.isCreative() ? 3 : COOLDOWN_TICKS);

            // dash a bit
            Vec3d look = player.getRotationVec(1f).normalize();
            player.addVelocity(look.x * 0.6, 0.15, look.z * 0.6);
            player.velocityModified = true;

            // long poke
            var hit = ReachHelper.pickAttackTarget(world, player, REACH);
            if (hit instanceof LivingEntity le) {
                le.damage(player.getDamageSources().playerAttack(player), (float)ATTACK_DAMAGE * 1.0f);
                world.playSound(null, le.getBlockPos(), SoundEvents.ENTITY_PLAYER_ATTACK_STRONG, SoundCategory.PLAYERS, 1f, 1.2f);
                ((ServerWorld)world).spawnParticles(ParticleTypes.CRIT, le.getX(), le.getY() + le.getHeight() * 0.5, le.getZ(), 8, 0.2, 0.2, 0.2, 0.0);
            } else {
                world.playSound(null, player.getBlockPos(), SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP, SoundCategory.PLAYERS, 0.8f, 1.4f);
            }
        }
        return TypedActionResult.success(player.getStackInHand(hand));
    }

    @Override
    public void postDamageEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        stack.damage(1, attacker, EquipmentSlot.MAINHAND);
    }
}

