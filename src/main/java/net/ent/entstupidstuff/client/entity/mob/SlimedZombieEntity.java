package net.ent.entstupidstuff.client.entity.mob;

import net.ent.entstupidstuff.sound.SoundFactory;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;

public class SlimedZombieEntity extends Zombie{

    public SlimedZombieEntity(EntityType<? extends SlimedZombieEntity> entityType, Level world) {
        super(entityType, world);
    }

    public static boolean canSpawnIn(EntityType<? extends Monster> type, ServerLevelAccessor world, EntitySpawnReason spawnReason, BlockPos pos, RandomSource random) {
		return world.getDifficulty() != Difficulty.PEACEFUL
			&& (EntitySpawnReason.ignoresLightRequirements(spawnReason) || isDarkEnoughToSpawn(world, pos, random))
			&& checkMobSpawnRules(type, world, spawnReason, pos, random) && pos.getY() < 0 && pos.getY() >= world.getMinY();
	}

    

    //Sound Code:
    protected SoundEvent getAmbientSound() {
        return SoundFactory.ENTITY_SLIMED_AMBIENT;
    }

    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return SoundFactory.ENTITY_SLIMED_HURT;
    }

    protected SoundEvent getDeathSound() {
        return SoundFactory.ENTITY_SLIMED_DEATH;
    }

    protected SoundEvent getStepSound() {
        return SoundFactory.ENTITY_SLIMED_STEP;
    }
    
}
