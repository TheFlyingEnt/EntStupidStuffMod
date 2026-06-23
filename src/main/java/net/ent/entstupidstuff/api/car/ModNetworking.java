package net.ent.entstupidstuff.api.car;

import net.ent.entstupidstuff.api.hat.ModAttachments;
import net.ent.entstupidstuff.api.ship.CustomBoatEntity;
import net.ent.entstupidstuff.api.ship.DeckOffsetPayload;
import net.ent.entstupidstuff.api.ship.DeckSync;
import net.ent.entstupidstuff.api.ship.SailControlPayload;
import net.ent.entstupidstuff.api.ship.SwapSeatPayload;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
 
/**
 * Registers all custom network packets for the car mod.
 *
 * Call from your mod initializer:
 *   ModNetworking.registerC2SPayloads();   — in common/server init
 *   ModNetworking.registerServerHandlers(); — in common/server init
 *
 * Example (in your main mod class):
 *   @Override
 *   public void onInitialize() {
 *       ModNetworking.registerC2SPayloads();
 *       ModNetworking.registerServerHandlers();
 *       // ... other init
 *   }
 */
public final class ModNetworking {
 
    /**
     * Registers packet types. Must be called BEFORE handlers.
     * Call in onInitialize().
     */
    public static void registerC2SPayloads() {
        PayloadTypeRegistry.playC2S().register(
            CarPhysicsPayload.TYPE,
            CarPhysicsPayload.STREAM_CODEC
        );

        PayloadTypeRegistry.playC2S().register(SailControlPayload.TYPE, SailControlPayload.CODEC);

    }
 
    /**
     * Registers server-side packet handlers.
     * Call in onInitialize() AFTER registerC2SPayloads().
     */
    public static void registerServerHandlers() {
        ServerPlayNetworking.registerGlobalReceiver(
            CarPhysicsPayload.TYPE,
            (payload, context) -> {
                // Validation: sender must be riding the car they claim
                var player = context.player();
                var level  = player.level();
                var entity = level.getEntity(payload.entityId());
 
                if (!(entity instanceof BaseCarEntity car)) return;
                if (car.getFirstPassenger() != player) return;
 
                // Write directly to entityData — syncs to ALL clients
                car.applyPhysicsPacket(payload);
            }
        );

        ServerPlayNetworking.registerGlobalReceiver(SwapSeatPayload.TYPE, (payload, context) -> {
            ServerPlayer player = context.player();
            player.level().getServer().execute(() -> {                       // hop to the main thread
                Entity e = player.level().getEntity(payload.boatId());
                if (e instanceof CustomBoatEntity boat) {
                    boat.cycleSeat(player);
                }
            });
        });

        ServerPlayNetworking.registerGlobalReceiver(DeckOffsetPayload.TYPE, (payload, context) -> {
            ServerPlayer p = context.player();
            p.level().getServer().execute(() -> {
                if (payload.boatId() < 0) {
                    p.removeAttached(ModAttachments.DECK_ANCHOR);
                } else if (p.level().getEntity(payload.boatId()) instanceof CustomBoatEntity) {
                    p.setAttached(ModAttachments.DECK_ANCHOR,
                        new DeckSync.Anchor(payload.boatId(), payload.x(), payload.y(), payload.z()));
                }
            });
        });

        ServerPlayNetworking.registerGlobalReceiver(SailControlPayload.TYPE, (payload, context) -> {
            ServerPlayer player = context.player();
            player.level().getServer().execute(() -> {
                if (!(player.level().getEntity(payload.boatId()) instanceof CustomBoatEntity ship)) return;
                if (ship.getControllingPassenger() != player) return;   // only the helmsman
                switch (payload.action()) {
                    case SailControlPayload.RAISE  -> ship.raiseSail();
                    case SailControlPayload.LOWER  -> ship.lowerSail();
                    case SailControlPayload.ANCHOR -> ship.toggleAnchor();
                }
            });
        });
    }
 
    private ModNetworking() {} // no instances
}

