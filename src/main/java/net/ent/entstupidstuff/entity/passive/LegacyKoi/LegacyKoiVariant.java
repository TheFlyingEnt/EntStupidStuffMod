package net.ent.entstupidstuff.entity.passive.LegacyKoi;

import java.util.Optional;

public class LegacyKoiVariant {

    //V1 of Koi Fish
    
    private final LegacyKoiColor base;
    private final LegacyKoiPattern pattern;
    private final Optional<LegacyKoiColor> patternColor1;
    private final Optional<LegacyKoiColor> patternColor2;

    public LegacyKoiVariant(LegacyKoiColor base, LegacyKoiPattern pattern, LegacyKoiColor color1, LegacyKoiColor color2) {
        this.base = base;
        this.pattern = pattern;
        this.patternColor1 = Optional.ofNullable(color1);
        this.patternColor2 = Optional.ofNullable(color2);

        // Validation rules
        if (pattern == LegacyKoiPattern.NONE) {
            if (color1 != null || color2 != null)
                throw new IllegalArgumentException("Pattern NONE cannot have colors.");
        }
        else if (pattern == LegacyKoiPattern.PATTERN_1) {
            if (color1 == null || color1 == base)
                throw new IllegalArgumentException("Pattern1 requires 1 color different from base.");
            if (color2 != null)
                throw new IllegalArgumentException("Pattern1 cannot have 2 colors.");
        }
        else if (pattern == LegacyKoiPattern.PATTERN_2) {
            if (color1 == null || color2 == null)
                throw new IllegalArgumentException("Pattern2 requires 2 colors.");
            if (color1 == base || color2 == base)
                throw new IllegalArgumentException("Pattern2 colors must differ from base.");
            if (color1 == color2)
                throw new IllegalArgumentException("Pattern2 colors must be distinct.");
        }
    }

    public LegacyKoiColor getBase() { return base; }
    public LegacyKoiPattern getPattern() { return pattern; }
    public Optional<LegacyKoiColor> getPatternColor1() { return patternColor1; }
    public Optional<LegacyKoiColor> getPatternColor2() { return patternColor2; }
}
