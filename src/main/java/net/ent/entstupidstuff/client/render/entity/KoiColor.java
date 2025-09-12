package net.ent.entstupidstuff.client.render.entity;

public enum KoiColor {
    WHITE("white"),
    RED("red"),
    ORANGE("orange"),
    YELLOW("yellow"),
    BLACK("black");

    private final String name;

    KoiColor(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}