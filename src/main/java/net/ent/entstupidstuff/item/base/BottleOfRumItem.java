package net.ent.entstupidstuff.item.base;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

public class BottleOfRumItem extends Item {
    private static final int DRINK_DURATION = 32;
    private static final int EFFECT_DURATION = 600;

    public BottleOfRumItem(Item.Properties settings) {
        super(settings.stacksTo(16));
    }

    @Override
    public int getUseDuration(ItemStack stack, LivingEntity user) {
        return DRINK_DURATION;
    }

    @Override
    public ItemUseAnimation getUseAnimation(ItemStack stack) {
        return ItemUseAnimation.DRINK;
    }

    @Override
    public InteractionResult use(Level world, Player player, InteractionHand hand) {
        player.startUsingItem(hand);
        return InteractionResult.SUCCESS;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity player) {

        Player player2 = (Player) player;

        if (!world.isClientSide()) {
            if (!player2.isCreative()) {
                stack.shrink(1);
            }
            player2.addItem(new ItemStack(Items.GLASS_BOTTLE));
            player2.addEffect(new MobEffectInstance(MobEffects.NAUSEA, EFFECT_DURATION, 5));
            //applyDrunkEffect(player2, (ServerWorld) world);
        }
        return stack.isEmpty() ? new ItemStack(Items.GLASS_BOTTLE) : stack;
    }
}

    // Old Code


    /*@Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity player) {
        if (!world.isClient()) {
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

