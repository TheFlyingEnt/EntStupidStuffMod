package net.ent.entstupidstuff.api.hat;
 
import net.ent.entstupidstuff.EntStupidStuff;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
 
import java.util.HashSet;
import java.util.Set;
 
/**
 * S2C — sends the player's full unlocked hat set to their client.
 * Sent on join and after every grant/revoke so the GUI stays in sync.
 */
public record UnlockSyncPayload(Set<String> unlockedHats)
    implements CustomPacketPayload {
 
    public static final CustomPacketPayload.Type<UnlockSyncPayload> TYPE =
        new CustomPacketPayload.Type<>(
            ResourceLocation.fromNamespaceAndPath(EntStupidStuff.MOD_ID, "unlock_sync")
        );
 
    public static final StreamCodec<FriendlyByteBuf, UnlockSyncPayload> CODEC =
        StreamCodec.of(
            (buf, payload) -> {
                buf.writeInt(payload.unlockedHats().size());
                payload.unlockedHats().forEach(buf::writeUtf);
            },
            buf -> {
                int size = buf.readInt();
                Set<String> hats = new HashSet<>();
                for (int i = 0; i < size; i++) hats.add(buf.readUtf());
                return new UnlockSyncPayload(hats);
            }
        );
 
    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
