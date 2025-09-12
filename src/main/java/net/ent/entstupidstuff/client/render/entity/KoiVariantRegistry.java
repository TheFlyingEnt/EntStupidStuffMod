package net.ent.entstupidstuff.client.render.entity;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.util.math.random.Random;

public class KoiVariantRegistry {
    private static final List<KoiVariant> VARIANTS = new ArrayList<>();

    static {
        // Generate all legal combinations automatically
        for (KoiColor base : KoiColor.values()) {
            // --- NONE ---
            VARIANTS.add(new KoiVariant(base, KoiPattern.NONE, null, null));

            // --- PATTERN 1 ---
            for (KoiColor c1 : KoiColor.values()) {
                if (c1 != base) {
                    VARIANTS.add(new KoiVariant(base, KoiPattern.PATTERN_1, c1, null));
                }
            }

            // --- PATTERN 2 ---
            for (KoiColor c1 : KoiColor.values()) {
                if (c1 == base) continue;
                for (KoiColor c2 : KoiColor.values()) {
                    if (c2 == base || c2 == c1) continue;
                    VARIANTS.add(new KoiVariant(base, KoiPattern.PATTERN_2, c1, c2));
                }
            }
        }
    }

    public static KoiVariant getById(int id) {
        return VARIANTS.get(Math.floorMod(id, VARIANTS.size()));
    }

    public static int getId(KoiVariant variant) {
        return VARIANTS.indexOf(variant);
    }

    public static KoiVariant getRandom(Random random) {
        return VARIANTS.get(random.nextInt(VARIANTS.size()));
    }

    public static int size() {
        return VARIANTS.size();
    }
}