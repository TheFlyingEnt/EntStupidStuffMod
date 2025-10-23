package net.ent.entstupidstuff.item.base;

import java.util.List;

import net.ent.entstupidstuff.entity.Jarredable;
import net.ent.entstupidstuff.entity.passive.ButterflyEntity;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.Items;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ButterflyJarItem extends Item {

    private final EntityType<? extends ButterflyEntity> entityType;
    private final SoundEvent releaseSound;

    public ButterflyJarItem(EntityType<? extends ButterflyEntity> type, SoundEvent emptyingSound, Settings settings) {
        super(settings);
        this.entityType = type;
        this.releaseSound = emptyingSound;
    }

    private void spawnEntity(ServerWorld world, ItemStack stack, BlockPos pos) {
        if (this.entityType.spawnFromItemStack(world, stack, null, pos, SpawnReason.BUCKET, true, false) instanceof Jarredable jarredEntity) {

            // Copy all NBT from stack
            NbtComponent nbtComponent = stack.getOrDefault(DataComponentTypes.BUCKET_ENTITY_DATA, NbtComponent.DEFAULT);
            jarredEntity.copyDataFromNbt(nbtComponent.copyNbt());

            // Copy custom name
            if (stack.get(DataComponentTypes.CUSTOM_NAME) != null) {
                ((LivingEntity) jarredEntity).setCustomName(stack.get(DataComponentTypes.CUSTOM_NAME).copy());
                ((LivingEntity) jarredEntity).setCustomNameVisible(true);
            }

            jarredEntity.setFromJar(true);
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
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        PlayerEntity player = context.getPlayer();
        ItemStack stack = context.getStack();
        BlockPos pos = context.getBlockPos().offset(context.getSide());

        if (world instanceof ServerWorld serverWorld) {
            this.spawnEntity(serverWorld, stack, pos);

            world.playSound(null, pos, this.releaseSound, SoundCategory.NEUTRAL, 1.0F, 1.0F);

            if (!player.isCreative()) {
                stack.decrement(1);
                player.giveItemStack(new ItemStack(Items.GLASS_BOTTLE));
            }
        }

        return ActionResult.SUCCESS;
    }
}
