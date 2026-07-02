package net.ent.entstupidstuff.api.ship;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

/**
 * Client → server steering input.
 *
 * WHY THIS EXISTS:
 * All ship physics runs SERVER-SIDE (single source of truth — see
 * CustomBoatEntity.tick()). But the driver's A/D keys are read on the CLIENT
 * via the mixin's controlBoat(). entityData only syncs server→client, so a
 * client-set rudder never reaches the server. This packet carries the raw
 * A/D input to the server, which applies it to the rudder and runs the turn.
 *
 * left / right: whether the A or D key is currently held this tick.
 */
public record SteerPayload(int boatId, boolean left, boolean right)
        implements CustomPacketPayload {

    public static final Type<SteerPayload> TYPE = new Type<>(
        ResourceLocation.fromNamespaceAndPath("entstupidstuff", "steer"));

    public static final StreamCodec<RegistryFriendlyByteBuf, SteerPayload> CODEC =
        StreamCodec.composite(
            ByteBufCodecs.VAR_INT, SteerPayload::boatId,
            ByteBufCodecs.BOOL,    SteerPayload::left,
            ByteBufCodecs.BOOL,    SteerPayload::right,
            SteerPayload::new);

    @Override
    public Type<? extends CustomPacketPayload> type() { return TYPE; }
}