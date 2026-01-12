package net.ent.entstupidstuff.client.entity.passive;

import java.util.ArrayList;
import java.util.List;
import java.util.function.IntFunction;

import com.mojang.serialization.Codec;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.util.Util;
import net.minecraft.util.function.ValueLists;
import net.minecraft.util.math.random.Random;

public final class KoiVariantRegistry {

    private static final List<KoiVariant> VARIANTS = new ArrayList<>();

    /* ------------------------------------------------------------
     *  VARIANT GENERATION
     * ------------------------------------------------------------ */

    static {
        for (KoiBaseColor base : KoiBaseColor.values()) {

            // Base only
            register(
                base.name().toLowerCase(),
                base,
                null,
                null
            );

            for (KoiPatternSecondary secondary : KoiPatternSecondary.values()) {

                // Base + secondary
                register(
                    base.name().toLowerCase() + "_" + secondary.name().toLowerCase(),
                    base,
                    secondary,
                    null
                );

                // White-only main patterns
                if (base == KoiBaseColor.WHITE) {
                    for (KoiPatternMain main : KoiPatternMain.values()) {
                        register(
                            base.name().toLowerCase() + "_" +
                            secondary.name().toLowerCase() + "_" +
                            main.name().toLowerCase(),
                            base,
                            secondary,
                            main
                        );
                    }
                }
            }
        }
    }

    private static void register(
        String id,
        KoiBaseColor base,
        KoiPatternSecondary secondary,
        KoiPatternMain main
    ) {
        VARIANTS.add(new KoiVariant(id, base, secondary, main));
    }

    /* ------------------------------------------------------------
     *  INDEXING
     * ------------------------------------------------------------ */

   public static final IntFunction<KoiVariant> INDEX_MAPPER = index -> {
    if (VARIANTS.isEmpty()) {
        throw new IllegalStateException("KoiVariantRegistry is empty");
    }
    return VARIANTS.get(Math.floorMod(index, VARIANTS.size()));
};

    public static int getIndex(KoiVariant variant) {
        return VARIANTS.indexOf(variant);
    }

    public static KoiVariant getByIndex(int index) {
        return VARIANTS.get(Math.floorMod(index, VARIANTS.size()));
    }

    /* ------------------------------------------------------------
     *  CODECS
     * ------------------------------------------------------------ */

    /** Used for entity data / NBT */
    public static final Codec<KoiVariant> INDEX_CODEC =
        Codec.INT.xmap(
            KoiVariantRegistry::getByIndex,
            KoiVariantRegistry::getIndex
        );

    /** Used for network sync */
    public static final PacketCodec<ByteBuf, KoiVariant> PACKET_CODEC =
        PacketCodecs.indexed(
            INDEX_MAPPER,
            KoiVariantRegistry::getIndex
        );

    /* ------------------------------------------------------------
     *  UTIL
     * ------------------------------------------------------------ */

    public static KoiVariant getRandom(Random random) {
        return Util.getRandom(VARIANTS, random);
    }

    public static int size() {
        return VARIANTS.size();
    }

    public static List<KoiVariant> values() {
        return VARIANTS;
    }
}
