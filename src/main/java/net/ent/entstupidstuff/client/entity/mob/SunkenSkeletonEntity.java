package net.ent.entstupidstuff.client.entity.mob;

import net.ent.entstupidstuff.client.entity.generic.GenericSkeletonBow;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;

public class SunkenSkeletonEntity extends GenericSkeletonBow {

    public enum Variant {
        Variant1,
        Variant2,
        Variant3
    }

    private final Variant variant;
    public SunkenSkeletonEntity(EntityType<? extends SunkenSkeletonEntity> entityType, Level world) {
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
