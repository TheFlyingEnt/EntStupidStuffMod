package net.ent.entstupidstuff.enchantment.effects;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.enchantment.EnchantmentEffectContext;
import net.minecraft.enchantment.EnchantmentLevelBasedValue;
import net.minecraft.enchantment.effect.EnchantmentEntityEffect;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;


public record OldFrostbiteEnchantmentEffect(EnchantmentLevelBasedValue amount) implements EnchantmentEntityEffect {

    private static int duration = 100;
    private static int amplifier = 1;

    public static final MapCodec<OldFrostbiteEnchantmentEffect> CODEC = RecordCodecBuilder.mapCodec(instance ->
        instance.group(
            EnchantmentLevelBasedValue.CODEC.fieldOf("amount").forGetter(OldFrostbiteEnchantmentEffect::amount)
        ).apply(instance, OldFrostbiteEnchantmentEffect::new)
    );

    @Override
    public void apply(ServerWorld world, int level, EnchantmentEffectContext context, Entity user, Vec3d pos) {

        if (user instanceof LivingEntity victim){
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











/*
public class FrostbiteEnchantmentEffect implements EnchantmentEntityEffect {
    public static final MapCodec<FrostbiteEnchantmentEffect> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            Codec.INT.fieldOf("duration").forGetter(effect -> effect.duration),
            Codec.INT.fieldOf("amplifier").forGetter(effect -> effect.amplifier)
    ).apply(instance, FrostbiteEnchantmentEffect::new));

    private final int duration;
    private final int amplifier;

    public FrostbiteEnchantmentEffect(int duration, int amplifier) {
        this.duration = duration;
        this.amplifier = amplifier;
    }

    @Override
    public void apply(ServerWorld world, int level, EnchantmentEffectContext context, Entity user, Vec3d pos) {

        if (user instanceof LivingEntity) {
            //LivingEntity target = (LivingEntity) context.getEntity();
            ((LivingEntity) user).addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, duration * level, amplifier));
            ((LivingEntity) user).addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, duration * level, amplifier));
        }


        /*if (context.getEntity() instanceof LivingEntity) {
            LivingEntity target = (LivingEntity) context.getEntity();
            target.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, duration * level, amplifier));
            target.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, duration * level, amplifier));
        }
    }

    @Override
	public MapCodec<FrostbiteEnchantmentEffect> getCodec() {
		return CODEC;
	}

    public static void register(Registry<MapCodec<? extends EnchantmentEntityEffect>> registry) {
        Registry.register(registry, "frostbite", CODEC);
    }
}*/
