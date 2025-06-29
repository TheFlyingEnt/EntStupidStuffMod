package net.ent.entstupidstuff.enchantment;

import com.mojang.serialization.MapCodec;

import net.ent.entstupidstuff.enchantment.effects.BerserkEnchantmentEffect;
import net.ent.entstupidstuff.enchantment.effects.OldFrostbiteEnchantmentEffect;
import net.minecraft.enchantment.effect.EnchantmentEntityEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class ModCodec {

    public static MapCodec<OldFrostbiteEnchantmentEffect> FROSTBITE = register("frostbite", OldFrostbiteEnchantmentEffect.CODEC);
    public static MapCodec<BerserkEnchantmentEffect> BERSERK = register("berserk", BerserkEnchantmentEffect.CODEC);



    //public static MapCodec<FrostbiteEnchantmentEffect> BANEOFRAIDERS = register("baneofraiders", FrostbiteEnchantmentEffect.CODEC);
    //public static MapCodec<FrostbiteEnchantmentEffect> EXPERIENCE = register("experience", FrostbiteEnchantmentEffect.CODEC);
    //public static MapCodec<FrostbiteEnchantmentEffect> WALL_JUMP = register("wall_jump", FrostbiteEnchantmentEffect.CODEC);
    //public static MapCodec<FrostbiteEnchantmentEffect> OSMOSIS = register("osmosis", FrostbiteEnchantmentEffect.CODEC);
    //public static MapCodec<FrostbiteEnchantmentEffect> STAFE = register("stafe", FrostbiteEnchantmentEffect.CODEC);
    //public static MapCodec<FrostbiteEnchantmentEffect> MAGIC_PROTECTION = register("magic_protection", FrostbiteEnchantmentEffect.CODEC);
    //public static MapCodec<FrostbiteEnchantmentEffect> LIGHTEN = register("lighten", FrostbiteEnchantmentEffect.CODEC);
    //public static MapCodec<FrostbiteEnchantmentEffect> STALWART = register("stalwart", FrostbiteEnchantmentEffect.CODEC);
    //public static MapCodec<FrostbiteEnchantmentEffect> SHIELD_BASH = register("shild_bash", FrostbiteEnchantmentEffect.CODEC);
    //public static MapCodec<FrostbiteEnchantmentEffect> SCORCHING = register("scorching", FrostbiteEnchantmentEffect.CODEC);

    private static <T extends EnchantmentEntityEffect> MapCodec<T> register(String id, MapCodec<T> codec) {
        return Registry.register(Registries.ENCHANTMENT_ENTITY_EFFECT_TYPE, id, codec);
    }

    public static void onInitialize() {
       //Do Noting :)
    }



}
