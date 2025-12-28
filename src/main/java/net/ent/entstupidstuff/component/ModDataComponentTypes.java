package net.ent.entstupidstuff.component;

import java.util.function.UnaryOperator;

import com.mojang.serialization.Codec;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.client.entity.passive.ButterflyEntity;
import net.minecraft.component.ComponentType;
import net.minecraft.entity.passive.AxolotlEntity;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModDataComponentTypes {

    public static final ComponentType<ButterflyEntity.Variant> BUTTERFLY_VARIANT = register(
        "butterfly_variant",
        builder -> builder
            .codec(ButterflyEntity.Variant.CODEC)
            .packetCodec(ButterflyEntity.Variant.PACKET_CODEC)
    );

    private static <T> ComponentType<T> register(String name, java.util.function.UnaryOperator<ComponentType.Builder<T>> builderOperator) {
        return Registry.register(
            Registries.DATA_COMPONENT_TYPE,
            Identifier.of(EntStupidStuff.MOD_ID, name),
            builderOperator.apply(ComponentType.builder()).build()
        );
    }

    /*public static final ComponentType<ButterflyEntity.Variant> BUTTERFLY_VARIANT = register(
		"butterfly/variant", builder -> builder.codec(ButterflyEntity.Variant.INDEX_CODEC).packetCodec(ButterflyEntity.Variant.PACKET_CODEC)
	);

    private static <T> ComponentType<T> register(String id, UnaryOperator<ComponentType.Builder<T>> builderOperator) {
		return Registry.register(Registries.DATA_COMPONENT_TYPE, id, ((ComponentType.Builder)builderOperator.apply(ComponentType.builder())).build());
	}*/

    public static void register() {
        // Call this method in your main mod class EntStupidStuff.java 
        // to ensure components are registered.
    }
    
}
