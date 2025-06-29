package net.ent.entstupidstuff.mixin;

import net.ent.entstupidstuff.api.enchantment.EntEnchantmentHelper;
import net.ent.entstupidstuff.enchantment.EnchantmentFactory;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@SuppressWarnings("unused")
@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity {

    protected PlayerEntityMixin(World world) {
        super(EntityType.PLAYER, world);
        newWorld = world;
    }

    public World newWorld;
    private boolean canWallJump = true;
    
    private int wallJumpCooldown = 0;
    private boolean hasWallJumped = false;

    private boolean canDoubleJump = true;
    private boolean hasDoubleJumped = false;
    private int doubleJumpCooldown = 0;

    @Inject(method = "jump", at = @At("HEAD"))
    private void onJump(CallbackInfo ci) {
        PlayerEntity player = (PlayerEntity) (Object) this;
        //System.out.println("Player tried to jump. Can Wall Jump: " + canWallJump);

        //System.out.println("hasWallJumpEnchantment: " + hasWallJumpEnchantment(player));
        //System.out.println("isNearWall: " + isNearWall(player));

        /*if (hasWallJumpEnchantment(player) && isNearWall(player)) { //Legacy Code
            performWallJump(player);
        }*/

        /*if (hasWallJumpEnchantment(player) && /*!player.isOnGround() && isNearWall(player) && canWallJump) {
            System.out.println("Wall Jump conditions met. Performing wall jump.");
            performWallJump(player);
            hasWallJumped = true;
            canWallJump = false;
            wallJumpCooldown = 10; // Adjust cooldown as needed
        }*/

        /*if (hasWallJumpEnchantment(player)) {
            if (player.isOnGround()) {
                // Regular jump
                hasDoubleJumped = false; // Reset double jump status when on ground
            } else if (/*!hasDoubleJumped &&*/ //canDoubleJump) {
                // Perform double jump if in air and near a wall/*
                /*if (isNearWall(player)) {
                    performWallJump(player);
                    hasDoubleJumped = true;
                    canDoubleJump = false;
                    doubleJumpCooldown = 10; // Adjust cooldown as needed
                }
            }
        }*/
    }

    @Inject(method = "tick", at = @At("HEAD"))
    private void onTick(CallbackInfo ci) {
        /*if (wallJumpCooldown > 0) {
            wallJumpCooldown--;
        } else {
            if (hasWallJumped && this.isOnGround()) {
                System.out.println("Player landed. Resetting wall jump.");
                canWallJump = true;
                hasWallJumped = false;
            }
        }*/
        if (doubleJumpCooldown > 0) {
            doubleJumpCooldown--;
        } else {
            if (this.isOnGround()) {
                canDoubleJump = true;
            }
        }
    }

    private boolean hasWallJumpEnchantment(PlayerEntity player) {
        ItemStack mainHandStack = player.getMainHandStack();
        //System.out.println("worldcleint: " + player.getWorld().isClient());
        //System.out.println("world2: " + player.getWorld());
        //System.out.println("Enchant: " + EnchantmentHelper.getLevel(EntEnchantmentHelper.getEnchantments(player.getWorld(), EnchantmentFactory.WALL_JUMP), mainHandStack));
        //System.out.println("Wall Jump Enchantment Level: " + EnchantmentHelper.getLevel(EntEnchantmentHelper.getEnchantments(player.getWorld(), EnchantmentFactory.WALL_JUMP), mainHandStack) + 1);
        return EnchantmentHelper.getLevel(EntEnchantmentHelper.getEnchantments(player.getWorld(), EnchantmentFactory.WALL_JUMP), mainHandStack) > -1;
    }

    private boolean isNearWall(PlayerEntity player) {

        //System.out.println("CODE IS RAN");
        /*BlockPos playerPos = player.getBlockPos();
        Box box = player.getBoundingBox().expand(0.1); // Slightly expand the bounding box to check for nearby walls
        World world = player.getWorld();

        // Check for blocks in each direction
        return world.getBlockState(playerPos.offset(Direction.NORTH)).isSolidBlock(world, playerPos.offset(Direction.NORTH))
            || world.getBlockState(playerPos.offset(Direction.SOUTH)).isSolidBlock(world, playerPos.offset(Direction.SOUTH))
            || world.getBlockState(playerPos.offset(Direction.EAST)).isSolidBlock(world, playerPos.offset(Direction.EAST))
            || world.getBlockState(playerPos.offset(Direction.WEST)).isSolidBlock(world, playerPos.offset(Direction.WEST));

            System.out.println("Player near wall: " + isNearWall);*/

            BlockPos playerPos = player.getBlockPos();
            World world = player.getWorld();
    
            boolean isNearWall = world.getBlockState(playerPos.offset(Direction.NORTH)).isSolidBlock(world, playerPos.offset(Direction.NORTH))
                    || world.getBlockState(playerPos.offset(Direction.SOUTH)).isSolidBlock(world, playerPos.offset(Direction.SOUTH))
                    || world.getBlockState(playerPos.offset(Direction.EAST)).isSolidBlock(world, playerPos.offset(Direction.EAST))
                    || world.getBlockState(playerPos.offset(Direction.WEST)).isSolidBlock(world, playerPos.offset(Direction.WEST));
    
            //System.out.println("Player near wall: " + isNearWall);
            return isNearWall;
    }

    private void performWallJump(PlayerEntity player) {
        Vec3d velocity = player.getVelocity();
        player.setVelocity(velocity.x, 1.0, velocity.z); // Adjust the Y velocity for the jump
        player.velocityModified = true;
    }


    //Test

    
}
