package net.ent.entstupidstuff.enchantment;

import java.util.List;
import java.util.function.UnaryOperator;

import net.minecraft.component.ComponentType;
import net.minecraft.enchantment.effect.EnchantmentEntityEffect;
import net.minecraft.enchantment.effect.TargetedEnchantmentEffect;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class ModComponentTypes {

    public static final ComponentType<List<TargetedEnchantmentEffect<EnchantmentEntityEffect>>> POST_DEATH = register("post_death", builder -> builder.codec(TargetedEnchantmentEffect.createPostAttackCodec(EnchantmentEntityEffect.CODEC, LootContextTypes.ENCHANTED_DAMAGE).listOf()));

    private static <T> ComponentType<T> register(String id, UnaryOperator<ComponentType.Builder<T>> builderOperator) {
        return Registry.register(Registries.ENCHANTMENT_EFFECT_COMPONENT_TYPE, "entstupidstuff:" + id, (builderOperator.apply(ComponentType.builder())).build());
    }

    public static void onInitialize() {
        
     }

}
