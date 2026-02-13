package net.ent.entstupidstuff.client.entity.passive;

import org.jetbrains.annotations.Nullable;

public class KoiVariant {

    //V2 of Koi Fish
    // So Varient code will hold 3 Values KOI(baseColor, secondaryPattern, mainPattern)

    private final KoiBaseColor base2; //For now, White, Red and Yellow
    private final @Nullable KoiPatternSecondary patternp2; //Small, Big or Clear (small_patter1-5, big_pattern_1-5, or N/A)
    private final @Nullable KoiPatternMain patternp1; //if (base2=white): Kohaku(kohaku_pattern1-5) or N/A

    public KoiVariant(KoiBaseColor color, KoiPatternSecondary patternp2, KoiPatternMain patternp1) {
        this.base2 = color;
        this.patternp2 = patternp2;
        this.patternp1 = patternp1;

        // Validation rules (Updated)
        if (color == KoiBaseColor.YELLOW) {
            //throw new IllegalArgumentException("Pattern1 requires 1 color different from base.");
        } else if (color == KoiBaseColor.RED) {

        }
    }

    public KoiBaseColor getBaseColor() { return base2; }
    public @Nullable KoiPatternSecondary getSecondaryPattern() { return patternp2; }
    public @Nullable KoiPatternMain getPatternKohaku() { return patternp1; }
    public @Nullable KoiPatternMain getMainPattern() { return patternp1; }
    
}

    /*
    public class KoiVariant implements StringRepresentable {

        private final KoiBaseColor baseColor;
        private final @Nullable KoiPatternSecondary secondaryPattern;
        private final @Nullable KoiPatternMain mainPattern;
        //private final String id;

        public KoiVariant(
            String id,
            KoiBaseColor baseColor,
            @Nullable KoiPatternSecondary secondaryPattern,
            @Nullable KoiPatternMain mainPattern
        ) {
            //this.id = id;
            this.baseColor = baseColor;
            this.secondaryPattern = secondaryPattern;
            this.mainPattern = mainPattern;
        }

        public KoiBaseColor getBaseColor() {
            return baseColor;
        }

        public @Nullable KoiPatternSecondary getSecondaryPattern() {
            return secondaryPattern;
        }

        public @Nullable KoiPatternMain getMainPattern() {
            return mainPattern;
        }

        /*@Override
        public String getSerializedName() {
            return this.id;
        }
    }
*/