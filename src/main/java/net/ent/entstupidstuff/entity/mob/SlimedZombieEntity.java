package net.ent.entstupidstuff.entity.mob;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.Difficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;

public class SlimedZombieEntity extends ZombieEntity{

    public SlimedZombieEntity(EntityType<? extends SlimedZombieEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
	protected ItemStack getSkull() {
		return ItemStack.EMPTY;
	}

    public static boolean canSpawnIn(EntityType<? extends HostileEntity> type, ServerWorldAccess world, SpawnReason spawnReason, BlockPos pos, Random random) {
		return world.getDifficulty() != Difficulty.PEACEFUL
			&& (SpawnReason.isTrialSpawner(spawnReason) || isSpawnDark(world, pos, random))
			&& canMobSpawn(type, world, spawnReason, pos, random) && pos.getY() < 0 && pos.getY() >= world.getBottomY();
	}
    
}
