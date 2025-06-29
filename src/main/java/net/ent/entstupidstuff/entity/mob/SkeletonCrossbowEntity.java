package net.ent.entstupidstuff.entity.mob;

import net.ent.entstupidstuff.entity.generic.GenericSkeletonCrossbow;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public class SkeletonCrossbowEntity extends GenericSkeletonCrossbow {

    public enum Variant {
        Variant1,
        Variant2,
        Variant3
    }

    private final Variant variant;

    public SkeletonCrossbowEntity(EntityType<? extends SkeletonCrossbowEntity> entityType, World world) {
        super(entityType, world);

        int varNum = 1 + random.nextInt(3);
        if (varNum == 1) {
            variant = Variant.Variant2;
        }
        else if (varNum == 2) {
            variant = Variant.Variant3;
        }
        else {
            variant = Variant.Variant1;
        }
    }

    public Variant getVariant() {
        return variant;
    }


}
