package net.ent.entstupidstuff.api.mold;

import com.mojang.serialization.Codec;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;

public record MoldComponent(ResourceLocation moldId) {
    public static final Codec<MoldComponent> CODEC = ResourceLocation.CODEC
        .xmap(MoldComponent::new, MoldComponent::moldId);

    public static final StreamCodec<ByteBuf, MoldComponent> STREAM_CODEC =
        ResourceLocation.STREAM_CODEC.map(MoldComponent::new, MoldComponent::moldId);
}


/*

    Current Armor Molds:
    Samurai
    Knight
    Illager
    Classic - Maybe
    Advanture - Might Migrate to Knights
    Story - Story Mod design
    Doom :)



*/
