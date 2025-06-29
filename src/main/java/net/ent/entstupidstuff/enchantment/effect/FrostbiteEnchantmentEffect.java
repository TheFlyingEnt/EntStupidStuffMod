package net.ent.entstupidstuff.enchantment.effect;

import com.mojang.serialization.MapCodec;

import net.minecraft.enchantment.EnchantmentEffectContext;
import net.minecraft.enchantment.effect.EnchantmentEntityEffect;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;

public record FrostbiteEnchantmentEffect() implements EnchantmentEntityEffect {

    public static final MapCodec<FrostbiteEnchantmentEffect> CODEC = MapCodec.unit(FrostbiteEnchantmentEffect::new);

    @Override
    public void apply(ServerWorld world, int level, EnchantmentEffectContext context, Entity user, Vec3d pos) {

        int duration = 100;
        int amplifier = 1;

        if (user instanceof LivingEntity victim){ //Will only be Frostbite I and II
            if (context.owner() != null){

                victim.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, duration * level, amplifier));
                victim.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, duration * level, amplifier));
                victim.setFrozenTicks(duration * level);
            }
        }
    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> getCodec() {
        return CODEC;
    }
    
}
