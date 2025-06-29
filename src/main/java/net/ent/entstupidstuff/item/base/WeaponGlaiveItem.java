package net.ent.entstupidstuff.item.base;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
// Apply massive dash attack damage - target.damage(new DamageSources((ServerWorld) world).create(ModDamageTypes.SLASH_DAMAGE), getAttackDamage() * DASH_DAMAGE_MULTIPLIER);


public class WeaponGlaiveItem extends WeaponUpdatedItem {

    private static final float ATTACK_REACH = 5.0f; // Increased melee range
    private static final int DASH_COOLDOWN_TICKS = 100; // 5-second cooldown
    private static final double DASH_SPEED = 3;//1.2; // Dash speed multiplier
    private static final float DASH_DAMAGE_MULTIPLIER = 2.0f; // Massive damage multiplier

    private static final double BASE_ATTACK_DAMAGE = 4.5;;
    private static double ATTACK_DAMAGE;


    public WeaponGlaiveItem(ToolMaterial toolMaterial, Settings settings) {
        super(toolMaterial, settings.attributeModifiers(
            WeaponUpdatedItem.createAttributeModifiers(
                toolMaterial, 
                BASE_ATTACK_DAMAGE + toolMaterial.getAttackDamage(), 
                -2.0f, 
                3, 
                0, 
                0
            )
        ));

        ATTACK_DAMAGE = BASE_ATTACK_DAMAGE + toolMaterial.getAttackDamage();
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {

        if (!world.isClient) {

            if (player.isCreative() == true) {
                player.getItemCooldownManager().set(this, 3);
            } else {
                player.getItemCooldownManager().set(this, DASH_COOLDOWN_TICKS);
            }

            if (player.isOnGround()) {
                Vec3d lookVec = player.getRotationVec(1.0F).normalize(); //Might at this as FeedBack for the #@Cannon
                Vec3d dashVelocity = new Vec3d(lookVec.x * DASH_SPEED, 0, lookVec.z * DASH_SPEED);

                player.addVelocity(dashVelocity.x, 0.1, dashVelocity.z);
                player.velocityModified = true;

                Vec3d startPos = player.getEyePos();
                Vec3d endPos = startPos.add(lookVec.multiply(ATTACK_REACH + 1));
                EntityHitResult hitResult = ProjectileUtil.getEntityCollision(
                    world, player, startPos, endPos, 
                    new Box(startPos, endPos).expand(1.0), 
                    e -> e instanceof LivingEntity && e != player
                );

                if (hitResult != null && hitResult.getEntity() instanceof LivingEntity target) {
                    target.damage(player.getDamageSources().playerAttack(player), (float) ATTACK_DAMAGE * DASH_DAMAGE_MULTIPLIER);
                    System.out.println("Dash Attack Worked");

                    // Knockback target
                    Vec3d knockback = lookVec.multiply(2.0);
                    target.addVelocity(knockback.x, 0.5, knockback.z);
                    target.velocityModified = true;

                    // Play attack sound
                    world.playSound(null, player.getBlockPos(), SoundEvents.ENTITY_PLAYER_ATTACK_STRONG, SoundCategory.PLAYERS, 1.0f, 1.0f);
                    world.playSound(null, player.getBlockPos(), SoundEvents.ENTITY_ENDER_DRAGON_FLAP, SoundCategory.PLAYERS, 1.0f, 1.2f);
                    // Play Swoop Sound Effect Instead.

                }
            }

            

        }

        return TypedActionResult.success(player.getStackInHand(hand));
        
    }
    
}
