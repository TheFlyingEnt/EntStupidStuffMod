package net.ent.entstupidstuff.component;

import net.ent.entstupidstuff.EntStupidStuff;
import net.ent.entstupidstuff.client.entity.passive.BassEntity;
import net.ent.entstupidstuff.client.entity.passive.ButterflyEntity;
import net.ent.entstupidstuff.client.entity.passive.KoiVariant;
import net.ent.entstupidstuff.client.entity.passive.KoiVariantRegistry;
import net.ent.entstupidstuff.client.entity.passive.MahiMahiEntity;
import net.ent.entstupidstuff.client.entity.passive.PerchFishEntity;
import net.ent.entstupidstuff.client.entity.passive.ZebraFishEntity;
import net.minecraft.component.ComponentType;
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

    public static final ComponentType<ZebraFishEntity.Variant> ZEBRA_FISH_VARIANT = register(
        "zebra_fish_variant",
        builder -> builder
            .codec(ZebraFishEntity.Variant.CODEC)
            .packetCodec(ZebraFishEntity.Variant.PACKET_CODEC)
    );

    public static final ComponentType<PerchFishEntity.Variant> PERCH_FISH_VARIANT = register(
        "perch_fish_variant",
        builder -> builder
            .codec(PerchFishEntity.Variant.CODEC)
            .packetCodec(PerchFishEntity.Variant.PACKET_CODEC)
    );

    public static final ComponentType<BassEntity.Variant> BASS_FISH_VARIANT = register(
        "bass_fish_variant",
        builder -> builder
            .codec(BassEntity.Variant.CODEC)
            .packetCodec(BassEntity.Variant.PACKET_CODEC)
    );

    public static final ComponentType<KoiVariant> KOI_FISH_VARIANT = register(
        "koi_fish_variant",
        builder -> builder
            .codec(KoiVariantRegistry.INDEX_CODEC)
            .packetCodec(KoiVariantRegistry.PACKET_CODEC)
    );

    public static final ComponentType<MahiMahiEntity.Variant> MAHIMAHI_FISH_VARIANT = register(
        "mahimahi_fish_variant",
        builder -> builder
            .codec(MahiMahiEntity.Variant.CODEC)
            .packetCodec(MahiMahiEntity.Variant.PACKET_CODEC)
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
