package net.ent.entstupidstuff.item.base;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;


public class WeaponGlaiveItem extends WeaponUpdatedItem {

    private static final float ATTACK_REACH = 5.0f; // Increased melee range
    private static final int DASH_COOLDOWN_TICKS = 100; // 5-second cooldown
    private static final double DASH_SPEED = 3;//1.2; // Dash speed multiplier
    private static final float DASH_DAMAGE_MULTIPLIER = 2.0f; // Massive damage multiplier

    private static final double BASE_ATTACK_DAMAGE = 4.5;;
    private static double ATTACK_DAMAGE;


    public WeaponGlaiveItem(ToolMaterial toolMaterial, Properties settings) {
        super(toolMaterial, settings.attributes(
            WeaponUpdatedItem.createAttributeModifiers(
                toolMaterial, 
                BASE_ATTACK_DAMAGE + toolMaterial.attackDamageBonus(), 
                -2.0f, 
                3, 
                0, 
                0
            )
        ));

        ATTACK_DAMAGE = BASE_ATTACK_DAMAGE + toolMaterial.attackDamageBonus();
    }

    @Override
    public InteractionResult use(Level world, Player player, InteractionHand hand) {

        if (!world.isClientSide()) {

            if (player.isCreative() == true) {
                player.getCooldowns().addCooldown(player.getMainHandItem(), 3);
            } else {
                player.getCooldowns().addCooldown(player.getMainHandItem(), DASH_COOLDOWN_TICKS);
            }

            if (player.onGround()) {
                Vec3 lookVec = player.getViewVector(1.0F).normalize(); //Might at this as FeedBack for the #@Cannon
                Vec3 dashVelocity = new Vec3(lookVec.x * DASH_SPEED, 0, lookVec.z * DASH_SPEED);

                player.push(dashVelocity.x, 0.1, dashVelocity.z);
                player.hurtMarked = true;

                Vec3 startPos = player.getEyePosition();
                Vec3 endPos = startPos.add(lookVec.scale(ATTACK_REACH + 1));
                /*EntityHitResult hitResult = ProjectileUtil.getEntityCollision(
                    world, player, startPos, endPos, 
                    new Box(startPos, endPos).expand(1.0), 
                    e -> e instanceof LivingEntity && e != player
                );

                if (hitResult != null && hitResult.getEntity() instanceof LivingEntity target) {
                    target.damage((ServerWorld) world, player.getDamageSources().playerAttack(player), (float) ATTACK_DAMAGE * DASH_DAMAGE_MULTIPLIER);
                    System.out.println("Dash Attack Worked");

                    // Knockback target
                    Vec3d knockback = lookVec.multiply(2.0);
                    target.addVelocity(knockback.x, 0.5, knockback.z);
                    target.velocityModified = true;

                    // Play attack sound
                    world.playSound(null, player.getBlockPos(), SoundEvents.ENTITY_PLAYER_ATTACK_STRONG, SoundCategory.PLAYERS, 1.0f, 1.0f);
                    world.playSound(null, player.getBlockPos(), SoundEvents.ENTITY_ENDER_DRAGON_FLAP, SoundCategory.PLAYERS, 1.0f, 1.2f);
                    // Play Swoop Sound Effect Instead.

                }*/
            }

            

        }

        return InteractionResult.SUCCESS;
        
    }
    
}
