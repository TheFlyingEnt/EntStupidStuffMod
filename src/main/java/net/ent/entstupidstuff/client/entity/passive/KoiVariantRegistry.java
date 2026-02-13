package net.ent.entstupidstuff.client.entity.passive;

import java.util.ArrayList;
import java.util.List;
import java.util.function.IntFunction;
import net.minecraft.Util;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.RandomSource;
import com.mojang.serialization.Codec;

import io.netty.buffer.ByteBuf;

public final class KoiVariantRegistry {

    private static final List<KoiVariant> VARIANTS = new ArrayList<>();

    //Value 85 is Basic Red Koi

    static {
        for (KoiBaseColor base : KoiBaseColor.values()) {
            //Register a Base Color
            VARIANTS.add(new KoiVariant(base, null, null));

            for (KoiPatternSecondary secondary : KoiPatternSecondary.values()) {
                //Generate for [Secondary] + Blank
                VARIANTS.add(new KoiVariant(base, secondary, null));

                if (base.equals(KoiBaseColor.WHITE)) {
                    //Generate for [Secondary] + [Main]
                    for (KoiPatternMain main : KoiPatternMain.values()) {
                        VARIANTS.add(new KoiVariant(base, secondary, main));
                    }

                }
                
            }
        }
    }


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


    /** Used for entity data / NBT */
    public static final Codec<KoiVariant> INDEX_CODEC = Codec.INT.xmap(
        KoiVariantRegistry::getByIndex,
        KoiVariantRegistry::getIndex
    );

    /** Used for network sync */
    public static final StreamCodec<ByteBuf, KoiVariant> PACKET_CODEC = ByteBufCodecs.idMapper(
        INDEX_MAPPER,
        KoiVariantRegistry::getIndex
    );

    public static KoiVariant getRandom(RandomSource random) {
        return Util.getRandom(VARIANTS, random);
    }

    public static int size() {
        return VARIANTS.size();
    }

    public static List<KoiVariant> values() {
        return VARIANTS;
    }
}
