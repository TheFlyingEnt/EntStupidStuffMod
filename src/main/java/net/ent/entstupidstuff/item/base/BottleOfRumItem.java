package net.ent.entstupidstuff.item.base;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;

public class BottleOfRumItem extends Item {
    private static final int DRINK_DURATION = 32;
    private static final int EFFECT_DURATION = 600;

    public BottleOfRumItem(Item.Settings settings) {
        super(settings.maxCount(16));
    }

    @Override
    public int getMaxUseTime(ItemStack stack, LivingEntity user) {
        return DRINK_DURATION;
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.DRINK;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        player.setCurrentHand(hand);
        return new TypedActionResult<>(ActionResult.SUCCESS, player.getStackInHand(hand));
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity player) {

        PlayerEntity player2 = (PlayerEntity) player;

        if (!world.isClient) {
            if (!player2.isCreative()) {
                stack.decrement(1);
            }
            player2.giveItemStack(new ItemStack(Items.GLASS_BOTTLE));
            player2.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, EFFECT_DURATION, 5));
            //applyDrunkEffect(player2, (ServerWorld) world);
        }
        return stack.isEmpty() ? new ItemStack(Items.GLASS_BOTTLE) : stack;
    }
}

    // Old Code


    /*@Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity player) {
        if (!world.isClient) {
            player.setStackInHand(player.getActiveHand(), new ItemStack(Items.GLASS_BOTTLE));
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 200, 1));
            //applyDrunkEffect(player, (ServerWorld) world);
        }
        return stack.isEmpty() ? new ItemStack(Items.GLASS_BOTTLE) : stack;
    }

    /*private void applyDrunkEffect(LivingEntity player, ServerWorld world) {
        final int[] ticks = {0};
        ServerTickEvents.START_SERVER_TICK.register(server -> {
            if (ticks[0] < 200) { // 10 intervals of 20 ticks (1 second each)
                if (ticks[0] % 20 == 0) { // Every second
                    Vec3d randomMovement = player.getPos().add(
                        (world.random.nextDouble() - 0.5) * 2, 
                        0, 
                        (world.random.nextDouble() - 0.5) * 2
                    );
                    player.requestTeleport(randomMovement.x, randomMovement.y, randomMovement.z);
                }
                ticks[0]++;
            }
        });
    }*/

    ////////////////////////////////////////

    /*@Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        player.setStackInHand(hand, new ItemStack(Items.GLASS_BOTTLE));
        player.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 200, 1));
        
        if (world instanceof ServerWorld) {
            ((ServerWorld) world).getServer().submit(() -> applyDrunkEffect(player));
        }

        return new TypedActionResult<>(ActionResult.SUCCESS, player.getStackInHand(hand));
    }

    private void applyDrunkEffect(PlayerEntity player) {
        Vec3d randomMovement = player.getPos().add(
            player.getWorld().random.nextGaussian() * 0.5, 
            0, 
            player.getWorld().random.nextGaussian() * 0.5
        );
        player.requestTeleport(randomMovement.x, randomMovement.y, randomMovement.z);
    }*/

