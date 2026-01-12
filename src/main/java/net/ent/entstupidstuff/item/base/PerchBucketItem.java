package net.ent.entstupidstuff.item.base;

import java.util.function.Consumer;

import net.ent.entstupidstuff.client.entity.passive.ButterflyEntity;
import net.ent.entstupidstuff.client.entity.passive.PerchFishEntity;
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

public class PerchBucketItem extends EntityBucketItem{

    public PerchBucketItem(EntityType<? extends MobEntity> type, Fluid fluid, SoundEvent emptyingSound, Settings settings) {
        super(type, fluid, emptyingSound, settings);
    }
    
    /*@Override
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType type) {
        super.appendTooltip(stack, context, tooltip, type);

        NbtComponent nbtComponent = stack.getOrDefault(DataComponentTypes.BUCKET_ENTITY_DATA, NbtComponent.DEFAULT);
        NbtCompound nbt = nbtComponent.copyNbt();


        if (nbt.contains("BucketVariantTag", NbtElement.INT_TYPE)) {
            PerchFishEntity.Variant variant = PerchFishEntity.Variant.byId(nbt.getInt("BucketVariantTag"));
            tooltip.add(Text.literal(variant.getName()).formatted(Formatting.GRAY, Formatting.ITALIC));
        }
    }*/

        @Override
        public void appendTooltip(ItemStack stack, TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type) {
            PerchFishEntity.Variant variant2 = stack.getOrDefault(ModDataComponentTypes.PERCH_FISH_VARIANT, PerchFishEntity.Variant.DEFAULT);
            
            PerchFishEntity.Variant variant = stack.get(ModDataComponentTypes.PERCH_FISH_VARIANT);
            
            if (variant != null) {
                String variantName = variant.getId();
                String formattedName = variantName.substring(0, 1).toUpperCase() + variantName.substring(1);
                textConsumer.accept(
                    Text.literal(formattedName)
                        .formatted(Formatting.GRAY, Formatting.ITALIC)
                );
                return;
            }
        }
}
