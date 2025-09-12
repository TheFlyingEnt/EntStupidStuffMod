package net.ent.entstupidstuff.client.render.entity;

import java.util.Optional;

public class KoiVariant {
    
    private final KoiColor base;
    private final KoiPattern pattern;
    private final Optional<KoiColor> patternColor1;
    private final Optional<KoiColor> patternColor2;

    public KoiVariant(KoiColor base, KoiPattern pattern, KoiColor color1, KoiColor color2) {
        this.base = base;
        this.pattern = pattern;
        this.patternColor1 = Optional.ofNullable(color1);
        this.patternColor2 = Optional.ofNullable(color2);

        // Validation rules
        if (pattern == KoiPattern.NONE) {
            if (color1 != null || color2 != null)
                throw new IllegalArgumentException("Pattern NONE cannot have colors.");
        }
        else if (pattern == KoiPattern.PATTERN_1) {
            if (color1 == null || color1 == base)
                throw new IllegalArgumentException("Pattern1 requires 1 color different from base.");
            if (color2 != null)
                throw new IllegalArgumentException("Pattern1 cannot have 2 colors.");
        }
        else if (pattern == KoiPattern.PATTERN_2) {
            if (color1 == null || color2 == null)
                throw new IllegalArgumentException("Pattern2 requires 2 colors.");
            if (color1 == base || color2 == base)
                throw new IllegalArgumentException("Pattern2 colors must differ from base.");
            if (color1 == color2)
                throw new IllegalArgumentException("Pattern2 colors must be distinct.");
        }
    }

    public KoiColor getBase() { return base; }
    public KoiPattern getPattern() { return pattern; }
    public Optional<KoiColor> getPatternColor1() { return patternColor1; }
    public Optional<KoiColor> getPatternColor2() { return patternColor2; }
}
