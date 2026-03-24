package net.ent.entstupidstuff.api.casting;

import com.mojang.serialization.Codec;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;

/*public record ArmorMoldComponent(ResourceLocation castId) implements TooltipProvider {

    public static final Codec<ArmorMoldComponent> CODEC = ResourceLocation.CODEC
        .xmap(ArmorMoldComponent::new, ArmorMoldComponent::castId);
    public static final StreamCodec<ByteBuf, ArmorMoldComponent> STREAM_CODEC =
        ResourceLocation.STREAM_CODEC.map(ArmorMoldComponent::new, ArmorMoldComponent::castId);

    @Override
    public void addToTooltip(TooltipContext context, Consumer<Component> consumer,
                              TooltipFlag flag, DataComponentGetter components) {
        consumer.accept(CommonComponents.EMPTY);
        consumer.accept(Component.translatable(
            "item.entstupidstuff.mold.applied_armor_mold"
        ).withStyle(ChatFormatting.GRAY));
        consumer.accept(
            CommonComponents.space().append(
                Component.literal(castId.getPath()).withStyle(ChatFormatting.GOLD)
            )
        );
    }
}*/

public record ArmorCastingComponent(ResourceLocation castId) {
    public static final Codec<ArmorCastingComponent> CODEC = ResourceLocation.CODEC
        .xmap(ArmorCastingComponent::new, ArmorCastingComponent::castId);
    public static final StreamCodec<ByteBuf, ArmorCastingComponent> STREAM_CODEC =
        ResourceLocation.STREAM_CODEC.map(ArmorCastingComponent::new, ArmorCastingComponent::castId);
}


