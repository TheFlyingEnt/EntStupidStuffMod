package net.ent.entstupidstuff.entity.passive;

public enum KoiPatternSecondary {
    sanka_1("sanka", "1"),
    sanka_2("sanka", "2"),
    sanka_3("sanka", "3"),
    sanka_4("sanka", "4"),
    sanka_5("sanka", "5"),
    SHOWA_CLASSIC("showa", "Classic"),
    SHOWA_INAZUMA("showa", "Inazuma"),
    SHOWA_V("showa", "V-Shape"),
    SHOWA_Y("showa", "Y-Shape");

    private final String type;
    private final String name;

    KoiPatternSecondary(String type, String name) {
        this.type = type;
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }
    
}
