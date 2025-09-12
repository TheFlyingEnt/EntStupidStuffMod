package net.ent.entstupidstuff.item.base;

import java.util.List;

import net.ent.entstupidstuff.client.render.entity.KoiVariant;
import net.ent.entstupidstuff.client.render.entity.KoiVariantRegistry;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.entity.EntityType;
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

public class KoiBucketItem extends EntityBucketItem {

    public KoiBucketItem(EntityType<?> type, Fluid fluid, SoundEvent emptyingSound, Item.Settings settings) {
        super(type, fluid, emptyingSound, settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, Item.TooltipContext context,
                              List<Text> tooltip, TooltipType type) {
        super.appendTooltip(stack, context, tooltip, type);

        // Grab the NBT that stores entity data
        NbtComponent nbtComponent = stack.getOrDefault(DataComponentTypes.BUCKET_ENTITY_DATA, NbtComponent.DEFAULT);
        NbtCompound nbt = nbtComponent.copyNbt();

        // Koi’s variant is saved as an int (like your Bass example)
        if (nbt.contains("BucketVariantTag", NbtElement.INT_TYPE)) {
            int variantId = nbt.getInt("BucketVariantTag");
            KoiVariant variant = KoiVariantRegistry.getById(variantId); // registry lookup

            // Add tooltip line with koi’s variant
            /*String display = variant.getBase().getName();

            if (variant.getPattern() != null && variant.getPattern().name() != "NONE") {
                display += " (" + variant.getPattern().name();
                variant.getPatternColor1().ifPresent(c -> display += " " + c.getName());
                variant.getPatternColor2().ifPresent(c -> display += " + " + c.getName());
                display += ")";
            }*/

            //tooltip.add(Text.literal(display).formatted(Formatting.GRAY, Formatting.ITALIC));
        }
    }
}
