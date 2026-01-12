package net.ent.entstupidstuff.enchantment.effect;

import com.mojang.serialization.MapCodec;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.phys.Vec3;

public record FrostbiteEnchantmentEffect() implements EnchantmentEntityEffect {

    public static final MapCodec<FrostbiteEnchantmentEffect> CODEC = MapCodec.unit(FrostbiteEnchantmentEffect::new);

    @Override
    public void apply(ServerLevel world, int level, EnchantedItemInUse context, Entity user, Vec3 pos) {

        int duration = 100;
        int amplifier = 1;

        if (user instanceof LivingEntity victim){ //Will only be Frostbite I and II
            if (context.owner() != null){

                victim.addEffect(new MobEffectInstance(MobEffects.SLOWNESS, duration * level, amplifier));
                victim.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, duration * level, amplifier));
                victim.setTicksFrozen(duration * level);
            }
        }
    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> codec() {
        return CODEC;
    }
    
}
