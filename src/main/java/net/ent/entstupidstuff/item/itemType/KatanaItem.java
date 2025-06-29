package net.ent.entstupidstuff.item.itemType;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import net.ent.entstupidstuff.api.IntTrait.ITrait;
import net.ent.entstupidstuff.item.base.WeaponItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class KatanaItem  extends WeaponItem implements ITrait{

    public KatanaItem(ToolMaterial toolMaterial, Settings settings) {
        super(toolMaterial, settings.attributeModifiers(WeaponItem.createAttributeModifiers(toolMaterial, (6.5 /*5.5*/)  + toolMaterial.getAttackDamage(), -3.4f, 1, 0, 3)));
    }

    @Override
    public void appendTooltip(ItemStack itemStack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        //tooltip.add(Text.translatable("item.entstupidstuff.double_hand.tooltip").formatted(Formatting.GRAY));
        //tooltip.add(Text.translatable("item.entstupidstuff.blunt.tooltip").formatted(Formatting.GRAY));
    }

    private static final int CHARGE_DURATION = 3 * 20; // Charge lasts for 10 ticks (0.5 sec)
    private static final double CHARGE_SPEED = 1.2; // Speed multiplier
    private static final HashMap<UUID, Long> CHARGING_PLAYERS = new HashMap<>(); // Store charge state

    /*@Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity player, LivingEntity target, Hand hand) {
        if (!player.getWorld().isClient) { // Server-side only
            if (!isCharging(player)) {
                startCharging(player);
                //target.damage(player.getDamageSources().playerAttack(player), 1 * 2); // Double damage
                checkForCollision(player, stack);
                return ActionResult.SUCCESS; // Indicate successful activation
            }
        }
        return super.useOnEntity(stack, player, target, hand);
    }*/

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        if (!world.isClient && !isCharging(player)) {
            startCharging(player);
            checkForCollision(player);
            return TypedActionResult.success(player.getStackInHand(hand));
        }
        return TypedActionResult.fail(player.getStackInHand(hand));
    }

    /*@Override
    public void inventoryTick(ItemStack stack, World world, LivingEntity entity, int slot, boolean selected) {
        if (!world.isClient && entity instanceof PlayerEntity player && isCharging(player)) {
            checkForCollision(player, stack);
        }
    }*/

    private boolean checkForCollision(PlayerEntity player) {
        Box boundingBox = player.getBoundingBox().expand(1); // Slightly larger hitbox
        List<LivingEntity> entities = player.getWorld().getEntitiesByClass(LivingEntity.class, boundingBox, e -> e != player);

        for (LivingEntity target : entities) {
            if (target.isAlive()) {
                target.damage(player.getDamageSources().playerAttack(player), 10 * 2); // Double damage
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (attacker instanceof PlayerEntity player) {
            if (isCharging(player)) {
                target.damage(player.getDamageSources().playerAttack(player), 1 * 2); // Double damage
                stopCharging(player);
            }
        }
        return super.postHit(stack, target, attacker);
    }

    public static void startCharging(PlayerEntity player) {
        CHARGING_PLAYERS.put(player.getUuid(), player.getWorld().getTime() + CHARGE_DURATION);
        Vec3d lookDirection = player.getRotationVec(1.0f).multiply(CHARGE_SPEED);
        player.addVelocity(lookDirection.x, 0, lookDirection.z);
        player.velocityModified = true;
    }

    public static boolean isCharging(PlayerEntity player) {
        return CHARGING_PLAYERS.getOrDefault(player.getUuid(), 0L) > player.getWorld().getTime();
    }

    public static void stopCharging(PlayerEntity player) {
        CHARGING_PLAYERS.remove(player.getUuid());
    }

    


    /*private double knockbackStrength = 1;

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        applyKnockback(target, attacker, knockbackStrength);
        return true;
    }

    private void applyKnockback(LivingEntity target, LivingEntity attacker, double strength) {
        double xRatio = attacker.getX() - target.getX();
        double zRatio = attacker.getZ() - target.getZ();
        while (xRatio * xRatio + zRatio * zRatio < 0.0001) {
            xRatio = (Math.random() - Math.random()) * 0.01;
            zRatio = (Math.random() - Math.random()) * 0.01;
        }
        target.takeKnockback(strength, xRatio, zRatio);
        target.velocityModified = true;
    }

    private double knockbackStrengthUp = 0.5;

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient) {
            // Define a 2-block radius bounding box around the player
            double radius = 2.0;
            Box box = new Box(
                user.getX() - radius, user.getY() - radius, user.getZ() - radius,
                user.getX() + radius, user.getY() + radius, user.getZ() + radius
            );

            // Find all entities in the area (excluding the user)
            for (Entity entity : world.getOtherEntities(user, box)) {
                if (entity instanceof LivingEntity) {
                    // Launch the entity into the air
                    entity.addVelocity(0, knockbackStrengthUp, 0); // Adjust the Y velocity for higher or lower launch
                    entity.velocityModified = true; // Ensure velocity is updated
                }
            }

            BlockPos positionClicked = hand.getBlockPos();

            

        }

        //Particle
        




        // Play a sound or particle effect if desired
        user.getItemCooldownManager().set(this, 40); // Optional cooldown: 20 ticks = 1 second

        return TypedActionResult.success(user.getStackInHand(hand));
    }

    private void spawnParticles(ItemUsageContext pContext, BlockPos positionClicked) {
        for(int i = 0; i < 360; i++) {
            if(i % 20 == 0) {
                pContext.getWorld().addParticle(ParticleTypes.EXPLOSION,
                        positionClicked.getX() + 0.5d, positionClicked.getY() + 1, positionClicked.getZ() + 0.5d,
                        Math.cos(i) * 0.25d, 0.15d, Math.sin(i) * 0.25d);
            }
        }
    }*/

}
