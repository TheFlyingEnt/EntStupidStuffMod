package net.ent.entstupidstuff.client.entity.passive;

public enum KoiBaseColor {
    WHITE("white"),
    RED("red"),
    YELLOW("yellow");

    private final String name;

    KoiBaseColor(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
