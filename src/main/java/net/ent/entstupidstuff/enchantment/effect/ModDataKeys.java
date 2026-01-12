package net.ent.entstupidstuff.enchantment.effect;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.player.Player;

public class ModDataKeys {
    public static final EntityDataAccessor<Boolean> DOUBLE_JUMP_AVAILABLE =
        SynchedEntityData.defineId(Player.class, EntityDataSerializers.BOOLEAN);
}
