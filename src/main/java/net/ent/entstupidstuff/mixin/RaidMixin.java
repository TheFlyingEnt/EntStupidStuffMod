package net.ent.entstupidstuff.mixin;

import net.ent.entstupidstuff.entity.mob.ArmoredPillagerEntity;
import net.ent.entstupidstuff.registry.EntityFactory;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.raid.RaiderEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
//import java.util.Random;
import net.minecraft.village.raid.Raid;
import net.minecraft.world.ServerWorldAccess;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Raid.class)
public class RaidMixin {

    @Inject(method = "addRaider", at = @At("HEAD"), cancellable = true)
    private void addRaider(int wave, RaiderEntity raider, BlockPos pos, boolean existing, CallbackInfo ci) {

        if (raider.getType() == EntityType.PILLAGER) {
            System.out.println("PILLAGER SPAWNED - Checking Replacement");
            Random random = Random.create();
            if (random.nextFloat() < 0.5) {
                System.out.println("SPAWNING ARMORED PILLAGER - Part 2");
                ArmoredPillagerEntity armoredPillager = new ArmoredPillagerEntity(EntityFactory.ARMORED_PILLAGER, ((ServerWorldAccess)raider.getWorld()).toServerWorld());
                armoredPillager.refreshPositionAndAngles(pos.getX(), pos.getY(), pos.getZ(), raider.getYaw(), raider.getPitch());
                raider.discard();
                
                if (raider.getWorld().spawnEntity(armoredPillager)) {
                    System.out.println("ARMORED PILLAGER SPAWNED SUCCESSFULLY at: " + pos.getX() + " " + pos.getY() + " " + pos.getZ());
                    
                    // Get the raid instance
                    Raid raid = (Raid) (Object) this;
                    
                    // Add the ArmoredPillagerEntity to the raid
                    raid.addRaider(wave, armoredPillager, pos, existing);
                    
                    System.out.println("ARMORED PILLAGER ADDED TO RAID");
                } else {
                    System.out.println("FAILED TO SPAWN ARMORED PILLAGER");
                }
                ci.cancel();  // Prevent the original raider from being added
            } else {
                System.out.println("SPAWNING PILLAGER - Part 3");
            }
        }


        /*if (raider.getType() == EntityType.PILLAGER) {

            Random random = Random.create();
            if (random.nextFloat() < 0.5) {
                System.out.println("SPAWNING ARMORED PILLAGER - Part 2");
                ArmoredPillagerEntity armoredPillager = new ArmoredPillagerEntity(EntityFactory.ARMORED_PILLAGER, ((ServerWorldAccess)raider.getWorld()).toServerWorld());
                armoredPillager.refreshPositionAndAngles(pos.getX(), pos.getY(), pos.getZ(), raider.getYaw(), raider.getPitch());
                raider.discard();
                if (raider.getWorld().spawnEntity(armoredPillager)) {
                    System.out.println("ARMORED PILLAGER SPAWNED SUCCESSFULLY at: " + pos.getX() + " " + pos.getY() + " " + pos.getZ());
                } else {
                    System.out.println("FAILED TO SPAWN ARMORED PILLAGER");
                }
                ci.cancel();  // Prevent the original raider from being added
            } else {
                System.out.println("SPAWNING PILLAGER - Part 3");
            }


            /*System.out.println("SPAWNING APILLAGER");
            Random random = Random.create();
            if (random.nextFloat() < 0.5) {
                System.out.println("SPAWNING APILLAGER - Part 2");
                ArmoredPillagerEntity armoredPillager = new ArmoredPillagerEntity(EntityFactory.ARMORED_PILLAGER, (/*(ServerWorldAccess)raider.getWorld()) .toServerWorld());
                System.out.println("Spawning at: " + raider.getX() + " " + raider.getY() + " " + raider.getZ() + " " + raider.getYaw() + " " + raider.getPitch());
                armoredPillager.refreshPositionAndAngles(raider.getX(), raider.getY(), raider.getZ(), raider.getYaw(), raider.getPitch());
                raider.discard();
                raider.getWorld().spawnEntity(armoredPillager);
                System.out.println(raider.getWorld().spawnEntity(armoredPillager));
                ci.cancel();  // Prevent the original raider from being added
            }
            else {
                System.out.println("SPAWNING PILLAGER - Part 3");
            }*/
        }
    }

    /*@Inject(method = "addRaider", at = @At("HEAD"), cancellable = true)
    private void addRaider(int wave, RaiderEntity raider, BlockPos pos, boolean existing, CallbackInfo ci) {
        if (raider.getType() == EntityType.PILLAGER) {
            Random random = Random.create();
            if (random.nextFloat() < 0.5) {
                ArmoredPillagerEntity armoredPillager = new ArmoredPillagerEntity(EntityFactory.ARMORED_PILLAGER, ((ServerWorldAccess)raider.getWorld()).toServerWorld());
                armoredPillager.refreshPositionAndAngles(raider.getX(), raider.getY(), raider.getZ(), raider.getYaw(), raider.getPitch());
                raider.discard();
                raider.getWorld().spawnEntity(armoredPillager);
                ci.cancel();  // Prevent the original raider from being added
            }
        }
    }*/

    /*@Inject(method = "addRaider", at = @At("HEAD"), cancellable = true)
    private void addRaider(int wave, RaiderEntity raider, ServerWorldAccess world, boolean existing, CallbackInfoReturnable<Boolean> cir) {
        if (raider.getType() == EntityType.PILLAGER) {
            Random random = Random.create();
            if (random.nextFloat() < 0.5) {
                ArmoredPillagerEntity armoredPillager = new ArmoredPillagerEntity(EntityFactory.ARMORED_PILLAGER, world.toServerWorld());
                armoredPillager.refreshPositionAndAngles(raider.getX(), raider.getY(), raider.getZ(), raider.getYaw(), raider.getPitch());
                raider.discard();
                world.spawnEntity(armoredPillager);
                cir.setReturnValue(true);
                return;
            }
        }
    }*/

    /*@Inject(method = "addRaider", at = @At("HEAD"), cancellable = true)
    private void addRaider(int wave, RaiderEntity raider, ServerWorldAccess world, boolean existing, CallbackInfoReturnable<Boolean> cir) {
        if (raider.getType() == EntityType.PILLAGER) {
            Random random = Random.create();
            if (random.nextFloat() < 0.5) {
                ArmoredPillagerEntity armoredPillager = new ArmoredPillagerEntity(EntityFactory.ARMORED_PILLAGER, world.toServerWorld());
                armoredPillager.refreshPositionAndAngles(raider.getX(), raider.getY(), raider.getZ(), raider.getYaw(), raider.getPitch());
                raider.discard();
                world.spawnEntity(armoredPillager);
                cir.setReturnValue(true);
                return;
            }
        }
    }*/

    


            //Random random = new Random();

/*Inject(method = "spawnNextWave", at = @At("HEAD")) // This is not Usefull now, but will be for the Redstone Golems
    private void injectSpawnNextWave(BlockPos pos, CallbackInfo ci) {
        Raid raid = (Raid) (Object) this;

        // Define the wave number and the local difficulty
        int wave = raid.getGroupsSpawned() + 1;

        // Define the number of custom entities to spawn
        int count = getArmoredPillagerCount(wave);

        // Spawn the custom entities
        for (int i = 0; i < count; i++) {
            RaiderEntity armoredPillager = new ArmoredPillagerEntity(EntityFactory.ARMORED_PILLAGER, raid.getWorld());
            raid.addRaider(wave, armoredPillager, pos, false);
        }
    }

    private int getArmoredPillagerCount(int wave) {
        // Define how many ArmoredPillagers to spawn based on the wave number
        // Example logic: 1 ArmoredPillager per wave
        int test = 7;
        return test;
    }*/
