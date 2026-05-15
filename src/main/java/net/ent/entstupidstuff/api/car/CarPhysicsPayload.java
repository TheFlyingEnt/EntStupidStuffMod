package net.ent.entstupidstuff.api.car;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

/**
 * Client-to-Server packet: driver sends exact physics state every tick.
 *
 * The driver's client runs the physics engine and produces authoritative
 * values for RPM, gear, speed, drifting, wheel spin, etc. This packet
 * ships those values to the server, which writes them into entityData.
 * entityData syncs server→client to ALL other clients automatically.
 *
 * Payload size: ~36 bytes × 20 ticks/sec = 720 bytes/sec — negligible.
 */
public record CarPhysicsPayload(
    int   entityId,
    float forwardSpeed,
    float engineRPM,
    int   gear,
    float wheelSpin,
    float rearWheelSpin,
    float steerInput,
    boolean throttle,
    boolean braking,
    boolean burnout,
    boolean drifting
) implements CustomPacketPayload {

    public static final CustomPacketPayload.Type<CarPhysicsPayload> TYPE =
        new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(
            "entstupidstuff", "car_physics"));

    public static final StreamCodec<FriendlyByteBuf, CarPhysicsPayload> STREAM_CODEC =
        StreamCodec.of(CarPhysicsPayload::write, CarPhysicsPayload::read);

    private static CarPhysicsPayload read(FriendlyByteBuf buf) {
        return new CarPhysicsPayload(
            buf.readVarInt(),    // entityId
            buf.readFloat(),     // forwardSpeed
            buf.readFloat(),     // engineRPM
            buf.readVarInt(),    // gear
            buf.readFloat(),     // wheelSpin
            buf.readFloat(),     // rearWheelSpin
            buf.readFloat(),     // steerInput
            buf.readBoolean(),   // throttle
            buf.readBoolean(),   // braking
            buf.readBoolean(),   // burnout
            buf.readBoolean()    // drifting
        );
    }

    private static void write(FriendlyByteBuf buf, CarPhysicsPayload p) {
        buf.writeVarInt(p.entityId);
        buf.writeFloat(p.forwardSpeed);
        buf.writeFloat(p.engineRPM);
        buf.writeVarInt(p.gear);
        buf.writeFloat(p.wheelSpin);
        buf.writeFloat(p.rearWheelSpin);
        buf.writeFloat(p.steerInput);
        buf.writeBoolean(p.throttle);
        buf.writeBoolean(p.braking);
        buf.writeBoolean(p.burnout);
        buf.writeBoolean(p.drifting);
    }

    @Override
    public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
