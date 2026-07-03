package net.ent.entstupidstuff.api.ship;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

/**
 * Server → client: the global wind state.
 *   dir      — direction the wind blows toward, in degrees (entity-yaw convention)
 *   strength — 0..1 gust strength
 *
 * Broadcast periodically (a few times a second is plenty; the wind drifts
 * slowly) so every client's HUD compass and burgee flags agree.
 */
public record WindSyncPayload(float dir, float strength) implements CustomPacketPayload {

    public static final Type<WindSyncPayload> TYPE = new Type<>(
        ResourceLocation.fromNamespaceAndPath("entstupidstuff", "wind_sync"));

    public static final StreamCodec<RegistryFriendlyByteBuf, WindSyncPayload> CODEC =
        StreamCodec.composite(
            ByteBufCodecs.FLOAT, WindSyncPayload::dir,
            ByteBufCodecs.FLOAT, WindSyncPayload::strength,
            WindSyncPayload::new);

    @Override
    public Type<? extends CustomPacketPayload> type() { return TYPE; }
}
