package net.ent.entstupidstuff.client.render.entity.koiNew;

public enum KoiPatternMain {
    KOKAKU_TANCO("Tancho"),
    KOKAKU_DOITSU("Doitsu"),
    KOKAKU_INAZUMA("Inazuma"),
    KOKAKU_INZAUMA_2("Inazuma_2"),
    KOKAKU_INZAUMA_3("Inazuma_3"),
    KOKAKU_MARUTEN("Maruten"),
    KOKAKU_MEKABURI("Menkaburi"),
    KOKAKU_MEKABURI_2("Menkaburi_2");

    private final String name;

    KoiPatternMain(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
}