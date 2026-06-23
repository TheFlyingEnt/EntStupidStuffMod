package net.ent.entstupidstuff.api.ship;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
 
/**
 * Client -> server: "I'm standing on boat {boatId} at this boat-local offset."
 * boatId < 0 means "I stepped off — clear my anchor."
 */
public record DeckOffsetPayload(int boatId, double x, double y, double z) implements CustomPacketPayload {
 
    public static final Type<DeckOffsetPayload> TYPE = new Type<>(
        ResourceLocation.fromNamespaceAndPath("entstupidstuff", "deck_offset"));
 
    public static final StreamCodec<RegistryFriendlyByteBuf, DeckOffsetPayload> CODEC =
        StreamCodec.composite(
            ByteBufCodecs.VAR_INT, DeckOffsetPayload::boatId,
            ByteBufCodecs.DOUBLE,  DeckOffsetPayload::x,
            ByteBufCodecs.DOUBLE,  DeckOffsetPayload::y,
            ByteBufCodecs.DOUBLE,  DeckOffsetPayload::z,
            DeckOffsetPayload::new);
 
    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}

