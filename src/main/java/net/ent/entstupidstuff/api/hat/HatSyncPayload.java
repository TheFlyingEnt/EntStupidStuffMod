package net.ent.entstupidstuff.api.hat;

import net.ent.entstupidstuff.EntStupidStuff;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
 
import java.util.UUID;

public record HatSyncPayload(UUID playerUuid, String hatName)
    implements CustomPacketPayload {
 
    public static final CustomPacketPayload.Type<HatSyncPayload> TYPE =
        new CustomPacketPayload.Type<>(
            ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "hat_sync")
        );
 
    /**
     * Using StreamCodec.of() with explicit lambdas avoids the type-inference
     * issues that come from mixing ByteBuf-typed built-in codecs with a custom
     * FriendlyByteBuf UUID codec inside composite().
     */
    public static final StreamCodec<FriendlyByteBuf, HatSyncPayload> CODEC =
        StreamCodec.of(
            /* encoder */ (buf, payload) -> {
                buf.writeLong(payload.playerUuid().getMostSignificantBits());
                buf.writeLong(payload.playerUuid().getLeastSignificantBits());
                buf.writeUtf(payload.hatName());
            },
            /* decoder */ buf -> {
                UUID uuid = new UUID(buf.readLong(), buf.readLong());
                String hatName = buf.readUtf();
                return new HatSyncPayload(uuid, hatName);
            }
        );
 
    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
 
