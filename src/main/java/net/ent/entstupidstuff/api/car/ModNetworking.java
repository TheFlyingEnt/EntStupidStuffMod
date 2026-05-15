package net.ent.entstupidstuff.api.car;

import net.ent.entstupidstuff.api.car.BaseCarEntity;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
 
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
    }
 
    private ModNetworking() {} // no instances
}

