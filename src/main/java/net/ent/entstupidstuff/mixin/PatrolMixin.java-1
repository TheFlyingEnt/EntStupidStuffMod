package net.ent.entstupidstuff.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;

import net.ent.entstupidstuff.registry.EntityFactory;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.mob.PatrolEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.SpawnHelper;
import net.minecraft.world.spawner.PatrolSpawner;


//TODO: UnTested Code
@Mixin(PatrolSpawner.class)
public class PatrolMixin {

    @Inject(method = "spawnPillager", at = @At("HEAD"), cancellable = true)
    private boolean spawnPillager(ServerWorld world, BlockPos pos, Random random, boolean captain) {
		BlockState blockState = world.getBlockState(pos);
		if (!SpawnHelper.isClearForSpawn(world, pos, blockState, blockState.getFluidState(), EntityType.PILLAGER)) {
			return false;
		} else if (!PatrolEntity.canSpawn(EntityType.PILLAGER, world, SpawnReason.PATROL, pos, random)) {
			return false;
		} else {

            //Random Spawn
            Random randomSpawn = Random.create();
            float randomSpawnC = randomSpawn.nextFloat();

            if (randomSpawnC < 0.50) {
                PatrolEntity patrolEntity = EntityType.PILLAGER.create(world);
		        if (patrolEntity != null) {
				    if (captain) {
					    patrolEntity.setPatrolLeader(true);
					    patrolEntity.setRandomPatrolTarget();
				    }

				    patrolEntity.setPosition((double)pos.getX(), (double)pos.getY(), (double)pos.getZ());
				    patrolEntity.initialize(world, world.getLocalDifficulty(pos), SpawnReason.PATROL, null);
				    world.spawnEntityAndPassengers(patrolEntity);
				    return true;
			    } else {
				    return false;
			    }
            }
            else {
                PatrolEntity patrolEntity = EntityFactory.ARMORED_PILLAGER.create(world);
		        if (patrolEntity != null) {
				    if (captain) {
					    patrolEntity.setPatrolLeader(true);
					    patrolEntity.setRandomPatrolTarget();
				    }

				    patrolEntity.setPosition((double)pos.getX(), (double)pos.getY(), (double)pos.getZ());
				    patrolEntity.initialize(world, world.getLocalDifficulty(pos), SpawnReason.PATROL, null);
				    world.spawnEntityAndPassengers(patrolEntity);
				    return true;
			    } else {
				    return false;
			    }
            }
		}
    }

}
