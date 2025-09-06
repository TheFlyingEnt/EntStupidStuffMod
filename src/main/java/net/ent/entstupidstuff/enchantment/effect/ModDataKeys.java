package net.ent.entstupidstuff.enchantment.effect;

import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.player.PlayerEntity;

public class ModDataKeys {
    public static final TrackedData<Boolean> DOUBLE_JUMP_AVAILABLE =
        DataTracker.registerData(PlayerEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
}
