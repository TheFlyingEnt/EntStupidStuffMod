package net.ent.entstupidstuff.enchantment;

import java.util.List;
import java.util.function.UnaryOperator;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.enchantment.TargetedConditionalEffect;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

public class ModComponentTypes {

    public static final DataComponentType<List<TargetedConditionalEffect<EnchantmentEntityEffect>>> POST_DEATH = register("post_death", builder -> builder.persistent(TargetedConditionalEffect.codec(EnchantmentEntityEffect.CODEC, LootContextParamSets.ENCHANTED_DAMAGE).listOf()));

    private static <T> DataComponentType<T> register(String id, UnaryOperator<DataComponentType.Builder<T>> builderOperator) {
        return Registry.register(BuiltInRegistries.ENCHANTMENT_EFFECT_COMPONENT_TYPE, "entstupidstuff:" + id, (builderOperator.apply(DataComponentType.builder())).build());
    }

    public static void onInitialize() {
        
     }

}
