package net.ent.entstupidstuff.api.ship;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

/**
 * Client → server: bow gunner cannon commands.
 * action:
 *   0 = FIRE          — shoot a cannonball in the look direction
 *   1 = LAUNCH_PLAYER — launch the gunner themselves out of the cannon
 * yaw/pitch: player's look direction (used for both actions)
 */
public record CannonControlPayload(int boatId, int action, float yaw, float pitch)
        implements CustomPacketPayload {

    public static final int FIRE          = 0;
    public static final int LAUNCH_PLAYER = 1;

    public static final Type<CannonControlPayload> TYPE = new Type<>(
        ResourceLocation.fromNamespaceAndPath("entstupidstuff", "cannon_control"));

    public static final StreamCodec<RegistryFriendlyByteBuf, CannonControlPayload> CODEC =
        StreamCodec.composite(
            ByteBufCodecs.VAR_INT,  CannonControlPayload::boatId,
            ByteBufCodecs.VAR_INT,  CannonControlPayload::action,
            ByteBufCodecs.FLOAT,    CannonControlPayload::yaw,
            ByteBufCodecs.FLOAT,    CannonControlPayload::pitch,
            CannonControlPayload::new);

    @Override
    public Type<? extends CustomPacketPayload> type() { return TYPE; }
}