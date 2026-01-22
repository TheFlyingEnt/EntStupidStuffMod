package net.ent.entstupidstuff.event.callback;

import com.mojang.serialization.Codec;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;

public class ModDataComponents { //For Testing - OLD
    
    public static final DataComponentType<Integer> LOADED_ARROWS = DataComponentType.<Integer>builder()
            .persistent(Codec.INT)
            .networkSynchronized(ByteBufCodecs.INT)
            .build();
    
    public static void register() {
        Registry.register(
            BuiltInRegistries.DATA_COMPONENT_TYPE,
            ResourceLocation.fromNamespaceAndPath("entstupidstuff", "loaded_arrows"),
            LOADED_ARROWS
        );
    }
}

