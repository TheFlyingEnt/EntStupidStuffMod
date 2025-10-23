package net.ent.entstupidstuff.item.base;

import java.util.List;

import net.ent.entstupidstuff.entity.passive.PerchFishEntity;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.EntityBucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
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
}
