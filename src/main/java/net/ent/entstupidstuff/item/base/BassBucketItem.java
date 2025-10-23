package net.ent.entstupidstuff.item.base;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.EntityBucketItem;
import net.minecraft.sound.SoundEvent;

public class BassBucketItem extends EntityBucketItem {
    public BassBucketItem(EntityType<? extends MobEntity> type, Fluid fluid, SoundEvent emptyingSound, Settings settings) {
        super(type, fluid, emptyingSound, settings);
    }

    /*@Override
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType type) {
        super.appendTooltip(stack, context, tooltip, type);

        NbtComponent nbtComponent = stack.getOrDefault(DataComponentTypes.BUCKET_ENTITY_DATA, NbtComponent.DEFAULT);
        NbtCompound nbt = nbtComponent.copyNbt();


        if (nbt.contains("BucketVariantTag", NbtElement.INT_TYPE)) {
            BassEntity.Variant variant = BassEntity.Variant.byId(nbt.getInt("BucketVariantTag"));
            tooltip.add(Text.literal(variant.getName()).formatted(Formatting.GRAY, Formatting.ITALIC));
        }
    }*/
}
