package net.ent.entstupidstuff.api.casting;

import org.jetbrains.annotations.Nullable;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;

import net.ent.entstupidstuff.component.ModDataComponentTypes;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.item.properties.select.SelectItemModelProperty;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

public record ArmorCastProperty() implements SelectItemModelProperty<ResourceLocation> {

    public static final SelectItemModelProperty.Type<ArmorCastProperty, ResourceLocation> TYPE =
        SelectItemModelProperty.Type.create(
            MapCodec.unit(new ArmorCastProperty()),
            ResourceLocation.CODEC
        );

    /*@Nullable
    @Override
    public ResourceLocation get(ItemStack stack, @Nullable ClientLevel level, @Nullable LivingEntity entity, int seed, ItemDisplayContext displayContext) {
        return stack.get(ModDataComponentTypes.ARMOR_CAST);
    }*/

    @Nullable
    @Override
    public ResourceLocation get(ItemStack stack, @Nullable ClientLevel level,
                                @Nullable LivingEntity entity, int seed,
                                ItemDisplayContext displayContext) {
        ArmorCastingComponent comp = stack.get(ModDataComponentTypes.ARMOR_CAST);
        return comp != null ? comp.castId() : null; // extract ResourceLocation from record
    }

    @Override
    public SelectItemModelProperty.Type<ArmorCastProperty, ResourceLocation> type() {
        return TYPE;
    }

    @Override
    public Codec<ResourceLocation> valueCodec() {
        return ResourceLocation.CODEC;
    }
}
