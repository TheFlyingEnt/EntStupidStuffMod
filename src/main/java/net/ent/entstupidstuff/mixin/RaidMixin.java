package net.ent.entstupidstuff.mixin;
//net.ent.entstupidstuff.registry.EntityFactory

import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.raid.Raid;
import net.minecraft.world.entity.raid.Raider;
import net.minecraft.world.entity.EntityType;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Raid.class)
public abstract class RaidMixin {
    
    @Shadow
    private RandomSource random;
    
    @Shadow
    private int groupsSpawned;
    
    @Shadow
    public abstract int getNumGroups(Difficulty difficulty);
    
    @Unique
    private boolean entstupidstuff$shouldUseArmoredPillager = false;
    
    /**
     * Intercept the check for Normal difficulty to determine if we should use armored pillager
     */
    @Redirect(
        method = "spawnGroup",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/world/entity/raid/Raid;getNumGroups(Lnet/minecraft/world/Difficulty;)I",
            ordinal = 0
        )
    )
    private int captureNormalDifficultyCheck(Raid instance, Difficulty difficulty) {
        int result = this.getNumGroups(difficulty);
        // Check if this is the pillager spawn check (i == getNumGroups(NORMAL))
        if (difficulty == Difficulty.NORMAL && (this.groupsSpawned + 1) == result) {
            // 30% chance to use armored pillager
            entstupidstuff$shouldUseArmoredPillager = random.nextFloat() < 0.44f;
        }
        return result;
    }
    
    /**
     * Replace EntityType.PILLAGER with armored variant when flag is set
     */
    @Redirect(
        method = "spawnGroup",
        at = @At(
            value = "FIELD",
            target = "Lnet/minecraft/world/entity/EntityType;PILLAGER:Lnet/minecraft/world/entity/EntityType;"
        )
    )
    private EntityType<? extends Raider> useArmoredPillager() {
        if (entstupidstuff$shouldUseArmoredPillager) {
            entstupidstuff$shouldUseArmoredPillager = false; // Reset flag
            System.out.println("[ESS-Mixin] Spawned in Armored Pillager in Raid");
            return net.ent.entstupidstuff.registry.EntityFactory.ARMORED_PILLAGER;
        }
        return EntityType.PILLAGER;
    }
}
