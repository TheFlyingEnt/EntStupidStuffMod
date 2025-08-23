package net.ent.entstupidstuff.item.base.weapons;

import java.util.List;

import net.ent.entstupidstuff.item.base.WeaponUpdatedItem;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class WeaponGreatSwordItem extends SwordItem {
    private static final int COOLDOWN_TICKS = 60;
    private static final float CONE_ANGLE_DOT = 0.5f; // ~60° cone
    private static final double SWEEP_RANGE = 4.0;
    private static final float KNOCKBACK = 0.85f;
    private static final double BASE_ATTACK_DAMAGE = 7.0;
    private static double ATTACK_DAMAGE;

    public WeaponGreatSwordItem(ToolMaterial toolMaterial, Settings settings) {
        super(toolMaterial, settings.attributeModifiers(
            WeaponUpdatedItem.createAttributeModifiers(
                toolMaterial,
                BASE_ATTACK_DAMAGE + toolMaterial.getAttackDamage(),
                -3.2f,        // slow swing
                1,            // reach bonus (mild)
                2,            // sweep level
                0.25f         // kb bonus
            )
        ));
        ATTACK_DAMAGE = BASE_ATTACK_DAMAGE + toolMaterial.getAttackDamage();
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        if (!world.isClient) {
            player.getItemCooldownManager().set(this, player.isCreative() ? 3 : COOLDOWN_TICKS);

            Vec3d eye = player.getCameraPosVec(1f);
            Vec3d look = player.getRotationVec(1f).normalize();

            List<LivingEntity> targets = world.getEntitiesByClass(
                LivingEntity.class,
                new Box(player.getBlockPos()).expand(SWEEP_RANGE),
                e -> e != player && e.isAlive()
            );

            for (LivingEntity e : targets) {
                Vec3d dir = e.getPos().subtract(eye).normalize();
                if (dir.dotProduct(look) >= CONE_ANGLE_DOT && player.squaredDistanceTo(e) <= SWEEP_RANGE * SWEEP_RANGE) {
                    e.damage(player.getDamageSources().playerAttack(player), (float)ATTACK_DAMAGE * 1.10f);
                    e.takeKnockback(KNOCKBACK, player.getX() - e.getX(), player.getZ() - e.getZ());
                }
            }

            world.playSound(null, player.getBlockPos(), SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP, SoundCategory.PLAYERS, 1f, 0.8f);
            ((ServerWorld)world).spawnParticles(ParticleTypes.SWEEP_ATTACK, player.getX(), player.getY() + 1.0, player.getZ(), 10, 2.0, 0.5, 2.0, 0.0);
        }
        return TypedActionResult.success(player.getStackInHand(hand));
    }

    @Override
    public void postDamageEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        stack.damage(1, attacker, EquipmentSlot.MAINHAND);
    }

    
}

