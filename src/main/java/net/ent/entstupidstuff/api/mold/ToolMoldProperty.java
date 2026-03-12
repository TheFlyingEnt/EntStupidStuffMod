package net.ent.entstupidstuff.api.mold;

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

public record ToolMoldProperty() implements SelectItemModelProperty<ResourceLocation> {

    public static final SelectItemModelProperty.Type<ToolMoldProperty, ResourceLocation> TYPE =
        SelectItemModelProperty.Type.create(
            MapCodec.unit(new ToolMoldProperty()),
            ResourceLocation.CODEC
        );

    @Nullable
    @Override
    public ResourceLocation get(ItemStack stack, @Nullable ClientLevel level,
                                @Nullable LivingEntity entity, int seed,
                                ItemDisplayContext displayContext) {
        return stack.get(ModDataComponentTypes.TOOL_MOLD);
    }

    @Override
    public SelectItemModelProperty.Type<ToolMoldProperty, ResourceLocation> type() {
        return TYPE;
    }

    @Override
    public Codec<ResourceLocation> valueCodec() {
        return ResourceLocation.CODEC;
    }
}
