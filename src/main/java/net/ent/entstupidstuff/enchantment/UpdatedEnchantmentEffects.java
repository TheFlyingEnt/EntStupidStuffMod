package net.ent.entstupidstuff.enchantment;

import com.mojang.serialization.MapCodec;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.enchantment.effect.FrostbiteEnchantmentEffect;
import net.ent.entstupidstuff.enchantment.effect.GravityEnchantmentEffect;
import net.ent.entstupidstuff.enchantment.effect.LightningStrikerEnchantmentEffect;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;

public class UpdatedEnchantmentEffects {

    public static final MapCodec<? extends EnchantmentEntityEffect> LIGHTNING_STRIKER = registerEntityEffect("lightning_striker", LightningStrikerEnchantmentEffect.CODEC);
    public static final MapCodec<? extends EnchantmentEntityEffect> FROSTBITE = registerEntityEffect("frostbite", FrostbiteEnchantmentEffect.CODEC);
    //public static final MapCodec<? extends EnchantmentEntityEffect> BANEOFRAIDERS = registerEntityEffect("baneofraiders", LightningStrikerEnchantmentEffect.CODEC);
    public static final MapCodec<? extends EnchantmentEntityEffect> GRAVITY = registerEntityEffect("gravity", GravityEnchantmentEffect.CODEC);






    private static MapCodec<? extends EnchantmentEntityEffect> registerEntityEffect(String name, MapCodec<? extends EnchantmentEntityEffect> codec) {
        return Registry.register(BuiltInRegistries.ENCHANTMENT_ENTITY_EFFECT_TYPE, ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, name), codec);
    }

    public static void registerEnchantmentEffects() {
        EntStupidStuff.LOGGER.info("Registering Mod Enchantment Effects for " + EntStupidStuff.MOD_ID);
    }
    
}
