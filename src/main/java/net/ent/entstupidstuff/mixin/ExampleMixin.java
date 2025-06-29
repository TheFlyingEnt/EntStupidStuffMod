package net.ent.entstupidstuff.mixin;

import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftServer.class)
public class ExampleMixin {
	@Inject(at = @At("HEAD"), method = "loadWorld")
	private void init(CallbackInfo info) {
		// This code is injected into the start of MinecraftServer.loadWorld()V
	}
}

//PlayerEntityMixin.java paste

//package net.ent.entstupidstuff.mixin;

/*import net.ent.entstupidstuff.api.weaponTrait.TwoHandTrait;
import net.ent.entstupidstuff.api.weaponTrait.BleedingTrait;
import net.ent.entstupidstuff.api.weaponTrait.BluntTrait;
import net.ent.entstupidstuff.api.IntTrait.IBleedingTrait;
import net.ent.entstupidstuff.api.IntTrait.IBluntTrait;
import net.ent.entstupidstuff.api.IntTrait.ITwoHandTrait;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import java.util.List;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import net.minecraft.entity.Entity;



/*@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin {

    /*@Inject(method = "attack", at = @At("HEAD"), cancellable = true)
    private void onAttack(Entity target, CallbackInfo info) {
        if (target instanceof LivingEntity) {
            PlayerEntity playerEntity = (PlayerEntity) (Object) this;
            LivingEntity livingTarget = (LivingEntity) target;
            World world = playerEntity.getEntityWorld();
            ItemStack mainHandStack = playerEntity.getMainHandStack();
            Item mainHandItem = mainHandStack.getItem();

            // Pre-Trait Damage:
            float baseDamage = (float) playerEntity.getAttributeValue(net.minecraft.entity.attribute.EntityAttributes.GENERIC_ATTACK_DAMAGE);

            // : Fix the Sweep Damage as All Weapon (Including Vanilla) does not have it.

            if (mainHandItem instanceof ITwoHandTrait && TwoHandTrait.isUsingTwoHands(playerEntity)) {
                baseDamage = TwoHandTrait.applyDamageReduction(playerEntity, livingTarget, baseDamage);
                TwoHandTrait.applyMiningFatigue(playerEntity);
            }

            if (mainHandItem instanceof IBluntTrait) {
                BluntTrait.applyBluntEffect(playerEntity, livingTarget);
                world.addParticle(ParticleTypes.CRIT, livingTarget.getX(), livingTarget.getY(), livingTarget.getZ(), 0.0D, 0.0D, 0.0D);
            }

            if (mainHandItem instanceof IBleedingTrait) {
                baseDamage = BleedingTrait.applyBleedingEffect(playerEntity, livingTarget, baseDamage);
            }

            livingTarget.damage(playerEntity.getDamageSources().playerAttack(playerEntity), baseDamage);

            // Cancel the attack cooldown and return success
            playerEntity.resetLastAttackedTicks();

            // Sweep attack logic
            if (mainHandItem == Items.NETHERITE_SWORD || mainHandItem == Items.DIAMOND_SWORD || 
                mainHandItem == Items.IRON_SWORD || mainHandItem == Items.STONE_SWORD || 
                mainHandItem == Items.WOODEN_SWORD) {
                
                float attackCooldown = playerEntity.getAttackCooldownProgress(0.5F);
                
                if (attackCooldown > 0.9F && playerEntity.isOnGround() && !playerEntity.isSprinting()) {
                    List<LivingEntity> entities = world.getEntitiesByClass(LivingEntity.class, 
                            new Box(target.getX() - 1.0D, target.getY() - 0.5D, target.getZ() - 1.0D, 
                                    target.getX() + 1.0D, target.getY() + 0.5D, target.getZ() + 1.0D), 
                            entity -> entity != playerEntity && entity != target && !entity.isTeammate(playerEntity));
                    
                    for (LivingEntity entity : entities) {
                        entity.takeKnockback(0.4F, MathHelper.sin(playerEntity.getYaw() * ((float)Math.PI / 180F)), -MathHelper.cos(playerEntity.getYaw() * ((float)Math.PI / 180F)));
                        entity.damage(playerEntity.getDamageSources().playerAttack(playerEntity), baseDamage * 0.75F);
                    }
                    
                    world.playSound(null, playerEntity.getX(), playerEntity.getY(), playerEntity.getZ(), SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP, SoundCategory.PLAYERS, 1.0F, 1.0F);
                    playerEntity.addExhaustion(1.0F);
                    playerEntity.spawnSweepAttackParticles();
                }
            }

            info.cancel();
        }
    }*/


    //Legacy

    /*@Inject(method = "getAttackReachScale", at = @At("HEAD"), cancellable = true)
    public void getAttackReachScale(CallbackInfoReturnable<Float> cir) {
        PlayerEntity player = (PlayerEntity) (Object) this;
        ItemStack mainHandItem = player.getMainHandStack();

        if (mainHandItem.getItem() instanceof LongSwordItem) {
            cir.setReturnValue(1.5F);  // Increase reach by 50%
        }
    }
}*/
