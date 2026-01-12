package net.ent.entstupidstuff.client.entity.passive;

import org.jetbrains.annotations.Nullable;

import net.minecraft.util.StringIdentifiable;

public class KoiVariant implements StringIdentifiable {

    private final KoiBaseColor baseColor;
    private final @Nullable KoiPatternSecondary secondaryPattern;
    private final @Nullable KoiPatternMain mainPattern;
    private final String id;

    public KoiVariant(
        String id,
        KoiBaseColor baseColor,
        @Nullable KoiPatternSecondary secondaryPattern,
        @Nullable KoiPatternMain mainPattern
    ) {
        this.id = id;
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

    @Override
    public String asString() {
        return this.id;
    }
}