package net.ent.entstupidstuff.item.base;

import java.util.function.Consumer;

import net.ent.entstupidstuff.client.entity.passive.ZebraFishEntity;
import net.ent.entstupidstuff.component.ModDataComponentTypes;
import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.EntityBucketItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.sound.SoundEvent;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class ZebraFishBucketItem extends EntityBucketItem {
    public ZebraFishBucketItem(EntityType<? extends MobEntity> type, Fluid fluid, SoundEvent emptyingSound,
            Settings settings) {
        super(type, fluid, emptyingSound, settings);
    }

    /*
     * @Override
     * public void appendTooltip(ItemStack stack, Item.TooltipContext context,
     * List<Text> tooltip, TooltipType type) {
     * super.appendTooltip(stack, context, tooltip, type);
     * 
     * NbtComponent nbtComponent =
     * stack.getOrDefault(DataComponentTypes.BUCKET_ENTITY_DATA,
     * NbtComponent.DEFAULT);
     * NbtCompound nbt = nbtComponent.copyNbt();
     * 
     * if (nbt.contains("BucketVariantTag", NbtElement.INT_TYPE)) {
     * ZebraFishEntity.Variant variant =
     * ZebraFishEntity.Variant.byId(nbt.getInt("BucketVariantTag"));
     * tooltip.add(Text.literal(variant.getPattern()).formatted(Formatting.GRAY,
     * Formatting.ITALIC));
     * tooltip.add(Text.literal(variant.getColor()).formatted(Formatting.GRAY,
     * Formatting.ITALIC));
     * }
     * }
     */

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, TooltipDisplayComponent displayComponent,
            Consumer<Text> textConsumer, TooltipType type) {

        ZebraFishEntity.Variant variant = stack.get(ModDataComponentTypes.ZEBRA_FISH_VARIANT);

        if (variant != null) {
            textConsumer.accept(Text.literal(variant.getPattern()).formatted(Formatting.GRAY, Formatting.ITALIC));
            textConsumer.accept(Text.literal(variant.getColor()).formatted(Formatting.GRAY, Formatting.ITALIC));
        }
    }
}
