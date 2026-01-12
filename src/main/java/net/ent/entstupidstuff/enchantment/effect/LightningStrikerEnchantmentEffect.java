package net.ent.entstupidstuff.enchantment.effect;

import java.util.Random;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.phys.Vec3;
import com.mojang.serialization.MapCodec;

public record LightningStrikerEnchantmentEffect() implements EnchantmentEntityEffect {
    public static final MapCodec<LightningStrikerEnchantmentEffect> CODEC = MapCodec.unit(LightningStrikerEnchantmentEffect::new);
    private static final Random RANDOM = new Random();

    @Override
    public void apply(ServerLevel world, int level, EnchantedItemInUse context, Entity user, Vec3 pos) { 
        // Has a 30% chance to summon a lightning strike that damages nearby enemies @ level 2

        float chance = level * 0.15f;

        if (RANDOM.nextFloat() < chance)  {
            EntityType.LIGHTNING_BOLT.spawn(world, user.blockPosition(), EntitySpawnReason.TRIGGERED);
        }

    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> codec() {
        return CODEC;
    }
}