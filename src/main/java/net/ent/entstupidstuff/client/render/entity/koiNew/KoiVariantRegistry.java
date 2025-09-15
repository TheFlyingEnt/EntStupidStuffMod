package net.ent.entstupidstuff.client.render.entity.koiNew;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.util.math.random.Random;

public class KoiVariantRegistry {
    private static final List<KoiVariant> VARIANTS = new ArrayList<>();

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

    public static KoiVariant getById(int id) {
        return VARIANTS.get(Math.floorMod(id, VARIANTS.size()));
    }

    public static int getId(KoiVariant variant) {
        return VARIANTS.indexOf(variant);
    }

    public static KoiVariant getRandom(Random random) {
        return VARIANTS.get(random.nextInt(VARIANTS.size()));


        /*double roll = random.nextDouble();

        if (roll < 0.05) {
            // 5%: plain color koi (no secondary/main)
            List<KoiVariant> baseOnly = VARIANTS.stream()
                    .filter(v -> v.getSecondaryPattern() == null && v.getPatternKohaku() == null)
                    .toList();
            return baseOnly.get(random.nextInt(baseOnly.size()));

        } else if (roll < 0.65) {
            // 60%: base + secondary, no main
            List<KoiVariant> secondaryOnly = VARIANTS.stream()
                    .filter(v -> v.getSecondaryPattern() != null && v.getPatternKohaku() == null)
                    .toList();
            return secondaryOnly.get(random.nextInt(secondaryOnly.size()));

        } else {
            // 35%: base + secondary + main
            List<KoiVariant> fullPattern = VARIANTS.stream()
                    .filter(v -> v.getSecondaryPattern() != null && v.getPatternKohaku() != null)
                    .toList();

            // Within this group, bias specific patterns
            KoiVariant pick = fullPattern.get(random.nextInt(fullPattern.size()));

            // Rarity tweak: make Tancho / Doitsu less common
            if (pick.getPatternKohaku().getName().equals("Tancho") && random.nextDouble() < 0.7) {
                // 70% chance re-roll if Tancho picked
                return getRandom(random);
            }

            if (pick.getPatternKohaku().getName().equals("Doitsu") && random.nextDouble() < 0.5) {
                // 50% chance re-roll if Doitsu picked
                return getRandom(random);
            }

            return pick;
        }*/
    }

    public static int size() {
        return VARIANTS.size();
    }
    
}
