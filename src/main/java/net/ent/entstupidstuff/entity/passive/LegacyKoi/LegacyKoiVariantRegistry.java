package net.ent.entstupidstuff.entity.passive.LegacyKoi;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.util.math.random.Random;

public class LegacyKoiVariantRegistry {
    private static final List<LegacyKoiVariant> VARIANTS = new ArrayList<>();

    static {
        // Generate all legal combinations automatically
        for (LegacyKoiColor base : LegacyKoiColor.values()) {
            // --- NONE ---
            VARIANTS.add(new LegacyKoiVariant(base, LegacyKoiPattern.NONE, null, null));

            // --- PATTERN 1 ---
            for (LegacyKoiColor c1 : LegacyKoiColor.values()) {
                if (c1 != base) {
                    VARIANTS.add(new LegacyKoiVariant(base, LegacyKoiPattern.PATTERN_1, c1, null));
                }
            }

            // --- PATTERN 2 ---
            for (LegacyKoiColor c1 : LegacyKoiColor.values()) {
                if (c1 == base) continue;
                for (LegacyKoiColor c2 : LegacyKoiColor.values()) {
                    if (c2 == base || c2 == c1) continue;
                    VARIANTS.add(new LegacyKoiVariant(base, LegacyKoiPattern.PATTERN_2, c1, c2));
                }
            }
        }
    }

    public static LegacyKoiVariant getById(int id) {
        return VARIANTS.get(Math.floorMod(id, VARIANTS.size()));
    }

    public static int getId(LegacyKoiVariant variant) {
        return VARIANTS.indexOf(variant);
    }

    public static LegacyKoiVariant getRandom(Random random) {
        return VARIANTS.get(random.nextInt(VARIANTS.size()));
    }

    public static int size() {
        return VARIANTS.size();
    }
}