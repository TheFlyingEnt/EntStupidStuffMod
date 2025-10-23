package net.ent.entstupidstuff.enchantment.effect;

import java.util.List;

import com.mojang.serialization.MapCodec;

import net.minecraft.enchantment.EnchantmentEffectContext;
import net.minecraft.enchantment.effect.EnchantmentEntityEffect;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;

public class GravityEnchantmentEffect implements EnchantmentEntityEffect {

    public static final MapCodec<GravityEnchantmentEffect> CODEC = MapCodec.unit(GravityEnchantmentEffect::new);

    @Override
    public void apply(ServerWorld world, int level, EnchantmentEffectContext context, Entity user, Vec3d pos) {
        // Pull nearby living entities toward the user
        if (!(user instanceof LivingEntity player)) return;

        // Pull radius increases slightly with level
        double radius = 5.0 + level * 2.0;

        List<LivingEntity> targets = world.getEntitiesByClass(
            LivingEntity.class,
            user.getBoundingBox().expand(radius),
            e -> e != player
        );

        for (LivingEntity target : targets) {
            Vec3d direction = player.getEntityPos().subtract(target.getEntityPos()).normalize();
            double strength = 0.5 + 0.1 * level; // Pull multiplier

            target.addVelocity(direction.x * strength, 0.3 * strength, direction.z * strength);
            target.velocityModified = true;
        }
    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> getCodec() {
        return CODEC;
    }
    
}
