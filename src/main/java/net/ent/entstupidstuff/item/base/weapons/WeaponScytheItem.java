package net.ent.entstupidstuff.item.base.weapons;

import java.util.List;

import net.ent.entstupidstuff.item.base.combat.WeaponUpdatedItem;
import net.minecraft.core.BlockPos;
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
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

public class WeaponScytheItem extends WeaponUpdatedItem {
    private static final int COOLDOWN_TICKS = 50;
    private static final double SWEEP_RANGE = 3.5;
    private static final double BASE_ATTACK_DAMAGE = 5.0;
    private static double ATTACK_DAMAGE;

    public WeaponScytheItem(ToolMaterial toolMaterial, Properties settings) {
        super(toolMaterial, settings.attributes(
            WeaponUpdatedItem.createAttributeModifiers(
                toolMaterial,
                BASE_ATTACK_DAMAGE + toolMaterial.attackDamageBonus(),
                -3.0f,
                1,
                2,
                0.0f
            )
        ));
        ATTACK_DAMAGE = BASE_ATTACK_DAMAGE + toolMaterial.attackDamageBonus();
    }

    /*@Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        // small extra sweep on hit
        World world = attacker.getWorld();
        if (!world.isClient()) {
            List<LivingEntity> ents = world.getEntitiesByClass(LivingEntity.class, target.getBoundingBox().expand(2.0), e -> e != attacker && e.isAlive());
            for (LivingEntity le : ents) {
                le.damage(attacker.getDamageSources().mobAttack(attacker), 2.0F);
            }
            world.playSound(null, target.getBlockPos(), SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP, SoundCategory.PLAYERS, 0.8f, 0.9f);
        }
        return super.postHit(stack, target, attacker);
    }*/

    @Override
    public InteractionResult use(Level world, Player player, InteractionHand hand) {
        if (!world.isClientSide()) {
            player.getCooldowns().addCooldown(player.getMainHandItem(), player.isCreative() ? 3 : COOLDOWN_TICKS);

            // Harvest mature crops around player in a small area
            BlockPos center = player.blockPosition();
            int r = 3;
            for (int dx = -r; dx <= r; dx++) {
                for (int dz = -r; dz <= r; dz++) {
                    BlockPos pos = center.offset(dx, 0, dz);
                    BlockState state = world.getBlockState(pos);
                    Block b = state.getBlock();
                    if (b instanceof CropBlock crop && crop.isMaxAge(state)) {
                        world.destroyBlock(pos, true, player);
                    }
                }
            }

            // visual sweep + light mob damage
            List<LivingEntity> ents = world.getEntitiesOfClass(LivingEntity.class, new AABB(center).inflate(SWEEP_RANGE), e -> e != player && e.isAlive());
            for (LivingEntity le : ents) {
                le.hurtServer((ServerLevel) world,player.damageSources().playerAttack(player), (float)ATTACK_DAMAGE * 0.75f);
            }

            world.playSound(null, center, SoundEvents.PLAYER_ATTACK_SWEEP, SoundSource.PLAYERS, 1f, 0.7f);
            ((ServerLevel)world).sendParticles(ParticleTypes.SWEEP_ATTACK, player.getX(), player.getY() + 1.0, player.getZ(), 12, 2.0, 0.5, 2.0, 0.0);
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public void postHurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        stack.hurtAndBreak(1, attacker, EquipmentSlot.MAINHAND);
    }
}
