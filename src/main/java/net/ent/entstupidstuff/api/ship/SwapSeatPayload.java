package net.ent.entstupidstuff.api.ship;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public record SwapSeatPayload(int boatId) implements CustomPacketPayload {
 
    public static final CustomPacketPayload.Type<SwapSeatPayload> TYPE =
        new CustomPacketPayload.Type<>(
            ResourceLocation.fromNamespaceAndPath("entstupidstuff", "swap_seat"));
 
    public static final StreamCodec<RegistryFriendlyByteBuf, SwapSeatPayload> CODEC =
        StreamCodec.composite(
            ByteBufCodecs.VAR_INT, SwapSeatPayload::boatId,
            SwapSeatPayload::new);
 
    @Override
    public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
 

