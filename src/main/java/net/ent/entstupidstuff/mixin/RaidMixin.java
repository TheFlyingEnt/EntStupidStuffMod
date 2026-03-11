package net.ent.entstupidstuff.mixin;
//net.ent.entstupidstuff.registry.EntityFactory

import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.raid.Raid;
import net.minecraft.world.entity.raid.Raider;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.block.entity.BannerPattern;
import net.minecraft.world.level.block.entity.BannerPatternLayers;
import net.minecraft.world.level.block.entity.BannerPatterns;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.ent.entstupidstuff.item.ItemFactory;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Raid.class)
public abstract class RaidMixin {
    
    @Shadow
    private RandomSource random;
    
    @Shadow
    private int groupsSpawned;

    @Shadow
    private int numGroups;
    
    @Shadow
    public abstract int getNumGroups(Difficulty difficulty);

    @Shadow
    public abstract void joinRaid(ServerLevel serverLevel, int i, Raider raider, BlockPos blockPos, boolean bl);
    
    @Unique
    private boolean entstupidstuff$shouldUseArmoredPillager = false;

    @Unique
    private boolean entstupidstuff$shouldUseArmoredVindicator= false;
    
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
            entstupidstuff$shouldUseArmoredVindicator = random.nextFloat() < 0.44f;
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
    
    /**
     * Replace EntityType.VINDICATOR with armored variant when flag is set
     */
    @Redirect(
        method = "spawnGroup",
        at = @At(
            value = "FIELD",
            target = "Lnet/minecraft/world/entity/EntityType;VINDICATOR:Lnet/minecraft/world/entity/EntityType;"
        )
    )
    private EntityType<? extends Raider> useArmoredVindicator() {
        if (entstupidstuff$shouldUseArmoredVindicator) {
            entstupidstuff$shouldUseArmoredVindicator = false; // Reset flag
            System.out.println("[ESS-Mixin] Spawned in Armored Vindicator in Raid");
            return net.ent.entstupidstuff.registry.EntityFactory.ARMORED_VINDICATOR;
        }
        return EntityType.VINDICATOR;
    }

    @Inject(
        method = "spawnGroup",
        at = @At("TAIL")
    )
    private void spawnRedstoneGolemOnFinalWave(ServerLevel serverLevel, BlockPos blockPos, CallbackInfo ci) {
        // Check if this is the final wave (last group spawned)
        if (this.groupsSpawned == this.numGroups) {
            System.out.println("[ESS-Mixin] Final wave detected! Spawning Redstone Golem...");
            
            // Create the Redstone Golem
            Raider redstoneGolem = net.ent.entstupidstuff.registry.EntityFactory.REDSTONE_GOLEM.create(serverLevel, EntitySpawnReason.EVENT);
            
            if (redstoneGolem != null) {
                // Join the raid with current wave number
                this.joinRaid(serverLevel, this.groupsSpawned, redstoneGolem, blockPos, false);
                System.out.println("[ESS-Mixin] Successfully spawned Redstone Golem in final raid wave!");
            } else {
                System.err.println("[ESS-Mixin] Failed to create Redstone Golem entity!");
            }
        }
    }

}
