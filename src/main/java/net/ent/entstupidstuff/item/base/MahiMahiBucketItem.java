package net.ent.entstupidstuff.item.base;

import java.util.function.Consumer;

import net.ent.entstupidstuff.client.entity.passive.MahiMahiEntity;
import net.ent.entstupidstuff.component.ModDataComponentTypes;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.MobBucketItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.material.Fluid;

public class MahiMahiBucketItem extends MobBucketItem{

    public MahiMahiBucketItem(EntityType<? extends Mob> type, Fluid fluid, SoundEvent emptyingSound, Properties settings) {
        super(type, fluid, emptyingSound, settings);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay displayComponent,
            Consumer<Component> textConsumer, TooltipFlag type) {

        MahiMahiEntity.Variant variant = stack.get(ModDataComponentTypes.MAHIMAHI_FISH_VARIANT);

        if (variant != null) {
            textConsumer.accept(Component.literal(variant.getId()).withStyle(ChatFormatting.GRAY, ChatFormatting.ITALIC));
        }
    }
    
    /*@Override
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType type) {
        super.appendTooltip(stack, context, tooltip, type);

        NbtComponent nbtComponent = stack.getOrDefault(DataComponentTypes.BUCKET_ENTITY_DATA, NbtComponent.DEFAULT);
        NbtCompound nbt = nbtComponent.copyNbt();


        if (nbt.contains("BucketVariantTag", NbtElement.INT_TYPE)) {
            MahiMahiEntity.Variant variant = MahiMahiEntity.Variant.byId(nbt.getInt("BucketVariantTag"));
            tooltip.add(Text.literal(variant.getName()).formatted(Formatting.GRAY, Formatting.ITALIC));
        }
    }*/
}
