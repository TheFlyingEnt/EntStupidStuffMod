package net.ent.entstupidstuff.block.blockentity;

import java.util.List;

import net.ent.entstupidstuff.effects.ModEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

public class MushroomAuraBlockEntity_2 extends BlockEntity {

    private static final int RANGE = 3;

    public MushroomAuraBlockEntity_2(BlockPos pos, BlockState state) {
        super(BlockEntityFactory.MUSHROOM_AURA_BLOCK_ENTITY_2, pos, state);
    }

    public static void tick(Level level, BlockPos pos, BlockState state, MushroomAuraBlockEntity_2 be) {

        AABB box = new AABB(pos).inflate(RANGE);

        List<Player> players = be.getLevel().getEntitiesOfClass(Player.class, box);

        for (Player player : players) {
            player.addEffect(new MobEffectInstance(
                ModEffects.HALLUC, // <-- CHANGE EFFECT HERE
                40,                 // duration (2 seconds)
                1,                  // amplifier
                true,               // ambient
                true               // show particles
            ));
        }
    }
}
