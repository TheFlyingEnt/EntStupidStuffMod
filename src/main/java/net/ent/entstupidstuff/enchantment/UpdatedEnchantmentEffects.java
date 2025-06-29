package net.ent.entstupidstuff.enchantment;

import com.mojang.serialization.MapCodec;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.enchantment.effect.FrostbiteEnchantmentEffect;
import net.ent.entstupidstuff.enchantment.effect.LightningStrikerEnchantmentEffect;
import net.minecraft.enchantment.effect.EnchantmentEntityEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class UpdatedEnchantmentEffects {

    public static final MapCodec<? extends EnchantmentEntityEffect> LIGHTNING_STRIKER = registerEntityEffect("lightning_striker", LightningStrikerEnchantmentEffect.CODEC);
    public static final MapCodec<? extends EnchantmentEntityEffect> FROSTBITE = registerEntityEffect("frostbite", FrostbiteEnchantmentEffect.CODEC);
    public static final MapCodec<? extends EnchantmentEntityEffect> BANEOFRAIDERS = registerEntityEffect("lightning_striker", LightningStrikerEnchantmentEffect.CODEC);





    private static MapCodec<? extends EnchantmentEntityEffect> registerEntityEffect(String name, MapCodec<? extends EnchantmentEntityEffect> codec) {
        return Registry.register(Registries.ENCHANTMENT_ENTITY_EFFECT_TYPE, Identifier.of(EntStupidStuff.MOD_ID, name), codec);
    }

    public static void registerEnchantmentEffects() {
        EntStupidStuff.LOGGER.info("Registering Mod Enchantment Effects for " + EntStupidStuff.MOD_ID);
    }
    
}
