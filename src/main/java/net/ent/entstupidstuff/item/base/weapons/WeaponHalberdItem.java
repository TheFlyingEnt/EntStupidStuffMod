package net.ent.entstupidstuff.item.base.weapons;

import net.ent.entstupidstuff.item.base.WeaponUpdatedItem;
//import net.ent.entstupidstuff.util.ReachHelper;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.*;
import net.minecraft.util.*;
import net.minecraft.world.World;

public class WeaponHalberdItem extends SwordItem {
    private static final int COOLDOWN_TICKS = 36;
    private static final double REACH = 5.0;
    private static final double BASE_ATTACK_DAMAGE = 6.0;
    private static double ATTACK_DAMAGE;

    public WeaponHalberdItem(ToolMaterial toolMaterial, Settings settings) {
        super(toolMaterial, settings.attributeModifiers(
            WeaponUpdatedItem.createAttributeModifiers(
                toolMaterial,
                BASE_ATTACK_DAMAGE + toolMaterial.getAttackDamage(),
                -3.1f,
                2,
                1,
                0.1f
            )
        ));
        ATTACK_DAMAGE = BASE_ATTACK_DAMAGE + toolMaterial.getAttackDamage();
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (target.hasVehicle() || target.hasPassengers()) {
            target.damage(attacker.getDamageSources().mobAttack(attacker), 3.0F);
        }
        return super.postHit(stack, target, attacker);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        if (!world.isClient) {
            player.getItemCooldownManager().set(this, player.isCreative() ? 3 : COOLDOWN_TICKS);

            

            var hit = ReachHelper.pickAttackTarget(world, player, REACH);
            if (hit instanceof LivingEntity le) {
                le.damage(player.getDamageSources().playerAttack(player), (float)ATTACK_DAMAGE);
                world.playSound(null, le.getBlockPos(), SoundEvents.ENTITY_PLAYER_ATTACK_STRONG, SoundCategory.PLAYERS, 1f, 0.9f);
                ((ServerWorld)world).spawnParticles(ParticleTypes.SWEEP_ATTACK, le.getX(), le.getY() + 1.0, le.getZ(), 6, 0.3, 0.2, 0.3, 0.0);
            } else {
                world.playSound(null, player.getBlockPos(), SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP, SoundCategory.PLAYERS, 0.8f, 1.0f);
            }
        }
        return TypedActionResult.success(player.getStackInHand(hand));
    }

    @Override
    public void postDamageEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        stack.damage(1, attacker, EquipmentSlot.MAINHAND);
    }
}

