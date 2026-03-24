package net.ent.entstupidstuff.api.casting;

import java.util.function.Consumer;

import com.mojang.serialization.Codec;

import io.netty.buffer.ByteBuf;
import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item.TooltipContext;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipProvider;

/*public record ToolMoldComponent(ResourceLocation castId) implements TooltipProvider {

    public static final Codec<ToolMoldComponent> CODEC = ResourceLocation.CODEC
        .xmap(ToolMoldComponent::new, ToolMoldComponent::castId);
    public static final StreamCodec<ByteBuf, ToolMoldComponent> STREAM_CODEC =
        ResourceLocation.STREAM_CODEC.map(ToolMoldComponent::new, ToolMoldComponent::castId);

    @Override
    public void addToTooltip(TooltipContext context, Consumer<Component> consumer,
                              TooltipFlag flag, DataComponentGetter components) {
        consumer.accept(CommonComponents.EMPTY);
        consumer.accept(Component.translatable(
            "item.entstupidstuff.mold.applied_tool_mold"
        ).withStyle(ChatFormatting.GRAY));
        consumer.accept(
            CommonComponents.space().append(
                Component.literal(castId.getPath()).withStyle(ChatFormatting.GOLD)
            )
        );
    }
}*/

public record ToolCastingComponent(ResourceLocation castId) {
    public static final Codec<ToolCastingComponent> CODEC = ResourceLocation.CODEC
        .xmap(ToolCastingComponent::new, ToolCastingComponent::castId);
    public static final StreamCodec<ByteBuf, ToolCastingComponent> STREAM_CODEC =
        ResourceLocation.STREAM_CODEC.map(ToolCastingComponent::new, ToolCastingComponent::castId);
}
