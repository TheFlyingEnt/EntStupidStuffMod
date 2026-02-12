package net.ent.entstupidstuff.block;

import net.minecraft.world.level.block.SkullBlock;

public enum ModSkullStype implements SkullBlock.Type {
    DROWNED("drowned"),
    ENDERMAN("enderman"),
    BLAZE("enderman"),
    BREEZE("breeze"),
    HUSK("husk"),
    STRAY("stray"),
    BOGGED("bogged"),
    ZOMBIE_LOBBER("zombie_lobber"),
    ZOMBIE_SCORCHED("zombie_scorched"),
    ZOMBIE_SLIMED("zombie_slimed"),
    ZOMBIE_FROSTBITTEN("zombie_frostbitten"),
    ZOMBIE_FROSTBITTEN_CHILLED("zombie_frostbitten_chilled"),
    ZOMBIE_FUNGAL("zombie_fungal"),
    SPOREBONE("sporebone"),
    SPOREPER("sporebone"),
    
    CORAL_SKELETON_BRAIN("coral_skeleton_brain"),
    CORAL_SKELETON_FIRE("coral_skeleton_fire"),    
    CORAL_SKELETON_HORN("coral_skeleton_horn"),
    CORAL_SKELETON_TUBE("coral_skeleton_tube"),
    CORAL_SKELETON_BUBBLE("coral_skeleton_bubble"),
    CORAL_SKELETON_UNUSED("coral_skeleton_unused"),

    SUNKEN_SKELTON_1("sunken_skeleton_1"),
    SUNKEN_SKELTON_2("sunken_skeleton_2"),
    SUNKEN_SKELTON_3("sunken_skeleton_3"),

    METAL_SKELETON_DEFAULT("metal_skeleton_default"),
    METAL_SKELETON_BLUE("metal_skeleton_blue"),
    METAL_SKELETON_RED("metal_skeleton_red"),
    
    PHANTOM_SKELETON_1("phantom_skeleton_1"),
    PHANTOM_SKELETON_2("phantom_skeleton_2"),
    PHANTOM_SKELETON_3("phantom_skeleton_3"),
    ANCIENT_DROWNED("ancient_drowned"),
    SOUL_SKELETON("soul_skeleton");


    private final String name;

    private ModSkullStype(final String name) {
        this.name = name;
    }

    @Override
    public String getSerializedName() {
        return this.name;
    }
}
