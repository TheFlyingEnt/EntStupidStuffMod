package net.ent.entstupidstuff.component;

import com.mojang.serialization.Codec;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.client.entity.passive.BassEntity;
import net.ent.entstupidstuff.client.entity.passive.ButterflyEntity;
import net.ent.entstupidstuff.client.entity.passive.KoiVariant;
import net.ent.entstupidstuff.client.entity.passive.KoiVariantRegistry;
import net.ent.entstupidstuff.client.entity.passive.MahiMahiEntity;
import net.ent.entstupidstuff.client.entity.passive.PerchFishEntity;
import net.ent.entstupidstuff.client.entity.passive.ZebraFishEntity;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.resources.ResourceLocation;

public class ModDataComponentTypes {

    public static final DataComponentType<ButterflyEntity.Variant> BUTTERFLY_VARIANT = register(
        "butterfly_variant",
        builder -> builder
            .persistent(ButterflyEntity.Variant.CODEC)
            .networkSynchronized(ButterflyEntity.Variant.PACKET_CODEC)
    );

    public static final DataComponentType<ZebraFishEntity.Variant> ZEBRA_FISH_VARIANT = register(
        "zebra_fish_variant",
        builder -> builder
            .persistent(ZebraFishEntity.Variant.CODEC)
            .networkSynchronized(ZebraFishEntity.Variant.PACKET_CODEC)
    );

    public static final DataComponentType<PerchFishEntity.Variant> PERCH_FISH_VARIANT = register(
        "perch_fish_variant",
        builder -> builder
            .persistent(PerchFishEntity.Variant.CODEC)
            .networkSynchronized(PerchFishEntity.Variant.PACKET_CODEC)
    );

    public static final DataComponentType<BassEntity.Variant> BASS_FISH_VARIANT = register(
        "bass_fish_variant",
        builder -> builder
            .persistent(BassEntity.Variant.CODEC)
            .networkSynchronized(BassEntity.Variant.PACKET_CODEC)
    );

    public static final DataComponentType<KoiVariant> KOI_FISH_VARIANT = register(
        "koi_fish_variant",
        builder -> builder
            .persistent(KoiVariantRegistry.INDEX_CODEC)
            .networkSynchronized(KoiVariantRegistry.PACKET_CODEC)
    );

    public static final DataComponentType<MahiMahiEntity.Variant> MAHIMAHI_FISH_VARIANT = register(
        "mahimahi_fish_variant",
        builder -> builder
            .persistent(MahiMahiEntity.Variant.CODEC)
            .networkSynchronized(MahiMahiEntity.Variant.PACKET_CODEC)
    );

    public static final DataComponentType<Integer> LOADED_ARROWS = DataComponentType.<Integer>builder()
            .persistent(Codec.INT)
            .networkSynchronized(ByteBufCodecs.INT)
            .build();


    private static <T> DataComponentType<T> register(String name, java.util.function.UnaryOperator<DataComponentType.Builder<T>> builderOperator) {
        return Registry.register(
            BuiltInRegistries.DATA_COMPONENT_TYPE,
            ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, name),
            builderOperator.apply(DataComponentType.builder()).build()
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

        Registry.register(
            BuiltInRegistries.DATA_COMPONENT_TYPE,
            ResourceLocation.fromNamespaceAndPath("entstupidstuff", "loaded_arrows"),
            LOADED_ARROWS
        );
    }
    
}
