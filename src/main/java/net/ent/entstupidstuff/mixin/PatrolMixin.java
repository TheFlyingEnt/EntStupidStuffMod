package net.ent.entstupidstuff.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.PatrollingMonster;
import net.minecraft.world.level.NaturalSpawner;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.PatrolSpawner;

@Mixin(PatrolSpawner.class)
public class PatrolMixin {
    
    @Inject(method = "spawnPatrolMember", at = @At("HEAD"), cancellable = true)
    private void spawnPillager(ServerLevel world, BlockPos pos, RandomSource random, boolean captain, CallbackInfoReturnable<Boolean> cir) {
        BlockState blockState = world.getBlockState(pos);
        
        if (!NaturalSpawner.isValidEmptySpawnBlock(world, pos, blockState, blockState.getFluidState(), EntityType.PILLAGER)) {
            cir.setReturnValue(false);
            return;
        }
        
        if (!PatrollingMonster.checkPatrollingMonsterSpawnRules(EntityType.PILLAGER, world, EntitySpawnReason.PATROL, pos, random)) {
            cir.setReturnValue(false);
            return;
        }
        
        // 50% chance for armored pillager
        boolean spawnArmored = random.nextFloat() >= 0.50f;
        
        PatrollingMonster patrolEntity;
        if (spawnArmored) {
            patrolEntity = net.ent.entstupidstuff.registry.EntityFactory.ARMORED_PILLAGER.create(world, EntitySpawnReason.PATROL);
        } else {
            patrolEntity = EntityType.PILLAGER.create(world, EntitySpawnReason.PATROL);
        }
        
        if (patrolEntity != null) {
            if (captain) {
                patrolEntity.setPatrolLeader(true);
                patrolEntity.findPatrolTarget();
            }
            
            patrolEntity.setPos(pos.getX(), pos.getY(), pos.getZ());
            patrolEntity.finalizeSpawn(world, world.getCurrentDifficultyAt(pos), EntitySpawnReason.PATROL, null);
            world.addFreshEntityWithPassengers(patrolEntity);
            cir.setReturnValue(true);
        } else {
            cir.setReturnValue(false);
        }
    }
}