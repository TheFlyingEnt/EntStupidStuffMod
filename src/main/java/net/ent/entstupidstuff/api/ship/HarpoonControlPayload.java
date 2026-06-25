package net.ent.entstupidstuff.api.ship;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

/**
 * Client → server: bow gunner harpoon commands.
 * action: 0 = fire, 1 = reel in, 2 = release
 * yaw/pitch: player's look direction (only used for FIRE)
 */
public record HarpoonControlPayload(int boatId, int action, float yaw, float pitch)
        implements CustomPacketPayload {

    public static final int FIRE    = 0;
    public static final int REEL    = 1;
    public static final int RELEASE = 2;

    public static final Type<HarpoonControlPayload> TYPE = new Type<>(
        ResourceLocation.fromNamespaceAndPath("entstupidstuff", "harpoon_control"));

    public static final StreamCodec<RegistryFriendlyByteBuf, HarpoonControlPayload> CODEC =
        StreamCodec.composite(
            ByteBufCodecs.VAR_INT,  HarpoonControlPayload::boatId,
            ByteBufCodecs.VAR_INT,  HarpoonControlPayload::action,
            ByteBufCodecs.FLOAT,    HarpoonControlPayload::yaw,
            ByteBufCodecs.FLOAT,    HarpoonControlPayload::pitch,
            HarpoonControlPayload::new);

    @Override
    public Type<? extends CustomPacketPayload> type() { return TYPE; }
}
