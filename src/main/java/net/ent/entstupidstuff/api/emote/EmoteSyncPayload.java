package net.ent.entstupidstuff.api.emote;

import java.util.UUID;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public record EmoteSyncPayload(UUID playerUuid, String emoteName)
    implements CustomPacketPayload {
 
    public static final CustomPacketPayload.Type<EmoteSyncPayload> TYPE =
        new CustomPacketPayload.Type<>(
            ResourceLocation.fromNamespaceAndPath("hatsmod", "emote_sync")
        );
 
    public static final StreamCodec<FriendlyByteBuf, EmoteSyncPayload> CODEC =
        StreamCodec.of(
            (buf, payload) -> {
                buf.writeLong(payload.playerUuid().getMostSignificantBits());
                buf.writeLong(payload.playerUuid().getLeastSignificantBits());
                buf.writeUtf(payload.emoteName());
            },
            buf -> {
                UUID uuid = new UUID(buf.readLong(), buf.readLong());
                String name = buf.readUtf();
                return new EmoteSyncPayload(uuid, name);
            }
        );
 
    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
 
