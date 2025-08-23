package net.ent.entstupidstuff.item.base.weapons;

import java.util.List;

import net.ent.entstupidstuff.item.base.WeaponUpdatedItem;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropBlock;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.*;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import net.minecraft.world.World;

public class WeaponScytheItem extends SwordItem {
    private static final int COOLDOWN_TICKS = 50;
    private static final double SWEEP_RANGE = 3.5;
    private static final double BASE_ATTACK_DAMAGE = 5.0;
    private static double ATTACK_DAMAGE;

    public WeaponScytheItem(ToolMaterial toolMaterial, Settings settings) {
        super(toolMaterial, settings.attributeModifiers(
            WeaponUpdatedItem.createAttributeModifiers(
                toolMaterial,
                BASE_ATTACK_DAMAGE + toolMaterial.getAttackDamage(),
                -3.0f,
                1,
                2,
                0.0f
            )
        ));
        ATTACK_DAMAGE = BASE_ATTACK_DAMAGE + toolMaterial.getAttackDamage();
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        // small extra sweep on hit
        World world = attacker.getWorld();
        if (!world.isClient) {
            List<LivingEntity> ents = world.getEntitiesByClass(LivingEntity.class, target.getBoundingBox().expand(2.0), e -> e != attacker && e.isAlive());
            for (LivingEntity le : ents) {
                le.damage(attacker.getDamageSources().mobAttack(attacker), 2.0F);
            }
            world.playSound(null, target.getBlockPos(), SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP, SoundCategory.PLAYERS, 0.8f, 0.9f);
        }
        return super.postHit(stack, target, attacker);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        if (!world.isClient) {
            player.getItemCooldownManager().set(this, player.isCreative() ? 3 : COOLDOWN_TICKS);

            // Harvest mature crops around player in a small area
            BlockPos center = player.getBlockPos();
            int r = 3;
            for (int dx = -r; dx <= r; dx++) {
                for (int dz = -r; dz <= r; dz++) {
                    BlockPos pos = center.add(dx, 0, dz);
                    BlockState state = world.getBlockState(pos);
                    Block b = state.getBlock();
                    if (b instanceof CropBlock crop && crop.isMature(state)) {
                        world.breakBlock(pos, true, player);
                    }
                }
            }

            // visual sweep + light mob damage
            List<LivingEntity> ents = world.getEntitiesByClass(LivingEntity.class, new Box(center).expand(SWEEP_RANGE), e -> e != player && e.isAlive());
            for (LivingEntity le : ents) {
                le.damage(player.getDamageSources().playerAttack(player), (float)ATTACK_DAMAGE * 0.75f);
            }

            world.playSound(null, center, SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP, SoundCategory.PLAYERS, 1f, 0.7f);
            ((ServerWorld)world).spawnParticles(ParticleTypes.SWEEP_ATTACK, player.getX(), player.getY() + 1.0, player.getZ(), 12, 2.0, 0.5, 2.0, 0.0);
        }
        return TypedActionResult.success(player.getStackInHand(hand));
    }

    @Override
    public void postDamageEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        stack.damage(1, attacker, EquipmentSlot.MAINHAND);
    }
}
