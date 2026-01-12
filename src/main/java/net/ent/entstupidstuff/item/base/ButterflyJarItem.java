package net.ent.entstupidstuff.item.base;

import java.util.function.Consumer;

import net.ent.entstupidstuff.client.entity.Jarredable;
import net.ent.entstupidstuff.client.entity.passive.ButterflyEntity;
import net.ent.entstupidstuff.component.ModDataComponentTypes;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

public class ButterflyJarItem extends Item {

    private final EntityType<? extends ButterflyEntity> entityType;
    private final SoundEvent releaseSound;

    public ButterflyJarItem(EntityType<? extends ButterflyEntity> type, SoundEvent emptyingSound, Properties settings) {
        super(settings);
        this.entityType = type;
        this.releaseSound = emptyingSound;
    }

    private void spawnEntity(ServerLevel world, ItemStack stack, BlockPos pos) {
        if (this.entityType.spawn(world, stack, null, pos, EntitySpawnReason.BUCKET, true, false) instanceof Jarredable jarredEntity) {

            // Copy all NBT from stack
            CustomData nbtComponent = stack.getOrDefault(DataComponents.BUCKET_ENTITY_DATA, CustomData.EMPTY);
            jarredEntity.copyDataFromNbt(nbtComponent.copyTag());

            // Copy custom name
            if (stack.get(DataComponents.CUSTOM_NAME) != null) {
                ((LivingEntity) jarredEntity).setCustomName(stack.get(DataComponents.CUSTOM_NAME).copy());
                ((LivingEntity) jarredEntity).setCustomNameVisible(true);
            }

            jarredEntity.setFromJar(true);
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay displayComponent, Consumer<Component> textConsumer, TooltipFlag type) {
        ButterflyEntity.Variant variant = stack.get(ModDataComponentTypes.BUTTERFLY_VARIANT);
        if (variant != null) {
            String variantName = variant.getId();
            String formattedName = variantName.substring(0, 1).toUpperCase() + variantName.substring(1);
            textConsumer.accept(
                Component.literal(formattedName)
                    .withStyle(ChatFormatting.GRAY, ChatFormatting.ITALIC)
            );
            return;
        }
    }

    /*@Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        super.appendTooltip(stack, context, tooltip, type);

        NbtComponent nbtComponent = stack.getOrDefault(DataComponentTypes.BUCKET_ENTITY_DATA, NbtComponent.DEFAULT);
        NbtCompound nbt = nbtComponent.copyNbt();

        if (nbt.contains("Variant", NbtElement.INT_TYPE)) {
            ButterflyEntity.Variant variant = ButterflyEntity.Variant.byId(nbt.getInt("Variant"));
            tooltip.add(Text.literal(variant.getName()).formatted(Formatting.GRAY, Formatting.ITALIC));
        }
    }*/
    


    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level world = context.getLevel();
        Player player = context.getPlayer();
        ItemStack stack = context.getItemInHand();
        BlockPos pos = context.getClickedPos().relative(context.getClickedFace());

        if (world instanceof ServerLevel serverWorld) {
            this.spawnEntity(serverWorld, stack, pos);

            world.playSound(null, pos, this.releaseSound, SoundSource.NEUTRAL, 1.0F, 1.0F);

            if (!player.isCreative()) {
                stack.shrink(1);
                player.addItem(new ItemStack(Items.GLASS_BOTTLE));
            }
        }

        return InteractionResult.SUCCESS;
    }
}
