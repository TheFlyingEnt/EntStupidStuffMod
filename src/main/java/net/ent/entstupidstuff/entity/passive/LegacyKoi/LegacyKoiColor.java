package net.ent.entstupidstuff.entity.passive.LegacyKoi;

public enum LegacyKoiColor {
    WHITE("white"),
    RED("red"),
    ORANGE("orange"),
    YELLOW("yellow"),
    BLACK("black");

    private final String name;

    LegacyKoiColor(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    
}