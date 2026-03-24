package net.ent.entstupidstuff.api.casting;

import com.mojang.serialization.Codec;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;

public record CastingComponent(ResourceLocation castId) {
    public static final Codec<CastingComponent> CODEC = ResourceLocation.CODEC
        .xmap(CastingComponent::new, CastingComponent::castId);

    public static final StreamCodec<ByteBuf, CastingComponent> STREAM_CODEC =
        ResourceLocation.STREAM_CODEC.map(CastingComponent::new, CastingComponent::castId);
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
    Halo
    Soldier



*/
