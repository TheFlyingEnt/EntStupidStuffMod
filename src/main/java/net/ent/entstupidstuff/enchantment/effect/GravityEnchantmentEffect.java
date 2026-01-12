package net.ent.entstupidstuff.enchantment.effect;

import java.util.List;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.phys.Vec3;
import com.mojang.serialization.MapCodec;

public class GravityEnchantmentEffect implements EnchantmentEntityEffect {

    public static final MapCodec<GravityEnchantmentEffect> CODEC = MapCodec.unit(GravityEnchantmentEffect::new);

    @Override
    public void apply(ServerLevel world, int level, EnchantedItemInUse context, Entity user, Vec3 pos) {
        // Pull nearby living entities toward the user
        if (!(user instanceof LivingEntity player)) return;

        // Pull radius increases slightly with level
        double radius = 5.0 + level * 2.0;

        List<LivingEntity> targets = world.getEntitiesOfClass(
            LivingEntity.class,
            user.getBoundingBox().inflate(radius),
            e -> e != player
        );

        for (LivingEntity target : targets) {
            Vec3 direction = player.position().subtract(target.position()).normalize();
            double strength = 0.5 + 0.1 * level; // Pull multiplier

            target.push(direction.x * strength, 0.3 * strength, direction.z * strength);
            target.hurtMarked = true;
        }
    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> codec() {
        return CODEC;
    }
    
}
