package net.ent.entstupidstuff.item.base.itemType;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import net.ent.entstupidstuff.api.IntTrait.ITrait;
import net.ent.entstupidstuff.item.base.combat.WeaponItem;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class KatanaItem  extends WeaponItem implements ITrait{

    public KatanaItem(ToolMaterial toolMaterial, Properties settings) {
        super(toolMaterial, settings);
        //super(toolMaterial, settings.attributeModifiers(WeaponItem.createAttributeModifiers(toolMaterial, (6.5 /*5.5*/)  + toolMaterial.attackDamageBonus(), -3.4f, 1, 0, 3)));
    }

    /*@Override
    public void appendTooltip(ItemStack itemStack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        //tooltip.add(Text.translatable("item.entstupidstuff.double_hand.tooltip").formatted(Formatting.GRAY));
        //tooltip.add(Text.translatable("item.entstupidstuff.blunt.tooltip").formatted(Formatting.GRAY));
    }*/

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
    public InteractionResult use(Level world, Player player, InteractionHand hand) {
        if (!world.isClientSide() && !isCharging(player)) {
            startCharging(player);
            checkForCollision(player);
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.FAIL;
    }

    /*@Override
    public void inventoryTick(ItemStack stack, World world, LivingEntity entity, int slot, boolean selected) {
        if (!world.isClient() && entity instanceof PlayerEntity player && isCharging(player)) {
            checkForCollision(player, stack);
        }
    }*/

    private boolean checkForCollision(Player player) {
        AABB boundingBox = player.getBoundingBox().inflate(1); // Slightly larger hitbox
        List<LivingEntity> entities = player.level().getEntitiesOfClass(LivingEntity.class, boundingBox, e -> e != player);

        for (LivingEntity target : entities) {
            if (target.isAlive()) {
                target.hurtServer((ServerLevel) player.level(), player.damageSources().playerAttack(player), 10 * 2); // Double damage
                return true;
            }
        }
        return false;
    }

    /*@Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (attacker instanceof PlayerEntity player) {
            if (isCharging(player)) {
                target.damage(player.getDamageSources().playerAttack(player), 1 * 2); // Double damage
                stopCharging(player);
            }
        }
        return super.postHit(stack, target, attacker);
    }*/

    public static void startCharging(Player player) {
        CHARGING_PLAYERS.put(player.getUUID(), player.level().getGameTime() + CHARGE_DURATION);
        Vec3 lookDirection = player.getViewVector(1.0f).scale(CHARGE_SPEED);
        player.push(lookDirection.x, 0, lookDirection.z);
        player.hurtMarked = true;
    }

    public static boolean isCharging(Player player) {
        return CHARGING_PLAYERS.getOrDefault(player.getUUID(), 0L) > player.level().getGameTime();
    }

    public static void stopCharging(Player player) {
        CHARGING_PLAYERS.remove(player.getUUID());
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
        if (!world.isClient()) {
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
