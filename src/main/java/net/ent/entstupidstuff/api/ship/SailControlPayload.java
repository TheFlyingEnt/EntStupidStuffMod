package net.ent.entstupidstuff.api.ship;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
 
/** Client -> server ship control. action: 0 raise sail, 1 lower sail, 2 toggle anchor. */
public record SailControlPayload(int boatId, int action) implements CustomPacketPayload {
 
    public static final int RAISE  = 0;
    public static final int LOWER  = 1;
    public static final int ANCHOR = 2;
 
    public static final Type<SailControlPayload> TYPE = new Type<>(
        ResourceLocation.fromNamespaceAndPath("entstupidstuff", "sail_control"));
 
    public static final StreamCodec<RegistryFriendlyByteBuf, SailControlPayload> CODEC =
        StreamCodec.composite(
            ByteBufCodecs.VAR_INT, SailControlPayload::boatId,
            ByteBufCodecs.VAR_INT, SailControlPayload::action,
            SailControlPayload::new);
 
    @Override
    public Type<? extends CustomPacketPayload> type() { return TYPE; }
}

