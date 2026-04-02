package net.ent.entstupidstuff.api.hat;

import net.ent.entstupidstuff.EntStupidStuff;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
 
/**
 * C2S packet — sent when the player clicks a hat in the hat menu GUI.
 * Empty string means "remove hat".
 */

public record HatSelectPayload(String hatName)
    implements CustomPacketPayload {
 
    public static final CustomPacketPayload.Type<HatSelectPayload> TYPE =
        new CustomPacketPayload.Type<>(
            ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "hat_select")
        );
 
    public static final StreamCodec<FriendlyByteBuf, HatSelectPayload> CODEC =
        StreamCodec.of(
            (buf, payload) -> buf.writeUtf(payload.hatName()),
            buf -> new HatSelectPayload(buf.readUtf())
        );
 
    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
